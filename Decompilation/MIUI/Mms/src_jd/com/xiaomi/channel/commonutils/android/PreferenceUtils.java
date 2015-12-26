package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public abstract class PreferenceUtils
{
  public static void checkProcess(Context paramContext) {}
  
  public static boolean getSettingBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    checkProcess(paramContext);
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramString, paramBoolean);
  }
  
  public static void setSettingBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    checkProcess(paramContext);
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean(paramString, paramBoolean).commit();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.android.PreferenceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */