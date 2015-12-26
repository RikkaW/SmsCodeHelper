package com.meizu.common.widget;

import android.animation.TimeInterpolator;

final class MultiWaveView$Ease$customTrack$1
  implements TimeInterpolator
{
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= 0.42D) {
      MultiWaveView.Ease.customTrack.mOutput = 2.3809524F * paramFloat;
    }
    for (;;)
    {
      return MultiWaveView.Ease.customTrack.mOutput;
      if (paramFloat > 0.65D) {
        MultiWaveView.Ease.customTrack.mOutput = (float)(-2.857142857142857D * paramFloat + 2.857142857142857D);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease.customTrack.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */