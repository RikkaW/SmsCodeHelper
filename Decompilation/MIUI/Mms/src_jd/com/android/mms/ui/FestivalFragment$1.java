package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.android.mms.LogTag;

class FestivalFragment$1
  extends BroadcastReceiver
{
  FestivalFragment$1(FestivalFragment paramFestivalFragment) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra("success", false);
    LogTag.verbose("Received broadcast of finishing update. Success=%s", new Object[] { Boolean.valueOf(bool) });
    FestivalFragment.access$102(this$0, true);
    if ((this$0.getUserVisibleHint()) && (FestivalFragment.access$200(this$0)))
    {
      LogTag.verbose("Activity is visible. Presenting result.", new Object[0]);
      if (bool) {
        break label95;
      }
      Toast.makeText(FestivalFragment.access$300(this$0), 2131362060, 0).show();
    }
    for (;;)
    {
      FestivalFragment.access$500(this$0).setVisibility(8);
      return;
      label95:
      FestivalFragment.access$400(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */