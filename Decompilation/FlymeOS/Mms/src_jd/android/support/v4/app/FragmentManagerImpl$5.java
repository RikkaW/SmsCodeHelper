package android.support.v4.app;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class FragmentManagerImpl$5
  implements Animation.AnimationListener
{
  FragmentManagerImpl$5(FragmentManagerImpl paramFragmentManagerImpl, Fragment paramFragment) {}
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    if (val$fragment.mAnimatingAway != null)
    {
      val$fragment.mAnimatingAway = null;
      this$0.moveToState(val$fragment, val$fragment.mStateAfterAnimating, 0, 0, false);
    }
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentManagerImpl.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */