package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.data.WorkingMessage;

class MessageEditableActivityBase$DiscardDraftListener
  implements DialogInterface.OnClickListener
{
  private MessageEditableActivityBase$DiscardDraftListener(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$0.mWorkingMessage.discard();
    paramDialogInterface.dismiss();
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.DiscardDraftListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */