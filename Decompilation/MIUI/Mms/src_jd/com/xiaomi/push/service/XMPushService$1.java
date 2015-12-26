package com.xiaomi.push.service;

import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.packet.Packet;

class XMPushService$1
  implements PacketListener
{
  XMPushService$1(XMPushService paramXMPushService) {}
  
  public void processPacket(Packet paramPacket)
  {
    this$0.executeJob(new XMPushService.PacketReceiveJob(this$0, paramPacket));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */