package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

class ConversationBase$4$1
  implements DialogInterface.OnClickListener
{
  ConversationBase$4$1(ConversationBase.4 param4) {}
  
  public void onClick(final DialogInterface paramDialogInterface, int paramInt)
  {
    ConversationBase.access$300(this$1.this$0);
    paramDialogInterface = PreferenceManager.getDefaultSharedPreferences(this$1.this$0.getApplicationContext());
    if (!paramDialogInterface.getBoolean("already_remind_filter_stranger_mx_message", false))
    {
      if (!paramDialogInterface.getBoolean("pref_key_mx_filter_message_from_stranger", false)) {
        new AlertDialog.Builder(ConversationBase.access$200(this$1.this$0)).setIconAttribute(16843605).setCancelable(true).setTitle(2131362411).setMessage(2131362412).setPositiveButton(17039370, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramDialogInterface.edit().putBoolean("pref_key_mx_filter_message_from_stranger", true).commit();
          }
        }).setNegativeButton(2131361892, null).show();
      }
      paramDialogInterface.edit().putBoolean("already_remind_filter_stranger_mx_message", true).commit();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */