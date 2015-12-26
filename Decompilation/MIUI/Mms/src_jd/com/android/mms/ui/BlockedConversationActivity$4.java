package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

class BlockedConversationActivity$4
  implements View.OnClickListener
{
  BlockedConversationActivity$4(BlockedConversationActivity paramBlockedConversationActivity, BlockedConversationActivity.RestoreListener paramRestoreListener, CheckBox paramCheckBox) {}
  
  public void onClick(View paramView)
  {
    if (val$listener != null) {
      val$listener.setUnblockChecked(val$checkbox.isChecked());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */