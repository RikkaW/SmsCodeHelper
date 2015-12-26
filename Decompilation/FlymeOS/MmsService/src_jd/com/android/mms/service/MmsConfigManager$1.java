package com.android.mms.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class MmsConfigManager$1
  extends BroadcastReceiver
{
  MmsConfigManager$1(MmsConfigManager paramMmsConfigManager) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    Log.i("MmsService", "mReceiver action: " + paramContext);
    if (paramContext.equals("LOADED")) {
      MmsConfigManager.access$000(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsConfigManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */