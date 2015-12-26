package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.recipient.MessageRecipient;
import java.util.Iterator;
import java.util.List;

class ComposeMessageActivity$i
  implements DialogInterface.OnClickListener
{
  private ComposeMessageActivity$i(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (ComposeMessageActivity.i(a))
    {
      Iterator localIterator = ComposeMessageActivity.j(a).getInvalidNumbers().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ComposeMessageActivity.j(a).b(str);
      }
      ComposeMessageActivity.j(a).requestFocus();
      a.i();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */