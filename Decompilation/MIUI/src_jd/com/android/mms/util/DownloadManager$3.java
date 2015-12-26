package com.android.mms.util;

import android.util.Log;
import android.widget.Toast;

class DownloadManager$3
  implements Runnable
{
  DownloadManager$3(DownloadManager paramDownloadManager, int paramInt) {}
  
  public void run()
  {
    try
    {
      Toast.makeText(DownloadManager.access$600(this$0), val$errStr, 1).show();
      return;
    }
    catch (Exception localException)
    {
      Log.e("DownloadManager", "Caught an exception in showErrorCodeToast");
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DownloadManager.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */