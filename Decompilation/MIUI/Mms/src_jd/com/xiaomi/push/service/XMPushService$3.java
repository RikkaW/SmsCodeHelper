package com.xiaomi.push.service;

import com.xiaomi.stats.StatsHandler;

class XMPushService$3
  extends XMPushService.Job
{
  XMPushService$3(XMPushService paramXMPushService, int paramInt)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "prepare the mi push account.";
  }
  
  public void process()
  {
    StatsHandler.getInstance().init(this$0, XMPushService.access$000(this$0));
    XMPushService.access$100(this$0);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */