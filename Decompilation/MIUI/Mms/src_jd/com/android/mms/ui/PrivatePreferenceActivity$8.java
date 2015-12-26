package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class PrivatePreferenceActivity$8
  extends BroadcastReceiver
{
  PrivatePreferenceActivity$8(PrivatePreferenceActivity paramPrivatePreferenceActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramContext)) {
      if ("homekey".equals(paramIntent.getStringExtra("reason"))) {
        this$0.finish();
      }
    }
    while (!"android.intent.action.SCREEN_OFF".equals(paramContext)) {
      return;
    }
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */