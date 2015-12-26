package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.ServiceState;
import android.util.Log;

class DownloadManager$2
  extends BroadcastReceiver
{
  DownloadManager$2(DownloadManager paramDownloadManager) {}
  
  public void onReceive(Context arg1, Intent paramIntent)
  {
    if ("android.intent.action.SERVICE_STATE".equals(paramIntent.getAction()))
    {
      ServiceState localServiceState = ServiceState.newFromBundle(paramIntent.getExtras());
      boolean bool = localServiceState.getRoaming();
      synchronized (DownloadManager.access$100())
      {
        int i = MSimUtils.getSlotIdFromIntent(paramIntent);
        DownloadManager.access$300(this$0)[i] = localServiceState.getState();
        if (MSimUtils.isMSimSlotIdValid(i))
        {
          long l = MSimUtils.getSimIdBySlotId(i);
          if (l < 0L)
          {
            Log.e("DownloadManager", "Download manager : cannot get sim id for slot " + i);
            return;
          }
          DownloadManager.access$000(this$0)[i] = DownloadManager.access$500(this$0, DownloadManager.access$400(this$0), bool, l);
        }
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DownloadManager.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */