package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class PraiseView$1
  extends AnimatorListenerAdapter
{
  PraiseView$1(PraiseView paramPraiseView) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    this$0.setBackgroundDrawable(PraiseView.access$000(this$0));
    if (this$0.mPraCallBack != null) {
      this$0.mPraCallBack.praAlphAnimEnd();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PraiseView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */