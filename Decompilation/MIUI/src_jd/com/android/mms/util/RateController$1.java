package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class RateController$1
  extends BroadcastReceiver
{
  RateController$1(RateController paramRateController) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.mms.RATE_LIMIT_CONFIRMED".equals(paramIntent.getAction())) {}
    for (;;)
    {
      try
      {
        paramContext = this$0;
        if (!paramIntent.getBooleanExtra("answer", false)) {
          break label50;
        }
        i = 1;
        RateController.access$002(paramContext, i);
        notifyAll();
        return;
      }
      finally {}
      return;
      label50:
      int i = 2;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.RateController.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */