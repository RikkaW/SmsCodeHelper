package com.meizu.common.widget;

import android.animation.TimeInterpolator;

final class MultiWaveView$Ease$Quart$2
  implements TimeInterpolator
{
  public float getInterpolation(float paramFloat)
  {
    paramFloat = paramFloat / 1.0F - 1.0F;
    return -1.0F * (paramFloat * (paramFloat * paramFloat * paramFloat) - 1.0F) + 0.0F;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease.Quart.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */