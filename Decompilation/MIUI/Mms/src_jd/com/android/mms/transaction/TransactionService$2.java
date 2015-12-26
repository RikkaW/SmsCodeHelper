package com.android.mms.transaction;

import com.android.mms.LogTag;

class TransactionService$2
  implements Runnable
{
  TransactionService$2(TransactionService paramTransactionService) {}
  
  public void run()
  {
    TransactionService.access$002(this$0, false);
    LogTag.verbose("requestRetryConnectForFailed end", new Object[0]);
    if (TransactionService.access$200(this$0) < 3) {
      TransactionService.access$204(this$0);
    }
    for (;;)
    {
      TransactionService.access$100(this$0);
      return;
      TransactionService.access$400(this$0, TransactionService.access$300(this$0), false, false, true);
      TransactionService.access$202(this$0, 0);
      TransactionService.access$502(this$0, 0);
      TransactionService.access$602(this$0, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */