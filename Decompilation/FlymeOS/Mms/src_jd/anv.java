import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class anv
{
  public static String a(String paramString)
  {
    paramString = b(paramString);
    if (paramString == null) {
      return null;
    }
    return anq.a(paramString);
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = anq.a(a(1, paramString2).doFinal(paramString1.getBytes()));
      return paramString1;
    }
    catch (Exception paramString1) {}
    return "";
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    return anq.a(b(paramArrayOfByte));
  }
  
  private static Cipher a(int paramInt, String paramString)
  {
    try
    {
      SecureRandom localSecureRandom = new SecureRandom();
      paramString = new DESKeySpec(b(paramString.getBytes()));
      paramString = SecretKeyFactory.getInstance("DES").generateSecret(paramString);
      Cipher localCipher = Cipher.getInstance("DES");
      localCipher.init(paramInt, paramString, localSecureRandom);
      return localCipher;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  /* Error */
  public static void a(java.io.InputStream paramInputStream, java.io.OutputStream paramOutputStream, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 73	javax/crypto/CipherInputStream
    //   6: dup
    //   7: aload_0
    //   8: iconst_2
    //   9: aload_2
    //   10: invokestatic 22	anv:a	(ILjava/lang/String;)Ljavax/crypto/Cipher;
    //   13: invokespecial 76	javax/crypto/CipherInputStream:<init>	(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
    //   16: astore_0
    //   17: sipush 1024
    //   20: newarray <illegal type>
    //   22: astore_2
    //   23: aload_0
    //   24: aload_2
    //   25: invokevirtual 80	javax/crypto/CipherInputStream:read	([B)I
    //   28: istore_3
    //   29: iload_3
    //   30: iconst_m1
    //   31: if_icmpne +12 -> 43
    //   34: aload_0
    //   35: ifnull +7 -> 42
    //   38: aload_0
    //   39: invokevirtual 83	javax/crypto/CipherInputStream:close	()V
    //   42: return
    //   43: aload_1
    //   44: aload_2
    //   45: iconst_0
    //   46: iload_3
    //   47: invokevirtual 89	java/io/OutputStream:write	([BII)V
    //   50: goto -27 -> 23
    //   53: astore_1
    //   54: aload_0
    //   55: ifnull -13 -> 42
    //   58: aload_0
    //   59: invokevirtual 83	javax/crypto/CipherInputStream:close	()V
    //   62: return
    //   63: astore_0
    //   64: return
    //   65: astore_0
    //   66: aload 4
    //   68: astore_1
    //   69: aload_1
    //   70: ifnull +7 -> 77
    //   73: aload_1
    //   74: invokevirtual 83	javax/crypto/CipherInputStream:close	()V
    //   77: aload_0
    //   78: athrow
    //   79: astore_1
    //   80: goto -3 -> 77
    //   83: astore_0
    //   84: return
    //   85: astore_2
    //   86: aload_0
    //   87: astore_1
    //   88: aload_2
    //   89: astore_0
    //   90: goto -21 -> 69
    //   93: astore_0
    //   94: aconst_null
    //   95: astore_0
    //   96: goto -42 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	paramInputStream	java.io.InputStream
    //   0	99	1	paramOutputStream	java.io.OutputStream
    //   0	99	2	paramString	String
    //   28	19	3	i	int
    //   1	66	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   17	23	53	java/lang/Exception
    //   23	29	53	java/lang/Exception
    //   43	50	53	java/lang/Exception
    //   58	62	63	java/io/IOException
    //   3	17	65	finally
    //   73	77	79	java/io/IOException
    //   38	42	83	java/io/IOException
    //   17	23	85	finally
    //   23	29	85	finally
    //   43	50	85	finally
    //   3	17	93	java/lang/Exception
  }
  
  /* Error */
  public static byte[] a(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc 92
    //   4: invokestatic 97	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   7: astore_2
    //   8: new 99	java/io/BufferedInputStream
    //   11: dup
    //   12: new 101	java/io/FileInputStream
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 104	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   20: invokespecial 107	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   23: astore_0
    //   24: sipush 1024
    //   27: newarray <illegal type>
    //   29: astore 4
    //   31: aload_0
    //   32: aload 4
    //   34: invokevirtual 108	java/io/BufferedInputStream:read	([B)I
    //   37: istore_1
    //   38: iload_1
    //   39: iconst_m1
    //   40: if_icmpne +26 -> 66
    //   43: aload_0
    //   44: invokevirtual 109	java/io/BufferedInputStream:close	()V
    //   47: aload_2
    //   48: invokevirtual 112	java/security/MessageDigest:digest	()[B
    //   51: astore_2
    //   52: aload_2
    //   53: astore_3
    //   54: aload_0
    //   55: ifnull +9 -> 64
    //   58: aload_0
    //   59: invokevirtual 109	java/io/BufferedInputStream:close	()V
    //   62: aload_2
    //   63: astore_3
    //   64: aload_3
    //   65: areturn
    //   66: aload_2
    //   67: aload 4
    //   69: iconst_0
    //   70: iload_1
    //   71: invokevirtual 115	java/security/MessageDigest:update	([BII)V
    //   74: goto -43 -> 31
    //   77: astore_2
    //   78: aload_0
    //   79: ifnull -15 -> 64
    //   82: aload_0
    //   83: invokevirtual 109	java/io/BufferedInputStream:close	()V
    //   86: aconst_null
    //   87: areturn
    //   88: astore_0
    //   89: aconst_null
    //   90: areturn
    //   91: astore_2
    //   92: aconst_null
    //   93: astore_0
    //   94: aload_0
    //   95: ifnull +7 -> 102
    //   98: aload_0
    //   99: invokevirtual 109	java/io/BufferedInputStream:close	()V
    //   102: aload_2
    //   103: athrow
    //   104: astore_0
    //   105: aload_2
    //   106: areturn
    //   107: astore_0
    //   108: goto -6 -> 102
    //   111: astore_2
    //   112: goto -18 -> 94
    //   115: astore_0
    //   116: aconst_null
    //   117: astore_0
    //   118: goto -40 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	paramFile	File
    //   37	34	1	i	int
    //   7	60	2	localObject1	Object
    //   77	1	2	localException	Exception
    //   91	15	2	arrayOfByte1	byte[]
    //   111	1	2	localObject2	Object
    //   1	64	3	localObject3	Object
    //   29	39	4	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   24	31	77	java/lang/Exception
    //   31	38	77	java/lang/Exception
    //   43	52	77	java/lang/Exception
    //   66	74	77	java/lang/Exception
    //   82	86	88	java/lang/Exception
    //   2	24	91	finally
    //   58	62	104	java/lang/Exception
    //   98	102	107	java/lang/Exception
    //   24	31	111	finally
    //   31	38	111	finally
    //   43	52	111	finally
    //   66	74	111	finally
    //   2	24	115	java/lang/Exception
  }
  
  public static String b(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new String(a(2, paramString2).doFinal(anq.a(paramString1)));
      return paramString1;
    }
    catch (Exception paramString1) {}
    return "";
  }
  
  public static byte[] b(String paramString)
  {
    return a(new File(paramString));
  }
  
  public static byte[] b(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      if (localMessageDigest != null)
      {
        localMessageDigest.update(paramArrayOfByte);
        arrayOfByte = localMessageDigest.digest();
      }
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        localNoSuchAlgorithmException.printStackTrace();
        Object localObject = null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     anv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */