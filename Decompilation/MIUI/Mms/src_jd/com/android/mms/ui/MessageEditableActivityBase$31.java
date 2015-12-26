package com.android.mms.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mms.transaction.PushSession;

class MessageEditableActivityBase$31
  extends BroadcastReceiver
{
  MessageEditableActivityBase$31(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.AIRPLANE_MODE".equals(paramIntent.getAction()))
    {
      this$0.mAirModeOn = paramIntent.getBooleanExtra("state", false);
      this$0.postUpdateSendButtonState();
      return;
    }
    if (PushSession.getInstance(paramContext).getConnectedSimIndex() > 0) {
      MessageEditableActivityBase.access$2702(this$0, true);
    }
    this$0.onPushStatusChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.31
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */