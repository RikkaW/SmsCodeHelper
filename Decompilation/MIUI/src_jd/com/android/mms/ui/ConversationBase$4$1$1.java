package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

class ConversationBase$4$1$1
  implements DialogInterface.OnClickListener
{
  ConversationBase$4$1$1(ConversationBase.4.1 param1, SharedPreferences paramSharedPreferences) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    val$sp.edit().putBoolean("pref_key_mx_filter_message_from_stranger", true).commit();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.4.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */