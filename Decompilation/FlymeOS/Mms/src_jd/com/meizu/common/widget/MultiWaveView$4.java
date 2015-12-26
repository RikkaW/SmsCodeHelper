package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class MultiWaveView$4
  extends AnimatorListenerAdapter
{
  MultiWaveView$4(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    MultiWaveView.access$1802(this$0, false);
    MultiWaveView.Tweener.reset();
    MultiWaveView.access$1900(this$0);
    MultiWaveView.access$2202(this$0, false);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    MultiWaveView.access$1802(this$0, false);
    MultiWaveView.Tweener.reset();
    MultiWaveView.access$1900(this$0);
    MultiWaveView.access$2100(this$0, 330, 1200, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, MultiWaveView.access$2000(this$0));
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    MultiWaveView.access$500(this$0, 2, 0.0F, 0.0F);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */