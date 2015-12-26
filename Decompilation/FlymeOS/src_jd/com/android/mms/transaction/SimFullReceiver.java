package com.android.mms.transaction;

import aac;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Global;
import android.util.Log;
import com.android.mms.ui.ManageSimMessages;
import zv;

public class SimFullReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((Settings.Global.getInt(paramContext.getContentResolver(), "device_provisioned", 0) == 1) && ("android.provider.Telephony.SIM_FULL".equals(paramIntent.getAction())))
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      Object localObject = new Intent(paramContext, ManageSimMessages.class);
      ((Intent)localObject).setAction("android.intent.action.VIEW");
      ((Intent)localObject).setFlags(268435456);
      Bundle localBundle = paramIntent.getExtras();
      long l = zv.a(paramIntent);
      int j = aac.a(l);
      int i = j;
      if (!zv.a(j))
      {
        Log.w("SimFullReceiver", "slot id translate error, sub_id = " + l + ", slot_id = " + j);
        i = localBundle.getInt("KEY_SLOT", 0);
      }
      ((Intent)localObject).putExtra("sim_id", i);
      paramIntent = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 134217728);
      localObject = new Notification();
      icon = 2130838679;
      tickerText = paramContext.getString(2131493118);
      defaults = -1;
      ((Notification)localObject).setLatestEventInfo(paramContext, paramContext.getString(2131493118), paramContext.getString(2131493117), paramIntent);
      localNotificationManager.notify(234, (Notification)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SimFullReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */