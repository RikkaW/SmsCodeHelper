package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class SerializedAsyncTaskProcessor
{
  private volatile SerializedAsyncTask mCurrentTask;
  private final boolean mIsDaemon;
  private int mKeepAliveTime = 0;
  private Handler mMainThreadHandler = null;
  private ProcessPackageThread mProcessThread;
  private volatile boolean threadQuit = false;
  
  public SerializedAsyncTaskProcessor()
  {
    this(false);
  }
  
  public SerializedAsyncTaskProcessor(boolean paramBoolean)
  {
    this(paramBoolean, 0);
  }
  
  public SerializedAsyncTaskProcessor(boolean paramBoolean, int paramInt)
  {
    mIsDaemon = paramBoolean;
    mKeepAliveTime = paramInt;
  }
  
  private void stopTaskProcessor()
  {
    try
    {
      mProcessThread = null;
      threadQuit = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void addNewTask(SerializedAsyncTask paramSerializedAsyncTask)
  {
    try
    {
      if (mProcessThread == null)
      {
        mProcessThread = new ProcessPackageThread();
        mProcessThread.setDaemon(mIsDaemon);
        threadQuit = false;
        mProcessThread.start();
      }
      mProcessThread.insertTask(paramSerializedAsyncTask);
      return;
    }
    finally {}
  }
  
  public void addNewTaskWithDelayed(final SerializedAsyncTask paramSerializedAsyncTask, long paramLong)
  {
    mMainThreadHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        addNewTask(paramSerializedAsyncTask);
      }
    }, paramLong);
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
      int i = 1;
      if (mKeepAliveTime > 0) {
        i = mKeepAliveTime;
      }
      while (!threadQuit)
      {
        try
        {
          SerializedAsyncTaskProcessor.access$302(SerializedAsyncTaskProcessor.this, (SerializedAsyncTaskProcessor.SerializedAsyncTask)mTasks.poll(i, TimeUnit.SECONDS));
          if (mCurrentTask == null) {
            break label146;
          }
          Message localMessage = mMainThreadHandler.obtainMessage(0, mCurrentTask);
          mMainThreadHandler.sendMessage(localMessage);
          mCurrentTask.process();
          localMessage = mMainThreadHandler.obtainMessage(1, mCurrentTask);
          mMainThreadHandler.sendMessage(localMessage);
        }
        catch (InterruptedException localInterruptedException)
        {
          MyLog.e(localInterruptedException);
        }
        continue;
        label146:
        if (mKeepAliveTime > 0) {
          SerializedAsyncTaskProcessor.this.stopTaskProcessor();
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
 * Qualified Name:     com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */