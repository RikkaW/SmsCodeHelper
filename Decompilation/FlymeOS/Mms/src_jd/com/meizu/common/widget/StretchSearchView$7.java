package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

class StretchSearchView$7
  implements ValueAnimator.AnimatorUpdateListener
{
  StretchSearchView$7(StretchSearchView paramStretchSearchView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    StretchSearchView.access$700(this$0).setLayoutParams(new RelativeLayout.LayoutParams((int)f, StretchSearchView.access$700(this$0).getHeight()));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */