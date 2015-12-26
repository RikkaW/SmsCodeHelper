package com.meizu.common.drawble;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

public class CircularAnimatedDrawable
  extends Drawable
  implements Animatable
{
  public static final String START_ANGLE_PROPERTY = "startAngle";
  public static final String SWEEP_ANGLE_PROPERTY = "sweepAngle";
  private final long LOADING_ANIM_DURATION = 1760L;
  private final RectF fBounds = new RectF();
  private boolean mAllowLoading = true;
  private float mBorderWidth;
  private Animator mLoadingAnimator = null;
  private Paint mPaint;
  private boolean mRunning;
  private float mStartAngle;
  private float mSweepAngle;
  
  public CircularAnimatedDrawable(int paramInt, float paramFloat)
  {
    mBorderWidth = paramFloat;
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(paramFloat);
    mPaint.setColor(paramInt);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mLoadingAnimator = createLoadingAnimator();
  }
  
  private Animator createLoadingAnimator()
  {
    ObjectAnimator localObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[] { PropertyValuesHolder.ofKeyframe("startAngle", new Keyframe[] { Keyframe.ofFloat(0.0F, -90.0F), Keyframe.ofFloat(0.5F, 330.0F), Keyframe.ofFloat(1.0F, 630.0F) }), PropertyValuesHolder.ofFloat("sweepAngle", new float[] { 0.0F, -120.0F, 0.0F }) });
    localObjectAnimator.setDuration(1760L);
    localObjectAnimator.setInterpolator(new LinearInterpolator());
    localObjectAnimator.setRepeatCount(-1);
    return localObjectAnimator;
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.drawArc(fBounds, mStartAngle, mSweepAngle, false, mPaint);
  }
  
  public int getOpacity()
  {
    return -2;
  }
  
  public float getStartAngle()
  {
    return mStartAngle;
  }
  
  public float getSweepAngle()
  {
    return mSweepAngle;
  }
  
  public boolean isRunning()
  {
    return mRunning;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    fBounds.left = (left + mBorderWidth / 2.0F + 0.5F);
    fBounds.right = (right - mBorderWidth / 2.0F - 0.5F);
    fBounds.top = (top + mBorderWidth / 2.0F + 0.5F);
    fBounds.bottom = (bottom - mBorderWidth / 2.0F - 0.5F);
  }
  
  public void setAllowLoading(boolean paramBoolean)
  {
    mAllowLoading = paramBoolean;
  }
  
  public void setAlpha(int paramInt)
  {
    mPaint.setAlpha(paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mPaint.setColorFilter(paramColorFilter);
  }
  
  public void setStartAngle(float paramFloat)
  {
    mStartAngle = paramFloat;
    if (mAllowLoading) {
      invalidateSelf();
    }
  }
  
  public void setSweepAngle(float paramFloat)
  {
    mSweepAngle = paramFloat;
    if (mAllowLoading) {
      invalidateSelf();
    }
  }
  
  public void start()
  {
    if (isRunning()) {
      return;
    }
    mRunning = true;
    mLoadingAnimator.start();
    invalidateSelf();
  }
  
  public void stop()
  {
    if (!isRunning()) {
      return;
    }
    mRunning = false;
    mLoadingAnimator.cancel();
    invalidateSelf();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.CircularAnimatedDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */