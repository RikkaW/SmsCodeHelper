package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.widget.TextView;

class ProgressTextButtonView$1
  implements ValueAnimator.AnimatorUpdateListener
{
  ProgressTextButtonView$1(ProgressTextButtonView paramProgressTextButtonView, boolean paramBoolean) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    if (val$show)
    {
      ProgressTextButtonView.access$000(this$0).setAlpha(1.0F - f);
      ProgressTextButtonView.access$100(this$0).setAlpha(f);
      return;
    }
    ProgressTextButtonView.access$000(this$0).setAlpha(f);
    ProgressTextButtonView.access$100(this$0).setAlpha(1.0F - f);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ProgressTextButtonView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */