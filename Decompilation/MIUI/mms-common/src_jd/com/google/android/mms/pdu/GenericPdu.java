package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class GenericPdu
{
  PduHeaders mPduHeaders = null;
  
  public GenericPdu()
  {
    mPduHeaders = new PduHeaders();
  }
  
  GenericPdu(PduHeaders paramPduHeaders)
  {
    mPduHeaders = paramPduHeaders;
  }
  
  public EncodedStringValue getFrom()
  {
    return mPduHeaders.getEncodedStringValue(137);
  }
  
  public int getMessageType()
  {
    return mPduHeaders.getOctet(140);
  }
  
  public int getMmsVersion()
  {
    return mPduHeaders.getOctet(141);
  }
  
  PduHeaders getPduHeaders()
  {
    return mPduHeaders;
  }
  
  public void setFrom(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 137);
  }
  
  public void setMessageType(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 140);
  }
  
  public void setMmsVersion(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 141);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.GenericPdu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */