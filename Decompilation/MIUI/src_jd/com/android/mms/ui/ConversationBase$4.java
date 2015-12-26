package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;

class ConversationBase$4
  implements View.OnClickListener
{
  ConversationBase$4(ConversationBase paramConversationBase) {}
  
  public void onClick(View paramView)
  {
    paramView = String.format(ConversationBase.access$200(this$0).getResources().getString(2131362384), new Object[] { ((Contact)this$0.getRecipients().get(0)).getNumber() });
    new AlertDialog.Builder(ConversationBase.access$200(this$0)).setIconAttribute(16843605).setCancelable(true).setTitle(2131362383).setMessage(paramView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(final DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ConversationBase.access$300(this$0);
        paramAnonymousDialogInterface = PreferenceManager.getDefaultSharedPreferences(this$0.getApplicationContext());
        if (!paramAnonymousDialogInterface.getBoolean("already_remind_filter_stranger_mx_message", false))
        {
          if (!paramAnonymousDialogInterface.getBoolean("pref_key_mx_filter_message_from_stranger", false)) {
            new AlertDialog.Builder(ConversationBase.access$200(this$0)).setIconAttribute(16843605).setCancelable(true).setTitle(2131362411).setMessage(2131362412).setPositiveButton(17039370, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                paramAnonymousDialogInterface.edit().putBoolean("pref_key_mx_filter_message_from_stranger", true).commit();
              }
            }).setNegativeButton(2131361892, null).show();
          }
          paramAnonymousDialogInterface.edit().putBoolean("already_remind_filter_stranger_mx_message", true).commit();
        }
      }
    }).setNegativeButton(2131361892, null).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */