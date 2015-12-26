package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class MessageEditableActivityBase$23
  implements DialogInterface.OnClickListener
{
  MessageEditableActivityBase$23(MessageEditableActivityBase paramMessageEditableActivityBase, Runnable paramRunnable) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (val$onCancel != null) {
      val$onCancel.run();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.23
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */