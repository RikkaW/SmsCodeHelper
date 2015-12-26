package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Telephony.Sms;

class MessageListItem$5$1
  extends Thread
{
  MessageListItem$5$1(MessageListItem.5 param5, Context paramContext) {}
  
  public void run()
  {
    val$context.getContentResolver().delete(Telephony.Sms.CONTENT_URI, "thread_id=? AND date=? AND type=5", new String[] { String.valueOf(this$1.val$thread_id), String.valueOf(this$1.val$date) });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.5.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */