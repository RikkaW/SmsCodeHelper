package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class MultiWaveView$6
  implements ValueAnimator.AnimatorUpdateListener
{
  MultiWaveView$6(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    MultiWaveView.access$2402(this$0, ((Float)paramValueAnimator.getAnimatedValue("rotation")).floatValue());
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */