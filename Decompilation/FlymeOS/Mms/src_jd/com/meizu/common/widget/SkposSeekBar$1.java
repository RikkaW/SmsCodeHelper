package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class SkposSeekBar$1
  implements ValueAnimator.AnimatorUpdateListener
{
  SkposSeekBar$1(SkposSeekBar paramSkposSeekBar, float paramFloat1, float paramFloat2, int paramInt) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
    int i;
    if (val$endPos > val$startPos)
    {
      i = (int)(f * (val$endPos - val$startPos) / SkposSeekBar.access$000(this$0));
      SkposSeekBar.access$102(this$0, i + val$curProgress);
    }
    for (;;)
    {
      this$0.setProgress(SkposSeekBar.access$100(this$0));
      return;
      i = (int)(f * (val$startPos - val$endPos) / SkposSeekBar.access$000(this$0));
      SkposSeekBar.access$102(this$0, val$curProgress - i);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.SkposSeekBar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */