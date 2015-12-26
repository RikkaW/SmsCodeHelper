package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.stats.StatsHelper;

class XMPushService$PingJob
  extends XMPushService.Job
{
  public XMPushService$PingJob(XMPushService paramXMPushService)
  {
    super(4);
  }
  
  public String getDesc()
  {
    return "send ping..";
  }
  
  public void process()
  {
    if (this$0.isConnected()) {}
    try
    {
      StatsHelper.pingStarted();
      XMPushService.access$400(this$0).sendPingString();
      return;
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      this$0.disconnect(10, localXMPPException);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.PingJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */