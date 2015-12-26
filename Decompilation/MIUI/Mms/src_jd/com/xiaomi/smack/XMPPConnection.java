package com.xiaomi.smack;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.Host;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.Job;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.Presence.Type;
import com.xiaomi.smack.util.TaskExecutor;
import com.xiaomi.stats.StatsHelper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class XMPPConnection
  extends Connection
{
  private final String PING_PERF = "<pf><p>t:%1$d</p></pf>";
  private String connectedHost;
  String connectionID = null;
  private int curShortConnCount;
  public Exception failedException = null;
  private volatile long lastConnectedTime = 0L;
  private volatile long lastPingReceived = 0L;
  private volatile long lastPingSent = 0L;
  PacketReader packetReader;
  PacketWriter packetWriter;
  private String pingString = "";
  private XMPushService pushService;
  protected Socket socket;
  private String user = null;
  
  public XMPPConnection(XMPushService paramXMPushService, ConnectionConfiguration paramConnectionConfiguration)
  {
    super(paramXMPushService, paramConnectionConfiguration);
    pushService = paramXMPushService;
  }
  
  private void connectDirectly(String paramString, int paramInt)
    throws XMPPException
  {
    int j = 0;
    failedException = null;
    Object localObject1 = new ArrayList();
    int i = MyLog.ps("get bucket for host : " + paramString).intValue();
    Fallback localFallback = getFallback(paramString);
    MyLog.pe(Integer.valueOf(i));
    if (localFallback != null) {
      localObject1 = localFallback.getHosts(true);
    }
    if (((ArrayList)localObject1).isEmpty()) {
      ((ArrayList)localObject1).add(paramString);
    }
    lastConnectedTime = 0L;
    paramString = Network.getActiveConnPoint(pushService);
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = ((ArrayList)localObject1).iterator();
    for (;;)
    {
      i = j;
      long l;
      int k;
      int m;
      int n;
      if (localIterator.hasNext())
      {
        localObject1 = (String)localIterator.next();
        l = System.currentTimeMillis();
        connTimes += 1;
        k = j;
        m = j;
        n = j;
        i = j;
      }
      try
      {
        MyLog.warn("begin to connect to " + (String)localObject1);
        k = j;
        m = j;
        n = j;
        i = j;
        socket = createSocket();
        k = j;
        m = j;
        n = j;
        i = j;
        socket.bind(null);
        k = j;
        m = j;
        n = j;
        i = j;
        InetSocketAddress localInetSocketAddress = Host.from((String)localObject1, paramInt);
        k = j;
        m = j;
        n = j;
        i = j;
        socket.connect(localInetSocketAddress, 5000);
        k = j;
        m = j;
        n = j;
        i = j;
        socket.setTcpNoDelay(true);
        k = j;
        m = j;
        n = j;
        i = j;
        connectedHost = ((String)localObject1);
        k = j;
        m = j;
        n = j;
        i = j;
        initConnection();
        int i1 = 1;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        j = 1;
        k = i1;
        m = i2;
        n = i3;
        i = i4;
        connectTime = (System.currentTimeMillis() - l);
        if (localFallback != null)
        {
          k = i1;
          m = i2;
          n = i3;
          i = i4;
          localFallback.succeedHost((String)localObject1, connectTime, 0L);
        }
        k = i1;
        m = i2;
        n = i3;
        i = i4;
        lastConnectedTime = SystemClock.elapsedRealtime();
        k = i1;
        m = i2;
        n = i3;
        i = i4;
        MyLog.warn("connected to " + (String)localObject1 + " in " + connectTime);
        i = j;
        if (1 == 0)
        {
          StatsHelper.connectFail((String)localObject1, failedException);
          i = j;
          if (TextUtils.equals(paramString, Network.getActiveConnPoint(pushService))) {}
        }
        HostManager.getInstance().persist();
        if (i == 0) {
          throw new XMPPException(localStringBuilder.toString());
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (localFallback != null)
          {
            i = k;
            localFallback.failedHost((String)localObject1, System.currentTimeMillis() - l, 0L, localIOException);
          }
          i = k;
          failedException = localIOException;
          i = k;
          MyLog.e("SMACK: Could not connect to:" + (String)localObject1);
          i = k;
          localStringBuilder.append("SMACK: Could not connect to ").append((String)localObject1).append(" port:").append(paramInt).append(" ").append(localIOException.getMessage()).append("\n");
          j = k;
          if (k != 0) {
            break;
          }
          StatsHelper.connectFail((String)localObject1, failedException);
          j = k;
          if (TextUtils.equals(paramString, Network.getActiveConnPoint(pushService))) {
            break;
          }
          i = k;
        }
      }
      catch (XMPPException localXMPPException)
      {
        for (;;)
        {
          if (localFallback != null)
          {
            i = m;
            localFallback.failedHost((String)localObject1, System.currentTimeMillis() - l, 0L, localXMPPException);
          }
          i = m;
          failedException = localXMPPException;
          i = m;
          MyLog.e("SMACK: Could not connect to:" + (String)localObject1);
          i = m;
          localStringBuilder.append("SMACK: Could not connect to ").append((String)localObject1).append(" port:").append(paramInt).append(" ").append(localXMPPException.getMessage()).append("\n");
          j = m;
          if (m != 0) {
            break;
          }
          StatsHelper.connectFail((String)localObject1, failedException);
          j = m;
          if (TextUtils.equals(paramString, Network.getActiveConnPoint(pushService))) {
            break;
          }
          i = m;
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          i = n;
          failedException = new Exception("abnormal exception", localThrowable);
          i = n;
          MyLog.e(localThrowable);
          j = n;
          if (n != 0) {
            break;
          }
          StatsHelper.connectFail((String)localObject1, failedException);
          j = n;
          if (TextUtils.equals(paramString, Network.getActiveConnPoint(pushService))) {
            break;
          }
          i = n;
        }
      }
      finally
      {
        do
        {
          if (i != 0) {
            break;
          }
          StatsHelper.connectFail((String)localObject1, failedException);
        } while (!TextUtils.equals(paramString, Network.getActiveConnPoint(pushService)));
      }
    }
  }
  
  private void connectUsingConfiguration(ConnectionConfiguration paramConnectionConfiguration)
    throws XMPPException, IOException
  {
    connectDirectly(paramConnectionConfiguration.getHost(), paramConnectionConfiguration.getPort());
  }
  
  private void initConnection()
    throws XMPPException, IOException
  {
    try
    {
      initReaderAndWriter();
      packetWriter = new PacketWriter(this);
      packetReader = new PacketReader(this);
      if (config.isDebuggerEnabled())
      {
        addPacketListener(debugger.getReaderListener(), null);
        if (debugger.getWriterListener() != null) {
          addPacketSendingListener(debugger.getWriterListener(), null);
        }
      }
      packetWriter.openStream();
      packetReader.startup();
      return;
    }
    finally {}
  }
  
  private void initReaderAndWriter()
    throws XMPPException
  {
    try
    {
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"), 4096);
      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
      if ((reader != null) && (writer != null)) {
        initDebugger();
      }
      return;
    }
    catch (Exception localException)
    {
      throw new XMPPException("Error to init reader and writer", localException);
    }
  }
  
  private void sinkdownHost(Exception paramException)
  {
    if (SystemClock.elapsedRealtime() - lastConnectedTime < 300000L)
    {
      if (Network.hasNetwork(pushService))
      {
        curShortConnCount += 1;
        if (curShortConnCount >= 2)
        {
          String str = getHost();
          MyLog.warn("max short conn time reached, sink down current host:" + str);
          sinkdownHost(str, 0L, paramException);
          curShortConnCount = 0;
        }
      }
      return;
    }
    curShortConnCount = 0;
  }
  
  private void sinkdownHost(String paramString, long paramLong, Exception paramException)
  {
    Object localObject = ConnectionConfiguration.getXmppServerHost();
    localObject = HostManager.getInstance().getFallbacksByHost((String)localObject, false);
    if (localObject != null)
    {
      ((Fallback)localObject).failedHost(paramString, paramLong, 0L, paramException);
      HostManager.getInstance().persist();
    }
  }
  
  public void batchSendPacket(Packet[] paramArrayOfPacket)
    throws XMPPException
  {
    int j = paramArrayOfPacket.length;
    int i = 0;
    while (i < j)
    {
      sendPacket(paramArrayOfPacket[i]);
      i += 1;
    }
  }
  
  public void bind(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
    throws XMPPException
  {
    try
    {
      new XMBinder().doBind(paramClientLoginInfo, getChallenge(), this);
      return;
    }
    finally
    {
      paramClientLoginInfo = finally;
      throw paramClientLoginInfo;
    }
  }
  
  /* Error */
  public void connect()
    throws XMPPException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 416	com/xiaomi/smack/XMPPConnection:isConnected	()Z
    //   6: ifne +10 -> 16
    //   9: aload_0
    //   10: invokevirtual 419	com/xiaomi/smack/XMPPConnection:isConnecting	()Z
    //   13: ifeq +12 -> 25
    //   16: ldc_w 421
    //   19: invokestatic 161	com/xiaomi/channel/commonutils/logger/MyLog:warn	(Ljava/lang/String;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: aload_0
    //   26: iconst_0
    //   27: iconst_0
    //   28: aconst_null
    //   29: invokevirtual 425	com/xiaomi/smack/XMPPConnection:setConnectionStatus	(IILjava/lang/Exception;)V
    //   32: aload_0
    //   33: aload_0
    //   34: getfield 300	com/xiaomi/smack/XMPPConnection:config	Lcom/xiaomi/smack/ConnectionConfiguration;
    //   37: invokespecial 427	com/xiaomi/smack/XMPPConnection:connectUsingConfiguration	(Lcom/xiaomi/smack/ConnectionConfiguration;)V
    //   40: goto -18 -> 22
    //   43: astore_1
    //   44: new 65	com/xiaomi/smack/XMPPException
    //   47: dup
    //   48: aload_1
    //   49: invokespecial 429	com/xiaomi/smack/XMPPException:<init>	(Ljava/lang/Throwable;)V
    //   52: athrow
    //   53: astore_1
    //   54: aload_0
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	XMPPConnection
    //   43	6	1	localIOException	IOException
    //   53	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	43	java/io/IOException
    //   16	22	43	java/io/IOException
    //   25	40	43	java/io/IOException
    //   2	16	53	finally
    //   16	22	53	finally
    //   25	40	53	finally
    //   44	53	53	finally
  }
  
  public Socket createSocket()
  {
    return new Socket();
  }
  
  public void disconnect(Presence paramPresence, int paramInt, Exception paramException)
  {
    shutdown(paramPresence, paramInt, paramException);
    if ((paramException != null) && (lastConnectedTime != 0L)) {
      sinkdownHost(paramException);
    }
  }
  
  public String getChallenge()
  {
    return challenge;
  }
  
  Fallback getFallback(final String paramString)
  {
    Fallback localFallback = HostManager.getInstance().getFallbacksByHost(paramString, false);
    if (!localFallback.isEffective()) {
      TaskExecutor.execute(new Runnable()
      {
        public void run()
        {
          HostManager.getInstance().getFallbacksByHost(paramString, true);
        }
      });
    }
    clientIP = 0;
    try
    {
      paramString = InetAddress.getByName(ip).getAddress();
      clientIP = (paramString[0] & 0xFF);
      clientIP |= paramString[1] << 8 & 0xFF00;
      clientIP |= paramString[2] << 16 & 0xFF0000;
      clientIP |= paramString[3] << 24 & 0xFF000000;
      return localFallback;
    }
    catch (UnknownHostException paramString) {}
    return localFallback;
  }
  
  public String getHost()
  {
    return connectedHost;
  }
  
  public String getPingString()
  {
    String str1;
    if ((lastPingReceived == 0L) || (lastPingSent == 0L))
    {
      str1 = "";
      str2 = StatsHelper.retriveStatsAsString();
      if (str2 == null) {
        break label104;
      }
    }
    label104:
    for (String str2 = "<q>" + str2 + "</q>";; str2 = "")
    {
      return String.format(pingString, new Object[] { str1, str2 });
      str1 = String.format("<pf><p>t:%1$d</p></pf>", new Object[] { Long.valueOf(lastPingReceived - lastPingSent) });
      break;
    }
  }
  
  public void notifyConnectionError(final int paramInt, final Exception paramException)
  {
    pushService.executeJob(new XMPushService.Job(2)
    {
      public String getDesc()
      {
        return "shutdown the connection. " + paramInt + ", " + paramException;
      }
      
      public void process()
      {
        pushService.disconnect(paramInt, paramException);
      }
    });
  }
  
  public void sendPacket(Packet paramPacket)
    throws XMPPException
  {
    if (packetWriter != null)
    {
      packetWriter.sendPacket(paramPacket);
      return;
    }
    throw new XMPPException("the writer is null.");
  }
  
  public void sendPingString()
    throws XMPPException
  {
    if (packetWriter != null)
    {
      packetWriter.sendPingString();
      final long l = System.currentTimeMillis();
      pushService.executeJobDelayed(new XMPushService.Job(13)
      {
        public String getDesc()
        {
          return "check the ping-pong.";
        }
        
        public void process()
        {
          if ((isConnected()) && (!isReadAlive(l))) {
            pushService.disconnect(22, null);
          }
        }
      }, 15000L);
      return;
    }
    throw new XMPPException("the packetwriter is null.");
  }
  
  public void setPingString(String paramString)
  {
    pingString = paramString;
  }
  
  /* Error */
  protected void shutdown(Presence paramPresence, int paramInt, Exception paramException)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 524	com/xiaomi/smack/XMPPConnection:getConnectionStatus	()I
    //   6: istore 4
    //   8: iload 4
    //   10: iconst_2
    //   11: if_icmpne +6 -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: iconst_2
    //   19: iload_2
    //   20: aload_3
    //   21: invokevirtual 425	com/xiaomi/smack/XMPPConnection:setConnectionStatus	(IILjava/lang/Exception;)V
    //   24: aload_0
    //   25: ldc 44
    //   27: putfield 440	com/xiaomi/smack/XMPPConnection:challenge	Ljava/lang/String;
    //   30: aload_0
    //   31: getfield 296	com/xiaomi/smack/XMPPConnection:packetReader	Lcom/xiaomi/smack/PacketReader;
    //   34: ifnull +22 -> 56
    //   37: aload_0
    //   38: getfield 296	com/xiaomi/smack/XMPPConnection:packetReader	Lcom/xiaomi/smack/PacketReader;
    //   41: invokevirtual 526	com/xiaomi/smack/PacketReader:shutdown	()V
    //   44: aload_0
    //   45: getfield 296	com/xiaomi/smack/XMPPConnection:packetReader	Lcom/xiaomi/smack/PacketReader;
    //   48: invokevirtual 529	com/xiaomi/smack/PacketReader:cleanup	()V
    //   51: aload_0
    //   52: aconst_null
    //   53: putfield 296	com/xiaomi/smack/XMPPConnection:packetReader	Lcom/xiaomi/smack/PacketReader;
    //   56: aload_0
    //   57: getfield 291	com/xiaomi/smack/XMPPConnection:packetWriter	Lcom/xiaomi/smack/PacketWriter;
    //   60: astore_1
    //   61: aload_1
    //   62: ifnull +22 -> 84
    //   65: aload_0
    //   66: getfield 291	com/xiaomi/smack/XMPPConnection:packetWriter	Lcom/xiaomi/smack/PacketWriter;
    //   69: invokevirtual 530	com/xiaomi/smack/PacketWriter:shutdown	()V
    //   72: aload_0
    //   73: getfield 291	com/xiaomi/smack/XMPPConnection:packetWriter	Lcom/xiaomi/smack/PacketWriter;
    //   76: invokevirtual 531	com/xiaomi/smack/PacketWriter:cleanup	()V
    //   79: aload_0
    //   80: aconst_null
    //   81: putfield 291	com/xiaomi/smack/XMPPConnection:packetWriter	Lcom/xiaomi/smack/PacketWriter;
    //   84: aload_0
    //   85: getfield 167	com/xiaomi/smack/XMPPConnection:socket	Ljava/net/Socket;
    //   88: invokevirtual 534	java/net/Socket:close	()V
    //   91: aload_0
    //   92: getfield 349	com/xiaomi/smack/XMPPConnection:reader	Ljava/io/Reader;
    //   95: astore_1
    //   96: aload_1
    //   97: ifnull +15 -> 112
    //   100: aload_0
    //   101: getfield 349	com/xiaomi/smack/XMPPConnection:reader	Ljava/io/Reader;
    //   104: invokevirtual 537	java/io/Reader:close	()V
    //   107: aload_0
    //   108: aconst_null
    //   109: putfield 349	com/xiaomi/smack/XMPPConnection:reader	Ljava/io/Reader;
    //   112: aload_0
    //   113: getfield 367	com/xiaomi/smack/XMPPConnection:writer	Ljava/io/Writer;
    //   116: astore_1
    //   117: aload_1
    //   118: ifnull +15 -> 133
    //   121: aload_0
    //   122: getfield 367	com/xiaomi/smack/XMPPConnection:writer	Ljava/io/Writer;
    //   125: invokevirtual 540	java/io/Writer:close	()V
    //   128: aload_0
    //   129: aconst_null
    //   130: putfield 367	com/xiaomi/smack/XMPPConnection:writer	Ljava/io/Writer;
    //   133: aload_0
    //   134: lconst_0
    //   135: putfield 48	com/xiaomi/smack/XMPPConnection:lastPingSent	J
    //   138: aload_0
    //   139: lconst_0
    //   140: putfield 50	com/xiaomi/smack/XMPPConnection:lastPingReceived	J
    //   143: goto -129 -> 14
    //   146: astore_1
    //   147: aload_0
    //   148: monitorexit
    //   149: aload_1
    //   150: athrow
    //   151: astore_1
    //   152: aload_1
    //   153: invokestatic 268	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/Throwable;)V
    //   156: goto -84 -> 72
    //   159: astore_1
    //   160: goto -32 -> 128
    //   163: astore_1
    //   164: goto -57 -> 107
    //   167: astore_1
    //   168: goto -77 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	this	XMPPConnection
    //   0	171	1	paramPresence	Presence
    //   0	171	2	paramInt	int
    //   0	171	3	paramException	Exception
    //   6	6	4	i	int
    // Exception table:
    //   from	to	target	type
    //   2	8	146	finally
    //   17	56	146	finally
    //   56	61	146	finally
    //   65	72	146	finally
    //   72	84	146	finally
    //   84	91	146	finally
    //   91	96	146	finally
    //   100	107	146	finally
    //   107	112	146	finally
    //   112	117	146	finally
    //   121	128	146	finally
    //   128	133	146	finally
    //   133	143	146	finally
    //   152	156	146	finally
    //   65	72	151	java/io/IOException
    //   121	128	159	java/lang/Throwable
    //   100	107	163	java/lang/Throwable
    //   84	91	167	java/lang/Throwable
  }
  
  public void unbind(String paramString1, String paramString2)
    throws XMPPException
  {
    try
    {
      Presence localPresence = new Presence(Presence.Type.unavailable);
      localPresence.setChannelId(paramString1);
      localPresence.setFrom(paramString2);
      if (packetWriter != null) {
        packetWriter.sendPacket(localPresence);
      }
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public void updateLastReceived()
  {
    lastPingReceived = SystemClock.uptimeMillis();
  }
  
  public void updateLastSent()
  {
    lastPingSent = SystemClock.uptimeMillis();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.XMPPConnection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */