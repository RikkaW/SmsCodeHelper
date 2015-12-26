package com.android.mms.transaction;

import android.widget.Toast;

class TransactionService$6
  implements Runnable
{
  TransactionService$6(TransactionService paramTransactionService, boolean paramBoolean, int paramInt) {}
  
  public void run()
  {
    if (val$isStroageUnavailable)
    {
      Toast.makeText(this$0, 2131362380, 1).show();
      return;
    }
    switch (val$transactionType)
    {
    default: 
      return;
    case 1: 
      Toast.makeText(this$0, 2131362010, 1).show();
      return;
    }
    Toast.makeText(this$0, 2131362000, 1).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */