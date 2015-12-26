package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class PraiseView$3
  extends AnimatorListenerAdapter
{
  PraiseView$3(PraiseView paramPraiseView) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (this$0.mPraCallBack != null) {
      this$0.mPraCallBack.cancelAnimEnd();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PraiseView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */