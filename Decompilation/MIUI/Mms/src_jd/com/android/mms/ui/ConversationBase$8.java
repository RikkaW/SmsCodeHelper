package com.android.mms.ui;

import com.android.mms.transaction.MessagingNotification;

class ConversationBase$8
  implements Runnable
{
  ConversationBase$8(ConversationBase paramConversationBase, long paramLong) {}
  
  public void run()
  {
    MessagingNotification.updateSendFailedNotificationForThread(this$0, val$threadId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */