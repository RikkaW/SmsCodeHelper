package com.meizu.common.widget;

import android.view.animation.Interpolator;

class PraiseView$AnimInterpolator
  implements Interpolator
{
  private PraiseView$AnimInterpolator(PraiseView paramPraiseView) {}
  
  public float getInterpolation(float paramFloat)
  {
    return (float)(1.0D - Math.pow(1.0F - paramFloat, 2.0D));
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PraiseView.AnimInterpolator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */