package com.meizu.common.widget;

import android.view.View;

class CustomPicker$DataAdapter
  implements ScrollTextView.IDataAdapter
{
  private int mColumnIndex = 0;
  private int mStartValue;
  
  CustomPicker$DataAdapter(CustomPicker paramCustomPicker, int paramInt1, int paramInt2)
  {
    mColumnIndex = paramInt1;
    mStartValue = paramInt2;
  }
  
  public String getItemText(int paramInt)
  {
    return String.valueOf(mStartValue + paramInt);
  }
  
  public void onChanged(View paramView, int paramInt1, int paramInt2)
  {
    CustomPicker.access$000(this$0)[mColumnIndex] = paramInt2;
    if (CustomPicker.access$100(this$0) != null) {
      CustomPicker.access$100(this$0).onCurrentItemChanged(this$0, CustomPicker.access$000(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomPicker.DataAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */