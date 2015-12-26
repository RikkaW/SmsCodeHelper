package com.meizu.common.drawble;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class CircularProgressDrawable
  extends Drawable
{
  private Drawable mCenterIcon;
  private Paint mIconPaint;
  private Paint mPaint;
  private Path mPath;
  private RectF mRectF;
  private boolean mShouldIcon = false;
  private int mSize;
  private float mStartAngle;
  private int mStrokeColor;
  private int mStrokeWidth;
  private float mSweepAngle;
  
  public CircularProgressDrawable(int paramInt1, int paramInt2, int paramInt3)
  {
    mSize = paramInt1;
    mStrokeWidth = paramInt2;
    mStrokeColor = paramInt3;
    mStartAngle = -90.0F;
    mSweepAngle = 0.0F;
  }
  
  private Paint createPaint()
  {
    if (mPaint == null)
    {
      mPaint = new Paint();
      mPaint.setAntiAlias(true);
      mPaint.setStyle(Paint.Style.STROKE);
      mPaint.setStrokeWidth(mStrokeWidth);
      mPaint.setColor(mStrokeColor);
    }
    return mPaint;
  }
  
  private RectF getRect()
  {
    if (mRectF == null)
    {
      int i = mStrokeWidth / 2;
      mRectF = new RectF(i, i, getSize() - i, getSize() - i);
    }
    return mRectF;
  }
  
  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    if (mPath == null) {
      mPath = new Path();
    }
    mPath.reset();
    mPath.addArc(getRect(), mStartAngle, mSweepAngle);
    mPath.offset(left, top);
    paramCanvas.drawPath(mPath, createPaint());
    if (mCenterIcon != null)
    {
      paramCanvas.save();
      mCenterIcon.setBounds(0, 0, mCenterIcon.getIntrinsicWidth(), mCenterIcon.getIntrinsicHeight());
      paramCanvas.translate(left + getSize() / 2 - mCenterIcon.getIntrinsicWidth() / 2, top + getSize() / 2 - mCenterIcon.getIntrinsicHeight() / 2);
      mCenterIcon.draw(paramCanvas);
      paramCanvas.restore();
    }
    while (!mShouldIcon) {
      return;
    }
    if (mIconPaint == null)
    {
      mIconPaint = new Paint();
      mIconPaint.setStrokeCap(Paint.Cap.ROUND);
      mIconPaint.setColor(mStrokeColor);
    }
    int i = getSize();
    int j = getSize();
    int k = mStrokeWidth;
    int m = (int)(i / 5.0F * 2.0F);
    int n = (int)(0.15D * j);
    mIconPaint.setStrokeWidth(k);
    paramCanvas.drawLine(left + j / 2 - n / 2 - k / 2, top + i / 2 - m / 2, left + j / 2 - n / 2 - k / 2, top + i / 2 + m / 2, mIconPaint);
    paramCanvas.drawLine(left + j / 2 + n / 2 + k / 2, top + i / 2 - m / 2, left + j / 2 + n / 2 + k / 2, top + i / 2 + m / 2, mIconPaint);
  }
  
  public int getOpacity()
  {
    return 1;
  }
  
  public int getSize()
  {
    return mSize;
  }
  
  public void setAlpha(int paramInt) {}
  
  public void setCenterIcon(Drawable paramDrawable)
  {
    mCenterIcon = paramDrawable;
  }
  
  public void setColorFilter(ColorFilter paramColorFilter) {}
  
  public void setShowCenterIcon(boolean paramBoolean)
  {
    mShouldIcon = paramBoolean;
  }
  
  public void setSweepAngle(float paramFloat)
  {
    mSweepAngle = paramFloat;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.CircularProgressDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */