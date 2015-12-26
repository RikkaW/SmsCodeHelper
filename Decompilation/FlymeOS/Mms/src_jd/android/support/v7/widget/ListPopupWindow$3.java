package android.support.v7.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class ListPopupWindow$3
  implements AdapterView.OnItemSelectedListener
{
  ListPopupWindow$3(ListPopupWindow paramListPopupWindow) {}
  
  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt != -1)
    {
      paramAdapterView = ListPopupWindow.access$600(this$0);
      if (paramAdapterView != null) {
        ListPopupWindow.DropDownListView.access$502(paramAdapterView, false);
      }
    }
  }
  
  public void onNothingSelected(AdapterView<?> paramAdapterView) {}
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ListPopupWindow.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */