package com.xiaomi.channel.commonutils.logger;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog
{
  private static int LOG_LEVEL = 2;
  private static final Integer NEGATIVE_CODE = Integer.valueOf(-1);
  private static LoggerInterface logger = new DefaultAndroidLogger();
  private static final HashMap<Integer, String> mActionNames;
  private static AtomicInteger mCodeGenerator = new AtomicInteger(1);
  private static final HashMap<Integer, Long> mStartTimes = new HashMap();
  
  static
  {
    mActionNames = new HashMap();
  }
  
  public static void e(String paramString)
  {
    log(4, paramString);
  }
  
  public static void e(String paramString, Throwable paramThrowable)
  {
    log(4, paramString, paramThrowable);
  }
  
  public static void e(Throwable paramThrowable)
  {
    log(4, paramThrowable);
  }
  
  public static void info(String paramString)
  {
    log(0, paramString);
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
  
  public static void pe(Integer paramInteger)
  {
    if ((LOG_LEVEL > 1) || (!mStartTimes.containsKey(paramInteger))) {
      return;
    }
    long l1 = ((Long)mStartTimes.remove(paramInteger)).longValue();
    paramInteger = (String)mActionNames.remove(paramInteger);
    long l2 = System.currentTimeMillis();
    logger.log(paramInteger + " ends in " + (l2 - l1) + " ms");
  }
  
  public static Integer ps(String paramString)
  {
    if (LOG_LEVEL <= 1)
    {
      Integer localInteger = Integer.valueOf(mCodeGenerator.incrementAndGet());
      mStartTimes.put(localInteger, Long.valueOf(System.currentTimeMillis()));
      mActionNames.put(localInteger, paramString);
      logger.log(paramString + " starts");
      return localInteger;
    }
    return NEGATIVE_CODE;
  }
  
  public static void v(String paramString)
  {
    log(1, "[Thread:" + Thread.currentThread().getId() + "] " + paramString);
  }
  
  public static void warn(String paramString)
  {
    log(2, "[Thread:" + Thread.currentThread().getId() + "] " + paramString);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.logger.MyLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */