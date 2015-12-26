package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;

class MessageListItem$2
  implements View.OnClickListener
{
  MessageListItem$2(MessageListItem paramMessageListItem, MessageItem paramMessageItem) {}
  
  public void onClick(View paramView)
  {
    if ((MessageListItem.access$300(this$0) instanceof ConversationBase)) {
      ((ConversationBase)MessageListItem.access$400(this$0)).setSendTimeForSpecifiedMessage(val$msgItem);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */