package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class MessageEditableActivityBase$24
  implements DialogInterface.OnCancelListener
{
  MessageEditableActivityBase$24(MessageEditableActivityBase paramMessageEditableActivityBase, Runnable paramRunnable) {}
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (val$onCancel != null) {
      val$onCancel.run();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */