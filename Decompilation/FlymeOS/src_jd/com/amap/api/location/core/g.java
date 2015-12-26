package com.amap.api.location.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class g
{
  private static final String[] a = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
  
  public static String a(String paramString)
  {
    Object localObject2 = null;
    try
    {
      localMessageDigest = MessageDigest.getInstance("MD5");
      if (localMessageDigest == null) {}
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      try
      {
        MessageDigest localMessageDigest;
        localMessageDigest.update(paramString.getBytes("utf-8"));
        paramString = (String)localObject2;
        if (localMessageDigest != null) {
          paramString = localMessageDigest.digest();
        }
        return a(paramString);
        localNoSuchAlgorithmException = localNoSuchAlgorithmException;
        localNoSuchAlgorithmException.printStackTrace();
        Object localObject1 = null;
      }
      catch (UnsupportedEncodingException paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
    }
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      String str1 = str2;
      if (str2.length() == 1) {
        str1 = '0' + str2;
      }
      localStringBuilder.append(str1);
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */