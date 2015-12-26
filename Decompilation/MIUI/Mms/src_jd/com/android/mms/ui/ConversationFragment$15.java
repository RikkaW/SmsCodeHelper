package com.android.mms.ui;

import com.android.mms.util.MSimUtils;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class ConversationFragment$15
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  ConversationFragment$15(ConversationFragment paramConversationFragment) {}
  
  public void onSubscriptionsChanged()
  {
    boolean bool = MSimUtils.isMSimInserted();
    if (ConversationFragment.access$2700(this$0) != bool)
    {
      ConversationFragment.access$2702(this$0, bool);
      this$0.startAsyncQuery(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */