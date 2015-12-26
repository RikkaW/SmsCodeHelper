package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class MessageUtils$5
  implements DialogInterface.OnClickListener
{
  MessageUtils$5(Runnable paramRunnable) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$callback != null) {
      val$callback.run();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */