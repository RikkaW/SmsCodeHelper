package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MmsPushOutboxMessages
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("MmsPushOutboxMessages", "Received the MMS_SEND_OUTBOX_MSG intent: " + paramIntent);
    }
    if (paramIntent.getAction().equalsIgnoreCase("android.intent.action.MMS_SEND_OUTBOX_MSG"))
    {
      Log.d("MmsPushOutboxMessages", "Now waking up the MMS service");
      paramContext.startService(new Intent(paramContext, TransactionService.class));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsPushOutboxMessages
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */