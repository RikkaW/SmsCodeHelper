package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class BackgroundService$a
  extends BroadcastReceiver
{
  private BackgroundService$a(BackgroundService paramBackgroundService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.d("FlymeBackgroundService", "NetworkStatusMonitorReceiver -> intent : " + paramIntent);
    if (paramIntent.getAction().equals("android.intent.action.ANY_DATA_STATE"))
    {
      paramIntent = paramIntent.getStringExtra("state");
      Log.d("FlymeBackgroundService", "NetworkInfo : " + paramIntent);
      if (paramIntent.equals("CONNECTED")) {
        BackgroundService.a(a, paramContext);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundService.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */