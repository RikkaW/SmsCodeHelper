package com.amap.api.mapcore2d;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class de
{
  private static final char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] b = new byte[''];
  
  static
  {
    int i = 0;
    while (i < 128)
    {
      b[i] = -1;
      i += 1;
    }
    i = 65;
    while (i <= 90)
    {
      b[i] = ((byte)(i - 65));
      i += 1;
    }
    i = 97;
    while (i <= 122)
    {
      b[i] = ((byte)(i - 97 + 26));
      i += 1;
    }
    i = 48;
    while (i <= 57)
    {
      b[i] = ((byte)(i - 48 + 52));
      i += 1;
    }
    b[43] = 62;
    b[47] = 63;
  }
  
  public static String a(String paramString)
  {
    return new String(b(paramString));
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = c(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      ed.a(paramArrayOfByte, "Encrypt", "encodeBase64");
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  static byte[] a(byte[] paramArrayOfByte, Key paramKey)
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(1, paramKey);
    return localCipher.doFinal(paramArrayOfByte);
  }
  
  static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      paramArrayOfByte1 = b(paramArrayOfByte1, paramArrayOfByte2);
      return paramArrayOfByte1;
    }
    catch (InvalidKeyException paramArrayOfByte1)
    {
      ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
      paramArrayOfByte1.printStackTrace();
      return null;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte1)
    {
      for (;;)
      {
        ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
        paramArrayOfByte1.printStackTrace();
      }
    }
    catch (NoSuchPaddingException paramArrayOfByte1)
    {
      for (;;)
      {
        ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
        paramArrayOfByte1.printStackTrace();
      }
    }
    catch (IllegalBlockSizeException paramArrayOfByte1)
    {
      for (;;)
      {
        ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
        paramArrayOfByte1.printStackTrace();
      }
    }
    catch (BadPaddingException paramArrayOfByte1)
    {
      for (;;)
      {
        ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
        paramArrayOfByte1.printStackTrace();
      }
    }
    catch (Throwable paramArrayOfByte1)
    {
      for (;;)
      {
        ed.a(paramArrayOfByte1, "Encrypt", "aesEncrypt");
        paramArrayOfByte1.printStackTrace();
      }
    }
  }
  
  public static String b(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = c(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static byte[] b(String paramString)
  {
    int i = 0;
    if (paramString == null) {
      return new byte[0];
    }
    paramString = paramString.getBytes();
    int k = paramString.length;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(k);
    if (i < k) {}
    for (int j = i;; j = i)
    {
      byte[] arrayOfByte = b;
      i = j + 1;
      int n = arrayOfByte[paramString[j]];
      if ((i >= k) || (n != -1))
      {
        j = i;
        if (n == -1) {}
        label71:
        int m;
        do
        {
          return localByteArrayOutputStream.toByteArray();
          do
          {
            j = i;
            arrayOfByte = b;
            i = j + 1;
            m = arrayOfByte[paramString[j]];
          } while ((i < k) && (m == -1));
        } while (m == -1);
        localByteArrayOutputStream.write(n << 2 | (m & 0x30) >>> 4);
        for (j = i;; j = i)
        {
          i = j + 1;
          j = paramString[j];
          if (j == 61) {
            return localByteArrayOutputStream.toByteArray();
          }
          n = b[j];
          if ((i >= k) || (n != -1))
          {
            if (n == -1) {
              break label71;
            }
            localByteArrayOutputStream.write((m & 0xF) << 4 | (n & 0x3C) >>> 2);
            for (j = i;; j = i)
            {
              i = j + 1;
              j = paramString[j];
              if (j == 61) {
                return localByteArrayOutputStream.toByteArray();
              }
              j = b[j];
              if ((i >= k) || (j != -1))
              {
                if (j == -1) {
                  break label71;
                }
                localByteArrayOutputStream.write(j | (n & 0x3) << 6);
                break;
              }
            }
          }
        }
      }
    }
  }
  
  private static byte[] b(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    paramArrayOfByte1 = new SecretKeySpec(paramArrayOfByte1, "AES");
    Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    localCipher.init(1, paramArrayOfByte1);
    return localCipher.doFinal(paramArrayOfByte2);
  }
  
  private static String c(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramArrayOfByte.length;
    int i = 0;
    for (;;)
    {
      int n;
      int k;
      if (i < j)
      {
        n = i + 1;
        k = paramArrayOfByte[i] & 0xFF;
        if (n != j) {
          break label80;
        }
        localStringBuffer.append(a[(k >>> 2)]);
        localStringBuffer.append(a[((k & 0x3) << 4)]);
        localStringBuffer.append("==");
      }
      for (;;)
      {
        return localStringBuffer.toString();
        label80:
        m = n + 1;
        n = paramArrayOfByte[n] & 0xFF;
        if (m != j) {
          break;
        }
        localStringBuffer.append(a[(k >>> 2)]);
        localStringBuffer.append(a[((k & 0x3) << 4 | (n & 0xF0) >>> 4)]);
        localStringBuffer.append(a[((n & 0xF) << 2)]);
        localStringBuffer.append("=");
      }
      i = m + 1;
      int m = paramArrayOfByte[m] & 0xFF;
      localStringBuffer.append(a[(k >>> 2)]);
      localStringBuffer.append(a[((k & 0x3) << 4 | (n & 0xF0) >>> 4)]);
      localStringBuffer.append(a[((n & 0xF) << 2 | (m & 0xC0) >>> 6)]);
      localStringBuffer.append(a[(m & 0x3F)]);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.de
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */