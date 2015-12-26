package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class MultiWaveView$MyAnimatorUpdateListener
  implements ValueAnimator.AnimatorUpdateListener
{
  public boolean ignorSnap = true;
  
  private MultiWaveView$MyAnimatorUpdateListener(MultiWaveView paramMultiWaveView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    MultiWaveView.access$200(this$0, null, ignorSnap);
    this$0.invalidate();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.MyAnimatorUpdateListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */