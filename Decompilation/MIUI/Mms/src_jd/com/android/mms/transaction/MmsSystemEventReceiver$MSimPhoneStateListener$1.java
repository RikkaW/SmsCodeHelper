package com.android.mms.transaction;

import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;

class MmsSystemEventReceiver$MSimPhoneStateListener$1
  implements Runnable
{
  MmsSystemEventReceiver$MSimPhoneStateListener$1(MmsSystemEventReceiver.MSimPhoneStateListener paramMSimPhoneStateListener) {}
  
  public void run()
  {
    LogTag.verbose("send queued message without toast", new Object[0]);
    MSimUtils.sendQueuedMessageNoToast(MmsSystemEventReceiver.MSimPhoneStateListener.access$100(this$1), MmsSystemEventReceiver.MSimPhoneStateListener.access$200(this$1));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsSystemEventReceiver.MSimPhoneStateListener.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */