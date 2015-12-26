package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PushPreferencesUtils
{
  private static final String MZ_PUSH_PREFERENCE = "mz_push_preference";
  private static final String MZ_PUSH_PREFIX_NOTIFICATION_ID = ".notification_id";
  private static final String MZ_PUSH_PREFIX_PUSH_TASK_ID = ".notification_push_task_id";
  
  public static int getDiscardNotificationId(Context paramContext, String paramString)
  {
    return getSharePerferenceByName(paramContext, "mz_push_preference").getInt(paramString + ".notification_id", 0);
  }
  
  public static int getDiscardNotificationTaskId(Context paramContext, String paramString)
  {
    return getSharePerferenceByName(paramContext, "mz_push_preference").getInt(paramString + ".notification_push_task_id", 0);
  }
  
  public static String getPushIdByPkg(Context paramContext, String paramString)
  {
    return getSharePerferenceByName(paramContext, "mz_push_preference").getString(paramString, null);
  }
  
  public static SharedPreferences getSharePerferenceByName(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0);
  }
  
  public static String getStringBykey(Context paramContext, String paramString1, String paramString2)
  {
    return getSharePerferenceByName(paramContext, paramString1).getString(paramString2, null);
  }
  
  public static void putDiscardNotificationIdByPackageName(Context paramContext, String paramString, int paramInt)
  {
    putIntBykey(paramContext, "mz_push_preference", paramString + ".notification_id", paramInt);
  }
  
  public static void putDiscardNotificationTaskId(Context paramContext, String paramString, int paramInt)
  {
    putIntBykey(paramContext, "mz_push_preference", paramString + ".notification_push_task_id", paramInt);
  }
  
  public static void putIntBykey(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    getSharePerferenceByName(paramContext, paramString1).edit().putInt(paramString2, paramInt).commit();
  }
  
  public static void putPushIdByPkg(Context paramContext, String paramString1, String paramString2)
  {
    putStringByKey(paramContext, "mz_push_preference", paramString1, paramString2);
  }
  
  public static void putStringByKey(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    getSharePerferenceByName(paramContext, paramString1).edit().putString(paramString2, paramString3).commit();
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.util.PushPreferencesUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */