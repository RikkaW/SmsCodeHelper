package android.support.v7.widget;

import android.database.DataSetObserver;

class ListPopupWindow$PopupDataSetObserver
  extends DataSetObserver
{
  private ListPopupWindow$PopupDataSetObserver(ListPopupWindow paramListPopupWindow) {}
  
  public void onChanged()
  {
    if (this$0.isShowing()) {
      this$0.show();
    }
  }
  
  public void onInvalidated()
  {
    this$0.dismiss();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.PopupDataSetObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */