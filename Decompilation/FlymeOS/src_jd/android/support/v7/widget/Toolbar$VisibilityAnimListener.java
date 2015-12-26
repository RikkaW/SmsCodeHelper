package android.support.v7.widget;

import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

public class Toolbar$VisibilityAnimListener
  implements ViewPropertyAnimatorListener
{
  private boolean mCanceled = false;
  int mFinalVisibility;
  
  protected Toolbar$VisibilityAnimListener(Toolbar paramToolbar) {}
  
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
      this$0.mMenuViewVisibilityAnim = null;
    } while (Toolbar.access$600(this$0) == null);
    Toolbar.access$600(this$0).setVisibility(mFinalVisibility);
  }
  
  public void onAnimationStart(View paramView)
  {
    if (Toolbar.access$600(this$0) != null) {
      Toolbar.access$600(this$0).setVisibility(0);
    }
    mCanceled = false;
  }
  
  public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat paramViewPropertyAnimatorCompat, int paramInt)
  {
    this$0.mMenuViewVisibilityAnim = paramViewPropertyAnimatorCompat;
    mFinalVisibility = paramInt;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.Toolbar.VisibilityAnimListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */