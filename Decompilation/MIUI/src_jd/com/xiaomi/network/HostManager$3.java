package com.xiaomi.network;

class HostManager$3
  extends Fallback
{
  Fallback local = val$localFB;
  
  HostManager$3(HostManager paramHostManager, String paramString, Fallback paramFallback)
  {
    super(paramString);
    if (val$localFB != null) {
      ip = val$localFB.ip;
    }
  }
  
  public void accessHost(String paramString, AccessHistory paramAccessHistory)
  {
    try
    {
      if (local != null) {
        local.accessHost(paramString, paramAccessHistory);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  public java.util.ArrayList<String> getHosts(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 39	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 42	java/util/ArrayList:<init>	()V
    //   9: astore_3
    //   10: aload_0
    //   11: getfield 25	com/xiaomi/network/HostManager$3:local	Lcom/xiaomi/network/Fallback;
    //   14: ifnull +16 -> 30
    //   17: aload_3
    //   18: aload_0
    //   19: getfield 25	com/xiaomi/network/HostManager$3:local	Lcom/xiaomi/network/Fallback;
    //   22: iconst_1
    //   23: invokevirtual 44	com/xiaomi/network/Fallback:getHosts	(Z)Ljava/util/ArrayList;
    //   26: invokevirtual 48	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   29: pop
    //   30: getstatic 52	com/xiaomi/network/HostManager:mReservedHosts	Ljava/util/Map;
    //   33: astore_2
    //   34: aload_2
    //   35: monitorenter
    //   36: getstatic 52	com/xiaomi/network/HostManager:mReservedHosts	Ljava/util/Map;
    //   39: aload_0
    //   40: getfield 55	com/xiaomi/network/HostManager$3:host	Ljava/lang/String;
    //   43: invokeinterface 61 2 0
    //   48: checkcast 39	java/util/ArrayList
    //   51: astore 4
    //   53: aload 4
    //   55: ifnull +10 -> 65
    //   58: aload_3
    //   59: aload 4
    //   61: invokevirtual 48	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
    //   64: pop
    //   65: aload_2
    //   66: monitorexit
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_3
    //   70: areturn
    //   71: astore_3
    //   72: aload_2
    //   73: monitorexit
    //   74: aload_3
    //   75: athrow
    //   76: astore_2
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_2
    //   80: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	81	0	this	3
    //   0	81	1	paramBoolean	boolean
    //   76	4	2	localObject1	Object
    //   9	61	3	localArrayList1	java.util.ArrayList
    //   71	4	3	localObject2	Object
    //   51	9	4	localArrayList2	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   36	53	71	finally
    //   58	65	71	finally
    //   65	67	71	finally
    //   72	74	71	finally
    //   2	30	76	finally
    //   30	36	76	finally
    //   74	76	76	finally
  }
  
  public boolean isEffective()
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.HostManager.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */