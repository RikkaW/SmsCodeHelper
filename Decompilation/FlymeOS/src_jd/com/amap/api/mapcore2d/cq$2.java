package com.amap.api.mapcore2d;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class cq$2
  extends FutureTask<Result>
{
  cq$2(cq paramcq, Callable paramCallable)
  {
    super(paramCallable);
  }
  
  protected void done()
  {
    try
    {
      cq.b(a, cq.b(a).get());
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      cy.a(localInterruptedException, "AsyncTask", "done");
      Log.w("AsyncTask", localInterruptedException);
      return;
    }
    catch (ExecutionException localExecutionException)
    {
      cy.a(localExecutionException, "AsyncTask", "done");
      throw new RuntimeException("An error occured while executing doInBackground()", localExecutionException.getCause());
    }
    catch (CancellationException localCancellationException)
    {
      cy.a(localCancellationException, "AsyncTask", "done");
      cq.b(a, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cq.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */