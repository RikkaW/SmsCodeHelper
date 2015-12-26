package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ex
{
  private static ex a;
  private ea b;
  private Handler c;
  
  private ex(boolean paramBoolean, int paramInt)
  {
    if (paramBoolean) {}
    try
    {
      b = ea.a(paramInt);
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
      ed.a(localThrowable, "NetManger", "NetManger1");
      localThrowable.printStackTrace();
    }
  }
  
  public static ex a()
  {
    return a(true, 5);
  }
  
  public static ex a(boolean paramBoolean)
  {
    return a(paramBoolean, 5);
  }
  
  private static ex a(boolean paramBoolean, int paramInt)
  {
    try
    {
      if (a == null) {
        a = new ex(paramBoolean, paramInt);
      }
      for (;;)
      {
        ex localex = a;
        return localex;
        if ((paramBoolean) && (ab == null)) {
          ab = ea.a(paramInt);
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
  private fa a(ey paramey, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 75	com/amap/api/mapcore2d/ey:e	()Lorg/apache/http/HttpEntity;
    //   4: astore 4
    //   6: aload_1
    //   7: invokevirtual 79	com/amap/api/mapcore2d/ey:a_	()[B
    //   10: astore 5
    //   12: aload_0
    //   13: aload_1
    //   14: invokespecial 82	com/amap/api/mapcore2d/ex:c	(Lcom/amap/api/mapcore2d/ey;)V
    //   17: aload_1
    //   18: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   21: ifnonnull +50 -> 71
    //   24: aconst_null
    //   25: astore_3
    //   26: new 88	com/amap/api/mapcore2d/ev
    //   29: dup
    //   30: aload_1
    //   31: getfield 91	com/amap/api/mapcore2d/ey:b	I
    //   34: aload_1
    //   35: getfield 93	com/amap/api/mapcore2d/ey:c	I
    //   38: aload_3
    //   39: iload_2
    //   40: invokespecial 96	com/amap/api/mapcore2d/ev:<init>	(IILjava/net/Proxy;Z)V
    //   43: astore_3
    //   44: aload 4
    //   46: ifnonnull +59 -> 105
    //   49: aload 5
    //   51: ifnonnull +54 -> 105
    //   54: aload_3
    //   55: aload_1
    //   56: invokevirtual 99	com/amap/api/mapcore2d/ey:d	()Ljava/lang/String;
    //   59: aload_1
    //   60: invokevirtual 102	com/amap/api/mapcore2d/ey:b	()Ljava/util/Map;
    //   63: aload_1
    //   64: invokevirtual 104	com/amap/api/mapcore2d/ey:c	()Ljava/util/Map;
    //   67: invokevirtual 107	com/amap/api/mapcore2d/ev:b	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lcom/amap/api/mapcore2d/fa;
    //   70: areturn
    //   71: new 109	java/net/Proxy
    //   74: dup
    //   75: getstatic 115	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   78: aload_1
    //   79: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   82: invokevirtual 120	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   85: aload_1
    //   86: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   89: invokevirtual 124	org/apache/http/HttpHost:getPort	()I
    //   92: invokestatic 130	java/net/InetSocketAddress:createUnresolved	(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    //   95: invokespecial 133	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   98: astore_3
    //   99: goto -73 -> 26
    //   102: astore_1
    //   103: aload_1
    //   104: athrow
    //   105: aload 5
    //   107: ifnull +22 -> 129
    //   110: aload_3
    //   111: aload_1
    //   112: invokevirtual 99	com/amap/api/mapcore2d/ey:d	()Ljava/lang/String;
    //   115: aload_1
    //   116: invokevirtual 102	com/amap/api/mapcore2d/ey:b	()Ljava/util/Map;
    //   119: aload_1
    //   120: invokevirtual 104	com/amap/api/mapcore2d/ey:c	()Ljava/util/Map;
    //   123: aload 5
    //   125: invokevirtual 136	com/amap/api/mapcore2d/ev:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;[B)Lcom/amap/api/mapcore2d/fa;
    //   128: areturn
    //   129: aload_3
    //   130: aload_1
    //   131: invokevirtual 99	com/amap/api/mapcore2d/ey:d	()Ljava/lang/String;
    //   134: aload_1
    //   135: invokevirtual 102	com/amap/api/mapcore2d/ey:b	()Ljava/util/Map;
    //   138: aload_1
    //   139: invokevirtual 104	com/amap/api/mapcore2d/ey:c	()Ljava/util/Map;
    //   142: aload 4
    //   144: invokevirtual 139	com/amap/api/mapcore2d/ev:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lorg/apache/http/HttpEntity;)Lcom/amap/api/mapcore2d/fa;
    //   147: astore_1
    //   148: aload_1
    //   149: areturn
    //   150: astore_1
    //   151: aload_1
    //   152: invokevirtual 53	java/lang/Throwable:printStackTrace	()V
    //   155: new 69	com/amap/api/mapcore2d/cz
    //   158: dup
    //   159: ldc -115
    //   161: invokespecial 144	com/amap/api/mapcore2d/cz:<init>	(Ljava/lang/String;)V
    //   164: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	ex
    //   0	165	1	paramey	ey
    //   0	165	2	paramBoolean	boolean
    //   25	105	3	localObject	Object
    //   4	139	4	localHttpEntity	org.apache.http.HttpEntity
    //   10	114	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   12	24	102	com/amap/api/mapcore2d/cz
    //   26	44	102	com/amap/api/mapcore2d/cz
    //   54	71	102	com/amap/api/mapcore2d/cz
    //   71	99	102	com/amap/api/mapcore2d/cz
    //   110	129	102	com/amap/api/mapcore2d/cz
    //   129	148	102	com/amap/api/mapcore2d/cz
    //   12	24	150	java/lang/Throwable
    //   26	44	150	java/lang/Throwable
    //   54	71	150	java/lang/Throwable
    //   71	99	150	java/lang/Throwable
    //   110	129	150	java/lang/Throwable
    //   129	148	150	java/lang/Throwable
  }
  
  private void a(cz paramcz, ez paramez)
  {
    fc localfc = new fc();
    a = paramcz;
    b = paramez;
    paramcz = Message.obtain();
    obj = localfc;
    what = 1;
    c.sendMessage(paramcz);
  }
  
  private void a(fa paramfa, ez paramez)
  {
    paramez.a(b, a);
    paramfa = new fc();
    b = paramez;
    paramez = Message.obtain();
    obj = paramfa;
    what = 0;
    c.sendMessage(paramez);
  }
  
  /* Error */
  private fa b(ey paramey, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 82	com/amap/api/mapcore2d/ex:c	(Lcom/amap/api/mapcore2d/ey;)V
    //   5: aload_1
    //   6: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   9: ifnonnull +38 -> 47
    //   12: aconst_null
    //   13: astore_3
    //   14: new 88	com/amap/api/mapcore2d/ev
    //   17: dup
    //   18: aload_1
    //   19: getfield 91	com/amap/api/mapcore2d/ey:b	I
    //   22: aload_1
    //   23: getfield 93	com/amap/api/mapcore2d/ey:c	I
    //   26: aload_3
    //   27: iload_2
    //   28: invokespecial 96	com/amap/api/mapcore2d/ev:<init>	(IILjava/net/Proxy;Z)V
    //   31: aload_1
    //   32: invokevirtual 99	com/amap/api/mapcore2d/ey:d	()Ljava/lang/String;
    //   35: aload_1
    //   36: invokevirtual 102	com/amap/api/mapcore2d/ey:b	()Ljava/util/Map;
    //   39: aload_1
    //   40: invokevirtual 104	com/amap/api/mapcore2d/ey:c	()Ljava/util/Map;
    //   43: invokevirtual 195	com/amap/api/mapcore2d/ev:a	(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lcom/amap/api/mapcore2d/fa;
    //   46: areturn
    //   47: new 109	java/net/Proxy
    //   50: dup
    //   51: getstatic 115	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   54: aload_1
    //   55: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   58: invokevirtual 120	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   61: aload_1
    //   62: getfield 86	com/amap/api/mapcore2d/ey:d	Lorg/apache/http/HttpHost;
    //   65: invokevirtual 124	org/apache/http/HttpHost:getPort	()I
    //   68: invokestatic 130	java/net/InetSocketAddress:createUnresolved	(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    //   71: invokespecial 133	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   74: astore_3
    //   75: goto -61 -> 14
    //   78: astore_1
    //   79: aload_1
    //   80: athrow
    //   81: astore_1
    //   82: aload_1
    //   83: invokevirtual 53	java/lang/Throwable:printStackTrace	()V
    //   86: new 69	com/amap/api/mapcore2d/cz
    //   89: dup
    //   90: ldc -115
    //   92: invokespecial 144	com/amap/api/mapcore2d/cz:<init>	(Ljava/lang/String;)V
    //   95: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	96	0	this	ex
    //   0	96	1	paramey	ey
    //   0	96	2	paramBoolean	boolean
    //   13	62	3	localProxy	java.net.Proxy
    // Exception table:
    //   from	to	target	type
    //   0	12	78	com/amap/api/mapcore2d/cz
    //   14	47	78	com/amap/api/mapcore2d/cz
    //   47	75	78	com/amap/api/mapcore2d/cz
    //   0	12	81	java/lang/Throwable
    //   14	47	81	java/lang/Throwable
    //   47	75	81	java/lang/Throwable
  }
  
  private void c(ey paramey)
  {
    if (paramey == null) {
      throw new cz("requeust is null");
    }
    if ((paramey.d() == null) || ("".equals(paramey.d()))) {
      throw new cz("request url is empty");
    }
  }
  
  public byte[] a(ey paramey)
  {
    try
    {
      paramey = a(paramey, false);
      if (paramey != null) {
        return a;
      }
    }
    catch (cz paramey)
    {
      throw paramey;
    }
    catch (Throwable paramey)
    {
      throw new cz("未知的错误");
    }
    return null;
  }
  
  public byte[] b(ey paramey)
  {
    try
    {
      paramey = b(paramey, false);
      if (paramey != null) {
        return a;
      }
    }
    catch (cz paramey)
    {
      throw paramey;
    }
    catch (Throwable paramey)
    {
      throw new cz("未知的错误");
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
      Looper.prepare();
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
      paramMessage = (fc)obj;
      b.a(a);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ex
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */