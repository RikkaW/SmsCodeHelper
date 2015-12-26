package com.xiaomi.channel.commonutils.string;

public class Base64Coder
{
  private static char[] map1;
  private static byte[] map2;
  private static final String systemLineSeparator = System.getProperty("line.separator");
  
  static
  {
    map1 = new char[64];
    int i = 65;
    int j = 0;
    while (i <= 90)
    {
      map1[j] = i;
      i = (char)(i + 1);
      j += 1;
    }
    i = 97;
    while (i <= 122)
    {
      map1[j] = i;
      i = (char)(i + 1);
      j += 1;
    }
    i = 48;
    while (i <= 57)
    {
      map1[j] = i;
      i = (char)(i + 1);
      j += 1;
    }
    char[] arrayOfChar = map1;
    int k = j + 1;
    arrayOfChar[j] = '+';
    map1[k] = '/';
    map2 = new byte[''];
    j = 0;
    while (j < map2.length)
    {
      map2[j] = -1;
      j += 1;
    }
    j = 0;
    while (j < 64)
    {
      map2[map1[j]] = ((byte)j);
      j += 1;
    }
  }
  
  public static byte[] decode(String paramString)
  {
    return decode(paramString.toCharArray());
  }
  
  public static byte[] decode(char[] paramArrayOfChar)
  {
    return decode(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public static byte[] decode(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = paramInt2;
    if (paramInt2 % 4 != 0) {
      throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
    }
    while ((i > 0) && (paramArrayOfChar[(paramInt1 + i - 1)] == '=')) {
      i -= 1;
    }
    int m = i * 3 / 4;
    byte[] arrayOfByte = new byte[m];
    int n = paramInt1 + i;
    paramInt2 = 0;
    int j;
    int k;
    if (paramInt1 < n)
    {
      i = paramInt1 + 1;
      int i1 = paramArrayOfChar[paramInt1];
      paramInt1 = i + 1;
      int i2 = paramArrayOfChar[i];
      if (paramInt1 < n)
      {
        i = paramArrayOfChar[paramInt1];
        paramInt1 += 1;
        if (paramInt1 >= n) {
          break label165;
        }
        j = paramInt1 + 1;
        k = paramArrayOfChar[paramInt1];
        paramInt1 = j;
      }
      label165:
      for (j = k;; j = 65)
      {
        if ((i1 <= 127) && (i2 <= 127) && (i <= 127) && (j <= 127)) {
          break label172;
        }
        throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
        i = 65;
        break;
      }
      label172:
      i1 = map2[i1];
      i2 = map2[i2];
      k = map2[i];
      j = map2[j];
      if ((i1 < 0) || (i2 < 0) || (k < 0) || (j < 0)) {
        throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
      }
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = ((byte)(i1 << 2 | i2 >>> 4));
      if (i >= m) {
        break label314;
      }
      paramInt2 = i + 1;
      arrayOfByte[i] = ((byte)((i2 & 0xF) << 4 | k >>> 2));
    }
    for (;;)
    {
      if (paramInt2 < m)
      {
        i = paramInt2 + 1;
        arrayOfByte[paramInt2] = ((byte)((k & 0x3) << 6 | j));
        paramInt2 = i;
      }
      for (;;)
      {
        break;
        return arrayOfByte;
      }
      label314:
      paramInt2 = i;
    }
  }
  
  public static char[] encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static char[] encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int n = (paramInt2 * 4 + 2) / 3;
    char[] arrayOfChar = new char[(paramInt2 + 2) / 3 * 4];
    int i1 = paramInt1 + paramInt2;
    paramInt2 = 0;
    if (paramInt1 < i1)
    {
      int j = paramInt1 + 1;
      int i2 = paramArrayOfByte[paramInt1] & 0xFF;
      label69:
      int k;
      if (j < i1)
      {
        paramInt1 = j + 1;
        j = paramArrayOfByte[j] & 0xFF;
        if (paramInt1 >= i1) {
          break label212;
        }
        k = paramInt1 + 1;
        int m = paramArrayOfByte[paramInt1] & 0xFF;
        paramInt1 = k;
        k = m;
        label96:
        m = paramInt2 + 1;
        arrayOfChar[paramInt2] = map1[(i2 >>> 2)];
        paramInt2 = m + 1;
        arrayOfChar[m] = map1[((i2 & 0x3) << 4 | j >>> 4)];
        if (paramInt2 >= n) {
          break label218;
        }
        i = map1[((j & 0xF) << 2 | k >>> 6)];
        label162:
        arrayOfChar[paramInt2] = i;
        paramInt2 += 1;
        if (paramInt2 >= n) {
          break label224;
        }
      }
      label212:
      label218:
      label224:
      for (int i = map1[(k & 0x3F)];; i = 61)
      {
        arrayOfChar[paramInt2] = i;
        paramInt2 += 1;
        break;
        k = 0;
        paramInt1 = j;
        j = k;
        break label69;
        k = 0;
        break label96;
        i = 61;
        break label162;
      }
    }
    return arrayOfChar;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.string.Base64Coder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */