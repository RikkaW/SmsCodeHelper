package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class MessageUtils$12$1
  implements DialogInterface.OnClickListener
{
  MessageUtils$12$1(MessageUtils.12 param12) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MessageUtils.access$000(this$0.val$context, new Intent("android.intent.action.VIEW", this$0.val$info.uri));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.12.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */