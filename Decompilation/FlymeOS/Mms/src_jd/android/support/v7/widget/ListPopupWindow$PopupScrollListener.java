package android.support.v7.widget;

import android.os.Handler;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.PopupWindow;

class ListPopupWindow$PopupScrollListener
  implements AbsListView.OnScrollListener
{
  private ListPopupWindow$PopupScrollListener(ListPopupWindow paramListPopupWindow) {}
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if ((paramInt == 1) && (!this$0.isInputMethodNotNeeded()) && (ListPopupWindow.access$1100(this$0).getContentView() != null))
    {
      ListPopupWindow.access$1300(this$0).removeCallbacks(ListPopupWindow.access$1200(this$0));
      ListPopupWindow.access$1200(this$0).run();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.PopupScrollListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */