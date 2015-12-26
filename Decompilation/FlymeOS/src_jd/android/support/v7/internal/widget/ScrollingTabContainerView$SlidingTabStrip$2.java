package android.support.v7.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ScrollingTabContainerView$SlidingTabStrip$2
  extends AnimatorListenerAdapter
{
  ScrollingTabContainerView$SlidingTabStrip$2(ScrollingTabContainerView.SlidingTabStrip paramSlidingTabStrip, int paramInt) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    ScrollingTabContainerView.SlidingTabStrip.access$902(this$1, val$position);
    ScrollingTabContainerView.SlidingTabStrip.access$1002(this$1, 0.0F);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    ScrollingTabContainerView.SlidingTabStrip.access$902(this$1, val$position);
    ScrollingTabContainerView.SlidingTabStrip.access$1002(this$1, 0.0F);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.SlidingTabStrip.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */