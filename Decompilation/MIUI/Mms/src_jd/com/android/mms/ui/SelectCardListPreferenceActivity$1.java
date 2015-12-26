package com.android.mms.ui;

import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class SelectCardListPreferenceActivity$1
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  SelectCardListPreferenceActivity$1(SelectCardListPreferenceActivity paramSelectCardListPreferenceActivity) {}
  
  public void onSubscriptionsChanged()
  {
    if (!MSimUtils.isMSimInserted())
    {
      Log.d("SelectCardListPreferenceActivity", "onChange not multi sim is inserted");
      this$0.finish();
      return;
    }
    Log.d("SelectCardListPreferenceActivity", "onChange update sim state");
    SelectCardListPreferenceActivity.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectCardListPreferenceActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */