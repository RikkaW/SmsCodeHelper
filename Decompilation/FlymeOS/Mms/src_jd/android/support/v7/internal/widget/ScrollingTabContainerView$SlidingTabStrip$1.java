package android.support.v7.internal.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.v7.widget.AnimationUtils;

class ScrollingTabContainerView$SlidingTabStrip$1
  implements ValueAnimator.AnimatorUpdateListener
{
  ScrollingTabContainerView$SlidingTabStrip$1(ScrollingTabContainerView.SlidingTabStrip paramSlidingTabStrip, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = paramValueAnimator.getAnimatedFraction();
    ScrollingTabContainerView.SlidingTabStrip.access$800(this$1, AnimationUtils.lerp(val$startLeft, val$targetLeft, f), AnimationUtils.lerp(val$startRight, val$targetRight, f));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.SlidingTabStrip.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */