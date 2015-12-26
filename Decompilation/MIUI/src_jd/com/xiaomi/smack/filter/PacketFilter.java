package com.xiaomi.smack.filter;

import com.xiaomi.smack.packet.Packet;

public abstract interface PacketFilter
{
  public abstract boolean accept(Packet paramPacket);
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.filter.PacketFilter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */