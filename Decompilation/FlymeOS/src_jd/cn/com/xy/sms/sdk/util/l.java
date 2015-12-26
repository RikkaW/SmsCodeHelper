package cn.com.xy.sms.sdk.util;

public final class l
{
  /* Error */
  public static String a(java.io.File paramFile)
  {
    // Byte code:
    //   0: ldc 17
    //   2: invokestatic 23	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   5: astore_3
    //   6: new 25	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 28	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: astore_2
    //   15: aload_2
    //   16: astore_1
    //   17: aload_3
    //   18: aload_2
    //   19: invokevirtual 32	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   22: getstatic 38	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
    //   25: lconst_0
    //   26: aload_0
    //   27: invokevirtual 44	java/io/File:length	()J
    //   30: invokevirtual 50	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   33: invokevirtual 54	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
    //   36: aload_2
    //   37: astore_1
    //   38: aload_3
    //   39: invokevirtual 58	java/security/MessageDigest:digest	()[B
    //   42: invokestatic 61	cn/com/xy/sms/sdk/util/l:a	([B)Ljava/lang/String;
    //   45: astore_0
    //   46: aload_2
    //   47: invokestatic 66	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   50: aload_0
    //   51: areturn
    //   52: astore_1
    //   53: aconst_null
    //   54: astore_0
    //   55: aload_1
    //   56: invokevirtual 69	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   59: aload_0
    //   60: invokestatic 66	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   63: aconst_null
    //   64: areturn
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_2
    //   68: aload_2
    //   69: astore_1
    //   70: aload_0
    //   71: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   74: aload_2
    //   75: invokestatic 66	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   78: goto -15 -> 63
    //   81: astore_0
    //   82: aconst_null
    //   83: astore_1
    //   84: aload_1
    //   85: invokestatic 66	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   88: aload_0
    //   89: athrow
    //   90: astore_0
    //   91: goto -7 -> 84
    //   94: astore_2
    //   95: aload_0
    //   96: astore_1
    //   97: aload_2
    //   98: astore_0
    //   99: goto -15 -> 84
    //   102: astore_0
    //   103: goto -35 -> 68
    //   106: astore_1
    //   107: aload_2
    //   108: astore_0
    //   109: goto -54 -> 55
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramFile	java.io.File
    //   16	22	1	localFileInputStream1	java.io.FileInputStream
    //   52	4	1	localNoSuchAlgorithmException1	java.security.NoSuchAlgorithmException
    //   69	28	1	localObject1	Object
    //   106	1	1	localNoSuchAlgorithmException2	java.security.NoSuchAlgorithmException
    //   14	61	2	localFileInputStream2	java.io.FileInputStream
    //   94	14	2	localObject2	Object
    //   5	34	3	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   0	15	52	java/security/NoSuchAlgorithmException
    //   0	15	65	java/lang/Throwable
    //   0	15	81	finally
    //   17	36	90	finally
    //   38	46	90	finally
    //   70	74	90	finally
    //   55	59	94	finally
    //   17	36	102	java/lang/Throwable
    //   38	46	102	java/lang/Throwable
    //   17	36	106	java/security/NoSuchAlgorithmException
    //   38	46	106	java/security/NoSuchAlgorithmException
  }
  
  private static String a(byte[] paramArrayOfByte)
  {
    int j = 0;
    if (paramArrayOfByte == null) {
      return null;
    }
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    for (;;)
    {
      if (i >= paramArrayOfByte.length) {
        return String.valueOf(arrayOfChar);
      }
      arrayOfChar[j] = "0123456789abcdef".charAt(paramArrayOfByte[i] >> 4 & 0xF);
      int k = paramArrayOfByte[i];
      j += 1;
      arrayOfChar[j] = "0123456789abcdef".charAt(k & 0xF);
      i += 1;
      j += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */