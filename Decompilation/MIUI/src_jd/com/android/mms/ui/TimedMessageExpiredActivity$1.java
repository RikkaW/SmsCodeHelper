package com.android.mms.ui;

import android.database.DataSetObserver;
import android.widget.TextView;

class TimedMessageExpiredActivity$1
  extends DataSetObserver
{
  TimedMessageExpiredActivity$1(TimedMessageExpiredActivity paramTimedMessageExpiredActivity) {}
  
  public void onChanged()
  {
    if (TimedMessageExpiredActivity.access$000(this$0).getCount() == 0)
    {
      this$0.finish();
      return;
    }
    TimedMessageExpiredActivity.access$100(this$0).setText(this$0.getString(2131362157, new Object[] { Integer.valueOf(TimedMessageExpiredActivity.access$000(this$0).getCount()) }));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.TimedMessageExpiredActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */