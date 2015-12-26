package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.widget.EditText;

class MessageEditableActivityBase$22
  implements DialogInterface.OnClickListener
{
  MessageEditableActivityBase$22(MessageEditableActivityBase paramMessageEditableActivityBase, Runnable paramRunnable) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = this$0.mTextEditor.getText();
    int i;
    for (paramInt = 0; paramInt < paramDialogInterface.length(); paramInt = i + 1)
    {
      i = paramInt;
      if (paramDialogInterface.charAt(paramInt) == 65535)
      {
        paramDialogInterface.delete(paramInt, paramInt + 1);
        i = paramInt - 1;
      }
    }
    if (val$onOk != null) {
      val$onOk.run();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.22
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */