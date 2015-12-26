package com.xiaomi.push.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class ConnectionJobController$1
  extends Handler
{
  ConnectionJobController$1(ConnectionJobController paramConnectionJobController, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    ConnectionJobController.access$002(this$0, true);
    ConnectionJobController.access$102(this$0, System.currentTimeMillis());
    if ((obj instanceof XMPushService.Job)) {
      ((XMPushService.Job)obj).run();
    }
    ConnectionJobController.access$002(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ConnectionJobController.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */