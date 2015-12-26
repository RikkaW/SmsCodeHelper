package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;

public class TaskExecutor
{
  private static SerializedAsyncTaskProcessor mAsyncProcessor = new SerializedAsyncTaskProcessor(true, 20);
  
  public static void execute(SerializedAsyncTaskProcessor.SerializedAsyncTask paramSerializedAsyncTask)
  {
    mAsyncProcessor.addNewTask(paramSerializedAsyncTask);
  }
  
  public static void execute(SerializedAsyncTaskProcessor.SerializedAsyncTask paramSerializedAsyncTask, long paramLong)
  {
    mAsyncProcessor.addNewTaskWithDelayed(paramSerializedAsyncTask, paramLong);
  }
  
  public static void execute(Runnable paramRunnable)
  {
    mAsyncProcessor.addNewTask(new SerializedAsyncTaskProcessor.SerializedAsyncTask()
    {
      public void process()
      {
        val$runnable.run();
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.TaskExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */