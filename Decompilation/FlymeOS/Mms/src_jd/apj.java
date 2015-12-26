import android.util.Log;
import com.ted.android.contacts.netparser.model.DealItem;
import com.ted.android.contacts.netparser.model.NumItem;
import com.ted.android.contacts.netparser.model.SpItem;
import com.ted.android.contacts.netparser.model.SpItem.SpType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class apj
{
  public static int a = -1;
  public static int b = -1;
  private static final String c = apj.class.getSimpleName();
  
  public static NumItem a(String paramString)
  {
    Object localObject = null;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString).getJSONObject("data");
      paramString = (String)localObject;
      if (localJSONObject != null) {
        paramString = a(localJSONObject);
      }
      return paramString;
    }
    catch (JSONException paramString)
    {
      Log.e(c, "parser network number json error:", paramString);
    }
    return null;
  }
  
  /* Error */
  public static NumItem a(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_0
    //   3: ldc 56
    //   5: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   8: ifne +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: new 62	com/ted/android/contacts/netparser/model/NumItem
    //   16: dup
    //   17: invokespecial 63	com/ted/android/contacts/netparser/model/NumItem:<init>	()V
    //   20: astore 6
    //   22: aload 6
    //   24: aload_0
    //   25: ldc 56
    //   27: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   30: invokevirtual 69	com/ted/android/contacts/netparser/model/NumItem:c	(Ljava/lang/String;)V
    //   33: aload_0
    //   34: ldc 71
    //   36: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   39: ifeq +53 -> 92
    //   42: aload_0
    //   43: ldc 71
    //   45: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   48: astore 5
    //   50: aload 5
    //   52: instanceof 77
    //   55: ifeq +37 -> 92
    //   58: aload 5
    //   60: checkcast 77	org/json/JSONArray
    //   63: astore 7
    //   65: new 79	java/util/ArrayList
    //   68: dup
    //   69: invokespecial 80	java/util/ArrayList:<init>	()V
    //   72: astore 8
    //   74: iconst_0
    //   75: istore_1
    //   76: iload_1
    //   77: aload 7
    //   79: invokevirtual 84	org/json/JSONArray:length	()I
    //   82: if_icmplt +932 -> 1014
    //   85: aload 6
    //   87: aload 8
    //   89: invokevirtual 87	com/ted/android/contacts/netparser/model/NumItem:a	(Ljava/util/List;)V
    //   92: aload_0
    //   93: ldc 89
    //   95: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   98: ifeq +14 -> 112
    //   101: aload 6
    //   103: aload_0
    //   104: ldc 89
    //   106: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   109: invokevirtual 91	com/ted/android/contacts/netparser/model/NumItem:a	(Ljava/lang/String;)V
    //   112: aload_0
    //   113: ldc 93
    //   115: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   118: ifeq +14 -> 132
    //   121: aload 6
    //   123: aload_0
    //   124: ldc 93
    //   126: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   129: invokevirtual 95	com/ted/android/contacts/netparser/model/NumItem:b	(Ljava/lang/String;)V
    //   132: aload_0
    //   133: ldc 97
    //   135: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   138: ifeq +88 -> 226
    //   141: aload_0
    //   142: ldc 97
    //   144: invokevirtual 41	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   147: astore 7
    //   149: iconst_m1
    //   150: istore_2
    //   151: ldc 99
    //   153: astore 5
    //   155: aload 7
    //   157: ldc 101
    //   159: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   162: ifeq +1221 -> 1383
    //   165: aload 7
    //   167: ldc 101
    //   169: invokevirtual 105	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   172: istore_1
    //   173: aload 7
    //   175: ldc 107
    //   177: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   180: ifeq +12 -> 192
    //   183: aload 7
    //   185: ldc 107
    //   187: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   190: astore 5
    //   192: aload 7
    //   194: ldc 109
    //   196: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   199: ifeq +11 -> 210
    //   202: aload 7
    //   204: ldc 109
    //   206: invokevirtual 105	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   209: istore_2
    //   210: aload 6
    //   212: new 111	com/ted/android/contacts/netparser/model/NumItem$MarkerData
    //   215: dup
    //   216: iload_1
    //   217: aload 5
    //   219: iload_2
    //   220: invokespecial 114	com/ted/android/contacts/netparser/model/NumItem$MarkerData:<init>	(ILjava/lang/String;I)V
    //   223: invokevirtual 117	com/ted/android/contacts/netparser/model/NumItem:b	(Lcom/ted/android/contacts/netparser/model/NumItem$MarkerData;)V
    //   226: aload_0
    //   227: ldc 119
    //   229: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   232: ifeq +61 -> 293
    //   235: ldc 121
    //   237: aload_0
    //   238: ldc 119
    //   240: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   243: invokevirtual 124	java/lang/Object:toString	()Ljava/lang/String;
    //   246: invokevirtual 130	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   249: ifne +44 -> 293
    //   252: aload_0
    //   253: ldc 119
    //   255: invokevirtual 41	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   258: astore 5
    //   260: aload 6
    //   262: new 132	com/ted/android/contacts/netparser/model/NumItem$IconData
    //   265: dup
    //   266: aload 5
    //   268: ldc -122
    //   270: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   273: aload 5
    //   275: ldc -120
    //   277: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   280: aload 5
    //   282: ldc -118
    //   284: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   287: invokespecial 141	com/ted/android/contacts/netparser/model/NumItem$IconData:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   290: invokevirtual 144	com/ted/android/contacts/netparser/model/NumItem:a	(Lcom/ted/android/contacts/netparser/model/NumItem$IconData;)V
    //   293: aload_0
    //   294: ldc -110
    //   296: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   299: ifeq +14 -> 313
    //   302: aload 6
    //   304: aload_0
    //   305: ldc -110
    //   307: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   310: invokevirtual 148	com/ted/android/contacts/netparser/model/NumItem:e	(Ljava/lang/String;)V
    //   313: aload_0
    //   314: ldc -106
    //   316: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   319: ifeq +14 -> 333
    //   322: aload 6
    //   324: aload_0
    //   325: ldc -106
    //   327: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   330: invokevirtual 153	com/ted/android/contacts/netparser/model/NumItem:g	(Ljava/lang/String;)V
    //   333: aload_0
    //   334: ldc -101
    //   336: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   339: ifeq +14 -> 353
    //   342: aload 6
    //   344: aload_0
    //   345: ldc -101
    //   347: invokevirtual 105	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   350: invokevirtual 158	com/ted/android/contacts/netparser/model/NumItem:b	(I)V
    //   353: aload_0
    //   354: ldc -96
    //   356: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   359: ifeq +14 -> 373
    //   362: aload 6
    //   364: aload_0
    //   365: ldc -96
    //   367: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   370: invokevirtual 163	com/ted/android/contacts/netparser/model/NumItem:h	(Ljava/lang/String;)V
    //   373: aload_0
    //   374: ldc -91
    //   376: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   379: ifeq +14 -> 393
    //   382: aload 6
    //   384: aload_0
    //   385: ldc -91
    //   387: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   390: invokevirtual 168	com/ted/android/contacts/netparser/model/NumItem:f	(Ljava/lang/String;)V
    //   393: aload_0
    //   394: ldc -86
    //   396: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   399: ifeq +14 -> 413
    //   402: aload 6
    //   404: aload_0
    //   405: ldc -86
    //   407: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   410: invokevirtual 173	com/ted/android/contacts/netparser/model/NumItem:i	(Ljava/lang/String;)V
    //   413: aload_0
    //   414: ldc -81
    //   416: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   419: ifeq +14 -> 433
    //   422: aload 6
    //   424: aload_0
    //   425: ldc -81
    //   427: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   430: invokevirtual 178	com/ted/android/contacts/netparser/model/NumItem:j	(Ljava/lang/String;)V
    //   433: aload_0
    //   434: ldc -76
    //   436: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   439: ifeq +14 -> 453
    //   442: aload 6
    //   444: aload_0
    //   445: ldc -76
    //   447: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   450: invokevirtual 183	com/ted/android/contacts/netparser/model/NumItem:k	(Ljava/lang/String;)V
    //   453: aload_0
    //   454: ldc -71
    //   456: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   459: ifeq +14 -> 473
    //   462: aload 6
    //   464: aload_0
    //   465: ldc -71
    //   467: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   470: invokevirtual 188	com/ted/android/contacts/netparser/model/NumItem:l	(Ljava/lang/String;)V
    //   473: aload_0
    //   474: ldc -66
    //   476: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   479: ifeq +14 -> 493
    //   482: aload 6
    //   484: aload_0
    //   485: ldc -66
    //   487: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   490: invokevirtual 193	com/ted/android/contacts/netparser/model/NumItem:n	(Ljava/lang/String;)V
    //   493: aload_0
    //   494: ldc -61
    //   496: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   499: ifeq +14 -> 513
    //   502: aload 6
    //   504: aload_0
    //   505: ldc -61
    //   507: invokevirtual 105	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   510: invokevirtual 197	com/ted/android/contacts/netparser/model/NumItem:a	(I)V
    //   513: aload_0
    //   514: ldc -57
    //   516: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   519: ifeq +14 -> 533
    //   522: aload 6
    //   524: aload_0
    //   525: ldc -57
    //   527: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   530: invokevirtual 202	com/ted/android/contacts/netparser/model/NumItem:m	(Ljava/lang/String;)V
    //   533: aload_0
    //   534: ldc -52
    //   536: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   539: istore 4
    //   541: iload 4
    //   543: ifeq +29 -> 572
    //   546: aload_0
    //   547: ldc -52
    //   549: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   552: astore 5
    //   554: aload 5
    //   556: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   559: ifne +13 -> 572
    //   562: aload 6
    //   564: aload 5
    //   566: invokestatic 216	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   569: invokevirtual 219	com/ted/android/contacts/netparser/model/NumItem:a	(F)V
    //   572: aload_0
    //   573: ldc -35
    //   575: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   578: istore 4
    //   580: iload 4
    //   582: ifeq +29 -> 611
    //   585: aload_0
    //   586: ldc -35
    //   588: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   591: astore 5
    //   593: aload 5
    //   595: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   598: ifne +13 -> 611
    //   601: aload 6
    //   603: aload 5
    //   605: invokestatic 216	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   608: invokevirtual 223	com/ted/android/contacts/netparser/model/NumItem:b	(F)V
    //   611: aload_0
    //   612: ldc -31
    //   614: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   617: istore 4
    //   619: iload 4
    //   621: ifeq +29 -> 650
    //   624: aload_0
    //   625: ldc -31
    //   627: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   630: astore 5
    //   632: aload 5
    //   634: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   637: ifne +13 -> 650
    //   640: aload 6
    //   642: aload 5
    //   644: invokestatic 216	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   647: invokevirtual 227	com/ted/android/contacts/netparser/model/NumItem:c	(F)V
    //   650: aload_0
    //   651: ldc -27
    //   653: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   656: ifeq +38 -> 694
    //   659: aload_0
    //   660: ldc -27
    //   662: invokevirtual 233	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   665: astore 5
    //   667: aload 5
    //   669: invokevirtual 84	org/json/JSONArray:length	()I
    //   672: newarray <illegal type>
    //   674: astore 7
    //   676: iconst_0
    //   677: istore_1
    //   678: iload_1
    //   679: aload 5
    //   681: invokevirtual 84	org/json/JSONArray:length	()I
    //   684: if_icmplt +461 -> 1145
    //   687: aload 6
    //   689: aload 7
    //   691: invokevirtual 236	com/ted/android/contacts/netparser/model/NumItem:a	([F)V
    //   694: aload_0
    //   695: ldc -18
    //   697: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   700: ifeq +38 -> 738
    //   703: aload_0
    //   704: ldc -18
    //   706: invokevirtual 233	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   709: astore 5
    //   711: new 240	java/util/LinkedList
    //   714: dup
    //   715: invokespecial 241	java/util/LinkedList:<init>	()V
    //   718: astore 7
    //   720: iconst_0
    //   721: istore_1
    //   722: iload_1
    //   723: aload 5
    //   725: invokevirtual 84	org/json/JSONArray:length	()I
    //   728: if_icmplt +475 -> 1203
    //   731: aload 6
    //   733: aload 7
    //   735: invokevirtual 244	com/ted/android/contacts/netparser/model/NumItem:a	(Ljava/util/LinkedList;)V
    //   738: aload_0
    //   739: ldc -10
    //   741: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   744: ifeq +39 -> 783
    //   747: aload_0
    //   748: ldc -10
    //   750: invokevirtual 233	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   753: astore 5
    //   755: aload 5
    //   757: invokevirtual 84	org/json/JSONArray:length	()I
    //   760: anewarray 126	java/lang/String
    //   763: astore 7
    //   765: iconst_0
    //   766: istore_1
    //   767: iload_1
    //   768: aload 5
    //   770: invokevirtual 84	org/json/JSONArray:length	()I
    //   773: if_icmplt +449 -> 1222
    //   776: aload 6
    //   778: aload 7
    //   780: invokevirtual 249	com/ted/android/contacts/netparser/model/NumItem:a	([Ljava/lang/String;)V
    //   783: aload_0
    //   784: ldc -5
    //   786: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   789: ifeq +216 -> 1005
    //   792: aload_0
    //   793: ldc -5
    //   795: invokevirtual 41	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   798: astore 7
    //   800: new 79	java/util/ArrayList
    //   803: dup
    //   804: invokespecial 80	java/util/ArrayList:<init>	()V
    //   807: astore 5
    //   809: new 79	java/util/ArrayList
    //   812: dup
    //   813: invokespecial 80	java/util/ArrayList:<init>	()V
    //   816: astore 9
    //   818: new 79	java/util/ArrayList
    //   821: dup
    //   822: invokespecial 80	java/util/ArrayList:<init>	()V
    //   825: astore 8
    //   827: aload 6
    //   829: aload 5
    //   831: invokevirtual 254	com/ted/android/contacts/netparser/model/NumItem:a	(Ljava/util/ArrayList;)V
    //   834: aload 6
    //   836: aload 8
    //   838: invokevirtual 256	com/ted/android/contacts/netparser/model/NumItem:c	(Ljava/util/ArrayList;)V
    //   841: aload 6
    //   843: aload 9
    //   845: invokevirtual 258	com/ted/android/contacts/netparser/model/NumItem:b	(Ljava/util/ArrayList;)V
    //   848: aload 7
    //   850: ldc_w 260
    //   853: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   856: istore 4
    //   858: iload 4
    //   860: ifeq +41 -> 901
    //   863: aload 7
    //   865: ldc_w 260
    //   868: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   871: astore 10
    //   873: aload 10
    //   875: instanceof 77
    //   878: ifeq +23 -> 901
    //   881: aload 10
    //   883: checkcast 77	org/json/JSONArray
    //   886: astore 10
    //   888: iconst_0
    //   889: istore_1
    //   890: aload 10
    //   892: invokevirtual 84	org/json/JSONArray:length	()I
    //   895: istore_2
    //   896: iload_1
    //   897: iload_2
    //   898: if_icmplt +341 -> 1239
    //   901: aload 7
    //   903: ldc_w 262
    //   906: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   909: istore 4
    //   911: iload 4
    //   913: ifeq +41 -> 954
    //   916: aload 7
    //   918: ldc_w 262
    //   921: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   924: astore 9
    //   926: aload 9
    //   928: instanceof 77
    //   931: ifeq +23 -> 954
    //   934: aload 9
    //   936: checkcast 77	org/json/JSONArray
    //   939: astore 9
    //   941: iconst_0
    //   942: istore_1
    //   943: aload 9
    //   945: invokevirtual 84	org/json/JSONArray:length	()I
    //   948: istore_2
    //   949: iload_1
    //   950: iload_2
    //   951: if_icmplt +336 -> 1287
    //   954: aload 7
    //   956: ldc 56
    //   958: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   961: istore 4
    //   963: iload 4
    //   965: ifeq +40 -> 1005
    //   968: aload 7
    //   970: ldc 56
    //   972: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   975: astore 7
    //   977: aload 7
    //   979: instanceof 77
    //   982: ifeq +23 -> 1005
    //   985: aload 7
    //   987: checkcast 77	org/json/JSONArray
    //   990: astore 7
    //   992: iload_3
    //   993: istore_1
    //   994: aload 7
    //   996: invokevirtual 84	org/json/JSONArray:length	()I
    //   999: istore_2
    //   1000: iload_1
    //   1001: iload_2
    //   1002: if_icmplt +333 -> 1335
    //   1005: aload_0
    //   1006: aload 6
    //   1008: invokestatic 265	apj:a	(Lorg/json/JSONObject;Lcom/ted/android/contacts/netparser/model/NumItem;)V
    //   1011: goto +377 -> 1388
    //   1014: aload 7
    //   1016: iload_1
    //   1017: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   1020: astore 9
    //   1022: aload 9
    //   1024: ldc -110
    //   1026: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   1029: ifeq +362 -> 1391
    //   1032: aload 9
    //   1034: ldc -110
    //   1036: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1039: astore 5
    //   1041: aload 8
    //   1043: new 270	com/ted/android/contacts/netparser/model/NumItem$RelevantNumber
    //   1046: dup
    //   1047: aload 9
    //   1049: ldc 56
    //   1051: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1054: aload 5
    //   1056: aload 9
    //   1058: ldc -91
    //   1060: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1063: invokespecial 271	com/ted/android/contacts/netparser/model/NumItem$RelevantNumber:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1066: invokeinterface 276 2 0
    //   1071: pop
    //   1072: iload_1
    //   1073: iconst_1
    //   1074: iadd
    //   1075: istore_1
    //   1076: goto -1000 -> 76
    //   1079: astore 5
    //   1081: getstatic 19	apj:c	Ljava/lang/String;
    //   1084: ldc_w 278
    //   1087: aload 5
    //   1089: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1092: pop
    //   1093: goto -521 -> 572
    //   1096: astore_0
    //   1097: getstatic 19	apj:c	Ljava/lang/String;
    //   1100: ldc_w 280
    //   1103: aload_0
    //   1104: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1107: pop
    //   1108: goto +280 -> 1388
    //   1111: astore 5
    //   1113: getstatic 19	apj:c	Ljava/lang/String;
    //   1116: ldc_w 282
    //   1119: aload 5
    //   1121: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1124: pop
    //   1125: goto -514 -> 611
    //   1128: astore 5
    //   1130: getstatic 19	apj:c	Ljava/lang/String;
    //   1133: ldc_w 284
    //   1136: aload 5
    //   1138: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1141: pop
    //   1142: goto -492 -> 650
    //   1145: aload 5
    //   1147: iload_1
    //   1148: invokevirtual 287	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   1151: astore 8
    //   1153: aload 8
    //   1155: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1158: ifne +15 -> 1173
    //   1161: aload 7
    //   1163: iload_1
    //   1164: aload 8
    //   1166: invokestatic 216	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   1169: fastore
    //   1170: goto +228 -> 1398
    //   1173: aload 7
    //   1175: iload_1
    //   1176: fconst_0
    //   1177: fastore
    //   1178: goto +220 -> 1398
    //   1181: astore 8
    //   1183: getstatic 19	apj:c	Ljava/lang/String;
    //   1186: ldc_w 289
    //   1189: aload 8
    //   1191: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1194: pop
    //   1195: aload 7
    //   1197: iload_1
    //   1198: fconst_0
    //   1199: fastore
    //   1200: goto +198 -> 1398
    //   1203: aload 7
    //   1205: aload 5
    //   1207: iload_1
    //   1208: invokevirtual 287	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   1211: invokevirtual 290	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   1214: pop
    //   1215: iload_1
    //   1216: iconst_1
    //   1217: iadd
    //   1218: istore_1
    //   1219: goto -497 -> 722
    //   1222: aload 7
    //   1224: iload_1
    //   1225: aload 5
    //   1227: iload_1
    //   1228: invokevirtual 287	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   1231: aastore
    //   1232: iload_1
    //   1233: iconst_1
    //   1234: iadd
    //   1235: istore_1
    //   1236: goto -469 -> 767
    //   1239: aload 10
    //   1241: iload_1
    //   1242: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   1245: invokestatic 293	apj:b	(Lorg/json/JSONObject;)Lcom/ted/android/contacts/netparser/model/MenuItem;
    //   1248: astore 11
    //   1250: aload 11
    //   1252: ifnull +11 -> 1263
    //   1255: aload 9
    //   1257: aload 11
    //   1259: invokevirtual 294	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1262: pop
    //   1263: iload_1
    //   1264: iconst_1
    //   1265: iadd
    //   1266: istore_1
    //   1267: goto -377 -> 890
    //   1270: astore 9
    //   1272: getstatic 19	apj:c	Ljava/lang/String;
    //   1275: ldc_w 296
    //   1278: aload 9
    //   1280: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1283: pop
    //   1284: goto -383 -> 901
    //   1287: aload 9
    //   1289: iload_1
    //   1290: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   1293: invokestatic 293	apj:b	(Lorg/json/JSONObject;)Lcom/ted/android/contacts/netparser/model/MenuItem;
    //   1296: astore 10
    //   1298: aload 10
    //   1300: ifnull +11 -> 1311
    //   1303: aload 8
    //   1305: aload 10
    //   1307: invokevirtual 294	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1310: pop
    //   1311: iload_1
    //   1312: iconst_1
    //   1313: iadd
    //   1314: istore_1
    //   1315: goto -372 -> 943
    //   1318: astore 8
    //   1320: getstatic 19	apj:c	Ljava/lang/String;
    //   1323: ldc_w 298
    //   1326: aload 8
    //   1328: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1331: pop
    //   1332: goto -378 -> 954
    //   1335: aload 7
    //   1337: iload_1
    //   1338: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   1341: invokestatic 293	apj:b	(Lorg/json/JSONObject;)Lcom/ted/android/contacts/netparser/model/MenuItem;
    //   1344: astore 8
    //   1346: aload 8
    //   1348: ifnull +11 -> 1359
    //   1351: aload 5
    //   1353: aload 8
    //   1355: invokevirtual 294	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1358: pop
    //   1359: iload_1
    //   1360: iconst_1
    //   1361: iadd
    //   1362: istore_1
    //   1363: goto -369 -> 994
    //   1366: astore 5
    //   1368: getstatic 19	apj:c	Ljava/lang/String;
    //   1371: ldc_w 300
    //   1374: aload 5
    //   1376: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1379: pop
    //   1380: goto -375 -> 1005
    //   1383: iconst_0
    //   1384: istore_1
    //   1385: goto -1212 -> 173
    //   1388: aload 6
    //   1390: areturn
    //   1391: ldc 99
    //   1393: astore 5
    //   1395: goto -354 -> 1041
    //   1398: iload_1
    //   1399: iconst_1
    //   1400: iadd
    //   1401: istore_1
    //   1402: goto -724 -> 678
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1405	0	paramJSONObject	JSONObject
    //   75	1327	1	i	int
    //   150	853	2	j	int
    //   1	992	3	k	int
    //   539	425	4	bool	boolean
    //   48	1007	5	localObject1	Object
    //   1079	9	5	localException1	Exception
    //   1111	9	5	localException2	Exception
    //   1128	224	5	localException3	Exception
    //   1366	9	5	localException4	Exception
    //   1393	1	5	str	String
    //   20	1369	6	localNumItem	NumItem
    //   63	1273	7	localObject2	Object
    //   72	1093	8	localObject3	Object
    //   1181	123	8	localException5	Exception
    //   1318	9	8	localException6	Exception
    //   1344	10	8	localMenuItem1	com.ted.android.contacts.netparser.model.MenuItem
    //   816	440	9	localObject4	Object
    //   1270	18	9	localException7	Exception
    //   871	435	10	localObject5	Object
    //   1248	10	11	localMenuItem2	com.ted.android.contacts.netparser.model.MenuItem
    // Exception table:
    //   from	to	target	type
    //   546	572	1079	java/lang/Exception
    //   22	74	1096	org/json/JSONException
    //   76	92	1096	org/json/JSONException
    //   92	112	1096	org/json/JSONException
    //   112	132	1096	org/json/JSONException
    //   132	149	1096	org/json/JSONException
    //   155	173	1096	org/json/JSONException
    //   173	192	1096	org/json/JSONException
    //   192	210	1096	org/json/JSONException
    //   210	226	1096	org/json/JSONException
    //   226	293	1096	org/json/JSONException
    //   293	313	1096	org/json/JSONException
    //   313	333	1096	org/json/JSONException
    //   333	353	1096	org/json/JSONException
    //   353	373	1096	org/json/JSONException
    //   373	393	1096	org/json/JSONException
    //   393	413	1096	org/json/JSONException
    //   413	433	1096	org/json/JSONException
    //   433	453	1096	org/json/JSONException
    //   453	473	1096	org/json/JSONException
    //   473	493	1096	org/json/JSONException
    //   493	513	1096	org/json/JSONException
    //   513	533	1096	org/json/JSONException
    //   533	541	1096	org/json/JSONException
    //   546	572	1096	org/json/JSONException
    //   572	580	1096	org/json/JSONException
    //   585	611	1096	org/json/JSONException
    //   611	619	1096	org/json/JSONException
    //   624	650	1096	org/json/JSONException
    //   650	676	1096	org/json/JSONException
    //   678	694	1096	org/json/JSONException
    //   694	720	1096	org/json/JSONException
    //   722	738	1096	org/json/JSONException
    //   738	765	1096	org/json/JSONException
    //   767	783	1096	org/json/JSONException
    //   783	858	1096	org/json/JSONException
    //   863	888	1096	org/json/JSONException
    //   890	896	1096	org/json/JSONException
    //   901	911	1096	org/json/JSONException
    //   916	941	1096	org/json/JSONException
    //   943	949	1096	org/json/JSONException
    //   954	963	1096	org/json/JSONException
    //   968	992	1096	org/json/JSONException
    //   994	1000	1096	org/json/JSONException
    //   1005	1011	1096	org/json/JSONException
    //   1014	1041	1096	org/json/JSONException
    //   1041	1072	1096	org/json/JSONException
    //   1081	1093	1096	org/json/JSONException
    //   1113	1125	1096	org/json/JSONException
    //   1130	1142	1096	org/json/JSONException
    //   1145	1170	1096	org/json/JSONException
    //   1183	1195	1096	org/json/JSONException
    //   1203	1215	1096	org/json/JSONException
    //   1222	1232	1096	org/json/JSONException
    //   1239	1250	1096	org/json/JSONException
    //   1255	1263	1096	org/json/JSONException
    //   1272	1284	1096	org/json/JSONException
    //   1287	1298	1096	org/json/JSONException
    //   1303	1311	1096	org/json/JSONException
    //   1320	1332	1096	org/json/JSONException
    //   1335	1346	1096	org/json/JSONException
    //   1351	1359	1096	org/json/JSONException
    //   1368	1380	1096	org/json/JSONException
    //   585	611	1111	java/lang/Exception
    //   624	650	1128	java/lang/Exception
    //   1145	1170	1181	java/lang/Exception
    //   863	888	1270	java/lang/Exception
    //   890	896	1270	java/lang/Exception
    //   1239	1250	1270	java/lang/Exception
    //   1255	1263	1270	java/lang/Exception
    //   916	941	1318	java/lang/Exception
    //   943	949	1318	java/lang/Exception
    //   1287	1298	1318	java/lang/Exception
    //   1303	1311	1318	java/lang/Exception
    //   968	992	1366	java/lang/Exception
    //   994	1000	1366	java/lang/Exception
    //   1335	1346	1366	java/lang/Exception
    //   1351	1359	1366	java/lang/Exception
  }
  
  private static void a(JSONObject paramJSONObject, NumItem paramNumItem)
  {
    ArrayList localArrayList;
    int i;
    if (paramJSONObject.has("deals"))
    {
      paramJSONObject = paramJSONObject.getJSONArray("deals");
      localArrayList = new ArrayList(paramJSONObject.length());
      i = 0;
      if (i >= paramJSONObject.length()) {
        paramNumItem.d(localArrayList);
      }
    }
    else
    {
      return;
    }
    DealItem localDealItem = new DealItem();
    Object localObject1 = paramJSONObject.getJSONObject(i);
    if (((JSONObject)localObject1).has("deal_name")) {
      localDealItem.setDealName(((JSONObject)localObject1).getString("deal_name"));
    }
    if (((JSONObject)localObject1).has("deal_image")) {
      localDealItem.setDealImage(((JSONObject)localObject1).getString("deal_image"));
    }
    if (((JSONObject)localObject1).has("deal_desc")) {
      localDealItem.setDescription(((JSONObject)localObject1).getString("deal_desc"));
    }
    if (((JSONObject)localObject1).has("original_price")) {
      localDealItem.setOrigPrice(Float.parseFloat(((JSONObject)localObject1).getString("original_price")));
    }
    if (((JSONObject)localObject1).has("current_price")) {
      localDealItem.setCurrPrice(Float.parseFloat(((JSONObject)localObject1).getString("current_price")));
    }
    if (((JSONObject)localObject1).has("reservation")) {
      if (((JSONObject)localObject1).getInt("reservation") == 0) {
        break label343;
      }
    }
    label343:
    for (boolean bool = true;; bool = false)
    {
      localDealItem.enableReserved(bool);
      if (((JSONObject)localObject1).has("deal_url")) {
        localDealItem.setUrl(((JSONObject)localObject1).getString("deal_url"));
      }
      if (((JSONObject)localObject1).has("start_time"))
      {
        Object localObject2 = ((JSONObject)localObject1).get("start_time");
        if ((localObject2 instanceof Integer)) {
          localDealItem.setStartTime(((Integer)localObject2).intValue());
        }
      }
      if (((JSONObject)localObject1).has("end_time"))
      {
        localObject1 = ((JSONObject)localObject1).get("end_time");
        if ((localObject1 instanceof Integer)) {
          localDealItem.setStartTime(((Integer)localObject1).intValue());
        }
      }
      localArrayList.add(localDealItem);
      i += 1;
      break;
    }
  }
  
  /* Error */
  public static com.ted.android.contacts.netparser.model.MenuItem b(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 360	com/ted/android/contacts/netparser/model/MenuItem
    //   5: dup
    //   6: invokespecial 361	com/ted/android/contacts/netparser/model/MenuItem:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: ldc -110
    //   14: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   17: ifeq +14 -> 31
    //   20: aload 4
    //   22: aload_0
    //   23: ldc -110
    //   25: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   28: invokevirtual 362	com/ted/android/contacts/netparser/model/MenuItem:a	(Ljava/lang/String;)V
    //   31: aload_0
    //   32: ldc_w 364
    //   35: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   38: ifeq +18 -> 56
    //   41: aload 4
    //   43: aload_0
    //   44: ldc_w 364
    //   47: invokevirtual 41	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   50: invokevirtual 365	org/json/JSONObject:toString	()Ljava/lang/String;
    //   53: invokevirtual 366	com/ted/android/contacts/netparser/model/MenuItem:e	(Ljava/lang/String;)V
    //   56: aload_0
    //   57: ldc 107
    //   59: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   62: ifeq +38 -> 100
    //   65: aload_0
    //   66: ldc 107
    //   68: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 5
    //   73: aload 5
    //   75: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   78: ifne +22 -> 100
    //   81: aload 5
    //   83: ldc_w 368
    //   86: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   89: ifeq +230 -> 319
    //   92: aload 4
    //   94: getstatic 376	com/ted/android/contacts/netparser/model/MenuItem$MenuType:g	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   97: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   100: aload_0
    //   101: ldc -106
    //   103: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   106: ifeq +14 -> 120
    //   109: aload 4
    //   111: aload_0
    //   112: ldc -106
    //   114: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokevirtual 380	com/ted/android/contacts/netparser/model/MenuItem:b	(Ljava/lang/String;)V
    //   120: aload_0
    //   121: ldc_w 382
    //   124: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   127: ifeq +15 -> 142
    //   130: aload 4
    //   132: aload_0
    //   133: ldc_w 382
    //   136: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   139: invokevirtual 384	com/ted/android/contacts/netparser/model/MenuItem:d	(Ljava/lang/String;)V
    //   142: aload_0
    //   143: ldc 37
    //   145: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   148: ifeq +29 -> 177
    //   151: aload_0
    //   152: ldc 37
    //   154: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   157: astore 5
    //   159: aload 5
    //   161: instanceof 126
    //   164: ifeq +354 -> 518
    //   167: aload 4
    //   169: aload 5
    //   171: checkcast 126	java/lang/String
    //   174: invokevirtual 385	com/ted/android/contacts/netparser/model/MenuItem:c	(Ljava/lang/String;)V
    //   177: aload_0
    //   178: ldc_w 387
    //   181: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   184: istore_3
    //   185: iload_3
    //   186: ifeq +30 -> 216
    //   189: aload_0
    //   190: ldc_w 387
    //   193: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   196: astore 5
    //   198: aload 5
    //   200: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   203: ifne +13 -> 216
    //   206: aload 4
    //   208: aload 5
    //   210: invokestatic 390	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   213: invokevirtual 391	com/ted/android/contacts/netparser/model/MenuItem:a	(I)V
    //   216: aload_0
    //   217: ldc_w 393
    //   220: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   223: istore_3
    //   224: iload_3
    //   225: ifeq +33 -> 258
    //   228: aload_0
    //   229: ldc_w 393
    //   232: invokevirtual 67	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   235: astore 5
    //   237: aload 5
    //   239: invokestatic 210	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   242: ifne +16 -> 258
    //   245: aload 4
    //   247: aload 5
    //   249: invokestatic 399	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   252: invokestatic 403	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   255: invokevirtual 406	com/ted/android/contacts/netparser/model/MenuItem:a	(Ljava/lang/Long;)V
    //   258: aload_0
    //   259: ldc_w 408
    //   262: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   265: istore_3
    //   266: iload_3
    //   267: ifeq +49 -> 316
    //   270: aload_0
    //   271: ldc_w 408
    //   274: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   277: astore_0
    //   278: aload_0
    //   279: instanceof 77
    //   282: ifeq +34 -> 316
    //   285: aload_0
    //   286: checkcast 77	org/json/JSONArray
    //   289: astore_0
    //   290: new 79	java/util/ArrayList
    //   293: dup
    //   294: invokespecial 80	java/util/ArrayList:<init>	()V
    //   297: astore 5
    //   299: iload_2
    //   300: istore_1
    //   301: iload_1
    //   302: aload_0
    //   303: invokevirtual 84	org/json/JSONArray:length	()I
    //   306: if_icmplt +369 -> 675
    //   309: aload 4
    //   311: aload 5
    //   313: invokevirtual 409	com/ted/android/contacts/netparser/model/MenuItem:a	(Ljava/util/ArrayList;)V
    //   316: aload 4
    //   318: areturn
    //   319: aload 5
    //   321: ldc_w 411
    //   324: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   327: ifeq +29 -> 356
    //   330: aload 4
    //   332: getstatic 413	com/ted/android/contacts/netparser/model/MenuItem$MenuType:e	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   335: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   338: goto -238 -> 100
    //   341: astore_0
    //   342: getstatic 19	apj:c	Ljava/lang/String;
    //   345: ldc_w 415
    //   348: aload_0
    //   349: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   352: pop
    //   353: aload 4
    //   355: areturn
    //   356: aload 5
    //   358: ldc_w 262
    //   361: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   364: ifeq +14 -> 378
    //   367: aload 4
    //   369: getstatic 417	com/ted/android/contacts/netparser/model/MenuItem$MenuType:b	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   372: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   375: goto -275 -> 100
    //   378: aload 5
    //   380: ldc -118
    //   382: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   385: ifeq +14 -> 399
    //   388: aload 4
    //   390: getstatic 419	com/ted/android/contacts/netparser/model/MenuItem$MenuType:f	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   393: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   396: goto -296 -> 100
    //   399: aload 5
    //   401: ldc 56
    //   403: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   406: ifeq +14 -> 420
    //   409: aload 4
    //   411: getstatic 421	com/ted/android/contacts/netparser/model/MenuItem$MenuType:c	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   414: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   417: goto -317 -> 100
    //   420: aload 5
    //   422: ldc -96
    //   424: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   427: ifeq +14 -> 441
    //   430: aload 4
    //   432: getstatic 423	com/ted/android/contacts/netparser/model/MenuItem$MenuType:d	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   435: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   438: goto -338 -> 100
    //   441: aload 5
    //   443: ldc_w 425
    //   446: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   449: ifeq +14 -> 463
    //   452: aload 4
    //   454: getstatic 427	com/ted/android/contacts/netparser/model/MenuItem$MenuType:h	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   457: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   460: goto -360 -> 100
    //   463: aload 5
    //   465: ldc_w 429
    //   468: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   471: ifeq +14 -> 485
    //   474: aload 4
    //   476: getstatic 431	com/ted/android/contacts/netparser/model/MenuItem$MenuType:a	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   479: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   482: goto -382 -> 100
    //   485: aload 5
    //   487: ldc_w 260
    //   490: invokevirtual 371	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   493: ifeq +14 -> 507
    //   496: aload 4
    //   498: getstatic 433	com/ted/android/contacts/netparser/model/MenuItem$MenuType:i	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   501: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   504: goto -404 -> 100
    //   507: aload 4
    //   509: getstatic 419	com/ted/android/contacts/netparser/model/MenuItem$MenuType:f	Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;
    //   512: invokevirtual 379	com/ted/android/contacts/netparser/model/MenuItem:a	(Lcom/ted/android/contacts/netparser/model/MenuItem$MenuType;)V
    //   515: goto -415 -> 100
    //   518: aload 5
    //   520: checkcast 32	org/json/JSONObject
    //   523: astore 5
    //   525: aload 5
    //   527: ldc_w 262
    //   530: invokevirtual 60	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   533: istore_3
    //   534: iload_3
    //   535: ifeq -358 -> 177
    //   538: aload 5
    //   540: ldc_w 262
    //   543: invokevirtual 75	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   546: astore 5
    //   548: aload 5
    //   550: instanceof 77
    //   553: ifeq -376 -> 177
    //   556: aload 5
    //   558: checkcast 77	org/json/JSONArray
    //   561: astore 5
    //   563: new 240	java/util/LinkedList
    //   566: dup
    //   567: invokespecial 241	java/util/LinkedList:<init>	()V
    //   570: astore 6
    //   572: iconst_0
    //   573: istore_1
    //   574: iload_1
    //   575: aload 5
    //   577: invokevirtual 84	org/json/JSONArray:length	()I
    //   580: if_icmplt +30 -> 610
    //   583: aload 4
    //   585: aload 6
    //   587: invokevirtual 434	com/ted/android/contacts/netparser/model/MenuItem:a	(Ljava/util/LinkedList;)V
    //   590: goto -413 -> 177
    //   593: astore 5
    //   595: getstatic 19	apj:c	Ljava/lang/String;
    //   598: ldc_w 436
    //   601: aload 5
    //   603: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   606: pop
    //   607: goto -430 -> 177
    //   610: aload 5
    //   612: iload_1
    //   613: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   616: invokestatic 439	apj:c	(Lorg/json/JSONObject;)Lcom/ted/android/contacts/netparser/model/SpItem;
    //   619: astore 7
    //   621: aload 7
    //   623: ifnull +11 -> 634
    //   626: aload 6
    //   628: aload 7
    //   630: invokevirtual 290	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   633: pop
    //   634: iload_1
    //   635: iconst_1
    //   636: iadd
    //   637: istore_1
    //   638: goto -64 -> 574
    //   641: astore 5
    //   643: getstatic 19	apj:c	Ljava/lang/String;
    //   646: ldc_w 441
    //   649: aload 5
    //   651: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   654: pop
    //   655: goto -439 -> 216
    //   658: astore 5
    //   660: getstatic 19	apj:c	Ljava/lang/String;
    //   663: ldc_w 443
    //   666: aload 5
    //   668: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   671: pop
    //   672: goto -414 -> 258
    //   675: aload 5
    //   677: aload_0
    //   678: iload_1
    //   679: invokevirtual 268	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   682: invokestatic 293	apj:b	(Lorg/json/JSONObject;)Lcom/ted/android/contacts/netparser/model/MenuItem;
    //   685: invokevirtual 294	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   688: pop
    //   689: iload_1
    //   690: iconst_1
    //   691: iadd
    //   692: istore_1
    //   693: goto -392 -> 301
    //   696: astore_0
    //   697: getstatic 19	apj:c	Ljava/lang/String;
    //   700: ldc_w 445
    //   703: aload_0
    //   704: invokestatic 52	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   707: pop
    //   708: aload 4
    //   710: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	711	0	paramJSONObject	JSONObject
    //   300	393	1	i	int
    //   1	299	2	j	int
    //   184	351	3	bool	boolean
    //   9	700	4	localMenuItem	com.ted.android.contacts.netparser.model.MenuItem
    //   71	505	5	localObject	Object
    //   593	18	5	localException1	Exception
    //   641	9	5	localException2	Exception
    //   658	18	5	localException3	Exception
    //   570	57	6	localLinkedList	java.util.LinkedList
    //   619	10	7	localSpItem	SpItem
    // Exception table:
    //   from	to	target	type
    //   11	31	341	org/json/JSONException
    //   31	56	341	org/json/JSONException
    //   56	100	341	org/json/JSONException
    //   100	120	341	org/json/JSONException
    //   120	142	341	org/json/JSONException
    //   142	177	341	org/json/JSONException
    //   177	185	341	org/json/JSONException
    //   189	216	341	org/json/JSONException
    //   216	224	341	org/json/JSONException
    //   228	258	341	org/json/JSONException
    //   258	266	341	org/json/JSONException
    //   270	299	341	org/json/JSONException
    //   301	316	341	org/json/JSONException
    //   319	338	341	org/json/JSONException
    //   356	375	341	org/json/JSONException
    //   378	396	341	org/json/JSONException
    //   399	417	341	org/json/JSONException
    //   420	438	341	org/json/JSONException
    //   441	460	341	org/json/JSONException
    //   463	482	341	org/json/JSONException
    //   485	504	341	org/json/JSONException
    //   507	515	341	org/json/JSONException
    //   518	534	341	org/json/JSONException
    //   538	572	341	org/json/JSONException
    //   574	590	341	org/json/JSONException
    //   595	607	341	org/json/JSONException
    //   610	621	341	org/json/JSONException
    //   626	634	341	org/json/JSONException
    //   643	655	341	org/json/JSONException
    //   660	672	341	org/json/JSONException
    //   675	689	341	org/json/JSONException
    //   697	708	341	org/json/JSONException
    //   538	572	593	java/lang/Exception
    //   574	590	593	java/lang/Exception
    //   610	621	593	java/lang/Exception
    //   626	634	593	java/lang/Exception
    //   189	216	641	java/lang/Exception
    //   228	258	658	java/lang/Exception
    //   270	299	696	java/lang/Exception
    //   301	316	696	java/lang/Exception
    //   675	689	696	java/lang/Exception
  }
  
  public static SpItem c(JSONObject paramJSONObject)
  {
    for (;;)
    {
      SpItem localSpItem;
      Object localObject;
      HashMap localHashMap;
      try
      {
        localSpItem = new SpItem();
        if (paramJSONObject.has("sp"))
        {
          localObject = paramJSONObject.getString("sp");
          if (((String)localObject).equalsIgnoreCase("cm")) {
            localSpItem.a(SpItem.SpType.a);
          }
        }
        else
        {
          if (paramJSONObject.has("a")) {
            localSpItem.a(paramJSONObject.getString("a"));
          }
          if (paramJSONObject.has("b")) {
            localSpItem.b(paramJSONObject.getString("b"));
          }
          localObject = paramJSONObject.keys();
          localHashMap = new HashMap();
          if (((Iterator)localObject).hasNext()) {
            break label153;
          }
          return localSpItem;
        }
        if (((String)localObject).equalsIgnoreCase("cu"))
        {
          localSpItem.a(SpItem.SpType.b);
          continue;
        }
        localSpItem.a(SpItem.SpType.c);
      }
      catch (JSONException paramJSONObject)
      {
        Log.e(c, "parser spItem error", paramJSONObject);
        return null;
      }
      continue;
      label153:
      String str = (String)((Iterator)localObject).next();
      localHashMap.put(str, (String)paramJSONObject.get(str));
      localSpItem.a(localHashMap);
    }
  }
}

/* Location:
 * Qualified Name:     apj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */