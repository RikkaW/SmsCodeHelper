package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class ConversationBase$9
  implements View.OnClickListener
{
  ConversationBase$9(ConversationBase paramConversationBase, ConversationBase.BatchDeleteListener paramBatchDeleteListener, CheckBox paramCheckBox) {}
  
  public void onClick(View paramView)
  {
    val$listener.setDeleteLockedMessage(val$checkbox.isChecked());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */