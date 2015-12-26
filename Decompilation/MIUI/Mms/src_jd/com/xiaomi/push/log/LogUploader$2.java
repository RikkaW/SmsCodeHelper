package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import java.util.concurrent.ConcurrentLinkedQueue;

class LogUploader$2
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  SerializedAsyncTaskProcessor.SerializedAsyncTask current;
  
  LogUploader$2(LogUploader paramLogUploader) {}
  
  public void postProcess()
  {
    if (current != null) {
      current.postProcess();
    }
  }
  
  public void process()
  {
    LogUploader.Task localTask = (LogUploader.Task)LogUploader.access$100(this$0).peek();
    if ((localTask != null) && (localTask.canExcuteNow()))
    {
      current = ((SerializedAsyncTaskProcessor.SerializedAsyncTask)LogUploader.access$100(this$0).remove());
      current.process();
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogUploader.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */