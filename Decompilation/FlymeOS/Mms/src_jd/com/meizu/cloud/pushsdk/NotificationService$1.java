package com.meizu.cloud.pushsdk;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class NotificationService$1
  extends Handler
{
  NotificationService$1(NotificationService paramNotificationService, Looper paramLooper)
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
    Log.i("NotificationService", "stop notification service");
    this$0.stopSelf();
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.NotificationService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */