package com.android.mms.transaction;

import android.util.Log;
import com.android.mms.util.MSimUtils;

class SmsReceiverService$1
  implements Runnable
{
  SmsReceiverService$1(SmsReceiverService paramSmsReceiverService) {}
  
  public void run()
  {
    Log.d("SmsReceiverService", "send queued message without toast");
    MSimUtils.sendQueuedMessageNoToast(this$0, 0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */