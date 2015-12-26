import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

public class bi
  extends bg
{
  private bh f;
  private CRC32 g = new CRC32();
  private long h;
  private byte[] i = new byte['Ȁ'];
  private boolean j = false;
  private boolean k = false;
  private byte[] l = new byte['Ā'];
  
  public bi(InputStream paramInputStream, String paramString)
  {
    super(new PushbackInputStream(paramInputStream, 512), new Inflater(true), 512);
    e = true;
    if (paramInputStream == null) {
      throw new NullPointerException("in is null");
    }
    c = paramString;
  }
  
  private static final int a(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8;
  }
  
  private static String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i1 = paramInt1 + paramInt2;
    int m = paramInt1;
    paramInt2 = 0;
    int i2;
    for (;;)
    {
      if (m >= i1)
      {
        if (m == i1) {
          break;
        }
        throw new IllegalArgumentException();
      }
      n = m + 1;
      switch ((paramArrayOfByte[m] & 0xFF) >> 4)
      {
      case 8: 
      case 9: 
      case 10: 
      case 11: 
      default: 
        throw new IllegalArgumentException();
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
        paramInt2 += 1;
        m = n;
        break;
      case 12: 
      case 13: 
        m = n + 1;
        if ((paramArrayOfByte[n] & 0xC0) != 128) {
          throw new IllegalArgumentException();
        }
        paramInt2 += 1;
        break;
      case 14: 
        i2 = n + 1;
        if ((paramArrayOfByte[n] & 0xC0) == 128)
        {
          m = i2 + 1;
          if ((paramArrayOfByte[i2] & 0xC0) == 128) {}
        }
        else
        {
          throw new IllegalArgumentException();
        }
        paramInt2 += 1;
      }
    }
    char[] arrayOfChar = new char[paramInt2];
    int n = 0;
    m = paramInt1;
    paramInt1 = n;
    for (;;)
    {
      if (m >= i1) {
        return new String(arrayOfChar, 0, paramInt2);
      }
      n = m + 1;
      i2 = paramArrayOfByte[m] & 0xFF;
      switch (i2 >> 4)
      {
      case 8: 
      case 9: 
      case 10: 
      case 11: 
      default: 
        throw new IllegalArgumentException();
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
        arrayOfChar[paramInt1] = ((char)i2);
        paramInt1 += 1;
        m = n;
        break;
      case 12: 
      case 13: 
        m = n + 1;
        arrayOfChar[paramInt1] = ((char)(paramArrayOfByte[n] & 0x3F | (i2 & 0x1F) << 6));
        paramInt1 += 1;
        break;
      case 14: 
        int i3 = n + 1;
        n = paramArrayOfByte[n];
        m = i3 + 1;
        arrayOfChar[paramInt1] = ((char)((n & 0x3F) << 6 | (i2 & 0xF) << 12 | paramArrayOfByte[i3] & 0x3F));
        paramInt1 += 1;
      }
    }
  }
  
  private void a(bh parambh)
  {
    int m = a.getRemaining();
    if (m > 0) {
      ((PushbackInputStream)in).unread(b, d - m, m);
    }
    if ((h & 0x8) == 8)
    {
      b(i, 0, 16);
      long l1 = b(i, 0);
      if (l1 == 134695760L) {
        break label182;
      }
      c = l1;
      e = b(i, 4);
      d = b(i, 8);
      ((PushbackInputStream)in).unread(i, 11, 4);
    }
    while (d != a.getBytesWritten())
    {
      throw new ZipException("invalid entry size (expected " + d + " but got " + a.getBytesWritten() + " bytes)");
      label182:
      c = b(i, 4);
      e = b(i, 8);
      if (h == 9) {
        e -= 12L;
      }
      d = b(i, 12);
    }
    if (e != a.getBytesRead()) {
      throw new ZipException("invalid entry compressed size (expected " + e + " but got " + a.getBytesRead() + " bytes)");
    }
    if (c != g.getValue()) {
      throw new ZipException("invalid entry CRC (expected 0x" + Long.toHexString(c) + " but got 0x" + Long.toHexString(g.getValue()) + ")");
    }
  }
  
  private static final long b(byte[] paramArrayOfByte, int paramInt)
  {
    return a(paramArrayOfByte, paramInt) | a(paramArrayOfByte, paramInt + 2) << 16;
  }
  
  private void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      if (paramInt2 <= 0) {
        return;
      }
      int m = in.read(paramArrayOfByte, paramInt1, paramInt2);
      if (m == -1) {
        throw new EOFException();
      }
      paramInt1 += m;
      paramInt2 -= m;
    }
  }
  
  private void d()
  {
    if (j) {
      throw new IOException("Stream closed");
    }
  }
  
  private bh e()
  {
    Object localObject = null;
    try
    {
      b(i, 0, 30);
      if (b(i, 0) != 67324752L) {}
      bh localbh;
      byte[] arrayOfByte;
      do
      {
        do
        {
          do
          {
            return (bh)localObject;
            int i1 = a(i, 26);
            if (i1 == 0) {
              throw new ZipException("missing entry name");
            }
            int m = l.length;
            if (i1 > m)
            {
              int n;
              do
              {
                n = m * 2;
                m = n;
              } while (i1 > n);
              l = new byte[n];
            }
            b(l, 0, i1);
            localbh = a(a(l, 0, i1));
            i = a(i, 4);
            h = a(i, 6);
            m = h;
            f = a(i, 8);
            b = b(i, 10);
            if ((h & 0x8) == 8)
            {
              if (f != 8) {
                throw new ZipException("only DEFLATED entries can have EXT descriptor");
              }
            }
            else
            {
              c = b(i, 14);
              e = b(i, 18);
              d = b(i, 22);
            }
            m = a(i, 28);
            if (m > 0)
            {
              localObject = new byte[m];
              b((byte[])localObject, 0, m);
              g = ((byte[])localObject);
            }
            localObject = localbh;
          } while (c == null);
          localObject = new byte[12];
          b((byte[])localObject, 0, 12);
          bk.a(c);
          arrayOfByte = bk.a((byte[])localObject, 12);
          localObject = localbh;
        } while (arrayOfByte[11] == (byte)(int)(c >> 24 & 0xFF));
        if ((h & 0x8) != 8) {
          throw new ZipException("The password did not match.");
        }
        localObject = localbh;
      } while (arrayOfByte[11] == (byte)(int)(b >> 8 & 0xFF));
      throw new ZipException("The password did not match.");
    }
    catch (EOFException localEOFException) {}
    return null;
  }
  
  protected bh a(String paramString)
  {
    return new bh(paramString);
  }
  
  public int available()
  {
    d();
    if (k) {
      return 0;
    }
    return 1;
  }
  
  public bh b()
  {
    d();
    if (f != null) {
      c();
    }
    g.reset();
    a.reset();
    bh localbh = e();
    f = localbh;
    if (localbh == null) {
      return null;
    }
    if (f.f == 0) {
      h = f.d;
    }
    k = false;
    return f;
  }
  
  public void c()
  {
    d();
    while (read(i, 0, i.length) != -1) {}
    k = true;
  }
  
  public void close()
  {
    if (!j)
    {
      super.close();
      j = true;
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int m = -1;
    d();
    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 > paramArrayOfByte.length - paramInt2)) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      m = 0;
    }
    while (f == null) {
      return m;
    }
    switch (f.f)
    {
    default: 
      throw new InternalError("invalid compression method");
    case 8: 
      paramInt2 = super.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt2 == -1)
      {
        a(f);
        k = true;
        f = null;
      }
      for (;;)
      {
        return paramInt2;
        g.update(paramArrayOfByte, paramInt1, paramInt2);
      }
    }
    if (h <= 0L)
    {
      k = true;
      f = null;
      return -1;
    }
    m = paramInt2;
    if (paramInt2 > h) {
      m = (int)h;
    }
    paramInt2 = in.read(paramArrayOfByte, paramInt1, m);
    if (paramInt2 == -1) {
      throw new ZipException("unexpected EOF");
    }
    g.update(paramArrayOfByte, paramInt1, paramInt2);
    h -= paramInt2;
    return paramInt2;
  }
  
  public long skip(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("negative skip length");
    }
    d();
    int i2 = (int)Math.min(paramLong, 2147483647L);
    int n;
    for (int m = 0;; m = n + m)
    {
      if (m >= i2) {}
      for (;;)
      {
        return m;
        int i1 = i2 - m;
        n = i1;
        if (i1 > i.length) {
          n = i.length;
        }
        n = read(i, 0, n);
        if (n != -1) {
          break;
        }
        k = true;
      }
    }
  }
}

/* Location:
 * Qualified Name:     bi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */