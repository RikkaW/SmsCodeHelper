package com.meizu.common.app;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

public class SlideNoticeManagerService
{
  private static final boolean DBG = true;
  private static final int LONG_DELAY = 3500;
  private static final int MESSAGE_TIMEOUT = 1;
  private static final int SHORT_DELAY = 2000;
  private static final String TAG = "SlideNoticeManagerService";
  private Activity mCurActivity;
  private WorkerHandler mHandler = new WorkerHandler(null);
  private ArrayList<NoticeRecord> mNoticeQueue = new ArrayList();
  
  private void cancelNotice(int paramInt)
  {
    Log.d("SlideNoticeManagerService", "cancelNotice index=" + paramInt);
    mNoticeQueue.get(paramInt)).callback.hide();
    mNoticeQueue.remove(paramInt);
    if (mNoticeQueue.size() > 0) {
      showNextNotice();
    }
  }
  
  private void handleTimeout(NoticeRecord paramNoticeRecord)
  {
    Log.d("SlideNoticeManagerService", "Timeout callback=" + callback);
    synchronized (mNoticeQueue)
    {
      int i = indexOfNotice(callback);
      if (i >= 0) {
        cancelNotice(i);
      }
      return;
    }
  }
  
  private int indexOfNotice(NoticeCallBack paramNoticeCallBack)
  {
    ArrayList localArrayList = mNoticeQueue;
    int i = 0;
    while (i < localArrayList.size())
    {
      if (getcallback == paramNoticeCallBack) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private void removeLastActivityRecord()
  {
    ArrayList localArrayList = mNoticeQueue;
    int j;
    for (int i = 0; i < localArrayList.size(); i = j + 1)
    {
      j = i;
      if (getactivity != null)
      {
        localArrayList.remove(i);
        j = i - 1;
      }
    }
  }
  
  private void scheduleTimeout(NoticeRecord paramNoticeRecord)
  {
    mHandler.removeCallbacksAndMessages(paramNoticeRecord);
    Message localMessage = Message.obtain(mHandler, 1, paramNoticeRecord);
    if (duration == 1) {}
    for (long l = 3500L;; l = 2000L)
    {
      mHandler.sendMessageDelayed(localMessage, l);
      return;
    }
  }
  
  private void showNextNotice()
  {
    NoticeRecord localNoticeRecord = (NoticeRecord)mNoticeQueue.get(0);
    if (localNoticeRecord != null) {}
    try
    {
      Log.d("SlideNoticeManagerService", "Show callback=" + callback);
      callback.show();
      scheduleTimeout(localNoticeRecord);
      return;
    }
    catch (Exception localException)
    {
      Log.e("SlideNoticeManagerService", "catch an exception when showing next notice, it will be romoved from queue", localException);
      int i = mNoticeQueue.indexOf(localNoticeRecord);
      if (i >= 0) {
        mNoticeQueue.remove(i);
      }
      if (mNoticeQueue.size() <= 0) {}
    }
    for (localNoticeRecord = (NoticeRecord)mNoticeQueue.get(0);; localNoticeRecord = null) {
      break;
    }
  }
  
  public void cancelNotice(NoticeCallBack paramNoticeCallBack)
  {
    if (paramNoticeCallBack == null) {
      return;
    }
    for (;;)
    {
      synchronized (mNoticeQueue)
      {
        int i = indexOfNotice(paramNoticeCallBack);
        if (i >= 0)
        {
          mHandler.removeCallbacksAndMessages(mNoticeQueue.get(i));
          cancelNotice(i);
          return;
        }
      }
      Log.w("SlideNoticeManagerService", "Notice already cancelled. callback=" + paramNoticeCallBack);
    }
  }
  
  public void enqueueNotice(CharSequence paramCharSequence, NoticeCallBack paramNoticeCallBack, int paramInt)
  {
    Log.i("SlideNoticeManagerService", "enqueueNotice callback=" + paramNoticeCallBack + " message=" + paramCharSequence + " duration=" + paramInt);
    if (paramNoticeCallBack == null) {
      return;
    }
    synchronized (mNoticeQueue)
    {
      i = indexOfNotice(paramNoticeCallBack);
      if (i >= 0)
      {
        ((NoticeRecord)mNoticeQueue.get(i)).update(paramInt);
        paramInt = i;
        if (paramInt == 0) {
          showNextNotice();
        }
        return;
      }
    }
    if ((mNoticeQueue.size() > 0) && (TextUtils.equals(paramCharSequence, mNoticeQueue.get(mNoticeQueue.size() - 1)).message))) {}
    for (int i = 1;; i = 0)
    {
      if (i == 0)
      {
        paramCharSequence = new NoticeRecord(paramCharSequence, paramInt, paramNoticeCallBack);
        mNoticeQueue.add(paramCharSequence);
      }
      paramInt = mNoticeQueue.size();
      paramInt -= 1;
      break;
    }
  }
  
  public void enqueueNoticeInActivity(CharSequence paramCharSequence, NoticeCallBack paramNoticeCallBack, int paramInt, Activity paramActivity)
  {
    Log.i("SlideNoticeManagerService", "enqueueNoticeInActivity callback=" + paramNoticeCallBack + " message=" + paramCharSequence + " duration=" + paramInt + " activity=" + paramActivity);
    if ((paramNoticeCallBack == null) || (paramActivity == null)) {
      return;
    }
    synchronized (mNoticeQueue)
    {
      if (mCurActivity != paramActivity)
      {
        mCurActivity = paramActivity;
        removeLastActivityRecord();
      }
      i = indexOfNotice(paramNoticeCallBack);
      if (i >= 0)
      {
        ((NoticeRecord)mNoticeQueue.get(i)).update(paramInt);
        paramInt = i;
        if (paramInt == 0) {
          showNextNotice();
        }
        return;
      }
    }
    if ((mNoticeQueue.size() > 0) && (TextUtils.equals(paramCharSequence, mNoticeQueue.get(mNoticeQueue.size() - 1)).message))) {}
    for (int i = 1;; i = 0)
    {
      if (i == 0)
      {
        paramCharSequence = new NoticeRecord(paramCharSequence, paramInt, paramNoticeCallBack, paramActivity);
        mNoticeQueue.add(paramCharSequence);
      }
      paramInt = mNoticeQueue.size();
      paramInt -= 1;
      break;
    }
  }
  
  public static abstract interface NoticeCallBack
  {
    public abstract void hide();
    
    public abstract void show();
  }
  
  static final class NoticeRecord
  {
    final Activity activity;
    final SlideNoticeManagerService.NoticeCallBack callback;
    int duration;
    CharSequence message;
    
    NoticeRecord(CharSequence paramCharSequence, int paramInt, SlideNoticeManagerService.NoticeCallBack paramNoticeCallBack)
    {
      message = paramCharSequence;
      duration = paramInt;
      callback = paramNoticeCallBack;
      activity = null;
    }
    
    NoticeRecord(CharSequence paramCharSequence, int paramInt, SlideNoticeManagerService.NoticeCallBack paramNoticeCallBack, Activity paramActivity)
    {
      message = paramCharSequence;
      duration = paramInt;
      callback = paramNoticeCallBack;
      activity = paramActivity;
    }
    
    public final String toString()
    {
      return "NoticeRecord{" + Integer.toHexString(System.identityHashCode(this)) + " callback=" + callback + " duration=" + duration;
    }
    
    void update(int paramInt)
    {
      duration = paramInt;
    }
  }
  
  final class WorkerHandler
    extends Handler
  {
    private WorkerHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        return;
      }
      SlideNoticeManagerService.this.handleTimeout((SlideNoticeManagerService.NoticeRecord)obj);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNoticeManagerService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */