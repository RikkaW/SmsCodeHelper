package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Build.VERSION;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.HttpHost;

public class dg
{
  private static String a()
  {
    try
    {
      String str1 = android.net.Proxy.getDefaultHost();
      String str2 = str1;
      if (str1 == null) {
        str2 = "null";
      }
      return str2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        ed.a(localThrowable, "ProxyUtil", "getDefHost");
        Object localObject = null;
      }
    }
  }
  
  public static HttpHost a(Context paramContext)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 11) {
        return a(paramContext, new URI("http://restapi.amap.com"));
      }
      paramContext = b(paramContext);
      return paramContext;
    }
    catch (URISyntaxException paramContext)
    {
      ed.a(paramContext, "ProxyUtil", "getProxy");
      paramContext.printStackTrace();
      return null;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "ProxyUtil", "getProxy");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static HttpHost a(Context paramContext, URI paramURI)
  {
    if (dd.g(paramContext) == 0) {}
    for (;;)
    {
      try
      {
        paramContext = ProxySelector.getDefault();
        int i = -1;
        paramContext = paramContext.select(paramURI);
        if ((paramContext == null) || (paramContext.isEmpty())) {
          break label130;
        }
        paramURI = (java.net.Proxy)paramContext.get(0);
        if (paramURI == null) {
          break label135;
        }
        paramContext = paramURI;
        if (paramURI.type() == Proxy.Type.DIRECT) {
          break label135;
        }
        if (paramContext == null) {
          break label125;
        }
        paramURI = (InetSocketAddress)paramContext.address();
        if (paramURI == null) {
          break label125;
        }
        paramContext = paramURI.getHostName();
        i = paramURI.getPort();
        if (a(paramContext, i))
        {
          paramContext = new HttpHost(paramContext, i, "http");
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        ed.a(paramContext, "ProxyUtil", "getProxySelectorCfg");
        paramContext.printStackTrace();
      }
      return null;
      label125:
      paramContext = null;
      continue;
      label130:
      paramContext = null;
      continue;
      label135:
      paramContext = null;
    }
  }
  
  private static boolean a(String paramString, int paramInt)
  {
    return (paramString != null) && (paramString.length() > 0) && (paramInt != -1);
  }
  
  private static int b()
  {
    try
    {
      int i = android.net.Proxy.getDefaultPort();
      return i;
    }
    catch (Throwable localThrowable)
    {
      ed.a(localThrowable, "ProxyUtil", "getDefPort");
      localThrowable.printStackTrace();
    }
    return -1;
  }
  
  /* Error */
  private static HttpHost b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: invokestatic 65	com/amap/api/mapcore2d/dd:g	(Landroid/content/Context;)I
    //   6: ifne +752 -> 758
    //   9: ldc -119
    //   11: invokestatic 143	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   14: astore 5
    //   16: aload_0
    //   17: invokevirtual 149	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 6
    //   22: aload 6
    //   24: aload 5
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: aconst_null
    //   30: invokevirtual 155	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore 5
    //   35: aload 5
    //   37: ifnull +830 -> 867
    //   40: aload 5
    //   42: astore 7
    //   44: aload 5
    //   46: invokeinterface 160 1 0
    //   51: ifeq +816 -> 867
    //   54: aload 5
    //   56: astore 7
    //   58: aload 5
    //   60: aload 5
    //   62: ldc -94
    //   64: invokeinterface 166 2 0
    //   69: invokeinterface 170 2 0
    //   74: astore 8
    //   76: aload 8
    //   78: astore 6
    //   80: aload 8
    //   82: ifnull +17 -> 99
    //   85: aload 5
    //   87: astore 7
    //   89: aload 8
    //   91: getstatic 176	java/util/Locale:US	Ljava/util/Locale;
    //   94: invokevirtual 180	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   97: astore 6
    //   99: aload 6
    //   101: ifnull +137 -> 238
    //   104: aload 5
    //   106: astore 7
    //   108: aload 6
    //   110: ldc -74
    //   112: invokevirtual 186	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   115: ifeq +123 -> 238
    //   118: aload 5
    //   120: astore 7
    //   122: invokestatic 188	com/amap/api/mapcore2d/dg:a	()Ljava/lang/String;
    //   125: astore 6
    //   127: aload 5
    //   129: astore 7
    //   131: invokestatic 190	com/amap/api/mapcore2d/dg:b	()I
    //   134: istore_2
    //   135: aload 5
    //   137: astore 7
    //   139: aload 6
    //   141: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   144: ifne +736 -> 880
    //   147: aload 5
    //   149: astore 7
    //   151: aload 6
    //   153: ldc 20
    //   155: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   158: istore 4
    //   160: iload 4
    //   162: ifne +718 -> 880
    //   165: iconst_1
    //   166: istore_1
    //   167: aload 6
    //   169: astore_0
    //   170: iload_1
    //   171: ifne +703 -> 874
    //   174: ldc -56
    //   176: astore 6
    //   178: iload_2
    //   179: istore_1
    //   180: aload 6
    //   182: astore_0
    //   183: iload_2
    //   184: iconst_m1
    //   185: if_icmpne +9 -> 194
    //   188: bipush 80
    //   190: istore_1
    //   191: aload 6
    //   193: astore_0
    //   194: iload_1
    //   195: istore_2
    //   196: aload_0
    //   197: astore 6
    //   199: aload 5
    //   201: ifnull +15 -> 216
    //   204: aload 5
    //   206: invokeinterface 203 1 0
    //   211: aload_0
    //   212: astore 6
    //   214: iload_1
    //   215: istore_2
    //   216: aload 6
    //   218: iload_2
    //   219: invokestatic 113	com/amap/api/mapcore2d/dg:a	(Ljava/lang/String;I)Z
    //   222: ifeq +536 -> 758
    //   225: new 115	org/apache/http/HttpHost
    //   228: dup
    //   229: aload 6
    //   231: iload_2
    //   232: ldc 117
    //   234: invokespecial 120	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   237: areturn
    //   238: aload 6
    //   240: ifnull +627 -> 867
    //   243: aload 5
    //   245: astore 7
    //   247: aload 6
    //   249: ldc -51
    //   251: invokevirtual 186	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   254: ifeq +613 -> 867
    //   257: aload 5
    //   259: astore 7
    //   261: invokestatic 188	com/amap/api/mapcore2d/dg:a	()Ljava/lang/String;
    //   264: astore 6
    //   266: aload 5
    //   268: astore 7
    //   270: invokestatic 190	com/amap/api/mapcore2d/dg:b	()I
    //   273: istore_1
    //   274: aload 5
    //   276: astore 7
    //   278: aload 6
    //   280: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   283: ifne +577 -> 860
    //   286: aload 5
    //   288: astore 7
    //   290: aload 6
    //   292: ldc 20
    //   294: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   297: istore 4
    //   299: iload 4
    //   301: ifne +559 -> 860
    //   304: iconst_1
    //   305: istore_2
    //   306: aload 6
    //   308: astore_0
    //   309: iload_2
    //   310: ifne +547 -> 857
    //   313: ldc -49
    //   315: astore_0
    //   316: iload_1
    //   317: iconst_m1
    //   318: if_icmpne +536 -> 854
    //   321: bipush 80
    //   323: istore_1
    //   324: goto -130 -> 194
    //   327: astore 5
    //   329: invokestatic 210	com/amap/api/mapcore2d/ed:b	()Lcom/amap/api/mapcore2d/ed;
    //   332: astore 6
    //   334: aload 6
    //   336: ifnull +14 -> 350
    //   339: aload 6
    //   341: aload 5
    //   343: ldc 25
    //   345: ldc -44
    //   347: invokevirtual 214	com/amap/api/mapcore2d/ed:b	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   350: aload 5
    //   352: invokevirtual 23	java/lang/Throwable:printStackTrace	()V
    //   355: iload_1
    //   356: istore_2
    //   357: aload_0
    //   358: astore 6
    //   360: goto -144 -> 216
    //   363: astore 6
    //   365: aconst_null
    //   366: astore 5
    //   368: iconst_m1
    //   369: istore_1
    //   370: aconst_null
    //   371: astore 10
    //   373: aconst_null
    //   374: astore 9
    //   376: aload 5
    //   378: astore 7
    //   380: aload 6
    //   382: ldc 25
    //   384: ldc -40
    //   386: invokestatic 32	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   389: aload 5
    //   391: astore 7
    //   393: aload_0
    //   394: invokestatic 220	com/amap/api/mapcore2d/dd:i	(Landroid/content/Context;)Ljava/lang/String;
    //   397: astore_0
    //   398: aload_0
    //   399: ifnull +450 -> 849
    //   402: aload 5
    //   404: astore 7
    //   406: aload_0
    //   407: getstatic 176	java/util/Locale:US	Ljava/util/Locale;
    //   410: invokevirtual 180	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   413: astore_0
    //   414: aload 5
    //   416: astore 7
    //   418: invokestatic 188	com/amap/api/mapcore2d/dg:a	()Ljava/lang/String;
    //   421: astore 8
    //   423: aload 5
    //   425: astore 7
    //   427: invokestatic 190	com/amap/api/mapcore2d/dg:b	()I
    //   430: istore_2
    //   431: aload 5
    //   433: astore 7
    //   435: aload_0
    //   436: ldc -74
    //   438: invokevirtual 223	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   441: iconst_m1
    //   442: if_icmpeq +123 -> 565
    //   445: aload 9
    //   447: astore_0
    //   448: iload_3
    //   449: istore_1
    //   450: aload 5
    //   452: astore 7
    //   454: aload 8
    //   456: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   459: ifne +27 -> 486
    //   462: aload 9
    //   464: astore_0
    //   465: iload_3
    //   466: istore_1
    //   467: aload 5
    //   469: astore 7
    //   471: aload 8
    //   473: ldc 20
    //   475: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   478: ifne +8 -> 486
    //   481: iconst_1
    //   482: istore_1
    //   483: aload 8
    //   485: astore_0
    //   486: iload_1
    //   487: ifne +6 -> 493
    //   490: ldc -56
    //   492: astore_0
    //   493: aload_0
    //   494: astore 6
    //   496: iload_2
    //   497: iconst_m1
    //   498: if_icmpne +343 -> 841
    //   501: bipush 80
    //   503: istore_1
    //   504: iload_1
    //   505: istore_2
    //   506: aload_0
    //   507: astore 6
    //   509: aload 5
    //   511: ifnull -295 -> 216
    //   514: aload 5
    //   516: invokeinterface 203 1 0
    //   521: iload_1
    //   522: istore_2
    //   523: aload_0
    //   524: astore 6
    //   526: goto -310 -> 216
    //   529: astore 5
    //   531: invokestatic 210	com/amap/api/mapcore2d/ed:b	()Lcom/amap/api/mapcore2d/ed;
    //   534: astore 6
    //   536: aload 6
    //   538: ifnull +14 -> 552
    //   541: aload 6
    //   543: aload 5
    //   545: ldc 25
    //   547: ldc -44
    //   549: invokevirtual 214	com/amap/api/mapcore2d/ed:b	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   552: aload 5
    //   554: invokevirtual 23	java/lang/Throwable:printStackTrace	()V
    //   557: iload_1
    //   558: istore_2
    //   559: aload_0
    //   560: astore 6
    //   562: goto -346 -> 216
    //   565: aload 5
    //   567: astore 7
    //   569: aload 10
    //   571: astore 6
    //   573: aload_0
    //   574: ldc -51
    //   576: invokevirtual 223	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   579: iconst_m1
    //   580: if_icmpeq +261 -> 841
    //   583: aload 5
    //   585: astore 7
    //   587: aload 8
    //   589: invokestatic 194	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   592: ifne +242 -> 834
    //   595: aload 5
    //   597: astore 7
    //   599: aload 8
    //   601: ldc 20
    //   603: invokevirtual 198	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   606: ifne +228 -> 834
    //   609: aload 8
    //   611: astore_0
    //   612: iconst_1
    //   613: istore_1
    //   614: iload_1
    //   615: ifne +6 -> 621
    //   618: ldc -56
    //   620: astore_0
    //   621: bipush 80
    //   623: istore_1
    //   624: goto -120 -> 504
    //   627: astore 5
    //   629: aconst_null
    //   630: astore_0
    //   631: iconst_m1
    //   632: istore_1
    //   633: aconst_null
    //   634: astore 7
    //   636: aload 5
    //   638: ldc 25
    //   640: ldc -31
    //   642: invokestatic 32	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   645: aload 5
    //   647: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   650: iload_1
    //   651: istore_2
    //   652: aload 7
    //   654: astore 6
    //   656: aload_0
    //   657: ifnull -441 -> 216
    //   660: aload_0
    //   661: invokeinterface 203 1 0
    //   666: iload_1
    //   667: istore_2
    //   668: aload 7
    //   670: astore 6
    //   672: goto -456 -> 216
    //   675: astore_0
    //   676: invokestatic 210	com/amap/api/mapcore2d/ed:b	()Lcom/amap/api/mapcore2d/ed;
    //   679: astore 5
    //   681: aload 5
    //   683: ifnull +13 -> 696
    //   686: aload 5
    //   688: aload_0
    //   689: ldc 25
    //   691: ldc -44
    //   693: invokevirtual 214	com/amap/api/mapcore2d/ed:b	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   696: aload_0
    //   697: invokevirtual 23	java/lang/Throwable:printStackTrace	()V
    //   700: iload_1
    //   701: istore_2
    //   702: aload 7
    //   704: astore 6
    //   706: goto -490 -> 216
    //   709: astore_0
    //   710: aconst_null
    //   711: astore 7
    //   713: aload 7
    //   715: ifnull +10 -> 725
    //   718: aload 7
    //   720: invokeinterface 203 1 0
    //   725: aload_0
    //   726: athrow
    //   727: astore 5
    //   729: invokestatic 210	com/amap/api/mapcore2d/ed:b	()Lcom/amap/api/mapcore2d/ed;
    //   732: astore 6
    //   734: aload 6
    //   736: ifnull +14 -> 750
    //   739: aload 6
    //   741: aload 5
    //   743: ldc 25
    //   745: ldc -44
    //   747: invokevirtual 214	com/amap/api/mapcore2d/ed:b	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   750: aload 5
    //   752: invokevirtual 23	java/lang/Throwable:printStackTrace	()V
    //   755: goto -30 -> 725
    //   758: aconst_null
    //   759: areturn
    //   760: astore_0
    //   761: goto -48 -> 713
    //   764: astore 5
    //   766: aload_0
    //   767: astore 7
    //   769: aload 5
    //   771: astore_0
    //   772: goto -59 -> 713
    //   775: astore 6
    //   777: aload 5
    //   779: astore_0
    //   780: aload 6
    //   782: astore 5
    //   784: iconst_m1
    //   785: istore_1
    //   786: goto -153 -> 633
    //   789: astore 6
    //   791: aload 5
    //   793: astore_0
    //   794: aload 6
    //   796: astore 5
    //   798: iload_2
    //   799: istore_1
    //   800: goto -167 -> 633
    //   803: astore 6
    //   805: aload 5
    //   807: astore_0
    //   808: aload 6
    //   810: astore 5
    //   812: goto -179 -> 633
    //   815: astore 6
    //   817: iconst_m1
    //   818: istore_1
    //   819: goto -449 -> 370
    //   822: astore 6
    //   824: iload_2
    //   825: istore_1
    //   826: goto -456 -> 370
    //   829: astore 6
    //   831: goto -461 -> 370
    //   834: iconst_0
    //   835: istore_1
    //   836: aconst_null
    //   837: astore_0
    //   838: goto -224 -> 614
    //   841: aload 6
    //   843: astore_0
    //   844: iload_2
    //   845: istore_1
    //   846: goto -342 -> 504
    //   849: aconst_null
    //   850: astore_0
    //   851: goto -347 -> 504
    //   854: goto -660 -> 194
    //   857: goto -541 -> 316
    //   860: iconst_0
    //   861: istore_2
    //   862: aconst_null
    //   863: astore_0
    //   864: goto -555 -> 309
    //   867: iconst_m1
    //   868: istore_1
    //   869: aconst_null
    //   870: astore_0
    //   871: goto -677 -> 194
    //   874: aload_0
    //   875: astore 6
    //   877: goto -699 -> 178
    //   880: iconst_0
    //   881: istore_1
    //   882: aconst_null
    //   883: astore_0
    //   884: goto -714 -> 170
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	887	0	paramContext	Context
    //   166	716	1	i	int
    //   134	728	2	j	int
    //   1	465	3	k	int
    //   158	142	4	bool	boolean
    //   14	273	5	localObject1	Object
    //   327	24	5	localThrowable1	Throwable
    //   366	149	5	localObject2	Object
    //   529	67	5	localThrowable2	Throwable
    //   627	19	5	localException1	Exception
    //   679	8	5	localed	ed
    //   727	24	5	localThrowable3	Throwable
    //   764	14	5	localObject3	Object
    //   782	29	5	localObject4	Object
    //   20	339	6	localObject5	Object
    //   363	18	6	localSecurityException1	SecurityException
    //   494	246	6	localObject6	Object
    //   775	6	6	localException2	Exception
    //   789	6	6	localException3	Exception
    //   803	6	6	localException4	Exception
    //   815	1	6	localSecurityException2	SecurityException
    //   822	1	6	localSecurityException3	SecurityException
    //   829	13	6	localSecurityException4	SecurityException
    //   875	1	6	localContext	Context
    //   42	726	7	localObject7	Object
    //   74	536	8	str	String
    //   374	89	9	localObject8	Object
    //   371	199	10	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   204	211	327	java/lang/Throwable
    //   22	35	363	java/lang/SecurityException
    //   514	521	529	java/lang/Throwable
    //   22	35	627	java/lang/Exception
    //   660	666	675	java/lang/Throwable
    //   22	35	709	finally
    //   718	725	727	java/lang/Throwable
    //   44	54	760	finally
    //   58	76	760	finally
    //   89	99	760	finally
    //   108	118	760	finally
    //   122	127	760	finally
    //   131	135	760	finally
    //   139	147	760	finally
    //   151	160	760	finally
    //   247	257	760	finally
    //   261	266	760	finally
    //   270	274	760	finally
    //   278	286	760	finally
    //   290	299	760	finally
    //   380	389	760	finally
    //   393	398	760	finally
    //   406	414	760	finally
    //   418	423	760	finally
    //   427	431	760	finally
    //   435	445	760	finally
    //   454	462	760	finally
    //   471	481	760	finally
    //   573	583	760	finally
    //   587	595	760	finally
    //   599	609	760	finally
    //   636	650	764	finally
    //   44	54	775	java/lang/Exception
    //   58	76	775	java/lang/Exception
    //   89	99	775	java/lang/Exception
    //   108	118	775	java/lang/Exception
    //   122	127	775	java/lang/Exception
    //   131	135	775	java/lang/Exception
    //   247	257	775	java/lang/Exception
    //   261	266	775	java/lang/Exception
    //   270	274	775	java/lang/Exception
    //   139	147	789	java/lang/Exception
    //   151	160	789	java/lang/Exception
    //   278	286	803	java/lang/Exception
    //   290	299	803	java/lang/Exception
    //   44	54	815	java/lang/SecurityException
    //   58	76	815	java/lang/SecurityException
    //   89	99	815	java/lang/SecurityException
    //   108	118	815	java/lang/SecurityException
    //   122	127	815	java/lang/SecurityException
    //   131	135	815	java/lang/SecurityException
    //   247	257	815	java/lang/SecurityException
    //   261	266	815	java/lang/SecurityException
    //   270	274	815	java/lang/SecurityException
    //   139	147	822	java/lang/SecurityException
    //   151	160	822	java/lang/SecurityException
    //   278	286	829	java/lang/SecurityException
    //   290	299	829	java/lang/SecurityException
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */