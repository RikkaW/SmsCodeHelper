package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

abstract class en
{
  private dh a;
  
  static en a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new eh();
    case 1: 
      return new ej();
    }
    return new ef();
  }
  
  private String a(Context paramContext, dh paramdh)
  {
    return dc.a(paramContext, paramdh);
  }
  
  private String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = dc.a(paramContext, paramString.getBytes("UTF-8"));
      return paramContext;
    }
    catch (UnsupportedEncodingException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private String a(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, String paramString5)
  {
    paramString1 = new StringBuffer();
    paramString1.append(paramString2).append(",").append("\"timestamp\":\"");
    paramString1.append(paramString3);
    paramString1.append("\",\"et\":\"");
    paramString1.append(paramInt);
    paramString1.append("\",\"classname\":\"");
    paramString1.append(paramString4);
    paramString1.append("\",");
    paramString1.append("\"detail\":\"");
    paramString1.append(paramString5);
    paramString1.append("\"");
    return paramString1.toString();
  }
  
  private void a(dp paramdp, String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    dr localdr = new dr();
    localdr.a(0);
    localdr.b(paramString1);
    localdr.a(paramString2);
    paramdp.b(localdr, paramInt);
  }
  
  /* Error */
  private boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, dp paramdp)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 15
    //   3: aconst_null
    //   4: astore 17
    //   6: aconst_null
    //   7: astore 16
    //   9: aconst_null
    //   10: astore 19
    //   12: aconst_null
    //   13: astore 20
    //   15: aconst_null
    //   16: astore 18
    //   18: aload 18
    //   20: astore 11
    //   22: aload 16
    //   24: astore 14
    //   26: aload 19
    //   28: astore 12
    //   30: aload 15
    //   32: astore 13
    //   34: aload 20
    //   36: astore 9
    //   38: aload 17
    //   40: astore 10
    //   42: new 98	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 99	java/lang/StringBuilder:<init>	()V
    //   49: astore 21
    //   51: aload 18
    //   53: astore 11
    //   55: aload 16
    //   57: astore 14
    //   59: aload 19
    //   61: astore 12
    //   63: aload 15
    //   65: astore 13
    //   67: aload 20
    //   69: astore 9
    //   71: aload 17
    //   73: astore 10
    //   75: aload 21
    //   77: aload_1
    //   78: invokevirtual 105	android/content/Context:getFilesDir	()Ljava/io/File;
    //   81: invokevirtual 110	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   84: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload 18
    //   90: astore 11
    //   92: aload 16
    //   94: astore 14
    //   96: aload 19
    //   98: astore 12
    //   100: aload 15
    //   102: astore 13
    //   104: aload 20
    //   106: astore 9
    //   108: aload 17
    //   110: astore 10
    //   112: aload 21
    //   114: getstatic 118	com/amap/api/mapcore2d/ek:a	Ljava/lang/String;
    //   117: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: pop
    //   121: aload 18
    //   123: astore 11
    //   125: aload 16
    //   127: astore 14
    //   129: aload 19
    //   131: astore 12
    //   133: aload 15
    //   135: astore 13
    //   137: aload 20
    //   139: astore 9
    //   141: aload 17
    //   143: astore 10
    //   145: aload 21
    //   147: aload_3
    //   148: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: aload 18
    //   154: astore 11
    //   156: aload 16
    //   158: astore 14
    //   160: aload 19
    //   162: astore 12
    //   164: aload 15
    //   166: astore 13
    //   168: aload 20
    //   170: astore 9
    //   172: aload 17
    //   174: astore 10
    //   176: new 107	java/io/File
    //   179: dup
    //   180: aload 21
    //   182: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokespecial 121	java/io/File:<init>	(Ljava/lang/String;)V
    //   188: astore_1
    //   189: aload 18
    //   191: astore 11
    //   193: aload 16
    //   195: astore 14
    //   197: aload 19
    //   199: astore 12
    //   201: aload 15
    //   203: astore 13
    //   205: aload 20
    //   207: astore 9
    //   209: aload 17
    //   211: astore 10
    //   213: aload_1
    //   214: invokevirtual 125	java/io/File:exists	()Z
    //   217: ifne +71 -> 288
    //   220: aload 18
    //   222: astore 11
    //   224: aload 16
    //   226: astore 14
    //   228: aload 19
    //   230: astore 12
    //   232: aload 15
    //   234: astore 13
    //   236: aload 20
    //   238: astore 9
    //   240: aload 17
    //   242: astore 10
    //   244: aload_1
    //   245: invokevirtual 128	java/io/File:mkdirs	()Z
    //   248: istore 6
    //   250: iload 6
    //   252: ifne +36 -> 288
    //   255: iconst_0
    //   256: istore 6
    //   258: iconst_0
    //   259: istore 6
    //   261: iconst_0
    //   262: ifeq +11 -> 273
    //   265: new 130	java/lang/NullPointerException
    //   268: dup
    //   269: invokespecial 131	java/lang/NullPointerException:<init>	()V
    //   272: athrow
    //   273: iconst_0
    //   274: ifeq +11 -> 285
    //   277: new 130	java/lang/NullPointerException
    //   280: dup
    //   281: invokespecial 131	java/lang/NullPointerException:<init>	()V
    //   284: athrow
    //   285: iload 6
    //   287: ireturn
    //   288: aload 18
    //   290: astore 11
    //   292: aload 16
    //   294: astore 14
    //   296: aload 19
    //   298: astore 12
    //   300: aload 15
    //   302: astore 13
    //   304: aload 20
    //   306: astore 9
    //   308: aload 17
    //   310: astore 10
    //   312: aload_1
    //   313: iconst_1
    //   314: iconst_1
    //   315: ldc2_w 132
    //   318: invokestatic 138	com/amap/api/mapcore2d/ep:a	(Ljava/io/File;IIJ)Lcom/amap/api/mapcore2d/ep;
    //   321: astore_1
    //   322: aload_1
    //   323: astore 11
    //   325: aload 16
    //   327: astore 14
    //   329: aload_1
    //   330: astore 12
    //   332: aload 15
    //   334: astore 13
    //   336: aload_1
    //   337: astore 9
    //   339: aload 17
    //   341: astore 10
    //   343: aload_1
    //   344: aload_0
    //   345: aload 5
    //   347: invokevirtual 141	com/amap/api/mapcore2d/en:a	(Lcom/amap/api/mapcore2d/dp;)Lcom/amap/api/mapcore2d/es;
    //   350: invokevirtual 144	com/amap/api/mapcore2d/ep:a	(Lcom/amap/api/mapcore2d/es;)V
    //   353: aload_1
    //   354: astore 11
    //   356: aload 16
    //   358: astore 14
    //   360: aload_1
    //   361: astore 12
    //   363: aload 15
    //   365: astore 13
    //   367: aload_1
    //   368: astore 9
    //   370: aload 17
    //   372: astore 10
    //   374: aload_1
    //   375: aload_2
    //   376: invokevirtual 147	com/amap/api/mapcore2d/ep:a	(Ljava/lang/String;)Lcom/amap/api/mapcore2d/ep$b;
    //   379: astore_3
    //   380: aload_3
    //   381: ifnull +58 -> 439
    //   384: iconst_0
    //   385: istore 8
    //   387: iconst_0
    //   388: istore 7
    //   390: iconst_0
    //   391: ifeq +11 -> 402
    //   394: new 130	java/lang/NullPointerException
    //   397: dup
    //   398: invokespecial 131	java/lang/NullPointerException:<init>	()V
    //   401: athrow
    //   402: iload 7
    //   404: istore 6
    //   406: aload_1
    //   407: ifnull -122 -> 285
    //   410: iload 7
    //   412: istore 6
    //   414: aload_1
    //   415: invokevirtual 149	com/amap/api/mapcore2d/ep:a	()Z
    //   418: ifne -133 -> 285
    //   421: aload_1
    //   422: invokevirtual 152	com/amap/api/mapcore2d/ep:close	()V
    //   425: iconst_0
    //   426: ireturn
    //   427: astore_1
    //   428: iload 8
    //   430: istore 6
    //   432: aload_1
    //   433: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   436: iload 6
    //   438: ireturn
    //   439: aload_1
    //   440: astore 11
    //   442: aload 16
    //   444: astore 14
    //   446: aload_1
    //   447: astore 12
    //   449: aload 15
    //   451: astore 13
    //   453: aload_1
    //   454: astore 9
    //   456: aload 17
    //   458: astore 10
    //   460: aload 4
    //   462: ldc 33
    //   464: invokevirtual 39	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   467: astore_3
    //   468: aload_1
    //   469: astore 11
    //   471: aload 16
    //   473: astore 14
    //   475: aload_1
    //   476: astore 12
    //   478: aload 15
    //   480: astore 13
    //   482: aload_1
    //   483: astore 9
    //   485: aload 17
    //   487: astore 10
    //   489: aload_1
    //   490: aload_2
    //   491: invokevirtual 155	com/amap/api/mapcore2d/ep:b	(Ljava/lang/String;)Lcom/amap/api/mapcore2d/ep$a;
    //   494: astore 4
    //   496: aload_1
    //   497: astore 11
    //   499: aload 16
    //   501: astore 14
    //   503: aload_1
    //   504: astore 12
    //   506: aload 15
    //   508: astore 13
    //   510: aload_1
    //   511: astore 9
    //   513: aload 17
    //   515: astore 10
    //   517: aload 4
    //   519: iconst_0
    //   520: invokevirtual 160	com/amap/api/mapcore2d/ep$a:a	(I)Ljava/io/OutputStream;
    //   523: astore_2
    //   524: aload_1
    //   525: astore 11
    //   527: aload_2
    //   528: astore 14
    //   530: aload_1
    //   531: astore 12
    //   533: aload_2
    //   534: astore 13
    //   536: aload_1
    //   537: astore 9
    //   539: aload_2
    //   540: astore 10
    //   542: aload_2
    //   543: aload_3
    //   544: invokevirtual 166	java/io/OutputStream:write	([B)V
    //   547: aload_1
    //   548: astore 11
    //   550: aload_2
    //   551: astore 14
    //   553: aload_1
    //   554: astore 12
    //   556: aload_2
    //   557: astore 13
    //   559: aload_1
    //   560: astore 9
    //   562: aload_2
    //   563: astore 10
    //   565: aload 4
    //   567: invokevirtual 168	com/amap/api/mapcore2d/ep$a:a	()V
    //   570: aload_1
    //   571: astore 11
    //   573: aload_2
    //   574: astore 14
    //   576: aload_1
    //   577: astore 12
    //   579: aload_2
    //   580: astore 13
    //   582: aload_1
    //   583: astore 9
    //   585: aload_2
    //   586: astore 10
    //   588: aload_1
    //   589: invokevirtual 170	com/amap/api/mapcore2d/ep:b	()V
    //   592: iconst_1
    //   593: istore 8
    //   595: iconst_1
    //   596: istore 7
    //   598: aload_2
    //   599: ifnull +7 -> 606
    //   602: aload_2
    //   603: invokevirtual 171	java/io/OutputStream:close	()V
    //   606: iload 7
    //   608: istore 6
    //   610: aload_1
    //   611: ifnull -326 -> 285
    //   614: iload 7
    //   616: istore 6
    //   618: aload_1
    //   619: invokevirtual 149	com/amap/api/mapcore2d/ep:a	()Z
    //   622: ifne -337 -> 285
    //   625: aload_1
    //   626: invokevirtual 152	com/amap/api/mapcore2d/ep:close	()V
    //   629: iconst_1
    //   630: ireturn
    //   631: astore_1
    //   632: iload 8
    //   634: istore 6
    //   636: goto -204 -> 432
    //   639: astore_1
    //   640: aload 11
    //   642: astore 9
    //   644: aload 14
    //   646: astore 10
    //   648: aload_1
    //   649: invokevirtual 172	java/io/IOException:printStackTrace	()V
    //   652: aload 14
    //   654: ifnull +8 -> 662
    //   657: aload 14
    //   659: invokevirtual 171	java/io/OutputStream:close	()V
    //   662: aload 11
    //   664: ifnull +16 -> 680
    //   667: aload 11
    //   669: invokevirtual 149	com/amap/api/mapcore2d/ep:a	()Z
    //   672: ifne +8 -> 680
    //   675: aload 11
    //   677: invokevirtual 152	com/amap/api/mapcore2d/ep:close	()V
    //   680: iconst_0
    //   681: ireturn
    //   682: astore_1
    //   683: aload 12
    //   685: astore 9
    //   687: aload 13
    //   689: astore 10
    //   691: aload_1
    //   692: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   695: aload 13
    //   697: ifnull +8 -> 705
    //   700: aload 13
    //   702: invokevirtual 171	java/io/OutputStream:close	()V
    //   705: aload 12
    //   707: ifnull -27 -> 680
    //   710: aload 12
    //   712: invokevirtual 149	com/amap/api/mapcore2d/ep:a	()Z
    //   715: ifne -35 -> 680
    //   718: aload 12
    //   720: invokevirtual 152	com/amap/api/mapcore2d/ep:close	()V
    //   723: goto -43 -> 680
    //   726: astore_1
    //   727: aload_1
    //   728: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   731: goto -51 -> 680
    //   734: astore_1
    //   735: aload 10
    //   737: ifnull +8 -> 745
    //   740: aload 10
    //   742: invokevirtual 171	java/io/OutputStream:close	()V
    //   745: aload 9
    //   747: ifnull +16 -> 763
    //   750: aload 9
    //   752: invokevirtual 149	com/amap/api/mapcore2d/ep:a	()Z
    //   755: ifne +8 -> 763
    //   758: aload 9
    //   760: invokevirtual 152	com/amap/api/mapcore2d/ep:close	()V
    //   763: aload_1
    //   764: athrow
    //   765: astore_2
    //   766: aload_2
    //   767: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   770: goto -25 -> 745
    //   773: astore_2
    //   774: aload_2
    //   775: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   778: goto -15 -> 763
    //   781: astore_1
    //   782: aload_1
    //   783: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   786: goto -124 -> 662
    //   789: astore_1
    //   790: goto -63 -> 727
    //   793: astore_1
    //   794: aload_1
    //   795: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   798: goto -93 -> 705
    //   801: astore_1
    //   802: aload_1
    //   803: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   806: goto -533 -> 273
    //   809: astore_2
    //   810: aload_2
    //   811: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   814: goto -412 -> 402
    //   817: astore_2
    //   818: aload_2
    //   819: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   822: goto -216 -> 606
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	825	0	this	en
    //   0	825	1	paramContext	Context
    //   0	825	2	paramString1	String
    //   0	825	3	paramString2	String
    //   0	825	4	paramString3	String
    //   0	825	5	paramdp	dp
    //   248	387	6	bool1	boolean
    //   388	227	7	bool2	boolean
    //   385	248	8	bool3	boolean
    //   36	723	9	localObject1	Object
    //   40	701	10	localObject2	Object
    //   20	656	11	localObject3	Object
    //   28	691	12	localObject4	Object
    //   32	669	13	localObject5	Object
    //   24	634	14	localObject6	Object
    //   1	506	15	localObject7	Object
    //   7	493	16	localObject8	Object
    //   4	510	17	localObject9	Object
    //   16	273	18	localObject10	Object
    //   10	287	19	localObject11	Object
    //   13	292	20	localObject12	Object
    //   49	132	21	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   421	425	427	java/lang/Throwable
    //   625	629	631	java/lang/Throwable
    //   42	51	639	java/io/IOException
    //   75	88	639	java/io/IOException
    //   112	121	639	java/io/IOException
    //   145	152	639	java/io/IOException
    //   176	189	639	java/io/IOException
    //   213	220	639	java/io/IOException
    //   244	250	639	java/io/IOException
    //   312	322	639	java/io/IOException
    //   343	353	639	java/io/IOException
    //   374	380	639	java/io/IOException
    //   460	468	639	java/io/IOException
    //   489	496	639	java/io/IOException
    //   517	524	639	java/io/IOException
    //   542	547	639	java/io/IOException
    //   565	570	639	java/io/IOException
    //   588	592	639	java/io/IOException
    //   42	51	682	java/lang/Throwable
    //   75	88	682	java/lang/Throwable
    //   112	121	682	java/lang/Throwable
    //   145	152	682	java/lang/Throwable
    //   176	189	682	java/lang/Throwable
    //   213	220	682	java/lang/Throwable
    //   244	250	682	java/lang/Throwable
    //   312	322	682	java/lang/Throwable
    //   343	353	682	java/lang/Throwable
    //   374	380	682	java/lang/Throwable
    //   460	468	682	java/lang/Throwable
    //   489	496	682	java/lang/Throwable
    //   517	524	682	java/lang/Throwable
    //   542	547	682	java/lang/Throwable
    //   565	570	682	java/lang/Throwable
    //   588	592	682	java/lang/Throwable
    //   718	723	726	java/lang/Throwable
    //   42	51	734	finally
    //   75	88	734	finally
    //   112	121	734	finally
    //   145	152	734	finally
    //   176	189	734	finally
    //   213	220	734	finally
    //   244	250	734	finally
    //   312	322	734	finally
    //   343	353	734	finally
    //   374	380	734	finally
    //   460	468	734	finally
    //   489	496	734	finally
    //   517	524	734	finally
    //   542	547	734	finally
    //   565	570	734	finally
    //   588	592	734	finally
    //   648	652	734	finally
    //   691	695	734	finally
    //   740	745	765	java/lang/Throwable
    //   758	763	773	java/lang/Throwable
    //   657	662	781	java/lang/Throwable
    //   675	680	789	java/lang/Throwable
    //   700	705	793	java/lang/Throwable
    //   265	273	801	java/lang/Throwable
    //   394	402	809	java/lang/Throwable
    //   602	606	817	java/lang/Throwable
  }
  
  private String b(Throwable paramThrowable)
  {
    return paramThrowable.toString();
  }
  
  /* Error */
  private List<dh> b(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: invokestatic 181	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   5: astore 4
    //   7: aload 4
    //   9: monitorenter
    //   10: new 183	com/amap/api/mapcore2d/ds
    //   13: dup
    //   14: aload_1
    //   15: invokespecial 186	com/amap/api/mapcore2d/ds:<init>	(Landroid/content/Context;)V
    //   18: invokevirtual 189	com/amap/api/mapcore2d/ds:a	()Ljava/util/List;
    //   21: astore_1
    //   22: aload 4
    //   24: monitorexit
    //   25: aload_1
    //   26: areturn
    //   27: astore_3
    //   28: aload_2
    //   29: astore_1
    //   30: aload_1
    //   31: astore_2
    //   32: aload 4
    //   34: monitorexit
    //   35: aload_3
    //   36: athrow
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 46	java/lang/Throwable:printStackTrace	()V
    //   42: aload_1
    //   43: areturn
    //   44: astore_2
    //   45: aconst_null
    //   46: astore_1
    //   47: goto -9 -> 38
    //   50: astore_3
    //   51: goto -21 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	this	en
    //   0	54	1	paramContext	Context
    //   1	31	2	localContext	Context
    //   37	2	2	localThrowable1	Throwable
    //   44	1	2	localThrowable2	Throwable
    //   27	9	3	localObject1	Object
    //   50	1	3	localObject2	Object
    //   5	28	4	localLooper	Looper
    // Exception table:
    //   from	to	target	type
    //   10	22	27	finally
    //   32	35	27	finally
    //   35	37	37	java/lang/Throwable
    //   2	10	44	java/lang/Throwable
    //   22	25	50	finally
  }
  
  private String c()
  {
    return eo.a(new Date().getTime());
  }
  
  private String c(Context paramContext)
  {
    return da.e(paramContext);
  }
  
  protected abstract int a();
  
  protected abstract es a(dp paramdp);
  
  protected abstract String a(String paramString);
  
  protected String a(Throwable paramThrowable)
  {
    try
    {
      paramThrowable = eo.a(paramThrowable);
      return paramThrowable;
    }
    catch (Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
    return null;
  }
  
  protected abstract String a(List<dh> paramList);
  
  void a(Context paramContext)
  {
    Object localObject1 = b(paramContext);
    if ((localObject1 == null) || (((List)localObject1).size() == 0)) {}
    int i;
    do
    {
      do
      {
        return;
        localObject1 = a((List)localObject1);
      } while ((localObject1 == null) || ("".equals(localObject1)));
      ??? = c();
      str1 = a(paramContext, a);
      str2 = c(paramContext);
      i = a();
      ??? = a(str2, str1, (String)???, i, "ANR", (String)localObject1);
    } while ((??? == null) || ("".equals(???)));
    localObject1 = a((String)localObject1);
    String str1 = a(paramContext, (String)???);
    String str2 = b();
    synchronized (Looper.getMainLooper())
    {
      dp localdp = new dp(paramContext);
      boolean bool = a(paramContext, (String)localObject1, str2, str1, localdp);
      a(localdp, a.a(), (String)localObject1, i, bool);
      return;
    }
  }
  
  void a(Context paramContext, Throwable paramThrowable, String paramString1, String paramString2)
  {
    Object localObject1 = b(paramContext);
    label22:
    String str1;
    if ((localObject1 == null) || (((List)localObject1).size() == 0))
    {
      return;
    }
    else
    {
      do
      {
        str1 = a(paramThrowable);
      } while ((str1 == null) || ("".equals(str1)));
      localObject1 = ((List)localObject1).iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        break label22;
      }
      dh localdh = (dh)((Iterator)localObject1).next();
      if (!a(localdh.f(), str1)) {
        break;
      }
      a(localdh);
      ??? = c();
      String str3 = a(paramContext, localdh);
      String str4 = c(paramContext);
      Object localObject3 = b(paramThrowable);
      if ((localObject3 == null) || ("".equals(localObject3))) {
        break label22;
      }
      int i = a();
      StringBuilder localStringBuilder = new StringBuilder();
      if (paramString1 != null) {
        localStringBuilder.append("class:").append(paramString1);
      }
      if (paramString2 != null) {
        localStringBuilder.append(" method:").append(paramString2).append("$").append("<br/>");
      }
      localStringBuilder.append(str1);
      String str2 = a(str1);
      ??? = a(str4, str3, (String)???, i, (String)localObject3, localStringBuilder.toString());
      if ((??? == null) || ("".equals(???))) {
        break label22;
      }
      str3 = a(paramContext, (String)???);
      str4 = b();
      synchronized (Looper.getMainLooper())
      {
        localObject3 = new dp(paramContext);
        boolean bool = a(paramContext, str2, str4, str3, (dp)localObject3);
        a((dp)localObject3, localdh.a(), str2, i, bool);
      }
    }
  }
  
  protected void a(dh paramdh)
  {
    a = paramdh;
  }
  
  protected boolean a(String[] paramArrayOfString, String paramString)
  {
    if ((paramArrayOfString == null) || (paramString == null)) {}
    for (;;)
    {
      return false;
      try
      {
        int j = paramArrayOfString.length;
        int i = 0;
        while (i < j)
        {
          int k = paramString.indexOf(paramArrayOfString[i]);
          if (k != -1) {
            return true;
          }
          i += 1;
        }
        return false;
      }
      catch (Throwable paramArrayOfString)
      {
        paramArrayOfString.printStackTrace();
      }
    }
  }
  
  protected abstract String b();
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.en
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */