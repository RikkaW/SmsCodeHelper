package com.android.mms.util;

public class Coder
{
  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
  
  private static String byteArrayToString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append(byteToHexString(paramArrayOfByte[i]));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  private static String byteToHexString(byte paramByte)
  {
    byte b = paramByte;
    paramByte = b;
    if (b < 0) {
      paramByte = b + 256;
    }
    b = paramByte / 16;
    return hexDigits[b] + hexDigits[(paramByte % 16)];
  }
  
  /* Error */
  public static final String encodeMD5(java.io.File paramFile)
  {
    // Byte code:
    //   0: sipush 1024
    //   3: newarray <illegal type>
    //   5: astore_2
    //   6: new 82	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 85	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: astore_0
    //   15: ldc 87
    //   17: invokestatic 93	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   20: astore_3
    //   21: aload_0
    //   22: aload_2
    //   23: invokevirtual 99	java/io/InputStream:read	([B)I
    //   26: istore_1
    //   27: iload_1
    //   28: ifle +56 -> 84
    //   31: aload_3
    //   32: aload_2
    //   33: iconst_0
    //   34: iload_1
    //   35: invokevirtual 103	java/security/MessageDigest:update	([BII)V
    //   38: goto -17 -> 21
    //   41: astore_2
    //   42: aload_2
    //   43: invokevirtual 106	java/security/NoSuchAlgorithmException:printStackTrace	()V
    //   46: aload_0
    //   47: invokevirtual 109	java/io/InputStream:close	()V
    //   50: aconst_null
    //   51: areturn
    //   52: astore_0
    //   53: aload_0
    //   54: invokevirtual 110	java/io/FileNotFoundException:printStackTrace	()V
    //   57: aconst_null
    //   58: areturn
    //   59: astore_2
    //   60: aload_2
    //   61: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   64: aload_0
    //   65: invokevirtual 109	java/io/InputStream:close	()V
    //   68: aconst_null
    //   69: areturn
    //   70: astore_0
    //   71: aload_0
    //   72: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   75: aconst_null
    //   76: areturn
    //   77: astore_2
    //   78: aload_0
    //   79: invokevirtual 109	java/io/InputStream:close	()V
    //   82: aload_2
    //   83: athrow
    //   84: aload_0
    //   85: invokevirtual 109	java/io/InputStream:close	()V
    //   88: aload_3
    //   89: invokevirtual 115	java/security/MessageDigest:digest	()[B
    //   92: invokestatic 117	com/android/mms/util/Coder:byteArrayToString	([B)Ljava/lang/String;
    //   95: areturn
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   101: goto -19 -> 82
    //   104: astore_0
    //   105: goto -34 -> 71
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 111	java/io/IOException:printStackTrace	()V
    //   113: goto -25 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	paramFile	java.io.File
    //   26	9	1	i	int
    //   5	28	2	arrayOfByte	byte[]
    //   41	2	2	localNoSuchAlgorithmException	java.security.NoSuchAlgorithmException
    //   59	2	2	localIOException	java.io.IOException
    //   77	6	2	localObject	Object
    //   20	69	3	localMessageDigest	java.security.MessageDigest
    // Exception table:
    //   from	to	target	type
    //   15	21	41	java/security/NoSuchAlgorithmException
    //   21	27	41	java/security/NoSuchAlgorithmException
    //   31	38	41	java/security/NoSuchAlgorithmException
    //   6	15	52	java/io/FileNotFoundException
    //   15	21	59	java/io/IOException
    //   21	27	59	java/io/IOException
    //   31	38	59	java/io/IOException
    //   64	68	70	java/io/IOException
    //   15	21	77	finally
    //   21	27	77	finally
    //   31	38	77	finally
    //   42	46	77	finally
    //   60	64	77	finally
    //   78	82	96	java/io/IOException
    //   46	50	104	java/io/IOException
    //   84	88	108	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.Coder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */