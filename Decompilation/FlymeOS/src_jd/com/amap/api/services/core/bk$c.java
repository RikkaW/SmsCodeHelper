package com.amap.api.services.core;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class bk$c
{
  private final String b;
  private final long[] c;
  private boolean d;
  private bk.a e;
  private long f;
  
  private bk$c(bk parambk, String paramString)
  {
    b = paramString;
    c = new long[bk.e(parambk)];
  }
  
  private void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length != bk.e(a)) {
      throw b(paramArrayOfString);
    }
    int i = 0;
    try
    {
      while (i < paramArrayOfString.length)
      {
        c[i] = Long.parseLong(paramArrayOfString[i]);
        i += 1;
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw b(paramArrayOfString);
    }
  }
  
  private IOException b(String[] paramArrayOfString)
  {
    throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
  }
  
  public File a(int paramInt)
  {
    return new File(bk.f(a), b + "." + paramInt);
  }
  
  public String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    long[] arrayOfLong = c;
    int j = arrayOfLong.length;
    int i = 0;
    while (i < j)
    {
      long l = arrayOfLong[i];
      localStringBuilder.append(' ').append(l);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public File b(int paramInt)
  {
    return new File(bk.f(a), b + "." + paramInt + ".tmp");
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bk.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */