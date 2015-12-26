package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;

class ListPopupWindow$ForwardingListener$DisallowIntercept
  implements Runnable
{
  private ListPopupWindow$ForwardingListener$DisallowIntercept(ListPopupWindow.ForwardingListener paramForwardingListener) {}
  
  public void run()
  {
    ViewParent localViewParent = ListPopupWindow.ForwardingListener.access$900(this$0).getParent();
    if (localViewParent != null) {
      localViewParent.requestDisallowInterceptTouchEvent(true);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.ForwardingListener.DisallowIntercept
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */