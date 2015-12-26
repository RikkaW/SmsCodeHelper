package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class StretchSearchView$9
  implements Animator.AnimatorListener
{
  StretchSearchView$9(StretchSearchView paramStretchSearchView) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    this$0.afterStretchViewState();
    StretchSearchView.access$202(this$0, 2);
    if (StretchSearchView.access$800(this$0) != null) {
      StretchSearchView.access$800(this$0).onStetchAnimationEnd(StretchSearchView.access$900(this$0));
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator)
  {
    if (StretchSearchView.access$800(this$0) != null) {
      StretchSearchView.access$800(this$0).onStetchAnimationStart(StretchSearchView.access$900(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */