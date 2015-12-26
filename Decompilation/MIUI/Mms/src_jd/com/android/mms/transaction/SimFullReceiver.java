package com.android.mms.transaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.util.MSimUtils;

public class SimFullReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((Settings.Secure.getInt(paramContext.getContentResolver(), "device_provisioned", 0) == 1) && ("android.provider.Telephony.SIM_FULL".equals(paramIntent.getAction())))
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      int i = MSimUtils.getSlotIdFromIntent(paramIntent);
      paramIntent = new Intent(paramContext, ManageSimMessages.class);
      paramIntent.setAction("android.intent.action.VIEW");
      paramIntent.setFlags(268435456);
      paramIntent.putExtra(MSimUtils.SLOT_ID, i);
      paramIntent = PendingIntent.getActivity(paramContext, 0, paramIntent, 0);
      Notification localNotification = new Notification();
      icon = 2130838005;
      tickerText = paramContext.getString(2131361800);
      defaults = -1;
      localNotification.setLatestEventInfo(paramContext, paramContext.getString(2131361800), paramContext.getString(2131361989), paramIntent);
      localNotificationManager.notify(234, localNotification);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SimFullReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */