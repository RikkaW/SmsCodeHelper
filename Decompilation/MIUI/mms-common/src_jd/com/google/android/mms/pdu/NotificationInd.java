package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class NotificationInd
  extends GenericPdu
{
  public NotificationInd()
    throws InvalidHeaderValueException
  {
    setMessageType(130);
  }
  
  NotificationInd(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public int getContentClass()
  {
    return mPduHeaders.getOctet(186);
  }
  
  public byte[] getContentLocation()
  {
    return mPduHeaders.getTextString(131);
  }
  
  public int getDeliveryReport()
  {
    return mPduHeaders.getOctet(134);
  }
  
  public long getExpiry()
  {
    return mPduHeaders.getLongInteger(136);
  }
  
  public EncodedStringValue getFrom()
  {
    return mPduHeaders.getEncodedStringValue(137);
  }
  
  public byte[] getMessageClass()
  {
    return mPduHeaders.getTextString(138);
  }
  
  public long getMessageSize()
  {
    return mPduHeaders.getLongInteger(142);
  }
  
  public EncodedStringValue getSubject()
  {
    return mPduHeaders.getEncodedStringValue(150);
  }
  
  public byte[] getTransactionId()
  {
    return mPduHeaders.getTextString(152);
  }
  
  public void setContentClass(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 186);
  }
  
  public void setContentLocation(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 131);
  }
  
  public void setDeliveryReport(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 134);
  }
  
  public void setExpiry(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 136);
  }
  
  public void setFrom(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 137);
  }
  
  public void setMessageClass(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 138);
  }
  
  public void setMessageSize(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 142);
  }
  
  public void setSubject(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 150);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.NotificationInd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */