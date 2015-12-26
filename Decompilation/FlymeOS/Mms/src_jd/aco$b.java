import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public final class aco$b
{
  private int a = 0;
  private File b;
  private long c;
  private int d = 1000;
  private long e;
  private long f;
  private long g;
  private long h;
  private long i = -1L;
  private long j = -1L;
  private long k = -1L;
  private long l = 0L;
  private long m = 0L;
  private long n = 0L;
  
  private long c(long paramLong)
  {
    long l1 = 0L;
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    long l2 = localStatFs.getAvailableBlocks() - 32;
    long l3 = localStatFs.getBlockSize();
    if (l2 < 0L) {}
    for (;;)
    {
      if ((e == -1L) || (l1 != f))
      {
        e = paramLong;
        f = l1;
      }
      l1 = f * l3 / d - (paramLong - e) / 1000L;
      if (b == null)
      {
        a = 2;
        return l1;
      }
      b = new File(b.getAbsolutePath());
      l2 = b.length();
      if ((g == -1L) || (l2 != h))
      {
        g = paramLong;
        h = l2;
      }
      paramLong = (c - l2) / d - (paramLong - g) / 1000L - 1L;
      if (l1 < paramLong) {}
      for (int i1 = 2;; i1 = 1)
      {
        a = i1;
        return Math.min(l1, paramLong);
      }
      l1 = l2;
    }
  }
  
  public long a(long paramLong)
  {
    paramLong = paramLong - i - m;
    n = paramLong;
    return paramLong;
  }
  
  public void a()
  {
    a = 0;
    e = -1L;
    g = -1L;
    i = -1L;
    k = -1L;
    j = -1L;
    l = 0L;
    m = 0L;
    n = 0L;
  }
  
  public void a(int paramInt)
  {
    d = (paramInt / 8);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    i = System.currentTimeMillis();
    k = paramInt1;
    j = paramInt2;
    l = 0L;
    m = 0L;
    n = 0L;
  }
  
  public void a(File paramFile, long paramLong)
  {
    b = paramFile;
    c = paramLong;
  }
  
  public int b()
  {
    return a;
  }
  
  public long b(long paramLong)
  {
    paramLong = 1000L * c(paramLong);
    if (k != -1L)
    {
      long l1 = k - n;
      if (paramLong < l1) {}
      for (int i1 = a;; i1 = 3)
      {
        a = i1;
        return Math.min(paramLong, l1);
      }
    }
    return paramLong;
  }
  
  public void c()
  {
    l = System.currentTimeMillis();
    n = a(l);
  }
  
  public long d()
  {
    return n;
  }
  
  public void e()
  {
    m = (m + System.currentTimeMillis() - l);
  }
}

/* Location:
 * Qualified Name:     aco.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */