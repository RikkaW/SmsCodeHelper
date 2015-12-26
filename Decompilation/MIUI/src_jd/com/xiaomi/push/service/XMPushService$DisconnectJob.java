package com.xiaomi.push.service;

public class XMPushService$DisconnectJob
  extends XMPushService.Job
{
  public Exception e;
  public int reason;
  
  XMPushService$DisconnectJob(XMPushService paramXMPushService, int paramInt, Exception paramException)
  {
    super(2);
    reason = paramInt;
    e = paramException;
  }
  
  public String getDesc()
  {
    return "disconnect the connection.";
  }
  
  public void process()
  {
    this$0.disconnect(reason, e);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.DisconnectJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */