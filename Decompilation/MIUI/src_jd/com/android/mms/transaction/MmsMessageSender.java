package com.android.mms.transaction;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.Mms.Sent;
import com.android.mms.data.Contact;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Lists;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.ReadRecInd;
import com.google.android.mms.pdu.SendReq;
import com.google.android.mms.util.SqliteWrapper;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import miui.os.Build;

public class MmsMessageSender
  implements MessageSender
{
  private final Context mContext;
  private final long mMessageSize;
  private final Uri mMessageUri;
  private final boolean mSendByMx;
  private final boolean mSendByMxV2;
  private final int mSlotId;
  private final long mTimeToSend;
  
  public MmsMessageSender(Context paramContext, Uri paramUri, long paramLong1, long paramLong2, boolean paramBoolean, int paramInt)
  {
    this(paramContext, paramUri, paramLong1, paramLong2, paramBoolean, paramInt, false);
  }
  
  public MmsMessageSender(Context paramContext, Uri paramUri, long paramLong1, long paramLong2, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    mContext = paramContext;
    mMessageUri = paramUri;
    mMessageSize = paramLong1;
    mTimeToSend = paramLong2;
    mSendByMx = paramBoolean1;
    mSlotId = paramInt;
    mSendByMxV2 = paramBoolean2;
    if (mMessageUri == null) {
      throw new IllegalArgumentException("Null message URI.");
    }
  }
  
  public static void sendReadRec(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    paramString1 = new EncodedStringValue(paramString1);
    try
    {
      paramString1 = new ReadRecInd(new EncodedStringValue("insert-address-token".getBytes()), paramString2.getBytes(), 18, paramInt, new EncodedStringValue[] { paramString1 });
      paramString1.setDate(System.currentTimeMillis() / 1000L);
      MiuiPduPersister.getPduPersister(paramContext).persist(paramString1, Telephony.Mms.Outbox.CONTENT_URI, null, -1L);
      paramContext.startService(new Intent(paramContext, TransactionService.class));
      return;
    }
    catch (InvalidHeaderValueException paramContext)
    {
      MyLog.e("MmsMessageSender", "Invalide header value", paramContext);
      return;
    }
    catch (MmsException paramContext)
    {
      MyLog.e("MmsMessageSender", "Persist message failed", paramContext);
    }
  }
  
  private void updatePreferencesHeaders(SendReq paramSendReq, long paramLong)
    throws MmsException
  {
    int j = 128;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      String str = localSharedPreferences.getString(MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_validity_period"), "0");
      if (!"0".equals(str)) {
        paramSendReq.setExpiry(Integer.parseInt(str));
      }
      paramSendReq.setPriority(129);
      if (!MessageUtils.requireDeliveryStatusBySimId(mContext, paramLong)) {
        break label133;
      }
      i = 128;
      label80:
      paramSendReq.setDeliveryReport(i);
      if (!localSharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(paramLong, "pref_key_mms_read_reports"), false)) {
        break label141;
      }
    }
    label133:
    label141:
    for (int i = j;; i = 129)
    {
      paramSendReq.setReadReport(i);
      return;
      paramSendReq.setExpiry(localSharedPreferences.getLong("pref_key_mms_expiry", 604800L));
      break;
      i = 129;
      break label80;
    }
  }
  
  public boolean sendMessage()
    throws MmsException
  {
    long l1 = MSimUtils.getSimIdBySlotId(mSlotId);
    Object localObject1 = MiuiPduPersister.getPduPersister(mContext);
    Object localObject2 = ((MiuiPduPersister)localObject1).load(mMessageUri);
    if (((GenericPdu)localObject2).getMessageType() != 128) {
      throw new MmsException("Invalid message: " + ((GenericPdu)localObject2).getMessageType());
    }
    Object localObject3 = (SendReq)localObject2;
    updatePreferencesHeaders((SendReq)localObject3, l1);
    ((SendReq)localObject3).setMessageClass("personal".getBytes());
    long l2 = System.currentTimeMillis();
    ((SendReq)localObject3).setDate(l2 / 1000L);
    ((SendReq)localObject3).setMessageSize(mMessageSize);
    ((MiuiPduPersister)localObject1).updateHeaders(mMessageUri, (SendReq)localObject3);
    localObject2 = new ContentValues();
    if (((SendReq)localObject3).getDate() != -1L) {
      ((ContentValues)localObject2).put("date_ms_part", Long.valueOf(l2 % 1000L));
    }
    ((ContentValues)localObject2).put("sim_id", Long.valueOf(l1));
    SqliteWrapper.update(mContext, mContext.getContentResolver(), mMessageUri, (ContentValues)localObject2, null, null);
    if (mTimeToSend > 0L)
    {
      localObject1 = ((MiuiPduPersister)localObject1).move(mMessageUri, Telephony.Mms.Sent.CONTENT_URI);
      MessageUtils.setMmsSendTime(mContext, (Uri)localObject1, mTimeToSend, System.currentTimeMillis());
      TimedMessageReceiver.scheduleNextTimedMsg(mContext);
    }
    for (;;)
    {
      return true;
      int j = 0;
      int i = 0;
      boolean bool2 = false;
      boolean bool1 = false;
      localObject2 = Lists.newArrayList();
      int m;
      int k;
      if ((mSendByMx) || (mSendByMxV2))
      {
        localObject3 = ((SendReq)localObject3).getTo();
        m = localObject3.length;
        k = 0;
      }
      for (;;)
      {
        j = i;
        bool2 = bool1;
        Object localObject4;
        if (k < m)
        {
          localObject4 = Contact.get(localObject3[k].getString());
          if (localObject4 != null) {
            ((ArrayList)localObject2).add(localObject4);
          }
          localObject4 = MxIdCache.getOrQuery(mContext, ((Contact)localObject4).getMxPhoneNumber());
          if (localObject4 == null)
          {
            j = 0;
            bool2 = bool1;
          }
        }
        else
        {
          l1 = ContentUris.parseId(mMessageUri);
          localObject2 = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l1);
          if ((j == 0) && (!bool2)) {
            break label439;
          }
          MxMessagePduHelper.markMmsSendAsMx(mContext, (Uri)localObject2, true);
          MxMmsTransactionService.startSendMms(mContext, (Uri)localObject2, bool2);
          break;
        }
        if (((MxIdCache.MxIdCacheItem)localObject4).allowMms()) {
          i = 1;
        }
        if (((MxIdCache.MxIdCacheItem)localObject4).allowMxV2()) {
          bool1 = true;
        }
        k += 1;
      }
      label439:
      if ((mSendByMx) || (mSendByMxV2))
      {
        MxMessagePduHelper.handleMxMmsFailed(mContext, l1);
      }
      else
      {
        ((MiuiPduPersister)localObject1).move(mMessageUri, Telephony.Mms.Outbox.CONTENT_URI);
        localObject1 = new Intent(mContext, TransactionService.class);
        ((Intent)localObject1).putExtra("uri", ((Uri)localObject2).toString());
        ((Intent)localObject1).putExtra("type", 2);
        mContext.startService((Intent)localObject1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsMessageSender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */