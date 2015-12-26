package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.ActionMenuView;
import android.view.View;

public class AbsActionBarView$VisibilityAnimListener
  implements ViewPropertyAnimatorListener
{
  private boolean mCanceled = false;
  int mFinalVisibility;
  
  protected AbsActionBarView$VisibilityAnimListener(AbsActionBarView paramAbsActionBarView) {}
  
  public void onAnimationCancel(View paramView)
  {
    mCanceled = true;
  }
  
  public void onAnimationEnd(View paramView)
  {
    if (mCanceled) {}
    do
    {
      return;
      this$0.mVisibilityAnim = null;
      this$0.setVisibility(mFinalVisibility);
    } while ((this$0.mSplitView == null) || (this$0.mMenuView == null));
    this$0.mMenuView.setVisibility(mFinalVisibility);
  }
  
  public void onAnimationStart(View paramView)
  {
    this$0.setVisibility(0);
    mCanceled = false;
  }
  
  public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
  {
    this$0.mVisibilityAnim = paramViewPropertyAnimatorCompat;
    mFinalVisibility = paramInt;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AbsActionBarView.VisibilityAnimListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */