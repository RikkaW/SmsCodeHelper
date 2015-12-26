package com.ted.android.core;

import com.ted.android.utils.TedSDKLog;

class SmsEntityLoader$b
  implements Thread.UncaughtExceptionHandler
{
  private SmsEntityLoader$b(SmsEntityLoader paramSmsEntityLoader) {}
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    TedSDKLog.d(SmsEntityLoader.access$7(), paramThrowable.getMessage());
    SmsEntityLoader.access$0(a, false);
    TedSDKLog.d(SmsEntityLoader.access$7(), "The thread is crashed. Will restart soon!");
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */