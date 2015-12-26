package com.meizu.common.widget;

class ScrollTextView$VisibleItemsRange
{
  private int count;
  private int first;
  
  public ScrollTextView$VisibleItemsRange(ScrollTextView paramScrollTextView)
  {
    this(paramScrollTextView, 0, 0);
  }
  
  public ScrollTextView$VisibleItemsRange(ScrollTextView paramScrollTextView, int paramInt1, int paramInt2)
  {
    first = paramInt1;
    count = paramInt2;
  }
  
  public int getCount()
  {
    return count;
  }
  
  public int getFirst()
  {
    return first;
  }
  
  public int getLast()
  {
    return getFirst() + getCount() - 1;
  }
  
  public VisibleItemsRange update(int paramInt1, int paramInt2)
  {
    first = paramInt1;
    count = paramInt2;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ScrollTextView.VisibleItemsRange
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */