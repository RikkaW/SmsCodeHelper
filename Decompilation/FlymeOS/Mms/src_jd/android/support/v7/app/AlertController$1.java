package android.support.v7.app;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class AlertController$1
  implements View.OnClickListener
{
  AlertController$1(AlertController paramAlertController) {}
  
  public void onClick(View paramView)
  {
    if ((paramView == AlertController.access$000(this$0)) && (AlertController.access$100(this$0) != null)) {
      paramView = Message.obtain(AlertController.access$100(this$0));
    }
    for (;;)
    {
      if (paramView != null) {
        paramView.sendToTarget();
      }
      AlertController.access$700(this$0).obtainMessage(1, AlertController.access$600(this$0)).sendToTarget();
      return;
      if ((paramView == AlertController.access$200(this$0)) && (AlertController.access$300(this$0) != null)) {
        paramView = Message.obtain(AlertController.access$300(this$0));
      } else if ((paramView == AlertController.access$400(this$0)) && (AlertController.access$500(this$0) != null)) {
        paramView = Message.obtain(AlertController.access$500(this$0));
      } else {
        paramView = null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AlertController.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */