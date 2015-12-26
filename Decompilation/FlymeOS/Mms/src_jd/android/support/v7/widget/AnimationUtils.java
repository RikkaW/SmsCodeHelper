package android.support.v7.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class AnimationUtils
{
  public static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
  public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
  
  public static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return (paramFloat2 - paramFloat1) * paramFloat3 + paramFloat1;
  }
  
  public static int lerp(int paramInt1, int paramInt2, float paramFloat)
  {
    return Math.round((paramInt2 - paramInt1) * paramFloat) + paramInt1;
  }
  
  static class AnimationListenerAdapter
    implements Animation.AnimationListener
  {
    public void onAnimationEnd(Animation paramAnimation) {}
    
    public void onAnimationRepeat(Animation paramAnimation) {}
    
    public void onAnimationStart(Animation paramAnimation) {}
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AnimationUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */