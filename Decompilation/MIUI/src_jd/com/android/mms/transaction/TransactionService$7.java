package com.android.mms.transaction;

import android.content.Intent;
import com.android.mms.LogTag;
import com.android.mms.util.RateController;
import java.util.LinkedHashSet;

class TransactionService$7
  implements Runnable
{
  TransactionService$7(TransactionService paramTransactionService, Transaction paramTransaction) {}
  
  public void run()
  {
    LogTag.verbose("Update transaction %s", new Object[] { val$transaction });
    if (val$transaction != TransactionService.access$1200(this$0)) {
      LogTag.error("Expecting %s to update but it is actually %s", new Object[] { TransactionService.access$1200(this$0), val$transaction });
    }
    Intent localIntent = new Intent("android.intent.action.TRANSACTION_COMPLETED_ACTION");
    TransactionState localTransactionState = val$transaction.getState();
    int i = localTransactionState.getState();
    localIntent.putExtra("state", i);
    localIntent.putExtra("uri", localTransactionState.getContentUri());
    switch (i)
    {
    default: 
      LogTag.verbose("Transaction state unknown: %s. result=%d", new Object[] { val$transaction, Integer.valueOf(i) });
    }
    for (;;)
    {
      val$transaction.detach(this$0);
      TransactionService.access$1202(this$0, null);
      this$0.sendBroadcast(localIntent);
      TransactionService.access$100(this$0);
      return;
      LogTag.verbose("Transaction complete: %s", new Object[] { val$transaction });
      switch (val$transaction.getType())
      {
      }
      for (;;)
      {
        TransactionService.access$1300(this$0).remove(val$transaction);
        TransactionService.access$1404(this$0);
        break;
        MessagingNotification.blockingUpdateNewMessageIndicator(this$0, true, false);
        MessagingNotification.updateDownloadFailedNotification(this$0);
        continue;
        RateController.getInstance().update();
      }
      LogTag.verbose("Transaction failed: %s", new Object[] { val$transaction });
      TransactionService.access$1500(this$0, localTransactionState.getContentUri(), val$transaction.mSimId, false, false, true);
      TransactionService.access$1300(this$0).remove(val$transaction);
      TransactionService.access$1604(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */