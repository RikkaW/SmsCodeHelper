package com.android.mms.ui;

import android.app.AlertDialog;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class MessageListItem$16
  implements View.OnClickListener
{
  MessageListItem$16(MessageListItem paramMessageListItem, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {}
  
  public void onClick(View paramView)
  {
    MessageListItem.access$2200(this$0, val$msgId, val$slotId, val$isSms);
    if (!val$isMx)
    {
      paramView = MessageListItem.access$700(this$0);
      if (val$isSms) {}
      for (i = 4;; i = 3)
      {
        paramView = Message.obtain(paramView, i);
        obj = Long.valueOf(val$msgId);
        arg1 = Integer.valueOf(val$slotId).intValue();
        paramView.sendToTarget();
        if (MessageListItem.access$2500(this$0) != null)
        {
          MessageListItem.access$2500(this$0).dismiss();
          MessageListItem.access$2502(this$0, null);
        }
        return;
      }
    }
    paramView = MessageListItem.access$700(this$0);
    if (val$isSms) {}
    for (int i = 8;; i = 9)
    {
      paramView = Message.obtain(paramView, i);
      obj = Long.valueOf(val$msgId);
      arg1 = Integer.valueOf(val$slotId).intValue();
      paramView.sendToTarget();
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */