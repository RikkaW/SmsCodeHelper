package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class MultiWaveView$5
  extends AnimatorListenerAdapter
{
  MultiWaveView$5(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    MultiWaveView.Tweener.reset();
    MultiWaveView.access$2100(this$0, 330, 1200, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, MultiWaveView.access$2000(this$0));
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    MultiWaveView.Tweener.reset();
    MultiWaveView.access$2100(this$0, 330, 1200, MultiWaveView.Ease.Quart.easeOut, 1.0F, 0.0F, 0.0F, 0.0F, MultiWaveView.access$2000(this$0));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */