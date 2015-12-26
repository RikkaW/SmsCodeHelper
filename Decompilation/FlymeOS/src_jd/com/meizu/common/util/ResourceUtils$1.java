package com.meizu.common.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class ResourceUtils$1
  extends AnimatorListenerAdapter
{
  ResourceUtils$1(View paramView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    val$view.setBackgroundColor(0);
    val$view.setHasTransientState(false);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    val$view.setBackgroundColor(0);
    val$view.setHasTransientState(false);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ResourceUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */