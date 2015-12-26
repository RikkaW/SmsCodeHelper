package com.android.mms.fragmentstyle;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomeViewPager
  extends ViewPager
{
  private boolean a = true;
  
  public CustomeViewPager(Context paramContext)
  {
    super(paramContext);
  }
  
  public CustomeViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!a) {
      return false;
    }
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
  
  public void setScrollEnable(boolean paramBoolean)
  {
    a = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.CustomeViewPager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */