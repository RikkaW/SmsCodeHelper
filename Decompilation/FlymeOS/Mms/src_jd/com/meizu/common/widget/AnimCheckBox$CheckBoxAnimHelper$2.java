package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class AnimCheckBox$CheckBoxAnimHelper$2
  implements ValueAnimator.AnimatorUpdateListener
{
  AnimCheckBox$CheckBoxAnimHelper$2(AnimCheckBox.CheckBoxAnimHelper paramCheckBoxAnimHelper) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    if (AnimCheckBox.access$400(AnimCheckBox.CheckBoxAnimHelper.access$100(this$0)) != null)
    {
      float f2 = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
      float f1 = f2;
      if (!AnimCheckBox.CheckBoxAnimHelper.access$000(this$0)) {
        f1 = 1.0F - f2;
      }
      AnimCheckBox.access$400(AnimCheckBox.CheckBoxAnimHelper.access$100(this$0)).getUpdateTransition(f1);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimCheckBox.CheckBoxAnimHelper.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */