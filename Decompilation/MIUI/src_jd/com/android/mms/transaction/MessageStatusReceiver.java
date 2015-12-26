package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.SmsMessageBase;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.lang.reflect.Method;
import miui.telephony.TelephonyManager;

public class MessageStatusReceiver
  extends BroadcastReceiver
{
  private static final String[] ID_PROJECTION = { "_id" };
  private static final Uri STATUS_URI = Uri.parse("content://sms/status");
  private int mStatus = 0;
  
  private boolean checkSpecialFailedStatus(String paramString, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.v("MessageStatusReceiver", "checkSpecialFailedStatus dest is empty");
    }
    String str;
    do
    {
      return false;
      str = TelephonyManager.getDefault().getSimOperatorForSlot(paramInt2);
      if (TextUtils.isEmpty(str))
      {
        Log.v("MessageStatusReceiver", "checkSpecialFailedStatus plmn is empty for slotId " + paramInt2);
        return false;
      }
    } while ((paramInt1 != 67) || (!"46009".equalsIgnoreCase(str)) || ((!"10010".equals(paramString)) && (!"+8610010".equals(paramString))));
    Log.v("MessageStatusReceiver", "checkSpecialFailedStatus is right");
    return true;
  }
  
  private void error(String paramString)
  {
    MyLog.e("MessageStatusReceiver", "[MessageStatusReceiver] " + paramString);
  }
  
  private String getRecipientAddress(SmsMessage paramSmsMessage)
  {
    paramSmsMessage = mWrappedSmsMessage;
    try
    {
      Method localMethod = SmsMessageBase.class.getDeclaredMethod("getRecipientAddress", new Class[0]);
      Log.v("MessageStatusReceiver", "status getRecipientAddress is abstract");
      try
      {
        paramSmsMessage = (String)localMethod.invoke(paramSmsMessage, new Object[0]);
        return paramSmsMessage;
      }
      catch (Exception paramSmsMessage)
      {
        Log.v("MessageStatusReceiver", "Couldn't invoke this method" + paramSmsMessage);
      }
    }
    catch (NoSuchMethodException paramSmsMessage)
    {
      for (;;)
      {
        paramSmsMessage.printStackTrace();
      }
    }
    return "";
  }
  
  private void handleSendFailed(int paramInt, ContentValues paramContentValues)
  {
    if (paramInt >= 64)
    {
      paramContentValues.put("type", Integer.valueOf(5));
      paramContentValues.put("read", Integer.valueOf(0));
      paramContentValues.put("error_code", Integer.valueOf(0));
    }
  }
  
  private void log(String paramString)
  {
    MyLog.d("MessageStatusReceiver", "[MessageStatusReceiver] " + paramString);
  }
  
  /* Error */
  private SmsMessage updateMessageStatus(Context paramContext, Uri paramUri, byte[] paramArrayOfByte, String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_3
    //   1: aload 4
    //   3: invokestatic 175	android/telephony/SmsMessage:createFromPdu	([BLjava/lang/String;)Landroid/telephony/SmsMessage;
    //   6: astore 4
    //   8: aload 4
    //   10: ifnonnull +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload_0
    //   16: aload 4
    //   18: invokevirtual 179	android/telephony/SmsMessage:getStatus	()I
    //   21: ldc -76
    //   23: iand
    //   24: putfield 34	com/android/mms/transaction/MessageStatusReceiver:mStatus	I
    //   27: aload_0
    //   28: aload_0
    //   29: aload 4
    //   31: invokespecial 182	com/android/mms/transaction/MessageStatusReceiver:getRecipientAddress	(Landroid/telephony/SmsMessage;)Ljava/lang/String;
    //   34: aload_0
    //   35: getfield 34	com/android/mms/transaction/MessageStatusReceiver:mStatus	I
    //   38: iload 5
    //   40: invokespecial 184	com/android/mms/transaction/MessageStatusReceiver:checkSpecialFailedStatus	(Ljava/lang/String;II)Z
    //   43: ifeq +8 -> 51
    //   46: aload_0
    //   47: iconst_0
    //   48: putfield 34	com/android/mms/transaction/MessageStatusReceiver:mStatus	I
    //   51: aload_1
    //   52: aload_1
    //   53: invokevirtual 190	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   56: aload_2
    //   57: getstatic 18	com/android/mms/transaction/MessageStatusReceiver:ID_PROJECTION	[Ljava/lang/String;
    //   60: aconst_null
    //   61: aconst_null
    //   62: aconst_null
    //   63: invokestatic 196	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   66: astore_3
    //   67: aload_3
    //   68: ifnonnull +5 -> 73
    //   71: aconst_null
    //   72: areturn
    //   73: aload_3
    //   74: invokeinterface 202 1 0
    //   79: ifeq +158 -> 237
    //   82: aload_3
    //   83: iconst_0
    //   84: invokeinterface 206 2 0
    //   89: istore 5
    //   91: getstatic 28	com/android/mms/transaction/MessageStatusReceiver:STATUS_URI	Landroid/net/Uri;
    //   94: iload 5
    //   96: i2l
    //   97: invokestatic 212	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   100: astore 9
    //   102: aload_0
    //   103: getfield 34	com/android/mms/transaction/MessageStatusReceiver:mStatus	I
    //   106: istore 5
    //   108: aload 4
    //   110: invokevirtual 215	android/telephony/SmsMessage:isStatusReportMessage	()Z
    //   113: istore 6
    //   115: new 157	android/content/ContentValues
    //   118: dup
    //   119: invokespecial 216	android/content/ContentValues:<init>	()V
    //   122: astore 10
    //   124: ldc -38
    //   126: iconst_3
    //   127: invokestatic 222	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   130: ifeq +46 -> 176
    //   133: aload_0
    //   134: new 64	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   141: ldc -32
    //   143: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_2
    //   147: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   150: ldc -30
    //   152: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: iload 5
    //   157: invokevirtual 74	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   160: ldc -28
    //   162: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: iload 6
    //   167: invokevirtual 231	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   170: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokespecial 233	com/android/mms/transaction/MessageStatusReceiver:log	(Ljava/lang/String;)V
    //   176: invokestatic 239	java/lang/System:currentTimeMillis	()J
    //   179: lstore 7
    //   181: aload 10
    //   183: ldc -15
    //   185: iload 5
    //   187: invokestatic 155	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   190: invokevirtual 161	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   193: aload 10
    //   195: ldc -13
    //   197: lload 7
    //   199: invokestatic 248	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   202: invokevirtual 251	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   205: aload_0
    //   206: iload 5
    //   208: aload 10
    //   210: invokespecial 253	com/android/mms/transaction/MessageStatusReceiver:handleSendFailed	(ILandroid/content/ContentValues;)V
    //   213: aload_1
    //   214: aload_1
    //   215: invokevirtual 190	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   218: aload 9
    //   220: aload 10
    //   222: aconst_null
    //   223: aconst_null
    //   224: invokestatic 257	android/database/sqlite/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   227: pop
    //   228: aload_3
    //   229: invokeinterface 260 1 0
    //   234: aload 4
    //   236: areturn
    //   237: aload_0
    //   238: new 64	java/lang/StringBuilder
    //   241: dup
    //   242: invokespecial 65	java/lang/StringBuilder:<init>	()V
    //   245: ldc_w 262
    //   248: invokevirtual 71	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: aload_2
    //   252: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   255: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   258: invokespecial 264	com/android/mms/transaction/MessageStatusReceiver:error	(Ljava/lang/String;)V
    //   261: goto -33 -> 228
    //   264: astore_1
    //   265: aload_3
    //   266: invokeinterface 260 1 0
    //   271: aload_1
    //   272: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	MessageStatusReceiver
    //   0	273	1	paramContext	Context
    //   0	273	2	paramUri	Uri
    //   0	273	3	paramArrayOfByte	byte[]
    //   0	273	4	paramString	String
    //   0	273	5	paramInt	int
    //   113	53	6	bool	boolean
    //   179	19	7	l	long
    //   100	119	9	localUri	Uri
    //   122	99	10	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   73	176	264	finally
    //   176	228	264	finally
    //   237	261	264	finally
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i;
    if ("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED".equals(paramIntent.getAction()))
    {
      Uri localUri = paramIntent.getData();
      byte[] arrayOfByte = (byte[])paramIntent.getExtra("pdu");
      String str = paramIntent.getStringExtra("format");
      i = MSimUtils.getSlotIdFromIntent(paramIntent);
      MyLog.d("MessageStatusReceiver", "sms delivered, uri: " + localUri);
      mStatus = 0;
      paramIntent = updateMessageStatus(paramContext, localUri, arrayOfByte, str, i);
      if ((paramIntent != null) && (paramIntent.isStatusReportMessage()))
      {
        if (mStatus != 0) {
          break label117;
        }
        MessagingNotification.nonBlockingUpdateDeliveryInfo(paramContext, localUri);
      }
    }
    label117:
    while (mStatus < 64) {
      return;
    }
    MessagingNotification.notifySendFailed(paramContext, true, i);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MessageStatusReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */