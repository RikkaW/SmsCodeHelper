package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;

class PhraseActivity$SelectedMessage$2
  implements DialogInterface.OnShowListener
{
  PhraseActivity$SelectedMessage$2(PhraseActivity.SelectedMessage paramSelectedMessage) {}
  
  public void onShow(DialogInterface paramDialogInterface)
  {
    MessageUtils.requestInputMethod(PhraseActivity.access$100(this$1.this$0), PhraseActivity.SelectedMessage.access$400(this$1));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseActivity.SelectedMessage.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */