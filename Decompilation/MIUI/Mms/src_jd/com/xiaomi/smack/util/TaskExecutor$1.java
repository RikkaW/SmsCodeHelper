package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;

final class TaskExecutor$1
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  TaskExecutor$1(Runnable paramRunnable) {}
  
  public void process()
  {
    val$runnable.run();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.TaskExecutor.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */