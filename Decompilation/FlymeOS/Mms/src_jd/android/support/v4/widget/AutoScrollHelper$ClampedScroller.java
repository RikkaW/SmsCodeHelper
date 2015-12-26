package android.support.v4.widget;

import android.view.animation.AnimationUtils;

class AutoScrollHelper$ClampedScroller
{
  private long mDeltaTime = 0L;
  private int mDeltaX = 0;
  private int mDeltaY = 0;
  private int mEffectiveRampDown;
  private int mRampDownDuration;
  private int mRampUpDuration;
  private long mStartTime = Long.MIN_VALUE;
  private long mStopTime = -1L;
  private float mStopValue;
  private float mTargetVelocityX;
  private float mTargetVelocityY;
  
  private float getValueAt(long paramLong)
  {
    if (paramLong < mStartTime) {
      return 0.0F;
    }
    if ((mStopTime < 0L) || (paramLong < mStopTime)) {
      return AutoScrollHelper.access$900((float)(paramLong - mStartTime) / mRampUpDuration, 0.0F, 1.0F) * 0.5F;
    }
    long l = mStopTime;
    float f1 = mStopValue;
    float f2 = mStopValue;
    return AutoScrollHelper.access$900((float)(paramLong - l) / mEffectiveRampDown, 0.0F, 1.0F) * f2 + (1.0F - f1);
  }
  
  private float interpolateValue(float paramFloat)
  {
    return -4.0F * paramFloat * paramFloat + 4.0F * paramFloat;
  }
  
  public void computeScrollDelta()
  {
    if (mDeltaTime == 0L) {
      throw new RuntimeException("Cannot compute scroll delta before calling start()");
    }
    long l1 = AnimationUtils.currentAnimationTimeMillis();
    float f = interpolateValue(getValueAt(l1));
    long l2 = l1 - mDeltaTime;
    mDeltaTime = l1;
    mDeltaX = ((int)((float)l2 * f * mTargetVelocityX));
    mDeltaY = ((int)((float)l2 * f * mTargetVelocityY));
  }
  
  public int getDeltaX()
  {
    return mDeltaX;
  }
  
  public int getDeltaY()
  {
    return mDeltaY;
  }
  
  public int getHorizontalDirection()
  {
    return (int)(mTargetVelocityX / Math.abs(mTargetVelocityX));
  }
  
  public int getVerticalDirection()
  {
    return (int)(mTargetVelocityY / Math.abs(mTargetVelocityY));
  }
  
  public boolean isFinished()
  {
    return (mStopTime > 0L) && (AnimationUtils.currentAnimationTimeMillis() > mStopTime + mEffectiveRampDown);
  }
  
  public void requestStop()
  {
    long l = AnimationUtils.currentAnimationTimeMillis();
    mEffectiveRampDown = AutoScrollHelper.access$800((int)(l - mStartTime), 0, mRampDownDuration);
    mStopValue = getValueAt(l);
    mStopTime = l;
  }
  
  public void setRampDownDuration(int paramInt)
  {
    mRampDownDuration = paramInt;
  }
  
  public void setRampUpDuration(int paramInt)
  {
    mRampUpDuration = paramInt;
  }
  
  public void setTargetVelocity(float paramFloat1, float paramFloat2)
  {
    mTargetVelocityX = paramFloat1;
    mTargetVelocityY = paramFloat2;
  }
  
  public void start()
  {
    mStartTime = AnimationUtils.currentAnimationTimeMillis();
    mStopTime = -1L;
    mDeltaTime = mStartTime;
    mStopValue = 0.5F;
    mDeltaX = 0;
    mDeltaY = 0;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.AutoScrollHelper.ClampedScroller
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */