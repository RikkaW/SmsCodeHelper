package com.meizu.cloud.pushsdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.NotificationTools;
import com.meizu.cloud.pushsdk.notification.PushNotificationBuilder;
import com.meizu.cloud.pushsdk.util.PushPreferencesUtils;
import com.meizu.cloud.pushsdk.util.UxIPUtils;

public abstract class MzPushMessageReceiver
  extends BroadcastReceiver
{
  public static final String TAG = "MzPushMessageReceiver";
  public static String deviceId;
  
  private void parseMethodMessage(Context paramContext, Intent paramIntent, String paramString)
  {
    if ("message".equals(paramString))
    {
      paramString = paramIntent.getStringExtra("message");
      paramIntent = paramIntent.getStringExtra("extra_app_push_task_Id");
      Log.i("MzPushMessageReceiver", " packageName " + paramContext.getPackageName() + "receive through message " + paramString + " taskId " + paramIntent);
      onMessage(paramContext, paramString);
    }
    do
    {
      return;
      if ("private".equals(paramString))
      {
        paramIntent = (MPushMessage)paramIntent.getSerializableExtra("pushMessage");
        Log.i("MzPushMessageReceiver", " packageName " + paramContext.getPackageName() + "push private message " + paramIntent);
        PushPreferencesUtils.putDiscardNotificationIdByPackageName(paramContext, paramIntent.getPackageName(), 0);
        paramString = NotificationTools.buildIntent(paramContext, paramIntent);
        if (paramString != null)
        {
          paramString.addFlags(268435456);
          paramContext.startActivity(paramString);
          UxIPUtils.onClickPushMessageEvent(paramContext, paramIntent.getPackageName(), deviceId, paramIntent.getTaskId());
        }
        Log.i("MzPushMessageReceiver", "unable click message");
        return;
      }
      if ("notification_show".equals(paramString))
      {
        paramIntent = (MPushMessage)paramIntent.getSerializableExtra("pushMessage");
        Log.i("MzPushMessageReceiver", " packageName " + paramContext.getPackageName() + " push notification message " + paramIntent);
        PushPreferencesUtils.putDiscardNotificationIdByPackageName(paramContext, paramIntent.getPackageName(), 0);
        paramString = new PushNotificationBuilder();
        onUpdateNotificationBuilder(paramString);
        NotificationTools.showPrivateNotification(paramContext, paramIntent, paramString);
        UxIPUtils.onReceivePushMessageEvent(paramContext, paramIntent.getPackageName(), deviceId, paramIntent.getTaskId());
        return;
      }
    } while (!"notification_delete".equals(paramString));
    paramIntent = (MPushMessage)paramIntent.getSerializableExtra("pushMessage");
    Log.i("MzPushMessageReceiver", " packageName " + paramContext.getPackageName() + " delete notification message " + paramIntent);
  }
  
  private void processRegisterCallback(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("registration_id");
    Log.i("MzPushMessageReceiver", "receive push action " + paramIntent.getAction() + " pushId " + str);
    paramContext.getSharedPreferences("com.meizu.flyme.push", 0).edit().putString("pushId", str).commit();
    onRegister(paramContext, str);
  }
  
  private void processUnRegisterCallback(Context paramContext, Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra("extra_app_is_unregister_success", false);
    String str = paramIntent.getStringExtra("registration_error");
    paramIntent = paramIntent.getStringExtra("unregistered");
    Log.i("MzPushMessageReceiver", "processUnRegisterCallback 5.0:" + bool + " 4.0:" + str + " 3.0:" + paramIntent);
    if ((TextUtils.isEmpty(str)) || (bool) || (!TextUtils.isEmpty(paramIntent)))
    {
      paramContext.getSharedPreferences("com.meizu.flyme.push", 0).edit().putString("pushId", "").commit();
      onUnRegister(paramContext, true);
      return;
    }
    onUnRegister(paramContext, false);
  }
  
  public void onMessage(Context paramContext, Intent paramIntent) {}
  
  public abstract void onMessage(Context paramContext, String paramString);
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("MzPushMessageReceiver", " receive pushaciton " + paramIntent.getAction());
    if ("com.meizu.flyme.push.intent.REGISTER.FEEDBACK".equals(paramIntent.getAction())) {
      processRegisterCallback(paramContext, paramIntent);
    }
    do
    {
      String str1;
      do
      {
        return;
        if ("com.meizu.flyme.push.intent.UNREGISTER.FEEDBACK".equals(paramIntent.getAction()))
        {
          processUnRegisterCallback(paramContext, paramIntent);
          return;
        }
        if ("com.meizu.c2dm.intent.REGISTRATION".equals(paramIntent.getAction()))
        {
          if (!TextUtils.isEmpty(paramIntent.getStringExtra("registration_id")))
          {
            processRegisterCallback(paramContext, paramIntent);
            return;
          }
          processUnRegisterCallback(paramContext, paramIntent);
          return;
        }
        if (!"com.meizu.flyme.push.intent.MESSAGE".equals(paramIntent.getAction())) {
          break;
        }
        str1 = paramIntent.getStringExtra("method");
        String str2 = paramIntent.getStringExtra("message");
        Log.i("MzPushMessageReceiver", "receive Push Message " + str2 + " method=" + str1);
        if ((TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)))
        {
          onMessage(paramContext, str2);
          return;
        }
      } while (TextUtils.isEmpty(str1));
      if (!TextUtils.isEmpty(paramIntent.getStringExtra("statistics_imei_key"))) {
        deviceId = paramIntent.getStringExtra("statistics_imei_key");
      }
      Log.i("MzPushMessageReceiver", "current deviceId=" + deviceId);
      parseMethodMessage(paramContext, paramIntent, str1);
      return;
    } while (!"com.meizu.c2dm.intent.RECEIVE".equals(paramIntent.getAction()));
    Log.i("MzPushMessageReceiver", "flyme3 Message arrive");
    onMessage(paramContext, paramIntent);
  }
  
  public abstract void onRegister(Context paramContext, String paramString);
  
  public abstract void onUnRegister(Context paramContext, boolean paramBoolean);
  
  public void onUpdateNotificationBuilder(PushNotificationBuilder paramPushNotificationBuilder) {}
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.MzPushMessageReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */