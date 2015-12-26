package com.android.mms.transaction;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import miui.mms.IMxStatusService.Stub;

public class MxStatusService
  extends Service
{
  private static HashSet<String> sPendingQueries = new HashSet();
  private volatile int mBinderCount = 0;
  private MxStatuQueryListener mMxStatuQueryListener;
  private MxStatusServiceImpl mService;
  
  private void broadcastStatusToReceiver(String paramString)
  {
    boolean bool = false;
    ??? = MxIdCache.get(paramString, false);
    if (??? != null)
    {
      Intent localIntent = new Intent("com.android.mms.QUERY_MX_STATUS_RESULT");
      if ((((MxIdCache.MxIdCacheItem)???).allowSms()) || (((MxIdCache.MxIdCacheItem)???).allowMms())) {
        bool = true;
      }
      localIntent.putExtra("online", bool);
      localIntent.putExtra("address", paramString);
      sendBroadcast(localIntent, "com.xiaomi.permission.QUERY_MX_STAUTS");
      synchronized (sPendingQueries)
      {
        sPendingQueries.remove(paramString);
        MyLog.d("MxStatusService", "broadcast mx status update");
        return;
      }
    }
    MyLog.d("MxStatusService", "broadcastStatusToReceiver(String address) -> requeryStatus(this, address)");
    MxTaskService.queryStatus(this, paramString);
  }
  
  private int decreaseReference()
  {
    try
    {
      int i = mBinderCount - 1;
      mBinderCount = i;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private int increaseReference()
  {
    try
    {
      int i = mBinderCount + 1;
      mBinderCount = i;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void queryPendingPresence(Context paramContext)
  {
    MyLog.d("MxStatusService", "queryPendingPresence(Context context)");
    ArrayList localArrayList = new ArrayList();
    synchronized (sPendingQueries)
    {
      Iterator localIterator = sPendingQueries.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add((String)localIterator.next());
      }
    }
    ??? = localArrayList.iterator();
    while (((Iterator)???).hasNext()) {
      MxTaskService.queryStatus(paramContext, (String)((Iterator)???).next());
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if (mService == null) {
      mService = new MxStatusServiceImpl(this);
    }
    increaseReference();
    MyLog.d("MxStatusService", "MxStatusService is on bind");
    return mService;
  }
  
  public void onCreate()
  {
    super.onCreate();
    mMxStatuQueryListener = new MxStatuQueryListener(null);
    MxIdCache.addStatusListener(mMxStatuQueryListener);
  }
  
  public void onDestroy()
  {
    MyLog.d("MxStatusService", "MxStatusService is on destroy");
    super.onDestroy();
    synchronized (sPendingQueries)
    {
      sPendingQueries.clear();
      if (mMxStatuQueryListener != null) {
        MxIdCache.removeStatusListener(mMxStatuQueryListener);
      }
      mMxStatuQueryListener = null;
      return;
    }
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    decreaseReference();
    MyLog.d("MxStatusService", "MxStatusService is on Unbind");
    super.onUnbind(paramIntent);
    mService = null;
    return true;
  }
  
  private class MxStatuQueryListener
    implements MxIdCache.MxCacheStatusListener
  {
    private MxStatuQueryListener() {}
    
    public void onMxIdAdded(String paramString1, String paramString2)
    {
      if (mBinderCount > 0)
      {
        MyLog.d("MxStatusService", "onMxIdAdded");
        MxStatusService.this.broadcastStatusToReceiver(paramString2);
      }
    }
    
    public void onMxIdOffline(String paramString1, String paramString2)
    {
      if (mBinderCount > 0)
      {
        MyLog.d("MxStatusService", "onMxIdOffline");
        MxStatusService.this.broadcastStatusToReceiver(paramString2);
      }
    }
    
    public void onMxIdOnline(String paramString1, String paramString2)
    {
      if (mBinderCount > 0)
      {
        MyLog.d("MxStatusService", "onMxIdOnline");
        MxStatusService.this.broadcastStatusToReceiver(paramString2);
      }
    }
  }
  
  private static class MxStatusServiceImpl
    extends IMxStatusService.Stub
  {
    private Context mContext;
    
    public MxStatusServiceImpl(Context paramContext)
    {
      mContext = paramContext;
    }
    
    public boolean isMxOnline(String paramString)
      throws RemoteException
    {
      boolean bool = false;
      ??? = MxIdCache.get(paramString, false);
      MyLog.d("MxStatusService", "query mx status by remote service");
      if (??? != null)
      {
        if ((((MxIdCache.MxIdCacheItem)???).allowMms()) || (((MxIdCache.MxIdCacheItem)???).allowSms())) {
          bool = true;
        }
        return bool;
      }
      MyLog.d("MxStatusService", "cache missed, query the status");
      synchronized (MxStatusService.sPendingQueries)
      {
        MxStatusService.sPendingQueries.add(paramString);
        MxTaskService.queryStatus(mContext, paramString);
        return false;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MxStatusService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */