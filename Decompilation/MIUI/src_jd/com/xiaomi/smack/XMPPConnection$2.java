package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;

class XMPPConnection$2
  extends XMPushService.Job
{
  XMPPConnection$2(XMPPConnection paramXMPPConnection, int paramInt1, int paramInt2, Exception paramException)
  {
    super(paramInt1);
  }
  
  public String getDesc()
  {
    return "shutdown the connection. " + val$error + ", " + val$e;
  }
  
  public void process()
  {
    XMPPConnection.access$000(this$0).disconnect(val$error, val$e);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMPPConnection.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */