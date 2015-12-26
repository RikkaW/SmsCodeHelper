package android.support.v4.animation;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

class DonutAnimatorCompatProvider$DonutFloatValueAnimator
  implements ValueAnimatorCompat
{
  private long mDuration = 200L;
  private boolean mEnded = false;
  private float mFraction = 0.0F;
  List<AnimatorListenerCompat> mListeners = new ArrayList();
  private Runnable mLoopRunnable = new DonutAnimatorCompatProvider.DonutFloatValueAnimator.1(this);
  private long mStartTime;
  private boolean mStarted = false;
  View mTarget;
  List<AnimatorUpdateListenerCompat> mUpdateListeners = new ArrayList();
  
  private void dispatchCancel()
  {
    int i = mListeners.size() - 1;
    while (i >= 0)
    {
      ((AnimatorListenerCompat)mListeners.get(i)).onAnimationCancel(this);
      i -= 1;
    }
  }
  
  private void dispatchEnd()
  {
    int i = mListeners.size() - 1;
    while (i >= 0)
    {
      ((AnimatorListenerCompat)mListeners.get(i)).onAnimationEnd(this);
      i -= 1;
    }
  }
  
  private void dispatchStart()
  {
    int i = mListeners.size() - 1;
    while (i >= 0)
    {
      ((AnimatorListenerCompat)mListeners.get(i)).onAnimationStart(this);
      i -= 1;
    }
  }
  
  private long getTime()
  {
    return mTarget.getDrawingTime();
  }
  
  private void notifyUpdateListeners()
  {
    int i = mUpdateListeners.size() - 1;
    while (i >= 0)
    {
      ((AnimatorUpdateListenerCompat)mUpdateListeners.get(i)).onAnimationUpdate(this);
      i -= 1;
    }
  }
  
  public void addListener(AnimatorListenerCompat paramAnimatorListenerCompat)
  {
    mListeners.add(paramAnimatorListenerCompat);
  }
  
  public void addUpdateListener(AnimatorUpdateListenerCompat paramAnimatorUpdateListenerCompat)
  {
    mUpdateListeners.add(paramAnimatorUpdateListenerCompat);
  }
  
  public void cancel()
  {
    if (mEnded) {
      return;
    }
    mEnded = true;
    if (mStarted) {
      dispatchCancel();
    }
    dispatchEnd();
  }
  
  public float getAnimatedFraction()
  {
    return mFraction;
  }
  
  public void setDuration(long paramLong)
  {
    if (!mStarted) {
      mDuration = paramLong;
    }
  }
  
  public void setTarget(View paramView)
  {
    mTarget = paramView;
  }
  
  public void start()
  {
    if (mStarted) {
      return;
    }
    mStarted = true;
    dispatchStart();
    mFraction = 0.0F;
    mStartTime = getTime();
    mTarget.postDelayed(mLoopRunnable, 16L);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.animation.DonutAnimatorCompatProvider.DonutFloatValueAnimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */