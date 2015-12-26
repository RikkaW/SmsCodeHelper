package com.xiaomi.mms.mx.cache;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class DiskLruCache$Entry
{
  private DiskLruCache.Editor currentEditor;
  private final String key;
  private final long[] lengths;
  private boolean readable;
  private long sequenceNumber;
  
  private DiskLruCache$Entry(DiskLruCache paramDiskLruCache, String paramString)
  {
    key = paramString;
    lengths = new long[DiskLruCache.access$2100(paramDiskLruCache)];
  }
  
  private IOException invalidLengths(String[] paramArrayOfString)
    throws IOException
  {
    throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
  }
  
  private void setLengths(String[] paramArrayOfString)
    throws IOException
  {
    if (paramArrayOfString.length != DiskLruCache.access$2100(this$0)) {
      throw invalidLengths(paramArrayOfString);
    }
    int i = 0;
    try
    {
      while (i < paramArrayOfString.length)
      {
        lengths[i] = Long.parseLong(paramArrayOfString[i]);
        i += 1;
      }
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw invalidLengths(paramArrayOfString);
    }
  }
  
  public File getCleanFile(int paramInt)
  {
    return new File(DiskLruCache.access$2200(this$0), key + "." + paramInt);
  }
  
  public File getDirtyFile(int paramInt)
  {
    return new File(DiskLruCache.access$2200(this$0), key + "." + paramInt + ".tmp");
  }
  
  public String getLengths()
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    long[] arrayOfLong = lengths;
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
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.DiskLruCache.Entry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */