package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class AnimSeekBar$2
  implements ValueAnimator.AnimatorUpdateListener
{
  AnimSeekBar$2(AnimSeekBar paramAnimSeekBar) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
    AnimSeekBar.access$100(this$0, i);
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimSeekBar.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */