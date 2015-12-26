package com.android.mms.transaction;

import com.android.mms.LogTag;

class TransactionService$9
  implements Runnable
{
  TransactionService$9(TransactionService paramTransactionService) {}
  
  public void run()
  {
    if ((TransactionService.access$1700(this$0) == 0) && (TransactionService.access$1800(this$0) == 0))
    {
      LogTag.verbose("get slot1 new mReConnectWhenCallIdle : " + TransactionService.access$1900(this$0), new Object[0]);
      if (TransactionService.access$1900(this$0))
      {
        TransactionService.access$1902(this$0, false);
        TransactionService.access$100(this$0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */