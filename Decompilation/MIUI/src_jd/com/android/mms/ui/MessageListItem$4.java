package com.android.mms.ui;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.util.MSimUtils;

class MessageListItem$4
  implements View.OnClickListener
{
  MessageListItem$4(MessageListItem paramMessageListItem, MessageItem paramMessageItem) {}
  
  public void onClick(View paramView)
  {
    if (MessageListItem.access$700(this$0) != null)
    {
      paramView = Message.obtain(MessageListItem.access$700(this$0), 5);
      obj = Long.valueOf(val$msgItem.getMsgId());
      arg1 = Integer.valueOf(MSimUtils.getSlotIdBySimInfoId(val$msgItem.getSimId())).intValue();
      paramView.sendToTarget();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */