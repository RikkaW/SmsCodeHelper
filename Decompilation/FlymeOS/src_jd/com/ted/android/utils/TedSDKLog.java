package com.ted.android.utils;

import android.util.Log;

public final class TedSDKLog
{
  public static final boolean LOGD = false;
  public static final boolean LOGE = true;
  public static final boolean LOGI = false;
  public static final boolean LOGV = false;
  public static final boolean LOGW = true;
  private static final String TAG = "TedSDKLog";
  
  public static void begin(String paramString) {}
  
  public static void d(String paramString1, String paramString2) {}
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public static void e(String paramString1, String paramString2)
  {
    Log.e("TedSDKLog", paramString1 + ": " + paramString2);
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    Log.e("TedSDKLog", paramString1 + ": " + paramString2, paramThrowable);
  }
  
  public static void end(String paramString) {}
  
  private static String getMethodName()
  {
    StackTraceElement[] arrayOfStackTraceElement = new Exception().getStackTrace();
    String str2 = "";
    String str1 = str2;
    if (arrayOfStackTraceElement != null)
    {
      str1 = str2;
      if (arrayOfStackTraceElement.length > 2) {
        str1 = arrayOfStackTraceElement[2].getMethodName();
      }
    }
    return str1;
  }
  
  public static void i(String paramString1, String paramString2) {}
  
  public static void method(String paramString) {}
  
  public static void v(String paramString1, String paramString2) {}
  
  public static void v(String paramString1, String paramString2, Throwable paramThrowable) {}
  
  public static void w(String paramString1, String paramString2)
  {
    Log.w("TedSDKLog", paramString1 + ": " + paramString2);
  }
  
  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    Log.w("TedSDKLog", paramString1 + ": " + paramString2, paramThrowable);
  }
  
  public static void w(String paramString, Throwable paramThrowable)
  {
    Log.w("TedSDKLog", paramThrowable);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.utils.TedSDKLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */