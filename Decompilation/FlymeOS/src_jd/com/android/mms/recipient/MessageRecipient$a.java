package com.android.mms.recipient;

import android.database.ContentObserver;
import android.os.Handler;

class MessageRecipient$a
  extends ContentObserver
{
  private Handler b;
  
  public MessageRecipient$a(MessageRecipient paramMessageRecipient, Handler paramHandler)
  {
    super(paramHandler);
    b = paramHandler;
  }
  
  public void onChange(boolean paramBoolean)
  {
    b.removeMessages(4);
    b.sendEmptyMessageDelayed(4, 500L);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.recipient.MessageRecipient.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */