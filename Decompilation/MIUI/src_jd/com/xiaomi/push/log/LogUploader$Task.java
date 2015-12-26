package com.xiaomi.push.log;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;

class LogUploader$Task
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  long timestamp = System.currentTimeMillis();
  
  LogUploader$Task(LogUploader paramLogUploader) {}
  
  public boolean canExcuteNow()
  {
    return true;
  }
  
  final boolean isExpired()
  {
    return System.currentTimeMillis() - timestamp > 172800000L;
  }
  
  public void process() {}
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogUploader.Task
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */