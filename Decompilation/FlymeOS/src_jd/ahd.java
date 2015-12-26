import java.io.File;
import java.io.RandomAccessFile;

public final class ahd
{
  private RandomAccessFile a;
  private agc b;
  private String c = "";
  private File d = null;
  
  protected ahd(agc paramagc)
  {
    b = paramagc;
  }
  
  /* Error */
  protected final void a(long paramLong, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: aload_0
    //   7: getfield 25	ahd:b	Lagc;
    //   10: lload_1
    //   11: invokevirtual 36	agc:a	(J)Ljava/io/File;
    //   14: putfield 23	ahd:d	Ljava/io/File;
    //   17: aload_0
    //   18: getfield 23	ahd:d	Ljava/io/File;
    //   21: astore 7
    //   23: aload 7
    //   25: ifnonnull +6 -> 31
    //   28: aload_0
    //   29: monitorexit
    //   30: return
    //   31: aload_0
    //   32: new 38	java/io/RandomAccessFile
    //   35: dup
    //   36: aload_0
    //   37: getfield 23	ahd:d	Ljava/io/File;
    //   40: ldc 40
    //   42: invokespecial 43	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   45: putfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   48: aload_0
    //   49: getfield 25	ahd:b	Lagc;
    //   52: invokevirtual 48	agc:a	()I
    //   55: newarray <illegal type>
    //   57: astore 7
    //   59: aload_0
    //   60: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   63: aload 7
    //   65: invokevirtual 52	java/io/RandomAccessFile:read	([B)I
    //   68: iconst_m1
    //   69: if_icmpne +79 -> 148
    //   72: iconst_0
    //   73: istore 4
    //   75: aload 7
    //   77: invokestatic 55	agc:b	([B)Ljava/util/BitSet;
    //   80: astore 7
    //   82: aload_0
    //   83: getfield 25	ahd:b	Lagc;
    //   86: invokevirtual 48	agc:a	()I
    //   89: istore 6
    //   91: iload 4
    //   93: iflt +17 -> 110
    //   96: iload 4
    //   98: aload_0
    //   99: getfield 25	ahd:b	Lagc;
    //   102: invokevirtual 48	agc:a	()I
    //   105: iconst_3
    //   106: ishl
    //   107: if_icmple +53 -> 160
    //   110: aload_0
    //   111: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   114: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   117: aload_0
    //   118: getfield 23	ahd:d	Ljava/io/File;
    //   121: invokevirtual 64	java/io/File:delete	()Z
    //   124: pop
    //   125: aload_0
    //   126: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   129: astore_3
    //   130: aload_3
    //   131: ifnull -103 -> 28
    //   134: aload_0
    //   135: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   138: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   141: goto -113 -> 28
    //   144: astore_3
    //   145: goto -117 -> 28
    //   148: aload_0
    //   149: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   152: invokevirtual 67	java/io/RandomAccessFile:readInt	()I
    //   155: istore 4
    //   157: goto -82 -> 75
    //   160: aload_0
    //   161: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   164: iload 6
    //   166: iconst_4
    //   167: iadd
    //   168: iload 4
    //   170: sipush 1500
    //   173: imul
    //   174: iadd
    //   175: i2l
    //   176: invokevirtual 71	java/io/RandomAccessFile:seek	(J)V
    //   179: aload_3
    //   180: invokestatic 74	agc:a	([B)[B
    //   183: astore_3
    //   184: aload_0
    //   185: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   188: aload_3
    //   189: arraylength
    //   190: invokevirtual 78	java/io/RandomAccessFile:writeInt	(I)V
    //   193: aload_0
    //   194: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   197: lload_1
    //   198: invokevirtual 81	java/io/RandomAccessFile:writeLong	(J)V
    //   201: aload_0
    //   202: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   205: aload_3
    //   206: invokevirtual 85	java/io/RandomAccessFile:write	([B)V
    //   209: aload 7
    //   211: iload 4
    //   213: iconst_1
    //   214: invokevirtual 91	java/util/BitSet:set	(IZ)V
    //   217: aload_0
    //   218: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   221: lconst_0
    //   222: invokevirtual 71	java/io/RandomAccessFile:seek	(J)V
    //   225: aload_0
    //   226: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   229: aload 7
    //   231: invokestatic 94	agc:a	(Ljava/util/BitSet;)[B
    //   234: invokevirtual 85	java/io/RandomAccessFile:write	([B)V
    //   237: iload 4
    //   239: iconst_1
    //   240: iadd
    //   241: istore 4
    //   243: iload 4
    //   245: aload_0
    //   246: getfield 25	ahd:b	Lagc;
    //   249: invokevirtual 48	agc:a	()I
    //   252: iconst_3
    //   253: ishl
    //   254: if_icmpne +159 -> 413
    //   257: iload 5
    //   259: istore 4
    //   261: aload_0
    //   262: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   265: iload 4
    //   267: invokevirtual 78	java/io/RandomAccessFile:writeInt	(I)V
    //   270: aload_0
    //   271: getfield 21	ahd:c	Ljava/lang/String;
    //   274: aload_0
    //   275: getfield 23	ahd:d	Ljava/io/File;
    //   278: invokevirtual 98	java/io/File:getName	()Ljava/lang/String;
    //   281: invokevirtual 104	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   284: ifne +14 -> 298
    //   287: aload_0
    //   288: aload_0
    //   289: getfield 23	ahd:d	Ljava/io/File;
    //   292: invokevirtual 98	java/io/File:getName	()Ljava/lang/String;
    //   295: putfield 21	ahd:c	Ljava/lang/String;
    //   298: aload_0
    //   299: getfield 23	ahd:d	Ljava/io/File;
    //   302: invokevirtual 108	java/io/File:length	()J
    //   305: pop2
    //   306: aload_0
    //   307: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   310: astore_3
    //   311: aload_3
    //   312: ifnull +10 -> 322
    //   315: aload_0
    //   316: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   319: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   322: aload_0
    //   323: aconst_null
    //   324: putfield 23	ahd:d	Ljava/io/File;
    //   327: goto -299 -> 28
    //   330: astore_3
    //   331: aload_0
    //   332: monitorexit
    //   333: aload_3
    //   334: athrow
    //   335: astore_3
    //   336: aload_0
    //   337: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   340: astore_3
    //   341: aload_3
    //   342: ifnull -20 -> 322
    //   345: aload_0
    //   346: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   349: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   352: goto -30 -> 322
    //   355: astore_3
    //   356: goto -34 -> 322
    //   359: astore_3
    //   360: aload_0
    //   361: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   364: astore_3
    //   365: aload_3
    //   366: ifnull -44 -> 322
    //   369: aload_0
    //   370: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   373: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   376: goto -54 -> 322
    //   379: astore_3
    //   380: goto -58 -> 322
    //   383: astore_3
    //   384: aload_0
    //   385: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   388: astore 7
    //   390: aload 7
    //   392: ifnull +10 -> 402
    //   395: aload_0
    //   396: getfield 45	ahd:a	Ljava/io/RandomAccessFile;
    //   399: invokevirtual 58	java/io/RandomAccessFile:close	()V
    //   402: aload_3
    //   403: athrow
    //   404: astore 7
    //   406: goto -4 -> 402
    //   409: astore_3
    //   410: goto -88 -> 322
    //   413: goto -152 -> 261
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	416	0	this	ahd
    //   0	416	1	paramLong	long
    //   0	416	3	paramArrayOfByte	byte[]
    //   73	193	4	i	int
    //   1	257	5	j	int
    //   89	79	6	k	int
    //   21	370	7	localObject	Object
    //   404	1	7	localIOException	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   134	141	144	java/io/IOException
    //   5	23	330	finally
    //   125	130	330	finally
    //   134	141	330	finally
    //   306	311	330	finally
    //   315	322	330	finally
    //   322	327	330	finally
    //   336	341	330	finally
    //   345	352	330	finally
    //   360	365	330	finally
    //   369	376	330	finally
    //   384	390	330	finally
    //   395	402	330	finally
    //   402	404	330	finally
    //   31	72	335	java/io/FileNotFoundException
    //   75	91	335	java/io/FileNotFoundException
    //   96	110	335	java/io/FileNotFoundException
    //   110	125	335	java/io/FileNotFoundException
    //   148	157	335	java/io/FileNotFoundException
    //   160	237	335	java/io/FileNotFoundException
    //   243	257	335	java/io/FileNotFoundException
    //   261	298	335	java/io/FileNotFoundException
    //   298	306	335	java/io/FileNotFoundException
    //   345	352	355	java/io/IOException
    //   31	72	359	java/io/IOException
    //   75	91	359	java/io/IOException
    //   96	110	359	java/io/IOException
    //   110	125	359	java/io/IOException
    //   148	157	359	java/io/IOException
    //   160	237	359	java/io/IOException
    //   243	257	359	java/io/IOException
    //   261	298	359	java/io/IOException
    //   298	306	359	java/io/IOException
    //   369	376	379	java/io/IOException
    //   31	72	383	finally
    //   75	91	383	finally
    //   96	110	383	finally
    //   110	125	383	finally
    //   148	157	383	finally
    //   160	237	383	finally
    //   243	257	383	finally
    //   261	298	383	finally
    //   298	306	383	finally
    //   395	402	404	java/io/IOException
    //   315	322	409	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     ahd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */