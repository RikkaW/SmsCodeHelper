package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class Scroller
{
  private static float DECELERATION_RATE = 0.0F;
  private static final int DEFAULT_DURATION = 250;
  private static final float END_TENSION = 1.0F;
  private static final int FLING_MODE = 1;
  private static final float INFLEXION = 0.35F;
  private static final int NB_SAMPLES = 100;
  private static final float P1 = 0.175F;
  private static final float P2 = 0.35000002F;
  private static final int SCROLL_MODE = 0;
  private static final float[] SPLINE_POSITION;
  private static final float[] SPLINE_TIME;
  private static final float START_TENSION = 0.5F;
  private static float sViscousFluidNormalize = 1.0F / viscousFluid(1.0F);
  private static float sViscousFluidScale;
  private float mCurrVelocity;
  private int mCurrX;
  private int mCurrY;
  private float mDeceleration;
  private float mDeltaX;
  private float mDeltaY;
  private int mDistance;
  private int mDuration;
  private float mDurationReciprocal;
  private int mFinalX;
  private int mFinalY;
  private boolean mFinished = true;
  private float mFlingFriction = ViewConfiguration.getScrollFriction();
  private boolean mFlywheel;
  private Interpolator mInterpolator;
  private int mMaxX;
  private int mMaxY;
  private int mMinX;
  private int mMinY;
  private int mMode;
  private float mPhysicalCoeff;
  private final float mPpi;
  private long mStartTime;
  private int mStartX;
  private int mStartY;
  private float mVelocity;
  
  static
  {
    float f2 = 0.0F;
    DECELERATION_RATE = (float)(Math.log(0.78D) / Math.log(0.9D));
    SPLINE_POSITION = new float[101];
    SPLINE_TIME = new float[101];
    int i = 0;
    float f1 = 0.0F;
    if (i < 100)
    {
      float f5 = i / 100.0F;
      float f3 = 1.0F;
      label55:
      float f4 = (f3 - f1) / 2.0F + f1;
      float f6 = 3.0F * f4 * (1.0F - f4);
      float f7 = ((1.0F - f4) * 0.175F + 0.35000002F * f4) * f6 + f4 * f4 * f4;
      if (Math.abs(f7 - f5) < 1.0E-5D)
      {
        SPLINE_POSITION[i] = (f4 * (f4 * f4) + f6 * ((1.0F - f4) * 0.5F + f4));
        f3 = 1.0F;
      }
      for (;;)
      {
        f4 = (f3 - f2) / 2.0F + f2;
        f6 = 3.0F * f4 * (1.0F - f4);
        f7 = ((1.0F - f4) * 0.5F + f4) * f6 + f4 * f4 * f4;
        if (Math.abs(f7 - f5) < 1.0E-5D)
        {
          SPLINE_TIME[i] = (f4 * (f4 * f4) + ((1.0F - f4) * 0.175F + 0.35000002F * f4) * f6);
          i += 1;
          break;
          if (f7 > f5)
          {
            f3 = f4;
            break label55;
          }
          f1 = f4;
          break label55;
        }
        if (f7 > f5) {
          f3 = f4;
        } else {
          f2 = f4;
        }
      }
    }
    float[] arrayOfFloat = SPLINE_POSITION;
    SPLINE_TIME[100] = 1.0F;
    arrayOfFloat[100] = 1.0F;
    sViscousFluidScale = 8.0F;
    sViscousFluidNormalize = 1.0F;
  }
  
  public Scroller(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public Scroller(Context paramContext, Interpolator paramInterpolator) {}
  
  public Scroller(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean)
  {
    mInterpolator = paramInterpolator;
    mPpi = (getResourcesgetDisplayMetricsdensity * 160.0F);
    mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
    mFlywheel = paramBoolean;
    mPhysicalCoeff = computeDeceleration(0.84F);
  }
  
  private float computeDeceleration(float paramFloat)
  {
    return 386.0878F * mPpi * paramFloat;
  }
  
  private double getSplineDeceleration(float paramFloat)
  {
    return Math.log(0.35F * Math.abs(paramFloat) / (mFlingFriction * mPhysicalCoeff));
  }
  
  private double getSplineFlingDistance(float paramFloat)
  {
    double d1 = getSplineDeceleration(paramFloat);
    double d2 = DECELERATION_RATE;
    double d3 = mFlingFriction * mPhysicalCoeff;
    return Math.exp(d1 * (DECELERATION_RATE / (d2 - 1.0D))) * d3;
  }
  
  private int getSplineFlingDuration(float paramFloat)
  {
    return (int)(Math.exp(getSplineDeceleration(paramFloat) / (DECELERATION_RATE - 1.0D)) * 1000.0D);
  }
  
  static float viscousFluid(float paramFloat)
  {
    paramFloat = sViscousFluidScale * paramFloat;
    if (paramFloat < 1.0F) {}
    for (paramFloat -= 1.0F - (float)Math.exp(-paramFloat);; paramFloat = (1.0F - (float)Math.exp(1.0F - paramFloat)) * (1.0F - 0.36787945F) + 0.36787945F) {
      return paramFloat * sViscousFluidNormalize;
    }
  }
  
  public void abortAnimation()
  {
    mCurrX = mFinalX;
    mCurrY = mFinalY;
    mFinished = true;
  }
  
  public boolean computeScrollOffset()
  {
    if (mFinished) {
      return false;
    }
    int i = (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
    if (i < mDuration) {
      switch (mMode)
      {
      }
    }
    for (;;)
    {
      return true;
      float f1 = i * mDurationReciprocal;
      if (mInterpolator == null) {}
      for (f1 = viscousFluid(f1);; f1 = mInterpolator.getInterpolation(f1))
      {
        mCurrX = (mStartX + Math.round(mDeltaX * f1));
        i = mStartY;
        mCurrY = (Math.round(f1 * mDeltaY) + i);
        break;
      }
      float f3 = i / mDuration;
      i = (int)(100.0F * f3);
      float f2 = 1.0F;
      f1 = 0.0F;
      if (i < 100)
      {
        f2 = i / 100.0F;
        f1 = (i + 1) / 100.0F;
        float f4 = SPLINE_POSITION[i];
        f1 = (SPLINE_POSITION[(i + 1)] - f4) / (f1 - f2);
        f2 = (f3 - f2) * f1 + f4;
      }
      mCurrVelocity = (f1 * mDistance / mDuration * 1000.0F);
      mCurrX = (mStartX + Math.round((mFinalX - mStartX) * f2));
      mCurrX = Math.min(mCurrX, mMaxX);
      mCurrX = Math.max(mCurrX, mMinX);
      mCurrY = (mStartY + Math.round(f2 * (mFinalY - mStartY)));
      mCurrY = Math.min(mCurrY, mMaxY);
      mCurrY = Math.max(mCurrY, mMinY);
      if ((mCurrX == mFinalX) && (mCurrY == mFinalY))
      {
        mFinished = true;
        continue;
        mCurrX = mFinalX;
        mCurrY = mFinalY;
        mFinished = true;
      }
    }
  }
  
  public void extendDuration(int paramInt)
  {
    mDuration = (timePassed() + paramInt);
    mDurationReciprocal = (1.0F / mDuration);
    mFinished = false;
  }
  
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    float f2 = 1.0F;
    int j = paramInt3;
    int i = paramInt4;
    float f1;
    if (mFlywheel)
    {
      j = paramInt3;
      i = paramInt4;
      if (!mFinished)
      {
        f1 = getCurrVelocity();
        float f5 = mFinalX - mStartX;
        f3 = mFinalY - mStartY;
        float f4 = FloatMath.sqrt(f5 * f5 + f3 * f3);
        f5 /= f4;
        f3 /= f4;
        f4 = f5 * f1;
        f1 *= f3;
        j = paramInt3;
        i = paramInt4;
        if (Math.signum(paramInt3) == Math.signum(f4))
        {
          j = paramInt3;
          i = paramInt4;
          if (Math.signum(paramInt4) == Math.signum(f1))
          {
            j = (int)(f4 + paramInt3);
            i = (int)(f1 + paramInt4);
          }
        }
      }
    }
    mMode = 1;
    mFinished = false;
    float f3 = FloatMath.sqrt(j * j + i * i);
    mVelocity = f3;
    mDuration = getSplineFlingDuration(f3);
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    mStartX = paramInt1;
    mStartY = paramInt2;
    if (f3 == 0.0F)
    {
      f1 = 1.0F;
      if (f3 != 0.0F) {
        break label392;
      }
    }
    for (;;)
    {
      double d = getSplineFlingDistance(f3);
      mDistance = ((int)(Math.signum(f3) * d));
      mMinX = paramInt5;
      mMaxX = paramInt6;
      mMinY = paramInt7;
      mMaxY = paramInt8;
      mFinalX = ((int)Math.round(f1 * d) + paramInt1);
      mFinalX = Math.min(mFinalX, mMaxX);
      mFinalX = Math.max(mFinalX, mMinX);
      mFinalY = ((int)Math.round(f2 * d) + paramInt2);
      mFinalY = Math.min(mFinalY, mMaxY);
      mFinalY = Math.max(mFinalY, mMinY);
      return;
      f1 = j / f3;
      break;
      label392:
      f2 = i / f3;
    }
  }
  
  public final void forceFinished(boolean paramBoolean)
  {
    mFinished = paramBoolean;
  }
  
  public float getCurrVelocity()
  {
    if (mMode == 1) {
      return mCurrVelocity;
    }
    return mVelocity - mDeceleration * timePassed() / 2000.0F;
  }
  
  public final int getCurrX()
  {
    return mCurrX;
  }
  
  public final int getCurrY()
  {
    return mCurrY;
  }
  
  public final int getDuration()
  {
    return mDuration;
  }
  
  public final int getFinalX()
  {
    return mFinalX;
  }
  
  public final int getFinalY()
  {
    return mFinalY;
  }
  
  public final int getStartX()
  {
    return mStartX;
  }
  
  public final int getStartY()
  {
    return mStartY;
  }
  
  public final boolean isFinished()
  {
    return mFinished;
  }
  
  public boolean isScrollingInDirection(float paramFloat1, float paramFloat2)
  {
    return (!mFinished) && (Math.signum(paramFloat1) == Math.signum(mFinalX - mStartX)) && (Math.signum(paramFloat2) == Math.signum(mFinalY - mStartY));
  }
  
  public void setFinalX(int paramInt)
  {
    mFinalX = paramInt;
    mDeltaX = (mFinalX - mStartX);
    mFinished = false;
  }
  
  public void setFinalY(int paramInt)
  {
    mFinalY = paramInt;
    mDeltaY = (mFinalY - mStartY);
    mFinished = false;
  }
  
  public final void setFriction(float paramFloat)
  {
    mDeceleration = computeDeceleration(paramFloat);
    mFlingFriction = paramFloat;
  }
  
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    startScroll(paramInt1, paramInt2, paramInt3, paramInt4, 250);
  }
  
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mMode = 0;
    mFinished = false;
    mDuration = paramInt5;
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    mStartX = paramInt1;
    mStartY = paramInt2;
    mFinalX = (paramInt1 + paramInt3);
    mFinalY = (paramInt2 + paramInt4);
    mDeltaX = paramInt3;
    mDeltaY = paramInt4;
    mDurationReciprocal = (1.0F / mDuration);
  }
  
  public int timePassed()
  {
    return (int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.Scroller
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */