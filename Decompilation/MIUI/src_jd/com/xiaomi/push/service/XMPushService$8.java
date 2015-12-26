package com.xiaomi.push.service;

import com.xiaomi.smack.Connection;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;

class XMPushService$8
  extends XMPushService.Job
{
  XMPushService$8(XMPushService paramXMPushService, int paramInt)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "disconnect for service destroy.";
  }
  
  public void process()
  {
    if (XMPushService.access$400(this$0) != null)
    {
      XMPushService.access$400(this$0).disconnect(new Presence(Presence.Type.unavailable), 15, null);
      XMPushService.access$402(this$0, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */