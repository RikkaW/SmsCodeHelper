package com.xiaomi.push.service;

class XMPushService$KillJob
  extends XMPushService.Job
{
  public XMPushService$KillJob(XMPushService paramXMPushService)
  {
    super(5);
  }
  
  public String getDesc()
  {
    return "ask the job queue to quit";
  }
  
  public void process()
  {
    XMPushService.access$500(this$0).quit();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.KillJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */