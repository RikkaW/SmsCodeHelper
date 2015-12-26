package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.Scroller;
import com.meizu.common.R.styleable;
import java.util.List;

public class HorizontalWheelView
  extends View
{
  private static final String TAG = "HorizontalWheelView";
  private String INSTANCE_STATE = "instanceState";
  private String STATE_SELECTED = "selected";
  private boolean mAllowScroll = true;
  private int mClickNumber = -1;
  private float mClickNumberOffset;
  private float mClickOffset;
  private float mDamping = 0.0F;
  private List<String> mData;
  private int mDataSize = 100;
  private float mDensity;
  private int mDownX;
  private int mDrawCount;
  private boolean mIsFling;
  private int mLastX;
  private int mLineColor = -16777216;
  private float mLineHeight = 18.0F;
  private float mLineMarginBottom = 5.0F;
  private Paint mLinePaint;
  private float mLineStartY;
  private float mLineStopY;
  private float mLineWidth = 2.0F;
  private int mLittleLineColor = -16777216;
  private float mLittleLineWidth = 1.0F;
  private float mMaxTotalMove;
  private int mMiddle;
  private int mMinVelocity;
  private OnValueChangeListener mOnValueChangeListener;
  private boolean mOnce = true;
  private boolean mPaintRound;
  private float mScaleDistance = 20.0F;
  private float mScaleTextSize = 18.0F;
  private Scroller mScroller;
  private boolean mScrolling;
  private int mSelected = 0;
  private int mSelectedColor = -65536;
  private int mShowNumber = 5;
  private int mTextColor = -16777216;
  private float mTextMargin = 10.0F;
  private Paint mTextPaint;
  private float mTotalMove = 0.0F;
  private Paint mTrianglePaint;
  private Path mTrianglePath;
  private float mTriangleSideLength = 10.0F;
  private VelocityTracker mVelocityTracker;
  private int mWidth;
  
  public HorizontalWheelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
    getAttrs(paramContext, paramAttributeSet);
    mTextPaint = new TextPaint(1);
    mTextPaint.setTextSize(mScaleTextSize);
    mTextPaint.setColor(mTextColor);
    paramContext = new Rect();
    mTextPaint.getTextBounds("0", 0, 1, paramContext);
    int i = paramContext.height();
    float f1 = getPaddingTop();
    float f2 = mTextMargin;
    mLineStartY = (i + (f1 + f2));
    mLineStopY = (mLineStartY + mLineHeight);
    mLinePaint = new Paint(1);
    mLinePaint.setColor(mLineColor);
    if (mPaintRound) {
      mLinePaint.setStrokeCap(Paint.Cap.ROUND);
    }
    mTriangleSideLength *= mDensity;
    mTrianglePath = new Path();
    mTrianglePaint = new Paint(1);
    mTrianglePaint.setStyle(Paint.Style.FILL);
    mTrianglePaint.setColor(mSelectedColor);
  }
  
  private float computeDistance(float paramFloat)
  {
    if (paramFloat <= mScaleDistance / 2.0F) {
      return -paramFloat;
    }
    return mScaleDistance - paramFloat;
  }
  
  private void computeTextSizeAndColor(int paramInt, float paramFloat)
  {
    int m = mSelectedColor;
    int i = Color.alpha(m);
    int j = Color.red(m);
    int k = Color.green(m);
    m = Color.blue(m);
    int n = Color.alpha(paramInt);
    int i1 = Color.red(paramInt);
    int i2 = Color.green(paramInt);
    paramInt = Color.blue(paramInt);
    paramInt = Color.argb((int)(i * (1.0F - paramFloat) + n * paramFloat), (int)(j * (1.0F - paramFloat) + i1 * paramFloat), (int)(k * (1.0F - paramFloat) + i2 * paramFloat), (int)(m * (1.0F - paramFloat) + paramInt * paramFloat));
    mLinePaint.setColor(paramInt);
  }
  
  private void countVelocityTracker()
  {
    mVelocityTracker.computeCurrentVelocity(1000);
    float f1 = mVelocityTracker.getXVelocity();
    if (Math.abs(f1) > mMinVelocity)
    {
      float f2 = mDamping;
      mIsFling = true;
      mScroller.fling(0, 0, (int)((1.0F - f2) * f1), 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
      return;
    }
    fixPos();
  }
  
  private void drawScaleLine(Canvas paramCanvas)
  {
    paramCanvas.save();
    float f1 = mTotalMove;
    float f2 = mScaleDistance;
    int j = (int)(mTotalMove / mScaleDistance);
    if (j != mSelected)
    {
      mSelected = j;
      notifySelectedChange();
    }
    f1 = mWidth / 2 - f1 % f2;
    int i = 0;
    if (i < mDrawCount)
    {
      f2 = f1 + i * mScaleDistance;
      String str;
      PointF localPointF;
      if ((getPaddingRight() + f2 < mWidth) && (j + i <= mDataSize))
      {
        if ((j + i) % mShowNumber == 0)
        {
          str = getText(j + i);
          localPointF = getTextPoint(str, mTextPaint, f2);
          paramCanvas.drawText(str, x, y, mTextPaint);
          setLinePaint(mLineColor, mLineWidth, f2);
          paramCanvas.drawLine(f2, mLineStartY, f2, mLineStopY, mLinePaint);
        }
      }
      else
      {
        label197:
        f2 = f1 - i * mScaleDistance;
        if ((f2 > getPaddingLeft()) && (j - i >= 0))
        {
          if ((j - i) % mShowNumber != 0) {
            break label379;
          }
          str = getText(j - i);
          localPointF = getTextPoint(str, mTextPaint, f2);
          paramCanvas.drawText(str, x, y, mTextPaint);
          setLinePaint(mLineColor, mLineWidth, f2);
          paramCanvas.drawLine(f2, mLineStartY, f2, mLineStopY, mLinePaint);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        setLinePaint(mLittleLineColor, mLittleLineWidth, f2);
        float f3 = mLineStartY;
        paramCanvas.drawLine(f2, mLineHeight / 4.0F + f3, f2, mLineStopY - mLineHeight / 4.0F, mLinePaint);
        break label197;
        label379:
        setLinePaint(mLittleLineColor, mLittleLineWidth, f2);
        mLinePaint.setStrokeWidth(mLittleLineWidth);
        f3 = mLineStartY;
        paramCanvas.drawLine(f2, mLineHeight / 4.0F + f3, f2, mLineStopY - mLineHeight / 4.0F, mLinePaint);
      }
    }
    paramCanvas.restore();
  }
  
  private void drawTriangle(Canvas paramCanvas)
  {
    paramCanvas.drawPath(mTrianglePath, mTrianglePaint);
  }
  
  private void fixPos()
  {
    mIsFling = false;
    mAllowScroll = true;
    mScroller.forceFinished(true);
    float f = computeDistance(mTotalMove % mScaleDistance);
    mScroller.startScroll((int)mTotalMove, 0, (int)f, 0, 1000);
    postInvalidate();
  }
  
  private void getAttrs(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.HorizontalWheelView, 0, 0);
    int j = paramContext.getIndexCount();
    int i = 0;
    if (i < j)
    {
      int k = paramContext.getIndex(i);
      if (k == R.styleable.HorizontalWheelView_mcScaleDistance) {
        mScaleDistance = ((int)paramContext.getDimension(k, mScaleDistance));
      }
      for (;;)
      {
        i += 1;
        break;
        if (k == R.styleable.HorizontalWheelView_mcTextColor)
        {
          mTextColor = paramContext.getColor(k, -16777216);
        }
        else if (k == R.styleable.HorizontalWheelView_mcTextSize)
        {
          mScaleTextSize = ((int)paramContext.getDimension(k, mScaleTextSize));
          mMaxTotalMove = (mDataSize * mScaleDistance);
        }
        else if (k == R.styleable.HorizontalWheelView_mcSelectedColor)
        {
          mSelectedColor = paramContext.getColor(k, -65536);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLineColor)
        {
          mLineColor = paramContext.getColor(k, -16777216);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLineWidth)
        {
          mLineWidth = paramContext.getDimension(k, mLineWidth);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLineHeight)
        {
          mLineHeight = paramContext.getDimension(k, mLineHeight);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLittleLineWidth)
        {
          mLittleLineWidth = paramContext.getDimension(k, mLittleLineWidth);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLittleLineColor)
        {
          mLittleLineColor = paramContext.getColor(k, -16777216);
        }
        else if (k == R.styleable.HorizontalWheelView_mcTriangleSideLength)
        {
          mTriangleSideLength = paramContext.getDimension(k, mTriangleSideLength);
        }
        else if (k == R.styleable.HorizontalWheelView_mcShowNumber)
        {
          mShowNumber = paramContext.getInt(k, mShowNumber);
        }
        else if (k == R.styleable.HorizontalWheelView_mcTextMarginBottom)
        {
          mTextMargin = paramContext.getDimension(k, mTextMargin);
        }
        else if (k == R.styleable.HorizontalWheelView_mcLineMarginBottom)
        {
          mLineMarginBottom = paramContext.getDimension(k, mLineMarginBottom);
        }
        else if (k == R.styleable.HorizontalWheelView_mcDamping)
        {
          mDamping = paramContext.getFloat(k, mDamping);
          if (mDamping > 1.0F) {
            mDamping = 1.0F;
          } else if (mDamping < 0.0F) {
            mDamping = 0.0F;
          }
        }
        else if (k == R.styleable.HorizontalWheelView_mcPaintRound)
        {
          mPaintRound = paramContext.getBoolean(k, false);
        }
      }
    }
    paramContext.recycle();
  }
  
  private float getMove(float paramFloat)
  {
    float f;
    if (mTotalMove + paramFloat < 0.0F) {
      f = -mTotalMove;
    }
    do
    {
      return f;
      f = paramFloat;
    } while (mTotalMove + paramFloat <= mMaxTotalMove);
    return mMaxTotalMove - mTotalMove;
  }
  
  private String getText(int paramInt)
  {
    if ((mData != null) && (mData.size() > 0) && (paramInt < mData.size()) && (paramInt >= 0)) {
      return (String)mData.get(paramInt);
    }
    return String.valueOf(paramInt);
  }
  
  private PointF getTextPoint(String paramString, Paint paramPaint, float paramFloat)
  {
    Rect localRect = new Rect();
    paramPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    paramString = new PointF();
    float f = localRect.width() / 2;
    int i = getPaddingTop();
    paramString.set(paramFloat - f, localRect.height() + i);
    return paramString;
  }
  
  private void init()
  {
    mScroller = new Scroller(getContext());
    mDensity = ((int)getContextgetResourcesgetDisplayMetricsdensity);
    mScaleTextSize *= mDensity;
    mScaleDistance *= mDensity;
    mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
    mLineHeight *= mDensity;
    mLittleLineWidth *= mDensity;
    mLineWidth *= mDensity;
    mTextMargin *= mDensity;
    mLineMarginBottom *= mDensity;
    mMaxTotalMove = (mDataSize * mScaleDistance);
    mClickOffset = (3.0F * mDensity);
    mClickNumberOffset = (15.0F * mDensity);
  }
  
  private void initTriangle()
  {
    mMiddle = (mWidth / 2);
    int i = (int)(Math.sin(1.0471975511965976D) * mTriangleSideLength);
    float f1 = mLineStopY + mLineMarginBottom;
    float f2 = i + f1;
    mTrianglePath.moveTo(mMiddle, f1);
    mTrianglePath.lineTo(mMiddle - mTriangleSideLength / 2.0F, f2);
    mTrianglePath.lineTo(mMiddle + mTriangleSideLength / 2.0F, f2);
    mTrianglePath.close();
  }
  
  private void notifySelectedChange()
  {
    if (mOnValueChangeListener != null) {
      post(new HorizontalWheelView.1(this));
    }
  }
  
  private void setLinePaint(int paramInt, float paramFloat1, float paramFloat2)
  {
    mLinePaint.setStrokeWidth(paramFloat1);
    if (Math.abs(paramFloat2 - mWidth / 2) < mScaleDistance)
    {
      computeTextSizeAndColor(paramInt, Math.abs(paramFloat2 - mWidth / 2) / mScaleDistance);
      return;
    }
    mLinePaint.setColor(paramInt);
  }
  
  private void setSelectNotDraw(int paramInt)
  {
    if (paramInt > mDataSize) {
      mSelected = mDataSize;
    }
    for (;;)
    {
      mTotalMove = (mSelected * mScaleDistance);
      return;
      if (paramInt < 0) {
        mSelected = 0;
      } else {
        mSelected = paramInt;
      }
    }
  }
  
  public void computeScroll()
  {
    super.computeScroll();
    if (mScroller.computeScrollOffset())
    {
      int i = mScroller.getCurrX();
      float f;
      if (mIsFling)
      {
        f = mLastX - i;
        mLastX = i;
        if (((f >= 0.0F) && (mTotalMove >= mMaxTotalMove)) || ((f <= 0.0F) && (mTotalMove <= 0.0F)))
        {
          mScroller.forceFinished(true);
          return;
        }
      }
      for (mTotalMove = (getMove(f) + mTotalMove);; mTotalMove = i)
      {
        postInvalidate();
        return;
      }
    }
    if (mIsFling)
    {
      fixPos();
      return;
    }
    mClickNumber = -1;
    mScrolling = false;
  }
  
  public float getScaleDistance()
  {
    return mScaleDistance;
  }
  
  public float getSelected()
  {
    return mSelected;
  }
  
  public boolean isAllowScroll()
  {
    return mAllowScroll;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (mVelocityTracker != null) {
      mVelocityTracker.recycle();
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawScaleLine(paramCanvas);
    drawTriangle(paramCanvas);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    mWidth = getWidth();
    if ((mWidth != 0) && (mOnce))
    {
      mTotalMove = (mSelected * mScaleDistance);
      initTriangle();
      mDrawCount = ((int)(mWidth / mScaleDistance) / 2 + 1);
      mOnce = false;
    }
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof Bundle))
    {
      paramParcelable = (Bundle)paramParcelable;
      setSelect(paramParcelable.getInt(STATE_SELECTED));
      super.onRestoreInstanceState(paramParcelable.getParcelable(INSTANCE_STATE));
      return;
    }
    super.onRestoreInstanceState(paramParcelable);
  }
  
  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
    if (mClickNumber != -1)
    {
      localBundle.putInt(STATE_SELECTED, mClickNumber);
      return localBundle;
    }
    localBundle.putInt(STATE_SELECTED, mSelected);
    return localBundle;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((!mAllowScroll) || (mScrolling))
    {
      bool1 = super.onTouchEvent(paramMotionEvent);
      return bool1;
    }
    int i = paramMotionEvent.getAction();
    int j = (int)paramMotionEvent.getX();
    if (mVelocityTracker == null) {
      mVelocityTracker = VelocityTracker.obtain();
    }
    mVelocityTracker.addMovement(paramMotionEvent);
    mIsFling = false;
    switch (i)
    {
    }
    for (;;)
    {
      mLastX = j;
      return true;
      mScroller.forceFinished(true);
      mLastX = j;
      mDownX = j;
      continue;
      if (getParent() != null) {
        getParent().requestDisallowInterceptTouchEvent(true);
      }
      float f = mLastX - j;
      if (f >= 0.0F)
      {
        bool1 = bool2;
        if (mTotalMove >= mMaxTotalMove) {
          break;
        }
      }
      if (f <= 0.0F)
      {
        bool1 = bool2;
        if (mTotalMove <= 0.0F) {
          break;
        }
      }
      mTotalMove = (getMove(f) + mTotalMove);
      invalidate();
      continue;
      if (Math.abs(mDownX - mLastX) < 5)
      {
        f = mTotalMove + (mDownX - mMiddle);
        if ((f >= -mClickOffset) && (f <= mMaxTotalMove + mClickOffset))
        {
          i = Math.round(f / (mScaleDistance * mShowNumber));
          if ((Math.abs(f - i * mScaleDistance * mShowNumber) < mClickNumberOffset) && (mSelected != mShowNumber * i))
          {
            mClickNumber = (mShowNumber * i);
            smoothScroll(mClickNumber, 500);
            mScrolling = true;
          }
        }
      }
      for (i = 1; i == 0; i = 0)
      {
        mLastX = 0;
        invalidate();
        countVelocityTracker();
        return true;
      }
    }
  }
  
  public void scrollBy(float paramFloat)
  {
    mTotalMove = (getMove(paramFloat) + mTotalMove);
    invalidate();
  }
  
  public void setAllowScroll(boolean paramBoolean)
  {
    mAllowScroll = paramBoolean;
  }
  
  public void setData(List<String> paramList, int paramInt)
  {
    mScroller.forceFinished(true);
    mData = paramList;
    mDataSize = paramList.size();
    mMaxTotalMove = (mDataSize * mScaleDistance);
    setSelectNotDraw(paramInt);
    invalidate();
  }
  
  public void setOnValueChangeListener(OnValueChangeListener paramOnValueChangeListener)
  {
    mOnValueChangeListener = paramOnValueChangeListener;
  }
  
  public void setSelect(int paramInt)
  {
    mScroller.forceFinished(true);
    setSelectNotDraw(paramInt);
    invalidate();
  }
  
  public void setTotalMove(float paramFloat)
  {
    int i = 1;
    mScroller.forceFinished(true);
    if ((paramFloat < 0.0F) && (mTotalMove != 0.0F)) {
      mTotalMove = 0.0F;
    }
    for (;;)
    {
      if (i != 0) {
        invalidate();
      }
      return;
      if ((paramFloat > mMaxTotalMove) && (mTotalMove != mMaxTotalMove)) {
        mTotalMove = mMaxTotalMove;
      } else if (paramFloat != mTotalMove) {
        mTotalMove = paramFloat;
      } else {
        i = 0;
      }
    }
  }
  
  public void smoothScroll(int paramInt)
  {
    smoothScroll(paramInt, 1000);
  }
  
  public void smoothScroll(int paramInt1, int paramInt2)
  {
    mIsFling = false;
    mScroller.forceFinished(true);
    paramInt1 = (int)(paramInt1 * mScaleDistance - mTotalMove);
    mScroller.startScroll((int)mTotalMove, 0, paramInt1, 0, paramInt2);
    invalidate();
  }
  
  public static abstract interface OnValueChangeListener
  {
    public abstract void onSelectedChange(float paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.HorizontalWheelView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */