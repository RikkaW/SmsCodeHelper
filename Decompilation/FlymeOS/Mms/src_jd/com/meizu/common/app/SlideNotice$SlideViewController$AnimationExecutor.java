package com.meizu.common.app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;

class SlideNotice$SlideViewController$AnimationExecutor
{
  private ValueAnimator anim;
  private SlideNotice.SlideViewController.SlidingAnimatorListener animatorListener;
  
  public SlideNotice$SlideViewController$AnimationExecutor(SlideNotice.SlideViewController paramSlideViewController)
  {
    animatorListener = new SlideNotice.SlideViewController.SlidingAnimatorListener(paramSlideViewController, null);
  }
  
  public void cancel()
  {
    if ((anim != null) && (anim.isRunning())) {
      anim.cancel();
    }
  }
  
  public boolean isRunning()
  {
    if (anim == null) {
      return false;
    }
    return anim.isRunning();
  }
  
  public void start(int paramInt1, int paramInt2)
  {
    if ((anim != null) && (anim.isRunning())) {
      anim.cancel();
    }
    anim = ObjectAnimator.ofInt(SlideNotice.access$1200(this$1.this$0), "height", new int[] { paramInt1, paramInt2 });
    anim.setDuration(300L);
    anim.addListener(animatorListener);
    anim.setInterpolator(new DecelerateInterpolator());
    anim.start();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.SlideViewController.AnimationExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */