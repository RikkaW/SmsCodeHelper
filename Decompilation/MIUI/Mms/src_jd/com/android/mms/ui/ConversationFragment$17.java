package com.android.mms.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;

class ConversationFragment$17
  implements DialogInterface.OnClickListener
{
  ConversationFragment$17(ConversationFragment paramConversationFragment, Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Settings.System.putInt(val$context.getContentResolver(), "mms_upload_old_msg_state", 0);
    Settings.System.putString(val$context.getContentResolver(), "mms_upload_old_msg_accounts", null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */