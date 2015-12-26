package com.android.mms.ui;

import android.util.Log;
import com.android.mms.transaction.MmsSystemEventReceiver.SimStateChangedListener;

class MessageEditableActivityBase$30
  implements MmsSystemEventReceiver.SimStateChangedListener
{
  MessageEditableActivityBase$30(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onSimStateChanged(String paramString)
  {
    Log.d("MessageEditableActivityBase", "update sim state change");
    this$0.updateSlotRelatedState();
    this$0.postUpdateSendButtonState();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.30
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */