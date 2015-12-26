package com.android.mms;

import android.app.Application;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.ui.TimedMessageExpiredActivity;

class MmsApp$1$1
  extends Thread
{
  MmsApp$1$1(MmsApp.1 param1) {}
  
  public void run()
  {
    Application localApplication = MmsApp.getApp();
    TimedMessageReceiver.scheduleNextTimedMsg(localApplication);
    TimedMessageExpiredActivity.markPrivateExpiredMsgFailed(localApplication);
    TimedMessageExpiredActivity.raiseIfNecessary(localApplication);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */