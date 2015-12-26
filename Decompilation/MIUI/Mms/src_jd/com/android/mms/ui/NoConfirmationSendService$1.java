package com.android.mms.ui;

class NoConfirmationSendService$1
  implements Runnable
{
  NoConfirmationSendService$1(NoConfirmationSendService paramNoConfirmationSendService, String paramString, int paramInt) {}
  
  public void run()
  {
    NoConfirmationSendService.startSendPendingMessage(val$address, false, val$slotId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NoConfirmationSendService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */