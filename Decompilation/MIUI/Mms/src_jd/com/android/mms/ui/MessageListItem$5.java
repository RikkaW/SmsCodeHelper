package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Telephony.Sms;
import android.view.View;
import android.view.View.OnClickListener;

class MessageListItem$5
  implements View.OnClickListener
{
  MessageListItem$5(MessageListItem paramMessageListItem, long paramLong1, long paramLong2) {}
  
  public void onClick(View paramView)
  {
    new Thread()
    {
      public void run()
      {
        val$context.getContentResolver().delete(Telephony.Sms.CONTENT_URI, "thread_id=? AND date=? AND type=5", new String[] { String.valueOf(val$thread_id), String.valueOf(val$date) });
      }
    }.start();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */