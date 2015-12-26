package android.support.v7.internal.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class MzSlidePopupWindow$DismissAnimator$1
  implements Animation.AnimationListener
{
  MzSlidePopupWindow$DismissAnimator$1(MzSlidePopupWindow.DismissAnimator paramDismissAnimator) {}
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    this$1.this$0.supperDismiss();
    MzSlidePopupWindow.DismissAnimator.access$302(this$1, false);
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.MzSlidePopupWindow.DismissAnimator.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */