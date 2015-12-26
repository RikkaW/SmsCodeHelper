package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MIPushClientManager
{
  private static ArrayList<Pair<String, byte[]>> pendingMessages = new ArrayList();
  private static final Map<String, byte[]> pendingRegisterationRequests = new HashMap();
  
  public static void addPendingMessages(String paramString, byte[] paramArrayOfByte)
  {
    synchronized (pendingMessages)
    {
      pendingMessages.add(new Pair(paramString, paramArrayOfByte));
      if (pendingMessages.size() > 50) {
        pendingMessages.remove(0);
      }
      return;
    }
  }
  
  public static void notifyError(Context paramContext, String paramString1, byte[] paramArrayOfByte, int paramInt, String paramString2)
  {
    Intent localIntent = new Intent("com.xiaomi.mipush.ERROR");
    localIntent.setPackage(paramString1);
    localIntent.putExtra("mipush_payload", paramArrayOfByte);
    localIntent.putExtra("mipush_error_code", paramInt);
    localIntent.putExtra("mipush_error_msg", paramString2);
    paramContext.sendBroadcast(localIntent, ClientEventDispatcher.getReceiverPermission(paramString1));
  }
  
  public static void notifyRegisterError(Context paramContext, int paramInt, String paramString)
  {
    synchronized (pendingRegisterationRequests)
    {
      Iterator localIterator = pendingRegisterationRequests.keySet().iterator();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        notifyError(paramContext, str, (byte[])pendingRegisterationRequests.get(str), paramInt, paramString);
      }
    }
    pendingRegisterationRequests.clear();
  }
  
  /* Error */
  public static void processPendingMessages(XMPushService paramXMPushService)
  {
    // Byte code:
    //   0: getstatic 24	com/xiaomi/push/service/MIPushClientManager:pendingMessages	Ljava/util/ArrayList;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: getstatic 24	com/xiaomi/push/service/MIPushClientManager:pendingMessages	Ljava/util/ArrayList;
    //   9: astore_2
    //   10: new 21	java/util/ArrayList
    //   13: dup
    //   14: invokespecial 22	java/util/ArrayList:<init>	()V
    //   17: putstatic 24	com/xiaomi/push/service/MIPushClientManager:pendingMessages	Ljava/util/ArrayList;
    //   20: aload_1
    //   21: monitorexit
    //   22: aload_2
    //   23: invokevirtual 128	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   26: astore_1
    //   27: aload_1
    //   28: invokeinterface 106 1 0
    //   33: ifeq +46 -> 79
    //   36: aload_1
    //   37: invokeinterface 110 1 0
    //   42: checkcast 30	android/util/Pair
    //   45: astore_2
    //   46: aload_0
    //   47: aload_2
    //   48: getfield 132	android/util/Pair:first	Ljava/lang/Object;
    //   51: checkcast 112	java/lang/String
    //   54: aload_2
    //   55: getfield 135	android/util/Pair:second	Ljava/lang/Object;
    //   58: checkcast 118	[B
    //   61: invokevirtual 140	com/xiaomi/push/service/XMPushService:sendMIPushPacket	(Ljava/lang/String;[B)V
    //   64: goto -37 -> 27
    //   67: astore_1
    //   68: aload_1
    //   69: invokestatic 146	com/xiaomi/channel/commonutils/logger/MyLog:e	(Ljava/lang/Throwable;)V
    //   72: aload_0
    //   73: bipush 10
    //   75: aload_1
    //   76: invokevirtual 150	com/xiaomi/push/service/XMPushService:disconnect	(ILjava/lang/Exception;)V
    //   79: return
    //   80: astore_2
    //   81: aload_1
    //   82: monitorexit
    //   83: aload_2
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramXMPushService	XMPushService
    //   67	15	1	localXMPPException	XMPPException
    //   9	46	2	localObject2	Object
    //   80	4	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	6	67	com/xiaomi/smack/XMPPException
    //   22	27	67	com/xiaomi/smack/XMPPException
    //   27	64	67	com/xiaomi/smack/XMPPException
    //   83	85	67	com/xiaomi/smack/XMPPException
    //   6	22	80	finally
    //   81	83	80	finally
  }
  
  public static void processPendingRegistrationRequest(XMPushService paramXMPushService)
  {
    try
    {
      synchronized (pendingRegisterationRequests)
      {
        Iterator localIterator = pendingRegisterationRequests.keySet().iterator();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          paramXMPushService.sendMIPushPacket(str, (byte[])pendingRegisterationRequests.get(str));
        }
      }
      pendingRegisterationRequests.clear();
    }
    catch (XMPPException localXMPPException)
    {
      MyLog.e(localXMPPException);
      paramXMPushService.disconnect(10, localXMPPException);
      return;
    }
  }
  
  public static void registerApp(String paramString, byte[] paramArrayOfByte)
  {
    synchronized (pendingRegisterationRequests)
    {
      pendingRegisterationRequests.put(paramString, paramArrayOfByte);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushClientManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */