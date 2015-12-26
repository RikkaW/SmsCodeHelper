package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.widget.TextView;

class ProgressTextButtonView$2
  implements Animator.AnimatorListener
{
  ProgressTextButtonView$2(ProgressTextButtonView paramProgressTextButtonView, boolean paramBoolean) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (val$show)
    {
      ProgressTextButtonView.access$100(this$0).setVisibility(0);
      ProgressTextButtonView.access$000(this$0).setVisibility(8);
      return;
    }
    ProgressTextButtonView.access$100(this$0).setVisibility(8);
    ProgressTextButtonView.access$000(this$0).setVisibility(0);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressTextButtonView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */