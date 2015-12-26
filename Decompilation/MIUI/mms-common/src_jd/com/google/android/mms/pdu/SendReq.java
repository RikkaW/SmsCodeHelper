package com.google.android.mms.pdu;

import android.util.Log;
import com.google.android.mms.InvalidHeaderValueException;

public class SendReq
  extends MultimediaMessagePdu
{
  private static final String TAG = "SendReq";
  
  public SendReq()
  {
    try
    {
      setMessageType(128);
      setMmsVersion(18);
      setContentType("application/vnd.wap.multipart.related".getBytes());
      setFrom(new EncodedStringValue("insert-address-token".getBytes()));
      setTransactionId(generateTransactionId());
      return;
    }
    catch (InvalidHeaderValueException localInvalidHeaderValueException)
    {
      Log.e("SendReq", "Unexpected InvalidHeaderValueException.", localInvalidHeaderValueException);
      throw new RuntimeException(localInvalidHeaderValueException);
    }
  }
  
  SendReq(PduHeaders paramPduHeaders)
  {
    super(paramPduHeaders);
  }
  
  SendReq(PduHeaders paramPduHeaders, PduBody paramPduBody)
  {
    super(paramPduHeaders, paramPduBody);
  }
  
  public SendReq(byte[] paramArrayOfByte1, EncodedStringValue paramEncodedStringValue, int paramInt, byte[] paramArrayOfByte2)
    throws InvalidHeaderValueException
  {
    setMessageType(128);
    setContentType(paramArrayOfByte1);
    setFrom(paramEncodedStringValue);
    setMmsVersion(paramInt);
    setTransactionId(paramArrayOfByte2);
  }
  
  private byte[] generateTransactionId()
  {
    return ("T" + Long.toHexString(System.currentTimeMillis())).getBytes();
  }
  
  public void addBcc(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.appendEncodedStringValue(paramEncodedStringValue, 129);
  }
  
  public void addCc(EncodedStringValue paramEncodedStringValue)
  {
    mPduHeaders.appendEncodedStringValue(paramEncodedStringValue, 130);
  }
  
  public EncodedStringValue[] getBcc()
  {
    return mPduHeaders.getEncodedStringValues(129);
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
  
  public long getExpiry()
  {
    return mPduHeaders.getLongInteger(136);
  }
  
  public byte[] getMessageClass()
  {
    return mPduHeaders.getTextString(138);
  }
  
  public long getMessageSize()
  {
    return mPduHeaders.getLongInteger(142);
  }
  
  public int getReadReport()
  {
    return mPduHeaders.getOctet(144);
  }
  
  public byte[] getTransactionId()
  {
    return mPduHeaders.getTextString(152);
  }
  
  public void setBcc(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValues(paramArrayOfEncodedStringValue, 129);
  }
  
  public void setCc(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValues(paramArrayOfEncodedStringValue, 130);
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
  
  public void setExpiry(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 136);
  }
  
  public void setMessageClass(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 138);
  }
  
  public void setMessageSize(long paramLong)
  {
    mPduHeaders.setLongInteger(paramLong, 142);
  }
  
  public void setReadReport(int paramInt)
    throws InvalidHeaderValueException
  {
    mPduHeaders.setOctet(paramInt, 144);
  }
  
  public void setTo(EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    mPduHeaders.setEncodedStringValues(paramArrayOfEncodedStringValue, 151);
  }
  
  public void setTransactionId(byte[] paramArrayOfByte)
  {
    mPduHeaders.setTextString(paramArrayOfByte, 152);
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.SendReq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */