import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class ahl$a
{
  private final ahl.b b;
  private final boolean[] c;
  private boolean d;
  private boolean e;
  
  private ahl$a(ahl paramahl, ahl.b paramb)
  {
    b = paramb;
    if (ahl.b.d(paramb)) {}
    for (paramahl = null;; paramahl = new boolean[ahl.e(paramahl)])
    {
      c = paramahl;
      return;
    }
  }
  
  public OutputStream a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= ahl.e(a))) {
      throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + ahl.e(a));
    }
    synchronized (a)
    {
      if (ahl.b.a(b) != this) {
        throw new IllegalStateException();
      }
    }
    if (!ahl.b.d(b)) {
      c[paramInt] = true;
    }
    File localFile = b.b(paramInt);
    try
    {
      Object localObject2 = new FileOutputStream(localFile);
      localObject2 = new ahl.a.a((OutputStream)localObject2, null);
      return (OutputStream)localObject2;
    }
    catch (FileNotFoundException localFileNotFoundException1)
    {
      for (;;)
      {
        ahl.f(a).mkdirs();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          OutputStream localOutputStream = ahl.b();
          return localOutputStream;
        }
      }
    }
  }
  
  public void a()
  {
    if (d)
    {
      ahl.a(a, this, false);
      a.c(ahl.b.c(b));
    }
    for (;;)
    {
      e = true;
      return;
      ahl.a(a, this, true);
    }
  }
  
  public void b()
  {
    ahl.a(a, this, false);
  }
  
  class a
    extends FilterOutputStream
  {
    private a(OutputStream paramOutputStream)
    {
      super();
    }
    
    public void close()
    {
      try
      {
        out.close();
        return;
      }
      catch (IOException localIOException)
      {
        ahl.a.a(ahl.a.this, true);
      }
    }
    
    public void flush()
    {
      try
      {
        out.flush();
        return;
      }
      catch (IOException localIOException)
      {
        ahl.a.a(ahl.a.this, true);
      }
    }
    
    public void write(int paramInt)
    {
      try
      {
        out.write(paramInt);
        return;
      }
      catch (IOException localIOException)
      {
        ahl.a.a(ahl.a.this, true);
      }
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      try
      {
        out.write(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      catch (IOException paramArrayOfByte)
      {
        ahl.a.a(ahl.a.this, true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     ahl.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */