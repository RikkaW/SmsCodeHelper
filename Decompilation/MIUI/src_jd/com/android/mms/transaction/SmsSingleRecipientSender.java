package com.android.mms.transaction;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import com.android.mms.MmsConfig;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SmsManager;

public class SmsSingleRecipientSender
  extends SmsMessageSender
{
  private String mDest;
  private final boolean mRequestDeliveryReport;
  private boolean mShowToastWhenOffline;
  private Uri mUri;
  
  public SmsSingleRecipientSender(Context paramContext, String paramString1, String paramString2, long paramLong, boolean paramBoolean1, Uri paramUri, int paramInt, boolean paramBoolean2)
  {
    super(paramContext, null, paramString2, paramLong, 0L, paramInt);
    mRequestDeliveryReport = paramBoolean1;
    mDest = paramString1;
    mUri = paramUri;
    mShowToastWhenOffline = paramBoolean2;
  }
  
  private int getSmsValidityPeriod()
  {
    return Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(mContext).getString(MSimUtils.createKeyWithSimId(MSimUtils.getSimIdBySlotId(mSlotId), "pref_key_sms_validity_period"), "-1"));
  }
  
  public boolean sendMessage()
    throws MmsException
  {
    if (mMessageText == null) {
      throw new MmsException("Null message body or have multiple destinations.");
    }
    Object localObject1 = SmsManager.getDefault();
    if ((MmsConfig.getEmailGateway() != null) && ((Telephony.Mms.isEmailAddress(mDest)) || (MessageUtils.isAlias(mDest))))
    {
      localObject2 = mDest + " " + mMessageText;
      mDest = MmsConfig.getEmailGateway();
      localObject1 = ((SmsManager)localObject1).divideMessage((String)localObject2);
    }
    int k;
    for (;;)
    {
      k = ((ArrayList)localObject1).size();
      if (k != 0) {
        break;
      }
      throw new MmsException("SmsMessageSender.sendMessage: divideMessage returned empty messages. Original message is \"" + mMessageText + "\"");
      localObject1 = ((SmsManager)localObject1).divideMessage(mMessageText);
      mDest = mDest.replaceAll(" ", "");
      mDest = Conversation.verifySingleRecipient(mContext, mThreadId, mDest);
    }
    if (!Telephony.Sms.moveMessageToFolder(mContext, mUri, 4, 0)) {
      throw new MmsException("SmsMessageSender.sendMessage: couldn't move message to outbox: " + mUri);
    }
    Object localObject2 = new ArrayList(k);
    ArrayList localArrayList = new ArrayList(k);
    int i = 0;
    Object localObject3;
    if (i < k) {
      if ((mRequestDeliveryReport) && (i == k - 1))
      {
        localObject3 = new Intent("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED", mUri, mContext, MessageStatusReceiver.class);
        ((Intent)localObject3).putExtra(MSimUtils.SLOT_ID, mSlotId);
        ((ArrayList)localObject2).add(PendingIntent.getBroadcast(mContext, 0, (Intent)localObject3, 0));
        label326:
        localObject3 = new Intent("com.android.mms.transaction.MESSAGE_SENT", mUri, mContext, SmsReceiver.class);
        ((Intent)localObject3).putExtra("show_toast_when_offline", mShowToastWhenOffline);
        ((Intent)localObject3).putExtra(MSimUtils.SLOT_ID, mSlotId);
        if (i != k - 1) {
          break label573;
        }
        ((Intent)localObject3).putExtra("SendNextMsg", true);
      }
    }
    label573:
    for (int j = 1;; j = 0)
    {
      localArrayList.add(PendingIntent.getBroadcast(mContext, j, (Intent)localObject3, 0));
      i += 1;
      break;
      ((ArrayList)localObject2).add(null);
      break label326;
      try
      {
        localObject3 = MSimUtils.getSmsManager(mSlotId);
        if (localObject3 == null)
        {
          MyLog.d("SmsSingleRecipientSender", "get SmsManager is failed for slotId " + mSlotId);
          return false;
        }
        if (Build.IS_CM_CUSTOMIZATION_TEST)
        {
          i = getSmsValidityPeriod();
          ((SmsManager)localObject3).sendMultipartTextMessage(PhoneNumberUtils.stripSeparators(mDest), mServiceCenter, (ArrayList)localObject1, localArrayList, (ArrayList)localObject2, -1, false, i);
          return false;
        }
      }
      catch (Exception localException)
      {
        MyLog.e("SmsSingleRecipientSender", "SmsMessageSender.sendMessage: caught", localException);
        throw new MmsException("SmsMessageSender.sendMessage: caught " + localException + " from SmsManager.sendTextMessage()");
      }
      ((SmsManager)localObject3).sendMultipartTextMessage(PhoneNumberUtils.stripSeparators(mDest), mServiceCenter, localException, localArrayList, (ArrayList)localObject2);
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SmsSingleRecipientSender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */