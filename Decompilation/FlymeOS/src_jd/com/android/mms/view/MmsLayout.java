package com.android.mms.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import gb.a;

public class MmsLayout
  extends LinearLayout
{
  protected int a = 640;
  protected int b = 0;
  
  public MmsLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public MmsLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public MmsLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, gb.a.MmsLayout, paramInt, 0);
    a = paramContext.getDimensionPixelSize(0, 0);
    b = paramContext.getResourceId(1, 0);
    paramContext.recycle();
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    inflate(getContext(), b, this);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getMeasuredWidth() > a) {
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(a, 1073741824), paramInt2);
    }
  }
  
  public void setMaxWidth(int paramInt)
  {
    if (paramInt == a) {
      return;
    }
    a = paramInt;
    requestLayout();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MmsLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */