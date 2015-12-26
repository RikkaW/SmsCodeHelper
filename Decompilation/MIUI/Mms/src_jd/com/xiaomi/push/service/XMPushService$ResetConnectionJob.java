package com.xiaomi.push.service;

class XMPushService$ResetConnectionJob
  extends XMPushService.Job
{
  XMPushService$ResetConnectionJob(XMPushService paramXMPushService)
  {
    super(3);
  }
  
  public String getDesc()
  {
    return "reset the connection.";
  }
  
  public void process()
  {
    this$0.disconnect(11, null);
    if (this$0.shouldReconnect()) {
      XMPushService.access$700(this$0);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.ResetConnectionJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */