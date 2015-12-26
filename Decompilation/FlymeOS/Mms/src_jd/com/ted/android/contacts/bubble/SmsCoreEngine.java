package com.ted.android.contacts.bubble;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import ask;
import com.ted.android.contacts.common.util.NovoFileUtil;
import com.ted.android.utils.TedSDKLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SmsCoreEngine
{
  private static final String a = SmsCoreEngine.class.getSimpleName();
  private static long b = 0L;
  private static String c = null;
  private static String d = null;
  private static int e = -1;
  private static boolean f = false;
  private static boolean g = false;
  private static Object h = new Object();
  private static ask i;
  
  static
  {
    System.loadLibrary("SmsCore");
  }
  
  private static String a(Context paramContext, String paramString)
  {
    String str = paramContext.getFilesDir().getAbsolutePath() + File.separator + paramString;
    File localFile = new File(str);
    int j = i.a(str);
    TedSDKLog.d(a, "Check " + paramString + " result: " + j);
    if ((j == 1) || (j == 3)) {
      a(paramContext, localFile);
    }
    return str;
  }
  
  private static void a(Context paramContext, File paramFile)
  {
    if (!paramFile.exists()) {
      try
      {
        paramContext = NovoFileUtil.openLatestInputFile(paramContext, paramFile.getName());
        paramFile = new FileOutputStream(paramFile.getAbsolutePath());
        byte[] arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
          int j = paramContext.read(arrayOfByte);
          if (j == -1)
          {
            paramContext.close();
            paramFile.close();
            return;
          }
          paramFile.write(arrayOfByte, 0, j);
        }
        return;
      }
      catch (FileNotFoundException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  private static void a(String paramString)
  {
    if (!TextUtils.equals(paramString, c))
    {
      matchAC(paramString, b);
      c = paramString;
    }
  }
  
  public static native boolean doBubbleInit(String paramString, int paramInt, long paramLong);
  
  public static native boolean doCardInit(String paramString, int paramInt, long paramLong);
  
  public static native long doInit(String paramString, boolean paramBoolean);
  
  public static native int doMultiClassify(long paramLong);
  
  public static native boolean doMultiInit(String paramString, long paramLong);
  
  public static native void doRelease(long paramLong);
  
  public static native int doSpamClassify(long paramLong);
  
  public static native boolean doSpamInit(String paramString, long paramLong);
  
  public static int getMultiCategory(String paramString)
  {
    a(paramString);
    if ((e == -1) || (!TextUtils.equals(paramString, d)))
    {
      e = doMultiClassify(b);
      d = paramString;
    }
    return e;
  }
  
  public static void init(Context paramContext)
  {
    i = ask.a(paramContext);
    if ((f) || (g)) {
      return;
    }
    g = true;
    if (b != 0L)
    {
      doRelease(b);
      b = 0L;
    }
    if (b == 0L)
    {
      c = null;
      d = null;
      e = -1;
      b = doInit(a(paramContext, "sms.index"), false);
    }
    if (b != 0L)
    {
      doBubbleInit(a(paramContext, "bubble.bin"), 20, b);
      doCardInit(a(paramContext, "card.bin"), 20, b);
      doSpamInit(a(paramContext, "spam.bin"), b);
      boolean bool = doMultiInit(a(paramContext, "multi.bin"), b);
      Log.d(a, "isInitMulti: " + bool);
    }
    g = false;
    f = true;
  }
  
  public static int isSpamMessage(String paramString)
  {
    a(paramString);
    return doSpamClassify(b);
  }
  
  public static native boolean matchAC(String paramString, long paramLong);
  
  public static void release()
  {
    doRelease(b);
    b = 0L;
    c = null;
    d = null;
    e = -1;
    f = false;
  }
  
  public static native int[] searchBubble(String paramString, int paramInt, long paramLong);
  
  public static int[] searchBubble(String paramString1, String paramString2)
  {
    if (f) {
      return searchBubble(paramString2, getMultiCategory(paramString1), b);
    }
    return null;
  }
  
  public static native int[] searchCard(String paramString, int paramInt, long paramLong);
  
  public static int[] searchCard(String paramString1, String paramString2)
  {
    if (f) {
      return searchCard(paramString2, getMultiCategory(paramString1), b);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.bubble.SmsCoreEngine
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */