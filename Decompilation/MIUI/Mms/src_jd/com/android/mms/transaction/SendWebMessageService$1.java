package com.android.mms.transaction;

import android.os.Handler;
import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;

class SendWebMessageService$1
  implements MxIdCache.MxCacheStatusListener
{
  SendWebMessageService$1(SendWebMessageService paramSendWebMessageService) {}
  
  public void onMxIdAdded(String paramString1, String paramString2) {}
  
  public void onMxIdOffline(String paramString1, final String paramString2)
  {
    SendWebMessageService.access$100(this$0).post(new Runnable()
    {
      public void run()
      {
        SendWebMessageService.access$000(this$0, paramString2, false, 0);
        SendWebMessageService.access$000(this$0, paramString2, false, 1);
      }
    });
  }
  
  public void onMxIdOnline(String paramString1, final String paramString2)
  {
    SendWebMessageService.access$100(this$0).post(new Runnable()
    {
      public void run()
      {
        SendWebMessageService.access$000(this$0, paramString2, true, 0);
        SendWebMessageService.access$000(this$0, paramString2, true, 1);
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SendWebMessageService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */