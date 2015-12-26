package android.support.v4.content;

import android.os.Handler;
import android.os.Message;

class ModernAsyncTask$InternalHandler
  extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    ModernAsyncTask.AsyncTaskResult localAsyncTaskResult = (ModernAsyncTask.AsyncTaskResult)obj;
    switch (what)
    {
    default: 
      return;
    case 1: 
      ModernAsyncTask.access$500(mTask, mData[0]);
      return;
    }
    mTask.onProgressUpdate(mData);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.ModernAsyncTask.InternalHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */