package com.xiaomi.channel.commonutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.channel.commonutils.string.MD5;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

public class Network
{
  public static final Pattern ContentTypePattern_Charset = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
  public static final Pattern ContentTypePattern_MimeType = Pattern.compile("([^\\s;]+)(.*)");
  public static final Pattern ContentTypePattern_XmlEncoding = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
  
  public static HttpResponse doHttpPost(Context paramContext, String paramString, Map<String, String> paramMap)
    throws IOException
  {
    return httpRequest(paramContext, paramString, "POST", null, fromParamsMapToString(paramMap));
  }
  
  public static String downloadXml(Context paramContext, URL paramURL)
    throws IOException
  {
    return downloadXml(paramContext, paramURL, false, null, "UTF-8", null);
  }
  
  public static String downloadXml(Context paramContext, URL paramURL, boolean paramBoolean, String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    localContext = null;
    try
    {
      paramContext = downloadXmlAsStream(paramContext, paramURL, paramBoolean, paramString1, paramString3);
      localContext = paramContext;
      paramURL = new StringBuilder(1024);
      localContext = paramContext;
      paramString1 = new BufferedReader(new InputStreamReader(paramContext, paramString2));
      localContext = paramContext;
      paramString2 = new char['က'];
      for (;;)
      {
        localContext = paramContext;
        int i = paramString1.read(paramString2);
        if (-1 == i) {
          break;
        }
        localContext = paramContext;
        paramURL.append(paramString2, 0, i);
      }
      try
      {
        localContext.close();
        throw paramContext;
        if (paramContext != null) {}
        try
        {
          paramContext.close();
          return paramURL.toString();
        }
        catch (IOException paramContext)
        {
          for (;;)
          {
            Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + paramContext.toString());
          }
        }
      }
      catch (IOException paramURL)
      {
        for (;;)
        {
          Log.e("com.xiaomi.common.Network", "Failed to close responseStream" + paramURL.toString());
        }
      }
    }
    finally
    {
      if (localContext == null) {}
    }
  }
  
  public static InputStream downloadXmlAsStream(Context paramContext, URL paramURL, boolean paramBoolean, String paramString1, String paramString2)
    throws IOException
  {
    return downloadXmlAsStream(paramContext, paramURL, paramBoolean, paramString1, paramString2, null, null);
  }
  
  public static InputStream downloadXmlAsStream(Context paramContext, URL paramURL, boolean paramBoolean, String paramString1, String paramString2, Map<String, String> paramMap, HttpHeaderInfo paramHttpHeaderInfo)
    throws IOException
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("context");
    }
    if (paramURL == null) {
      throw new IllegalArgumentException("url");
    }
    URL localURL = paramURL;
    if (!paramBoolean) {
      localURL = new URL(encryptURL(paramURL.toString()));
    }
    for (;;)
    {
      int i;
      try
      {
        HttpURLConnection.setFollowRedirects(true);
        paramContext = getHttpUrlConnection(paramContext, localURL);
        paramContext.setConnectTimeout(10000);
        paramContext.setReadTimeout(15000);
        if (!TextUtils.isEmpty(paramString1)) {
          paramContext.setRequestProperty("User-Agent", paramString1);
        }
        if (paramString2 != null) {
          paramContext.setRequestProperty("Cookie", paramString2);
        }
        if (paramMap != null)
        {
          paramString1 = paramMap.keySet().iterator();
          if (paramString1.hasNext())
          {
            paramString2 = (String)paramString1.next();
            paramContext.setRequestProperty(paramString2, (String)paramMap.get(paramString2));
            continue;
          }
        }
        i = 0;
      }
      catch (IOException paramContext)
      {
        throw paramContext;
        if ((paramHttpHeaderInfo != null) && ((paramURL.getProtocol().equals("http")) || (paramURL.getProtocol().equals("https"))))
        {
          ResponseCode = paramContext.getResponseCode();
          if (AllHeaders != null) {
            break label306;
          }
          AllHeaders = new HashMap();
          break label306;
          paramURL = paramContext.getHeaderFieldKey(i);
          paramString1 = paramContext.getHeaderField(i);
          if ((paramURL != null) || (paramString1 != null)) {}
        }
        else
        {
          return new DoneHandlerInputStream(paramContext.getInputStream());
        }
        if ((TextUtils.isEmpty(paramURL)) || (TextUtils.isEmpty(paramString1))) {
          break label312;
        }
        AllHeaders.put(paramURL, paramString1);
      }
      catch (Throwable paramContext)
      {
        throw new IOException(paramContext.getMessage());
      }
      label306:
      continue;
      label312:
      i += 1;
    }
  }
  
  public static String encryptURL(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      new String();
      return String.format("%s&key=%s", new Object[] { paramString, MD5.MD5_32(String.format("%sbe988a6134bc8254465424e5a70ef037", new Object[] { paramString })) });
    }
    return null;
  }
  
  public static String fromParamsMapToString(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if ((localEntry.getKey() != null) && (localEntry.getValue() != null)) {
          try
          {
            localStringBuffer.append(URLEncoder.encode((String)localEntry.getKey(), "UTF-8"));
            localStringBuffer.append("=");
            localStringBuffer.append(URLEncoder.encode((String)localEntry.getValue(), "UTF-8"));
            localStringBuffer.append("&");
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            Log.d("com.xiaomi.common.Network", "Failed to convert from params map to string: " + localUnsupportedEncodingException.toString());
            Log.d("com.xiaomi.common.Network", "map: " + paramMap.toString());
            return null;
          }
        }
      }
      paramMap = localUnsupportedEncodingException;
      if (localUnsupportedEncodingException.length() > 0) {
        paramMap = localUnsupportedEncodingException.deleteCharAt(localUnsupportedEncodingException.length() - 1);
      }
      return paramMap.toString();
    }
    return null;
  }
  
  public static String getActiveConnPoint(Context paramContext)
  {
    if (isWIFIConnected(paramContext)) {
      return "wifi";
    }
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      return "";
    }
    try
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext == null) {
        return "";
      }
    }
    catch (Exception paramContext)
    {
      return "";
    }
    return (paramContext.getTypeName() + "-" + paramContext.getSubtypeName() + "-" + paramContext.getExtraInfo()).toLowerCase();
  }
  
  public static int getActiveNetworkType(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {
          return -1;
        }
      }
      catch (Exception paramContext)
      {
        return -1;
      }
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext != null) {
          return paramContext.getType();
        }
      }
      catch (Exception paramContext) {}
    }
    return -1;
  }
  
  public static String getCMWapUrl(URL paramURL)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramURL.getProtocol()).append("://").append("10.0.0.172").append(paramURL.getPath());
    if (!TextUtils.isEmpty(paramURL.getQuery())) {
      localStringBuilder.append("?").append(paramURL.getQuery());
    }
    return localStringBuilder.toString();
  }
  
  private static URL getDefaultStreamHandlerURL(String paramString)
    throws MalformedURLException
  {
    return new URL(paramString);
  }
  
  public static HttpURLConnection getHttpUrlConnection(Context paramContext, URL paramURL)
    throws IOException
  {
    if (!"http".equals(paramURL.getProtocol())) {
      return (HttpURLConnection)paramURL.openConnection();
    }
    if (isCtwap(paramContext)) {
      return (HttpURLConnection)paramURL.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
    }
    if (!isCmwap(paramContext)) {
      return (HttpURLConnection)paramURL.openConnection();
    }
    paramContext = paramURL.getHost();
    paramURL = (HttpURLConnection)new URL(getCMWapUrl(paramURL)).openConnection();
    paramURL.addRequestProperty("X-Online-Host", paramContext);
    return paramURL;
  }
  
  public static boolean hasNetwork(Context paramContext)
  {
    return getActiveNetworkType(paramContext) >= 0;
  }
  
  /* Error */
  public static HttpResponse httpRequest(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap, String paramString3)
    throws IOException
  {
    // Byte code:
    //   0: new 420	com/xiaomi/channel/commonutils/network/HttpResponse
    //   3: dup
    //   4: invokespecial 421	com/xiaomi/channel/commonutils/network/HttpResponse:<init>	()V
    //   7: astore 18
    //   9: aconst_null
    //   10: astore 15
    //   12: aconst_null
    //   13: astore 16
    //   15: aconst_null
    //   16: astore 17
    //   18: aconst_null
    //   19: astore 12
    //   21: aconst_null
    //   22: astore 14
    //   24: aconst_null
    //   25: astore 13
    //   27: aload 17
    //   29: astore 9
    //   31: aload 13
    //   33: astore 11
    //   35: aload 15
    //   37: astore 6
    //   39: aload 12
    //   41: astore 7
    //   43: aload 16
    //   45: astore 8
    //   47: aload 14
    //   49: astore 10
    //   51: aload_0
    //   52: aload_1
    //   53: invokestatic 423	com/xiaomi/channel/commonutils/network/Network:getDefaultStreamHandlerURL	(Ljava/lang/String;)Ljava/net/URL;
    //   56: invokestatic 148	com/xiaomi/channel/commonutils/network/Network:getHttpUrlConnection	(Landroid/content/Context;Ljava/net/URL;)Ljava/net/HttpURLConnection;
    //   59: astore_1
    //   60: aload 17
    //   62: astore 9
    //   64: aload 13
    //   66: astore 11
    //   68: aload 15
    //   70: astore 6
    //   72: aload 12
    //   74: astore 7
    //   76: aload 16
    //   78: astore 8
    //   80: aload 14
    //   82: astore 10
    //   84: aload_1
    //   85: sipush 10000
    //   88: invokevirtual 151	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   91: aload 17
    //   93: astore 9
    //   95: aload 13
    //   97: astore 11
    //   99: aload 15
    //   101: astore 6
    //   103: aload 12
    //   105: astore 7
    //   107: aload 16
    //   109: astore 8
    //   111: aload 14
    //   113: astore 10
    //   115: aload_1
    //   116: sipush 15000
    //   119: invokevirtual 154	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   122: aload_2
    //   123: astore_0
    //   124: aload_2
    //   125: ifnonnull +7 -> 132
    //   128: ldc_w 425
    //   131: astore_0
    //   132: aload 17
    //   134: astore 9
    //   136: aload 13
    //   138: astore 11
    //   140: aload 15
    //   142: astore 6
    //   144: aload 12
    //   146: astore 7
    //   148: aload 16
    //   150: astore 8
    //   152: aload 14
    //   154: astore 10
    //   156: aload_1
    //   157: aload_0
    //   158: invokevirtual 428	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   161: aload_3
    //   162: ifnull +182 -> 344
    //   165: aload 17
    //   167: astore 9
    //   169: aload 13
    //   171: astore 11
    //   173: aload 15
    //   175: astore 6
    //   177: aload 12
    //   179: astore 7
    //   181: aload 16
    //   183: astore 8
    //   185: aload 14
    //   187: astore 10
    //   189: aload_3
    //   190: invokeinterface 174 1 0
    //   195: invokeinterface 180 1 0
    //   200: astore_0
    //   201: aload 17
    //   203: astore 9
    //   205: aload 13
    //   207: astore 11
    //   209: aload 15
    //   211: astore 6
    //   213: aload 12
    //   215: astore 7
    //   217: aload 16
    //   219: astore 8
    //   221: aload 14
    //   223: astore 10
    //   225: aload_0
    //   226: invokeinterface 186 1 0
    //   231: ifeq +113 -> 344
    //   234: aload 17
    //   236: astore 9
    //   238: aload 13
    //   240: astore 11
    //   242: aload 15
    //   244: astore 6
    //   246: aload 12
    //   248: astore 7
    //   250: aload 16
    //   252: astore 8
    //   254: aload 14
    //   256: astore 10
    //   258: aload_0
    //   259: invokeinterface 190 1 0
    //   264: checkcast 192	java/lang/String
    //   267: astore_2
    //   268: aload 17
    //   270: astore 9
    //   272: aload 13
    //   274: astore 11
    //   276: aload 15
    //   278: astore 6
    //   280: aload 12
    //   282: astore 7
    //   284: aload 16
    //   286: astore 8
    //   288: aload 14
    //   290: astore 10
    //   292: aload_1
    //   293: aload_2
    //   294: aload_3
    //   295: aload_2
    //   296: invokeinterface 196 2 0
    //   301: checkcast 192	java/lang/String
    //   304: invokevirtual 166	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   307: goto -106 -> 201
    //   310: astore_0
    //   311: aload 9
    //   313: astore 6
    //   315: aload 11
    //   317: astore 7
    //   319: aload_0
    //   320: athrow
    //   321: astore_0
    //   322: aload 6
    //   324: ifnull +8 -> 332
    //   327: aload 6
    //   329: invokevirtual 431	java/io/OutputStream:close	()V
    //   332: aload 7
    //   334: ifnull +8 -> 342
    //   337: aload 7
    //   339: invokevirtual 432	java/io/BufferedReader:close	()V
    //   342: aload_0
    //   343: athrow
    //   344: aload 17
    //   346: astore 9
    //   348: aload 13
    //   350: astore 11
    //   352: aload 15
    //   354: astore 6
    //   356: aload 12
    //   358: astore 7
    //   360: aload 16
    //   362: astore 8
    //   364: aload 14
    //   366: astore 10
    //   368: aload 4
    //   370: invokestatic 160	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   373: ifne +170 -> 543
    //   376: aload 17
    //   378: astore 9
    //   380: aload 13
    //   382: astore 11
    //   384: aload 15
    //   386: astore 6
    //   388: aload 12
    //   390: astore 7
    //   392: aload 16
    //   394: astore 8
    //   396: aload 14
    //   398: astore 10
    //   400: aload_1
    //   401: iconst_1
    //   402: invokevirtual 435	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   405: aload 17
    //   407: astore 9
    //   409: aload 13
    //   411: astore 11
    //   413: aload 15
    //   415: astore 6
    //   417: aload 12
    //   419: astore 7
    //   421: aload 16
    //   423: astore 8
    //   425: aload 14
    //   427: astore 10
    //   429: aload 4
    //   431: invokevirtual 439	java/lang/String:getBytes	()[B
    //   434: astore_2
    //   435: aload 17
    //   437: astore 9
    //   439: aload 13
    //   441: astore 11
    //   443: aload 15
    //   445: astore 6
    //   447: aload 12
    //   449: astore 7
    //   451: aload 16
    //   453: astore 8
    //   455: aload 14
    //   457: astore 10
    //   459: aload_1
    //   460: invokevirtual 443	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   463: astore_0
    //   464: aload_0
    //   465: astore 9
    //   467: aload 13
    //   469: astore 11
    //   471: aload_0
    //   472: astore 6
    //   474: aload 12
    //   476: astore 7
    //   478: aload_0
    //   479: astore 8
    //   481: aload 14
    //   483: astore 10
    //   485: aload_0
    //   486: aload_2
    //   487: iconst_0
    //   488: aload_2
    //   489: arraylength
    //   490: invokevirtual 447	java/io/OutputStream:write	([BII)V
    //   493: aload_0
    //   494: astore 9
    //   496: aload 13
    //   498: astore 11
    //   500: aload_0
    //   501: astore 6
    //   503: aload 12
    //   505: astore 7
    //   507: aload_0
    //   508: astore 8
    //   510: aload 14
    //   512: astore 10
    //   514: aload_0
    //   515: invokevirtual 450	java/io/OutputStream:flush	()V
    //   518: aload_0
    //   519: astore 9
    //   521: aload 13
    //   523: astore 11
    //   525: aload_0
    //   526: astore 6
    //   528: aload 12
    //   530: astore 7
    //   532: aload_0
    //   533: astore 8
    //   535: aload 14
    //   537: astore 10
    //   539: aload_0
    //   540: invokevirtual 431	java/io/OutputStream:close	()V
    //   543: aconst_null
    //   544: astore 4
    //   546: aconst_null
    //   547: astore_2
    //   548: aconst_null
    //   549: astore_3
    //   550: aload_3
    //   551: astore 9
    //   553: aload 13
    //   555: astore 11
    //   557: aload 4
    //   559: astore 6
    //   561: aload 12
    //   563: astore 7
    //   565: aload_2
    //   566: astore 8
    //   568: aload 14
    //   570: astore 10
    //   572: aload 18
    //   574: aload_1
    //   575: invokevirtual 211	java/net/HttpURLConnection:getResponseCode	()I
    //   578: putfield 453	com/xiaomi/channel/commonutils/network/HttpResponse:responseCode	I
    //   581: aload_3
    //   582: astore 9
    //   584: aload 13
    //   586: astore 11
    //   588: aload 4
    //   590: astore 6
    //   592: aload 12
    //   594: astore 7
    //   596: aload_2
    //   597: astore 8
    //   599: aload 14
    //   601: astore 10
    //   603: ldc 103
    //   605: new 71	java/lang/StringBuilder
    //   608: dup
    //   609: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   612: ldc_w 455
    //   615: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: aload 18
    //   620: getfield 453	com/xiaomi/channel/commonutils/network/HttpResponse:responseCode	I
    //   623: invokevirtual 458	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   626: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   629: invokestatic 297	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   632: pop
    //   633: iconst_0
    //   634: istore 5
    //   636: aload_3
    //   637: astore 9
    //   639: aload 13
    //   641: astore 11
    //   643: aload 4
    //   645: astore 6
    //   647: aload 12
    //   649: astore 7
    //   651: aload_2
    //   652: astore 8
    //   654: aload 14
    //   656: astore 10
    //   658: aload_1
    //   659: iload 5
    //   661: invokevirtual 226	java/net/HttpURLConnection:getHeaderFieldKey	(I)Ljava/lang/String;
    //   664: astore_0
    //   665: aload_3
    //   666: astore 9
    //   668: aload 13
    //   670: astore 11
    //   672: aload 4
    //   674: astore 6
    //   676: aload 12
    //   678: astore 7
    //   680: aload_2
    //   681: astore 8
    //   683: aload 14
    //   685: astore 10
    //   687: aload_1
    //   688: iload 5
    //   690: invokevirtual 229	java/net/HttpURLConnection:getHeaderField	(I)Ljava/lang/String;
    //   693: astore 15
    //   695: aload_0
    //   696: ifnonnull +212 -> 908
    //   699: aload 15
    //   701: ifnonnull +207 -> 908
    //   704: aload 4
    //   706: astore 6
    //   708: aload 12
    //   710: astore 7
    //   712: aload_2
    //   713: astore 8
    //   715: aload 14
    //   717: astore 10
    //   719: new 76	java/io/BufferedReader
    //   722: dup
    //   723: new 78	java/io/InputStreamReader
    //   726: dup
    //   727: new 6	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream
    //   730: dup
    //   731: aload_1
    //   732: invokevirtual 233	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   735: invokespecial 236	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream:<init>	(Ljava/io/InputStream;)V
    //   738: invokespecial 459	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   741: invokespecial 84	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   744: astore_0
    //   745: aload_3
    //   746: astore 9
    //   748: aload_0
    //   749: astore 11
    //   751: aload 4
    //   753: astore 6
    //   755: aload_0
    //   756: astore 7
    //   758: aload_2
    //   759: astore 8
    //   761: aload_0
    //   762: astore 10
    //   764: aload_0
    //   765: invokevirtual 462	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   768: astore_1
    //   769: aload_3
    //   770: astore 9
    //   772: aload_0
    //   773: astore 11
    //   775: aload 4
    //   777: astore 6
    //   779: aload_0
    //   780: astore 7
    //   782: aload_2
    //   783: astore 8
    //   785: aload_0
    //   786: astore 10
    //   788: new 266	java/lang/StringBuffer
    //   791: dup
    //   792: invokespecial 267	java/lang/StringBuffer:<init>	()V
    //   795: astore 12
    //   797: aload_3
    //   798: astore 9
    //   800: aload_0
    //   801: astore 11
    //   803: aload 4
    //   805: astore 6
    //   807: aload_0
    //   808: astore 7
    //   810: aload_2
    //   811: astore 8
    //   813: aload_0
    //   814: astore 10
    //   816: ldc_w 464
    //   819: invokestatic 469	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   822: astore 13
    //   824: aload_1
    //   825: ifnull +182 -> 1007
    //   828: aload_3
    //   829: astore 9
    //   831: aload_0
    //   832: astore 11
    //   834: aload 4
    //   836: astore 6
    //   838: aload_0
    //   839: astore 7
    //   841: aload_2
    //   842: astore 8
    //   844: aload_0
    //   845: astore 10
    //   847: aload 12
    //   849: aload_1
    //   850: invokevirtual 287	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   853: pop
    //   854: aload_3
    //   855: astore 9
    //   857: aload_0
    //   858: astore 11
    //   860: aload 4
    //   862: astore 6
    //   864: aload_0
    //   865: astore 7
    //   867: aload_2
    //   868: astore 8
    //   870: aload_0
    //   871: astore 10
    //   873: aload 12
    //   875: aload 13
    //   877: invokevirtual 287	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   880: pop
    //   881: aload_3
    //   882: astore 9
    //   884: aload_0
    //   885: astore 11
    //   887: aload 4
    //   889: astore 6
    //   891: aload_0
    //   892: astore 7
    //   894: aload_2
    //   895: astore 8
    //   897: aload_0
    //   898: astore 10
    //   900: aload_0
    //   901: invokevirtual 462	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   904: astore_1
    //   905: goto -81 -> 824
    //   908: aload_3
    //   909: astore 9
    //   911: aload 13
    //   913: astore 11
    //   915: aload 4
    //   917: astore 6
    //   919: aload 12
    //   921: astore 7
    //   923: aload_2
    //   924: astore 8
    //   926: aload 14
    //   928: astore 10
    //   930: aload 18
    //   932: getfield 472	com/xiaomi/channel/commonutils/network/HttpResponse:headers	Ljava/util/Map;
    //   935: aload_0
    //   936: aload 15
    //   938: invokeinterface 240 3 0
    //   943: pop
    //   944: iload 5
    //   946: iconst_1
    //   947: iadd
    //   948: iconst_1
    //   949: iadd
    //   950: istore 5
    //   952: goto -316 -> 636
    //   955: astore_0
    //   956: aload_3
    //   957: astore 9
    //   959: aload 13
    //   961: astore 11
    //   963: aload 4
    //   965: astore 6
    //   967: aload 12
    //   969: astore 7
    //   971: aload_2
    //   972: astore 8
    //   974: aload 14
    //   976: astore 10
    //   978: new 76	java/io/BufferedReader
    //   981: dup
    //   982: new 78	java/io/InputStreamReader
    //   985: dup
    //   986: new 6	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream
    //   989: dup
    //   990: aload_1
    //   991: invokevirtual 475	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   994: invokespecial 236	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream:<init>	(Ljava/io/InputStream;)V
    //   997: invokespecial 459	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   1000: invokespecial 84	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   1003: astore_0
    //   1004: goto -259 -> 745
    //   1007: aload_3
    //   1008: astore 9
    //   1010: aload_0
    //   1011: astore 11
    //   1013: aload 4
    //   1015: astore 6
    //   1017: aload_0
    //   1018: astore 7
    //   1020: aload_2
    //   1021: astore 8
    //   1023: aload_0
    //   1024: astore 10
    //   1026: aload 18
    //   1028: aload 12
    //   1030: invokevirtual 308	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1033: putfield 479	com/xiaomi/channel/commonutils/network/HttpResponse:responseString	Ljava/lang/String;
    //   1036: aload_3
    //   1037: astore 9
    //   1039: aload_0
    //   1040: astore 11
    //   1042: aload 4
    //   1044: astore 6
    //   1046: aload_0
    //   1047: astore 7
    //   1049: aload_2
    //   1050: astore 8
    //   1052: aload_0
    //   1053: astore 10
    //   1055: aload_0
    //   1056: invokevirtual 432	java/io/BufferedReader:close	()V
    //   1059: iconst_0
    //   1060: ifeq +11 -> 1071
    //   1063: new 481	java/lang/NullPointerException
    //   1066: dup
    //   1067: invokespecial 482	java/lang/NullPointerException:<init>	()V
    //   1070: athrow
    //   1071: iconst_0
    //   1072: ifeq +11 -> 1083
    //   1075: new 481	java/lang/NullPointerException
    //   1078: dup
    //   1079: invokespecial 482	java/lang/NullPointerException:<init>	()V
    //   1082: athrow
    //   1083: aload 18
    //   1085: areturn
    //   1086: astore_0
    //   1087: ldc 103
    //   1089: ldc_w 484
    //   1092: aload_0
    //   1093: invokestatic 487	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1096: pop
    //   1097: aload 18
    //   1099: areturn
    //   1100: astore_0
    //   1101: aload 8
    //   1103: astore 6
    //   1105: aload 10
    //   1107: astore 7
    //   1109: new 45	java/io/IOException
    //   1112: dup
    //   1113: aload_0
    //   1114: invokevirtual 243	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   1117: invokespecial 244	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   1120: athrow
    //   1121: astore_1
    //   1122: ldc 103
    //   1124: ldc_w 484
    //   1127: aload_1
    //   1128: invokestatic 487	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1131: pop
    //   1132: goto -790 -> 342
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1135	0	paramContext	Context
    //   0	1135	1	paramString1	String
    //   0	1135	2	paramString2	String
    //   0	1135	3	paramMap	Map<String, String>
    //   0	1135	4	paramString3	String
    //   634	317	5	i	int
    //   37	1067	6	localObject1	Object
    //   41	1067	7	localObject2	Object
    //   45	1057	8	localObject3	Object
    //   29	1009	9	localObject4	Object
    //   49	1057	10	localObject5	Object
    //   33	1008	11	localObject6	Object
    //   19	1010	12	localStringBuffer	StringBuffer
    //   25	935	13	str1	String
    //   22	953	14	localObject7	Object
    //   10	927	15	str2	String
    //   13	439	16	localObject8	Object
    //   16	420	17	localObject9	Object
    //   7	1091	18	localHttpResponse	HttpResponse
    // Exception table:
    //   from	to	target	type
    //   51	60	310	java/io/IOException
    //   84	91	310	java/io/IOException
    //   115	122	310	java/io/IOException
    //   156	161	310	java/io/IOException
    //   189	201	310	java/io/IOException
    //   225	234	310	java/io/IOException
    //   258	268	310	java/io/IOException
    //   292	307	310	java/io/IOException
    //   368	376	310	java/io/IOException
    //   400	405	310	java/io/IOException
    //   429	435	310	java/io/IOException
    //   459	464	310	java/io/IOException
    //   485	493	310	java/io/IOException
    //   514	518	310	java/io/IOException
    //   539	543	310	java/io/IOException
    //   572	581	310	java/io/IOException
    //   603	633	310	java/io/IOException
    //   658	665	310	java/io/IOException
    //   687	695	310	java/io/IOException
    //   764	769	310	java/io/IOException
    //   788	797	310	java/io/IOException
    //   816	824	310	java/io/IOException
    //   847	854	310	java/io/IOException
    //   873	881	310	java/io/IOException
    //   900	905	310	java/io/IOException
    //   930	944	310	java/io/IOException
    //   978	1004	310	java/io/IOException
    //   1026	1036	310	java/io/IOException
    //   1055	1059	310	java/io/IOException
    //   51	60	321	finally
    //   84	91	321	finally
    //   115	122	321	finally
    //   156	161	321	finally
    //   189	201	321	finally
    //   225	234	321	finally
    //   258	268	321	finally
    //   292	307	321	finally
    //   319	321	321	finally
    //   368	376	321	finally
    //   400	405	321	finally
    //   429	435	321	finally
    //   459	464	321	finally
    //   485	493	321	finally
    //   514	518	321	finally
    //   539	543	321	finally
    //   572	581	321	finally
    //   603	633	321	finally
    //   658	665	321	finally
    //   687	695	321	finally
    //   719	745	321	finally
    //   764	769	321	finally
    //   788	797	321	finally
    //   816	824	321	finally
    //   847	854	321	finally
    //   873	881	321	finally
    //   900	905	321	finally
    //   930	944	321	finally
    //   978	1004	321	finally
    //   1026	1036	321	finally
    //   1055	1059	321	finally
    //   1109	1121	321	finally
    //   719	745	955	java/io/IOException
    //   1063	1071	1086	java/io/IOException
    //   1075	1083	1086	java/io/IOException
    //   51	60	1100	java/lang/Throwable
    //   84	91	1100	java/lang/Throwable
    //   115	122	1100	java/lang/Throwable
    //   156	161	1100	java/lang/Throwable
    //   189	201	1100	java/lang/Throwable
    //   225	234	1100	java/lang/Throwable
    //   258	268	1100	java/lang/Throwable
    //   292	307	1100	java/lang/Throwable
    //   368	376	1100	java/lang/Throwable
    //   400	405	1100	java/lang/Throwable
    //   429	435	1100	java/lang/Throwable
    //   459	464	1100	java/lang/Throwable
    //   485	493	1100	java/lang/Throwable
    //   514	518	1100	java/lang/Throwable
    //   539	543	1100	java/lang/Throwable
    //   572	581	1100	java/lang/Throwable
    //   603	633	1100	java/lang/Throwable
    //   658	665	1100	java/lang/Throwable
    //   687	695	1100	java/lang/Throwable
    //   719	745	1100	java/lang/Throwable
    //   764	769	1100	java/lang/Throwable
    //   788	797	1100	java/lang/Throwable
    //   816	824	1100	java/lang/Throwable
    //   847	854	1100	java/lang/Throwable
    //   873	881	1100	java/lang/Throwable
    //   900	905	1100	java/lang/Throwable
    //   930	944	1100	java/lang/Throwable
    //   978	1004	1100	java/lang/Throwable
    //   1026	1036	1100	java/lang/Throwable
    //   1055	1059	1100	java/lang/Throwable
    //   327	332	1121	java/io/IOException
    //   337	342	1121	java/io/IOException
  }
  
  public static boolean isCmwap(Context paramContext)
  {
    if (!"CN".equalsIgnoreCase(((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso())) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {}
      }
      catch (Exception paramContext)
      {
        try
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if (paramContext == null) {
            continue;
          }
          paramContext = paramContext.getExtraInfo();
          if ((TextUtils.isEmpty(paramContext)) || (paramContext.length() < 3) || (paramContext.contains("ctwap"))) {
            continue;
          }
          return paramContext.regionMatches(true, paramContext.length() - 3, "wap", 0, 3);
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
        return false;
      }
    }
    return false;
  }
  
  public static boolean isCtwap(Context paramContext)
  {
    if (!"CN".equalsIgnoreCase(((TelephonyManager)paramContext.getSystemService("phone")).getSimCountryIso())) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext == null) {}
      }
      catch (Exception paramContext)
      {
        try
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if (paramContext == null) {
            continue;
          }
          paramContext = paramContext.getExtraInfo();
          if ((TextUtils.isEmpty(paramContext)) || (paramContext.length() < 3) || (!paramContext.contains("ctwap"))) {
            continue;
          }
          return true;
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
        return false;
      }
    }
    return false;
  }
  
  public static boolean isWIFIConnected(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if (paramContext == null) {
          break;
        }
        if (1 == paramContext.getType())
        {
          bool = true;
          return bool;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
      boolean bool = false;
    }
  }
  
  /* Error */
  public static String uploadFile(String paramString1, Map<String, String> paramMap, java.io.File paramFile, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 520	java/io/File:exists	()Z
    //   4: ifne +7 -> 11
    //   7: aconst_null
    //   8: astore_0
    //   9: aload_0
    //   10: areturn
    //   11: aload_2
    //   12: invokevirtual 523	java/io/File:getName	()Ljava/lang/String;
    //   15: astore 20
    //   17: aconst_null
    //   18: astore 16
    //   20: aconst_null
    //   21: astore 14
    //   23: aconst_null
    //   24: astore 12
    //   26: aconst_null
    //   27: astore 11
    //   29: aconst_null
    //   30: astore 17
    //   32: aconst_null
    //   33: astore 15
    //   35: aconst_null
    //   36: astore 13
    //   38: aconst_null
    //   39: astore 18
    //   41: aconst_null
    //   42: astore 10
    //   44: aconst_null
    //   45: astore 9
    //   47: aconst_null
    //   48: astore 8
    //   50: aload 17
    //   52: astore 5
    //   54: aload 16
    //   56: astore 6
    //   58: aload 18
    //   60: astore 7
    //   62: new 132	java/net/URL
    //   65: dup
    //   66: aload_0
    //   67: invokespecial 138	java/net/URL:<init>	(Ljava/lang/String;)V
    //   70: invokevirtual 378	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   73: checkcast 140	java/net/HttpURLConnection
    //   76: astore 19
    //   78: aload 17
    //   80: astore 5
    //   82: aload 16
    //   84: astore 6
    //   86: aload 18
    //   88: astore 7
    //   90: aload 19
    //   92: sipush 15000
    //   95: invokevirtual 154	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   98: aload 17
    //   100: astore 5
    //   102: aload 16
    //   104: astore 6
    //   106: aload 18
    //   108: astore 7
    //   110: aload 19
    //   112: sipush 10000
    //   115: invokevirtual 151	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   118: aload 17
    //   120: astore 5
    //   122: aload 16
    //   124: astore 6
    //   126: aload 18
    //   128: astore 7
    //   130: aload 19
    //   132: iconst_1
    //   133: invokevirtual 526	java/net/HttpURLConnection:setDoInput	(Z)V
    //   136: aload 17
    //   138: astore 5
    //   140: aload 16
    //   142: astore 6
    //   144: aload 18
    //   146: astore 7
    //   148: aload 19
    //   150: iconst_1
    //   151: invokevirtual 435	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   154: aload 17
    //   156: astore 5
    //   158: aload 16
    //   160: astore 6
    //   162: aload 18
    //   164: astore 7
    //   166: aload 19
    //   168: iconst_0
    //   169: invokevirtual 529	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   172: aload 17
    //   174: astore 5
    //   176: aload 16
    //   178: astore 6
    //   180: aload 18
    //   182: astore 7
    //   184: aload 19
    //   186: ldc 47
    //   188: invokevirtual 428	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   191: aload 17
    //   193: astore 5
    //   195: aload 16
    //   197: astore 6
    //   199: aload 18
    //   201: astore 7
    //   203: aload 19
    //   205: ldc_w 531
    //   208: ldc_w 533
    //   211: invokevirtual 166	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   214: aload 17
    //   216: astore 5
    //   218: aload 16
    //   220: astore 6
    //   222: aload 18
    //   224: astore 7
    //   226: aload 19
    //   228: ldc_w 535
    //   231: ldc_w 537
    //   234: invokevirtual 166	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   237: aload_1
    //   238: ifnull +158 -> 396
    //   241: aload 17
    //   243: astore 5
    //   245: aload 16
    //   247: astore 6
    //   249: aload 18
    //   251: astore 7
    //   253: aload_1
    //   254: invokeinterface 270 1 0
    //   259: invokeinterface 180 1 0
    //   264: astore_0
    //   265: aload 17
    //   267: astore 5
    //   269: aload 16
    //   271: astore 6
    //   273: aload 18
    //   275: astore 7
    //   277: aload_0
    //   278: invokeinterface 186 1 0
    //   283: ifeq +113 -> 396
    //   286: aload 17
    //   288: astore 5
    //   290: aload 16
    //   292: astore 6
    //   294: aload 18
    //   296: astore 7
    //   298: aload_0
    //   299: invokeinterface 190 1 0
    //   304: checkcast 272	java/util/Map$Entry
    //   307: astore_1
    //   308: aload 17
    //   310: astore 5
    //   312: aload 16
    //   314: astore 6
    //   316: aload 18
    //   318: astore 7
    //   320: aload 19
    //   322: aload_1
    //   323: invokeinterface 275 1 0
    //   328: checkcast 192	java/lang/String
    //   331: aload_1
    //   332: invokeinterface 278 1 0
    //   337: checkcast 192	java/lang/String
    //   340: invokevirtual 166	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   343: goto -78 -> 265
    //   346: astore_1
    //   347: aload 8
    //   349: astore 7
    //   351: aload 11
    //   353: astore 6
    //   355: aload 13
    //   357: astore_0
    //   358: aload_0
    //   359: astore 5
    //   361: aload_1
    //   362: athrow
    //   363: astore_0
    //   364: aload 6
    //   366: ifnull +8 -> 374
    //   369: aload 6
    //   371: invokevirtual 540	java/io/FileInputStream:close	()V
    //   374: aload 5
    //   376: ifnull +8 -> 384
    //   379: aload 5
    //   381: invokevirtual 543	java/io/DataOutputStream:close	()V
    //   384: aload 7
    //   386: ifnull +8 -> 394
    //   389: aload 7
    //   391: invokevirtual 432	java/io/BufferedReader:close	()V
    //   394: aload_0
    //   395: athrow
    //   396: aload 17
    //   398: astore 5
    //   400: aload 16
    //   402: astore 6
    //   404: aload 18
    //   406: astore 7
    //   408: aload 19
    //   410: aload 20
    //   412: invokevirtual 502	java/lang/String:length	()I
    //   415: bipush 77
    //   417: iadd
    //   418: aload_2
    //   419: invokevirtual 546	java/io/File:length	()J
    //   422: l2i
    //   423: iadd
    //   424: aload_3
    //   425: invokevirtual 502	java/lang/String:length	()I
    //   428: iadd
    //   429: invokevirtual 549	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   432: aload 17
    //   434: astore 5
    //   436: aload 16
    //   438: astore 6
    //   440: aload 18
    //   442: astore 7
    //   444: new 542	java/io/DataOutputStream
    //   447: dup
    //   448: aload 19
    //   450: invokevirtual 443	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   453: invokespecial 552	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   456: astore_0
    //   457: aload_0
    //   458: ldc_w 554
    //   461: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   464: aload_0
    //   465: new 71	java/lang/StringBuilder
    //   468: dup
    //   469: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   472: ldc_w 559
    //   475: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: aload_3
    //   479: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: ldc_w 561
    //   485: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: aload_2
    //   489: invokevirtual 523	java/io/File:getName	()Ljava/lang/String;
    //   492: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: ldc_w 563
    //   498: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: ldc_w 565
    //   504: invokevirtual 109	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: invokevirtual 101	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   510: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   513: aload_0
    //   514: ldc_w 565
    //   517: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   520: new 539	java/io/FileInputStream
    //   523: dup
    //   524: aload_2
    //   525: invokespecial 568	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   528: astore_1
    //   529: sipush 1024
    //   532: newarray <illegal type>
    //   534: astore_2
    //   535: aload_1
    //   536: aload_2
    //   537: invokevirtual 571	java/io/FileInputStream:read	([B)I
    //   540: istore 4
    //   542: iload 4
    //   544: iconst_m1
    //   545: if_icmpeq +18 -> 563
    //   548: aload_0
    //   549: aload_2
    //   550: iconst_0
    //   551: iload 4
    //   553: invokevirtual 572	java/io/DataOutputStream:write	([BII)V
    //   556: aload_0
    //   557: invokevirtual 573	java/io/DataOutputStream:flush	()V
    //   560: goto -25 -> 535
    //   563: aload_0
    //   564: ldc_w 565
    //   567: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   570: aload_0
    //   571: ldc_w 575
    //   574: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   577: aload_0
    //   578: ldc_w 577
    //   581: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   584: aload_0
    //   585: ldc_w 575
    //   588: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   591: aload_0
    //   592: ldc_w 565
    //   595: invokevirtual 557	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   598: aload_0
    //   599: invokevirtual 573	java/io/DataOutputStream:flush	()V
    //   602: new 266	java/lang/StringBuffer
    //   605: dup
    //   606: invokespecial 267	java/lang/StringBuffer:<init>	()V
    //   609: astore_3
    //   610: new 76	java/io/BufferedReader
    //   613: dup
    //   614: new 78	java/io/InputStreamReader
    //   617: dup
    //   618: new 6	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream
    //   621: dup
    //   622: aload 19
    //   624: invokevirtual 233	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   627: invokespecial 236	com/xiaomi/channel/commonutils/network/Network$DoneHandlerInputStream:<init>	(Ljava/io/InputStream;)V
    //   630: invokespecial 459	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   633: invokespecial 84	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   636: astore_2
    //   637: aload_2
    //   638: invokevirtual 462	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   641: astore 5
    //   643: aload 5
    //   645: ifnull +13 -> 658
    //   648: aload_3
    //   649: aload 5
    //   651: invokevirtual 287	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   654: pop
    //   655: goto -18 -> 637
    //   658: aload_3
    //   659: invokevirtual 308	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   662: astore_3
    //   663: aload_1
    //   664: ifnull +7 -> 671
    //   667: aload_1
    //   668: invokevirtual 540	java/io/FileInputStream:close	()V
    //   671: aload_0
    //   672: ifnull +7 -> 679
    //   675: aload_0
    //   676: invokevirtual 543	java/io/DataOutputStream:close	()V
    //   679: aload_3
    //   680: astore_0
    //   681: aload_2
    //   682: ifnull -673 -> 9
    //   685: aload_2
    //   686: invokevirtual 432	java/io/BufferedReader:close	()V
    //   689: aload_3
    //   690: areturn
    //   691: astore_0
    //   692: ldc 103
    //   694: ldc_w 484
    //   697: aload_0
    //   698: invokestatic 487	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   701: pop
    //   702: aload_3
    //   703: areturn
    //   704: astore_1
    //   705: aload 9
    //   707: astore 7
    //   709: aload 12
    //   711: astore 6
    //   713: aload 15
    //   715: astore_0
    //   716: aload_0
    //   717: astore 5
    //   719: new 45	java/io/IOException
    //   722: dup
    //   723: aload_1
    //   724: invokevirtual 243	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   727: invokespecial 244	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   730: athrow
    //   731: astore_1
    //   732: ldc 103
    //   734: ldc_w 484
    //   737: aload_1
    //   738: invokestatic 487	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   741: pop
    //   742: goto -348 -> 394
    //   745: astore_1
    //   746: aload_0
    //   747: astore 5
    //   749: aload 14
    //   751: astore 6
    //   753: aload 10
    //   755: astore 7
    //   757: aload_1
    //   758: astore_0
    //   759: goto -395 -> 364
    //   762: astore_2
    //   763: aload_0
    //   764: astore 5
    //   766: aload_1
    //   767: astore 6
    //   769: aload 10
    //   771: astore 7
    //   773: aload_2
    //   774: astore_0
    //   775: goto -411 -> 364
    //   778: astore_3
    //   779: aload_0
    //   780: astore 5
    //   782: aload_1
    //   783: astore 6
    //   785: aload_2
    //   786: astore 7
    //   788: aload_3
    //   789: astore_0
    //   790: goto -426 -> 364
    //   793: astore_1
    //   794: aload 12
    //   796: astore 6
    //   798: aload 9
    //   800: astore 7
    //   802: goto -86 -> 716
    //   805: astore_2
    //   806: aload_1
    //   807: astore 6
    //   809: aload_2
    //   810: astore_1
    //   811: aload 9
    //   813: astore 7
    //   815: goto -99 -> 716
    //   818: astore_3
    //   819: aload_1
    //   820: astore 6
    //   822: aload_3
    //   823: astore_1
    //   824: aload_2
    //   825: astore 7
    //   827: goto -111 -> 716
    //   830: astore_1
    //   831: aload 11
    //   833: astore 6
    //   835: aload 8
    //   837: astore 7
    //   839: goto -481 -> 358
    //   842: astore_2
    //   843: aload_1
    //   844: astore 6
    //   846: aload_2
    //   847: astore_1
    //   848: aload 8
    //   850: astore 7
    //   852: goto -494 -> 358
    //   855: astore_3
    //   856: aload_1
    //   857: astore 6
    //   859: aload_3
    //   860: astore_1
    //   861: aload_2
    //   862: astore 7
    //   864: goto -506 -> 358
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	867	0	paramString1	String
    //   0	867	1	paramMap	Map<String, String>
    //   0	867	2	paramFile	java.io.File
    //   0	867	3	paramString2	String
    //   540	12	4	i	int
    //   52	729	5	localObject1	Object
    //   56	802	6	localObject2	Object
    //   60	803	7	localObject3	Object
    //   48	801	8	localObject4	Object
    //   45	767	9	localObject5	Object
    //   42	728	10	localObject6	Object
    //   27	805	11	localObject7	Object
    //   24	771	12	localObject8	Object
    //   36	320	13	localObject9	Object
    //   21	729	14	localObject10	Object
    //   33	681	15	localObject11	Object
    //   18	419	16	localObject12	Object
    //   30	403	17	localObject13	Object
    //   39	402	18	localObject14	Object
    //   76	547	19	localHttpURLConnection	HttpURLConnection
    //   15	396	20	str	String
    // Exception table:
    //   from	to	target	type
    //   62	78	346	java/io/IOException
    //   90	98	346	java/io/IOException
    //   110	118	346	java/io/IOException
    //   130	136	346	java/io/IOException
    //   148	154	346	java/io/IOException
    //   166	172	346	java/io/IOException
    //   184	191	346	java/io/IOException
    //   203	214	346	java/io/IOException
    //   226	237	346	java/io/IOException
    //   253	265	346	java/io/IOException
    //   277	286	346	java/io/IOException
    //   298	308	346	java/io/IOException
    //   320	343	346	java/io/IOException
    //   408	432	346	java/io/IOException
    //   444	457	346	java/io/IOException
    //   62	78	363	finally
    //   90	98	363	finally
    //   110	118	363	finally
    //   130	136	363	finally
    //   148	154	363	finally
    //   166	172	363	finally
    //   184	191	363	finally
    //   203	214	363	finally
    //   226	237	363	finally
    //   253	265	363	finally
    //   277	286	363	finally
    //   298	308	363	finally
    //   320	343	363	finally
    //   361	363	363	finally
    //   408	432	363	finally
    //   444	457	363	finally
    //   719	731	363	finally
    //   667	671	691	java/io/IOException
    //   675	679	691	java/io/IOException
    //   685	689	691	java/io/IOException
    //   62	78	704	java/lang/Throwable
    //   90	98	704	java/lang/Throwable
    //   110	118	704	java/lang/Throwable
    //   130	136	704	java/lang/Throwable
    //   148	154	704	java/lang/Throwable
    //   166	172	704	java/lang/Throwable
    //   184	191	704	java/lang/Throwable
    //   203	214	704	java/lang/Throwable
    //   226	237	704	java/lang/Throwable
    //   253	265	704	java/lang/Throwable
    //   277	286	704	java/lang/Throwable
    //   298	308	704	java/lang/Throwable
    //   320	343	704	java/lang/Throwable
    //   408	432	704	java/lang/Throwable
    //   444	457	704	java/lang/Throwable
    //   369	374	731	java/io/IOException
    //   379	384	731	java/io/IOException
    //   389	394	731	java/io/IOException
    //   457	529	745	finally
    //   529	535	762	finally
    //   535	542	762	finally
    //   548	560	762	finally
    //   563	637	762	finally
    //   637	643	778	finally
    //   648	655	778	finally
    //   658	663	778	finally
    //   457	529	793	java/lang/Throwable
    //   529	535	805	java/lang/Throwable
    //   535	542	805	java/lang/Throwable
    //   548	560	805	java/lang/Throwable
    //   563	637	805	java/lang/Throwable
    //   637	643	818	java/lang/Throwable
    //   648	655	818	java/lang/Throwable
    //   658	663	818	java/lang/Throwable
    //   457	529	830	java/io/IOException
    //   529	535	842	java/io/IOException
    //   535	542	842	java/io/IOException
    //   548	560	842	java/io/IOException
    //   563	637	842	java/io/IOException
    //   637	643	855	java/io/IOException
    //   648	655	855	java/io/IOException
    //   658	663	855	java/io/IOException
  }
  
  public static final class DoneHandlerInputStream
    extends FilterInputStream
  {
    private boolean done;
    
    public DoneHandlerInputStream(InputStream paramInputStream)
    {
      super();
    }
    
    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      if (!done)
      {
        paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
        if (paramInt1 != -1) {
          return paramInt1;
        }
      }
      done = true;
      return -1;
    }
  }
  
  public static class HttpHeaderInfo
  {
    public Map<String, String> AllHeaders;
    public int ResponseCode;
    
    public String toString()
    {
      return String.format("resCode = %1$d, headers = %2$s", new Object[] { Integer.valueOf(ResponseCode), AllHeaders.toString() });
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.network.Network
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */