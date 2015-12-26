import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.zip.GZIPInputStream;

public final class ahc
{
  private RandomAccessFile a;
  private agc b;
  private File c = null;
  
  protected ahc(agc paramagc)
  {
    b = paramagc;
  }
  
  private static byte a(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    ByteArrayInputStream localByteArrayInputStream;
    GZIPInputStream localGZIPInputStream;
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      localObject1 = localObject2;
      localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
      localObject1 = localObject2;
      paramArrayOfByte = new byte['Ѐ'];
      localObject1 = localObject2;
      localByteArrayOutputStream = new ByteArrayOutputStream();
      for (;;)
      {
        localObject1 = localObject2;
        int i = localGZIPInputStream.read(paramArrayOfByte, 0, paramArrayOfByte.length);
        if (i == -1) {
          break;
        }
        localObject1 = localObject2;
        localByteArrayOutputStream.write(paramArrayOfByte, 0, i);
      }
      return paramArrayOfByte[0];
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte = (byte[])localObject1;
    }
    for (;;)
    {
      localObject1 = localObject2;
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.flush();
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.close();
      localObject1 = paramArrayOfByte;
      localGZIPInputStream.close();
      localObject1 = paramArrayOfByte;
      localByteArrayInputStream.close();
    }
  }
  
  private static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = (paramInt3 - 1) * 1500 + paramInt1;
    while (paramInt1 >= paramInt2) {
      paramInt1 -= 1500;
    }
    return paramInt1;
  }
  
  private int a(BitSet paramBitSet)
  {
    int k = 0;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < paramBitSet.length())
      {
        if (paramBitSet.get(i)) {
          j = b.a() + (i * 1500 + 4);
        }
      }
      else {
        return j;
      }
      i += 1;
    }
  }
  
  private ArrayList a(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    while (paramInt1 <= paramInt2)
    {
      try
      {
        a.seek(paramInt1);
        int i = a.readInt();
        a.readLong();
        if (i <= 0) {
          break label114;
        }
        if (i > 1500) {
          return null;
        }
        byte[] arrayOfByte = new byte[i];
        a.read(arrayOfByte);
        i = a(arrayOfByte);
        if ((i != 3) && (i != 4) && (i != 41)) {
          break label114;
        }
        localArrayList.add(arrayOfByte);
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
      paramInt1 += 1500;
    }
    return localArrayList;
    label114:
    return null;
  }
  
  private BitSet b()
  {
    Object localObject = new byte[b.a()];
    try
    {
      a.read((byte[])localObject);
      localObject = agc.b((byte[])localObject);
      return (BitSet)localObject;
    }
    catch (IOException localIOException) {}
    return null;
  }
  
  /* Error */
  protected final int a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore 6
    //   5: iconst_0
    //   6: istore 7
    //   8: iconst_0
    //   9: istore 8
    //   11: iconst_0
    //   12: istore_1
    //   13: aload_0
    //   14: monitorenter
    //   15: aload_0
    //   16: aload_0
    //   17: getfield 19	ahc:b	Lagc;
    //   20: invokevirtual 113	agc:b	()Ljava/io/File;
    //   23: putfield 17	ahc:c	Ljava/io/File;
    //   26: iload 6
    //   28: istore_3
    //   29: iload 7
    //   31: istore 4
    //   33: iload 8
    //   35: istore 5
    //   37: aload_0
    //   38: getfield 17	ahc:c	Ljava/io/File;
    //   41: ifnull +157 -> 198
    //   44: iload 6
    //   46: istore_3
    //   47: iload 7
    //   49: istore 4
    //   51: iload 8
    //   53: istore 5
    //   55: aload_0
    //   56: new 82	java/io/RandomAccessFile
    //   59: dup
    //   60: aload_0
    //   61: getfield 19	ahc:b	Lagc;
    //   64: invokevirtual 113	agc:b	()Ljava/io/File;
    //   67: ldc 115
    //   69: invokespecial 118	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   72: putfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   75: iload 6
    //   77: istore_3
    //   78: iload 7
    //   80: istore 4
    //   82: iload 8
    //   84: istore 5
    //   86: aload_0
    //   87: getfield 19	ahc:b	Lagc;
    //   90: invokevirtual 72	agc:a	()I
    //   93: newarray <illegal type>
    //   95: astore 10
    //   97: iload 6
    //   99: istore_3
    //   100: iload 7
    //   102: istore 4
    //   104: iload 8
    //   106: istore 5
    //   108: aload_0
    //   109: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   112: aload 10
    //   114: invokevirtual 96	java/io/RandomAccessFile:read	([B)I
    //   117: pop
    //   118: iload 6
    //   120: istore_3
    //   121: iload 7
    //   123: istore 4
    //   125: iload 8
    //   127: istore 5
    //   129: aload 10
    //   131: invokestatic 106	agc:b	([B)Ljava/util/BitSet;
    //   134: astore 10
    //   136: iconst_0
    //   137: istore 6
    //   139: iload_1
    //   140: istore_2
    //   141: iload_1
    //   142: istore_3
    //   143: iload_1
    //   144: istore 4
    //   146: iload_1
    //   147: istore 5
    //   149: iload 6
    //   151: aload 10
    //   153: invokevirtual 121	java/util/BitSet:size	()I
    //   156: if_icmpge +42 -> 198
    //   159: iload_1
    //   160: istore_3
    //   161: iload_1
    //   162: istore 4
    //   164: iload_1
    //   165: istore 5
    //   167: aload 10
    //   169: iload 6
    //   171: invokevirtual 68	java/util/BitSet:get	(I)Z
    //   174: istore 9
    //   176: iload_1
    //   177: istore_2
    //   178: iload 9
    //   180: ifeq +7 -> 187
    //   183: iload_1
    //   184: iconst_1
    //   185: iadd
    //   186: istore_2
    //   187: iload 6
    //   189: iconst_1
    //   190: iadd
    //   191: istore 6
    //   193: iload_2
    //   194: istore_1
    //   195: goto -56 -> 139
    //   198: aload_0
    //   199: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   202: astore 10
    //   204: iload_2
    //   205: istore_1
    //   206: aload 10
    //   208: ifnull +12 -> 220
    //   211: aload_0
    //   212: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   215: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   218: iload_2
    //   219: istore_1
    //   220: aload_0
    //   221: aconst_null
    //   222: putfield 17	ahc:c	Ljava/io/File;
    //   225: aload_0
    //   226: monitorexit
    //   227: iload_1
    //   228: ireturn
    //   229: astore 10
    //   231: aload_0
    //   232: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   235: astore 10
    //   237: iload_3
    //   238: istore_1
    //   239: aload 10
    //   241: ifnull -21 -> 220
    //   244: aload_0
    //   245: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   248: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   251: iload_3
    //   252: istore_1
    //   253: goto -33 -> 220
    //   256: astore 10
    //   258: iload_3
    //   259: istore_1
    //   260: goto -40 -> 220
    //   263: astore 10
    //   265: aload_0
    //   266: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   269: astore 10
    //   271: iload 4
    //   273: istore_1
    //   274: aload 10
    //   276: ifnull -56 -> 220
    //   279: aload_0
    //   280: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   283: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   286: iload 4
    //   288: istore_1
    //   289: goto -69 -> 220
    //   292: astore 10
    //   294: iload 4
    //   296: istore_1
    //   297: goto -77 -> 220
    //   300: astore 10
    //   302: aload_0
    //   303: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   306: astore 10
    //   308: iload 5
    //   310: istore_1
    //   311: aload 10
    //   313: ifnull -93 -> 220
    //   316: aload_0
    //   317: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   320: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   323: iload 5
    //   325: istore_1
    //   326: goto -106 -> 220
    //   329: astore 10
    //   331: iload 5
    //   333: istore_1
    //   334: goto -114 -> 220
    //   337: astore 10
    //   339: aload_0
    //   340: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   343: astore 11
    //   345: aload 11
    //   347: ifnull +10 -> 357
    //   350: aload_0
    //   351: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   354: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   357: aload 10
    //   359: athrow
    //   360: astore 10
    //   362: aload_0
    //   363: monitorexit
    //   364: aload 10
    //   366: athrow
    //   367: astore 11
    //   369: goto -12 -> 357
    //   372: astore 10
    //   374: iload_2
    //   375: istore_1
    //   376: goto -156 -> 220
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	379	0	this	ahc
    //   12	364	1	i	int
    //   1	374	2	j	int
    //   28	231	3	k	int
    //   31	264	4	m	int
    //   35	297	5	n	int
    //   3	189	6	i1	int
    //   6	116	7	i2	int
    //   9	117	8	i3	int
    //   174	5	9	bool	boolean
    //   95	112	10	localObject1	Object
    //   229	1	10	localFileNotFoundException	java.io.FileNotFoundException
    //   235	5	10	localRandomAccessFile1	RandomAccessFile
    //   256	1	10	localIOException1	IOException
    //   263	1	10	localIOException2	IOException
    //   269	6	10	localRandomAccessFile2	RandomAccessFile
    //   292	1	10	localIOException3	IOException
    //   300	1	10	localNullPointerException	NullPointerException
    //   306	6	10	localRandomAccessFile3	RandomAccessFile
    //   329	1	10	localIOException4	IOException
    //   337	21	10	localObject2	Object
    //   360	5	10	localObject3	Object
    //   372	1	10	localIOException5	IOException
    //   343	3	11	localRandomAccessFile4	RandomAccessFile
    //   367	1	11	localIOException6	IOException
    // Exception table:
    //   from	to	target	type
    //   37	44	229	java/io/FileNotFoundException
    //   55	75	229	java/io/FileNotFoundException
    //   86	97	229	java/io/FileNotFoundException
    //   108	118	229	java/io/FileNotFoundException
    //   129	136	229	java/io/FileNotFoundException
    //   149	159	229	java/io/FileNotFoundException
    //   167	176	229	java/io/FileNotFoundException
    //   244	251	256	java/io/IOException
    //   37	44	263	java/io/IOException
    //   55	75	263	java/io/IOException
    //   86	97	263	java/io/IOException
    //   108	118	263	java/io/IOException
    //   129	136	263	java/io/IOException
    //   149	159	263	java/io/IOException
    //   167	176	263	java/io/IOException
    //   279	286	292	java/io/IOException
    //   37	44	300	java/lang/NullPointerException
    //   55	75	300	java/lang/NullPointerException
    //   86	97	300	java/lang/NullPointerException
    //   108	118	300	java/lang/NullPointerException
    //   129	136	300	java/lang/NullPointerException
    //   149	159	300	java/lang/NullPointerException
    //   167	176	300	java/lang/NullPointerException
    //   316	323	329	java/io/IOException
    //   37	44	337	finally
    //   55	75	337	finally
    //   86	97	337	finally
    //   108	118	337	finally
    //   129	136	337	finally
    //   149	159	337	finally
    //   167	176	337	finally
    //   15	26	360	finally
    //   198	204	360	finally
    //   211	218	360	finally
    //   220	227	360	finally
    //   231	237	360	finally
    //   244	251	360	finally
    //   265	271	360	finally
    //   279	286	360	finally
    //   302	308	360	finally
    //   316	323	360	finally
    //   339	345	360	finally
    //   350	357	360	finally
    //   357	360	360	finally
    //   350	357	367	java/io/IOException
    //   211	218	372	java/io/IOException
  }
  
  /* Error */
  protected final agb a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 19	ahc:b	Lagc;
    //   9: astore_3
    //   10: aload_3
    //   11: ifnonnull +10 -> 21
    //   14: aload 4
    //   16: astore_3
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_3
    //   20: areturn
    //   21: aload_0
    //   22: monitorenter
    //   23: aload_0
    //   24: aload_0
    //   25: getfield 19	ahc:b	Lagc;
    //   28: invokevirtual 113	agc:b	()Ljava/io/File;
    //   31: putfield 17	ahc:c	Ljava/io/File;
    //   34: aload_0
    //   35: getfield 17	ahc:c	Ljava/io/File;
    //   38: ifnonnull +21 -> 59
    //   41: aload_0
    //   42: monitorexit
    //   43: aload 4
    //   45: astore_3
    //   46: goto -29 -> 17
    //   49: astore_3
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_3
    //   53: athrow
    //   54: astore_3
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_3
    //   58: athrow
    //   59: aload_0
    //   60: new 82	java/io/RandomAccessFile
    //   63: dup
    //   64: aload_0
    //   65: getfield 17	ahc:c	Ljava/io/File;
    //   68: ldc 115
    //   70: invokespecial 118	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   73: putfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   76: aload_0
    //   77: invokespecial 125	ahc:b	()Ljava/util/BitSet;
    //   80: astore_3
    //   81: aload_3
    //   82: ifnonnull +35 -> 117
    //   85: aload_0
    //   86: getfield 17	ahc:c	Ljava/io/File;
    //   89: invokevirtual 131	java/io/File:delete	()Z
    //   92: pop
    //   93: aload_0
    //   94: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   97: astore_3
    //   98: aload_3
    //   99: ifnull +10 -> 109
    //   102: aload_0
    //   103: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   106: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   109: aload_0
    //   110: monitorexit
    //   111: aload 4
    //   113: astore_3
    //   114: goto -97 -> 17
    //   117: aload_0
    //   118: aload_3
    //   119: invokespecial 133	ahc:a	(Ljava/util/BitSet;)I
    //   122: istore_2
    //   123: iload_2
    //   124: aload_0
    //   125: getfield 17	ahc:c	Ljava/io/File;
    //   128: invokevirtual 135	java/io/File:length	()J
    //   131: l2i
    //   132: iload_1
    //   133: invokestatic 137	ahc:a	(III)I
    //   136: istore_1
    //   137: aload_0
    //   138: iload_2
    //   139: iload_1
    //   140: invokespecial 139	ahc:a	(II)Ljava/util/ArrayList;
    //   143: astore_3
    //   144: aload_3
    //   145: ifnonnull +35 -> 180
    //   148: aload_0
    //   149: getfield 17	ahc:c	Ljava/io/File;
    //   152: invokevirtual 131	java/io/File:delete	()Z
    //   155: pop
    //   156: aload_0
    //   157: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   160: astore_3
    //   161: aload_3
    //   162: ifnull +10 -> 172
    //   165: aload_0
    //   166: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   169: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   172: aload_0
    //   173: monitorexit
    //   174: aload 4
    //   176: astore_3
    //   177: goto -160 -> 17
    //   180: iload_2
    //   181: aload_0
    //   182: getfield 19	ahc:b	Lagc;
    //   185: invokevirtual 72	agc:a	()I
    //   188: isub
    //   189: iconst_4
    //   190: isub
    //   191: sipush 1500
    //   194: idiv
    //   195: istore_2
    //   196: iload_1
    //   197: aload_0
    //   198: getfield 19	ahc:b	Lagc;
    //   201: invokevirtual 72	agc:a	()I
    //   204: isub
    //   205: iconst_4
    //   206: isub
    //   207: sipush 1500
    //   210: idiv
    //   211: istore_1
    //   212: new 141	agb
    //   215: dup
    //   216: aload_0
    //   217: getfield 17	ahc:c	Ljava/io/File;
    //   220: aload_3
    //   221: iconst_2
    //   222: newarray <illegal type>
    //   224: dup
    //   225: iconst_0
    //   226: iload_2
    //   227: iastore
    //   228: dup
    //   229: iconst_1
    //   230: iload_1
    //   231: iastore
    //   232: invokespecial 144	agb:<init>	(Ljava/io/File;Ljava/util/ArrayList;[I)V
    //   235: astore 5
    //   237: aload_0
    //   238: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   241: astore 6
    //   243: aload 5
    //   245: astore_3
    //   246: aload 6
    //   248: ifnull +13 -> 261
    //   251: aload_0
    //   252: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   255: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   258: aload 5
    //   260: astore_3
    //   261: aload_3
    //   262: ifnull +21 -> 283
    //   265: aload_3
    //   266: invokevirtual 146	agb:c	()I
    //   269: bipush 100
    //   271: if_icmple +12 -> 283
    //   274: aload_3
    //   275: invokevirtual 146	agb:c	()I
    //   278: ldc -109
    //   280: if_icmplt +101 -> 381
    //   283: aload_0
    //   284: getfield 17	ahc:c	Ljava/io/File;
    //   287: invokevirtual 131	java/io/File:delete	()Z
    //   290: pop
    //   291: aload_0
    //   292: aconst_null
    //   293: putfield 17	ahc:c	Ljava/io/File;
    //   296: aload_0
    //   297: monitorexit
    //   298: aload 4
    //   300: astore_3
    //   301: goto -284 -> 17
    //   304: astore_3
    //   305: aload_0
    //   306: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   309: astore_3
    //   310: aload_3
    //   311: ifnull +95 -> 406
    //   314: aload_0
    //   315: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   318: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   321: aconst_null
    //   322: astore_3
    //   323: goto -62 -> 261
    //   326: astore_3
    //   327: aconst_null
    //   328: astore_3
    //   329: goto -68 -> 261
    //   332: astore_3
    //   333: aload_0
    //   334: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   337: astore_3
    //   338: aload_3
    //   339: ifnull +67 -> 406
    //   342: aload_0
    //   343: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   346: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   349: aconst_null
    //   350: astore_3
    //   351: goto -90 -> 261
    //   354: astore_3
    //   355: aconst_null
    //   356: astore_3
    //   357: goto -96 -> 261
    //   360: astore_3
    //   361: aload_0
    //   362: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   365: astore 4
    //   367: aload 4
    //   369: ifnull +10 -> 379
    //   372: aload_0
    //   373: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   376: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   379: aload_3
    //   380: athrow
    //   381: aload_0
    //   382: monitorexit
    //   383: goto -366 -> 17
    //   386: astore 4
    //   388: goto -9 -> 379
    //   391: astore_3
    //   392: aload 5
    //   394: astore_3
    //   395: goto -134 -> 261
    //   398: astore_3
    //   399: goto -227 -> 172
    //   402: astore_3
    //   403: goto -294 -> 109
    //   406: aconst_null
    //   407: astore_3
    //   408: goto -147 -> 261
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	411	0	this	ahc
    //   0	411	1	paramInt	int
    //   122	105	2	i	int
    //   9	37	3	localObject1	Object
    //   49	4	3	localObject2	Object
    //   54	4	3	localObject3	Object
    //   80	221	3	localObject4	Object
    //   304	1	3	localFileNotFoundException	java.io.FileNotFoundException
    //   309	14	3	localRandomAccessFile1	RandomAccessFile
    //   326	1	3	localException1	Exception
    //   328	1	3	localObject5	Object
    //   332	1	3	localException2	Exception
    //   337	14	3	localRandomAccessFile2	RandomAccessFile
    //   354	1	3	localException3	Exception
    //   356	1	3	localObject6	Object
    //   360	20	3	localObject7	Object
    //   391	1	3	localException4	Exception
    //   394	1	3	localObject8	Object
    //   398	1	3	localException5	Exception
    //   402	1	3	localException6	Exception
    //   407	1	3	localObject9	Object
    //   1	367	4	localRandomAccessFile3	RandomAccessFile
    //   386	1	4	localException7	Exception
    //   235	158	5	localagb	agb
    //   241	6	6	localRandomAccessFile4	RandomAccessFile
    // Exception table:
    //   from	to	target	type
    //   23	43	49	finally
    //   93	98	49	finally
    //   102	109	49	finally
    //   109	111	49	finally
    //   156	161	49	finally
    //   165	172	49	finally
    //   172	174	49	finally
    //   237	243	49	finally
    //   251	258	49	finally
    //   265	283	49	finally
    //   283	298	49	finally
    //   305	310	49	finally
    //   314	321	49	finally
    //   333	338	49	finally
    //   342	349	49	finally
    //   361	367	49	finally
    //   372	379	49	finally
    //   379	381	49	finally
    //   5	10	54	finally
    //   21	23	54	finally
    //   50	54	54	finally
    //   381	383	54	finally
    //   59	81	304	java/io/FileNotFoundException
    //   85	93	304	java/io/FileNotFoundException
    //   117	144	304	java/io/FileNotFoundException
    //   148	156	304	java/io/FileNotFoundException
    //   180	237	304	java/io/FileNotFoundException
    //   314	321	326	java/lang/Exception
    //   59	81	332	java/lang/Exception
    //   85	93	332	java/lang/Exception
    //   117	144	332	java/lang/Exception
    //   148	156	332	java/lang/Exception
    //   180	237	332	java/lang/Exception
    //   342	349	354	java/lang/Exception
    //   59	81	360	finally
    //   85	93	360	finally
    //   117	144	360	finally
    //   148	156	360	finally
    //   180	237	360	finally
    //   372	379	386	java/lang/Exception
    //   251	258	391	java/lang/Exception
    //   165	172	398	java/lang/Exception
    //   102	109	402	java/lang/Exception
  }
  
  /* Error */
  protected final void a(agb paramagb)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: monitorenter
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: aload_1
    //   12: getfield 150	agb:a	Ljava/io/File;
    //   15: putfield 17	ahc:c	Ljava/io/File;
    //   18: aload_0
    //   19: getfield 17	ahc:c	Ljava/io/File;
    //   22: ifnonnull +8 -> 30
    //   25: aload_0
    //   26: monitorexit
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload 5
    //   32: astore_3
    //   33: aload 6
    //   35: astore 4
    //   37: aload_0
    //   38: new 82	java/io/RandomAccessFile
    //   41: dup
    //   42: aload_0
    //   43: getfield 17	ahc:c	Ljava/io/File;
    //   46: ldc 115
    //   48: invokespecial 118	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   51: putfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   54: aload 5
    //   56: astore_3
    //   57: aload 6
    //   59: astore 4
    //   61: aload_0
    //   62: getfield 19	ahc:b	Lagc;
    //   65: invokevirtual 72	agc:a	()I
    //   68: newarray <illegal type>
    //   70: astore 7
    //   72: aload 5
    //   74: astore_3
    //   75: aload 6
    //   77: astore 4
    //   79: aload_0
    //   80: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   83: aload 7
    //   85: invokevirtual 96	java/io/RandomAccessFile:read	([B)I
    //   88: pop
    //   89: aload 5
    //   91: astore_3
    //   92: aload 6
    //   94: astore 4
    //   96: aload 7
    //   98: invokestatic 106	agc:b	([B)Ljava/util/BitSet;
    //   101: astore 5
    //   103: aload 5
    //   105: astore_3
    //   106: aload 5
    //   108: astore 4
    //   110: aload_1
    //   111: invokevirtual 152	agb:b	()Z
    //   114: ifeq +89 -> 203
    //   117: aload 5
    //   119: astore_3
    //   120: aload 5
    //   122: astore 4
    //   124: aload_1
    //   125: getfield 155	agb:b	[I
    //   128: iconst_0
    //   129: iaload
    //   130: istore_2
    //   131: aload 5
    //   133: astore_3
    //   134: aload 5
    //   136: astore 4
    //   138: iload_2
    //   139: aload_1
    //   140: getfield 155	agb:b	[I
    //   143: iconst_1
    //   144: iaload
    //   145: if_icmpgt +24 -> 169
    //   148: aload 5
    //   150: astore_3
    //   151: aload 5
    //   153: astore 4
    //   155: aload 5
    //   157: iload_2
    //   158: iconst_0
    //   159: invokevirtual 159	java/util/BitSet:set	(IZ)V
    //   162: iload_2
    //   163: iconst_1
    //   164: iadd
    //   165: istore_2
    //   166: goto -35 -> 131
    //   169: aload 5
    //   171: astore_3
    //   172: aload 5
    //   174: astore 4
    //   176: aload_0
    //   177: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   180: lconst_0
    //   181: invokevirtual 86	java/io/RandomAccessFile:seek	(J)V
    //   184: aload 5
    //   186: astore_3
    //   187: aload 5
    //   189: astore 4
    //   191: aload_0
    //   192: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   195: aload 5
    //   197: invokestatic 162	agc:a	(Ljava/util/BitSet;)[B
    //   200: invokevirtual 164	java/io/RandomAccessFile:write	([B)V
    //   203: aload_0
    //   204: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   207: astore_3
    //   208: aload 5
    //   210: astore_1
    //   211: aload_3
    //   212: ifnull +13 -> 225
    //   215: aload_0
    //   216: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   219: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   222: aload 5
    //   224: astore_1
    //   225: aload_1
    //   226: invokevirtual 167	java/util/BitSet:isEmpty	()Z
    //   229: ifeq +11 -> 240
    //   232: aload_0
    //   233: getfield 17	ahc:c	Ljava/io/File;
    //   236: invokevirtual 131	java/io/File:delete	()Z
    //   239: pop
    //   240: aload_0
    //   241: aconst_null
    //   242: putfield 17	ahc:c	Ljava/io/File;
    //   245: aload_0
    //   246: monitorexit
    //   247: goto -220 -> 27
    //   250: astore_1
    //   251: aload_0
    //   252: monitorexit
    //   253: aload_1
    //   254: athrow
    //   255: astore_1
    //   256: aload_0
    //   257: monitorexit
    //   258: aload_1
    //   259: athrow
    //   260: astore_1
    //   261: aload_0
    //   262: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   265: astore 4
    //   267: aload_3
    //   268: astore_1
    //   269: aload 4
    //   271: ifnull -46 -> 225
    //   274: aload_0
    //   275: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   278: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   281: aload_3
    //   282: astore_1
    //   283: goto -58 -> 225
    //   286: astore_1
    //   287: aload_3
    //   288: astore_1
    //   289: goto -64 -> 225
    //   292: astore_1
    //   293: aload_0
    //   294: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   297: astore_3
    //   298: aload 4
    //   300: astore_1
    //   301: aload_3
    //   302: ifnull -77 -> 225
    //   305: aload_0
    //   306: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   309: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   312: aload 4
    //   314: astore_1
    //   315: goto -90 -> 225
    //   318: astore_1
    //   319: aload 4
    //   321: astore_1
    //   322: goto -97 -> 225
    //   325: astore_1
    //   326: aload_0
    //   327: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   330: astore_3
    //   331: aload_3
    //   332: ifnull +10 -> 342
    //   335: aload_0
    //   336: getfield 80	ahc:a	Ljava/io/RandomAccessFile;
    //   339: invokevirtual 122	java/io/RandomAccessFile:close	()V
    //   342: aload_1
    //   343: athrow
    //   344: astore_3
    //   345: goto -3 -> 342
    //   348: astore_1
    //   349: aload 5
    //   351: astore_1
    //   352: goto -127 -> 225
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	355	0	this	ahc
    //   0	355	1	paramagb	agb
    //   130	36	2	i	int
    //   32	300	3	localObject1	Object
    //   344	1	3	localIOException	IOException
    //   35	285	4	localObject2	Object
    //   4	346	5	localBitSet	BitSet
    //   1	92	6	localObject3	Object
    //   70	27	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   10	27	250	finally
    //   203	208	250	finally
    //   215	222	250	finally
    //   225	240	250	finally
    //   240	247	250	finally
    //   261	267	250	finally
    //   274	281	250	finally
    //   293	298	250	finally
    //   305	312	250	finally
    //   326	331	250	finally
    //   335	342	250	finally
    //   342	344	250	finally
    //   8	10	255	finally
    //   251	255	255	finally
    //   37	54	260	java/io/FileNotFoundException
    //   61	72	260	java/io/FileNotFoundException
    //   79	89	260	java/io/FileNotFoundException
    //   96	103	260	java/io/FileNotFoundException
    //   110	117	260	java/io/FileNotFoundException
    //   124	131	260	java/io/FileNotFoundException
    //   138	148	260	java/io/FileNotFoundException
    //   155	162	260	java/io/FileNotFoundException
    //   176	184	260	java/io/FileNotFoundException
    //   191	203	260	java/io/FileNotFoundException
    //   274	281	286	java/io/IOException
    //   37	54	292	java/io/IOException
    //   61	72	292	java/io/IOException
    //   79	89	292	java/io/IOException
    //   96	103	292	java/io/IOException
    //   110	117	292	java/io/IOException
    //   124	131	292	java/io/IOException
    //   138	148	292	java/io/IOException
    //   155	162	292	java/io/IOException
    //   176	184	292	java/io/IOException
    //   191	203	292	java/io/IOException
    //   305	312	318	java/io/IOException
    //   37	54	325	finally
    //   61	72	325	finally
    //   79	89	325	finally
    //   96	103	325	finally
    //   110	117	325	finally
    //   124	131	325	finally
    //   138	148	325	finally
    //   155	162	325	finally
    //   176	184	325	finally
    //   191	203	325	finally
    //   335	342	344	java/io/IOException
    //   215	222	348	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     ahc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */