package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.PushMessage;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import java.util.List;

public class PushMessageHelper
{
  private static int pushMode = 0;
  
  public static MiPushCommandMessage generateCommandMessage(String paramString1, List<String> paramList, long paramLong, String paramString2, String paramString3)
  {
    MiPushCommandMessage localMiPushCommandMessage = new MiPushCommandMessage();
    localMiPushCommandMessage.setCommand(paramString1);
    localMiPushCommandMessage.setCommandArguments(paramList);
    localMiPushCommandMessage.setResultCode(paramLong);
    localMiPushCommandMessage.setReason(paramString2);
    localMiPushCommandMessage.setCategory(paramString3);
    return localMiPushCommandMessage;
  }
  
  public static MiPushMessage generateMessage(XmPushActionSendMessage paramXmPushActionSendMessage, PushMetaInfo paramPushMetaInfo, boolean paramBoolean)
  {
    MiPushMessage localMiPushMessage = new MiPushMessage();
    localMiPushMessage.setMessageId(paramXmPushActionSendMessage.getId());
    if (!TextUtils.isEmpty(paramXmPushActionSendMessage.getAliasName()))
    {
      localMiPushMessage.setMessageType(1);
      localMiPushMessage.setAlias(paramXmPushActionSendMessage.getAliasName());
    }
    for (;;)
    {
      localMiPushMessage.setCategory(paramXmPushActionSendMessage.getCategory());
      if (paramXmPushActionSendMessage.getMessage() != null) {
        localMiPushMessage.setContent(paramXmPushActionSendMessage.getMessage().getPayload());
      }
      if (paramPushMetaInfo != null)
      {
        if (TextUtils.isEmpty(localMiPushMessage.getMessageId())) {
          localMiPushMessage.setMessageId(paramPushMetaInfo.getId());
        }
        if (TextUtils.isEmpty(localMiPushMessage.getTopic())) {
          localMiPushMessage.setTopic(paramPushMetaInfo.getTopic());
        }
        localMiPushMessage.setDescription(paramPushMetaInfo.getDescription());
        localMiPushMessage.setTitle(paramPushMetaInfo.getTitle());
        localMiPushMessage.setNotifyType(paramPushMetaInfo.getNotifyType());
        localMiPushMessage.setNotifyId(paramPushMetaInfo.getNotifyId());
        localMiPushMessage.setPassThrough(paramPushMetaInfo.getPassThrough());
        localMiPushMessage.setExtra(paramPushMetaInfo.getExtra());
      }
      localMiPushMessage.setNotified(paramBoolean);
      return localMiPushMessage;
      if (!TextUtils.isEmpty(paramXmPushActionSendMessage.getTopic()))
      {
        localMiPushMessage.setMessageType(2);
        localMiPushMessage.setTopic(paramXmPushActionSendMessage.getTopic());
      }
      else if (!TextUtils.isEmpty(paramXmPushActionSendMessage.getUserAccount()))
      {
        localMiPushMessage.setMessageType(3);
        localMiPushMessage.setUserAccount(paramXmPushActionSendMessage.getUserAccount());
      }
      else
      {
        localMiPushMessage.setMessageType(0);
      }
    }
  }
  
  public static int getPushMode(Context paramContext)
  {
    if (pushMode == 0)
    {
      if (!isUseCallbackPushMode(paramContext)) {
        break label21;
      }
      setPushMode(1);
    }
    for (;;)
    {
      return pushMode;
      label21:
      setPushMode(2);
    }
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
  
  public static boolean isUseCallbackPushMode(Context paramContext)
  {
    Intent localIntent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
    localIntent.setClassName(paramContext.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
    return isIntentAvailable(paramContext, localIntent);
  }
  
  public static void sendCommandMessageBroadcast(Context paramContext, MiPushCommandMessage paramMiPushCommandMessage)
  {
    Intent localIntent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra("message_type", 3);
    localIntent.putExtra("key_command", paramMiPushCommandMessage);
    new PushServiceReceiver().onReceive(paramContext, localIntent);
  }
  
  private static void setPushMode(int paramInt)
  {
    pushMode = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushMessageHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */