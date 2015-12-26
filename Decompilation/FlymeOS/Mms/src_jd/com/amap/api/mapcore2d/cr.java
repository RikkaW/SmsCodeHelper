package com.amap.api.mapcore2d;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class cr
  implements ThreadFactory
{
  private final AtomicInteger a = new AtomicInteger(1);
  
  public Thread newThread(Runnable paramRunnable)
  {
    return new Thread(paramRunnable, "AsyncTask #" + a.getAndIncrement());
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */