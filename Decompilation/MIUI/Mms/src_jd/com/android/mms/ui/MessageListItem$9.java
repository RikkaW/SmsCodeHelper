package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class MessageListItem$9
  implements View.OnClickListener
{
  MessageListItem$9(MessageListItem paramMessageListItem) {}
  
  public void onClick(View paramView)
  {
    MessageListItem.access$700(this$0).removeMessages(12);
    Message localMessage = Message.obtain(MessageListItem.access$700(this$0), 12);
    obj = paramView;
    localMessage.sendToTarget();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */