package com.amap.api.location;

import android.location.LocationListener;
import android.os.Bundle;

class f
  implements LocationListener
{
  f(d paramd) {}
  
  /* Error */
  public void onLocationChanged(android.location.Location paramLocation)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   4: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   7: iconst_1
    //   8: invokevirtual 33	com/amap/api/location/a:b	(Z)V
    //   11: aload_0
    //   12: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   15: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   18: invokestatic 39	android/os/SystemClock:elapsedRealtime	()J
    //   21: putfield 43	com/amap/api/location/a:e	J
    //   24: aconst_null
    //   25: astore_2
    //   26: aload_1
    //   27: ifnonnull +111 -> 138
    //   30: new 45	android/os/Message
    //   33: dup
    //   34: invokespecial 46	android/os/Message:<init>	()V
    //   37: astore_1
    //   38: aload_1
    //   39: aconst_null
    //   40: putfield 50	android/os/Message:obj	Ljava/lang/Object;
    //   43: aload_1
    //   44: bipush 100
    //   46: putfield 54	android/os/Message:what	I
    //   49: aload_0
    //   50: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   53: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   56: ifnull +15 -> 71
    //   59: aload_0
    //   60: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   63: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   66: aload_1
    //   67: invokevirtual 63	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   70: pop
    //   71: aload_0
    //   72: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   75: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   78: iconst_1
    //   79: putfield 67	com/amap/api/location/a:d	Z
    //   82: aload_0
    //   83: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   86: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   89: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   92: ifnull +45 -> 137
    //   95: aload_0
    //   96: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   99: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   102: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   105: getfield 76	com/amap/api/location/c:a	Lahp;
    //   108: ifnull +29 -> 137
    //   111: aload_0
    //   112: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   115: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   118: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   121: getfield 76	com/amap/api/location/c:a	Lahp;
    //   124: aload_0
    //   125: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   128: invokestatic 79	com/amap/api/location/d:c	(Lcom/amap/api/location/d;)Landroid/content/Context;
    //   131: aconst_null
    //   132: invokeinterface 84 3 0
    //   137: return
    //   138: aload_1
    //   139: invokevirtual 90	android/location/Location:getLatitude	()D
    //   142: aload_1
    //   143: invokevirtual 93	android/location/Location:getLongitude	()D
    //   146: invokestatic 98	com/amap/api/location/core/c:a	(DD)Z
    //   149: ifeq +156 -> 305
    //   152: aload_1
    //   153: invokevirtual 93	android/location/Location:getLongitude	()D
    //   156: aload_1
    //   157: invokevirtual 90	android/location/Location:getLatitude	()D
    //   160: invokestatic 103	aia:a	(DD)[D
    //   163: astore_3
    //   164: new 105	com/amap/api/location/AMapLocation
    //   167: dup
    //   168: aload_1
    //   169: invokespecial 107	com/amap/api/location/AMapLocation:<init>	(Landroid/location/Location;)V
    //   172: astore_1
    //   173: aload_1
    //   174: astore_2
    //   175: aload_1
    //   176: aload_3
    //   177: iconst_1
    //   178: daload
    //   179: invokevirtual 111	com/amap/api/location/AMapLocation:setLatitude	(D)V
    //   182: aload_1
    //   183: astore_2
    //   184: aload_1
    //   185: aload_3
    //   186: iconst_0
    //   187: daload
    //   188: invokevirtual 114	com/amap/api/location/AMapLocation:setLongitude	(D)V
    //   191: new 45	android/os/Message
    //   194: dup
    //   195: invokespecial 46	android/os/Message:<init>	()V
    //   198: astore_2
    //   199: aload_2
    //   200: aload_1
    //   201: putfield 50	android/os/Message:obj	Ljava/lang/Object;
    //   204: aload_2
    //   205: bipush 100
    //   207: putfield 54	android/os/Message:what	I
    //   210: aload_0
    //   211: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   214: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   217: ifnull +15 -> 232
    //   220: aload_0
    //   221: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   224: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   227: aload_2
    //   228: invokevirtual 63	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   231: pop
    //   232: aload_0
    //   233: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   236: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   239: iconst_1
    //   240: putfield 67	com/amap/api/location/a:d	Z
    //   243: aload_0
    //   244: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   247: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   250: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   253: ifnull -116 -> 137
    //   256: aload_0
    //   257: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   260: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   263: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   266: getfield 76	com/amap/api/location/c:a	Lahp;
    //   269: ifnull -132 -> 137
    //   272: aload_0
    //   273: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   276: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   279: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   282: getfield 76	com/amap/api/location/c:a	Lahp;
    //   285: aload_0
    //   286: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   289: invokestatic 79	com/amap/api/location/d:c	(Lcom/amap/api/location/d;)Landroid/content/Context;
    //   292: aload_1
    //   293: invokeinterface 84 3 0
    //   298: return
    //   299: astore_1
    //   300: aload_1
    //   301: invokevirtual 117	java/lang/Throwable:printStackTrace	()V
    //   304: return
    //   305: new 105	com/amap/api/location/AMapLocation
    //   308: dup
    //   309: aload_1
    //   310: invokespecial 107	com/amap/api/location/AMapLocation:<init>	(Landroid/location/Location;)V
    //   313: astore_1
    //   314: goto -123 -> 191
    //   317: astore_3
    //   318: aconst_null
    //   319: astore_1
    //   320: aload_1
    //   321: astore_2
    //   322: aload_3
    //   323: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   326: new 45	android/os/Message
    //   329: dup
    //   330: invokespecial 46	android/os/Message:<init>	()V
    //   333: astore_2
    //   334: aload_2
    //   335: aload_1
    //   336: putfield 50	android/os/Message:obj	Ljava/lang/Object;
    //   339: aload_2
    //   340: bipush 100
    //   342: putfield 54	android/os/Message:what	I
    //   345: aload_0
    //   346: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   349: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   352: ifnull +15 -> 367
    //   355: aload_0
    //   356: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   359: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   362: aload_2
    //   363: invokevirtual 63	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   366: pop
    //   367: aload_0
    //   368: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   371: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   374: iconst_1
    //   375: putfield 67	com/amap/api/location/a:d	Z
    //   378: aload_0
    //   379: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   382: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   385: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   388: ifnull -251 -> 137
    //   391: aload_0
    //   392: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   395: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   398: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   401: getfield 76	com/amap/api/location/c:a	Lahp;
    //   404: ifnull -267 -> 137
    //   407: aload_0
    //   408: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   411: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   414: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   417: getfield 76	com/amap/api/location/c:a	Lahp;
    //   420: aload_0
    //   421: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   424: invokestatic 79	com/amap/api/location/d:c	(Lcom/amap/api/location/d;)Landroid/content/Context;
    //   427: aload_1
    //   428: invokeinterface 84 3 0
    //   433: return
    //   434: new 45	android/os/Message
    //   437: dup
    //   438: invokespecial 46	android/os/Message:<init>	()V
    //   441: astore_3
    //   442: aload_3
    //   443: aload_2
    //   444: putfield 50	android/os/Message:obj	Ljava/lang/Object;
    //   447: aload_3
    //   448: bipush 100
    //   450: putfield 54	android/os/Message:what	I
    //   453: aload_0
    //   454: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   457: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   460: ifnull +15 -> 475
    //   463: aload_0
    //   464: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   467: invokestatic 57	com/amap/api/location/d:b	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a$a;
    //   470: aload_3
    //   471: invokevirtual 63	com/amap/api/location/a$a:sendMessage	(Landroid/os/Message;)Z
    //   474: pop
    //   475: aload_0
    //   476: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   479: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   482: iconst_1
    //   483: putfield 67	com/amap/api/location/a:d	Z
    //   486: aload_0
    //   487: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   490: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   493: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   496: ifnull +45 -> 541
    //   499: aload_0
    //   500: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   503: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   506: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   509: getfield 76	com/amap/api/location/c:a	Lahp;
    //   512: ifnull +29 -> 541
    //   515: aload_0
    //   516: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   519: invokestatic 27	com/amap/api/location/d:a	(Lcom/amap/api/location/d;)Lcom/amap/api/location/a;
    //   522: getfield 71	com/amap/api/location/a:c	Lcom/amap/api/location/c;
    //   525: getfield 76	com/amap/api/location/c:a	Lahp;
    //   528: aload_0
    //   529: getfield 12	com/amap/api/location/f:a	Lcom/amap/api/location/d;
    //   532: invokestatic 79	com/amap/api/location/d:c	(Lcom/amap/api/location/d;)Landroid/content/Context;
    //   535: aload_2
    //   536: invokeinterface 84 3 0
    //   541: aload_1
    //   542: athrow
    //   543: astore_1
    //   544: goto -110 -> 434
    //   547: astore_3
    //   548: goto -228 -> 320
    //   551: astore_1
    //   552: goto -118 -> 434
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	555	0	this	f
    //   0	555	1	paramLocation	android.location.Location
    //   25	511	2	localObject	Object
    //   163	23	3	arrayOfDouble	double[]
    //   317	6	3	localException1	Exception
    //   441	30	3	localMessage	android.os.Message
    //   547	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	24	299	java/lang/Throwable
    //   30	71	299	java/lang/Throwable
    //   71	137	299	java/lang/Throwable
    //   191	232	299	java/lang/Throwable
    //   232	298	299	java/lang/Throwable
    //   326	367	299	java/lang/Throwable
    //   367	433	299	java/lang/Throwable
    //   434	475	299	java/lang/Throwable
    //   475	541	299	java/lang/Throwable
    //   541	543	299	java/lang/Throwable
    //   138	173	317	java/lang/Exception
    //   305	314	317	java/lang/Exception
    //   175	182	543	finally
    //   184	191	543	finally
    //   322	326	543	finally
    //   175	182	547	java/lang/Exception
    //   184	191	547	java/lang/Exception
    //   138	173	551	finally
    //   305	314	551	finally
  }
  
  public void onProviderDisabled(String paramString)
  {
    if ("gps".equals(paramString)) {
      d.a(a).b(false);
    }
  }
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    if ((paramInt == 0) || (paramInt == 1)) {
      d.a(a).b(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */