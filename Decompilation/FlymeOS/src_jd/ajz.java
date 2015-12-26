import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.http.util.EncodingUtils;

public abstract class ajz
{
  protected static final byte[] a = EncodingUtils.getAsciiBytes("----------------314159265358979323846");
  protected static final byte[] b = EncodingUtils.getAsciiBytes("\r\n");
  protected static final byte[] c = EncodingUtils.getAsciiBytes("\"");
  protected static final byte[] d = EncodingUtils.getAsciiBytes("--");
  protected static final byte[] e = EncodingUtils.getAsciiBytes("Content-Disposition: form-data; name=");
  protected static final byte[] f = EncodingUtils.getAsciiBytes("Content-Type: ");
  protected static final byte[] g = EncodingUtils.getAsciiBytes("; charset=");
  protected static final byte[] h = EncodingUtils.getAsciiBytes("Content-Transfer-Encoding: ");
  private static final byte[] i = a;
  private byte[] j;
  
  public static long a(ajz[] paramArrayOfajz, byte[] paramArrayOfByte)
  {
    if (paramArrayOfajz == null) {
      throw new IllegalArgumentException("Parts may not be null");
    }
    int k = 0;
    long l1 = 0L;
    while (k < paramArrayOfajz.length)
    {
      paramArrayOfajz[k].a(paramArrayOfByte);
      long l2 = paramArrayOfajz[k].h();
      if (l2 < 0L) {
        return -1L;
      }
      l1 += l2;
      k += 1;
    }
    return d.length + l1 + paramArrayOfByte.length + d.length + b.length;
  }
  
  public static void a(OutputStream paramOutputStream, ajz[] paramArrayOfajz, byte[] paramArrayOfByte)
  {
    if (paramArrayOfajz == null) {
      throw new IllegalArgumentException("Parts may not be null");
    }
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      throw new IllegalArgumentException("partBoundary may not be empty");
    }
    int k = 0;
    while (k < paramArrayOfajz.length)
    {
      paramArrayOfajz[k].a(paramArrayOfByte);
      paramArrayOfajz[k].h(paramOutputStream);
      k += 1;
    }
    paramOutputStream.write(d);
    paramOutputStream.write(paramArrayOfByte);
    paramOutputStream.write(d);
    paramOutputStream.write(b);
  }
  
  protected abstract long a();
  
  protected void a(OutputStream paramOutputStream)
  {
    paramOutputStream.write(e);
    paramOutputStream.write(c);
    paramOutputStream.write(EncodingUtils.getAsciiBytes(b()));
    paramOutputStream.write(c);
  }
  
  void a(byte[] paramArrayOfByte)
  {
    j = paramArrayOfByte;
  }
  
  public abstract String b();
  
  protected abstract void b(OutputStream paramOutputStream);
  
  public abstract String c();
  
  protected void c(OutputStream paramOutputStream)
  {
    paramOutputStream.write(d);
    paramOutputStream.write(f());
    paramOutputStream.write(b);
  }
  
  public abstract String d();
  
  protected void d(OutputStream paramOutputStream)
  {
    String str = c();
    if (str != null)
    {
      paramOutputStream.write(b);
      paramOutputStream.write(f);
      paramOutputStream.write(EncodingUtils.getAsciiBytes(str));
      str = d();
      if (str != null)
      {
        paramOutputStream.write(g);
        paramOutputStream.write(EncodingUtils.getAsciiBytes(str));
      }
    }
  }
  
  public abstract String e();
  
  protected void e(OutputStream paramOutputStream)
  {
    String str = e();
    if (str != null)
    {
      paramOutputStream.write(b);
      paramOutputStream.write(h);
      paramOutputStream.write(EncodingUtils.getAsciiBytes(str));
    }
  }
  
  protected void f(OutputStream paramOutputStream)
  {
    paramOutputStream.write(b);
    paramOutputStream.write(b);
  }
  
  protected byte[] f()
  {
    if (j == null) {
      return i;
    }
    return j;
  }
  
  protected void g(OutputStream paramOutputStream)
  {
    paramOutputStream.write(b);
  }
  
  public boolean g()
  {
    return true;
  }
  
  public long h()
  {
    if (a() < 0L) {
      return -1L;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    c(localByteArrayOutputStream);
    a(localByteArrayOutputStream);
    d(localByteArrayOutputStream);
    e(localByteArrayOutputStream);
    f(localByteArrayOutputStream);
    g(localByteArrayOutputStream);
    return localByteArrayOutputStream.size() + a();
  }
  
  public void h(OutputStream paramOutputStream)
  {
    c(paramOutputStream);
    a(paramOutputStream);
    d(paramOutputStream);
    e(paramOutputStream);
    f(paramOutputStream);
    b(paramOutputStream);
    g(paramOutputStream);
  }
  
  public String toString()
  {
    return b();
  }
}

/* Location:
 * Qualified Name:     ajz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */