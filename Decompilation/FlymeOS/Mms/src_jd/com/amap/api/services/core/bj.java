package com.amap.api.services.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bj
{
  public static String a(long paramLong)
  {
    try
    {
      String str = new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS").format(new Date(paramLong));
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  static String a(Throwable paramThrowable)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: ldc 2
    //   5: monitorenter
    //   6: new 35	java/io/StringWriter
    //   9: dup
    //   10: invokespecial 36	java/io/StringWriter:<init>	()V
    //   13: astore_1
    //   14: new 38	java/io/PrintWriter
    //   17: dup
    //   18: aload_1
    //   19: invokespecial 41	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   22: astore 5
    //   24: aload 5
    //   26: astore_3
    //   27: aload_1
    //   28: astore_2
    //   29: aload_0
    //   30: aload 5
    //   32: invokevirtual 44	java/lang/Throwable:printStackTrace	(Ljava/io/PrintWriter;)V
    //   35: aload 5
    //   37: astore_3
    //   38: aload_1
    //   39: astore_2
    //   40: aload_0
    //   41: invokevirtual 48	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   44: astore_0
    //   45: aload_0
    //   46: ifnull +27 -> 73
    //   49: aload 5
    //   51: astore_3
    //   52: aload_1
    //   53: astore_2
    //   54: aload_0
    //   55: aload 5
    //   57: invokevirtual 44	java/lang/Throwable:printStackTrace	(Ljava/io/PrintWriter;)V
    //   60: aload 5
    //   62: astore_3
    //   63: aload_1
    //   64: astore_2
    //   65: aload_0
    //   66: invokevirtual 48	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   69: astore_0
    //   70: goto -25 -> 45
    //   73: aload 5
    //   75: astore_3
    //   76: aload_1
    //   77: astore_2
    //   78: aload_1
    //   79: invokevirtual 52	java/lang/Object:toString	()Ljava/lang/String;
    //   82: ldc 54
    //   84: ldc 56
    //   86: invokevirtual 62	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   89: astore_0
    //   90: aload_1
    //   91: ifnull +7 -> 98
    //   94: aload_1
    //   95: invokevirtual 67	java/io/Writer:close	()V
    //   98: aload_0
    //   99: astore_1
    //   100: aload 5
    //   102: ifnull +10 -> 112
    //   105: aload 5
    //   107: invokevirtual 68	java/io/PrintWriter:close	()V
    //   110: aload_0
    //   111: astore_1
    //   112: ldc 2
    //   114: monitorexit
    //   115: aload_1
    //   116: areturn
    //   117: astore 4
    //   119: aconst_null
    //   120: astore_0
    //   121: aconst_null
    //   122: astore_1
    //   123: aload_0
    //   124: astore_3
    //   125: aload_1
    //   126: astore_2
    //   127: aload 4
    //   129: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   132: aload_1
    //   133: ifnull +7 -> 140
    //   136: aload_1
    //   137: invokevirtual 67	java/io/Writer:close	()V
    //   140: aload 6
    //   142: astore_1
    //   143: aload_0
    //   144: ifnull -32 -> 112
    //   147: aload_0
    //   148: invokevirtual 68	java/io/PrintWriter:close	()V
    //   151: aload 6
    //   153: astore_1
    //   154: goto -42 -> 112
    //   157: astore_0
    //   158: aload_0
    //   159: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   162: aload 6
    //   164: astore_1
    //   165: goto -53 -> 112
    //   168: astore_0
    //   169: ldc 2
    //   171: monitorexit
    //   172: aload_0
    //   173: athrow
    //   174: astore_0
    //   175: aconst_null
    //   176: astore_3
    //   177: aconst_null
    //   178: astore_1
    //   179: aload_1
    //   180: ifnull +7 -> 187
    //   183: aload_1
    //   184: invokevirtual 67	java/io/Writer:close	()V
    //   187: aload_3
    //   188: ifnull +7 -> 195
    //   191: aload_3
    //   192: invokevirtual 68	java/io/PrintWriter:close	()V
    //   195: aload_0
    //   196: athrow
    //   197: astore_1
    //   198: aload_1
    //   199: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   202: goto -15 -> 187
    //   205: astore_1
    //   206: aload_1
    //   207: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   210: goto -15 -> 195
    //   213: astore_1
    //   214: aload_1
    //   215: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   218: goto -78 -> 140
    //   221: astore_1
    //   222: aload_1
    //   223: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   226: goto -128 -> 98
    //   229: astore_1
    //   230: aload_1
    //   231: invokevirtual 32	java/lang/Throwable:printStackTrace	()V
    //   234: aload_0
    //   235: astore_1
    //   236: goto -124 -> 112
    //   239: astore_0
    //   240: aconst_null
    //   241: astore_3
    //   242: goto -63 -> 179
    //   245: astore_0
    //   246: aload_2
    //   247: astore_1
    //   248: goto -69 -> 179
    //   251: astore 4
    //   253: aconst_null
    //   254: astore_0
    //   255: goto -132 -> 123
    //   258: astore 4
    //   260: aload 5
    //   262: astore_0
    //   263: goto -140 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	266	0	paramThrowable	Throwable
    //   13	171	1	localObject1	Object
    //   197	2	1	localThrowable1	Throwable
    //   205	2	1	localThrowable2	Throwable
    //   213	2	1	localThrowable3	Throwable
    //   221	2	1	localThrowable4	Throwable
    //   229	2	1	localThrowable5	Throwable
    //   235	13	1	localObject2	Object
    //   28	219	2	localObject3	Object
    //   26	216	3	localObject4	Object
    //   117	11	4	localThrowable6	Throwable
    //   251	1	4	localThrowable7	Throwable
    //   258	1	4	localThrowable8	Throwable
    //   22	239	5	localPrintWriter	java.io.PrintWriter
    //   1	162	6	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   6	14	117	java/lang/Throwable
    //   147	151	157	java/lang/Throwable
    //   94	98	168	finally
    //   105	110	168	finally
    //   136	140	168	finally
    //   147	151	168	finally
    //   158	162	168	finally
    //   183	187	168	finally
    //   191	195	168	finally
    //   195	197	168	finally
    //   198	202	168	finally
    //   206	210	168	finally
    //   214	218	168	finally
    //   222	226	168	finally
    //   230	234	168	finally
    //   6	14	174	finally
    //   183	187	197	java/lang/Throwable
    //   191	195	205	java/lang/Throwable
    //   136	140	213	java/lang/Throwable
    //   94	98	221	java/lang/Throwable
    //   105	110	229	java/lang/Throwable
    //   14	24	239	finally
    //   29	35	245	finally
    //   40	45	245	finally
    //   54	60	245	finally
    //   65	70	245	finally
    //   78	90	245	finally
    //   127	132	245	finally
    //   14	24	251	java/lang/Throwable
    //   29	35	258	java/lang/Throwable
    //   40	45	258	java/lang/Throwable
    //   54	60	258	java/lang/Throwable
    //   65	70	258	java/lang/Throwable
    //   78	90	258	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */