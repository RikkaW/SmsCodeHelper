package com.meizu.common.widget;

class ScrollTextView$ScrollTextViewAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = 0;
  private int count = 0;
  private int validEnd = 0;
  private int validStart = 0;
  
  public ScrollTextView$ScrollTextViewAdapter(ScrollTextView paramScrollTextView)
  {
    this(paramScrollTextView, 10, 0, 9);
  }
  
  public ScrollTextView$ScrollTextViewAdapter(ScrollTextView paramScrollTextView, int paramInt1, int paramInt2)
  {
    this(paramScrollTextView, paramInt2 - paramInt1 + 1, paramInt1, paramInt2);
  }
  
  public ScrollTextView$ScrollTextViewAdapter(ScrollTextView paramScrollTextView, int paramInt1, int paramInt2, int paramInt3)
  {
    update(paramInt1, paramInt2, paramInt3);
  }
  
  public String getItemText(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getItemsCount()) && (ScrollTextView.access$500(this$0) != null)) {
      return ScrollTextView.access$500(this$0).getItemText(paramInt);
    }
    return null;
  }
  
  public int getItemsCount()
  {
    return count;
  }
  
  public int getValidEnd()
  {
    return validEnd;
  }
  
  public int getValidStart()
  {
    return validStart;
  }
  
  public void setItemCount(int paramInt)
  {
    count = paramInt;
  }
  
  public ScrollTextViewAdapter update(int paramInt1, int paramInt2, int paramInt3)
  {
    validStart = paramInt2;
    validEnd = paramInt3;
    count = paramInt1;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.ScrollTextViewAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */