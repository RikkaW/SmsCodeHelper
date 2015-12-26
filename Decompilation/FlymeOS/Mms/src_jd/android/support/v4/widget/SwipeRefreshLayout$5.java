package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class SwipeRefreshLayout$5
  implements Animation.AnimationListener
{
  SwipeRefreshLayout$5(SwipeRefreshLayout paramSwipeRefreshLayout) {}
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    if (!SwipeRefreshLayout.access$600(this$0)) {
      SwipeRefreshLayout.access$1000(this$0, null);
    }
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SwipeRefreshLayout.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */