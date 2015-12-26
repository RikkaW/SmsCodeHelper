package com.meizu.common.widget;

import android.view.View;

class DatePicker$DateAdapter
  implements ScrollTextView.IDataAdapter
{
  static final int SET_DAY = 3;
  static final int SET_MONTH = 2;
  static final int SET_YEAR = 1;
  int mType = 0;
  
  DatePicker$DateAdapter(DatePicker paramDatePicker, int paramInt)
  {
    mType = paramInt;
  }
  
  public String getItemText(int paramInt)
  {
    switch (mType)
    {
    default: 
    case 1: 
    case 2: 
      do
      {
        return null;
        return String.valueOf(DatePicker.access$000(this$0) + paramInt);
        if (DatePicker.access$100(this$0)) {
          return DatePicker.access$200(this$0, paramInt);
        }
      } while (paramInt >= DatePicker.access$300(this$0).length);
      return DatePicker.access$300(this$0)[paramInt];
    }
    if (DatePicker.access$100(this$0)) {
      return DatePicker.access$400(this$0, paramInt);
    }
    return String.valueOf(paramInt + 1);
  }
  
  public void onChanged(View paramView, int paramInt1, int paramInt2)
  {
    paramInt1 = DatePicker.access$500(this$0);
    int i = DatePicker.access$600(this$0);
    switch (mType)
    {
    default: 
      return;
    case 1: 
      DatePicker.access$702(this$0, DatePicker.access$000(this$0) + paramInt2);
      if ((i != DatePicker.access$600(this$0)) && (DatePicker.access$800(this$0) != null))
      {
        paramInt2 = DatePicker.access$600(this$0);
        DatePicker.access$800(this$0).refreshCount(paramInt2);
        if (paramInt2 - 1 < DatePicker.access$900(this$0))
        {
          DatePicker.access$1002(this$0, paramInt1);
          DatePicker.access$902(this$0, paramInt2 - 1);
          DatePicker.access$800(this$0).setCurrentItem(DatePicker.access$900(this$0), true);
        }
      }
      if ((paramInt1 != DatePicker.access$500(this$0)) && (DatePicker.access$1100(this$0) != null))
      {
        paramInt1 = DatePicker.access$500(this$0);
        DatePicker.access$1100(this$0).refreshCount(paramInt1);
        if (paramInt1 < DatePicker.access$1000(this$0))
        {
          DatePicker.access$1002(this$0, paramInt1);
          DatePicker.access$1100(this$0).setCurrentItem(DatePicker.access$1000(this$0) - 1, true);
        }
      }
      break;
    }
    for (;;)
    {
      if (DatePicker.access$1200(this$0) != null) {
        DatePicker.access$1200(this$0).onDateChanged(this$0, DatePicker.access$700(this$0), DatePicker.access$900(this$0), DatePicker.access$1000(this$0));
      }
      DatePicker.access$1300(this$0);
      return;
      DatePicker.access$902(this$0, paramInt2);
      if ((paramInt1 != DatePicker.access$500(this$0)) && (DatePicker.access$1100(this$0) != null))
      {
        paramInt1 = DatePicker.access$500(this$0);
        DatePicker.access$1100(this$0).refreshCount(paramInt1);
        if (paramInt1 < DatePicker.access$1000(this$0))
        {
          DatePicker.access$1002(this$0, paramInt1);
          DatePicker.access$1100(this$0).setCurrentItem(DatePicker.access$1000(this$0) - 1, true);
          continue;
          DatePicker.access$1002(this$0, paramInt2 + 1);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.DatePicker.DateAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */