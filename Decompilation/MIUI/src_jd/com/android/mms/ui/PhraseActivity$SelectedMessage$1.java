package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Pair;

class PhraseActivity$SelectedMessage$1
  implements DialogInterface.OnClickListener
{
  PhraseActivity$SelectedMessage$1(PhraseActivity.SelectedMessage paramSelectedMessage) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    PhraseActivity.access$300(this$1.this$0).deletePhrase(((Integer)access$200this$1).second).intValue());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseActivity.SelectedMessage.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */