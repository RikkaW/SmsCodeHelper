package android.support.v4.view;

import android.view.animation.Interpolator;

final class ViewPager$4
  implements Interpolator
{
  public float getInterpolation(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      return ViewPager.access$500()[0];
    }
    if (paramFloat >= 1.0F) {
      return ViewPager.access$500()[(ViewPager.access$500().length - 1)];
    }
    float f1 = 1.0F / (ViewPager.access$500().length - 1);
    int i = (int)(paramFloat / f1);
    float f2 = i;
    float f3 = ViewPager.access$500()[i];
    return (ViewPager.access$500()[(i + 1)] - ViewPager.access$500()[i]) * (paramFloat - f2 * f1) / f1 + f3;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPager.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */