import android.content.BroadcastReceiver;

final class agj
  extends BroadcastReceiver
{
  private agj(agf paramagf) {}
  
  /* Error */
  public final void onReceive(android.content.Context paramContext, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +223 -> 224
    //   4: aload_2
    //   5: ifnull +219 -> 224
    //   8: aload_0
    //   9: getfield 10	agj:a	Lagf;
    //   12: invokestatic 27	agf:c	(Lagf;)Landroid/net/wifi/WifiManager;
    //   15: ifnull +209 -> 224
    //   18: aload_0
    //   19: getfield 10	agj:a	Lagf;
    //   22: invokestatic 31	agf:d	(Lagf;)Ljava/util/Timer;
    //   25: ifnull +199 -> 224
    //   28: aload_0
    //   29: getfield 10	agj:a	Lagf;
    //   32: invokestatic 35	agf:e	(Lagf;)Ljava/util/List;
    //   35: ifnull +189 -> 224
    //   38: aload_2
    //   39: invokevirtual 41	android/content/Intent:getAction	()Ljava/lang/String;
    //   42: ifnull +182 -> 224
    //   45: ldc 43
    //   47: aload_2
    //   48: invokevirtual 41	android/content/Intent:getAction	()Ljava/lang/String;
    //   51: invokevirtual 49	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   54: ifeq +170 -> 224
    //   57: aload_0
    //   58: getfield 10	agj:a	Lagf;
    //   61: invokestatic 27	agf:c	(Lagf;)Landroid/net/wifi/WifiManager;
    //   64: invokevirtual 55	android/net/wifi/WifiManager:getScanResults	()Ljava/util/List;
    //   67: astore_1
    //   68: aload_0
    //   69: monitorenter
    //   70: aload_0
    //   71: getfield 10	agj:a	Lagf;
    //   74: invokestatic 35	agf:e	(Lagf;)Ljava/util/List;
    //   77: invokeinterface 60 1 0
    //   82: aload_0
    //   83: getfield 10	agj:a	Lagf;
    //   86: invokestatic 66	java/lang/System:currentTimeMillis	()J
    //   89: invokestatic 69	agf:a	(Lagf;J)J
    //   92: pop2
    //   93: aload_1
    //   94: ifnull +56 -> 150
    //   97: aload_1
    //   98: invokeinterface 73 1 0
    //   103: ifle +47 -> 150
    //   106: iconst_0
    //   107: istore_3
    //   108: iload_3
    //   109: aload_1
    //   110: invokeinterface 73 1 0
    //   115: if_icmpge +35 -> 150
    //   118: aload_1
    //   119: iload_3
    //   120: invokeinterface 77 2 0
    //   125: checkcast 79	android/net/wifi/ScanResult
    //   128: astore_2
    //   129: aload_0
    //   130: getfield 10	agj:a	Lagf;
    //   133: invokestatic 35	agf:e	(Lagf;)Ljava/util/List;
    //   136: aload_2
    //   137: invokeinterface 82 2 0
    //   142: pop
    //   143: iload_3
    //   144: iconst_1
    //   145: iadd
    //   146: istore_3
    //   147: goto -39 -> 108
    //   150: aload_0
    //   151: monitorexit
    //   152: new 84	agk
    //   155: dup
    //   156: aload_0
    //   157: invokespecial 87	agk:<init>	(Lagj;)V
    //   160: astore_1
    //   161: aload_0
    //   162: monitorenter
    //   163: aload_0
    //   164: getfield 10	agj:a	Lagf;
    //   167: invokestatic 31	agf:d	(Lagf;)Ljava/util/Timer;
    //   170: ifnull +22 -> 192
    //   173: aload_0
    //   174: getfield 10	agj:a	Lagf;
    //   177: invokestatic 31	agf:d	(Lagf;)Ljava/util/Timer;
    //   180: invokevirtual 92	java/util/Timer:cancel	()V
    //   183: aload_0
    //   184: getfield 10	agj:a	Lagf;
    //   187: aconst_null
    //   188: invokestatic 95	agf:a	(Lagf;Ljava/util/Timer;)Ljava/util/Timer;
    //   191: pop
    //   192: aload_0
    //   193: getfield 10	agj:a	Lagf;
    //   196: new 89	java/util/Timer
    //   199: dup
    //   200: invokespecial 96	java/util/Timer:<init>	()V
    //   203: invokestatic 95	agf:a	(Lagf;Ljava/util/Timer;)Ljava/util/Timer;
    //   206: pop
    //   207: aload_0
    //   208: getfield 10	agj:a	Lagf;
    //   211: invokestatic 31	agf:d	(Lagf;)Ljava/util/Timer;
    //   214: aload_1
    //   215: invokestatic 99	agf:z	()I
    //   218: i2l
    //   219: invokevirtual 103	java/util/Timer:schedule	(Ljava/util/TimerTask;J)V
    //   222: aload_0
    //   223: monitorexit
    //   224: return
    //   225: astore_1
    //   226: aload_0
    //   227: monitorexit
    //   228: aload_1
    //   229: athrow
    //   230: astore_1
    //   231: aload_0
    //   232: monitorexit
    //   233: aload_1
    //   234: athrow
    //   235: astore_1
    //   236: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	237	0	this	agj
    //   0	237	1	paramContext	android.content.Context
    //   0	237	2	paramIntent	android.content.Intent
    //   107	40	3	i	int
    // Exception table:
    //   from	to	target	type
    //   70	93	225	finally
    //   97	106	225	finally
    //   108	143	225	finally
    //   150	152	225	finally
    //   163	192	230	finally
    //   192	224	230	finally
    //   8	70	235	java/lang/Exception
    //   152	163	235	java/lang/Exception
    //   226	230	235	java/lang/Exception
    //   231	235	235	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     agj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */