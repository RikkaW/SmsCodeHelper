import java.io.InputStream;

public class any
  extends InputStream
{
  private static final int[] a = new int['Ā'];
  private static final int[] b;
  private final InputStream c;
  private final String d;
  private final int[] e = new int[3];
  private any.a f = any.a.a;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  
  static
  {
    int m = 0;
    int i1;
    int n;
    for (;;)
    {
      if (m >= 256)
      {
        b = new int[] { 80, 75, 3, 4 };
        return;
      }
      i1 = 0;
      n = m;
      if (i1 < 8) {
        break;
      }
      a[m] = n;
      m += 1;
    }
    if ((n & 0x1) == 1) {
      n = n >>> 1 ^ 0xEDB88320;
    }
    for (;;)
    {
      i1 += 1;
      break;
      n >>>= 1;
    }
  }
  
  public any(InputStream paramInputStream, String paramString)
  {
    c = paramInputStream;
    d = paramString;
  }
  
  private int a(int paramInt, byte paramByte)
  {
    return paramInt >>> 8 ^ a[((paramInt ^ paramByte) & 0xFF)];
  }
  
  private void a(byte paramByte)
  {
    e[0] = a(e[0], paramByte);
    int[] arrayOfInt = e;
    arrayOfInt[1] += (e[0] & 0xFF);
    e[1] = (e[1] * 134775813 + 1);
    e[2] = a(e[2], (byte)(e[1] >> 24));
  }
  
  private void a(String paramString)
  {
    int m = 0;
    e[0] = 305419896;
    e[1] = 591751049;
    e[2] = 878082192;
    for (;;)
    {
      if (m >= paramString.length()) {
        return;
      }
      a((byte)(paramString.charAt(m) & 0xFF));
      m += 1;
    }
  }
  
  private byte b()
  {
    int m = e[2] | 0x2;
    return (byte)(m * (m ^ 0x1) >>> 8);
  }
  
  public void close()
  {
    c.close();
    super.close();
  }
  
  public int read()
  {
    int m = c.read();
    if (g == 0)
    {
      int n = m;
      switch (a()[f.ordinal()])
      {
      default: 
        n = m;
      case 1: 
        do
        {
          return n;
          if (m != b[j])
          {
            f = any.a.h;
            return m;
          }
          j += 1;
          n = m;
        } while (j < b.length);
        g = 2;
        f = any.a.b;
        return m;
      case 2: 
        if ((m & 0x1) == 0) {
          throw new IllegalStateException("ZIP not password protected.");
        }
        if ((m & 0x40) == 64) {
          throw new IllegalStateException("Strong encryption used.");
        }
        if ((m & 0x8) == 8) {
          throw new IllegalStateException("Unsupported ZIP format.");
        }
        h = 0;
        j = 0;
        k = 12;
        f = any.a.c;
        g = 11;
        return m - 1;
      case 3: 
        h += (m << j * 8);
        m -= k;
        if (m < 0)
        {
          k = 1;
          m += 256;
        }
        for (;;)
        {
          j += 1;
          n = m;
          if (j <= 3) {
            break;
          }
          j = 0;
          i = 0;
          f = any.a.d;
          g = 4;
          return m;
          k = 0;
        }
      case 4: 
      case 5: 
        i += (m << j * 8);
        if (j == 1)
        {
          j = 0;
          if (f == any.a.d)
          {
            f = any.a.e;
            return m;
          }
          f = any.a.f;
          g = i;
          return m;
        }
        j = 1;
        return m;
      case 6: 
        a(d);
        n = 0;
      }
      for (;;)
      {
        if (n >= 12)
        {
          h -= 12;
          f = any.a.g;
          n = m;
          m = (n ^ b()) & 0xFF;
          a((byte)m);
          h -= 1;
          n = m;
          if (h != 0) {
            break;
          }
          j = 0;
          f = any.a.a;
          return m;
        }
        a((byte)(m ^ b()));
        m = c.read();
        n += 1;
      }
    }
    g -= 1;
    return m;
  }
  
  static enum a {}
}

/* Location:
 * Qualified Name:     any
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */