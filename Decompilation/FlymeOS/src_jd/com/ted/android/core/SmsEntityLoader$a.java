package com.ted.android.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class SmsEntityLoader$a
  extends Handler
{
  public SmsEntityLoader$a(SmsEntityLoader paramSmsEntityLoader, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    }
    ((SmsEntityLoader.d)obj).run();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */