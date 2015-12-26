package com.android.mms.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimedUpdateReceiver
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.mms.update.UPDATE_TEMPLATE".equals(paramIntent.getAction()))
    {
      Log.v("TimedUpdateReceiver", "try update template() ");
      TemplateRequest.tryUpdateTemplate();
      UpdateManager.onUpdated();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.TimedUpdateReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */