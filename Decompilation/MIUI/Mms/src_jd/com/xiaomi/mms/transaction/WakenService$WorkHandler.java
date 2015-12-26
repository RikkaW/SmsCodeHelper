package com.xiaomi.mms.transaction;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class WakenService$WorkHandler
  extends Handler
{
  public WakenService$WorkHandler(WakenService paramWakenService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    this$0.handleIntent((Intent)obj);
    WakenService.finishStartingService(this$0, arg1);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.WakenService.WorkHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */