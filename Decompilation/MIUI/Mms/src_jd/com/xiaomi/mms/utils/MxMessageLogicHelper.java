package com.xiaomi.mms.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.collect.Lists;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import miui.push.CommonPacketExtension;
import miui.push.Message;
import miui.push.ServiceClient;
import org.json.JSONException;
import org.json.JSONObject;

public class MxMessageLogicHelper
{
  private static final AtomicLong B2C_SMS_ID;
  private static final AtomicLong MMS_ID;
  private static final AtomicLong SMS_ID = new AtomicLong();
  
  static
  {
    B2C_SMS_ID = new AtomicLong();
    MMS_ID = new AtomicLong();
    long l = new Random(System.nanoTime()).nextInt(65535) | (System.currentTimeMillis() & 0xFFFFFFFFFFF) << 16;
    SMS_ID.set(0L | l);
    B2C_SMS_ID.set(0x2000000000000000 | l);
    MMS_ID.set(0x4000000000000000 | l);
  }
  
  public static String base64Decode(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      MyLog.d("MxMessageLogicHelper", "base64Decode pdu is empty");
      return paramString;
    }
    paramString = Base64.decode(paramString, 0);
    if (paramString == null)
    {
      MyLog.e("MxMessageLogicHelper", "error for decode to string");
      return null;
    }
    return new String(paramString);
  }
  
  public static String base64Encode(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      MyLog.d("MxMessageLogicHelper", "base64Encode pdu is empty");
      return paramString;
    }
    paramString = Base64.encodeToString(paramString.getBytes(), 2);
    if (TextUtils.isEmpty(paramString)) {
      MyLog.e("MxMessageLogicHelper", "error for encode to string");
    }
    return paramString;
  }
  
  public static CommonPacketExtension constructB2cNumbers(String paramString1, String paramString2)
  {
    CommonPacketExtension localCommonPacketExtension = new CommonPacketExtension("b2cNumber", null, "", "");
    if (!TextUtils.equals(paramString1, paramString2)) {
      localCommonPacketExtension.setText(paramString1);
    }
    return localCommonPacketExtension;
  }
  
  public static String constructFullRecipientId(String paramString)
  {
    if (paramString.contains("@")) {
      return paramString;
    }
    return paramString + "@xiaomi.com";
  }
  
  public static CommonPacketExtension constructMmsAttachment(String paramString1, String paramString2, long paramLong1, long paramLong2)
  {
    return new CommonPacketExtension("attachment", null, Lists.newArrayList(new String[] { "shareId", "mimeType", "expireAt", "msgSize" }), Lists.newArrayList(new String[] { paramString1, paramString2, String.valueOf(paramLong1), String.valueOf(paramLong2) }));
  }
  
  public static String constructMmsBody(String paramString, long paramLong)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "mms");
      localJSONObject.put("msgId", paramLong);
      localJSONObject.put("sentTime", System.currentTimeMillis());
      localJSONObject.put("subject", paramString);
      return localJSONObject.toString();
    }
    catch (JSONException paramString)
    {
      throw new IllegalStateException("error when construct mms", paramString);
    }
  }
  
  public static CommonPacketExtension constructMxAttachment(String paramString1, String paramString2, long paramLong1, long paramLong2, String paramString3)
  {
    return new CommonPacketExtension("attachment", null, Lists.newArrayList(new String[] { "shareId", "mimeType", "expireAt", "msgSize", "mxExtension" }), Lists.newArrayList(new String[] { paramString1, paramString2, String.valueOf(paramLong1), String.valueOf(paramLong2), paramString3 }));
  }
  
  public static String constructMxBody(String paramString1, long paramLong, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "mx2");
      localJSONObject.put("msgId", paramLong);
      localJSONObject.put("sentTime", System.currentTimeMillis());
      localJSONObject.put("subject", paramString1);
      localJSONObject.put("mxType", paramString2);
      return localJSONObject.toString();
    }
    catch (JSONException paramString1)
    {
      throw new IllegalStateException("error when construct mx", paramString1);
    }
  }
  
  public static String constructSmsBody(String paramString, long paramLong)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "sms");
      localJSONObject.put("pdu", paramString);
      localJSONObject.put("msgId", paramLong);
      localJSONObject.put("sentTime", System.currentTimeMillis());
      return localJSONObject.toString();
    }
    catch (JSONException paramString)
    {
      throw new IllegalStateException("error when construct sms", paramString);
    }
  }
  
  public static String getSimpleMid(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    int i;
    do
    {
      return str;
      i = paramString.indexOf("@");
      str = paramString;
    } while (i <= 0);
    return paramString.substring(0, i);
  }
  
  public static boolean isB2cSms(long paramLong)
  {
    return (0xF000000000000000 & paramLong) == 2305843009213693952L;
  }
  
  public static boolean isMms(long paramLong)
  {
    return (0xF000000000000000 & paramLong) == 4611686018427387904L;
  }
  
  public static boolean isSms(long paramLong)
  {
    return ((0xF000000000000000 & paramLong) == 0L) || (isB2cSms(paramLong));
  }
  
  public static Long nextMiId(boolean paramBoolean)
  {
    return nextMiId(false, paramBoolean);
  }
  
  public static Long nextMiId(boolean paramBoolean1, boolean paramBoolean2)
  {
    long l;
    if (paramBoolean1) {
      l = MMS_ID.getAndIncrement();
    }
    for (;;)
    {
      return Long.valueOf(l);
      if (paramBoolean2) {
        l = B2C_SMS_ID.getAndIncrement();
      } else {
        l = SMS_ID.getAndIncrement();
      }
    }
  }
  
  public static Message obtainMessage(String paramString1, String paramString2)
  {
    paramString2 = constructFullRecipientId(paramString2);
    Message localMessage = new Message();
    localMessage.setFrom(paramString1);
    localMessage.setChannelId("3");
    localMessage.setTo(paramString2);
    return localMessage;
  }
  
  public static boolean sendAckToServer(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = obtainMessage(paramString1, paramString2);
    paramString1.addExtension(new CommonPacketExtension("sent", null, "id", paramString3));
    return ServiceClient.getInstance(paramContext).sendMessage(paramString1);
  }
  
  public static boolean sendDeleteCommandToServer(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramString1 = obtainMessage(paramString1, paramString2);
    paramString2 = new CommonPacketExtension("deleted", "xm", "id", paramString3);
    paramString1.setType("chat");
    paramString1.addExtension(paramString2);
    return ServiceClient.getInstance(paramContext).sendMessage(paramString1);
  }
  
  public static boolean sendReceivedAck(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return sendReceivedAck(paramContext, paramString1, paramString2, paramString3, null, null);
  }
  
  public static boolean sendReceivedAck(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    paramString1 = obtainMessage(paramString1, paramString2);
    paramString1.addExtension(new CommonPacketExtension("received", null, "id", paramString3));
    if (paramString4 != null)
    {
      paramString2 = new CommonPacketExtension("error", null, new String[] { "type", "reason" }, new String[] { "client", paramString4 });
      paramString2.setText(paramString5);
      paramString1.addExtension(paramString2);
    }
    return ServiceClient.getInstance(paramContext).sendMessage(paramString1);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MxMessageLogicHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */