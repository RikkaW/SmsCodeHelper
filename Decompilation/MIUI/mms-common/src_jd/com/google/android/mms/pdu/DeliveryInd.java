package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class DeliveryInd
  extends GenericPdu
{
  public DeliveryInd()
    throws InvalidHeaderValueException
  {
    setMessageType(134);
  }
  
  DeliveryInd(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public long getDate()
  {
    return mPduHeaders.getLongInteger(133);
  }
  
  public byte[] getMessageId()
  {
    return mPduHeaders.getTextString(139);
  }
  
  public int getStatus()
  {
    return mPduHeaders.getOctet(149);
  }
  
  public EncodedStringValue[] getTo()
  {
    return mPduHeaders.getEncodedStringValues(151);
  }
  
  public void setDate(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 133);
  }
  
  public void setMessageId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 139);
  }
  
  public void setStatus(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 149);
  }
  
  public void setTo(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValues(paramArrayOfEncodedStringValue, 151);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.DeliveryInd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */