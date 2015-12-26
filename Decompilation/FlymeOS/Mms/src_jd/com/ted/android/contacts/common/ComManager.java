package com.ted.android.contacts.common;

import android.content.Context;
import bq;

public class ComManager
{
  public static void a(Context paramContext)
  {
    System.loadLibrary("teddycom-jni");
    b(paramContext);
  }
  
  private static void b(Context paramContext)
  {
    initJni();
    DataBus.CONTACT = getVer(bq.a(paramContext), bq.b(paramContext));
    DataBus.U1_YEK = getSeal();
    DataBus.FILE_MASK = getFileMask();
  }
  
  public static native String getFileMask();
  
  public static native String getSeal();
  
  public static native String getVer(String paramString1, String paramString2);
  
  public static native void initJni();
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.common.ComManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */