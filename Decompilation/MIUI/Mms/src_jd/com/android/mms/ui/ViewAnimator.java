package com.android.mms.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewAnimator
{
  private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener()
  {
    public void onAnimationCancel(Animator paramAnonymousAnimator) {}
    
    public void onAnimationEnd(Animator paramAnonymousAnimator)
    {
      ViewAnimator.access$002(ViewAnimator.this, 0);
      mIsRunning.set(false);
      paramAnonymousAnimator.removeAllListeners();
      mView.invalidate();
      if ((mView instanceof ConversationListItem)) {
        ((ConversationListItem)mView).onAnimationDone();
      }
    }
    
    public void onAnimationRepeat(Animator paramAnonymousAnimator) {}
    
    public void onAnimationStart(Animator paramAnonymousAnimator) {}
  };
  private ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener()
  {
    public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
    {
      ViewAnimator.access$002(ViewAnimator.this, Integer.parseInt(paramAnonymousValueAnimator.getAnimatedValue("EXTRA_POSITION").toString()));
      mView.invalidate();
    }
  };
  private AtomicBoolean mIsRunning = new AtomicBoolean(false);
  private int mPosition;
  private final View mView;
  
  public ViewAnimator(View paramView)
  {
    mView = paramView;
  }
  
  public void animate(Interpolator paramInterpolator, long paramLong, int... paramVarArgs)
  {
    paramVarArgs = ObjectAnimator.ofInt(this, "EXTRA_POSITION", paramVarArgs);
    paramVarArgs.setDuration(paramLong);
    paramVarArgs.setInterpolator(paramInterpolator);
    paramVarArgs.addUpdateListener(mAnimatorUpdateListener);
    paramVarArgs.addListener(mAnimatorListener);
    paramVarArgs.start();
    mIsRunning.set(true);
  }
  
  public int getPosition()
  {
    return mPosition;
  }
  
  public boolean isRunning()
  {
    return mIsRunning.get();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ViewAnimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */