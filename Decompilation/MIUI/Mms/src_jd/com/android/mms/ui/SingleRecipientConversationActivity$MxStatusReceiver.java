package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class SingleRecipientConversationActivity$MxStatusReceiver
  extends BroadcastReceiver
{
  private SingleRecipientConversationActivity$MxStatusReceiver(SingleRecipientConversationActivity paramSingleRecipientConversationActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (("com.xiaomi.mms.action.ENBALE_RESULT".equals(paramIntent.getAction())) && (paramIntent.getBooleanExtra("success", false))) {
      this$0.postSwitchMsgType();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.MxStatusReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */