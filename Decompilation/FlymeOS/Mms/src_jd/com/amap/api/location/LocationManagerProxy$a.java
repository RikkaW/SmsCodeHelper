package com.amap.api.location;

import android.os.Bundle;

class LocationManagerProxy$a
  implements AMapLocationListener
{
  LocationManagerProxy$a(LocationManagerProxy paramLocationManagerProxy) {}
  
  /* Error */
  public void onLocationChanged(android.location.Location paramLocation)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_1
    //   3: ifnull +497 -> 500
    //   6: new 24	com/amap/api/location/AMapLocation
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 26	com/amap/api/location/AMapLocation:<init>	(Landroid/location/Location;)V
    //   14: astore_1
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_0
    //   18: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   21: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   24: ifnull +100 -> 124
    //   27: iload_2
    //   28: aload_0
    //   29: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   32: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   35: invokevirtual 36	java/util/Vector:size	()I
    //   38: if_icmpge +86 -> 124
    //   41: aload_0
    //   42: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   45: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   48: iload_2
    //   49: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
    //   52: checkcast 42	com/amap/api/location/i
    //   55: astore 4
    //   57: aload 4
    //   59: ifnull +22 -> 81
    //   62: aload 4
    //   64: getfield 46	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
    //   67: ifnull +14 -> 81
    //   70: aload 4
    //   72: getfield 46	com/amap/api/location/i:b	Lcom/amap/api/location/AMapLocationListener;
    //   75: aload_1
    //   76: invokeinterface 49 2 0
    //   81: aload 4
    //   83: ifnull +409 -> 492
    //   86: aload 4
    //   88: getfield 52	com/amap/api/location/i:a	J
    //   91: ldc2_w 53
    //   94: lcmp
    //   95: ifne +397 -> 492
    //   98: aload_0
    //   99: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   102: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   105: ifnull +387 -> 492
    //   108: aload_0
    //   109: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   112: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   115: aload 4
    //   117: invokevirtual 61	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   120: pop
    //   121: goto +371 -> 492
    //   124: aload_0
    //   125: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   128: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   131: ifnull +368 -> 499
    //   134: aload_0
    //   135: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   138: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   141: invokevirtual 36	java/util/Vector:size	()I
    //   144: ifle +355 -> 499
    //   147: aload_0
    //   148: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   151: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   154: ifnull +345 -> 499
    //   157: iload_3
    //   158: istore_2
    //   159: iload_2
    //   160: aload_0
    //   161: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   164: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   167: invokevirtual 36	java/util/Vector:size	()I
    //   170: if_icmpge +32 -> 202
    //   173: aload_0
    //   174: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   177: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   180: aload_0
    //   181: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   184: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   187: iload_2
    //   188: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
    //   191: invokevirtual 64	java/util/Vector:remove	(Ljava/lang/Object;)Z
    //   194: pop
    //   195: iload_2
    //   196: iconst_1
    //   197: iadd
    //   198: istore_2
    //   199: goto -40 -> 159
    //   202: aload_0
    //   203: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   206: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   209: invokevirtual 67	java/util/Vector:clear	()V
    //   212: aload_0
    //   213: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   216: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   219: invokevirtual 36	java/util/Vector:size	()I
    //   222: ifne +277 -> 499
    //   225: aload_0
    //   226: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   229: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
    //   232: ifnull +267 -> 499
    //   235: aload_0
    //   236: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   239: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
    //   242: ifnull +257 -> 499
    //   245: aload_0
    //   246: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   249: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
    //   252: aload_0
    //   253: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   256: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
    //   259: invokevirtual 81	android/location/LocationManager:removeUpdates	(Landroid/location/LocationListener;)V
    //   262: return
    //   263: aload_0
    //   264: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   267: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   270: ifnull +72 -> 342
    //   273: iload_2
    //   274: aload_0
    //   275: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   278: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   281: invokevirtual 36	java/util/Vector:size	()I
    //   284: if_icmpge +58 -> 342
    //   287: aload_0
    //   288: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   291: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   294: iload_2
    //   295: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
    //   298: checkcast 42	com/amap/api/location/i
    //   301: astore_1
    //   302: aload_1
    //   303: ifnull +202 -> 505
    //   306: aload_1
    //   307: getfield 52	com/amap/api/location/i:a	J
    //   310: ldc2_w 53
    //   313: lcmp
    //   314: ifne +191 -> 505
    //   317: aload_0
    //   318: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   321: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   324: ifnull +181 -> 505
    //   327: aload_0
    //   328: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   331: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   334: aload_1
    //   335: invokevirtual 61	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   338: pop
    //   339: goto +166 -> 505
    //   342: aload_0
    //   343: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   346: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   349: ifnull +150 -> 499
    //   352: aload_0
    //   353: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   356: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   359: invokevirtual 36	java/util/Vector:size	()I
    //   362: ifle +137 -> 499
    //   365: aload_0
    //   366: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   369: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   372: ifnull +127 -> 499
    //   375: iconst_0
    //   376: istore_2
    //   377: iload_2
    //   378: aload_0
    //   379: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   382: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   385: invokevirtual 36	java/util/Vector:size	()I
    //   388: if_icmpge +32 -> 420
    //   391: aload_0
    //   392: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   395: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   398: aload_0
    //   399: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   402: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   405: iload_2
    //   406: invokevirtual 40	java/util/Vector:get	(I)Ljava/lang/Object;
    //   409: invokevirtual 64	java/util/Vector:remove	(Ljava/lang/Object;)Z
    //   412: pop
    //   413: iload_2
    //   414: iconst_1
    //   415: iadd
    //   416: istore_2
    //   417: goto -40 -> 377
    //   420: aload_0
    //   421: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   424: invokestatic 57	com/amap/api/location/LocationManagerProxy:d	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   427: invokevirtual 67	java/util/Vector:clear	()V
    //   430: aload_0
    //   431: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   434: invokestatic 30	com/amap/api/location/LocationManagerProxy:c	(Lcom/amap/api/location/LocationManagerProxy;)Ljava/util/Vector;
    //   437: invokevirtual 36	java/util/Vector:size	()I
    //   440: ifne +59 -> 499
    //   443: aload_0
    //   444: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   447: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
    //   450: ifnull +49 -> 499
    //   453: aload_0
    //   454: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   457: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
    //   460: ifnull +39 -> 499
    //   463: aload_0
    //   464: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   467: invokestatic 71	com/amap/api/location/LocationManagerProxy:e	(Lcom/amap/api/location/LocationManagerProxy;)Landroid/location/LocationManager;
    //   470: aload_0
    //   471: getfield 14	com/amap/api/location/LocationManagerProxy$a:a	Lcom/amap/api/location/LocationManagerProxy;
    //   474: invokestatic 75	com/amap/api/location/LocationManagerProxy:f	(Lcom/amap/api/location/LocationManagerProxy;)Lcom/amap/api/location/LocationManagerProxy$a;
    //   477: invokevirtual 81	android/location/LocationManager:removeUpdates	(Landroid/location/LocationListener;)V
    //   480: return
    //   481: astore_1
    //   482: aload_1
    //   483: invokevirtual 84	java/lang/Throwable:printStackTrace	()V
    //   486: return
    //   487: astore 5
    //   489: goto -408 -> 81
    //   492: iload_2
    //   493: iconst_1
    //   494: iadd
    //   495: istore_2
    //   496: goto -479 -> 17
    //   499: return
    //   500: iconst_0
    //   501: istore_2
    //   502: goto -239 -> 263
    //   505: iload_2
    //   506: iconst_1
    //   507: iadd
    //   508: istore_2
    //   509: goto -246 -> 263
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	512	0	this	a
    //   0	512	1	paramLocation	android.location.Location
    //   16	493	2	i	int
    //   1	157	3	j	int
    //   55	61	4	locali	i
    //   487	1	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   6	15	481	java/lang/Throwable
    //   17	57	481	java/lang/Throwable
    //   86	121	481	java/lang/Throwable
    //   124	157	481	java/lang/Throwable
    //   159	195	481	java/lang/Throwable
    //   202	262	481	java/lang/Throwable
    //   263	302	481	java/lang/Throwable
    //   306	339	481	java/lang/Throwable
    //   342	375	481	java/lang/Throwable
    //   377	413	481	java/lang/Throwable
    //   420	480	481	java/lang/Throwable
    //   62	81	487	java/lang/Throwable
  }
  
  public void onLocationChanged(AMapLocation paramAMapLocation) {}
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}

/* Location:
 * Qualified Name:     com.amap.api.location.LocationManagerProxy.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */