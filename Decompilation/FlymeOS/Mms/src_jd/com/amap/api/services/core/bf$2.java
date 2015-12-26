package com.amap.api.services.core;

import android.content.Context;

final class bf$2
  implements Runnable
{
  bf$2(Context paramContext) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   9: iconst_0
    //   10: invokestatic 26	com/amap/api/services/core/bg:a	(Landroid/content/Context;I)Lcom/amap/api/services/core/bg;
    //   13: astore_1
    //   14: aload_0
    //   15: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   18: iconst_1
    //   19: invokestatic 26	com/amap/api/services/core/bg:a	(Landroid/content/Context;I)Lcom/amap/api/services/core/bg;
    //   22: astore_2
    //   23: aload_0
    //   24: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   27: iconst_2
    //   28: invokestatic 26	com/amap/api/services/core/bg:a	(Landroid/content/Context;I)Lcom/amap/api/services/core/bg;
    //   31: astore_3
    //   32: aload_3
    //   33: astore 4
    //   35: aload_1
    //   36: aload_0
    //   37: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   40: invokevirtual 29	com/amap/api/services/core/bg:b	(Landroid/content/Context;)V
    //   43: aload_3
    //   44: astore 4
    //   46: aload_2
    //   47: aload_0
    //   48: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   51: invokevirtual 29	com/amap/api/services/core/bg:b	(Landroid/content/Context;)V
    //   54: aload_3
    //   55: astore 4
    //   57: aload_3
    //   58: aload_0
    //   59: getfield 12	com/amap/api/services/core/bf$2:a	Landroid/content/Context;
    //   62: invokevirtual 29	com/amap/api/services/core/bg:b	(Landroid/content/Context;)V
    //   65: aload_1
    //   66: ifnull +7 -> 73
    //   69: aload_1
    //   70: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   73: aload_2
    //   74: ifnull +7 -> 81
    //   77: aload_2
    //   78: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   81: aload_3
    //   82: ifnull +7 -> 89
    //   85: aload_3
    //   86: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   89: return
    //   90: astore_1
    //   91: aconst_null
    //   92: astore_3
    //   93: aconst_null
    //   94: astore 4
    //   96: aconst_null
    //   97: astore_2
    //   98: aload_1
    //   99: ldc 34
    //   101: ldc 36
    //   103: invokestatic 41	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   106: aload_1
    //   107: invokevirtual 44	java/lang/Throwable:printStackTrace	()V
    //   110: aload 4
    //   112: ifnull +8 -> 120
    //   115: aload 4
    //   117: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   120: aload_3
    //   121: ifnull +7 -> 128
    //   124: aload_3
    //   125: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   128: aload_2
    //   129: ifnull -40 -> 89
    //   132: aload_2
    //   133: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   136: return
    //   137: astore_1
    //   138: aconst_null
    //   139: astore_3
    //   140: aconst_null
    //   141: astore 4
    //   143: aconst_null
    //   144: astore_2
    //   145: aload 4
    //   147: ifnull +8 -> 155
    //   150: aload 4
    //   152: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   155: aload_3
    //   156: ifnull +7 -> 163
    //   159: aload_3
    //   160: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   163: aload_2
    //   164: ifnull +7 -> 171
    //   167: aload_2
    //   168: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   171: aload_1
    //   172: athrow
    //   173: astore_1
    //   174: aconst_null
    //   175: astore_2
    //   176: aconst_null
    //   177: astore_1
    //   178: aload_1
    //   179: ifnull +7 -> 186
    //   182: aload_1
    //   183: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   186: aload_2
    //   187: ifnull +7 -> 194
    //   190: aload_2
    //   191: invokevirtual 32	com/amap/api/services/core/bg:c	()V
    //   194: aload_3
    //   195: ifnull -106 -> 89
    //   198: goto -113 -> 85
    //   201: astore_2
    //   202: aload_1
    //   203: astore 4
    //   205: aconst_null
    //   206: astore_3
    //   207: aload_2
    //   208: astore_1
    //   209: aconst_null
    //   210: astore_2
    //   211: goto -66 -> 145
    //   214: astore 5
    //   216: aload_1
    //   217: astore 4
    //   219: aload_2
    //   220: astore_3
    //   221: aconst_null
    //   222: astore_2
    //   223: aload 5
    //   225: astore_1
    //   226: goto -81 -> 145
    //   229: astore 6
    //   231: aload_1
    //   232: astore 4
    //   234: aload_2
    //   235: astore 5
    //   237: aload_3
    //   238: astore_2
    //   239: aload 6
    //   241: astore_1
    //   242: aload 5
    //   244: astore_3
    //   245: goto -100 -> 145
    //   248: astore_1
    //   249: goto -104 -> 145
    //   252: astore_2
    //   253: aload_1
    //   254: astore 4
    //   256: aconst_null
    //   257: astore_3
    //   258: aload_2
    //   259: astore_1
    //   260: aconst_null
    //   261: astore_2
    //   262: goto -164 -> 98
    //   265: astore 5
    //   267: aload_1
    //   268: astore 4
    //   270: aload_2
    //   271: astore_3
    //   272: aconst_null
    //   273: astore_2
    //   274: aload 5
    //   276: astore_1
    //   277: goto -179 -> 98
    //   280: astore 6
    //   282: aload_1
    //   283: astore 4
    //   285: aload_2
    //   286: astore 5
    //   288: aload_3
    //   289: astore_2
    //   290: aload 6
    //   292: astore_1
    //   293: aload 5
    //   295: astore_3
    //   296: goto -198 -> 98
    //   299: astore_2
    //   300: aconst_null
    //   301: astore_2
    //   302: goto -124 -> 178
    //   305: astore_3
    //   306: aload 4
    //   308: astore_3
    //   309: goto -131 -> 178
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	312	0	this	2
    //   13	57	1	localbg1	bg
    //   90	17	1	localThrowable1	Throwable
    //   137	35	1	localObject1	Object
    //   173	1	1	localRejectedExecutionException1	java.util.concurrent.RejectedExecutionException
    //   177	65	1	localObject2	Object
    //   248	6	1	localObject3	Object
    //   259	34	1	localObject4	Object
    //   22	169	2	localbg2	bg
    //   201	7	2	localObject5	Object
    //   210	29	2	localObject6	Object
    //   252	7	2	localThrowable2	Throwable
    //   261	29	2	localObject7	Object
    //   299	1	2	localRejectedExecutionException2	java.util.concurrent.RejectedExecutionException
    //   301	1	2	localObject8	Object
    //   4	292	3	localObject9	Object
    //   305	1	3	localRejectedExecutionException3	java.util.concurrent.RejectedExecutionException
    //   308	1	3	localObject10	Object
    //   1	306	4	localObject11	Object
    //   214	10	5	localObject12	Object
    //   235	8	5	localObject13	Object
    //   265	10	5	localThrowable3	Throwable
    //   286	8	5	localObject14	Object
    //   229	11	6	localObject15	Object
    //   280	11	6	localThrowable4	Throwable
    // Exception table:
    //   from	to	target	type
    //   5	14	90	java/lang/Throwable
    //   5	14	137	finally
    //   5	14	173	java/util/concurrent/RejectedExecutionException
    //   14	23	201	finally
    //   23	32	214	finally
    //   35	43	229	finally
    //   46	54	229	finally
    //   57	65	229	finally
    //   98	110	248	finally
    //   14	23	252	java/lang/Throwable
    //   23	32	265	java/lang/Throwable
    //   35	43	280	java/lang/Throwable
    //   46	54	280	java/lang/Throwable
    //   57	65	280	java/lang/Throwable
    //   14	23	299	java/util/concurrent/RejectedExecutionException
    //   23	32	305	java/util/concurrent/RejectedExecutionException
    //   35	43	305	java/util/concurrent/RejectedExecutionException
    //   46	54	305	java/util/concurrent/RejectedExecutionException
    //   57	65	305	java/util/concurrent/RejectedExecutionException
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bf.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */