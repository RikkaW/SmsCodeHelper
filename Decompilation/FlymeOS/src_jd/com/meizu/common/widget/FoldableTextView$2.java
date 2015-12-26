package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

class FoldableTextView$2
  implements ValueAnimator.AnimatorUpdateListener
{
  FoldableTextView$2(FoldableTextView paramFoldableTextView) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    FoldableTextView.access$902(this$0, Float.valueOf(paramValueAnimator.getAnimatedFraction()));
    this$0.requestLayout();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.FoldableTextView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */