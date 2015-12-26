package com.xiaomi.mms.utils.logger;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog
{
  private static int LOG_LEVEL = 0;
  private static final Integer NEGATIVE_CODE = Integer.valueOf(-1);
  private static LoggerInterface logger = new DefaultAndroidLogger();
  private static HashMap<Integer, String> mActionNames;
  private static AtomicInteger mCodeGenerator = new AtomicInteger(1);
  private static HashMap<Integer, Long> mStartTimes = new HashMap();
  
  static
  {
    mActionNames = new HashMap();
  }
  
  public static void d(String paramString)
  {
    log(1, paramString);
  }
  
  public static void d(String paramString1, String paramString2)
  {
    log(1, paramString1 + ", " + paramString2);
  }
  
  public static void e(String paramString)
  {
    log(4, paramString);
  }
  
  public static void e(String paramString1, String paramString2)
  {
    log(4, paramString1 + ", " + paramString2);
  }
  
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    log(4, paramString1 + ", " + paramString2, paramThrowable);
  }
  
  public static void e(String paramString, Throwable paramThrowable)
  {
    log(4, paramString, paramThrowable);
  }
  
  public static void e(Throwable paramThrowable)
  {
    log(4, paramThrowable);
  }
  
  public static void i(String paramString1, String paramString2)
  {
    log(0, paramString1 + ", " + paramString2);
  }
  
  public static void log(int paramInt, String paramString)
  {
    if (paramInt >= LOG_LEVEL) {
      logger.log(paramString);
    }
  }
  
  public static void log(int paramInt, String paramString, Throwable paramThrowable)
  {
    if (paramInt >= LOG_LEVEL) {
      logger.log(paramString, paramThrowable);
    }
  }
  
  public static void log(int paramInt, Throwable paramThrowable)
  {
    if (paramInt >= LOG_LEVEL) {
      logger.log("", paramThrowable);
    }
  }
  
  public static void setLogLevel(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 5)) {
      log(2, "set log level as " + paramInt);
    }
    LOG_LEVEL = paramInt;
  }
  
  public static void setLogger(LoggerInterface paramLoggerInterface)
  {
    logger = paramLoggerInterface;
  }
  
  public static void v(String paramString)
  {
    log(1, paramString);
  }
  
  public static void v(String paramString1, String paramString2)
  {
    log(1, paramString1 + ", " + paramString2);
  }
  
  public static void w(String paramString1, String paramString2)
  {
    log(2, paramString1 + ", " + paramString2);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.MyLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */