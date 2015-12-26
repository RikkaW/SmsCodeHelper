package com.xiaomi.mms.mx.fw.misc;

import android.os.Handler;
import android.os.Message;
import com.xiaomi.mms.mx.utils.Log;
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
    while (!SerializedAsyncTaskProcessor.access$100(this$0)) {
      try
      {
        SerializedAsyncTaskProcessor.access$202(this$0, (SerializedAsyncTaskProcessor.SerializedAsyncTask)mTasks.poll(1L, TimeUnit.SECONDS));
        if (SerializedAsyncTaskProcessor.access$200(this$0) != null)
        {
          Message localMessage = SerializedAsyncTaskProcessor.access$300(this$0).obtainMessage(0, SerializedAsyncTaskProcessor.access$200(this$0));
          SerializedAsyncTaskProcessor.access$300(this$0).sendMessage(localMessage);
          SerializedAsyncTaskProcessor.access$200(this$0).process();
          localMessage = SerializedAsyncTaskProcessor.access$300(this$0).obtainMessage(1, SerializedAsyncTaskProcessor.access$200(this$0));
          SerializedAsyncTaskProcessor.access$300(this$0).sendMessage(localMessage);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("SerializedAsyncTaskProcessor", localInterruptedException.toString());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.ProcessPackageThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */