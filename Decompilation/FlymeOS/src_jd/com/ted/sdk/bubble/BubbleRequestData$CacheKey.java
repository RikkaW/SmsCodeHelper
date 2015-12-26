package com.ted.sdk.bubble;

public class BubbleRequestData$CacheKey
{
  public long msgId;
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (CacheKey)paramObject;
    } while (msgId == msgId);
    return false;
  }
  
  public int hashCode()
  {
    return (int)(msgId ^ msgId >>> 32) + 31;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.bubble.BubbleRequestData.CacheKey
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */