package com.android.mms.ui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.mms.util.Reminder;
import com.android.mms.util.VibratorManager;

public class NewMessageNotificationDismissReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    NewMessagePopupActivity.dismiss(paramContext);
    Reminder.cancelReminder(paramContext);
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(123);
    VibratorManager.cancel();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageNotificationDismissReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */