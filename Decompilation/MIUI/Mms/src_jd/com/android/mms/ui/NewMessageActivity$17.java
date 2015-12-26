package com.android.mms.ui;

import android.content.Intent;
import com.android.mms.data.Conversation;

class NewMessageActivity$17
  implements Runnable
{
  NewMessageActivity$17(NewMessageActivity paramNewMessageActivity) {}
  
  public void run()
  {
    Object localObject = Conversation.get(this$0.mConversation.getThreadId());
    ((Conversation)localObject).update();
    if ((!((Conversation)localObject).isPrivate()) || ((((Conversation)localObject).isPrivate()) && (this$0.mOrgMsgIsPrivate)))
    {
      localObject = ComposeMessageRouterActivity.createIntent(this$0, this$0.mConversation.getThreadId());
      ((Intent)localObject).putExtra("new_message_count", 1);
      ((Intent)localObject).putExtra("was_soft_keyboard_on", this$0.mIsSoftInputEnabled);
      ((Intent)localObject).addFlags(65536);
      ComposeMessageRouterActivity.route(this$0, (Intent)localObject);
    }
    this$0.finish();
    this$0.overridePendingTransition(17432576, 17432577);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */