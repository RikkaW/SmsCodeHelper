package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

final class MessagingNotification$1
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getIntExtra(MessagingNotification.access$100(), MessagingNotification.CANCEL_FLOAT_MSGID_DEFAULT) == MessagingNotification.access$200()))
    {
      MessagingNotification.cancelFloatNotification(paramContext);
      MessagingNotification.access$400().removeCallbacks(MessagingNotification.access$300());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */