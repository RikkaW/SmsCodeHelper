package com.android.mms.ui;

import android.util.Log;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class MessagingPreferenceActivity$2
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  MessagingPreferenceActivity$2(MessagingPreferenceActivity paramMessagingPreferenceActivity) {}
  
  public void onSubscriptionsChanged()
  {
    Log.d("MessagingPreferenceActivity", "update sim info change");
    MessagingPreferenceActivity.access$600(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */