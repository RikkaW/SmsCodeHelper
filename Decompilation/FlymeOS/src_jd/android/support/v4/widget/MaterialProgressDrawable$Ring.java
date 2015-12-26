package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.NonNull;

class MaterialProgressDrawable$Ring
{
  private int mAlpha;
  private Path mArrow;
  private int mArrowHeight;
  private final Paint mArrowPaint = new Paint();
  private float mArrowScale;
  private int mArrowWidth;
  private int mBackgroundColor;
  private final Drawable.Callback mCallback;
  private final Paint mCirclePaint = new Paint(1);
  private int mColorIndex;
  private int[] mColors;
  private int mCurrentColor;
  private float mEndTrim = 0.0F;
  private final Paint mPaint = new Paint();
  private double mRingCenterRadius;
  private float mRotation = 0.0F;
  private boolean mShowArrow;
  private float mStartTrim = 0.0F;
  private float mStartingEndTrim;
  private float mStartingRotation;
  private float mStartingStartTrim;
  private float mStrokeInset = 2.5F;
  private float mStrokeWidth = 5.0F;
  private final RectF mTempBounds = new RectF();
  
  public MaterialProgressDrawable$Ring(Drawable.Callback paramCallback)
  {
    mCallback = paramCallback;
    mPaint.setStrokeCap(Paint.Cap.SQUARE);
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mArrowPaint.setStyle(Paint.Style.FILL);
    mArrowPaint.setAntiAlias(true);
  }
  
  private void drawTriangle(Canvas paramCanvas, float paramFloat1, float paramFloat2, Rect paramRect)
  {
    if (mShowArrow)
    {
      if (mArrow != null) {
        break label213;
      }
      mArrow = new Path();
      mArrow.setFillType(Path.FillType.EVEN_ODD);
    }
    for (;;)
    {
      float f1 = (int)mStrokeInset / 2;
      float f2 = mArrowScale;
      float f3 = (float)(mRingCenterRadius * Math.cos(0.0D) + paramRect.exactCenterX());
      float f4 = (float)(mRingCenterRadius * Math.sin(0.0D) + paramRect.exactCenterY());
      mArrow.moveTo(0.0F, 0.0F);
      mArrow.lineTo(mArrowWidth * mArrowScale, 0.0F);
      mArrow.lineTo(mArrowWidth * mArrowScale / 2.0F, mArrowHeight * mArrowScale);
      mArrow.offset(f3 - f1 * f2, f4);
      mArrow.close();
      mArrowPaint.setColor(mCurrentColor);
      paramCanvas.rotate(paramFloat1 + paramFloat2 - 5.0F, paramRect.exactCenterX(), paramRect.exactCenterY());
      paramCanvas.drawPath(mArrow, mArrowPaint);
      return;
      label213:
      mArrow.reset();
    }
  }
  
  private int getNextColorIndex()
  {
    return (mColorIndex + 1) % mColors.length;
  }
  
  private void invalidateSelf()
  {
    mCallback.invalidateDrawable(null);
  }
  
  public void draw(Canvas paramCanvas, Rect paramRect)
  {
    RectF localRectF = mTempBounds;
    localRectF.set(paramRect);
    localRectF.inset(mStrokeInset, mStrokeInset);
    float f1 = (mStartTrim + mRotation) * 360.0F;
    float f2 = (mEndTrim + mRotation) * 360.0F - f1;
    mPaint.setColor(mCurrentColor);
    paramCanvas.drawArc(localRectF, f1, f2, false, mPaint);
    drawTriangle(paramCanvas, f1, f2, paramRect);
    if (mAlpha < 255)
    {
      mCirclePaint.setColor(mBackgroundColor);
      mCirclePaint.setAlpha(255 - mAlpha);
      paramCanvas.drawCircle(paramRect.exactCenterX(), paramRect.exactCenterY(), paramRect.width() / 2, mCirclePaint);
    }
  }
  
  public int getAlpha()
  {
    return mAlpha;
  }
  
  public double getCenterRadius()
  {
    return mRingCenterRadius;
  }
  
  public float getEndTrim()
  {
    return mEndTrim;
  }
  
  public float getInsets()
  {
    return mStrokeInset;
  }
  
  public int getNextColor()
  {
    return mColors[getNextColorIndex()];
  }
  
  public float getRotation()
  {
    return mRotation;
  }
  
  public float getStartTrim()
  {
    return mStartTrim;
  }
  
  public int getStartingColor()
  {
    return mColors[mColorIndex];
  }
  
  public float getStartingEndTrim()
  {
    return mStartingEndTrim;
  }
  
  public float getStartingRotation()
  {
    return mStartingRotation;
  }
  
  public float getStartingStartTrim()
  {
    return mStartingStartTrim;
  }
  
  public float getStrokeWidth()
  {
    return mStrokeWidth;
  }
  
  public void goToNextColor()
  {
    setColorIndex(getNextColorIndex());
  }
  
  public void resetOriginals()
  {
    mStartingStartTrim = 0.0F;
    mStartingEndTrim = 0.0F;
    mStartingRotation = 0.0F;
    setStartTrim(0.0F);
    setEndTrim(0.0F);
    setRotation(0.0F);
  }
  
  public void setAlpha(int paramInt)
  {
    mAlpha = paramInt;
  }
  
  public void setArrowDimensions(float paramFloat1, float paramFloat2)
  {
    mArrowWidth = ((int)paramFloat1);
    mArrowHeight = ((int)paramFloat2);
  }
  
  public void setArrowScale(float paramFloat)
  {
    if (paramFloat != mArrowScale)
    {
      mArrowScale = paramFloat;
      invalidateSelf();
    }
  }
  
  public void setBackgroundColor(int paramInt)
  {
    mBackgroundColor = paramInt;
  }
  
  public void setCenterRadius(double paramDouble)
  {
    mRingCenterRadius = paramDouble;
  }
  
  public void setColor(int paramInt)
  {
    mCurrentColor = paramInt;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setColorIndex(int paramInt)
  {
    mColorIndex = paramInt;
    mCurrentColor = mColors[mColorIndex];
  }
  
  public void setColors(@NonNull int[] paramArrayOfInt)
  {
    mColors = paramArrayOfInt;
    setColorIndex(0);
  }
  
  public void setEndTrim(float paramFloat)
  {
    mEndTrim = paramFloat;
    invalidateSelf();
  }
  
  public void setInsets(int paramInt1, int paramInt2)
  {
    float f = Math.min(paramInt1, paramInt2);
    if ((mRingCenterRadius <= 0.0D) || (f < 0.0F)) {}
    for (f = (float)Math.ceil(mStrokeWidth / 2.0F);; f = (float)(f / 2.0F - mRingCenterRadius))
    {
      mStrokeInset = f;
      return;
    }
  }
  
  public void setRotation(float paramFloat)
  {
    mRotation = paramFloat;
    invalidateSelf();
  }
  
  public void setShowArrow(boolean paramBoolean)
  {
    if (mShowArrow != paramBoolean)
    {
      mShowArrow = paramBoolean;
      invalidateSelf();
    }
  }
  
  public void setStartTrim(float paramFloat)
  {
    mStartTrim = paramFloat;
    invalidateSelf();
  }
  
  public void setStrokeWidth(float paramFloat)
  {
    mStrokeWidth = paramFloat;
    mPaint.setStrokeWidth(paramFloat);
    invalidateSelf();
  }
  
  public void storeOriginals()
  {
    mStartingStartTrim = mStartTrim;
    mStartingEndTrim = mEndTrim;
    mStartingRotation = mRotation;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.MaterialProgressDrawable.Ring
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */