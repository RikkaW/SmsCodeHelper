package android.support.v4.animation;

import android.os.Build.VERSION;

public abstract class AnimatorCompatHelper
{
  static AnimatorProvider IMPL = new DonutAnimatorCompatProvider();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 12)
    {
      IMPL = new HoneycombMr1AnimatorCompatProvider();
      return;
    }
  }
  
  public static ValueAnimatorCompat emptyValueAnimator()
  {
    return IMPL.emptyValueAnimator();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.animation.AnimatorCompatHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */