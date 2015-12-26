package android.support.v4.content;

import java.util.concurrent.CountDownLatch;

final class AsyncTaskLoader$LoadTask
  extends ModernAsyncTask<Void, Void, D>
  implements Runnable
{
  private CountDownLatch done = new CountDownLatch(1);
  D result;
  boolean waiting;
  
  AsyncTaskLoader$LoadTask(AsyncTaskLoader paramAsyncTaskLoader) {}
  
  protected D doInBackground(Void... paramVarArgs)
  {
    result = this$0.onLoadInBackground();
    return (D)result;
  }
  
  protected void onCancelled()
  {
    try
    {
      this$0.dispatchOnCancelled(this, result);
      return;
    }
    finally
    {
      done.countDown();
    }
  }
  
  protected void onPostExecute(D paramD)
  {
    try
    {
      this$0.dispatchOnLoadComplete(this, paramD);
      return;
    }
    finally
    {
      done.countDown();
    }
  }
  
  public void run()
  {
    waiting = false;
    this$0.executePendingTask();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.AsyncTaskLoader.LoadTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */