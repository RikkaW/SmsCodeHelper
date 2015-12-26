package com.xiaomi.smack;

import com.xiaomi.smack.packet.Packet;

public abstract interface PacketListener
{
  public abstract void processPacket(Packet paramPacket);
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.PacketListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */