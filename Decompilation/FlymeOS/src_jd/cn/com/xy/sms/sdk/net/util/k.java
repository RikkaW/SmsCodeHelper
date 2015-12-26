package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.log.LogManager;
import java.io.PrintStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class k
{
  public static String a(String paramString)
  {
    try
    {
      paramString = MessageDigest.getInstance("SHA-256").digest(paramString.getBytes("UTF-8"));
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      for (;;)
      {
        if (i >= paramString.length) {
          return localStringBuffer.toString();
        }
        int j = paramString[i] & 0xFF;
        if (j < 16) {
          localStringBuffer.append("0");
        }
        localStringBuffer.append(Integer.toHexString(j));
        i += 1;
      }
      return "";
    }
    catch (Throwable paramString)
    {
      System.out.println(paramString.toString());
      paramString.printStackTrace();
    }
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return new String(a(paramString1.getBytes(), paramString2)).replaceAll("\r\n", "").replaceAll("\n", "");
  }
  
  private static boolean a(String paramString1, String paramString2, String paramString3)
  {
    return a(paramString1.getBytes(), paramString2, paramString3.getBytes());
  }
  
  private static boolean a(byte[] paramArrayOfByte1, String paramString, byte[] paramArrayOfByte2)
  {
    boolean bool1 = false;
    try
    {
      paramArrayOfByte1 = new X509EncodedKeySpec(a.b(paramArrayOfByte1));
      paramArrayOfByte1 = KeyFactory.getInstance("RSA").generatePublic(paramArrayOfByte1);
      paramArrayOfByte2 = a.b(paramArrayOfByte2);
      Signature localSignature = Signature.getInstance("SHA256WithRSA");
      localSignature.initVerify(paramArrayOfByte1);
      localSignature.update(paramString.getBytes());
      boolean bool2 = localSignature.verify(paramArrayOfByte2);
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (Throwable paramArrayOfByte1)
    {
      LogManager.e("Signaturer", "sign failed", paramArrayOfByte1);
      paramArrayOfByte1.printStackTrace();
    }
    return false;
  }
  
  private static byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      paramArrayOfByte = new PKCS8EncodedKeySpec(a.b(paramArrayOfByte));
      paramArrayOfByte = KeyFactory.getInstance("RSA").generatePrivate(paramArrayOfByte);
      Signature localSignature = Signature.getInstance("SHA256WithRSA");
      localSignature.initSign(paramArrayOfByte);
      localSignature.update(paramString.getBytes());
      paramArrayOfByte = a.a(localSignature.sign());
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      LogManager.e("Signaturer", "sign failed", paramArrayOfByte);
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  private static byte[] a(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      paramArrayOfByte = new PKCS8EncodedKeySpec(a.b(paramArrayOfByte));
      paramArrayOfByte = KeyFactory.getInstance("RSA").generatePrivate(paramArrayOfByte);
      paramString2 = Signature.getInstance(paramString2);
      paramString2.initSign(paramArrayOfByte);
      paramString2.update(paramString1.getBytes());
      paramArrayOfByte = a.a(paramString2.sign());
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      LogManager.e("Signaturer", "sign failed", paramArrayOfByte);
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */