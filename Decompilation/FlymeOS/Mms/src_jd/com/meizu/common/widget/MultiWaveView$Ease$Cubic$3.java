package com.meizu.common.widget;

import android.animation.TimeInterpolator;

final class MultiWaveView$Ease$Cubic$3
  implements TimeInterpolator
{
  public float getInterpolation(float paramFloat)
  {
    paramFloat /= 0.5F;
    if (paramFloat < 1.0F) {
      return paramFloat * (0.5F * paramFloat * paramFloat) + 0.0F;
    }
    paramFloat -= 2.0F;
    return (paramFloat * (paramFloat * paramFloat) + 2.0F) * 0.5F + 0.0F;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease.Cubic.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */