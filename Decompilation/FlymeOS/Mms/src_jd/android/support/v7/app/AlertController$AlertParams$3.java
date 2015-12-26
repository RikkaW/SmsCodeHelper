package android.support.v7.app;

import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class AlertController$AlertParams$3
  implements AdapterView.OnItemClickListener
{
  AlertController$AlertParams$3(AlertController.AlertParams paramAlertParams, AlertController paramAlertController) {}
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this$0.mOnClickListener.onClick(AlertController.access$600(val$dialog), paramInt);
    if (!this$0.mIsSingleChoice) {
      AlertController.access$600(val$dialog).dismiss();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController.AlertParams.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */