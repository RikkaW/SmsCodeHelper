package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.transaction.TransactionService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;

class MessageListItem$1
  implements View.OnClickListener
{
  MessageListItem$1(MessageListItem paramMessageListItem, MessageItem paramMessageItem) {}
  
  public void onClick(View paramView)
  {
    if (val$msgItem.isMiXin())
    {
      MxMmsTransactionService.startRetrieveMms(MessageListItem.access$000(this$0), val$msgItem.getMessageUri());
      return;
    }
    paramView = new Intent(MessageListItem.access$100(this$0), TransactionService.class);
    paramView.putExtra("uri", val$msgItem.getMessageUri().toString());
    paramView.putExtra("type", 1);
    MessageListItem.access$200(this$0).startService(paramView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */