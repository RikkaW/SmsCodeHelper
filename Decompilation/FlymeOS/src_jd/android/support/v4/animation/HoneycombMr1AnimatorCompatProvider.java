package android.support.v4.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.view.View;

class HoneycombMr1AnimatorCompatProvider
  implements AnimatorProvider
{
  public ValueAnimatorCompat emptyValueAnimator()
  {
    return new HoneycombValueAnimatorCompat(ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F }));
  }
  
  static class AnimatorListenerCompatWrapper
    implements Animator.AnimatorListener
  {
    final ValueAnimatorCompat mValueAnimatorCompat;
    final AnimatorListenerCompat mWrapped;
    
    public AnimatorListenerCompatWrapper(AnimatorListenerCompat paramAnimatorListenerCompat, ValueAnimatorCompat paramValueAnimatorCompat)
    {
      mWrapped = paramAnimatorListenerCompat;
      mValueAnimatorCompat = paramValueAnimatorCompat;
    }
    
    public void onAnimationCancel(Animator paramAnimator)
    {
      mWrapped.onAnimationCancel(mValueAnimatorCompat);
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      mWrapped.onAnimationEnd(mValueAnimatorCompat);
    }
    
    public void onAnimationRepeat(Animator paramAnimator)
    {
      mWrapped.onAnimationRepeat(mValueAnimatorCompat);
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      mWrapped.onAnimationStart(mValueAnimatorCompat);
    }
  }
  
  static class HoneycombValueAnimatorCompat
    implements ValueAnimatorCompat
  {
    final Animator mWrapped;
    
    public HoneycombValueAnimatorCompat(Animator paramAnimator)
    {
      mWrapped = paramAnimator;
    }
    
    public void addListener(AnimatorListenerCompat paramAnimatorListenerCompat)
    {
      mWrapped.addListener(new HoneycombMr1AnimatorCompatProvider.AnimatorListenerCompatWrapper(paramAnimatorListenerCompat, this));
    }
    
    public void addUpdateListener(AnimatorUpdateListenerCompat paramAnimatorUpdateListenerCompat)
    {
      if ((mWrapped instanceof ValueAnimator)) {
        ((ValueAnimator)mWrapped).addUpdateListener(new HoneycombMr1AnimatorCompatProvider.HoneycombValueAnimatorCompat.1(this, paramAnimatorUpdateListenerCompat));
      }
    }
    
    public void cancel()
    {
      mWrapped.cancel();
    }
    
    public float getAnimatedFraction()
    {
      return ((ValueAnimator)mWrapped).getAnimatedFraction();
    }
    
    public void setDuration(long paramLong)
    {
      mWrapped.setDuration(paramLong);
    }
    
    public void setTarget(View paramView)
    {
      mWrapped.setTarget(paramView);
    }
    
    public void start()
    {
      mWrapped.start();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.animation.HoneycombMr1AnimatorCompatProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */