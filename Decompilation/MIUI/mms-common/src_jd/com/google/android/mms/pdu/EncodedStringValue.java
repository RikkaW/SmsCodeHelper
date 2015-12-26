package com.google.android.mms.pdu;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class EncodedStringValue
  implements Cloneable
{
  private static final boolean DEBUG = false;
  private static final boolean LOCAL_LOGV = false;
  private static final String TAG = "EncodedStringValue";
  private int mCharacterSet;
  private byte[] mData;
  
  public EncodedStringValue(int paramInt, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("EncodedStringValue: Text-string is null.");
    }
    mCharacterSet = paramInt;
    mData = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, mData, 0, paramArrayOfByte.length);
  }
  
  public EncodedStringValue(String paramString)
  {
    try
    {
      mData = paramString.getBytes("utf-8");
      mCharacterSet = 106;
      return;
    }
    catch (UnsupportedEncodingException paramString)
    {
      Log.e("EncodedStringValue", "Default encoding must be supported.", paramString);
    }
  }
  
  public EncodedStringValue(byte[] paramArrayOfByte)
  {
    this(106, paramArrayOfByte);
  }
  
  public static String concat(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramArrayOfEncodedStringValue.length - 1;
    int i = 0;
    while (i <= j)
    {
      localStringBuilder.append(paramArrayOfEncodedStringValue[i].getString());
      if (i < j) {
        localStringBuilder.append(";");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static EncodedStringValue copy(EncodedStringValue paramEncodedStringValue)
  {
    if (paramEncodedStringValue == null) {
      return null;
    }
    return new EncodedStringValue(mCharacterSet, mData);
  }
  
  public static EncodedStringValue[] encodeStrings(String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    if (j > 0)
    {
      EncodedStringValue[] arrayOfEncodedStringValue2 = new EncodedStringValue[j];
      int i = 0;
      for (;;)
      {
        arrayOfEncodedStringValue1 = arrayOfEncodedStringValue2;
        if (i >= j) {
          break;
        }
        arrayOfEncodedStringValue2[i] = new EncodedStringValue(paramArrayOfString[i]);
        i += 1;
      }
    }
    EncodedStringValue[] arrayOfEncodedStringValue1 = null;
    return arrayOfEncodedStringValue1;
  }
  
  public static EncodedStringValue[] extract(String paramString)
  {
    paramString = paramString.split(";");
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramString.length)
    {
      if (paramString[i].length() > 0) {
        localArrayList.add(new EncodedStringValue(paramString[i]));
      }
      i += 1;
    }
    i = localArrayList.size();
    if (i > 0) {
      return (EncodedStringValue[])localArrayList.toArray(new EncodedStringValue[i]);
    }
    return null;
  }
  
  public void appendTextString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("Text-string is null.");
    }
    if (mData == null)
    {
      mData = new byte[paramArrayOfByte.length];
      System.arraycopy(paramArrayOfByte, 0, mData, 0, paramArrayOfByte.length);
      return;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      localByteArrayOutputStream.write(mData);
      localByteArrayOutputStream.write(paramArrayOfByte);
      mData = localByteArrayOutputStream.toByteArray();
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
      throw new NullPointerException("appendTextString: failed when write a new Text-string");
    }
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    super.clone();
    int i = mData.length;
    Object localObject = new byte[i];
    System.arraycopy(mData, 0, localObject, 0, i);
    try
    {
      localObject = new EncodedStringValue(mCharacterSet, (byte[])localObject);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e("EncodedStringValue", "failed to clone an EncodedStringValue: " + this);
      localException.printStackTrace();
      throw new CloneNotSupportedException(localException.getMessage());
    }
  }
  
  public int getCharacterSet()
  {
    return mCharacterSet;
  }
  
  public String getString()
  {
    if (mCharacterSet == 0) {
      return new String(mData);
    }
    try
    {
      String str1 = CharacterSets.getMimeName(mCharacterSet);
      str1 = new String(mData, str1);
      return str1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException1)
    {
      try
      {
        String str2 = new String(mData, "iso-8859-1");
        return str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException2) {}
    }
    return new String(mData);
  }
  
  public byte[] getTextString()
  {
    byte[] arrayOfByte = new byte[mData.length];
    System.arraycopy(mData, 0, arrayOfByte, 0, mData.length);
    return arrayOfByte;
  }
  
  public void setCharacterSet(int paramInt)
  {
    mCharacterSet = paramInt;
  }
  
  public void setTextString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("EncodedStringValue: Text-string is null.");
    }
    mData = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, mData, 0, paramArrayOfByte.length);
  }
  
  public EncodedStringValue[] split(String paramString)
  {
    String[] arrayOfString = getString().split(paramString);
    EncodedStringValue[] arrayOfEncodedStringValue = new EncodedStringValue[arrayOfString.length];
    int i = 0;
    for (;;)
    {
      paramString = arrayOfEncodedStringValue;
      if (i < arrayOfEncodedStringValue.length) {
        try
        {
          arrayOfEncodedStringValue[i] = new EncodedStringValue(mCharacterSet, arrayOfString[i].getBytes());
          i += 1;
        }
        catch (NullPointerException paramString)
        {
          paramString = null;
        }
      }
    }
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.EncodedStringValue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */