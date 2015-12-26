package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

final class MessageUtils$6
  implements DialogInterface.OnCancelListener
{
  MessageUtils$6(Runnable paramRunnable) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (val$callback != null) {
      val$callback.run();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */