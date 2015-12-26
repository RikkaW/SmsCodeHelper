package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class Switch$1
  implements ValueAnimator.AnimatorUpdateListener
{
  Switch$1(Switch paramSwitch) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    Switch.access$000(this$0, ((Float)paramValueAnimator.getAnimatedValue()).floatValue());
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.Switch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */