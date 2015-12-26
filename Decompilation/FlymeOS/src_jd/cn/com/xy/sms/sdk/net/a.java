package cn.com.xy.sms.sdk.net;

import android.content.Context;
import android.telephony.TelephonyManager;
import cn.com.xy.sms.sdk.Iservice.OnlineParseInterface;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.k;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseManager;
import java.net.HttpURLConnection;
import java.net.URL;

public class a
  implements Runnable
{
  private static int h = 40000;
  private static String i = null;
  public String a;
  public String b;
  public boolean c = false;
  private c d;
  private XyCallBack e;
  private boolean f = false;
  private String g;
  
  private a(String paramString1, c paramc, String paramString2, boolean paramBoolean, XyCallBack paramXyCallBack)
  {
    a(paramString1, paramc, paramString2, paramBoolean, null, paramXyCallBack, false);
  }
  
  private a(String paramString1, c paramc, String paramString2, boolean paramBoolean, String paramString3, XyCallBack paramXyCallBack)
  {
    a(paramString1, paramc, paramString2, paramBoolean, paramString3, paramXyCallBack, false);
  }
  
  public a(String paramString1, c paramc, String paramString2, boolean paramBoolean1, String paramString3, XyCallBack paramXyCallBack, boolean paramBoolean2)
  {
    a(paramString1, null, paramString2, paramBoolean1, paramString3, paramXyCallBack, paramBoolean2);
  }
  
  public static String a(boolean paramBoolean)
  {
    try
    {
      if (i == null) {
        i = ((TelephonyManager)Constant.getContext().getSystemService("phone")).getDeviceId();
      }
      boolean bool = StringUtils.isNull(i);
      if (bool) {
        return "";
      }
      if (paramBoolean) {
        return k.a(i);
      }
      String str = i;
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return "";
  }
  
  public static void a(String paramString, int paramInt)
  {
    try
    {
      if (DuoquUtils.getLogSdkDoAction() != null) {
        new StringBuilder("length=").append(paramInt).append(" req=").append(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void a(String paramString1, c paramc, String paramString2, boolean paramBoolean1, String paramString3, XyCallBack paramXyCallBack, boolean paramBoolean2)
  {
    d = paramc;
    a = paramString1;
    b = paramString2;
    e = paramXyCallBack;
    f = paramBoolean1;
    g = paramString3;
    c = paramBoolean2;
  }
  
  private static boolean b()
  {
    if (StringUtils.isNull(i.e)) {
      return false;
    }
    try
    {
      OnlineParseInterface localOnlineParseInterface = DexUtil.getOnlineParseImpl(false);
      if (localOnlineParseInterface != null)
      {
        boolean bool = localOnlineParseInterface.isAppChannel(i.e);
        return bool;
      }
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("BaseHttpRunnable isAppChannel", "获取算法包内的是否为应用渠道出现异常");
    }
    return true;
  }
  
  public final HttpURLConnection a()
  {
    try
    {
      
      if ((a.startsWith("https")) || (a.startsWith("HTTPS"))) {}
      Object localObject2;
      for (Object localObject1 = b.b(a);; localObject1 = (HttpURLConnection)new URL(a).openConnection())
      {
        ((HttpURLConnection)localObject1).setConnectTimeout(h);
        ((HttpURLConnection)localObject1).setDoInput(true);
        ((HttpURLConnection)localObject1).setDoOutput(true);
        ((HttpURLConnection)localObject1).setRequestMethod("POST");
        ((HttpURLConnection)localObject1).setUseCaches(false);
        ((HttpURLConnection)localObject1).setInstanceFollowRedirects(true);
        localObject2 = d;
        a(f, g, (HttpURLConnection)localObject1);
        if (c) {
          ((HttpURLConnection)localObject1).addRequestProperty("nz", "1");
        }
        localObject2 = localObject1;
        if (!ParseManager.ismUseNewDes()) {
          break;
        }
        ((HttpURLConnection)localObject1).addRequestProperty("encrypt", "1");
        return (HttpURLConnection)localObject1;
      }
      return (HttpURLConnection)localObject2;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("HTTP", "BaseHttpRunnable getHttpURLConnection error: " + localThrowable.getMessage(), localThrowable);
      localObject2 = null;
    }
  }
  
  public final void a(int paramInt, String paramString)
  {
    new StringBuilder("STATUS: ").append(paramInt).append(" responseStr: ").append(paramString);
    if (e != null) {
      e.execute(new Object[] { Integer.valueOf(paramInt), paramString });
    }
  }
  
  protected final void a(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      paramHttpURLConnection.addRequestProperty("x", cn.com.xy.sms.sdk.net.util.i.a());
      if (b())
      {
        if (a.endsWith("token/"))
        {
          paramHttpURLConnection.addRequestProperty("s", a(false));
          return;
        }
        paramHttpURLConnection.addRequestProperty("p", a(true));
        return;
      }
    }
    catch (Throwable paramHttpURLConnection)
    {
      paramHttpURLConnection.printStackTrace();
    }
  }
  
  public void a(boolean paramBoolean, String paramString, HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection == null) {
      return;
    }
    paramHttpURLConnection.addRequestProperty("Content-Type", "text/xml;UTF-8");
    String str = k.a(i.d, i.e);
    paramHttpURLConnection.addRequestProperty("app-key", i.e);
    paramHttpURLConnection.addRequestProperty("app-key-sign", str);
    paramHttpURLConnection.addRequestProperty("compress", "1");
    paramHttpURLConnection.addRequestProperty("loginid", "");
    paramHttpURLConnection.addRequestProperty("sdkversion", NetUtil.APPVERSION);
    if (paramBoolean)
    {
      paramHttpURLConnection.addRequestProperty("h-token", k.a("", i.e));
      paramHttpURLConnection.addRequestProperty("command", "0");
    }
    for (;;)
    {
      if (!StringUtils.isNull(paramString)) {
        paramHttpURLConnection.addRequestProperty("cmd", paramString);
      }
      a(paramHttpURLConnection);
      return;
      paramHttpURLConnection.addRequestProperty("command", "1");
    }
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aload_0
    //   6: invokevirtual 292	cn/com/xy/sms/sdk/net/a:a	()Ljava/net/HttpURLConnection;
    //   9: astore 5
    //   11: aload 5
    //   13: astore 4
    //   15: aload 5
    //   17: astore_3
    //   18: aload 5
    //   20: invokevirtual 295	java/net/HttpURLConnection:connect	()V
    //   23: aload 5
    //   25: astore 4
    //   27: aload 5
    //   29: astore_3
    //   30: aload 5
    //   32: invokevirtual 299	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   35: astore 9
    //   37: aload 5
    //   39: astore 4
    //   41: aload 5
    //   43: astore_3
    //   44: iconst_2
    //   45: invokestatic 305	cn/com/xy/sms/sdk/util/XyUtil:getXyValue	(I)Ljava/lang/String;
    //   48: astore 8
    //   50: aload 5
    //   52: astore 4
    //   54: aload 5
    //   56: astore_3
    //   57: new 88	java/lang/StringBuilder
    //   60: dup
    //   61: ldc_w 307
    //   64: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   67: aload_0
    //   68: getfield 106	cn/com/xy/sms/sdk/net/a:a	Ljava/lang/String;
    //   71: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload 5
    //   77: astore 4
    //   79: aload 5
    //   81: astore_3
    //   82: new 88	java/lang/StringBuilder
    //   85: dup
    //   86: ldc_w 309
    //   89: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   92: aload_0
    //   93: getfield 108	cn/com/xy/sms/sdk/net/a:b	Ljava/lang/String;
    //   96: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload 5
    //   102: astore 4
    //   104: aload 5
    //   106: astore_3
    //   107: ldc_w 311
    //   110: new 88	java/lang/StringBuilder
    //   113: dup
    //   114: ldc_w 313
    //   117: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   120: aload 8
    //   122: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: invokestatic 137	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload 5
    //   133: astore 4
    //   135: aload 5
    //   137: astore_3
    //   138: aload_0
    //   139: getfield 108	cn/com/xy/sms/sdk/net/a:b	Ljava/lang/String;
    //   142: aload 8
    //   144: invokestatic 318	cn/com/xy/sms/sdk/net/util/f:a	(Ljava/lang/String;Ljava/lang/String;)[B
    //   147: astore 7
    //   149: aload 7
    //   151: astore 6
    //   153: aload 5
    //   155: astore 4
    //   157: aload 5
    //   159: astore_3
    //   160: aload_0
    //   161: getfield 36	cn/com/xy/sms/sdk/net/a:c	Z
    //   164: ifeq +17 -> 181
    //   167: aload 5
    //   169: astore 4
    //   171: aload 5
    //   173: astore_3
    //   174: aload 7
    //   176: invokestatic 322	cn/com/xy/sms/sdk/util/StringUtils:compressGZip	([B)[B
    //   179: astore 6
    //   181: aload 5
    //   183: astore 4
    //   185: aload 5
    //   187: astore_3
    //   188: aload 9
    //   190: aload 6
    //   192: invokevirtual 328	java/io/OutputStream:write	([B)V
    //   195: aload 5
    //   197: astore 4
    //   199: aload 5
    //   201: astore_3
    //   202: aload 9
    //   204: invokevirtual 331	java/io/OutputStream:flush	()V
    //   207: aload 5
    //   209: astore 4
    //   211: aload 5
    //   213: astore_3
    //   214: aload 9
    //   216: invokestatic 336	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   219: aload 5
    //   221: astore 4
    //   223: aload 5
    //   225: astore_3
    //   226: aload 5
    //   228: invokevirtual 340	java/net/HttpURLConnection:getResponseCode	()I
    //   231: istore_1
    //   232: aload 5
    //   234: astore 4
    //   236: aload 5
    //   238: astore_3
    //   239: new 88	java/lang/StringBuilder
    //   242: dup
    //   243: ldc_w 342
    //   246: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   249: aload_0
    //   250: getfield 106	cn/com/xy/sms/sdk/net/a:a	Ljava/lang/String;
    //   253: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: ldc_w 344
    //   259: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: iload_1
    //   263: invokevirtual 97	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   266: pop
    //   267: iload_1
    //   268: sipush 200
    //   271: if_icmpne +189 -> 460
    //   274: aload 5
    //   276: astore 4
    //   278: aload 5
    //   280: astore_3
    //   281: aload 5
    //   283: invokevirtual 348	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   286: astore 9
    //   288: aload 5
    //   290: astore 4
    //   292: aload 5
    //   294: astore_3
    //   295: aload 9
    //   297: invokestatic 351	cn/com/xy/sms/sdk/util/d:b	(Ljava/io/InputStream;)[B
    //   300: astore 7
    //   302: aload 5
    //   304: astore 4
    //   306: aload 5
    //   308: astore_3
    //   309: aload 7
    //   311: arraylength
    //   312: istore_1
    //   313: aload 5
    //   315: astore 4
    //   317: aload 5
    //   319: astore_3
    //   320: aload_0
    //   321: getfield 108	cn/com/xy/sms/sdk/net/a:b	Ljava/lang/String;
    //   324: iload_1
    //   325: invokestatic 353	cn/com/xy/sms/sdk/net/a:a	(Ljava/lang/String;I)V
    //   328: iload_1
    //   329: i2l
    //   330: ldc2_w 354
    //   333: lcmp
    //   334: ifle +35 -> 369
    //   337: aload 5
    //   339: astore 4
    //   341: aload 5
    //   343: astore_3
    //   344: aload_0
    //   345: bipush -9
    //   347: ldc 71
    //   349: invokevirtual 357	cn/com/xy/sms/sdk/net/a:a	(ILjava/lang/String;)V
    //   352: aload 5
    //   354: ifnull +8 -> 362
    //   357: aload 5
    //   359: invokevirtual 360	java/net/HttpURLConnection:disconnect	()V
    //   362: return
    //   363: astore_3
    //   364: aload_3
    //   365: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   368: return
    //   369: aload 5
    //   371: astore 4
    //   373: aload 5
    //   375: astore_3
    //   376: aload_0
    //   377: getfield 36	cn/com/xy/sms/sdk/net/a:c	Z
    //   380: istore_2
    //   381: aload 7
    //   383: astore 6
    //   385: iload_2
    //   386: ifeq +13 -> 399
    //   389: aload 5
    //   391: astore_3
    //   392: aload 7
    //   394: invokestatic 363	cn/com/xy/sms/sdk/util/StringUtils:uncompressGZip	([B)[B
    //   397: astore 6
    //   399: aload 5
    //   401: astore 4
    //   403: aload 5
    //   405: astore_3
    //   406: aload_0
    //   407: iconst_0
    //   408: new 147	java/lang/String
    //   411: dup
    //   412: aload 6
    //   414: aload 8
    //   416: invokevirtual 367	java/lang/String:getBytes	()[B
    //   419: invokestatic 370	cn/com/xy/sms/sdk/net/util/f:a	([B[B)[B
    //   422: ldc_w 372
    //   425: invokespecial 375	java/lang/String:<init>	([BLjava/lang/String;)V
    //   428: invokevirtual 357	cn/com/xy/sms/sdk/net/a:a	(ILjava/lang/String;)V
    //   431: aload 5
    //   433: astore 4
    //   435: aload 5
    //   437: astore_3
    //   438: aload 9
    //   440: invokestatic 336	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   443: aload 5
    //   445: ifnull -83 -> 362
    //   448: aload 5
    //   450: invokevirtual 360	java/net/HttpURLConnection:disconnect	()V
    //   453: return
    //   454: astore_3
    //   455: aload_3
    //   456: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   459: return
    //   460: aload 5
    //   462: astore 4
    //   464: aload 5
    //   466: astore_3
    //   467: aload_0
    //   468: bipush -8
    //   470: ldc 71
    //   472: invokevirtual 357	cn/com/xy/sms/sdk/net/a:a	(ILjava/lang/String;)V
    //   475: goto -32 -> 443
    //   478: astore 5
    //   480: aload 4
    //   482: astore_3
    //   483: ldc -49
    //   485: new 88	java/lang/StringBuilder
    //   488: dup
    //   489: ldc_w 377
    //   492: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   495: aload 5
    //   497: invokevirtual 212	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   500: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: invokevirtual 215	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   506: aload 5
    //   508: invokestatic 218	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   511: aload 4
    //   513: astore_3
    //   514: aload 5
    //   516: invokevirtual 381	java/lang/Object:getClass	()Ljava/lang/Class;
    //   519: ldc_w 383
    //   522: if_acmpne +31 -> 553
    //   525: aload 4
    //   527: astore_3
    //   528: aload_0
    //   529: bipush -6
    //   531: ldc 71
    //   533: invokevirtual 357	cn/com/xy/sms/sdk/net/a:a	(ILjava/lang/String;)V
    //   536: aload 4
    //   538: ifnull -176 -> 362
    //   541: aload 4
    //   543: invokevirtual 360	java/net/HttpURLConnection:disconnect	()V
    //   546: return
    //   547: astore_3
    //   548: aload_3
    //   549: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   552: return
    //   553: aload 4
    //   555: astore_3
    //   556: aload_0
    //   557: bipush -7
    //   559: ldc 71
    //   561: invokevirtual 357	cn/com/xy/sms/sdk/net/a:a	(ILjava/lang/String;)V
    //   564: goto -28 -> 536
    //   567: astore 4
    //   569: aload_3
    //   570: ifnull +7 -> 577
    //   573: aload_3
    //   574: invokevirtual 360	java/net/HttpURLConnection:disconnect	()V
    //   577: aload 4
    //   579: athrow
    //   580: astore_3
    //   581: aload_3
    //   582: invokevirtual 79	java/lang/Throwable:printStackTrace	()V
    //   585: goto -8 -> 577
    //   588: astore_3
    //   589: aload 7
    //   591: astore 6
    //   593: goto -194 -> 399
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	596	0	this	a
    //   231	98	1	j	int
    //   380	6	2	bool	boolean
    //   1	343	3	localObject1	Object
    //   363	2	3	localThrowable1	Throwable
    //   375	63	3	localObject2	Object
    //   454	2	3	localThrowable2	Throwable
    //   466	62	3	localObject3	Object
    //   547	2	3	localThrowable3	Throwable
    //   555	19	3	localObject4	Object
    //   580	2	3	localThrowable4	Throwable
    //   588	1	3	localThrowable5	Throwable
    //   3	551	4	localObject5	Object
    //   567	11	4	localObject6	Object
    //   9	456	5	localHttpURLConnection	HttpURLConnection
    //   478	37	5	localThrowable6	Throwable
    //   151	441	6	arrayOfByte1	byte[]
    //   147	443	7	arrayOfByte2	byte[]
    //   48	367	8	str	String
    //   35	404	9	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   357	362	363	java/lang/Throwable
    //   448	453	454	java/lang/Throwable
    //   5	11	478	java/lang/Throwable
    //   18	23	478	java/lang/Throwable
    //   30	37	478	java/lang/Throwable
    //   44	50	478	java/lang/Throwable
    //   57	75	478	java/lang/Throwable
    //   82	100	478	java/lang/Throwable
    //   107	131	478	java/lang/Throwable
    //   138	149	478	java/lang/Throwable
    //   160	167	478	java/lang/Throwable
    //   174	181	478	java/lang/Throwable
    //   188	195	478	java/lang/Throwable
    //   202	207	478	java/lang/Throwable
    //   214	219	478	java/lang/Throwable
    //   226	232	478	java/lang/Throwable
    //   239	267	478	java/lang/Throwable
    //   281	288	478	java/lang/Throwable
    //   295	302	478	java/lang/Throwable
    //   309	313	478	java/lang/Throwable
    //   320	328	478	java/lang/Throwable
    //   344	352	478	java/lang/Throwable
    //   376	381	478	java/lang/Throwable
    //   406	431	478	java/lang/Throwable
    //   438	443	478	java/lang/Throwable
    //   467	475	478	java/lang/Throwable
    //   541	546	547	java/lang/Throwable
    //   5	11	567	finally
    //   18	23	567	finally
    //   30	37	567	finally
    //   44	50	567	finally
    //   57	75	567	finally
    //   82	100	567	finally
    //   107	131	567	finally
    //   138	149	567	finally
    //   160	167	567	finally
    //   174	181	567	finally
    //   188	195	567	finally
    //   202	207	567	finally
    //   214	219	567	finally
    //   226	232	567	finally
    //   239	267	567	finally
    //   281	288	567	finally
    //   295	302	567	finally
    //   309	313	567	finally
    //   320	328	567	finally
    //   344	352	567	finally
    //   376	381	567	finally
    //   392	399	567	finally
    //   406	431	567	finally
    //   438	443	567	finally
    //   467	475	567	finally
    //   483	511	567	finally
    //   514	525	567	finally
    //   528	536	567	finally
    //   556	564	567	finally
    //   573	577	580	java/lang/Throwable
    //   392	399	588	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */