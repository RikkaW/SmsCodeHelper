package com.xiaomi.mms.mx.utils;

import miui.os.Build;

public class Log
{
  private static final boolean DEBUG = Build.IS_DEBUGGABLE;
  
  public static void d(String paramString1, String paramString2)
  {
    if (DEBUG) {
      android.util.Log.d("MiXinSDK", paramString1 + ":" + paramString2);
    }
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if (DEBUG) {
      android.util.Log.e("MiXinSDK", paramString1 + ":" + paramString2);
    }
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (DEBUG) {
      android.util.Log.e("MiXinSDK", paramString1 + ":" + paramString2, paramThrowable);
    }
  }
  
  public static void i(String paramString1, String paramString2)
  {
    if (DEBUG) {
      android.util.Log.i("MiXinSDK", paramString1 + ":" + paramString2);
    }
  }
  
  public static boolean isDebug()
  {
    return DEBUG;
  }
  
  public static void v(String paramString1, String paramString2)
  {
    if (DEBUG) {
      android.util.Log.v("MiXinSDK", paramString1 + ":" + paramString2);
    }
  }
  
  public static void w(String paramString1, String paramString2)
  {
    if (DEBUG) {
      android.util.Log.d("MiXinSDK", paramString1 + ":" + paramString2);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.utils.Log
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */