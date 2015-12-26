package com.xiaomi.mms.transaction;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public abstract class WakenService
  extends Service
{
  private static final Object WAKE_LOCK_SYNCER = new Object();
  private static PowerManager.WakeLock mWakeLock;
  private HandlerThread mHandlerThread;
  private Handler mWorkHandler;
  
  public static void beginStartingService(Context paramContext, Intent paramIntent)
  {
    synchronized (WAKE_LOCK_SYNCER)
    {
      if (mWakeLock == null)
      {
        mWakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "WakenService");
        mWakeLock.setReferenceCounted(false);
      }
      mWakeLock.acquire();
      paramContext.startService(paramIntent);
      return;
    }
  }
  
  public static void finishStartingService(Service paramService, int paramInt)
  {
    synchronized (WAKE_LOCK_SYNCER)
    {
      if ((mWakeLock != null) && (paramService.stopSelfResult(paramInt))) {
        mWakeLock.release();
      }
      return;
    }
  }
  
  protected abstract void handleIntent(Intent paramIntent);
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    mHandlerThread = new HandlerThread("WakeServiceWorkThread");
    mHandlerThread.start();
    mWorkHandler = new WorkHandler(mHandlerThread.getLooper());
  }
  
  public void onDestroy()
  {
    mHandlerThread.quit();
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null) {
      return 2;
    }
    Message localMessage = mWorkHandler.obtainMessage();
    arg1 = paramInt2;
    obj = paramIntent;
    mWorkHandler.sendMessage(localMessage);
    return 2;
  }
  
  private class WorkHandler
    extends Handler
  {
    public WorkHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      handleIntent((Intent)obj);
      WakenService.finishStartingService(WakenService.this, arg1);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.WakenService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */