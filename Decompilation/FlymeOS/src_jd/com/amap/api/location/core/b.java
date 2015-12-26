package com.amap.api.location.core;

import java.io.ByteArrayOutputStream;

public class b
{
  private static final char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static byte[] b = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
  
  public static String a(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      StringBuffer localStringBuffer;
      int n;
      int k;
      try
      {
        localStringBuffer = new StringBuffer();
        int j = paramArrayOfByte.length;
        int i = 0;
        if (i < j)
        {
          n = i + 1;
          k = paramArrayOfByte[i] & 0xFF;
          if (n == j)
          {
            localStringBuffer.append(a[(k >>> 2)]);
            localStringBuffer.append(a[((k & 0x3) << 4)]);
            localStringBuffer.append("==");
          }
        }
        else
        {
          return localStringBuffer.toString();
        }
        m = n + 1;
        n = paramArrayOfByte[n] & 0xFF;
        if (m == j)
        {
          localStringBuffer.append(a[(k >>> 2)]);
          localStringBuffer.append(a[((k & 0x3) << 4 | (n & 0xF0) >>> 4)]);
          localStringBuffer.append(a[((n & 0xF) << 2)]);
          localStringBuffer.append("=");
          continue;
        }
        i = m + 1;
      }
      catch (Throwable paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
        return "";
      }
      int m = paramArrayOfByte[m] & 0xFF;
      localStringBuffer.append(a[(k >>> 2)]);
      localStringBuffer.append(a[((k & 0x3) << 4 | (n & 0xF0) >>> 4)]);
      localStringBuffer.append(a[((n & 0xF) << 2 | (m & 0xC0) >>> 6)]);
      localStringBuffer.append(a[(m & 0x3F)]);
    }
  }
  
  public static byte[] a(String paramString)
  {
    int k;
    int i;
    int j;
    int n;
    for (;;)
    {
      try
      {
        paramString = paramString.getBytes("UTF-8");
        k = paramString.length;
        localByteArrayOutputStream = new ByteArrayOutputStream(k);
        i = 0;
      }
      catch (Throwable paramString)
      {
        ByteArrayOutputStream localByteArrayOutputStream;
        byte[] arrayOfByte;
        paramString.printStackTrace();
        return null;
      }
      arrayOfByte = b;
      j = i + 1;
      n = arrayOfByte[paramString[i]];
      if (j >= k) {
        break label263;
      }
      i = j;
      if (n != -1) {
        break label263;
      }
    }
    for (;;)
    {
      label58:
      return localByteArrayOutputStream.toByteArray();
      label255:
      label263:
      do
      {
        int m;
        do
        {
          arrayOfByte = b;
          j = i + 1;
          m = arrayOfByte[paramString[i]];
          if (j >= k) {
            break;
          }
          i = j;
        } while (m == -1);
        if (m == -1) {
          break label58;
        }
        localByteArrayOutputStream.write(n << 2 | (m & 0x30) >>> 4);
        i = j;
        do
        {
          j = i + 1;
          i = paramString[i];
          if (i == 61) {
            return localByteArrayOutputStream.toByteArray();
          }
          n = b[i];
          if (j >= k) {
            break;
          }
          i = j;
        } while (n == -1);
        if (n == -1) {
          break label58;
        }
        localByteArrayOutputStream.write((m & 0xF) << 4 | (n & 0x3C) >>> 2);
        for (;;)
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
              break;
            }
            localByteArrayOutputStream.write(j | (n & 0x3) << 6);
            break label255;
          }
          j = i;
        }
        if (i >= k) {
          break label58;
        }
        break;
        i = j;
      } while (n != -1);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */