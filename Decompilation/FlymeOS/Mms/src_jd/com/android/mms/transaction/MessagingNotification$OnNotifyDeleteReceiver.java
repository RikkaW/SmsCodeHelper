package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessagingNotification$OnNotifyDeleteReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_DELETED_ACTION") {
      MessagingNotification.h(paramContext);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.OnNotifyDeleteReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */