package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.TextView;

class PhraseActivity$SelectedMessage$3
  implements DialogInterface.OnClickListener
{
  PhraseActivity$SelectedMessage$3(PhraseActivity.SelectedMessage paramSelectedMessage) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = PhraseActivity.SelectedMessage.access$400(this$1).getText().toString();
    if (PhraseActivity.SelectedMessage.access$500(this$1))
    {
      PhraseActivity.SelectedMessage.access$502(this$1, false);
      if (TextUtils.isEmpty(paramDialogInterface)) {
        return;
      }
      PhraseActivity.access$300(this$1.this$0).addPhrase(paramDialogInterface);
      return;
    }
    if (TextUtils.isEmpty(paramDialogInterface))
    {
      this$1.delete();
      return;
    }
    PhraseActivity.access$300(this$1.this$0).setPhrase(((Integer)access$200this$1).second).intValue(), paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseActivity.SelectedMessage.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */