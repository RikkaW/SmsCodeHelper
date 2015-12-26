package com.ted.android.core;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import aqh;
import bo;
import com.ted.android.data.SmsEntity;
import com.ted.android.utils.TedSDKLog;
import java.util.Stack;

public class SmsEntityLoader
{
  private static final int BUBBLE_LOADED_MSG = 1;
  private static boolean DEBUG = true;
  private static final int START_LOADING_MSG = 2;
  private static final String TAG = SmsEntityLoader.class.getSimpleName();
  private static SmsEntityLoader mInstance;
  private aqh cache;
  private long currentHandlingMsgId = -1L;
  private int finished;
  private bo helper;
  private boolean isParsing = false;
  private boolean isStarted = false;
  private OnLoadingStatusListener loadingStatusListener;
  private Context mContext;
  private Handler mLoaderHandler;
  private HandlerThread mLoaderThread;
  private boolean mStop = false;
  private c mUiHandler;
  private Stack<SmsInfo> missingSmsEntity = new Stack();
  
  private SmsEntityLoader(Context paramContext)
  {
    mUiHandler = new c(paramContext.getMainLooper());
    mContext = paramContext;
    cache = aqh.a();
    mLoaderThread = new HandlerThread("sms_entity_loader");
    mLoaderThread.setUncaughtExceptionHandler(new b(null));
    mLoaderThread.start();
    mLoaderHandler = new a(mLoaderThread.getLooper());
    helper = bo.a(mContext.getApplicationContext());
  }
  
  private void checkThreadIsLiving()
  {
    if ((mLoaderThread == null) || (!mLoaderThread.isAlive()))
    {
      mLoaderThread = new HandlerThread("sms_entity_loader");
      mLoaderThread.start();
      mLoaderHandler = new a(mLoaderThread.getLooper());
      TedSDKLog.d(TAG, "============Thread is restarted!!!!========");
    }
  }
  
  public static SmsEntityLoader getInstance(Context paramContext)
  {
    if (mInstance == null) {}
    try
    {
      mInstance = new SmsEntityLoader(paramContext.getApplicationContext());
      return mInstance;
    }
    finally {}
  }
  
  private void notifyQuery()
  {
    if (!missingSmsEntity.empty())
    {
      localSmsInfo = (SmsInfo)missingSmsEntity.pop();
      if (!cache.a(msgId))
      {
        startLoading(localSmsInfo);
        if (loadingStatusListener != null) {
          loadingStatusListener.onUpdate(missingSmsEntity.size(), finished);
        }
      }
    }
    while (loadingStatusListener == null)
    {
      SmsInfo localSmsInfo;
      return;
    }
    loadingStatusListener.onCompleted();
  }
  
  public boolean isStop()
  {
    return mStop;
  }
  
  public SmsEntity loadSmsEntity(long paramLong1, String paramString1, String paramString2, long paramLong2, SmsEntityLoaderCallback paramSmsEntityLoaderCallback)
  {
    TedSDKLog.begin(TAG);
    if (TextUtils.isEmpty(paramString1)) {
      localObject = null;
    }
    SmsEntity localSmsEntity;
    do
    {
      return (SmsEntity)localObject;
      localSmsEntity = (SmsEntity)cache.get(Long.valueOf(paramLong1));
      localObject = localSmsEntity;
    } while (localSmsEntity != null);
    if (cache.a(Long.valueOf(paramLong1))) {
      return null;
    }
    Object localObject = helper.a(paramLong1);
    if (localObject != null)
    {
      cache.a(Long.valueOf(paramLong1), (SmsEntity)localObject);
      ((SmsEntity)localObject).setBody(paramString1);
      return (SmsEntity)localObject;
    }
    if (currentHandlingMsgId == paramLong1) {
      return null;
    }
    localObject = new SmsInfo();
    body = paramString1;
    msgId = Long.valueOf(paramLong1);
    number = paramString2;
    callback = paramSmsEntityLoaderCallback;
    date = paramLong2;
    if (missingSmsEntity.contains(localObject)) {
      missingSmsEntity.remove(localObject);
    }
    missingSmsEntity.push(localObject);
    if (!isParsing) {
      notifyQuery();
    }
    return null;
  }
  
  public void quit()
  {
    stop();
  }
  
  public void setOnLoadingStatusListener(OnLoadingStatusListener paramOnLoadingStatusListener)
  {
    loadingStatusListener = paramOnLoadingStatusListener;
  }
  
  public void setSmsEntityLoaderCallback(SmsEntityLoaderCallback paramSmsEntityLoaderCallback) {}
  
  public void start() {}
  
  public void startLoading(SmsInfo paramSmsInfo)
  {
    if (paramSmsInfo == null) {}
    do
    {
      do
      {
        return;
        checkThreadIsLiving();
        TedSDKLog.d(TAG, "Start loading: " + msgId);
        currentHandlingMsgId = msgId.longValue();
        paramSmsInfo = new d(paramSmsInfo);
        Message localMessage = mLoaderHandler.obtainMessage(2);
        obj = paramSmsInfo;
        mLoaderHandler.sendMessage(localMessage);
      } while (isStarted);
      isStarted = true;
    } while (loadingStatusListener == null);
    loadingStatusListener.onStart();
  }
  
  public void stop()
  {
    mUiHandler.a();
    missingSmsEntity.removeAllElements();
  }
  
  public static abstract interface OnLoadingStatusListener
  {
    public abstract void onCompleted();
    
    public abstract void onStart();
    
    public abstract void onUpdate(int paramInt1, int paramInt2);
  }
  
  public static abstract interface SmsEntityLoaderCallback
  {
    public abstract void onSmsEntityLoaded(Long paramLong, SmsEntity paramSmsEntity);
  }
  
  public static class SmsInfo
  {
    public String body;
    public SmsEntityLoader.SmsEntityLoaderCallback callback;
    public long date;
    public Long msgId;
    public String number;
    
    public boolean equals(Object paramObject)
    {
      if ((paramObject instanceof SmsInfo))
      {
        paramObject = (SmsInfo)paramObject;
        return msgId.equals(msgId);
      }
      return false;
    }
    
    public int hashCode()
    {
      return msgId.intValue();
    }
  }
  
  class a
    extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      default: 
        return;
      }
      ((SmsEntityLoader.d)obj).run();
    }
  }
  
  class b
    implements Thread.UncaughtExceptionHandler
  {
    private b() {}
    
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
      TedSDKLog.d(SmsEntityLoader.TAG, paramThrowable.getMessage());
      isParsing = false;
      TedSDKLog.d(SmsEntityLoader.TAG, "The thread is crashed. Will restart soon!");
    }
  }
  
  class c
    extends Handler
  {
    public c(Looper paramLooper)
    {
      super();
    }
    
    public void a()
    {
      removeMessages(1);
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (what)
      {
      }
      do
      {
        return;
        SmsEntityLoader localSmsEntityLoader = SmsEntityLoader.this;
        finished += 1;
        TedSDKLog.d(SmsEntityLoader.TAG, "====== LoaderUiHandler handleMessage start =====");
      } while (mStop);
      paramMessage = (SmsEntityLoader.d)obj;
      if (paramMessage == null)
      {
        TedSDKLog.d(SmsEntityLoader.TAG, "LoaderUiHandler handleMessage fetcher is null");
        return;
      }
      cache.a(amsgId, SmsEntityLoader.d.b(paramMessage));
      if (acallback != null) {
        acallback.onSmsEntityLoaded(amsgId, SmsEntityLoader.d.b(paramMessage));
      }
      for (;;)
      {
        currentHandlingMsgId = -1L;
        TedSDKLog.d(SmsEntityLoader.TAG, "====== LoaderUiHandler handleMessage end =====" + amsgId);
        SmsEntityLoader.this.notifyQuery();
        return;
        TedSDKLog.d(SmsEntityLoader.TAG, "LoaderUiHandler handleMessage callback is null!");
      }
    }
  }
  
  class d
    implements Runnable
  {
    private SmsEntityLoader.SmsInfo b;
    private SmsEntity c;
    
    public d(SmsEntityLoader.SmsInfo paramSmsInfo)
    {
      b = paramSmsInfo;
    }
    
    private void a()
    {
      Message localMessage = new Message();
      what = 1;
      obj = this;
      mUiHandler.sendMessage(localMessage);
    }
    
    public void run()
    {
      isParsing = true;
      if (mStop) {}
      while (Thread.interrupted()) {
        return;
      }
      c = SmsParserEngine.getInstance(mContext).parseMessage(b.msgId.longValue(), b.body, b.number, b.date);
      isParsing = false;
      if (c != null) {
        helper.a(c);
      }
      a();
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */