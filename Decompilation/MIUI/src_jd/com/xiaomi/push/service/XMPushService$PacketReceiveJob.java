package com.xiaomi.push.service;

import com.xiaomi.smack.packet.Packet;

class XMPushService$PacketReceiveJob
  extends XMPushService.Job
{
  private Packet mPacket = null;
  
  public XMPushService$PacketReceiveJob(XMPushService paramXMPushService, Packet paramPacket)
  {
    super(8);
    mPacket = paramPacket;
  }
  
  public String getDesc()
  {
    return "receive a message.";
  }
  
  public void process()
  {
    XMPushService.access$600(this$0).onPacketReceive(mPacket);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.PacketReceiveJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */