package com.android.mms.ui;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

class SimMessagesFragment$2
  extends ContentObserver
{
  SimMessagesFragment$2(SimMessagesFragment paramSimMessagesFragment, Handler paramHandler)
  {
    super(paramHandler);
  }
  
  public void onChange(boolean paramBoolean)
  {
    Log.d("SimMessagesFragment", "sim message change");
    SimMessagesFragment.access$1100(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SimMessagesFragment.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */