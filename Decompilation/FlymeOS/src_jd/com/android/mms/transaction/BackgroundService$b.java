package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class BackgroundService$b
  extends BroadcastReceiver
{
  private BackgroundService$b(BackgroundService paramBackgroundService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.d("FlymeBackgroundService", "SDCardMountStatusMonitorReceiver -> intent : " + paramIntent);
    if (paramIntent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
      BackgroundService.a(a, paramContext);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundService.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */