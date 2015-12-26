package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.packet.Packet;
import java.text.SimpleDateFormat;
import java.util.Date;

class AndroidDebugger$3
  implements PacketListener
{
  AndroidDebugger$3(AndroidDebugger paramAndroidDebugger) {}
  
  public void processPacket(Packet paramPacket)
  {
    if (AndroidDebugger.printInterpreted) {
      MyLog.v("SMACK " + AndroidDebugger.access$000(this$0).format(new Date()) + " RCV PKT (" + AndroidDebugger.access$100(this$0).hashCode() + "): " + paramPacket.toXML());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.measite.smack.AndroidDebugger.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */