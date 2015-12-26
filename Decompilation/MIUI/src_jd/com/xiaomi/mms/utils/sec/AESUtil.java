package com.xiaomi.mms.utils.sec;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil
{
  public static String encrypt(String paramString1, String paramString2)
    throws Exception
  {
    if (paramString2 == null) {
      throw new Exception("AES ENCRYPT : sKey is null");
    }
    if (paramString2.length() != 16) {
      throw new Exception("AES ENCRYPT : sKey's length is not 16");
    }
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, paramString2, new IvParameterSpec("0102030405060708".getBytes()));
    return Base64Utils.encrypt(localCipher.doFinal(paramString1.getBytes()));
  }
  
  public static String getAESKeyPlaintext()
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < 16)
    {
      localStringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(localRandom.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.sec.AESUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */