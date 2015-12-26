package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class FloatPanelView
  extends LinearLayout
{
  private int mPos;
  
  public FloatPanelView(Context paramContext)
  {
    super(paramContext);
  }
  
  public FloatPanelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    View localView = findViewById(2131820553);
    if (localView == null)
    {
      Log.e("FloatPanelView", "counldn't find view");
      return;
    }
    paramInt2 = mPos - localView.getMeasuredWidth() / 2;
    if (paramInt2 < 0) {
      paramInt1 = 0;
    }
    for (;;)
    {
      localView.layout(paramInt1, localView.getTop(), localView.getMeasuredWidth() + paramInt1, localView.getBottom());
      return;
      paramInt1 = paramInt2;
      if (paramInt2 > paramInt3 - localView.getMeasuredWidth()) {
        paramInt1 = paramInt3 - localView.getMeasuredWidth();
      }
    }
  }
  
  public void setArrowPos(int paramInt)
  {
    if (paramInt != mPos)
    {
      mPos = paramInt;
      requestLayout();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FloatPanelView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */