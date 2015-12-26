import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

public class bg
  extends FilterInputStream
{
  protected Inflater a;
  protected byte[] b;
  protected String c = null;
  protected int d;
  boolean e = false;
  private boolean f = false;
  private boolean g = false;
  private byte[] h = new byte[1];
  private byte[] i = new byte['Ȁ'];
  
  public bg(InputStream paramInputStream, Inflater paramInflater, int paramInt)
  {
    super(paramInputStream);
    if ((paramInputStream == null) || (paramInflater == null)) {
      throw new NullPointerException();
    }
    if (paramInt <= 0) {
      throw new IllegalArgumentException("buffer size <= 0");
    }
    a = paramInflater;
    b = new byte[paramInt];
  }
  
  private void b()
  {
    if (f) {
      throw new IOException("Stream closed");
    }
  }
  
  protected void a()
  {
    b();
    d = in.read(b, 0, b.length);
    if (d == -1) {
      throw new EOFException("Unexpected end of ZLIB input stream");
    }
    if (c != null)
    {
      byte[] arrayOfByte = bk.a(b, d);
      a.setInput(arrayOfByte, 0, arrayOfByte.length);
      return;
    }
    a.setInput(b, 0, d);
  }
  
  public int available()
  {
    b();
    if (g) {
      return 0;
    }
    return 1;
  }
  
  public void close()
  {
    if (!f)
    {
      if (e) {
        a.end();
      }
      in.close();
      f = true;
    }
  }
  
  public void mark(int paramInt) {}
  
  public boolean markSupported()
  {
    return false;
  }
  
  public int read()
  {
    b();
    if (read(h, 0, 1) == -1) {
      return -1;
    }
    return h[0] & 0xFF;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    b();
    if ((paramInt1 | paramInt2 | paramInt1 + paramInt2 | paramArrayOfByte.length - (paramInt1 + paramInt2)) < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (paramInt2 == 0) {
      return 0;
    }
    try
    {
      int j;
      do
      {
        if ((a.finished()) || (a.needsDictionary()))
        {
          g = true;
          return -1;
        }
        if (a.needsInput()) {
          a();
        }
        j = a.inflate(paramArrayOfByte, paramInt1, paramInt2);
      } while (j == 0);
      return j;
    }
    catch (DataFormatException paramArrayOfByte)
    {
      paramArrayOfByte = paramArrayOfByte.getMessage();
      if (paramArrayOfByte == null) {}
    }
    for (;;)
    {
      throw new ZipException(paramArrayOfByte);
      paramArrayOfByte = "Invalid ZLIB data format";
    }
  }
  
  public void reset()
  {
    try
    {
      throw new IOException("mark/reset not supported");
    }
    finally {}
  }
  
  public long skip(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("negative skip length");
    }
    b();
    int n = (int)Math.min(paramLong, 2147483647L);
    int k;
    for (int j = 0;; j = k + j)
    {
      if (j >= n) {}
      for (;;)
      {
        return j;
        int m = n - j;
        k = m;
        if (m > i.length) {
          k = i.length;
        }
        k = read(i, 0, k);
        if (k != -1) {
          break;
        }
        g = true;
      }
    }
  }
}

/* Location:
 * Qualified Name:     bg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */