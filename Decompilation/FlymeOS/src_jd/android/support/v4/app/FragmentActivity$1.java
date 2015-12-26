package android.support.v4.app;

import android.os.Handler;
import android.os.Message;

class FragmentActivity$1
  extends Handler
{
  FragmentActivity$1(FragmentActivity paramFragmentActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      super.handleMessage(paramMessage);
    case 1: 
      do
      {
        return;
      } while (!this$0.mStopped);
      this$0.doReallyStop(false);
      return;
    }
    this$0.onResumeFragments();
    this$0.mFragments.execPendingActions();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */