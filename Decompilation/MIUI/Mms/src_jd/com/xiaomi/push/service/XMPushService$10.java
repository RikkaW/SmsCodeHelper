package com.xiaomi.push.service;

class XMPushService$10
  extends XMPushService.Job
{
  XMPushService$10(XMPushService paramXMPushService, int paramInt)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "disconnect because of connecting timeout";
  }
  
  public void process()
  {
    this$0.disconnect(18, null);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */