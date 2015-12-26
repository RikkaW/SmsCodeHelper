package com.google.android.mms.pdu;

public class Base64
{
  static final int BASELENGTH = 255;
  static final int FOURBYTE = 4;
  static final byte PAD = 61;
  private static byte[] base64Alphabet = new byte['ÿ'];
  
  static
  {
    int i = 0;
    while (i < 255)
    {
      base64Alphabet[i] = -1;
      i += 1;
    }
    i = 90;
    while (i >= 65)
    {
      base64Alphabet[i] = ((byte)(i - 65));
      i -= 1;
    }
    i = 122;
    while (i >= 97)
    {
      base64Alphabet[i] = ((byte)(i - 97 + 26));
      i -= 1;
    }
    i = 57;
    while (i >= 48)
    {
      base64Alphabet[i] = ((byte)(i - 48 + 52));
      i -= 1;
    }
    base64Alphabet[43] = 62;
    base64Alphabet[47] = 63;
  }
  
  public static byte[] decodeBase64(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte2 = discardNonBase64(paramArrayOfByte);
    if (arrayOfByte2.length == 0) {
      paramArrayOfByte = new byte[0];
    }
    int m;
    int j;
    int i;
    byte[] arrayOfByte1;
    do
    {
      return paramArrayOfByte;
      m = arrayOfByte2.length / 4;
      j = 0;
      i = arrayOfByte2.length;
      while (arrayOfByte2[(i - 1)] == 61)
      {
        k = i - 1;
        i = k;
        if (k == 0) {
          return new byte[0];
        }
      }
      arrayOfByte1 = new byte[i - m];
      i = 0;
      paramArrayOfByte = arrayOfByte1;
    } while (i >= m);
    int n = i * 4;
    int i2 = arrayOfByte2[(n + 2)];
    int i1 = arrayOfByte2[(n + 3)];
    int k = base64Alphabet[arrayOfByte2[n]];
    n = base64Alphabet[arrayOfByte2[(n + 1)]];
    if ((i2 != 61) && (i1 != 61))
    {
      i2 = base64Alphabet[i2];
      i1 = base64Alphabet[i1];
      arrayOfByte1[j] = ((byte)(k << 2 | n >> 4));
      arrayOfByte1[(j + 1)] = ((byte)((n & 0xF) << 4 | i2 >> 2 & 0xF));
      arrayOfByte1[(j + 2)] = ((byte)(i2 << 6 | i1));
    }
    for (;;)
    {
      j += 3;
      i += 1;
      break;
      if (i2 == 61)
      {
        arrayOfByte1[j] = ((byte)(k << 2 | n >> 4));
      }
      else if (i1 == 61)
      {
        i1 = base64Alphabet[i2];
        arrayOfByte1[j] = ((byte)(k << 2 | n >> 4));
        arrayOfByte1[(j + 1)] = ((byte)((n & 0xF) << 4 | i1 >> 2 & 0xF));
      }
    }
  }
  
  static byte[] discardNonBase64(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    int j = 0;
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int k = j;
      if (isBase64(paramArrayOfByte[i]))
      {
        arrayOfByte[j] = paramArrayOfByte[i];
        k = j + 1;
      }
      i += 1;
      j = k;
    }
    paramArrayOfByte = new byte[j];
    System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, j);
    return paramArrayOfByte;
  }
  
  private static boolean isBase64(byte paramByte)
  {
    if (paramByte == 61) {}
    while (base64Alphabet[paramByte] != -1) {
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.Base64
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */