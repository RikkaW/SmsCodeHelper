package com.xiaomi.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.IQ.Type;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.PacketParserUtils;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class PacketReader
{
  private XMPPConnection connection;
  private boolean done;
  private XmlPullParser parser;
  private Thread readerThread;
  
  protected PacketReader(XMPPConnection paramXMPPConnection)
  {
    connection = paramXMPPConnection;
    init();
  }
  
  private void parsePackets()
  {
    int i;
    Object localObject2;
    String str2;
    label165:
    label166:
    Object localObject1;
    for (;;)
    {
      try
      {
        setParser();
        i = parser.getEventType();
        localObject2 = "";
        connection.setReadAlive();
        if (i != 2) {
          break label578;
        }
        str2 = parser.getName();
        if (!parser.getName().equals("message")) {
          break label166;
        }
        processPacket(PacketParserUtils.parseMessage(parser));
        String str1 = str2;
        int j = parser.next();
        if (!done)
        {
          i = j;
          localObject2 = str1;
          if (j != 1) {
            continue;
          }
        }
        if (j != 1) {
          break label165;
        }
        throw new Exception("SMACK: server close the connection or timeout happened, last element name=" + str1 + " host=" + connection.getHost());
      }
      catch (Exception localException)
      {
        MyLog.e(localException);
        if (done) {
          break label619;
        }
      }
      notifyConnectionError(9, localException);
      return;
      if (parser.getName().equals("iq"))
      {
        processPacket(PacketParserUtils.parseIQ(parser, connection));
        localObject1 = str2;
      }
      else
      {
        if (!parser.getName().equals("presence")) {
          break;
        }
        processPacket(PacketParserUtils.parsePresence(parser));
        localObject1 = str2;
      }
    }
    if (parser.getName().equals("stream"))
    {
      localObject1 = "";
      i = 0;
    }
    for (;;)
    {
      if (i < parser.getAttributeCount())
      {
        if (parser.getAttributeName(i).equals("from"))
        {
          connection.config.setServiceName(parser.getAttributeValue(i));
          localObject2 = localObject1;
        }
        else if (parser.getAttributeName(i).equals("challenge"))
        {
          localObject2 = parser.getAttributeValue(i);
        }
        else
        {
          localObject2 = localObject1;
          if ("ps".equals(parser.getAttributeName(i)))
          {
            localObject2 = parser.getAttributeValue(i);
            IQ localIQ = new IQ();
            localIQ.setChannelId("0");
            localIQ.setPacketID("0");
            localIQ.setAttribute("ps", (String)localObject2);
            localIQ.setType(IQ.Type.SET);
            processPacket(localIQ);
            localObject2 = localObject1;
          }
        }
      }
      else
      {
        connection.setChallenge((String)localObject1);
        localObject1 = str2;
        break;
        if (parser.getName().equals("error")) {
          throw new XMPPException(PacketParserUtils.parseStreamError(parser));
        }
        if (parser.getName().equals("warning"))
        {
          parser.next();
          localObject1 = str2;
          if (!parser.getName().equals("multi-login")) {
            break;
          }
          notifyConnectionError(6, null);
          localObject1 = str2;
          break;
        }
        localObject1 = str2;
        if (!parser.getName().equals("bind")) {
          break;
        }
        processPacket(PacketParserUtils.parseBindResult(parser));
        localObject1 = str2;
        break;
        label578:
        localObject1 = localObject2;
        if (i != 3) {
          break;
        }
        localObject1 = localObject2;
        if (!parser.getName().equals("stream")) {
          break;
        }
        notifyConnectionError(13, null);
        localObject1 = localObject2;
        break;
        label619:
        MyLog.v("reader is shutdown, ignore the exception.");
        return;
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  private void processPacket(Packet paramPacket)
  {
    if (paramPacket == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = connection.recvListeners.values().iterator();
      while (localIterator.hasNext()) {
        ((Connection.ListenerWrapper)localIterator.next()).notifyListener(paramPacket);
      }
    }
  }
  
  private void setParser()
    throws XmlPullParserException
  {
    parser = XmlPullParserFactory.newInstance().newPullParser();
    parser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
    parser.setInput(connection.reader);
  }
  
  void cleanup()
  {
    connection.recvListeners.clear();
  }
  
  protected void init()
  {
    done = false;
    readerThread = new Thread("Smack Packet Reader (" + connection.connectionCounterValue + ")")
    {
      public void run()
      {
        PacketReader.this.parsePackets();
      }
    };
  }
  
  void notifyConnectionError(int paramInt, Exception paramException)
  {
    done = true;
    connection.notifyConnectionError(paramInt, paramException);
  }
  
  public void shutdown()
  {
    done = true;
  }
  
  public void startup()
    throws XMPPException
  {
    readerThread.start();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.PacketReader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */