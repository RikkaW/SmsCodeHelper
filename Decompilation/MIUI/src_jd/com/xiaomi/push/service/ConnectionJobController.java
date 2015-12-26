package com.xiaomi.push.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectionJobController
  extends HandlerThread
{
  private volatile boolean executing = false;
  private volatile long lastJob = 0L;
  private volatile Handler mHandler;
  private List<Pair<XMPushService.Job, Long>> mPendingJob = new ArrayList();
  
  public ConnectionJobController(String paramString)
  {
    super(paramString);
  }
  
  public void executeJobDelayed(XMPushService.Job paramJob, long paramLong)
  {
    synchronized (mPendingJob)
    {
      if (mHandler != null)
      {
        Message localMessage = Message.obtain();
        what = type;
        obj = paramJob;
        mHandler.sendMessageDelayed(localMessage, paramLong);
        return;
      }
      MyLog.warn("the job is pended, the controller is not ready.");
      mPendingJob.add(new Pair(paramJob, Long.valueOf(paramLong)));
    }
  }
  
  public boolean hasJob(int paramInt)
  {
    if (mHandler != null) {
      return mHandler.hasMessages(paramInt);
    }
    return false;
  }
  
  public boolean isBlocked()
  {
    return (executing) && (System.currentTimeMillis() - lastJob > 600000L);
  }
  
  protected void onLooperPrepared()
  {
    mHandler = new Handler(getLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        ConnectionJobController.access$002(ConnectionJobController.this, true);
        ConnectionJobController.access$102(ConnectionJobController.this, System.currentTimeMillis());
        if ((obj instanceof XMPushService.Job)) {
          ((XMPushService.Job)obj).run();
        }
        ConnectionJobController.access$002(ConnectionJobController.this, false);
      }
    };
    synchronized (mPendingJob)
    {
      Iterator localIterator = mPendingJob.iterator();
      if (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        MyLog.warn("executing the pending job.");
        executeJobDelayed((XMPushService.Job)first, ((Long)second).longValue());
      }
    }
    mPendingJob.clear();
  }
  
  public void removeAllJobs()
  {
    int i = 1;
    while (i < 15)
    {
      removeJobs(i);
      i += 1;
    }
  }
  
  public void removeJobs(int paramInt)
  {
    if (mHandler != null) {
      mHandler.removeMessages(paramInt);
    }
  }
  
  public void removeJobs(int paramInt, Object paramObject)
  {
    if (mHandler != null) {
      mHandler.removeMessages(paramInt, paramObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ConnectionJobController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */