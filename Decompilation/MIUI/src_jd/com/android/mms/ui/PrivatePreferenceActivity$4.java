package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;

class PrivatePreferenceActivity$4
  implements DialogInterface.OnShowListener
{
  PrivatePreferenceActivity$4(PrivatePreferenceActivity paramPrivatePreferenceActivity) {}
  
  public void onShow(DialogInterface paramDialogInterface)
  {
    MessageUtils.requestInputMethod(PrivatePreferenceActivity.access$100(this$0), PrivatePreferenceActivity.access$1000(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */