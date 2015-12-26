package com.meizu.common.widget;

import android.view.View;

class TimePicker$TimeAdapter
  implements ScrollTextView.IDataAdapter
{
  static final int SET_AMPM = 3;
  static final int SET_HOUR = 1;
  static final int SET_MIN = 2;
  int mType = 0;
  
  TimePicker$TimeAdapter(TimePicker paramTimePicker, int paramInt)
  {
    mType = paramInt;
  }
  
  public String getItemText(int paramInt)
  {
    switch (mType)
    {
    }
    do
    {
      return null;
      if (this$0.is24HourView()) {
        return String.valueOf(paramInt);
      }
      int i = paramInt;
      if (paramInt == 0) {
        i = 12;
      }
      return String.valueOf(i);
      return String.valueOf(paramInt);
      if (paramInt == 0) {
        return TimePicker.access$500(this$0);
      }
    } while (paramInt != 1);
    return TimePicker.access$600(this$0);
  }
  
  public void onChanged(View paramView, int paramInt1, int paramInt2)
  {
    switch (mType)
    {
    default: 
      return;
    case 1: 
      TimePicker.access$002(this$0, paramInt2);
    case 2: 
      for (;;)
      {
        if (TimePicker.access$300(this$0) != null) {
          TimePicker.access$300(this$0).onTimeChanged(this$0, this$0.getCurrentHour(), this$0.getCurrentMinute().intValue());
        }
        TimePicker.access$400(this$0);
        return;
        TimePicker.access$102(this$0, paramInt2);
      }
    }
    paramView = this$0;
    if (paramInt2 == 0) {}
    for (boolean bool = true;; bool = false)
    {
      TimePicker.access$202(paramView, bool);
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TimePicker.TimeAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */