package com.android.mms.ui;

import android.util.Log;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class MessagingAdvancedPreferenceActivity$1
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  MessagingAdvancedPreferenceActivity$1(MessagingAdvancedPreferenceActivity paramMessagingAdvancedPreferenceActivity) {}
  
  public void onSubscriptionsChanged()
  {
    Log.d("MessagingAdvancedPreferenceActivity", "update sim info change");
    MessagingAdvancedPreferenceActivity.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingAdvancedPreferenceActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */