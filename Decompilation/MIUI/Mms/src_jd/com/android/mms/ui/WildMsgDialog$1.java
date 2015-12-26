package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;

class WildMsgDialog$1
  implements DialogInterface.OnMultiChoiceClickListener
{
  WildMsgDialog$1(WildMsgDialog paramWildMsgDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
  {
    if ((WildMsgDialog.access$000(this$0) != null) && (WildMsgDialog.access$000(this$0).length > paramInt)) {
      WildMsgDialog.access$000(this$0)[paramInt] = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */