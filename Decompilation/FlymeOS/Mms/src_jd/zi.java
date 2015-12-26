import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.zip.Adler32;

public class zi
  implements Closeable
{
  private RandomAccessFile a;
  private RandomAccessFile b;
  private RandomAccessFile c;
  private FileChannel d;
  private MappedByteBuffer e;
  private int f;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  private RandomAccessFile l;
  private RandomAccessFile m;
  private int n;
  private int o;
  private byte[] p = new byte[32];
  private byte[] q = new byte[20];
  private Adler32 r = new Adler32();
  private zi.a s = new zi.a();
  private int t;
  private int u;
  
  public zi(String paramString, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    a = new RandomAccessFile(paramString + ".idx", "rw");
    b = new RandomAccessFile(paramString + ".0", "rw");
    c = new RandomAccessFile(paramString + ".1", "rw");
    k = paramInt3;
    if ((!paramBoolean) && (d())) {}
    do
    {
      return;
      a(paramInt1, paramInt2);
    } while (d());
    c();
    throw new IOException("unable to load index");
  }
  
  static int a(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] & 0xFF | (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt + 2)] & 0xFF) << 16 | (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24;
  }
  
  private void a(int paramInt)
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    e.position(paramInt);
    paramInt = f * 12;
    while (paramInt > 0)
    {
      int i1 = Math.min(paramInt, 1024);
      e.put(arrayOfByte, 0, i1);
      paramInt -= i1;
    }
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    a.setLength(0L);
    a.setLength(paramInt1 * 12 * 2 + 32);
    a.seek(0L);
    byte[] arrayOfByte = p;
    b(arrayOfByte, 0, -1289277392);
    b(arrayOfByte, 4, paramInt1);
    b(arrayOfByte, 8, paramInt2);
    b(arrayOfByte, 12, 0);
    b(arrayOfByte, 16, 0);
    b(arrayOfByte, 20, 4);
    b(arrayOfByte, 24, k);
    b(arrayOfByte, 28, a(arrayOfByte, 0, 28));
    a.write(arrayOfByte);
    b.setLength(0L);
    c.setLength(0L);
    b.seek(0L);
    c.seek(0L);
    b(arrayOfByte, 0, -1121680112);
    b.write(arrayOfByte, 0, 4);
    c.write(arrayOfByte, 0, 4);
  }
  
  private void a(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = q;
    int i1 = a(paramArrayOfByte);
    a(arrayOfByte, 0, paramLong);
    b(arrayOfByte, 8, i1);
    b(arrayOfByte, 12, j);
    b(arrayOfByte, 16, paramInt);
    l.write(arrayOfByte);
    l.write(paramArrayOfByte, 0, paramInt);
    e.putLong(t, paramLong);
    e.putInt(t + 8, j);
    j += paramInt + 20;
    b(p, 20, j);
  }
  
  static void a(Closeable paramCloseable)
  {
    if (paramCloseable == null) {
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable) {}
  }
  
  public static void a(String paramString)
  {
    b(paramString + ".idx");
    b(paramString + ".0");
    b(paramString + ".1");
  }
  
  static void a(byte[] paramArrayOfByte, int paramInt, long paramLong)
  {
    int i1 = 0;
    while (i1 < 8)
    {
      paramArrayOfByte[(paramInt + i1)] = ((byte)(int)(0xFF & paramLong));
      paramLong >>= 8;
      i1 += 1;
    }
  }
  
  private boolean a(long paramLong, int paramInt)
  {
    int i2 = (int)(paramLong % f);
    int i1 = i2;
    if (i2 < 0) {
      i1 = i2 + f;
    }
    int i3 = i1;
    for (;;)
    {
      i2 = i3 * 12 + paramInt;
      long l1 = e.getLong(i2);
      int i4 = e.getInt(i2 + 8);
      if (i4 == 0)
      {
        t = i2;
        return false;
      }
      if (l1 == paramLong)
      {
        t = i2;
        u = i4;
        return true;
      }
      i3 += 1;
      i2 = i3;
      if (i3 >= f) {
        i2 = 0;
      }
      i3 = i2;
      if (i2 == i1)
      {
        Log.w("BlobCache", "corrupted index: clear the slot.");
        e.putInt(i2 * 12 + paramInt + 8, 0);
        i3 = i2;
      }
    }
  }
  
  private boolean a(RandomAccessFile paramRandomAccessFile, int paramInt, zi.a parama)
  {
    byte[] arrayOfByte = q;
    long l1 = paramRandomAccessFile.getFilePointer();
    long l2 = paramInt;
    try
    {
      paramRandomAccessFile.seek(l2);
      if (paramRandomAccessFile.read(arrayOfByte) != 20)
      {
        Log.w("BlobCache", "cannot read blob header");
        return false;
      }
      l2 = b(arrayOfByte, 0);
      if (l2 != a)
      {
        Log.w("BlobCache", "blob key does not match: " + l2);
        return false;
      }
      int i1 = a(arrayOfByte, 8);
      int i2 = a(arrayOfByte, 12);
      if (i2 != paramInt)
      {
        Log.w("BlobCache", "blob offset does not match: " + i2);
        return false;
      }
      i2 = a(arrayOfByte, 16);
      if ((i2 < 0) || (i2 > g - paramInt - 20))
      {
        Log.w("BlobCache", "invalid blob length: " + i2);
        return false;
      }
      if ((b == null) || (b.length < i2)) {
        b = new byte[i2];
      }
      arrayOfByte = b;
      c = i2;
      if (paramRandomAccessFile.read(arrayOfByte, 0, i2) != i2)
      {
        Log.w("BlobCache", "cannot read blob data");
        return false;
      }
      if (a(arrayOfByte, 0, i2) != i1)
      {
        Log.w("BlobCache", "blob checksum does not match: " + i1);
        return false;
      }
      return true;
    }
    catch (Throwable parama)
    {
      Log.e("BlobCache", "getBlob failed.", parama);
      return false;
    }
    finally
    {
      paramRandomAccessFile.seek(l1);
    }
  }
  
  static long b(byte[] paramArrayOfByte, int paramInt)
  {
    long l1 = paramArrayOfByte[(paramInt + 7)] & 0xFF;
    int i1 = 6;
    while (i1 >= 0)
    {
      l1 = l1 << 8 | paramArrayOfByte[(paramInt + i1)] & 0xFF;
      i1 -= 1;
    }
    return l1;
  }
  
  private static void b(String paramString)
  {
    try
    {
      new File(paramString).delete();
      return;
    }
    catch (Throwable paramString) {}
  }
  
  static void b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i2 = 0;
    int i1 = paramInt2;
    paramInt2 = i2;
    while (paramInt2 < 4)
    {
      paramArrayOfByte[(paramInt1 + paramInt2)] = ((byte)(i1 & 0xFF));
      i1 >>= 8;
      paramInt2 += 1;
    }
  }
  
  private void c()
  {
    a(d);
    a(a);
    a(b);
    a(c);
  }
  
  private boolean d()
  {
    try
    {
      a.seek(0L);
      b.seek(0L);
      c.seek(0L);
      byte[] arrayOfByte = p;
      if (a.read(arrayOfByte) != 32)
      {
        Log.w("BlobCache", "cannot read header");
        return false;
      }
      if (a(arrayOfByte, 0) != -1289277392)
      {
        Log.w("BlobCache", "cannot read header magic");
        return false;
      }
      if (a(arrayOfByte, 24) != k)
      {
        Log.w("BlobCache", "version mismatch");
        return false;
      }
      f = a(arrayOfByte, 4);
      g = a(arrayOfByte, 8);
      h = a(arrayOfByte, 12);
      i = a(arrayOfByte, 16);
      j = a(arrayOfByte, 20);
      int i1 = a(arrayOfByte, 28);
      if (a(arrayOfByte, 0, 28) != i1)
      {
        Log.w("BlobCache", "header checksum does not match");
        return false;
      }
      if (f <= 0)
      {
        Log.w("BlobCache", "invalid max entries");
        return false;
      }
      if (g <= 0)
      {
        Log.w("BlobCache", "invalid max bytes");
        return false;
      }
      if ((h != 0) && (h != 1))
      {
        Log.w("BlobCache", "invalid active region");
        return false;
      }
      if ((i < 0) || (i > f))
      {
        Log.w("BlobCache", "invalid active entries");
        return false;
      }
      if ((j < 4) || (j > g))
      {
        Log.w("BlobCache", "invalid active bytes");
        return false;
      }
      if (a.length() != f * 12 * 2 + 32)
      {
        Log.w("BlobCache", "invalid index file length");
        return false;
      }
      arrayOfByte = new byte[4];
      if (b.read(arrayOfByte) != 4)
      {
        Log.w("BlobCache", "cannot read data file magic");
        return false;
      }
      if (a(arrayOfByte, 0) != -1121680112)
      {
        Log.w("BlobCache", "invalid data file magic");
        return false;
      }
      if (c.read(arrayOfByte) != 4)
      {
        Log.w("BlobCache", "cannot read data file magic");
        return false;
      }
      if (a(arrayOfByte, 0) != -1121680112)
      {
        Log.w("BlobCache", "invalid data file magic");
        return false;
      }
      d = a.getChannel();
      e = d.map(FileChannel.MapMode.READ_WRITE, 0L, a.length());
      e.order(ByteOrder.LITTLE_ENDIAN);
      e();
      return true;
    }
    catch (IOException localIOException)
    {
      Log.e("BlobCache", "loadIndex failed.", localIOException);
    }
    return false;
  }
  
  private void e()
  {
    if (h == 0)
    {
      localRandomAccessFile = b;
      l = localRandomAccessFile;
      if (h != 1) {
        break label103;
      }
    }
    label103:
    for (RandomAccessFile localRandomAccessFile = b;; localRandomAccessFile = c)
    {
      m = localRandomAccessFile;
      l.setLength(j);
      l.seek(j);
      n = 32;
      o = 32;
      if (h != 0) {
        break label111;
      }
      o += f * 12;
      return;
      localRandomAccessFile = c;
      break;
    }
    label111:
    n += f * 12;
  }
  
  private void f()
  {
    h = (1 - h);
    i = 0;
    j = 4;
    b(p, 12, h);
    b(p, 16, i);
    b(p, 20, j);
    g();
    e();
    a(n);
    a();
  }
  
  private void g()
  {
    b(p, 28, a(p, 0, 28));
    e.position(0);
    e.put(p);
  }
  
  int a(byte[] paramArrayOfByte)
  {
    r.reset();
    r.update(paramArrayOfByte);
    return (int)r.getValue();
  }
  
  int a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    r.reset();
    r.update(paramArrayOfByte, paramInt1, paramInt2);
    return (int)r.getValue();
  }
  
  public void a()
  {
    try
    {
      e.force();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("BlobCache", "sync index failed", localThrowable);
    }
  }
  
  public void a(long paramLong, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length + 24 > g) {
      throw new RuntimeException("blob is too large!");
    }
    if ((j + 20 + paramArrayOfByte.length > g) || (i * 2 >= f)) {
      f();
    }
    if (!a(paramLong, n))
    {
      i += 1;
      b(p, 16, i);
    }
    a(paramLong, paramArrayOfByte, paramArrayOfByte.length);
    g();
  }
  
  public boolean a(zi.a parama)
  {
    if ((a(a, n)) && (a(l, u, parama))) {}
    int i1;
    do
    {
      return true;
      i1 = t;
      if ((!a(a, o)) || (!a(m, u, parama))) {
        break;
      }
    } while ((j + 20 + c > g) || (i * 2 >= f));
    t = i1;
    try
    {
      a(a, b, c);
      i += 1;
      b(p, 16, i);
      g();
      return true;
    }
    catch (Throwable parama)
    {
      Log.e("BlobCache", "cannot copy over");
      return true;
    }
    return false;
  }
  
  public byte[] a(long paramLong)
  {
    byte[] arrayOfByte = null;
    s.a = paramLong;
    s.b = null;
    if (a(s)) {
      arrayOfByte = s.b;
    }
    return arrayOfByte;
  }
  
  public void b()
  {
    a();
    try
    {
      b.getFD().sync();
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        try
        {
          c.getFD().sync();
          return;
        }
        catch (Throwable localThrowable2)
        {
          Log.w("BlobCache", "sync data file 1 failed", localThrowable2);
        }
        localThrowable1 = localThrowable1;
        Log.w("BlobCache", "sync data file 0 failed", localThrowable1);
      }
    }
  }
  
  public void close()
  {
    b();
    c();
  }
  
  public static class a
  {
    public long a;
    public byte[] b;
    public int c;
  }
}

/* Location:
 * Qualified Name:     zi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */