package com.android.mms.ui;

import android.util.Log;
import com.android.mms.util.MSimUtils;
import miui.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

class MessageEditableActivityBase$29
  implements SubscriptionManager.OnSubscriptionsChangedListener
{
  MessageEditableActivityBase$29(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onSubscriptionsChanged()
  {
    int i = 1;
    long l1;
    long l2;
    if (MSimUtils.isMSimInserted())
    {
      l1 = MSimUtils.getSimIdBySlotId(0);
      l2 = MSimUtils.getSimIdBySlotId(1);
      if ((MessageEditableActivityBase.access$2500(this$0) == l1) && (MessageEditableActivityBase.access$2600(this$0) == l2)) {
        i = 0;
      }
    }
    while (i != 0)
    {
      Log.d("MessageEditableActivityBase", "update sim info change");
      this$0.updateSlotRelatedState();
      this$0.postUpdateSendButtonState();
      this$0.onChildSimInfoChanged();
      return;
      MessageEditableActivityBase.access$2502(this$0, l1);
      MessageEditableActivityBase.access$2602(this$0, l2);
      continue;
      MessageEditableActivityBase.access$2502(this$0, -1L);
      MessageEditableActivityBase.access$2602(this$0, -1L);
    }
    this$0.updateSlotButtonInfo();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.29
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */