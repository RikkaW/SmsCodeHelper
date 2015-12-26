package com.xiaomi.smack;

import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;

public class Connection$ListenerWrapper
{
  private PacketFilter packetFilter;
  private PacketListener packetListener;
  
  public Connection$ListenerWrapper(PacketListener paramPacketListener, PacketFilter paramPacketFilter)
  {
    packetListener = paramPacketListener;
    packetFilter = paramPacketFilter;
  }
  
  public void notifyListener(Packet paramPacket)
  {
    if ((packetFilter == null) || (packetFilter.accept(paramPacket))) {
      packetListener.processPacket(paramPacket);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.Connection.ListenerWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */