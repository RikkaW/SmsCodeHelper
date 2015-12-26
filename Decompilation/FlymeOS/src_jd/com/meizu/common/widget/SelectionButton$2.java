package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

class SelectionButton$2
  extends AnimatorListenerAdapter
{
  SelectionButton$2(SelectionButton paramSelectionButton) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    paramAnimator = (View)((ObjectAnimator)paramAnimator).getTarget();
    paramAnimator.setScaleX(1.0F);
    paramAnimator.setScaleY(1.0F);
    this$0.setVisibility(SelectionButton.access$000(this$0), false);
    this$0.setClickable(true);
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    this$0.setClickable(false);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SelectionButton.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */