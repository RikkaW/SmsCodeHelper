package com.ted.sdk.bubble;

import anu;

public class BubbleRequestData
{
  private String mBody;
  private long mDate;
  private CacheKey mKey;
  private String mNumber;
  private int mSlotId;
  private int mSrvMsgType;
  
  private BubbleRequestData(Builder paramBuilder)
  {
    mNumber = number;
    mBody = body;
    mKey = key;
    mSlotId = slotId;
    mSrvMsgType = srvMsgType;
    mDate = date;
  }
  
  public String getBody()
  {
    return mBody;
  }
  
  public CacheKey getCacheKey()
  {
    return mKey;
  }
  
  public long getDate()
  {
    return mDate;
  }
  
  public String getNumber()
  {
    return mNumber;
  }
  
  public int getSlotId()
  {
    return mSlotId;
  }
  
  public int getSrvMsgType()
  {
    return mSrvMsgType;
  }
  
  public static class Builder
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
  
  public static class CacheKey
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
}

/* Location:
 * Qualified Name:     com.ted.sdk.bubble.BubbleRequestData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */