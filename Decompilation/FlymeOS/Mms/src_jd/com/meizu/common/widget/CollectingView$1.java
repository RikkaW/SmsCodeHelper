package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class CollectingView$1
  extends AnimatorListenerAdapter
{
  CollectingView$1(CollectingView paramCollectingView) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (this$0.mCollectCallBack != null) {
      this$0.mCollectCallBack.collectEndAnim();
    }
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    if (this$0.mCollectCallBack != null) {
      this$0.mCollectCallBack.collectStartAnim();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CollectingView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */