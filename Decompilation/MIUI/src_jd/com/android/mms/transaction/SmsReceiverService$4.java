package com.android.mms.transaction;

class SmsReceiverService$4
  implements Runnable
{
  SmsReceiverService$4(SmsReceiverService paramSmsReceiverService, int paramInt) {}
  
  public void run()
  {
    MmsSystemEventReceiver.getInstance().registerForServiceStateChanged(val$slotId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsReceiverService.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */