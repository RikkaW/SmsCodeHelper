package com.meizu.cloud.pushsdk;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.meizu.cloud.pushsdk.util.SystemUtils;

public class PushManager
{
  static final String KEY_PUSH_ID = "pushId";
  static final String PUSH_ID_PREFERENCE_NAME = "com.meizu.flyme.push";
  private static final String TAG = "PushManager-v1.2.3";
  
  public static String getPushId(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.meizu.flyme.push", 0).getString("pushId", null);
  }
  
  public static void register(Context paramContext)
  {
    String str = SystemUtils.getAppVersionName(paramContext, "com.meizu.cloud");
    Log.i("PushManager-v1.2.3", paramContext.getPackageName() + " start register cloudVersion_name " + str);
    Intent localIntent = new Intent("com.meizu.flyme.push.intent.REGISTER");
    if ("com.meizu.cloud".equals(SystemUtils.getMzPushServicePackageName(paramContext)))
    {
      localIntent.setClassName("com.meizu.cloud", "com.meizu.cloud.pushsdk.pushservice.MzPushService");
      localIntent.putExtra("sender", paramContext.getPackageName());
    }
    for (;;)
    {
      paramContext.startService(localIntent);
      return;
      if ((!TextUtils.isEmpty(str)) && (SystemUtils.compareVersion(str, "4.5.7")))
      {
        Log.i("PushManager-v1.2.3", "flyme 4.x start register cloud versionName " + str);
        localIntent.setPackage("com.meizu.cloud");
        localIntent.putExtra("sender", paramContext.getPackageName());
      }
      else
      {
        Log.i("PushManager-v1.2.3", "flyme 3.x start register cloud versionName " + str);
        localIntent.setAction("com.meizu.c2dm.intent.REGISTER");
        localIntent.setPackage("com.meizu.cloud");
        localIntent.putExtra("app", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
        localIntent.putExtra("sender", paramContext.getPackageName());
      }
    }
  }
  
  public static void unRegister(Context paramContext)
  {
    String str = SystemUtils.getAppVersionName(paramContext, "com.meizu.cloud");
    Log.i("PushManager-v1.2.3", paramContext.getPackageName() + " start unRegister cloud versionName " + str);
    Intent localIntent = new Intent("com.meizu.flyme.push.intent.UNREGISTER");
    if ("com.meizu.cloud".equals(SystemUtils.getMzPushServicePackageName(paramContext)))
    {
      localIntent.setClassName("com.meizu.cloud", "com.meizu.cloud.pushsdk.pushservice.MzPushService");
      localIntent.putExtra("sender", paramContext.getPackageName());
    }
    for (;;)
    {
      paramContext.startService(localIntent);
      return;
      if ((!TextUtils.isEmpty(str)) && (SystemUtils.compareVersion(str, "4.5.7")))
      {
        localIntent.setPackage("com.meizu.cloud");
        localIntent.putExtra("sender", paramContext.getPackageName());
      }
      else
      {
        localIntent.setAction("com.meizu.c2dm.intent.UNREGISTER");
        localIntent.setPackage("com.meizu.cloud");
        localIntent.putExtra("app", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
        localIntent.putExtra("sender", paramContext.getPackageName());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.PushManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */