package com.meizu.common.preference;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ExpandableListPreference$AnimHelper$4
  implements ValueAnimator.AnimatorUpdateListener
{
  ExpandableListPreference$AnimHelper$4(ExpandableListPreference.AnimHelper paramAnimHelper) {}
  
  public void onAnimationUpdate(ValueAnimator paramValueAnimator)
  {
    int i = Math.abs(ExpandableListPreference.AnimHelper.access$1300(this$1) - ExpandableListPreference.AnimHelper.access$1400(this$1));
    float f = Math.abs(((Integer)paramValueAnimator.getAnimatedValue()).intValue() - ExpandableListPreference.AnimHelper.access$1300(this$1)) / i;
    if (ExpandableListPreference.AnimHelper.access$1100(this$1) == 0) {
      ExpandableListPreference.AnimHelper.access$1500(this$1).setRotation(f * 180.0F);
    }
    for (;;)
    {
      access$1600this$1).bottomMargin = ((Integer)paramValueAnimator.getAnimatedValue()).intValue();
      if (!ExpandableListPreference.AnimHelper.access$1200(this$1).isInLayout()) {
        ExpandableListPreference.AnimHelper.access$1200(this$1).requestLayout();
      }
      return;
      ExpandableListPreference.AnimHelper.access$1500(this$1).setRotation((1.0F - f) * 180.0F);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */