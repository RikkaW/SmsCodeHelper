package com.android.mms.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.MmsSms;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.cdma.sms.BearerData;
import com.android.internal.telephony.cdma.sms.BearerData.TimeStamp;
import com.android.internal.telephony.cdma.sms.CdmaSmsAddress;
import com.android.internal.telephony.cdma.sms.SmsEnvelope;
import com.android.internal.telephony.cdma.sms.UserData;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class SimCardManager$CDMADeliveryPduGenerator
{
  SimCardManager$CDMADeliveryPduGenerator(SimCardManager paramSimCardManager) {}
  
  private int getNextMessageId()
  {
    int i = 1;
    try
    {
      Uri localUri = Uri.parse(Telephony.MmsSms.CONTENT_URI + "/getCdmaMsgId");
      localUri = SqliteWrapper.insert(SimCardManager.access$100(this$0), SimCardManager.access$100(this$0).getContentResolver(), localUri, new ContentValues(1));
      if (localUri != null) {
        i = Integer.parseInt(localUri.getLastPathSegment());
      }
      Log.v("SimCardManager", "getNextMessageId is " + i);
      return i;
    }
    finally {}
  }
  
  private SimCardManager.DeliveryPdu privateGetDeliveryPdu(String paramString, boolean paramBoolean, UserData paramUserData, long paramLong)
  {
    if (paramString == null) {
      return null;
    }
    paramString = PhoneNumberUtils.cdmaCheckAndProcessPlusCode(paramString);
    if (paramString == null)
    {
      Log.e("CDMA DeliveryPduGenerator", "privateGetDeliveryPdu addr is error");
      return null;
    }
    paramString = CdmaSmsAddress.parse(paramString);
    if (paramString == null)
    {
      Log.e("CDMA DeliveryPduGenerator", "privateGetDeliveryPdu origin addr is error");
      return null;
    }
    Object localObject1 = new BearerData();
    messageType = 1;
    messageId = getNextMessageId();
    deliveryAckReq = paramBoolean;
    userAckReq = false;
    readAckReq = false;
    reportReq = false;
    userData = paramUserData;
    Object localObject3 = BearerData.encode((BearerData)localObject1);
    if (localObject3 == null) {
      return null;
    }
    BearerData.TimeStamp localTimeStamp = new BearerData.TimeStamp();
    localTimeStamp.set(paramLong);
    paramUserData = new ByteArrayOutputStream();
    Object localObject2 = new DataOutputStream(paramUserData);
    try
    {
      ((DataOutputStream)localObject2).write((byte[])localObject3, 0, localObject3.length);
      ((DataOutputStream)localObject2).writeByte(3);
      ((DataOutputStream)localObject2).writeByte(6);
      localObject3 = timeStampToBCDBytes(localTimeStamp);
      ((DataOutputStream)localObject2).write((byte[])localObject3, 0, localObject3.length);
      paramUserData = paramUserData.toByteArray();
      if (hasUserDataHeader)
      {
        i = 4101;
        localObject2 = new SmsEnvelope();
        messageType = 0;
        teleService = i;
        origAddress = paramString;
        bearerReply = 1;
        bearerData = paramUserData;
        try
        {
          localObject1 = new ByteArrayOutputStream(500);
          localObject3 = new DataOutputStream((OutputStream)localObject1);
          if (MSimUtils.isAndroid50())
          {
            ((DataOutputStream)localObject3).writeInt(teleService);
            ((DataOutputStream)localObject3).writeInt(0);
            ((DataOutputStream)localObject3).writeInt(0);
            ((DataOutputStream)localObject3).write(digitMode);
            ((DataOutputStream)localObject3).write(numberMode);
            ((DataOutputStream)localObject3).write(ton);
            ((DataOutputStream)localObject3).write(numberPlan);
            ((DataOutputStream)localObject3).write(numberOfDigits);
            ((DataOutputStream)localObject3).write(origBytes, 0, origBytes.length);
            ((DataOutputStream)localObject3).write(0);
            ((DataOutputStream)localObject3).write(0);
            ((DataOutputStream)localObject3).write(0);
            ((DataOutputStream)localObject3).write(paramUserData.length);
            ((DataOutputStream)localObject3).write(paramUserData, 0, paramUserData.length);
            ((DataOutputStream)localObject3).close();
            paramString = new SimCardManager.DeliveryPdu(null);
            encodedMessage = ((ByteArrayOutputStream)localObject1).toByteArray();
            encodedScAddress = null;
            return paramString;
          }
        }
        catch (IOException paramString)
        {
          Log.e("CDMA DeliveryPduGenerator", "creating Delivery failed: " + paramString);
          return null;
        }
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        continue;
        int i = 4098;
        continue;
        ((DataOutputStream)localObject3).writeInt(teleService);
        ((DataOutputStream)localObject3).write(0);
        ((DataOutputStream)localObject3).writeInt(0);
        ((DataOutputStream)localObject3).writeInt(digitMode);
        ((DataOutputStream)localObject3).writeInt(numberMode);
        ((DataOutputStream)localObject3).writeInt(ton);
        ((DataOutputStream)localObject3).writeInt(numberPlan);
        ((DataOutputStream)localObject3).write(numberOfDigits);
        ((DataOutputStream)localObject3).write(origBytes, 0, origBytes.length);
        ((DataOutputStream)localObject3).writeInt(0);
        ((DataOutputStream)localObject3).write(0);
        ((DataOutputStream)localObject3).write(0);
        ((DataOutputStream)localObject3).writeInt(paramUserData.length);
        ((DataOutputStream)localObject3).write(paramUserData, 0, paramUserData.length);
      }
    }
  }
  
  public byte createCDMABCD(int paramInt)
  {
    int i = (paramInt & 0xFF) / 10;
    return (byte)(i << 4 | (paramInt & 0xFF) - i * 10);
  }
  
  public SimCardManager.DeliveryPdu getDeliveryPdu(String paramString1, String paramString2, boolean paramBoolean, long paramLong, SmsHeader paramSmsHeader)
  {
    if ((paramString2 == null) || (paramString1 == null)) {
      return null;
    }
    UserData localUserData = new UserData();
    payloadStr = paramString2;
    userDataHeader = paramSmsHeader;
    return privateGetDeliveryPdu(paramString1, paramBoolean, localUserData, paramLong);
  }
  
  public byte[] timeStampToBCDBytes(BearerData.TimeStamp paramTimeStamp)
  {
    int i1 = year;
    if ((i1 > 2095) || (i1 < 1996)) {
      return null;
    }
    if (i1 >= 2000) {
      i1 -= 2000;
    }
    int i;
    for (;;)
    {
      i = createCDMABCD(i1);
      i1 = month + 1;
      if ((i1 >= 1) && (i1 <= 12)) {
        break;
      }
      return null;
      i1 -= 1900;
    }
    int j = createCDMABCD(i1);
    i1 = monthDay;
    if ((i1 < 1) || (i1 > 31)) {
      return null;
    }
    int k = createCDMABCD(i1);
    i1 = hour;
    if ((i1 < 0) || (i1 > 23)) {
      return null;
    }
    int m = createCDMABCD(i1);
    i1 = minute;
    if ((i1 < 0) || (i1 > 59)) {
      return null;
    }
    int n = createCDMABCD(i1);
    i1 = second;
    if ((i1 < 0) || (i1 > 59)) {
      return null;
    }
    return new byte[] { i, j, k, m, n, createCDMABCD(i1) };
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.SimCardManager.CDMADeliveryPduGenerator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */