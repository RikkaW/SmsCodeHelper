package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.util.ParseManager;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public final class f
{
  private static byte[] a = { 18, 52, 86, 120, -112, -85, -51, -17 };
  
  public static byte[] a(String paramString1, String paramString2)
  {
    try
    {
      SecureRandom localSecureRandom = new SecureRandom();
      paramString2 = new DESKeySpec(paramString2.getBytes());
      SecretKey localSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(paramString2);
      if (ParseManager.ismUseNewDes())
      {
        paramString2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        paramString2.init(1, localSecretKey, new IvParameterSpec(a));
      }
      for (;;)
      {
        return paramString2.doFinal(paramString1.getBytes());
        paramString2 = Cipher.getInstance("DES");
        paramString2.init(1, localSecretKey, localSecureRandom);
      }
      return null;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if ((paramArrayOfByte2 == null) || (paramArrayOfByte1 == null)) {
      return null;
    }
    try
    {
      paramArrayOfByte2 = new DESKeySpec(paramArrayOfByte2);
      SecretKey localSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(paramArrayOfByte2);
      if (ParseManager.ismUseNewDes())
      {
        paramArrayOfByte2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
        paramArrayOfByte2.init(2, localSecretKey, new IvParameterSpec(a));
      }
      for (;;)
      {
        return paramArrayOfByte2.doFinal(paramArrayOfByte1);
        SecureRandom localSecureRandom = new SecureRandom();
        paramArrayOfByte2 = Cipher.getInstance("DES");
        paramArrayOfByte2.init(2, localSecretKey, localSecureRandom);
      }
      return null;
    }
    catch (Throwable paramArrayOfByte1)
    {
      paramArrayOfByte1.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */