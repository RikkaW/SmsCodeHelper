package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MessagingNotification$OnNotifyOperationReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_MARKASREAD_ACTION") {
      MessagingNotification.b(paramContext, 0);
    }
    do
    {
      return;
      if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_MARKASREAD_ACTION_HEADS_UP")
      {
        MessagingNotification.b(paramContext, 1);
        return;
      }
      if (paramIntent.getAction() == "com.android.mms.NOTIFICATION_DELETED_MSG_ACTION")
      {
        MessagingNotification.c(paramContext, 0);
        return;
      }
    } while (paramIntent.getAction() != "com.android.mms.NOTIFICATION_DELETED_MSG_ACTION_HEADS_UP");
    MessagingNotification.c(paramContext, 1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessagingNotification.OnNotifyOperationReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */