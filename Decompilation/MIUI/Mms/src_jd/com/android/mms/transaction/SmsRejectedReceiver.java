package com.android.mms.transaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import com.android.mms.ui.MmsTabActivity;
import com.android.mms.util.DownloadManager;
import miui.os.Build;

public class SmsRejectedReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((Settings.Secure.getInt(paramContext.getContentResolver(), "device_provisioned", 0) == 1) && ("android.provider.Telephony.SMS_REJECTED".equals(paramIntent.getAction()))) {
      if (paramIntent.getIntExtra("result", -1) != 3) {
        break label44;
      }
    }
    label44:
    for (int i = 1; i == 0; i = 0) {
      return;
    }
    if (Build.IS_CM_CUSTOMIZATION_TEST) {
      DownloadManager.getInstance().setOutOfMemory(true);
    }
    paramIntent = (NotificationManager)paramContext.getSystemService("notification");
    Object localObject = new Intent(paramContext, MmsTabActivity.class);
    ((Intent)localObject).setAction("android.intent.action.VIEW");
    ((Intent)localObject).setFlags(872415232);
    localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 0);
    Notification localNotification = new Notification();
    icon = 2130838005;
    int j;
    if (i != 0) {
      j = 2131361990;
    }
    for (i = 2131361991;; i = 2131361993)
    {
      tickerText = paramContext.getString(j);
      defaults = -1;
      localNotification.setLatestEventInfo(paramContext, paramContext.getString(j), paramContext.getString(i), (PendingIntent)localObject);
      paramIntent.notify(239, localNotification);
      return;
      j = 2131361992;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsRejectedReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */