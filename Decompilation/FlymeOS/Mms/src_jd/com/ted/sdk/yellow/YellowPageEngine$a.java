package com.ted.sdk.yellow;

import android.util.Log;

class YellowPageEngine$a
  implements Thread.UncaughtExceptionHandler
{
  private final IExceptionCallback a;
  
  public YellowPageEngine$a(IExceptionCallback paramIExceptionCallback)
  {
    a = paramIExceptionCallback;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    Log.e(YellowPageEngine.access$0(), "caught: " + paramThrowable);
    paramThrowable.printStackTrace();
    if (a != null) {
      a.onException(paramThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.YellowPageEngine.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */