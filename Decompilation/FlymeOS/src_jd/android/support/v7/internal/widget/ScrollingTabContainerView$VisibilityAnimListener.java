package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

public class ScrollingTabContainerView$VisibilityAnimListener
  implements ViewPropertyAnimatorListener
{
  private boolean mCanceled = false;
  private int mFinalVisibility;
  
  protected ScrollingTabContainerView$VisibilityAnimListener(ScrollingTabContainerView paramScrollingTabContainerView) {}
  
  public void onAnimationCancel(View paramView)
  {
    mCanceled = true;
  }
  
  public void onAnimationEnd(View paramView)
  {
    if (mCanceled) {
      return;
    }
    this$0.mVisibilityAnim = null;
    this$0.setVisibility(mFinalVisibility);
  }
  
  public void onAnimationStart(View paramView)
  {
    this$0.setVisibility(0);
    mCanceled = false;
  }
  
  public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
  {
    mFinalVisibility = paramInt;
    this$0.mVisibilityAnim = paramViewPropertyAnimatorCompat;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.VisibilityAnimListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */