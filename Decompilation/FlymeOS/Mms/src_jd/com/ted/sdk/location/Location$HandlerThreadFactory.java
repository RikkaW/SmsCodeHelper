package com.ted.sdk.location;

import java.util.concurrent.ThreadFactory;

class Location$HandlerThreadFactory
  implements ThreadFactory
{
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable);
    paramRunnable.setUncaughtExceptionHandler(new Location.MyUncaughtExceptionHandler(null));
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.Location.HandlerThreadFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */