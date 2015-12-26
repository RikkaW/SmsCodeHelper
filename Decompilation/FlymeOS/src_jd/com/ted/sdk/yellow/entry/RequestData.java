package com.ted.sdk.yellow.entry;

import anu;
import com.ted.sdk.yellow.CtaDataBus;

public class RequestData
{
  public static final int DATA_TYPE_PHONE = 0;
  public static final int DATA_TYPE_SMS = 1;
  public static final int OP_TYPE_IN_CALL = 1;
  public static final int OP_TYPE_IN_SMS = 1;
  public static final int OP_TYPE_MISSED_CALL = 3;
  public static final int OP_TYPE_OUT_CALL = 2;
  public static final int OP_TYPE_OUT_SMS = 2;
  private int dataType;
  private int duration;
  private String msgBody;
  private String number;
  private int operationType;
  
  private RequestData(Builder paramBuilder)
  {
    number = number;
    dataType = dataType;
    operationType = operationType;
    duration = duration;
    msgBody = msgBody;
  }
  
  public int getDataType()
  {
    return dataType;
  }
  
  public int getDuration()
  {
    return duration;
  }
  
  public String getMsgBody()
  {
    return msgBody;
  }
  
  public String getNumber()
  {
    return number;
  }
  
  public int getOperationType()
  {
    return operationType;
  }
  
  public boolean isAccessNetwork()
  {
    return CtaDataBus.a;
  }
  
  public static class Builder
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
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.entry.RequestData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */