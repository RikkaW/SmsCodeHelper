package com.xiaomi.smack;

import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.MD5;
import com.xiaomi.measite.smack.AndroidDebugger;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.filter.PacketFilter;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Connection
{
  public static boolean DEBUG_ENABLED = false;
  private static final AtomicInteger connectionCounter = new AtomicInteger(0);
  protected String challenge = "";
  protected int clientIP;
  protected ConnectionConfiguration config;
  protected int connTimes = 0;
  private int connectStatus = 2;
  protected long connectTime = -1L;
  protected final int connectionCounterValue = connectionCounter.getAndIncrement();
  private final Collection<ConnectionListener> connectionListeners = new CopyOnWriteArrayList();
  protected SmackDebugger debugger = null;
  private LinkedList<Pair<Integer, Long>> mCachedStatus = new LinkedList();
  protected XMPushService mPushService;
  private long readAlive = 0L;
  protected Reader reader;
  protected final Map<PacketListener, ListenerWrapper> recvListeners = new ConcurrentHashMap();
  protected final Map<PacketListener, ListenerWrapper> sendListeners = new ConcurrentHashMap();
  protected Writer writer;
  
  static
  {
    try
    {
      DEBUG_ENABLED = Boolean.getBoolean("smack.debugEnabled");
      SmackConfiguration.getVersion();
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected Connection(XMPushService paramXMPushService, ConnectionConfiguration paramConnectionConfiguration)
  {
    config = paramConnectionConfiguration;
    mPushService = paramXMPushService;
  }
  
  private void addStatus(int paramInt)
  {
    localLinkedList = mCachedStatus;
    if (paramInt == 1) {}
    for (;;)
    {
      try
      {
        mCachedStatus.clear();
        return;
      }
      finally {}
      mCachedStatus.add(new Pair(Integer.valueOf(paramInt), Long.valueOf(System.currentTimeMillis())));
      if (mCachedStatus.size() > 6) {
        mCachedStatus.remove(0);
      }
    }
  }
  
  private String getDesc(int paramInt)
  {
    if (paramInt == 1) {
      return "connected";
    }
    if (paramInt == 0) {
      return "connecting";
    }
    if (paramInt == 2) {
      return "disconnected";
    }
    return "unknown";
  }
  
  public void addConnectionListener(ConnectionListener paramConnectionListener)
  {
    if (paramConnectionListener == null) {}
    while (connectionListeners.contains(paramConnectionListener)) {
      return;
    }
    connectionListeners.add(paramConnectionListener);
  }
  
  public void addPacketListener(PacketListener paramPacketListener, PacketFilter paramPacketFilter)
  {
    if (paramPacketListener == null) {
      throw new NullPointerException("Packet listener is null.");
    }
    paramPacketFilter = new ListenerWrapper(paramPacketListener, paramPacketFilter);
    recvListeners.put(paramPacketListener, paramPacketFilter);
  }
  
  public void addPacketSendingListener(PacketListener paramPacketListener, PacketFilter paramPacketFilter)
  {
    if (paramPacketListener == null) {
      throw new NullPointerException("Packet listener is null.");
    }
    paramPacketFilter = new ListenerWrapper(paramPacketListener, paramPacketFilter);
    sendListeners.put(paramPacketListener, paramPacketFilter);
  }
  
  public abstract void batchSendPacket(Packet[] paramArrayOfPacket)
    throws XMPPException;
  
  public abstract void bind(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
    throws XMPPException;
  
  public void clearCachedStatus()
  {
    synchronized (mCachedStatus)
    {
      mCachedStatus.clear();
      return;
    }
  }
  
  public abstract void disconnect(Presence paramPresence, int paramInt, Exception paramException);
  
  protected void firePacketSendingListeners(Packet paramPacket)
  {
    Iterator localIterator = sendListeners.values().iterator();
    while (localIterator.hasNext()) {
      ((ListenerWrapper)localIterator.next()).notifyListener(paramPacket);
    }
  }
  
  public int getClientIP()
  {
    return clientIP;
  }
  
  public ConnectionConfiguration getConfiguration()
  {
    return config;
  }
  
  public int getConnTryTimes()
  {
    return connTimes;
  }
  
  public long getConnectTime()
  {
    return connectTime;
  }
  
  public String getConnectionPoint()
  {
    return config.getConnectionPoint();
  }
  
  public int getConnectionStatus()
  {
    return connectStatus;
  }
  
  public String getHost()
  {
    return config.getHost();
  }
  
  public String getServiceName()
  {
    return config.getServiceName();
  }
  
  protected void initDebugger()
  {
    Object localObject3 = null;
    if ((reader == null) || (writer == null)) {}
    while (!config.isDebuggerEnabled()) {
      return;
    }
    if (debugger == null) {
      try
      {
        str = System.getProperty("smack.debuggerClass");
        localObject2 = localObject3;
        if (str == null) {}
      }
      catch (Throwable localThrowable)
      {
        try
        {
          String str;
          localObject2 = Class.forName(str);
          if (localObject2 == null)
          {
            debugger = new AndroidDebugger(this, writer, reader);
            reader = debugger.getReader();
            writer = debugger.getWriter();
            return;
            localThrowable = localThrowable;
            Object localObject1 = null;
          }
        }
        catch (Exception localException1)
        {
          Object localObject2;
          for (;;)
          {
            localException1.printStackTrace();
            localObject2 = localObject3;
          }
          try
          {
            debugger = ((SmackDebugger)((Class)localObject2).getConstructor(new Class[] { Connection.class, Writer.class, Reader.class }).newInstance(new Object[] { this, writer, reader }));
            reader = debugger.getReader();
            writer = debugger.getWriter();
            return;
          }
          catch (Exception localException2)
          {
            throw new IllegalArgumentException("Can't initialize the configured debugger!", localException2);
          }
        }
      }
    }
    reader = debugger.newConnectionReader(reader);
    writer = debugger.newConnectionWriter(writer);
  }
  
  public boolean isConnected()
  {
    return connectStatus == 1;
  }
  
  public boolean isConnecting()
  {
    return connectStatus == 0;
  }
  
  public boolean isReadAlive()
  {
    return System.currentTimeMillis() - readAlive < SmackConfiguration.getCheckAliveInterval();
  }
  
  public boolean isReadAlive(long paramLong)
  {
    return readAlive >= paramLong;
  }
  
  public void removeConnectionListener(ConnectionListener paramConnectionListener)
  {
    connectionListeners.remove(paramConnectionListener);
  }
  
  public void resetConnTryTimes()
  {
    connTimes = 0;
  }
  
  public void resetConnectTime()
  {
    connectTime = -1L;
  }
  
  public abstract void sendPacket(Packet paramPacket)
    throws XMPPException;
  
  public abstract void sendPingString()
    throws XMPPException;
  
  public void setChallenge(String paramString)
  {
    MyLog.warn("setChallenge hash = " + MD5.MD5_32(paramString).substring(0, 8));
    challenge = paramString;
    setConnectionStatus(1, 0, null);
  }
  
  public void setConnectionStatus(int paramInt1, int paramInt2, Exception paramException)
  {
    if (paramInt1 != connectStatus) {
      MyLog.warn(String.format("update the connection status. %1$s -> %2$s : %3$s ", new Object[] { getDesc(connectStatus), getDesc(paramInt1), PushConstants.getErrorDesc(paramInt2) }));
    }
    if (Network.hasNetwork(mPushService)) {
      addStatus(paramInt1);
    }
    if (paramInt1 == 1)
    {
      mPushService.removeJobs(10);
      if (connectStatus != 0) {
        MyLog.warn("try set connected while not connecting.");
      }
      connectStatus = paramInt1;
      paramException = connectionListeners.iterator();
      while (paramException.hasNext()) {
        ((ConnectionListener)paramException.next()).reconnectionSuccessful(this);
      }
    }
    if (paramInt1 == 0)
    {
      mPushService.setConnectingTimeout();
      if (connectStatus != 2) {
        MyLog.warn("try set connecting while not disconnected.");
      }
      connectStatus = paramInt1;
      paramException = connectionListeners.iterator();
      while (paramException.hasNext()) {
        ((ConnectionListener)paramException.next()).connectionStarted(this);
      }
    }
    if (paramInt1 == 2)
    {
      mPushService.removeJobs(10);
      Object localObject;
      if (connectStatus == 0)
      {
        Iterator localIterator = connectionListeners.iterator();
        if (localIterator.hasNext())
        {
          ConnectionListener localConnectionListener = (ConnectionListener)localIterator.next();
          if (paramException == null) {}
          for (localObject = new CancellationException("disconnect while connecting");; localObject = paramException)
          {
            localConnectionListener.reconnectionFailed(this, (Exception)localObject);
            break;
          }
        }
      }
      else if (connectStatus == 1)
      {
        localObject = connectionListeners.iterator();
        while (((Iterator)localObject).hasNext()) {
          ((ConnectionListener)((Iterator)localObject).next()).connectionClosed(this, paramInt2, paramException);
        }
      }
      connectStatus = paramInt1;
    }
  }
  
  public void setReadAlive()
  {
    readAlive = System.currentTimeMillis();
  }
  
  public abstract void unbind(String paramString1, String paramString2)
    throws XMPPException;
  
  protected static class ListenerWrapper
  {
    private PacketFilter packetFilter;
    private PacketListener packetListener;
    
    public ListenerWrapper(PacketListener paramPacketListener, PacketFilter paramPacketFilter)
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
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.Connection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */