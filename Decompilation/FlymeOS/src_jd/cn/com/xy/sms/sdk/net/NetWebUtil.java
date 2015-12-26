package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetWebUtil
{
  public static String WEBACTIVITY_URL = "http://x.bizport.cn/get_url_red";
  public static String WEB_SERVER_URL;
  public static String WEB_SERVER_URL2 = "http://android" + (int)(Math.random() * 2.0D + 1.0D) + ".bizport.cn:9998/AndroidWeb/kbAction";
  public static String WEB_SERVER_URL_FLIGHT;
  private static ExecutorService a = Executors.newFixedThreadPool(1);
  
  static
  {
    WEB_SERVER_URL = "http://android" + (int)(Math.random() * 2.0D + 1.0D) + ".bizport.cn:9998/AndroidWeb/";
    WEB_SERVER_URL_FLIGHT = "http://android.bizport.cn:9998/AndroidWeb/flightAction";
  }
  
  private static void a(Runnable paramRunnable)
  {
    a.execute(paramRunnable);
  }
  
  /* Error */
  private static String b(String paramString1, String paramString2, XyCallBack paramXyCallBack)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 8
    //   9: aconst_null
    //   10: astore_3
    //   11: invokestatic 84	cn/com/xy/sms/sdk/util/KeyManager:initAppKey	()V
    //   14: new 86	java/net/URL
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 87	java/net/URL:<init>	(Ljava/lang/String;)V
    //   22: invokevirtual 91	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   25: astore 9
    //   27: aload 9
    //   29: ldc 93
    //   31: getstatic 98	cn/com/xy/sms/sdk/net/NetUtil:APPVERSION	Ljava/lang/String;
    //   34: invokevirtual 104	java/net/URLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   37: aload 9
    //   39: ldc 106
    //   41: ldc 108
    //   43: invokevirtual 111	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   46: aload 9
    //   48: ldc 113
    //   50: ldc 115
    //   52: invokevirtual 111	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   55: aload 9
    //   57: ldc 117
    //   59: getstatic 122	cn/com/xy/sms/sdk/net/i:e	Ljava/lang/String;
    //   62: invokevirtual 111	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   65: aload 9
    //   67: iconst_1
    //   68: invokevirtual 126	java/net/URLConnection:setDoOutput	(Z)V
    //   71: aload 9
    //   73: iconst_1
    //   74: invokevirtual 129	java/net/URLConnection:setDoInput	(Z)V
    //   77: new 131	java/io/PrintWriter
    //   80: dup
    //   81: aload 9
    //   83: invokevirtual 135	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   86: invokespecial 138	java/io/PrintWriter:<init>	(Ljava/io/OutputStream;)V
    //   89: astore 7
    //   91: aload 6
    //   93: astore_0
    //   94: aload 7
    //   96: astore 4
    //   98: aload 8
    //   100: astore_3
    //   101: aload 7
    //   103: aload_1
    //   104: invokevirtual 141	java/io/PrintWriter:print	(Ljava/lang/String;)V
    //   107: aload 6
    //   109: astore_0
    //   110: aload 7
    //   112: astore 4
    //   114: aload 8
    //   116: astore_3
    //   117: aload 7
    //   119: invokevirtual 144	java/io/PrintWriter:flush	()V
    //   122: aload 6
    //   124: astore_0
    //   125: aload 7
    //   127: astore 4
    //   129: aload 8
    //   131: astore_3
    //   132: aload 9
    //   134: invokevirtual 148	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   137: astore 5
    //   139: aload 5
    //   141: astore_0
    //   142: aload 7
    //   144: astore 4
    //   146: aload 5
    //   148: astore_3
    //   149: new 150	java/lang/String
    //   152: dup
    //   153: aload 5
    //   155: invokestatic 155	cn/com/xy/sms/sdk/util/d:b	(Ljava/io/InputStream;)[B
    //   158: ldc -99
    //   160: invokespecial 160	java/lang/String:<init>	([BLjava/lang/String;)V
    //   163: astore_1
    //   164: aload 5
    //   166: astore_0
    //   167: aload 7
    //   169: astore 4
    //   171: aload 5
    //   173: invokestatic 163	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   176: aload 5
    //   178: astore_0
    //   179: aload 7
    //   181: astore 4
    //   183: aload_2
    //   184: iconst_2
    //   185: anewarray 150	java/lang/String
    //   188: dup
    //   189: iconst_0
    //   190: ldc -91
    //   192: aastore
    //   193: dup
    //   194: iconst_1
    //   195: aload_1
    //   196: aastore
    //   197: invokeinterface 170 2 0
    //   202: aload 7
    //   204: invokevirtual 173	java/io/PrintWriter:close	()V
    //   207: aload_1
    //   208: astore_0
    //   209: aload 5
    //   211: ifnull +10 -> 221
    //   214: aload 5
    //   216: invokevirtual 176	java/io/InputStream:close	()V
    //   219: aload_1
    //   220: astore_0
    //   221: aload_0
    //   222: areturn
    //   223: astore 6
    //   225: aconst_null
    //   226: astore 5
    //   228: ldc -78
    //   230: astore_1
    //   231: aload_3
    //   232: astore_0
    //   233: aload 5
    //   235: astore 4
    //   237: aload 6
    //   239: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   242: aload_3
    //   243: astore_0
    //   244: aload 5
    //   246: astore 4
    //   248: aload_2
    //   249: iconst_2
    //   250: anewarray 150	java/lang/String
    //   253: dup
    //   254: iconst_0
    //   255: ldc -73
    //   257: aastore
    //   258: dup
    //   259: iconst_1
    //   260: new 15	java/lang/StringBuilder
    //   263: dup
    //   264: ldc -71
    //   266: invokespecial 21	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   269: aload 6
    //   271: invokevirtual 188	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   274: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   277: invokevirtual 42	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   280: aastore
    //   281: invokeinterface 170 2 0
    //   286: aload 5
    //   288: ifnull +8 -> 296
    //   291: aload 5
    //   293: invokevirtual 173	java/io/PrintWriter:close	()V
    //   296: aload_1
    //   297: astore_0
    //   298: aload_3
    //   299: ifnull -78 -> 221
    //   302: aload_3
    //   303: invokevirtual 176	java/io/InputStream:close	()V
    //   306: aload_1
    //   307: areturn
    //   308: astore_0
    //   309: aload_0
    //   310: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   313: aload_1
    //   314: areturn
    //   315: astore_1
    //   316: aconst_null
    //   317: astore 4
    //   319: aload 5
    //   321: astore_0
    //   322: aload 4
    //   324: ifnull +8 -> 332
    //   327: aload 4
    //   329: invokevirtual 173	java/io/PrintWriter:close	()V
    //   332: aload_0
    //   333: ifnull +7 -> 340
    //   336: aload_0
    //   337: invokevirtual 176	java/io/InputStream:close	()V
    //   340: aload_1
    //   341: athrow
    //   342: astore_0
    //   343: aload_0
    //   344: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   347: goto -7 -> 340
    //   350: astore_0
    //   351: aload_0
    //   352: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   355: aload_1
    //   356: areturn
    //   357: astore_1
    //   358: goto -36 -> 322
    //   361: astore 6
    //   363: ldc -78
    //   365: astore_1
    //   366: aload 7
    //   368: astore 5
    //   370: goto -139 -> 231
    //   373: astore 6
    //   375: aload 5
    //   377: astore_3
    //   378: aload 7
    //   380: astore 5
    //   382: goto -151 -> 231
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	paramString1	String
    //   0	385	1	paramString2	String
    //   0	385	2	paramXyCallBack	XyCallBack
    //   10	368	3	localObject1	Object
    //   96	232	4	localObject2	Object
    //   1	380	5	localObject3	Object
    //   4	119	6	localObject4	Object
    //   223	47	6	localThrowable1	Throwable
    //   361	1	6	localThrowable2	Throwable
    //   373	1	6	localThrowable3	Throwable
    //   89	290	7	localPrintWriter	java.io.PrintWriter
    //   7	123	8	localObject5	Object
    //   25	108	9	localURLConnection	java.net.URLConnection
    // Exception table:
    //   from	to	target	type
    //   11	91	223	java/lang/Throwable
    //   291	296	308	java/lang/Throwable
    //   302	306	308	java/lang/Throwable
    //   11	91	315	finally
    //   327	332	342	java/lang/Throwable
    //   336	340	342	java/lang/Throwable
    //   202	207	350	java/lang/Throwable
    //   214	219	350	java/lang/Throwable
    //   101	107	357	finally
    //   117	122	357	finally
    //   132	139	357	finally
    //   149	164	357	finally
    //   171	176	357	finally
    //   183	202	357	finally
    //   237	242	357	finally
    //   248	286	357	finally
    //   101	107	361	java/lang/Throwable
    //   117	122	361	java/lang/Throwable
    //   132	139	361	java/lang/Throwable
    //   149	164	361	java/lang/Throwable
    //   171	176	373	java/lang/Throwable
    //   183	202	373	java/lang/Throwable
  }
  
  public static void sendPostRequest(String paramString1, String paramString2, XyCallBack paramXyCallBack)
  {
    a.execute(new g(paramString1, paramString2, paramXyCallBack));
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.NetWebUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */