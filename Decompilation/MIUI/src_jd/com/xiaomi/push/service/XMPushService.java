package com.xiaomi.push.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.log.LogUploader;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.push.service.timers.AlarmManagerTimer;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.HttpRequestProxy;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.stats.StatsHelper;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistration;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class XMPushService
  extends Service
  implements ConnectionListener
{
  public static int START_STICKY = 1;
  private ConnectionConfiguration connConfig;
  private long lastAlive = 0L;
  private ClientEventDispatcher mClientEventDispatcher;
  private Connection mCurrentConnection;
  private ConnectionJobController mJobController = null;
  private PacketListener mPacketListener = new PacketListener()
  {
    public void processPacket(Packet paramAnonymousPacket)
    {
      executeJob(new XMPushService.PacketReceiveJob(XMPushService.this, paramAnonymousPacket));
    }
  };
  private PacketSync mPacketSync = null;
  private ReconnectionManager mReconnManager;
  private AlarmManagerTimer mTimer = null;
  private XMPPConnection mXMPPConnection;
  
  static
  {
    HostManager.addReservedHost("app.chat.xiaomi.net", "42.62.94.2");
    HostManager.addReservedHost("app.chat.xiaomi.net", "app01.nodes.gslb.mi-idc.com");
    HostManager.addReservedHost("app.chat.xiaomi.net", "120.134.33.29");
    HostManager.addReservedHost("app.chat.xiaomi.net", "app02.nodes.gslb.mi-idc.com");
    XMPPConnection.DEBUG_ENABLED = true;
  }
  
  private void closeAllChannelByChid(String paramString, int paramInt)
  {
    Object localObject = PushClientsManager.getInstance().getAllClientLoginInfoByChid(paramString);
    if (localObject != null)
    {
      localObject = ((Collection)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PushClientsManager.ClientLoginInfo localClientLoginInfo = (PushClientsManager.ClientLoginInfo)((Iterator)localObject).next();
        if (localClientLoginInfo != null) {
          executeJob(new UnbindJob(localClientLoginInfo, paramInt, null, null));
        }
      }
    }
    PushClientsManager.getInstance().deactivateAllClientByChid(paramString);
  }
  
  private void connect()
  {
    if ((mCurrentConnection != null) && (mCurrentConnection.isConnecting()))
    {
      MyLog.e("try to connect while connecting.");
      return;
    }
    if ((mCurrentConnection != null) && (mCurrentConnection.isConnected()))
    {
      MyLog.e("try to connect while is connected.");
      return;
    }
    connConfig.setConnectionPoint(Network.getActiveConnPoint(this));
    connectByXMPP();
    if (mCurrentConnection == null)
    {
      PushClientsManager.getInstance().notifyConnectionFailed(this);
      sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
      return;
    }
    sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
  }
  
  private void connectByXMPP()
  {
    try
    {
      mXMPPConnection.addPacketListener(mPacketListener, new PacketFilter()
      {
        public boolean accept(Packet paramAnonymousPacket)
        {
          return true;
        }
      });
      mXMPPConnection.connect();
      mCurrentConnection = mXMPPConnection;
      return;
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e("fail to create xmpp connection", localXMPPException);
      mXMPPConnection.disconnect(new Presence(Presence.Type.unavailable), 3, localXMPPException);
    }
  }
  
  private Message encrypt(Message paramMessage, String paramString)
  {
    Object localObject = RC4Cryption.generateKeyForRC4(paramString, paramMessage.getPacketID());
    paramString = new Message();
    paramString.setFrom(paramMessage.getFrom());
    paramString.setTo(paramMessage.getTo());
    paramString.setPacketID(paramMessage.getPacketID());
    paramString.setChannelId(paramMessage.getChannelId());
    paramString.setEncrypted(true);
    paramMessage = RC4Cryption.encrypt((byte[])localObject, StringUtils.stripInvalidXMLChars(paramMessage.toXML()));
    localObject = new CommonPacketExtension("s", null, (String[])null, (String[])null);
    ((CommonPacketExtension)localObject).setText(paramMessage);
    paramString.addExtension((CommonPacketExtension)localObject);
    return paramString;
  }
  
  private String generatePingString(String paramString)
  {
    return "<iq to='" + paramString + "' id='0' chid='0' type='get'><ping xmlns='urn:xmpp:ping'>%1$s%2$s</ping></iq>";
  }
  
  private void prepareMIPushAccount()
  {
    if (MIPushAccountUtils.getMIPushAccount(getApplicationContext()) != null)
    {
      PushClientsManager.ClientLoginInfo localClientLoginInfo = MIPushAccountUtils.getMIPushAccount(getApplicationContext()).toClientLoginInfo(this);
      prepareMIPushClientLoginInfo(localClientLoginInfo);
      PushClientsManager.getInstance().addActiveClient(localClientLoginInfo);
      if (Network.hasNetwork(getApplicationContext())) {
        scheduleConnect(true);
      }
    }
  }
  
  private Packet preparePacket(Packet paramPacket, String paramString1, String paramString2, boolean paramBoolean)
  {
    PushClientsManager localPushClientsManager = PushClientsManager.getInstance();
    List localList = localPushClientsManager.queryChannelIdByPackage(paramString1);
    if (localList.isEmpty())
    {
      MyLog.warn("open channel should be called first before sending a packet, pkg=" + paramString1);
      paramString1 = null;
    }
    Object localObject;
    do
    {
      do
      {
        return paramString1;
        paramPacket.setPackageName(paramString1);
        localObject = paramPacket.getChannelId();
        paramString1 = (String)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          paramString1 = (String)localList.get(0);
          paramPacket.setChannelId(paramString1);
        }
        localObject = localPushClientsManager.getClientLoginInfoByChidAndUserId(paramString1, paramPacket.getFrom());
        if (!isConnected())
        {
          MyLog.warn("drop a packet as the channel is not connected, chid=" + paramString1);
          break;
        }
        if ((localObject == null) || (status != PushClientsManager.ClientStatus.binded))
        {
          MyLog.warn("drop a packet as the channel is not opened, chid=" + paramString1);
          break;
        }
        if (!TextUtils.equals(paramString2, session))
        {
          MyLog.warn("invalid session. " + paramString2);
          break;
        }
        paramString1 = paramPacket;
      } while (!(paramPacket instanceof Message));
      paramString1 = paramPacket;
    } while (!paramBoolean);
    return encrypt((Message)paramPacket, security);
  }
  
  private boolean shouldRebind(String paramString, Intent paramIntent)
  {
    Object localObject = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
    localObject = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(paramString, (String)localObject);
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = bool3;
    if (localObject != null)
    {
      bool1 = bool3;
      if (paramString != null)
      {
        paramString = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
        paramIntent = paramIntent.getStringExtra(PushConstants.EXTRA_SECURITY);
        bool1 = bool2;
        if (!TextUtils.isEmpty(session))
        {
          bool1 = bool2;
          if (!TextUtils.equals(paramString, session))
          {
            MyLog.warn("session changed. old session=" + session + ", new session=" + paramString);
            bool1 = true;
          }
        }
        if (!paramIntent.equals(security))
        {
          MyLog.warn("security changed. ");
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  private void updateAlarmTimer()
  {
    if (shouldReconnect())
    {
      if (!mTimer.isAlive()) {
        mTimer.registerPing(true);
      }
      return;
    }
    mTimer.stop();
  }
  
  private PushClientsManager.ClientLoginInfo updatePushClient(String paramString, Intent paramIntent)
  {
    Object localObject = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
    localObject = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(paramString, (String)localObject);
    paramString = (String)localObject;
    if (localObject == null) {
      paramString = new PushClientsManager.ClientLoginInfo(this);
    }
    chid = paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
    userId = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
    token = paramIntent.getStringExtra(PushConstants.EXTRA_TOKEN);
    pkgName = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
    clientExtra = paramIntent.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
    cloudExtra = paramIntent.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
    kick = paramIntent.getBooleanExtra(PushConstants.EXTRA_KICK, false);
    security = paramIntent.getStringExtra(PushConstants.EXTRA_SECURITY);
    session = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
    authMethod = paramIntent.getStringExtra(PushConstants.EXTRA_AUTH_METHOD);
    mClientEventDispatcher = mClientEventDispatcher;
    context = getApplicationContext();
    PushClientsManager.getInstance().addActiveClient(paramString);
    return paramString;
  }
  
  public void batchSendPacket(Packet[] paramArrayOfPacket)
    throws XMPPException
  {
    if (mCurrentConnection != null)
    {
      mCurrentConnection.batchSendPacket(paramArrayOfPacket);
      return;
    }
    throw new XMPPException("try send msg while connection is null.");
  }
  
  public void closeChannel(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
  {
    PushClientsManager.ClientLoginInfo localClientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(paramString1, paramString2);
    if (localClientLoginInfo != null) {
      executeJob(new UnbindJob(localClientLoginInfo, paramInt, paramString4, paramString3));
    }
    PushClientsManager.getInstance().deactivateClient(paramString1, paramString2);
  }
  
  public void connectionClosed(Connection paramConnection, int paramInt, Exception paramException)
  {
    scheduleConnect(false);
  }
  
  public void connectionStarted(Connection paramConnection)
  {
    MyLog.v("begin to connect...");
  }
  
  public Message constructMIPushMessage(XmPushActionContainer paramXmPushActionContainer)
  {
    try
    {
      Message localMessage = new Message();
      localMessage.setChannelId("5");
      localMessage.setTo("xiaomi.com");
      localMessage.setFrom(getMIPushAccountaccount);
      localMessage.setEncrypted(true);
      localMessage.setType("push");
      localMessage.setPackageName(packageName);
      Object localObject = getMIPushAccountaccount;
      target.userId = ((String)localObject).substring(0, ((String)localObject).indexOf("@"));
      target.resource = ((String)localObject).substring(((String)localObject).indexOf("/") + 1);
      localObject = XmPushThriftSerializeUtils.convertThriftObjectToBytes(paramXmPushActionContainer);
      localObject = String.valueOf(Base64Coder.encode(RC4Cryption.encrypt(RC4Cryption.generateKeyForRC4(getMIPushAccountsecurity, localMessage.getPacketID()), (byte[])localObject)));
      CommonPacketExtension localCommonPacketExtension = new CommonPacketExtension("s", null, (String[])null, (String[])null);
      localCommonPacketExtension.setText((String)localObject);
      localMessage.addExtension(localCommonPacketExtension);
      MyLog.warn("try send mi push message. packagename:" + packageName + " action:" + action);
      return localMessage;
    }
    catch (NullPointerException paramXmPushActionContainer)
    {
      MyLog.e(paramXmPushActionContainer);
    }
    return null;
  }
  
  public Message constructMIPushMessage(byte[] paramArrayOfByte)
  {
    XmPushActionContainer localXmPushActionContainer = new XmPushActionContainer();
    try
    {
      XmPushThriftSerializeUtils.convertByteArrayToThriftObject(localXmPushActionContainer, paramArrayOfByte);
      paramArrayOfByte = constructMIPushMessage(localXmPushActionContainer);
      return paramArrayOfByte;
    }
    catch (TException paramArrayOfByte)
    {
      MyLog.e(paramArrayOfByte);
    }
    return null;
  }
  
  public XmPushActionContainer contructAppAbsentMessage(String paramString1, String paramString2)
  {
    XmPushActionNotification localXmPushActionNotification = new XmPushActionNotification();
    localXmPushActionNotification.setAppId(paramString2);
    localXmPushActionNotification.setType("package uninstalled");
    localXmPushActionNotification.setId(Packet.nextID());
    localXmPushActionNotification.setRequireAck(false);
    return generateRequestContainer(paramString1, paramString2, localXmPushActionNotification, ActionType.Notification);
  }
  
  public ClientEventDispatcher createClientEventDispatcher()
  {
    return new ClientEventDispatcher();
  }
  
  public XMPPConnection createXMPPConnection(ConnectionConfiguration paramConnectionConfiguration)
  {
    return new XMPPConnection(this, paramConnectionConfiguration);
  }
  
  public void disconnect(int paramInt, Exception paramException)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("disconnect ").append(hashCode()).append(", ");
    if (mCurrentConnection == null) {}
    for (Object localObject = null;; localObject = Integer.valueOf(mCurrentConnection.hashCode()))
    {
      MyLog.warn(localObject);
      if (mCurrentConnection != null)
      {
        mCurrentConnection.disconnect(new Presence(Presence.Type.unavailable), paramInt, paramException);
        mCurrentConnection = null;
      }
      removeJobs(7);
      removeJobs(4);
      PushClientsManager.getInstance().resetAllClients(this, paramInt);
      return;
    }
  }
  
  public void executeJob(Job paramJob)
  {
    executeJobDelayed(paramJob, 0L);
  }
  
  public void executeJobDelayed(Job paramJob, long paramLong)
  {
    mJobController.executeJobDelayed(paramJob, paramLong);
  }
  
  public <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(String paramString1, String paramString2, T paramT, ActionType paramActionType)
  {
    paramT = XmPushThriftSerializeUtils.convertThriftObjectToBytes(paramT);
    XmPushActionContainer localXmPushActionContainer = new XmPushActionContainer();
    Target localTarget = new Target();
    channelId = 5L;
    userId = "fakeid";
    localXmPushActionContainer.setTarget(localTarget);
    localXmPushActionContainer.setPushAction(ByteBuffer.wrap(paramT));
    localXmPushActionContainer.setAction(paramActionType);
    localXmPushActionContainer.setIsRequest(true);
    localXmPushActionContainer.setPackageName(paramString1);
    localXmPushActionContainer.setEncryptAction(false);
    localXmPushActionContainer.setAppid(paramString2);
    return localXmPushActionContainer;
  }
  
  public ClientEventDispatcher getClientEventDispatcher()
  {
    return mClientEventDispatcher;
  }
  
  public Connection getCurrentConnection()
  {
    return mCurrentConnection;
  }
  
  public boolean hasJob(int paramInt)
  {
    return mJobController.hasJob(paramInt);
  }
  
  public boolean isConnected()
  {
    return (mCurrentConnection != null) && (mCurrentConnection.isConnected());
  }
  
  public boolean isConnecting()
  {
    return (mCurrentConnection != null) && (mCurrentConnection.isConnecting());
  }
  
  public boolean isPushDisabled()
  {
    boolean bool1 = false;
    try
    {
      Object localObject = Class.forName("miui.os.Build");
      Field localField = ((Class)localObject).getField("IS_CM_CUSTOMIZATION_TEST");
      localObject = ((Class)localObject).getField("IS_CU_CUSTOMIZATION_TEST");
      if (!localField.getBoolean(null))
      {
        boolean bool2 = ((Field)localObject).getBoolean(null);
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return new MessageChannel();
  }
  
  public void onCreate()
  {
    super.onCreate();
    SystemUtils.initialize(this);
    Object localObject = MIPushAccountUtils.getMIPushAccount(this);
    if (localObject != null) {
      BuildSettings.setEnvType(envType);
    }
    PushHostManagerFactory.init(this);
    connConfig = new ConnectionConfiguration(null, 5222, "xiaomi.com", null)
    {
      public byte[] getConnectionBlob()
      {
        try
        {
          Object localObject = new ChannelMessage.PushServiceConfigMsg();
          ((ChannelMessage.PushServiceConfigMsg)localObject).setClientVersion(ServiceConfig.getInstance().getConfigVersion());
          localObject = ((ChannelMessage.PushServiceConfigMsg)localObject).toByteArray();
          return (byte[])localObject;
        }
        catch (Exception localException)
        {
          MyLog.warn("getOBBString err: " + localException.toString());
        }
        return null;
      }
    };
    connConfig.setDebuggerEnabled(true);
    mXMPPConnection = createXMPPConnection(connConfig);
    mXMPPConnection.setPingString(generatePingString("xiaomi.com"));
    new Fallback("mibind.chat.gslb.mi-idc.com");
    mClientEventDispatcher = createClientEventDispatcher();
    try
    {
      if (TextUtils.equals((String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class }).invoke(null, new Object[] { "sys.boot_completed" }), "1")) {
        mClientEventDispatcher.notifyServiceStarted(this);
      }
      mTimer = new AlarmManagerTimer(this);
      mXMPPConnection.addConnectionListener(this);
      mPacketSync = new PacketSync(this);
      mReconnManager = new ReconnectionManager(this);
      new CommonPacketExtensionProvider().register();
      mJobController = new ConnectionJobController("Connection Controller Thread");
      mJobController.start();
      executeJob(new Job(11)
      {
        public String getDesc()
        {
          return "prepare the mi push account.";
        }
        
        public void process()
        {
          StatsHandler.getInstance().init(XMPushService.this, mXMPPConnection);
          XMPushService.this.prepareMIPushAccount();
        }
      });
      localObject = PushClientsManager.getInstance();
      ((PushClientsManager)localObject).removeAllClientChangeListeners();
      ((PushClientsManager)localObject).addClientChangeListener(new PushClientsManager.ClientChangeListener()
      {
        public void onChange()
        {
          XMPushService.this.updateAlarmTimer();
          if (PushClientsManager.getInstance().getActiveClientCount() <= 0) {
            executeJob(new XMPushService.DisconnectJob(XMPushService.this, 12, null));
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        MyLog.e(localException);
      }
    }
  }
  
  public void onDestroy()
  {
    mJobController.removeAllJobs();
    executeJob(new Job(2)
    {
      public String getDesc()
      {
        return "disconnect for service destroy.";
      }
      
      public void process()
      {
        if (mCurrentConnection != null)
        {
          mCurrentConnection.disconnect(new Presence(Presence.Type.unavailable), 15, null);
          XMPushService.access$402(XMPushService.this, null);
        }
      }
    });
    executeJob(new KillJob());
    PushClientsManager.getInstance().removeAllClientChangeListeners();
    PushClientsManager.getInstance().resetAllClients(this, 15);
    PushClientsManager.getInstance().removeActiveClients();
    mXMPPConnection.removeConnectionListener(this);
    ServiceConfig.getInstance().clear();
    mTimer.stop();
    super.onDestroy();
    MyLog.warn("Service destroyed");
  }
  
  public void onStart(Intent paramIntent, final int paramInt)
  {
    Object localObject4;
    Object localObject1;
    if (paramIntent == null)
    {
      MyLog.e("onStart() with intent NULL");
      localObject4 = PushClientsManager.getInstance();
      if ((paramIntent != null) && (paramIntent.getAction() != null))
      {
        if ((!PushConstants.ACTION_OPEN_CHANNEL.equalsIgnoreCase(paramIntent.getAction())) && (!PushConstants.ACTION_FORCE_RECONNECT.equalsIgnoreCase(paramIntent.getAction()))) {
          break label280;
        }
        localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
        if (!TextUtils.isEmpty(paramIntent.getStringExtra(PushConstants.EXTRA_SECURITY))) {
          break label114;
        }
        MyLog.warn("security is empty. ignore.");
      }
    }
    label114:
    label280:
    label600:
    label867:
    label1080:
    label1376:
    label1378:
    label1650:
    label1687:
    label1705:
    label1972:
    label2035:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                boolean bool;
                final Object localObject2;
                do
                {
                  do
                  {
                    for (;;)
                    {
                      return;
                      MyLog.v(String.format("onStart() with intent.Action = %s, chid = %s", new Object[] { paramIntent.getAction(), paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID) }));
                      break;
                      if (localObject1 != null)
                      {
                        bool = shouldRebind((String)localObject1, paramIntent);
                        paramIntent = updatePushClient((String)localObject1, paramIntent);
                        if (!Network.hasNetwork(this))
                        {
                          mClientEventDispatcher.notifyChannelOpenResult(this, paramIntent, false, 2, null);
                          return;
                        }
                        if (isConnected())
                        {
                          if (status == PushClientsManager.ClientStatus.unbind)
                          {
                            executeJob(new BindJob(paramIntent));
                            return;
                          }
                          if (bool)
                          {
                            executeJob(new ReBindJob(paramIntent));
                            return;
                          }
                          if (status == PushClientsManager.ClientStatus.binding)
                          {
                            MyLog.warn(String.format("the client is binding. %1$s %2$s.", new Object[] { chid, userId }));
                            return;
                          }
                          if (status == PushClientsManager.ClientStatus.binded) {
                            mClientEventDispatcher.notifyChannelOpenResult(this, paramIntent, true, 0, null);
                          }
                        }
                        else
                        {
                          scheduleConnect(true);
                        }
                      }
                      else
                      {
                        MyLog.e("channel id is empty, do nothing!");
                        return;
                        if (PushConstants.ACTION_CLOSE_CHANNEL.equalsIgnoreCase(paramIntent.getAction()))
                        {
                          localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                          localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                          paramIntent = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
                          if (TextUtils.isEmpty((CharSequence)localObject3))
                          {
                            paramIntent = ((PushClientsManager)localObject4).queryChannelIdByPackage((String)localObject1).iterator();
                            while (paramIntent.hasNext()) {
                              closeAllChannelByChid((String)paramIntent.next(), 2);
                            }
                          }
                          else
                          {
                            if (TextUtils.isEmpty(paramIntent))
                            {
                              closeAllChannelByChid((String)localObject3, 2);
                              return;
                            }
                            closeChannel((String)localObject3, paramIntent, 2, null, null);
                          }
                        }
                        else if (PushConstants.ACTION_SEND_MESSAGE.equalsIgnoreCase(paramIntent.getAction()))
                        {
                          localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                          localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
                          localObject4 = paramIntent.getBundleExtra("ext_packet");
                          bool = paramIntent.getBooleanExtra("ext_encrypt", true);
                          paramIntent = preparePacket(new Message((Bundle)localObject4), (String)localObject1, (String)localObject3, bool);
                          if (paramIntent != null) {
                            executeJob(new SendMessageJob(this, paramIntent));
                          }
                        }
                        else
                        {
                          Object localObject5;
                          if (PushConstants.ACTION_BATCH_SEND_MESSAGE.equalsIgnoreCase(paramIntent.getAction()))
                          {
                            localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                            localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
                            localObject4 = paramIntent.getParcelableArrayExtra("ext_packets");
                            localObject5 = new Message[localObject4.length];
                            bool = paramIntent.getBooleanExtra("ext_encrypt", true);
                            paramInt = 0;
                            for (;;)
                            {
                              if (paramInt >= localObject4.length) {
                                break label600;
                              }
                              localObject5[paramInt] = new Message((Bundle)localObject4[paramInt]);
                              localObject5[paramInt] = ((Message)preparePacket(localObject5[paramInt], (String)localObject1, (String)localObject3, bool));
                              if (localObject5[paramInt] == null) {
                                break;
                              }
                              paramInt += 1;
                            }
                            executeJob(new BatchSendMessageJob(this, (Message[])localObject5));
                            return;
                          }
                          if (PushConstants.ACTION_SEND_IQ.equalsIgnoreCase(paramIntent.getAction()))
                          {
                            localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                            localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
                            paramIntent = new IQ(paramIntent.getBundleExtra("ext_packet"));
                            if (preparePacket(paramIntent, (String)localObject1, (String)localObject3, false) != null) {
                              executeJob(new SendMessageJob(this, paramIntent));
                            }
                          }
                          else if (PushConstants.ACTION_SEND_PRESENCE.equalsIgnoreCase(paramIntent.getAction()))
                          {
                            localObject1 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                            localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_SESSION);
                            paramIntent = new Presence(paramIntent.getBundleExtra("ext_packet"));
                            if (preparePacket(paramIntent, (String)localObject1, (String)localObject3, false) != null) {
                              executeJob(new SendMessageJob(this, paramIntent));
                            }
                          }
                          else
                          {
                            if (("com.xiaomi.push.timer".equalsIgnoreCase(paramIntent.getAction())) || ("com.xiaomi.push.check_alive".equalsIgnoreCase(paramIntent.getAction())))
                            {
                              if ("com.xiaomi.push.timer".equalsIgnoreCase(paramIntent.getAction())) {
                                MyLog.warn("Service called on timer");
                              }
                              for (;;)
                              {
                                if (!mJobController.isBlocked()) {
                                  break label867;
                                }
                                MyLog.e("ERROR, the job controller is blocked.");
                                PushClientsManager.getInstance().resetAllClients(this, 14);
                                stopSelf();
                                return;
                                if (System.currentTimeMillis() - lastAlive < 30000L) {
                                  break;
                                }
                                lastAlive = System.currentTimeMillis();
                                MyLog.warn("Service called on check alive.");
                              }
                              if (!isConnected())
                              {
                                if ("com.xiaomi.push.timer".equalsIgnoreCase(paramIntent.getAction()))
                                {
                                  scheduleConnect(false);
                                  return;
                                }
                                scheduleConnect(true);
                                return;
                              }
                              if (mCurrentConnection.isReadAlive())
                              {
                                executeJob(new PingJob());
                                return;
                              }
                              executeJob(new DisconnectJob(17, null));
                              return;
                            }
                            if ("com.xiaomi.push.network_status_changed".equalsIgnoreCase(paramIntent.getAction()))
                            {
                              paramIntent = null;
                              try
                              {
                                localObject1 = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
                                paramIntent = (Intent)localObject1;
                              }
                              catch (Exception localException)
                              {
                                for (;;)
                                {
                                  MyLog.e(localException);
                                  continue;
                                  MyLog.warn("network changed, no active network");
                                  continue;
                                  executeJob(new DisconnectJob(2, null));
                                }
                              }
                              if (paramIntent != null)
                              {
                                MyLog.warn("network changed, " + paramIntent.toString());
                                mXMPPConnection.clearCachedStatus();
                                if (!Network.hasNetwork(this)) {
                                  break label1080;
                                }
                                if ((!isConnected()) && (!isConnecting()))
                                {
                                  mJobController.removeJobs(1);
                                  executeJob(new ConnectJob());
                                }
                                LogUploader.getInstance(this).checkUpload();
                                updateAlarmTimer();
                              }
                            }
                            if (PushConstants.ACTION_RESET_CONNECTION.equals(paramIntent.getAction()))
                            {
                              localObject2 = paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                              if (localObject2 != null) {
                                updatePushClient((String)localObject2, paramIntent);
                              }
                              executeJob(new ResetConnectionJob());
                              return;
                            }
                            if (!PushConstants.ACTION_UPDATE_CHANNEL_INFO.equals(paramIntent.getAction())) {
                              break label1378;
                            }
                            localObject2 = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
                            List localList = ((PushClientsManager)localObject4).queryChannelIdByPackage((String)localObject2);
                            if (localList.isEmpty())
                            {
                              MyLog.warn("open channel should be called first before update info, pkg=" + (String)localObject2);
                              return;
                            }
                            localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
                            localObject5 = paramIntent.getStringExtra(PushConstants.EXTRA_USER_ID);
                            localObject2 = localObject3;
                            if (TextUtils.isEmpty((CharSequence)localObject3)) {
                              localObject2 = (String)localList.get(0);
                            }
                            localObject3 = null;
                            if (TextUtils.isEmpty((CharSequence)localObject5))
                            {
                              localObject4 = ((PushClientsManager)localObject4).getAllClientLoginInfoByChid((String)localObject2);
                              localObject2 = localObject3;
                              if (localObject4 != null)
                              {
                                localObject2 = localObject3;
                                if (((Collection)localObject4).isEmpty()) {}
                              }
                            }
                            for (localObject2 = (PushClientsManager.ClientLoginInfo)((Collection)localObject4).iterator().next();; localObject2 = ((PushClientsManager)localObject4).getClientLoginInfoByChidAndUserId((String)localObject2, (String)localObject5))
                            {
                              if (localObject2 == null) {
                                break label1376;
                              }
                              if (paramIntent.hasExtra(PushConstants.EXTRA_CLIENT_ATTR)) {
                                clientExtra = paramIntent.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
                              }
                              if (!paramIntent.hasExtra(PushConstants.EXTRA_CLOUD_ATTR)) {
                                break;
                              }
                              cloudExtra = paramIntent.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
                              return;
                            }
                          }
                        }
                      }
                    }
                    if ("com.xiaomi.mipush.REGISTER_APP".equals(paramIntent.getAction()))
                    {
                      if ((PushProvision.getInstance(getApplicationContext()).checkProvisioned()) && (PushProvision.getInstance(getApplicationContext()).getProvisioned() == 0))
                      {
                        MyLog.warn("register without being provisioned. " + paramIntent.getStringExtra("mipush_app_package"));
                        return;
                      }
                      localObject2 = paramIntent.getByteArrayExtra("mipush_payload");
                      localObject3 = paramIntent.getStringExtra("mipush_app_package");
                      bool = paramIntent.getBooleanExtra("mipush_env_chanage", false);
                      paramInt = paramIntent.getIntExtra("mipush_env_type", 1);
                      MIPushAppInfo.getInstance(this).removeUnRegisteredPkg((String)localObject3);
                      if ((bool) && (!"com.xiaomi.xmsf".equals(getPackageName())))
                      {
                        executeJob(new Job(14)
                        {
                          public String getDesc()
                          {
                            return "clear account cache.";
                          }
                          
                          public void process()
                          {
                            MIPushAccountUtils.clearAccount(XMPushService.this);
                            PushClientsManager.getInstance().deactivateAllClientByChid("5");
                            BuildSettings.setEnvType(paramInt);
                            connConfig.setHost(ConnectionConfiguration.getXmppServerHost());
                            registerForMiPushApp(localObject2, localObject3);
                          }
                        });
                        return;
                      }
                      registerForMiPushApp((byte[])localObject2, (String)localObject3);
                      return;
                    }
                    if ((!"com.xiaomi.mipush.SEND_MESSAGE".equals(paramIntent.getAction())) && (!"com.xiaomi.mipush.UNREGISTER_APP".equals(paramIntent.getAction()))) {
                      break label1705;
                    }
                    localObject2 = paramIntent.getStringExtra("mipush_app_package");
                    localObject3 = paramIntent.getByteArrayExtra("mipush_payload");
                    bool = paramIntent.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    localObject4 = PushClientsManager.getInstance().getAllClientLoginInfoByChid("5");
                    if ("com.xiaomi.mipush.UNREGISTER_APP".equals(paramIntent.getAction())) {
                      MIPushAppInfo.getInstance(this).addUnRegisteredPkg((String)localObject2);
                    }
                    if (!((Collection)localObject4).isEmpty()) {
                      break label1650;
                    }
                  } while (!bool);
                  MIPushClientManager.addPendingMessages((String)localObject2, (byte[])localObject3);
                  return;
                  if (iteratornextstatus == PushClientsManager.ClientStatus.binded) {
                    break label1687;
                  }
                } while (!bool);
                MIPushClientManager.addPendingMessages((String)localObject2, (byte[])localObject3);
                return;
                executeJob(new Job(4)
                {
                  public String getDesc()
                  {
                    return "send mi push message";
                  }
                  
                  public void process()
                  {
                    try
                    {
                      sendMIPushPacket(localObject2, localObject3);
                      return;
                    }
                    catch (XMPPException localXMPPException)
                    {
                      MyLog.e(localXMPPException);
                      disconnect(10, localXMPPException);
                    }
                  }
                });
                return;
                if (!PushServiceConstants.ACTION_UNINSTALL.equals(paramIntent.getAction())) {
                  break label1972;
                }
                paramIntent = paramIntent.getStringExtra("uninstall_pkg_name");
              } while ((paramIntent == null) || (TextUtils.isEmpty(paramIntent.trim())));
              paramInt = 0;
              try
              {
                getPackageManager().getPackageInfo(paramIntent, 256);
                if (("com.xiaomi.channel".equals(paramIntent)) && (!PushClientsManager.getInstance().getAllClientLoginInfoByChid("1").isEmpty()) && (paramInt != 0))
                {
                  closeAllChannelByChid("1", 0);
                  MyLog.warn("close the miliao channel as the app is uninstalled.");
                  return;
                }
              }
              catch (PackageManager.NameNotFoundException localNameNotFoundException)
              {
                for (;;)
                {
                  paramInt = 1;
                }
                localObject3 = getSharedPreferences("pref_registered_pkg_names", 0);
                str = ((SharedPreferences)localObject3).getString(paramIntent, null);
              }
            } while ((TextUtils.isEmpty(str)) || (paramInt == 0));
            localObject3 = ((SharedPreferences)localObject3).edit();
            ((SharedPreferences.Editor)localObject3).remove(paramIntent);
            ((SharedPreferences.Editor)localObject3).commit();
            if (MIPushNotificationHelper.hasLocalNotifyType(this, paramIntent)) {
              MIPushNotificationHelper.clearLocalNotifyType(this, paramIntent);
            }
            MIPushNotificationHelper.clearNotification(this, paramIntent);
          } while ((!isConnected()) || (str == null));
          try
          {
            sendMIPushPacket(contructAppAbsentMessage(paramIntent, str));
            MyLog.warn("uninstall " + paramIntent + " msg sent");
            return;
          }
          catch (XMPPException paramIntent)
          {
            MyLog.e("Fail to send Message: " + paramIntent.getMessage());
            disconnect(10, paramIntent);
            return;
          }
          if (!"com.xiaomi.mipush.CLEAR_NOTIFICATION".equals(paramIntent.getAction())) {
            break label2035;
          }
          str = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
          paramInt = paramIntent.getIntExtra(PushConstants.EXTRA_NOTIFY_ID, 0);
        } while (TextUtils.isEmpty(str));
        if (paramInt >= 0)
        {
          MIPushNotificationHelper.clearNotification(this, str, paramInt);
          return;
        }
      } while (paramInt != -1);
      MIPushNotificationHelper.clearNotification(this, str);
      return;
    } while (!"com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals(paramIntent.getAction()));
    String str = paramIntent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
    final Object localObject3 = paramIntent.getStringExtra(PushConstants.EXTRA_SIG);
    int i = 0;
    paramInt = 0;
    if (paramIntent.hasExtra(PushConstants.EXTRA_NOTIFY_TYPE))
    {
      i = paramIntent.getIntExtra(PushConstants.EXTRA_NOTIFY_TYPE, 0);
      paramIntent = MD5.MD5_16(str + i);
    }
    while ((TextUtils.isEmpty(str)) || (!TextUtils.equals((CharSequence)localObject3, paramIntent)))
    {
      MyLog.e("invalid notification for " + str);
      return;
      paramIntent = MD5.MD5_16(str);
      paramInt = 1;
    }
    if (paramInt != 0)
    {
      MIPushNotificationHelper.clearLocalNotifyType(this, str);
      return;
    }
    MIPushNotificationHelper.setLocalNotifyType(this, str, i);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    onStart(paramIntent, paramInt2);
    return START_STICKY;
  }
  
  public void prepareMIPushClientLoginInfo(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    paramClientLoginInfo.addClientStatusListener(new PushClientsManager.ClientLoginInfo.ClientStatusListener()
    {
      public void onChange(PushClientsManager.ClientStatus paramAnonymousClientStatus1, PushClientsManager.ClientStatus paramAnonymousClientStatus2, int paramAnonymousInt)
      {
        if (paramAnonymousClientStatus2 == PushClientsManager.ClientStatus.binded)
        {
          MIPushClientManager.processPendingRegistrationRequest(XMPushService.this);
          MIPushClientManager.processPendingMessages(XMPushService.this);
        }
        while (paramAnonymousClientStatus2 != PushClientsManager.ClientStatus.unbind) {
          return;
        }
        MIPushClientManager.notifyRegisterError(XMPushService.this, 70000001, " the push is not connected.");
      }
    });
  }
  
  public void reconnectionFailed(Connection paramConnection, Exception paramException)
  {
    scheduleConnect(false);
  }
  
  public void reconnectionSuccessful(Connection paramConnection)
  {
    mReconnManager.onConnectSucceeded();
    paramConnection = PushClientsManager.getInstance().getAllClients().iterator();
    while (paramConnection.hasNext()) {
      executeJob(new BindJob((PushClientsManager.ClientLoginInfo)paramConnection.next()));
    }
  }
  
  public void registerForMiPushApp(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
    {
      MIPushClientManager.notifyError(this, paramString, paramArrayOfByte, 70000003, "null payload");
      MyLog.warn("register request without payload");
      return;
    }
    XmPushActionContainer localXmPushActionContainer = new XmPushActionContainer();
    try
    {
      XmPushThriftSerializeUtils.convertByteArrayToThriftObject(localXmPushActionContainer, paramArrayOfByte);
      if (action == ActionType.Registration)
      {
        XmPushActionRegistration localXmPushActionRegistration = new XmPushActionRegistration();
        try
        {
          XmPushThriftSerializeUtils.convertByteArrayToThriftObject(localXmPushActionRegistration, localXmPushActionContainer.getPushAction());
          MIPushClientManager.registerApp(localXmPushActionContainer.getPackageName(), paramArrayOfByte);
          executeJob(new MIPushAppRegisterJob(this, localXmPushActionContainer.getPackageName(), localXmPushActionRegistration.getAppId(), localXmPushActionRegistration.getToken(), paramArrayOfByte));
          return;
        }
        catch (TException localTException1)
        {
          MyLog.e(localTException1);
          MIPushClientManager.notifyError(this, paramString, paramArrayOfByte, 70000003, " data action error.");
          return;
        }
      }
      MIPushClientManager.notifyError(this, paramString, paramArrayOfByte, 70000003, " registration action required.");
    }
    catch (TException localTException2)
    {
      MyLog.e(localTException2);
      MIPushClientManager.notifyError(this, paramString, paramArrayOfByte, 70000003, " data container error.");
      return;
    }
    MyLog.warn("register request with invalid payload");
  }
  
  public void removeJobs(int paramInt)
  {
    mJobController.removeJobs(paramInt);
  }
  
  public void removeJobs(Job paramJob)
  {
    mJobController.removeJobs(type, paramJob);
  }
  
  public void scheduleConnect(boolean paramBoolean)
  {
    mReconnManager.tryReconnect(paramBoolean);
  }
  
  public void scheduleRebindChannel(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    if (paramClientLoginInfo != null)
    {
      long l = paramClientLoginInfo.getNextRetryInterval();
      MyLog.warn("schedule rebind job in " + l / 1000L);
      executeJobDelayed(new BindJob(paramClientLoginInfo), l);
    }
  }
  
  public void sendMIPushPacket(XmPushActionContainer paramXmPushActionContainer)
    throws XMPPException
  {
    if (mCurrentConnection != null)
    {
      paramXmPushActionContainer = constructMIPushMessage(paramXmPushActionContainer);
      if (paramXmPushActionContainer != null) {
        mCurrentConnection.sendPacket(paramXmPushActionContainer);
      }
      return;
    }
    throw new XMPPException("try send msg while connection is null.");
  }
  
  public void sendMIPushPacket(String paramString, byte[] paramArrayOfByte)
    throws XMPPException
  {
    if (mCurrentConnection != null)
    {
      Message localMessage = constructMIPushMessage(paramArrayOfByte);
      if (localMessage != null)
      {
        mCurrentConnection.sendPacket(localMessage);
        return;
      }
      MIPushClientManager.notifyError(this, paramString, paramArrayOfByte, 70000003, "not a valid message");
      return;
    }
    throw new XMPPException("try send msg while connection is null.");
  }
  
  public void sendPacket(Packet paramPacket)
    throws XMPPException
  {
    if (mCurrentConnection != null)
    {
      mCurrentConnection.sendPacket(paramPacket);
      return;
    }
    throw new XMPPException("try send msg while connection is null.");
  }
  
  public void setConnectingTimeout()
  {
    executeJobDelayed(new Job(10)
    {
      public String getDesc()
      {
        return "disconnect because of connecting timeout";
      }
      
      public void process()
      {
        disconnect(18, null);
      }
    }, 120000L);
  }
  
  public boolean shouldReconnect()
  {
    return (Network.hasNetwork(this)) && (PushClientsManager.getInstance().getActiveClientCount() > 0) && (!isPushDisabled());
  }
  
  class BindJob
    extends XMPushService.Job
  {
    PushClientsManager.ClientLoginInfo mLoginInfo = null;
    
    public BindJob(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
    {
      super();
      mLoginInfo = paramClientLoginInfo;
    }
    
    public String getDesc()
    {
      return "bind the client. " + mLoginInfo.chid + ", " + mLoginInfo.userId;
    }
    
    public void process()
    {
      try
      {
        if (!isConnected())
        {
          MyLog.e("trying bind while the connection is not created, quit!");
          return;
        }
        PushClientsManager.ClientLoginInfo localClientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(mLoginInfo.chid, mLoginInfo.userId);
        if (localClientLoginInfo == null)
        {
          MyLog.warn("ignore bind because the channel " + mLoginInfo.chid + " is removed ");
          return;
        }
      }
      catch (XMPPException localXMPPException)
      {
        MyLog.e(localXMPPException);
        disconnect(10, localXMPPException);
        return;
      }
      if (status == PushClientsManager.ClientStatus.unbind)
      {
        localXMPPException.setStatus(PushClientsManager.ClientStatus.binding, 0, 0, null, null);
        mCurrentConnection.bind(localXMPPException);
        StatsHelper.statsBind(XMPushService.this, localXMPPException);
        return;
      }
      MyLog.warn("trying duplicate bind, ingore! " + status);
    }
  }
  
  static class BindTimeoutJob
    extends XMPushService.Job
  {
    private final PushClientsManager.ClientLoginInfo mLoginInfo;
    
    public BindTimeoutJob(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
    {
      super();
      mLoginInfo = paramClientLoginInfo;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof BindTimeoutJob)) {
        return false;
      }
      return TextUtils.equals(mLoginInfo.chid, mLoginInfo.chid);
    }
    
    public String getDesc()
    {
      return "bind time out. chid=" + mLoginInfo.chid;
    }
    
    public int hashCode()
    {
      return mLoginInfo.chid.hashCode();
    }
    
    public void process()
    {
      mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 21, null, null);
    }
  }
  
  public class ConnectJob
    extends XMPushService.Job
  {
    ConnectJob()
    {
      super();
    }
    
    public String getDesc()
    {
      return "do reconnect..";
    }
    
    public void process()
    {
      if (shouldReconnect())
      {
        XMPushService.this.connect();
        return;
      }
      MyLog.warn("should not connect. quit the job.");
    }
  }
  
  public class DisconnectJob
    extends XMPushService.Job
  {
    public Exception e;
    public int reason;
    
    DisconnectJob(int paramInt, Exception paramException)
    {
      super();
      reason = paramInt;
      e = paramException;
    }
    
    public String getDesc()
    {
      return "disconnect the connection.";
    }
    
    public void process()
    {
      disconnect(reason, e);
    }
  }
  
  public static abstract class Job
  {
    protected int type;
    
    public Job(int paramInt)
    {
      type = paramInt;
    }
    
    public abstract String getDesc();
    
    public abstract void process();
    
    public void run()
    {
      if ((type != 4) && (type != 8)) {
        MyLog.warn("JOB: " + getDesc());
      }
      process();
    }
  }
  
  class KillJob
    extends XMPushService.Job
  {
    public KillJob()
    {
      super();
    }
    
    public String getDesc()
    {
      return "ask the job queue to quit";
    }
    
    public void process()
    {
      mJobController.quit();
    }
  }
  
  public class MessageChannel
    extends Binder
  {
    public MessageChannel() {}
  }
  
  class PacketReceiveJob
    extends XMPushService.Job
  {
    private Packet mPacket = null;
    
    public PacketReceiveJob(Packet paramPacket)
    {
      super();
      mPacket = paramPacket;
    }
    
    public String getDesc()
    {
      return "receive a message.";
    }
    
    public void process()
    {
      mPacketSync.onPacketReceive(mPacket);
    }
  }
  
  class PingJob
    extends XMPushService.Job
  {
    public PingJob()
    {
      super();
    }
    
    public String getDesc()
    {
      return "send ping..";
    }
    
    public void process()
    {
      if (isConnected()) {}
      try
      {
        StatsHelper.pingStarted();
        mCurrentConnection.sendPingString();
        return;
      }
      catch (XMPPException localXMPPException)
      {
        MyLog.e(localXMPPException);
        disconnect(10, localXMPPException);
      }
    }
  }
  
  class ReBindJob
    extends XMPushService.Job
  {
    PushClientsManager.ClientLoginInfo mLoginInfo = null;
    
    public ReBindJob(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
    {
      super();
      mLoginInfo = paramClientLoginInfo;
    }
    
    public String getDesc()
    {
      return "bind the client. " + mLoginInfo.chid + ", " + mLoginInfo.userId;
    }
    
    public void process()
    {
      try
      {
        mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 16, null, null);
        mCurrentConnection.unbind(mLoginInfo.chid, mLoginInfo.userId);
        mLoginInfo.setStatus(PushClientsManager.ClientStatus.binding, 1, 16, null, null);
        mCurrentConnection.bind(mLoginInfo);
        return;
      }
      catch (XMPPException localXMPPException)
      {
        MyLog.e(localXMPPException);
        disconnect(10, localXMPPException);
      }
    }
  }
  
  class ResetConnectionJob
    extends XMPushService.Job
  {
    ResetConnectionJob()
    {
      super();
    }
    
    public String getDesc()
    {
      return "reset the connection.";
    }
    
    public void process()
    {
      disconnect(11, null);
      if (shouldReconnect()) {
        XMPushService.this.connect();
      }
    }
  }
  
  class UnbindJob
    extends XMPushService.Job
  {
    String kickType;
    PushClientsManager.ClientLoginInfo mLoginInfo = null;
    int mNotifyType;
    String reason;
    
    public UnbindJob(PushClientsManager.ClientLoginInfo paramClientLoginInfo, int paramInt, String paramString1, String paramString2)
    {
      super();
      mLoginInfo = paramClientLoginInfo;
      mNotifyType = paramInt;
      kickType = paramString1;
      reason = paramString2;
    }
    
    public String getDesc()
    {
      return "unbind the channel. " + mLoginInfo.chid + ", " + mLoginInfo.userId;
    }
    
    public void process()
    {
      if ((mLoginInfo.status != PushClientsManager.ClientStatus.unbind) && (mCurrentConnection != null)) {}
      try
      {
        mCurrentConnection.unbind(mLoginInfo.chid, mLoginInfo.userId);
        mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, mNotifyType, 0, reason, kickType);
        return;
      }
      catch (XMPPException localXMPPException)
      {
        for (;;)
        {
          MyLog.e(localXMPPException);
          disconnect(10, localXMPPException);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */