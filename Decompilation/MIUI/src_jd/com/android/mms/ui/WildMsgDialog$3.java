package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;

class WildMsgDialog$3
  implements DialogInterface.OnClickListener
{
  WildMsgDialog$3(WildMsgDialog paramWildMsgDialog) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramInt = Settings.System.getInt(WildMsgDialog.access$100(this$0).getContentResolver(), "mms_sync_wild_msg_state", 0);
    if ((paramInt != 4) && (paramInt != 5)) {
      Settings.System.putInt(WildMsgDialog.access$100(this$0).getContentResolver(), "mms_sync_wild_msg_state", 2);
    }
    if (!WildMsgDialog.access$300(this$0)) {
      WildMsgDialog.access$400(this$0, WildMsgDialog.access$100(this$0));
    }
    WildMsgDialog.access$500(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.WildMsgDialog.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */