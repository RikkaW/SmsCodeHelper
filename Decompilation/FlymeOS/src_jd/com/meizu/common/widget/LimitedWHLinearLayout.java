package com.meizu.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;

public class LimitedWHLinearLayout
  extends LinearLayout
{
  private int mMaxHeight = Integer.MAX_VALUE;
  private int mMaxWidth = Integer.MAX_VALUE;
  
  public LimitedWHLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public LimitedWHLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public int getMaxHeight()
  {
    return mMaxHeight;
  }
  
  public int getMaxWidth()
  {
    return mMaxWidth;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = 1;
    super.onMeasure(paramInt1, paramInt2);
    int m = getMeasuredHeight();
    int k = getMeasuredWidth();
    int i = 0;
    if (m > mMaxHeight)
    {
      paramInt2 = View.MeasureSpec.makeMeasureSpec(mMaxHeight, 1073741824);
      i = 1;
    }
    if (k > mMaxWidth)
    {
      paramInt1 = View.MeasureSpec.makeMeasureSpec(mMaxWidth, 1073741824);
      i = j;
    }
    for (;;)
    {
      if (i != 0) {
        super.onMeasure(paramInt1, paramInt2);
      }
      return;
    }
  }
  
  public void setMaxHeight(int paramInt)
  {
    mMaxHeight = paramInt;
  }
  
  public void setMaxWidth(int paramInt)
  {
    mMaxWidth = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.LimitedWHLinearLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */