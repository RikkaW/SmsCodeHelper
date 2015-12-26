package com.ted.android.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import aqh;
import com.ted.android.utils.TedSDKLog;

class SmsEntityLoader$c
  extends Handler
{
  public SmsEntityLoader$c(SmsEntityLoader paramSmsEntityLoader, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void a()
  {
    removeMessages(1);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    }
    do
    {
      return;
      SmsEntityLoader localSmsEntityLoader = a;
      SmsEntityLoader.access$6(localSmsEntityLoader, SmsEntityLoader.access$5(localSmsEntityLoader) + 1);
      TedSDKLog.d(SmsEntityLoader.access$7(), "====== LoaderUiHandler handleMessage start =====");
    } while (SmsEntityLoader.access$1(a));
    paramMessage = (SmsEntityLoader.d)obj;
    if (paramMessage == null)
    {
      TedSDKLog.d(SmsEntityLoader.access$7(), "LoaderUiHandler handleMessage fetcher is null");
      return;
    }
    SmsEntityLoader.access$8(a).a(amsgId, SmsEntityLoader.d.b(paramMessage));
    if (acallback != null) {
      acallback.onSmsEntityLoaded(amsgId, SmsEntityLoader.d.b(paramMessage));
    }
    for (;;)
    {
      SmsEntityLoader.access$9(a, -1L);
      TedSDKLog.d(SmsEntityLoader.access$7(), "====== LoaderUiHandler handleMessage end =====" + amsgId);
      SmsEntityLoader.access$10(a);
      return;
      TedSDKLog.d(SmsEntityLoader.access$7(), "LoaderUiHandler handleMessage callback is null!");
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */