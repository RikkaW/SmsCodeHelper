package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class SendConf
  extends GenericPdu
{
  public SendConf()
    throws InvalidHeaderValueException
  {
    setMessageType(129);
  }
  
  SendConf(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public byte[] getMessageId()
  {
    return mPduHeaders.getTextString(139);
  }
  
  public int getResponseStatus()
  {
    return mPduHeaders.getOctet(146);
  }
  
  public byte[] getTransactionId()
  {
    return mPduHeaders.getTextString(152);
  }
  
  public void setMessageId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 139);
  }
  
  public void setResponseStatus(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 146);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.SendConf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */