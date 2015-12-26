package com.xiaomi.smack.util;

import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.List;

final class TrafficUtils$1
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  TrafficUtils$1(XMPushService paramXMPushService) {}
  
  public void process()
  {
    ArrayList localArrayList;
    synchronized ()
    {
      localArrayList = new ArrayList(TrafficUtils.access$100());
    }
    try
    {
      TrafficUtils.access$100().clear();
      TrafficUtils.access$200(val$pushService, localArrayList);
      return;
    }
    finally {}
    localObject1 = finally;
    throw ((Throwable)localObject1);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.util.TrafficUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */