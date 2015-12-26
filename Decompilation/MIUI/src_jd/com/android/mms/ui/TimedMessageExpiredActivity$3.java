package com.android.mms.ui;

import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;

class TimedMessageExpiredActivity$3
  implements AdapterView.OnItemClickListener
{
  TimedMessageExpiredActivity$3(TimedMessageExpiredActivity paramTimedMessageExpiredActivity) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((TimedMessageExpiredActivity.access$000(this$0).getCachedMessageItem(paramInt) == null) || (paramView == null)) {
      return;
    }
    paramAdapterView = (CheckBox)paramView.findViewById(2131820631);
    if (TimedMessageExpiredActivity.access$300(this$0).isItemChecked(paramInt)) {
      paramAdapterView.setChecked(true);
    }
    for (;;)
    {
      TimedMessageExpiredActivity.access$000(this$0).setListViewCheckStates(TimedMessageExpiredActivity.access$300(this$0).getCheckedItemPositions());
      paramInt = TimedMessageExpiredActivity.access$300(this$0).getCheckedItemCount();
      paramAdapterView = this$0.getResources().getString(2131362071);
      if (paramInt != 0) {
        break;
      }
      TimedMessageExpiredActivity.access$400(this$0).setText(paramAdapterView);
      TimedMessageExpiredActivity.access$400(this$0).setEnabled(false);
      return;
      paramAdapterView.setChecked(false);
    }
    TimedMessageExpiredActivity.access$400(this$0).setText(paramAdapterView + "(" + paramInt + ")");
    TimedMessageExpiredActivity.access$400(this$0).setEnabled(true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.TimedMessageExpiredActivity.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */