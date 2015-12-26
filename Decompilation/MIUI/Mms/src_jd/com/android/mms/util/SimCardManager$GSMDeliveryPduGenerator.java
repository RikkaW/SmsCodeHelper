package com.android.mms.util;

import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsHeader;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;

class SimCardManager$GSMDeliveryPduGenerator
{
  SimCardManager$GSMDeliveryPduGenerator(SimCardManager paramSimCardManager) {}
  
  private byte[] encodeUCS2(String paramString, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    paramString = paramString.getBytes("utf-16be");
    if (paramArrayOfByte != null)
    {
      byte[] arrayOfByte = new byte[paramArrayOfByte.length + paramString.length + 1];
      arrayOfByte[0] = ((byte)paramArrayOfByte.length);
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 1, paramArrayOfByte.length);
      System.arraycopy(paramString, 0, arrayOfByte, paramArrayOfByte.length + 1, paramString.length);
      paramString = arrayOfByte;
    }
    for (;;)
    {
      paramArrayOfByte = new byte[paramString.length + 1];
      paramArrayOfByte[0] = ((byte)(paramString.length & 0xFF));
      System.arraycopy(paramString, 0, paramArrayOfByte, 1, paramString.length);
      return paramArrayOfByte;
    }
  }
  
  private ByteArrayOutputStream getDeliveryPduHead(String paramString1, String paramString2, byte paramByte, SimCardManager.DeliveryPdu paramDeliveryPdu)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(180);
    if (paramString1 == null) {}
    for (encodedScAddress = null;; encodedScAddress = PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength(paramString1))
    {
      localByteArrayOutputStream.write(paramByte);
      paramString1 = PhoneNumberUtils.networkPortionToCalledPartyBCD(paramString2);
      if (paramString1 != null) {
        break;
      }
      Log.e("GSM DeliveryPduGenerator", "originator address is error");
      return null;
    }
    int i = paramString1.length;
    if ((paramString1[(paramString1.length - 1)] & 0xF0) == 240) {}
    for (paramByte = 1;; paramByte = 0)
    {
      localByteArrayOutputStream.write((i - 1) * 2 - paramByte);
      localByteArrayOutputStream.write(paramString1, 0, paramString1.length);
      localByteArrayOutputStream.write(0);
      return localByteArrayOutputStream;
    }
  }
  
  private void writeTimeStampStringForDate(long paramLong, ByteArrayOutputStream paramByteArrayOutputStream)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int k = localCalendar.get(1);
    int m = localCalendar.get(2);
    int n = localCalendar.get(5);
    int i1 = localCalendar.get(11);
    int i2 = localCalendar.get(12);
    int i3 = localCalendar.get(13);
    int j = localCalendar.getTimeZone().getOffset(paramLong) / 60000 / 15;
    int i = j;
    if (j < 0) {
      i = 128 - j;
    }
    paramByteArrayOutputStream.write(createSwappedBCD(k - 2000));
    paramByteArrayOutputStream.write(createSwappedBCD(m + 1));
    paramByteArrayOutputStream.write(createSwappedBCD(n));
    paramByteArrayOutputStream.write(createSwappedBCD(i1));
    paramByteArrayOutputStream.write(createSwappedBCD(i2));
    paramByteArrayOutputStream.write(createSwappedBCD(i3));
    paramByteArrayOutputStream.write(createSwappedBCD(i));
  }
  
  public int createSwappedBCD(int paramInt)
  {
    int i = (paramInt & 0xFF) / 10;
    return (paramInt & 0xFF) - i * 10 << 4 | i;
  }
  
  public SimCardManager.DeliveryPdu getDeliveryPdu(String paramString1, String paramString2, String paramString3, long paramLong, SmsHeader paramSmsHeader)
  {
    if ((paramString3 == null) || (paramString2 == null)) {
      return null;
    }
    SimCardManager.DeliveryPdu localDeliveryPdu = new SimCardManager.DeliveryPdu(null);
    if (paramSmsHeader != null) {}
    for (int i = 64;; i = 0)
    {
      paramString2 = getDeliveryPduHead(paramString1, paramString2, (byte)(i | 0x0), localDeliveryPdu);
      if (paramString2 != null) {
        break;
      }
      Log.e("GSM DeliveryPduGenerator", "getDeliveryPduHead is null");
      return null;
    }
    i = 1;
    if (paramSmsHeader != null) {}
    for (;;)
    {
      try
      {
        paramString1 = SmsHeader.toByteArray(paramSmsHeader);
        paramString1 = GsmAlphabet.stringToGsm7BitPackedWithHeader(paramString3, paramString1);
      }
      catch (EncodeException paramString1)
      {
        if (paramSmsHeader == null) {
          continue;
        }
        try
        {
          paramString1 = SmsHeader.toByteArray(paramSmsHeader);
          paramString1 = encodeUCS2(paramString3, paramString1);
          i = 3;
        }
        catch (UnsupportedEncodingException paramString1)
        {
          Log.e("GSM DeliveryPduGenerator", "Implausible UnsupportedEncodingException ", paramString1);
          return null;
        }
        paramString1 = null;
        continue;
        paramString2.write(18);
      }
      if (i != 1) {
        break label184;
      }
      if ((paramString1[0] & 0xFF) <= 160) {
        continue;
      }
      return null;
      paramString1 = null;
    }
    for (;;)
    {
      writeTimeStampStringForDate(paramLong, paramString2);
      paramString2.write(paramString1, 0, paramString1.length);
      encodedMessage = paramString2.toByteArray();
      return localDeliveryPdu;
      label184:
      if ((paramString1[0] & 0xFF) > 140) {
        return null;
      }
      paramString2.write(8);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.SimCardManager.GSMDeliveryPduGenerator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */