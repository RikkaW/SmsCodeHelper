package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class CustomButton$1
  implements ValueAnimator.AnimatorUpdateListener
{
  CustomButton$1(CustomButton paramCustomButton) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    CustomButton.access$000(this$0, f);
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomButton.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */