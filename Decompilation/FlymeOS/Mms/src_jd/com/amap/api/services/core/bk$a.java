package com.amap.api.services.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class bk$a
{
  private final bk.c b;
  private final boolean[] c;
  private boolean d;
  private boolean e;
  
  private bk$a(bk parambk, bk.c paramc)
  {
    b = paramc;
    if (bk.c.d(paramc)) {}
    for (parambk = null;; parambk = new boolean[bk.e(parambk)])
    {
      c = parambk;
      return;
    }
  }
  
  public OutputStream a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= bk.e(a))) {
      throw new IllegalArgumentException("Expected index " + paramInt + " to " + "be greater than 0 and less than the maximum value count " + "of " + bk.e(a));
    }
    synchronized (a)
    {
      if (bk.c.a(b) != this) {
        throw new IllegalStateException();
      }
    }
    if (!bk.c.d(b)) {
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
        bk.f(a).mkdirs();
        try
        {
          FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          OutputStream localOutputStream = bk.d();
          return localOutputStream;
        }
      }
    }
  }
  
  public void a()
  {
    if (d)
    {
      bk.a(a, this, false);
      a.c(bk.c.c(b));
    }
    for (;;)
    {
      e = true;
      return;
      bk.a(a, this, true);
    }
  }
  
  public void b()
  {
    bk.a(a, this, false);
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
        bk.a.a(bk.a.this, true);
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
        bk.a.a(bk.a.this, true);
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
        bk.a.a(bk.a.this, true);
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
        bk.a.a(bk.a.this, true);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bk.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */