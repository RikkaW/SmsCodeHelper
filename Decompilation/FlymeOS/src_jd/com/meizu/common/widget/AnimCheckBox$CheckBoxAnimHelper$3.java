package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class AnimCheckBox$CheckBoxAnimHelper$3
  extends AnimatorListenerAdapter
{
  AnimCheckBox$CheckBoxAnimHelper$3(AnimCheckBox.CheckBoxAnimHelper paramCheckBoxAnimHelper) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (AnimCheckBox.CheckBoxAnimHelper.access$300(this$0).isRunning()) {
      AnimCheckBox.CheckBoxAnimHelper.access$300(this$0).end();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimCheckBox.CheckBoxAnimHelper.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */