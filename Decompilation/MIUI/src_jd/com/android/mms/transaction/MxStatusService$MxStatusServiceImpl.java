package com.android.mms.transaction;

import android.content.Context;
import android.os.RemoteException;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.HashSet;
import miui.mms.IMxStatusService.Stub;

class MxStatusService$MxStatusServiceImpl
  extends IMxStatusService.Stub
{
  private Context mContext;
  
  public MxStatusService$MxStatusServiceImpl(Context paramContext)
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
    synchronized (MxStatusService.access$300())
    {
      MxStatusService.access$300().add(paramString);
      MxTaskService.queryStatus(mContext, paramString);
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MxStatusService.MxStatusServiceImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */