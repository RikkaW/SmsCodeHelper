package com.xiaomi.mms.mx.common;

import com.xiaomi.mms.mx.utils.Log;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

final class CommonApplication$3
  implements RejectedExecutionHandler
{
  CommonApplication$3(ThreadPoolExecutor paramThreadPoolExecutor) {}
  
  public void rejectedExecution(Runnable paramRunnable, ThreadPoolExecutor paramThreadPoolExecutor)
  {
    Log.v("CommonApplication", "Thread pool executor: reject work, put into backup pool");
    val$backupExe.execute(paramRunnable);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.common.CommonApplication.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */