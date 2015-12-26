package com.ted.sdk.yellow.util;

import android.util.Log;

class AssetsDatabaseHelper$MyUncaughtExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(AssetsDatabaseHelper.access$0(), "caught: " + paramThrowable);
    paramThrowable.printStackTrace();
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.AssetsDatabaseHelper.MyUncaughtExceptionHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */