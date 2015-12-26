package com.ted.android.contacts.block;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import anv;
import bm;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.contacts.common.util.NovoFileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SpamMsgEngine
{
  private static long a = 0L;
  
  static
  {
    System.loadLibrary("spam");
  }
  
  public static int a(Context paramContext)
  {
    try
    {
      int i = paramContext.getSharedPreferences("SPAM_BOX_DATA", 0).getInt("SPAM_BOX_CHECK_COUNTS", 0);
      return i;
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static void a()
  {
    if (a != 0L)
    {
      doRelease(a);
      a = 0L;
    }
  }
  
  private static void a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("SPAM_BOX_DATA", 0).edit();
    paramContext.putInt("SPAM_BOX_CHECK_COUNTS", paramInt);
    paramContext.commit();
  }
  
  private static void a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean) {
      b(paramContext, b(paramContext) + 1);
    }
    a(paramContext, a(paramContext) + 1);
  }
  
  public static final boolean a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (bm.a(paramContext, paramString1)) {
      return false;
    }
    return b(paramContext, paramString1, paramString2, paramString3);
  }
  
  public static int b(Context paramContext)
  {
    try
    {
      int i = paramContext.getSharedPreferences("SPAM_BOX_DATA", 0).getInt("SPAM_BOX_AFFIRM_COUNTS", 0);
      return i;
    }
    catch (NullPointerException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  private static void b(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getSharedPreferences("SPAM_BOX_DATA", 0).edit();
    paramContext.putInt("SPAM_BOX_AFFIRM_COUNTS", paramInt);
    paramContext.commit();
  }
  
  private static final boolean b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (a == 0L) {
      c(paramContext);
    }
    boolean bool = false;
    if (a != 0L)
    {
      bool = isSpamMsg(paramString2, a);
      a(paramContext, bool);
    }
    return bool;
  }
  
  public static void c(Context paramContext)
  {
    if (a == 0L)
    {
      bm.a(paramContext);
      paramContext = d(paramContext);
      a = doInit(paramContext);
      new File(paramContext).delete();
    }
  }
  
  private static String d(Context paramContext)
  {
    try
    {
      InputStream localInputStream = NovoFileUtil.openLatestInputFile(paramContext, "sms.model");
      paramContext = paramContext.getCacheDir().getAbsolutePath() + "/" + "sms.model" + ".dec";
      FileOutputStream localFileOutputStream;
      localException1.printStackTrace();
    }
    catch (Exception localException1)
    {
      try
      {
        localFileOutputStream = new FileOutputStream(paramContext);
        anv.a(localInputStream, localFileOutputStream, DataBus.FILE_MASK);
        localInputStream.close();
        localFileOutputStream.close();
        return paramContext;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      paramContext = "";
    }
    return paramContext;
  }
  
  private static native long doInit(String paramString);
  
  private static native void doRelease(long paramLong);
  
  private static native boolean isSpamMsg(String paramString, long paramLong);
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.block.SpamMsgEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */