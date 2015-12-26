package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

final class ViewPropertyAnimatorCompatJB$1
  extends AnimatorListenerAdapter
{
  ViewPropertyAnimatorCompatJB$1(ViewPropertyAnimatorListener paramViewPropertyAnimatorListener, View paramView) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    val$listener.onAnimationCancel(val$view);
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    val$listener.onAnimationEnd(val$view);
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    val$listener.onAnimationStart(val$view);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPropertyAnimatorCompatJB.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */