package com.meizu.common.preference;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.view.View;

class ExpandableListPreference$AnimHelper$2
  implements Animator.AnimatorListener
{
  ExpandableListPreference$AnimHelper$2(ExpandableListPreference.AnimHelper paramAnimHelper) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (ExpandableListPreference.AnimHelper.access$1100(this$1) == 1)
    {
      ExpandableListPreference.AnimHelper.access$1000(this$1).setVisibility(0);
      return;
    }
    ExpandableListPreference.AnimHelper.access$1000(this$1).setVisibility(4);
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */