package com.android.mms.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool
{
  private static int THREAD_POOL_COUNT = 32;
  private static ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(THREAD_POOL_COUNT, THREAD_POOL_COUNT, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  
  static
  {
    sExecutor.allowCoreThreadTimeOut(true);
  }
  
  public static void execute(Runnable paramRunnable)
  {
    sExecutor.execute(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.ThreadPool
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */