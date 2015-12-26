package com.xiaomi.smack;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TrafficUtils;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

class PacketWriter
{
  private XMPPConnection connection;
  private Writer writer;
  
  protected PacketWriter(XMPPConnection paramXMPPConnection)
  {
    connection = paramXMPPConnection;
    writer = writer;
  }
  
  private void writePackets(Packet paramPacket)
    throws XMPPException
  {
    synchronized (writer)
    {
      try
      {
        String str = paramPacket.toXML();
        writer.write(str + "\r\n");
        writer.flush();
        paramPacket = paramPacket.getPackageName();
        if (!TextUtils.isEmpty(paramPacket)) {
          TrafficUtils.distributionTraffic(connection.mPushService, paramPacket, TrafficUtils.getTrafficFlow(str), false, System.currentTimeMillis());
        }
        return;
      }
      catch (IOException paramPacket)
      {
        throw new XMPPException(paramPacket);
      }
    }
  }
  
  void cleanup()
  {
    connection.sendListeners.clear();
  }
  
  void openStream()
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<stream:stream");
    localStringBuilder.append(" xmlns=\"xm\"");
    localStringBuilder.append(" xmlns:stream=\"xm\"");
    localStringBuilder.append(" to=\"").append(connection.getServiceName()).append("\"");
    localStringBuilder.append(" version=\"105\"");
    localStringBuilder.append(" model=\"").append(StringUtils.escapeForXML(Build.MODEL)).append("\"");
    localStringBuilder.append(" os=\"").append(StringUtils.escapeForXML(Build.VERSION.INCREMENTAL)).append("\"");
    Object localObject = SystemUtils.getDeviceUUID();
    if (localObject != null) {
      localStringBuilder.append(" uid=\"").append((String)localObject).append("\"");
    }
    localStringBuilder.append(" sdk=\"").append(7).append("\"");
    localStringBuilder.append(" connpt=\"").append(StringUtils.escapeForXML(connection.getConnectionPoint())).append("\"");
    localStringBuilder.append(" host=\"").append(connection.getHost()).append("\"");
    localStringBuilder.append(" locale=\"").append(StringUtils.escapeForXML(Locale.getDefault().toString())).append("\"");
    localObject = connection.getConfiguration().getConnectionBlob();
    if (localObject != null) {
      localStringBuilder.append(" ps=\"").append(Base64.encodeToString((byte[])localObject, 10)).append("\"");
    }
    localStringBuilder.append(">");
    writer.write(localStringBuilder.toString());
    writer.flush();
  }
  
  public void sendPacket(Packet paramPacket)
    throws XMPPException
  {
    writePackets(paramPacket);
    connection.firePacketSendingListeners(paramPacket);
  }
  
  public void sendPingString()
    throws XMPPException
  {
    synchronized (writer)
    {
      try
      {
        writer.write(connection.getPingString() + "\r\n");
        writer.flush();
        connection.updateLastSent();
        return;
      }
      catch (IOException localIOException)
      {
        throw new XMPPException(localIOException);
      }
    }
  }
  
  public void shutdown()
    throws IOException
  {
    synchronized (writer)
    {
      writer.write("</stream:stream>");
      writer.flush();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.PacketWriter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */