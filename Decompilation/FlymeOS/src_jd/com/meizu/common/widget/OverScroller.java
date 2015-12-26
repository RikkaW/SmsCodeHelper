package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class OverScroller
{
  private static final int DEFAULT_DURATION = 250;
  private static final int FLING_MODE = 1;
  private static final int SCROLL_MODE = 0;
  private final boolean mFlywheel;
  private Interpolator mInterpolator;
  private int mMode;
  private final SplineOverScroller mScrollerX;
  private final SplineOverScroller mScrollerY;
  
  public OverScroller(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public OverScroller(Context paramContext, Interpolator paramInterpolator)
  {
    this(paramContext, paramInterpolator, true);
  }
  
  public OverScroller(Context paramContext, Interpolator paramInterpolator, float paramFloat1, float paramFloat2)
  {
    this(paramContext, paramInterpolator, true);
  }
  
  public OverScroller(Context paramContext, Interpolator paramInterpolator, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    this(paramContext, paramInterpolator, paramBoolean);
  }
  
  public OverScroller(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean)
  {
    mInterpolator = paramInterpolator;
    mFlywheel = paramBoolean;
    mScrollerX = new SplineOverScroller(paramContext);
    mScrollerY = new SplineOverScroller(paramContext);
  }
  
  public void abortAnimation()
  {
    mScrollerX.finish();
    mScrollerY.finish();
  }
  
  public boolean computeScrollOffset()
  {
    if (isFinished()) {
      return false;
    }
    switch (mMode)
    {
    }
    for (;;)
    {
      return true;
      long l = AnimationUtils.currentAnimationTimeMillis() - mScrollerX.mStartTime;
      int i = mScrollerX.mDuration;
      if (l < i)
      {
        float f = (float)l / i;
        if (mInterpolator == null) {}
        for (f = Scroller.viscousFluid(f);; f = mInterpolator.getInterpolation(f))
        {
          mScrollerX.updateScroll(f);
          mScrollerY.updateScroll(f);
          break;
        }
      }
      abortAnimation();
      continue;
      if ((!mScrollerX.mFinished) && (!mScrollerX.update()) && (!mScrollerX.continueWhenFinished())) {
        mScrollerX.finish();
      }
      if ((!mScrollerY.mFinished) && (!mScrollerY.update()) && (!mScrollerY.continueWhenFinished())) {
        mScrollerY.finish();
      }
    }
  }
  
  @Deprecated
  public void extendDuration(int paramInt)
  {
    mScrollerX.extendDuration(paramInt);
    mScrollerY.extendDuration(paramInt);
  }
  
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    fling(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, 0, 0);
  }
  
  public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10)
  {
    if ((mFlywheel) && (!isFinished()))
    {
      float f1 = mScrollerX.mCurrVelocity;
      float f2 = mScrollerY.mCurrVelocity;
      if ((Math.signum(paramInt3) == Math.signum(f1)) && (Math.signum(paramInt4) == Math.signum(f2)))
      {
        paramInt3 = (int)(f1 + paramInt3);
        paramInt4 = (int)(paramInt4 + f2);
      }
    }
    for (;;)
    {
      mMode = 1;
      mScrollerX.fling(paramInt1, paramInt3, paramInt5, paramInt6, paramInt9);
      mScrollerY.fling(paramInt2, paramInt4, paramInt7, paramInt8, paramInt10);
      return;
    }
  }
  
  public final void forceFinished(boolean paramBoolean)
  {
    SplineOverScroller.access$002(mScrollerX, SplineOverScroller.access$002(mScrollerY, paramBoolean));
  }
  
  public float getCurrVelocity()
  {
    return FloatMath.sqrt(mScrollerX.mCurrVelocity * mScrollerX.mCurrVelocity + mScrollerY.mCurrVelocity * mScrollerY.mCurrVelocity);
  }
  
  public final int getCurrX()
  {
    return mScrollerX.mCurrentPosition;
  }
  
  public final int getCurrY()
  {
    return mScrollerY.mCurrentPosition;
  }
  
  @Deprecated
  public final int getDuration()
  {
    return Math.max(mScrollerX.mDuration, mScrollerY.mDuration);
  }
  
  public final int getFinalX()
  {
    return mScrollerX.mFinal;
  }
  
  public final int getFinalY()
  {
    return mScrollerY.mFinal;
  }
  
  public final int getStartX()
  {
    return mScrollerX.mStart;
  }
  
  public final int getStartY()
  {
    return mScrollerY.mStart;
  }
  
  public final boolean isFinished()
  {
    return (mScrollerX.mFinished) && (mScrollerY.mFinished);
  }
  
  public boolean isOverScrolled()
  {
    return ((!mScrollerX.mFinished) && (mScrollerX.mState != 0)) || ((!mScrollerY.mFinished) && (mScrollerY.mState != 0));
  }
  
  public boolean isScrollingInDirection(float paramFloat1, float paramFloat2)
  {
    int i = mScrollerX.mFinal;
    int j = mScrollerX.mStart;
    int k = mScrollerY.mFinal;
    int m = mScrollerY.mStart;
    return (!isFinished()) && (Math.signum(paramFloat1) == Math.signum(i - j)) && (Math.signum(paramFloat2) == Math.signum(k - m));
  }
  
  public void notifyHorizontalEdgeReached(int paramInt1, int paramInt2, int paramInt3)
  {
    mScrollerX.notifyEdgeReached(paramInt1, paramInt2, paramInt3);
  }
  
  public void notifyVerticalEdgeReached(int paramInt1, int paramInt2, int paramInt3)
  {
    mScrollerY.notifyEdgeReached(paramInt1, paramInt2, paramInt3);
  }
  
  public void setEnableMZOverScroll(boolean paramBoolean1, boolean paramBoolean2)
  {
    mScrollerX.setEnableMZOverScroll(paramBoolean1, paramBoolean2);
    mScrollerY.setEnableMZOverScroll(paramBoolean1, paramBoolean2);
  }
  
  @Deprecated
  public void setFinalX(int paramInt)
  {
    mScrollerX.setFinalPosition(paramInt);
  }
  
  @Deprecated
  public void setFinalY(int paramInt)
  {
    mScrollerY.setFinalPosition(paramInt);
  }
  
  public final void setFriction(float paramFloat)
  {
    mScrollerX.setFriction(paramFloat);
    mScrollerY.setFriction(paramFloat);
  }
  
  void setInterpolator(Interpolator paramInterpolator)
  {
    mInterpolator = paramInterpolator;
  }
  
  public boolean springBack(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    mMode = 1;
    boolean bool1 = mScrollerX.springback(paramInt1, paramInt3, paramInt4);
    boolean bool2 = mScrollerY.springback(paramInt2, paramInt5, paramInt6);
    return (bool1) || (bool2);
  }
  
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    startScroll(paramInt1, paramInt2, paramInt3, paramInt4, 250);
  }
  
  public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mMode = 0;
    mScrollerX.startScroll(paramInt1, paramInt3, paramInt5);
    mScrollerY.startScroll(paramInt2, paramInt4, paramInt5);
  }
  
  public int timePassed()
  {
    return (int)(AnimationUtils.currentAnimationTimeMillis() - Math.min(mScrollerX.mStartTime, mScrollerY.mStartTime));
  }
  
  static class SplineOverScroller
  {
    private static final int BALLISTIC = 2;
    private static final int CUBIC = 1;
    private static final int DECELERATIONSPEED_FAST = 50;
    private static final int DECELERATIONSPEED_SLOW = 25;
    private static float DECELERATION_RATE = 0.0F;
    private static final float END_TENSION = 1.0F;
    private static final float GRAVITY = 2000.0F;
    private static final float INFLEXION = 0.35F;
    private static final int MAXFLINGTESTCOUNT = 4;
    private static final int MAXUPDATECOUNT = 5;
    private static final int NB_SAMPLES = 100;
    private static final int OVERSCROLL_SPRINGBACK_DURATION = 618;
    private static final float P1 = 0.175F;
    private static final float P2 = 0.35000002F;
    private static final int SPLINE = 0;
    private static final float[] SPLINE_POSITION;
    private static final float[] SPLINE_TIME;
    private static final float START_TENSION = 0.5F;
    private static final String tag = "OverScroller";
    private long mAverageTime = 0L;
    private float mCoeffDeceleration = 0.0F;
    private float mCurrVelocity;
    private int mCurrentPosition;
    private float mDeceleration;
    private int mDecelerationSpeed = 50;
    private float mDelta = 0.0F;
    private int mDuration;
    private boolean mEnableOverScrollForMz = false;
    private int mFinal;
    private boolean mFinished = true;
    private float mFlingFriction = ViewConfiguration.getScrollFriction();
    private int mFlingTestCount = 1;
    private int mIterateCount = 0;
    private float mLastDistance = 0.0F;
    private int mOver;
    private float mPhysicalCoeff;
    private boolean mSmoothFling = false;
    private int mSplineDistance;
    private int mSplineDuration;
    private int mSpringDistance = 0;
    private int mSpringbackEnd = 0;
    private int mStart;
    private long mStartTime;
    private int mState = 0;
    private int mUpdateCount = 0;
    private int mVelocity;
    
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
    }
    
    SplineOverScroller(Context paramContext)
    {
      mPhysicalCoeff = (getResourcesgetDisplayMetricsdensity * 160.0F * 386.0878F * 0.84F);
      mFlingTestCount = 0;
      mAverageTime = 0L;
      mSmoothFling = false;
    }
    
    private void adjustDuration(int paramInt1, int paramInt2, int paramInt3)
    {
      float f1 = Math.abs((paramInt3 - paramInt1) / (paramInt2 - paramInt1));
      paramInt1 = (int)(100.0F * f1);
      if (paramInt1 < 100)
      {
        float f2 = paramInt1 / 100.0F;
        float f3 = (paramInt1 + 1) / 100.0F;
        float f4 = SPLINE_TIME[paramInt1];
        float f5 = SPLINE_TIME[(paramInt1 + 1)];
        mDuration = ((int)(((f1 - f2) / (f3 - f2) * (f5 - f4) + f4) * mDuration));
      }
    }
    
    private void fitOnBounceCurve(int paramInt1, int paramInt2, int paramInt3)
    {
      float f1 = -paramInt3 / mDeceleration;
      float f2 = (float)Math.sqrt((paramInt3 * paramInt3 / 2.0F / Math.abs(mDeceleration) + Math.abs(paramInt2 - paramInt1)) * 2.0D / Math.abs(mDeceleration));
      mStartTime -= (int)((f2 - f1) * 1000.0F);
      mStart = paramInt2;
      mVelocity = ((int)(-mDeceleration * f2));
    }
    
    private static float getDeceleration(int paramInt)
    {
      if (paramInt > 0) {
        return -2000.0F;
      }
      return 2000.0F;
    }
    
    private double getSplineDeceleration(int paramInt)
    {
      return Math.log(0.35F * Math.abs(paramInt) / (mFlingFriction * mPhysicalCoeff));
    }
    
    private double getSplineFlingDistance(int paramInt)
    {
      double d1 = getSplineDeceleration(paramInt);
      double d2 = DECELERATION_RATE;
      double d3 = mFlingFriction * mPhysicalCoeff;
      return Math.exp(d1 * (DECELERATION_RATE / (d2 - 1.0D))) * d3;
    }
    
    private int getSplineFlingDuration(int paramInt)
    {
      return (int)(Math.exp(getSplineDeceleration(paramInt) / (DECELERATION_RATE - 1.0D)) * 1000.0D);
    }
    
    private void onEdgeReached()
    {
      float f2 = mVelocity * mVelocity / (Math.abs(mDeceleration) * 2.0F);
      float f1;
      if (!mEnableOverScrollForMz)
      {
        float f3 = Math.signum(mVelocity);
        f1 = f2;
        if (f2 > mOver)
        {
          mDeceleration = (-f3 * mVelocity * mVelocity / (mOver * 2.0F));
          f1 = mOver;
        }
        i = mStart;
        if (mVelocity > 0) {}
        for (f2 = f1;; f2 = -f1)
        {
          mFinal = ((int)f2 + i);
          mDuration = (-(int)(1000.0F * mVelocity / mDeceleration));
          mOver = ((int)f1);
          mState = 2;
          return;
        }
      }
      mCoeffDeceleration = 0.5F;
      mLastDistance = 0.0F;
      mFinished = false;
      mDuration = Integer.MAX_VALUE;
      mDelta = (mCurrVelocity / 150.0F);
      int i = 0;
      for (;;)
      {
        if ((int)(mDelta * Math.pow(mCoeffDeceleration, i)) == 0)
        {
          mIterateCount = i;
          double d = mDelta;
          f1 = (float)((1.0D - Math.pow(mCoeffDeceleration, i)) * d / (1.0F - mCoeffDeceleration));
          mFinal = ((int)(mStart + f1));
          break;
        }
        i += 1;
      }
    }
    
    private int quintic(long paramLong1, int paramInt1, int paramInt2, long paramLong2)
    {
      float f = (float)paramLong1 * 1.0F / (float)paramLong2;
      double d = paramInt2;
      return (int)Math.round((Math.pow(f - 1.0F, 5.0D) + 1.0D) * d);
    }
    
    private void startAfterEdge(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      int k = 1;
      if ((paramInt1 > paramInt2) && (paramInt1 < paramInt3))
      {
        Log.e("OverScroller", "startAfterEdge called from a valid position");
        mFinished = true;
        return;
      }
      int i;
      int j;
      label43:
      int m;
      if (paramInt1 > paramInt3)
      {
        i = 1;
        if (i == 0) {
          break label78;
        }
        j = paramInt3;
        m = paramInt1 - j;
        if (m * paramInt4 < 0) {
          break label84;
        }
      }
      for (;;)
      {
        if (k == 0) {
          break label90;
        }
        startBounceAfterEdge(paramInt1, j, paramInt4);
        return;
        i = 0;
        break;
        label78:
        j = paramInt2;
        break label43;
        label84:
        k = 0;
      }
      label90:
      if (getSplineFlingDistance(paramInt4) > Math.abs(m))
      {
        if (i != 0)
        {
          if (i == 0) {
            break label137;
          }
          paramInt3 = paramInt1;
        }
        label137:
        for (;;)
        {
          fling(paramInt1, paramInt4, paramInt2, paramInt3, mOver);
          return;
          paramInt2 = paramInt1;
          break;
        }
      }
      startSpringback(paramInt1, j, paramInt4);
    }
    
    private void startBounceAfterEdge(int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt3 == 0) {}
      for (int i = paramInt1 - paramInt2;; i = paramInt3)
      {
        mDeceleration = getDeceleration(i);
        fitOnBounceCurve(paramInt1, paramInt2, paramInt3);
        onEdgeReached();
        return;
      }
    }
    
    private void startSpringback(int paramInt1, int paramInt2, int paramInt3)
    {
      mFinished = false;
      mState = 1;
      mStart = paramInt1;
      mFinal = paramInt2;
      paramInt1 -= paramInt2;
      mDeceleration = getDeceleration(paramInt1);
      mVelocity = (-paramInt1);
      mOver = Math.abs(paramInt1);
      if (mEnableOverScrollForMz)
      {
        mDuration = 618;
        return;
      }
      mDuration = ((int)(Math.sqrt(paramInt1 * -2.0D / mDeceleration) * 1000.0D));
    }
    
    boolean continueWhenFinished()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      switch (mState)
      {
      default: 
      case 1: 
      case 0: 
        for (;;)
        {
          update();
          bool1 = true;
          do
          {
            return bool1;
            bool1 = bool2;
          } while (mDuration >= mSplineDuration);
          mStart = mFinal;
          mVelocity = ((int)mCurrVelocity);
          mDeceleration = getDeceleration(mVelocity);
          mStartTime += mDuration;
          onEdgeReached();
        }
      }
      if (mEnableOverScrollForMz) {}
      for (mStartTime = AnimationUtils.currentAnimationTimeMillis();; mStartTime += mDuration)
      {
        startSpringback(mFinal, mStart, 0);
        break;
      }
    }
    
    void extendDuration(int paramInt)
    {
      mDuration = ((int)(AnimationUtils.currentAnimationTimeMillis() - mStartTime) + paramInt);
      mFinished = false;
    }
    
    void finish()
    {
      mCurrentPosition = mFinal;
      mFinished = true;
    }
    
    void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      mOver = paramInt5;
      mFinished = false;
      mVelocity = paramInt2;
      mCurrVelocity = paramInt2;
      mSplineDuration = 0;
      mDuration = 0;
      mStartTime = AnimationUtils.currentAnimationTimeMillis();
      mStart = paramInt1;
      mCurrentPosition = paramInt1;
      if ((paramInt1 > paramInt4) || (paramInt1 < paramInt3)) {
        startAfterEdge(paramInt1, paramInt3, paramInt4, paramInt2);
      }
      label222:
      label322:
      label329:
      do
      {
        return;
        mState = 0;
        double d = 0.0D;
        if (paramInt2 != 0)
        {
          paramInt5 = getSplineFlingDuration(paramInt2);
          mSplineDuration = paramInt5;
          mDuration = paramInt5;
          d = getSplineFlingDistance(paramInt2);
        }
        if ((mEnableOverScrollForMz) && (mFlingTestCount >= 4) && (mAverageTime > 40L)) {
          mSmoothFling = false;
        }
        mFlingTestCount += 1;
        mUpdateCount = 0;
        if (mSmoothFling)
        {
          if (mAverageTime < 20L)
          {
            mDecelerationSpeed = 50;
            paramInt2 = Math.abs(mVelocity);
            mIterateCount = 0;
            mLastDistance = 0.0F;
            mCoeffDeceleration = 0.97F;
            paramInt1 = 0;
            mDelta = (1.0F * mVelocity / mDecelerationSpeed);
            if ((int)(mDelta * Math.pow(mCoeffDeceleration, paramInt1)) != 0) {
              break label322;
            }
            mIterateCount = paramInt1;
            d = mDelta * (1.0D - Math.pow(mCoeffDeceleration, paramInt1)) / (1.0F - mCoeffDeceleration);
            if (paramInt2 <= 2000) {
              break label329;
            }
            mDuration = 5000;
          }
          for (;;)
          {
            mFinal = (mStart + (int)d);
            return;
            if (mAverageTime >= 40L) {
              break;
            }
            mDecelerationSpeed = 25;
            break;
            paramInt1 += 1;
            break label222;
            if (paramInt2 < 200) {
              mDuration = 0;
            } else {
              mDuration = 3000;
            }
          }
        }
        mSplineDistance = ((int)(d * Math.signum(paramInt2)));
        mFinal = (mSplineDistance + paramInt1);
        if (mFinal < paramInt3)
        {
          adjustDuration(mStart, mFinal, paramInt3);
          mFinal = paramInt3;
        }
      } while (mFinal <= paramInt4);
      adjustDuration(mStart, mFinal, paramInt4);
      mFinal = paramInt4;
    }
    
    void notifyEdgeReached(int paramInt1, int paramInt2, int paramInt3)
    {
      if (mState == 0)
      {
        mOver = paramInt3;
        mStartTime = AnimationUtils.currentAnimationTimeMillis();
        startAfterEdge(paramInt1, paramInt2, paramInt2, (int)mCurrVelocity);
      }
    }
    
    public void setEnableMZOverScroll(boolean paramBoolean1, boolean paramBoolean2)
    {
      mEnableOverScrollForMz = paramBoolean1;
      mSmoothFling = paramBoolean2;
    }
    
    void setFinalPosition(int paramInt)
    {
      mFinal = paramInt;
      mFinished = false;
    }
    
    void setFriction(float paramFloat)
    {
      mFlingFriction = paramFloat;
    }
    
    boolean springback(int paramInt1, int paramInt2, int paramInt3)
    {
      mFinished = true;
      mFinal = paramInt1;
      mStart = paramInt1;
      mVelocity = 0;
      mStartTime = AnimationUtils.currentAnimationTimeMillis();
      mDuration = 0;
      if (paramInt1 < paramInt2) {
        startSpringback(paramInt1, paramInt2, 0);
      }
      while (!mFinished)
      {
        return true;
        if (paramInt1 > paramInt3) {
          startSpringback(paramInt1, paramInt3, 0);
        }
      }
      return false;
    }
    
    void startScroll(int paramInt1, int paramInt2, int paramInt3)
    {
      mFinished = false;
      mStart = paramInt1;
      mFinal = (paramInt1 + paramInt2);
      mStartTime = AnimationUtils.currentAnimationTimeMillis();
      mDuration = paramInt3;
      mDeceleration = 0.0F;
      mVelocity = 0;
    }
    
    boolean update()
    {
      long l = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
      if (l > mDuration)
      {
        if (mEnableOverScrollForMz)
        {
          if (!mSmoothFling) {
            break label57;
          }
          if (mIterateCount != 0) {
            mFinal = mCurrentPosition;
          }
        }
        label57:
        while ((mDuration >= mSplineDuration) || (mFinal == mCurrentPosition))
        {
          mFinished = true;
          return false;
        }
        mCurrentPosition = mFinal;
        return true;
      }
      double d = 0.0D;
      switch (mState)
      {
      }
      while (mEnableOverScrollForMz) {
        if ((mState == 0) && (!mSmoothFling))
        {
          i = mStart;
          mCurrentPosition = ((int)Math.round(d) + i);
          return true;
          float f3;
          float f2;
          float f1;
          if (!mSmoothFling)
          {
            f3 = (float)l / mSplineDuration;
            i = (int)(100.0F * f3);
            f2 = 1.0F;
            f1 = 0.0F;
            if (i < 100)
            {
              f2 = i / 100.0F;
              f1 = (i + 1) / 100.0F;
              float f4 = SPLINE_POSITION[i];
              f1 = (SPLINE_POSITION[(i + 1)] - f4) / (f1 - f2);
              f2 = (f3 - f2) * f1 + f4;
            }
            d = f2 * mSplineDistance;
            mCurrVelocity = (f1 * mSplineDistance / mSplineDuration * 1000.0F);
          }
          else
          {
            mUpdateCount += 1;
            if ((mEnableOverScrollForMz) && (mUpdateCount == 5)) {
              mAverageTime = ((mAverageTime + l / mUpdateCount) / 2L);
            }
            mCurrVelocity *= mCoeffDeceleration;
            d = mLastDistance + mDelta;
            mDelta *= mCoeffDeceleration;
            mLastDistance = ((float)d);
            continue;
            if (!mEnableOverScrollForMz)
            {
              f1 = (float)l / 1000.0F;
              mCurrVelocity = (mVelocity + mDeceleration * f1);
              f2 = mVelocity;
              d = f1 * (mDeceleration * f1) / 2.0F + f2 * f1;
            }
            else
            {
              mCurrVelocity *= mCoeffDeceleration;
              d = mLastDistance + mDelta;
              mDelta *= mCoeffDeceleration;
              mLastDistance = ((float)d);
              continue;
              f1 = (float)l / mDuration;
              f2 = f1 * f1;
              f3 = Math.signum(mVelocity);
              if (mEnableOverScrollForMz)
              {
                d = quintic(l, mStart, mOver, mDuration) * f3;
              }
              else
              {
                d = mOver * f3 * (3.0F * f2 - 2.0F * f1 * f2);
                mCurrVelocity = (mOver * f3 * 6.0F * (-f1 + f2));
              }
            }
          }
        }
        else
        {
          i = mStart;
          mCurrentPosition = ((int)d + i);
          if (mCurrentPosition != mFinal) {
            break label635;
          }
          return false;
        }
      }
      int i = mStart;
      mCurrentPosition = ((int)Math.round(d) + i);
      label635:
      return true;
    }
    
    void updateScroll(float paramFloat)
    {
      mCurrentPosition = (mStart + Math.round((mFinal - mStart) * paramFloat));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.OverScroller
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */