package com.amap.api.services.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class bs
{
  private static bs a;
  private av b;
  private Handler c;
  
  private bs(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean) {}
    try
    {
      b = av.a(paramInt);
      if (Looper.myLooper() == null)
      {
        c = new a(Looper.getMainLooper(), null);
        return;
      }
      c = new a();
      return;
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "NetManger", "NetManger1");
      localThrowable.printStackTrace();
    }
  }
  
  public static bs a(boolean paramBoolean)
  {
    return a(paramBoolean, 5);
  }
  
  private static bs a(boolean paramBoolean, int paramInt)
  {
    try
    {
      if (a == null) {
        a = new bs(paramBoolean, paramInt);
      }
      for (;;)
      {
        bs localbs = a;
        return localbs;
        if ((paramBoolean) && (ab == null)) {
          ab = av.a(paramInt);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
    finally {}
  }
  
  /* Error */
  private bv a(bt parambt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 74	com/amap/api/services/core/bt:e	()Lorg/apache/http/HttpEntity;
    //   4: astore 4
    //   6: aload_1
    //   7: invokevirtual 78	com/amap/api/services/core/bt:e_	()[B
    //   10: astore 5
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 81	com/amap/api/services/core/bs:c	(Lcom/amap/api/services/core/bt;)V
    //   17: aload_1
    //   18: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   21: ifnonnull +50 -> 71
    //   24: aconst_null
    //   25: astore_3
    //   26: new 87	com/amap/api/services/core/bq
    //   29: dup
    //   30: aload_1
    //   31: getfield 90	com/amap/api/services/core/bt:e	I
    //   34: aload_1
    //   35: getfield 93	com/amap/api/services/core/bt:f	I
    //   38: aload_3
    //   39: iload_2
    //   40: invokespecial 96	com/amap/api/services/core/bq:<init>	(IILjava/net/Proxy;Z)V
    //   43: astore_3
    //   44: aload 4
    //   46: ifnonnull +59 -> 105
    //   49: aload 5
    //   51: ifnonnull +54 -> 105
    //   54: aload_3
    //   55: aload_1
    //   56: invokevirtual 99	com/amap/api/services/core/bt:b	()Ljava/lang/String;
    //   59: aload_1
    //   60: invokevirtual 103	com/amap/api/services/core/bt:d_	()Ljava/util/Map;
    //   63: aload_1
    //   64: invokevirtual 106	com/amap/api/services/core/bt:c_	()Ljava/util/Map;
    //   67: invokevirtual 109	com/amap/api/services/core/bq:b	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lcom/amap/api/services/core/bv;
    //   70: areturn
    //   71: new 111	java/net/Proxy
    //   74: dup
    //   75: getstatic 117	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   78: aload_1
    //   79: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   82: invokevirtual 122	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   85: aload_1
    //   86: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   89: invokevirtual 126	org/apache/http/HttpHost:getPort	()I
    //   92: invokestatic 132	java/net/InetSocketAddress:createUnresolved	(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    //   95: invokespecial 135	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   98: astore_3
    //   99: goto -73 -> 26
    //   102: astore_1
    //   103: aload_1
    //   104: athrow
    //   105: aload 5
    //   107: ifnull +22 -> 129
    //   110: aload_3
    //   111: aload_1
    //   112: invokevirtual 99	com/amap/api/services/core/bt:b	()Ljava/lang/String;
    //   115: aload_1
    //   116: invokevirtual 103	com/amap/api/services/core/bt:d_	()Ljava/util/Map;
    //   119: aload_1
    //   120: invokevirtual 106	com/amap/api/services/core/bt:c_	()Ljava/util/Map;
    //   123: aload 5
    //   125: invokevirtual 138	com/amap/api/services/core/bq:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;[B)Lcom/amap/api/services/core/bv;
    //   128: areturn
    //   129: aload_3
    //   130: aload_1
    //   131: invokevirtual 99	com/amap/api/services/core/bt:b	()Ljava/lang/String;
    //   134: aload_1
    //   135: invokevirtual 103	com/amap/api/services/core/bt:d_	()Ljava/util/Map;
    //   138: aload_1
    //   139: invokevirtual 106	com/amap/api/services/core/bt:c_	()Ljava/util/Map;
    //   142: aload 4
    //   144: invokevirtual 141	com/amap/api/services/core/bq:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lorg/apache/http/HttpEntity;)Lcom/amap/api/services/core/bv;
    //   147: astore_1
    //   148: aload_1
    //   149: areturn
    //   150: astore_1
    //   151: aload_1
    //   152: invokevirtual 53	java/lang/Throwable:printStackTrace	()V
    //   155: new 68	com/amap/api/services/core/v
    //   158: dup
    //   159: ldc -113
    //   161: invokespecial 146	com/amap/api/services/core/v:<init>	(Ljava/lang/String;)V
    //   164: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	bs
    //   0	165	1	parambt	bt
    //   0	165	2	paramBoolean	boolean
    //   25	105	3	localObject	Object
    //   4	139	4	localHttpEntity	org.apache.http.HttpEntity
    //   10	114	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   12	24	102	com/amap/api/services/core/v
    //   26	44	102	com/amap/api/services/core/v
    //   54	71	102	com/amap/api/services/core/v
    //   71	99	102	com/amap/api/services/core/v
    //   110	129	102	com/amap/api/services/core/v
    //   129	148	102	com/amap/api/services/core/v
    //   12	24	150	java/lang/Throwable
    //   26	44	150	java/lang/Throwable
    //   54	71	150	java/lang/Throwable
    //   71	99	150	java/lang/Throwable
    //   110	129	150	java/lang/Throwable
    //   129	148	150	java/lang/Throwable
  }
  
  private void a(bv parambv, bu parambu)
  {
    parambu.a(b, a);
    parambv = new bx();
    b = parambu;
    parambu = Message.obtain();
    obj = parambv;
    what = 0;
    c.sendMessage(parambu);
  }
  
  private void a(v paramv, bu parambu)
  {
    bx localbx = new bx();
    a = paramv;
    b = parambu;
    paramv = Message.obtain();
    obj = localbx;
    what = 1;
    c.sendMessage(paramv);
  }
  
  /* Error */
  private bv b(bt parambt, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 81	com/amap/api/services/core/bs:c	(Lcom/amap/api/services/core/bt;)V
    //   5: aload_1
    //   6: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   9: ifnonnull +38 -> 47
    //   12: aconst_null
    //   13: astore_3
    //   14: new 87	com/amap/api/services/core/bq
    //   17: dup
    //   18: aload_1
    //   19: getfield 90	com/amap/api/services/core/bt:e	I
    //   22: aload_1
    //   23: getfield 93	com/amap/api/services/core/bt:f	I
    //   26: aload_3
    //   27: iload_2
    //   28: invokespecial 96	com/amap/api/services/core/bq:<init>	(IILjava/net/Proxy;Z)V
    //   31: aload_1
    //   32: invokevirtual 99	com/amap/api/services/core/bt:b	()Ljava/lang/String;
    //   35: aload_1
    //   36: invokevirtual 103	com/amap/api/services/core/bt:d_	()Ljava/util/Map;
    //   39: aload_1
    //   40: invokevirtual 106	com/amap/api/services/core/bt:c_	()Ljava/util/Map;
    //   43: invokevirtual 197	com/amap/api/services/core/bq:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lcom/amap/api/services/core/bv;
    //   46: areturn
    //   47: new 111	java/net/Proxy
    //   50: dup
    //   51: getstatic 117	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   54: aload_1
    //   55: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   58: invokevirtual 122	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   61: aload_1
    //   62: getfield 85	com/amap/api/services/core/bt:g	Lorg/apache/http/HttpHost;
    //   65: invokevirtual 126	org/apache/http/HttpHost:getPort	()I
    //   68: invokestatic 132	java/net/InetSocketAddress:createUnresolved	(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    //   71: invokespecial 135	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   74: astore_3
    //   75: goto -61 -> 14
    //   78: astore_1
    //   79: aload_1
    //   80: athrow
    //   81: astore_1
    //   82: aload_1
    //   83: invokevirtual 53	java/lang/Throwable:printStackTrace	()V
    //   86: new 68	com/amap/api/services/core/v
    //   89: dup
    //   90: ldc -113
    //   92: invokespecial 146	com/amap/api/services/core/v:<init>	(Ljava/lang/String;)V
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	bs
    //   0	96	1	parambt	bt
    //   0	96	2	paramBoolean	boolean
    //   13	62	3	localProxy	java.net.Proxy
    // Exception table:
    //   from	to	target	type
    //   0	12	78	com/amap/api/services/core/v
    //   14	47	78	com/amap/api/services/core/v
    //   47	75	78	com/amap/api/services/core/v
    //   0	12	81	java/lang/Throwable
    //   14	47	81	java/lang/Throwable
    //   47	75	81	java/lang/Throwable
  }
  
  private void c(bt parambt)
  {
    if (parambt == null) {
      throw new v("requeust is null");
    }
    if ((parambt.b() == null) || ("".equals(parambt.b()))) {
      throw new v("request url is empty");
    }
  }
  
  public byte[] a(bt parambt)
  {
    try
    {
      parambt = a(parambt, false);
      if (parambt != null) {
        return a;
      }
    }
    catch (v parambt)
    {
      throw parambt;
    }
    catch (Throwable parambt)
    {
      throw new v("未知的错误");
    }
    return null;
  }
  
  public byte[] b(bt parambt)
  {
    try
    {
      parambt = a(parambt, true);
      if (parambt != null) {
        return a;
      }
    }
    catch (v parambt)
    {
      throw parambt;
    }
    catch (Throwable parambt)
    {
      throw new v("未知的错误");
    }
    return null;
  }
  
  static class a
    extends Handler
  {
    public a() {}
    
    private a(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      try
      {
        switch (what)
        {
        case 0: 
          obj).b.a();
          return;
        }
      }
      catch (Throwable paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      paramMessage = (bx)obj;
      b.a(a);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */