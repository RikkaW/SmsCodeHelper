package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.stats.Stats;
import com.xiaomi.channel.commonutils.stats.Stats.Item;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.service.ServiceConfig.Listener;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.push.thrift.StatsEvents;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.util.SystemUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.XmPushTBinaryProtocol.Factory;
import org.apache.thrift.transport.TMemoryBuffer;

public class StatsHandler
{
  private boolean allowStatsUpload = false;
  private StatsContext context;
  private int duration;
  private long startTime;
  private Stats statsContainer = Stats.instance();
  private String uuid;
  
  private StatsEvent from(Stats.Item paramItem)
  {
    StatsEvent localStatsEvent = null;
    if (key == 0)
    {
      if ((obj instanceof StatsEvent)) {
        localStatsEvent = (StatsEvent)obj;
      }
      return localStatsEvent;
    }
    localStatsEvent = createStatsEvent();
    localStatsEvent.setType(ChannelStatsType.CHANNEL_STATS_COUNTER.getValue());
    localStatsEvent.setSubvalue(key);
    localStatsEvent.setAnnotation(annotation);
    return localStatsEvent;
  }
  
  static StatsContext getContext()
  {
    return sStatsHandlercontext;
  }
  
  public static StatsHandler getInstance()
  {
    return Holder.sStatsHandler;
  }
  
  private void internalAdd(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, long paramLong)
  {
    StatsEvent localStatsEvent = new StatsEvent();
    chid = ((byte)paramInt1);
    type = paramInt2;
    value = paramInt3;
    connpt = paramString2;
    host = paramString1;
    time = ((int)System.currentTimeMillis() / 1000);
    if (context.connection != null) {
      localStatsEvent.setClientIp(context.connection.getClientIP());
    }
    statsContainer.stat(localStatsEvent);
    MyLog.v(String.format(Locale.US, "add stats: chid = %s, type =%d, value = %d, connpt = %s", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramString2 }));
  }
  
  private StatsEvents retriveStatsEvents(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    StatsEvents localStatsEvents = new StatsEvents(uuid, localArrayList);
    if (!Network.isWIFIConnected(context.pushService)) {
      localStatsEvents.setOperator(DeviceInfo.getSimOperatorName(context.pushService));
    }
    TMemoryBuffer localTMemoryBuffer = new TMemoryBuffer(paramInt);
    TProtocol localTProtocol = new XmPushTBinaryProtocol.Factory().getProtocol(localTMemoryBuffer);
    try
    {
      localStatsEvents.write(localTProtocol);
      localLinkedList = statsContainer.getStats();
    }
    catch (TException localTException2)
    {
      try
      {
        for (;;)
        {
          LinkedList localLinkedList;
          StatsEvent localStatsEvent;
          if (localLinkedList.size() > 0)
          {
            localStatsEvent = from((Stats.Item)localLinkedList.getLast());
            if (localStatsEvent != null) {
              localStatsEvent.write(localTProtocol);
            }
            int i = localTMemoryBuffer.length();
            if (i <= paramInt) {}
          }
          else
          {
            MyLog.warn("stat approximate size = " + localTMemoryBuffer.length());
            return localStatsEvents;
          }
          if (localStatsEvent != null) {
            localArrayList.add(localStatsEvent);
          }
          localLinkedList.removeLast();
        }
      }
      catch (NoSuchElementException localNoSuchElementException)
      {
        for (;;) {}
        localTException2 = localTException2;
      }
      catch (TException localTException1)
      {
        for (;;) {}
      }
    }
  }
  
  private void startStats()
  {
    if (startTime == 0L) {
      startTime = System.currentTimeMillis();
    }
  }
  
  private void stopStatsIfNeed()
  {
    if ((allowStatsUpload) && (System.currentTimeMillis() - startTime > duration))
    {
      allowStatsUpload = false;
      startTime = 0L;
    }
  }
  
  /* Error */
  public void add(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 172	com/xiaomi/stats/StatsHandler:uuid	Ljava/lang/String;
    //   6: ifnonnull +22 -> 28
    //   9: getstatic 139	java/util/Locale:US	Ljava/util/Locale;
    //   12: ldc_w 267
    //   15: iconst_0
    //   16: anewarray 4	java/lang/Object
    //   19: invokestatic 153	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   22: invokestatic 159	com/xiaomi/channel/commonutils/logger/MyLog:v	(Ljava/lang/String;)V
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: aload_0
    //   29: getfield 85	com/xiaomi/stats/StatsHandler:context	Lcom/xiaomi/stats/StatsContext;
    //   32: getfield 179	com/xiaomi/stats/StatsContext:pushService	Lcom/xiaomi/push/service/XMPushService;
    //   35: invokestatic 270	com/xiaomi/channel/commonutils/network/Network:getActiveConnPoint	(Landroid/content/Context;)Ljava/lang/String;
    //   38: astore 5
    //   40: aload 5
    //   42: invokestatic 276	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   45: ifne -20 -> 25
    //   48: aload_0
    //   49: iload_1
    //   50: iload_2
    //   51: iload_3
    //   52: aload 4
    //   54: aload 5
    //   56: invokestatic 112	java/lang/System:currentTimeMillis	()J
    //   59: invokespecial 278	com/xiaomi/stats/StatsHandler:internalAdd	(IIILjava/lang/String;Ljava/lang/String;J)V
    //   62: goto -37 -> 25
    //   65: astore 4
    //   67: aload_0
    //   68: monitorexit
    //   69: aload 4
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	StatsHandler
    //   0	72	1	paramInt1	int
    //   0	72	2	paramInt2	int
    //   0	72	3	paramInt3	int
    //   0	72	4	paramString	String
    //   38	17	5	str	String
    // Exception table:
    //   from	to	target	type
    //   2	25	65	finally
    //   28	62	65	finally
  }
  
  void add(StatsEvent paramStatsEvent)
  {
    try
    {
      statsContainer.stat(paramStatsEvent);
      return;
    }
    finally
    {
      paramStatsEvent = finally;
      throw paramStatsEvent;
    }
  }
  
  StatsEvent createStatsEvent()
  {
    StatsEvent localStatsEvent = new StatsEvent();
    localStatsEvent.setConnpt(Network.getActiveConnPoint(context.pushService));
    chid = 0;
    value = 1;
    localStatsEvent.setTime((int)(System.currentTimeMillis() / 1000L));
    if (context.connection != null) {
      localStatsEvent.setClientIp(context.connection.getClientIP());
    }
    return localStatsEvent;
  }
  
  public void init(XMPushService paramXMPushService, XMPPConnection paramXMPPConnection)
  {
    try
    {
      context = new StatsContext(paramXMPushService);
      uuid = "";
      if (paramXMPPConnection != null) {
        paramXMPPConnection.addConnectionListener(context);
      }
      ServiceConfig.getInstance().addListener(new ServiceConfig.Listener()
      {
        public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg paramAnonymousPushServiceConfigMsg)
        {
          if (paramAnonymousPushServiceConfigMsg.hasDots()) {
            StatsHandler.getInstance().setDuration(paramAnonymousPushServiceConfigMsg.getDots());
          }
        }
      });
      return;
    }
    finally {}
  }
  
  public boolean isAllowStats()
  {
    return allowStatsUpload;
  }
  
  StatsEvents retriveStatsEvents()
  {
    StatsEvents localStatsEvents = null;
    try
    {
      if (shouldSendStatsNow())
      {
        int i = 750;
        if (!Network.isWIFIConnected(SystemUtils.getContext())) {
          i = 'ˮ' / 2;
        }
        localStatsEvents = retriveStatsEvents(i);
      }
      return localStatsEvents;
    }
    finally {}
  }
  
  public void setDuration(int paramInt)
  {
    if (paramInt > 0)
    {
      allowStatsUpload = true;
      int i = paramInt * 1000;
      paramInt = i;
      if (i > 604800000) {
        paramInt = 604800000;
      }
      if (duration != paramInt)
      {
        duration = paramInt;
        startStats();
      }
    }
  }
  
  boolean shouldSendStatsNow()
  {
    stopStatsIfNeed();
    return (allowStatsUpload) && (statsContainer.getCount() > 0);
  }
  
  static class Holder
  {
    static final StatsHandler sStatsHandler = new StatsHandler();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.StatsHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */