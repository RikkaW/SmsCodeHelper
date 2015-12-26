package cn.com.xy.sms.sdk.net.util;

import java.util.Arrays;

public final class a
{
  private static final boolean a = true;
  private static final char[] b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
  private static final int[] c;
  
  static
  {
    int[] arrayOfInt = new int['Ā'];
    c = arrayOfInt;
    Arrays.fill(arrayOfInt, -1);
    int j = b.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        c[61] = 0;
        return;
      }
      c[b[i]] = i;
      i += 1;
    }
  }
  
  public static final byte[] a(byte[] paramArrayOfByte)
  {
    int i1 = 0;
    if (paramArrayOfByte != null) {}
    for (int m = paramArrayOfByte.length; m == 0; m = 0) {
      return new byte[0];
    }
    int i6 = m / 3 * 3;
    int j = (m - 1) / 3 + 1 << 2;
    int i5 = j + ((j - 1) / 76 << 1);
    byte[] arrayOfByte = new byte[i5];
    j = 0;
    int k = 0;
    int n = 0;
    if (n >= i6)
    {
      k = m - i6;
      if (k > 0)
      {
        n = paramArrayOfByte[i6];
        j = i1;
        if (k == 2) {
          j = (paramArrayOfByte[(m - 1)] & 0xFF) << 2;
        }
        j |= (n & 0xFF) << 10;
        arrayOfByte[(i5 - 4)] = ((byte)b[(j >> 12)]);
        arrayOfByte[(i5 - 3)] = ((byte)b[(j >>> 6 & 0x3F)]);
        if (k != 2) {
          break label416;
        }
      }
    }
    label416:
    for (int i = (byte)b[(j & 0x3F)];; i = 61)
    {
      arrayOfByte[(i5 - 2)] = i;
      arrayOfByte[(i5 - 1)] = 61;
      return arrayOfByte;
      int i2 = n + 1;
      n = paramArrayOfByte[n];
      int i3 = i2 + 1;
      int i4 = paramArrayOfByte[i2];
      i2 = i3 + 1;
      n = (i4 & 0xFF) << 8 | (n & 0xFF) << 16 | paramArrayOfByte[i3] & 0xFF;
      i3 = k + 1;
      arrayOfByte[k] = ((byte)b[(n >>> 18 & 0x3F)]);
      k = i3 + 1;
      arrayOfByte[i3] = ((byte)b[(n >>> 12 & 0x3F)]);
      i4 = k + 1;
      arrayOfByte[k] = ((byte)b[(n >>> 6 & 0x3F)]);
      i3 = i4 + 1;
      arrayOfByte[i4] = ((byte)b[(n & 0x3F)]);
      i4 = j + 1;
      j = i4;
      k = i3;
      n = i2;
      if (i4 != 19) {
        break;
      }
      j = i4;
      k = i3;
      n = i2;
      if (i3 >= i5 - 2) {
        break;
      }
      j = i3 + 1;
      arrayOfByte[i3] = 13;
      arrayOfByte[j] = 10;
      k = j + 1;
      j = 0;
      n = i2;
      break;
    }
  }
  
  private static byte[] a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int i1 = 0;
    if (paramArrayOfByte != null) {}
    for (int m = paramArrayOfByte.length; m == 0; m = 0) {
      return new byte[0];
    }
    int i6 = m / 3 * 3;
    int j = (m - 1) / 3 + 1 << 2;
    int i5 = j + ((j - 1) / 76 << 1);
    byte[] arrayOfByte = new byte[i5];
    j = 0;
    int k = 0;
    int n = 0;
    if (n >= i6)
    {
      k = m - i6;
      if (k > 0)
      {
        n = paramArrayOfByte[i6];
        j = i1;
        if (k == 2) {
          j = (paramArrayOfByte[(m - 1)] & 0xFF) << 2;
        }
        j |= (n & 0xFF) << 10;
        arrayOfByte[(i5 - 4)] = ((byte)b[(j >> 12)]);
        arrayOfByte[(i5 - 3)] = ((byte)b[(j >>> 6 & 0x3F)]);
        if (k != 2) {
          break label429;
        }
      }
    }
    label429:
    for (int i = (byte)b[(j & 0x3F)];; i = 61)
    {
      arrayOfByte[(i5 - 2)] = i;
      arrayOfByte[(i5 - 1)] = 61;
      return arrayOfByte;
      int i2 = n + 1;
      n = paramArrayOfByte[n];
      int i3 = i2 + 1;
      int i4 = paramArrayOfByte[i2];
      i2 = i3 + 1;
      n = (i4 & 0xFF) << 8 | (n & 0xFF) << 16 | paramArrayOfByte[i3] & 0xFF;
      i3 = k + 1;
      arrayOfByte[k] = ((byte)b[(n >>> 18 & 0x3F)]);
      k = i3 + 1;
      arrayOfByte[i3] = ((byte)b[(n >>> 12 & 0x3F)]);
      i4 = k + 1;
      arrayOfByte[k] = ((byte)b[(n >>> 6 & 0x3F)]);
      i3 = i4 + 1;
      arrayOfByte[i4] = ((byte)b[(n & 0x3F)]);
      i4 = j + 1;
      j = i4;
      k = i3;
      n = i2;
      if (i4 != 19) {
        break;
      }
      j = i4;
      k = i3;
      n = i2;
      if (i3 >= i5 - 2) {
        break;
      }
      j = i3 + 1;
      arrayOfByte[i3] = 13;
      arrayOfByte[j] = 10;
      k = j + 1;
      j = 0;
      n = i2;
      break;
    }
  }
  
  public static final byte[] b(byte[] paramArrayOfByte)
  {
    int m = paramArrayOfByte.length;
    int j = 0;
    for (int i = 0;; i = k)
    {
      if (j >= m)
      {
        if ((m - i) % 4 == 0) {
          break;
        }
        return null;
      }
      k = i;
      if (c[(paramArrayOfByte[j] & 0xFF)] < 0) {
        k = i + 1;
      }
      j += 1;
    }
    j = m;
    int k = 0;
    Object localObject;
    int n;
    if (j > 1)
    {
      localObject = c;
      n = j - 1;
      if (localObject[(paramArrayOfByte[n] & 0xFF)] <= 0) {}
    }
    else
    {
      n = ((m - i) * 6 >> 3) - k;
      localObject = new byte[n];
      j = 0;
      i = 0;
    }
    label261:
    for (;;)
    {
      if (j >= n)
      {
        return (byte[])localObject;
        j = n;
        if (paramArrayOfByte[n] != 61) {
          break;
        }
        k += 1;
        j = n;
        break;
      }
      m = 0;
      k = 0;
      if (k >= 4)
      {
        k = j + 1;
        localObject[j] = ((byte)(m >> 16));
        j = k;
        if (k < n)
        {
          j = k + 1;
          localObject[k] = ((byte)(m >> 8));
          if (j >= n) {
            break label261;
          }
          k = j + 1;
          localObject[j] = ((byte)m);
          j = k;
        }
      }
      else
      {
        int i1 = c[(paramArrayOfByte[i] & 0xFF)];
        if (i1 >= 0) {
          m |= i1 << 18 - k * 6;
        }
        for (;;)
        {
          k += 1;
          i += 1;
          break;
          k -= 1;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */