package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class NotifyRespInd
  extends GenericPdu
{
  public NotifyRespInd(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws InvalidHeaderValueException
  {
    setMessageType(131);
    setMmsVersion(paramInt1);
    setTransactionId(paramArrayOfByte);
    setStatus(paramInt2);
  }
  
  NotifyRespInd(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public int getReportAllowed()
  {
    return mPduHeaders.getOctet(145);
  }
  
  public int getStatus()
  {
    return mPduHeaders.getOctet(149);
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
  
  public void setStatus(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 149);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.NotifyRespInd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */