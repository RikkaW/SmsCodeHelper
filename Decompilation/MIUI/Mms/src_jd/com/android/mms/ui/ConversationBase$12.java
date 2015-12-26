package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

class ConversationBase$12
  extends Handler
{
  ConversationBase$12(ConversationBase paramConversationBase) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      Log.w("ConversationBase", "Unknown message: " + what);
      return;
    case 3: 
    case 9: 
      ConversationBase.access$500(this$0, "mms", paramMessage);
      return;
    case 1: 
      ConversationBase.access$600(this$0, "mms", paramMessage);
      return;
    case 4: 
    case 5: 
    case 8: 
      ConversationBase.access$500(this$0, "sms", paramMessage);
      return;
    case 2: 
      ConversationBase.access$600(this$0, "sms", paramMessage);
      return;
    case 12: 
      ConversationBase.access$700(this$0, paramMessage);
      return;
    case 101: 
      ConversationBase.access$800(this$0);
      return;
    }
    this$0.hideSoftKeyboard();
    ConversationBase.access$900(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */