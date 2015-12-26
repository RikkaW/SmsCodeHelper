package com.xiaomi.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;

public class NotificationReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.xiaomi.mms.UPDATE_NOTIFICATION".equalsIgnoreCase(paramIntent.getAction()))
    {
      boolean bool = paramIntent.getBooleanExtra("extra_is_new", false);
      MessagingNotification.nonBlockingUpdateNewMessageIndicator(paramContext, bool, false);
      if (Log.isLoggable("Mms:app", 2)) {
        Log.d("NotificationReceiver", "get a action: com.xiaomi.mms.UPDATE_NOTIFICATION, isNew=" + bool);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.NotificationReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */