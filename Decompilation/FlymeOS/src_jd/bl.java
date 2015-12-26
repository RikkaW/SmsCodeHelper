import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class bl
{
  private static final String a = bl.class.getSimpleName();
  
  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) && (aux.a))
    {
      Log.w(a, "download context is null, download failed!");
      return false;
    }
    if ((TextUtils.isEmpty(paramString1)) && (aux.a))
    {
      Log.w(a, "download url is null, download failed!");
      return false;
    }
    if ((TextUtils.isEmpty(paramString2)) && (aux.a))
    {
      Log.w(a, "download path is null, download failed!");
      return false;
    }
    return b(paramContext, paramString1, paramString2);
  }
  
  /* Error */
  private static boolean b(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 12
    //   6: aload_0
    //   7: ifnonnull +39 -> 46
    //   10: getstatic 26	aux:a	Z
    //   13: istore 7
    //   15: iload 7
    //   17: ifeq +29 -> 46
    //   20: iconst_0
    //   21: ifeq +11 -> 32
    //   24: new 51	java/lang/NullPointerException
    //   27: dup
    //   28: invokespecial 52	java/lang/NullPointerException:<init>	()V
    //   31: athrow
    //   32: iconst_0
    //   33: ifeq +11 -> 44
    //   36: new 51	java/lang/NullPointerException
    //   39: dup
    //   40: invokespecial 52	java/lang/NullPointerException:<init>	()V
    //   43: athrow
    //   44: iconst_0
    //   45: ireturn
    //   46: getstatic 26	aux:a	Z
    //   49: ifeq +49 -> 98
    //   52: getstatic 16	bl:a	Ljava/lang/String;
    //   55: new 54	java/lang/StringBuilder
    //   58: dup
    //   59: ldc 56
    //   61: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   64: aload_1
    //   65: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   74: pop
    //   75: getstatic 16	bl:a	Ljava/lang/String;
    //   78: new 54	java/lang/StringBuilder
    //   81: dup
    //   82: ldc 71
    //   84: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   87: aload_2
    //   88: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   97: pop
    //   98: new 73	org/apache/http/params/BasicHttpParams
    //   101: dup
    //   102: invokespecial 74	org/apache/http/params/BasicHttpParams:<init>	()V
    //   105: astore 13
    //   107: aload 13
    //   109: getstatic 80	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
    //   112: invokestatic 86	org/apache/http/params/HttpProtocolParams:setVersion	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/ProtocolVersion;)V
    //   115: aload 13
    //   117: ldc 88
    //   119: invokestatic 92	org/apache/http/params/HttpProtocolParams:setUserAgent	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
    //   122: aload 13
    //   124: ldc 93
    //   126: invokestatic 99	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   129: aload 13
    //   131: ldc 93
    //   133: invokestatic 102	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   136: new 104	org/apache/http/impl/client/DefaultHttpClient
    //   139: dup
    //   140: aload 13
    //   142: invokespecial 107	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   145: astore 10
    //   147: new 109	org/apache/http/client/methods/HttpGet
    //   150: dup
    //   151: aload_1
    //   152: invokespecial 110	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   155: astore_1
    //   156: aload_1
    //   157: aload 13
    //   159: invokevirtual 113	org/apache/http/client/methods/HttpGet:setParams	(Lorg/apache/http/params/HttpParams;)V
    //   162: aload_1
    //   163: ldc 115
    //   165: ldc 117
    //   167: invokevirtual 121	org/apache/http/client/methods/HttpGet:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   170: aload 10
    //   172: aload_1
    //   173: invokeinterface 127 2 0
    //   178: astore 13
    //   180: aload 13
    //   182: invokeinterface 133 1 0
    //   187: astore_1
    //   188: aload_1
    //   189: invokeinterface 139 1 0
    //   194: lstore 8
    //   196: aload_1
    //   197: invokeinterface 143 1 0
    //   202: astore_1
    //   203: aload 13
    //   205: ldc -111
    //   207: invokeinterface 149 2 0
    //   212: astore 10
    //   214: aload 10
    //   216: ifnull +575 -> 791
    //   219: aload 10
    //   221: invokeinterface 154 1 0
    //   226: ldc 117
    //   228: invokevirtual 160	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   231: ifeq +560 -> 791
    //   234: new 162	java/util/zip/GZIPInputStream
    //   237: dup
    //   238: aload_1
    //   239: invokespecial 165	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   242: astore 10
    //   244: iconst_1
    //   245: istore_3
    //   246: aload 10
    //   248: astore_1
    //   249: aload 13
    //   251: ldc -89
    //   253: invokeinterface 149 2 0
    //   258: astore 10
    //   260: aload 10
    //   262: ifnull +523 -> 785
    //   265: aload 10
    //   267: invokeinterface 154 1 0
    //   272: ldc -87
    //   274: invokevirtual 160	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   277: ifeq +508 -> 785
    //   280: getstatic 16	bl:a	Ljava/lang/String;
    //   283: new 54	java/lang/StringBuilder
    //   286: dup
    //   287: ldc -85
    //   289: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   292: iconst_1
    //   293: invokevirtual 174	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   296: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   299: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   302: pop
    //   303: iconst_1
    //   304: istore 4
    //   306: aload_2
    //   307: astore 10
    //   309: aload_2
    //   310: ldc -80
    //   312: invokevirtual 179	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   315: ifne +31 -> 346
    //   318: aload_0
    //   319: invokevirtual 185	android/content/Context:getFilesDir	()Ljava/io/File;
    //   322: invokevirtual 190	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   325: new 54	java/lang/StringBuilder
    //   328: dup
    //   329: ldc -80
    //   331: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   334: aload_2
    //   335: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: invokevirtual 194	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   344: astore 10
    //   346: new 187	java/io/File
    //   349: dup
    //   350: aload 10
    //   352: invokespecial 195	java/io/File:<init>	(Ljava/lang/String;)V
    //   355: astore_0
    //   356: aload_0
    //   357: invokestatic 201	com/ted/android/contacts/common/util/FileUtil:deleteFile	(Ljava/io/File;)V
    //   360: aload_0
    //   361: invokevirtual 204	java/io/File:getParentFile	()Ljava/io/File;
    //   364: invokestatic 208	com/ted/android/contacts/common/util/FileUtil:ensurePathExists	(Ljava/io/File;)Z
    //   367: pop
    //   368: new 210	java/io/FileOutputStream
    //   371: dup
    //   372: aload 10
    //   374: iconst_0
    //   375: invokespecial 213	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   378: astore_0
    //   379: aload_1
    //   380: ifnull +54 -> 434
    //   383: sipush 1024
    //   386: newarray <illegal type>
    //   388: astore_2
    //   389: iconst_0
    //   390: istore 5
    //   392: aload_1
    //   393: aload_2
    //   394: invokevirtual 219	java/io/InputStream:read	([B)I
    //   397: istore 6
    //   399: iload 6
    //   401: ifgt +157 -> 558
    //   404: getstatic 26	aux:a	Z
    //   407: ifeq +27 -> 434
    //   410: getstatic 16	bl:a	Ljava/lang/String;
    //   413: new 54	java/lang/StringBuilder
    //   416: dup
    //   417: ldc -35
    //   419: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   422: iload 5
    //   424: invokevirtual 224	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   427: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   433: pop
    //   434: aload_0
    //   435: invokevirtual 227	java/io/FileOutputStream:flush	()V
    //   438: aload 13
    //   440: invokeinterface 231 1 0
    //   445: astore_2
    //   446: aload_2
    //   447: invokeinterface 237 1 0
    //   452: istore 5
    //   454: getstatic 26	aux:a	Z
    //   457: ifeq +339 -> 796
    //   460: getstatic 16	bl:a	Ljava/lang/String;
    //   463: new 54	java/lang/StringBuilder
    //   466: dup
    //   467: ldc -17
    //   469: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   472: iload 5
    //   474: invokevirtual 224	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   477: ldc -15
    //   479: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: aload_2
    //   483: invokeinterface 244 1 0
    //   488: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   494: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   497: pop
    //   498: getstatic 16	bl:a	Ljava/lang/String;
    //   501: new 54	java/lang/StringBuilder
    //   504: dup
    //   505: ldc -10
    //   507: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   510: lload 8
    //   512: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   515: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   521: pop
    //   522: goto +274 -> 796
    //   525: getstatic 26	aux:a	Z
    //   528: ifeq +12 -> 540
    //   531: getstatic 16	bl:a	Ljava/lang/String;
    //   534: ldc -5
    //   536: invokestatic 254	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   539: pop
    //   540: aload_0
    //   541: ifnull +7 -> 548
    //   544: aload_0
    //   545: invokevirtual 257	java/io/FileOutputStream:close	()V
    //   548: aload_1
    //   549: ifnull +7 -> 556
    //   552: aload_1
    //   553: invokevirtual 258	java/io/InputStream:close	()V
    //   556: iconst_1
    //   557: ireturn
    //   558: aload_0
    //   559: aload_2
    //   560: iconst_0
    //   561: iload 6
    //   563: invokevirtual 262	java/io/FileOutputStream:write	([BII)V
    //   566: iload 5
    //   568: iload 6
    //   570: iadd
    //   571: istore 5
    //   573: goto -181 -> 392
    //   576: getstatic 26	aux:a	Z
    //   579: ifeq +13 -> 592
    //   582: getstatic 16	bl:a	Ljava/lang/String;
    //   585: ldc_w 264
    //   588: invokestatic 34	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   591: pop
    //   592: aload 10
    //   594: invokestatic 266	com/ted/android/contacts/common/util/FileUtil:deleteFile	(Ljava/lang/String;)V
    //   597: aload_0
    //   598: ifnull +7 -> 605
    //   601: aload_0
    //   602: invokevirtual 257	java/io/FileOutputStream:close	()V
    //   605: aload_1
    //   606: ifnull -562 -> 44
    //   609: aload_1
    //   610: invokevirtual 258	java/io/InputStream:close	()V
    //   613: iconst_0
    //   614: ireturn
    //   615: astore_0
    //   616: iconst_0
    //   617: ireturn
    //   618: astore_2
    //   619: aconst_null
    //   620: astore_0
    //   621: aload 12
    //   623: astore 10
    //   625: getstatic 26	aux:a	Z
    //   628: ifeq +34 -> 662
    //   631: aload_2
    //   632: invokevirtual 269	java/lang/Exception:printStackTrace	()V
    //   635: getstatic 16	bl:a	Ljava/lang/String;
    //   638: new 54	java/lang/StringBuilder
    //   641: dup
    //   642: ldc_w 271
    //   645: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   648: aload_2
    //   649: invokevirtual 274	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   652: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   658: invokestatic 34	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   661: pop
    //   662: aload 10
    //   664: ifnull +8 -> 672
    //   667: aload 10
    //   669: invokevirtual 257	java/io/FileOutputStream:close	()V
    //   672: aload_0
    //   673: ifnull -629 -> 44
    //   676: aload_0
    //   677: invokevirtual 258	java/io/InputStream:close	()V
    //   680: iconst_0
    //   681: ireturn
    //   682: astore_0
    //   683: iconst_0
    //   684: ireturn
    //   685: astore_0
    //   686: aconst_null
    //   687: astore_1
    //   688: aload 11
    //   690: astore 10
    //   692: aload 10
    //   694: ifnull +8 -> 702
    //   697: aload 10
    //   699: invokevirtual 257	java/io/FileOutputStream:close	()V
    //   702: aload_1
    //   703: ifnull +7 -> 710
    //   706: aload_1
    //   707: invokevirtual 258	java/io/InputStream:close	()V
    //   710: aload_0
    //   711: athrow
    //   712: astore_1
    //   713: goto -3 -> 710
    //   716: astore_0
    //   717: aload 11
    //   719: astore 10
    //   721: goto -29 -> 692
    //   724: astore_0
    //   725: aload 11
    //   727: astore 10
    //   729: goto -37 -> 692
    //   732: astore_2
    //   733: aload_0
    //   734: astore 10
    //   736: aload_2
    //   737: astore_0
    //   738: goto -46 -> 692
    //   741: astore_2
    //   742: aload_0
    //   743: astore_1
    //   744: aload_2
    //   745: astore_0
    //   746: goto -54 -> 692
    //   749: astore_2
    //   750: aload 12
    //   752: astore 10
    //   754: aload_1
    //   755: astore_0
    //   756: goto -131 -> 625
    //   759: astore_2
    //   760: aload_1
    //   761: astore_0
    //   762: aload 12
    //   764: astore 10
    //   766: goto -141 -> 625
    //   769: astore_2
    //   770: aload_0
    //   771: astore 10
    //   773: aload_1
    //   774: astore_0
    //   775: goto -150 -> 625
    //   778: astore_0
    //   779: goto -223 -> 556
    //   782: astore_0
    //   783: iconst_0
    //   784: ireturn
    //   785: iconst_0
    //   786: istore 4
    //   788: goto -482 -> 306
    //   791: iconst_0
    //   792: istore_3
    //   793: goto -544 -> 249
    //   796: iload 5
    //   798: sipush 200
    //   801: if_icmpne -225 -> 576
    //   804: lload 8
    //   806: lconst_0
    //   807: lcmp
    //   808: ifgt -283 -> 525
    //   811: iload_3
    //   812: ifne -287 -> 525
    //   815: iload 4
    //   817: ifeq -241 -> 576
    //   820: goto -295 -> 525
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	823	0	paramContext	Context
    //   0	823	1	paramString1	String
    //   0	823	2	paramString2	String
    //   245	567	3	i	int
    //   304	512	4	j	int
    //   390	412	5	k	int
    //   397	174	6	m	int
    //   13	3	7	bool	boolean
    //   194	611	8	l	long
    //   145	627	10	localObject1	Object
    //   1	725	11	localObject2	Object
    //   4	759	12	localObject3	Object
    //   105	334	13	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   601	605	615	java/lang/Exception
    //   609	613	615	java/lang/Exception
    //   10	15	618	java/lang/Exception
    //   46	98	618	java/lang/Exception
    //   98	203	618	java/lang/Exception
    //   667	672	682	java/lang/Exception
    //   676	680	682	java/lang/Exception
    //   10	15	685	finally
    //   46	98	685	finally
    //   98	203	685	finally
    //   697	702	712	java/lang/Exception
    //   706	710	712	java/lang/Exception
    //   203	214	716	finally
    //   219	244	716	finally
    //   249	260	724	finally
    //   265	303	724	finally
    //   309	346	724	finally
    //   346	379	724	finally
    //   383	389	732	finally
    //   392	399	732	finally
    //   404	434	732	finally
    //   434	522	732	finally
    //   525	540	732	finally
    //   558	566	732	finally
    //   576	592	732	finally
    //   592	597	732	finally
    //   625	662	741	finally
    //   203	214	749	java/lang/Exception
    //   219	244	749	java/lang/Exception
    //   249	260	759	java/lang/Exception
    //   265	303	759	java/lang/Exception
    //   309	346	759	java/lang/Exception
    //   346	379	759	java/lang/Exception
    //   383	389	769	java/lang/Exception
    //   392	399	769	java/lang/Exception
    //   404	434	769	java/lang/Exception
    //   434	522	769	java/lang/Exception
    //   525	540	769	java/lang/Exception
    //   558	566	769	java/lang/Exception
    //   576	592	769	java/lang/Exception
    //   592	597	769	java/lang/Exception
    //   544	548	778	java/lang/Exception
    //   552	556	778	java/lang/Exception
    //   24	32	782	java/lang/Exception
    //   36	44	782	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     bl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */