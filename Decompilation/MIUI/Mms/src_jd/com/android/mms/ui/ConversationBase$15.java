package com.android.mms.ui;

import android.os.Handler;

class ConversationBase$15
  implements MessageListAdapter.OnDataSetChangedListener
{
  ConversationBase$15(ConversationBase paramConversationBase) {}
  
  public boolean needHoldCache()
  {
    if (this$0.mMsgListView != null) {
      return this$0.mMsgListView.isDataSetChanged();
    }
    return false;
  }
  
  public void onContentChanged(MessageListAdapter paramMessageListAdapter)
  {
    if (this$0.mHandler != null)
    {
      this$0.mHandler.removeCallbacks(ConversationBase.access$1200(this$0));
      this$0.mHandler.postDelayed(ConversationBase.access$1200(this$0), 50L);
    }
  }
  
  public void onDataSetChanged(MessageListAdapter paramMessageListAdapter)
  {
    ConversationBase.access$1102(this$0, true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */