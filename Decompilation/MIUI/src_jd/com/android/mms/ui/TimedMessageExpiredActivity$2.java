package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class TimedMessageExpiredActivity$2
  extends BroadcastReceiver
{
  TimedMessageExpiredActivity$2(TimedMessageExpiredActivity paramTimedMessageExpiredActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("homekey".equals(paramIntent.getStringExtra("reason")))
    {
      TimedMessageExpiredActivity.access$200(this$0, false);
      this$0.finish();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.TimedMessageExpiredActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */