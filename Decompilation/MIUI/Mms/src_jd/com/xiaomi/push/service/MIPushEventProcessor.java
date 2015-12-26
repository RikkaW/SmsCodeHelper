package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.TrafficUtils;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TException;
import org.json.JSONException;

public class MIPushEventProcessor
{
  private static XmPushActionContainer constructAckMessage(XMPushService paramXMPushService, XmPushActionContainer paramXmPushActionContainer)
  {
    XmPushActionAckMessage localXmPushActionAckMessage = new XmPushActionAckMessage();
    localXmPushActionAckMessage.setAppId(paramXmPushActionContainer.getAppid());
    PushMetaInfo localPushMetaInfo = paramXmPushActionContainer.getMetaInfo();
    if (localPushMetaInfo != null)
    {
      localXmPushActionAckMessage.setId(localPushMetaInfo.getId());
      localXmPushActionAckMessage.setMessageTs(localPushMetaInfo.getMessageTs());
      if (!TextUtils.isEmpty(localPushMetaInfo.getTopic())) {
        localXmPushActionAckMessage.setTopic(localPushMetaInfo.getTopic());
      }
    }
    paramXMPushService = paramXMPushService.generateRequestContainer(paramXmPushActionContainer.getPackageName(), paramXmPushActionContainer.getAppid(), localXmPushActionAckMessage, ActionType.AckMessage);
    paramXmPushActionContainer = paramXmPushActionContainer.getMetaInfo().deepCopy();
    paramXmPushActionContainer.putToExtra("mat", Long.toString(System.currentTimeMillis()));
    paramXMPushService.setMetaInfo(paramXmPushActionContainer);
    return paramXMPushService;
  }
  
  private static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.queryBroadcastReceivers(paramIntent, 32);
      if (paramContext != null)
      {
        boolean bool = paramContext.isEmpty();
        if (!bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  private static boolean isMIUIOldAdsSDKMessage(XmPushActionContainer paramXmPushActionContainer)
  {
    if ((paramXmPushActionContainer.getMetaInfo() == null) || (paramXmPushActionContainer.getMetaInfo().getExtra() == null)) {
      return false;
    }
    return "1".equals(paramXmPushActionContainer.getMetaInfo().getExtra().get("obslete_ads_message"));
  }
  
  private static boolean isMIUIPushMessage(XmPushActionContainer paramXmPushActionContainer)
  {
    return ("com.xiaomi.xmsf".equals(packageName)) && (paramXmPushActionContainer.getMetaInfo() != null) && (paramXmPushActionContainer.getMetaInfo().getExtra() != null) && (paramXmPushActionContainer.getMetaInfo().getExtra().containsKey("miui_package_name"));
  }
  
  private static boolean isMIUIPushSupported(Context paramContext, String paramString)
  {
    boolean bool1 = false;
    Intent localIntent1 = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
    localIntent1.setPackage(paramString);
    Intent localIntent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
    localIntent2.setPackage(paramString);
    paramContext = paramContext.getPackageManager();
    try
    {
      paramString = paramContext.queryBroadcastReceivers(localIntent2, 32);
      paramContext = paramContext.queryIntentServices(localIntent1, 32);
      if (paramString.isEmpty())
      {
        boolean bool2 = paramContext.isEmpty();
        if (bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      MyLog.e(paramContext);
    }
    return false;
  }
  
  private static boolean isPkgInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
    }
    return true;
  }
  
  private static boolean predefinedNotification(XmPushActionContainer paramXmPushActionContainer)
  {
    return paramXmPushActionContainer.getMetaInfo().getExtra().containsKey("notify_effect");
  }
  
  private static void processMIPushMessage(final XMPushService paramXMPushService, byte[] paramArrayOfByte, long paramLong)
  {
    Object localObject2 = Long.valueOf(System.currentTimeMillis());
    final XmPushActionContainer localXmPushActionContainer = new XmPushActionContainer();
    Object localObject1;
    Object localObject3;
    PushMetaInfo localPushMetaInfo;
    try
    {
      XmPushThriftSerializeUtils.convertByteArrayToThriftObject(localXmPushActionContainer, paramArrayOfByte);
      if (TextUtils.isEmpty(packageName)) {
        break label857;
      }
      localObject1 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
      ((Intent)localObject1).putExtra("mipush_payload", paramArrayOfByte);
      ((Intent)localObject1).putExtra("mrt", Long.toString(((Long)localObject2).longValue()));
      ((Intent)localObject1).setPackage(packageName);
      localObject3 = MIPushNotificationHelper.getTargetPackage(localXmPushActionContainer);
      TrafficUtils.distributionTraffic(paramXMPushService, (String)localObject3, paramLong, true, System.currentTimeMillis());
      localPushMetaInfo = localXmPushActionContainer.getMetaInfo();
      if (localPushMetaInfo != null) {
        localPushMetaInfo.putToExtra("mrt", Long.toString(((Long)localObject2).longValue()));
      }
      if ((ActionType.SendMessage == localXmPushActionContainer.getAction()) && (MIPushAppInfo.getInstance(paramXMPushService).isUnRegistered(packageName)) && (!MIPushNotificationHelper.isBusinessMessage(localXmPushActionContainer)))
      {
        paramArrayOfByte = "";
        if (localPushMetaInfo != null) {
          paramArrayOfByte = localPushMetaInfo.getId();
        }
        MyLog.warn("Drop a message for unregistered, msgid=" + paramArrayOfByte);
        sendAppAbsentAck(paramXMPushService, localXmPushActionContainer, packageName);
        return;
      }
      if ((ActionType.SendMessage == localXmPushActionContainer.getAction()) && (!TextUtils.equals(paramXMPushService.getPackageName(), "com.xiaomi.xmsf")) && (!TextUtils.equals(paramXMPushService.getPackageName(), packageName)))
      {
        MyLog.warn("Receive a message with wrong package name, expect " + paramXMPushService.getPackageName() + ", received " + packageName);
        sendErrorAck(paramXMPushService, localXmPushActionContainer, "unmatched_package", "package should be " + paramXMPushService.getPackageName() + ", but got " + packageName);
        return;
      }
    }
    catch (TException paramXMPushService)
    {
      MyLog.e(paramXMPushService);
      return;
    }
    if ((localPushMetaInfo != null) && (localPushMetaInfo.getId() != null)) {
      MyLog.warn(String.format("receive a message, appid=%1$s, msgid= %2$s", new Object[] { localXmPushActionContainer.getAppid(), localPushMetaInfo.getId() }));
    }
    if ((isMIUIOldAdsSDKMessage(localXmPushActionContainer)) && (isMIUIPushSupported(paramXMPushService, (String)localObject3)))
    {
      sendMIUIOldAdsAckMessage(paramXMPushService, localXmPushActionContainer);
      return;
    }
    if ((isMIUIPushMessage(localXmPushActionContainer)) && (!isMIUIPushSupported(paramXMPushService, (String)localObject3)) && (!predefinedNotification(localXmPushActionContainer)))
    {
      sendMIUINewAdsAckMessage(paramXMPushService, localXmPushActionContainer);
      return;
    }
    if (((MIPushNotificationHelper.isBusinessMessage(localXmPushActionContainer)) && (isPkgInstalled(paramXMPushService, packageName))) || (isIntentAvailable(paramXMPushService, (Intent)localObject1)))
    {
      if (ActionType.Registration == localXmPushActionContainer.getAction())
      {
        localObject2 = localXmPushActionContainer.getPackageName();
        localObject3 = paramXMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
        ((SharedPreferences.Editor)localObject3).putString((String)localObject2, appid);
        ((SharedPreferences.Editor)localObject3).commit();
      }
      if ((localPushMetaInfo == null) || (TextUtils.isEmpty(localPushMetaInfo.getTitle())) || (TextUtils.isEmpty(localPushMetaInfo.getDescription())) || (passThrough == 1) || ((!MIPushNotificationHelper.isNotifyForeground(localPushMetaInfo.getExtra())) && (MIPushNotificationHelper.isApplicationForeground(paramXMPushService, packageName))))
      {
        paramXMPushService.sendBroadcast((Intent)localObject1, ClientEventDispatcher.getReceiverPermission(packageName));
        if ((localXmPushActionContainer.getAction() == ActionType.UnRegistration) && (!"com.xiaomi.xmsf".equals(paramXMPushService.getPackageName()))) {
          paramXMPushService.stopSelf();
        }
      }
      else
      {
        boolean bool = false;
        localObject2 = null;
        localObject1 = null;
        if (localPushMetaInfo != null)
        {
          if (extra != null) {
            localObject1 = (String)extra.get("jobkey");
          }
          localObject2 = localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            localObject2 = localPushMetaInfo.getId();
          }
          bool = MiPushMessageDuplicate.isDuplicateMessage(paramXMPushService, packageName, (String)localObject2);
        }
        if (bool) {
          MyLog.warn("drop a duplicate message, key=" + (String)localObject2);
        }
        for (;;)
        {
          sendAckMessage(paramXMPushService, localXmPushActionContainer);
          break;
          MIPushNotificationHelper.notifyPushMessage(paramXMPushService, localXmPushActionContainer, paramArrayOfByte);
          if (!MIPushNotificationHelper.isBusinessMessage(localXmPushActionContainer))
          {
            localObject1 = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
            ((Intent)localObject1).putExtra("mipush_payload", paramArrayOfByte);
            ((Intent)localObject1).setPackage(packageName);
            try
            {
              paramArrayOfByte = paramXMPushService.getPackageManager().queryBroadcastReceivers((Intent)localObject1, 0);
              if ((paramArrayOfByte != null) && (!paramArrayOfByte.isEmpty())) {
                paramXMPushService.sendBroadcast((Intent)localObject1, ClientEventDispatcher.getReceiverPermission(packageName));
              }
            }
            catch (Exception paramArrayOfByte)
            {
              paramXMPushService.sendBroadcast((Intent)localObject1, ClientEventDispatcher.getReceiverPermission(packageName));
            }
          }
        }
      }
    }
    else
    {
      paramXMPushService.executeJob(new XMPushService.Job(4)
      {
        public String getDesc()
        {
          return "send app absent message.";
        }
        
        public void process()
        {
          try
          {
            paramXMPushService.sendMIPushPacket(paramXMPushService.contructAppAbsentMessage(localXmPushActionContainer.getPackageName(), localXmPushActionContainer.getAppid()));
            return;
          }
          catch (XMPPException localXMPPException)
          {
            MyLog.e(localXMPPException);
            paramXMPushService.disconnect(10, localXMPPException);
          }
        }
      });
      return;
      label857:
      MyLog.warn("receive a mipush message without package name");
    }
  }
  
  private static void sendAckMessage(final XMPushService paramXMPushService, final XmPushActionContainer paramXmPushActionContainer)
  {
    paramXMPushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "send ack message for message.";
      }
      
      public void process()
      {
        try
        {
          XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.constructAckMessage(paramXMPushService, paramXmPushActionContainer);
          paramXMPushService.sendMIPushPacket(localXmPushActionContainer);
          return;
        }
        catch (XMPPException localXMPPException)
        {
          MyLog.e(localXMPPException);
          paramXMPushService.disconnect(10, localXMPPException);
        }
      }
    });
  }
  
  private static void sendAppAbsentAck(final XMPushService paramXMPushService, final XmPushActionContainer paramXmPushActionContainer, final String paramString)
  {
    paramXMPushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "send app absent ack message for message.";
      }
      
      public void process()
      {
        try
        {
          XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.constructAckMessage(paramXMPushService, paramXmPushActionContainer);
          localXmPushActionContainer.getMetaInfo().putToExtra("absent_target_package", paramString);
          paramXMPushService.sendMIPushPacket(localXmPushActionContainer);
          return;
        }
        catch (XMPPException localXMPPException)
        {
          MyLog.e(localXMPPException);
          paramXMPushService.disconnect(10, localXMPPException);
        }
      }
    });
  }
  
  private static void sendErrorAck(final XMPushService paramXMPushService, final XmPushActionContainer paramXmPushActionContainer, final String paramString1, final String paramString2)
  {
    paramXMPushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "send wrong message ack for message.";
      }
      
      public void process()
      {
        try
        {
          XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.constructAckMessage(paramXMPushService, paramXmPushActionContainer);
          metaInfo.putToExtra("error", paramString1);
          metaInfo.putToExtra("reason", paramString2);
          paramXMPushService.sendMIPushPacket(localXmPushActionContainer);
          return;
        }
        catch (XMPPException localXMPPException)
        {
          MyLog.e(localXMPPException);
          paramXMPushService.disconnect(10, localXMPPException);
        }
      }
    });
  }
  
  private static void sendMIUINewAdsAckMessage(final XMPushService paramXMPushService, final XmPushActionContainer paramXmPushActionContainer)
  {
    paramXMPushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "send ack message for unrecognized new miui message.";
      }
      
      public void process()
      {
        try
        {
          XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.constructAckMessage(paramXMPushService, paramXmPushActionContainer);
          localXmPushActionContainer.getMetaInfo().putToExtra("miui_message_unrecognized", "1");
          paramXMPushService.sendMIPushPacket(localXmPushActionContainer);
          return;
        }
        catch (XMPPException localXMPPException)
        {
          MyLog.e(localXMPPException);
          paramXMPushService.disconnect(10, localXMPPException);
        }
      }
    });
  }
  
  private static void sendMIUIOldAdsAckMessage(final XMPushService paramXMPushService, final XmPushActionContainer paramXmPushActionContainer)
  {
    paramXMPushService.executeJob(new XMPushService.Job(4)
    {
      public String getDesc()
      {
        return "send ack message for obsleted message.";
      }
      
      public void process()
      {
        try
        {
          XmPushActionContainer localXmPushActionContainer = MIPushEventProcessor.constructAckMessage(paramXMPushService, paramXmPushActionContainer);
          localXmPushActionContainer.getMetaInfo().putToExtra("message_obsleted", "1");
          paramXMPushService.sendMIPushPacket(localXmPushActionContainer);
          return;
        }
        catch (XMPPException localXMPPException)
        {
          MyLog.e(localXMPPException);
          paramXMPushService.disconnect(10, localXMPPException);
        }
      }
    });
  }
  
  public void processChannelOpenResult(Context paramContext, PushClientsManager.ClientLoginInfo paramClientLoginInfo, boolean paramBoolean, int paramInt, String paramString)
  {
    if (!paramBoolean)
    {
      paramClientLoginInfo = MIPushAccountUtils.getMIPushAccount(paramContext);
      if ((paramClientLoginInfo == null) || (!"token-expired".equals(paramString))) {}
    }
    try
    {
      MIPushAccountUtils.register(paramContext, appId, appToken, packageName);
      return;
    }
    catch (IOException paramContext)
    {
      MyLog.e(paramContext);
      return;
    }
    catch (JSONException paramContext)
    {
      MyLog.e(paramContext);
    }
  }
  
  public void processNewPacket(XMPushService paramXMPushService, Packet paramPacket, PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    if ((paramPacket instanceof Message))
    {
      Message localMessage = (Message)paramPacket;
      CommonPacketExtension localCommonPacketExtension = localMessage.getExtension("s");
      if (localCommonPacketExtension != null) {}
      try
      {
        processMIPushMessage(paramXMPushService, RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(security, localMessage.getPacketID()), localCommonPacketExtension.getText()), TrafficUtils.getTrafficFlow(paramPacket.toXML()));
        return;
      }
      catch (IllegalArgumentException paramXMPushService)
      {
        MyLog.e(paramXMPushService);
        return;
      }
    }
    MyLog.warn("not a mipush message");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushEventProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */