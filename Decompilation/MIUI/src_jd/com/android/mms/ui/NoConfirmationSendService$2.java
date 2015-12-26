package com.android.mms.ui;

import com.xiaomi.mms.data.MxIdCache.MxCacheStatusListener;

final class NoConfirmationSendService$2
  implements MxIdCache.MxCacheStatusListener
{
  public void onMxIdAdded(String paramString1, String paramString2) {}
  
  public void onMxIdOffline(String paramString1, String paramString2)
  {
    NoConfirmationSendService.startSendPendingMessage(paramString2, false, 0);
    NoConfirmationSendService.startSendPendingMessage(paramString2, false, 1);
  }
  
  public void onMxIdOnline(String paramString1, String paramString2)
  {
    NoConfirmationSendService.startSendPendingMessage(paramString2, true, 0);
    NoConfirmationSendService.startSendPendingMessage(paramString2, true, 1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NoConfirmationSendService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */