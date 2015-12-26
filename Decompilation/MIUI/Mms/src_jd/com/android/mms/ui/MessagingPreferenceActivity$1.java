package com.android.mms.ui;

import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.utils.MxActivateSimpleDialog.ActivationCallBack;

class MessagingPreferenceActivity$1
  implements MxActivateSimpleDialog.ActivationCallBack
{
  MessagingPreferenceActivity$1(MessagingPreferenceActivity paramMessagingPreferenceActivity, int paramInt) {}
  
  public void onResult(int paramInt)
  {
    if (paramInt != 1) {
      MxActivateService.setEnableAfterActivation(val$slotId, true);
    }
    MessagingPreferenceActivity.access$100(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessagingPreferenceActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */