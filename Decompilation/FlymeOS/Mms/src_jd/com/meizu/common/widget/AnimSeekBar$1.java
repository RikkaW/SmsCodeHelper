package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class AnimSeekBar$1
  implements ValueAnimator.AnimatorUpdateListener
{
  AnimSeekBar$1(AnimSeekBar paramAnimSeekBar) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    AnimSeekBar.access$000(this$0, f);
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimSeekBar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */