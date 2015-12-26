package com.meizu.common.widget;

import android.view.View;

class CustomTimePicker$TimeAdapter
  implements ScrollTextView.IDataAdapter
{
  static final int SET_AMPM = 3;
  static final int SET_DAY = 5;
  static final int SET_HOUR = 1;
  static final int SET_MIN = 2;
  int mType = 0;
  
  CustomTimePicker$TimeAdapter(CustomTimePicker paramCustomTimePicker, int paramInt)
  {
    mType = paramInt;
  }
  
  public String getItemText(int paramInt)
  {
    switch (mType)
    {
    case 4: 
    default: 
    case 1: 
    case 2: 
    case 3: 
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
          return CustomTimePicker.access$400(this$0);
        }
      } while (paramInt != 1);
      return CustomTimePicker.access$500(this$0);
    }
    if (CustomTimePicker.access$600(this$0)) {
      return CustomTimePicker.access$700(this$0, paramInt);
    }
    return String.valueOf(paramInt + 1);
  }
  
  public void onChanged(View paramView, int paramInt1, int paramInt2)
  {
    switch (mType)
    {
    case 4: 
    default: 
      return;
    case 1: 
      CustomTimePicker.access$002(this$0, paramInt2);
      return;
    case 2: 
      CustomTimePicker.access$102(this$0, paramInt2);
      return;
    case 3: 
      paramView = this$0;
      if (paramInt2 == 0) {}
      for (boolean bool = true;; bool = false)
      {
        CustomTimePicker.access$202(paramView, bool);
        return;
      }
    }
    CustomTimePicker.access$302(this$0, paramInt2 + 1);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomTimePicker.TimeAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */