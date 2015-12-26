package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import tl;
import tm;
import vv;

public class ComposeMessageActivity$e
  implements DialogInterface.OnClickListener
{
  private final vv b;
  private final vv[] c;
  
  public ComposeMessageActivity$e(ComposeMessageActivity paramComposeMessageActivity, vv paramvv)
  {
    b = paramvv;
    c = null;
  }
  
  public ComposeMessageActivity$e(ComposeMessageActivity paramComposeMessageActivity, vv[] paramArrayOfvv)
  {
    c = paramArrayOfvv;
    if (c.length > 0)
    {
      b = c[0];
      return;
    }
    b = null;
  }
  
  public void a(boolean paramBoolean)
  {
    new tm(this, paramBoolean).execute(new Void[0]);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    new tl(this).execute(new Void[0]);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */