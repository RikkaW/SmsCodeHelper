package android.support.v7.internal.widget;

import android.view.View;

public class ActionBarContextView$ContextViewVisibilityAnimListener
  extends AbsActionBarView.VisibilityAnimListener
{
  protected ActionBarContextView$ContextViewVisibilityAnimListener(ActionBarContextView paramActionBarContextView)
  {
    super(paramActionBarContextView);
  }
  
  public void onAnimationEnd(View paramView)
  {
    super.onAnimationEnd(paramView);
    if (mFinalVisibility == 8) {
      this$0.killMode();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarContextView.ContextViewVisibilityAnimListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */