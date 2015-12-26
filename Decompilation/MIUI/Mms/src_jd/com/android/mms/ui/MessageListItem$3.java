package com.android.mms.ui;

import android.content.Context;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.ArrayList;

class MessageListItem$3
  implements CompoundButton.OnCheckedChangeListener
{
  MessageListItem$3(MessageListItem paramMessageListItem, MessageItem paramMessageItem, MessageItem.FailedItem paramFailedItem) {}
  
  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    boolean bool = true;
    if (paramBoolean)
    {
      val$msgItem.addToResend(val$failedItem.messageUri);
      int i = val$msgItem.resendMsgInGroup().size();
      MessageListItem.access$600(this$0).setText(MessageListItem.access$500(this$0).getString(2131362072, new Object[] { Integer.valueOf(i) }));
      paramCompoundButton = MessageListItem.access$600(this$0);
      if (i <= 0) {
        break label103;
      }
    }
    label103:
    for (paramBoolean = bool;; paramBoolean = false)
    {
      paramCompoundButton.setEnabled(paramBoolean);
      return;
      val$msgItem.removeFromResend(val$failedItem.messageUri);
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */