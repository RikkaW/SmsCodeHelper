package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class NewMessageActivity$SendIgnoreInvalidRecipientListener
  implements DialogInterface.OnClickListener
{
  private final int mSlotId;
  
  public NewMessageActivity$SendIgnoreInvalidRecipientListener(NewMessageActivity paramNewMessageActivity, int paramInt)
  {
    mSlotId = paramInt;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$0.checkAndSendMessage(true, mSlotId);
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.SendIgnoreInvalidRecipientListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */