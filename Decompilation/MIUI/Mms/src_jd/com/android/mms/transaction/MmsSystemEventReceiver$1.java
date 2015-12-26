package com.android.mms.transaction;

import android.database.ContentObserver;
import android.os.Handler;
import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;

class MmsSystemEventReceiver$1
  extends ContentObserver
{
  MmsSystemEventReceiver$1(MmsSystemEventReceiver paramMmsSystemEventReceiver, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    LogTag.verbose("mobile data or enable mms is changed", new Object[0]);
    int i = 0;
    while (i < MSimUtils.getMultiSimCount())
    {
      MmsSystemEventReceiver.access$000(this$0, i);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsSystemEventReceiver.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */