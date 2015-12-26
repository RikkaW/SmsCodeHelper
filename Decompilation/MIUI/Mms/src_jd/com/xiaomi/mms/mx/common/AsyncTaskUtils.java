package com.xiaomi.mms.mx.common;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;

@SuppressLint({"NewApi"})
public abstract class AsyncTaskUtils
{
  public static <Params, Progress, Result> void exe(int paramInt, AsyncTask<Params, Progress, Result> paramAsyncTask, Params... paramVarArgs)
  {
    if ((Build.VERSION.SDK_INT >= 11) && (CommonApplication.getExecutorByLevel(paramInt) != null))
    {
      paramAsyncTask.executeOnExecutor(CommonApplication.getExecutorByLevel(paramInt), paramVarArgs);
      return;
    }
    paramAsyncTask.execute(paramVarArgs);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.common.AsyncTaskUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */