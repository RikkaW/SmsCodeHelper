package com.meizu.common.widget;

class CustomTimePicker$DayPicker
  implements ScrollTextView.OnScrollTextViewScrollListener
{
  private ScrollTextView picker;
  private int validEnd;
  private int validStart;
  
  public CustomTimePicker$DayPicker(CustomTimePicker paramCustomTimePicker, ScrollTextView paramScrollTextView)
  {
    picker = paramScrollTextView;
  }
  
  public int getCurrentItem()
  {
    return picker.getCurrentItem();
  }
  
  public int getValidEnd()
  {
    return validEnd;
  }
  
  public int getValidStart()
  {
    return validStart;
  }
  
  public void onScrollingFinished(ScrollTextView paramScrollTextView)
  {
    int i = Math.max(Math.min(picker.getCurrentItem(), getValidEnd()), getValidStart());
    picker.setCurrentItem(i, true);
  }
  
  public void onScrollingStarted(ScrollTextView paramScrollTextView) {}
  
  public void refreshCount(int paramInt)
  {
    picker.refreshCount(paramInt);
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    picker.setCurrentItem(paramInt, paramBoolean);
  }
  
  public void setData(CustomTimePicker.TimeAdapter paramTimeAdapter, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    setValidEnd(paramInt6);
    setValidStart(paramInt5);
    picker.setData(paramTimeAdapter, paramInt1, paramInt2, paramInt3, paramInt4, 0, paramInt3 - 1, paramBoolean);
    picker.addScrollingListener(this);
  }
  
  public void setSelectItemHeight(int paramInt)
  {
    picker.setSelectItemHeight(paramInt);
  }
  
  public void setTextColor(int paramInt1, int paramInt2)
  {
    picker.setTextColor(paramInt1, paramInt2);
  }
  
  public void setTextSize(int paramInt1, int paramInt2)
  {
    picker.setTextSize(paramInt1, paramInt2);
  }
  
  public void setValidEnd(int paramInt)
  {
    validEnd = paramInt;
  }
  
  public void setValidStart(int paramInt)
  {
    validStart = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomTimePicker.DayPicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */