package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.recipient.MessageRecipient;

class ComposeMessageActivity$b
  implements DialogInterface.OnClickListener
{
  private ComposeMessageActivity$b(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (ComposeMessageActivity.i(a)) {
      ComposeMessageActivity.j(a).requestFocus();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */