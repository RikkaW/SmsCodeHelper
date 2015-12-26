package com.xiaomi.mms.mx.cache;

import java.io.IOException;

public final class DiskLruCache$Editor
{
  private final DiskLruCache.Entry entry;
  
  private DiskLruCache$Editor(DiskLruCache paramDiskLruCache, DiskLruCache.Entry paramEntry)
  {
    entry = paramEntry;
  }
  
  public void abort()
    throws IOException
  {
    DiskLruCache.access$1900(this$0, this, false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.DiskLruCache.Editor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */