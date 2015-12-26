package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.provider.Settings.System;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;

class ConversationFragment$16
  implements DialogInterface.OnClickListener
{
  ConversationFragment$16(ConversationFragment paramConversationFragment, Context paramContext, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Settings.System.putInt(val$context.getContentResolver(), "mms_upload_old_msg_state", 0);
    Settings.System.putString(val$context.getContentResolver(), "mms_upload_old_msg_accounts", null);
    paramDialogInterface = new ContentValues();
    paramDialogInterface.put("account", val$currentAccount);
    paramDialogInterface.put("bind_id", Integer.valueOf(0));
    val$context.getContentResolver().update(Telephony.Sms.CONTENT_URI, paramDialogInterface, "account is not null and account != ?", new String[] { val$currentAccount });
    val$context.getContentResolver().update(Telephony.Mms.CONTENT_URI, paramDialogInterface, "account is not null and account != ?", new String[] { val$currentAccount });
    MessageUtils.forceSync(val$context);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */