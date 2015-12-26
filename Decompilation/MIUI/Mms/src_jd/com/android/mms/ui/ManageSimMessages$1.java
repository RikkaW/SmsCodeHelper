package com.android.mms.ui;

import android.util.Log;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class ManageSimMessages$1
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  ManageSimMessages$1(ManageSimMessages paramManageSimMessages) {}
  
  public void onSubscriptionsChanged()
  {
    Log.d("ManageSimMessages", "update sim info change");
    ManageSimMessages.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ManageSimMessages.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */