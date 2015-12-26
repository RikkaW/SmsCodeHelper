package com.android.mms.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class StaticGridView
  extends ViewGroup
{
  private int mBottom;
  private int mChildHeight;
  private int mChildWidth;
  private int mColumnCount;
  private Initializer mInitializer;
  private boolean mIsInitialized;
  private int mLeft;
  private int mRight;
  private int mRowCount;
  private int mTop;
  
  public StaticGridView(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext);
    set(paramInt1, paramInt2, paramInt3, paramInt4);
    setDrawingCacheEnabled(true);
    setWillNotDraw(false);
  }
  
  public StaticGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  protected void doLayout(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = 0;
    while (paramInt1 < getChildCount())
    {
      layoutChildByIndex(paramInt1);
      paramInt1 += 1;
    }
  }
  
  protected void layoutChildByIndex(int paramInt)
  {
    int i = paramInt / mColumnCount;
    int j = paramInt % mColumnCount;
    getChildAt(paramInt).layout(mChildWidth * j, mChildHeight * i, mChildWidth * (j + 1), mChildHeight * (i + 1));
  }
  
  int measureDimension(int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    switch (View.MeasureSpec.getMode(paramInt1))
    {
    default: 
      i = 0;
    case 0: 
      return i;
    case 1073741824: 
      return View.MeasureSpec.getSize(paramInt1);
    }
    return Math.min(paramInt2, View.MeasureSpec.getSize(paramInt1));
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if ((mIsInitialized) || (mInitializer == null)) {
      return;
    }
    mIsInitialized = true;
    mInitializer.onInitialize(this);
    measureChildren(View.MeasureSpec.makeMeasureSpec(mChildWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(mChildHeight, 1073741824));
    doLayout(mLeft, mTop, mRight, mBottom);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((mIsInitialized) || (mInitializer == null))
    {
      doLayout(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    mLeft = paramInt1;
    mTop = paramInt2;
    mRight = paramInt3;
    mBottom = paramInt4;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(measureDimension(paramInt1, mChildWidth * mColumnCount), measureDimension(paramInt2, mChildHeight * mRowCount));
    measureChildren(View.MeasureSpec.makeMeasureSpec(mChildWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(mChildHeight, 1073741824));
  }
  
  public void set(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mRowCount = Math.max(1, paramInt1);
    mColumnCount = Math.max(1, paramInt2);
    mChildHeight = Math.max(1, paramInt4);
    mChildWidth = Math.max(1, paramInt3);
  }
  
  public void setInitializer(Initializer paramInitializer)
  {
    mInitializer = paramInitializer;
  }
  
  public static abstract interface Initializer
  {
    public abstract void onInitialize(StaticGridView paramStaticGridView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.StaticGridView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */