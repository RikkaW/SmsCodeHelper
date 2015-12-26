package com.android.mms.ui;

import android.os.Handler;

class ConversationFragment$9
  implements ConversationListAdapter.OnContentChangedListener
{
  ConversationFragment$9(ConversationFragment paramConversationFragment) {}
  
  public void onContentChanged(ConversationListAdapter paramConversationListAdapter)
  {
    ConversationFragment.access$600(this$0).removeMessages(1003);
    ConversationFragment.access$600(this$0).sendEmptyMessageDelayed(1003, 50L);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */