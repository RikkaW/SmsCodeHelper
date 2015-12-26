package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.meizu.common.R.styleable;

public class CircleProgressBar
  extends View
{
  public static final int BAR_COLOR_DEF = -436207617;
  public static final float BAR_WIDTH_DEF_DIP = 2.0F;
  public static final int CENTER_TEXT_COLOR_DEF = -1;
  public static final int CENTER_TEXT_SIZE_DEF = 14;
  public static final int RIM_COLOR_DEF = 889192447;
  private int mBarColor;
  private Paint mBarPaint = new Paint();
  private int mBarPostition = 0;
  private float mBarWidth;
  private RectF mCircleBound = new RectF();
  private int mMax;
  private int mPercentage = 0;
  private int mProgress;
  private int mRimColor;
  private Paint mRimPaint = new Paint();
  private boolean mShouldUpdateBound = false;
  private String mText = "0%";
  private int mTextColor;
  private Paint mTextPaint = new Paint();
  private int mTextSize = 0;
  
  public CircleProgressBar(Context paramContext)
  {
    super(paramContext, null);
  }
  
  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CircleProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleProgressBar, paramInt, 0);
    mBarColor = paramContext.getColor(R.styleable.CircleProgressBar_mcCircleBarColor, -436207617);
    mRimColor = paramContext.getColor(R.styleable.CircleProgressBar_mcCircleBarRimColor, 889192447);
    new DisplayMetrics();
    paramAttributeSet = getResources().getDisplayMetrics();
    paramInt = (int)(density * 2.0F);
    mBarWidth = paramContext.getDimensionPixelSize(R.styleable.CircleProgressBar_mcCircleBarWidth, paramInt);
    paramInt = (int)(density * 14.0F);
    mTextSize = paramContext.getDimensionPixelSize(R.styleable.CircleProgressBar_mcCenterTextSize, paramInt);
    mTextColor = paramContext.getColor(R.styleable.CircleProgressBar_mcCenterTextColor, -1);
    setMax(paramContext.getInt(R.styleable.CircleProgressBar_mcCircleBarMax, 0));
    setProgress(paramContext.getInt(R.styleable.CircleProgressBar_mcCircleBarProgress, 0));
    paramContext.recycle();
    init();
  }
  
  private int getPosByProgress(int paramInt, boolean paramBoolean)
  {
    int i;
    int j;
    if (paramBoolean)
    {
      i = 360;
      if (mMax > 0) {
        break label29;
      }
      j = 0;
    }
    label29:
    do
    {
      return j;
      i = 100;
      break;
      j = i;
    } while (paramInt >= mMax);
    float f = paramInt / mMax;
    return (int)(i * f);
  }
  
  private void init()
  {
    setBound();
    setPaint();
    mBarPostition = getPosByProgress(mProgress, true);
    mPercentage = getPosByProgress(mProgress, false);
    mText = (String.valueOf(mPercentage) + "%");
  }
  
  private void setBound()
  {
    if (mCircleBound == null) {
      mCircleBound = new RectF();
    }
    mCircleBound.left = (getPaddingLeft() + mBarWidth);
    mCircleBound.top = (getPaddingTop() + mBarWidth);
    mCircleBound.right = (getWidth() - getPaddingRight() - mBarWidth);
    mCircleBound.bottom = (getHeight() - getPaddingBottom() - mBarWidth);
  }
  
  private void setPaint()
  {
    if (mBarPaint == null) {
      mBarPaint = new Paint();
    }
    mBarPaint.setColor(mBarColor);
    mBarPaint.setAntiAlias(true);
    mBarPaint.setStyle(Paint.Style.STROKE);
    mBarPaint.setStrokeWidth(mBarWidth);
    mBarPaint.setStrokeJoin(Paint.Join.ROUND);
    if (mRimPaint == null) {
      mRimPaint = new Paint();
    }
    mRimPaint.setColor(mRimColor);
    mRimPaint.setAntiAlias(true);
    mRimPaint.setStyle(Paint.Style.STROKE);
    mRimPaint.setStrokeWidth(mBarWidth);
    if (mTextPaint == null) {
      mTextPaint = new Paint();
    }
    mTextPaint.setTextSize(mTextSize);
    mTextPaint.setColor(mTextColor);
    mTextPaint.setAntiAlias(true);
  }
  
  public int getMax()
  {
    if (mMax < 0) {
      return 0;
    }
    return mMax;
  }
  
  public int getProgress()
  {
    if (mProgress < 0) {
      return 0;
    }
    return mProgress;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (mShouldUpdateBound)
    {
      setBound();
      mShouldUpdateBound = false;
    }
    paramCanvas.drawArc(mCircleBound, 360.0F, 360.0F, false, mRimPaint);
    paramCanvas.drawArc(mCircleBound, -90.0F, mBarPostition, false, mBarPaint);
    float f1 = (mTextPaint.descent() - mTextPaint.ascent()) / 2.0F;
    float f2 = mTextPaint.descent();
    float f3 = mTextPaint.measureText(mText) / 2.0F;
    paramCanvas.drawText(mText, getWidth() / 2 - f3, f1 - f2 + getHeight() / 2, mTextPaint);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    mShouldUpdateBound = true;
  }
  
  public void setCircleBarColor(int paramInt)
  {
    if (mBarColor != paramInt)
    {
      mBarColor = paramInt;
      mBarPaint.setColor(mBarColor);
      postInvalidate();
    }
  }
  
  public void setCircleBarWidth(float paramFloat)
  {
    if (Math.abs(mBarWidth - paramFloat) < 1.0E-6D) {
      return;
    }
    if (paramFloat < 0.0F) {}
    for (mBarWidth = 0.0F;; mBarWidth = paramFloat)
    {
      mBarPaint.setStrokeWidth(mBarWidth);
      mRimPaint.setStrokeWidth(mBarWidth);
      mShouldUpdateBound = true;
      postInvalidate();
      return;
    }
  }
  
  public void setCircleRimColor(int paramInt)
  {
    if (mRimColor != paramInt)
    {
      mRimColor = paramInt;
      mRimPaint.setColor(mRimColor);
      postInvalidate();
    }
  }
  
  public void setMax(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    if (i != mMax)
    {
      mMax = i;
      if (mProgress > i) {
        mProgress = i;
      }
      postInvalidate();
    }
  }
  
  public void setProgress(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    paramInt = i;
    if (i > mMax) {
      paramInt = mMax;
    }
    if (paramInt != mProgress)
    {
      mProgress = paramInt;
      mBarPostition = getPosByProgress(mProgress, true);
      mPercentage = getPosByProgress(mProgress, false);
      mText = (String.valueOf(mPercentage) + "%");
      postInvalidate();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircleProgressBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */