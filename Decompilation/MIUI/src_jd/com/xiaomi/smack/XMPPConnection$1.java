package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;

class XMPPConnection$1
  extends XMPushService.Job
{
  XMPPConnection$1(XMPPConnection paramXMPPConnection, int paramInt, long paramLong)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "check the ping-pong.";
  }
  
  public void process()
  {
    if ((this$0.isConnected()) && (!this$0.isReadAlive(val$current))) {
      XMPPConnection.access$000(this$0).disconnect(22, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMPPConnection.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */