package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;

class MultiWaveView$8
  implements Animator.AnimatorListener
{
  MultiWaveView$8(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    this$0.resetCircleAnimation();
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (MultiWaveView.access$3400(this$0)) {
      MultiWaveView.access$3500(this$0).start();
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */