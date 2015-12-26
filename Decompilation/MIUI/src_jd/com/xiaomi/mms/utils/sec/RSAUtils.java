package com.xiaomi.mms.utils.sec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils
{
  public static String bcd2Str(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      int j = (char)((paramArrayOfByte[i] & 0xF0) >> 4 & 0xF);
      if (j > 9)
      {
        j = j + 65 - 10;
        label43:
        arrayOfChar[(i * 2)] = ((char)j);
        j = (char)(paramArrayOfByte[i] & 0xF);
        if (j <= 9) {
          break label96;
        }
        j = j + 65 - 10;
      }
      for (;;)
      {
        arrayOfChar[(i * 2 + 1)] = ((char)j);
        i += 1;
        break;
        j += 48;
        break label43;
        label96:
        j += 48;
      }
    }
    return new String(arrayOfChar);
  }
  
  public static String encryptByPublicKey(String paramString, RSAPublicKey paramRSAPublicKey)
    throws Exception
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(1, paramRSAPublicKey);
    paramRSAPublicKey = splitString(paramString, paramRSAPublicKey.getModulus().bitLength() / 8 - 11 - 15);
    paramString = "";
    int j = paramRSAPublicKey.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramRSAPublicKey[i];
      paramString = paramString + bcd2Str(localCipher.doFinal(((String)localObject).getBytes()));
      i += 1;
    }
    return paramString;
  }
  
  static Key loadKey(String paramString, boolean paramBoolean)
    throws Exception
  {
    try
    {
      Object localObject = Base64Utils.decryptToByte(paramString);
      paramString = KeyFactory.getInstance("RSA");
      localObject = new X509EncodedKeySpec((byte[])localObject);
      if (paramBoolean) {
        return paramString.generatePrivate((KeySpec)localObject);
      }
      paramString = paramString.generatePublic((KeySpec)localObject);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new Exception("NoSuchAlgorithmException:" + paramString);
    }
    catch (InvalidKeySpecException paramString)
    {
      throw new Exception("Key is invalid:" + paramString);
    }
    catch (IOException paramString)
    {
      throw new Exception("Failed to read key data:" + paramString);
    }
    catch (NullPointerException paramString)
    {
      throw new Exception("Key data is empty:" + paramString);
    }
  }
  
  public static PublicKey loadPublicKey(String paramString)
    throws Exception
  {
    return (PublicKey)loadKey(paramString, false);
  }
  
  public static String[] splitString(String paramString, int paramInt)
  {
    int k = paramString.length() / paramInt;
    int m = paramString.length() % paramInt;
    int i = 0;
    if (m != 0) {
      i = 1;
    }
    String[] arrayOfString = new String[k + i];
    int j = 0;
    if (j < k + i)
    {
      if ((j == k + i - 1) && (m != 0)) {}
      for (String str = paramString.substring(j * paramInt, j * paramInt + m);; str = paramString.substring(j * paramInt, j * paramInt + paramInt))
      {
        arrayOfString[j] = str;
        j += 1;
        break;
      }
    }
    return arrayOfString;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.sec.RSAUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */