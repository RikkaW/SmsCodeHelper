package com.xiaomi.mms.utils.sec;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class Base64Utils
{
  public static byte[] decryptToByte(String paramString)
    throws UnsupportedEncodingException
  {
    return Base64.decode(paramString.getBytes("UTF-8"), 2);
  }
  
  public static String encrypt(String paramString)
    throws UnsupportedEncodingException
  {
    return encrypt(paramString.getBytes("UTF-8"));
  }
  
  public static String encrypt(byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    return new String(Base64.encode(paramArrayOfByte, 2), "UTF-8");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.sec.Base64Utils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */