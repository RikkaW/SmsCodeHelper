package com.android.mms.ui;

import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import com.android.mms.MmsApp;

public class SmsStorageMonitor
  extends BroadcastReceiver
{
  private static NotificationManager c;
  private Context a;
  private final int b = -1;
  
  private void a()
  {
    Notification.Builder localBuilder = new Notification.Builder(a).setContentTitle(a.getString(2131493206)).setContentText(a.getString(2131493205)).setOngoing(true);
    Object localObject = MmsApp.a(a, 2130903040);
    if ((localObject instanceof BitmapDrawable)) {}
    for (localObject = (BitmapDrawable)localObject;; localObject = (BitmapDrawable)a.getResources().getDrawable(2130903040))
    {
      localBuilder.setLargeIcon(((BitmapDrawable)localObject).getBitmap());
      localBuilder.setSmallIcon(2130838482);
      c.notify(-1, localBuilder.build());
      a(a, false);
      return;
    }
  }
  
  private void a(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putBoolean("pref_key_storage_available", paramBoolean);
    paramContext.apply();
  }
  
  private void b()
  {
    c.cancel(-1);
    a(a, true);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a = paramContext;
    if (c == null) {
      c = (NotificationManager)a.getSystemService("notification");
    }
    if (paramIntent.getAction().equals("android.intent.action.DEVICE_STORAGE_FULL")) {
      a();
    }
    while (!paramIntent.getAction().equals("android.intent.action.DEVICE_STORAGE_NOT_FULL")) {
      return;
    }
    b();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SmsStorageMonitor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */