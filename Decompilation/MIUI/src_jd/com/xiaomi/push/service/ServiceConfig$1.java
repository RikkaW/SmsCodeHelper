package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig;
import com.xiaomi.smack.util.SystemUtils;
import java.util.Iterator;
import java.util.List;

class ServiceConfig$1
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  boolean success = false;
  
  ServiceConfig$1(ServiceConfig paramServiceConfig) {}
  
  public void postProcess()
  {
    ServiceConfig.access$202(this$0, null);
    if (success)
    {
      Iterator localIterator = ServiceConfig.access$300(this$0).iterator();
      while (localIterator.hasNext()) {
        ((ServiceConfig.Listener)localIterator.next()).onConfigChange(ServiceConfig.access$000(this$0));
      }
    }
  }
  
  public void process()
  {
    try
    {
      ChannelConfig.PushServiceConfig localPushServiceConfig = ChannelConfig.PushServiceConfig.parseFrom(Base64.decode(HttpUtils.get(SystemUtils.getContext(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
      if (localPushServiceConfig != null)
      {
        ServiceConfig.access$002(this$0, localPushServiceConfig);
        success = true;
        ServiceConfig.access$100(this$0);
      }
      return;
    }
    catch (Exception localException)
    {
      MyLog.warn("fetch config failure: " + localException.getMessage());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ServiceConfig.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */