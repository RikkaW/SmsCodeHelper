package com.meizu.common.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class MultiWaveView$3
  implements ValueAnimator.AnimatorUpdateListener
{
  MultiWaveView$3(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    double d = Math.atan2(-MultiWaveView.access$800(this$0).getY(), MultiWaveView.access$800(this$0).getX());
    MultiWaveView.access$902(this$0, MultiWaveView.access$1000(this$0, d));
    float f = MultiWaveView.access$800(this$0).getX();
    if ((f < 0.0F) && (f > -117.0F * MultiWaveView.access$1100(this$0))) {
      MultiWaveView.access$1202(this$0, ((Integer)MultiWaveView.access$1500(this$0).evaluate(-f / (MultiWaveView.access$1100(this$0) * 117.0F), Integer.valueOf(MultiWaveView.access$1300(this$0)), Integer.valueOf(MultiWaveView.access$1400(this$0)))).intValue());
    }
    for (;;)
    {
      this$0.invalidate();
      return;
      if ((f > 0.0F) && (f < MultiWaveView.access$1100(this$0) * 117.0F)) {
        MultiWaveView.access$1202(this$0, ((Integer)MultiWaveView.access$1500(this$0).evaluate(f / (MultiWaveView.access$1100(this$0) * 117.0F), Integer.valueOf(MultiWaveView.access$1300(this$0)), Integer.valueOf(MultiWaveView.access$1600(this$0)))).intValue());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */