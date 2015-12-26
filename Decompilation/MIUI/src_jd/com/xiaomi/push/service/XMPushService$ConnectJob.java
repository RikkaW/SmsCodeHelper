package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;

public class XMPushService$ConnectJob
  extends XMPushService.Job
{
  XMPushService$ConnectJob(XMPushService paramXMPushService)
  {
    super(1);
  }
  
  public String getDesc()
  {
    return "do reconnect..";
  }
  
  public void process()
  {
    if (this$0.shouldReconnect())
    {
      XMPushService.access$700(this$0);
      return;
    }
    MyLog.warn("should not connect. quit the job.");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.ConnectJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */