public class bk
{
  private static long[] a = { 305419896L, 591751049L, 878082192L };
  
  private static short a()
  {
    int i = (int)(a[2] & 0xFFFF | 0x2);
    return (short)(i * (i ^ 0x1) >> 8);
  }
  
  public static void a(String paramString)
  {
    int i = 0;
    a[0] = 305419896L;
    a[1] = 591751049L;
    a[2] = 878082192L;
    for (;;)
    {
      if (i >= paramString.length()) {
        return;
      }
      a((byte)paramString.charAt(i));
      i += 1;
    }
  }
  
  private static void a(short paramShort)
  {
    a[0] = bf.a(a[0], paramShort);
    short s = (byte)(int)a[0];
    paramShort = s;
    if ((byte)(int)a[0] < 0) {
      paramShort = (short)(s + 256);
    }
    a[1] += paramShort;
    a[1] *= 134775813L;
    a[1] += 1L;
    a[2] = bf.a(a[2], (byte)(int)(a[1] >> 24));
  }
  
  public static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    if (i >= paramInt) {
      return arrayOfByte;
    }
    short s = (byte)(a() ^ paramArrayOfByte[i]);
    if (s < 0)
    {
      a((short)((short)s + 256));
      arrayOfByte[i] = ((byte)(short)((short)s + 256));
    }
    for (;;)
    {
      i += 1;
      break;
      a(s);
      arrayOfByte[i] = s;
    }
  }
}

/* Location:
 * Qualified Name:     bk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */