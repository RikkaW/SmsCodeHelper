package android.support.v7.internal.widget;

import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.ListPopupWindow.ForwardingListener;
import android.view.View;

class SpinnerCompat$1
  extends ListPopupWindow.ForwardingListener
{
  SpinnerCompat$1(SpinnerCompat paramSpinnerCompat, View paramView, SpinnerCompat.DropdownPopup paramDropdownPopup)
  {
    super(paramView);
  }
  
  public ListPopupWindow getPopup()
  {
    return val$popup;
  }
  
  public boolean onForwardingStarted()
  {
    if (!SpinnerCompat.access$100(this$0).isShowing()) {
      SpinnerCompat.access$100(this$0).show();
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */