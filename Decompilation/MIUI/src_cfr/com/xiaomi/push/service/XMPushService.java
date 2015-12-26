/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.os.Parcelable
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 *  java.nio.ByteBuffer
 *  java.util.ArrayList
 */
package com.xiaomi.push.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.log.LogUploader;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.BatchSendMessageJob;
import com.xiaomi.push.service.ClientEventDispatcher;
import com.xiaomi.push.service.CommonPacketExtensionProvider;
import com.xiaomi.push.service.ConnectionJobController;
import com.xiaomi.push.service.MIPushAccount;
import com.xiaomi.push.service.MIPushAccountUtils;
import com.xiaomi.push.service.MIPushAppInfo;
import com.xiaomi.push.service.MIPushAppRegisterJob;
import com.xiaomi.push.service.MIPushClientManager;
import com.xiaomi.push.service.MIPushNotificationHelper;
import com.xiaomi.push.service.PacketSync;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.PushHostManagerFactory;
import com.xiaomi.push.service.PushProvision;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.push.service.ReconnectionManager;
import com.xiaomi.push.service.SendMessageJob;
import com.xiaomi.push.service.ServiceConfig;
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
implements ConnectionListener {
    public static int START_STICKY;
    private ConnectionConfiguration connConfig;
    private long lastAlive = 0;
    private ClientEventDispatcher mClientEventDispatcher;
    private Connection mCurrentConnection;
    private ConnectionJobController mJobController = null;
    private PacketListener mPacketListener;
    private PacketSync mPacketSync = null;
    private ReconnectionManager mReconnManager;
    private AlarmManagerTimer mTimer = null;
    private XMPPConnection mXMPPConnection;

    static {
        HostManager.addReservedHost("app.chat.xiaomi.net", "42.62.94.2");
        HostManager.addReservedHost("app.chat.xiaomi.net", "app01.nodes.gslb.mi-idc.com");
        HostManager.addReservedHost("app.chat.xiaomi.net", "120.134.33.29");
        HostManager.addReservedHost("app.chat.xiaomi.net", "app02.nodes.gslb.mi-idc.com");
        XMPPConnection.DEBUG_ENABLED = true;
        START_STICKY = 1;
    }

    public XMPushService() {
        this.mPacketListener = new PacketListener(){

            @Override
            public void processPacket(Packet packet) {
                XMPushService.this.executeJob(new PacketReceiveJob(packet));
            }
        };
    }

    private void closeAllChannelByChid(String string2, int n) {
        Collection<PushClientsManager.ClientLoginInfo> collection = PushClientsManager.getInstance().getAllClientLoginInfoByChid(string2);
        if (collection != null) {
            for (PushClientsManager.ClientLoginInfo clientLoginInfo : collection) {
                if (clientLoginInfo == null) continue;
                this.executeJob(new UnbindJob(clientLoginInfo, n, null, null));
            }
        }
        PushClientsManager.getInstance().deactivateAllClientByChid(string2);
    }

    private void connect() {
        if (this.mCurrentConnection != null && this.mCurrentConnection.isConnecting()) {
            MyLog.e("try to connect while connecting.");
            return;
        }
        if (this.mCurrentConnection != null && this.mCurrentConnection.isConnected()) {
            MyLog.e("try to connect while is connected.");
            return;
        }
        this.connConfig.setConnectionPoint(Network.getActiveConnPoint((Context)this));
        this.connectByXMPP();
        if (this.mCurrentConnection == null) {
            PushClientsManager.getInstance().notifyConnectionFailed((Context)this);
            this.sendBroadcast(new Intent("miui.intent.action.NETWORK_BLOCKED"));
            return;
        }
        this.sendBroadcast(new Intent("miui.intent.action.NETWORK_CONNECTED"));
    }

    private void connectByXMPP() {
        try {
            this.mXMPPConnection.addPacketListener(this.mPacketListener, new PacketFilter(){

                @Override
                public boolean accept(Packet packet) {
                    return true;
                }
            });
            this.mXMPPConnection.connect();
            this.mCurrentConnection = this.mXMPPConnection;
            return;
        }
        catch (XMPPException var1_1) {
            MyLog.e("fail to create xmpp connection", var1_1);
            this.mXMPPConnection.disconnect(new Presence(Presence.Type.unavailable), 3, var1_1);
            return;
        }
    }

    private Message encrypt(Message object, String object2) {
        Object object3 = RC4Cryption.generateKeyForRC4((String)object2, object.getPacketID());
        object2 = new Message();
        object2.setFrom(object.getFrom());
        object2.setTo(object.getTo());
        object2.setPacketID(object.getPacketID());
        object2.setChannelId(object.getChannelId());
        object2.setEncrypted(true);
        object = RC4Cryption.encrypt((byte[])object3, StringUtils.stripInvalidXMLChars(object.toXML()));
        object3 = new CommonPacketExtension("s", null, (String[])null, (String[])null);
        object3.setText((String)object);
        object2.addExtension((CommonPacketExtension)object3);
        return object2;
    }

    private String generatePingString(String string2) {
        return "<iq to='" + string2 + "' id='0' chid='0' type='get'><ping xmlns='urn:xmpp:ping'>%1$s%2$s</ping></iq>";
    }

    private void prepareMIPushAccount() {
        if (MIPushAccountUtils.getMIPushAccount(this.getApplicationContext()) != null) {
            PushClientsManager.ClientLoginInfo clientLoginInfo = MIPushAccountUtils.getMIPushAccount(this.getApplicationContext()).toClientLoginInfo(this);
            this.prepareMIPushClientLoginInfo(clientLoginInfo);
            PushClientsManager.getInstance().addActiveClient(clientLoginInfo);
            if (Network.hasNetwork(this.getApplicationContext())) {
                this.scheduleConnect(true);
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Packet preparePacket(Packet packet, String object, String string2, boolean bl) {
        Object object2;
        PushClientsManager pushClientsManager = PushClientsManager.getInstance();
        List<String> list = pushClientsManager.queryChannelIdByPackage((String)object);
        if (list.isEmpty()) {
            MyLog.warn("open channel should be called first before sending a packet, pkg=" + (String)object);
            do {
                return null;
                break;
            } while (true);
        }
        packet.setPackageName((String)object);
        object = object2 = packet.getChannelId();
        if (TextUtils.isEmpty((CharSequence)object2)) {
            object = list.get(0);
            packet.setChannelId((String)object);
        }
        object2 = pushClientsManager.getClientLoginInfoByChidAndUserId((String)object, packet.getFrom());
        if (!this.isConnected()) {
            MyLog.warn("drop a packet as the channel is not connected, chid=" + (String)object);
            return null;
        }
        if (object2 == null || object2.status != PushClientsManager.ClientStatus.binded) {
            MyLog.warn("drop a packet as the channel is not opened, chid=" + (String)object);
            return null;
        }
        if (!TextUtils.equals((CharSequence)string2, (CharSequence)object2.session)) {
            MyLog.warn("invalid session. " + string2);
            return null;
        }
        object = packet;
        if (!(packet instanceof Message)) return object;
        object = packet;
        if (!bl) return object;
        return this.encrypt((Message)packet, object2.security);
    }

    private boolean shouldRebind(String string2, Intent object) {
        Object object2 = object.getStringExtra(PushConstants.EXTRA_USER_ID);
        object2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(string2, (String)object2);
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = bl;
        if (object2 != null) {
            bl3 = bl;
            if (string2 != null) {
                string2 = object.getStringExtra(PushConstants.EXTRA_SESSION);
                object = object.getStringExtra(PushConstants.EXTRA_SECURITY);
                bl3 = bl2;
                if (!TextUtils.isEmpty((CharSequence)object2.session)) {
                    bl3 = bl2;
                    if (!TextUtils.equals((CharSequence)string2, (CharSequence)object2.session)) {
                        MyLog.warn("session changed. old session=" + object2.session + ", new session=" + string2);
                        bl3 = true;
                    }
                }
                if (!object.equals((Object)object2.security)) {
                    MyLog.warn("security changed. ");
                    bl3 = true;
                }
            }
        }
        return bl3;
    }

    private void updateAlarmTimer() {
        if (this.shouldReconnect()) {
            if (!this.mTimer.isAlive()) {
                this.mTimer.registerPing(true);
            }
            return;
        }
        this.mTimer.stop();
    }

    private PushClientsManager.ClientLoginInfo updatePushClient(String object, Intent intent) {
        Object object2 = intent.getStringExtra(PushConstants.EXTRA_USER_ID);
        object = object2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId((String)object, (String)object2);
        if (object2 == null) {
            object = new PushClientsManager.ClientLoginInfo(this);
        }
        object.chid = intent.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
        object.userId = intent.getStringExtra(PushConstants.EXTRA_USER_ID);
        object.token = intent.getStringExtra(PushConstants.EXTRA_TOKEN);
        object.pkgName = intent.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
        object.clientExtra = intent.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
        object.cloudExtra = intent.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
        object.kick = intent.getBooleanExtra(PushConstants.EXTRA_KICK, false);
        object.security = intent.getStringExtra(PushConstants.EXTRA_SECURITY);
        object.session = intent.getStringExtra(PushConstants.EXTRA_SESSION);
        object.authMethod = intent.getStringExtra(PushConstants.EXTRA_AUTH_METHOD);
        object.mClientEventDispatcher = this.mClientEventDispatcher;
        object.context = this.getApplicationContext();
        PushClientsManager.getInstance().addActiveClient((PushClientsManager.ClientLoginInfo)object);
        return object;
    }

    public void batchSendPacket(Packet[] arrpacket) throws XMPPException {
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.batchSendPacket(arrpacket);
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void closeChannel(String string2, String string3, int n, String string4, String string5) {
        PushClientsManager.ClientLoginInfo clientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(string2, string3);
        if (clientLoginInfo != null) {
            this.executeJob(new UnbindJob(clientLoginInfo, n, string5, string4));
        }
        PushClientsManager.getInstance().deactivateClient(string2, string3);
    }

    @Override
    public void connectionClosed(Connection connection, int n, Exception exception) {
        this.scheduleConnect(false);
    }

    @Override
    public void connectionStarted(Connection connection) {
        MyLog.v("begin to connect...");
    }

    public Message constructMIPushMessage(XmPushActionContainer xmPushActionContainer) {
        try {
            Message message = new Message();
            message.setChannelId("5");
            message.setTo("xiaomi.com");
            message.setFrom(MIPushAccountUtils.getMIPushAccount((Context)this).account);
            message.setEncrypted(true);
            message.setType("push");
            message.setPackageName(xmPushActionContainer.packageName);
            String string2 = MIPushAccountUtils.getMIPushAccount((Context)this).account;
            xmPushActionContainer.target.userId = string2.substring(0, string2.indexOf("@"));
            xmPushActionContainer.target.resource = string2.substring(string2.indexOf("/") + 1);
            string2 = (String)XmPushThriftSerializeUtils.convertThriftObjectToBytes(xmPushActionContainer);
            string2 = String.valueOf((char[])Base64Coder.encode(RC4Cryption.encrypt(RC4Cryption.generateKeyForRC4(MIPushAccountUtils.getMIPushAccount((Context)this).security, message.getPacketID()), (byte[])string2)));
            CommonPacketExtension commonPacketExtension = new CommonPacketExtension("s", null, (String[])null, (String[])null);
            commonPacketExtension.setText(string2);
            message.addExtension(commonPacketExtension);
            MyLog.warn("try send mi push message. packagename:" + xmPushActionContainer.packageName + " action:" + (Object)((Object)xmPushActionContainer.action));
            return message;
        }
        catch (NullPointerException var1_2) {
            MyLog.e(var1_2);
            return null;
        }
    }

    public Message constructMIPushMessage(byte[] object) {
        XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
        try {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(xmPushActionContainer, (byte[])object);
            object = this.constructMIPushMessage(xmPushActionContainer);
            return object;
        }
        catch (TException var1_2) {
            MyLog.e(var1_2);
            return null;
        }
    }

    public XmPushActionContainer contructAppAbsentMessage(String string2, String string3) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        xmPushActionNotification.setAppId(string3);
        xmPushActionNotification.setType("package uninstalled");
        xmPushActionNotification.setId(Packet.nextID());
        xmPushActionNotification.setRequireAck(false);
        return this.generateRequestContainer(string2, string3, xmPushActionNotification, ActionType.Notification);
    }

    public ClientEventDispatcher createClientEventDispatcher() {
        return new ClientEventDispatcher();
    }

    public XMPPConnection createXMPPConnection(ConnectionConfiguration connectionConfiguration) {
        return new XMPPConnection(this, connectionConfiguration);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void disconnect(int n, Exception exception) {
        StringBuilder stringBuilder = new StringBuilder().append("disconnect ").append(this.hashCode()).append(", ");
        Integer n2 = this.mCurrentConnection == null ? null : Integer.valueOf((int)this.mCurrentConnection.hashCode());
        MyLog.warn(stringBuilder.append((Object)n2).toString());
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.disconnect(new Presence(Presence.Type.unavailable), n, exception);
            this.mCurrentConnection = null;
        }
        this.removeJobs(7);
        this.removeJobs(4);
        PushClientsManager.getInstance().resetAllClients((Context)this, n);
    }

    public void executeJob(Job job) {
        this.executeJobDelayed(job, 0);
    }

    public void executeJobDelayed(Job job, long l) {
        this.mJobController.executeJobDelayed(job, l);
    }

    public <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(String string2, String string3, T object, ActionType actionType) {
        object = XmPushThriftSerializeUtils.convertThriftObjectToBytes(object);
        XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
        Target target = new Target();
        target.channelId = 5;
        target.userId = "fakeid";
        xmPushActionContainer.setTarget(target);
        xmPushActionContainer.setPushAction(ByteBuffer.wrap((byte[])object));
        xmPushActionContainer.setAction(actionType);
        xmPushActionContainer.setIsRequest(true);
        xmPushActionContainer.setPackageName(string2);
        xmPushActionContainer.setEncryptAction(false);
        xmPushActionContainer.setAppid(string3);
        return xmPushActionContainer;
    }

    public ClientEventDispatcher getClientEventDispatcher() {
        return this.mClientEventDispatcher;
    }

    public Connection getCurrentConnection() {
        return this.mCurrentConnection;
    }

    public boolean hasJob(int n) {
        return this.mJobController.hasJob(n);
    }

    public boolean isConnected() {
        if (this.mCurrentConnection != null && this.mCurrentConnection.isConnected()) {
            return true;
        }
        return false;
    }

    public boolean isConnecting() {
        if (this.mCurrentConnection != null && this.mCurrentConnection.isConnecting()) {
            return true;
        }
        return false;
    }

    public boolean isPushDisabled() {
        boolean bl = false;
        try {
            boolean bl2;
            Class class_ = Class.forName((String)"miui.os.Build");
            Field field = class_.getField("IS_CM_CUSTOMIZATION_TEST");
            class_ = class_.getField("IS_CU_CUSTOMIZATION_TEST");
            if (field.getBoolean((Object)null) || (bl2 = class_.getBoolean((Object)null))) {
                bl = true;
            }
            return bl;
        }
        catch (Throwable var3_4) {
            return false;
        }
    }

    public IBinder onBind(Intent intent) {
        return new MessageChannel();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onCreate() {
        super.onCreate();
        SystemUtils.initialize((Context)this);
        Object object = MIPushAccountUtils.getMIPushAccount((Context)this);
        if (object != null) {
            BuildSettings.setEnvType(object.envType);
        }
        PushHostManagerFactory.init(this);
        this.connConfig = new ConnectionConfiguration(null, 5222, "xiaomi.com", null){

            @Override
            public byte[] getConnectionBlob() {
                try {
                    ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg = new ChannelMessage.PushServiceConfigMsg();
                    pushServiceConfigMsg.setClientVersion(ServiceConfig.getInstance().getConfigVersion());
                    pushServiceConfigMsg = (ChannelMessage.PushServiceConfigMsg)pushServiceConfigMsg.toByteArray();
                    return pushServiceConfigMsg;
                }
                catch (Exception var1_2) {
                    MyLog.warn("getOBBString err: " + var1_2.toString());
                    return null;
                }
            }
        };
        this.connConfig.setDebuggerEnabled(true);
        this.mXMPPConnection = this.createXMPPConnection(this.connConfig);
        this.mXMPPConnection.setPingString(this.generatePingString("xiaomi.com"));
        new Fallback("mibind.chat.gslb.mi-idc.com");
        this.mClientEventDispatcher = this.createClientEventDispatcher();
        try {
            if (TextUtils.equals((CharSequence)((String)Class.forName((String)"android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object)null, new Object[]{"sys.boot_completed"})), (CharSequence)"1")) {
                this.mClientEventDispatcher.notifyServiceStarted((Context)this);
            }
        }
        catch (Exception var1_2) {
            MyLog.e(var1_2);
        }
        this.mTimer = new AlarmManagerTimer((Context)this);
        this.mXMPPConnection.addConnectionListener(this);
        this.mPacketSync = new PacketSync(this);
        this.mReconnManager = new ReconnectionManager(this);
        new CommonPacketExtensionProvider().register();
        this.mJobController = new ConnectionJobController("Connection Controller Thread");
        this.mJobController.start();
        this.executeJob(new Job(11){

            @Override
            public String getDesc() {
                return "prepare the mi push account.";
            }

            @Override
            public void process() {
                StatsHandler.getInstance().init(XMPushService.this, XMPushService.this.mXMPPConnection);
                XMPushService.this.prepareMIPushAccount();
            }
        });
        object = PushClientsManager.getInstance();
        object.removeAllClientChangeListeners();
        object.addClientChangeListener(new PushClientsManager.ClientChangeListener(){

            @Override
            public void onChange() {
                XMPushService.this.updateAlarmTimer();
                if (PushClientsManager.getInstance().getActiveClientCount() <= 0) {
                    XMPushService.this.executeJob(new DisconnectJob(12, null));
                }
            }
        });
    }

    public void onDestroy() {
        this.mJobController.removeAllJobs();
        this.executeJob(new Job(2){

            @Override
            public String getDesc() {
                return "disconnect for service destroy.";
            }

            @Override
            public void process() {
                if (XMPushService.this.mCurrentConnection != null) {
                    XMPushService.this.mCurrentConnection.disconnect(new Presence(Presence.Type.unavailable), 15, null);
                    XMPushService.this.mCurrentConnection = null;
                }
            }
        });
        this.executeJob(new KillJob());
        PushClientsManager.getInstance().removeAllClientChangeListeners();
        PushClientsManager.getInstance().resetAllClients((Context)this, 15);
        PushClientsManager.getInstance().removeActiveClients();
        this.mXMPPConnection.removeConnectionListener(this);
        ServiceConfig.getInstance().clear();
        this.mTimer.stop();
        super.onDestroy();
        MyLog.warn("Service destroyed");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void onStart(Intent object, final int n) {
        if (object == null) {
            MyLog.e("onStart() with intent NULL");
        } else {
            MyLog.v(String.format((String)"onStart() with intent.Action = %s, chid = %s", (Object[])new Object[]{object.getAction(), object.getStringExtra(PushConstants.EXTRA_CHANNEL_ID)}));
        }
        Collection<PushClientsManager.ClientLoginInfo> collection = PushClientsManager.getInstance();
        if (object == null) return;
        if (object.getAction() == null) return;
        if (PushConstants.ACTION_OPEN_CHANNEL.equalsIgnoreCase(object.getAction()) || PushConstants.ACTION_FORCE_RECONNECT.equalsIgnoreCase(object.getAction())) {
            String string2 = object.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
            if (TextUtils.isEmpty((CharSequence)object.getStringExtra(PushConstants.EXTRA_SECURITY))) {
                MyLog.warn("security is empty. ignore.");
                return;
            }
            if (string2 == null) {
                MyLog.e("channel id is empty, do nothing!");
                return;
            }
            boolean bl = this.shouldRebind(string2, (Intent)object);
            object = this.updatePushClient(string2, (Intent)object);
            if (!Network.hasNetwork((Context)this)) {
                this.mClientEventDispatcher.notifyChannelOpenResult((Context)this, (PushClientsManager.ClientLoginInfo)object, false, 2, null);
                return;
            }
            if (!this.isConnected()) {
                this.scheduleConnect(true);
                return;
            }
            if (object.status == PushClientsManager.ClientStatus.unbind) {
                this.executeJob(new BindJob((PushClientsManager.ClientLoginInfo)object));
                return;
            }
            if (bl) {
                this.executeJob(new ReBindJob((PushClientsManager.ClientLoginInfo)object));
                return;
            }
            if (object.status == PushClientsManager.ClientStatus.binding) {
                MyLog.warn(String.format((String)"the client is binding. %1$s %2$s.", (Object[])new Object[]{object.chid, object.userId}));
                return;
            }
            if (object.status != PushClientsManager.ClientStatus.binded) return;
            this.mClientEventDispatcher.notifyChannelOpenResult((Context)this, (PushClientsManager.ClientLoginInfo)object, true, 0, null);
            return;
        }
        if (PushConstants.ACTION_CLOSE_CHANNEL.equalsIgnoreCase(object.getAction())) {
            String string3 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            String string4 = object.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
            object = object.getStringExtra(PushConstants.EXTRA_USER_ID);
            if (TextUtils.isEmpty((CharSequence)string4)) {
                object = collection.queryChannelIdByPackage(string3).iterator();
                while (object.hasNext()) {
                    this.closeAllChannelByChid(object.next(), 2);
                }
                return;
            }
            if (TextUtils.isEmpty((CharSequence)object)) {
                this.closeAllChannelByChid(string4, 2);
                return;
            }
            this.closeChannel(string4, (String)object, 2, null, null);
            return;
        }
        if (PushConstants.ACTION_SEND_MESSAGE.equalsIgnoreCase(object.getAction())) {
            String string5 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            String string6 = object.getStringExtra(PushConstants.EXTRA_SESSION);
            collection = object.getBundleExtra("ext_packet");
            boolean bl = object.getBooleanExtra("ext_encrypt", true);
            object = this.preparePacket(new Message((Bundle)collection), string5, string6, bl);
            if (object == null) return;
            this.executeJob(new SendMessageJob(this, (Packet)object));
            return;
        }
        if (PushConstants.ACTION_BATCH_SEND_MESSAGE.equalsIgnoreCase(object.getAction())) {
            String string7 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            String string8 = object.getStringExtra(PushConstants.EXTRA_SESSION);
            collection = object.getParcelableArrayExtra("ext_packets");
            Message[] arrmessage = new Message[collection.length];
            boolean bl = object.getBooleanExtra("ext_encrypt", true);
            n = 0;
            do {
                if (n >= collection.length) {
                    this.executeJob(new BatchSendMessageJob(this, arrmessage));
                    return;
                }
                arrmessage[n] = new Message((Bundle)collection[n]);
                arrmessage[n] = (Message)this.preparePacket(arrmessage[n], string7, string8, bl);
                if (arrmessage[n] == null) return;
                ++n;
            } while (true);
        }
        if (PushConstants.ACTION_SEND_IQ.equalsIgnoreCase(object.getAction())) {
            String string9 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            String string10 = object.getStringExtra(PushConstants.EXTRA_SESSION);
            if (this.preparePacket((Packet)(object = new IQ(object.getBundleExtra("ext_packet"))), string9, string10, false) == null) return;
            this.executeJob(new SendMessageJob(this, (Packet)object));
            return;
        }
        if (PushConstants.ACTION_SEND_PRESENCE.equalsIgnoreCase(object.getAction())) {
            String string11 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            String string12 = object.getStringExtra(PushConstants.EXTRA_SESSION);
            if (this.preparePacket((Packet)(object = new Presence(object.getBundleExtra("ext_packet"))), string11, string12, false) == null) return;
            this.executeJob(new SendMessageJob(this, (Packet)object));
            return;
        }
        if ("com.xiaomi.push.timer".equalsIgnoreCase(object.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(object.getAction())) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(object.getAction())) {
                MyLog.warn("Service called on timer");
            } else {
                if (System.currentTimeMillis() - this.lastAlive < 30000) return;
                this.lastAlive = System.currentTimeMillis();
                MyLog.warn("Service called on check alive.");
            }
            if (this.mJobController.isBlocked()) {
                MyLog.e("ERROR, the job controller is blocked.");
                PushClientsManager.getInstance().resetAllClients((Context)this, 14);
                this.stopSelf();
                return;
            }
            if (!this.isConnected()) {
                if ("com.xiaomi.push.timer".equalsIgnoreCase(object.getAction())) {
                    this.scheduleConnect(false);
                    return;
                }
                this.scheduleConnect(true);
                return;
            }
            if (this.mCurrentConnection.isReadAlive()) {
                this.executeJob(new PingJob());
                return;
            }
            this.executeJob(new DisconnectJob(17, null));
            return;
        }
        if ("com.xiaomi.push.network_status_changed".equalsIgnoreCase(object.getAction())) {
            object = null;
            try {
                NetworkInfo networkInfo;
                object = networkInfo = ((ConnectivityManager)this.getSystemService("connectivity")).getActiveNetworkInfo();
            }
            catch (Exception var5_12) {
                MyLog.e(var5_12);
            }
            if (object != null) {
                MyLog.warn("network changed, " + object.toString());
            } else {
                MyLog.warn("network changed, no active network");
            }
            this.mXMPPConnection.clearCachedStatus();
            if (Network.hasNetwork((Context)this)) {
                if (!this.isConnected() && !this.isConnecting()) {
                    this.mJobController.removeJobs(1);
                    this.executeJob(new ConnectJob());
                }
                LogUploader.getInstance((Context)this).checkUpload();
            } else {
                this.executeJob(new DisconnectJob(2, null));
            }
            this.updateAlarmTimer();
            return;
        }
        if (PushConstants.ACTION_RESET_CONNECTION.equals((Object)object.getAction())) {
            String string13 = object.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
            if (string13 != null) {
                this.updatePushClient(string13, (Intent)object);
            }
            this.executeJob(new ResetConnectionJob());
            return;
        }
        if (PushConstants.ACTION_UPDATE_CHANNEL_INFO.equals((Object)object.getAction())) {
            Object object2 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            List<String> list = collection.queryChannelIdByPackage((String)object2);
            if (list.isEmpty()) {
                MyLog.warn("open channel should be called first before update info, pkg=" + (String)object2);
                return;
            }
            String string14 = object.getStringExtra(PushConstants.EXTRA_CHANNEL_ID);
            String string15 = object.getStringExtra(PushConstants.EXTRA_USER_ID);
            object2 = string14;
            if (TextUtils.isEmpty((CharSequence)string14)) {
                object2 = list.get(0);
            }
            string14 = null;
            if (TextUtils.isEmpty((CharSequence)string15)) {
                collection = collection.getAllClientLoginInfoByChid((String)object2);
                object2 = string14;
                if (collection != null) {
                    object2 = string14;
                    if (!collection.isEmpty()) {
                        object2 = collection.iterator().next();
                    }
                }
            } else {
                object2 = collection.getClientLoginInfoByChidAndUserId((String)object2, string15);
            }
            if (object2 == null) return;
            if (object.hasExtra(PushConstants.EXTRA_CLIENT_ATTR)) {
                object2.clientExtra = object.getStringExtra(PushConstants.EXTRA_CLIENT_ATTR);
            }
            if (!object.hasExtra(PushConstants.EXTRA_CLOUD_ATTR)) return;
            object2.cloudExtra = object.getStringExtra(PushConstants.EXTRA_CLOUD_ATTR);
            return;
        }
        if ("com.xiaomi.mipush.REGISTER_APP".equals((Object)object.getAction())) {
            if (PushProvision.getInstance(this.getApplicationContext()).checkProvisioned() && PushProvision.getInstance(this.getApplicationContext()).getProvisioned() == 0) {
                MyLog.warn("register without being provisioned. " + object.getStringExtra("mipush_app_package"));
                return;
            }
            final byte[] arrby = object.getByteArrayExtra("mipush_payload");
            final String string16 = object.getStringExtra("mipush_app_package");
            boolean bl = object.getBooleanExtra("mipush_env_chanage", false);
            n = object.getIntExtra("mipush_env_type", 1);
            MIPushAppInfo.getInstance((Context)this).removeUnRegisteredPkg(string16);
            if (bl && !"com.xiaomi.xmsf".equals((Object)this.getPackageName())) {
                this.executeJob(new Job(14){

                    @Override
                    public String getDesc() {
                        return "clear account cache.";
                    }

                    @Override
                    public void process() {
                        MIPushAccountUtils.clearAccount((Context)XMPushService.this);
                        PushClientsManager.getInstance().deactivateAllClientByChid("5");
                        BuildSettings.setEnvType(n);
                        XMPushService.this.connConfig.setHost(ConnectionConfiguration.getXmppServerHost());
                        XMPushService.this.registerForMiPushApp(arrby, string16);
                    }
                });
                return;
            }
            this.registerForMiPushApp(arrby, string16);
            return;
        }
        if ("com.xiaomi.mipush.SEND_MESSAGE".equals((Object)object.getAction()) || "com.xiaomi.mipush.UNREGISTER_APP".equals((Object)object.getAction())) {
            final String string17 = object.getStringExtra("mipush_app_package");
            final byte[] arrby = object.getByteArrayExtra("mipush_payload");
            boolean bl = object.getBooleanExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
            collection = PushClientsManager.getInstance().getAllClientLoginInfoByChid("5");
            if ("com.xiaomi.mipush.UNREGISTER_APP".equals((Object)object.getAction())) {
                MIPushAppInfo.getInstance((Context)this).addUnRegisteredPkg(string17);
            }
            if (collection.isEmpty()) {
                if (!bl) return;
                MIPushClientManager.addPendingMessages(string17, arrby);
                return;
            }
            if (collection.iterator().next().status != PushClientsManager.ClientStatus.binded) {
                if (!bl) return;
                MIPushClientManager.addPendingMessages(string17, arrby);
                return;
            }
            this.executeJob(new Job(4){

                @Override
                public String getDesc() {
                    return "send mi push message";
                }

                @Override
                public void process() {
                    try {
                        XMPushService.this.sendMIPushPacket(string17, arrby);
                        return;
                    }
                    catch (XMPPException var1_1) {
                        MyLog.e(var1_1);
                        XMPushService.this.disconnect(10, var1_1);
                        return;
                    }
                }
            });
            return;
        }
        if (PushServiceConstants.ACTION_UNINSTALL.equals((Object)object.getAction())) {
            if ((object = object.getStringExtra("uninstall_pkg_name")) == null) return;
            if (TextUtils.isEmpty((CharSequence)object.trim())) return;
            n = 0;
            try {
                this.getPackageManager().getPackageInfo((String)object, 256);
            }
            catch (PackageManager.NameNotFoundException var5_17) {
                n = 1;
            }
            if ("com.xiaomi.channel".equals(object) && !PushClientsManager.getInstance().getAllClientLoginInfoByChid("1").isEmpty() && n != 0) {
                this.closeAllChannelByChid("1", 0);
                MyLog.warn("close the miliao channel as the app is uninstalled.");
                return;
            }
            SharedPreferences sharedPreferences = this.getSharedPreferences("pref_registered_pkg_names", 0);
            String string18 = sharedPreferences.getString((String)object, null);
            if (TextUtils.isEmpty((CharSequence)string18)) return;
            if (n == 0) return;
            sharedPreferences = sharedPreferences.edit();
            sharedPreferences.remove((String)object);
            sharedPreferences.commit();
            if (MIPushNotificationHelper.hasLocalNotifyType((Context)this, (String)object)) {
                MIPushNotificationHelper.clearLocalNotifyType((Context)this, (String)object);
            }
            MIPushNotificationHelper.clearNotification((Context)this, (String)object);
            if (!this.isConnected()) return;
            if (string18 == null) return;
            try {
                this.sendMIPushPacket(this.contructAppAbsentMessage((String)object, string18));
                MyLog.warn("uninstall " + (String)object + " msg sent");
                return;
            }
            catch (XMPPException var1_2) {
                MyLog.e("Fail to send Message: " + var1_2.getMessage());
                this.disconnect(10, var1_2);
                return;
            }
        }
        if ("com.xiaomi.mipush.CLEAR_NOTIFICATION".equals((Object)object.getAction())) {
            String string19 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
            n = object.getIntExtra(PushConstants.EXTRA_NOTIFY_ID, 0);
            if (TextUtils.isEmpty((CharSequence)string19)) return;
            if (n >= 0) {
                MIPushNotificationHelper.clearNotification((Context)this, string19, n);
                return;
            }
            if (n != -1) return;
            MIPushNotificationHelper.clearNotification((Context)this, string19);
            return;
        }
        if (!"com.xiaomi.mipush.SET_NOTIFICATION_TYPE".equals((Object)object.getAction())) return;
        String string20 = object.getStringExtra(PushConstants.EXTRA_PACKAGE_NAME);
        String string21 = object.getStringExtra(PushConstants.EXTRA_SIG);
        int n2 = 0;
        n = 0;
        if (object.hasExtra(PushConstants.EXTRA_NOTIFY_TYPE)) {
            n2 = object.getIntExtra(PushConstants.EXTRA_NOTIFY_TYPE, 0);
            object = MD5.MD5_16(string20 + n2);
        } else {
            object = MD5.MD5_16(string20);
            n = 1;
        }
        if (TextUtils.isEmpty((CharSequence)string20) || !TextUtils.equals((CharSequence)string21, (CharSequence)object)) {
            MyLog.e("invalid notification for " + string20);
            return;
        }
        if (n != 0) {
            MIPushNotificationHelper.clearLocalNotifyType((Context)this, string20);
            return;
        }
        MIPushNotificationHelper.setLocalNotifyType((Context)this, string20, n2);
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        this.onStart(intent, n2);
        return START_STICKY;
    }

    public void prepareMIPushClientLoginInfo(PushClientsManager.ClientLoginInfo clientLoginInfo) {
        clientLoginInfo.addClientStatusListener(new PushClientsManager.ClientLoginInfo.ClientStatusListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void onChange(PushClientsManager.ClientStatus clientStatus, PushClientsManager.ClientStatus clientStatus2, int n) {
                if (clientStatus2 == PushClientsManager.ClientStatus.binded) {
                    MIPushClientManager.processPendingRegistrationRequest(XMPushService.this);
                    MIPushClientManager.processPendingMessages(XMPushService.this);
                    return;
                } else {
                    if (clientStatus2 != PushClientsManager.ClientStatus.unbind) return;
                    {
                        MIPushClientManager.notifyRegisterError((Context)XMPushService.this, 70000001, " the push is not connected.");
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void reconnectionFailed(Connection connection, Exception exception) {
        this.scheduleConnect(false);
    }

    @Override
    public void reconnectionSuccessful(Connection object) {
        this.mReconnManager.onConnectSucceeded();
        object = PushClientsManager.getInstance().getAllClients().iterator();
        while (object.hasNext()) {
            this.executeJob(new BindJob((PushClientsManager.ClientLoginInfo)object.next()));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void registerForMiPushApp(byte[] var1_1, String var2_2) {
        if (var1_1 == null) {
            MIPushClientManager.notifyError((Context)this, var2_2, var1_1, 70000003, "null payload");
            MyLog.warn("register request without payload");
            return;
        }
        var3_3 = new XmPushActionContainer();
        XmPushThriftSerializeUtils.convertByteArrayToThriftObject(var3_3, var1_1);
        if (var3_3.action != ActionType.Registration) ** GOTO lbl25
        var4_6 = new XmPushActionRegistration();
        {
            catch (TException var3_5) {
                MyLog.e(var3_5);
                MIPushClientManager.notifyError((Context)this, var2_2, var1_1, 70000003, " data container error.");
                return;
            }
        }
        try {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(var4_6, var3_3.getPushAction());
            MIPushClientManager.registerApp(var3_3.getPackageName(), var1_1);
            this.executeJob(new MIPushAppRegisterJob(this, var3_3.getPackageName(), var4_6.getAppId(), var4_6.getToken(), var1_1));
            return;
        }
        catch (TException var3_4) {
            MyLog.e(var3_4);
            MIPushClientManager.notifyError((Context)this, var2_2, var1_1, 70000003, " data action error.");
            return;
lbl25: // 2 sources:
            MIPushClientManager.notifyError((Context)this, var2_2, var1_1, 70000003, " registration action required.");
            MyLog.warn("register request with invalid payload");
            return;
        }
    }

    public void removeJobs(int n) {
        this.mJobController.removeJobs(n);
    }

    public void removeJobs(Job job) {
        this.mJobController.removeJobs(job.type, job);
    }

    public void scheduleConnect(boolean bl) {
        this.mReconnManager.tryReconnect(bl);
    }

    public void scheduleRebindChannel(PushClientsManager.ClientLoginInfo clientLoginInfo) {
        if (clientLoginInfo != null) {
            long l = clientLoginInfo.getNextRetryInterval();
            MyLog.warn("schedule rebind job in " + l / 1000);
            this.executeJobDelayed(new BindJob(clientLoginInfo), l);
        }
    }

    public void sendMIPushPacket(XmPushActionContainer object) throws XMPPException {
        if (this.mCurrentConnection != null) {
            if ((object = this.constructMIPushMessage((XmPushActionContainer)object)) != null) {
                this.mCurrentConnection.sendPacket((Packet)object);
            }
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void sendMIPushPacket(String string2, byte[] arrby) throws XMPPException {
        if (this.mCurrentConnection != null) {
            Message message = this.constructMIPushMessage(arrby);
            if (message != null) {
                this.mCurrentConnection.sendPacket(message);
                return;
            }
            MIPushClientManager.notifyError((Context)this, string2, arrby, 70000003, "not a valid message");
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void sendPacket(Packet packet) throws XMPPException {
        if (this.mCurrentConnection != null) {
            this.mCurrentConnection.sendPacket(packet);
            return;
        }
        throw new XMPPException("try send msg while connection is null.");
    }

    public void setConnectingTimeout() {
        this.executeJobDelayed(new Job(10){

            @Override
            public String getDesc() {
                return "disconnect because of connecting timeout";
            }

            @Override
            public void process() {
                XMPushService.this.disconnect(18, null);
            }
        }, 120000);
    }

    public boolean shouldReconnect() {
        if (Network.hasNetwork((Context)this) && PushClientsManager.getInstance().getActiveClientCount() > 0 && !this.isPushDisabled()) {
            return true;
        }
        return false;
    }

    class BindJob
    extends Job {
        PushClientsManager.ClientLoginInfo mLoginInfo;

        public BindJob(PushClientsManager.ClientLoginInfo clientLoginInfo) {
            super(9);
            this.mLoginInfo = null;
            this.mLoginInfo = clientLoginInfo;
        }

        @Override
        public String getDesc() {
            return "bind the client. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void process() {
            try {
                if (!XMPushService.this.isConnected()) {
                    MyLog.e("trying bind while the connection is not created, quit!");
                    return;
                }
                PushClientsManager.ClientLoginInfo clientLoginInfo = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(this.mLoginInfo.chid, this.mLoginInfo.userId);
                if (clientLoginInfo == null) {
                    MyLog.warn("ignore bind because the channel " + this.mLoginInfo.chid + " is removed ");
                    return;
                }
                if (clientLoginInfo.status == PushClientsManager.ClientStatus.unbind) {
                    clientLoginInfo.setStatus(PushClientsManager.ClientStatus.binding, 0, 0, null, null);
                    XMPushService.this.mCurrentConnection.bind(clientLoginInfo);
                    StatsHelper.statsBind(XMPushService.this, clientLoginInfo);
                    return;
                }
            }
            catch (XMPPException var1_2) {
                MyLog.e(var1_2);
                XMPushService.this.disconnect(10, var1_2);
                return;
            }
            MyLog.warn("trying duplicate bind, ingore! " + (Object)((Object)clientLoginInfo.status));
        }
    }

    static class BindTimeoutJob
    extends Job {
        private final PushClientsManager.ClientLoginInfo mLoginInfo;

        public BindTimeoutJob(PushClientsManager.ClientLoginInfo clientLoginInfo) {
            super(12);
            this.mLoginInfo = clientLoginInfo;
        }

        public boolean equals(Object object) {
            if (!(object instanceof BindTimeoutJob)) {
                return false;
            }
            return TextUtils.equals((CharSequence)((BindTimeoutJob)object).mLoginInfo.chid, (CharSequence)this.mLoginInfo.chid);
        }

        @Override
        public String getDesc() {
            return "bind time out. chid=" + this.mLoginInfo.chid;
        }

        public int hashCode() {
            return this.mLoginInfo.chid.hashCode();
        }

        @Override
        public void process() {
            this.mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 21, null, null);
        }
    }

    public class ConnectJob
    extends Job {
        ConnectJob() {
            super(1);
        }

        @Override
        public String getDesc() {
            return "do reconnect..";
        }

        @Override
        public void process() {
            if (XMPushService.this.shouldReconnect()) {
                XMPushService.this.connect();
                return;
            }
            MyLog.warn("should not connect. quit the job.");
        }
    }

    public class DisconnectJob
    extends Job {
        public Exception e;
        public int reason;

        DisconnectJob(int n, Exception exception) {
            super(2);
            this.reason = n;
            this.e = exception;
        }

        @Override
        public String getDesc() {
            return "disconnect the connection.";
        }

        @Override
        public void process() {
            XMPushService.this.disconnect(this.reason, this.e);
        }
    }

    public static abstract class Job {
        protected int type;

        public Job(int n) {
            this.type = n;
        }

        public abstract String getDesc();

        public abstract void process();

        public void run() {
            if (this.type != 4 && this.type != 8) {
                MyLog.warn("JOB: " + this.getDesc());
            }
            this.process();
        }
    }

    class KillJob
    extends Job {
        public KillJob() {
            super(5);
        }

        @Override
        public String getDesc() {
            return "ask the job queue to quit";
        }

        @Override
        public void process() {
            XMPushService.this.mJobController.quit();
        }
    }

    public class MessageChannel
    extends Binder {
    }

    class PacketReceiveJob
    extends Job {
        private Packet mPacket;

        public PacketReceiveJob(Packet packet) {
            super(8);
            this.mPacket = null;
            this.mPacket = packet;
        }

        @Override
        public String getDesc() {
            return "receive a message.";
        }

        @Override
        public void process() {
            XMPushService.this.mPacketSync.onPacketReceive(this.mPacket);
        }
    }

    class PingJob
    extends Job {
        public PingJob() {
            super(4);
        }

        @Override
        public String getDesc() {
            return "send ping..";
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public void process() {
            if (!XMPushService.this.isConnected()) return;
            try {
                StatsHelper.pingStarted();
                XMPushService.this.mCurrentConnection.sendPingString();
                return;
            }
            catch (XMPPException var1_1) {
                MyLog.e(var1_1);
                XMPushService.this.disconnect(10, var1_1);
                return;
            }
        }
    }

    class ReBindJob
    extends Job {
        PushClientsManager.ClientLoginInfo mLoginInfo;

        public ReBindJob(PushClientsManager.ClientLoginInfo clientLoginInfo) {
            super(4);
            this.mLoginInfo = null;
            this.mLoginInfo = clientLoginInfo;
        }

        @Override
        public String getDesc() {
            return "bind the client. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }

        @Override
        public void process() {
            try {
                this.mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 16, null, null);
                XMPushService.this.mCurrentConnection.unbind(this.mLoginInfo.chid, this.mLoginInfo.userId);
                this.mLoginInfo.setStatus(PushClientsManager.ClientStatus.binding, 1, 16, null, null);
                XMPushService.this.mCurrentConnection.bind(this.mLoginInfo);
                return;
            }
            catch (XMPPException var1_1) {
                MyLog.e(var1_1);
                XMPushService.this.disconnect(10, var1_1);
                return;
            }
        }
    }

    class ResetConnectionJob
    extends Job {
        ResetConnectionJob() {
            super(3);
        }

        @Override
        public String getDesc() {
            return "reset the connection.";
        }

        @Override
        public void process() {
            XMPushService.this.disconnect(11, null);
            if (XMPushService.this.shouldReconnect()) {
                XMPushService.this.connect();
            }
        }
    }

    class UnbindJob
    extends Job {
        String kickType;
        PushClientsManager.ClientLoginInfo mLoginInfo;
        int mNotifyType;
        String reason;

        public UnbindJob(PushClientsManager.ClientLoginInfo clientLoginInfo, int n, String string2, String string3) {
            super(9);
            this.mLoginInfo = null;
            this.mLoginInfo = clientLoginInfo;
            this.mNotifyType = n;
            this.kickType = string2;
            this.reason = string3;
        }

        @Override
        public String getDesc() {
            return "unbind the channel. " + this.mLoginInfo.chid + ", " + this.mLoginInfo.userId;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void process() {
            if (this.mLoginInfo.status != PushClientsManager.ClientStatus.unbind && XMPushService.this.mCurrentConnection != null) {
                try {
                    XMPushService.this.mCurrentConnection.unbind(this.mLoginInfo.chid, this.mLoginInfo.userId);
                }
                catch (XMPPException var1_1) {
                    MyLog.e(var1_1);
                    XMPushService.this.disconnect(10, var1_1);
                }
            }
            this.mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, this.mNotifyType, 0, this.reason, this.kickType);
        }
    }

}

