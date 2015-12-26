package android.support.v7.widget;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

class ListPopupWindow$PopupTouchInterceptor
  implements View.OnTouchListener
{
  private ListPopupWindow$PopupTouchInterceptor(ListPopupWindow paramListPopupWindow) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    int j = (int)paramMotionEvent.getX();
    int k = (int)paramMotionEvent.getY();
    if ((i == 0) && (ListPopupWindow.access$1100(this$0) != null) && (ListPopupWindow.access$1100(this$0).isShowing()) && (j >= 0) && (j < ListPopupWindow.access$1100(this$0).getWidth()) && (k >= 0) && (k < ListPopupWindow.access$1100(this$0).getHeight())) {
      ListPopupWindow.access$1300(this$0).postDelayed(ListPopupWindow.access$1200(this$0), 250L);
    }
    for (;;)
    {
      return false;
      if (i == 1) {
        ListPopupWindow.access$1300(this$0).removeCallbacks(ListPopupWindow.access$1200(this$0));
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.PopupTouchInterceptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */