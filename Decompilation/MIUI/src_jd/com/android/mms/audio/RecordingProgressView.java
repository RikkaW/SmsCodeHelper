package com.android.mms.audio;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.android.mms.R.styleable;
import java.util.Iterator;
import java.util.LinkedList;

public class RecordingProgressView
  extends View
{
  private int mCount;
  private int mDefaultColor;
  private int mDrawingPositionX;
  private LinkedList<Integer> mElements = new LinkedList();
  private int mFilterCount = 10;
  private int mIndicatorColor;
  private int mIndicatorMaxHeight;
  private int mIndicatorNum;
  private int mIndicatorRadius;
  private int mIndicatorWidth;
  private int mInterval;
  private int mMaxIndicatorNum;
  
  public RecordingProgressView(Context paramContext)
  {
    super(paramContext);
  }
  
  public RecordingProgressView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initParams(paramContext, paramAttributeSet);
  }
  
  public RecordingProgressView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initParams(paramContext, paramAttributeSet);
  }
  
  private void drawDots(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(mDefaultColor);
    localPaint.setStyle(Paint.Style.FILL);
    mDrawingPositionX = 0;
    int j = getHeight() / 2;
    int k = mIndicatorRadius;
    int i = 0;
    while (i < mIndicatorNum - mElements.size())
    {
      paramCanvas.drawCircle(mDrawingPositionX + mIndicatorRadius, mIndicatorRadius + (j - k), mIndicatorRadius, localPaint);
      mDrawingPositionX += mInterval;
      i += 1;
    }
  }
  
  private void drawVolumes(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(mIndicatorColor);
    localPaint.setStyle(Paint.Style.FILL);
    int i = 0;
    Iterator localIterator = mElements.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      if (mElements.size() - i > mIndicatorNum)
      {
        i += 1;
      }
      else
      {
        int j = localInteger.intValue();
        int k = (getHeight() - j) / 2;
        paramCanvas.drawCircle(mDrawingPositionX + mIndicatorRadius, k, mIndicatorRadius, localPaint);
        paramCanvas.drawRect(mDrawingPositionX, k, mDrawingPositionX + mIndicatorWidth, k + j, localPaint);
        paramCanvas.drawCircle(mDrawingPositionX + mIndicatorRadius, k + j, mIndicatorRadius, localPaint);
        mDrawingPositionX += mInterval;
      }
    }
  }
  
  private void initParams(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RecordingProgressView);
    mInterval = paramContext.getDimensionPixelSize(0, 25);
    mIndicatorWidth = paramContext.getDimensionPixelSize(1, 4);
    mIndicatorRadius = (mIndicatorWidth / 2);
    mIndicatorMaxHeight = paramContext.getDimensionPixelSize(2, 80);
    int i = paramContext.getInt(3, 30);
    mIndicatorNum = i;
    mMaxIndicatorNum = i;
    mDefaultColor = paramContext.getColor(4, -7829368);
    mIndicatorColor = paramContext.getColor(5, -16776961);
    paramContext.recycle();
  }
  
  private void update()
  {
    invalidate();
  }
  
  private int volumeToPix(double paramDouble)
  {
    double d2 = paramDouble * 0.05D;
    double d1 = d2;
    if (d2 > mIndicatorMaxHeight / 2)
    {
      int i = mCount;
      mCount = (i + 1);
      if (i >= mFilterCount) {
        break label75;
      }
    }
    label75:
    for (d1 = mIndicatorMaxHeight / 2;; d1 = mIndicatorMaxHeight / 2 + Math.sqrt(paramDouble - mIndicatorMaxHeight / 2 / 0.05D) * 0.05D * 5.0D)
    {
      paramDouble = d1;
      if (d1 > mIndicatorMaxHeight) {
        paramDouble = mIndicatorMaxHeight;
      }
      return (int)paramDouble;
    }
  }
  
  public void addVolumes(double paramDouble)
  {
    if (mElements.size() == mMaxIndicatorNum) {
      mElements.removeFirst();
    }
    mElements.add(Integer.valueOf(volumeToPix(paramDouble)));
    update();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawDots(paramCanvas);
    drawVolumes(paramCanvas);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(mInterval * (mIndicatorNum - 1) + mIndicatorWidth, getDefaultSize(getSuggestedMinimumHeight(), paramInt2));
  }
  
  public void reset()
  {
    mElements.clear();
    update();
    mCount = 0;
    setWidth(mInterval * (mMaxIndicatorNum - 1) + mIndicatorWidth);
  }
  
  public void setWidth(int paramInt)
  {
    int i = (paramInt - mIndicatorWidth) / mInterval + 1;
    if (mIndicatorNum != i)
    {
      mIndicatorNum = i;
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      width = paramInt;
      setLayoutParams(localLayoutParams);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.RecordingProgressView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */