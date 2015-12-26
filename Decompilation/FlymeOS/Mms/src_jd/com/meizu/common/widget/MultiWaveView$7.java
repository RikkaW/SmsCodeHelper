package com.meizu.common.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class MultiWaveView$7
  implements ValueAnimator.AnimatorUpdateListener
{
  MultiWaveView$7(MultiWaveView paramMultiWaveView, float paramFloat, ObjectAnimator paramObjectAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    MultiWaveView.access$2502(this$0, ((Integer)paramValueAnimator.getAnimatedValue("ballalpha")).intValue());
    MultiWaveView.access$2602(this$0, ((Float)paramValueAnimator.getAnimatedValue("translateX")).floatValue());
    MultiWaveView.access$2702(this$0, ((Float)paramValueAnimator.getAnimatedValue("strokewidth")).floatValue());
    MultiWaveView.access$2802(this$0, ((Float)paramValueAnimator.getAnimatedValue("radius")).floatValue());
    MultiWaveView.access$2902(this$0, ((Float)paramValueAnimator.getAnimatedValue("angel")).floatValue());
    MultiWaveView.access$3002(this$0, ((Integer)paramValueAnimator.getAnimatedValue("bigcirclealpha")).intValue());
    MultiWaveView.access$3102(this$0, MultiWaveView.access$1300(this$0));
    MultiWaveView.access$3202(this$0, ((Float)paramValueAnimator.getAnimatedValue("pointRadius")).floatValue());
    if ((MultiWaveView.access$3300(this$0)) && (Math.abs(MultiWaveView.access$2800(this$0) - val$targetRadius) < 10.0F))
    {
      this$0.invalidate();
      MultiWaveView.access$3302(this$0, false);
      val$objectAnimator.cancel();
      return;
    }
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */