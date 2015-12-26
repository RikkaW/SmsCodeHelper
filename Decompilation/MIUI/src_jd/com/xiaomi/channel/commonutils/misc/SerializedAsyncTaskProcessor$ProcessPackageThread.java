package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class SerializedAsyncTaskProcessor$ProcessPackageThread
  extends Thread
{
  private final LinkedBlockingQueue<SerializedAsyncTaskProcessor.SerializedAsyncTask> mTasks = new LinkedBlockingQueue();
  
  public SerializedAsyncTaskProcessor$ProcessPackageThread(SerializedAsyncTaskProcessor paramSerializedAsyncTaskProcessor)
  {
    super("PackageProcessor");
  }
  
  public void insertTask(SerializedAsyncTaskProcessor.SerializedAsyncTask paramSerializedAsyncTask)
  {
    mTasks.add(paramSerializedAsyncTask);
  }
  
  public void run()
  {
    int i = 1;
    if (SerializedAsyncTaskProcessor.access$100(this$0) > 0) {
      i = SerializedAsyncTaskProcessor.access$100(this$0);
    }
    while (!SerializedAsyncTaskProcessor.access$200(this$0))
    {
      try
      {
        SerializedAsyncTaskProcessor.access$302(this$0, (SerializedAsyncTaskProcessor.SerializedAsyncTask)mTasks.poll(i, TimeUnit.SECONDS));
        if (SerializedAsyncTaskProcessor.access$300(this$0) == null) {
          break label146;
        }
        Message localMessage = SerializedAsyncTaskProcessor.access$400(this$0).obtainMessage(0, SerializedAsyncTaskProcessor.access$300(this$0));
        SerializedAsyncTaskProcessor.access$400(this$0).sendMessage(localMessage);
        SerializedAsyncTaskProcessor.access$300(this$0).process();
        localMessage = SerializedAsyncTaskProcessor.access$400(this$0).obtainMessage(1, SerializedAsyncTaskProcessor.access$300(this$0));
        SerializedAsyncTaskProcessor.access$400(this$0).sendMessage(localMessage);
      }
      catch (InterruptedException localInterruptedException)
      {
        MyLog.e(localInterruptedException);
      }
      continue;
      label146:
      if (SerializedAsyncTaskProcessor.access$100(this$0) > 0) {
        SerializedAsyncTaskProcessor.access$500(this$0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.ProcessPackageThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */