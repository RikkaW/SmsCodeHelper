package com.xiaomi.mms.mx.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceUtils
{
  public static long getSettingLong(Context paramContext, String paramString, long paramLong)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(paramString, paramLong);
  }
  
  public static void setSettingLong(Context paramContext, String paramString, long paramLong)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong(paramString, paramLong).commit();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.utils.PreferenceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */