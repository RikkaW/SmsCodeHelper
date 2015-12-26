package com.android.mms.transaction;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.Sms.Sent;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.HashSet;

public class SmsMessageSender
  implements MessageSender
{
  private static final String[] SERVICE_CENTER_PROJECTION = { "reply_path_present", "service_center" };
  private static final Uri URI_QUEUED = Uri.parse("content://sms/queued");
  protected final Context mContext;
  private final String[] mDests;
  protected final String mMessageText;
  protected final int mNumberOfDests;
  protected final boolean mSendByMx;
  protected final String mServiceCenter;
  protected final int mSlotId;
  protected final long mThreadId;
  private final long mTimeToSend;
  protected long mTimestamp;
  
  public SmsMessageSender(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    this(paramContext, paramArrayOfString, paramString, paramLong1, paramLong2, false, paramInt);
  }
  
  public SmsMessageSender(Context paramContext, String[] paramArrayOfString, String paramString, long paramLong1, long paramLong2, boolean paramBoolean, int paramInt)
  {
    mSlotId = paramInt;
    mContext = paramContext;
    mMessageText = paramString;
    if (paramArrayOfString != null)
    {
      mNumberOfDests = paramArrayOfString.length;
      mDests = new String[mNumberOfDests];
      System.arraycopy(paramArrayOfString, 0, mDests, 0, mNumberOfDests);
    }
    for (;;)
    {
      mTimeToSend = paramLong2;
      mTimestamp = System.currentTimeMillis();
      if (paramLong1 != 0L) {
        break label157;
      }
      paramArrayOfString = new HashSet();
      paramInt = 0;
      while (paramInt < mNumberOfDests)
      {
        paramArrayOfString.add(mDests[paramInt]);
        paramInt += 1;
      }
      mNumberOfDests = 0;
      mDests = null;
    }
    label157:
    for (mThreadId = Telephony.Threads.getOrCreateThreadId(paramContext, paramArrayOfString);; mThreadId = paramLong1)
    {
      mServiceCenter = getOutgoingServiceCenter(mThreadId);
      mSendByMx = paramBoolean;
      return;
    }
  }
  
  /* Error */
  private String getOutgoingServiceCenter(long paramLong)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aconst_null
    //   3: astore 5
    //   5: aload_0
    //   6: getfield 57	com/android/mms/transaction/SmsMessageSender:mContext	Landroid/content/Context;
    //   9: aload_0
    //   10: getfield 57	com/android/mms/transaction/SmsMessageSender:mContext	Landroid/content/Context;
    //   13: invokevirtual 106	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: getstatic 111	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   19: getstatic 45	com/android/mms/transaction/SmsMessageSender:SERVICE_CENTER_PROJECTION	[Ljava/lang/String;
    //   22: new 113	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   29: ldc 116
    //   31: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: lload_1
    //   35: invokevirtual 123	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   38: invokevirtual 127	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: aconst_null
    //   42: ldc -127
    //   44: invokestatic 135	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   47: astore 7
    //   49: aload 7
    //   51: ifnull +21 -> 72
    //   54: aload 7
    //   56: astore 5
    //   58: aload 7
    //   60: invokeinterface 141 1 0
    //   65: istore 4
    //   67: iload 4
    //   69: ifne +21 -> 90
    //   72: aload 7
    //   74: ifnull +10 -> 84
    //   77: aload 7
    //   79: invokeinterface 144 1 0
    //   84: aconst_null
    //   85: astore 6
    //   87: aload 6
    //   89: areturn
    //   90: aload 7
    //   92: astore 5
    //   94: iconst_1
    //   95: aload 7
    //   97: iconst_0
    //   98: invokeinterface 148 2 0
    //   103: if_icmpne +44 -> 147
    //   106: iload_3
    //   107: ifeq +45 -> 152
    //   110: aload 7
    //   112: astore 5
    //   114: aload 7
    //   116: iconst_1
    //   117: invokeinterface 152 2 0
    //   122: astore 6
    //   124: aload 6
    //   126: astore 5
    //   128: aload 5
    //   130: astore 6
    //   132: aload 7
    //   134: ifnull -47 -> 87
    //   137: aload 7
    //   139: invokeinterface 144 1 0
    //   144: aload 5
    //   146: areturn
    //   147: iconst_0
    //   148: istore_3
    //   149: goto -43 -> 106
    //   152: aconst_null
    //   153: astore 5
    //   155: goto -27 -> 128
    //   158: astore 6
    //   160: aload 5
    //   162: ifnull +10 -> 172
    //   165: aload 5
    //   167: invokeinterface 144 1 0
    //   172: aload 6
    //   174: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	this	SmsMessageSender
    //   0	175	1	paramLong	long
    //   1	148	3	i	int
    //   65	3	4	bool	boolean
    //   3	163	5	localObject1	Object
    //   85	46	6	localObject2	Object
    //   158	15	6	localObject3	Object
    //   47	91	7	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   5	49	158	finally
    //   58	67	158	finally
    //   94	106	158	finally
    //   114	124	158	finally
  }
  
  private boolean queueMessage()
    throws MmsException
  {
    if ((mMessageText == null) || (mNumberOfDests == 0)) {
      throw new MmsException("Null message body or dest.");
    }
    boolean bool2;
    long l1;
    boolean bool4;
    Uri localUri2;
    long l2;
    label103:
    long l3;
    int i;
    label110:
    boolean bool1;
    Object localObject;
    if (MSimUtils.isMSimSlotIdValid(mSlotId))
    {
      bool2 = MxActivateService.isMxEnabled(mContext, mSlotId);
      l1 = MSimUtils.getSimIdBySlotId(mSlotId);
      bool4 = MessageUtils.requireDeliveryStatusBySimId(mContext, l1);
      LogTag.debug("是否获取发送报告:%b", new Object[] { Boolean.valueOf(bool4) });
      if (mTimeToSend <= 0L) {
        break label337;
      }
      localUri2 = Telephony.Sms.Sent.CONTENT_URI;
      l2 = mTimeToSend;
      l3 = System.currentTimeMillis();
      i = 0;
      if (i >= mNumberOfDests) {
        break label491;
      }
      boolean bool3 = mSendByMx;
      bool1 = bool3;
      if (bool2)
      {
        bool1 = bool3;
        if (!bool3)
        {
          bool1 = bool3;
          if (PushSession.getInstance(mContext).isConnected(mSlotId))
          {
            bool1 = bool3;
            if (mNumberOfDests > 1)
            {
              localObject = Contact.get(mDests[i]);
              localObject = MxIdCache.getOrQuery(mContext, ((Contact)localObject).getMxPhoneNumber());
              if ((localObject == null) || (!((MxIdCache.MxIdCacheItem)localObject).allowSms())) {
                break label351;
              }
              bool1 = true;
            }
          }
        }
      }
      if (mTimeToSend > 0L) {
        bool1 = false;
      }
      if (!bool1) {
        break label402;
      }
    }
    for (;;)
    {
      try
      {
        localObject = MSimUtils.addMiMessageToUri(mContext.getContentResolver(), URI_QUEUED, mDests[i], translateWithNickname(mDests[i]), null, Long.valueOf(l2), true, bool4, mThreadId, l1, B2cMessageUtils.getPossibleLastB2cNumber(mContext, mDests[i]));
        if ((mTimeToSend > 0L) && (localObject != null)) {
          MessageUtils.setSmsSendTime(mContext, (Uri)localObject, mTimeToSend, l3);
        }
        i += 1;
        break label110;
        bool2 = false;
        l1 = 0L;
        LogTag.verbose("slot id is not valid", new Object[0]);
        break;
        label337:
        localUri2 = URI_QUEUED;
        l2 = mTimestamp;
        break label103;
        label351:
        bool1 = false;
      }
      catch (SQLiteException localSQLiteException1)
      {
        MyLog.e("SmsMessageSender", "failed to queue message, tid: " + mThreadId);
        SqliteWrapper.checkSQLiteException(mContext, localSQLiteException1);
        localUri1 = null;
        continue;
      }
      try
      {
        label402:
        localUri1 = MSimUtils.addMessageToUri(mContext.getContentResolver(), localUri2, mDests[i], translateWithNickname(mDests[i]), null, Long.valueOf(l2), true, bool4, mThreadId, l1);
        try
        {
          MyLog.d("SmsMessageSender", "message queued, uri:" + localUri1);
        }
        catch (SQLiteException localSQLiteException2) {}
      }
      catch (SQLiteException localSQLiteException3)
      {
        for (;;)
        {
          localUri1 = null;
        }
      }
      SqliteWrapper.checkSQLiteException(mContext, localSQLiteException2);
    }
    label491:
    if (mTimeToSend > 0L) {
      TimedMessageReceiver.scheduleNextTimedMsg(mContext);
    }
    for (;;)
    {
      return false;
      LogTag.debug("广播SmsReceiverService.ACTION_SEND_MESSAGE", new Object[0]);
      MSimUtils.sendQueuedMessage(mContext, mSlotId);
    }
  }
  
  private String translateWithNickname(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 0;
    if (j < mMessageText.length())
    {
      int i = mMessageText.charAt(j);
      Object localObject;
      if (i == 65535)
      {
        localObject = Contact.get(paramString).load(true, false);
        String str = ((Contact)localObject).getNickname();
        localObject = ((Contact)localObject).getRealName();
        if (!TextUtils.isEmpty(str)) {
          localStringBuilder.append(str);
        }
      }
      for (;;)
      {
        j += 1;
        break;
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          localStringBuilder.append((String)localObject);
          continue;
          localStringBuilder.append(i);
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean sendMessage()
    throws MmsException
  {
    return queueMessage();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsMessageSender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */