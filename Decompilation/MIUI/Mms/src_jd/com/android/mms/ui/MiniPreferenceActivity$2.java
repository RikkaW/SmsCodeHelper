package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class MiniPreferenceActivity$2
  implements DialogInterface.OnClickListener
{
  MiniPreferenceActivity$2(MiniPreferenceActivity paramMiniPreferenceActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1)
    {
      MessagingPreferenceActivity.enableNotifications(false, this$0);
      this$0.setResult(-1);
    }
    for (;;)
    {
      this$0.finish();
      return;
      this$0.setResult(0);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MiniPreferenceActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */