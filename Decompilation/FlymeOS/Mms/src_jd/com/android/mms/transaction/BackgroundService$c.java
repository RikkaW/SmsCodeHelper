package com.android.mms.transaction;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class BackgroundService$c
  extends Handler
{
  public BackgroundService$c(BackgroundService paramBackgroundService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  private String a(Message paramMessage)
  {
    if (what == 2) {
      return "EVENT_LISTEN_SDCARD_STATUS";
    }
    if (what == 1) {
      return "EVENT_LISTEN_NEWWORK_STATUS";
    }
    if (what == 3) {
      return "EVENT_QUIT";
    }
    return "unknown message.what";
  }
  
  public void handleMessage(Message paramMessage)
  {
    Log.v("FlymeBackgroundService", "Handling incoming message :" + paramMessage + " = " + a(paramMessage));
    switch (what)
    {
    default: 
      Log.d("FlymeBackgroundService", "what= " + what);
      return;
    case 1: 
      BackgroundService.a(a);
      return;
    case 2: 
      BackgroundService.b(a);
      return;
    }
    getLooper().quit();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundService.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */