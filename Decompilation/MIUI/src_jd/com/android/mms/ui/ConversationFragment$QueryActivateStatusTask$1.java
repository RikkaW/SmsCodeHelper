package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ConversationFragment$QueryActivateStatusTask$1
  implements DialogInterface.OnClickListener
{
  ConversationFragment$QueryActivateStatusTask$1(ConversationFragment.QueryActivateStatusTask paramQueryActivateStatusTask) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    MessageUtils.activatePhone(ConversationFragment.QueryActivateStatusTask.access$2800(this$0), ConversationFragment.QueryActivateStatusTask.access$2900(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.QueryActivateStatusTask.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */