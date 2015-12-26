package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class StretchSearchView$8
  implements ValueAnimator.AnimatorUpdateListener
{
  StretchSearchView$8(StretchSearchView paramStretchSearchView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    if (StretchSearchView.access$800(this$0) != null) {
      StretchSearchView.access$800(this$0).onStetchAnimationUpdate(StretchSearchView.access$900(this$0), f);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */