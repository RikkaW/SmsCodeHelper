package com.android.mms.transaction;

import android.telephony.PhoneStateListener;

class TransactionService$10
  extends PhoneStateListener
{
  TransactionService$10(TransactionService paramTransactionService) {}
  
  public void onCallStateChanged(int paramInt, String arg2)
  {
    synchronized (TransactionService.access$2000(this$0))
    {
      TransactionService.access$2100(this$0);
      TransactionService.access$2300(this$0, TransactionService.access$2200(this$0));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */