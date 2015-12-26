package android.support.v7.internal.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

class ToolbarWidgetWrapper$3
  extends ViewPropertyAnimatorListenerAdapter
{
  ToolbarWidgetWrapper$3(ToolbarWidgetWrapper paramToolbarWidgetWrapper) {}
  
  public void onAnimationStart(View paramView)
  {
    ToolbarWidgetWrapper.access$000(this$0).setVisibility(0);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ToolbarWidgetWrapper.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */