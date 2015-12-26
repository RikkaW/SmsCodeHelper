package com.android.mms.ui;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

class DateTimePickerActivity$1
  implements RadioGroup.OnCheckedChangeListener
{
  DateTimePickerActivity$1(DateTimePickerActivity paramDateTimePickerActivity) {}
  
  public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
  {
    if (paramRadioGroup.getCheckedRadioButtonId() == 2131820610)
    {
      DateTimePickerActivity.access$002(this$0, true);
      DateTimePickerActivity.access$100(this$0, DateTimePickerActivity.access$000(this$0));
      return;
    }
    DateTimePickerActivity.access$002(this$0, false);
    DateTimePickerActivity.access$100(this$0, DateTimePickerActivity.access$000(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DateTimePickerActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */