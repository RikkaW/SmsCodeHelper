package android.support.v7.internal.widget;

import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.view.View;

class ActivityChooserView$3
  extends ListPopupWindow.ForwardingListener
{
  ActivityChooserView$3(ActivityChooserView paramActivityChooserView, View paramView)
  {
    super(paramView);
  }
  
  public ListPopupWindow getPopup()
  {
    return ActivityChooserView.access$100(this$0);
  }
  
  protected boolean onForwardingStarted()
  {
    this$0.showPopup();
    return true;
  }
  
  protected boolean onForwardingStopped()
  {
    this$0.dismissPopup();
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */