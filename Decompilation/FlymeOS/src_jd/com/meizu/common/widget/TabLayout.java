package com.meizu.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.meizu.common.util.TabScroller;

public class TabLayout
  extends FrameLayout
{
  private TabScroller mTabScroller = new TabScroller(paramContext, this);
  
  public TabLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public TabLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    mTabScroller.dispatchDraw(paramCanvas);
  }
  
  public TabScroller getTabScroller()
  {
    return mTabScroller;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TabLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */