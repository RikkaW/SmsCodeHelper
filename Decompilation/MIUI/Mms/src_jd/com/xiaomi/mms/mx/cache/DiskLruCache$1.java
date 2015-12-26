package com.xiaomi.mms.mx.cache;

import java.util.concurrent.Callable;

class DiskLruCache$1
  implements Callable<Void>
{
  DiskLruCache$1(DiskLruCache paramDiskLruCache) {}
  
  public Void call()
    throws Exception
  {
    synchronized (this$0)
    {
      if (DiskLruCache.access$000(this$0) == null) {
        return null;
      }
      DiskLruCache.access$100(this$0);
      if (DiskLruCache.access$200(this$0))
      {
        DiskLruCache.access$300(this$0);
        DiskLruCache.access$402(this$0, 0);
      }
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.DiskLruCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */