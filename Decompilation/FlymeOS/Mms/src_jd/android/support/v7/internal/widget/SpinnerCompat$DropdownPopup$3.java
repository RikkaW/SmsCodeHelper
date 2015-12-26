package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.PopupWindow.OnDismissListener;

class SpinnerCompat$DropdownPopup$3
  implements PopupWindow.OnDismissListener
{
  SpinnerCompat$DropdownPopup$3(SpinnerCompat.DropdownPopup paramDropdownPopup, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener) {}
  
  public void onDismiss()
  {
    ViewTreeObserver localViewTreeObserver = this$1.this$0.getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.removeGlobalOnLayoutListener(val$layoutListener);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.DropdownPopup.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */