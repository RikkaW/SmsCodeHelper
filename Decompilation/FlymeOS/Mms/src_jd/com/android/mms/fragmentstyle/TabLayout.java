package com.android.mms.fragmentstyle;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.meizu.common.util.TabScroller;

public class TabLayout
  extends LinearLayout
{
  private TabScroller a = new TabScroller(paramContext, this);
  
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
    a.dispatchDraw(paramCanvas);
  }
  
  public TabScroller getTabScroller()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.TabLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */