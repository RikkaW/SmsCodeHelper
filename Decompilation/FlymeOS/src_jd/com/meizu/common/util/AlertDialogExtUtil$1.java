package com.meizu.common.util;

import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

final class AlertDialogExtUtil$1
  implements View.OnClickListener
{
  AlertDialogExtUtil$1(AlertDialog paramAlertDialog, int paramInt, DialogInterface.OnClickListener paramOnClickListener) {}
  
  public void onClick(View paramView)
  {
    paramView = new AlertDialogExtUtil.ButtonHandler(val$dialog);
    switch (val$whichButton)
    {
    default: 
      throw new IllegalArgumentException("Button does not exist");
    }
    if (val$listener != null) {}
    for (paramView = paramView.obtainMessage(val$whichButton, val$listener);; paramView = null)
    {
      if (paramView != null) {
        paramView.sendToTarget();
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.AlertDialogExtUtil.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */