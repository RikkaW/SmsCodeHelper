package com.xiaomi.mms.data;

public class MxIdCache$MxIdCacheItem
{
  protected long capability = -2147483648L;
  protected long expire = System.currentTimeMillis();
  protected String mid;
  protected boolean transientState = true;
  
  private MxIdCache$MxIdCacheItem(String paramString)
  {
    mid = paramString;
  }
  
  public boolean allowMms()
  {
    return (mid != null) && ((capability & 0x2) > 0L);
  }
  
  public boolean allowMxV2()
  {
    return (mid != null) && ((capability & 0x400000000) > 0L);
  }
  
  public boolean allowSms()
  {
    return (mid != null) && ((capability & 1L) > 0L);
  }
  
  public long getCapability()
  {
    return capability;
  }
  
  public String getMId()
  {
    return mid;
  }
  
  public boolean isExpired()
  {
    return expire <= System.currentTimeMillis();
  }
  
  public String toString()
  {
    return "MxIdCacheItem{mid='" + mid + '\'' + ", expire=" + expire + ", capability=" + capability + '}';
  }
  
  protected void updateExpire()
  {
    long l2 = System.currentTimeMillis();
    if (mid != null) {}
    for (long l1 = 300000L;; l1 = 120000L)
    {
      expire = (l1 + l2);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.MxIdCache.MxIdCacheItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */