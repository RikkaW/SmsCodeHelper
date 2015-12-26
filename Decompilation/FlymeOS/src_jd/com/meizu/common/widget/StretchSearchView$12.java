package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class StretchSearchView$12
  implements Animator.AnimatorListener
{
  StretchSearchView$12(StretchSearchView paramStretchSearchView) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    this$0.afterShortenViewState();
    StretchSearchView.access$202(this$0, 4);
    if (StretchSearchView.access$1000(this$0) != null) {
      StretchSearchView.access$1000(this$0).onShortenAnimationEnd(StretchSearchView.access$900(this$0));
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator)
  {
    if (StretchSearchView.access$1000(this$0) != null) {
      StretchSearchView.access$1000(this$0).onShortenAnimationStart(StretchSearchView.access$900(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */