package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class MultimediaMessagePdu
  extends GenericPdu
{
  private PduBody mMessageBody;
  
  public MultimediaMessagePdu() {}
  
  MultimediaMessagePdu(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  public MultimediaMessagePdu(PduHeaders paramPduHeaders, PduBody paramPduBody)
  {
    super(paramPduHeaders);
    mMessageBody = paramPduBody;
  }
  
  public void addTo(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.appendEncodedStringValue(paramEncodedStringValue, 151);
  }
  
  public PduBody getBody()
  {
    return mMessageBody;
  }
  
  public long getDate()
  {
    return mPduHeaders.getLongInteger(133);
  }
  
  public int getPriority()
  {
    return mPduHeaders.getOctet(143);
  }
  
  public EncodedStringValue getSubject()
  {
    return mPduHeaders.getEncodedStringValue(150);
  }
  
  public EncodedStringValue[] getTo()
  {
    return mPduHeaders.getEncodedStringValues(151);
  }
  
  public void setBody(PduBody paramPduBody)
  {
    mMessageBody = paramPduBody;
  }
  
  public void setDate(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 133);
  }
  
  public void setPriority(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 143);
  }
  
  public void setSubject(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 150);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.MultimediaMessagePdu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */