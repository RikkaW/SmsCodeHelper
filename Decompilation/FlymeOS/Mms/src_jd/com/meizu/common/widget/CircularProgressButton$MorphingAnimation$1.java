package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

class CircularProgressButton$MorphingAnimation$1
  implements ValueAnimator.AnimatorUpdateListener
{
  CircularProgressButton$MorphingAnimation$1(CircularProgressButton.MorphingAnimation paramMorphingAnimation, GradientDrawable paramGradientDrawable) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    Integer localInteger = (Integer)paramValueAnimator.getAnimatedValue();
    int k;
    int j;
    if (CircularProgressButton.MorphingAnimation.access$1400(this$1) > CircularProgressButton.MorphingAnimation.access$1500(this$1))
    {
      k = (CircularProgressButton.MorphingAnimation.access$1400(this$1) - localInteger.intValue()) / 2;
      j = CircularProgressButton.MorphingAnimation.access$1400(this$1) - k;
    }
    for (int i = (int)(CircularProgressButton.MorphingAnimation.access$1600(this$1) * paramValueAnimator.getAnimatedFraction());; i = (int)(CircularProgressButton.MorphingAnimation.access$1600(this$1) - CircularProgressButton.MorphingAnimation.access$1600(this$1) * paramValueAnimator.getAnimatedFraction()))
    {
      val$gradientDrawable.setBounds(k + i, i, j - i, CircularProgressButton.MorphingAnimation.access$1700(this$1).getHeight() - i);
      this$1.this$0.invalidate();
      return;
      k = (CircularProgressButton.MorphingAnimation.access$1500(this$1) - localInteger.intValue()) / 2;
      j = CircularProgressButton.MorphingAnimation.access$1500(this$1) - k;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CircularProgressButton.MorphingAnimation.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */