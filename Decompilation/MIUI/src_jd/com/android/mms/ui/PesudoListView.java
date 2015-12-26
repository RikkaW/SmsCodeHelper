package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class PesudoListView
  extends ViewGroup
{
  public PesudoListView(Context paramContext)
  {
    super(paramContext);
  }
  
  public PesudoListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public PesudoListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt4 = 0;
    while (paramInt4 < getChildCount())
    {
      View localView = getChildAt(paramInt4);
      int i = 0;
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      if ((localLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
        i = topMargin;
      }
      localView.layout(paramInt1, paramInt2 + i, paramInt3, paramInt2 + i + localView.getMeasuredHeight());
      paramInt4 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    measureChildren(paramInt1, 0);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PesudoListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */