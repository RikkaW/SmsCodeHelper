package cn.com.xy.sms.sdk.log;

import android.util.Log;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class LogManager
{
  private static SimpleDateFormat a = null;
  public static boolean debug = false;
  public static boolean writeFileLog = false;
  
  private static SimpleDateFormat a()
  {
    try
    {
      if (a == null) {
        a = new SimpleDateFormat("yyyy.MM.dd");
      }
      SimpleDateFormat localSimpleDateFormat = a;
      return localSimpleDateFormat;
    }
    finally {}
  }
  
  public static void d(String paramString1, String paramString2)
  {
    if (debug) {
      Log.d(paramString1, paramString2);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (debug) {
      Log.d(paramString1, paramString2, paramThrowable);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void e(String paramString1, String paramString2)
  {
    if (debug) {
      Log.e(paramString1, paramString2);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (debug) {
      Log.e(paramString1, paramString2, paramThrowable);
    }
    writeLogToFile(paramString1, paramString2);
    try
    {
      DuoquUtils.getSdkDoAction().logError(paramString1, paramString2, paramThrowable);
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void i(String paramString1, String paramString2)
  {
    if (debug) {
      Log.i(paramString1, paramString2);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (debug) {
      Log.i(paramString1, paramString2, paramThrowable);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void ll(String paramString1, String paramString2)
  {
    if (debug) {
      Log.d(paramString1, paramString2);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void w(String paramString1, String paramString2)
  {
    if (debug) {
      Log.w(paramString1, paramString2);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (debug) {
      Log.w(paramString1, paramString2, paramThrowable);
    }
    writeLogToFile(paramString1, paramString2);
  }
  
  public static void writeLogToFile(String paramString1, String paramString2)
  {
    if (!writeFileLog) {
      return;
    }
    try
    {
      synchronized (a())
      {
        Object localObject = ???.format(Long.valueOf(System.currentTimeMillis()));
        String str = Constant.getFilePath() + File.separator + "logs";
        File localFile = new File(str);
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        localObject = new PrintStream(new FileOutputStream(str + File.separator + (String)localObject + ".ll.log", true));
        ((PrintStream)localObject).println(paramString1 + "  " + paramString2 + " time=" + System.currentTimeMillis());
        ((PrintStream)localObject).close();
        return;
      }
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void writeLogToFile(String paramString1, String paramString2, String paramString3)
  {
    if (!writeFileLog) {
      return;
    }
    try
    {
      synchronized (a())
      {
        String str1 = ???.format(Long.valueOf(System.currentTimeMillis()));
        String str2 = Constant.getFilePath() + File.separator + "logs";
        File localFile = new File(str2);
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        paramString3 = new PrintStream(new FileOutputStream(str2 + File.separator + str1 + paramString3, true));
        paramString3.println(paramString1 + "  " + paramString2 + " time=" + System.currentTimeMillis());
        paramString3.close();
        return;
      }
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.log.LogManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */