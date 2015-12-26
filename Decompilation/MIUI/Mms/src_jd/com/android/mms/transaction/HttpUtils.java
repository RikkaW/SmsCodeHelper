package com.android.mms.transaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.http.AndroidHttpClient;
import android.util.Log;
import com.android.mms.MmsConfig;
import com.google.android.collect.Maps;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpUtils
{
  private static final String HDR_VALUE_ACCEPT_LANGUAGE = getCurrentAcceptLanguage(Locale.getDefault());
  private static final HashMap<Long, HttpRequestBase> mRequestMap = Maps.newHashMap();
  
  protected static boolean abortHttpConnection(long paramLong)
  {
    synchronized (mRequestMap)
    {
      HttpRequestBase localHttpRequestBase = (HttpRequestBase)mRequestMap.get(Long.valueOf(paramLong));
      if (localHttpRequestBase == null) {
        return false;
      }
    }
    ((HttpRequestBase)localObject).abort();
    return true;
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
  
  private static AndroidHttpClient createHttpClient(Context paramContext)
  {
    paramContext = AndroidHttpClient.newInstance(MmsConfig.getUserAgent(), paramContext);
    HttpParams localHttpParams = paramContext.getParams();
    HttpProtocolParams.setContentCharset(localHttpParams, "UTF-8");
    HttpConnectionParams.setSoTimeout(localHttpParams, MmsConfig.getHttpSocketTimeout());
    return paramContext;
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
  
  private static void handleHttpConnectionException(Exception paramException, String paramString)
    throws IOException
  {
    MyLog.e("Mms:transaction", "Url: " + paramString + "\n" + paramException.getMessage());
    paramString = new IOException(paramException.getMessage());
    paramString.initCause(paramException);
    throw paramString;
  }
  
  /* Error */
  protected static byte[] httpConnection(Context paramContext, long paramLong, String paramString1, byte[] paramArrayOfByte, int paramInt1, boolean paramBoolean, String paramString2, int paramInt2, ProgressReceiver paramProgressReceiver)
    throws IOException
  {
    // Byte code:
    //   0: aload_3
    //   1: ifnonnull +13 -> 14
    //   4: new 183	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc -69
    //   10: invokespecial 188	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aconst_null
    //   15: astore 27
    //   17: aconst_null
    //   18: astore 28
    //   20: aconst_null
    //   21: astore 29
    //   23: aconst_null
    //   24: astore 30
    //   26: aconst_null
    //   27: astore 31
    //   29: aconst_null
    //   30: astore 21
    //   32: aload 21
    //   34: astore 22
    //   36: aload 27
    //   38: astore 23
    //   40: aload 28
    //   42: astore 24
    //   44: aload 29
    //   46: astore 25
    //   48: aload 30
    //   50: astore 26
    //   52: aload 31
    //   54: astore 20
    //   56: new 190	java/net/URI
    //   59: dup
    //   60: aload_3
    //   61: invokespecial 191	java/net/URI:<init>	(Ljava/lang/String;)V
    //   64: astore 32
    //   66: aload 21
    //   68: astore 22
    //   70: aload 27
    //   72: astore 23
    //   74: aload 28
    //   76: astore 24
    //   78: aload 29
    //   80: astore 25
    //   82: aload 30
    //   84: astore 26
    //   86: aload 31
    //   88: astore 20
    //   90: new 193	org/apache/http/HttpHost
    //   93: dup
    //   94: aload 32
    //   96: invokevirtual 196	java/net/URI:getHost	()Ljava/lang/String;
    //   99: aload 32
    //   101: invokevirtual 199	java/net/URI:getPort	()I
    //   104: ldc -55
    //   106: invokespecial 204	org/apache/http/HttpHost:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   109: astore 32
    //   111: aload 21
    //   113: astore 22
    //   115: aload 27
    //   117: astore 23
    //   119: aload 28
    //   121: astore 24
    //   123: aload 29
    //   125: astore 25
    //   127: aload 30
    //   129: astore 26
    //   131: aload 31
    //   133: astore 20
    //   135: aload_0
    //   136: invokestatic 206	com/android/mms/transaction/HttpUtils:createHttpClient	(Landroid/content/Context;)Landroid/net/http/AndroidHttpClient;
    //   139: astore 21
    //   141: iload 5
    //   143: tableswitch	default:+2451->2594, 1:+136->279, 2:+1013->1156
    //   164: aload 21
    //   166: astore 22
    //   168: aload 21
    //   170: astore 23
    //   172: aload 21
    //   174: astore 24
    //   176: aload 21
    //   178: astore 25
    //   180: aload 21
    //   182: astore 26
    //   184: aload 21
    //   186: astore 20
    //   188: ldc -104
    //   190: new 66	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   197: ldc -48
    //   199: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: iload 5
    //   204: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   207: ldc -43
    //   209: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: iconst_1
    //   213: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   216: ldc -41
    //   218: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: iconst_2
    //   222: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   225: ldc -39
    //   227: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokestatic 167	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   236: aconst_null
    //   237: astore_0
    //   238: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   241: astore_3
    //   242: aload_3
    //   243: monitorenter
    //   244: lload_1
    //   245: lconst_0
    //   246: lcmp
    //   247: ifle +14 -> 261
    //   250: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   253: lload_1
    //   254: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   257: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   260: pop
    //   261: aload_3
    //   262: monitorexit
    //   263: aload_0
    //   264: astore_3
    //   265: aload 21
    //   267: ifnull +10 -> 277
    //   270: aload 21
    //   272: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   275: aload_0
    //   276: astore_3
    //   277: aload_3
    //   278: areturn
    //   279: aload 21
    //   281: astore 22
    //   283: aload 21
    //   285: astore 23
    //   287: aload 21
    //   289: astore 24
    //   291: aload 21
    //   293: astore 25
    //   295: aload 21
    //   297: astore 26
    //   299: aload 21
    //   301: astore 20
    //   303: new 225	com/android/mms/transaction/ProgressCallbackEntity
    //   306: dup
    //   307: aload 9
    //   309: aload 4
    //   311: invokespecial 228	com/android/mms/transaction/ProgressCallbackEntity:<init>	(Lcom/android/mms/transaction/ProgressReceiver;[B)V
    //   314: astore 27
    //   316: aload 21
    //   318: astore 22
    //   320: aload 21
    //   322: astore 23
    //   324: aload 21
    //   326: astore 24
    //   328: aload 21
    //   330: astore 25
    //   332: aload 21
    //   334: astore 26
    //   336: aload 21
    //   338: astore 20
    //   340: aload 27
    //   342: ldc -26
    //   344: invokevirtual 233	com/android/mms/transaction/ProgressCallbackEntity:setContentType	(Ljava/lang/String;)V
    //   347: aload 21
    //   349: astore 22
    //   351: aload 21
    //   353: astore 23
    //   355: aload 21
    //   357: astore 24
    //   359: aload 21
    //   361: astore 25
    //   363: aload 21
    //   365: astore 26
    //   367: aload 21
    //   369: astore 20
    //   371: new 235	org/apache/http/client/methods/HttpPost
    //   374: dup
    //   375: aload_3
    //   376: invokespecial 236	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   379: astore 4
    //   381: aload 21
    //   383: astore 22
    //   385: aload 21
    //   387: astore 23
    //   389: aload 21
    //   391: astore 24
    //   393: aload 21
    //   395: astore 25
    //   397: aload 21
    //   399: astore 26
    //   401: aload 21
    //   403: astore 20
    //   405: aload 4
    //   407: aload 27
    //   409: invokevirtual 240	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   412: aload 21
    //   414: astore 22
    //   416: aload 21
    //   418: astore 23
    //   420: aload 21
    //   422: astore 24
    //   424: aload 21
    //   426: astore 25
    //   428: aload 21
    //   430: astore 26
    //   432: aload 21
    //   434: astore 20
    //   436: aload 21
    //   438: invokevirtual 110	android/net/http/AndroidHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   441: astore 27
    //   443: aload 21
    //   445: astore 22
    //   447: aload 21
    //   449: astore 23
    //   451: aload 21
    //   453: astore 24
    //   455: aload 21
    //   457: astore 25
    //   459: aload 21
    //   461: astore 26
    //   463: aload 21
    //   465: astore 20
    //   467: aload 27
    //   469: iconst_0
    //   470: invokestatic 243	org/apache/http/params/HttpConnectionParams:setLinger	(Lorg/apache/http/params/HttpParams;I)V
    //   473: iload 6
    //   475: ifeq +43 -> 518
    //   478: aload 21
    //   480: astore 22
    //   482: aload 21
    //   484: astore 23
    //   486: aload 21
    //   488: astore 24
    //   490: aload 21
    //   492: astore 25
    //   494: aload 21
    //   496: astore 26
    //   498: aload 21
    //   500: astore 20
    //   502: aload 27
    //   504: new 193	org/apache/http/HttpHost
    //   507: dup
    //   508: aload 7
    //   510: iload 8
    //   512: invokespecial 246	org/apache/http/HttpHost:<init>	(Ljava/lang/String;I)V
    //   515: invokestatic 252	org/apache/http/conn/params/ConnRouteParams:setDefaultProxy	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/HttpHost;)V
    //   518: aload 21
    //   520: astore 22
    //   522: aload 21
    //   524: astore 23
    //   526: aload 21
    //   528: astore 24
    //   530: aload 21
    //   532: astore 25
    //   534: aload 21
    //   536: astore 26
    //   538: aload 21
    //   540: astore 20
    //   542: aload_0
    //   543: aload 27
    //   545: invokestatic 256	com/android/mms/transaction/HttpUtils:setRequestLinkIp	(Landroid/content/Context;Lorg/apache/http/params/HttpParams;)V
    //   548: aload 21
    //   550: astore 22
    //   552: aload 21
    //   554: astore 23
    //   556: aload 21
    //   558: astore 24
    //   560: aload 21
    //   562: astore 25
    //   564: aload 21
    //   566: astore 26
    //   568: aload 21
    //   570: astore 20
    //   572: aload 4
    //   574: aload 27
    //   576: invokevirtual 260	org/apache/http/client/methods/HttpRequestBase:setParams	(Lorg/apache/http/params/HttpParams;)V
    //   579: aload 21
    //   581: astore 22
    //   583: aload 21
    //   585: astore 23
    //   587: aload 21
    //   589: astore 24
    //   591: aload 21
    //   593: astore 25
    //   595: aload 21
    //   597: astore 26
    //   599: aload 21
    //   601: astore 20
    //   603: aload 4
    //   605: ldc_w 262
    //   608: ldc_w 264
    //   611: invokevirtual 267	org/apache/http/client/methods/HttpRequestBase:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   614: aload 21
    //   616: astore 22
    //   618: aload 21
    //   620: astore 23
    //   622: aload 21
    //   624: astore 24
    //   626: aload 21
    //   628: astore 25
    //   630: aload 21
    //   632: astore 26
    //   634: aload 21
    //   636: astore 20
    //   638: invokestatic 270	com/android/mms/MmsConfig:getUaProfTagName	()Ljava/lang/String;
    //   641: astore 7
    //   643: aload 21
    //   645: astore 22
    //   647: aload 21
    //   649: astore 23
    //   651: aload 21
    //   653: astore 24
    //   655: aload 21
    //   657: astore 25
    //   659: aload 21
    //   661: astore 26
    //   663: aload 21
    //   665: astore 20
    //   667: invokestatic 273	com/android/mms/MmsConfig:getUaProfUrl	()Ljava/lang/String;
    //   670: astore 27
    //   672: aload 27
    //   674: ifnull +36 -> 710
    //   677: aload 21
    //   679: astore 22
    //   681: aload 21
    //   683: astore 23
    //   685: aload 21
    //   687: astore 24
    //   689: aload 21
    //   691: astore 25
    //   693: aload 21
    //   695: astore 26
    //   697: aload 21
    //   699: astore 20
    //   701: aload 4
    //   703: aload 7
    //   705: aload 27
    //   707: invokevirtual 267	org/apache/http/client/methods/HttpRequestBase:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   710: aload 21
    //   712: astore 22
    //   714: aload 21
    //   716: astore 23
    //   718: aload 21
    //   720: astore 24
    //   722: aload 21
    //   724: astore 25
    //   726: aload 21
    //   728: astore 26
    //   730: aload 21
    //   732: astore 20
    //   734: invokestatic 276	com/android/mms/MmsConfig:getHttpParams	()Ljava/lang/String;
    //   737: astore 7
    //   739: aload 7
    //   741: ifnull +457 -> 1198
    //   744: aload 21
    //   746: astore 22
    //   748: aload 21
    //   750: astore 23
    //   752: aload 21
    //   754: astore 24
    //   756: aload 21
    //   758: astore 25
    //   760: aload 21
    //   762: astore 26
    //   764: aload 21
    //   766: astore 20
    //   768: aload_0
    //   769: ldc_w 278
    //   772: invokevirtual 284	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   775: checkcast 286	android/telephony/TelephonyManager
    //   778: invokevirtual 289	android/telephony/TelephonyManager:getLine1Number	()Ljava/lang/String;
    //   781: astore 27
    //   783: aload 21
    //   785: astore 22
    //   787: aload 21
    //   789: astore 23
    //   791: aload 21
    //   793: astore 24
    //   795: aload 21
    //   797: astore 25
    //   799: aload 21
    //   801: astore 26
    //   803: aload 21
    //   805: astore 20
    //   807: invokestatic 292	com/android/mms/MmsConfig:getHttpParamsLine1Key	()Ljava/lang/String;
    //   810: astore 28
    //   812: aload 21
    //   814: astore 22
    //   816: aload 21
    //   818: astore 23
    //   820: aload 21
    //   822: astore 24
    //   824: aload 21
    //   826: astore 25
    //   828: aload 21
    //   830: astore 26
    //   832: aload 21
    //   834: astore 20
    //   836: aload 7
    //   838: ldc_w 294
    //   841: invokevirtual 298	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   844: astore 29
    //   846: aload 21
    //   848: astore 22
    //   850: aload 21
    //   852: astore 23
    //   854: aload 21
    //   856: astore 24
    //   858: aload 21
    //   860: astore 25
    //   862: aload 21
    //   864: astore 26
    //   866: aload 21
    //   868: astore 20
    //   870: aload 29
    //   872: arraylength
    //   873: istore 10
    //   875: iconst_0
    //   876: istore 8
    //   878: iload 8
    //   880: iload 10
    //   882: if_icmpge +316 -> 1198
    //   885: aload 21
    //   887: astore 22
    //   889: aload 21
    //   891: astore 23
    //   893: aload 21
    //   895: astore 24
    //   897: aload 21
    //   899: astore 25
    //   901: aload 21
    //   903: astore 26
    //   905: aload 21
    //   907: astore 20
    //   909: aload 29
    //   911: iload 8
    //   913: aaload
    //   914: ldc_w 300
    //   917: iconst_2
    //   918: invokevirtual 303	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   921: astore_0
    //   922: aload 21
    //   924: astore 22
    //   926: aload 21
    //   928: astore 23
    //   930: aload 21
    //   932: astore 24
    //   934: aload 21
    //   936: astore 25
    //   938: aload 21
    //   940: astore 26
    //   942: aload 21
    //   944: astore 20
    //   946: aload_0
    //   947: arraylength
    //   948: iconst_2
    //   949: if_icmpne +1648 -> 2597
    //   952: aload 21
    //   954: astore 22
    //   956: aload 21
    //   958: astore 23
    //   960: aload 21
    //   962: astore 24
    //   964: aload 21
    //   966: astore 25
    //   968: aload 21
    //   970: astore 26
    //   972: aload 21
    //   974: astore 20
    //   976: aload_0
    //   977: iconst_0
    //   978: aaload
    //   979: invokevirtual 306	java/lang/String:trim	()Ljava/lang/String;
    //   982: astore 30
    //   984: aload 21
    //   986: astore 22
    //   988: aload 21
    //   990: astore 23
    //   992: aload 21
    //   994: astore 24
    //   996: aload 21
    //   998: astore 25
    //   1000: aload 21
    //   1002: astore 26
    //   1004: aload 21
    //   1006: astore 20
    //   1008: aload_0
    //   1009: iconst_1
    //   1010: aaload
    //   1011: invokevirtual 306	java/lang/String:trim	()Ljava/lang/String;
    //   1014: astore 7
    //   1016: aload 7
    //   1018: astore_0
    //   1019: aload 28
    //   1021: ifnull +37 -> 1058
    //   1024: aload 21
    //   1026: astore 22
    //   1028: aload 21
    //   1030: astore 23
    //   1032: aload 21
    //   1034: astore 24
    //   1036: aload 21
    //   1038: astore 25
    //   1040: aload 21
    //   1042: astore 26
    //   1044: aload 21
    //   1046: astore 20
    //   1048: aload 7
    //   1050: aload 28
    //   1052: aload 27
    //   1054: invokevirtual 310	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   1057: astore_0
    //   1058: aload 21
    //   1060: astore 22
    //   1062: aload 21
    //   1064: astore 23
    //   1066: aload 21
    //   1068: astore 24
    //   1070: aload 21
    //   1072: astore 25
    //   1074: aload 21
    //   1076: astore 26
    //   1078: aload 21
    //   1080: astore 20
    //   1082: aload 30
    //   1084: invokestatic 316	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1087: ifne +1510 -> 2597
    //   1090: aload 21
    //   1092: astore 22
    //   1094: aload 21
    //   1096: astore 23
    //   1098: aload 21
    //   1100: astore 24
    //   1102: aload 21
    //   1104: astore 25
    //   1106: aload 21
    //   1108: astore 26
    //   1110: aload 21
    //   1112: astore 20
    //   1114: aload_0
    //   1115: invokestatic 316	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1118: ifne +1479 -> 2597
    //   1121: aload 21
    //   1123: astore 22
    //   1125: aload 21
    //   1127: astore 23
    //   1129: aload 21
    //   1131: astore 24
    //   1133: aload 21
    //   1135: astore 25
    //   1137: aload 21
    //   1139: astore 26
    //   1141: aload 21
    //   1143: astore 20
    //   1145: aload 4
    //   1147: aload 30
    //   1149: aload_0
    //   1150: invokevirtual 267	org/apache/http/client/methods/HttpRequestBase:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   1153: goto +1444 -> 2597
    //   1156: aload 21
    //   1158: astore 22
    //   1160: aload 21
    //   1162: astore 23
    //   1164: aload 21
    //   1166: astore 24
    //   1168: aload 21
    //   1170: astore 25
    //   1172: aload 21
    //   1174: astore 26
    //   1176: aload 21
    //   1178: astore 20
    //   1180: new 318	org/apache/http/client/methods/HttpGet
    //   1183: dup
    //   1184: aload_3
    //   1185: invokespecial 319	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   1188: astore 4
    //   1190: goto -778 -> 412
    //   1193: astore_0
    //   1194: aload_3
    //   1195: monitorexit
    //   1196: aload_0
    //   1197: athrow
    //   1198: aload 21
    //   1200: astore 22
    //   1202: aload 21
    //   1204: astore 23
    //   1206: aload 21
    //   1208: astore 24
    //   1210: aload 21
    //   1212: astore 25
    //   1214: aload 21
    //   1216: astore 26
    //   1218: aload 21
    //   1220: astore 20
    //   1222: aload 4
    //   1224: ldc_w 321
    //   1227: getstatic 23	com/android/mms/transaction/HttpUtils:HDR_VALUE_ACCEPT_LANGUAGE	Ljava/lang/String;
    //   1230: invokevirtual 267	org/apache/http/client/methods/HttpRequestBase:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   1233: aload 21
    //   1235: astore 22
    //   1237: aload 21
    //   1239: astore 23
    //   1241: aload 21
    //   1243: astore 24
    //   1245: aload 21
    //   1247: astore 25
    //   1249: aload 21
    //   1251: astore 26
    //   1253: aload 21
    //   1255: astore 20
    //   1257: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1260: astore_0
    //   1261: aload 21
    //   1263: astore 22
    //   1265: aload 21
    //   1267: astore 23
    //   1269: aload 21
    //   1271: astore 24
    //   1273: aload 21
    //   1275: astore 25
    //   1277: aload 21
    //   1279: astore 26
    //   1281: aload 21
    //   1283: astore 20
    //   1285: aload_0
    //   1286: monitorenter
    //   1287: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1290: lload_1
    //   1291: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1294: aload 4
    //   1296: invokevirtual 325	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1299: pop
    //   1300: aload_0
    //   1301: monitorexit
    //   1302: aload 21
    //   1304: astore 22
    //   1306: aload 21
    //   1308: astore 23
    //   1310: aload 21
    //   1312: astore 24
    //   1314: aload 21
    //   1316: astore 25
    //   1318: aload 21
    //   1320: astore 26
    //   1322: aload 21
    //   1324: astore 20
    //   1326: aload 21
    //   1328: aload 32
    //   1330: aload 4
    //   1332: invokevirtual 329	android/net/http/AndroidHttpClient:execute	(Lorg/apache/http/HttpHost;Lorg/apache/http/HttpRequest;)Lorg/apache/http/HttpResponse;
    //   1335: astore_0
    //   1336: aload 21
    //   1338: astore 22
    //   1340: aload 21
    //   1342: astore 23
    //   1344: aload 21
    //   1346: astore 24
    //   1348: aload 21
    //   1350: astore 25
    //   1352: aload 21
    //   1354: astore 26
    //   1356: aload 21
    //   1358: astore 20
    //   1360: aload_0
    //   1361: invokeinterface 335 1 0
    //   1366: astore 4
    //   1368: aload 21
    //   1370: astore 22
    //   1372: aload 21
    //   1374: astore 23
    //   1376: aload 21
    //   1378: astore 24
    //   1380: aload 21
    //   1382: astore 25
    //   1384: aload 21
    //   1386: astore 26
    //   1388: aload 21
    //   1390: astore 20
    //   1392: aload 4
    //   1394: invokeinterface 340 1 0
    //   1399: sipush 200
    //   1402: if_icmpeq +187 -> 1589
    //   1405: aload 21
    //   1407: astore 22
    //   1409: aload 21
    //   1411: astore 23
    //   1413: aload 21
    //   1415: astore 24
    //   1417: aload 21
    //   1419: astore 25
    //   1421: aload 21
    //   1423: astore 26
    //   1425: aload 21
    //   1427: astore 20
    //   1429: new 150	java/io/IOException
    //   1432: dup
    //   1433: new 66	java/lang/StringBuilder
    //   1436: dup
    //   1437: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   1440: ldc_w 342
    //   1443: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1446: aload 4
    //   1448: invokeinterface 345 1 0
    //   1453: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1456: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1459: invokespecial 170	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   1462: athrow
    //   1463: astore_0
    //   1464: aload 22
    //   1466: astore 20
    //   1468: aload_0
    //   1469: aload_3
    //   1470: invokestatic 347	com/android/mms/transaction/HttpUtils:handleHttpConnectionException	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   1473: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1476: astore_0
    //   1477: aload_0
    //   1478: monitorenter
    //   1479: lload_1
    //   1480: lconst_0
    //   1481: lcmp
    //   1482: ifle +14 -> 1496
    //   1485: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1488: lload_1
    //   1489: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1492: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1495: pop
    //   1496: aload_0
    //   1497: monitorexit
    //   1498: aload 22
    //   1500: ifnull +8 -> 1508
    //   1503: aload 22
    //   1505: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   1508: aconst_null
    //   1509: areturn
    //   1510: astore 4
    //   1512: aload_0
    //   1513: monitorexit
    //   1514: aload 21
    //   1516: astore 22
    //   1518: aload 21
    //   1520: astore 23
    //   1522: aload 21
    //   1524: astore 24
    //   1526: aload 21
    //   1528: astore 25
    //   1530: aload 21
    //   1532: astore 26
    //   1534: aload 21
    //   1536: astore 20
    //   1538: aload 4
    //   1540: athrow
    //   1541: astore_0
    //   1542: aload 23
    //   1544: astore 20
    //   1546: aload_0
    //   1547: aload_3
    //   1548: invokestatic 347	com/android/mms/transaction/HttpUtils:handleHttpConnectionException	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   1551: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1554: astore_0
    //   1555: aload_0
    //   1556: monitorenter
    //   1557: lload_1
    //   1558: lconst_0
    //   1559: lcmp
    //   1560: ifle +14 -> 1574
    //   1563: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   1566: lload_1
    //   1567: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1570: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1573: pop
    //   1574: aload_0
    //   1575: monitorexit
    //   1576: aload 23
    //   1578: ifnull -70 -> 1508
    //   1581: aload 23
    //   1583: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   1586: goto -78 -> 1508
    //   1589: aload 21
    //   1591: astore 22
    //   1593: aload 21
    //   1595: astore 23
    //   1597: aload 21
    //   1599: astore 24
    //   1601: aload 21
    //   1603: astore 25
    //   1605: aload 21
    //   1607: astore 26
    //   1609: aload 21
    //   1611: astore 20
    //   1613: aload_0
    //   1614: invokeinterface 351 1 0
    //   1619: astore 7
    //   1621: aconst_null
    //   1622: astore_0
    //   1623: aconst_null
    //   1624: astore 4
    //   1626: aload 7
    //   1628: ifnull +538 -> 2166
    //   1631: aload 4
    //   1633: astore_0
    //   1634: aload 7
    //   1636: invokeinterface 357 1 0
    //   1641: lconst_0
    //   1642: lcmp
    //   1643: ifle +182 -> 1825
    //   1646: aload 7
    //   1648: invokeinterface 357 1 0
    //   1653: l2i
    //   1654: newarray <illegal type>
    //   1656: astore_0
    //   1657: new 359	java/io/DataInputStream
    //   1660: dup
    //   1661: aload 7
    //   1663: invokeinterface 363 1 0
    //   1668: invokespecial 366	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   1671: astore 4
    //   1673: aload_0
    //   1674: arraylength
    //   1675: istore 11
    //   1677: iconst_0
    //   1678: istore 12
    //   1680: iload 11
    //   1682: istore 8
    //   1684: iload 12
    //   1686: istore 10
    //   1688: aload 9
    //   1690: ifnull +38 -> 1728
    //   1693: iload 11
    //   1695: istore 8
    //   1697: iload 12
    //   1699: istore 10
    //   1701: iload 5
    //   1703: iconst_2
    //   1704: if_icmpne +24 -> 1728
    //   1707: aload 9
    //   1709: ldc2_w 367
    //   1712: aload_0
    //   1713: arraylength
    //   1714: i2l
    //   1715: invokeinterface 374 5 0
    //   1720: iload 12
    //   1722: istore 10
    //   1724: iload 11
    //   1726: istore 8
    //   1728: aload 9
    //   1730: ifnull +26 -> 1756
    //   1733: iload 5
    //   1735: iconst_2
    //   1736: if_icmpne +20 -> 1756
    //   1739: iload 10
    //   1741: i2l
    //   1742: lstore 16
    //   1744: aload 9
    //   1746: lload 16
    //   1748: aload_0
    //   1749: arraylength
    //   1750: i2l
    //   1751: invokeinterface 374 5 0
    //   1756: aload 4
    //   1758: aload_0
    //   1759: iload 10
    //   1761: iload 8
    //   1763: invokevirtual 378	java/io/DataInputStream:read	([BII)I
    //   1766: istore 12
    //   1768: iload 12
    //   1770: ifle +588 -> 2358
    //   1773: iload 8
    //   1775: iload 12
    //   1777: isub
    //   1778: istore 11
    //   1780: iload 10
    //   1782: iload 12
    //   1784: iadd
    //   1785: istore 10
    //   1787: iload 11
    //   1789: istore 8
    //   1791: iload 11
    //   1793: ifgt -65 -> 1728
    //   1796: aload 9
    //   1798: ifnull +22 -> 1820
    //   1801: iload 5
    //   1803: iconst_2
    //   1804: if_icmpne +16 -> 1820
    //   1807: aload 9
    //   1809: aload_0
    //   1810: arraylength
    //   1811: i2l
    //   1812: aload_0
    //   1813: arraylength
    //   1814: i2l
    //   1815: invokeinterface 374 5 0
    //   1820: aload 4
    //   1822: invokestatic 383	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   1825: aload_0
    //   1826: astore 4
    //   1828: aload 7
    //   1830: invokeinterface 387 1 0
    //   1835: ifeq +289 -> 2124
    //   1838: ldc -104
    //   1840: ldc_w 389
    //   1843: invokestatic 392	com/xiaomi/mms/utils/logger/MyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   1846: invokestatic 395	com/android/mms/MmsConfig:getMaxMessageSize	()I
    //   1849: istore 14
    //   1851: iload 14
    //   1853: newarray <illegal type>
    //   1855: astore 20
    //   1857: new 359	java/io/DataInputStream
    //   1860: dup
    //   1861: aload 7
    //   1863: invokeinterface 363 1 0
    //   1868: invokespecial 366	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   1871: astore 4
    //   1873: aload 9
    //   1875: ifnull +22 -> 1897
    //   1878: iload 5
    //   1880: iconst_2
    //   1881: if_icmpne +16 -> 1897
    //   1884: aload 9
    //   1886: ldc2_w 367
    //   1889: ldc2_w 367
    //   1892: invokeinterface 374 5 0
    //   1897: iconst_0
    //   1898: istore 12
    //   1900: iconst_0
    //   1901: istore 8
    //   1903: iconst_0
    //   1904: istore 15
    //   1906: aload 9
    //   1908: ifnull +24 -> 1932
    //   1911: iload 5
    //   1913: iconst_2
    //   1914: if_icmpne +18 -> 1932
    //   1917: iload 8
    //   1919: i2l
    //   1920: lstore 16
    //   1922: aload 9
    //   1924: lload 16
    //   1926: lconst_0
    //   1927: invokeinterface 374 5 0
    //   1932: aload 4
    //   1934: aload 20
    //   1936: iload 8
    //   1938: iload 14
    //   1940: invokevirtual 378	java/io/DataInputStream:read	([BII)I
    //   1943: istore 10
    //   1945: iload 14
    //   1947: istore 13
    //   1949: iload 8
    //   1951: istore 11
    //   1953: iload 10
    //   1955: ifle +17 -> 1972
    //   1958: iload 14
    //   1960: iload 10
    //   1962: isub
    //   1963: istore 13
    //   1965: iload 8
    //   1967: iload 10
    //   1969: iadd
    //   1970: istore 11
    //   1972: iload 10
    //   1974: istore 12
    //   1976: iload 11
    //   1978: istore 8
    //   1980: iload 15
    //   1982: istore 14
    //   1984: iload 10
    //   1986: iflt +32 -> 2018
    //   1989: iload 10
    //   1991: istore 12
    //   1993: iload 13
    //   1995: istore 14
    //   1997: iload 11
    //   1999: istore 8
    //   2001: iload 13
    //   2003: ifgt -97 -> 1906
    //   2006: iload 15
    //   2008: istore 14
    //   2010: iload 11
    //   2012: istore 8
    //   2014: iload 10
    //   2016: istore 12
    //   2018: aload 9
    //   2020: ifnull +30 -> 2050
    //   2023: iload 5
    //   2025: iconst_2
    //   2026: if_icmpne +24 -> 2050
    //   2029: iload 8
    //   2031: i2l
    //   2032: lstore 16
    //   2034: iload 8
    //   2036: i2l
    //   2037: lstore 18
    //   2039: aload 9
    //   2041: lload 16
    //   2043: lload 18
    //   2045: invokeinterface 374 5 0
    //   2050: iload 12
    //   2052: iconst_m1
    //   2053: if_icmpne +361 -> 2414
    //   2056: iload 8
    //   2058: ifle +356 -> 2414
    //   2061: iload 14
    //   2063: ifne +351 -> 2414
    //   2066: iload 8
    //   2068: newarray <illegal type>
    //   2070: astore_0
    //   2071: aload 20
    //   2073: iconst_0
    //   2074: aload_0
    //   2075: iconst_0
    //   2076: iload 8
    //   2078: invokestatic 401	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   2081: ldc -104
    //   2083: new 66	java/lang/StringBuilder
    //   2086: dup
    //   2087: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   2090: ldc_w 403
    //   2093: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2096: iload 8
    //   2098: invokestatic 408	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   2101: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2104: ldc_w 410
    //   2107: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2110: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2113: invokestatic 392	com/xiaomi/mms/utils/logger/MyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   2116: aload 4
    //   2118: invokestatic 383	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   2121: aload_0
    //   2122: astore 4
    //   2124: aload 4
    //   2126: astore_0
    //   2127: aload 7
    //   2129: ifnull +37 -> 2166
    //   2132: aload 21
    //   2134: astore 22
    //   2136: aload 21
    //   2138: astore 23
    //   2140: aload 21
    //   2142: astore 24
    //   2144: aload 21
    //   2146: astore 25
    //   2148: aload 21
    //   2150: astore 26
    //   2152: aload 21
    //   2154: astore 20
    //   2156: aload 7
    //   2158: invokeinterface 413 1 0
    //   2163: aload 4
    //   2165: astore_0
    //   2166: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2169: astore_3
    //   2170: aload_3
    //   2171: monitorenter
    //   2172: lload_1
    //   2173: lconst_0
    //   2174: lcmp
    //   2175: ifle +14 -> 2189
    //   2178: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2181: lload_1
    //   2182: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2185: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2188: pop
    //   2189: aload_3
    //   2190: monitorexit
    //   2191: aload_0
    //   2192: astore_3
    //   2193: aload 21
    //   2195: ifnull -1918 -> 277
    //   2198: aload 21
    //   2200: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   2203: aload_0
    //   2204: areturn
    //   2205: astore 20
    //   2207: ldc -104
    //   2209: new 66	java/lang/StringBuilder
    //   2212: dup
    //   2213: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   2216: ldc_w 415
    //   2219: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2222: aload 20
    //   2224: invokevirtual 416	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2227: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2230: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2233: invokestatic 167	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2236: goto -440 -> 1796
    //   2239: astore_0
    //   2240: aload 4
    //   2242: invokestatic 383	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   2245: aload_0
    //   2246: athrow
    //   2247: astore_0
    //   2248: aload 7
    //   2250: ifnull +34 -> 2284
    //   2253: aload 21
    //   2255: astore 22
    //   2257: aload 21
    //   2259: astore 23
    //   2261: aload 21
    //   2263: astore 24
    //   2265: aload 21
    //   2267: astore 25
    //   2269: aload 21
    //   2271: astore 26
    //   2273: aload 21
    //   2275: astore 20
    //   2277: aload 7
    //   2279: invokeinterface 413 1 0
    //   2284: aload 21
    //   2286: astore 22
    //   2288: aload 21
    //   2290: astore 23
    //   2292: aload 21
    //   2294: astore 24
    //   2296: aload 21
    //   2298: astore 25
    //   2300: aload 21
    //   2302: astore 26
    //   2304: aload 21
    //   2306: astore 20
    //   2308: aload_0
    //   2309: athrow
    //   2310: astore_0
    //   2311: aload 24
    //   2313: astore 20
    //   2315: aload_0
    //   2316: aload_3
    //   2317: invokestatic 347	com/android/mms/transaction/HttpUtils:handleHttpConnectionException	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   2320: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2323: astore_0
    //   2324: aload_0
    //   2325: monitorenter
    //   2326: lload_1
    //   2327: lconst_0
    //   2328: lcmp
    //   2329: ifle +14 -> 2343
    //   2332: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2335: lload_1
    //   2336: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2339: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2342: pop
    //   2343: aload_0
    //   2344: monitorexit
    //   2345: aload 24
    //   2347: ifnull -839 -> 1508
    //   2350: aload 24
    //   2352: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   2355: goto -847 -> 1508
    //   2358: ldc -104
    //   2360: ldc_w 418
    //   2363: invokestatic 167	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2366: goto -570 -> 1796
    //   2369: astore 22
    //   2371: iconst_1
    //   2372: istore 14
    //   2374: ldc -104
    //   2376: new 66	java/lang/StringBuilder
    //   2379: dup
    //   2380: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   2383: ldc_w 415
    //   2386: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2389: aload 22
    //   2391: invokevirtual 416	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2394: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2397: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2400: invokestatic 167	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2403: goto -385 -> 2018
    //   2406: astore_0
    //   2407: aload 4
    //   2409: invokestatic 383	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   2412: aload_0
    //   2413: athrow
    //   2414: ldc -104
    //   2416: ldc_w 420
    //   2419: invokestatic 167	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2422: goto -306 -> 2116
    //   2425: astore_0
    //   2426: aload_3
    //   2427: monitorexit
    //   2428: aload_0
    //   2429: athrow
    //   2430: astore_3
    //   2431: aload_0
    //   2432: monitorexit
    //   2433: aload_3
    //   2434: athrow
    //   2435: astore_3
    //   2436: aload_0
    //   2437: monitorexit
    //   2438: aload_3
    //   2439: athrow
    //   2440: astore_3
    //   2441: aload_0
    //   2442: monitorexit
    //   2443: aload_3
    //   2444: athrow
    //   2445: astore_0
    //   2446: aload 25
    //   2448: astore 20
    //   2450: aload_0
    //   2451: aload_3
    //   2452: invokestatic 347	com/android/mms/transaction/HttpUtils:handleHttpConnectionException	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   2455: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2458: astore_0
    //   2459: aload_0
    //   2460: monitorenter
    //   2461: lload_1
    //   2462: lconst_0
    //   2463: lcmp
    //   2464: ifle +14 -> 2478
    //   2467: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2470: lload_1
    //   2471: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2474: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2477: pop
    //   2478: aload_0
    //   2479: monitorexit
    //   2480: aload 25
    //   2482: ifnull -974 -> 1508
    //   2485: aload 25
    //   2487: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   2490: goto -982 -> 1508
    //   2493: astore_3
    //   2494: aload_0
    //   2495: monitorexit
    //   2496: aload_3
    //   2497: athrow
    //   2498: astore_0
    //   2499: aload 26
    //   2501: astore 20
    //   2503: aload_0
    //   2504: aload_3
    //   2505: invokestatic 347	com/android/mms/transaction/HttpUtils:handleHttpConnectionException	(Ljava/lang/Exception;Ljava/lang/String;)V
    //   2508: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2511: astore_0
    //   2512: aload_0
    //   2513: monitorenter
    //   2514: lload_1
    //   2515: lconst_0
    //   2516: lcmp
    //   2517: ifle +14 -> 2531
    //   2520: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2523: lload_1
    //   2524: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2527: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2530: pop
    //   2531: aload_0
    //   2532: monitorexit
    //   2533: aload 26
    //   2535: ifnull -1027 -> 1508
    //   2538: aload 26
    //   2540: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   2543: goto -1035 -> 1508
    //   2546: astore_3
    //   2547: aload_0
    //   2548: monitorexit
    //   2549: aload_3
    //   2550: athrow
    //   2551: astore_3
    //   2552: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2555: astore_0
    //   2556: aload_0
    //   2557: monitorenter
    //   2558: lload_1
    //   2559: lconst_0
    //   2560: lcmp
    //   2561: ifle +14 -> 2575
    //   2564: getstatic 31	com/android/mms/transaction/HttpUtils:mRequestMap	Ljava/util/HashMap;
    //   2567: lload_1
    //   2568: invokestatic 43	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   2571: invokevirtual 220	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2574: pop
    //   2575: aload_0
    //   2576: monitorexit
    //   2577: aload 20
    //   2579: ifnull +8 -> 2587
    //   2582: aload 20
    //   2584: invokevirtual 223	android/net/http/AndroidHttpClient:close	()V
    //   2587: aload_3
    //   2588: athrow
    //   2589: astore_3
    //   2590: aload_0
    //   2591: monitorexit
    //   2592: aload_3
    //   2593: athrow
    //   2594: goto -2430 -> 164
    //   2597: iload 8
    //   2599: iconst_1
    //   2600: iadd
    //   2601: istore 8
    //   2603: goto -1725 -> 878
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2606	0	paramContext	Context
    //   0	2606	1	paramLong	long
    //   0	2606	3	paramString1	String
    //   0	2606	4	paramArrayOfByte	byte[]
    //   0	2606	5	paramInt1	int
    //   0	2606	6	paramBoolean	boolean
    //   0	2606	7	paramString2	String
    //   0	2606	8	paramInt2	int
    //   0	2606	9	paramProgressReceiver	ProgressReceiver
    //   873	1142	10	i	int
    //   1675	336	11	j	int
    //   1678	376	12	k	int
    //   1947	55	13	m	int
    //   1849	524	14	n	int
    //   1904	103	15	i1	int
    //   1742	300	16	l1	long
    //   2037	7	18	l2	long
    //   54	2101	20	localObject1	Object
    //   2205	18	20	localIOException1	IOException
    //   2275	308	20	localObject2	Object
    //   30	2275	21	localAndroidHttpClient1	AndroidHttpClient
    //   34	2253	22	localAndroidHttpClient2	AndroidHttpClient
    //   2369	21	22	localIOException2	IOException
    //   38	2253	23	localObject3	Object
    //   42	2309	24	localObject4	Object
    //   46	2440	25	localObject5	Object
    //   50	2489	26	localObject6	Object
    //   15	1038	27	localObject7	Object
    //   18	1033	28	str1	String
    //   21	889	29	arrayOfString	String[]
    //   24	1124	30	str2	String
    //   27	105	31	localObject8	Object
    //   64	1265	32	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   250	261	1193	finally
    //   261	263	1193	finally
    //   1194	1196	1193	finally
    //   56	66	1463	java/net/URISyntaxException
    //   90	111	1463	java/net/URISyntaxException
    //   135	141	1463	java/net/URISyntaxException
    //   188	236	1463	java/net/URISyntaxException
    //   303	316	1463	java/net/URISyntaxException
    //   340	347	1463	java/net/URISyntaxException
    //   371	381	1463	java/net/URISyntaxException
    //   405	412	1463	java/net/URISyntaxException
    //   436	443	1463	java/net/URISyntaxException
    //   467	473	1463	java/net/URISyntaxException
    //   502	518	1463	java/net/URISyntaxException
    //   542	548	1463	java/net/URISyntaxException
    //   572	579	1463	java/net/URISyntaxException
    //   603	614	1463	java/net/URISyntaxException
    //   638	643	1463	java/net/URISyntaxException
    //   667	672	1463	java/net/URISyntaxException
    //   701	710	1463	java/net/URISyntaxException
    //   734	739	1463	java/net/URISyntaxException
    //   768	783	1463	java/net/URISyntaxException
    //   807	812	1463	java/net/URISyntaxException
    //   836	846	1463	java/net/URISyntaxException
    //   870	875	1463	java/net/URISyntaxException
    //   909	922	1463	java/net/URISyntaxException
    //   946	952	1463	java/net/URISyntaxException
    //   976	984	1463	java/net/URISyntaxException
    //   1008	1016	1463	java/net/URISyntaxException
    //   1048	1058	1463	java/net/URISyntaxException
    //   1082	1090	1463	java/net/URISyntaxException
    //   1114	1121	1463	java/net/URISyntaxException
    //   1145	1153	1463	java/net/URISyntaxException
    //   1180	1190	1463	java/net/URISyntaxException
    //   1222	1233	1463	java/net/URISyntaxException
    //   1257	1261	1463	java/net/URISyntaxException
    //   1285	1287	1463	java/net/URISyntaxException
    //   1326	1336	1463	java/net/URISyntaxException
    //   1360	1368	1463	java/net/URISyntaxException
    //   1392	1405	1463	java/net/URISyntaxException
    //   1429	1463	1463	java/net/URISyntaxException
    //   1538	1541	1463	java/net/URISyntaxException
    //   1613	1621	1463	java/net/URISyntaxException
    //   2156	2163	1463	java/net/URISyntaxException
    //   2277	2284	1463	java/net/URISyntaxException
    //   2308	2310	1463	java/net/URISyntaxException
    //   1287	1302	1510	finally
    //   1512	1514	1510	finally
    //   56	66	1541	java/lang/IllegalStateException
    //   90	111	1541	java/lang/IllegalStateException
    //   135	141	1541	java/lang/IllegalStateException
    //   188	236	1541	java/lang/IllegalStateException
    //   303	316	1541	java/lang/IllegalStateException
    //   340	347	1541	java/lang/IllegalStateException
    //   371	381	1541	java/lang/IllegalStateException
    //   405	412	1541	java/lang/IllegalStateException
    //   436	443	1541	java/lang/IllegalStateException
    //   467	473	1541	java/lang/IllegalStateException
    //   502	518	1541	java/lang/IllegalStateException
    //   542	548	1541	java/lang/IllegalStateException
    //   572	579	1541	java/lang/IllegalStateException
    //   603	614	1541	java/lang/IllegalStateException
    //   638	643	1541	java/lang/IllegalStateException
    //   667	672	1541	java/lang/IllegalStateException
    //   701	710	1541	java/lang/IllegalStateException
    //   734	739	1541	java/lang/IllegalStateException
    //   768	783	1541	java/lang/IllegalStateException
    //   807	812	1541	java/lang/IllegalStateException
    //   836	846	1541	java/lang/IllegalStateException
    //   870	875	1541	java/lang/IllegalStateException
    //   909	922	1541	java/lang/IllegalStateException
    //   946	952	1541	java/lang/IllegalStateException
    //   976	984	1541	java/lang/IllegalStateException
    //   1008	1016	1541	java/lang/IllegalStateException
    //   1048	1058	1541	java/lang/IllegalStateException
    //   1082	1090	1541	java/lang/IllegalStateException
    //   1114	1121	1541	java/lang/IllegalStateException
    //   1145	1153	1541	java/lang/IllegalStateException
    //   1180	1190	1541	java/lang/IllegalStateException
    //   1222	1233	1541	java/lang/IllegalStateException
    //   1257	1261	1541	java/lang/IllegalStateException
    //   1285	1287	1541	java/lang/IllegalStateException
    //   1326	1336	1541	java/lang/IllegalStateException
    //   1360	1368	1541	java/lang/IllegalStateException
    //   1392	1405	1541	java/lang/IllegalStateException
    //   1429	1463	1541	java/lang/IllegalStateException
    //   1538	1541	1541	java/lang/IllegalStateException
    //   1613	1621	1541	java/lang/IllegalStateException
    //   2156	2163	1541	java/lang/IllegalStateException
    //   2277	2284	1541	java/lang/IllegalStateException
    //   2308	2310	1541	java/lang/IllegalStateException
    //   1744	1756	2205	java/io/IOException
    //   1756	1768	2205	java/io/IOException
    //   1673	1677	2239	finally
    //   1707	1720	2239	finally
    //   1744	1756	2239	finally
    //   1756	1768	2239	finally
    //   1807	1820	2239	finally
    //   2207	2236	2239	finally
    //   2358	2366	2239	finally
    //   1634	1673	2247	finally
    //   1820	1825	2247	finally
    //   1828	1873	2247	finally
    //   1884	1897	2247	finally
    //   2116	2121	2247	finally
    //   2240	2247	2247	finally
    //   2407	2414	2247	finally
    //   56	66	2310	java/lang/IllegalArgumentException
    //   90	111	2310	java/lang/IllegalArgumentException
    //   135	141	2310	java/lang/IllegalArgumentException
    //   188	236	2310	java/lang/IllegalArgumentException
    //   303	316	2310	java/lang/IllegalArgumentException
    //   340	347	2310	java/lang/IllegalArgumentException
    //   371	381	2310	java/lang/IllegalArgumentException
    //   405	412	2310	java/lang/IllegalArgumentException
    //   436	443	2310	java/lang/IllegalArgumentException
    //   467	473	2310	java/lang/IllegalArgumentException
    //   502	518	2310	java/lang/IllegalArgumentException
    //   542	548	2310	java/lang/IllegalArgumentException
    //   572	579	2310	java/lang/IllegalArgumentException
    //   603	614	2310	java/lang/IllegalArgumentException
    //   638	643	2310	java/lang/IllegalArgumentException
    //   667	672	2310	java/lang/IllegalArgumentException
    //   701	710	2310	java/lang/IllegalArgumentException
    //   734	739	2310	java/lang/IllegalArgumentException
    //   768	783	2310	java/lang/IllegalArgumentException
    //   807	812	2310	java/lang/IllegalArgumentException
    //   836	846	2310	java/lang/IllegalArgumentException
    //   870	875	2310	java/lang/IllegalArgumentException
    //   909	922	2310	java/lang/IllegalArgumentException
    //   946	952	2310	java/lang/IllegalArgumentException
    //   976	984	2310	java/lang/IllegalArgumentException
    //   1008	1016	2310	java/lang/IllegalArgumentException
    //   1048	1058	2310	java/lang/IllegalArgumentException
    //   1082	1090	2310	java/lang/IllegalArgumentException
    //   1114	1121	2310	java/lang/IllegalArgumentException
    //   1145	1153	2310	java/lang/IllegalArgumentException
    //   1180	1190	2310	java/lang/IllegalArgumentException
    //   1222	1233	2310	java/lang/IllegalArgumentException
    //   1257	1261	2310	java/lang/IllegalArgumentException
    //   1285	1287	2310	java/lang/IllegalArgumentException
    //   1326	1336	2310	java/lang/IllegalArgumentException
    //   1360	1368	2310	java/lang/IllegalArgumentException
    //   1392	1405	2310	java/lang/IllegalArgumentException
    //   1429	1463	2310	java/lang/IllegalArgumentException
    //   1538	1541	2310	java/lang/IllegalArgumentException
    //   1613	1621	2310	java/lang/IllegalArgumentException
    //   2156	2163	2310	java/lang/IllegalArgumentException
    //   2277	2284	2310	java/lang/IllegalArgumentException
    //   2308	2310	2310	java/lang/IllegalArgumentException
    //   1922	1932	2369	java/io/IOException
    //   1932	1945	2369	java/io/IOException
    //   1922	1932	2406	finally
    //   1932	1945	2406	finally
    //   2039	2050	2406	finally
    //   2066	2116	2406	finally
    //   2374	2403	2406	finally
    //   2414	2422	2406	finally
    //   2178	2189	2425	finally
    //   2189	2191	2425	finally
    //   2426	2428	2425	finally
    //   1485	1496	2430	finally
    //   1496	1498	2430	finally
    //   2431	2433	2430	finally
    //   1563	1574	2435	finally
    //   1574	1576	2435	finally
    //   2436	2438	2435	finally
    //   2332	2343	2440	finally
    //   2343	2345	2440	finally
    //   2441	2443	2440	finally
    //   56	66	2445	java/net/SocketException
    //   90	111	2445	java/net/SocketException
    //   135	141	2445	java/net/SocketException
    //   188	236	2445	java/net/SocketException
    //   303	316	2445	java/net/SocketException
    //   340	347	2445	java/net/SocketException
    //   371	381	2445	java/net/SocketException
    //   405	412	2445	java/net/SocketException
    //   436	443	2445	java/net/SocketException
    //   467	473	2445	java/net/SocketException
    //   502	518	2445	java/net/SocketException
    //   542	548	2445	java/net/SocketException
    //   572	579	2445	java/net/SocketException
    //   603	614	2445	java/net/SocketException
    //   638	643	2445	java/net/SocketException
    //   667	672	2445	java/net/SocketException
    //   701	710	2445	java/net/SocketException
    //   734	739	2445	java/net/SocketException
    //   768	783	2445	java/net/SocketException
    //   807	812	2445	java/net/SocketException
    //   836	846	2445	java/net/SocketException
    //   870	875	2445	java/net/SocketException
    //   909	922	2445	java/net/SocketException
    //   946	952	2445	java/net/SocketException
    //   976	984	2445	java/net/SocketException
    //   1008	1016	2445	java/net/SocketException
    //   1048	1058	2445	java/net/SocketException
    //   1082	1090	2445	java/net/SocketException
    //   1114	1121	2445	java/net/SocketException
    //   1145	1153	2445	java/net/SocketException
    //   1180	1190	2445	java/net/SocketException
    //   1222	1233	2445	java/net/SocketException
    //   1257	1261	2445	java/net/SocketException
    //   1285	1287	2445	java/net/SocketException
    //   1326	1336	2445	java/net/SocketException
    //   1360	1368	2445	java/net/SocketException
    //   1392	1405	2445	java/net/SocketException
    //   1429	1463	2445	java/net/SocketException
    //   1538	1541	2445	java/net/SocketException
    //   1613	1621	2445	java/net/SocketException
    //   2156	2163	2445	java/net/SocketException
    //   2277	2284	2445	java/net/SocketException
    //   2308	2310	2445	java/net/SocketException
    //   2467	2478	2493	finally
    //   2478	2480	2493	finally
    //   2494	2496	2493	finally
    //   56	66	2498	java/lang/Exception
    //   90	111	2498	java/lang/Exception
    //   135	141	2498	java/lang/Exception
    //   188	236	2498	java/lang/Exception
    //   303	316	2498	java/lang/Exception
    //   340	347	2498	java/lang/Exception
    //   371	381	2498	java/lang/Exception
    //   405	412	2498	java/lang/Exception
    //   436	443	2498	java/lang/Exception
    //   467	473	2498	java/lang/Exception
    //   502	518	2498	java/lang/Exception
    //   542	548	2498	java/lang/Exception
    //   572	579	2498	java/lang/Exception
    //   603	614	2498	java/lang/Exception
    //   638	643	2498	java/lang/Exception
    //   667	672	2498	java/lang/Exception
    //   701	710	2498	java/lang/Exception
    //   734	739	2498	java/lang/Exception
    //   768	783	2498	java/lang/Exception
    //   807	812	2498	java/lang/Exception
    //   836	846	2498	java/lang/Exception
    //   870	875	2498	java/lang/Exception
    //   909	922	2498	java/lang/Exception
    //   946	952	2498	java/lang/Exception
    //   976	984	2498	java/lang/Exception
    //   1008	1016	2498	java/lang/Exception
    //   1048	1058	2498	java/lang/Exception
    //   1082	1090	2498	java/lang/Exception
    //   1114	1121	2498	java/lang/Exception
    //   1145	1153	2498	java/lang/Exception
    //   1180	1190	2498	java/lang/Exception
    //   1222	1233	2498	java/lang/Exception
    //   1257	1261	2498	java/lang/Exception
    //   1285	1287	2498	java/lang/Exception
    //   1326	1336	2498	java/lang/Exception
    //   1360	1368	2498	java/lang/Exception
    //   1392	1405	2498	java/lang/Exception
    //   1429	1463	2498	java/lang/Exception
    //   1538	1541	2498	java/lang/Exception
    //   1613	1621	2498	java/lang/Exception
    //   2156	2163	2498	java/lang/Exception
    //   2277	2284	2498	java/lang/Exception
    //   2308	2310	2498	java/lang/Exception
    //   2520	2531	2546	finally
    //   2531	2533	2546	finally
    //   2547	2549	2546	finally
    //   56	66	2551	finally
    //   90	111	2551	finally
    //   135	141	2551	finally
    //   188	236	2551	finally
    //   303	316	2551	finally
    //   340	347	2551	finally
    //   371	381	2551	finally
    //   405	412	2551	finally
    //   436	443	2551	finally
    //   467	473	2551	finally
    //   502	518	2551	finally
    //   542	548	2551	finally
    //   572	579	2551	finally
    //   603	614	2551	finally
    //   638	643	2551	finally
    //   667	672	2551	finally
    //   701	710	2551	finally
    //   734	739	2551	finally
    //   768	783	2551	finally
    //   807	812	2551	finally
    //   836	846	2551	finally
    //   870	875	2551	finally
    //   909	922	2551	finally
    //   946	952	2551	finally
    //   976	984	2551	finally
    //   1008	1016	2551	finally
    //   1048	1058	2551	finally
    //   1082	1090	2551	finally
    //   1114	1121	2551	finally
    //   1145	1153	2551	finally
    //   1180	1190	2551	finally
    //   1222	1233	2551	finally
    //   1257	1261	2551	finally
    //   1285	1287	2551	finally
    //   1326	1336	2551	finally
    //   1360	1368	2551	finally
    //   1392	1405	2551	finally
    //   1429	1463	2551	finally
    //   1468	1473	2551	finally
    //   1538	1541	2551	finally
    //   1546	1551	2551	finally
    //   1613	1621	2551	finally
    //   2156	2163	2551	finally
    //   2277	2284	2551	finally
    //   2308	2310	2551	finally
    //   2315	2320	2551	finally
    //   2450	2455	2551	finally
    //   2503	2508	2551	finally
    //   2564	2575	2589	finally
    //   2575	2577	2589	finally
    //   2590	2592	2589	finally
  }
  
  private static void setRequestLinkIp(Context paramContext, HttpParams paramHttpParams)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getLinkProperties(2);
    if (paramContext != null)
    {
      paramContext = paramContext.getAddresses().iterator();
      if ((paramContext != null) && (paramContext.hasNext()))
      {
        ConnRouteParams.setLocalAddress(paramHttpParams, (InetAddress)paramContext.next());
        Log.v("Mms:transaction", "bind the mms ip");
      }
    }
    else
    {
      return;
    }
    Log.e("Mms:transaction", "can not bind the mms ip");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.HttpUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */