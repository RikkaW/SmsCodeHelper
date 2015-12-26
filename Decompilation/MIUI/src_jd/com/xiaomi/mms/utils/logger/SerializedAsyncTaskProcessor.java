package com.xiaomi.mms.utils.logger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SerializedAsyncTaskProcessor
{
  private volatile SerializedAsyncTask mCurrentTask;
  private final boolean mIsDaemon;
  private Handler mMainThreadHandler = null;
  private ProcessPackageThread mProcessThread;
  private volatile boolean threadQuit = false;
  
  public SerializedAsyncTaskProcessor()
  {
    this(false);
  }
  
  public SerializedAsyncTaskProcessor(boolean paramBoolean)
  {
    mIsDaemon = paramBoolean;
  }
  
  public void addNewTask(SerializedAsyncTask paramSerializedAsyncTask)
  {
    try
    {
      if (mProcessThread == null)
      {
        mProcessThread = new ProcessPackageThread();
        mProcessThread.setDaemon(mIsDaemon);
        mProcessThread.start();
      }
      mProcessThread.insertTask(paramSerializedAsyncTask);
      return;
    }
    finally {}
  }
  
  private class ProcessPackageThread
    extends Thread
  {
    private final LinkedBlockingQueue<SerializedAsyncTaskProcessor.SerializedAsyncTask> mTasks = new LinkedBlockingQueue();
    
    public ProcessPackageThread()
    {
      super();
    }
    
    public void insertTask(SerializedAsyncTaskProcessor.SerializedAsyncTask paramSerializedAsyncTask)
    {
      mTasks.add(paramSerializedAsyncTask);
    }
    
    public void run()
    {
      while (!threadQuit) {
        try
        {
          SerializedAsyncTaskProcessor.access$202(SerializedAsyncTaskProcessor.this, (SerializedAsyncTaskProcessor.SerializedAsyncTask)mTasks.poll(1L, TimeUnit.SECONDS));
          if (mCurrentTask != null)
          {
            Message localMessage = mMainThreadHandler.obtainMessage(0, mCurrentTask);
            mMainThreadHandler.sendMessage(localMessage);
            mCurrentTask.process();
            localMessage = mMainThreadHandler.obtainMessage(1, mCurrentTask);
            mMainThreadHandler.sendMessage(localMessage);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          MyLog.e(localInterruptedException);
        }
      }
    }
  }
  
  public static abstract class SerializedAsyncTask
  {
    public void postProcess() {}
    
    public void preProcess() {}
    
    public abstract void process();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.SerializedAsyncTaskProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */