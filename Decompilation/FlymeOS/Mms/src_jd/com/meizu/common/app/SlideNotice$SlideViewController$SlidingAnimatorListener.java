package com.meizu.common.app;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class SlideNotice$SlideViewController$SlidingAnimatorListener
  implements Animator.AnimatorListener
{
  private SlideNotice$SlideViewController$SlidingAnimatorListener(SlideNotice.SlideViewController paramSlideViewController) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (SlideNotice.SlideViewController.access$1000(this$1) == 0) {}
    while (SlideNotice.SlideViewController.access$1400(this$1) > 0) {
      return;
    }
    SlideNotice.SlideViewController.access$1402(this$1, -1);
    SlideNotice.SlideViewController.access$000(SlideNotice.access$1200(this$1.this$0));
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator)
  {
    SlideNotice.SlideViewController.access$1302(SlideNotice.access$1200(this$1.this$0), true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNotice.SlideViewController.SlidingAnimatorListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */