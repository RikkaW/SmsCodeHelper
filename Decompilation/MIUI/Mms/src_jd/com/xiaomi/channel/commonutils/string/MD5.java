package com.xiaomi.channel.commonutils.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
  public static String MD5_16(String paramString)
  {
    return MD5_32(paramString).subSequence(8, 24).toString();
  }
  
  public static String MD5_32(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      StringBuffer localStringBuffer = new StringBuffer();
      localMessageDigest.update(paramString.getBytes(), 0, paramString.length());
      paramString = localMessageDigest.digest();
      int i = 0;
      while (i < paramString.length)
      {
        localStringBuffer.append(byte2Hex(paramString[i]));
        i += 1;
      }
      return localStringBuffer.toString();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      return null;
    }
  }
  
  private static String byte2Hex(byte paramByte)
  {
    int i;
    StringBuilder localStringBuilder;
    if (paramByte < 0)
    {
      i = 128;
      paramByte = (paramByte & 0x7F) + i;
      localStringBuilder = new StringBuilder();
      if (paramByte >= 16) {
        break label56;
      }
    }
    label56:
    for (String str = "0";; str = "")
    {
      return str + Integer.toHexString(paramByte).toLowerCase();
      i = 0;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.string.MD5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */