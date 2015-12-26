package com.android.mms.ui;

import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class MultiSimPreferenceAcitvity$1
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  MultiSimPreferenceAcitvity$1(MultiSimPreferenceAcitvity paramMultiSimPreferenceAcitvity) {}
  
  public void onSubscriptionsChanged()
  {
    if (!MSimUtils.isMSimInserted())
    {
      Log.d("MultiSimPreferenceAcitvity", "onChange not multi sim is inserted");
      this$0.finish();
      return;
    }
    Log.d("MultiSimPreferenceAcitvity", "onChange update sim state");
    MultiSimPreferenceAcitvity.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultiSimPreferenceAcitvity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */