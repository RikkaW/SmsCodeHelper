package android.support.v4.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;

class HoneycombMr1AnimatorCompatProvider$AnimatorListenerCompatWrapper
  implements Animator.AnimatorListener
{
  final ValueAnimatorCompat mValueAnimatorCompat;
  final AnimatorListenerCompat mWrapped;
  
  public HoneycombMr1AnimatorCompatProvider$AnimatorListenerCompatWrapper(AnimatorListenerCompat paramAnimatorListenerCompat, ValueAnimatorCompat paramValueAnimatorCompat)
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

/* Location:
 * Qualified Name:     android.support.v4.animation.HoneycombMr1AnimatorCompatProvider.AnimatorListenerCompatWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */