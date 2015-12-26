package com.android.mms.ui;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.data.Contact;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.B2cMessageUtils;

class MessageListItem$14
  implements View.OnClickListener
{
  MessageListItem$14(MessageListItem paramMessageListItem, MessageItem paramMessageItem) {}
  
  public void onClick(View paramView)
  {
    boolean bool;
    long l;
    if (MessageListItem.access$700(this$0) != null)
    {
      bool = val$msgItem.isSms();
      l = val$msgItem.getMsgId();
      if (B2cMessageUtils.isB2cNumber(Contact.get(val$msgItem.getAddress()))) {
        MessageListItem.access$1900(this$0, paramView, l, bool, val$msgItem.getSimId());
      }
    }
    else
    {
      return;
    }
    String str = MessageListItem.access$2000(this$0, val$msgItem.getAddress(), bool);
    if (MSimUtils.isMSimInserted())
    {
      MessageListItem.access$2100(this$0, paramView, l, bool, str);
      return;
    }
    int j = MSimUtils.getInsertedSlotId();
    if (MSimUtils.getSlotIdBySimInfoId(val$msgItem.getSimId()) < 0) {
      MessageListItem.access$2200(this$0, l, j, bool);
    }
    if (MessageListItem.access$2300(this$0, str, j))
    {
      MessageListItem.access$2400(this$0, paramView, l, bool, j);
      return;
    }
    paramView = MessageListItem.access$700(this$0);
    if (bool) {}
    for (int i = 4;; i = 3)
    {
      paramView = Message.obtain(paramView, i);
      obj = Long.valueOf(l);
      arg1 = Integer.valueOf(j).intValue();
      paramView.sendToTarget();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */