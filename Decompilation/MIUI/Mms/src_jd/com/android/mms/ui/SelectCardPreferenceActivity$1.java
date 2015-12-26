package com.android.mms.ui;

import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class SelectCardPreferenceActivity$1
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  SelectCardPreferenceActivity$1(SelectCardPreferenceActivity paramSelectCardPreferenceActivity) {}
  
  public void onSubscriptionsChanged()
  {
    if (!MSimUtils.isMSimInserted())
    {
      Log.d("SelectCardPreferenceActivity", "onChange not multi sim is inserted");
      this$0.finish();
      return;
    }
    Log.d("SelectCardPreferenceActivity", "onChange update sim state");
    SelectCardPreferenceActivity.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SelectCardPreferenceActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */