import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Entity.NamedContentValues;
import android.content.EntityIterator;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Profile;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContactsEntity;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class acu
{
  private static final String[] a = { "_display_name" };
  private static final int b = ga.f();
  private Context c;
  private String d = "";
  private Set<acu.b> e = new HashSet();
  private Set<acu.c> f = new HashSet();
  private Set<acu.i> g = new HashSet();
  private Set<acu.g> h = new HashSet();
  private Set<acu.h> i = new HashSet();
  private Set<acu.f> j = new HashSet();
  private Set<acu.d> k = new HashSet();
  private Set<acu.e> l = new HashSet();
  private Set<acu.a> m = new HashSet();
  private afc n;
  private afk o;
  
  public acu(Context paramContext)
  {
    c = paramContext;
  }
  
  /* Error */
  private static final Uri a(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_2
    //   4: iconst_0
    //   5: invokestatic 110	acu:a	(Landroid/net/Uri;Z)Landroid/net/Uri;
    //   8: astore 8
    //   10: getstatic 115	com/android/mms/TempFileProvider:e	Landroid/net/Uri;
    //   13: astore 13
    //   15: invokestatic 120	wd:f	()Z
    //   18: ifeq +324 -> 342
    //   21: aload_3
    //   22: astore_0
    //   23: aload_3
    //   24: ldc 122
    //   26: invokevirtual 126	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   29: ifne +23 -> 52
    //   32: new 128	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   39: aload_3
    //   40: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: ldc 122
    //   45: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore_0
    //   52: new 128	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   59: ldc -117
    //   61: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokestatic 145	java/lang/System:currentTimeMillis	()J
    //   67: invokevirtual 148	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   70: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore_3
    //   74: new 150	java/io/File
    //   77: dup
    //   78: aload_3
    //   79: invokespecial 153	java/io/File:<init>	(Ljava/lang/String;)V
    //   82: invokevirtual 156	java/io/File:mkdir	()Z
    //   85: pop
    //   86: new 128	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   93: aload_3
    //   94: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: ldc -98
    //   99: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_0
    //   103: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: invokestatic 161	wd:m	(Ljava/lang/String;)Landroid/net/Uri;
    //   112: astore 13
    //   114: aload_1
    //   115: aload 13
    //   117: ldc -93
    //   119: invokevirtual 169	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;Ljava/lang/String;)Ljava/io/OutputStream;
    //   122: astore_0
    //   123: sipush 1024
    //   126: newarray <illegal type>
    //   128: astore 14
    //   130: aload_1
    //   131: aload 8
    //   133: invokevirtual 173	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   136: astore_3
    //   137: aload_3
    //   138: astore 11
    //   140: aload_3
    //   141: astore 10
    //   143: aload_3
    //   144: astore 7
    //   146: aload_0
    //   147: astore 9
    //   149: aload_3
    //   150: astore 12
    //   152: aload_3
    //   153: invokevirtual 178	java/io/InputStream:available	()I
    //   156: istore 5
    //   158: aload_3
    //   159: astore 11
    //   161: aload_3
    //   162: astore 10
    //   164: aload_3
    //   165: astore 7
    //   167: aload_0
    //   168: astore 9
    //   170: aload_3
    //   171: astore 12
    //   173: getstatic 68	acu:b	I
    //   176: istore 6
    //   178: aload_3
    //   179: astore 8
    //   181: iload 5
    //   183: iload 6
    //   185: if_icmple +59 -> 244
    //   188: aload_3
    //   189: astore 8
    //   191: aload_3
    //   192: ifnull +22 -> 214
    //   195: aload_3
    //   196: astore 11
    //   198: aload_3
    //   199: astore 10
    //   201: aload_3
    //   202: astore 7
    //   204: aload_0
    //   205: astore 9
    //   207: aload_3
    //   208: invokevirtual 181	java/io/InputStream:close	()V
    //   211: aconst_null
    //   212: astore 8
    //   214: aload 8
    //   216: astore 11
    //   218: aload 8
    //   220: astore 10
    //   222: aload 8
    //   224: astore 7
    //   226: aload_0
    //   227: astore 9
    //   229: aload 8
    //   231: astore 12
    //   233: aload_1
    //   234: aload_2
    //   235: iconst_1
    //   236: invokestatic 110	acu:a	(Landroid/net/Uri;Z)Landroid/net/Uri;
    //   239: invokevirtual 173	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   242: astore 8
    //   244: aload 8
    //   246: astore 11
    //   248: aload 8
    //   250: astore 10
    //   252: aload 8
    //   254: astore 7
    //   256: aload_0
    //   257: astore 9
    //   259: aload 8
    //   261: astore 12
    //   263: aload 8
    //   265: aload 14
    //   267: invokevirtual 185	java/io/InputStream:read	([B)I
    //   270: istore 5
    //   272: iload 5
    //   274: ifle +192 -> 466
    //   277: aload 8
    //   279: astore 11
    //   281: aload 8
    //   283: astore 10
    //   285: aload 8
    //   287: astore 7
    //   289: aload_0
    //   290: astore 9
    //   292: aload 8
    //   294: astore 12
    //   296: aload_0
    //   297: aload 14
    //   299: iconst_0
    //   300: iload 5
    //   302: invokevirtual 191	java/io/OutputStream:write	([BII)V
    //   305: goto -61 -> 244
    //   308: astore_1
    //   309: aload_0
    //   310: astore_1
    //   311: aload 11
    //   313: astore_0
    //   314: ldc -63
    //   316: ldc -61
    //   318: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   321: pop
    //   322: aload_0
    //   323: ifnull +7 -> 330
    //   326: aload_0
    //   327: invokevirtual 181	java/io/InputStream:close	()V
    //   330: aload_1
    //   331: ifnull +367 -> 698
    //   334: aload_1
    //   335: invokevirtual 201	java/io/OutputStream:close	()V
    //   338: aconst_null
    //   339: astore_1
    //   340: aload_1
    //   341: areturn
    //   342: new 203	android/content/ContentValues
    //   345: dup
    //   346: invokespecial 204	android/content/ContentValues:<init>	()V
    //   349: astore_0
    //   350: aload_0
    //   351: ldc -50
    //   353: aload_3
    //   354: invokevirtual 210	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   357: aload_1
    //   358: aload 13
    //   360: aload_0
    //   361: aconst_null
    //   362: aconst_null
    //   363: invokevirtual 214	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   366: pop
    //   367: goto -253 -> 114
    //   370: astore_0
    //   371: aconst_null
    //   372: astore_0
    //   373: aconst_null
    //   374: astore_1
    //   375: goto -61 -> 314
    //   378: astore 8
    //   380: aload_3
    //   381: astore 11
    //   383: aload_3
    //   384: astore 10
    //   386: aload_3
    //   387: astore 7
    //   389: aload_0
    //   390: astore 9
    //   392: aload_3
    //   393: astore 12
    //   395: ldc -63
    //   397: new 128	java/lang/StringBuilder
    //   400: dup
    //   401: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   404: ldc -40
    //   406: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: aload 8
    //   411: invokevirtual 219	java/io/IOException:getMessage	()Ljava/lang/String;
    //   414: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   423: pop
    //   424: aload_3
    //   425: astore 8
    //   427: goto -213 -> 214
    //   430: astore_1
    //   431: aload 10
    //   433: astore 7
    //   435: aload_0
    //   436: astore 9
    //   438: ldc -63
    //   440: ldc -35
    //   442: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   445: pop
    //   446: aload 10
    //   448: ifnull +8 -> 456
    //   451: aload 10
    //   453: invokevirtual 181	java/io/InputStream:close	()V
    //   456: aload_0
    //   457: ifnull +241 -> 698
    //   460: aload_0
    //   461: invokevirtual 201	java/io/OutputStream:close	()V
    //   464: aconst_null
    //   465: areturn
    //   466: aload 8
    //   468: ifnull +8 -> 476
    //   471: aload 8
    //   473: invokevirtual 181	java/io/InputStream:close	()V
    //   476: aload 13
    //   478: astore_1
    //   479: aload_0
    //   480: ifnull -140 -> 340
    //   483: aload_0
    //   484: invokevirtual 201	java/io/OutputStream:close	()V
    //   487: aload 13
    //   489: areturn
    //   490: astore_0
    //   491: aload_0
    //   492: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   495: aload 13
    //   497: areturn
    //   498: astore_1
    //   499: aload_1
    //   500: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   503: goto -27 -> 476
    //   506: astore_0
    //   507: aload_0
    //   508: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   511: goto -181 -> 330
    //   514: astore_0
    //   515: aload_0
    //   516: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   519: aconst_null
    //   520: areturn
    //   521: astore_1
    //   522: aload_1
    //   523: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   526: goto -70 -> 456
    //   529: astore_0
    //   530: aload_0
    //   531: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   534: aconst_null
    //   535: areturn
    //   536: astore_0
    //   537: aconst_null
    //   538: astore 12
    //   540: aconst_null
    //   541: astore_0
    //   542: aload 12
    //   544: astore 7
    //   546: aload_0
    //   547: astore 9
    //   549: ldc -63
    //   551: ldc -30
    //   553: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   556: pop
    //   557: aload 12
    //   559: ifnull +8 -> 567
    //   562: aload 12
    //   564: invokevirtual 181	java/io/InputStream:close	()V
    //   567: aload_0
    //   568: ifnull +130 -> 698
    //   571: aload_0
    //   572: invokevirtual 201	java/io/OutputStream:close	()V
    //   575: aconst_null
    //   576: areturn
    //   577: astore_1
    //   578: aload_1
    //   579: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   582: goto -15 -> 567
    //   585: astore_0
    //   586: aload_0
    //   587: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   590: aconst_null
    //   591: areturn
    //   592: astore_1
    //   593: aconst_null
    //   594: astore_0
    //   595: aload 7
    //   597: astore_2
    //   598: aload_2
    //   599: ifnull +7 -> 606
    //   602: aload_2
    //   603: invokevirtual 181	java/io/InputStream:close	()V
    //   606: aload_0
    //   607: ifnull +7 -> 614
    //   610: aload_0
    //   611: invokevirtual 201	java/io/OutputStream:close	()V
    //   614: aload_1
    //   615: athrow
    //   616: astore_2
    //   617: aload_2
    //   618: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   621: goto -15 -> 606
    //   624: astore_0
    //   625: aload_0
    //   626: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   629: goto -15 -> 614
    //   632: astore_1
    //   633: aload 7
    //   635: astore_2
    //   636: goto -38 -> 598
    //   639: astore_1
    //   640: aload 7
    //   642: astore_2
    //   643: aload 9
    //   645: astore_0
    //   646: goto -48 -> 598
    //   649: astore_2
    //   650: aload_1
    //   651: astore_3
    //   652: aload_2
    //   653: astore_1
    //   654: aload_0
    //   655: astore_2
    //   656: aload_3
    //   657: astore_0
    //   658: goto -60 -> 598
    //   661: astore_1
    //   662: aconst_null
    //   663: astore 12
    //   665: goto -123 -> 542
    //   668: astore_1
    //   669: goto -127 -> 542
    //   672: astore_0
    //   673: aconst_null
    //   674: astore 10
    //   676: aconst_null
    //   677: astore_0
    //   678: goto -247 -> 431
    //   681: astore_1
    //   682: aconst_null
    //   683: astore 10
    //   685: goto -254 -> 431
    //   688: astore_1
    //   689: aconst_null
    //   690: astore_2
    //   691: aload_0
    //   692: astore_1
    //   693: aload_2
    //   694: astore_0
    //   695: goto -381 -> 314
    //   698: aconst_null
    //   699: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	700	0	paramContext	Context
    //   0	700	1	paramContentResolver	ContentResolver
    //   0	700	2	paramUri	Uri
    //   0	700	3	paramString	String
    //   0	700	4	paramBoolean	boolean
    //   156	145	5	i1	int
    //   176	10	6	i2	int
    //   1	640	7	localObject1	Object
    //   8	285	8	localObject2	Object
    //   378	32	8	localIOException	IOException
    //   425	47	8	str	String
    //   147	497	9	localContext	Context
    //   141	543	10	localObject3	Object
    //   138	244	11	localObject4	Object
    //   150	514	12	localObject5	Object
    //   13	483	13	localUri	Uri
    //   128	170	14	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   152	158	308	java/lang/NullPointerException
    //   173	178	308	java/lang/NullPointerException
    //   207	211	308	java/lang/NullPointerException
    //   233	244	308	java/lang/NullPointerException
    //   263	272	308	java/lang/NullPointerException
    //   296	305	308	java/lang/NullPointerException
    //   395	424	308	java/lang/NullPointerException
    //   10	21	370	java/lang/NullPointerException
    //   23	52	370	java/lang/NullPointerException
    //   52	114	370	java/lang/NullPointerException
    //   114	123	370	java/lang/NullPointerException
    //   342	367	370	java/lang/NullPointerException
    //   207	211	378	java/io/IOException
    //   152	158	430	java/io/FileNotFoundException
    //   173	178	430	java/io/FileNotFoundException
    //   207	211	430	java/io/FileNotFoundException
    //   233	244	430	java/io/FileNotFoundException
    //   263	272	430	java/io/FileNotFoundException
    //   296	305	430	java/io/FileNotFoundException
    //   395	424	430	java/io/FileNotFoundException
    //   483	487	490	java/io/IOException
    //   471	476	498	java/io/IOException
    //   326	330	506	java/io/IOException
    //   334	338	514	java/io/IOException
    //   451	456	521	java/io/IOException
    //   460	464	529	java/io/IOException
    //   10	21	536	java/io/IOException
    //   23	52	536	java/io/IOException
    //   52	114	536	java/io/IOException
    //   114	123	536	java/io/IOException
    //   342	367	536	java/io/IOException
    //   562	567	577	java/io/IOException
    //   571	575	585	java/io/IOException
    //   10	21	592	finally
    //   23	52	592	finally
    //   52	114	592	finally
    //   114	123	592	finally
    //   342	367	592	finally
    //   602	606	616	java/io/IOException
    //   610	614	624	java/io/IOException
    //   123	137	632	finally
    //   152	158	639	finally
    //   173	178	639	finally
    //   207	211	639	finally
    //   233	244	639	finally
    //   263	272	639	finally
    //   296	305	639	finally
    //   395	424	639	finally
    //   438	446	639	finally
    //   549	557	639	finally
    //   314	322	649	finally
    //   123	137	661	java/io/IOException
    //   152	158	668	java/io/IOException
    //   173	178	668	java/io/IOException
    //   233	244	668	java/io/IOException
    //   263	272	668	java/io/IOException
    //   296	305	668	java/io/IOException
    //   395	424	668	java/io/IOException
    //   10	21	672	java/io/FileNotFoundException
    //   23	52	672	java/io/FileNotFoundException
    //   52	114	672	java/io/FileNotFoundException
    //   114	123	672	java/io/FileNotFoundException
    //   342	367	672	java/io/FileNotFoundException
    //   123	137	681	java/io/FileNotFoundException
    //   123	137	688	java/lang/NullPointerException
  }
  
  public static Uri a(Context paramContext, Uri paramUri, boolean paramBoolean)
  {
    if (paramUri == null) {
      return null;
    }
    ContentResolver localContentResolver = paramContext.getContentResolver();
    paramUri = a(paramUri);
    return a(paramContext, localContentResolver, paramUri, a(a(localContentResolver, paramUri)), paramBoolean);
  }
  
  /* Error */
  public static Uri a(Context paramContext, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 4
    //   9: aload_1
    //   10: ifnull +8 -> 18
    //   13: aload_1
    //   14: arraylength
    //   15: ifne +5 -> 20
    //   18: aconst_null
    //   19: areturn
    //   20: aload_0
    //   21: invokevirtual 233	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   24: astore_3
    //   25: aload_0
    //   26: getstatic 247	com/android/mms/TempFileProvider:d	Landroid/net/Uri;
    //   29: invokestatic 250	com/android/mms/TempFileProvider:a	(Landroid/content/Context;Landroid/net/Uri;)V
    //   32: aload_3
    //   33: getstatic 247	com/android/mms/TempFileProvider:d	Landroid/net/Uri;
    //   36: ldc -93
    //   38: invokevirtual 169	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;Ljava/lang/String;)Ljava/io/OutputStream;
    //   41: astore_0
    //   42: sipush 1024
    //   45: newarray <illegal type>
    //   47: astore 7
    //   49: new 252	java/io/ByteArrayInputStream
    //   52: dup
    //   53: aload_1
    //   54: invokespecial 255	java/io/ByteArrayInputStream:<init>	([B)V
    //   57: astore_3
    //   58: aload_3
    //   59: aload 7
    //   61: invokevirtual 185	java/io/InputStream:read	([B)I
    //   64: istore_2
    //   65: iload_2
    //   66: ifle +46 -> 112
    //   69: aload_0
    //   70: aload 7
    //   72: iconst_0
    //   73: iload_2
    //   74: invokevirtual 191	java/io/OutputStream:write	([BII)V
    //   77: goto -19 -> 58
    //   80: astore_1
    //   81: aload_3
    //   82: astore_1
    //   83: ldc -63
    //   85: ldc_w 257
    //   88: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   91: pop
    //   92: aload_1
    //   93: ifnull +7 -> 100
    //   96: aload_1
    //   97: invokevirtual 181	java/io/InputStream:close	()V
    //   100: aload_0
    //   101: ifnull +7 -> 108
    //   104: aload_0
    //   105: invokevirtual 201	java/io/OutputStream:close	()V
    //   108: getstatic 247	com/android/mms/TempFileProvider:d	Landroid/net/Uri;
    //   111: areturn
    //   112: aload_3
    //   113: ifnull +7 -> 120
    //   116: aload_3
    //   117: invokevirtual 181	java/io/InputStream:close	()V
    //   120: aload_0
    //   121: ifnull -13 -> 108
    //   124: aload_0
    //   125: invokevirtual 201	java/io/OutputStream:close	()V
    //   128: goto -20 -> 108
    //   131: astore_0
    //   132: aload_0
    //   133: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   136: goto -28 -> 108
    //   139: astore_1
    //   140: aload_1
    //   141: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   144: goto -24 -> 120
    //   147: astore_1
    //   148: aload_1
    //   149: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   152: goto -52 -> 100
    //   155: astore_0
    //   156: aload_0
    //   157: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   160: goto -52 -> 108
    //   163: astore_0
    //   164: aconst_null
    //   165: astore_3
    //   166: aload 5
    //   168: astore 4
    //   170: aload 4
    //   172: astore_1
    //   173: aload_3
    //   174: astore_0
    //   175: ldc -63
    //   177: ldc_w 259
    //   180: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   183: pop
    //   184: aload 4
    //   186: ifnull +8 -> 194
    //   189: aload 4
    //   191: invokevirtual 181	java/io/InputStream:close	()V
    //   194: aload_3
    //   195: ifnull -87 -> 108
    //   198: aload_3
    //   199: invokevirtual 201	java/io/OutputStream:close	()V
    //   202: goto -94 -> 108
    //   205: astore_0
    //   206: aload_0
    //   207: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   210: goto -102 -> 108
    //   213: astore_0
    //   214: aload_0
    //   215: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   218: goto -24 -> 194
    //   221: astore_0
    //   222: aconst_null
    //   223: astore_3
    //   224: aload 6
    //   226: astore 4
    //   228: aload 4
    //   230: astore_1
    //   231: aload_3
    //   232: astore_0
    //   233: ldc -63
    //   235: ldc_w 261
    //   238: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   241: pop
    //   242: aload 4
    //   244: ifnull +8 -> 252
    //   247: aload 4
    //   249: invokevirtual 181	java/io/InputStream:close	()V
    //   252: aload_3
    //   253: ifnull -145 -> 108
    //   256: aload_3
    //   257: invokevirtual 201	java/io/OutputStream:close	()V
    //   260: goto -152 -> 108
    //   263: astore_0
    //   264: aload_0
    //   265: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   268: goto -160 -> 108
    //   271: astore_0
    //   272: aload_0
    //   273: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   276: goto -24 -> 252
    //   279: astore_1
    //   280: aconst_null
    //   281: astore_0
    //   282: aconst_null
    //   283: astore_3
    //   284: aload_3
    //   285: ifnull +7 -> 292
    //   288: aload_3
    //   289: invokevirtual 181	java/io/InputStream:close	()V
    //   292: aload_0
    //   293: ifnull +7 -> 300
    //   296: aload_0
    //   297: invokevirtual 201	java/io/OutputStream:close	()V
    //   300: aload_1
    //   301: athrow
    //   302: astore_3
    //   303: aload_3
    //   304: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   307: goto -15 -> 292
    //   310: astore_0
    //   311: aload_0
    //   312: invokevirtual 224	java/io/IOException:printStackTrace	()V
    //   315: goto -15 -> 300
    //   318: astore_1
    //   319: aconst_null
    //   320: astore_3
    //   321: goto -37 -> 284
    //   324: astore_1
    //   325: goto -41 -> 284
    //   328: astore 4
    //   330: aload_1
    //   331: astore_3
    //   332: aload 4
    //   334: astore_1
    //   335: goto -51 -> 284
    //   338: astore 4
    //   340: aload_1
    //   341: astore_3
    //   342: aload 4
    //   344: astore_1
    //   345: goto -61 -> 284
    //   348: astore_1
    //   349: aload 6
    //   351: astore 4
    //   353: aload_0
    //   354: astore_3
    //   355: goto -127 -> 228
    //   358: astore_1
    //   359: aload_3
    //   360: astore 4
    //   362: aload_0
    //   363: astore_3
    //   364: goto -136 -> 228
    //   367: astore_1
    //   368: aload 5
    //   370: astore 4
    //   372: aload_0
    //   373: astore_3
    //   374: goto -204 -> 170
    //   377: astore_1
    //   378: aload_3
    //   379: astore 4
    //   381: aload_0
    //   382: astore_3
    //   383: goto -213 -> 170
    //   386: astore_0
    //   387: aconst_null
    //   388: astore_0
    //   389: aload 4
    //   391: astore_1
    //   392: goto -309 -> 83
    //   395: astore_1
    //   396: aload 4
    //   398: astore_1
    //   399: goto -316 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	402	0	paramContext	Context
    //   0	402	1	paramArrayOfByte	byte[]
    //   64	10	2	i1	int
    //   24	265	3	localObject1	Object
    //   302	2	3	localIOException	IOException
    //   320	63	3	localObject2	Object
    //   7	241	4	localObject3	Object
    //   328	5	4	localObject4	Object
    //   338	5	4	localObject5	Object
    //   351	46	4	localObject6	Object
    //   1	368	5	localObject7	Object
    //   4	346	6	localObject8	Object
    //   47	24	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   58	65	80	java/lang/NullPointerException
    //   69	77	80	java/lang/NullPointerException
    //   124	128	131	java/io/IOException
    //   116	120	139	java/io/IOException
    //   96	100	147	java/io/IOException
    //   104	108	155	java/io/IOException
    //   25	42	163	java/io/FileNotFoundException
    //   198	202	205	java/io/IOException
    //   189	194	213	java/io/IOException
    //   25	42	221	java/io/IOException
    //   256	260	263	java/io/IOException
    //   247	252	271	java/io/IOException
    //   25	42	279	finally
    //   288	292	302	java/io/IOException
    //   296	300	310	java/io/IOException
    //   42	58	318	finally
    //   58	65	324	finally
    //   69	77	324	finally
    //   83	92	328	finally
    //   175	184	338	finally
    //   233	242	338	finally
    //   42	58	348	java/io/IOException
    //   58	65	358	java/io/IOException
    //   69	77	358	java/io/IOException
    //   42	58	367	java/io/FileNotFoundException
    //   58	65	377	java/io/FileNotFoundException
    //   69	77	377	java/io/FileNotFoundException
    //   25	42	386	java/lang/NullPointerException
    //   42	58	395	java/lang/NullPointerException
  }
  
  private static final Uri a(Uri paramUri)
  {
    String str = Uri.encode((String)paramUri.getPathSegments().get(1));
    if (ContactsContract.Contacts.CONTENT_MULTI_VCARD_URI.getLastPathSegment().equals(str)) {
      return paramUri;
    }
    paramUri = (String)paramUri.getPathSegments().get(2);
    if (paramUri.equals("profile")) {}
    for (paramUri = ContactsContract.Profile.CONTENT_VCARD_URI.buildUpon().build();; paramUri = ContactsContract.Contacts.CONTENT_VCARD_URI.buildUpon().appendPath(paramUri).build()) {
      return paramUri;
    }
  }
  
  private static final Uri a(Uri paramUri, boolean paramBoolean)
  {
    return paramUri.buildUpon().appendQueryParameter("nophoto", String.valueOf(paramBoolean)).build();
  }
  
  private static final String a(ContentResolver paramContentResolver, Uri paramUri)
  {
    Object localObject = null;
    Uri localUri = paramUri.buildUpon().appendQueryParameter("limit", Integer.toString(1)).build();
    paramUri = paramContentResolver.query(paramUri, a, null, null, null);
    if (paramUri != null) {}
    for (paramContentResolver = (ContentResolver)localObject;; paramContentResolver = null)
    {
      try
      {
        if (paramUri.moveToFirst()) {
          paramContentResolver = paramUri.getString(0);
        }
      }
      catch (Exception paramContentResolver)
      {
        for (;;)
        {
          Log.e("VcardTools", "getContentFileName()---->nameUri = " + localUri.toString() + "--" + paramContentResolver.getMessage());
          paramUri.close();
          paramContentResolver = null;
        }
      }
      finally
      {
        paramUri.close();
      }
      paramUri = paramContentResolver;
      if (paramContentResolver == null) {
        paramUri = localUri.getLastPathSegment();
      }
      return paramUri;
    }
  }
  
  private static final String a(String paramString)
  {
    Object localObject = act.b().a(paramString);
    int i2 = ((ArrayList)localObject).size();
    paramString = new StringBuilder();
    int i1 = 0;
    if (i1 < i2)
    {
      act.a locala = (act.a)((ArrayList)localObject).get(i1);
      if (2 == a) {
        paramString.append(c);
      }
      for (;;)
      {
        i1 += 1;
        break;
        paramString.append(b);
      }
    }
    if (paramString.length() < 1) {
      paramString.append("ExpectationFile");
    }
    try
    {
      localObject = new String(paramString.toString().getBytes("ISO8859-1"), "GBK");
      return (String)localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return paramString.toString();
  }
  
  private void a(ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(paramContentValues)) {
        d = paramContentValues;
      }
    }
  }
  
  private void a(Set<acu.g> paramSet, ContentValues paramContentValues)
  {
    String str;
    Object localObject;
    int i1;
    if (paramContentValues.containsKey("data1"))
    {
      str = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(str))
      {
        localObject = paramContentValues.getAsInteger("data2");
        if (localObject == null) {
          break label96;
        }
        i1 = ((Integer)localObject).intValue();
        localObject = paramContentValues.getAsString("data3");
        paramContentValues = paramContentValues.getAsBoolean("is_primary");
        if (paramContentValues == null) {
          break label101;
        }
      }
    }
    label96:
    label101:
    for (boolean bool = paramContentValues.booleanValue();; bool = false)
    {
      paramSet.add(new acu.g(str, i1, (String)localObject, bool));
      return;
      i1 = 1;
      break;
    }
  }
  
  /* Error */
  private boolean a(Uri paramUri, int paramInt, afb paramafb, afa paramafa, boolean paramBoolean, List<String> paramList)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 100	acu:c	Landroid/content/Context;
    //   4: invokevirtual 233	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 10
    //   9: aload 10
    //   11: aload_1
    //   12: invokevirtual 173	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   15: astore 9
    //   17: aload_0
    //   18: new 446	afg
    //   21: dup
    //   22: iload_2
    //   23: invokespecial 449	afg:<init>	(I)V
    //   26: putfield 451	acu:n	Lafc;
    //   29: aload 9
    //   31: astore 8
    //   33: aload_0
    //   34: new 453	afk
    //   37: dup
    //   38: invokespecial 454	afk:<init>	()V
    //   41: putfield 456	acu:o	Lafk;
    //   44: aload 9
    //   46: astore 8
    //   48: aload_0
    //   49: getfield 451	acu:n	Lafc;
    //   52: aload_0
    //   53: getfield 456	acu:o	Lafk;
    //   56: invokevirtual 461	afc:a	(Lafb;)V
    //   59: aload 9
    //   61: astore 8
    //   63: aload_0
    //   64: getfield 451	acu:n	Lafc;
    //   67: aload 9
    //   69: aload_3
    //   70: invokevirtual 464	afc:a	(Ljava/io/InputStream;Lafb;)V
    //   73: aload 9
    //   75: ifnull +8 -> 83
    //   78: aload 9
    //   80: invokevirtual 181	java/io/InputStream:close	()V
    //   83: iconst_1
    //   84: ireturn
    //   85: astore_3
    //   86: aload 9
    //   88: astore 8
    //   90: aload 9
    //   92: invokevirtual 181	java/io/InputStream:close	()V
    //   95: aload 9
    //   97: astore 8
    //   99: aload_0
    //   100: getfield 456	acu:o	Lafk;
    //   103: ifnull +125 -> 228
    //   106: aload 9
    //   108: astore 8
    //   110: aload_0
    //   111: getfield 456	acu:o	Lafk;
    //   114: invokevirtual 466	afk:e	()I
    //   117: istore 7
    //   119: aload 9
    //   121: astore 8
    //   123: new 468	aez
    //   126: dup
    //   127: iload 7
    //   129: aconst_null
    //   130: invokespecial 471	aez:<init>	(ILandroid/accounts/Account;)V
    //   133: astore 11
    //   135: aload 9
    //   137: astore 8
    //   139: aload 11
    //   141: aload 4
    //   143: invokevirtual 474	aez:a	(Lafa;)V
    //   146: aload 9
    //   148: astore 8
    //   150: aload 10
    //   152: aload_1
    //   153: invokevirtual 173	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   156: astore_3
    //   157: aload_3
    //   158: astore 8
    //   160: aload_0
    //   161: new 476	afh
    //   164: dup
    //   165: iload_2
    //   166: invokespecial 477	afh:<init>	(I)V
    //   169: putfield 451	acu:n	Lafc;
    //   172: aload_3
    //   173: astore 8
    //   175: aload_0
    //   176: new 453	afk
    //   179: dup
    //   180: invokespecial 454	afk:<init>	()V
    //   183: putfield 456	acu:o	Lafk;
    //   186: aload_3
    //   187: astore 8
    //   189: aload_0
    //   190: getfield 451	acu:n	Lafc;
    //   193: aload_0
    //   194: getfield 456	acu:o	Lafk;
    //   197: invokevirtual 461	afc:a	(Lafb;)V
    //   200: aload_3
    //   201: astore 8
    //   203: aload_0
    //   204: getfield 451	acu:n	Lafc;
    //   207: aload_3
    //   208: aload 11
    //   210: invokevirtual 464	afc:a	(Ljava/io/InputStream;Lafb;)V
    //   213: aload_3
    //   214: ifnull -131 -> 83
    //   217: aload_3
    //   218: invokevirtual 181	java/io/InputStream:close	()V
    //   221: goto -138 -> 83
    //   224: astore_1
    //   225: goto -142 -> 83
    //   228: aload 9
    //   230: astore 8
    //   232: getstatic 480	aex:b	I
    //   235: istore 7
    //   237: goto -118 -> 119
    //   240: astore 4
    //   242: aload_3
    //   243: astore 8
    //   245: new 442	afn
    //   248: dup
    //   249: ldc_w 482
    //   252: invokespecial 483	afn:<init>	(Ljava/lang/String;)V
    //   255: athrow
    //   256: astore_3
    //   257: aload 8
    //   259: ifnull +8 -> 267
    //   262: aload 8
    //   264: invokevirtual 181	java/io/InputStream:close	()V
    //   267: aload_3
    //   268: athrow
    //   269: astore_3
    //   270: ldc -63
    //   272: new 128	java/lang/StringBuilder
    //   275: dup
    //   276: invokespecial 129	java/lang/StringBuilder:<init>	()V
    //   279: ldc_w 485
    //   282: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: aload_3
    //   286: invokevirtual 219	java/io/IOException:getMessage	()Ljava/lang/String;
    //   289: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   292: invokevirtual 137	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   295: invokestatic 200	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   298: pop
    //   299: aload 6
    //   301: ifnull +15 -> 316
    //   304: aload 6
    //   306: aload_1
    //   307: invokevirtual 345	android/net/Uri:toString	()Ljava/lang/String;
    //   310: invokeinterface 486 2 0
    //   315: pop
    //   316: iconst_0
    //   317: ireturn
    //   318: astore_3
    //   319: aload_3
    //   320: instanceof 488
    //   323: ifeq +13 -> 336
    //   326: iload 5
    //   328: ifeq +8 -> 336
    //   331: aload_3
    //   332: checkcast 488	afq
    //   335: athrow
    //   336: aload 6
    //   338: ifnull +15 -> 353
    //   341: aload 6
    //   343: aload_1
    //   344: invokevirtual 345	android/net/Uri:toString	()Ljava/lang/String;
    //   347: invokeinterface 486 2 0
    //   352: pop
    //   353: iconst_0
    //   354: ireturn
    //   355: astore_3
    //   356: aload 6
    //   358: ifnull +15 -> 373
    //   361: aload 6
    //   363: aload_1
    //   364: invokevirtual 345	android/net/Uri:toString	()Ljava/lang/String;
    //   367: invokeinterface 486 2 0
    //   372: pop
    //   373: iconst_0
    //   374: ireturn
    //   375: astore_1
    //   376: goto -293 -> 83
    //   379: astore_3
    //   380: goto -285 -> 95
    //   383: astore 4
    //   385: goto -118 -> 267
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	388	0	this	acu
    //   0	388	1	paramUri	Uri
    //   0	388	2	paramInt	int
    //   0	388	3	paramafb	afb
    //   0	388	4	paramafa	afa
    //   0	388	5	paramBoolean	boolean
    //   0	388	6	paramList	List<String>
    //   117	119	7	i1	int
    //   31	232	8	localObject	Object
    //   15	214	9	localInputStream	java.io.InputStream
    //   7	144	10	localContentResolver	ContentResolver
    //   133	76	11	localaez	aez
    // Exception table:
    //   from	to	target	type
    //   33	44	85	afs
    //   48	59	85	afs
    //   63	73	85	afs
    //   217	221	224	java/io/IOException
    //   160	172	240	afs
    //   175	186	240	afs
    //   189	200	240	afs
    //   203	213	240	afs
    //   33	44	256	finally
    //   48	59	256	finally
    //   63	73	256	finally
    //   90	95	256	finally
    //   99	106	256	finally
    //   110	119	256	finally
    //   123	135	256	finally
    //   139	146	256	finally
    //   150	157	256	finally
    //   160	172	256	finally
    //   175	186	256	finally
    //   189	200	256	finally
    //   203	213	256	finally
    //   232	237	256	finally
    //   245	256	256	finally
    //   0	29	269	java/io/IOException
    //   267	269	269	java/io/IOException
    //   0	29	318	afr
    //   78	83	318	afr
    //   217	221	318	afr
    //   262	267	318	afr
    //   267	269	318	afr
    //   0	29	355	afn
    //   78	83	355	afn
    //   217	221	355	afn
    //   262	267	355	afn
    //   267	269	355	afn
    //   78	83	375	java/io/IOException
    //   90	95	379	java/io/IOException
    //   262	267	383	java/io/IOException
  }
  
  private final void b(long paramLong)
  {
    Object localObject2 = c.getContentResolver();
    Object localObject1;
    if (ContactsContract.isProfileId(paramLong))
    {
      localObject1 = ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI;
      localObject1 = ContactsContract.RawContacts.newEntityIterator(((ContentResolver)localObject2).query((Uri)localObject1, null, "contact_id= " + paramLong, null, null));
    }
    for (;;)
    {
      ContentValues localContentValues;
      String str;
      try
      {
        if (!((EntityIterator)localObject1).hasNext()) {
          break label377;
        }
        localObject2 = ((Entity)((EntityIterator)localObject1).next()).getSubValues().iterator();
        if (!((Iterator)localObject2).hasNext()) {
          continue;
        }
        localContentValues = nextvalues;
        str = localContentValues.getAsString("mimetype");
        if ("vnd.android.cursor.item/email_v2".equals(str))
        {
          b(e, localContentValues);
          continue;
        }
      }
      catch (IOException localIOException)
      {
        return;
        localObject1 = ContactsContract.RawContactsEntity.CONTENT_URI;
        break;
        if (str.equals("vnd.android.cursor.item/nickname"))
        {
          e(k, localContentValues);
          continue;
        }
      }
      finally
      {
        ((EntityIterator)localObject1).close();
      }
      if ("vnd.android.cursor.item/website".equals(str)) {
        f(g, localContentValues);
      } else if ("vnd.android.cursor.item/phone_v2".equals(str)) {
        a(h, localContentValues);
      } else if ("vnd.android.cursor.item/name".equals(str)) {
        a(localContentValues);
      } else if ("vnd.android.cursor.item/im".equals(str)) {
        c(f, localContentValues);
      } else if (str.equals("vnd.android.cursor.item/contact_event")) {
        d(m, localContentValues);
      } else if (str.equals("vnd.android.cursor.item/note")) {
        g(l, localContentValues);
      } else if (str.equals("vnd.android.cursor.item/postal-address_v2")) {
        h(i, localContentValues);
      } else if (str.equals("vnd.android.cursor.item/organization")) {
        i(j, localContentValues);
      }
    }
    label377:
    ((EntityIterator)localObject1).close();
  }
  
  private void b(Set<acu.b> paramSet, ContentValues paramContentValues)
  {
    String str1;
    if (paramContentValues.containsKey("data1"))
    {
      str1 = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(str1)) {}
    }
    else
    {
      return;
    }
    Object localObject = paramContentValues.getAsInteger("data2");
    int i1;
    String str2;
    boolean bool;
    if (localObject != null)
    {
      i1 = ((Integer)localObject).intValue();
      localObject = paramContentValues.getAsString("data3");
      str2 = paramContentValues.getAsString("data4");
      bool = paramContentValues.getAsBoolean("is_primary").booleanValue();
      if (str2 != null) {
        break label123;
      }
      if (localObject == null) {
        break label117;
      }
      paramContentValues = (ContentValues)localObject;
    }
    for (;;)
    {
      paramSet.add(new acu.b(str1, i1, paramContentValues, bool));
      return;
      i1 = 1;
      break;
      label117:
      paramContentValues = str1;
      continue;
      label123:
      paramContentValues = str2;
    }
  }
  
  private void c(Set<acu.c> paramSet, ContentValues paramContentValues)
  {
    String str;
    Object localObject;
    int i1;
    CharSequence localCharSequence;
    int i2;
    if (paramContentValues.containsKey("data1"))
    {
      str = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(str))
      {
        localObject = paramContentValues.getAsInteger("data5");
        if (localObject == null) {
          break label136;
        }
        i1 = ((Integer)localObject).intValue();
        localObject = paramContentValues.getAsString("data6");
        localCharSequence = ContactsContract.CommonDataKinds.Im.getProtocolLabel(c.getResources(), i1, (CharSequence)localObject);
        Integer localInteger = paramContentValues.getAsInteger("data2");
        if (localInteger == null) {
          break label141;
        }
        i2 = localInteger.intValue();
        label92:
        paramContentValues = paramContentValues.getAsBoolean("is_primary");
        if (paramContentValues == null) {
          break label147;
        }
      }
    }
    label136:
    label141:
    label147:
    for (boolean bool = paramContentValues.booleanValue();; bool = false)
    {
      paramSet.add(new acu.c(i1, (String)localObject, localCharSequence, str, i2, bool));
      return;
      i1 = 0;
      break;
      i2 = 1;
      break label92;
    }
  }
  
  private void d(Set<acu.a> paramSet, ContentValues paramContentValues)
  {
    Integer localInteger = paramContentValues.getAsInteger("data2");
    if ((localInteger == null) || (!localInteger.equals(Integer.valueOf(3)))) {}
    do
    {
      do
      {
        return;
      } while (!paramContentValues.containsKey("data1"));
      paramContentValues = paramContentValues.getAsString("data1");
    } while (TextUtils.isEmpty(paramContentValues));
    paramSet.add(new acu.a(paramContentValues));
  }
  
  private void e(Set<acu.d> paramSet, ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(paramContentValues)) {}
    }
    else
    {
      return;
    }
    paramSet.add(new acu.d(paramContentValues));
  }
  
  private void f(Set<acu.i> paramSet, ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1");
      if (!TextUtils.isEmpty(paramContentValues)) {}
    }
    else
    {
      return;
    }
    paramSet.add(new acu.i(paramContentValues));
  }
  
  private void g(Set<acu.e> paramSet, ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1").replaceAll("\n", "\r\n");
      if (!TextUtils.isEmpty(paramContentValues)) {}
    }
    else
    {
      return;
    }
    paramSet.add(new acu.e(paramContentValues));
  }
  
  private void h(Set<acu.h> paramSet, ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1").replaceAll("\n", "\r\n");
      if (!TextUtils.isEmpty(paramContentValues)) {}
    }
    else
    {
      return;
    }
    paramSet.add(new acu.h(paramContentValues));
  }
  
  private void i(Set<acu.f> paramSet, ContentValues paramContentValues)
  {
    if (paramContentValues.containsKey("data1"))
    {
      paramContentValues = paramContentValues.getAsString("data1").replaceAll("\n", "\r\n");
      if (!TextUtils.isEmpty(paramContentValues)) {}
    }
    else
    {
      return;
    }
    paramSet.add(new acu.f(paramContentValues));
  }
  
  public String a(long paramLong)
  {
    b(paramLong);
    StringBuffer localStringBuffer = new StringBuffer();
    Resources localResources = c.getResources();
    localStringBuffer.append(localResources.getString(2131493623)).append(":").append(d).append("\n");
    Iterator localIterator;
    if (h != null)
    {
      localIterator = h.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493625)).append(":").append(((acu.g)localIterator.next()).a().replaceAll("-", "")).append("\n");
      }
    }
    if (e != null)
    {
      localIterator = e.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493621, new Object[] { ((acu.b)localIterator.next()).a() }));
      }
    }
    if (f != null)
    {
      localIterator = f.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493622, new Object[] { ((acu.c)localIterator.next()).b() }));
      }
    }
    if (g != null)
    {
      localIterator = g.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493626, new Object[] { ((acu.i)localIterator.next()).a() }));
      }
    }
    if (i != null)
    {
      localIterator = i.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493817, new Object[] { ((acu.h)localIterator.next()).a() }));
      }
    }
    if (j != null)
    {
      localIterator = j.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493816, new Object[] { ((acu.f)localIterator.next()).a() }));
      }
    }
    if (k != null)
    {
      localIterator = k.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493814, new Object[] { ((acu.d)localIterator.next()).a() }));
      }
    }
    if (l != null)
    {
      localIterator = l.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493815, new Object[] { ((acu.e)localIterator.next()).a() }));
      }
    }
    if (m != null)
    {
      localIterator = m.iterator();
      while (localIterator.hasNext()) {
        localStringBuffer.append(localResources.getString(2131493813, new Object[] { ((acu.a)localIterator.next()).a() }));
      }
    }
    int i1 = localStringBuffer.length() - 1;
    if ((i1 >= 0) && (localStringBuffer.lastIndexOf("\n") == i1)) {
      localStringBuffer.deleteCharAt(i1);
    }
    return localStringBuffer.toString();
  }
  
  public String a(aey paramaey)
  {
    if (paramaey == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    Resources localResources = c.getResources();
    Object localObject1 = paramaey.k();
    if (localObject1 != null)
    {
      localStringBuffer.append(localResources.getString(2131493623));
      localStringBuffer.append(":");
      localStringBuffer.append(((String)localObject1).replaceAll("-", ""));
      localStringBuffer.append("\n");
    }
    localObject1 = paramaey.e();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.m)((Iterator)localObject1).next();
        if ((localObject2 != null) && (!TextUtils.isEmpty(((aey.m)localObject2).b())))
        {
          localStringBuffer.append(localResources.getString(2131493625));
          localStringBuffer.append(":");
          localStringBuffer.append(((aey.m)localObject2).b().replaceAll("-", ""));
          localStringBuffer.append("\n");
        }
      }
    }
    localObject1 = paramaey.f();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.d)((Iterator)localObject1).next();
        if ((localObject2 != null) && (!TextUtils.isEmpty(((aey.d)localObject2).b()))) {
          localStringBuffer.append(localResources.getString(2131493621, new Object[] { ((aey.d)localObject2).b() }));
        }
      }
    }
    localObject1 = paramaey.i();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.h)((Iterator)localObject1).next();
        if ((localObject2 != null) && (!TextUtils.isEmpty(((aey.h)localObject2).b()))) {
          localStringBuffer.append(localResources.getString(2131493622, new Object[] { ((aey.h)localObject2).b() }));
        }
      }
    }
    localObject1 = paramaey.j();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.r)((Iterator)localObject1).next();
        if ((localObject2 != null) && (!TextUtils.isEmpty(((aey.r)localObject2).b()))) {
          localStringBuffer.append(localResources.getString(2131493626, new Object[] { ((aey.r)localObject2).b() }));
        }
      }
    }
    localObject1 = paramaey.g();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.o)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          localObject2 = ((aey.o)localObject2).a(-1073741824);
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localStringBuffer.append(localResources.getString(2131493817, new Object[] { localObject2 }));
          }
        }
      }
    }
    localObject1 = paramaey.h();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.l)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          localObject2 = ((aey.l)localObject2).b();
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localStringBuffer.append(localResources.getString(2131493816, new Object[] { localObject2 }));
          }
        }
      }
    }
    localObject1 = paramaey.b();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.j)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          localObject2 = ((aey.j)localObject2).b();
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localStringBuffer.append(localResources.getString(2131493814, new Object[] { localObject2 }));
          }
        }
      }
    }
    localObject1 = paramaey.d();
    if (localObject1 != null)
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (aey.k)((Iterator)localObject1).next();
        if (localObject2 != null)
        {
          localObject2 = ((aey.k)localObject2).b();
          if (!TextUtils.isEmpty((CharSequence)localObject2)) {
            localStringBuffer.append(localResources.getString(2131493815, new Object[] { localObject2 }));
          }
        }
      }
    }
    paramaey = paramaey.c();
    if (!TextUtils.isEmpty(paramaey)) {
      localStringBuffer.append(localResources.getString(2131493813, new Object[] { paramaey }));
    }
    int i1 = localStringBuffer.length() - 1;
    if ((i1 >= 0) && (localStringBuffer.lastIndexOf("\n") == i1)) {
      localStringBuffer.deleteCharAt(i1);
    }
    return localStringBuffer.toString();
  }
  
  public void a(Uri paramUri, afa paramafa)
  {
    aez localaez1 = new aez(-1073741824, null);
    localaez1.a(paramafa);
    try
    {
      a(paramUri, 0, localaez1, paramafa, true, null);
      return;
    }
    catch (afq localafq) {}
    for (;;)
    {
      try
      {
        if (o != null)
        {
          i1 = o.e();
          aez localaez2 = new aez(i1, null);
          localaez2.a(paramafa);
          a(paramUri, i1, localaez2, paramafa, false, null);
          return;
        }
      }
      catch (afq paramUri)
      {
        Log.e("VcardTools", "Must not reach here. " + paramUri);
        return;
      }
      int i1 = aex.b;
    }
  }
  
  public static class a
  {
    private final String a;
    
    public a(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof a)) {
        return false;
      }
      paramObject = (a)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "birthday: " + a;
    }
  }
  
  public static class b
  {
    private final String a;
    private final int b;
    private final String c;
    private final boolean d;
    
    public b(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
    {
      b = paramInt;
      a = paramString1;
      c = paramString2;
      d = paramBoolean;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof b)) {
        return false;
      }
      paramObject = (b)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
  }
  
  public static class c
  {
    private final String a;
    private final int b;
    private final String c;
    private final CharSequence d;
    private final int e;
    private final boolean f;
    
    public c(int paramInt1, String paramString1, CharSequence paramCharSequence, String paramString2, int paramInt2, boolean paramBoolean)
    {
      b = paramInt1;
      c = paramString1;
      paramString1 = paramCharSequence;
      if (TextUtils.isEmpty(paramCharSequence)) {
        paramString1 = "Im";
      }
      d = paramString1;
      e = paramInt2;
      a = paramString2;
      f = paramBoolean;
    }
    
    public CharSequence a()
    {
      return d;
    }
    
    public String b()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof c)) {
          return false;
        }
        paramObject = (c)paramObject;
      } while ((TextUtils.equals(a(), ((c)paramObject).a())) && (TextUtils.equals(a, a)));
      return false;
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = b;
      if (a != null) {}
      for (int i = a.hashCode();; i = 0)
      {
        if (d != null) {
          j = d.hashCode();
        }
        return (i + k * 31) * 31 + j;
      }
    }
    
    public String toString()
    {
      return String.format("type: %d, protocol: %d, custom_protcol: %s, data: %s, isPrimary: %s", new Object[] { Integer.valueOf(e), Integer.valueOf(b), c, a, Boolean.valueOf(f) });
    }
  }
  
  public static class d
  {
    private final String a;
    
    public d(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof d)) {
        return false;
      }
      paramObject = (d)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "nickname: " + a;
    }
  }
  
  public static class e
  {
    public final String a;
    
    public e(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof e)) {
        return false;
      }
      paramObject = (e)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "note: " + a;
    }
  }
  
  public static class f
  {
    private final String a;
    
    public f(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof f)) {
        return false;
      }
      paramObject = (f)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "Organization: " + a;
    }
  }
  
  public static class g
  {
    private final String a;
    private final int b;
    private final String c;
    private boolean d;
    
    public g(String paramString1, int paramInt, String paramString2, boolean paramBoolean)
    {
      a = paramString1;
      b = paramInt;
      c = paramString2;
      d = paramBoolean;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof g)) {
        return false;
      }
      paramObject = (g)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return String.format("type: %d, data: %s, label: %s, isPrimary: %s", new Object[] { Integer.valueOf(b), a, c, Boolean.valueOf(d) });
    }
  }
  
  public static class h
  {
    private final String a;
    
    public h(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof h)) {
        return false;
      }
      paramObject = (h)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "postal: " + a;
    }
  }
  
  public static class i
  {
    private final String a;
    
    public i(String paramString)
    {
      a = paramString;
    }
    
    public String a()
    {
      return a;
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {
        return true;
      }
      if (!(paramObject instanceof i)) {
        return false;
      }
      paramObject = (i)paramObject;
      return TextUtils.equals(a, a);
    }
    
    public int hashCode()
    {
      if (a != null) {
        return a.hashCode();
      }
      return 0;
    }
    
    public String toString()
    {
      return "website: " + a;
    }
  }
}

/* Location:
 * Qualified Name:     acu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */