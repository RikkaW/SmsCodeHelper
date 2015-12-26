package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.MmsApp;
import miui.cloud.util.SyncAlertHelper;

class ConversationFragment$8
  implements View.OnClickListener
{
  ConversationFragment$8(ConversationFragment paramConversationFragment) {}
  
  public void onClick(View paramView)
  {
    if (this$0.getActivity() != null) {
      SyncAlertHelper.recordTime(MmsApp.getApp(), "sms");
    }
    ConversationFragment.access$1100(this$0);
    ConversationFragment.access$500(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */