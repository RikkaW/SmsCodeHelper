package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.meizu.common.R.attr;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.styleable;

public class PagerIndicator
  extends View
{
  private static final String TAG = "PagerIndicator";
  private int mCurPosition;
  private float mDistance;
  private float mEnlargeRadius;
  private int mFillColor;
  private int mGravity;
  private int mHighlightColor;
  private float mPageOffset;
  private int mPagerCount;
  private Paint mPaintFill;
  private Paint mPaintStroke;
  private float mRadius;
  private boolean mSnap;
  private int mSnapPage;
  private int mStrokeColor;
  
  public PagerIndicator(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PagerIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_PagerIndicator);
  }
  
  public PagerIndicator(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PagerIndicator, paramInt, 0);
    paramAttributeSet = getResources();
    float f1 = paramAttributeSet.getDimension(R.dimen.mc_pager_indicator_radius);
    float f2 = paramAttributeSet.getDimension(R.dimen.mc_pager_indicator_enlarge_radius);
    float f3 = paramAttributeSet.getDimension(R.dimen.mc_pager_indicator_distance);
    paramInt = paramAttributeSet.getColor(R.color.mc_pager_indicator_fill_color);
    int i = paramAttributeSet.getColor(R.color.mc_pager_indicator_highlight_color);
    mRadius = paramContext.getDimension(R.styleable.PagerIndicator_mcRadius, f1);
    mEnlargeRadius = paramContext.getDimension(R.styleable.PagerIndicator_mcEnlargeRadius, f2);
    mDistance = paramContext.getDimension(R.styleable.PagerIndicator_mcDistance, f3);
    mFillColor = paramContext.getColor(R.styleable.PagerIndicator_mcFillColor, paramInt);
    mHighlightColor = paramContext.getColor(R.styleable.PagerIndicator_mcHighlightColor, i);
    mStrokeColor = paramContext.getColor(R.styleable.PagerIndicator_mcStrokeColor, i);
    mGravity = paramContext.getInteger(R.styleable.PagerIndicator_mcGravity, 17);
    paramContext.recycle();
    mPaintFill = new Paint(1);
    mPaintFill.setStyle(Paint.Style.FILL);
    mPaintFill.setColor(mFillColor);
    mPaintStroke = new Paint(1);
    mPaintStroke.setStyle(Paint.Style.STROKE);
    mPaintStroke.setColor(mStrokeColor);
  }
  
  private int getGradualColor(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    int i = Color.red(paramInt1);
    int n = Color.green(paramInt1);
    int j = Color.blue(paramInt1);
    paramInt1 = Color.alpha(paramInt1);
    int i2 = Color.red(paramInt2);
    int i1 = Color.green(paramInt2);
    int m = Color.blue(paramInt2);
    int k = Color.alpha(paramInt2);
    if (paramInt3 < 0)
    {
      i = Math.round(i2 - (i2 - i) * paramFloat);
      paramInt3 = Math.round(i1 - (i1 - n) * paramFloat);
      paramInt2 = Math.round(m - (m - j) * paramFloat);
    }
    for (paramInt1 = Math.round(k - (k - paramInt1) * paramFloat);; paramInt1 = Math.round(paramInt1 + (k - paramInt1) * paramFloat))
    {
      return Color.argb(paramInt1, i, paramInt3, paramInt2);
      float f = i;
      i = Math.round((i2 - i) * paramFloat + f);
      paramInt3 = Math.round(n + (i1 - n) * paramFloat);
      paramInt2 = Math.round(j + (m - j) * paramFloat);
    }
  }
  
  private float getGradualRadius(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
  {
    if (paramInt < 0) {
      return paramFloat2 - (paramFloat2 - paramFloat1) * paramFloat3;
    }
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mPagerCount == 0) {
      return;
    }
    int k = mPagerCount;
    int i = getWidth();
    int j = mCurPosition;
    float f4 = mDistance;
    float f2 = mEnlargeRadius;
    float f1 = i / 2.0F - (k - 1) * f4 / 2.0F;
    float f3 = k - 1;
    f3 = mRadius;
    mPaintFill.setColor(mFillColor);
    i = 0;
    if (i < k)
    {
      if (j == i) {}
      for (;;)
      {
        i += 1;
        break;
        if (((j != k - 1) || (i != 0)) && (i != j + 1)) {
          paramCanvas.drawCircle(i * f4 + f1, f2, mRadius, mPaintFill);
        }
      }
    }
    if (mSnap)
    {
      i = mSnapPage;
      f3 = i * f4 + f1;
      if (j != k - 1) {
        break label315;
      }
    }
    for (;;)
    {
      i = getGradualColor(mFillColor, mHighlightColor, mPageOffset, -1);
      f4 = getGradualRadius(mRadius, mEnlargeRadius, mPageOffset, -1);
      mPaintFill.setColor(i);
      paramCanvas.drawCircle(f3, f2, f4, mPaintFill);
      i = getGradualColor(mFillColor, mHighlightColor, mPageOffset, 1);
      f3 = getGradualRadius(mRadius, mEnlargeRadius, mPageOffset, 1);
      mPaintFill.setColor(i);
      paramCanvas.drawCircle(f1, f2, f3, mPaintFill);
      return;
      i = j;
      break;
      label315:
      f1 = f3 + f4;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = (int)(mPagerCount * mDistance + mRadius * 2.0F);
    int j = (int)Math.max(mRadius * 2.0F, mEnlargeRadius * 2.0F);
    int k = getPaddingLeft();
    int m = getPaddingRight();
    int n = getPaddingTop();
    int i1 = getPaddingBottom();
    setMeasuredDimension(resolveSizeAndState(i + (k + m), paramInt1, 0), resolveSizeAndState(j + (n + i1), paramInt2, 0));
  }
  
  public void setCirclePosOffset(float paramFloat, int paramInt)
  {
    mCurPosition = paramInt;
    mPageOffset = paramFloat;
    invalidate();
  }
  
  public void setCirclePosition(int paramInt)
  {
    mCurPosition = paramInt;
    mSnapPage = paramInt;
    invalidate();
  }
  
  public void setPagerCount(int paramInt)
  {
    if (mPagerCount == paramInt) {
      return;
    }
    mPagerCount = paramInt;
    requestLayout();
  }
  
  public void setSnap(boolean paramBoolean)
  {
    mSnap = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PagerIndicator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */