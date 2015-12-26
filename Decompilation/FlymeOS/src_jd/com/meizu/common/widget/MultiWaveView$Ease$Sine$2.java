package com.meizu.common.widget;

import android.animation.TimeInterpolator;

final class MultiWaveView$Ease$Sine$2
  implements TimeInterpolator
{
  public float getInterpolation(float paramFloat)
  {
    return (float)Math.sin(paramFloat / 1.0F * 1.5707963267948966D) * 1.0F + 0.0F;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease.Sine.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */