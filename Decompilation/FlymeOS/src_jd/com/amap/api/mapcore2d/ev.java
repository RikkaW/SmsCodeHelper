package com.amap.api.mapcore2d;

import android.os.Build.VERSION;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class ev
{
  private static ew a;
  private static TrustManager g = new fb();
  private int b;
  private int c;
  private boolean d;
  private SSLContext e;
  private Proxy f;
  
  ev(int paramInt1, int paramInt2, Proxy paramProxy, boolean paramBoolean)
  {
    b = paramInt1;
    c = paramInt2;
    f = paramProxy;
    d = paramBoolean;
    if (paramBoolean) {}
    try
    {
      paramProxy = SSLContext.getInstance("TLS");
      paramProxy.init(null, new TrustManager[] { g }, null);
      e = paramProxy;
      return;
    }
    catch (NoSuchAlgorithmException paramProxy)
    {
      ed.a(paramProxy, "HttpUrlUtil", "HttpUrlUtil");
      paramProxy.printStackTrace();
      return;
    }
    catch (KeyManagementException paramProxy)
    {
      ed.a(paramProxy, "HttpUrlUtil", "HttpUrlUtil");
      paramProxy.printStackTrace();
      return;
    }
    catch (Throwable paramProxy)
    {
      ed.a(paramProxy, "HttpUtil", "HttpUtil");
      paramProxy.printStackTrace();
    }
  }
  
  /* Error */
  private fa a(HttpURLConnection paramHttpURLConnection)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_1
    //   7: invokevirtual 84	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   10: astore 8
    //   12: aload_1
    //   13: invokevirtual 88	java/net/HttpURLConnection:getResponseCode	()I
    //   16: istore_2
    //   17: iload_2
    //   18: sipush 200
    //   21: if_icmpeq +105 -> 126
    //   24: new 90	com/amap/api/mapcore2d/cz
    //   27: dup
    //   28: new 92	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   35: ldc 95
    //   37: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload_1
    //   41: invokevirtual 103	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   44: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 105
    //   49: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: iload_2
    //   53: invokevirtual 108	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   56: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: invokespecial 114	com/amap/api/mapcore2d/cz:<init>	(Ljava/lang/String;)V
    //   62: athrow
    //   63: astore_3
    //   64: aconst_null
    //   65: astore 4
    //   67: aconst_null
    //   68: astore 6
    //   70: aconst_null
    //   71: astore 7
    //   73: aload_3
    //   74: athrow
    //   75: astore_3
    //   76: aload 7
    //   78: ifnull +8 -> 86
    //   81: aload 7
    //   83: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   86: aload 6
    //   88: ifnull +8 -> 96
    //   91: aload 6
    //   93: invokevirtual 122	java/io/InputStream:close	()V
    //   96: aload 5
    //   98: ifnull +8 -> 106
    //   101: aload 5
    //   103: invokevirtual 125	java/io/PushbackInputStream:close	()V
    //   106: aload 4
    //   108: ifnull +8 -> 116
    //   111: aload 4
    //   113: invokevirtual 122	java/io/InputStream:close	()V
    //   116: aload_1
    //   117: ifnull +7 -> 124
    //   120: aload_1
    //   121: invokevirtual 128	java/net/HttpURLConnection:disconnect	()V
    //   124: aload_3
    //   125: athrow
    //   126: new 116	java/io/ByteArrayOutputStream
    //   129: dup
    //   130: invokespecial 129	java/io/ByteArrayOutputStream:<init>	()V
    //   133: astore 7
    //   135: aload_1
    //   136: invokevirtual 133	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   139: astore 6
    //   141: new 124	java/io/PushbackInputStream
    //   144: dup
    //   145: aload 6
    //   147: iconst_2
    //   148: invokespecial 136	java/io/PushbackInputStream:<init>	(Ljava/io/InputStream;I)V
    //   151: astore_3
    //   152: iconst_2
    //   153: newarray <illegal type>
    //   155: astore 4
    //   157: aload_3
    //   158: aload 4
    //   160: invokevirtual 140	java/io/PushbackInputStream:read	([B)I
    //   163: pop
    //   164: aload_3
    //   165: aload 4
    //   167: invokevirtual 144	java/io/PushbackInputStream:unread	([B)V
    //   170: aload 4
    //   172: iconst_0
    //   173: baload
    //   174: bipush 31
    //   176: if_icmpne +442 -> 618
    //   179: aload 4
    //   181: iconst_1
    //   182: baload
    //   183: bipush -117
    //   185: if_icmpne +433 -> 618
    //   188: new 146	java/util/zip/GZIPInputStream
    //   191: dup
    //   192: aload_3
    //   193: invokespecial 149	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   196: astore 4
    //   198: sipush 1024
    //   201: newarray <illegal type>
    //   203: astore 5
    //   205: aload 4
    //   207: aload 5
    //   209: invokevirtual 150	java/io/InputStream:read	([B)I
    //   212: istore_2
    //   213: iload_2
    //   214: iconst_m1
    //   215: if_icmpeq +15 -> 230
    //   218: aload 7
    //   220: aload 5
    //   222: iconst_0
    //   223: iload_2
    //   224: invokevirtual 154	java/io/ByteArrayOutputStream:write	([BII)V
    //   227: goto -22 -> 205
    //   230: getstatic 156	com/amap/api/mapcore2d/ev:a	Lcom/amap/api/mapcore2d/ew;
    //   233: ifnull +11 -> 244
    //   236: getstatic 156	com/amap/api/mapcore2d/ev:a	Lcom/amap/api/mapcore2d/ew;
    //   239: invokeinterface 160 1 0
    //   244: new 162	com/amap/api/mapcore2d/fa
    //   247: dup
    //   248: invokespecial 163	com/amap/api/mapcore2d/fa:<init>	()V
    //   251: astore 5
    //   253: aload 5
    //   255: aload 7
    //   257: invokevirtual 167	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   260: putfield 170	com/amap/api/mapcore2d/fa:a	[B
    //   263: aload 5
    //   265: aload 8
    //   267: putfield 173	com/amap/api/mapcore2d/fa:b	Ljava/util/Map;
    //   270: aload 7
    //   272: ifnull +8 -> 280
    //   275: aload 7
    //   277: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   280: aload 6
    //   282: ifnull +8 -> 290
    //   285: aload 6
    //   287: invokevirtual 122	java/io/InputStream:close	()V
    //   290: aload_3
    //   291: ifnull +7 -> 298
    //   294: aload_3
    //   295: invokevirtual 125	java/io/PushbackInputStream:close	()V
    //   298: aload 4
    //   300: ifnull +8 -> 308
    //   303: aload 4
    //   305: invokevirtual 122	java/io/InputStream:close	()V
    //   308: aload_1
    //   309: ifnull +7 -> 316
    //   312: aload_1
    //   313: invokevirtual 128	java/net/HttpURLConnection:disconnect	()V
    //   316: aload 5
    //   318: areturn
    //   319: astore 7
    //   321: aload 7
    //   323: ldc 61
    //   325: ldc -81
    //   327: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   330: aload 7
    //   332: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   335: goto -249 -> 86
    //   338: astore 6
    //   340: aload 6
    //   342: ldc 61
    //   344: ldc -81
    //   346: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   349: aload 6
    //   351: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   354: goto -258 -> 96
    //   357: astore 5
    //   359: aload 5
    //   361: ldc 61
    //   363: ldc -81
    //   365: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   368: aload 5
    //   370: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   373: goto -267 -> 106
    //   376: astore 4
    //   378: aload 4
    //   380: ldc 61
    //   382: ldc -81
    //   384: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   387: aload 4
    //   389: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   392: goto -276 -> 116
    //   395: astore_1
    //   396: aload_1
    //   397: ldc 61
    //   399: ldc -81
    //   401: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   404: aload_1
    //   405: invokevirtual 73	java/lang/Throwable:printStackTrace	()V
    //   408: goto -284 -> 124
    //   411: astore 7
    //   413: aload 7
    //   415: ldc 61
    //   417: ldc -81
    //   419: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   422: aload 7
    //   424: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   427: goto -147 -> 280
    //   430: astore 6
    //   432: aload 6
    //   434: ldc 61
    //   436: ldc -81
    //   438: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   441: aload 6
    //   443: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   446: goto -156 -> 290
    //   449: astore_3
    //   450: aload_3
    //   451: ldc 61
    //   453: ldc -81
    //   455: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   458: aload_3
    //   459: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   462: goto -164 -> 298
    //   465: astore_3
    //   466: aload_3
    //   467: ldc 61
    //   469: ldc -81
    //   471: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   474: aload_3
    //   475: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   478: goto -170 -> 308
    //   481: astore_1
    //   482: aload_1
    //   483: ldc 61
    //   485: ldc -81
    //   487: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   490: aload_1
    //   491: invokevirtual 73	java/lang/Throwable:printStackTrace	()V
    //   494: aload 5
    //   496: areturn
    //   497: astore_3
    //   498: aconst_null
    //   499: astore 8
    //   501: aconst_null
    //   502: astore 6
    //   504: aconst_null
    //   505: astore 7
    //   507: aload 4
    //   509: astore 5
    //   511: aload 8
    //   513: astore 4
    //   515: goto -439 -> 76
    //   518: astore_3
    //   519: aconst_null
    //   520: astore 8
    //   522: aconst_null
    //   523: astore 6
    //   525: aload 4
    //   527: astore 5
    //   529: aload 8
    //   531: astore 4
    //   533: goto -457 -> 76
    //   536: astore_3
    //   537: aconst_null
    //   538: astore 8
    //   540: aload 4
    //   542: astore 5
    //   544: aload 8
    //   546: astore 4
    //   548: goto -472 -> 76
    //   551: astore 8
    //   553: aconst_null
    //   554: astore 4
    //   556: aload_3
    //   557: astore 5
    //   559: aload 8
    //   561: astore_3
    //   562: goto -486 -> 76
    //   565: astore 8
    //   567: aload_3
    //   568: astore 5
    //   570: aload 8
    //   572: astore_3
    //   573: goto -497 -> 76
    //   576: astore_3
    //   577: aconst_null
    //   578: astore 4
    //   580: aconst_null
    //   581: astore 6
    //   583: goto -510 -> 73
    //   586: astore_3
    //   587: aconst_null
    //   588: astore 4
    //   590: goto -517 -> 73
    //   593: astore 8
    //   595: aconst_null
    //   596: astore 4
    //   598: aload_3
    //   599: astore 5
    //   601: aload 8
    //   603: astore_3
    //   604: goto -531 -> 73
    //   607: astore 8
    //   609: aload_3
    //   610: astore 5
    //   612: aload 8
    //   614: astore_3
    //   615: goto -542 -> 73
    //   618: aload_3
    //   619: astore 4
    //   621: goto -423 -> 198
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	624	0	this	ev
    //   0	624	1	paramHttpURLConnection	HttpURLConnection
    //   16	208	2	i	int
    //   63	11	3	localIOException1	IOException
    //   75	50	3	localObject1	Object
    //   151	144	3	localPushbackInputStream	java.io.PushbackInputStream
    //   449	10	3	localException1	Exception
    //   465	10	3	localException2	Exception
    //   497	1	3	localObject2	Object
    //   518	1	3	localObject3	Object
    //   536	21	3	localObject4	Object
    //   561	12	3	localObject5	Object
    //   576	1	3	localIOException2	IOException
    //   586	13	3	localIOException3	IOException
    //   603	16	3	localIOException4	IOException
    //   1	303	4	localObject6	Object
    //   376	132	4	localException3	Exception
    //   513	107	4	localObject7	Object
    //   4	313	5	localObject8	Object
    //   357	138	5	localException4	Exception
    //   509	102	5	localObject9	Object
    //   68	218	6	localInputStream	java.io.InputStream
    //   338	12	6	localException5	Exception
    //   430	12	6	localException6	Exception
    //   502	80	6	localObject10	Object
    //   71	205	7	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   319	12	7	localIOException5	IOException
    //   411	12	7	localIOException6	IOException
    //   505	1	7	localObject11	Object
    //   10	535	8	localMap	Map
    //   551	9	8	localObject12	Object
    //   565	6	8	localObject13	Object
    //   593	9	8	localIOException7	IOException
    //   607	6	8	localIOException8	IOException
    // Exception table:
    //   from	to	target	type
    //   6	17	63	java/io/IOException
    //   24	63	63	java/io/IOException
    //   126	135	63	java/io/IOException
    //   73	75	75	finally
    //   81	86	319	java/io/IOException
    //   91	96	338	java/lang/Exception
    //   101	106	357	java/lang/Exception
    //   111	116	376	java/lang/Exception
    //   120	124	395	java/lang/Throwable
    //   275	280	411	java/io/IOException
    //   285	290	430	java/lang/Exception
    //   294	298	449	java/lang/Exception
    //   303	308	465	java/lang/Exception
    //   312	316	481	java/lang/Throwable
    //   6	17	497	finally
    //   24	63	497	finally
    //   126	135	497	finally
    //   135	141	518	finally
    //   141	152	536	finally
    //   152	170	551	finally
    //   188	198	551	finally
    //   198	205	565	finally
    //   205	213	565	finally
    //   218	227	565	finally
    //   230	244	565	finally
    //   244	270	565	finally
    //   135	141	576	java/io/IOException
    //   141	152	586	java/io/IOException
    //   152	170	593	java/io/IOException
    //   188	198	593	java/io/IOException
    //   198	205	607	java/io/IOException
    //   205	213	607	java/io/IOException
    //   218	227	607	java/io/IOException
    //   230	244	607	java/io/IOException
    //   244	270	607	java/io/IOException
  }
  
  private String a(Map<String, String> paramMap)
  {
    LinkedList localLinkedList = new LinkedList();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localLinkedList.add(new BasicNameValuePair((String)localEntry.getKey(), (String)localEntry.getValue()));
      }
    }
    if (localLinkedList.size() > 0) {
      return URLEncodedUtils.format(localLinkedList, "UTF-8");
    }
    return null;
  }
  
  private HttpURLConnection a(URL paramURL)
  {
    if (f != null)
    {
      paramURL = paramURL.openConnection(f);
      if (!d) {
        break label79;
      }
      paramURL = (HttpsURLConnection)paramURL;
      ((HttpsURLConnection)paramURL).setSSLSocketFactory(e.getSocketFactory());
    }
    for (;;)
    {
      if ((Build.VERSION.SDK != null) && (Build.VERSION.SDK_INT > 13)) {
        paramURL.setRequestProperty("Connection", "close");
      }
      return paramURL;
      paramURL = (HttpURLConnection)paramURL.openConnection();
      break;
      label79:
      paramURL = (HttpURLConnection)paramURL;
    }
  }
  
  public static void a(ew paramew)
  {
    a = paramew;
  }
  
  private void a(Map<String, String> paramMap, HttpURLConnection paramHttpURLConnection)
  {
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        paramHttpURLConnection.addRequestProperty(str, (String)paramMap.get(str));
      }
    }
    paramHttpURLConnection.setConnectTimeout(b);
    paramHttpURLConnection.setReadTimeout(c);
  }
  
  fa a(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    try
    {
      paramMap2 = a(paramMap2);
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramString);
      if (paramMap2 != null) {
        localStringBuffer.append("?").append(paramMap2);
      }
      paramString = a(new URL(localStringBuffer.toString()));
      a(paramMap1, paramString);
      paramString.setRequestMethod("GET");
      paramString.setDoInput(true);
      paramString.connect();
      paramString = a(paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      ed.a(paramString, "HttpUrlUtil", "getRequest");
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        ed.a(paramString, "HttpUrlUtil", "getRequest");
        paramString.printStackTrace();
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        ed.a(paramString, "HttpUrlUtil", "getRequest");
        paramString.printStackTrace();
      }
    }
  }
  
  /* Error */
  fa a(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, org.apache.http.HttpEntity paramHttpEntity)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 9
    //   6: aload_1
    //   7: astore 7
    //   9: aload_3
    //   10: ifnull +49 -> 59
    //   13: aload_0
    //   14: aload_3
    //   15: invokespecial 297	com/amap/api/mapcore2d/ev:a	(Ljava/util/Map;)Ljava/lang/String;
    //   18: astore_3
    //   19: new 299	java/lang/StringBuffer
    //   22: dup
    //   23: invokespecial 300	java/lang/StringBuffer:<init>	()V
    //   26: astore 6
    //   28: aload 6
    //   30: aload_1
    //   31: invokevirtual 303	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   34: pop
    //   35: aload_3
    //   36: ifnull +16 -> 52
    //   39: aload 6
    //   41: ldc_w 305
    //   44: invokevirtual 303	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   47: aload_3
    //   48: invokevirtual 303	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   51: pop
    //   52: aload 6
    //   54: invokevirtual 306	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   57: astore 7
    //   59: new 116	java/io/ByteArrayOutputStream
    //   62: dup
    //   63: invokespecial 129	java/io/ByteArrayOutputStream:<init>	()V
    //   66: astore_1
    //   67: aload 4
    //   69: invokeinterface 337 1 0
    //   74: astore 6
    //   76: aload 6
    //   78: astore 4
    //   80: aload_1
    //   81: astore_3
    //   82: sipush 1024
    //   85: newarray <illegal type>
    //   87: astore 10
    //   89: aload 6
    //   91: astore 4
    //   93: aload_1
    //   94: astore_3
    //   95: aload 6
    //   97: aload 10
    //   99: invokevirtual 150	java/io/InputStream:read	([B)I
    //   102: istore 5
    //   104: iload 5
    //   106: iconst_m1
    //   107: if_icmpeq +73 -> 180
    //   110: aload 6
    //   112: astore 4
    //   114: aload_1
    //   115: astore_3
    //   116: aload_1
    //   117: aload 10
    //   119: iconst_0
    //   120: iload 5
    //   122: invokevirtual 154	java/io/ByteArrayOutputStream:write	([BII)V
    //   125: goto -36 -> 89
    //   128: astore_2
    //   129: aload 6
    //   131: astore 4
    //   133: aload_1
    //   134: astore_3
    //   135: aload_2
    //   136: ldc 61
    //   138: ldc_w 339
    //   141: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   144: aload 6
    //   146: astore 4
    //   148: aload_1
    //   149: astore_3
    //   150: aload_2
    //   151: invokevirtual 340	java/lang/IllegalStateException:printStackTrace	()V
    //   154: aload_1
    //   155: ifnull +7 -> 162
    //   158: aload_1
    //   159: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   162: aload 9
    //   164: astore_1
    //   165: aload 6
    //   167: ifnull +11 -> 178
    //   170: aload 6
    //   172: invokevirtual 122	java/io/InputStream:close	()V
    //   175: aload 9
    //   177: astore_1
    //   178: aload_1
    //   179: areturn
    //   180: aload 6
    //   182: astore 4
    //   184: aload_1
    //   185: astore_3
    //   186: aload_0
    //   187: aload 7
    //   189: aload_2
    //   190: aload_1
    //   191: invokevirtual 167	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   194: invokevirtual 343	com/amap/api/mapcore2d/ev:a	(Ljava/lang/String;Ljava/util/Map;[B)Lcom/amap/api/mapcore2d/fa;
    //   197: astore_2
    //   198: aload_1
    //   199: ifnull +7 -> 206
    //   202: aload_1
    //   203: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   206: aload_2
    //   207: astore_1
    //   208: aload 6
    //   210: ifnull -32 -> 178
    //   213: aload 6
    //   215: invokevirtual 122	java/io/InputStream:close	()V
    //   218: aload_2
    //   219: areturn
    //   220: astore_1
    //   221: aload_1
    //   222: ldc 61
    //   224: ldc_w 339
    //   227: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   230: aload_1
    //   231: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   234: aload_2
    //   235: areturn
    //   236: astore_2
    //   237: aconst_null
    //   238: astore 6
    //   240: aconst_null
    //   241: astore_1
    //   242: aload 6
    //   244: astore 4
    //   246: aload_1
    //   247: astore_3
    //   248: aload_2
    //   249: ldc 61
    //   251: ldc_w 339
    //   254: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   257: aload 6
    //   259: astore 4
    //   261: aload_1
    //   262: astore_3
    //   263: aload_2
    //   264: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   267: aload_1
    //   268: ifnull +7 -> 275
    //   271: aload_1
    //   272: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   275: aload 9
    //   277: astore_1
    //   278: aload 6
    //   280: ifnull -102 -> 178
    //   283: aload 6
    //   285: invokevirtual 122	java/io/InputStream:close	()V
    //   288: aconst_null
    //   289: areturn
    //   290: astore_1
    //   291: aload_1
    //   292: ldc 61
    //   294: ldc_w 339
    //   297: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   300: aload 8
    //   302: astore_2
    //   303: goto -73 -> 230
    //   306: astore_2
    //   307: aconst_null
    //   308: astore 6
    //   310: aconst_null
    //   311: astore_1
    //   312: aload 6
    //   314: astore 4
    //   316: aload_1
    //   317: astore_3
    //   318: aload_2
    //   319: ldc 61
    //   321: ldc_w 339
    //   324: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   327: aload 6
    //   329: astore 4
    //   331: aload_1
    //   332: astore_3
    //   333: aload_2
    //   334: invokevirtual 73	java/lang/Throwable:printStackTrace	()V
    //   337: aload_1
    //   338: ifnull +7 -> 345
    //   341: aload_1
    //   342: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   345: aload 9
    //   347: astore_1
    //   348: aload 6
    //   350: ifnull -172 -> 178
    //   353: aload 6
    //   355: invokevirtual 122	java/io/InputStream:close	()V
    //   358: aconst_null
    //   359: areturn
    //   360: astore_1
    //   361: aload_1
    //   362: ldc 61
    //   364: ldc_w 339
    //   367: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   370: aload 8
    //   372: astore_2
    //   373: goto -143 -> 230
    //   376: astore_2
    //   377: aconst_null
    //   378: astore 4
    //   380: aconst_null
    //   381: astore_1
    //   382: aload_1
    //   383: ifnull +7 -> 390
    //   386: aload_1
    //   387: invokevirtual 119	java/io/ByteArrayOutputStream:close	()V
    //   390: aload 4
    //   392: ifnull +8 -> 400
    //   395: aload 4
    //   397: invokevirtual 122	java/io/InputStream:close	()V
    //   400: aload_2
    //   401: athrow
    //   402: astore_1
    //   403: aload_1
    //   404: ldc 61
    //   406: ldc_w 339
    //   409: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   412: aload_1
    //   413: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   416: goto -26 -> 390
    //   419: astore_1
    //   420: aload_1
    //   421: ldc 61
    //   423: ldc_w 339
    //   426: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   429: aload_1
    //   430: invokevirtual 177	java/lang/Exception:printStackTrace	()V
    //   433: goto -33 -> 400
    //   436: astore_1
    //   437: aload_1
    //   438: ldc 61
    //   440: ldc_w 339
    //   443: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   446: aload_1
    //   447: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   450: goto -288 -> 162
    //   453: astore_1
    //   454: aload_1
    //   455: ldc 61
    //   457: ldc_w 339
    //   460: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   463: aload 8
    //   465: astore_2
    //   466: goto -236 -> 230
    //   469: astore_1
    //   470: aload_1
    //   471: ldc 61
    //   473: ldc_w 339
    //   476: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   479: aload_1
    //   480: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   483: goto -208 -> 275
    //   486: astore_1
    //   487: aload_1
    //   488: ldc 61
    //   490: ldc_w 339
    //   493: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   496: aload_1
    //   497: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   500: goto -155 -> 345
    //   503: astore_1
    //   504: aload_1
    //   505: ldc 61
    //   507: ldc_w 339
    //   510: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   513: aload_1
    //   514: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   517: goto -311 -> 206
    //   520: astore_2
    //   521: aconst_null
    //   522: astore 4
    //   524: goto -142 -> 382
    //   527: astore_2
    //   528: aload_3
    //   529: astore_1
    //   530: goto -148 -> 382
    //   533: astore_2
    //   534: aconst_null
    //   535: astore 6
    //   537: goto -225 -> 312
    //   540: astore_2
    //   541: goto -229 -> 312
    //   544: astore_2
    //   545: aconst_null
    //   546: astore 6
    //   548: goto -306 -> 242
    //   551: astore_2
    //   552: goto -310 -> 242
    //   555: astore_2
    //   556: aconst_null
    //   557: astore 6
    //   559: aconst_null
    //   560: astore_1
    //   561: goto -432 -> 129
    //   564: astore_2
    //   565: aconst_null
    //   566: astore 6
    //   568: goto -439 -> 129
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	571	0	this	ev
    //   0	571	1	paramString	String
    //   0	571	2	paramMap1	Map<String, String>
    //   0	571	3	paramMap2	Map<String, String>
    //   0	571	4	paramHttpEntity	org.apache.http.HttpEntity
    //   102	19	5	i	int
    //   26	541	6	localObject1	Object
    //   7	181	7	str	String
    //   1	463	8	localObject2	Object
    //   4	342	9	localObject3	Object
    //   87	31	10	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   82	89	128	java/lang/IllegalStateException
    //   95	104	128	java/lang/IllegalStateException
    //   116	125	128	java/lang/IllegalStateException
    //   186	198	128	java/lang/IllegalStateException
    //   213	218	220	java/lang/Exception
    //   13	35	236	java/io/IOException
    //   39	52	236	java/io/IOException
    //   52	59	236	java/io/IOException
    //   59	67	236	java/io/IOException
    //   283	288	290	java/lang/Exception
    //   13	35	306	java/lang/Throwable
    //   39	52	306	java/lang/Throwable
    //   52	59	306	java/lang/Throwable
    //   59	67	306	java/lang/Throwable
    //   353	358	360	java/lang/Exception
    //   13	35	376	finally
    //   39	52	376	finally
    //   52	59	376	finally
    //   59	67	376	finally
    //   386	390	402	java/io/IOException
    //   395	400	419	java/lang/Exception
    //   158	162	436	java/io/IOException
    //   170	175	453	java/lang/Exception
    //   271	275	469	java/io/IOException
    //   341	345	486	java/io/IOException
    //   202	206	503	java/io/IOException
    //   67	76	520	finally
    //   82	89	527	finally
    //   95	104	527	finally
    //   116	125	527	finally
    //   135	144	527	finally
    //   150	154	527	finally
    //   186	198	527	finally
    //   248	257	527	finally
    //   263	267	527	finally
    //   318	327	527	finally
    //   333	337	527	finally
    //   67	76	533	java/lang/Throwable
    //   82	89	540	java/lang/Throwable
    //   95	104	540	java/lang/Throwable
    //   116	125	540	java/lang/Throwable
    //   186	198	540	java/lang/Throwable
    //   67	76	544	java/io/IOException
    //   82	89	551	java/io/IOException
    //   95	104	551	java/io/IOException
    //   116	125	551	java/io/IOException
    //   186	198	551	java/io/IOException
    //   13	35	555	java/lang/IllegalStateException
    //   39	52	555	java/lang/IllegalStateException
    //   52	59	555	java/lang/IllegalStateException
    //   59	67	555	java/lang/IllegalStateException
    //   67	76	564	java/lang/IllegalStateException
  }
  
  fa a(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, byte[] paramArrayOfByte)
  {
    Object localObject = paramString;
    if (paramMap2 != null) {}
    try
    {
      paramMap2 = a(paramMap2);
      localObject = new StringBuffer();
      ((StringBuffer)localObject).append(paramString);
      if (paramMap2 != null) {
        ((StringBuffer)localObject).append("?").append(paramMap2);
      }
      localObject = ((StringBuffer)localObject).toString();
      paramString = a((String)localObject, paramMap1, paramArrayOfByte);
      return paramString;
    }
    catch (Throwable paramString)
    {
      ed.a(paramString, "HttpUrlUtil", "PostReqeust3");
      paramString.printStackTrace();
    }
    return null;
  }
  
  fa a(String paramString, Map<String, String> paramMap, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = a(new URL(paramString));
      a(paramMap, paramString);
      paramString.setRequestMethod("POST");
      paramString.setUseCaches(false);
      paramString.setDoInput(true);
      paramString.setDoOutput(true);
      if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
      {
        paramMap = new DataOutputStream(paramString.getOutputStream());
        paramMap.write(paramArrayOfByte);
        paramMap.close();
      }
      paramString.connect();
      paramString = a(paramString);
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      ed.a(paramString, "HttpUrlUtil", "postRequest");
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        ed.a(paramString, "HttpUrlUtil", "postRequest");
        paramString.printStackTrace();
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        ed.a(paramString, "HttpUrlUtil", "postRequest");
        paramString.printStackTrace();
      }
    }
  }
  
  fa b(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    paramMap2 = a(paramMap2);
    if (paramMap2 == null) {
      return a(paramString, paramMap1, new byte[0]);
    }
    try
    {
      fa localfa = a(paramString, paramMap1, paramMap2.getBytes("UTF-8"));
      return localfa;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ed.a(localUnsupportedEncodingException, "HttpUrlUtil", "postRequest1");
      localUnsupportedEncodingException.printStackTrace();
    }
    return a(paramString, paramMap1, paramMap2.getBytes());
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ev
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */