package android.support.v7.internal.widget;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

class MzSlidePopupWindow$DismissAnimator
{
  private boolean isRunning;
  private Animation mAnimation;
  
  private MzSlidePopupWindow$DismissAnimator(MzSlidePopupWindow paramMzSlidePopupWindow) {}
  
  void DismissAnimator() {}
  
  public boolean isRunning()
  {
    return isRunning;
  }
  
  public void start()
  {
    if (MzSlidePopupWindow.access$200(this$0)) {}
    for (int i = MzSlidePopupWindow.access$100(this$0).getMeasuredHeight();; i = -MzSlidePopupWindow.access$100(this$0).getMeasuredHeight())
    {
      mAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, i);
      mAnimation.setAnimationListener(new MzSlidePopupWindow.DismissAnimator.1(this));
      mAnimation.setDuration(200L);
      MzSlidePopupWindow.access$100(this$0).startAnimation(mAnimation);
      isRunning = true;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.MzSlidePopupWindow.DismissAnimator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */