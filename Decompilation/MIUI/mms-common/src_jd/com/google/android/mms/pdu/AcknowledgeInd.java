package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class AcknowledgeInd
  extends GenericPdu
{
  public AcknowledgeInd(int paramInt, byte[] paramArrayOfByte)
    throws InvalidHeaderValueException
  {
    setMessageType(133);
    setMmsVersion(paramInt);
    setTransactionId(paramArrayOfByte);
  }
  
  AcknowledgeInd(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public int getReportAllowed()
  {
    return mPduHeaders.getOctet(145);
  }
  
  public byte[] getTransactionId()
  {
    return mPduHeaders.getTextString(152);
  }
  
  public void setReportAllowed(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 145);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.AcknowledgeInd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */