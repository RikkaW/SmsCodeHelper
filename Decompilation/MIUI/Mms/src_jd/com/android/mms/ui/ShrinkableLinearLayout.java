package com.android.mms.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.android.mms.R.styleable;

public class ShrinkableLinearLayout
  extends LinearLayout
{
  private int mShrinkableChildIndex = -1;
  
  public ShrinkableLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public ShrinkableLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ShrinkableLinearLayout);
    mShrinkableChildIndex = paramContext.getInt(0, -1);
    paramContext.recycle();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (getOrientation() == 1)
    {
      shrinkIfNecessaryV(paramInt1, paramInt2);
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    shrinkIfNecessaryH(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }
  
  void shrinkIfNecessaryH(int paramInt1, int paramInt2)
  {
    if ((mShrinkableChildIndex == -1) || (getOrientation() == 1)) {}
    View localView;
    int i;
    do
    {
      do
      {
        return;
      } while (View.MeasureSpec.getMode(paramInt1) == 0);
      localView = getChildAt(mShrinkableChildIndex);
      getLayoutParamswidth = -2;
      super.onMeasure(0, paramInt2);
      paramInt2 = localView.getMeasuredWidth();
      i = getMeasuredWidth();
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
    } while (paramInt1 >= i);
    paramInt2 -= i - paramInt1;
    paramInt1 = paramInt2;
    if (paramInt2 < 0) {
      paramInt1 = 0;
    }
    getLayoutParamswidth = paramInt1;
  }
  
  void shrinkIfNecessaryV(int paramInt1, int paramInt2)
  {
    if ((mShrinkableChildIndex == -1) || (getOrientation() == 0)) {}
    View localView;
    int i;
    do
    {
      do
      {
        return;
      } while (View.MeasureSpec.getMode(paramInt2) == 0);
      localView = getChildAt(mShrinkableChildIndex);
      getLayoutParamsheight = -2;
      super.onMeasure(paramInt1, 0);
      paramInt1 = localView.getMeasuredHeight();
      i = getMeasuredHeight();
      paramInt2 = View.MeasureSpec.getSize(paramInt2);
    } while (paramInt2 >= i);
    paramInt2 = paramInt1 - (i - paramInt2);
    paramInt1 = paramInt2;
    if (paramInt2 < 0) {
      paramInt1 = 0;
    }
    getLayoutParamsheight = paramInt1;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ShrinkableLinearLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */