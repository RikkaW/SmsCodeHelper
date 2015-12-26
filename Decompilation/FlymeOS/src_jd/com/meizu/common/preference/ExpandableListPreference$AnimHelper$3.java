package com.meizu.common.preference;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ExpandableListPreference$AnimHelper$3
  implements ValueAnimator.AnimatorUpdateListener
{
  ExpandableListPreference$AnimHelper$3(ExpandableListPreference.AnimHelper paramAnimHelper) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    ExpandableListPreference.AnimHelper.access$1200(this$1).setAlpha(((Float)paramValueAnimator.getAnimatedValue()).floatValue());
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */