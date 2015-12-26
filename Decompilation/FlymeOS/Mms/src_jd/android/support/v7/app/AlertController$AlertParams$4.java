package android.support.v7.app;

import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

class AlertController$AlertParams$4
  implements AdapterView.OnItemClickListener
{
  AlertController$AlertParams$4(AlertController.AlertParams paramAlertParams, ListView paramListView, AlertController paramAlertController) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this$0.mCheckedItems != null) {
      this$0.mCheckedItems[paramInt] = val$listView.isItemChecked(paramInt);
    }
    this$0.mOnCheckboxClickListener.onClick(AlertController.access$600(val$dialog), paramInt, val$listView.isItemChecked(paramInt));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController.AlertParams.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */