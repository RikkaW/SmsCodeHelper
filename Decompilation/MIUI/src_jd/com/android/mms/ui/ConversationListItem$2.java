package com.android.mms.ui;

import android.os.Handler;
import android.util.Log;
import com.android.mms.data.Conversation;

class ConversationListItem$2
  implements Runnable
{
  ConversationListItem$2(ConversationListItem paramConversationListItem) {}
  
  public void run()
  {
    Log.v("Anting", " markBlockAsReadSync   ");
    ConversationListItem.access$400(this$0);
    Conversation.markBlockAsReadSync(ConversationListItem.access$300(this$0));
    ConversationListItem.access$200(this$0).post(new Runnable()
    {
      public void run()
      {
        ConversationListItem.access$400(this$0).setPreMarkUnread(false);
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationListItem.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */