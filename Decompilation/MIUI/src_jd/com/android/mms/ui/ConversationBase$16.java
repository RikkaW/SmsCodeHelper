package com.android.mms.ui;

import com.android.mms.data.Conversation;

class ConversationBase$16
  implements Runnable
{
  ConversationBase$16(ConversationBase paramConversationBase) {}
  
  public void run()
  {
    this$0.mConversation.update();
    this$0.startMsgListQuery();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */