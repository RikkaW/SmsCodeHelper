package com.android.mms.ui;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ViewAnimator$1
  implements ValueAnimator.AnimatorUpdateListener
{
  ViewAnimator$1(ViewAnimator paramViewAnimator) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    ViewAnimator.access$002(this$0, Integer.parseInt(paramValueAnimator.getAnimatedValue("EXTRA_POSITION").toString()));
    ViewAnimator.access$100(this$0).invalidate();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ViewAnimator.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */