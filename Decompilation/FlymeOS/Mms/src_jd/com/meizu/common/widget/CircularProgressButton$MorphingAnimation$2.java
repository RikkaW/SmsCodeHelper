package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class CircularProgressButton$MorphingAnimation$2
  implements Animator.AnimatorListener
{
  CircularProgressButton$MorphingAnimation$2(CircularProgressButton.MorphingAnimation paramMorphingAnimation) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (CircularProgressButton.MorphingAnimation.access$1900(this$1) != null) {
      CircularProgressButton.MorphingAnimation.access$1900(this$1).onAnimationEnd();
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.MorphingAnimation.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */