package com.amap.api.location;

import agu;
import ahf;
import aho;
import ahp;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.amap.api.location.core.AMapLocException;

public class c
  implements Runnable
{
  ahp a = null;
  volatile boolean b = false;
  boolean c = true;
  private volatile boolean d = false;
  private Context e;
  private long f = 2000L;
  private a.a g;
  private a h;
  private boolean i = false;
  
  c(Context paramContext, a.a parama, a parama1)
  {
    h = parama1;
    b(false);
    e = paramContext;
    a = new agu();
    g = parama;
  }
  
  private AMapLocation a(ahf paramahf)
  {
    AMapLocation localAMapLocation = new AMapLocation("");
    localAMapLocation.setProvider("lbs");
    localAMapLocation.setLatitude(paramahf.f());
    localAMapLocation.setLongitude(paramahf.e());
    localAMapLocation.setAccuracy(paramahf.g());
    localAMapLocation.setTime(paramahf.h());
    localAMapLocation.setPoiId(paramahf.b());
    localAMapLocation.setFloor(paramahf.c());
    localAMapLocation.setCountry(paramahf.n());
    localAMapLocation.setRoad(paramahf.q());
    localAMapLocation.setPoiName(paramahf.s());
    localAMapLocation.setAMapException(paramahf.a());
    Object localObject = new Bundle();
    ((Bundle)localObject).putString("citycode", paramahf.k());
    ((Bundle)localObject).putString("desc", paramahf.l());
    ((Bundle)localObject).putString("adcode", paramahf.m());
    localAMapLocation.setExtras((Bundle)localObject);
    localObject = paramahf.k();
    String str1 = paramahf.l();
    String str2 = paramahf.m();
    localAMapLocation.setCityCode((String)localObject);
    localAMapLocation.setAdCode(str2);
    if ((str2 != null) && (str2.trim().length() > 0)) {
      localAMapLocation.setAddress(str1.replace(" ", ""));
    }
    for (;;)
    {
      localAMapLocation.setCity(paramahf.p());
      localAMapLocation.setDistrict(paramahf.d());
      localAMapLocation.a(paramahf.r());
      localAMapLocation.setProvince(paramahf.o());
      return localAMapLocation;
      localAMapLocation.setAddress(str1);
    }
  }
  
  /* Error */
  private void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   4: invokestatic 213	com/amap/api/location/core/c:a	(Landroid/content/Context;)Lcom/amap/api/location/core/c;
    //   7: pop
    //   8: aload_0
    //   9: getfield 28	com/amap/api/location/c:a	Lahp;
    //   12: ifnull +16 -> 28
    //   15: aload_0
    //   16: getfield 28	com/amap/api/location/c:a	Lahp;
    //   19: aload_0
    //   20: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   23: invokeinterface 218 2 0
    //   28: aload_0
    //   29: getfield 28	com/amap/api/location/c:a	Lahp;
    //   32: ifnull +48 -> 80
    //   35: aload_0
    //   36: getfield 28	com/amap/api/location/c:a	Lahp;
    //   39: new 220	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   46: ldc -33
    //   48: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_0
    //   52: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   55: invokestatic 230	com/amap/api/location/core/c:b	(Landroid/content/Context;)Ljava/lang/String;
    //   58: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc -24
    //   63: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokestatic 233	com/amap/api/location/core/c:b	()Ljava/lang/String;
    //   69: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokeinterface 237 2 0
    //   80: new 239	org/json/JSONObject
    //   83: dup
    //   84: invokespecial 240	org/json/JSONObject:<init>	()V
    //   87: astore_1
    //   88: aload_1
    //   89: ldc -14
    //   91: aload_0
    //   92: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   95: invokestatic 230	com/amap/api/location/core/c:b	(Landroid/content/Context;)Ljava/lang/String;
    //   98: invokevirtual 246	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   101: pop
    //   102: aload_1
    //   103: ldc -8
    //   105: aload_0
    //   106: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   109: invokestatic 213	com/amap/api/location/core/c:a	(Landroid/content/Context;)Lcom/amap/api/location/core/c;
    //   112: ldc -6
    //   114: invokevirtual 253	com/amap/api/location/core/c:a	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokevirtual 246	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   120: pop
    //   121: new 239	org/json/JSONObject
    //   124: dup
    //   125: invokespecial 240	org/json/JSONObject:<init>	()V
    //   128: astore_2
    //   129: aload_0
    //   130: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   133: invokestatic 213	com/amap/api/location/core/c:a	(Landroid/content/Context;)Lcom/amap/api/location/core/c;
    //   136: invokevirtual 254	com/amap/api/location/core/c:c	()Ljava/lang/String;
    //   139: astore_3
    //   140: aload_2
    //   141: ldc_w 256
    //   144: aload_3
    //   145: ldc_w 258
    //   148: invokevirtual 262	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   151: invokestatic 267	com/amap/api/location/core/b:a	([B)Ljava/lang/String;
    //   154: invokevirtual 246	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   157: pop
    //   158: aload_1
    //   159: ldc_w 269
    //   162: aload_2
    //   163: invokevirtual 246	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   166: pop
    //   167: aload_1
    //   168: ldc_w 271
    //   171: ldc_w 273
    //   174: invokevirtual 246	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload_0
    //   179: getfield 28	com/amap/api/location/c:a	Lahp;
    //   182: ifnull +13 -> 195
    //   185: aload_0
    //   186: getfield 28	com/amap/api/location/c:a	Lahp;
    //   189: aload_1
    //   190: invokeinterface 276 2 0
    //   195: aload_0
    //   196: iconst_1
    //   197: putfield 38	com/amap/api/location/c:i	Z
    //   200: return
    //   201: astore_2
    //   202: aload_2
    //   203: invokevirtual 279	java/lang/Throwable:printStackTrace	()V
    //   206: goto -85 -> 121
    //   209: astore_1
    //   210: aload_1
    //   211: invokevirtual 280	org/json/JSONException:printStackTrace	()V
    //   214: goto -19 -> 195
    //   217: astore_3
    //   218: aload_3
    //   219: invokevirtual 281	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   222: goto -64 -> 158
    //   225: astore_1
    //   226: aload_1
    //   227: invokevirtual 279	java/lang/Throwable:printStackTrace	()V
    //   230: goto -35 -> 195
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	233	0	this	c
    //   87	103	1	localJSONObject1	org.json.JSONObject
    //   209	2	1	localJSONException	org.json.JSONException
    //   225	2	1	localThrowable1	Throwable
    //   128	35	2	localJSONObject2	org.json.JSONObject
    //   201	2	2	localThrowable2	Throwable
    //   139	6	3	str	String
    //   217	2	3	localUnsupportedEncodingException	java.io.UnsupportedEncodingException
    // Exception table:
    //   from	to	target	type
    //   102	121	201	java/lang/Throwable
    //   0	28	209	org/json/JSONException
    //   28	80	209	org/json/JSONException
    //   80	102	209	org/json/JSONException
    //   102	121	209	org/json/JSONException
    //   121	140	209	org/json/JSONException
    //   140	158	209	org/json/JSONException
    //   158	195	209	org/json/JSONException
    //   202	206	209	org/json/JSONException
    //   218	222	209	org/json/JSONException
    //   140	158	217	java/io/UnsupportedEncodingException
    //   0	28	225	java/lang/Throwable
    //   28	80	225	java/lang/Throwable
    //   80	102	225	java/lang/Throwable
    //   121	140	225	java/lang/Throwable
    //   140	158	225	java/lang/Throwable
    //   158	195	225	java/lang/Throwable
    //   202	206	225	java/lang/Throwable
    //   218	222	225	java/lang/Throwable
  }
  
  private ahf e()
  {
    ahf localahf2 = f();
    ahf localahf1 = localahf2;
    if (localahf2 == null)
    {
      localahf1 = new ahf();
      localahf1.a(new AMapLocException("未知的错误"));
      c = false;
    }
    return localahf1;
  }
  
  private ahf f()
  {
    for (;;)
    {
      try
      {
        if (a == null) {
          break label74;
        }
        localahf1 = a.a();
        if (localahf1 != null) {}
      }
      catch (AMapLocException localAMapLocException)
      {
        ahf localahf1;
        ahf localahf3 = new ahf();
        localahf3.a(localAMapLocException);
        c = false;
        return localahf3;
      }
      catch (Throwable localThrowable1)
      {
        localahf2 = null;
        c = false;
        localThrowable1.printStackTrace();
        return localahf2;
      }
      try
      {
        c = false;
        return localahf1;
      }
      catch (Throwable localThrowable2)
      {
        continue;
      }
      c = true;
      return localahf1;
      label74:
      ahf localahf2 = null;
    }
  }
  
  private boolean g()
  {
    boolean bool2 = false;
    long l = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
    if (l - h.i > 10000L)
    {
      bool1 = bool2;
      if (l - h.e > 10000L)
      {
        h.d = false;
        bool1 = true;
      }
    }
    return bool1;
  }
  
  void a(long paramLong)
  {
    if (paramLong > f) {
      f = paramLong;
    }
  }
  
  void a(aho paramaho, PendingIntent paramPendingIntent)
  {
    a.a(paramaho, paramPendingIntent);
  }
  
  void a(PendingIntent paramPendingIntent)
  {
    a.a(paramPendingIntent);
  }
  
  void a(boolean paramBoolean)
  {
    try
    {
      b = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean a()
  {
    try
    {
      boolean bool = b;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void b()
  {
    try
    {
      a(true);
      if (!d) {
        c();
      }
      if (h != null) {
        h.b();
      }
      i = false;
      return;
    }
    finally {}
  }
  
  void b(aho paramaho, PendingIntent paramPendingIntent)
  {
    a.b(paramaho, paramPendingIntent);
  }
  
  void b(PendingIntent paramPendingIntent)
  {
    a.b(paramPendingIntent);
  }
  
  void b(boolean paramBoolean)
  {
    try
    {
      d = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void c()
  {
    try
    {
      if (a != null) {
        a.b();
      }
      a = null;
      return;
    }
    finally {}
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: invokestatic 332	android/os/Looper:prepare	()V
    //   3: aload_0
    //   4: invokevirtual 334	com/amap/api/location/c:a	()Z
    //   7: ifeq +8 -> 15
    //   10: aload_0
    //   11: invokevirtual 319	com/amap/api/location/c:c	()V
    //   14: return
    //   15: aload_0
    //   16: getfield 38	com/amap/api/location/c:i	Z
    //   19: ifne +14 -> 33
    //   22: aload_0
    //   23: getfield 30	com/amap/api/location/c:d	Z
    //   26: ifeq +7 -> 33
    //   29: aload_0
    //   30: invokespecial 336	com/amap/api/location/c:d	()V
    //   33: aload_0
    //   34: getfield 30	com/amap/api/location/c:d	Z
    //   37: ifeq +167 -> 204
    //   40: invokestatic 342	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   43: invokevirtual 345	java/lang/Thread:isInterrupted	()Z
    //   46: ifne +158 -> 204
    //   49: aload_0
    //   50: invokevirtual 334	com/amap/api/location/c:a	()Z
    //   53: istore_2
    //   54: iload_2
    //   55: ifne +149 -> 204
    //   58: aconst_null
    //   59: astore 4
    //   61: aconst_null
    //   62: astore_3
    //   63: aload_0
    //   64: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   67: getfield 309	com/amap/api/location/a:d	Z
    //   70: ifeq +10 -> 80
    //   73: aload_0
    //   74: invokespecial 347	com/amap/api/location/c:g	()Z
    //   77: ifeq +15 -> 92
    //   80: aload_0
    //   81: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   84: getfield 349	com/amap/api/location/a:f	Z
    //   87: istore_2
    //   88: iload_2
    //   89: ifne +133 -> 222
    //   92: aload_0
    //   93: iconst_1
    //   94: putfield 40	com/amap/api/location/c:c	Z
    //   97: aload_0
    //   98: getfield 34	com/amap/api/location/c:f	J
    //   101: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   104: iconst_0
    //   105: ifeq +58 -> 163
    //   108: aload_0
    //   109: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   112: getfield 349	com/amap/api/location/a:f	Z
    //   115: ifeq +48 -> 163
    //   118: aload_0
    //   119: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   122: getfield 309	com/amap/api/location/a:d	Z
    //   125: ifeq +10 -> 135
    //   128: aload_0
    //   129: invokespecial 347	com/amap/api/location/c:g	()Z
    //   132: ifeq +31 -> 163
    //   135: new 354	android/os/Message
    //   138: dup
    //   139: invokespecial 355	android/os/Message:<init>	()V
    //   142: astore_3
    //   143: aload_3
    //   144: aconst_null
    //   145: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   148: aload_3
    //   149: bipush 100
    //   151: putfield 363	android/os/Message:what	I
    //   154: aload_0
    //   155: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   158: aload_3
    //   159: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   162: pop
    //   163: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   166: iconst_m1
    //   167: if_icmpne +19 -> 186
    //   170: aload_0
    //   171: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   174: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   177: pop
    //   178: aload_0
    //   179: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   182: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   185: pop
    //   186: aload_0
    //   187: getfield 40	com/amap/api/location/c:c	Z
    //   190: ifeq +646 -> 836
    //   193: aload_0
    //   194: getfield 34	com/amap/api/location/c:f	J
    //   197: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   200: goto -167 -> 33
    //   203: astore_3
    //   204: aload_0
    //   205: invokevirtual 334	com/amap/api/location/c:a	()Z
    //   208: ifeq +666 -> 874
    //   211: aload_0
    //   212: invokevirtual 319	com/amap/api/location/c:c	()V
    //   215: return
    //   216: astore_3
    //   217: aload_3
    //   218: invokevirtual 279	java/lang/Throwable:printStackTrace	()V
    //   221: return
    //   222: aload_0
    //   223: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   226: ifnull +117 -> 343
    //   229: aload_0
    //   230: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   233: getfield 381	com/amap/api/location/a:a	Ljava/util/Vector;
    //   236: invokevirtual 386	java/util/Vector:size	()I
    //   239: istore_1
    //   240: iload_1
    //   241: ifge +102 -> 343
    //   244: iconst_0
    //   245: ifeq +58 -> 303
    //   248: aload_0
    //   249: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   252: getfield 349	com/amap/api/location/a:f	Z
    //   255: ifeq +48 -> 303
    //   258: aload_0
    //   259: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   262: getfield 309	com/amap/api/location/a:d	Z
    //   265: ifeq +10 -> 275
    //   268: aload_0
    //   269: invokespecial 347	com/amap/api/location/c:g	()Z
    //   272: ifeq +31 -> 303
    //   275: new 354	android/os/Message
    //   278: dup
    //   279: invokespecial 355	android/os/Message:<init>	()V
    //   282: astore_3
    //   283: aload_3
    //   284: aconst_null
    //   285: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   288: aload_3
    //   289: bipush 100
    //   291: putfield 363	android/os/Message:what	I
    //   294: aload_0
    //   295: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   298: aload_3
    //   299: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   302: pop
    //   303: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   306: iconst_m1
    //   307: if_icmpne +19 -> 326
    //   310: aload_0
    //   311: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   314: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   317: pop
    //   318: aload_0
    //   319: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   322: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   325: pop
    //   326: aload_0
    //   327: getfield 40	com/amap/api/location/c:c	Z
    //   330: ifeq +515 -> 845
    //   333: aload_0
    //   334: getfield 34	com/amap/api/location/c:f	J
    //   337: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   340: goto -136 -> 204
    //   343: aload_0
    //   344: invokespecial 388	com/amap/api/location/c:e	()Lahf;
    //   347: astore 5
    //   349: aload 5
    //   351: ifnull +10 -> 361
    //   354: aload_0
    //   355: aload 5
    //   357: invokespecial 390	com/amap/api/location/c:a	(Lahf;)Lcom/amap/api/location/AMapLocation;
    //   360: astore_3
    //   361: aload_3
    //   362: ifnull +62 -> 424
    //   365: aload_0
    //   366: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   369: getfield 349	com/amap/api/location/a:f	Z
    //   372: ifeq +52 -> 424
    //   375: aload_0
    //   376: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   379: getfield 309	com/amap/api/location/a:d	Z
    //   382: ifeq +10 -> 392
    //   385: aload_0
    //   386: invokespecial 347	com/amap/api/location/c:g	()Z
    //   389: ifeq +35 -> 424
    //   392: new 354	android/os/Message
    //   395: dup
    //   396: invokespecial 355	android/os/Message:<init>	()V
    //   399: astore 4
    //   401: aload 4
    //   403: aload_3
    //   404: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   407: aload 4
    //   409: bipush 100
    //   411: putfield 363	android/os/Message:what	I
    //   414: aload_0
    //   415: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   418: aload 4
    //   420: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   423: pop
    //   424: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   427: iconst_m1
    //   428: if_icmpne +19 -> 447
    //   431: aload_0
    //   432: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   435: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   438: pop
    //   439: aload_0
    //   440: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   443: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   446: pop
    //   447: aload_0
    //   448: getfield 40	com/amap/api/location/c:c	Z
    //   451: ifeq +403 -> 854
    //   454: aload_0
    //   455: getfield 34	com/amap/api/location/c:f	J
    //   458: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   461: goto -428 -> 33
    //   464: astore_3
    //   465: aload_3
    //   466: invokevirtual 279	java/lang/Throwable:printStackTrace	()V
    //   469: new 56	com/amap/api/location/AMapLocation
    //   472: dup
    //   473: ldc 58
    //   475: invokespecial 61	com/amap/api/location/AMapLocation:<init>	(Ljava/lang/String;)V
    //   478: astore_3
    //   479: aload_3
    //   480: new 287	com/amap/api/location/core/AMapLocException
    //   483: dup
    //   484: ldc_w 289
    //   487: invokespecial 290	com/amap/api/location/core/AMapLocException:<init>	(Ljava/lang/String;)V
    //   490: invokevirtual 130	com/amap/api/location/AMapLocation:setAMapException	(Lcom/amap/api/location/core/AMapLocException;)V
    //   493: aload_3
    //   494: ifnull +62 -> 556
    //   497: aload_0
    //   498: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   501: getfield 349	com/amap/api/location/a:f	Z
    //   504: ifeq +52 -> 556
    //   507: aload_0
    //   508: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   511: getfield 309	com/amap/api/location/a:d	Z
    //   514: ifeq +10 -> 524
    //   517: aload_0
    //   518: invokespecial 347	com/amap/api/location/c:g	()Z
    //   521: ifeq +35 -> 556
    //   524: new 354	android/os/Message
    //   527: dup
    //   528: invokespecial 355	android/os/Message:<init>	()V
    //   531: astore 4
    //   533: aload 4
    //   535: aload_3
    //   536: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   539: aload 4
    //   541: bipush 100
    //   543: putfield 363	android/os/Message:what	I
    //   546: aload_0
    //   547: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   550: aload 4
    //   552: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   555: pop
    //   556: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   559: iconst_m1
    //   560: if_icmpne +19 -> 579
    //   563: aload_0
    //   564: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   567: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   570: pop
    //   571: aload_0
    //   572: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   575: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   578: pop
    //   579: aload_0
    //   580: getfield 40	com/amap/api/location/c:c	Z
    //   583: ifeq +135 -> 718
    //   586: aload_0
    //   587: getfield 34	com/amap/api/location/c:f	J
    //   590: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   593: goto -560 -> 33
    //   596: ldc2_w 391
    //   599: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   602: aload_3
    //   603: athrow
    //   604: astore_3
    //   605: aload_3
    //   606: invokevirtual 279	java/lang/Throwable:printStackTrace	()V
    //   609: goto -405 -> 204
    //   612: astore_3
    //   613: aload 4
    //   615: ifnull +63 -> 678
    //   618: aload_0
    //   619: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   622: getfield 349	com/amap/api/location/a:f	Z
    //   625: ifeq +53 -> 678
    //   628: aload_0
    //   629: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   632: getfield 309	com/amap/api/location/a:d	Z
    //   635: ifeq +10 -> 645
    //   638: aload_0
    //   639: invokespecial 347	com/amap/api/location/c:g	()Z
    //   642: ifeq +36 -> 678
    //   645: new 354	android/os/Message
    //   648: dup
    //   649: invokespecial 355	android/os/Message:<init>	()V
    //   652: astore 5
    //   654: aload 5
    //   656: aload 4
    //   658: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   661: aload 5
    //   663: bipush 100
    //   665: putfield 363	android/os/Message:what	I
    //   668: aload_0
    //   669: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   672: aload 5
    //   674: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   677: pop
    //   678: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   681: iconst_m1
    //   682: if_icmpne +19 -> 701
    //   685: aload_0
    //   686: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   689: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   692: pop
    //   693: aload_0
    //   694: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   697: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   700: pop
    //   701: aload_0
    //   702: getfield 40	com/amap/api/location/c:c	Z
    //   705: ifeq -109 -> 596
    //   708: aload_0
    //   709: getfield 34	com/amap/api/location/c:f	J
    //   712: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   715: goto -113 -> 602
    //   718: ldc2_w 391
    //   721: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   724: goto -691 -> 33
    //   727: astore_3
    //   728: iconst_0
    //   729: ifeq +58 -> 787
    //   732: aload_0
    //   733: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   736: getfield 349	com/amap/api/location/a:f	Z
    //   739: ifeq +48 -> 787
    //   742: aload_0
    //   743: getfield 42	com/amap/api/location/c:h	Lcom/amap/api/location/a;
    //   746: getfield 309	com/amap/api/location/a:d	Z
    //   749: ifeq +10 -> 759
    //   752: aload_0
    //   753: invokespecial 347	com/amap/api/location/c:g	()Z
    //   756: ifeq +31 -> 787
    //   759: new 354	android/os/Message
    //   762: dup
    //   763: invokespecial 355	android/os/Message:<init>	()V
    //   766: astore_3
    //   767: aload_3
    //   768: aconst_null
    //   769: putfield 359	android/os/Message:obj	Ljava/lang/Object;
    //   772: aload_3
    //   773: bipush 100
    //   775: putfield 363	android/os/Message:what	I
    //   778: aload_0
    //   779: getfield 52	com/amap/api/location/c:g	Lcom/amap/api/location/a$a;
    //   782: aload_3
    //   783: invokevirtual 369	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   786: pop
    //   787: invokestatic 373	com/amap/api/location/core/a:a	()I
    //   790: iconst_m1
    //   791: if_icmpne +19 -> 810
    //   794: aload_0
    //   795: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   798: invokestatic 376	com/amap/api/location/core/a:a	(Landroid/content/Context;)Z
    //   801: pop
    //   802: aload_0
    //   803: getfield 47	com/amap/api/location/c:e	Landroid/content/Context;
    //   806: invokestatic 378	com/amap/api/location/core/a:b	(Landroid/content/Context;)Z
    //   809: pop
    //   810: aload_0
    //   811: getfield 40	com/amap/api/location/c:c	Z
    //   814: ifeq +13 -> 827
    //   817: aload_0
    //   818: getfield 34	com/amap/api/location/c:f	J
    //   821: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   824: goto -620 -> 204
    //   827: ldc2_w 391
    //   830: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   833: goto -629 -> 204
    //   836: ldc2_w 391
    //   839: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   842: goto -809 -> 33
    //   845: ldc2_w 391
    //   848: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   851: goto -647 -> 204
    //   854: ldc2_w 391
    //   857: invokestatic 352	java/lang/Thread:sleep	(J)V
    //   860: goto -827 -> 33
    //   863: astore 5
    //   865: aload_3
    //   866: astore 4
    //   868: aload 5
    //   870: astore_3
    //   871: goto -258 -> 613
    //   874: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	875	0	this	c
    //   239	2	1	j	int
    //   53	36	2	bool	boolean
    //   62	97	3	localMessage	android.os.Message
    //   203	1	3	localThrowable1	Throwable
    //   216	2	3	localThrowable2	Throwable
    //   282	122	3	localObject1	Object
    //   464	2	3	localThrowable3	Throwable
    //   478	125	3	localAMapLocation	AMapLocation
    //   604	2	3	localThrowable4	Throwable
    //   612	1	3	localObject2	Object
    //   727	1	3	localThrowable5	Throwable
    //   766	105	3	localObject3	Object
    //   59	808	4	localObject4	Object
    //   347	326	5	localObject5	Object
    //   863	6	5	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   186	200	203	java/lang/Throwable
    //   326	340	203	java/lang/Throwable
    //   447	461	203	java/lang/Throwable
    //   579	593	203	java/lang/Throwable
    //   596	602	203	java/lang/Throwable
    //   701	715	203	java/lang/Throwable
    //   718	724	203	java/lang/Throwable
    //   810	824	203	java/lang/Throwable
    //   827	833	203	java/lang/Throwable
    //   836	842	203	java/lang/Throwable
    //   845	851	203	java/lang/Throwable
    //   854	860	203	java/lang/Throwable
    //   204	215	216	java/lang/Throwable
    //   63	80	464	java/lang/Throwable
    //   80	88	464	java/lang/Throwable
    //   222	240	464	java/lang/Throwable
    //   343	349	464	java/lang/Throwable
    //   354	361	464	java/lang/Throwable
    //   0	14	604	java/lang/Throwable
    //   15	33	604	java/lang/Throwable
    //   33	54	604	java/lang/Throwable
    //   108	135	604	java/lang/Throwable
    //   135	163	604	java/lang/Throwable
    //   163	186	604	java/lang/Throwable
    //   248	275	604	java/lang/Throwable
    //   275	303	604	java/lang/Throwable
    //   303	326	604	java/lang/Throwable
    //   365	392	604	java/lang/Throwable
    //   392	424	604	java/lang/Throwable
    //   424	447	604	java/lang/Throwable
    //   497	524	604	java/lang/Throwable
    //   524	556	604	java/lang/Throwable
    //   556	579	604	java/lang/Throwable
    //   602	604	604	java/lang/Throwable
    //   618	645	604	java/lang/Throwable
    //   645	678	604	java/lang/Throwable
    //   678	701	604	java/lang/Throwable
    //   732	759	604	java/lang/Throwable
    //   759	787	604	java/lang/Throwable
    //   787	810	604	java/lang/Throwable
    //   63	80	612	finally
    //   80	88	612	finally
    //   92	104	612	finally
    //   222	240	612	finally
    //   343	349	612	finally
    //   354	361	612	finally
    //   465	479	612	finally
    //   92	104	727	java/lang/Throwable
    //   479	493	863	finally
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */