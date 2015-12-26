package com.android.mms.transaction;

import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.xiaomi.mms.data.MidPhoneMap;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;

class SendWebMessageService$2
  implements Runnable
{
  SendWebMessageService$2(SendWebMessageService paramSendWebMessageService, String paramString1, int paramInt, String paramString2) {}
  
  public void run()
  {
    if (B2cMessageUtils.isB2cNumber(Contact.get(val$address)))
    {
      SendWebMessageService.access$200(this$0, val$address, true, val$slotId, true);
      return;
    }
    if ((!MxActivateService.isMxEnabled(this$0)) || (!PushSession.getInstance(this$0).isConnected(val$slotId)))
    {
      SendWebMessageService.access$300(this$0, val$address, false, val$slotId);
      return;
    }
    MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.get(val$address, true);
    if (localMxIdCacheItem == null)
    {
      if (TextUtils.isEmpty(val$mid))
      {
        SendWebMessageService.access$300(this$0, val$address, false, val$slotId);
        return;
      }
      MidPhoneMap.put(val$mid, val$address);
      MxIdCache.put(val$address, val$mid);
      SendWebMessageService.access$400(this$0, val$address, val$slotId);
      return;
    }
    if (localMxIdCacheItem.isExpired())
    {
      SendWebMessageService.access$400(this$0, val$address, val$slotId);
      return;
    }
    SendWebMessageService.access$300(this$0, val$address, localMxIdCacheItem.allowSms(), val$slotId);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SendWebMessageService.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */