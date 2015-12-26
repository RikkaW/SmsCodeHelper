import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import java.io.File;

public final class ags
{
  private Context a = null;
  private boolean b = true;
  private int c = 1270;
  private int d = 310;
  private int e = 4;
  private int f = 200;
  private int g = 1;
  private int h = 0;
  private int i = 0;
  private long j = 0L;
  private agr k = null;
  
  private ags(Context paramContext)
  {
    a = paramContext;
  }
  
  private static int a(byte[] paramArrayOfByte, int paramInt)
  {
    int m = 0;
    int n = 0;
    while (m < 4)
    {
      n += ((paramArrayOfByte[(m + paramInt)] & 0xFF) << (m << 3));
      m += 1;
    }
    return n;
  }
  
  /* Error */
  protected static ags a(Context paramContext)
  {
    // Byte code:
    //   0: new 2	ags
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 54	ags:<init>	(Landroid/content/Context;)V
    //   8: astore 6
    //   10: aload 6
    //   12: iconst_0
    //   13: putfield 41	ags:h	I
    //   16: aload 6
    //   18: iconst_0
    //   19: putfield 43	ags:i	I
    //   22: aload 6
    //   24: invokestatic 60	java/lang/System:currentTimeMillis	()J
    //   27: ldc2_w 61
    //   30: ladd
    //   31: ldc2_w 63
    //   34: ldiv
    //   35: ldc2_w 63
    //   38: lmul
    //   39: putfield 45	ags:j	J
    //   42: aconst_null
    //   43: astore 5
    //   45: new 66	java/io/FileInputStream
    //   48: dup
    //   49: new 68	java/io/File
    //   52: dup
    //   53: new 70	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   60: aload_0
    //   61: invokestatic 74	ags:b	(Landroid/content/Context;)Ljava/lang/String;
    //   64: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: getstatic 82	java/io/File:separator	Ljava/lang/String;
    //   70: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: ldc 84
    //   75: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokespecial 91	java/io/File:<init>	(Ljava/lang/String;)V
    //   84: invokespecial 94	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   87: astore_0
    //   88: new 96	java/io/ByteArrayOutputStream
    //   91: dup
    //   92: invokespecial 97	java/io/ByteArrayOutputStream:<init>	()V
    //   95: astore 5
    //   97: bipush 32
    //   99: newarray <illegal type>
    //   101: astore 7
    //   103: aload_0
    //   104: aload 7
    //   106: invokevirtual 101	java/io/FileInputStream:read	([B)I
    //   109: istore_1
    //   110: iload_1
    //   111: iconst_m1
    //   112: if_icmpeq +28 -> 140
    //   115: aload 5
    //   117: aload 7
    //   119: iconst_0
    //   120: iload_1
    //   121: invokevirtual 105	java/io/ByteArrayOutputStream:write	([BII)V
    //   124: goto -21 -> 103
    //   127: astore 5
    //   129: aload_0
    //   130: ifnull +7 -> 137
    //   133: aload_0
    //   134: invokevirtual 108	java/io/FileInputStream:close	()V
    //   137: aload 6
    //   139: areturn
    //   140: aload 5
    //   142: invokevirtual 111	java/io/ByteArrayOutputStream:flush	()V
    //   145: aload 5
    //   147: invokevirtual 115	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   150: astore 7
    //   152: aload 7
    //   154: ifnull +138 -> 292
    //   157: aload 7
    //   159: arraylength
    //   160: bipush 22
    //   162: if_icmplt +130 -> 292
    //   165: aload 7
    //   167: iconst_0
    //   168: baload
    //   169: ifeq +139 -> 308
    //   172: iconst_1
    //   173: istore_2
    //   174: aload 6
    //   176: iload_2
    //   177: putfield 29	ags:b	Z
    //   180: aload 6
    //   182: aload 7
    //   184: iconst_1
    //   185: baload
    //   186: bipush 10
    //   188: imul
    //   189: bipush 10
    //   191: ishl
    //   192: putfield 31	ags:c	I
    //   195: aload 6
    //   197: aload 7
    //   199: iconst_2
    //   200: baload
    //   201: bipush 10
    //   203: imul
    //   204: bipush 10
    //   206: ishl
    //   207: putfield 33	ags:d	I
    //   210: aload 6
    //   212: aload 7
    //   214: iconst_3
    //   215: baload
    //   216: putfield 35	ags:e	I
    //   219: aload 6
    //   221: aload 7
    //   223: iconst_4
    //   224: baload
    //   225: bipush 10
    //   227: imul
    //   228: putfield 37	ags:f	I
    //   231: aload 6
    //   233: aload 7
    //   235: iconst_5
    //   236: baload
    //   237: putfield 39	ags:g	I
    //   240: aload 7
    //   242: bipush 14
    //   244: invokestatic 118	ags:b	([BI)J
    //   247: lstore_3
    //   248: aload 6
    //   250: getfield 45	ags:j	J
    //   253: lload_3
    //   254: lsub
    //   255: ldc2_w 63
    //   258: lcmp
    //   259: ifge +33 -> 292
    //   262: aload 6
    //   264: lload_3
    //   265: putfield 45	ags:j	J
    //   268: aload 6
    //   270: aload 7
    //   272: bipush 6
    //   274: invokestatic 120	ags:a	([BI)I
    //   277: putfield 41	ags:h	I
    //   280: aload 6
    //   282: aload 7
    //   284: bipush 10
    //   286: invokestatic 120	ags:a	([BI)I
    //   289: putfield 43	ags:i	I
    //   292: aload 5
    //   294: invokevirtual 121	java/io/ByteArrayOutputStream:close	()V
    //   297: aload_0
    //   298: invokevirtual 108	java/io/FileInputStream:close	()V
    //   301: aload 6
    //   303: areturn
    //   304: astore_0
    //   305: aload 6
    //   307: areturn
    //   308: iconst_0
    //   309: istore_2
    //   310: goto -136 -> 174
    //   313: astore_0
    //   314: aload 5
    //   316: ifnull +8 -> 324
    //   319: aload 5
    //   321: invokevirtual 108	java/io/FileInputStream:close	()V
    //   324: aload_0
    //   325: athrow
    //   326: astore_0
    //   327: aload 6
    //   329: areturn
    //   330: astore 5
    //   332: goto -8 -> 324
    //   335: astore 6
    //   337: aload_0
    //   338: astore 5
    //   340: aload 6
    //   342: astore_0
    //   343: goto -29 -> 314
    //   346: astore_0
    //   347: aconst_null
    //   348: astore_0
    //   349: goto -220 -> 129
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	352	0	paramContext	Context
    //   109	12	1	m	int
    //   173	137	2	bool	boolean
    //   247	18	3	l	long
    //   43	73	5	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   127	193	5	localException1	Exception
    //   330	1	5	localException2	Exception
    //   338	1	5	localContext	Context
    //   8	320	6	localags	ags
    //   335	6	6	localObject	Object
    //   101	182	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   88	103	127	java/lang/Exception
    //   103	110	127	java/lang/Exception
    //   115	124	127	java/lang/Exception
    //   140	152	127	java/lang/Exception
    //   157	165	127	java/lang/Exception
    //   174	292	127	java/lang/Exception
    //   292	297	127	java/lang/Exception
    //   297	301	304	java/lang/Exception
    //   45	88	313	finally
    //   133	137	326	java/lang/Exception
    //   319	324	330	java/lang/Exception
    //   88	103	335	finally
    //   103	110	335	finally
    //   115	124	335	finally
    //   140	152	335	finally
    //   157	165	335	finally
    //   174	292	335	finally
    //   292	297	335	finally
    //   45	88	346	java/lang/Exception
  }
  
  private static byte[] a(long paramLong)
  {
    byte[] arrayOfByte = new byte[8];
    int m = 0;
    while (m < 8)
    {
      arrayOfByte[m] = ((byte)(int)(paramLong >> (m << 3) & 0xFF));
      m += 1;
    }
    return arrayOfByte;
  }
  
  private static long b(byte[] paramArrayOfByte, int paramInt)
  {
    paramInt = 0;
    int m = 0;
    while (paramInt < 8)
    {
      m += ((paramArrayOfByte[(paramInt + 14)] & 0xFF) << (paramInt << 3));
      paramInt += 1;
    }
    return m;
  }
  
  private static String b(Context paramContext)
  {
    int m = 0;
    File localFile = null;
    if (Process.myUid() != 1000) {
      localFile = agc.a(paramContext);
    }
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      m = bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if (((m != 0) || (!agc.c())) && (localFile != null)) {
      return localFile.getPath();
    }
    return paramContext.getFilesDir().getPath();
  }
  
  private static byte[] c(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    int m = 0;
    while (m < 4)
    {
      arrayOfByte[m] = ((byte)(paramInt >> (m << 3)));
      m += 1;
    }
    return arrayOfByte;
  }
  
  private void g()
  {
    long l = System.currentTimeMillis() + 28800000L;
    if (l - j > 86400000L)
    {
      j = (l / 86400000L * 86400000L);
      h = 0;
      i = 0;
    }
  }
  
  protected final void a(int paramInt)
  {
    g();
    int m = paramInt;
    if (paramInt < 0) {
      m = 0;
    }
    h = m;
  }
  
  protected final void a(agr paramagr)
  {
    k = paramagr;
  }
  
  protected final boolean a()
  {
    g();
    NetworkInfo localNetworkInfo = ((ConnectivityManager)a.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
    {
      if (localNetworkInfo.getType() == 1) {
        return (b) && (h < c);
      }
      return (b) && (i < d);
    }
    return b;
  }
  
  /* Error */
  protected final boolean a(String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: new 188	org/json/JSONObject
    //   5: dup
    //   6: aload_1
    //   7: invokespecial 189	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: aload_1
    //   12: ldc -66
    //   14: invokevirtual 193	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   17: ifeq +21 -> 38
    //   20: aload_1
    //   21: ldc -66
    //   23: invokevirtual 197	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   26: ifeq +434 -> 460
    //   29: iconst_1
    //   30: istore 4
    //   32: aload_0
    //   33: iload 4
    //   35: putfield 29	ags:b	Z
    //   38: aload_1
    //   39: ldc -58
    //   41: invokevirtual 193	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   44: ifeq +108 -> 152
    //   47: aload_1
    //   48: ldc -58
    //   50: invokevirtual 197	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   53: istore_3
    //   54: aload_0
    //   55: iload_3
    //   56: bipush 127
    //   58: iand
    //   59: bipush 10
    //   61: imul
    //   62: bipush 10
    //   64: ishl
    //   65: putfield 31	ags:c	I
    //   68: aload_0
    //   69: iload_3
    //   70: sipush 3968
    //   73: iand
    //   74: bipush 7
    //   76: ishr
    //   77: bipush 10
    //   79: imul
    //   80: bipush 10
    //   82: ishl
    //   83: putfield 33	ags:d	I
    //   86: aload_0
    //   87: ldc -57
    //   89: iload_3
    //   90: iand
    //   91: bipush 12
    //   93: ishr
    //   94: putfield 35	ags:e	I
    //   97: aload_0
    //   98: ldc -56
    //   100: iload_3
    //   101: iand
    //   102: bipush 19
    //   104: ishr
    //   105: bipush 10
    //   107: imul
    //   108: putfield 37	ags:f	I
    //   111: aload_0
    //   112: iload_3
    //   113: ldc -55
    //   115: iand
    //   116: bipush 26
    //   118: ishr
    //   119: putfield 39	ags:g	I
    //   122: aload_0
    //   123: getfield 39	ags:g	I
    //   126: bipush 31
    //   128: if_icmpne +10 -> 138
    //   131: aload_0
    //   132: sipush 1500
    //   135: putfield 39	ags:g	I
    //   138: aload_0
    //   139: getfield 47	ags:k	Lagr;
    //   142: ifnull +10 -> 152
    //   145: aload_0
    //   146: getfield 47	ags:k	Lagr;
    //   149: invokevirtual 205	agr:a	()V
    //   152: aload_1
    //   153: ldc -49
    //   155: invokevirtual 193	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   158: ifeq +377 -> 535
    //   161: aload_1
    //   162: ldc -49
    //   164: invokevirtual 197	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   167: istore_3
    //   168: iload_3
    //   169: ifeq +297 -> 466
    //   172: iconst_1
    //   173: istore 4
    //   175: aload_0
    //   176: invokespecial 164	ags:g	()V
    //   179: new 209	java/io/FileOutputStream
    //   182: dup
    //   183: new 68	java/io/File
    //   186: dup
    //   187: new 70	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   194: aload_0
    //   195: getfield 27	ags:a	Landroid/content/Context;
    //   198: invokestatic 74	ags:b	(Landroid/content/Context;)Ljava/lang/String;
    //   201: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: getstatic 82	java/io/File:separator	Ljava/lang/String;
    //   207: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: ldc 84
    //   212: invokevirtual 78	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 88	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokespecial 91	java/io/File:<init>	(Ljava/lang/String;)V
    //   221: invokespecial 210	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   224: astore_1
    //   225: aload_0
    //   226: getfield 41	ags:h	I
    //   229: invokestatic 212	ags:c	(I)[B
    //   232: astore 5
    //   234: aload_0
    //   235: getfield 43	ags:i	I
    //   238: invokestatic 212	ags:c	(I)[B
    //   241: astore 6
    //   243: aload_0
    //   244: getfield 45	ags:j	J
    //   247: invokestatic 214	ags:a	(J)[B
    //   250: astore 7
    //   252: aload_0
    //   253: getfield 29	ags:b	Z
    //   256: ifeq +223 -> 479
    //   259: aload_1
    //   260: bipush 22
    //   262: newarray <illegal type>
    //   264: dup
    //   265: iconst_0
    //   266: iload_2
    //   267: i2b
    //   268: bastore
    //   269: dup
    //   270: iconst_1
    //   271: aload_0
    //   272: getfield 31	ags:c	I
    //   275: sipush 10240
    //   278: idiv
    //   279: i2b
    //   280: bastore
    //   281: dup
    //   282: iconst_2
    //   283: aload_0
    //   284: getfield 33	ags:d	I
    //   287: sipush 10240
    //   290: idiv
    //   291: i2b
    //   292: bastore
    //   293: dup
    //   294: iconst_3
    //   295: aload_0
    //   296: getfield 35	ags:e	I
    //   299: i2b
    //   300: bastore
    //   301: dup
    //   302: iconst_4
    //   303: aload_0
    //   304: getfield 37	ags:f	I
    //   307: bipush 10
    //   309: idiv
    //   310: i2b
    //   311: bastore
    //   312: dup
    //   313: iconst_5
    //   314: aload_0
    //   315: getfield 39	ags:g	I
    //   318: i2b
    //   319: bastore
    //   320: dup
    //   321: bipush 6
    //   323: aload 5
    //   325: iconst_0
    //   326: baload
    //   327: bastore
    //   328: dup
    //   329: bipush 7
    //   331: aload 5
    //   333: iconst_1
    //   334: baload
    //   335: bastore
    //   336: dup
    //   337: bipush 8
    //   339: aload 5
    //   341: iconst_2
    //   342: baload
    //   343: bastore
    //   344: dup
    //   345: bipush 9
    //   347: aload 5
    //   349: iconst_3
    //   350: baload
    //   351: bastore
    //   352: dup
    //   353: bipush 10
    //   355: aload 6
    //   357: iconst_0
    //   358: baload
    //   359: bastore
    //   360: dup
    //   361: bipush 11
    //   363: aload 6
    //   365: iconst_1
    //   366: baload
    //   367: bastore
    //   368: dup
    //   369: bipush 12
    //   371: aload 6
    //   373: iconst_2
    //   374: baload
    //   375: bastore
    //   376: dup
    //   377: bipush 13
    //   379: aload 6
    //   381: iconst_3
    //   382: baload
    //   383: bastore
    //   384: dup
    //   385: bipush 14
    //   387: aload 7
    //   389: iconst_0
    //   390: baload
    //   391: bastore
    //   392: dup
    //   393: bipush 15
    //   395: aload 7
    //   397: iconst_1
    //   398: baload
    //   399: bastore
    //   400: dup
    //   401: bipush 16
    //   403: aload 7
    //   405: iconst_2
    //   406: baload
    //   407: bastore
    //   408: dup
    //   409: bipush 17
    //   411: aload 7
    //   413: iconst_3
    //   414: baload
    //   415: bastore
    //   416: dup
    //   417: bipush 18
    //   419: aload 7
    //   421: iconst_4
    //   422: baload
    //   423: bastore
    //   424: dup
    //   425: bipush 19
    //   427: aload 7
    //   429: iconst_5
    //   430: baload
    //   431: bastore
    //   432: dup
    //   433: bipush 20
    //   435: aload 7
    //   437: bipush 6
    //   439: baload
    //   440: bastore
    //   441: dup
    //   442: bipush 21
    //   444: aload 7
    //   446: bipush 7
    //   448: baload
    //   449: bastore
    //   450: invokevirtual 217	java/io/FileOutputStream:write	([B)V
    //   453: aload_1
    //   454: invokevirtual 218	java/io/FileOutputStream:close	()V
    //   457: iload 4
    //   459: ireturn
    //   460: iconst_0
    //   461: istore 4
    //   463: goto -431 -> 32
    //   466: iconst_0
    //   467: istore 4
    //   469: goto -294 -> 175
    //   472: astore_1
    //   473: iconst_0
    //   474: istore 4
    //   476: goto -301 -> 175
    //   479: iconst_0
    //   480: istore_2
    //   481: goto -222 -> 259
    //   484: astore_1
    //   485: aconst_null
    //   486: astore_1
    //   487: aload_1
    //   488: ifnull -31 -> 457
    //   491: aload_1
    //   492: invokevirtual 218	java/io/FileOutputStream:close	()V
    //   495: iload 4
    //   497: ireturn
    //   498: astore_1
    //   499: iload 4
    //   501: ireturn
    //   502: astore 5
    //   504: aconst_null
    //   505: astore_1
    //   506: aload_1
    //   507: ifnull +7 -> 514
    //   510: aload_1
    //   511: invokevirtual 218	java/io/FileOutputStream:close	()V
    //   514: aload 5
    //   516: athrow
    //   517: astore_1
    //   518: iload 4
    //   520: ireturn
    //   521: astore_1
    //   522: goto -8 -> 514
    //   525: astore 5
    //   527: goto -21 -> 506
    //   530: astore 5
    //   532: goto -45 -> 487
    //   535: iconst_0
    //   536: istore 4
    //   538: goto -363 -> 175
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	541	0	this	ags
    //   0	541	1	paramString	String
    //   1	480	2	m	int
    //   53	116	3	n	int
    //   30	507	4	bool	boolean
    //   232	116	5	arrayOfByte1	byte[]
    //   502	13	5	localObject1	Object
    //   525	1	5	localObject2	Object
    //   530	1	5	localException	Exception
    //   241	139	6	arrayOfByte2	byte[]
    //   250	195	7	arrayOfByte3	byte[]
    // Exception table:
    //   from	to	target	type
    //   2	29	472	java/lang/Exception
    //   32	38	472	java/lang/Exception
    //   38	138	472	java/lang/Exception
    //   138	152	472	java/lang/Exception
    //   152	168	472	java/lang/Exception
    //   175	225	484	java/lang/Exception
    //   491	495	498	java/lang/Exception
    //   175	225	502	finally
    //   453	457	517	java/lang/Exception
    //   510	514	521	java/lang/Exception
    //   225	259	525	finally
    //   259	453	525	finally
    //   225	259	530	java/lang/Exception
    //   259	453	530	java/lang/Exception
  }
  
  protected final int b()
  {
    return e;
  }
  
  protected final void b(int paramInt)
  {
    g();
    int m = paramInt;
    if (paramInt < 0) {
      m = 0;
    }
    i = m;
  }
  
  protected final int c()
  {
    return f;
  }
  
  protected final int d()
  {
    return g;
  }
  
  protected final int e()
  {
    g();
    return h;
  }
  
  protected final int f()
  {
    g();
    return i;
  }
}

/* Location:
 * Qualified Name:     ags
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */