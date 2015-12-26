package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class AnimSeekBar$3
  implements ValueAnimator.AnimatorUpdateListener
{
  AnimSeekBar$3(AnimSeekBar paramAnimSeekBar) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
    AnimSeekBar.access$200(this$0, i);
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimSeekBar.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */