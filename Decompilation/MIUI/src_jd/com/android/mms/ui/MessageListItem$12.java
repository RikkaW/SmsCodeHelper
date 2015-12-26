package com.android.mms.ui;

import com.android.mms.understand.UnderstandMessage.Item;

class MessageListItem$12
  implements Runnable
{
  MessageListItem$12(MessageListItem paramMessageListItem, String paramString1, String paramString2, UnderstandMessage.Item paramItem, MessageItem paramMessageItem) {}
  
  public void run()
  {
    MessageUtils.UriInfo localUriInfo = new MessageUtils.UriInfo();
    scheme = 4;
    formatContent = val$time;
    content = val$body.substring(val$item.mStartPosition, val$item.mEndPosition);
    title = val$time;
    MessageUtils.performUriOperation(MessageListItem.access$1700(this$0), localUriInfo, val$mi);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */