package com.android.mms.ui;

import android.content.Context;
import android.widget.TextView;
import com.android.mms.data.Contact;

class MessageListItem$6
  implements Runnable
{
  MessageListItem$6(MessageListItem paramMessageListItem, Contact paramContact) {}
  
  public void run()
  {
    if ((MessageListItem.access$800(this$0) != null) && (MessageListItem.access$800(this$0).getContact() == val$updated))
    {
      if ((MessageListItem.access$900(this$0) != null) && (access$1000this$0).mGroupSendToTitle.getVisibility() == 0)) {
        access$1000this$0).mGroupSendToTextView.setText(MessageListItem.access$800(this$0).getContactName());
      }
      if (!MessageListItem.access$800(this$0).isListLayoutStyle()) {
        break label146;
      }
      if ((MessageListItem.access$1100(this$0) != null) && (!MessageListItem.access$800(this$0).isGroup()) && (!MessageListItem.access$800(this$0).isReadOnly())) {
        MessageListItem.access$1100(this$0).setText(MessageListItem.access$800(this$0).getContactName());
      }
    }
    label146:
    while ((MessageListItem.access$800(this$0).isBubbleLayoutStyle()) || (MessageListItem.access$1100(this$0) == null)) {
      return;
    }
    MessageListItem.access$1100(this$0).setText(MessageListItem.access$1200(this$0).getString(2131362102, new Object[] { MessageListItem.access$800(this$0).getContactName() }));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */