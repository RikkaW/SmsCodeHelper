package android.support.v7.internal.widget;

import android.support.v4.view.ActionProvider;
import android.support.v7.widget.ListPopupWindow;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class ActivityChooserView$2
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  ActivityChooserView$2(ActivityChooserView paramActivityChooserView) {}
  
  public void onGlobalLayout()
  {
    if (this$0.isShowingPopup())
    {
      if (this$0.isShown()) {
        break label31;
      }
      ActivityChooserView.access$100(this$0).dismiss();
    }
    label31:
    do
    {
      return;
      ActivityChooserView.access$100(this$0).show();
    } while (this$0.mProvider == null);
    this$0.mProvider.subUiVisibilityChanged(true);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */