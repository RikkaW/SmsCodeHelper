package com.ted.android.core;

public class SmsEntityLoader$SmsInfo
{
  public String body;
  public SmsEntityLoader.SmsEntityLoaderCallback callback;
  public long date;
  public Long msgId;
  public String number;
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof SmsInfo))
    {
      paramObject = (SmsInfo)paramObject;
      return msgId.equals(msgId);
    }
    return false;
  }
  
  public int hashCode()
  {
    return msgId.intValue();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.core.SmsEntityLoader.SmsInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */