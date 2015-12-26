package com.android.mms.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.mms.R.styleable;
import java.util.ArrayList;

public class RowLayout
  extends ViewGroup
{
  private int mHorizontalSpacing = 0;
  protected ArrayList<RowInfo> mRows = new ArrayList();
  private int mVerticalSpacing = 0;
  
  public RowLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public RowLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAttributes(paramAttributeSet);
  }
  
  public RowLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAttributes(paramAttributeSet);
  }
  
  private void initAttributes(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.RowLayout);
    setVerticalSpacing(paramAttributeSet.getDimensionPixelSize(0, 0));
    setHorizontalSpacing(paramAttributeSet.getDimensionPixelSize(1, 0));
    paramAttributeSet.recycle();
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  public int getRowCount()
  {
    return mRows.size();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    paramInt4 = paramInt2 + getPaddingTop();
    paramInt2 = 0;
    while (paramInt2 < mRows.size())
    {
      int k = paramInt1 + getPaddingLeft();
      int j = 0;
      while (j < mRows.get(paramInt2)).childCount)
      {
        View localView = getChildAt(i);
        int m = k + localView.getMeasuredWidth();
        if (i == getChildCount() - 1) {
          m = paramInt3;
        }
        localView.layout(k, paramInt4, m, localView.getMeasuredHeight() + paramInt4);
        k = m + mHorizontalSpacing;
        i += 1;
        j += 1;
      }
      paramInt4 += mRows.get(paramInt2)).height + mVerticalSpacing;
      paramInt2 += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i1 = View.MeasureSpec.getMode(paramInt1);
    int i2 = View.MeasureSpec.getSize(paramInt1);
    int i3 = View.MeasureSpec.getMode(paramInt2);
    int i4 = View.MeasureSpec.getSize(paramInt2);
    int i5 = getChildCount();
    int i = 0;
    int j = 0;
    mRows.clear();
    Object localObject1 = new RowInfo();
    int k = 0;
    for (;;)
    {
      int i6;
      int i7;
      if (k < i5)
      {
        localObject2 = getChildAt(k);
        measureChildWithMargins((View)localObject2, paramInt1, 0, paramInt2, i);
        i6 = ((View)localObject2).getMeasuredHeight();
        i7 = ((View)localObject2).getMeasuredWidth();
        m = width + i7;
        int n = m;
        if (childCount > 0) {
          n = m + mHorizontalSpacing;
        }
        m = i;
        localObject2 = localObject1;
        if (i1 == 0) {
          break label300;
        }
        m = i;
        localObject2 = localObject1;
        if (n <= i2) {
          break label300;
        }
        if ((i3 == 0) || (i < i4)) {}
      }
      else
      {
        k = i;
        if (childCount > 0)
        {
          k = i;
          if (mRows.size() > 0) {
            k = i + mVerticalSpacing;
          }
          k += height;
          mRows.add(localObject1);
        }
        setMeasuredDimension(resolveSize(getPaddingLeft() + j + getPaddingRight(), paramInt1), resolveSize(getPaddingTop() + k + getPaddingBottom(), paramInt2));
        return;
      }
      int m = i;
      if (mRows.size() > 0) {
        m = i + mVerticalSpacing;
      }
      m += height;
      mRows.add(localObject1);
      Object localObject2 = new RowInfo();
      label300:
      if (childCount > 0) {
        width += mHorizontalSpacing;
      }
      width += i7;
      childCount += 1;
      j = Math.max(j, width);
      height = Math.max(height, i6);
      k += 1;
      i = m;
      localObject1 = localObject2;
    }
  }
  
  public void setHorizontalSpacing(int paramInt)
  {
    mHorizontalSpacing = paramInt;
    requestLayout();
  }
  
  public void setVerticalSpacing(int paramInt)
  {
    mVerticalSpacing = paramInt;
    requestLayout();
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }
  
  public static class RowInfo
  {
    int childCount;
    int height;
    int width;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.RowLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */