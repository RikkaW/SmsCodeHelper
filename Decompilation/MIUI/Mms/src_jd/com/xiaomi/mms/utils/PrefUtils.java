package com.xiaomi.mms.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PrefUtils
{
  public static String getString(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("pref_mx", 0).getString(paramString, null);
  }
  
  public static void remove(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("pref_mx", 0).edit().remove(paramString).commit();
  }
  
  public static void saveString(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("pref_mx", 0).edit();
    paramContext.putString(paramString1, paramString2);
    paramContext.commit();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.PrefUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */