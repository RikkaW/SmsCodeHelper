package com.meizu.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class AutoScrollListView
  extends ListView
{
  private static final float PREFERRED_SELECTION_OFFSET_FROM_TOP = 0.33F;
  private int mRequestedScrollPosition = -1;
  private boolean mSmoothScrollRequested;
  
  public AutoScrollListView(Context paramContext)
  {
    super(paramContext);
  }
  
  public AutoScrollListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public AutoScrollListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void layoutChildren()
  {
    super.layoutChildren();
    if (mRequestedScrollPosition == -1) {}
    int k;
    int n;
    int i1;
    do
    {
      return;
      k = mRequestedScrollPosition;
      mRequestedScrollPosition = -1;
      n = getFirstVisiblePosition() + 1;
      i1 = getLastVisiblePosition();
    } while ((k >= n) && (k <= i1));
    int m = (int)(getHeight() * 0.33F);
    if (!mSmoothScrollRequested)
    {
      setSelectionFromTop(k, m);
      super.layoutChildren();
      return;
    }
    int i = (i1 - n) * 2;
    int j;
    if (k < n)
    {
      j = i + k;
      i = j;
      if (j >= getCount()) {
        i = getCount() - 1;
      }
      if (i < n)
      {
        setSelection(i);
        super.layoutChildren();
      }
    }
    for (;;)
    {
      smoothScrollToPositionFromTop(k, m);
      return;
      j = k - i;
      i = j;
      if (j < 0) {
        i = 0;
      }
      if (i > i1)
      {
        setSelection(i);
        super.layoutChildren();
      }
    }
  }
  
  public void requestPositionToScreen(int paramInt, boolean paramBoolean)
  {
    mRequestedScrollPosition = paramInt;
    mSmoothScrollRequested = paramBoolean;
    requestLayout();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AutoScrollListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */