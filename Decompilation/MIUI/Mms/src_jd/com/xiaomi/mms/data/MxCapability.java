package com.xiaomi.mms.data;

public class MxCapability
{
  private long mCapability = -2147483648L;
  
  public MxCapability(long paramLong)
  {
    mCapability = paramLong;
  }
  
  public boolean allowAudio()
  {
    return (mCapability & 0x200000000) > 0L;
  }
  
  public boolean allowMms()
  {
    return (mCapability & 0x2) > 0L;
  }
  
  public boolean allowMxV2()
  {
    return (mCapability & 0x400000000) > 0L;
  }
  
  public boolean allowSms()
  {
    return (mCapability & 1L) > 0L;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.MxCapability
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */