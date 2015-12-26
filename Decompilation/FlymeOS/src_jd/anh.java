import java.io.InputStream;
import java.security.MessageDigest;

public class anh
{
  private static final char[] a = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  
  public static String a(InputStream paramInputStream)
  {
    try
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        localMessageDigest.update(arrayOfByte, 0, i);
      }
      paramInputStream = a(localMessageDigest.digest());
    }
    catch (Exception paramInputStream)
    {
      paramInputStream.printStackTrace();
      return null;
    }
    return paramInputStream;
  }
  
  /* Error */
  public static String a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 63	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 67	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: aload_1
    //   12: astore_0
    //   13: aload_1
    //   14: invokestatic 69	anh:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   17: astore_2
    //   18: aload_2
    //   19: astore_0
    //   20: aload_0
    //   21: astore_2
    //   22: aload_1
    //   23: ifnull +9 -> 32
    //   26: aload_1
    //   27: invokevirtual 72	java/io/InputStream:close	()V
    //   30: aload_0
    //   31: astore_2
    //   32: aload_2
    //   33: areturn
    //   34: astore_2
    //   35: aconst_null
    //   36: astore_1
    //   37: aload_1
    //   38: astore_0
    //   39: aload_2
    //   40: invokevirtual 73	java/io/IOException:printStackTrace	()V
    //   43: aload_3
    //   44: astore_2
    //   45: aload_1
    //   46: ifnull -14 -> 32
    //   49: aload_1
    //   50: invokevirtual 72	java/io/InputStream:close	()V
    //   53: aconst_null
    //   54: areturn
    //   55: astore_0
    //   56: aconst_null
    //   57: areturn
    //   58: astore_1
    //   59: aconst_null
    //   60: astore_0
    //   61: aload_0
    //   62: ifnull +7 -> 69
    //   65: aload_0
    //   66: invokevirtual 72	java/io/InputStream:close	()V
    //   69: aload_1
    //   70: athrow
    //   71: astore_1
    //   72: aload_0
    //   73: areturn
    //   74: astore_0
    //   75: goto -6 -> 69
    //   78: astore_1
    //   79: goto -18 -> 61
    //   82: astore_2
    //   83: goto -46 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramString	String
    //   10	40	1	localFileInputStream	java.io.FileInputStream
    //   58	12	1	localObject1	Object
    //   71	1	1	localIOException1	java.io.IOException
    //   78	1	1	localObject2	Object
    //   17	16	2	str	String
    //   34	6	2	localIOException2	java.io.IOException
    //   44	1	2	localObject3	Object
    //   82	1	2	localIOException3	java.io.IOException
    //   1	43	3	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   2	11	34	java/io/IOException
    //   49	53	55	java/io/IOException
    //   2	11	58	finally
    //   26	30	71	java/io/IOException
    //   65	69	74	java/io/IOException
    //   13	18	78	finally
    //   39	43	78	finally
    //   13	18	82	java/io/IOException
  }
  
  /* Error */
  public static String a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: new 76	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 77	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: invokevirtual 81	java/io/File:length	()J
    //   14: lstore 4
    //   16: lload 4
    //   18: iload_1
    //   19: iconst_2
    //   20: imul
    //   21: i2l
    //   22: lcmp
    //   23: ifge +44 -> 67
    //   26: new 83	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   33: ldc 87
    //   35: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: lload 4
    //   40: invokevirtual 94	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   43: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: invokestatic 103	anf:d	(Ljava/lang/String;)V
    //   49: iconst_0
    //   50: ifeq +11 -> 61
    //   53: new 105	java/lang/NullPointerException
    //   56: dup
    //   57: invokespecial 106	java/lang/NullPointerException:<init>	()V
    //   60: athrow
    //   61: aconst_null
    //   62: astore 9
    //   64: aload 9
    //   66: areturn
    //   67: new 63	java/io/FileInputStream
    //   70: dup
    //   71: aload_0
    //   72: invokespecial 67	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   75: astore 9
    //   77: sipush 1024
    //   80: newarray <illegal type>
    //   82: astore 8
    //   84: ldc 32
    //   86: invokestatic 38	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   89: astore 10
    //   91: iconst_0
    //   92: istore_2
    //   93: iload_2
    //   94: iload_1
    //   95: if_icmpge +46 -> 141
    //   98: iload_1
    //   99: iload_2
    //   100: isub
    //   101: aload 8
    //   103: arraylength
    //   104: if_icmple +256 -> 360
    //   107: aload 8
    //   109: arraylength
    //   110: istore_3
    //   111: aload 9
    //   113: aload 8
    //   115: iconst_0
    //   116: iload_3
    //   117: invokevirtual 109	java/io/InputStream:read	([BII)I
    //   120: istore_3
    //   121: iload_3
    //   122: ifle +19 -> 141
    //   125: aload 10
    //   127: aload 8
    //   129: iconst_0
    //   130: iload_3
    //   131: invokevirtual 48	java/security/MessageDigest:update	([BII)V
    //   134: iload_3
    //   135: iload_2
    //   136: iadd
    //   137: istore_2
    //   138: goto -45 -> 93
    //   141: aload 9
    //   143: invokevirtual 72	java/io/InputStream:close	()V
    //   146: new 63	java/io/FileInputStream
    //   149: dup
    //   150: aload_0
    //   151: invokespecial 67	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   154: astore_0
    //   155: lload 4
    //   157: iload_1
    //   158: i2l
    //   159: lsub
    //   160: lstore 4
    //   162: lload 4
    //   164: lconst_0
    //   165: lcmp
    //   166: ifle +71 -> 237
    //   169: aload_0
    //   170: lload 4
    //   172: invokevirtual 113	java/io/InputStream:skip	(J)J
    //   175: lstore 6
    //   177: lload 6
    //   179: lconst_0
    //   180: lcmp
    //   181: ifle +13 -> 194
    //   184: lload 4
    //   186: lload 6
    //   188: lsub
    //   189: lstore 4
    //   191: goto -29 -> 162
    //   194: new 83	java/lang/StringBuilder
    //   197: dup
    //   198: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   201: ldc 115
    //   203: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: lload 6
    //   208: invokevirtual 94	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   211: invokevirtual 98	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokestatic 103	anf:d	(Ljava/lang/String;)V
    //   217: goto -55 -> 162
    //   220: astore 8
    //   222: aload 8
    //   224: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   227: aload_0
    //   228: ifnull +7 -> 235
    //   231: aload_0
    //   232: invokevirtual 72	java/io/InputStream:close	()V
    //   235: aconst_null
    //   236: areturn
    //   237: aload_0
    //   238: aload 8
    //   240: invokevirtual 44	java/io/InputStream:read	([B)I
    //   243: istore_1
    //   244: iload_1
    //   245: ifle +35 -> 280
    //   248: aload 10
    //   250: aload 8
    //   252: iconst_0
    //   253: iload_1
    //   254: invokevirtual 48	java/security/MessageDigest:update	([BII)V
    //   257: goto -20 -> 237
    //   260: astore 9
    //   262: aload_0
    //   263: astore 8
    //   265: aload 9
    //   267: astore_0
    //   268: aload 8
    //   270: ifnull +8 -> 278
    //   273: aload 8
    //   275: invokevirtual 72	java/io/InputStream:close	()V
    //   278: aload_0
    //   279: athrow
    //   280: aload 10
    //   282: invokevirtual 55	java/security/MessageDigest:digest	()[B
    //   285: invokestatic 58	anh:a	([B)Ljava/lang/String;
    //   288: astore 8
    //   290: aload 8
    //   292: astore 9
    //   294: aload_0
    //   295: ifnull -231 -> 64
    //   298: aload_0
    //   299: invokevirtual 72	java/io/InputStream:close	()V
    //   302: aload 8
    //   304: areturn
    //   305: astore_0
    //   306: aload 8
    //   308: areturn
    //   309: astore_0
    //   310: goto -249 -> 61
    //   313: astore_0
    //   314: goto -79 -> 235
    //   317: astore 8
    //   319: goto -41 -> 278
    //   322: astore_0
    //   323: goto -55 -> 268
    //   326: astore_0
    //   327: aload 9
    //   329: astore 8
    //   331: goto -63 -> 268
    //   334: astore 9
    //   336: aload_0
    //   337: astore 8
    //   339: aload 9
    //   341: astore_0
    //   342: goto -74 -> 268
    //   345: astore 8
    //   347: aconst_null
    //   348: astore_0
    //   349: goto -127 -> 222
    //   352: astore 8
    //   354: aload 9
    //   356: astore_0
    //   357: goto -135 -> 222
    //   360: iload_1
    //   361: iload_2
    //   362: isub
    //   363: istore_3
    //   364: goto -253 -> 111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	367	0	paramString	String
    //   0	367	1	paramInt	int
    //   92	271	2	i	int
    //   110	254	3	j	int
    //   14	176	4	l1	long
    //   175	32	6	l2	long
    //   1	127	8	arrayOfByte	byte[]
    //   220	31	8	localException1	Exception
    //   263	44	8	str1	String
    //   317	1	8	localIOException	java.io.IOException
    //   329	9	8	localObject1	Object
    //   345	1	8	localException2	Exception
    //   352	1	8	localException3	Exception
    //   62	80	9	localObject2	Object
    //   260	6	9	localObject3	Object
    //   292	36	9	str2	String
    //   334	21	9	localObject4	Object
    //   89	192	10	localMessageDigest	MessageDigest
    // Exception table:
    //   from	to	target	type
    //   169	177	220	java/lang/Exception
    //   194	217	220	java/lang/Exception
    //   237	244	220	java/lang/Exception
    //   248	257	220	java/lang/Exception
    //   280	290	220	java/lang/Exception
    //   169	177	260	finally
    //   194	217	260	finally
    //   237	244	260	finally
    //   248	257	260	finally
    //   280	290	260	finally
    //   298	302	305	java/io/IOException
    //   53	61	309	java/io/IOException
    //   231	235	313	java/io/IOException
    //   273	278	317	java/io/IOException
    //   3	16	322	finally
    //   26	49	322	finally
    //   67	77	322	finally
    //   77	91	326	finally
    //   98	111	326	finally
    //   111	121	326	finally
    //   125	134	326	finally
    //   141	155	326	finally
    //   222	227	334	finally
    //   3	16	345	java/lang/Exception
    //   26	49	345	java/lang/Exception
    //   67	77	345	java/lang/Exception
    //   77	91	352	java/lang/Exception
    //   98	111	352	java/lang/Exception
    //   111	121	352	java/lang/Exception
    //   125	134	352	java/lang/Exception
    //   141	155	352	java/lang/Exception
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    int j = 0;
    int k = paramArrayOfByte.length;
    char[] arrayOfChar = new char[k << 1];
    int i = 0;
    while (i < k)
    {
      int m = j + 1;
      arrayOfChar[j] = a[((paramArrayOfByte[i] & 0xF0) >>> 4)];
      j = m + 1;
      arrayOfChar[m] = a[(paramArrayOfByte[i] & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
}

/* Location:
 * Qualified Name:     anh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */