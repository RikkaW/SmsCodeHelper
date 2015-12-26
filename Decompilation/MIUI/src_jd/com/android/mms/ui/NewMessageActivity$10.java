package com.android.mms.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnFocusChangeListener;

class NewMessageActivity$10
  implements View.OnFocusChangeListener
{
  NewMessageActivity$10(NewMessageActivity paramNewMessageActivity) {}
  
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (PreferenceManager.getDefaultSharedPreferences(this$0).getBoolean("pref_key_show_recent_contacts", true)) {
      NewMessageActivity.access$2100(this$0, paramBoolean);
    }
    NewMessageActivity.access$2200(this$0, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */