package com.android.mms.ui;

import android.app.Activity;

class ConversationFragment$2
  implements MessageListPullView.ScrolledListener
{
  ConversationFragment$2(ConversationFragment paramConversationFragment) {}
  
  public void onScrolled(int paramInt)
  {
    if ((!ConversationFragment.access$900(this$0)) && (paramInt > ConversationFragment.access$1000()) && (((MmsTabActivity)this$0.mActivity).enterPrivateActivity()))
    {
      ConversationFragment.access$902(this$0, true);
      this$0.mActivity.overridePendingTransition(2131034118, 17432577);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */