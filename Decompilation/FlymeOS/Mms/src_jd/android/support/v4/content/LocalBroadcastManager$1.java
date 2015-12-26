package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class LocalBroadcastManager$1
  extends Handler
{
  LocalBroadcastManager$1(LocalBroadcastManager paramLocalBroadcastManager, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      super.handleMessage(paramMessage);
      return;
    }
    LocalBroadcastManager.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */