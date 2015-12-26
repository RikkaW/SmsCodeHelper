package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import miui.cloud.util.SyncAlertHelper;

class ConversationFragment$7
  implements View.OnClickListener
{
  ConversationFragment$7(ConversationFragment paramConversationFragment) {}
  
  public void onClick(View paramView)
  {
    if (this$0.getActivity() != null) {
      SyncAlertHelper.handleSyncAlert(this$0.getActivity(), "sms");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */