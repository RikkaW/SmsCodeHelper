package com.android.mms.transaction;

import com.android.mms.LogTag;

class TransactionService$1
  implements Runnable
{
  TransactionService$1(TransactionService paramTransactionService) {}
  
  public void run()
  {
    TransactionService.access$002(this$0, false);
    LogTag.verbose("requestRetryConnect end", new Object[0]);
    TransactionService.access$100(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */