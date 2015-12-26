package com.android.mms.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.MmsSms;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.GsmAlphabet.TextEncodingDetails;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsHeader.ConcatRef;
import com.android.internal.telephony.SmsMessageBase.SubmitPduBase;
import com.android.internal.telephony.cdma.sms.BearerData;
import com.android.internal.telephony.cdma.sms.BearerData.TimeStamp;
import com.android.internal.telephony.cdma.sms.CdmaSmsAddress;
import com.android.internal.telephony.cdma.sms.SmsEnvelope;
import com.android.internal.telephony.cdma.sms.UserData;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import miui.telephony.SmsManager;

public class SimCardManager
{
  private static int sConcatenatedRef = new Random().nextInt(256);
  private static SimCardManager sInstance = null;
  private Context mContext;
  
  public SimCardManager(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private GsmAlphabet.TextEncodingDetails calculateLength(CharSequence paramCharSequence, boolean paramBoolean, int paramInt)
  {
    if (paramInt == 1) {
      paramCharSequence = com.android.internal.telephony.gsm.SmsMessage.calculateLength(paramCharSequence, paramBoolean);
    }
    for (;;)
    {
      if (paramCharSequence == null) {
        Log.e("SimCardManager", "calculateLength details is null");
      }
      return paramCharSequence;
      try
      {
        Class localClass = Class.forName(com.android.internal.telephony.cdma.SmsMessage.class.getName());
        if (MSimUtils.isAndroidM()) {
          paramCharSequence = (GsmAlphabet.TextEncodingDetails)localClass.getMethod("calculateLength", new Class[] { CharSequence.class, Boolean.TYPE, Boolean.TYPE }).invoke(null, new Object[] { paramCharSequence, Boolean.valueOf(paramBoolean), Boolean.valueOf(true) });
        } else {
          paramCharSequence = (GsmAlphabet.TextEncodingDetails)localClass.getMethod("calculateLength", new Class[] { CharSequence.class, Boolean.TYPE }).invoke(null, new Object[] { paramCharSequence, Boolean.valueOf(paramBoolean) });
        }
      }
      catch (Exception paramCharSequence)
      {
        Log.e("SimCardManager", "calculateLength cdma type ", paramCharSequence);
        paramCharSequence = null;
      }
    }
  }
  
  public static SimCardManager getInstance(Context paramContext)
  {
    if (sInstance == null) {
      sInstance = new SimCardManager(paramContext);
    }
    return sInstance;
  }
  
  private static int getNextConcatenatedRef()
  {
    sConcatenatedRef += 1;
    return sConcatenatedRef;
  }
  
  private SmsMessageBase.SubmitPduBase getSubmitPdu(String paramString1, String paramString2, String paramString3, boolean paramBoolean, SmsHeader paramSmsHeader)
  {
    byte[] arrayOfByte = null;
    if (paramSmsHeader != null) {
      arrayOfByte = SmsHeader.toByteArray(paramSmsHeader);
    }
    return com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(paramString1, paramString2, paramString3, paramBoolean, arrayOfByte);
  }
  
  private int saveToIcc(SmsManager paramSmsManager, String paramString1, String paramString2, long paramLong, int paramInt1, SmsHeader paramSmsHeader, int paramInt2, int paramInt3)
  {
    if ((paramInt1 != 1) && (paramInt3 == 1))
    {
      paramString1 = getSubmitPdu(null, paramString1, paramString2, true, paramSmsHeader);
      if (paramString1 == null)
      {
        Log.e("SimCardManager", "saveToIcc getSubmitPdu is null");
        return 1001;
      }
      if (paramSmsManager.copyMessageToIcc(encodedScAddress, encodedMessage, paramInt1)) {
        return 1000;
      }
      return 1001;
    }
    if (paramInt3 == 1)
    {
      paramString1 = new GSMDeliveryPduGenerator().getDeliveryPdu(null, paramString1, paramString2, paramLong, paramSmsHeader);
      if (paramString1 == null)
      {
        Log.e("SimCardManager", "saveToIcc GSM getDeliveryPdu is null");
        return 1001;
      }
      if (paramSmsManager.copyMessageToIcc(encodedScAddress, encodedMessage, paramInt1)) {
        return 1000;
      }
      return 1001;
    }
    paramString1 = new CDMADeliveryPduGenerator().getDeliveryPdu(paramString1, paramString2, false, paramLong, paramSmsHeader);
    if (paramString1 == null)
    {
      Log.e("SimCardManager", "saveToIcc CDMA getDeliveryPdu is null");
      return 1001;
    }
    if (paramSmsManager.copyMessageToIcc(encodedScAddress, encodedMessage, paramInt1)) {
      return 1000;
    }
    return 1001;
  }
  
  public int saveMessageToIcc(String paramString1, String paramString2, long paramLong, int paramInt1, int paramInt2)
  {
    SmsManager localSmsManager = MSimUtils.getSmsManager(paramInt2);
    int i;
    if (localSmsManager == null)
    {
      Log.e("SimCardManager", "saveMessageToIcc get SmsManager is failed");
      i = 1001;
    }
    for (;;)
    {
      return i;
      int n = MSimUtils.getPhoneType(paramInt2);
      ArrayList localArrayList = localSmsManager.divideMessage(paramString2);
      if (localArrayList == null) {
        return saveToIcc(localSmsManager, paramString1, paramString2, paramLong, paramInt1, null, paramInt2, n);
      }
      int m = 1000;
      int i1 = localArrayList.size();
      if (i1 == 1)
      {
        paramInt1 = saveToIcc(localSmsManager, paramString1, (String)localArrayList.get(0), paramLong, paramInt1, null, paramInt2, n);
        i = paramInt1;
        if (paramInt1 != 1000)
        {
          Log.e("SimCardManager", "saveMessageToIcc saveToIcc is failed");
          return paramInt1;
        }
      }
      else
      {
        int i2 = getNextConcatenatedRef();
        int j = 0;
        paramString2 = new GsmAlphabet.TextEncodingDetails[i1];
        i = 0;
        Object localObject;
        while (i < i1)
        {
          localObject = calculateLength((CharSequence)localArrayList.get(i), false, n);
          k = j;
          if (j != codeUnitSize) {
            if (j != 0)
            {
              k = j;
              if (j != 1) {}
            }
            else
            {
              k = codeUnitSize;
            }
          }
          paramString2[i] = localObject;
          i += 1;
          j = k;
        }
        int k = 0;
        i = m;
        while (k < i1)
        {
          localObject = (String)localArrayList.get(k);
          SmsHeader.ConcatRef localConcatRef = new SmsHeader.ConcatRef();
          refNumber = (i2 & 0xFF);
          seqNumber = (k + 1);
          msgCount = i1;
          isEightBits = true;
          SmsHeader localSmsHeader = new SmsHeader();
          concatRef = localConcatRef;
          if (j == 1)
          {
            languageTable = languageTable;
            languageShiftTable = languageShiftTable;
          }
          i = saveToIcc(localSmsManager, paramString1, (String)localObject, paramLong, paramInt1, localSmsHeader, paramInt2, n);
          if (i != 1000)
          {
            Log.e("SimCardManager", "saveMessageToIcc saveToIcc is failed");
            return i;
          }
          k += 1;
        }
      }
    }
  }
  
  class CDMADeliveryPduGenerator
  {
    CDMADeliveryPduGenerator() {}
    
    private int getNextMessageId()
    {
      int i = 1;
      try
      {
        Uri localUri = Uri.parse(Telephony.MmsSms.CONTENT_URI + "/getCdmaMsgId");
        localUri = SqliteWrapper.insert(mContext, mContext.getContentResolver(), localUri, new ContentValues(1));
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
  
  private static class DeliveryPdu
  {
    public byte[] encodedMessage;
    public byte[] encodedScAddress;
    
    public String toString()
    {
      return "DeliveryPdu: encodedScAddress = " + Arrays.toString(encodedScAddress) + ", encodedMessage = " + Arrays.toString(encodedMessage);
    }
  }
  
  class GSMDeliveryPduGenerator
  {
    GSMDeliveryPduGenerator() {}
    
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
}

/* Location:
 * Qualified Name:     com.android.mms.util.SimCardManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */