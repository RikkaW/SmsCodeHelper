import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.meizu.android.mms.pdu.MzPduPart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class zf
{
  private final Context a;
  private final Uri b;
  private String c;
  private String d;
  private String e;
  private int f;
  private int g;
  
  public zf(Context paramContext, Uri paramUri)
  {
    if ((paramContext == null) || (paramUri == null)) {
      throw new IllegalArgumentException();
    }
    if (paramUri.getScheme().equals("content")) {
      b(paramContext, paramUri);
    }
    for (;;)
    {
      if ("image/jpg".equals(c)) {
        c = "image/jpeg";
      }
      a = paramContext;
      b = paramUri;
      g();
      return;
      if (paramUri.getScheme().equals("file")) {
        a(paramContext, paramUri);
      }
    }
  }
  
  private void a(Context paramContext, Uri paramUri)
  {
    d = paramUri.getPath();
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    paramUri = MimeTypeMap.getFileExtensionFromUrl(d);
    paramContext = paramUri;
    if (TextUtils.isEmpty(paramUri))
    {
      int i = d.lastIndexOf('.');
      paramContext = paramUri;
      if (i >= 0) {
        paramContext = d.substring(i + 1);
      }
    }
    if (!TextUtils.isEmpty(paramContext)) {}
    for (c = localMimeTypeMap.getMimeTypeFromExtension(paramContext.toLowerCase());; c = null)
    {
      f();
      return;
    }
  }
  
  public static boolean a(String paramString)
  {
    return (paramString != null) && (paramString.equalsIgnoreCase("image/gif"));
  }
  
  /* Error */
  public static byte[] a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong, Uri paramUri, Context paramContext)
  {
    // Byte code:
    //   0: aload 7
    //   2: aload 6
    //   4: invokestatic 111	abp:a	(Landroid/content/Context;Landroid/net/Uri;)I
    //   7: istore 13
    //   9: fconst_1
    //   10: fstore 8
    //   12: aconst_null
    //   13: astore 17
    //   15: aconst_null
    //   16: astore 16
    //   18: aconst_null
    //   19: astore 25
    //   21: aconst_null
    //   22: astore 22
    //   24: aconst_null
    //   25: astore 21
    //   27: aconst_null
    //   28: astore 24
    //   30: aconst_null
    //   31: astore 15
    //   33: aconst_null
    //   34: astore 27
    //   36: aconst_null
    //   37: astore 26
    //   39: aconst_null
    //   40: astore 23
    //   42: iconst_1
    //   43: istore 10
    //   45: new 113	android/graphics/BitmapFactory$Options
    //   48: dup
    //   49: invokespecial 114	android/graphics/BitmapFactory$Options:<init>	()V
    //   52: astore 28
    //   54: aconst_null
    //   55: astore 19
    //   57: iconst_1
    //   58: istore 9
    //   60: aload 16
    //   62: astore 15
    //   64: aload 15
    //   66: astore 18
    //   68: aload 26
    //   70: astore 16
    //   72: aload 15
    //   74: astore 17
    //   76: aload 7
    //   78: invokevirtual 120	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   81: aload 6
    //   83: invokevirtual 126	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   86: astore 20
    //   88: aload 20
    //   90: astore 16
    //   92: aload 23
    //   94: astore 17
    //   96: aload 22
    //   98: astore 18
    //   100: aload 24
    //   102: astore 15
    //   104: aload 16
    //   106: astore 20
    //   108: aload 28
    //   110: iload 10
    //   112: putfield 129	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   115: aload 16
    //   117: aconst_null
    //   118: aload 28
    //   120: invokestatic 135	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   123: astore 15
    //   125: aload 15
    //   127: astore 19
    //   129: aload 19
    //   131: ifnonnull +199 -> 330
    //   134: aconst_null
    //   135: astore 6
    //   137: aload 16
    //   139: ifnull +24 -> 163
    //   142: aload 23
    //   144: astore 17
    //   146: aload 22
    //   148: astore 18
    //   150: aload 24
    //   152: astore 15
    //   154: aload 16
    //   156: astore 20
    //   158: aload 16
    //   160: invokevirtual 140	java/io/InputStream:close	()V
    //   163: aload 16
    //   165: ifnull +8 -> 173
    //   168: aload 16
    //   170: invokevirtual 140	java/io/InputStream:close	()V
    //   173: iconst_0
    //   174: ifeq +11 -> 185
    //   177: new 142	java/lang/NullPointerException
    //   180: dup
    //   181: invokespecial 143	java/lang/NullPointerException:<init>	()V
    //   184: athrow
    //   185: aload 6
    //   187: areturn
    //   188: astore 7
    //   190: aload 23
    //   192: astore 17
    //   194: aload 22
    //   196: astore 18
    //   198: aload 24
    //   200: astore 15
    //   202: aload 16
    //   204: astore 20
    //   206: ldc -111
    //   208: aload 7
    //   210: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   213: aload 7
    //   215: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   218: pop
    //   219: goto -56 -> 163
    //   222: astore 7
    //   224: aload 16
    //   226: astore 6
    //   228: aload 6
    //   230: astore 18
    //   232: aload 17
    //   234: astore 16
    //   236: ldc -111
    //   238: aload 7
    //   240: invokevirtual 154	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   243: aload 7
    //   245: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   248: pop
    //   249: aconst_null
    //   250: astore 7
    //   252: aload 6
    //   254: ifnull +8 -> 262
    //   257: aload 6
    //   259: invokevirtual 140	java/io/InputStream:close	()V
    //   262: aload 7
    //   264: astore 6
    //   266: aload 17
    //   268: ifnull -83 -> 185
    //   271: aload 17
    //   273: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   276: aconst_null
    //   277: areturn
    //   278: astore 6
    //   280: ldc -111
    //   282: aload 6
    //   284: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   287: aload 6
    //   289: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   292: pop
    //   293: aconst_null
    //   294: areturn
    //   295: astore 7
    //   297: ldc -111
    //   299: aload 7
    //   301: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   304: aload 7
    //   306: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   309: pop
    //   310: goto -137 -> 173
    //   313: astore 6
    //   315: ldc -111
    //   317: aload 6
    //   319: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   322: aload 6
    //   324: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   327: pop
    //   328: aconst_null
    //   329: areturn
    //   330: aload 19
    //   332: astore 15
    //   334: iload 10
    //   336: istore 12
    //   338: iload 9
    //   340: istore 11
    //   342: aload 16
    //   344: ifnull +2376 -> 2720
    //   347: aload 23
    //   349: astore 17
    //   351: aload 22
    //   353: astore 18
    //   355: aload 24
    //   357: astore 15
    //   359: aload 16
    //   361: astore 20
    //   363: aload 16
    //   365: invokevirtual 140	java/io/InputStream:close	()V
    //   368: iload 10
    //   370: istore 11
    //   372: iload 9
    //   374: istore 10
    //   376: iload 11
    //   378: istore 9
    //   380: aload 19
    //   382: ifnonnull +10 -> 392
    //   385: iload 10
    //   387: bipush 8
    //   389: if_icmplt +2346 -> 2735
    //   392: aload 19
    //   394: ifnonnull +479 -> 873
    //   397: aload 23
    //   399: astore 17
    //   401: aload 22
    //   403: astore 18
    //   405: aload 24
    //   407: astore 15
    //   409: aload 16
    //   411: astore 20
    //   413: ldc -97
    //   415: iconst_2
    //   416: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   419: ifeq +34 -> 453
    //   422: iload 10
    //   424: bipush 8
    //   426: if_icmplt +27 -> 453
    //   429: aload 23
    //   431: astore 17
    //   433: aload 22
    //   435: astore 18
    //   437: aload 24
    //   439: astore 15
    //   441: aload 16
    //   443: astore 20
    //   445: ldc -111
    //   447: ldc -91
    //   449: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   452: pop
    //   453: aconst_null
    //   454: astore 6
    //   456: aload 16
    //   458: ifnull +8 -> 466
    //   461: aload 16
    //   463: invokevirtual 140	java/io/InputStream:close	()V
    //   466: iconst_0
    //   467: ifeq -282 -> 185
    //   470: new 142	java/lang/NullPointerException
    //   473: dup
    //   474: invokespecial 143	java/lang/NullPointerException:<init>	()V
    //   477: athrow
    //   478: astore 6
    //   480: ldc -111
    //   482: aload 6
    //   484: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   487: aload 6
    //   489: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   492: pop
    //   493: aconst_null
    //   494: areturn
    //   495: astore 29
    //   497: aload 23
    //   499: astore 17
    //   501: aload 22
    //   503: astore 18
    //   505: aload 24
    //   507: astore 15
    //   509: aload 16
    //   511: astore 20
    //   513: ldc -111
    //   515: aload 29
    //   517: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   520: aload 29
    //   522: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   525: pop
    //   526: iload 10
    //   528: istore 11
    //   530: iload 9
    //   532: istore 10
    //   534: iload 11
    //   536: istore 9
    //   538: goto -158 -> 380
    //   541: astore 15
    //   543: ldc -111
    //   545: new 171	java/lang/StringBuilder
    //   548: dup
    //   549: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   552: ldc -82
    //   554: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: iload 10
    //   559: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   562: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   565: invokestatic 187	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   568: pop
    //   569: iload 10
    //   571: iconst_2
    //   572: imul
    //   573: istore 10
    //   575: iload 9
    //   577: iconst_1
    //   578: iadd
    //   579: istore 9
    //   581: aload 19
    //   583: astore 15
    //   585: iload 10
    //   587: istore 12
    //   589: iload 9
    //   591: istore 11
    //   593: aload 16
    //   595: ifnull +2125 -> 2720
    //   598: aload 23
    //   600: astore 17
    //   602: aload 22
    //   604: astore 18
    //   606: aload 24
    //   608: astore 15
    //   610: aload 16
    //   612: astore 20
    //   614: aload 16
    //   616: invokevirtual 140	java/io/InputStream:close	()V
    //   619: iload 9
    //   621: istore 11
    //   623: iload 10
    //   625: istore 9
    //   627: iload 11
    //   629: istore 10
    //   631: goto -251 -> 380
    //   634: astore 29
    //   636: aload 23
    //   638: astore 17
    //   640: aload 22
    //   642: astore 18
    //   644: aload 24
    //   646: astore 15
    //   648: aload 16
    //   650: astore 20
    //   652: ldc -111
    //   654: aload 29
    //   656: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   659: aload 29
    //   661: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   664: pop
    //   665: iload 10
    //   667: istore 11
    //   669: iload 9
    //   671: istore 10
    //   673: iload 11
    //   675: istore 9
    //   677: goto -297 -> 380
    //   680: astore 7
    //   682: aload 16
    //   684: ifnull +24 -> 708
    //   687: aload 23
    //   689: astore 17
    //   691: aload 22
    //   693: astore 18
    //   695: aload 24
    //   697: astore 15
    //   699: aload 16
    //   701: astore 20
    //   703: aload 16
    //   705: invokevirtual 140	java/io/InputStream:close	()V
    //   708: aload 23
    //   710: astore 17
    //   712: aload 22
    //   714: astore 18
    //   716: aload 24
    //   718: astore 15
    //   720: aload 16
    //   722: astore 20
    //   724: aload 7
    //   726: athrow
    //   727: astore 6
    //   729: aload 18
    //   731: astore 15
    //   733: aload 16
    //   735: astore 20
    //   737: ldc -111
    //   739: aload 6
    //   741: invokevirtual 188	java/lang/OutOfMemoryError:getMessage	()Ljava/lang/String;
    //   744: aload 6
    //   746: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   749: pop
    //   750: aconst_null
    //   751: astore 6
    //   753: aload 16
    //   755: ifnull +8 -> 763
    //   758: aload 16
    //   760: invokevirtual 140	java/io/InputStream:close	()V
    //   763: aload 18
    //   765: ifnull -580 -> 185
    //   768: aload 18
    //   770: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   773: aconst_null
    //   774: areturn
    //   775: astore 6
    //   777: ldc -111
    //   779: aload 6
    //   781: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   784: aload 6
    //   786: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   789: pop
    //   790: aconst_null
    //   791: areturn
    //   792: astore 6
    //   794: aload 23
    //   796: astore 17
    //   798: aload 22
    //   800: astore 18
    //   802: aload 24
    //   804: astore 15
    //   806: aload 16
    //   808: astore 20
    //   810: ldc -111
    //   812: aload 6
    //   814: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   817: aload 6
    //   819: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   822: pop
    //   823: goto -115 -> 708
    //   826: astore 6
    //   828: aload 20
    //   830: astore 16
    //   832: aload 16
    //   834: ifnull +8 -> 842
    //   837: aload 16
    //   839: invokevirtual 140	java/io/InputStream:close	()V
    //   842: aload 15
    //   844: ifnull +8 -> 852
    //   847: aload 15
    //   849: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   852: aload 6
    //   854: athrow
    //   855: astore 7
    //   857: ldc -111
    //   859: aload 7
    //   861: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   864: aload 7
    //   866: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   869: pop
    //   870: goto -404 -> 466
    //   873: aload 23
    //   875: astore 17
    //   877: aload 22
    //   879: astore 18
    //   881: aload 24
    //   883: astore 15
    //   885: aload 16
    //   887: astore 20
    //   889: aload 19
    //   891: invokevirtual 194	android/graphics/Bitmap:getWidth	()I
    //   894: istore 10
    //   896: aload 23
    //   898: astore 17
    //   900: aload 22
    //   902: astore 18
    //   904: aload 24
    //   906: astore 15
    //   908: aload 16
    //   910: astore 20
    //   912: aload 19
    //   914: invokevirtual 197	android/graphics/Bitmap:getHeight	()I
    //   917: istore 9
    //   919: goto +1835 -> 2754
    //   922: aload 23
    //   924: astore 17
    //   926: aload 22
    //   928: astore 18
    //   930: aload 24
    //   932: astore 15
    //   934: aload 16
    //   936: astore 20
    //   938: ldc -97
    //   940: iconst_2
    //   941: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   944: ifeq +101 -> 1045
    //   947: aload 23
    //   949: astore 17
    //   951: aload 22
    //   953: astore 18
    //   955: aload 24
    //   957: astore 15
    //   959: aload 16
    //   961: astore 20
    //   963: ldc -111
    //   965: new 171	java/lang/StringBuilder
    //   968: dup
    //   969: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   972: ldc -57
    //   974: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: iload_2
    //   978: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   981: ldc -55
    //   983: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   986: iload_3
    //   987: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   990: ldc -53
    //   992: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: lload 4
    //   997: invokevirtual 206	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1000: ldc -48
    //   1002: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1005: iload_0
    //   1006: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1009: ldc -46
    //   1011: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: iload_1
    //   1015: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1018: ldc -44
    //   1020: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1023: fload 8
    //   1025: invokevirtual 215	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   1028: ldc -39
    //   1030: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: aload 6
    //   1035: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1038: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1041: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1044: pop
    //   1045: iload 9
    //   1047: istore_1
    //   1048: iload 13
    //   1050: istore 9
    //   1052: iconst_1
    //   1053: istore 12
    //   1055: bipush 95
    //   1057: istore_0
    //   1058: aload 27
    //   1060: astore 7
    //   1062: aload 7
    //   1064: astore 17
    //   1066: aload 7
    //   1068: astore 15
    //   1070: aload 16
    //   1072: astore 20
    //   1074: aload 19
    //   1076: astore 6
    //   1078: iload 10
    //   1080: istore 11
    //   1082: aload 28
    //   1084: getfield 223	android/graphics/BitmapFactory$Options:outWidth	I
    //   1087: iload_2
    //   1088: if_icmpgt +77 -> 1165
    //   1091: aload 7
    //   1093: astore 17
    //   1095: aload 7
    //   1097: astore 15
    //   1099: aload 16
    //   1101: astore 20
    //   1103: aload 19
    //   1105: astore 6
    //   1107: iload 10
    //   1109: istore 11
    //   1111: aload 28
    //   1113: getfield 226	android/graphics/BitmapFactory$Options:outHeight	I
    //   1116: iload_3
    //   1117: if_icmpgt +48 -> 1165
    //   1120: aload 19
    //   1122: astore 18
    //   1124: aload 7
    //   1126: ifnull +299 -> 1425
    //   1129: aload 7
    //   1131: astore 17
    //   1133: aload 7
    //   1135: astore 15
    //   1137: aload 16
    //   1139: astore 20
    //   1141: aload 19
    //   1143: astore 18
    //   1145: aload 19
    //   1147: astore 6
    //   1149: iload 10
    //   1151: istore 11
    //   1153: aload 7
    //   1155: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   1158: i2l
    //   1159: lload 4
    //   1161: lcmp
    //   1162: ifle +263 -> 1425
    //   1165: iload 10
    //   1167: i2f
    //   1168: fload 8
    //   1170: fmul
    //   1171: f2i
    //   1172: istore 14
    //   1174: iload_1
    //   1175: i2f
    //   1176: fload 8
    //   1178: fmul
    //   1179: f2i
    //   1180: istore 13
    //   1182: aload 7
    //   1184: astore 17
    //   1186: aload 7
    //   1188: astore 15
    //   1190: aload 16
    //   1192: astore 20
    //   1194: aload 19
    //   1196: astore 6
    //   1198: iload 10
    //   1200: istore 11
    //   1202: ldc -97
    //   1204: iconst_2
    //   1205: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1208: ifeq +59 -> 1267
    //   1211: aload 7
    //   1213: astore 17
    //   1215: aload 7
    //   1217: astore 15
    //   1219: aload 16
    //   1221: astore 20
    //   1223: aload 19
    //   1225: astore 6
    //   1227: iload 10
    //   1229: istore 11
    //   1231: ldc -111
    //   1233: new 171	java/lang/StringBuilder
    //   1236: dup
    //   1237: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1240: ldc -25
    //   1242: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: iload 14
    //   1247: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1250: ldc -23
    //   1252: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1255: iload 13
    //   1257: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1260: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1263: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1266: pop
    //   1267: aload 7
    //   1269: astore 17
    //   1271: aload 7
    //   1273: astore 15
    //   1275: aload 16
    //   1277: astore 20
    //   1279: aload 19
    //   1281: astore 6
    //   1283: iload 10
    //   1285: istore 11
    //   1287: aload 19
    //   1289: iload 14
    //   1291: iload 13
    //   1293: iconst_0
    //   1294: invokestatic 237	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   1297: astore 19
    //   1299: aload 19
    //   1301: astore 18
    //   1303: aload 19
    //   1305: ifnonnull +120 -> 1425
    //   1308: aload 7
    //   1310: astore 17
    //   1312: aload 7
    //   1314: astore 15
    //   1316: aload 16
    //   1318: astore 20
    //   1320: aload 19
    //   1322: astore 6
    //   1324: iload 10
    //   1326: istore 11
    //   1328: ldc -97
    //   1330: iconst_2
    //   1331: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1334: ifeq +31 -> 1365
    //   1337: aload 7
    //   1339: astore 17
    //   1341: aload 7
    //   1343: astore 15
    //   1345: aload 16
    //   1347: astore 20
    //   1349: aload 19
    //   1351: astore 6
    //   1353: iload 10
    //   1355: istore 11
    //   1357: ldc -111
    //   1359: ldc -17
    //   1361: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1364: pop
    //   1365: aconst_null
    //   1366: astore 6
    //   1368: aload 16
    //   1370: ifnull +8 -> 1378
    //   1373: aload 16
    //   1375: invokevirtual 140	java/io/InputStream:close	()V
    //   1378: aload 7
    //   1380: ifnull -1195 -> 185
    //   1383: aload 7
    //   1385: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   1388: aconst_null
    //   1389: areturn
    //   1390: astore 6
    //   1392: ldc -111
    //   1394: aload 6
    //   1396: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1399: aload 6
    //   1401: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1404: pop
    //   1405: aconst_null
    //   1406: areturn
    //   1407: astore 15
    //   1409: ldc -111
    //   1411: aload 15
    //   1413: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1416: aload 15
    //   1418: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1421: pop
    //   1422: goto -44 -> 1378
    //   1425: iload 9
    //   1427: iconst_1
    //   1428: if_icmpeq +1239 -> 2667
    //   1431: iload 9
    //   1433: ifeq +1234 -> 2667
    //   1436: aload 7
    //   1438: astore 17
    //   1440: aload 7
    //   1442: astore 15
    //   1444: aload 16
    //   1446: astore 20
    //   1448: aload 18
    //   1450: astore 6
    //   1452: iload 10
    //   1454: istore 11
    //   1456: aload 18
    //   1458: iload 9
    //   1460: invokestatic 244	wd:a	(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
    //   1463: astore 18
    //   1465: aload 18
    //   1467: ifnonnull +63 -> 1530
    //   1470: aconst_null
    //   1471: astore 6
    //   1473: aload 16
    //   1475: ifnull +8 -> 1483
    //   1478: aload 16
    //   1480: invokevirtual 140	java/io/InputStream:close	()V
    //   1483: aload 7
    //   1485: ifnull -1300 -> 185
    //   1488: aload 7
    //   1490: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   1493: aconst_null
    //   1494: areturn
    //   1495: astore 6
    //   1497: ldc -111
    //   1499: aload 6
    //   1501: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1504: aload 6
    //   1506: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1509: pop
    //   1510: aconst_null
    //   1511: areturn
    //   1512: astore 15
    //   1514: ldc -111
    //   1516: aload 15
    //   1518: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1521: aload 15
    //   1523: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1526: pop
    //   1527: goto -44 -> 1483
    //   1530: aload 7
    //   1532: astore 17
    //   1534: aload 7
    //   1536: astore 15
    //   1538: aload 16
    //   1540: astore 20
    //   1542: aload 18
    //   1544: astore 6
    //   1546: iload 10
    //   1548: istore 11
    //   1550: aload 18
    //   1552: invokevirtual 194	android/graphics/Bitmap:getWidth	()I
    //   1555: istore 10
    //   1557: aload 7
    //   1559: astore 17
    //   1561: aload 7
    //   1563: astore 15
    //   1565: aload 16
    //   1567: astore 20
    //   1569: aload 18
    //   1571: astore 6
    //   1573: iload 10
    //   1575: istore 11
    //   1577: aload 18
    //   1579: invokevirtual 197	android/graphics/Bitmap:getHeight	()I
    //   1582: istore 13
    //   1584: iload 10
    //   1586: istore_1
    //   1587: iload 13
    //   1589: istore 9
    //   1591: iconst_1
    //   1592: istore 10
    //   1594: aload 7
    //   1596: ifnull +27 -> 1623
    //   1599: aload 7
    //   1601: astore 17
    //   1603: aload 7
    //   1605: astore 15
    //   1607: aload 16
    //   1609: astore 20
    //   1611: iload_0
    //   1612: istore 11
    //   1614: aload 7
    //   1616: astore 6
    //   1618: aload 7
    //   1620: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   1623: aload 7
    //   1625: astore 17
    //   1627: aload 7
    //   1629: astore 15
    //   1631: aload 16
    //   1633: astore 20
    //   1635: iload_0
    //   1636: istore 11
    //   1638: aload 7
    //   1640: astore 6
    //   1642: new 156	java/io/ByteArrayOutputStream
    //   1645: dup
    //   1646: invokespecial 245	java/io/ByteArrayOutputStream:<init>	()V
    //   1649: astore 7
    //   1651: iload_0
    //   1652: istore 11
    //   1654: aload 18
    //   1656: getstatic 251	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   1659: iload_0
    //   1660: aload 7
    //   1662: invokevirtual 255	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   1665: pop
    //   1666: iload_0
    //   1667: istore 11
    //   1669: aload 7
    //   1671: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   1674: istore 13
    //   1676: iload 13
    //   1678: i2l
    //   1679: lload 4
    //   1681: lcmp
    //   1682: ifle +1035 -> 2717
    //   1685: iload_0
    //   1686: istore 11
    //   1688: iload_0
    //   1689: i2l
    //   1690: lload 4
    //   1692: lmul
    //   1693: l2i
    //   1694: iload 13
    //   1696: idiv
    //   1697: istore 13
    //   1699: iload 13
    //   1701: istore_0
    //   1702: iload 13
    //   1704: bipush 50
    //   1706: if_icmpge +1083 -> 2789
    //   1709: bipush 50
    //   1711: istore_0
    //   1712: goto +1077 -> 2789
    //   1715: iload_0
    //   1716: istore 11
    //   1718: ldc -97
    //   1720: iconst_2
    //   1721: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1724: ifeq +32 -> 1756
    //   1727: iload_0
    //   1728: istore 11
    //   1730: ldc -111
    //   1732: new 171	java/lang/StringBuilder
    //   1735: dup
    //   1736: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1739: ldc_w 257
    //   1742: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1745: iload_0
    //   1746: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1749: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1752: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   1755: pop
    //   1756: aload 7
    //   1758: ifnull +11 -> 1769
    //   1761: iload_0
    //   1762: istore 11
    //   1764: aload 7
    //   1766: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   1769: iload_0
    //   1770: istore 11
    //   1772: new 156	java/io/ByteArrayOutputStream
    //   1775: dup
    //   1776: invokespecial 245	java/io/ByteArrayOutputStream:<init>	()V
    //   1779: astore 19
    //   1781: aload 19
    //   1783: astore 17
    //   1785: aload 19
    //   1787: astore 15
    //   1789: aload 16
    //   1791: astore 20
    //   1793: iload_0
    //   1794: istore 11
    //   1796: aload 19
    //   1798: astore 6
    //   1800: aload 18
    //   1802: getstatic 251	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   1805: iload_0
    //   1806: aload 19
    //   1808: invokevirtual 255	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   1811: pop
    //   1812: aload 19
    //   1814: astore 7
    //   1816: aload 7
    //   1818: astore 17
    //   1820: aload 7
    //   1822: astore 15
    //   1824: aload 16
    //   1826: astore 20
    //   1828: iload_0
    //   1829: istore 11
    //   1831: aload 7
    //   1833: astore 6
    //   1835: ldc -111
    //   1837: new 171	java/lang/StringBuilder
    //   1840: dup
    //   1841: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1844: ldc_w 259
    //   1847: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1850: aload 7
    //   1852: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   1855: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1858: ldc_w 261
    //   1861: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1864: iload 12
    //   1866: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1869: ldc_w 263
    //   1872: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1875: iload_0
    //   1876: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1879: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1882: invokestatic 265	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1885: pop
    //   1886: aload 18
    //   1888: astore 19
    //   1890: aload 7
    //   1892: astore 17
    //   1894: aload 7
    //   1896: astore 18
    //   1898: aload 7
    //   1900: astore 15
    //   1902: aload 16
    //   1904: astore 20
    //   1906: ldc -97
    //   1908: iconst_2
    //   1909: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   1912: ifeq +135 -> 2047
    //   1915: aload 7
    //   1917: astore 17
    //   1919: aload 7
    //   1921: astore 18
    //   1923: aload 7
    //   1925: astore 15
    //   1927: aload 16
    //   1929: astore 20
    //   1931: new 171	java/lang/StringBuilder
    //   1934: dup
    //   1935: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   1938: ldc_w 267
    //   1941: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1944: iload 12
    //   1946: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1949: ldc_w 269
    //   1952: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1955: astore 6
    //   1957: aload 7
    //   1959: ifnonnull +458 -> 2417
    //   1962: iconst_0
    //   1963: istore 11
    //   1965: aload 7
    //   1967: astore 17
    //   1969: aload 7
    //   1971: astore 18
    //   1973: aload 7
    //   1975: astore 15
    //   1977: aload 16
    //   1979: astore 20
    //   1981: ldc -111
    //   1983: aload 6
    //   1985: iload 11
    //   1987: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1990: ldc_w 271
    //   1993: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1996: iload_1
    //   1997: i2f
    //   1998: fload 8
    //   2000: fmul
    //   2001: invokevirtual 215	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   2004: ldc_w 273
    //   2007: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2010: iload 9
    //   2012: i2f
    //   2013: fload 8
    //   2015: fmul
    //   2016: invokevirtual 215	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   2019: ldc_w 275
    //   2022: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2025: fload 8
    //   2027: invokevirtual 215	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   2030: ldc_w 277
    //   2033: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2036: iload_0
    //   2037: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2040: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2043: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   2046: pop
    //   2047: iload 12
    //   2049: iconst_1
    //   2050: iadd
    //   2051: istore 12
    //   2053: aload 7
    //   2055: ifnull +751 -> 2806
    //   2058: aload 7
    //   2060: astore 17
    //   2062: aload 7
    //   2064: astore 18
    //   2066: aload 7
    //   2068: astore 15
    //   2070: aload 16
    //   2072: astore 20
    //   2074: aload 7
    //   2076: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   2079: i2l
    //   2080: lload 4
    //   2082: lcmp
    //   2083: ifle +741 -> 2824
    //   2086: goto +720 -> 2806
    //   2089: aload 7
    //   2091: astore 17
    //   2093: aload 7
    //   2095: astore 18
    //   2097: aload 7
    //   2099: astore 15
    //   2101: aload 16
    //   2103: astore 20
    //   2105: aload 19
    //   2107: invokevirtual 280	android/graphics/Bitmap:recycle	()V
    //   2110: aload 7
    //   2112: astore 17
    //   2114: aload 7
    //   2116: astore 18
    //   2118: aload 7
    //   2120: astore 15
    //   2122: aload 16
    //   2124: astore 20
    //   2126: ldc -97
    //   2128: iconst_2
    //   2129: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   2132: ifeq +65 -> 2197
    //   2135: iload 11
    //   2137: ifeq +60 -> 2197
    //   2140: aload 7
    //   2142: astore 17
    //   2144: aload 7
    //   2146: astore 18
    //   2148: aload 7
    //   2150: astore 15
    //   2152: aload 16
    //   2154: astore 20
    //   2156: ldc -111
    //   2158: new 171	java/lang/StringBuilder
    //   2161: dup
    //   2162: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   2165: ldc_w 282
    //   2168: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2171: lload 4
    //   2173: invokevirtual 206	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2176: ldc_w 284
    //   2179: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2182: aload 7
    //   2184: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   2187: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2190: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2193: invokestatic 169	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   2196: pop
    //   2197: iload 11
    //   2199: ifeq +244 -> 2443
    //   2202: aconst_null
    //   2203: astore 15
    //   2205: aload 16
    //   2207: ifnull +8 -> 2215
    //   2210: aload 16
    //   2212: invokevirtual 140	java/io/InputStream:close	()V
    //   2215: aload 15
    //   2217: astore 6
    //   2219: aload 7
    //   2221: ifnull -2036 -> 185
    //   2224: aload 7
    //   2226: invokevirtual 157	java/io/ByteArrayOutputStream:close	()V
    //   2229: aload 15
    //   2231: areturn
    //   2232: astore 6
    //   2234: ldc -111
    //   2236: aload 6
    //   2238: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2241: aload 6
    //   2243: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2246: pop
    //   2247: aload 15
    //   2249: areturn
    //   2250: astore 19
    //   2252: aload 7
    //   2254: astore 17
    //   2256: aload 7
    //   2258: astore 15
    //   2260: aload 16
    //   2262: astore 20
    //   2264: iload_0
    //   2265: istore 11
    //   2267: aload 7
    //   2269: astore 6
    //   2271: ldc -111
    //   2273: aload 19
    //   2275: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2278: aload 19
    //   2280: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2283: pop
    //   2284: goto -661 -> 1623
    //   2287: astore 7
    //   2289: iload 10
    //   2291: istore_0
    //   2292: iload 9
    //   2294: istore 10
    //   2296: iload_0
    //   2297: istore 9
    //   2299: iload 11
    //   2301: istore_0
    //   2302: aload 18
    //   2304: astore 19
    //   2306: aload 6
    //   2308: astore 17
    //   2310: aload 6
    //   2312: astore 18
    //   2314: aload 6
    //   2316: astore 15
    //   2318: aload 16
    //   2320: astore 20
    //   2322: ldc -111
    //   2324: new 171	java/lang/StringBuilder
    //   2327: dup
    //   2328: invokespecial 172	java/lang/StringBuilder:<init>	()V
    //   2331: ldc_w 286
    //   2334: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2337: fload 8
    //   2339: invokevirtual 215	java/lang/StringBuilder:append	(F)Ljava/lang/StringBuilder;
    //   2342: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2345: invokestatic 187	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   2348: pop
    //   2349: iload 10
    //   2351: istore 11
    //   2353: aload 6
    //   2355: astore 7
    //   2357: iload 9
    //   2359: istore 10
    //   2361: iload 11
    //   2363: istore 9
    //   2365: goto -475 -> 1890
    //   2368: astore 6
    //   2370: iload_0
    //   2371: istore 11
    //   2373: ldc -111
    //   2375: aload 6
    //   2377: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2380: aload 6
    //   2382: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2385: pop
    //   2386: goto -617 -> 1769
    //   2389: astore 6
    //   2391: aload 7
    //   2393: astore 6
    //   2395: iload 9
    //   2397: istore 13
    //   2399: aload 18
    //   2401: astore 19
    //   2403: iload 11
    //   2405: istore_0
    //   2406: iload 10
    //   2408: istore 9
    //   2410: iload 13
    //   2412: istore 10
    //   2414: goto -108 -> 2306
    //   2417: aload 7
    //   2419: astore 17
    //   2421: aload 7
    //   2423: astore 18
    //   2425: aload 7
    //   2427: astore 15
    //   2429: aload 16
    //   2431: astore 20
    //   2433: aload 7
    //   2435: invokevirtual 229	java/io/ByteArrayOutputStream:size	()I
    //   2438: istore 11
    //   2440: goto -475 -> 1965
    //   2443: aload 7
    //   2445: astore 17
    //   2447: aload 7
    //   2449: astore 18
    //   2451: aload 7
    //   2453: astore 15
    //   2455: aload 16
    //   2457: astore 20
    //   2459: aload 7
    //   2461: invokevirtual 290	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   2464: astore 6
    //   2466: aload 6
    //   2468: astore 15
    //   2470: goto -265 -> 2205
    //   2473: astore 6
    //   2475: ldc -111
    //   2477: aload 6
    //   2479: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2482: aload 6
    //   2484: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2487: pop
    //   2488: goto -273 -> 2215
    //   2491: astore 6
    //   2493: ldc -111
    //   2495: aload 6
    //   2497: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2500: aload 6
    //   2502: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2505: pop
    //   2506: goto -2244 -> 262
    //   2509: astore 7
    //   2511: ldc -111
    //   2513: aload 7
    //   2515: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2518: aload 7
    //   2520: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2523: pop
    //   2524: goto -1761 -> 763
    //   2527: astore 7
    //   2529: ldc -111
    //   2531: aload 7
    //   2533: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2536: aload 7
    //   2538: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2541: pop
    //   2542: goto -1700 -> 842
    //   2545: astore 7
    //   2547: ldc -111
    //   2549: aload 7
    //   2551: invokevirtual 148	java/io/IOException:getMessage	()Ljava/lang/String;
    //   2554: aload 7
    //   2556: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2559: pop
    //   2560: goto -1708 -> 852
    //   2563: astore 6
    //   2565: aconst_null
    //   2566: astore 16
    //   2568: goto -1736 -> 832
    //   2571: astore 6
    //   2573: aload 16
    //   2575: astore 15
    //   2577: aload 18
    //   2579: astore 16
    //   2581: goto -1749 -> 832
    //   2584: astore 6
    //   2586: aload 7
    //   2588: astore 15
    //   2590: goto -1758 -> 832
    //   2593: astore 6
    //   2595: aconst_null
    //   2596: astore 16
    //   2598: aload 21
    //   2600: astore 18
    //   2602: goto -1873 -> 729
    //   2605: astore 6
    //   2607: aload 21
    //   2609: astore 18
    //   2611: aload 15
    //   2613: astore 16
    //   2615: goto -1886 -> 729
    //   2618: astore 7
    //   2620: aload 17
    //   2622: astore 6
    //   2624: aload 25
    //   2626: astore 17
    //   2628: goto -2400 -> 228
    //   2631: astore 15
    //   2633: aload 7
    //   2635: astore 17
    //   2637: aload 16
    //   2639: astore 6
    //   2641: aload 15
    //   2643: astore 7
    //   2645: goto -2417 -> 228
    //   2648: astore 15
    //   2650: aload 6
    //   2652: astore 19
    //   2654: aload 7
    //   2656: astore 6
    //   2658: iload_1
    //   2659: istore 10
    //   2661: iload 11
    //   2663: istore_1
    //   2664: goto -358 -> 2306
    //   2667: iload 10
    //   2669: istore 11
    //   2671: iload 9
    //   2673: istore 10
    //   2675: iload_1
    //   2676: istore 9
    //   2678: iload 11
    //   2680: istore_1
    //   2681: goto -1087 -> 1594
    //   2684: fload 8
    //   2686: ldc_w 291
    //   2689: fmul
    //   2690: fstore 8
    //   2692: iload 10
    //   2694: istore 13
    //   2696: iload 9
    //   2698: istore 10
    //   2700: iload_1
    //   2701: istore 11
    //   2703: iload 13
    //   2705: istore 9
    //   2707: iload 10
    //   2709: istore_1
    //   2710: iload 11
    //   2712: istore 10
    //   2714: goto -1652 -> 1062
    //   2717: goto -901 -> 1816
    //   2720: iload 12
    //   2722: istore 9
    //   2724: iload 11
    //   2726: istore 10
    //   2728: aload 15
    //   2730: astore 19
    //   2732: goto -2352 -> 380
    //   2735: iload 10
    //   2737: istore 11
    //   2739: aload 16
    //   2741: astore 15
    //   2743: iload 9
    //   2745: istore 10
    //   2747: iload 11
    //   2749: istore 9
    //   2751: goto -2687 -> 64
    //   2754: iload 10
    //   2756: i2f
    //   2757: fload 8
    //   2759: fmul
    //   2760: iload_2
    //   2761: i2f
    //   2762: fcmpl
    //   2763: ifgt +15 -> 2778
    //   2766: iload 9
    //   2768: i2f
    //   2769: fload 8
    //   2771: fmul
    //   2772: iload_3
    //   2773: i2f
    //   2774: fcmpl
    //   2775: ifle -1853 -> 922
    //   2778: fload 8
    //   2780: ldc_w 291
    //   2783: fmul
    //   2784: fstore 8
    //   2786: goto -32 -> 2754
    //   2789: iload 12
    //   2791: iconst_4
    //   2792: if_icmple -1077 -> 1715
    //   2795: bipush 50
    //   2797: iload 12
    //   2799: iconst_4
    //   2800: imul
    //   2801: isub
    //   2802: istore_0
    //   2803: goto -1088 -> 1715
    //   2806: iconst_1
    //   2807: istore 11
    //   2809: iload 11
    //   2811: ifeq -722 -> 2089
    //   2814: iload 12
    //   2816: bipush 8
    //   2818: if_icmplt -134 -> 2684
    //   2821: goto -732 -> 2089
    //   2824: iconst_0
    //   2825: istore 11
    //   2827: goto -18 -> 2809
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2830	0	paramInt1	int
    //   0	2830	1	paramInt2	int
    //   0	2830	2	paramInt3	int
    //   0	2830	3	paramInt4	int
    //   0	2830	4	paramLong	long
    //   0	2830	6	paramUri	Uri
    //   0	2830	7	paramContext	Context
    //   10	2775	8	f1	float
    //   58	2709	9	i	int
    //   43	2712	10	j	int
    //   340	2486	11	k	int
    //   336	2483	12	m	int
    //   7	2697	13	n	int
    //   1172	118	14	i1	int
    //   31	477	15	localObject1	Object
    //   541	1	15	localOutOfMemoryError1	OutOfMemoryError
    //   583	761	15	localObject2	Object
    //   1407	10	15	localIOException1	IOException
    //   1442	1	15	localContext	Context
    //   1512	10	15	localIOException2	IOException
    //   1536	1076	15	localObject3	Object
    //   2631	11	15	localFileNotFoundException	FileNotFoundException
    //   2648	81	15	localOutOfMemoryError2	OutOfMemoryError
    //   2741	1	15	localObject4	Object
    //   16	2724	16	localObject5	Object
    //   13	2623	17	localObject6	Object
    //   66	2544	18	localObject7	Object
    //   55	2051	19	localObject8	Object
    //   2250	29	19	localIOException3	IOException
    //   2304	427	19	localObject9	Object
    //   86	2372	20	localObject10	Object
    //   25	2583	21	localObject11	Object
    //   22	930	22	localObject12	Object
    //   40	908	23	localObject13	Object
    //   28	928	24	localObject14	Object
    //   19	2606	25	localObject15	Object
    //   37	32	26	localObject16	Object
    //   34	1025	27	localObject17	Object
    //   52	1060	28	localOptions	BitmapFactory.Options
    //   495	26	29	localIOException4	IOException
    //   634	26	29	localIOException5	IOException
    // Exception table:
    //   from	to	target	type
    //   158	163	188	java/io/IOException
    //   108	115	222	java/io/FileNotFoundException
    //   158	163	222	java/io/FileNotFoundException
    //   206	219	222	java/io/FileNotFoundException
    //   363	368	222	java/io/FileNotFoundException
    //   413	422	222	java/io/FileNotFoundException
    //   445	453	222	java/io/FileNotFoundException
    //   513	526	222	java/io/FileNotFoundException
    //   614	619	222	java/io/FileNotFoundException
    //   652	665	222	java/io/FileNotFoundException
    //   703	708	222	java/io/FileNotFoundException
    //   724	727	222	java/io/FileNotFoundException
    //   810	823	222	java/io/FileNotFoundException
    //   889	896	222	java/io/FileNotFoundException
    //   912	919	222	java/io/FileNotFoundException
    //   938	947	222	java/io/FileNotFoundException
    //   963	1045	222	java/io/FileNotFoundException
    //   1082	1091	222	java/io/FileNotFoundException
    //   1111	1120	222	java/io/FileNotFoundException
    //   1153	1165	222	java/io/FileNotFoundException
    //   1202	1211	222	java/io/FileNotFoundException
    //   1231	1267	222	java/io/FileNotFoundException
    //   1287	1299	222	java/io/FileNotFoundException
    //   1328	1337	222	java/io/FileNotFoundException
    //   1357	1365	222	java/io/FileNotFoundException
    //   1456	1465	222	java/io/FileNotFoundException
    //   1550	1557	222	java/io/FileNotFoundException
    //   1577	1584	222	java/io/FileNotFoundException
    //   1618	1623	222	java/io/FileNotFoundException
    //   1642	1651	222	java/io/FileNotFoundException
    //   1800	1812	222	java/io/FileNotFoundException
    //   1835	1886	222	java/io/FileNotFoundException
    //   1906	1915	222	java/io/FileNotFoundException
    //   1931	1957	222	java/io/FileNotFoundException
    //   1981	2047	222	java/io/FileNotFoundException
    //   2074	2086	222	java/io/FileNotFoundException
    //   2105	2110	222	java/io/FileNotFoundException
    //   2126	2135	222	java/io/FileNotFoundException
    //   2156	2197	222	java/io/FileNotFoundException
    //   2271	2284	222	java/io/FileNotFoundException
    //   2322	2349	222	java/io/FileNotFoundException
    //   2433	2440	222	java/io/FileNotFoundException
    //   2459	2466	222	java/io/FileNotFoundException
    //   271	276	278	java/io/IOException
    //   168	173	295	java/io/IOException
    //   177	185	313	java/io/IOException
    //   470	478	478	java/io/IOException
    //   363	368	495	java/io/IOException
    //   115	125	541	java/lang/OutOfMemoryError
    //   614	619	634	java/io/IOException
    //   115	125	680	finally
    //   543	569	680	finally
    //   108	115	727	java/lang/OutOfMemoryError
    //   158	163	727	java/lang/OutOfMemoryError
    //   206	219	727	java/lang/OutOfMemoryError
    //   363	368	727	java/lang/OutOfMemoryError
    //   413	422	727	java/lang/OutOfMemoryError
    //   445	453	727	java/lang/OutOfMemoryError
    //   513	526	727	java/lang/OutOfMemoryError
    //   614	619	727	java/lang/OutOfMemoryError
    //   652	665	727	java/lang/OutOfMemoryError
    //   703	708	727	java/lang/OutOfMemoryError
    //   724	727	727	java/lang/OutOfMemoryError
    //   810	823	727	java/lang/OutOfMemoryError
    //   889	896	727	java/lang/OutOfMemoryError
    //   912	919	727	java/lang/OutOfMemoryError
    //   938	947	727	java/lang/OutOfMemoryError
    //   963	1045	727	java/lang/OutOfMemoryError
    //   1906	1915	727	java/lang/OutOfMemoryError
    //   1931	1957	727	java/lang/OutOfMemoryError
    //   1981	2047	727	java/lang/OutOfMemoryError
    //   2074	2086	727	java/lang/OutOfMemoryError
    //   2105	2110	727	java/lang/OutOfMemoryError
    //   2126	2135	727	java/lang/OutOfMemoryError
    //   2156	2197	727	java/lang/OutOfMemoryError
    //   2322	2349	727	java/lang/OutOfMemoryError
    //   2433	2440	727	java/lang/OutOfMemoryError
    //   2459	2466	727	java/lang/OutOfMemoryError
    //   768	773	775	java/io/IOException
    //   703	708	792	java/io/IOException
    //   108	115	826	finally
    //   158	163	826	finally
    //   206	219	826	finally
    //   363	368	826	finally
    //   413	422	826	finally
    //   445	453	826	finally
    //   513	526	826	finally
    //   614	619	826	finally
    //   652	665	826	finally
    //   703	708	826	finally
    //   724	727	826	finally
    //   737	750	826	finally
    //   810	823	826	finally
    //   889	896	826	finally
    //   912	919	826	finally
    //   938	947	826	finally
    //   963	1045	826	finally
    //   1082	1091	826	finally
    //   1111	1120	826	finally
    //   1153	1165	826	finally
    //   1202	1211	826	finally
    //   1231	1267	826	finally
    //   1287	1299	826	finally
    //   1328	1337	826	finally
    //   1357	1365	826	finally
    //   1456	1465	826	finally
    //   1550	1557	826	finally
    //   1577	1584	826	finally
    //   1618	1623	826	finally
    //   1642	1651	826	finally
    //   1800	1812	826	finally
    //   1835	1886	826	finally
    //   1906	1915	826	finally
    //   1931	1957	826	finally
    //   1981	2047	826	finally
    //   2074	2086	826	finally
    //   2105	2110	826	finally
    //   2126	2135	826	finally
    //   2156	2197	826	finally
    //   2271	2284	826	finally
    //   2322	2349	826	finally
    //   2433	2440	826	finally
    //   2459	2466	826	finally
    //   461	466	855	java/io/IOException
    //   1383	1388	1390	java/io/IOException
    //   1373	1378	1407	java/io/IOException
    //   1488	1493	1495	java/io/IOException
    //   1478	1483	1512	java/io/IOException
    //   2224	2229	2232	java/io/IOException
    //   1618	1623	2250	java/io/IOException
    //   1618	1623	2287	java/lang/OutOfMemoryError
    //   1642	1651	2287	java/lang/OutOfMemoryError
    //   1800	1812	2287	java/lang/OutOfMemoryError
    //   1835	1886	2287	java/lang/OutOfMemoryError
    //   2271	2284	2287	java/lang/OutOfMemoryError
    //   1764	1769	2368	java/io/IOException
    //   1654	1666	2389	java/lang/OutOfMemoryError
    //   1669	1676	2389	java/lang/OutOfMemoryError
    //   1688	1699	2389	java/lang/OutOfMemoryError
    //   1718	1727	2389	java/lang/OutOfMemoryError
    //   1730	1756	2389	java/lang/OutOfMemoryError
    //   1764	1769	2389	java/lang/OutOfMemoryError
    //   1772	1781	2389	java/lang/OutOfMemoryError
    //   2373	2386	2389	java/lang/OutOfMemoryError
    //   2210	2215	2473	java/io/IOException
    //   257	262	2491	java/io/IOException
    //   758	763	2509	java/io/IOException
    //   837	842	2527	java/io/IOException
    //   847	852	2545	java/io/IOException
    //   45	54	2563	finally
    //   76	88	2571	finally
    //   236	249	2571	finally
    //   1654	1666	2584	finally
    //   1669	1676	2584	finally
    //   1688	1699	2584	finally
    //   1718	1727	2584	finally
    //   1730	1756	2584	finally
    //   1764	1769	2584	finally
    //   1772	1781	2584	finally
    //   2373	2386	2584	finally
    //   45	54	2593	java/lang/OutOfMemoryError
    //   76	88	2605	java/lang/OutOfMemoryError
    //   45	54	2618	java/io/FileNotFoundException
    //   76	88	2618	java/io/FileNotFoundException
    //   1654	1666	2631	java/io/FileNotFoundException
    //   1669	1676	2631	java/io/FileNotFoundException
    //   1688	1699	2631	java/io/FileNotFoundException
    //   1718	1727	2631	java/io/FileNotFoundException
    //   1730	1756	2631	java/io/FileNotFoundException
    //   1764	1769	2631	java/io/FileNotFoundException
    //   1772	1781	2631	java/io/FileNotFoundException
    //   2373	2386	2631	java/io/FileNotFoundException
    //   1082	1091	2648	java/lang/OutOfMemoryError
    //   1111	1120	2648	java/lang/OutOfMemoryError
    //   1153	1165	2648	java/lang/OutOfMemoryError
    //   1202	1211	2648	java/lang/OutOfMemoryError
    //   1231	1267	2648	java/lang/OutOfMemoryError
    //   1287	1299	2648	java/lang/OutOfMemoryError
    //   1328	1337	2648	java/lang/OutOfMemoryError
    //   1357	1365	2648	java/lang/OutOfMemoryError
    //   1456	1465	2648	java/lang/OutOfMemoryError
    //   1550	1557	2648	java/lang/OutOfMemoryError
    //   1577	1584	2648	java/lang/OutOfMemoryError
  }
  
  private void b(Context paramContext, Uri paramUri)
  {
    Object localObject = paramContext.getContentResolver();
    Cursor localCursor = ((ContentResolver)localObject).query(paramUri, null, null, null, null);
    e = null;
    if (localCursor == null) {
      throw new IllegalArgumentException("Query on " + paramUri + " returns null result.");
    }
    try
    {
      if ((localCursor.getCount() != 1) || (!localCursor.moveToFirst())) {
        throw new IllegalArgumentException("Query on " + paramUri + " returns 0 or multiple rows.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      Log.e("Mms/image", "initFromContentUri couldn't load image uri: " + paramUri, paramContext);
      localCursor.close();
      return;
      if (lk.b(paramUri))
      {
        localObject = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
        paramContext = (Context)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject)) {
          paramContext = localCursor.getString(localCursor.getColumnIndexOrThrow("fn"));
        }
        c = localCursor.getString(localCursor.getColumnIndexOrThrow("ct"));
      }
      for (;;)
      {
        d = paramContext;
        if (e == null) {
          f();
        }
        localCursor.close();
        return;
        paramContext = paramUri.getPath();
        try
        {
          c = localCursor.getString(localCursor.getColumnIndexOrThrow("mime_type"));
          int i = localCursor.getColumnIndex("_display_name");
          if (i != -1)
          {
            e = localCursor.getString(i);
            if (!TextUtils.isEmpty(e)) {
              e = e.replace(' ', '_');
            }
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException1)
        {
          for (;;)
          {
            try
            {
              c = localCursor.getString(localCursor.getColumnIndexOrThrow("mimetype"));
            }
            catch (IllegalArgumentException localIllegalArgumentException2)
            {
              c = ((ContentResolver)localObject).getType(paramUri);
              Log.v("Mms/image", "initFromContentUri: " + paramUri + ", getType => " + c);
            }
          }
        }
      }
    }
    finally
    {
      localCursor.close();
      throw paramContext;
    }
  }
  
  public static boolean b(String paramString)
  {
    return ((paramString != null) && (paramString.equalsIgnoreCase("image/tif"))) || (paramString.equalsIgnoreCase("image/tiff"));
  }
  
  private void f()
  {
    e = d.substring(d.lastIndexOf('/') + 1);
    if ((e.startsWith(".")) && (e.length() > 1)) {
      e = e.substring(1);
    }
    e = e.replace(' ', '_');
  }
  
  private void g()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localInputStream = a.getContentResolver().openInputStream(b);
        localObject1 = localInputStream;
        localObject3 = localInputStream;
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localObject1 = localInputStream;
        localObject3 = localInputStream;
        inJustDecodeBounds = true;
        localObject1 = localInputStream;
        localObject3 = localInputStream;
        BitmapFactory.decodeStream(localInputStream, null, localOptions);
        localObject1 = localInputStream;
        localObject3 = localInputStream;
        f = outWidth;
        localObject1 = localInputStream;
        localObject3 = localInputStream;
        g = outHeight;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        InputStream localInputStream;
        localObject3 = localIOException1;
        Log.e("Mms/image", "IOException caught while opening stream", localFileNotFoundException);
        if (localIOException1 == null) {
          continue;
        }
        try
        {
          localIOException1.close();
          return;
        }
        catch (IOException localIOException2)
        {
          Log.e("Mms/image", "IOException caught while closing stream", localIOException2);
          return;
        }
      }
      finally
      {
        if (localObject3 == null) {
          break label144;
        }
      }
      try
      {
        localInputStream.close();
        return;
      }
      catch (IOException localIOException1)
      {
        Log.e("Mms/image", "IOException caught while closing stream", localIOException1);
        return;
      }
    }
    try
    {
      ((InputStream)localObject3).close();
      label144:
      throw ((Throwable)localObject2);
    }
    catch (IOException localIOException3)
    {
      for (;;)
      {
        Log.e("Mms/image", "IOException caught while closing stream", localIOException3);
      }
    }
  }
  
  public MzPduPart a(int paramInt1, int paramInt2, long paramLong)
  {
    MzPduPart localMzPduPart = new MzPduPart();
    byte[] arrayOfByte = a(f, g, paramInt1, paramInt2, paramLong, b, a);
    if (arrayOfByte == null) {
      return null;
    }
    localMzPduPart.setData(arrayOfByte);
    localMzPduPart.setContentType("image/jpeg".getBytes());
    return localMzPduPart;
  }
  
  public String a()
  {
    return c;
  }
  
  public String b()
  {
    return e;
  }
  
  public String c()
  {
    return d;
  }
  
  public int d()
  {
    return f;
  }
  
  public int e()
  {
    return g;
  }
}

/* Location:
 * Qualified Name:     zf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */