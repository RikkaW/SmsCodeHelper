package com.android.mms.update;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import miui.util.CoderUtils;

public class JSONRequest
  extends Request
{
  private static long sExpiredTime;
  private String mData;
  private String mDescription;
  private String mEtag;
  private SharedPreferences mPref;
  private int mStatus = -1;
  
  public JSONRequest(Context paramContext, String paramString)
  {
    super(paramContext, paramString);
    mPref = PreferenceManager.getDefaultSharedPreferences(paramContext);
  }
  
  /* Error */
  private int getRequestStatus()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 40	com/android/mms/update/JSONRequest:mRequestUrl	Ljava/lang/String;
    //   5: invokespecial 44	com/android/mms/update/JSONRequest:prohibitRequest	(Ljava/lang/String;)Z
    //   8: ifeq +32 -> 40
    //   11: ldc 46
    //   13: new 48	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   20: ldc 53
    //   22: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: getstatic 59	com/android/mms/update/JSONRequest:sExpiredTime	J
    //   28: invokevirtual 62	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   31: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   34: invokestatic 72	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   37: pop
    //   38: iconst_3
    //   39: ireturn
    //   40: aload_0
    //   41: aload_0
    //   42: getfield 40	com/android/mms/update/JSONRequest:mRequestUrl	Ljava/lang/String;
    //   45: iconst_0
    //   46: invokespecial 76	com/android/mms/update/JSONRequest:setClientError	(Ljava/lang/String;Z)V
    //   49: iconst_1
    //   50: ifne +6 -> 56
    //   53: bipush 6
    //   55: ireturn
    //   56: aload_0
    //   57: getfield 80	com/android/mms/update/JSONRequest:mContext	Landroid/content/Context;
    //   60: invokestatic 86	com/android/mms/update/Network:isNetWorkConnected	(Landroid/content/Context;)Z
    //   63: ifne +13 -> 76
    //   66: ldc 46
    //   68: ldc 88
    //   70: invokestatic 72	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   73: pop
    //   74: iconst_1
    //   75: ireturn
    //   76: aconst_null
    //   77: astore 11
    //   79: aconst_null
    //   80: astore 13
    //   82: aconst_null
    //   83: astore 15
    //   85: aconst_null
    //   86: astore 6
    //   88: aconst_null
    //   89: astore 9
    //   91: aconst_null
    //   92: astore 17
    //   94: aconst_null
    //   95: astore 18
    //   97: aconst_null
    //   98: astore 20
    //   100: aconst_null
    //   101: astore 19
    //   103: aconst_null
    //   104: astore 16
    //   106: aconst_null
    //   107: astore 21
    //   109: aconst_null
    //   110: astore 22
    //   112: aconst_null
    //   113: astore 23
    //   115: aconst_null
    //   116: astore 24
    //   118: aconst_null
    //   119: astore 4
    //   121: iconst_3
    //   122: istore_1
    //   123: aload 20
    //   125: astore 8
    //   127: aload 21
    //   129: astore 7
    //   131: aload 22
    //   133: astore 10
    //   135: aload 23
    //   137: astore 12
    //   139: aload 24
    //   141: astore 14
    //   143: aload_0
    //   144: invokevirtual 92	com/android/mms/update/JSONRequest:getConn	()Ljava/net/HttpURLConnection;
    //   147: astore 5
    //   149: aload 20
    //   151: astore 8
    //   153: aload 5
    //   155: astore 6
    //   157: aload 21
    //   159: astore 7
    //   161: aload 5
    //   163: astore 11
    //   165: aload 22
    //   167: astore 10
    //   169: aload 5
    //   171: astore 13
    //   173: aload 23
    //   175: astore 12
    //   177: aload 5
    //   179: astore 15
    //   181: aload 24
    //   183: astore 14
    //   185: aload 5
    //   187: invokevirtual 97	java/net/HttpURLConnection:connect	()V
    //   190: aload 20
    //   192: astore 8
    //   194: aload 5
    //   196: astore 6
    //   198: aload 21
    //   200: astore 7
    //   202: aload 5
    //   204: astore 11
    //   206: aload 22
    //   208: astore 10
    //   210: aload 5
    //   212: astore 13
    //   214: aload 23
    //   216: astore 12
    //   218: aload 5
    //   220: astore 15
    //   222: aload 24
    //   224: astore 14
    //   226: aload_0
    //   227: getfield 100	com/android/mms/update/JSONRequest:mRequestMethod	Ljava/lang/String;
    //   230: ldc 102
    //   232: invokestatic 108	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   235: ifeq +72 -> 307
    //   238: aload 20
    //   240: astore 8
    //   242: aload 5
    //   244: astore 6
    //   246: aload 21
    //   248: astore 7
    //   250: aload 5
    //   252: astore 11
    //   254: aload 22
    //   256: astore 10
    //   258: aload 5
    //   260: astore 13
    //   262: aload 23
    //   264: astore 12
    //   266: aload 5
    //   268: astore 15
    //   270: aload 24
    //   272: astore 14
    //   274: new 110	java/io/DataOutputStream
    //   277: dup
    //   278: aload 5
    //   280: invokevirtual 114	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   283: invokespecial 117	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   286: astore 4
    //   288: aload 4
    //   290: aload_0
    //   291: invokevirtual 120	com/android/mms/update/JSONRequest:getParams	()Ljava/lang/String;
    //   294: ldc 122
    //   296: invokevirtual 128	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   299: invokevirtual 132	java/io/DataOutputStream:write	([B)V
    //   302: aload 4
    //   304: invokevirtual 135	java/io/DataOutputStream:flush	()V
    //   307: aload 20
    //   309: astore 8
    //   311: aload 5
    //   313: astore 6
    //   315: aload 4
    //   317: astore 7
    //   319: aload 5
    //   321: astore 11
    //   323: aload 4
    //   325: astore 10
    //   327: aload 5
    //   329: astore 13
    //   331: aload 4
    //   333: astore 12
    //   335: aload 5
    //   337: astore 15
    //   339: aload 4
    //   341: astore 14
    //   343: aload 5
    //   345: invokevirtual 138	java/net/HttpURLConnection:getResponseCode	()I
    //   348: istore_2
    //   349: aload 20
    //   351: astore 8
    //   353: aload 5
    //   355: astore 6
    //   357: aload 4
    //   359: astore 7
    //   361: aload 5
    //   363: astore 11
    //   365: aload 4
    //   367: astore 10
    //   369: aload 5
    //   371: astore 13
    //   373: aload 4
    //   375: astore 12
    //   377: aload 5
    //   379: astore 15
    //   381: aload 4
    //   383: astore 14
    //   385: ldc 46
    //   387: new 48	java/lang/StringBuilder
    //   390: dup
    //   391: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   394: ldc -116
    //   396: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: iload_2
    //   400: invokevirtual 143	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   403: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokestatic 72	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   409: pop
    //   410: iload_2
    //   411: sipush 200
    //   414: if_icmpne +580 -> 994
    //   417: aload 20
    //   419: astore 8
    //   421: aload 5
    //   423: astore 6
    //   425: aload 4
    //   427: astore 7
    //   429: aload 5
    //   431: astore 11
    //   433: aload 4
    //   435: astore 10
    //   437: aload 5
    //   439: astore 13
    //   441: aload 4
    //   443: astore 12
    //   445: aload 5
    //   447: astore 15
    //   449: aload 4
    //   451: astore 14
    //   453: new 145	java/io/BufferedReader
    //   456: dup
    //   457: new 147	java/io/InputStreamReader
    //   460: dup
    //   461: aload 5
    //   463: invokevirtual 151	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   466: invokespecial 154	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   469: invokespecial 157	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   472: astore 9
    //   474: new 48	java/lang/StringBuilder
    //   477: dup
    //   478: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   481: astore 6
    //   483: aload 9
    //   485: invokevirtual 160	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   488: astore 7
    //   490: aload 7
    //   492: ifnull +96 -> 588
    //   495: aload 6
    //   497: aload 7
    //   499: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: pop
    //   503: goto -20 -> 483
    //   506: astore 10
    //   508: iconst_2
    //   509: istore_1
    //   510: aload 9
    //   512: astore 8
    //   514: aload 5
    //   516: astore 6
    //   518: aload 4
    //   520: astore 7
    //   522: aload 10
    //   524: invokevirtual 163	java/net/SocketTimeoutException:printStackTrace	()V
    //   527: aload 9
    //   529: ifnull +8 -> 537
    //   532: aload 9
    //   534: invokevirtual 166	java/io/BufferedReader:close	()V
    //   537: aload 5
    //   539: ifnull +8 -> 547
    //   542: aload 5
    //   544: invokevirtual 169	java/net/HttpURLConnection:disconnect	()V
    //   547: iload_1
    //   548: istore_2
    //   549: aload 4
    //   551: ifnull +10 -> 561
    //   554: aload 4
    //   556: invokevirtual 170	java/io/DataOutputStream:close	()V
    //   559: iload_1
    //   560: istore_2
    //   561: ldc 46
    //   563: new 48	java/lang/StringBuilder
    //   566: dup
    //   567: invokespecial 51	java/lang/StringBuilder:<init>	()V
    //   570: ldc -84
    //   572: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: iload_2
    //   576: invokevirtual 143	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   579: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   582: invokestatic 72	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   585: pop
    //   586: iload_2
    //   587: ireturn
    //   588: new 174	org/json/JSONObject
    //   591: dup
    //   592: aload 6
    //   594: invokevirtual 66	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   597: invokespecial 177	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   600: astore 6
    //   602: aload_0
    //   603: aload 6
    //   605: ldc -77
    //   607: invokevirtual 183	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   610: putfield 185	com/android/mms/update/JSONRequest:mDescription	Ljava/lang/String;
    //   613: aload 6
    //   615: ldc -69
    //   617: invokevirtual 190	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   620: ifeq +369 -> 989
    //   623: aload 6
    //   625: ldc -69
    //   627: invokevirtual 194	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   630: istore_1
    //   631: iload_1
    //   632: ifne +174 -> 806
    //   635: aload 6
    //   637: ldc -60
    //   639: invokevirtual 183	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   642: astore 6
    //   644: aload 6
    //   646: invokestatic 200	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   649: ifne +85 -> 734
    //   652: aload_0
    //   653: getfield 204	com/android/mms/update/JSONRequest:mDecryptDownloadData	Z
    //   656: ifeq +78 -> 734
    //   659: aload_0
    //   660: aload 6
    //   662: invokestatic 207	com/android/mms/update/Request:decryptData	(Ljava/lang/String;)Ljava/lang/String;
    //   665: putfield 209	com/android/mms/update/JSONRequest:mData	Ljava/lang/String;
    //   668: iconst_0
    //   669: istore_1
    //   670: aload_0
    //   671: aload 5
    //   673: ldc -45
    //   675: invokevirtual 214	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   678: putfield 216	com/android/mms/update/JSONRequest:mEtag	Ljava/lang/String;
    //   681: aload 9
    //   683: astore 6
    //   685: aload 6
    //   687: ifnull +8 -> 695
    //   690: aload 6
    //   692: invokevirtual 166	java/io/BufferedReader:close	()V
    //   695: aload 5
    //   697: ifnull +8 -> 705
    //   700: aload 5
    //   702: invokevirtual 169	java/net/HttpURLConnection:disconnect	()V
    //   705: iload_1
    //   706: istore_2
    //   707: aload 4
    //   709: ifnull -148 -> 561
    //   712: aload 4
    //   714: invokevirtual 170	java/io/DataOutputStream:close	()V
    //   717: iload_1
    //   718: istore_2
    //   719: goto -158 -> 561
    //   722: astore 4
    //   724: aload 4
    //   726: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   729: iload_1
    //   730: istore_2
    //   731: goto -170 -> 561
    //   734: aload_0
    //   735: aload 6
    //   737: putfield 209	com/android/mms/update/JSONRequest:mData	Ljava/lang/String;
    //   740: goto -72 -> 668
    //   743: astore 10
    //   745: iconst_2
    //   746: istore_1
    //   747: aload 9
    //   749: astore 8
    //   751: aload 5
    //   753: astore 6
    //   755: aload 4
    //   757: astore 7
    //   759: aload 10
    //   761: invokevirtual 218	org/json/JSONException:printStackTrace	()V
    //   764: aload 9
    //   766: ifnull +8 -> 774
    //   769: aload 9
    //   771: invokevirtual 166	java/io/BufferedReader:close	()V
    //   774: aload 5
    //   776: ifnull +8 -> 784
    //   779: aload 5
    //   781: invokevirtual 169	java/net/HttpURLConnection:disconnect	()V
    //   784: iload_1
    //   785: istore_2
    //   786: aload 4
    //   788: ifnull -227 -> 561
    //   791: aload 4
    //   793: invokevirtual 170	java/io/DataOutputStream:close	()V
    //   796: iload_1
    //   797: istore_2
    //   798: goto -237 -> 561
    //   801: astore 4
    //   803: goto -79 -> 724
    //   806: iload_1
    //   807: sipush 1003
    //   810: if_icmpeq +17 -> 827
    //   813: iload_1
    //   814: sipush 1005
    //   817: if_icmpeq +10 -> 827
    //   820: iload_1
    //   821: sipush 1004
    //   824: if_icmpne +148 -> 972
    //   827: aload 6
    //   829: ldc -60
    //   831: invokevirtual 183	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   834: astore 6
    //   836: aload 6
    //   838: invokestatic 200	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   841: ifne +37 -> 878
    //   844: new 174	org/json/JSONObject
    //   847: dup
    //   848: aload 6
    //   850: invokespecial 177	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   853: ldc -36
    //   855: ldc2_w 221
    //   858: invokevirtual 226	org/json/JSONObject:optLong	(Ljava/lang/String;J)J
    //   861: putstatic 59	com/android/mms/update/JSONRequest:sExpiredTime	J
    //   864: aload_0
    //   865: aload_0
    //   866: getfield 40	com/android/mms/update/JSONRequest:mRequestUrl	Ljava/lang/String;
    //   869: iconst_1
    //   870: invokespecial 76	com/android/mms/update/JSONRequest:setClientError	(Ljava/lang/String;Z)V
    //   873: iconst_3
    //   874: istore_1
    //   875: goto -194 -> 681
    //   878: ldc2_w 221
    //   881: putstatic 59	com/android/mms/update/JSONRequest:sExpiredTime	J
    //   884: goto -20 -> 864
    //   887: astore 10
    //   889: aload 9
    //   891: astore 8
    //   893: aload 5
    //   895: astore 6
    //   897: aload 4
    //   899: astore 7
    //   901: aload_0
    //   902: getfield 80	com/android/mms/update/JSONRequest:mContext	Landroid/content/Context;
    //   905: invokestatic 86	com/android/mms/update/Network:isNetWorkConnected	(Landroid/content/Context;)Z
    //   908: ifne +162 -> 1070
    //   911: iconst_1
    //   912: istore_1
    //   913: aload 9
    //   915: astore 8
    //   917: aload 5
    //   919: astore 6
    //   921: aload 4
    //   923: astore 7
    //   925: aload 10
    //   927: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   930: aload 9
    //   932: ifnull +8 -> 940
    //   935: aload 9
    //   937: invokevirtual 166	java/io/BufferedReader:close	()V
    //   940: aload 5
    //   942: ifnull +8 -> 950
    //   945: aload 5
    //   947: invokevirtual 169	java/net/HttpURLConnection:disconnect	()V
    //   950: iload_1
    //   951: istore_2
    //   952: aload 4
    //   954: ifnull -393 -> 561
    //   957: aload 4
    //   959: invokevirtual 170	java/io/DataOutputStream:close	()V
    //   962: iload_1
    //   963: istore_2
    //   964: goto -403 -> 561
    //   967: astore 4
    //   969: goto -245 -> 724
    //   972: iload_1
    //   973: sipush 1999
    //   976: if_icmpne +8 -> 984
    //   979: iconst_4
    //   980: istore_1
    //   981: goto -300 -> 681
    //   984: iconst_3
    //   985: istore_1
    //   986: goto -305 -> 681
    //   989: iconst_4
    //   990: istore_1
    //   991: goto -310 -> 681
    //   994: iload_2
    //   995: sipush 304
    //   998: if_icmpne +13 -> 1011
    //   1001: bipush 7
    //   1003: istore_1
    //   1004: aload 9
    //   1006: astore 6
    //   1008: goto -323 -> 685
    //   1011: aload 20
    //   1013: astore 8
    //   1015: aload 5
    //   1017: astore 6
    //   1019: aload 4
    //   1021: astore 7
    //   1023: aload 5
    //   1025: astore 11
    //   1027: aload 4
    //   1029: astore 10
    //   1031: aload 5
    //   1033: astore 13
    //   1035: aload 4
    //   1037: astore 12
    //   1039: aload 5
    //   1041: astore 15
    //   1043: aload 4
    //   1045: astore 14
    //   1047: aload_0
    //   1048: iload_2
    //   1049: invokevirtual 230	com/android/mms/update/JSONRequest:isServerError	(I)Z
    //   1052: istore_3
    //   1053: aload 9
    //   1055: astore 6
    //   1057: iload_3
    //   1058: ifeq -373 -> 685
    //   1061: iconst_4
    //   1062: istore_1
    //   1063: aload 9
    //   1065: astore 6
    //   1067: goto -382 -> 685
    //   1070: iconst_2
    //   1071: istore_1
    //   1072: goto -159 -> 913
    //   1075: astore 5
    //   1077: aload 7
    //   1079: astore 4
    //   1081: aload 8
    //   1083: ifnull +8 -> 1091
    //   1086: aload 8
    //   1088: invokevirtual 166	java/io/BufferedReader:close	()V
    //   1091: aload 6
    //   1093: ifnull +8 -> 1101
    //   1096: aload 6
    //   1098: invokevirtual 169	java/net/HttpURLConnection:disconnect	()V
    //   1101: aload 4
    //   1103: ifnull +8 -> 1111
    //   1106: aload 4
    //   1108: invokevirtual 170	java/io/DataOutputStream:close	()V
    //   1111: aload 5
    //   1113: athrow
    //   1114: astore 7
    //   1116: aload 7
    //   1118: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1121: goto -30 -> 1091
    //   1124: astore 4
    //   1126: aload 4
    //   1128: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1131: goto -20 -> 1111
    //   1134: astore 6
    //   1136: aload 6
    //   1138: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1141: goto -604 -> 537
    //   1144: astore 4
    //   1146: goto -422 -> 724
    //   1149: astore 6
    //   1151: aload 6
    //   1153: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1156: goto -382 -> 774
    //   1159: astore 6
    //   1161: aload 6
    //   1163: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1166: goto -226 -> 940
    //   1169: astore 6
    //   1171: aload 6
    //   1173: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   1176: goto -481 -> 695
    //   1179: astore 7
    //   1181: aload 19
    //   1183: astore 8
    //   1185: aload 5
    //   1187: astore 6
    //   1189: aload 7
    //   1191: astore 5
    //   1193: goto -112 -> 1081
    //   1196: astore 7
    //   1198: aload 9
    //   1200: astore 8
    //   1202: aload 5
    //   1204: astore 6
    //   1206: aload 7
    //   1208: astore 5
    //   1210: goto -129 -> 1081
    //   1213: astore 6
    //   1215: aload 18
    //   1217: astore 9
    //   1219: aload 11
    //   1221: astore 5
    //   1223: aload 10
    //   1225: astore 4
    //   1227: aload 6
    //   1229: astore 10
    //   1231: goto -342 -> 889
    //   1234: astore 10
    //   1236: aload 18
    //   1238: astore 9
    //   1240: goto -351 -> 889
    //   1243: astore 10
    //   1245: aload 17
    //   1247: astore 9
    //   1249: aload 13
    //   1251: astore 5
    //   1253: aload 12
    //   1255: astore 4
    //   1257: goto -512 -> 745
    //   1260: astore 10
    //   1262: aload 17
    //   1264: astore 9
    //   1266: goto -521 -> 745
    //   1269: astore 10
    //   1271: aload 16
    //   1273: astore 9
    //   1275: aload 15
    //   1277: astore 5
    //   1279: aload 14
    //   1281: astore 4
    //   1283: goto -775 -> 508
    //   1286: astore 10
    //   1288: aload 16
    //   1290: astore 9
    //   1292: goto -784 -> 508
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1295	0	this	JSONRequest
    //   122	950	1	i	int
    //   348	701	2	j	int
    //   1052	6	3	bool	boolean
    //   119	594	4	localDataOutputStream	java.io.DataOutputStream
    //   722	70	4	localIOException1	java.io.IOException
    //   801	157	4	localIOException2	java.io.IOException
    //   967	77	4	localIOException3	java.io.IOException
    //   1079	28	4	localObject1	Object
    //   1124	3	4	localIOException4	java.io.IOException
    //   1144	1	4	localIOException5	java.io.IOException
    //   1225	57	4	localObject2	Object
    //   147	893	5	localHttpURLConnection	java.net.HttpURLConnection
    //   1075	111	5	localObject3	Object
    //   1191	87	5	localObject4	Object
    //   86	1011	6	localObject5	Object
    //   1134	3	6	localIOException6	java.io.IOException
    //   1149	3	6	localIOException7	java.io.IOException
    //   1159	3	6	localIOException8	java.io.IOException
    //   1169	3	6	localIOException9	java.io.IOException
    //   1187	18	6	localObject6	Object
    //   1213	15	6	localIOException10	java.io.IOException
    //   129	949	7	localObject7	Object
    //   1114	3	7	localIOException11	java.io.IOException
    //   1179	11	7	localObject8	Object
    //   1196	11	7	localObject9	Object
    //   125	1076	8	localObject10	Object
    //   89	1202	9	localObject11	Object
    //   133	303	10	localObject12	Object
    //   506	17	10	localSocketTimeoutException1	java.net.SocketTimeoutException
    //   743	17	10	localJSONException1	org.json.JSONException
    //   887	39	10	localIOException12	java.io.IOException
    //   1029	201	10	localIOException13	java.io.IOException
    //   1234	1	10	localIOException14	java.io.IOException
    //   1243	1	10	localJSONException2	org.json.JSONException
    //   1260	1	10	localJSONException3	org.json.JSONException
    //   1269	1	10	localSocketTimeoutException2	java.net.SocketTimeoutException
    //   1286	1	10	localSocketTimeoutException3	java.net.SocketTimeoutException
    //   77	1143	11	localObject13	Object
    //   137	1117	12	localObject14	Object
    //   80	1170	13	localObject15	Object
    //   141	1139	14	localObject16	Object
    //   83	1193	15	localObject17	Object
    //   104	1185	16	localObject18	Object
    //   92	1171	17	localObject19	Object
    //   95	1142	18	localObject20	Object
    //   101	1081	19	localObject21	Object
    //   98	914	20	localObject22	Object
    //   107	140	21	localObject23	Object
    //   110	145	22	localObject24	Object
    //   113	150	23	localObject25	Object
    //   116	155	24	localObject26	Object
    // Exception table:
    //   from	to	target	type
    //   474	483	506	java/net/SocketTimeoutException
    //   483	490	506	java/net/SocketTimeoutException
    //   495	503	506	java/net/SocketTimeoutException
    //   588	631	506	java/net/SocketTimeoutException
    //   635	668	506	java/net/SocketTimeoutException
    //   670	681	506	java/net/SocketTimeoutException
    //   734	740	506	java/net/SocketTimeoutException
    //   827	864	506	java/net/SocketTimeoutException
    //   864	873	506	java/net/SocketTimeoutException
    //   878	884	506	java/net/SocketTimeoutException
    //   712	717	722	java/io/IOException
    //   474	483	743	org/json/JSONException
    //   483	490	743	org/json/JSONException
    //   495	503	743	org/json/JSONException
    //   588	631	743	org/json/JSONException
    //   635	668	743	org/json/JSONException
    //   670	681	743	org/json/JSONException
    //   734	740	743	org/json/JSONException
    //   827	864	743	org/json/JSONException
    //   864	873	743	org/json/JSONException
    //   878	884	743	org/json/JSONException
    //   791	796	801	java/io/IOException
    //   474	483	887	java/io/IOException
    //   483	490	887	java/io/IOException
    //   495	503	887	java/io/IOException
    //   588	631	887	java/io/IOException
    //   635	668	887	java/io/IOException
    //   670	681	887	java/io/IOException
    //   734	740	887	java/io/IOException
    //   827	864	887	java/io/IOException
    //   864	873	887	java/io/IOException
    //   878	884	887	java/io/IOException
    //   957	962	967	java/io/IOException
    //   143	149	1075	finally
    //   185	190	1075	finally
    //   226	238	1075	finally
    //   274	288	1075	finally
    //   343	349	1075	finally
    //   385	410	1075	finally
    //   453	474	1075	finally
    //   522	527	1075	finally
    //   759	764	1075	finally
    //   901	911	1075	finally
    //   925	930	1075	finally
    //   1047	1053	1075	finally
    //   1086	1091	1114	java/io/IOException
    //   1106	1111	1124	java/io/IOException
    //   532	537	1134	java/io/IOException
    //   554	559	1144	java/io/IOException
    //   769	774	1149	java/io/IOException
    //   935	940	1159	java/io/IOException
    //   690	695	1169	java/io/IOException
    //   288	307	1179	finally
    //   474	483	1196	finally
    //   483	490	1196	finally
    //   495	503	1196	finally
    //   588	631	1196	finally
    //   635	668	1196	finally
    //   670	681	1196	finally
    //   734	740	1196	finally
    //   827	864	1196	finally
    //   864	873	1196	finally
    //   878	884	1196	finally
    //   143	149	1213	java/io/IOException
    //   185	190	1213	java/io/IOException
    //   226	238	1213	java/io/IOException
    //   274	288	1213	java/io/IOException
    //   343	349	1213	java/io/IOException
    //   385	410	1213	java/io/IOException
    //   453	474	1213	java/io/IOException
    //   1047	1053	1213	java/io/IOException
    //   288	307	1234	java/io/IOException
    //   143	149	1243	org/json/JSONException
    //   185	190	1243	org/json/JSONException
    //   226	238	1243	org/json/JSONException
    //   274	288	1243	org/json/JSONException
    //   343	349	1243	org/json/JSONException
    //   385	410	1243	org/json/JSONException
    //   453	474	1243	org/json/JSONException
    //   1047	1053	1243	org/json/JSONException
    //   288	307	1260	org/json/JSONException
    //   143	149	1269	java/net/SocketTimeoutException
    //   185	190	1269	java/net/SocketTimeoutException
    //   226	238	1269	java/net/SocketTimeoutException
    //   274	288	1269	java/net/SocketTimeoutException
    //   343	349	1269	java/net/SocketTimeoutException
    //   385	410	1269	java/net/SocketTimeoutException
    //   453	474	1269	java/net/SocketTimeoutException
    //   1047	1053	1269	java/net/SocketTimeoutException
    //   288	307	1286	java/net/SocketTimeoutException
  }
  
  private String getUrlRequestTimeKey(String paramString)
  {
    return String.format("pref_last_request_mms_time", new Object[] { CoderUtils.encodeSHA(paramString) });
  }
  
  private boolean prohibitRequest(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mPref.getBoolean("pref_mms_client_error", false))
    {
      bool1 = bool2;
      if (Math.abs(System.currentTimeMillis() - mPref.getLong(getUrlRequestTimeKey(paramString), 0L)) < sExpiredTime) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void setClientError(String paramString, boolean paramBoolean)
  {
    mPref.edit().putBoolean("pref_mms_client_error", paramBoolean).commit();
    if (paramBoolean)
    {
      Log.e("JSONRequest", "setClientError for url " + paramString);
      mPref.edit().putLong(getUrlRequestTimeKey(paramString), System.currentTimeMillis()).commit();
    }
  }
  
  public int getStatus()
  {
    if (mStatus == -1)
    {
      mStatus = getRequestStatus();
      if ((mStatus != 4) || (!"GET".equals(mRequestMethod))) {}
    }
    try
    {
      Log.d("JSONRequest", "server error, sleep 5000ms and retry");
      Thread.sleep(5000L);
      mStatus = getRequestStatus();
      return mStatus;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public String requestData()
  {
    return mData;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.JSONRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */