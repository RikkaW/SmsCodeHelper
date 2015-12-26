package com.meizu.statsapp;

import akc;
import android.util.Log;

public class UsageStatusLog
{
  private static final String TAG = "UsageStats_";
  private static boolean sDebug = false;
  
  public static void d(String paramString1, String paramString2)
  {
    if (sDebug) {
      Log.d("UsageStats_" + paramString1, paramString2);
    }
  }
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (sDebug) {
      Log.d("UsageStats_" + paramString1, paramString2, paramThrowable);
    }
  }
  
  public static void initLog()
  {
    try
    {
      Object localObject = akc.a("android.os.SystemProperties", "get", new Object[] { "persist.meizu.usagestats.debug" });
      if (localObject != null) {
        sDebug = Boolean.valueOf(localObject.toString()).booleanValue();
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.statsapp.UsageStatusLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */