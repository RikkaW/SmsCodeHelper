package com.android.mms.transaction;

import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;
import com.xiaomi.mms.utils.logger.MyLog;

class MxStatusService$MxStatuQueryListener
  implements MxIdCache.MxCacheStatusListener
{
  private MxStatusService$MxStatuQueryListener(MxStatusService paramMxStatusService) {}
  
  public void onMxIdAdded(String paramString1, String paramString2)
  {
    if (MxStatusService.access$100(this$0) > 0)
    {
      MyLog.d("MxStatusService", "onMxIdAdded");
      MxStatusService.access$200(this$0, paramString2);
    }
  }
  
  public void onMxIdOffline(String paramString1, String paramString2)
  {
    if (MxStatusService.access$100(this$0) > 0)
    {
      MyLog.d("MxStatusService", "onMxIdOffline");
      MxStatusService.access$200(this$0, paramString2);
    }
  }
  
  public void onMxIdOnline(String paramString1, String paramString2)
  {
    if (MxStatusService.access$100(this$0) > 0)
    {
      MyLog.d("MxStatusService", "onMxIdOnline");
      MxStatusService.access$200(this$0, paramString2);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MxStatusService.MxStatuQueryListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */