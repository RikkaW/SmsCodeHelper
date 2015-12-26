package com.meizu.common.preference;

import android.view.animation.Interpolator;

class ExpandableListPreference$AnimHelper$AnimInterpolator
  implements Interpolator
{
  private ExpandableListPreference$AnimHelper$AnimInterpolator(ExpandableListPreference.AnimHelper paramAnimHelper) {}
  
  public float getInterpolation(float paramFloat)
  {
    return (float)(1.0D - Math.pow(1.0F - paramFloat, 3.0D));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference.AnimHelper.AnimInterpolator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */