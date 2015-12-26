package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

final class ConversationFragment$12
  implements View.OnClickListener
{
  ConversationFragment$12(ConversationFragment.DeleteThreadListener paramDeleteThreadListener, CheckBox paramCheckBox) {}
  
  public void onClick(View paramView)
  {
    val$listener.setDeleteLockedMessage(val$checkbox.isChecked());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */