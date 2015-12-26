package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.util.k;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.net.HttpURLConnection;

public final class i
  extends a
{
  public static String d = null;
  public static String e = null;
  private static String f = "HTTP";
  private String g;
  
  public i(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, XyCallBack paramXyCallBack, Boolean paramBoolean1)
  {
    super(paramString1, null, paramString2, paramBoolean, paramString4, paramXyCallBack, paramBoolean1.booleanValue());
    g = paramString3;
  }
  
  public final void a(boolean paramBoolean, String paramString, HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.addRequestProperty("Content-Type", "text/xml;UTF-8");
    if (!StringUtils.isNull(g)) {
      paramHttpURLConnection.addRequestProperty("cnum", g);
    }
    String str2 = e;
    String str1;
    if (paramBoolean)
    {
      paramHttpURLConnection.addRequestProperty("command", "2");
      str1 = str2;
    }
    for (;;)
    {
      str1 = k.a(d, str1);
      paramHttpURLConnection.addRequestProperty("app-key", e);
      paramHttpURLConnection.addRequestProperty("app-key-sign", str1);
      paramHttpURLConnection.addRequestProperty("compress", "1");
      paramHttpURLConnection.addRequestProperty("loginid", "");
      paramHttpURLConnection.addRequestProperty("sdkversion", NetUtil.APPVERSION);
      if (!StringUtils.isNull(paramString)) {
        paramHttpURLConnection.addRequestProperty("cmd", paramString);
      }
      a(paramHttpURLConnection);
      return;
      String str3 = SysParamEntityManager.getStringParam(Constant.getContext(), "HTTPTOKEN");
      paramHttpURLConnection.addRequestProperty("command", "1");
      str1 = str2;
      if (!StringUtils.isNull(str3))
      {
        str1 = str2 + str3;
        paramHttpURLConnection.addRequestProperty("token", str3);
      }
    }
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual 125	cn/com/xy/sms/sdk/net/i:a	()Ljava/net/HttpURLConnection;
    //   6: astore 4
    //   8: aload 4
    //   10: astore_3
    //   11: aload_3
    //   12: invokevirtual 129	java/net/HttpURLConnection:getRequestProperties	()Ljava/util/Map;
    //   15: astore 4
    //   17: getstatic 135	cn/com/xy/sms/sdk/log/LogManager:debug	Z
    //   20: ifeq +32 -> 52
    //   23: aload 4
    //   25: ifnull +27 -> 52
    //   28: aload 4
    //   30: invokeinterface 141 1 0
    //   35: invokeinterface 147 1 0
    //   40: astore 4
    //   42: aload 4
    //   44: invokeinterface 152 1 0
    //   49: ifne +223 -> 272
    //   52: aload_3
    //   53: invokevirtual 155	java/net/HttpURLConnection:connect	()V
    //   56: aload_3
    //   57: invokevirtual 159	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   60: astore 7
    //   62: iconst_2
    //   63: invokestatic 165	cn/com/xy/sms/sdk/util/XyUtil:getXyValue	(I)Ljava/lang/String;
    //   66: astore 6
    //   68: new 100	java/lang/StringBuilder
    //   71: dup
    //   72: ldc -89
    //   74: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   77: aload_0
    //   78: getfield 169	cn/com/xy/sms/sdk/net/i:a	Ljava/lang/String;
    //   81: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc -85
    //   86: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_0
    //   90: getfield 174	cn/com/xy/sms/sdk/net/i:c	Z
    //   93: invokevirtual 177	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: new 100	java/lang/StringBuilder
    //   100: dup
    //   101: ldc -77
    //   103: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   106: aload_0
    //   107: getfield 182	cn/com/xy/sms/sdk/net/i:b	Ljava/lang/String;
    //   110: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: pop
    //   114: ldc -72
    //   116: new 100	java/lang/StringBuilder
    //   119: dup
    //   120: ldc -70
    //   122: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   125: aload 6
    //   127: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: aconst_null
    //   134: invokestatic 189	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   137: aload_0
    //   138: getfield 182	cn/com/xy/sms/sdk/net/i:b	Ljava/lang/String;
    //   141: aload 6
    //   143: invokestatic 194	cn/com/xy/sms/sdk/net/util/f:a	(Ljava/lang/String;Ljava/lang/String;)[B
    //   146: astore 5
    //   148: aload 5
    //   150: astore 4
    //   152: aload_0
    //   153: getfield 174	cn/com/xy/sms/sdk/net/i:c	Z
    //   156: ifeq +10 -> 166
    //   159: aload 5
    //   161: invokestatic 198	cn/com/xy/sms/sdk/util/StringUtils:compressGZip	([B)[B
    //   164: astore 4
    //   166: aload 7
    //   168: aload 4
    //   170: invokevirtual 204	java/io/OutputStream:write	([B)V
    //   173: aload 7
    //   175: invokevirtual 207	java/io/OutputStream:flush	()V
    //   178: aload 7
    //   180: invokevirtual 210	java/io/OutputStream:close	()V
    //   183: aload_3
    //   184: invokevirtual 214	java/net/HttpURLConnection:getResponseCode	()I
    //   187: istore_1
    //   188: new 100	java/lang/StringBuilder
    //   191: dup
    //   192: ldc -40
    //   194: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   197: aload_0
    //   198: getfield 169	cn/com/xy/sms/sdk/net/i:a	Ljava/lang/String;
    //   201: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: ldc -38
    //   206: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: iload_1
    //   210: invokevirtual 221	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: iload_1
    //   215: sipush 200
    //   218: if_icmpne +219 -> 437
    //   221: aload_3
    //   222: invokevirtual 225	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   225: astore 7
    //   227: aload 7
    //   229: invokestatic 230	cn/com/xy/sms/sdk/util/d:b	(Ljava/io/InputStream;)[B
    //   232: astore 5
    //   234: aload 5
    //   236: arraylength
    //   237: istore_1
    //   238: aload_0
    //   239: getfield 182	cn/com/xy/sms/sdk/net/i:b	Ljava/lang/String;
    //   242: iload_1
    //   243: invokestatic 233	cn/com/xy/sms/sdk/net/i:a	(Ljava/lang/String;I)V
    //   246: iload_1
    //   247: i2l
    //   248: ldc2_w 234
    //   251: lcmp
    //   252: ifle +124 -> 376
    //   255: aload_0
    //   256: bipush -9
    //   258: ldc 72
    //   260: invokevirtual 238	cn/com/xy/sms/sdk/net/i:a	(ILjava/lang/String;)V
    //   263: aload_3
    //   264: ifnull +7 -> 271
    //   267: aload_3
    //   268: invokevirtual 241	java/net/HttpURLConnection:disconnect	()V
    //   271: return
    //   272: aload 4
    //   274: invokeinterface 245 1 0
    //   279: checkcast 247	java/util/Map$Entry
    //   282: astore 5
    //   284: new 100	java/lang/StringBuilder
    //   287: dup
    //   288: ldc -7
    //   290: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   293: aload 5
    //   295: invokeinterface 252 1 0
    //   300: checkcast 102	java/lang/String
    //   303: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: ldc -2
    //   308: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   311: aload 5
    //   313: invokeinterface 257 1 0
    //   318: invokevirtual 260	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: goto -280 -> 42
    //   325: astore 4
    //   327: new 100	java/lang/StringBuilder
    //   330: dup
    //   331: ldc_w 262
    //   334: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   337: aload 4
    //   339: invokevirtual 265	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   342: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: pop
    //   346: aload 4
    //   348: invokevirtual 271	java/lang/Object:getClass	()Ljava/lang/Class;
    //   351: ldc_w 273
    //   354: if_acmpne +114 -> 468
    //   357: aload_0
    //   358: bipush -6
    //   360: ldc 72
    //   362: invokevirtual 238	cn/com/xy/sms/sdk/net/i:a	(ILjava/lang/String;)V
    //   365: aload_3
    //   366: ifnull -95 -> 271
    //   369: aload_3
    //   370: invokevirtual 241	java/net/HttpURLConnection:disconnect	()V
    //   373: return
    //   374: astore_3
    //   375: return
    //   376: aload_0
    //   377: getfield 174	cn/com/xy/sms/sdk/net/i:c	Z
    //   380: istore_2
    //   381: aload 5
    //   383: astore 4
    //   385: iload_2
    //   386: ifeq +10 -> 396
    //   389: aload 5
    //   391: invokestatic 276	cn/com/xy/sms/sdk/util/StringUtils:uncompressGZip	([B)[B
    //   394: astore 4
    //   396: aload_0
    //   397: iconst_0
    //   398: new 102	java/lang/String
    //   401: dup
    //   402: aload 4
    //   404: aload 6
    //   406: invokevirtual 280	java/lang/String:getBytes	()[B
    //   409: invokestatic 283	cn/com/xy/sms/sdk/net/util/f:a	([B[B)[B
    //   412: ldc_w 285
    //   415: invokespecial 288	java/lang/String:<init>	([BLjava/lang/String;)V
    //   418: invokevirtual 238	cn/com/xy/sms/sdk/net/i:a	(ILjava/lang/String;)V
    //   421: aload 7
    //   423: invokestatic 291	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   426: aload_3
    //   427: ifnull -156 -> 271
    //   430: aload_3
    //   431: invokevirtual 241	java/net/HttpURLConnection:disconnect	()V
    //   434: return
    //   435: astore_3
    //   436: return
    //   437: aload_0
    //   438: bipush -8
    //   440: ldc 72
    //   442: invokevirtual 238	cn/com/xy/sms/sdk/net/i:a	(ILjava/lang/String;)V
    //   445: goto -19 -> 426
    //   448: astore 5
    //   450: aload_3
    //   451: astore 4
    //   453: aload 5
    //   455: astore_3
    //   456: aload 4
    //   458: ifnull +8 -> 466
    //   461: aload 4
    //   463: invokevirtual 241	java/net/HttpURLConnection:disconnect	()V
    //   466: aload_3
    //   467: athrow
    //   468: aload_0
    //   469: bipush -7
    //   471: ldc 72
    //   473: invokevirtual 238	cn/com/xy/sms/sdk/net/i:a	(ILjava/lang/String;)V
    //   476: goto -111 -> 365
    //   479: astore 5
    //   481: aload_3
    //   482: astore 4
    //   484: aload 5
    //   486: astore_3
    //   487: goto -31 -> 456
    //   490: astore_3
    //   491: return
    //   492: astore 4
    //   494: aload 5
    //   496: astore 4
    //   498: goto -102 -> 396
    //   501: astore 4
    //   503: goto -37 -> 466
    //   506: astore_3
    //   507: aconst_null
    //   508: astore 4
    //   510: goto -54 -> 456
    //   513: astore 4
    //   515: goto -188 -> 327
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	518	0	this	i
    //   187	60	1	i	int
    //   380	6	2	bool	boolean
    //   1	369	3	localObject1	Object
    //   374	57	3	localThrowable1	Throwable
    //   435	16	3	localThrowable2	Throwable
    //   455	32	3	localObject2	Object
    //   490	1	3	localThrowable3	Throwable
    //   506	1	3	localObject3	Object
    //   6	267	4	localObject4	Object
    //   325	22	4	localThrowable4	Throwable
    //   383	100	4	localObject5	Object
    //   492	1	4	localThrowable5	Throwable
    //   496	1	4	localObject6	Object
    //   501	1	4	localThrowable6	Throwable
    //   508	1	4	localObject7	Object
    //   513	1	4	localThrowable7	Throwable
    //   146	244	5	localObject8	Object
    //   448	6	5	localObject9	Object
    //   479	16	5	localObject10	Object
    //   66	339	6	str	String
    //   60	362	7	localObject11	Object
    // Exception table:
    //   from	to	target	type
    //   11	23	325	java/lang/Throwable
    //   28	42	325	java/lang/Throwable
    //   42	52	325	java/lang/Throwable
    //   52	148	325	java/lang/Throwable
    //   152	166	325	java/lang/Throwable
    //   166	214	325	java/lang/Throwable
    //   221	246	325	java/lang/Throwable
    //   255	263	325	java/lang/Throwable
    //   272	322	325	java/lang/Throwable
    //   376	381	325	java/lang/Throwable
    //   396	426	325	java/lang/Throwable
    //   437	445	325	java/lang/Throwable
    //   369	373	374	java/lang/Throwable
    //   430	434	435	java/lang/Throwable
    //   11	23	448	finally
    //   28	42	448	finally
    //   42	52	448	finally
    //   52	148	448	finally
    //   152	166	448	finally
    //   166	214	448	finally
    //   221	246	448	finally
    //   255	263	448	finally
    //   272	322	448	finally
    //   376	381	448	finally
    //   389	396	448	finally
    //   396	426	448	finally
    //   437	445	448	finally
    //   327	365	479	finally
    //   468	476	479	finally
    //   267	271	490	java/lang/Throwable
    //   389	396	492	java/lang/Throwable
    //   461	466	501	java/lang/Throwable
    //   2	8	506	finally
    //   2	8	513	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */