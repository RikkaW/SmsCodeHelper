package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.widget.TextView.BufferType;

class FoldableTextView$1
  implements Animator.AnimatorListener
{
  FoldableTextView$1(FoldableTextView paramFoldableTextView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    FoldableTextView.access$102(this$0, false);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    boolean bool = false;
    if (FoldableTextView.access$300(this$0)) {
      this$0.setText(FoldableTextView.access$700(this$0), TextView.BufferType.SPANNABLE);
    }
    FoldableTextView.access$102(this$0, false);
    paramAnimator = this$0;
    if (!FoldableTextView.access$800(this$0)) {
      bool = true;
    }
    FoldableTextView.access$802(paramAnimator, bool);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator)
  {
    FoldableTextView.access$102(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FoldableTextView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */