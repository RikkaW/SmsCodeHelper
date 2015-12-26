package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class StretchSearchView$11
  implements ValueAnimator.AnimatorUpdateListener
{
  StretchSearchView$11(StretchSearchView paramStretchSearchView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    if (StretchSearchView.access$1000(this$0) != null) {
      StretchSearchView.access$1000(this$0).onShortenAnimationUpdate(StretchSearchView.access$900(this$0), f);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */