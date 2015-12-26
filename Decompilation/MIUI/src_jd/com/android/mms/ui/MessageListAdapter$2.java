package com.android.mms.ui;

import android.database.Cursor;

class MessageListAdapter$2
  implements MessageListAdapter.GetMsgItem
{
  MessageListAdapter$2(MessageListAdapter paramMessageListAdapter) {}
  
  public MessageItem getCurrMessageItem(int paramInt)
  {
    return MessageListAdapter.access$100(this$0, MessageListAdapter.access$000(this$0), false);
  }
  
  public MessageItem getNextMessageItem(int paramInt)
  {
    MessageItem localMessageItem = null;
    if (MessageListAdapter.access$500(this$0).moveToNext()) {
      localMessageItem = MessageListAdapter.access$100(this$0, MessageListAdapter.access$600(this$0), false);
    }
    MessageListAdapter.access$700(this$0).moveToPrevious();
    return localMessageItem;
  }
  
  public MessageItem getPreMessageItem(int paramInt)
  {
    MessageItem localMessageItem = null;
    if (MessageListAdapter.access$200(this$0).moveToPrevious()) {
      localMessageItem = MessageListAdapter.access$100(this$0, MessageListAdapter.access$300(this$0), false);
    }
    MessageListAdapter.access$400(this$0).moveToNext();
    return localMessageItem;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListAdapter.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */