package com.meizu.common.widget;

import android.animation.TimeInterpolator;

final class MultiWaveView$Ease$customTrack$2
  implements TimeInterpolator
{
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= 0.34D) {
      return (float)Math.sin(19.634954084936208D * paramFloat - 0.39269908169872414D);
    }
    if (paramFloat <= 0.62D) {
      return (float)(0.7D * Math.sin(26.179938779914945D * paramFloat - 9.948376736367678D));
    }
    if (paramFloat <= 0.87D) {
      return (float)(0.3D * Math.sin(22.43994752564138D * paramFloat - 13.239569040128414D));
    }
    if (paramFloat <= 0.9D) {
      return (float)(3.3333333333333335D * paramFloat - 2.9D);
    }
    return (float)(-1.0F * paramFloat + 1.0D);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.Ease.customTrack.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */