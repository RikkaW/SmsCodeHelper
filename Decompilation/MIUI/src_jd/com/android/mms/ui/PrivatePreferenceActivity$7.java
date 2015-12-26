package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class PrivatePreferenceActivity$7
  implements DialogInterface.OnClickListener
{
  PrivatePreferenceActivity$7(PrivatePreferenceActivity paramPrivatePreferenceActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      PrivatePreferenceActivity.access$1200(this$0);
      return;
    }
    PrivatePreferenceActivity.access$1300(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */