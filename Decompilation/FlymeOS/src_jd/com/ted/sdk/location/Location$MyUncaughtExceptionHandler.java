package com.ted.sdk.location;

import android.util.Log;

class Location$MyUncaughtExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(Location.access$0(), "caught: " + paramThrowable);
    paramThrowable.printStackTrace();
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.location.Location.MyUncaughtExceptionHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */