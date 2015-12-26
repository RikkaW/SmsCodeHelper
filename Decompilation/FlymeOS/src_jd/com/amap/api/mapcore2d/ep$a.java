package com.amap.api.mapcore2d;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class ep$a
{
  private final ep.c b;
  private final boolean[] c;
  private boolean d;
  private boolean e;
  
  private ep$a(ep paramep, ep.c paramc)
  {
    b = paramc;
    if (ep.c.d(paramc)) {}
    for (paramep = null;; paramep = new boolean[ep.e(paramep)])
    {
      c = paramep;
      return;
    }
  }
  
  public OutputStream a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= ep.e(a))) {
      throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + ep.e(a));
    }
    synchronized (a)
    {
      if (ep.c.a(b) != this) {
        throw new IllegalStateException();
      }
    }
    if (!ep.c.d(b)) {
      c[paramInt] = true;
    }
    File localFile = b.b(paramInt);
    try
    {
      Object localObject2 = new FileOutputStream(localFile);
      localObject2 = new a((OutputStream)localObject2, null);
      return (OutputStream)localObject2;
    }
    catch (FileNotFoundException localFileNotFoundException1)
    {
      for (;;)
      {
        ep.f(a).mkdirs();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          OutputStream localOutputStream = ep.d();
          return localOutputStream;
        }
      }
    }
  }
  
  public void a()
  {
    if (d)
    {
      ep.a(a, this, false);
      a.c(ep.c.c(b));
    }
    for (;;)
    {
      e = true;
      return;
      ep.a(a, this, true);
    }
  }
  
  public void b()
  {
    ep.a(a, this, false);
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
        ep.a.a(ep.a.this, true);
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
        ep.a.a(ep.a.this, true);
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
        ep.a.a(ep.a.this, true);
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
        ep.a.a(ep.a.this, true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ep.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */