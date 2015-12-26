package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;

class ConversationBase$19
  extends Handler
{
  ConversationBase$19(ConversationBase paramConversationBase) {}
  
  public void handleMessage(Message paramMessage)
  {
    if (what == 0)
    {
      if (ConversationBase.access$2800(this$0) > 20)
      {
        ConversationBase.access$2902(this$0, 21);
        ConversationBase.access$2820(this$0, 20);
      }
      for (;;)
      {
        ConversationBase.access$3002(this$0, true);
        this$0.startMsgListQuery();
        this$0.mMsgListView.doneMore();
        return;
        ConversationBase.access$2902(this$0, ConversationBase.access$2800(this$0) + 1);
        ConversationBase.access$2802(this$0, 0);
        this$0.mMsgListView.setNeedMoreData(false);
      }
    }
    super.handleMessage(paramMessage);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */