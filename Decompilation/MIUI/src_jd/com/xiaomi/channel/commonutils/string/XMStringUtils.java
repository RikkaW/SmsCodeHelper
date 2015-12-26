package com.xiaomi.channel.commonutils.string;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class XMStringUtils
{
  public static String generateRandomString(int paramInt)
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramInt)
    {
      localStringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(localRandom.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static byte[] getBytes(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString.getBytes();
  }
  
  public static String getMd5Digest(String paramString)
  {
    if (paramString != null) {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(getBytes(paramString));
        localObject = String.format("%1$032X", new Object[] { new BigInteger(1, ((MessageDigest)localObject).digest()) });
        return (String)localObject;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        return paramString;
      }
    }
    return "";
  }
  
  public static String getSHA1Digest(String paramString)
  {
    if (paramString != null) {
      try
      {
        Object localObject = MessageDigest.getInstance("SHA1");
        ((MessageDigest)localObject).update(getBytes(paramString));
        localObject = String.format("%1$032X", new Object[] { new BigInteger(1, ((MessageDigest)localObject).digest()) });
        return (String)localObject;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        return paramString;
      }
    }
    return null;
  }
  
  public static String join(Collection<?> paramCollection, String paramString)
  {
    if (paramCollection == null) {
      return null;
    }
    return join(paramCollection.iterator(), paramString);
  }
  
  public static String join(Iterator<?> paramIterator, String paramString)
  {
    if (paramIterator == null) {
      return null;
    }
    if (!paramIterator.hasNext()) {
      return "";
    }
    Object localObject = paramIterator.next();
    if (!paramIterator.hasNext()) {
      return localObject.toString();
    }
    StringBuffer localStringBuffer = new StringBuffer(256);
    if (localObject != null) {
      localStringBuffer.append(localObject);
    }
    while (paramIterator.hasNext())
    {
      if (paramString != null) {
        localStringBuffer.append(paramString);
      }
      localObject = paramIterator.next();
      if (localObject != null) {
        localStringBuffer.append(localObject);
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.string.XMStringUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */