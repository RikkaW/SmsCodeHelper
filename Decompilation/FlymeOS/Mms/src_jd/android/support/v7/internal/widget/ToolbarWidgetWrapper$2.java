package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

class ToolbarWidgetWrapper$2
  extends ViewPropertyAnimatorListenerAdapter
{
  private boolean mCanceled = false;
  
  ToolbarWidgetWrapper$2(ToolbarWidgetWrapper paramToolbarWidgetWrapper) {}
  
  public void onAnimationCancel(View paramView)
  {
    mCanceled = true;
  }
  
  public void onAnimationEnd(View paramView)
  {
    if (!mCanceled)
    {
      ToolbarWidgetWrapper.access$000(this$0).setVisibility(8);
      ToolbarWidgetWrapper.access$000(this$0).setMenuVisibility(8);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ToolbarWidgetWrapper.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */