final class agn
  extends Thread
{
  agn(aie paramaie, String paramString)
  {
    super(paramString);
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: invokestatic 23	android/os/Looper:prepare	()V
    //   3: aload_0
    //   4: getfield 10	agn:a	Laie;
    //   7: invokestatic 27	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   10: invokestatic 32	aie:a	(Laie;Landroid/os/Looper;)Landroid/os/Looper;
    //   13: pop
    //   14: aload_0
    //   15: getfield 10	agn:a	Laie;
    //   18: new 34	agp
    //   21: dup
    //   22: aload_0
    //   23: getfield 10	agn:a	Laie;
    //   26: invokespecial 37	agp:<init>	(Laie;)V
    //   29: invokestatic 40	aie:a	(Laie;Lagp;)Lagp;
    //   32: pop
    //   33: aload_0
    //   34: getfield 10	agn:a	Laie;
    //   37: invokestatic 44	aie:e	(Laie;)Landroid/location/LocationManager;
    //   40: aload_0
    //   41: getfield 10	agn:a	Laie;
    //   44: invokestatic 48	aie:d	(Laie;)Lagp;
    //   47: invokevirtual 54	android/location/LocationManager:addGpsStatusListener	(Landroid/location/GpsStatus$Listener;)Z
    //   50: pop
    //   51: aload_0
    //   52: getfield 10	agn:a	Laie;
    //   55: invokestatic 44	aie:e	(Laie;)Landroid/location/LocationManager;
    //   58: aload_0
    //   59: getfield 10	agn:a	Laie;
    //   62: invokestatic 48	aie:d	(Laie;)Lagp;
    //   65: invokevirtual 58	android/location/LocationManager:addNmeaListener	(Landroid/location/GpsStatus$NmeaListener;)Z
    //   68: pop
    //   69: aload_0
    //   70: getfield 10	agn:a	Laie;
    //   73: new 60	ago
    //   76: dup
    //   77: aload_0
    //   78: invokespecial 63	ago:<init>	(Lagn;)V
    //   81: invokestatic 66	aie:a	(Laie;Landroid/os/Handler;)Landroid/os/Handler;
    //   84: pop
    //   85: aload_0
    //   86: getfield 10	agn:a	Laie;
    //   89: invokestatic 44	aie:e	(Laie;)Landroid/location/LocationManager;
    //   92: invokevirtual 70	android/location/LocationManager:getAllProviders	()Ljava/util/List;
    //   95: astore_1
    //   96: aload_1
    //   97: ifnull +23 -> 120
    //   100: aload_1
    //   101: ldc 72
    //   103: invokeinterface 78 2 0
    //   108: ifeq +12 -> 120
    //   111: aload_1
    //   112: ldc 80
    //   114: invokeinterface 78 2 0
    //   119: pop
    //   120: aload_0
    //   121: getfield 10	agn:a	Laie;
    //   124: invokestatic 44	aie:e	(Laie;)Landroid/location/LocationManager;
    //   127: ldc 80
    //   129: ldc2_w 81
    //   132: invokestatic 86	aie:l	()I
    //   135: i2f
    //   136: aload_0
    //   137: getfield 10	agn:a	Laie;
    //   140: invokestatic 90	aie:f	(Laie;)Landroid/location/LocationListener;
    //   143: invokevirtual 94	android/location/LocationManager:requestLocationUpdates	(Ljava/lang/String;JFLandroid/location/LocationListener;)V
    //   146: invokestatic 97	android/os/Looper:loop	()V
    //   149: return
    //   150: astore_1
    //   151: return
    //   152: astore_1
    //   153: goto -7 -> 146
    //   156: astore_1
    //   157: goto -88 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	160	0	this	agn
    //   95	17	1	localList	java.util.List
    //   150	1	1	localException1	Exception
    //   152	1	1	localException2	Exception
    //   156	1	1	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	33	150	java/lang/Exception
    //   69	96	150	java/lang/Exception
    //   100	120	150	java/lang/Exception
    //   146	149	150	java/lang/Exception
    //   120	146	152	java/lang/Exception
    //   33	69	156	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     agn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */