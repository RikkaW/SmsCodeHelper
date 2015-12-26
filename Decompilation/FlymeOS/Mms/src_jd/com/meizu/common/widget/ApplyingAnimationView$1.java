package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

class ApplyingAnimationView$1
  extends AnimatorListenerAdapter
{
  ApplyingAnimationView$1(ApplyingAnimationView paramApplyingAnimationView) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if ((!ApplyingAnimationView.access$000(this$0)) && (ApplyingAnimationView.access$100(this$0) != null)) {
      ApplyingAnimationView.access$100(this$0).start();
    }
    while (ApplyingAnimationView.access$100(this$0) == null) {
      return;
    }
    ApplyingAnimationView.access$002(this$0, false);
    ApplyingAnimationView.access$202(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ApplyingAnimationView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */