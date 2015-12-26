import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public abstract class ow
  extends on
{
  private final String a = "Transaction";
  protected Context b;
  protected String c;
  public pa d;
  protected oz e;
  public long f;
  protected Uri g;
  protected int h;
  public Object i;
  protected int j;
  protected int k;
  protected String l;
  protected int m;
  protected long n = 0L;
  protected int o;
  private final int p;
  
  public ow(Context paramContext, int paramInt, long paramLong)
  {
    b = paramContext;
    d = new pa();
    p = paramInt;
    f = paramLong;
    i = new Object();
    l = "";
  }
  
  public ow(Context paramContext, int paramInt, oz paramoz)
  {
    b = paramContext;
    d = new pa();
    p = paramInt;
    e = paramoz;
    i = new Object();
    l = "";
  }
  
  /* Error */
  public java.io.File a(byte[] paramArrayOfByte, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 41	ow:b	Landroid/content/Context;
    //   4: invokevirtual 72	android/content/Context:getFilesDir	()Ljava/io/File;
    //   7: astore_3
    //   8: new 74	java/io/File
    //   11: dup
    //   12: new 76	java/lang/StringBuilder
    //   15: dup
    //   16: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   19: aload_3
    //   20: invokevirtual 81	java/io/File:toString	()Ljava/lang/String;
    //   23: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: ldc 87
    //   28: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: ldc 89
    //   33: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   42: astore_3
    //   43: aload_3
    //   44: invokevirtual 97	java/io/File:exists	()Z
    //   47: ifne +15 -> 62
    //   50: aload_3
    //   51: invokevirtual 100	java/io/File:mkdirs	()Z
    //   54: pop
    //   55: aload_3
    //   56: iconst_1
    //   57: iconst_0
    //   58: invokevirtual 104	java/io/File:setExecutable	(ZZ)Z
    //   61: pop
    //   62: new 74	java/io/File
    //   65: dup
    //   66: new 76	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   73: aload_3
    //   74: invokevirtual 81	java/io/File:toString	()Ljava/lang/String;
    //   77: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: ldc 87
    //   82: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: aload_2
    //   86: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   95: astore_3
    //   96: new 106	java/io/FileOutputStream
    //   99: dup
    //   100: aload_3
    //   101: iconst_0
    //   102: invokespecial 109	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   105: astore_2
    //   106: aload_1
    //   107: ifnull +8 -> 115
    //   110: aload_2
    //   111: aload_1
    //   112: invokevirtual 113	java/io/FileOutputStream:write	([B)V
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 116	java/io/FileOutputStream:close	()V
    //   123: aload_3
    //   124: iconst_1
    //   125: iconst_0
    //   126: invokevirtual 119	java/io/File:setReadable	(ZZ)Z
    //   129: pop
    //   130: aload_3
    //   131: areturn
    //   132: aload_2
    //   133: ifnull +7 -> 140
    //   136: aload_2
    //   137: invokevirtual 116	java/io/FileOutputStream:close	()V
    //   140: aload_1
    //   141: athrow
    //   142: astore_1
    //   143: aload_1
    //   144: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   147: aconst_null
    //   148: areturn
    //   149: astore_1
    //   150: goto -18 -> 132
    //   153: astore_1
    //   154: aconst_null
    //   155: astore_2
    //   156: goto -24 -> 132
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	159	0	this	ow
    //   0	159	1	paramArrayOfByte	byte[]
    //   0	159	2	paramString	String
    //   7	124	3	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   0	62	142	java/io/IOException
    //   62	96	142	java/io/IOException
    //   119	123	142	java/io/IOException
    //   123	130	142	java/io/IOException
    //   136	140	142	java/io/IOException
    //   140	142	142	java/io/IOException
    //   110	115	149	finally
    //   96	106	153	finally
  }
  
  public String a(Uri paramUri)
  {
    if (l.length() > 1) {
      return l;
    }
    Cursor localCursor = b.getContentResolver().query(paramUri, new String[] { "uuid" }, null, null, null);
    if (localCursor != null) {}
    try
    {
      if ((localCursor.getCount() != 1) || (!localCursor.moveToFirst())) {
        Log.e("Transaction", "getMessageUUID from bad uri :" + paramUri.toString());
      }
      l = localCursor.getString(0);
      return l;
    }
    finally
    {
      if (localCursor != null) {
        localCursor.close();
      }
    }
  }
  
  public abstract void a();
  
  public void a(long paramLong)
  {
    n = paramLong;
  }
  
  public void a(oz paramoz)
  {
    e = paramoz;
  }
  
  public boolean a(ow paramow)
  {
    return c.equals(c);
  }
  
  /* Error */
  protected int b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 7
    //   5: aconst_null
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: iconst_1
    //   12: istore_2
    //   13: new 74	java/io/File
    //   16: dup
    //   17: new 76	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   24: aload_0
    //   25: getfield 41	ow:b	Landroid/content/Context;
    //   28: invokevirtual 72	android/content/Context:getFilesDir	()Ljava/io/File;
    //   31: invokevirtual 81	java/io/File:toString	()Ljava/lang/String;
    //   34: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: ldc 87
    //   39: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: ldc 89
    //   44: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: ldc 87
    //   49: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: aload_1
    //   53: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   62: astore_1
    //   63: aload_1
    //   64: invokevirtual 97	java/io/File:exists	()Z
    //   67: ifne +33 -> 100
    //   70: ldc 35
    //   72: new 76	java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   79: ldc -80
    //   81: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload_1
    //   85: invokevirtual 81	java/io/File:toString	()Ljava/lang/String;
    //   88: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: invokestatic 159	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   97: pop
    //   98: iload_2
    //   99: ireturn
    //   100: new 178	java/io/FileInputStream
    //   103: dup
    //   104: aload_1
    //   105: invokespecial 181	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   108: astore 5
    //   110: aload 5
    //   112: astore_3
    //   113: aload 7
    //   115: astore_1
    //   116: aload 6
    //   118: astore 4
    //   120: aload 5
    //   122: invokevirtual 185	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   125: astore 6
    //   127: aload 5
    //   129: astore_3
    //   130: aload 6
    //   132: astore_1
    //   133: aload 6
    //   135: astore 4
    //   137: aload 6
    //   139: invokevirtual 191	java/nio/channels/FileChannel:size	()J
    //   142: l2i
    //   143: invokestatic 197	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   146: astore 7
    //   148: aload 5
    //   150: astore_3
    //   151: aload 6
    //   153: astore_1
    //   154: aload 6
    //   156: astore 4
    //   158: aload 6
    //   160: aload 7
    //   162: invokevirtual 201	java/nio/channels/FileChannel:read	(Ljava/nio/ByteBuffer;)I
    //   165: ifgt -17 -> 148
    //   168: aload 5
    //   170: astore_3
    //   171: aload 6
    //   173: astore_1
    //   174: aload 6
    //   176: astore 4
    //   178: new 203	com/meizu/android/mms/pdu/MzPduParser
    //   181: dup
    //   182: aload 7
    //   184: invokevirtual 207	java/nio/ByteBuffer:array	()[B
    //   187: invokespecial 209	com/meizu/android/mms/pdu/MzPduParser:<init>	([B)V
    //   190: invokevirtual 213	com/meizu/android/mms/pdu/MzPduParser:parse	()Lcom/meizu/android/mms/pdu/MzGenericPdu;
    //   193: astore 7
    //   195: aload 7
    //   197: ifnull +21 -> 218
    //   200: aload 5
    //   202: astore_3
    //   203: aload 6
    //   205: astore_1
    //   206: aload 6
    //   208: astore 4
    //   210: aload 7
    //   212: instanceof 215
    //   215: ifne +58 -> 273
    //   218: aload 5
    //   220: astore_3
    //   221: aload 6
    //   223: astore_1
    //   224: aload 6
    //   226: astore 4
    //   228: ldc 35
    //   230: ldc -39
    //   232: invokestatic 159	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   235: pop
    //   236: aload 6
    //   238: ifnull +8 -> 246
    //   241: aload 6
    //   243: invokevirtual 218	java/nio/channels/FileChannel:close	()V
    //   246: aload 5
    //   248: ifnull -150 -> 98
    //   251: aload 5
    //   253: invokevirtual 219	java/io/FileInputStream:close	()V
    //   256: iconst_1
    //   257: ireturn
    //   258: astore_1
    //   259: aload_1
    //   260: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   263: iconst_1
    //   264: ireturn
    //   265: astore_1
    //   266: aload_1
    //   267: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   270: goto -24 -> 246
    //   273: iconst_m1
    //   274: istore_2
    //   275: aload 6
    //   277: ifnull +8 -> 285
    //   280: aload 6
    //   282: invokevirtual 218	java/nio/channels/FileChannel:close	()V
    //   285: aload 5
    //   287: ifnull -189 -> 98
    //   290: aload 5
    //   292: invokevirtual 219	java/io/FileInputStream:close	()V
    //   295: iconst_m1
    //   296: ireturn
    //   297: astore_1
    //   298: aload_1
    //   299: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   302: iconst_m1
    //   303: ireturn
    //   304: astore_1
    //   305: aload_1
    //   306: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   309: goto -24 -> 285
    //   312: astore 6
    //   314: aconst_null
    //   315: astore 5
    //   317: aload 5
    //   319: astore_3
    //   320: aload 4
    //   322: astore_1
    //   323: aload 6
    //   325: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   328: aload 4
    //   330: ifnull +8 -> 338
    //   333: aload 4
    //   335: invokevirtual 218	java/nio/channels/FileChannel:close	()V
    //   338: aload 5
    //   340: ifnull -242 -> 98
    //   343: aload 5
    //   345: invokevirtual 219	java/io/FileInputStream:close	()V
    //   348: iconst_1
    //   349: ireturn
    //   350: astore_1
    //   351: aload_1
    //   352: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   355: iconst_1
    //   356: ireturn
    //   357: astore_1
    //   358: aload_1
    //   359: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   362: goto -24 -> 338
    //   365: astore 4
    //   367: aconst_null
    //   368: astore 5
    //   370: aload_3
    //   371: astore_1
    //   372: aload 5
    //   374: astore_3
    //   375: aload_1
    //   376: ifnull +7 -> 383
    //   379: aload_1
    //   380: invokevirtual 218	java/nio/channels/FileChannel:close	()V
    //   383: aload_3
    //   384: ifnull +7 -> 391
    //   387: aload_3
    //   388: invokevirtual 219	java/io/FileInputStream:close	()V
    //   391: aload 4
    //   393: athrow
    //   394: astore_1
    //   395: aload_1
    //   396: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   399: goto -16 -> 383
    //   402: astore_1
    //   403: aload_1
    //   404: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   407: goto -16 -> 391
    //   410: astore 4
    //   412: goto -37 -> 375
    //   415: astore 6
    //   417: goto -100 -> 317
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	420	0	this	ow
    //   0	420	1	paramString	String
    //   12	263	2	i1	int
    //   1	387	3	localObject1	Object
    //   9	325	4	localFileChannel1	java.nio.channels.FileChannel
    //   365	27	4	localObject2	Object
    //   410	1	4	localObject3	Object
    //   108	265	5	localFileInputStream	java.io.FileInputStream
    //   6	275	6	localFileChannel2	java.nio.channels.FileChannel
    //   312	12	6	localIOException1	java.io.IOException
    //   415	1	6	localIOException2	java.io.IOException
    //   3	208	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   251	256	258	java/io/IOException
    //   241	246	265	java/io/IOException
    //   290	295	297	java/io/IOException
    //   280	285	304	java/io/IOException
    //   100	110	312	java/io/IOException
    //   343	348	350	java/io/IOException
    //   333	338	357	java/io/IOException
    //   100	110	365	finally
    //   379	383	394	java/io/IOException
    //   387	391	402	java/io/IOException
    //   120	127	410	finally
    //   137	148	410	finally
    //   158	168	410	finally
    //   178	195	410	finally
    //   210	218	410	finally
    //   228	236	410	finally
    //   323	328	410	finally
    //   120	127	415	java/io/IOException
    //   137	148	415	java/io/IOException
    //   158	168	415	java/io/IOException
    //   178	195	415	java/io/IOException
    //   210	218	415	java/io/IOException
    //   228	236	415	java/io/IOException
  }
  
  public void b(int paramInt)
  {
    j = paramInt;
  }
  
  /* Error */
  public void b(Uri paramUri)
  {
    // Byte code:
    //   0: getstatic 228	android/provider/Telephony$MmsSms$PendingMessages:CONTENT_URI	Landroid/net/Uri;
    //   3: invokevirtual 232	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   6: astore_3
    //   7: new 76	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   14: ldc -22
    //   16: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: invokestatic 240	android/content/ContentUris:parseId	(Landroid/net/Uri;)J
    //   23: invokevirtual 243	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   26: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: astore_1
    //   30: aload_0
    //   31: getfield 41	ow:b	Landroid/content/Context;
    //   34: invokevirtual 133	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   37: aload_3
    //   38: invokevirtual 249	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   41: aconst_null
    //   42: aload_1
    //   43: aconst_null
    //   44: aconst_null
    //   45: invokevirtual 141	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   48: astore_1
    //   49: aload_1
    //   50: invokeinterface 146 1 0
    //   55: istore_2
    //   56: ldc 35
    //   58: new 76	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   65: ldc -5
    //   67: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: iload_2
    //   71: invokevirtual 254	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   74: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: invokestatic 256	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   80: pop
    //   81: aload_1
    //   82: ifnull +69 -> 151
    //   85: iload_2
    //   86: ifle +65 -> 151
    //   89: aload_1
    //   90: invokeinterface 149 1 0
    //   95: pop
    //   96: aload_0
    //   97: aload_1
    //   98: aload_1
    //   99: ldc_w 258
    //   102: invokeinterface 261 2 0
    //   107: invokeinterface 265 2 0
    //   112: putfield 267	ow:m	I
    //   115: ldc 35
    //   117: new 76	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   124: ldc_w 269
    //   127: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload_0
    //   131: getfield 267	ow:m	I
    //   134: invokevirtual 254	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   137: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokestatic 256	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   143: pop
    //   144: aload_1
    //   145: invokeinterface 164 1 0
    //   150: return
    //   151: aload_0
    //   152: iconst_0
    //   153: putfield 267	ow:m	I
    //   156: goto -41 -> 115
    //   159: astore_3
    //   160: ldc 35
    //   162: new 76	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   169: ldc_w 269
    //   172: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: aload_0
    //   176: getfield 267	ow:m	I
    //   179: invokevirtual 254	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   182: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokestatic 256	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   188: pop
    //   189: aload_1
    //   190: invokeinterface 164 1 0
    //   195: aload_3
    //   196: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	197	0	this	ow
    //   0	197	1	paramUri	Uri
    //   55	31	2	i1	int
    //   6	32	3	localBuilder	android.net.Uri.Builder
    //   159	37	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   49	81	159	finally
    //   89	115	159	finally
    //   151	156	159	finally
  }
  
  public abstract int c();
  
  public void c(int paramInt)
  {
    k = paramInt;
  }
  
  public String e()
  {
    return c;
  }
  
  public pa f()
  {
    return d;
  }
  
  public int g()
  {
    return p;
  }
  
  public oz h()
  {
    return e;
  }
  
  public Uri i()
  {
    return g;
  }
  
  public String j()
  {
    return l;
  }
  
  public int k()
  {
    return k;
  }
  
  public String l()
  {
    return c;
  }
  
  public long m()
  {
    return n;
  }
  
  public String toString()
  {
    return getClass().getName() + ": serviceId=" + p;
  }
}

/* Location:
 * Qualified Name:     ow
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */