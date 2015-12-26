package com.meizu.common.preference;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.view.View;

class ExpandableListPreference$AnimHelper$5
  implements Animator.AnimatorListener
{
  ExpandableListPreference$AnimHelper$5(ExpandableListPreference.AnimHelper paramAnimHelper, ValueAnimator paramValueAnimator) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    paramAnimator.removeAllListeners();
    val$valueAnimator.removeAllUpdateListeners();
    val$valueAnimator.removeAllListeners();
    if (ExpandableListPreference.AnimHelper.access$1100(this$1) == 1) {
      ExpandableListPreference.AnimHelper.access$1200(this$1).setVisibility(4);
    }
    ExpandableListPreference.AnimHelper.access$1702(this$1, false);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator)
  {
    ExpandableListPreference.AnimHelper.access$1200(this$1).setVisibility(0);
    ExpandableListPreference.AnimHelper.access$1702(this$1, true);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */