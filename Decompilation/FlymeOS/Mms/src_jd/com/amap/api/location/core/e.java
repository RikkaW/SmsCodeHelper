package com.amap.api.location.core;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class e
{
  private static final char[] a = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final byte[] b = { 0, 1, 1, 2, 3, 5, 8, 13, 8, 7, 6, 5, 4, 3, 2, 1 };
  private static final IvParameterSpec c = new IvParameterSpec(b);
  
  static String a(String paramString)
  {
    if (paramString != null) {
      try
      {
        if (paramString.length() == 0) {
          return null;
        }
        String str = a("SHA1", paramString);
        paramString = a("MD5", str + paramString);
        return paramString;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return null;
    }
    try
    {
      paramString1 = MessageDigest.getInstance(paramString1);
      paramString1.update(paramString2.getBytes("utf-8"));
      paramString1 = b(paramString1.digest());
      return paramString1;
    }
    catch (Exception paramString1)
    {
      throw new RuntimeException(paramString1);
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1) {
        localStringBuffer.append("0");
      }
      localStringBuffer.append(str);
      i += 1;
    }
    return localStringBuffer.toString().toUpperCase();
  }
  
  static PublicKey a(Context paramContext)
  {
    try
    {
      paramContext = new ByteArrayInputStream(new byte[] { 48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103 });
      Object localObject = CertificateFactory.getInstance("X.509");
      KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
      localObject = ((CertificateFactory)localObject).generateCertificate(paramContext);
      paramContext.close();
      paramContext = localKeyFactory.generatePublic(new X509EncodedKeySpec(((Certificate)localObject).getPublicKey().getEncoded()));
      return paramContext;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      throw new Exception("无此算法");
    }
    catch (InvalidKeySpecException paramContext)
    {
      throw new Exception("公钥非法");
    }
    catch (NullPointerException paramContext)
    {
      throw new Exception("公钥数据为空");
    }
    catch (CertificateException paramContext)
    {
      return null;
    }
    catch (IOException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramString = c(paramString);
      Cipher localCipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
      localCipher.init(2, paramString);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static byte[] a(byte[] paramArrayOfByte, Key paramKey)
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(1, paramKey);
    return localCipher.doFinal(paramArrayOfByte);
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      localCipher.init(1, paramArrayOfByte1);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
      return null;
    }
    catch (NoSuchPaddingException paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
      return null;
    }
    catch (Throwable paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
    }
    return null;
  }
  
  /* Error */
  public static String b(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +10 -> 11
    //   4: aload_0
    //   5: invokevirtual 62	java/lang/String:length	()I
    //   8: ifgt +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload_0
    //   14: invokestatic 410	com/amap/api/location/core/e:b	(Ljava/lang/String;)[B
    //   17: astore_0
    //   18: aload_0
    //   19: aload_1
    //   20: invokestatic 412	com/amap/api/location/core/e:a	([BLjava/lang/String;)[B
    //   23: astore_0
    //   24: aload_0
    //   25: ifnull -14 -> 11
    //   28: new 58	java/lang/String
    //   31: dup
    //   32: aload_0
    //   33: ldc_w 414
    //   36: invokespecial 415	java/lang/String:<init>	([BLjava/lang/String;)V
    //   39: astore_0
    //   40: aload_0
    //   41: areturn
    //   42: astore_0
    //   43: aload_0
    //   44: invokevirtual 389	java/lang/Exception:printStackTrace	()V
    //   47: aconst_null
    //   48: astore_0
    //   49: goto -31 -> 18
    //   52: astore_0
    //   53: aload_0
    //   54: invokevirtual 416	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   57: aconst_null
    //   58: astore_0
    //   59: goto -19 -> 40
    //   62: astore_0
    //   63: aload_0
    //   64: invokevirtual 83	java/lang/Throwable:printStackTrace	()V
    //   67: aconst_null
    //   68: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	paramString1	String
    //   0	69	1	paramString2	String
    // Exception table:
    //   from	to	target	type
    //   13	18	42	java/lang/Exception
    //   28	40	52	java/io/UnsupportedEncodingException
    //   13	18	62	java/lang/Throwable
    //   18	24	62	java/lang/Throwable
    //   28	40	62	java/lang/Throwable
    //   43	47	62	java/lang/Throwable
    //   53	57	62	java/lang/Throwable
  }
  
  private static String b(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    StringBuilder localStringBuilder = new StringBuilder(j * 2);
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(a[(paramArrayOfByte[i] >> 4 & 0xF)]);
      localStringBuilder.append(a[(paramArrayOfByte[i] & 0xF)]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static byte[] b(String paramString)
  {
    int i = 0;
    if ((paramString == null) || (paramString.length() < 2))
    {
      paramString = new byte[0];
      return paramString;
    }
    String str = paramString.toLowerCase();
    int j = str.length() / 2;
    byte[] arrayOfByte = new byte[j];
    for (;;)
    {
      paramString = arrayOfByte;
      if (i >= j) {
        break;
      }
      arrayOfByte[i] = ((byte)(Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16) & 0xFF));
      i += 1;
    }
  }
  
  public static byte[] b(byte[] paramArrayOfByte, String paramString)
  {
    int i = 0;
    try
    {
      paramString = new PKCS8EncodedKeySpec(b.a(paramString));
      paramString = KeyFactory.getInstance("RSA").generatePrivate(paramString);
      Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      localCipher.init(1, paramString);
      int k = paramArrayOfByte.length;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      int j = 0;
      if (k - i > 0)
      {
        if (k - i > 245) {}
        for (paramString = localCipher.doFinal(paramArrayOfByte, i, 245);; paramString = localCipher.doFinal(paramArrayOfByte, i, k - i))
        {
          localByteArrayOutputStream.write(paramString, 0, paramString.length);
          j += 1;
          i = j * 245;
          break;
        }
      }
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return paramArrayOfByte;
    }
    finally {}
  }
  
  public static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, new SecretKeySpec(paramArrayOfByte1, "AES"), c);
    return localCipher.doFinal(paramArrayOfByte2);
  }
  
  private static SecretKeySpec c(String paramString)
  {
    Object localObject = null;
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    paramString = new StringBuffer(16);
    paramString.append(str);
    while (paramString.length() < 16) {
      paramString.append("0");
    }
    if (paramString.length() > 16) {
      paramString.setLength(16);
    }
    try
    {
      paramString = paramString.toString().getBytes("UTF-8");
      return new SecretKeySpec(paramString, "AES");
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = (String)localObject;
      }
    }
  }
  
  public static byte[] c(byte[] paramArrayOfByte, String paramString)
  {
    int i = 0;
    try
    {
      paramString = new PKCS8EncodedKeySpec(b.a(paramString));
      paramString = KeyFactory.getInstance("RSA").generatePrivate(paramString);
      Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
      localCipher.init(2, paramString);
      int k = paramArrayOfByte.length;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      int j = 0;
      if (k - i > 0)
      {
        if (k - i > 256) {}
        for (paramString = localCipher.doFinal(paramArrayOfByte, i, 256);; paramString = localCipher.doFinal(paramArrayOfByte, i, k - i))
        {
          localByteArrayOutputStream.write(paramString, 0, paramString.length);
          j += 1;
          i = j * 256;
          break;
        }
      }
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return paramArrayOfByte;
    }
    finally {}
  }
  
  public static String d(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramString = c(paramString);
      Cipher localCipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
      localCipher.init(1, paramString);
      paramArrayOfByte = localCipher.doFinal(paramArrayOfByte);
      if (paramArrayOfByte == null) {
        return null;
      }
      paramArrayOfByte = a(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */