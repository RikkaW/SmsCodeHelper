package com.android.mms.transaction;

import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;

class TransactionService$3
  implements Runnable
{
  TransactionService$3(TransactionService paramTransactionService) {}
  
  public void run()
  {
    TransactionService.access$002(this$0, false);
    LogTag.verbose("requestRetryConnectForStarted end", new Object[0]);
    int i = 18;
    if (MSimUtils.isMSim()) {
      i = 20;
    }
    if (TransactionService.access$500(this$0) < i) {
      TransactionService.access$504(this$0);
    }
    for (;;)
    {
      TransactionService.access$100(this$0);
      return;
      TransactionService.access$700(this$0, TransactionService.access$600(this$0), false, false);
      TransactionService.access$202(this$0, 0);
      TransactionService.access$502(this$0, 0);
      TransactionService.access$602(this$0, null);
      TransactionService.access$800(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */