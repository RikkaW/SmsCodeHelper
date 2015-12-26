package com.ted.sdk.bubble;

import anu;

public class BubbleRequestData$Builder
{
  private String body;
  private long date;
  private BubbleRequestData.CacheKey key;
  private String number;
  private int slotId;
  private int srvMsgType;
  
  public BubbleRequestData build()
  {
    return new BubbleRequestData(this, null);
  }
  
  public Builder setBody(String paramString)
  {
    body = paramString;
    return this;
  }
  
  public Builder setCacheKey(BubbleRequestData.CacheKey paramCacheKey)
  {
    key = paramCacheKey;
    return this;
  }
  
  public Builder setDate(long paramLong)
  {
    date = paramLong;
    return this;
  }
  
  public Builder setNumber(String paramString)
  {
    number = anu.b(paramString);
    return this;
  }
  
  public Builder setSlotId(int paramInt)
  {
    slotId = paramInt;
    return this;
  }
  
  public Builder setSrvMsgType(int paramInt)
  {
    srvMsgType = paramInt;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.bubble.BubbleRequestData.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */