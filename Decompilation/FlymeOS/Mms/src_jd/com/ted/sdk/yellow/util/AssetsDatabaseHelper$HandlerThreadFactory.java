package com.ted.sdk.yellow.util;

import java.util.concurrent.ThreadFactory;

class AssetsDatabaseHelper$HandlerThreadFactory
  implements ThreadFactory
{
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = new Thread(paramRunnable);
    paramRunnable.setUncaughtExceptionHandler(new AssetsDatabaseHelper.MyUncaughtExceptionHandler(null));
    return paramRunnable;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.AssetsDatabaseHelper.HandlerThreadFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */