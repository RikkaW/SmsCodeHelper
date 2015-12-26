package com.android.mms.transaction;

import com.android.mms.LogTag;

class TransactionService$4
  implements Runnable
{
  TransactionService$4(TransactionService paramTransactionService, int paramInt1, int paramInt2) {}
  
  public void run()
  {
    LogTag.verbose("Posting enqueueAllMessages", new Object[0]);
    TransactionService.access$902(this$0, val$startId);
    TransactionService.access$1000(this$0, val$slotId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */