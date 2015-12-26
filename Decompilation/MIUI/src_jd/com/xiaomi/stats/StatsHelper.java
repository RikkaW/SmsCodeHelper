package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.push.service.PushClientsManager.ClientLoginInfo;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.Hashtable;
import org.apache.thrift.TBase;

public class StatsHelper
{
  private static final int PING_RTT = ChannelStatsType.PING_RTT.getValue();
  
  public static void connectFail(String paramString, Exception paramException)
  {
    try
    {
      paramException = StatsAnalyser.fromConnectionException(paramException);
      StatsEvent localStatsEvent = StatsHandler.getInstance().createStatsEvent();
      localStatsEvent.setType(type.getValue());
      localStatsEvent.setAnnotation(annotation);
      localStatsEvent.setHost(paramString);
      StatsHandler.getInstance().add(localStatsEvent);
      return;
    }
    catch (NullPointerException paramString) {}
  }
  
  public static void connectionDown(String paramString, Exception paramException)
  {
    try
    {
      paramException = StatsAnalyser.fromDisconnectEx(paramException);
      StatsEvent localStatsEvent = StatsHandler.getInstance().createStatsEvent();
      localStatsEvent.setType(type.getValue());
      localStatsEvent.setAnnotation(annotation);
      localStatsEvent.setHost(paramString);
      StatsHandler.getInstance().add(localStatsEvent);
      return;
    }
    catch (NullPointerException paramString) {}
  }
  
  public static void count(int paramInt)
  {
    StatsEvent localStatsEvent = StatsHandler.getInstance().createStatsEvent();
    localStatsEvent.setType(ChannelStatsType.CHANNEL_STATS_COUNTER.getValue());
    localStatsEvent.setSubvalue(paramInt);
    StatsHandler.getInstance().add(localStatsEvent);
  }
  
  public static void pingEnded()
  {
    trackEnd(0, PING_RTT, null, -1);
  }
  
  public static void pingStarted()
  {
    trackStart(0, PING_RTT);
  }
  
  public static String retriveStatsAsString()
  {
    Object localObject2 = null;
    Object localObject3 = StatsHandler.getInstance().retriveStatsEvents();
    Object localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject3 = XmPushThriftSerializeUtils.convertThriftObjectToBytes((TBase)localObject3);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = new String(Base64Coder.encode((byte[])localObject3));
        MyLog.warn("stat encoded size = " + ((String)localObject1).length());
        MyLog.v((String)localObject1);
      }
    }
    return (String)localObject1;
  }
  
  public static void stats(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    StatsHandler.getInstance().add(paramInt1, paramInt2, paramInt3, paramString);
  }
  
  public static void statsBind(XMPushService paramXMPushService, PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    new BindTracker(paramXMPushService, paramClientLoginInfo).track();
  }
  
  public static void statsGslb(String paramString, int paramInt, Exception paramException)
  {
    StatsEvent localStatsEvent = StatsHandler.getInstance().createStatsEvent();
    if (paramInt > 0)
    {
      localStatsEvent.setType(ChannelStatsType.GSLB_REQUEST_SUCCESS.getValue());
      localStatsEvent.setHost(paramString);
      localStatsEvent.setValue(paramInt);
      StatsHandler.getInstance().add(localStatsEvent);
      return;
    }
    try
    {
      paramException = StatsAnalyser.fromGslbException(paramException);
      localStatsEvent.setType(type.getValue());
      localStatsEvent.setAnnotation(annotation);
      localStatsEvent.setHost(paramString);
      StatsHandler.getInstance().add(localStatsEvent);
      return;
    }
    catch (NullPointerException paramString) {}
  }
  
  /* Error */
  public static void trackEnd(int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 174	java/lang/System:currentTimeMillis	()J
    //   6: lstore 4
    //   8: iload_0
    //   9: bipush 24
    //   11: ishl
    //   12: iload_1
    //   13: ior
    //   14: istore_0
    //   15: getstatic 178	com/xiaomi/stats/StatsHelper$Holder:sTimeTracker	Ljava/util/Hashtable;
    //   18: iload_0
    //   19: invokestatic 184	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   22: invokevirtual 190	java/util/Hashtable:containsKey	(Ljava/lang/Object;)Z
    //   25: ifeq +86 -> 111
    //   28: invokestatic 42	com/xiaomi/stats/StatsHandler:getInstance	()Lcom/xiaomi/stats/StatsHandler;
    //   31: invokevirtual 46	com/xiaomi/stats/StatsHandler:createStatsEvent	()Lcom/xiaomi/push/thrift/StatsEvent;
    //   34: astore 6
    //   36: aload 6
    //   38: iload_1
    //   39: invokevirtual 57	com/xiaomi/push/thrift/StatsEvent:setType	(I)Lcom/xiaomi/push/thrift/StatsEvent;
    //   42: pop
    //   43: aload 6
    //   45: lload 4
    //   47: getstatic 178	com/xiaomi/stats/StatsHelper$Holder:sTimeTracker	Ljava/util/Hashtable;
    //   50: iload_0
    //   51: invokestatic 184	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   54: invokevirtual 194	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   57: checkcast 196	java/lang/Long
    //   60: invokevirtual 199	java/lang/Long:longValue	()J
    //   63: lsub
    //   64: l2i
    //   65: invokevirtual 165	com/xiaomi/push/thrift/StatsEvent:setValue	(I)Lcom/xiaomi/push/thrift/StatsEvent;
    //   68: pop
    //   69: aload 6
    //   71: aload_2
    //   72: invokevirtual 68	com/xiaomi/push/thrift/StatsEvent:setHost	(Ljava/lang/String;)Lcom/xiaomi/push/thrift/StatsEvent;
    //   75: pop
    //   76: iload_3
    //   77: iconst_m1
    //   78: if_icmple +10 -> 88
    //   81: aload 6
    //   83: iload_3
    //   84: invokevirtual 84	com/xiaomi/push/thrift/StatsEvent:setSubvalue	(I)Lcom/xiaomi/push/thrift/StatsEvent;
    //   87: pop
    //   88: invokestatic 42	com/xiaomi/stats/StatsHandler:getInstance	()Lcom/xiaomi/stats/StatsHandler;
    //   91: aload 6
    //   93: invokevirtual 72	com/xiaomi/stats/StatsHandler:add	(Lcom/xiaomi/push/thrift/StatsEvent;)V
    //   96: getstatic 178	com/xiaomi/stats/StatsHelper$Holder:sTimeTracker	Ljava/util/Hashtable;
    //   99: iload_1
    //   100: invokestatic 184	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   103: invokevirtual 202	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   106: pop
    //   107: ldc 2
    //   109: monitorexit
    //   110: return
    //   111: ldc -52
    //   113: invokestatic 207	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/String;)V
    //   116: goto -9 -> 107
    //   119: astore_2
    //   120: ldc 2
    //   122: monitorexit
    //   123: aload_2
    //   124: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramInt1	int
    //   0	125	1	paramInt2	int
    //   0	125	2	paramString	String
    //   0	125	3	paramInt3	int
    //   6	40	4	l	long
    //   34	58	6	localStatsEvent	StatsEvent
    // Exception table:
    //   from	to	target	type
    //   3	8	119	finally
    //   15	76	119	finally
    //   81	88	119	finally
    //   88	107	119	finally
    //   111	116	119	finally
  }
  
  public static void trackStart(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 16777215) {}
    for (;;)
    {
      try
      {
        Holder.sTimeTracker.put(Integer.valueOf(paramInt1 << 24 | paramInt2), Long.valueOf(System.currentTimeMillis()));
        return;
      }
      finally {}
      MyLog.e("stats key should less than 16777215");
    }
  }
  
  static class Holder
  {
    static Hashtable<Integer, Long> sTimeTracker = new Hashtable();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.StatsHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */