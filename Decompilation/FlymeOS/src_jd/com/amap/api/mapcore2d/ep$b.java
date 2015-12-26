package com.amap.api.mapcore2d;

import java.io.Closeable;
import java.io.InputStream;

public final class ep$b
  implements Closeable
{
  private final String b;
  private final long c;
  private final InputStream[] d;
  private final long[] e;
  
  private ep$b(ep paramep, String paramString, long paramLong, InputStream[] paramArrayOfInputStream, long[] paramArrayOfLong)
  {
    b = paramString;
    c = paramLong;
    d = paramArrayOfInputStream;
    e = paramArrayOfLong;
  }
  
  public InputStream a(int paramInt)
  {
    return d[paramInt];
  }
  
  public void close()
  {
    InputStream[] arrayOfInputStream = d;
    int j = arrayOfInputStream.length;
    int i = 0;
    while (i < j)
    {
      eu.a(arrayOfInputStream[i]);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ep.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */