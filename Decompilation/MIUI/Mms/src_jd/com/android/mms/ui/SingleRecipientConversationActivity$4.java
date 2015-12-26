package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.mms.data.Conversation;

class SingleRecipientConversationActivity$4
  extends BroadcastReceiver
{
  SingleRecipientConversationActivity$4(SingleRecipientConversationActivity paramSingleRecipientConversationActivity) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramContext)) {
      if (("homekey".equals(paramIntent.getStringExtra("reason"))) && (this$0.mConversation.isPrivate())) {
        this$0.finish();
      }
    }
    while ((!"android.intent.action.SCREEN_OFF".equals(paramContext)) || (!this$0.mConversation.isPrivate())) {
      return;
    }
    this$0.finish();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */