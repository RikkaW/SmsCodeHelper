package com.android.mms.ui;

import android.os.Handler;
import com.android.mms.data.Conversation;

class ConversationListItem$1
  implements Runnable
{
  ConversationListItem$1(ConversationListItem paramConversationListItem) {}
  
  public void run()
  {
    Conversation.markSPAsReadSync(ConversationListItem.access$000(this$0), 1);
    ConversationListItem.access$200(this$0).post(new Runnable()
    {
      public void run()
      {
        ConversationListItem.access$102(this$0, false);
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationListItem.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */