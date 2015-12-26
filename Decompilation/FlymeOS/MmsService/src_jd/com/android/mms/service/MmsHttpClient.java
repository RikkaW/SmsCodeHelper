package com.android.mms.service;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.service.exception.MmsHttpException;
import com.android.okhttp.ConnectionPool;
import com.android.okhttp.HostResolver;
import com.android.okhttp.HttpHandler;
import com.android.okhttp.HttpsHandler;
import com.android.okhttp.OkHttpClient;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.SocketFactory;

public class MmsHttpClient
{
  private static final Pattern MACRO_P = Pattern.compile("##(\\S+)##");
  private final ConnectionPool mConnectionPool;
  private final Context mContext;
  private final HostResolver mHostResolver;
  private final SocketFactory mSocketFactory;
  
  public MmsHttpClient(Context paramContext, SocketFactory paramSocketFactory, HostResolver paramHostResolver, ConnectionPool paramConnectionPool)
  {
    mContext = paramContext;
    mSocketFactory = paramSocketFactory;
    mHostResolver = paramHostResolver;
    mConnectionPool = paramConnectionPool;
  }
  
  private void addExtraHeaders(HttpURLConnection paramHttpURLConnection, MmsConfig.Overridden paramOverridden)
  {
    Object localObject1 = paramOverridden.getHttpParams();
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = ((String)localObject1).split("\\|");
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i].split(":", 2);
        if (localObject2.length == 2)
        {
          String str = localObject2[0].trim();
          localObject2 = resolveMacro(mContext, localObject2[1].trim(), paramOverridden);
          if ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject2))) {
            paramHttpURLConnection.setRequestProperty(str, (String)localObject2);
          }
        }
        i += 1;
      }
    }
  }
  
  private static void addLocaleToHttpAcceptLanguage(StringBuilder paramStringBuilder, Locale paramLocale)
  {
    String str = convertObsoleteLanguageCodeToNew(paramLocale.getLanguage());
    if (str != null)
    {
      paramStringBuilder.append(str);
      paramLocale = paramLocale.getCountry();
      if (paramLocale != null)
      {
        paramStringBuilder.append("-");
        paramStringBuilder.append(paramLocale);
      }
    }
  }
  
  private static void checkMethod(String paramString)
    throws MmsHttpException
  {
    if ((!"GET".equals(paramString)) && (!"POST".equals(paramString))) {
      throw new MmsHttpException(0, "Invalid method " + paramString);
    }
  }
  
  private static String convertObsoleteLanguageCodeToNew(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      if ("iw".equals(paramString)) {
        return "he";
      }
      if ("in".equals(paramString)) {
        return "id";
      }
      str = paramString;
    } while (!"ji".equals(paramString));
    return "yi";
  }
  
  public static String getCurrentAcceptLanguage(Locale paramLocale)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    addLocaleToHttpAcceptLanguage(localStringBuilder, paramLocale);
    if (!Locale.US.equals(paramLocale))
    {
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("en-US");
    }
    return localStringBuilder.toString();
  }
  
  private static void logHttpHeaders(Map<String, List<String>> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str1 = (String)((Map.Entry)localObject).getKey();
        localObject = (List)((Map.Entry)localObject).getValue();
        if (localObject != null)
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str2 = (String)((Iterator)localObject).next();
            localStringBuilder.append(str1).append('=').append(str2).append('\n');
          }
        }
      }
      Log.v("MmsService", "HTTP: headers\n" + localStringBuilder.toString());
    }
  }
  
  private HttpURLConnection openConnection(URL paramURL, Proxy paramProxy)
    throws MalformedURLException
  {
    String str = paramURL.getProtocol();
    if (str.equals("http")) {}
    for (paramProxy = HttpHandler.createHttpOkHttpClient(paramProxy);; paramProxy = HttpsHandler.createHttpsOkHttpClient(paramProxy))
    {
      return paramProxy.setSocketFactory(mSocketFactory).setHostResolver(mHostResolver).setConnectionPool(mConnectionPool).open(paramURL);
      if (!str.equals("https")) {
        break;
      }
    }
    throw new MalformedURLException("Invalid URL or unrecognized protocol " + str);
  }
  
  private static String resolveMacro(Context paramContext, String paramString, MmsConfig.Overridden paramOverridden)
  {
    if (TextUtils.isEmpty(paramString)) {}
    Object localObject2;
    do
    {
      return paramString;
      Matcher localMatcher = MACRO_P.matcher(paramString);
      int i = 0;
      localObject2 = null;
      if (localMatcher.find())
      {
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new StringBuilder();
        }
        int j = localMatcher.start();
        if (j > i) {
          ((StringBuilder)localObject1).append(paramString.substring(i, j));
        }
        localObject2 = localMatcher.group(1);
        String str = paramOverridden.getHttpParamMacro(paramContext, (String)localObject2);
        if (str != null) {
          ((StringBuilder)localObject1).append(str);
        }
        for (;;)
        {
          i = localMatcher.end();
          localObject2 = localObject1;
          break;
          Log.w("MmsService", "HTTP: invalid macro " + (String)localObject2);
        }
      }
      if ((localObject2 != null) && (i < paramString.length())) {
        ((StringBuilder)localObject2).append(paramString.substring(i));
      }
    } while (localObject2 == null);
    return ((StringBuilder)localObject2).toString();
  }
  
  /* Error */
  public byte[] execute(String paramString1, byte[] paramArrayOfByte, String paramString2, boolean paramBoolean, String paramString3, int paramInt, MmsConfig.Overridden paramOverridden)
    throws MmsHttpException
  {
    // Byte code:
    //   0: new 92	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   7: ldc_w 299
    //   10: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_3
    //   14: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: ldc_w 301
    //   20: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_1
    //   24: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: astore 10
    //   29: iload 4
    //   31: ifeq +774 -> 805
    //   34: new 92	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   41: ldc_w 303
    //   44: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload 5
    //   49: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc 63
    //   54: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: iload 6
    //   59: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   62: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore 9
    //   67: aload 10
    //   69: aload 9
    //   71: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc_w 308
    //   77: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: astore 9
    //   82: aload_2
    //   83: ifnull +730 -> 813
    //   86: aload_2
    //   87: arraylength
    //   88: istore 8
    //   90: ldc -64
    //   92: aload 9
    //   94: iload 8
    //   96: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   99: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: invokestatic 311	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   105: pop
    //   106: aload_3
    //   107: invokestatic 313	com/android/mms/service/MmsHttpClient:checkMethod	(Ljava/lang/String;)V
    //   110: aconst_null
    //   111: astore 15
    //   113: aconst_null
    //   114: astore 16
    //   116: aconst_null
    //   117: astore 17
    //   119: aconst_null
    //   120: astore 14
    //   122: aconst_null
    //   123: astore 13
    //   125: iload 4
    //   127: ifeq +42 -> 169
    //   130: aload 14
    //   132: astore 10
    //   134: aload 15
    //   136: astore 9
    //   138: aload 16
    //   140: astore 11
    //   142: aload 17
    //   144: astore 12
    //   146: new 315	java/net/Proxy
    //   149: dup
    //   150: getstatic 321	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   153: new 323	java/net/InetSocketAddress
    //   156: dup
    //   157: aload 5
    //   159: iload 6
    //   161: invokespecial 326	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   164: invokespecial 329	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   167: astore 13
    //   169: aload 14
    //   171: astore 10
    //   173: aload 15
    //   175: astore 9
    //   177: aload 16
    //   179: astore 11
    //   181: aload 17
    //   183: astore 12
    //   185: aload_0
    //   186: new 208	java/net/URL
    //   189: dup
    //   190: aload_1
    //   191: invokespecial 330	java/net/URL:<init>	(Ljava/lang/String;)V
    //   194: aload 13
    //   196: invokespecial 332	com/android/mms/service/MmsHttpClient:openConnection	(Ljava/net/URL;Ljava/net/Proxy;)Ljava/net/HttpURLConnection;
    //   199: astore 5
    //   201: aload 5
    //   203: astore 10
    //   205: aload 5
    //   207: astore 9
    //   209: aload 5
    //   211: astore 11
    //   213: aload 5
    //   215: astore 12
    //   217: aload 5
    //   219: iconst_1
    //   220: invokevirtual 336	java/net/HttpURLConnection:setDoInput	(Z)V
    //   223: aload 5
    //   225: astore 10
    //   227: aload 5
    //   229: astore 9
    //   231: aload 5
    //   233: astore 11
    //   235: aload 5
    //   237: astore 12
    //   239: aload 5
    //   241: aload 7
    //   243: invokevirtual 339	com/android/mms/service/MmsConfig$Overridden:getHttpSocketTimeout	()I
    //   246: invokevirtual 343	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   249: aload 5
    //   251: astore 10
    //   253: aload 5
    //   255: astore 9
    //   257: aload 5
    //   259: astore 11
    //   261: aload 5
    //   263: astore 12
    //   265: aload 5
    //   267: ldc_w 344
    //   270: invokevirtual 347	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   273: aload 5
    //   275: astore 10
    //   277: aload 5
    //   279: astore 9
    //   281: aload 5
    //   283: astore 11
    //   285: aload 5
    //   287: astore 12
    //   289: ldc_w 349
    //   292: invokestatic 355	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   295: ldc_w 357
    //   298: iconst_1
    //   299: anewarray 351	java/lang/Class
    //   302: dup
    //   303: iconst_0
    //   304: getstatic 363	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   307: aastore
    //   308: invokevirtual 367	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   311: aload 5
    //   313: iconst_1
    //   314: anewarray 4	java/lang/Object
    //   317: dup
    //   318: iconst_0
    //   319: sipush 30000
    //   322: invokestatic 371	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   325: aastore
    //   326: invokevirtual 377	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   329: pop
    //   330: aload 5
    //   332: astore 10
    //   334: aload 5
    //   336: astore 9
    //   338: aload 5
    //   340: astore 11
    //   342: aload 5
    //   344: astore 12
    //   346: aload 5
    //   348: ldc_w 379
    //   351: ldc_w 381
    //   354: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   357: aload 5
    //   359: astore 10
    //   361: aload 5
    //   363: astore 9
    //   365: aload 5
    //   367: astore 11
    //   369: aload 5
    //   371: astore 12
    //   373: aload 5
    //   375: ldc_w 383
    //   378: invokestatic 387	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   381: invokestatic 389	com/android/mms/service/MmsHttpClient:getCurrentAcceptLanguage	(Ljava/util/Locale;)Ljava/lang/String;
    //   384: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   387: aload 5
    //   389: astore 10
    //   391: aload 5
    //   393: astore 9
    //   395: aload 5
    //   397: astore 11
    //   399: aload 5
    //   401: astore 12
    //   403: aload 7
    //   405: invokevirtual 392	com/android/mms/service/MmsConfig$Overridden:getUserAgent	()Ljava/lang/String;
    //   408: astore 13
    //   410: aload 5
    //   412: astore 10
    //   414: aload 5
    //   416: astore 9
    //   418: aload 5
    //   420: astore 11
    //   422: aload 5
    //   424: astore 12
    //   426: ldc -64
    //   428: new 92	java/lang/StringBuilder
    //   431: dup
    //   432: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   435: ldc_w 394
    //   438: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: aload 13
    //   443: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   449: invokestatic 397	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   452: pop
    //   453: aload 5
    //   455: astore 10
    //   457: aload 5
    //   459: astore 9
    //   461: aload 5
    //   463: astore 11
    //   465: aload 5
    //   467: astore 12
    //   469: aload 5
    //   471: ldc_w 399
    //   474: aload 13
    //   476: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   479: aload 5
    //   481: astore 10
    //   483: aload 5
    //   485: astore 9
    //   487: aload 5
    //   489: astore 11
    //   491: aload 5
    //   493: astore 12
    //   495: aload 7
    //   497: invokevirtual 402	com/android/mms/service/MmsConfig$Overridden:getUaProfTagName	()Ljava/lang/String;
    //   500: astore 13
    //   502: aload 5
    //   504: astore 10
    //   506: aload 5
    //   508: astore 9
    //   510: aload 5
    //   512: astore 11
    //   514: aload 5
    //   516: astore 12
    //   518: aload 7
    //   520: invokevirtual 405	com/android/mms/service/MmsConfig$Overridden:getUaProfUrl	()Ljava/lang/String;
    //   523: astore 14
    //   525: aload 14
    //   527: ifnull +71 -> 598
    //   530: aload 5
    //   532: astore 10
    //   534: aload 5
    //   536: astore 9
    //   538: aload 5
    //   540: astore 11
    //   542: aload 5
    //   544: astore 12
    //   546: ldc -64
    //   548: new 92	java/lang/StringBuilder
    //   551: dup
    //   552: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   555: ldc_w 407
    //   558: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   561: aload 14
    //   563: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: invokestatic 397	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   572: pop
    //   573: aload 5
    //   575: astore 10
    //   577: aload 5
    //   579: astore 9
    //   581: aload 5
    //   583: astore 11
    //   585: aload 5
    //   587: astore 12
    //   589: aload 5
    //   591: aload 13
    //   593: aload 14
    //   595: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   598: aload 5
    //   600: astore 10
    //   602: aload 5
    //   604: astore 9
    //   606: aload 5
    //   608: astore 11
    //   610: aload 5
    //   612: astore 12
    //   614: aload_0
    //   615: aload 5
    //   617: aload 7
    //   619: invokespecial 409	com/android/mms/service/MmsHttpClient:addExtraHeaders	(Ljava/net/HttpURLConnection;Lcom/android/mms/service/MmsConfig$Overridden;)V
    //   622: aload 5
    //   624: astore 10
    //   626: aload 5
    //   628: astore 9
    //   630: aload 5
    //   632: astore 11
    //   634: aload 5
    //   636: astore 12
    //   638: ldc 113
    //   640: aload_3
    //   641: invokevirtual 111	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   644: ifeq +861 -> 1505
    //   647: aload_2
    //   648: ifnull +25 -> 673
    //   651: aload 5
    //   653: astore 10
    //   655: aload 5
    //   657: astore 9
    //   659: aload 5
    //   661: astore 11
    //   663: aload 5
    //   665: astore 12
    //   667: aload_2
    //   668: arraylength
    //   669: iconst_1
    //   670: if_icmpge +348 -> 1018
    //   673: aload 5
    //   675: astore 10
    //   677: aload 5
    //   679: astore 9
    //   681: aload 5
    //   683: astore 11
    //   685: aload 5
    //   687: astore 12
    //   689: ldc -64
    //   691: ldc_w 411
    //   694: invokestatic 414	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   697: pop
    //   698: aload 5
    //   700: astore 10
    //   702: aload 5
    //   704: astore 9
    //   706: aload 5
    //   708: astore 11
    //   710: aload 5
    //   712: astore 12
    //   714: new 105	com/android/mms/service/exception/MmsHttpException
    //   717: dup
    //   718: iconst_0
    //   719: ldc_w 416
    //   722: invokespecial 122	com/android/mms/service/exception/MmsHttpException:<init>	(ILjava/lang/String;)V
    //   725: athrow
    //   726: astore_2
    //   727: aload 10
    //   729: astore 9
    //   731: ldc -64
    //   733: new 92	java/lang/StringBuilder
    //   736: dup
    //   737: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   740: ldc_w 418
    //   743: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   746: aload_1
    //   747: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   753: aload_2
    //   754: invokestatic 421	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   757: pop
    //   758: aload 10
    //   760: astore 9
    //   762: new 105	com/android/mms/service/exception/MmsHttpException
    //   765: dup
    //   766: iconst_0
    //   767: new 92	java/lang/StringBuilder
    //   770: dup
    //   771: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   774: ldc_w 423
    //   777: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   780: aload_1
    //   781: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   784: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   787: aload_2
    //   788: invokespecial 426	com/android/mms/service/exception/MmsHttpException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   791: athrow
    //   792: astore_1
    //   793: aload 9
    //   795: ifnull +8 -> 803
    //   798: aload 9
    //   800: invokevirtual 429	java/net/HttpURLConnection:disconnect	()V
    //   803: aload_1
    //   804: athrow
    //   805: ldc_w 431
    //   808: astore 9
    //   810: goto -743 -> 67
    //   813: iconst_0
    //   814: istore 8
    //   816: goto -726 -> 90
    //   819: astore 13
    //   821: aload 5
    //   823: astore 10
    //   825: aload 5
    //   827: astore 9
    //   829: aload 5
    //   831: astore 11
    //   833: aload 5
    //   835: astore 12
    //   837: aload 13
    //   839: invokevirtual 434	java/lang/NoSuchMethodException:printStackTrace	()V
    //   842: goto -512 -> 330
    //   845: astore_2
    //   846: aload 11
    //   848: astore 9
    //   850: ldc -64
    //   852: new 92	java/lang/StringBuilder
    //   855: dup
    //   856: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   859: ldc_w 436
    //   862: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: aload_1
    //   866: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   872: aload_2
    //   873: invokestatic 421	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   876: pop
    //   877: aload 11
    //   879: astore 9
    //   881: new 105	com/android/mms/service/exception/MmsHttpException
    //   884: dup
    //   885: iconst_0
    //   886: new 92	java/lang/StringBuilder
    //   889: dup
    //   890: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   893: ldc_w 438
    //   896: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   899: aload_1
    //   900: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   903: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   906: aload_2
    //   907: invokespecial 426	com/android/mms/service/exception/MmsHttpException:<init>	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   910: athrow
    //   911: astore 13
    //   913: aload 5
    //   915: astore 10
    //   917: aload 5
    //   919: astore 9
    //   921: aload 5
    //   923: astore 11
    //   925: aload 5
    //   927: astore 12
    //   929: aload 13
    //   931: invokevirtual 439	java/lang/reflect/InvocationTargetException:printStackTrace	()V
    //   934: goto -604 -> 330
    //   937: astore_1
    //   938: aload 12
    //   940: astore 9
    //   942: ldc -64
    //   944: ldc_w 441
    //   947: aload_1
    //   948: invokestatic 421	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   951: pop
    //   952: aload 12
    //   954: astore 9
    //   956: new 105	com/android/mms/service/exception/MmsHttpException
    //   959: dup
    //   960: iconst_0
    //   961: aload_1
    //   962: invokespecial 444	com/android/mms/service/exception/MmsHttpException:<init>	(ILjava/lang/Throwable;)V
    //   965: athrow
    //   966: astore 13
    //   968: aload 5
    //   970: astore 10
    //   972: aload 5
    //   974: astore 9
    //   976: aload 5
    //   978: astore 11
    //   980: aload 5
    //   982: astore 12
    //   984: aload 13
    //   986: invokevirtual 445	java/lang/IllegalAccessException:printStackTrace	()V
    //   989: goto -659 -> 330
    //   992: astore 13
    //   994: aload 5
    //   996: astore 10
    //   998: aload 5
    //   1000: astore 9
    //   1002: aload 5
    //   1004: astore 11
    //   1006: aload 5
    //   1008: astore 12
    //   1010: aload 13
    //   1012: invokevirtual 446	java/lang/ClassNotFoundException:printStackTrace	()V
    //   1015: goto -685 -> 330
    //   1018: aload 5
    //   1020: astore 10
    //   1022: aload 5
    //   1024: astore 9
    //   1026: aload 5
    //   1028: astore 11
    //   1030: aload 5
    //   1032: astore 12
    //   1034: aload 5
    //   1036: iconst_1
    //   1037: invokevirtual 449	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   1040: aload 5
    //   1042: astore 10
    //   1044: aload 5
    //   1046: astore 9
    //   1048: aload 5
    //   1050: astore 11
    //   1052: aload 5
    //   1054: astore 12
    //   1056: aload 5
    //   1058: ldc 113
    //   1060: invokevirtual 452	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   1063: aload 5
    //   1065: astore 10
    //   1067: aload 5
    //   1069: astore 9
    //   1071: aload 5
    //   1073: astore 11
    //   1075: aload 5
    //   1077: astore 12
    //   1079: aload 7
    //   1081: invokevirtual 455	com/android/mms/service/MmsConfig$Overridden:getSupportHttpCharsetHeader	()Z
    //   1084: ifeq +391 -> 1475
    //   1087: aload 5
    //   1089: astore 10
    //   1091: aload 5
    //   1093: astore 9
    //   1095: aload 5
    //   1097: astore 11
    //   1099: aload 5
    //   1101: astore 12
    //   1103: aload 5
    //   1105: ldc_w 457
    //   1108: ldc_w 459
    //   1111: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1114: aload 5
    //   1116: astore 10
    //   1118: aload 5
    //   1120: astore 9
    //   1122: aload 5
    //   1124: astore 11
    //   1126: aload 5
    //   1128: astore 12
    //   1130: ldc -64
    //   1132: iconst_2
    //   1133: invokestatic 463	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1136: ifeq +27 -> 1163
    //   1139: aload 5
    //   1141: astore 10
    //   1143: aload 5
    //   1145: astore 9
    //   1147: aload 5
    //   1149: astore 11
    //   1151: aload 5
    //   1153: astore 12
    //   1155: aload 5
    //   1157: invokevirtual 467	java/net/HttpURLConnection:getRequestProperties	()Ljava/util/Map;
    //   1160: invokestatic 469	com/android/mms/service/MmsHttpClient:logHttpHeaders	(Ljava/util/Map;)V
    //   1163: aload 5
    //   1165: astore 10
    //   1167: aload 5
    //   1169: astore 9
    //   1171: aload 5
    //   1173: astore 11
    //   1175: aload 5
    //   1177: astore 12
    //   1179: aload 5
    //   1181: aload_2
    //   1182: arraylength
    //   1183: invokevirtual 472	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   1186: aload 5
    //   1188: astore 10
    //   1190: aload 5
    //   1192: astore 9
    //   1194: aload 5
    //   1196: astore 11
    //   1198: aload 5
    //   1200: astore 12
    //   1202: new 474	java/io/BufferedOutputStream
    //   1205: dup
    //   1206: aload 5
    //   1208: invokevirtual 478	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   1211: invokespecial 481	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   1214: astore_3
    //   1215: aload 5
    //   1217: astore 10
    //   1219: aload 5
    //   1221: astore 9
    //   1223: aload 5
    //   1225: astore 11
    //   1227: aload 5
    //   1229: astore 12
    //   1231: aload_3
    //   1232: aload_2
    //   1233: invokevirtual 487	java/io/OutputStream:write	([B)V
    //   1236: aload 5
    //   1238: astore 10
    //   1240: aload 5
    //   1242: astore 9
    //   1244: aload 5
    //   1246: astore 11
    //   1248: aload 5
    //   1250: astore 12
    //   1252: aload_3
    //   1253: invokevirtual 490	java/io/OutputStream:flush	()V
    //   1256: aload 5
    //   1258: astore 10
    //   1260: aload 5
    //   1262: astore 9
    //   1264: aload 5
    //   1266: astore 11
    //   1268: aload 5
    //   1270: astore 12
    //   1272: aload_3
    //   1273: invokevirtual 493	java/io/OutputStream:close	()V
    //   1276: aload 5
    //   1278: astore 10
    //   1280: aload 5
    //   1282: astore 9
    //   1284: aload 5
    //   1286: astore 11
    //   1288: aload 5
    //   1290: astore 12
    //   1292: aload 5
    //   1294: invokevirtual 496	java/net/HttpURLConnection:getResponseCode	()I
    //   1297: istore 6
    //   1299: aload 5
    //   1301: astore 10
    //   1303: aload 5
    //   1305: astore 9
    //   1307: aload 5
    //   1309: astore 11
    //   1311: aload 5
    //   1313: astore 12
    //   1315: aload 5
    //   1317: invokevirtual 499	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   1320: astore_2
    //   1321: aload 5
    //   1323: astore 10
    //   1325: aload 5
    //   1327: astore 9
    //   1329: aload 5
    //   1331: astore 11
    //   1333: aload 5
    //   1335: astore 12
    //   1337: ldc -64
    //   1339: new 92	java/lang/StringBuilder
    //   1342: dup
    //   1343: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   1346: ldc_w 299
    //   1349: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1352: iload 6
    //   1354: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1357: ldc_w 301
    //   1360: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1363: aload_2
    //   1364: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1367: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1370: invokestatic 311	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1373: pop
    //   1374: aload 5
    //   1376: astore 10
    //   1378: aload 5
    //   1380: astore 9
    //   1382: aload 5
    //   1384: astore 11
    //   1386: aload 5
    //   1388: astore 12
    //   1390: ldc -64
    //   1392: iconst_2
    //   1393: invokestatic 463	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1396: ifeq +27 -> 1423
    //   1399: aload 5
    //   1401: astore 10
    //   1403: aload 5
    //   1405: astore 9
    //   1407: aload 5
    //   1409: astore 11
    //   1411: aload 5
    //   1413: astore 12
    //   1415: aload 5
    //   1417: invokevirtual 502	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   1420: invokestatic 469	com/android/mms/service/MmsHttpClient:logHttpHeaders	(Ljava/util/Map;)V
    //   1423: aload 5
    //   1425: astore 10
    //   1427: aload 5
    //   1429: astore 9
    //   1431: aload 5
    //   1433: astore 11
    //   1435: aload 5
    //   1437: astore 12
    //   1439: iload 6
    //   1441: bipush 100
    //   1443: idiv
    //   1444: iconst_2
    //   1445: if_icmpeq +160 -> 1605
    //   1448: aload 5
    //   1450: astore 10
    //   1452: aload 5
    //   1454: astore 9
    //   1456: aload 5
    //   1458: astore 11
    //   1460: aload 5
    //   1462: astore 12
    //   1464: new 105	com/android/mms/service/exception/MmsHttpException
    //   1467: dup
    //   1468: iload 6
    //   1470: aload_2
    //   1471: invokespecial 122	com/android/mms/service/exception/MmsHttpException:<init>	(ILjava/lang/String;)V
    //   1474: athrow
    //   1475: aload 5
    //   1477: astore 10
    //   1479: aload 5
    //   1481: astore 9
    //   1483: aload 5
    //   1485: astore 11
    //   1487: aload 5
    //   1489: astore 12
    //   1491: aload 5
    //   1493: ldc_w 457
    //   1496: ldc_w 504
    //   1499: invokevirtual 79	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   1502: goto -388 -> 1114
    //   1505: aload 5
    //   1507: astore 10
    //   1509: aload 5
    //   1511: astore 9
    //   1513: aload 5
    //   1515: astore 11
    //   1517: aload 5
    //   1519: astore 12
    //   1521: ldc 107
    //   1523: aload_3
    //   1524: invokevirtual 111	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1527: ifeq -251 -> 1276
    //   1530: aload 5
    //   1532: astore 10
    //   1534: aload 5
    //   1536: astore 9
    //   1538: aload 5
    //   1540: astore 11
    //   1542: aload 5
    //   1544: astore 12
    //   1546: ldc -64
    //   1548: iconst_2
    //   1549: invokestatic 463	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1552: ifeq +27 -> 1579
    //   1555: aload 5
    //   1557: astore 10
    //   1559: aload 5
    //   1561: astore 9
    //   1563: aload 5
    //   1565: astore 11
    //   1567: aload 5
    //   1569: astore 12
    //   1571: aload 5
    //   1573: invokevirtual 467	java/net/HttpURLConnection:getRequestProperties	()Ljava/util/Map;
    //   1576: invokestatic 469	com/android/mms/service/MmsHttpClient:logHttpHeaders	(Ljava/util/Map;)V
    //   1579: aload 5
    //   1581: astore 10
    //   1583: aload 5
    //   1585: astore 9
    //   1587: aload 5
    //   1589: astore 11
    //   1591: aload 5
    //   1593: astore 12
    //   1595: aload 5
    //   1597: ldc 107
    //   1599: invokevirtual 452	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   1602: goto -326 -> 1276
    //   1605: aload 5
    //   1607: astore 10
    //   1609: aload 5
    //   1611: astore 9
    //   1613: aload 5
    //   1615: astore 11
    //   1617: aload 5
    //   1619: astore 12
    //   1621: new 506	java/io/BufferedInputStream
    //   1624: dup
    //   1625: aload 5
    //   1627: invokevirtual 510	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   1630: invokespecial 513	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   1633: astore_2
    //   1634: aload 5
    //   1636: astore 10
    //   1638: aload 5
    //   1640: astore 9
    //   1642: aload 5
    //   1644: astore 11
    //   1646: aload 5
    //   1648: astore 12
    //   1650: new 515	java/io/ByteArrayOutputStream
    //   1653: dup
    //   1654: invokespecial 516	java/io/ByteArrayOutputStream:<init>	()V
    //   1657: astore_3
    //   1658: aload 5
    //   1660: astore 10
    //   1662: aload 5
    //   1664: astore 9
    //   1666: aload 5
    //   1668: astore 11
    //   1670: aload 5
    //   1672: astore 12
    //   1674: sipush 4096
    //   1677: newarray <illegal type>
    //   1679: astore 7
    //   1681: aload 5
    //   1683: astore 10
    //   1685: aload 5
    //   1687: astore 9
    //   1689: aload 5
    //   1691: astore 11
    //   1693: aload 5
    //   1695: astore 12
    //   1697: aload_2
    //   1698: aload 7
    //   1700: invokevirtual 522	java/io/InputStream:read	([B)I
    //   1703: istore 6
    //   1705: iload 6
    //   1707: ifle +31 -> 1738
    //   1710: aload 5
    //   1712: astore 10
    //   1714: aload 5
    //   1716: astore 9
    //   1718: aload 5
    //   1720: astore 11
    //   1722: aload 5
    //   1724: astore 12
    //   1726: aload_3
    //   1727: aload 7
    //   1729: iconst_0
    //   1730: iload 6
    //   1732: invokevirtual 525	java/io/ByteArrayOutputStream:write	([BII)V
    //   1735: goto -54 -> 1681
    //   1738: aload 5
    //   1740: astore 10
    //   1742: aload 5
    //   1744: astore 9
    //   1746: aload 5
    //   1748: astore 11
    //   1750: aload 5
    //   1752: astore 12
    //   1754: aload_2
    //   1755: invokevirtual 526	java/io/InputStream:close	()V
    //   1758: aload 5
    //   1760: astore 10
    //   1762: aload 5
    //   1764: astore 9
    //   1766: aload 5
    //   1768: astore 11
    //   1770: aload 5
    //   1772: astore 12
    //   1774: aload_3
    //   1775: invokevirtual 530	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   1778: astore_2
    //   1779: aload 5
    //   1781: astore 10
    //   1783: aload 5
    //   1785: astore 9
    //   1787: aload 5
    //   1789: astore 11
    //   1791: aload 5
    //   1793: astore 12
    //   1795: new 92	java/lang/StringBuilder
    //   1798: dup
    //   1799: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   1802: ldc_w 532
    //   1805: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1808: astore_3
    //   1809: aload_2
    //   1810: ifnull +66 -> 1876
    //   1813: aload 5
    //   1815: astore 10
    //   1817: aload 5
    //   1819: astore 9
    //   1821: aload 5
    //   1823: astore 11
    //   1825: aload 5
    //   1827: astore 12
    //   1829: aload_2
    //   1830: arraylength
    //   1831: istore 6
    //   1833: aload 5
    //   1835: astore 10
    //   1837: aload 5
    //   1839: astore 9
    //   1841: aload 5
    //   1843: astore 11
    //   1845: aload 5
    //   1847: astore 12
    //   1849: ldc -64
    //   1851: aload_3
    //   1852: iload 6
    //   1854: invokevirtual 306	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1857: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1860: invokestatic 311	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   1863: pop
    //   1864: aload 5
    //   1866: ifnull +8 -> 1874
    //   1869: aload 5
    //   1871: invokevirtual 429	java/net/HttpURLConnection:disconnect	()V
    //   1874: aload_2
    //   1875: areturn
    //   1876: iconst_0
    //   1877: istore 6
    //   1879: goto -46 -> 1833
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1882	0	this	MmsHttpClient
    //   0	1882	1	paramString1	String
    //   0	1882	2	paramArrayOfByte	byte[]
    //   0	1882	3	paramString2	String
    //   0	1882	4	paramBoolean	boolean
    //   0	1882	5	paramString3	String
    //   0	1882	6	paramInt	int
    //   0	1882	7	paramOverridden	MmsConfig.Overridden
    //   88	727	8	i	int
    //   65	1775	9	localObject1	Object
    //   27	1809	10	localObject2	Object
    //   140	1704	11	localObject3	Object
    //   144	1704	12	localObject4	Object
    //   123	469	13	localObject5	Object
    //   819	19	13	localNoSuchMethodException	NoSuchMethodException
    //   911	19	13	localInvocationTargetException	java.lang.reflect.InvocationTargetException
    //   966	19	13	localIllegalAccessException	IllegalAccessException
    //   992	19	13	localClassNotFoundException	ClassNotFoundException
    //   120	474	14	str	String
    //   111	63	15	localObject6	Object
    //   114	64	16	localObject7	Object
    //   117	65	17	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   146	169	726	java/net/MalformedURLException
    //   185	201	726	java/net/MalformedURLException
    //   217	223	726	java/net/MalformedURLException
    //   239	249	726	java/net/MalformedURLException
    //   265	273	726	java/net/MalformedURLException
    //   289	330	726	java/net/MalformedURLException
    //   346	357	726	java/net/MalformedURLException
    //   373	387	726	java/net/MalformedURLException
    //   403	410	726	java/net/MalformedURLException
    //   426	453	726	java/net/MalformedURLException
    //   469	479	726	java/net/MalformedURLException
    //   495	502	726	java/net/MalformedURLException
    //   518	525	726	java/net/MalformedURLException
    //   546	573	726	java/net/MalformedURLException
    //   589	598	726	java/net/MalformedURLException
    //   614	622	726	java/net/MalformedURLException
    //   638	647	726	java/net/MalformedURLException
    //   667	673	726	java/net/MalformedURLException
    //   689	698	726	java/net/MalformedURLException
    //   714	726	726	java/net/MalformedURLException
    //   837	842	726	java/net/MalformedURLException
    //   929	934	726	java/net/MalformedURLException
    //   984	989	726	java/net/MalformedURLException
    //   1010	1015	726	java/net/MalformedURLException
    //   1034	1040	726	java/net/MalformedURLException
    //   1056	1063	726	java/net/MalformedURLException
    //   1079	1087	726	java/net/MalformedURLException
    //   1103	1114	726	java/net/MalformedURLException
    //   1130	1139	726	java/net/MalformedURLException
    //   1155	1163	726	java/net/MalformedURLException
    //   1179	1186	726	java/net/MalformedURLException
    //   1202	1215	726	java/net/MalformedURLException
    //   1231	1236	726	java/net/MalformedURLException
    //   1252	1256	726	java/net/MalformedURLException
    //   1272	1276	726	java/net/MalformedURLException
    //   1292	1299	726	java/net/MalformedURLException
    //   1315	1321	726	java/net/MalformedURLException
    //   1337	1374	726	java/net/MalformedURLException
    //   1390	1399	726	java/net/MalformedURLException
    //   1415	1423	726	java/net/MalformedURLException
    //   1439	1448	726	java/net/MalformedURLException
    //   1464	1475	726	java/net/MalformedURLException
    //   1491	1502	726	java/net/MalformedURLException
    //   1521	1530	726	java/net/MalformedURLException
    //   1546	1555	726	java/net/MalformedURLException
    //   1571	1579	726	java/net/MalformedURLException
    //   1595	1602	726	java/net/MalformedURLException
    //   1621	1634	726	java/net/MalformedURLException
    //   1650	1658	726	java/net/MalformedURLException
    //   1674	1681	726	java/net/MalformedURLException
    //   1697	1705	726	java/net/MalformedURLException
    //   1726	1735	726	java/net/MalformedURLException
    //   1754	1758	726	java/net/MalformedURLException
    //   1774	1779	726	java/net/MalformedURLException
    //   1795	1809	726	java/net/MalformedURLException
    //   1829	1833	726	java/net/MalformedURLException
    //   1849	1864	726	java/net/MalformedURLException
    //   146	169	792	finally
    //   185	201	792	finally
    //   217	223	792	finally
    //   239	249	792	finally
    //   265	273	792	finally
    //   289	330	792	finally
    //   346	357	792	finally
    //   373	387	792	finally
    //   403	410	792	finally
    //   426	453	792	finally
    //   469	479	792	finally
    //   495	502	792	finally
    //   518	525	792	finally
    //   546	573	792	finally
    //   589	598	792	finally
    //   614	622	792	finally
    //   638	647	792	finally
    //   667	673	792	finally
    //   689	698	792	finally
    //   714	726	792	finally
    //   731	758	792	finally
    //   762	792	792	finally
    //   837	842	792	finally
    //   850	877	792	finally
    //   881	911	792	finally
    //   929	934	792	finally
    //   942	952	792	finally
    //   956	966	792	finally
    //   984	989	792	finally
    //   1010	1015	792	finally
    //   1034	1040	792	finally
    //   1056	1063	792	finally
    //   1079	1087	792	finally
    //   1103	1114	792	finally
    //   1130	1139	792	finally
    //   1155	1163	792	finally
    //   1179	1186	792	finally
    //   1202	1215	792	finally
    //   1231	1236	792	finally
    //   1252	1256	792	finally
    //   1272	1276	792	finally
    //   1292	1299	792	finally
    //   1315	1321	792	finally
    //   1337	1374	792	finally
    //   1390	1399	792	finally
    //   1415	1423	792	finally
    //   1439	1448	792	finally
    //   1464	1475	792	finally
    //   1491	1502	792	finally
    //   1521	1530	792	finally
    //   1546	1555	792	finally
    //   1571	1579	792	finally
    //   1595	1602	792	finally
    //   1621	1634	792	finally
    //   1650	1658	792	finally
    //   1674	1681	792	finally
    //   1697	1705	792	finally
    //   1726	1735	792	finally
    //   1754	1758	792	finally
    //   1774	1779	792	finally
    //   1795	1809	792	finally
    //   1829	1833	792	finally
    //   1849	1864	792	finally
    //   289	330	819	java/lang/NoSuchMethodException
    //   146	169	845	java/net/ProtocolException
    //   185	201	845	java/net/ProtocolException
    //   217	223	845	java/net/ProtocolException
    //   239	249	845	java/net/ProtocolException
    //   265	273	845	java/net/ProtocolException
    //   289	330	845	java/net/ProtocolException
    //   346	357	845	java/net/ProtocolException
    //   373	387	845	java/net/ProtocolException
    //   403	410	845	java/net/ProtocolException
    //   426	453	845	java/net/ProtocolException
    //   469	479	845	java/net/ProtocolException
    //   495	502	845	java/net/ProtocolException
    //   518	525	845	java/net/ProtocolException
    //   546	573	845	java/net/ProtocolException
    //   589	598	845	java/net/ProtocolException
    //   614	622	845	java/net/ProtocolException
    //   638	647	845	java/net/ProtocolException
    //   667	673	845	java/net/ProtocolException
    //   689	698	845	java/net/ProtocolException
    //   714	726	845	java/net/ProtocolException
    //   837	842	845	java/net/ProtocolException
    //   929	934	845	java/net/ProtocolException
    //   984	989	845	java/net/ProtocolException
    //   1010	1015	845	java/net/ProtocolException
    //   1034	1040	845	java/net/ProtocolException
    //   1056	1063	845	java/net/ProtocolException
    //   1079	1087	845	java/net/ProtocolException
    //   1103	1114	845	java/net/ProtocolException
    //   1130	1139	845	java/net/ProtocolException
    //   1155	1163	845	java/net/ProtocolException
    //   1179	1186	845	java/net/ProtocolException
    //   1202	1215	845	java/net/ProtocolException
    //   1231	1236	845	java/net/ProtocolException
    //   1252	1256	845	java/net/ProtocolException
    //   1272	1276	845	java/net/ProtocolException
    //   1292	1299	845	java/net/ProtocolException
    //   1315	1321	845	java/net/ProtocolException
    //   1337	1374	845	java/net/ProtocolException
    //   1390	1399	845	java/net/ProtocolException
    //   1415	1423	845	java/net/ProtocolException
    //   1439	1448	845	java/net/ProtocolException
    //   1464	1475	845	java/net/ProtocolException
    //   1491	1502	845	java/net/ProtocolException
    //   1521	1530	845	java/net/ProtocolException
    //   1546	1555	845	java/net/ProtocolException
    //   1571	1579	845	java/net/ProtocolException
    //   1595	1602	845	java/net/ProtocolException
    //   1621	1634	845	java/net/ProtocolException
    //   1650	1658	845	java/net/ProtocolException
    //   1674	1681	845	java/net/ProtocolException
    //   1697	1705	845	java/net/ProtocolException
    //   1726	1735	845	java/net/ProtocolException
    //   1754	1758	845	java/net/ProtocolException
    //   1774	1779	845	java/net/ProtocolException
    //   1795	1809	845	java/net/ProtocolException
    //   1829	1833	845	java/net/ProtocolException
    //   1849	1864	845	java/net/ProtocolException
    //   289	330	911	java/lang/reflect/InvocationTargetException
    //   146	169	937	java/io/IOException
    //   185	201	937	java/io/IOException
    //   217	223	937	java/io/IOException
    //   239	249	937	java/io/IOException
    //   265	273	937	java/io/IOException
    //   289	330	937	java/io/IOException
    //   346	357	937	java/io/IOException
    //   373	387	937	java/io/IOException
    //   403	410	937	java/io/IOException
    //   426	453	937	java/io/IOException
    //   469	479	937	java/io/IOException
    //   495	502	937	java/io/IOException
    //   518	525	937	java/io/IOException
    //   546	573	937	java/io/IOException
    //   589	598	937	java/io/IOException
    //   614	622	937	java/io/IOException
    //   638	647	937	java/io/IOException
    //   667	673	937	java/io/IOException
    //   689	698	937	java/io/IOException
    //   714	726	937	java/io/IOException
    //   837	842	937	java/io/IOException
    //   929	934	937	java/io/IOException
    //   984	989	937	java/io/IOException
    //   1010	1015	937	java/io/IOException
    //   1034	1040	937	java/io/IOException
    //   1056	1063	937	java/io/IOException
    //   1079	1087	937	java/io/IOException
    //   1103	1114	937	java/io/IOException
    //   1130	1139	937	java/io/IOException
    //   1155	1163	937	java/io/IOException
    //   1179	1186	937	java/io/IOException
    //   1202	1215	937	java/io/IOException
    //   1231	1236	937	java/io/IOException
    //   1252	1256	937	java/io/IOException
    //   1272	1276	937	java/io/IOException
    //   1292	1299	937	java/io/IOException
    //   1315	1321	937	java/io/IOException
    //   1337	1374	937	java/io/IOException
    //   1390	1399	937	java/io/IOException
    //   1415	1423	937	java/io/IOException
    //   1439	1448	937	java/io/IOException
    //   1464	1475	937	java/io/IOException
    //   1491	1502	937	java/io/IOException
    //   1521	1530	937	java/io/IOException
    //   1546	1555	937	java/io/IOException
    //   1571	1579	937	java/io/IOException
    //   1595	1602	937	java/io/IOException
    //   1621	1634	937	java/io/IOException
    //   1650	1658	937	java/io/IOException
    //   1674	1681	937	java/io/IOException
    //   1697	1705	937	java/io/IOException
    //   1726	1735	937	java/io/IOException
    //   1754	1758	937	java/io/IOException
    //   1774	1779	937	java/io/IOException
    //   1795	1809	937	java/io/IOException
    //   1829	1833	937	java/io/IOException
    //   1849	1864	937	java/io/IOException
    //   289	330	966	java/lang/IllegalAccessException
    //   289	330	992	java/lang/ClassNotFoundException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsHttpClient
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */