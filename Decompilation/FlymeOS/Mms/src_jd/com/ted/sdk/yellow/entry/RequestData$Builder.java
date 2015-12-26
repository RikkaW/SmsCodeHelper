package com.ted.sdk.yellow.entry;

import anu;
import com.ted.sdk.yellow.CtaDataBus;

public class RequestData$Builder
{
  private int dataType = 0;
  private int duration = 0;
  private String msgBody = "";
  private String number;
  private int operationType = 1;
  
  public RequestData build()
  {
    return new RequestData(this, null);
  }
  
  public Builder setAccessNetwork(boolean paramBoolean)
  {
    CtaDataBus.setNetworkAccessible(paramBoolean);
    return this;
  }
  
  public Builder setDataType(int paramInt)
  {
    dataType = paramInt;
    return this;
  }
  
  public Builder setDuration(int paramInt)
  {
    duration = paramInt;
    return this;
  }
  
  public Builder setMsgBody(String paramString)
  {
    msgBody = paramString;
    return this;
  }
  
  public Builder setNumber(String paramString)
  {
    number = anu.b(paramString);
    return this;
  }
  
  public Builder setOperationType(int paramInt)
  {
    operationType = paramInt;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.RequestData.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */