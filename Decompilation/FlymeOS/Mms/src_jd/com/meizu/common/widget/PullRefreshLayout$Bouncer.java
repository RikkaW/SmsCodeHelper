package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;

class PullRefreshLayout$Bouncer
  implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener
{
  private ValueAnimator mAnimator;
  private boolean mCanceled;
  private int mLastOffset;
  
  private PullRefreshLayout$Bouncer(PullRefreshLayout paramPullRefreshLayout) {}
  
  public void cancel()
  {
    if ((mAnimator != null) && (mAnimator.isRunning()))
    {
      mAnimator.cancel();
      PullRefreshLayout.access$302(this$0, false);
    }
    mAnimator = null;
  }
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    mCanceled = true;
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    mAnimator = null;
    if (!mCanceled)
    {
      if (PullRefreshLayout.access$700(this$0) != PullRefreshLayout.ScrollState.STATE_LINE_END) {
        break label76;
      }
      PullRefreshLayout.access$800(this$0).setStartTime();
      PullRefreshLayout.access$902(this$0, true);
      if (PullRefreshLayout.access$1000(this$0) != null) {
        PullRefreshLayout.access$1000(this$0).startGetData();
      }
    }
    for (;;)
    {
      PullRefreshLayout.access$302(this$0, false);
      return;
      label76:
      if (PullRefreshLayout.access$700(this$0) == PullRefreshLayout.ScrollState.STATE_ARC_END)
      {
        PullRefreshLayout.access$800(this$0).setStartTime();
        PullRefreshLayout.access$902(this$0, true);
        if (PullRefreshLayout.access$1000(this$0) != null) {
          PullRefreshLayout.access$1000(this$0).startGetData();
        }
      }
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
    int j = mLastOffset;
    PullRefreshLayout.access$400(this$0, j - i);
    mLastOffset = i;
    PullRefreshLayout.access$502(this$0, PullRefreshLayout.access$600(this$0).getTop());
  }
  
  public void recover(int paramInt)
  {
    cancel();
    mCanceled = false;
    int i = Math.abs(paramInt * 400 / PullRefreshLayout.access$100(this$0));
    mAnimator = new ValueAnimator();
    mAnimator.setIntValues(new int[] { 0, paramInt });
    mLastOffset = 0;
    mAnimator.setDuration(i);
    mAnimator.setRepeatCount(0);
    if (PullRefreshLayout.access$200(this$0) == null)
    {
      if (Build.VERSION.SDK_INT >= 21) {
        break label158;
      }
      PullRefreshLayout.access$202(this$0, new DecelerateInterpolator());
    }
    for (;;)
    {
      mAnimator.setInterpolator(PullRefreshLayout.access$200(this$0));
      mAnimator.addListener(this);
      mAnimator.addUpdateListener(this);
      mAnimator.start();
      PullRefreshLayout.access$302(this$0, true);
      return;
      label158:
      PullRefreshLayout.access$202(this$0, new PathInterpolator(0.33F, 0.0F, 0.33F, 1.0F));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PullRefreshLayout.Bouncer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */