package com.amap.api.location;

import aho;
import android.app.PendingIntent;
import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import java.util.Vector;

public class a
{
  static a j = null;
  Vector<i> a = null;
  d b = null;
  c c = null;
  boolean d = false;
  long e;
  boolean f = true;
  boolean g = true;
  b h;
  long i;
  private Context k;
  private a l = null;
  private Vector<i> m = new Vector();
  private AMapLocation n;
  private AMapLocation o;
  private volatile Thread p;
  private long q = 2000L;
  private float r = 10.0F;
  
  private a(Context paramContext, LocationManager paramLocationManager)
  {
    k = paramContext;
    e();
    if (Looper.myLooper() == null) {}
    for (l = new a(paramContext.getMainLooper());; l = new a())
    {
      b = new d(paramContext, paramLocationManager, l, this);
      c = new c(paramContext, l, this);
      b(false);
      f = true;
      g = true;
      h = new b(this, paramContext);
      return;
    }
  }
  
  public static a a(Context paramContext, LocationManager paramLocationManager)
  {
    try
    {
      if (j == null) {
        j = new a(paramContext, paramLocationManager);
      }
      paramContext = j;
      return paramContext;
    }
    finally {}
  }
  
  static void c()
  {
    try
    {
      if (j != null) {
        j.d();
      }
      j = null;
      return;
    }
    finally {}
  }
  
  private void c(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  private void d(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  private void e()
  {
    a = new Vector();
  }
  
  AMapLocation a()
  {
    if (n != null) {
      return n;
    }
    return com.amap.api.location.core.d.b(k);
  }
  
  void a(double paramDouble1, double paramDouble2, float paramFloat, long paramLong, PendingIntent paramPendingIntent)
  {
    aho localaho = new aho();
    b = paramDouble1;
    a = paramDouble2;
    c = paramFloat;
    localaho.a(paramLong);
    c.a(localaho, paramPendingIntent);
  }
  
  void a(int paramInt, AMapLocalWeatherListener paramAMapLocalWeatherListener)
  {
    try
    {
      new a.1(this, paramInt, paramAMapLocalWeatherListener).start();
      return;
    }
    catch (Throwable paramAMapLocalWeatherListener)
    {
      paramAMapLocalWeatherListener.printStackTrace();
    }
  }
  
  void a(long paramLong, float paramFloat, AMapLocationListener paramAMapLocationListener, String paramString, boolean paramBoolean)
  {
    q = paramLong;
    r = paramFloat;
    if (paramAMapLocationListener != null)
    {
      paramAMapLocationListener = new i(paramLong, paramFloat, paramAMapLocationListener, paramString, paramBoolean);
      if (!a.contains(paramAMapLocationListener)) {
        a.add(paramAMapLocationListener);
      }
      if (!"gps".equals(paramString)) {
        break label74;
      }
      b.a(paramLong, paramFloat);
    }
    label74:
    do
    {
      do
      {
        return;
      } while (!"lbs".equals(paramString));
      if (g) {
        b.a(paramLong, paramFloat);
      }
      c.a(paramLong);
      c(true);
    } while (p != null);
    c.b(true);
    p = new Thread(c);
    p.start();
  }
  
  void a(PendingIntent paramPendingIntent)
  {
    c.a(paramPendingIntent);
  }
  
  void a(AMapLocationListener paramAMapLocationListener)
  {
    if (a != null) {}
    for (int i1 = a.size();; i1 = 0)
    {
      int i2 = 0;
      i locali;
      if (i2 < i1)
      {
        locali = (i)a.get(i2);
        if (locali == null)
        {
          a.remove(i2);
          i2 -= 1;
          i1 -= 1;
        }
      }
      for (;;)
      {
        i2 += 1;
        break;
        if ((b == null) || (paramAMapLocationListener.equals(b)))
        {
          a.remove(locali);
          i2 -= 1;
          i1 -= 1;
          continue;
          if ((a == null) || (a.size() == 0))
          {
            b(false);
            c(false);
            b();
            if (b != null) {
              b.b();
            }
          }
          return;
        }
      }
    }
  }
  
  void a(boolean paramBoolean)
  {
    d(paramBoolean);
    if ((a != null) && (a.size() > 0))
    {
      if (paramBoolean)
      {
        b.b();
        b.a(q, r);
      }
    }
    else {
      return;
    }
    b.b();
  }
  
  void b()
  {
    if (c != null) {
      c.b(false);
    }
    if (p != null)
    {
      p.interrupt();
      p = null;
    }
  }
  
  void b(double paramDouble1, double paramDouble2, float paramFloat, long paramLong, PendingIntent paramPendingIntent)
  {
    aho localaho = new aho();
    b = paramDouble1;
    a = paramDouble2;
    c = paramFloat;
    localaho.a(paramLong);
    c.b(localaho, paramPendingIntent);
  }
  
  void b(PendingIntent paramPendingIntent)
  {
    c.b(paramPendingIntent);
  }
  
  void b(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  void d()
  {
    if (b != null)
    {
      b.b();
      b.a();
      b = null;
    }
    if (c != null) {
      c.b();
    }
    if (a != null) {
      a.clear();
    }
    b(false);
  }
  
  class a
    extends Handler
  {
    public a() {}
    
    public a(Looper paramLooper)
    {
      super();
      Looper.prepare();
    }
    
    /* Error */
    public void handleMessage(android.os.Message paramMessage)
    {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull +4 -> 5
      //   4: return
      //   5: aload_1
      //   6: getfield 37	android/os/Message:what	I
      //   9: bipush 100
      //   11: if_icmpne -7 -> 4
      //   14: aload_0
      //   15: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   18: getfield 40	com/amap/api/location/a:a	Ljava/util/Vector;
      //   21: astore_3
      //   22: aload_3
      //   23: ifnull -19 -> 4
      //   26: aload_0
      //   27: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   30: aload_1
      //   31: getfield 44	android/os/Message:obj	Ljava/lang/Object;
      //   34: checkcast 46	com/amap/api/location/AMapLocation
      //   37: invokestatic 49	com/amap/api/location/a:a	(Lcom/amap/api/location/a;Lcom/amap/api/location/AMapLocation;)Lcom/amap/api/location/AMapLocation;
      //   40: pop
      //   41: aload_0
      //   42: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   45: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   48: ifnull +47 -> 95
      //   51: aload_0
      //   52: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   55: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   58: invokevirtual 56	com/amap/api/location/AMapLocation:getAdCode	()Ljava/lang/String;
      //   61: ifnull +34 -> 95
      //   64: aload_0
      //   65: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   68: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   71: invokevirtual 56	com/amap/api/location/AMapLocation:getAdCode	()Ljava/lang/String;
      //   74: invokevirtual 62	java/lang/String:length	()I
      //   77: ifle +18 -> 95
      //   80: aload_0
      //   81: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   84: aload_0
      //   85: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   88: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   91: invokestatic 65	com/amap/api/location/a:b	(Lcom/amap/api/location/a;Lcom/amap/api/location/AMapLocation;)Lcom/amap/api/location/AMapLocation;
      //   94: pop
      //   95: aload_0
      //   96: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   99: getfield 40	com/amap/api/location/a:a	Ljava/util/Vector;
      //   102: invokevirtual 71	java/util/Vector:iterator	()Ljava/util/Iterator;
      //   105: astore_3
      //   106: aload_3
      //   107: invokeinterface 77 1 0
      //   112: ifeq +124 -> 236
      //   115: aload_3
      //   116: invokeinterface 81 1 0
      //   121: checkcast 83	com/amap/api/location/i
      //   124: astore 4
      //   126: aload 4
      //   128: getfield 86	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
      //   131: ifnull +48 -> 179
      //   134: aload_1
      //   135: getfield 44	android/os/Message:obj	Ljava/lang/Object;
      //   138: checkcast 46	com/amap/api/location/AMapLocation
      //   141: astore 5
      //   143: aload 4
      //   145: getfield 90	com/amap/api/location/i:c	Ljava/lang/Boolean;
      //   148: invokevirtual 95	java/lang/Boolean:booleanValue	()Z
      //   151: ifne +16 -> 167
      //   154: aload 5
      //   156: invokevirtual 99	com/amap/api/location/AMapLocation:getAMapException	()Lcom/amap/api/location/core/AMapLocException;
      //   159: invokevirtual 104	com/amap/api/location/core/AMapLocException:getErrorCode	()I
      //   162: istore_2
      //   163: iload_2
      //   164: ifne -58 -> 106
      //   167: aload 4
      //   169: getfield 86	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
      //   172: aload 5
      //   174: invokeinterface 110 2 0
      //   179: aload 4
      //   181: getfield 90	com/amap/api/location/i:c	Ljava/lang/Boolean;
      //   184: invokevirtual 95	java/lang/Boolean:booleanValue	()Z
      //   187: ifeq -81 -> 106
      //   190: aload 4
      //   192: getfield 113	com/amap/api/location/i:a	J
      //   195: ldc2_w 114
      //   198: lcmp
      //   199: ifne -93 -> 106
      //   202: aload_0
      //   203: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   206: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   209: ifnull -103 -> 106
      //   212: aload_0
      //   213: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   216: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   219: aload 4
      //   221: invokevirtual 122	java/util/Vector:add	(Ljava/lang/Object;)Z
      //   224: pop
      //   225: goto -119 -> 106
      //   228: astore_3
      //   229: aload_3
      //   230: invokevirtual 125	java/lang/Exception:printStackTrace	()V
      //   233: goto -138 -> 95
      //   236: aload_0
      //   237: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   240: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   243: ifnull +73 -> 316
      //   246: aload_0
      //   247: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   250: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   253: invokevirtual 128	java/util/Vector:size	()I
      //   256: ifle +60 -> 316
      //   259: iconst_0
      //   260: istore_2
      //   261: iload_2
      //   262: aload_0
      //   263: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   266: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   269: invokevirtual 128	java/util/Vector:size	()I
      //   272: if_icmpge +34 -> 306
      //   275: aload_0
      //   276: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   279: aload_0
      //   280: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   283: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   286: iload_2
      //   287: invokevirtual 132	java/util/Vector:get	(I)Ljava/lang/Object;
      //   290: checkcast 83	com/amap/api/location/i
      //   293: getfield 86	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
      //   296: invokevirtual 135	com/amap/api/location/a:a	(Lcom/amap/api/location/AMapLocationListener;)V
      //   299: iload_2
      //   300: iconst_1
      //   301: iadd
      //   302: istore_2
      //   303: goto -42 -> 261
      //   306: aload_0
      //   307: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   310: invokestatic 118	com/amap/api/location/a:b	(Lcom/amap/api/location/a;)Ljava/util/Vector;
      //   313: invokevirtual 138	java/util/Vector:clear	()V
      //   316: aload_0
      //   317: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   320: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   323: ifnull -319 -> 4
      //   326: aload_0
      //   327: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   330: invokestatic 141	com/amap/api/location/a:c	(Lcom/amap/api/location/a;)Landroid/content/Context;
      //   333: aload_0
      //   334: getfield 12	com/amap/api/location/a$a:a	Lcom/amap/api/location/a;
      //   337: invokestatic 52	com/amap/api/location/a:a	(Lcom/amap/api/location/a;)Lcom/amap/api/location/AMapLocation;
      //   340: invokestatic 146	com/amap/api/location/core/d:a	(Landroid/content/Context;Lcom/amap/api/location/AMapLocation;)V
      //   343: return
      //   344: astore 5
      //   346: goto -167 -> 179
      //   349: astore_1
      //   350: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	351	0	this	a
      //   0	351	1	paramMessage	android.os.Message
      //   162	141	2	i	int
      //   21	95	3	localObject	Object
      //   228	2	3	localException	Exception
      //   124	96	4	locali	i
      //   141	32	5	localAMapLocation	AMapLocation
      //   344	1	5	localThrowable	Throwable
      // Exception table:
      //   from	to	target	type
      //   26	95	228	java/lang/Exception
      //   167	179	344	java/lang/Throwable
      //   5	22	349	java/lang/Throwable
      //   26	95	349	java/lang/Throwable
      //   95	106	349	java/lang/Throwable
      //   106	163	349	java/lang/Throwable
      //   179	225	349	java/lang/Throwable
      //   229	233	349	java/lang/Throwable
      //   236	259	349	java/lang/Throwable
      //   261	299	349	java/lang/Throwable
      //   306	316	349	java/lang/Throwable
      //   316	343	349	java/lang/Throwable
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */