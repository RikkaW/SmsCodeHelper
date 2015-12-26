package com.android.mms.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class MessageEditableActivityBase$QueryMxStatusHandler
  extends Handler
{
  private MessageEditableActivityBase$QueryMxStatusHandler(MessageEditableActivityBase paramMessageEditableActivityBase, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    Object localObject;
    switch (what)
    {
    default: 
      return;
    case 1: 
    case 3: 
    case 4: 
      String str = (String)obj;
      MxIdCache.MxIdCacheItem localMxIdCacheItem = MxIdCache.getOrQuery(this$0, str, false);
      localObject = new Bundle();
      if (localMxIdCacheItem != null) {
        ((Bundle)localObject).putLong("capability", localMxIdCacheItem.getCapability());
      }
      paramMessage = this$0.mHandler.obtainMessage(what, str);
      paramMessage.setData((Bundle)localObject);
      paramMessage.sendToTarget();
      return;
    case 2: 
      MessageEditableActivityBase.access$2400(this$0).removeMessages(2);
      paramMessage = MessageEditableActivityBase.access$2300(this$0).entrySet().iterator();
      while (paramMessage.hasNext())
      {
        localObject = (String)((Map.Entry)paramMessage.next()).getKey();
        MessageEditableActivityBase.access$2400(this$0).obtainMessage(4, localObject).sendToTarget();
      }
      MessageEditableActivityBase.access$2400(this$0).obtainMessage(5).sendToTarget();
      return;
    }
    this$0.mHandler.obtainMessage(5).sendToTarget();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.QueryMxStatusHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */