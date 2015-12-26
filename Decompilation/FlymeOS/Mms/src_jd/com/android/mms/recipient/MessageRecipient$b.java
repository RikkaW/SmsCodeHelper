package com.android.mms.recipient;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import nd.b;

public class MessageRecipient$b
  extends Handler
{
  public MessageRecipient$b(MessageRecipient paramMessageRecipient, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    }
    do
    {
      return;
      paramMessage = String.valueOf(obj);
    } while (MessageRecipient.h(a) == null);
    MessageRecipient.h(a).a(new String[] { paramMessage });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.recipient.MessageRecipient.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */