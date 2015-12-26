package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

class AnimCheckBox$CheckBoxAnimHelper$1
  extends AnimatorListenerAdapter
{
  AnimCheckBox$CheckBoxAnimHelper$1(AnimCheckBox.CheckBoxAnimHelper paramCheckBoxAnimHelper) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    AnimCheckBox.CheckBoxAnimHelper.access$100(this$0).superSetCheck(AnimCheckBox.CheckBoxAnimHelper.access$000(this$0));
    AnimCheckBox.CheckBoxAnimHelper.access$100(this$0).superSetActivate(AnimCheckBox.CheckBoxAnimHelper.access$200(this$0));
    if (access$100this$0).mInitVisible != 0)
    {
      if (!AnimCheckBox.CheckBoxAnimHelper.access$000(this$0)) {
        break label79;
      }
      AnimCheckBox.CheckBoxAnimHelper.access$100(this$0).setVisibility(0);
    }
    for (;;)
    {
      AnimCheckBox.CheckBoxAnimHelper.access$300(this$0).start();
      return;
      label79:
      AnimCheckBox.CheckBoxAnimHelper.access$100(this$0).setVisibility(access$100this$0).mInitVisible);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimCheckBox.CheckBoxAnimHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */