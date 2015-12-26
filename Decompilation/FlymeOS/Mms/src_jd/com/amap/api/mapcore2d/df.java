package com.amap.api.mapcore2d;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class df
{
  public static String a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return di.c(c(paramString));
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    return di.c(b(paramArrayOfByte));
  }
  
  public static String b(String paramString)
  {
    return di.d(d(paramString));
  }
  
  private static byte[] b(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = localMessageDigest.digest();
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
      ed.a(paramArrayOfByte, "MD5", "getMd5Bytes");
      paramArrayOfByte.printStackTrace();
      return null;
    }
    catch (Throwable paramArrayOfByte)
    {
      ed.a(paramArrayOfByte, "MD5", "getMd5Bytes1");
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }
  
  public static byte[] c(String paramString)
  {
    try
    {
      paramString = e(paramString);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      ed.a(paramString, "MD5", "getMd5Bytes");
      paramString.printStackTrace();
      return new byte[0];
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        ed.a(paramString, "MD5", "getMd5Bytes");
        paramString.printStackTrace();
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        ed.a(paramString, "MD5", "getMd5Bytes");
        paramString.printStackTrace();
      }
    }
  }
  
  private static byte[] d(String paramString)
  {
    try
    {
      paramString = e(paramString);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      paramString.printStackTrace();
      return new byte[0];
    }
    catch (UnsupportedEncodingException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private static byte[] e(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    localMessageDigest.update(paramString.getBytes("utf-8"));
    return localMessageDigest.digest();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.df
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */