package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class ReadOrigInd
  extends GenericPdu
{
  public ReadOrigInd()
    throws InvalidHeaderValueException
  {
    setMessageType(136);
  }
  
  ReadOrigInd(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public long getDate()
  {
    return mPduHeaders.getLongInteger(133);
  }
  
  public EncodedStringValue getFrom()
  {
    return mPduHeaders.getEncodedStringValue(137);
  }
  
  public byte[] getMessageId()
  {
    return mPduHeaders.getTextString(139);
  }
  
  public int getReadStatus()
  {
    return mPduHeaders.getOctet(155);
  }
  
  public EncodedStringValue[] getTo()
  {
    return mPduHeaders.getEncodedStringValues(151);
  }
  
  public void setDate(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 133);
  }
  
  public void setFrom(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 137);
  }
  
  public void setMessageId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 139);
  }
  
  public void setReadStatus(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 155);
  }
  
  public void setTo(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValues(paramArrayOfEncodedStringValue, 151);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.ReadOrigInd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */