package com.google.android.mms.pdu;

import com.google.android.mms.InvalidHeaderValueException;

public class RetrieveConf
  extends MultimediaMessagePdu
{
  public RetrieveConf()
    throws InvalidHeaderValueException
  {
    setMessageType(132);
  }
  
  RetrieveConf(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  RetrieveConf(PduHeaders paramPduHeaders, PduBody paramPduBody)
  {
    super(paramPduHeaders, paramPduBody);
  }
  
  public void addCc(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.appendEncodedStringValue(paramEncodedStringValue, 130);
  }
  
  public EncodedStringValue[] getCc()
  {
    return mPduHeaders.getEncodedStringValues(130);
  }
  
  public byte[] getContentType()
  {
    return mPduHeaders.getTextString(132);
  }
  
  public int getDeliveryReport()
  {
    return mPduHeaders.getOctet(134);
  }
  
  public EncodedStringValue getFrom()
  {
    return mPduHeaders.getEncodedStringValue(137);
  }
  
  public byte[] getMessageClass()
  {
    return mPduHeaders.getTextString(138);
  }
  
  public byte[] getMessageId()
  {
    return mPduHeaders.getTextString(139);
  }
  
  public int getReadReport()
  {
    return mPduHeaders.getOctet(144);
  }
  
  public int getRetrieveStatus()
  {
    return mPduHeaders.getOctet(153);
  }
  
  public EncodedStringValue getRetrieveText()
  {
    return mPduHeaders.getEncodedStringValue(154);
  }
  
  public byte[] getTransactionId()
  {
    return mPduHeaders.getTextString(152);
  }
  
  public void setContentType(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 132);
  }
  
  public void setDeliveryReport(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 134);
  }
  
  public void setFrom(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 137);
  }
  
  public void setMessageClass(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 138);
  }
  
  public void setMessageId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 139);
  }
  
  public void setReadReport(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 144);
  }
  
  public void setRetrieveStatus(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 153);
  }
  
  public void setRetrieveText(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValue(paramEncodedStringValue, 154);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.RetrieveConf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */