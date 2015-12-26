package cn.com.xy.sms.sdk.queue;

public final class b
  extends Thread
{
  private static boolean a = false;
  
  public static void a()
  {
    try
    {
      if (!a) {
        new b().start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  private void a(org.json.JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +132 -> 133
    //   4: aload_1
    //   5: ldc 23
    //   7: invokevirtual 29	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   10: invokestatic 34	cn/com/xy/sms/sdk/net/util/g:a	(Ljava/lang/String;)V
    //   13: aload_1
    //   14: ifnonnull +17 -> 31
    //   17: ldc2_w 35
    //   20: invokestatic 40	java/lang/Thread:sleep	(J)V
    //   23: aload_0
    //   24: invokestatic 46	cn/com/xy/sms/sdk/db/entity/c:b	()Lorg/json/JSONObject;
    //   27: invokespecial 48	cn/com/xy/sms/sdk/queue/b:a	(Lorg/json/JSONObject;)V
    //   30: return
    //   31: aload_1
    //   32: ldc 50
    //   34: invokestatic 56	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   37: checkcast 58	java/lang/String
    //   40: invokestatic 64	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   43: ifne -26 -> 17
    //   46: ldc 66
    //   48: ldc 68
    //   50: iconst_1
    //   51: anewarray 58	java/lang/String
    //   54: dup
    //   55: iconst_0
    //   56: aload_1
    //   57: ldc 50
    //   59: invokevirtual 29	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   62: aastore
    //   63: invokestatic 74	cn/com/xy/sms/sdk/db/DBManager:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   66: pop
    //   67: goto -50 -> 17
    //   70: astore_1
    //   71: goto -54 -> 17
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 77	java/lang/Throwable:printStackTrace	()V
    //   79: ldc2_w 35
    //   82: invokestatic 40	java/lang/Thread:sleep	(J)V
    //   85: aload_0
    //   86: invokestatic 46	cn/com/xy/sms/sdk/db/entity/c:b	()Lorg/json/JSONObject;
    //   89: invokespecial 48	cn/com/xy/sms/sdk/queue/b:a	(Lorg/json/JSONObject;)V
    //   92: return
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 77	java/lang/Throwable:printStackTrace	()V
    //   98: goto -13 -> 85
    //   101: astore_1
    //   102: ldc2_w 35
    //   105: invokestatic 40	java/lang/Thread:sleep	(J)V
    //   108: aload_0
    //   109: invokestatic 46	cn/com/xy/sms/sdk/db/entity/c:b	()Lorg/json/JSONObject;
    //   112: invokespecial 48	cn/com/xy/sms/sdk/queue/b:a	(Lorg/json/JSONObject;)V
    //   115: aload_1
    //   116: athrow
    //   117: astore_2
    //   118: aload_2
    //   119: invokevirtual 77	java/lang/Throwable:printStackTrace	()V
    //   122: goto -14 -> 108
    //   125: astore_1
    //   126: aload_1
    //   127: invokevirtual 77	java/lang/Throwable:printStackTrace	()V
    //   130: goto -107 -> 23
    //   133: iconst_0
    //   134: putstatic 10	cn/com/xy/sms/sdk/queue/b:a	Z
    //   137: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	138	0	this	b
    //   0	138	1	paramJSONObject	org.json.JSONObject
    //   117	2	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   31	67	70	java/lang/Throwable
    //   4	13	74	java/lang/Throwable
    //   79	85	93	java/lang/Throwable
    //   4	13	101	finally
    //   31	67	101	finally
    //   75	79	101	finally
    //   102	108	117	java/lang/Throwable
    //   17	23	125	java/lang/Throwable
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 80
    //   3: invokevirtual 83	cn/com/xy/sms/sdk/queue/b:setName	(Ljava/lang/String;)V
    //   6: getstatic 88	cn/com/xy/sms/sdk/queue/g:b	I
    //   9: invokestatic 94	android/os/Process:setThreadPriority	(I)V
    //   12: getstatic 10	cn/com/xy/sms/sdk/queue/b:a	Z
    //   15: ifne +24 -> 39
    //   18: iconst_1
    //   19: putstatic 10	cn/com/xy/sms/sdk/queue/b:a	Z
    //   22: ldc2_w 95
    //   25: invokestatic 40	java/lang/Thread:sleep	(J)V
    //   28: aload_0
    //   29: invokestatic 46	cn/com/xy/sms/sdk/db/entity/c:b	()Lorg/json/JSONObject;
    //   32: invokespecial 48	cn/com/xy/sms/sdk/queue/b:a	(Lorg/json/JSONObject;)V
    //   35: iconst_0
    //   36: putstatic 10	cn/com/xy/sms/sdk/queue/b:a	Z
    //   39: return
    //   40: astore_1
    //   41: aload_1
    //   42: invokevirtual 77	java/lang/Throwable:printStackTrace	()V
    //   45: return
    //   46: astore_1
    //   47: goto -12 -> 35
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	this	b
    //   40	2	1	localThrowable1	Throwable
    //   46	1	1	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	22	40	java/lang/Throwable
    //   35	39	40	java/lang/Throwable
    //   22	35	46	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */