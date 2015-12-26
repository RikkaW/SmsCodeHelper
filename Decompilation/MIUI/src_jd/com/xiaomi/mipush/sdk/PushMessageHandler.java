package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PushMessageHandler
  extends IntentService
{
  private static List<MiPushClient.MiPushClientCallback> sCallbacks = new ArrayList();
  
  public PushMessageHandler()
  {
    super("mipush message handler");
  }
  
  protected static void addPushCallbackClass(MiPushClient.MiPushClientCallback paramMiPushClientCallback)
  {
    synchronized (sCallbacks)
    {
      if (!sCallbacks.contains(paramMiPushClientCallback)) {
        sCallbacks.add(paramMiPushClientCallback);
      }
      return;
    }
  }
  
  public static boolean isCallbackEmpty()
  {
    return sCallbacks.isEmpty();
  }
  
  protected static boolean isCategoryMatch(String paramString1, String paramString2)
  {
    return ((TextUtils.isEmpty(paramString1)) && (TextUtils.isEmpty(paramString2))) || (TextUtils.equals(paramString1, paramString2));
  }
  
  protected static void onCommandResult(Context arg0, String paramString1, String paramString2, long paramLong, String paramString3, List<String> paramList)
  {
    synchronized (sCallbacks)
    {
      Iterator localIterator = sCallbacks.iterator();
      while (localIterator.hasNext())
      {
        MiPushClient.MiPushClientCallback localMiPushClientCallback = (MiPushClient.MiPushClientCallback)localIterator.next();
        if (isCategoryMatch(paramString1, localMiPushClientCallback.getCategory())) {
          localMiPushClientCallback.onCommandResult(paramString2, paramLong, paramString3, paramList);
        }
      }
    }
  }
  
  public static void onInitializeResult(long paramLong, String paramString1, String paramString2)
  {
    synchronized (sCallbacks)
    {
      Iterator localIterator = sCallbacks.iterator();
      if (localIterator.hasNext()) {
        ((MiPushClient.MiPushClientCallback)localIterator.next()).onInitializeResult(paramLong, paramString1, paramString2);
      }
    }
  }
  
  public static void onReceiveMessage(Context arg0, MiPushMessage paramMiPushMessage)
  {
    synchronized (sCallbacks)
    {
      Iterator localIterator = sCallbacks.iterator();
      while (localIterator.hasNext())
      {
        MiPushClient.MiPushClientCallback localMiPushClientCallback = (MiPushClient.MiPushClientCallback)localIterator.next();
        if (isCategoryMatch(paramMiPushMessage.getCategory(), localMiPushClientCallback.getCategory()))
        {
          localMiPushClientCallback.onReceiveMessage(paramMiPushMessage.getContent(), paramMiPushMessage.getAlias(), paramMiPushMessage.getTopic(), paramMiPushMessage.isNotified());
          localMiPushClientCallback.onReceiveMessage(paramMiPushMessage);
        }
      }
    }
  }
  
  protected static void onSubscribeResult(Context arg0, String paramString1, long paramLong, String paramString2, String paramString3)
  {
    synchronized (sCallbacks)
    {
      Iterator localIterator = sCallbacks.iterator();
      while (localIterator.hasNext())
      {
        MiPushClient.MiPushClientCallback localMiPushClientCallback = (MiPushClient.MiPushClientCallback)localIterator.next();
        if (isCategoryMatch(paramString1, localMiPushClientCallback.getCategory())) {
          localMiPushClientCallback.onSubscribeResult(paramLong, paramString2, paramString3);
        }
      }
    }
  }
  
  protected static void onUnsubscribeResult(Context arg0, String paramString1, long paramLong, String paramString2, String paramString3)
  {
    synchronized (sCallbacks)
    {
      Iterator localIterator = sCallbacks.iterator();
      while (localIterator.hasNext())
      {
        MiPushClient.MiPushClientCallback localMiPushClientCallback = (MiPushClient.MiPushClientCallback)localIterator.next();
        if (isCategoryMatch(paramString1, localMiPushClientCallback.getCategory())) {
          localMiPushClientCallback.onUnsubscribeResult(paramLong, paramString2, paramString3);
        }
      }
    }
  }
  
  public static void processMessageForCallback(Context paramContext, PushMessageInterface paramPushMessageInterface)
  {
    Object localObject = null;
    if ((paramPushMessageInterface instanceof MiPushMessage)) {
      onReceiveMessage(paramContext, (MiPushMessage)paramPushMessageInterface);
    }
    MiPushCommandMessage localMiPushCommandMessage;
    do
    {
      do
      {
        return;
      } while (!(paramPushMessageInterface instanceof MiPushCommandMessage));
      localMiPushCommandMessage = (MiPushCommandMessage)paramPushMessageInterface;
      paramPushMessageInterface = localMiPushCommandMessage.getCommand();
      if ("register".equals(paramPushMessageInterface))
      {
        paramPushMessageInterface = localMiPushCommandMessage.getCommandArguments();
        paramContext = (Context)localObject;
        if (paramPushMessageInterface != null)
        {
          paramContext = (Context)localObject;
          if (!paramPushMessageInterface.isEmpty()) {
            paramContext = (String)paramPushMessageInterface.get(0);
          }
        }
        onInitializeResult(localMiPushCommandMessage.getResultCode(), localMiPushCommandMessage.getReason(), paramContext);
        return;
      }
      if (("set-alias".equals(paramPushMessageInterface)) || ("unset-alias".equals(paramPushMessageInterface)) || ("accept-time".equals(paramPushMessageInterface)))
      {
        onCommandResult(paramContext, localMiPushCommandMessage.getCategory(), paramPushMessageInterface, localMiPushCommandMessage.getResultCode(), localMiPushCommandMessage.getReason(), localMiPushCommandMessage.getCommandArguments());
        return;
      }
      if ("subscribe-topic".equals(paramPushMessageInterface))
      {
        paramPushMessageInterface = localMiPushCommandMessage.getCommandArguments();
        if ((paramPushMessageInterface != null) && (!paramPushMessageInterface.isEmpty())) {}
        for (paramPushMessageInterface = (String)paramPushMessageInterface.get(0);; paramPushMessageInterface = null)
        {
          onSubscribeResult(paramContext, localMiPushCommandMessage.getCategory(), localMiPushCommandMessage.getResultCode(), localMiPushCommandMessage.getReason(), paramPushMessageInterface);
          return;
        }
      }
    } while (!"unsubscibe-topic".equals(paramPushMessageInterface));
    paramPushMessageInterface = localMiPushCommandMessage.getCommandArguments();
    if ((paramPushMessageInterface != null) && (!paramPushMessageInterface.isEmpty())) {}
    for (paramPushMessageInterface = (String)paramPushMessageInterface.get(0);; paramPushMessageInterface = null)
    {
      onUnsubscribeResult(paramContext, localMiPushCommandMessage.getCategory(), localMiPushCommandMessage.getResultCode(), localMiPushCommandMessage.getReason(), paramPushMessageInterface);
      return;
    }
  }
  
  protected static void removeAllPushCallbackClass()
  {
    synchronized (sCallbacks)
    {
      sCallbacks.clear();
      return;
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    try
    {
      if ("com.xiaomi.mipush.sdk.WAKEUP".equals(paramIntent.getAction()))
      {
        if (!AppInfoHolder.getInstance(this).appRegistered()) {
          return;
        }
        PushServiceClient.getInstance(this).awakePushService();
        return;
      }
      if (1 != PushMessageHelper.getPushMode(this)) {
        break label75;
      }
      if (isCallbackEmpty())
      {
        MyLog.e("receive a message before application calling initialize");
        return;
      }
    }
    catch (Throwable paramIntent)
    {
      MyLog.e(paramIntent);
      return;
    }
    paramIntent = PushMessageProcessor.getInstance(this).proccessIntent(paramIntent);
    if (paramIntent != null)
    {
      processMessageForCallback(this, paramIntent);
      return;
      label75:
      Intent localIntent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
      localIntent.setPackage(getPackageName());
      localIntent.putExtras(paramIntent);
      paramIntent = getPackageManager();
      try
      {
        Object localObject2 = paramIntent.queryBroadcastReceivers(localIntent, 32);
        Object localObject1 = null;
        paramIntent = (Intent)localObject1;
        if (localObject2 != null)
        {
          localObject2 = ((List)localObject2).iterator();
          do
          {
            paramIntent = (Intent)localObject1;
            if (!((Iterator)localObject2).hasNext()) {
              break;
            }
            paramIntent = (ResolveInfo)((Iterator)localObject2).next();
          } while ((activityInfo == null) || (!activityInfo.packageName.equals(getPackageName())));
        }
        if (paramIntent != null)
        {
          ((PushMessageReceiver)Class.forName(activityInfo.name).newInstance()).onReceive(getApplicationContext(), localIntent);
          return;
        }
      }
      catch (Exception paramIntent)
      {
        MyLog.e(paramIntent);
        return;
      }
      MyLog.e("cannot find the receiver to handler this message, check your manifest");
    }
  }
  
  static abstract interface PushMessageInterface
    extends Serializable
  {}
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.PushMessageHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */