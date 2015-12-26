package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Message;

class MessageListItem$15
  implements DialogInterface.OnClickListener
{
  MessageListItem$15(MessageListItem paramMessageListItem, boolean paramBoolean, long paramLong, int paramInt) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == 0)
    {
      paramDialogInterface = MessageListItem.access$700(this$0);
      if (val$isSms)
      {
        paramInt = 4;
        paramDialogInterface = Message.obtain(paramDialogInterface, paramInt);
        obj = Long.valueOf(val$msgId);
        arg1 = Integer.valueOf(val$slotId).intValue();
        paramDialogInterface.sendToTarget();
      }
    }
    while (paramInt != 1) {
      for (;;)
      {
        return;
        paramInt = 3;
      }
    }
    paramDialogInterface = MessageListItem.access$700(this$0);
    if (val$isSms) {}
    for (paramInt = 8;; paramInt = 9)
    {
      paramDialogInterface = Message.obtain(paramDialogInterface, paramInt);
      obj = Long.valueOf(val$msgId);
      arg1 = Integer.valueOf(val$slotId).intValue();
      paramDialogInterface.sendToTarget();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */