package com.amap.api.services.core;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class bk$a$a
  extends FilterOutputStream
{
  private bk$a$a(bk.a parama, OutputStream paramOutputStream)
  {
    super(paramOutputStream);
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
      bk.a.a(a, true);
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
      bk.a.a(a, true);
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
      bk.a.a(a, true);
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
      bk.a.a(a, true);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bk.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */