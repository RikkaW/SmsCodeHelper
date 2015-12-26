/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  com.google.android.mms.MmsException
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  miui.os.Build
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.SmsManager
 */
package com.android.mms.transaction;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import com.android.mms.MmsConfig;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.MessageStatusReceiver;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.transaction.SmsReceiver;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import miui.os.Build;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.SmsManager;

public class SmsSingleRecipientSender
extends SmsMessageSender {
    private String mDest;
    private final boolean mRequestDeliveryReport;
    private boolean mShowToastWhenOffline;
    private Uri mUri;

    public SmsSingleRecipientSender(Context context, String string, String string2, long l, boolean bl, Uri uri, int n, boolean bl2) {
        super(context, null, string2, l, 0, n);
        this.mRequestDeliveryReport = bl;
        this.mDest = string;
        this.mUri = uri;
        this.mShowToastWhenOffline = bl2;
    }

    private int getSmsValidityPeriod() {
        return Integer.parseInt((String)PreferenceManager.getDefaultSharedPreferences((Context)this.mContext).getString(MSimUtils.createKeyWithSimId(MSimUtils.getSimIdBySlotId(this.mSlotId), "pref_key_sms_validity_period"), "-1"));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean sendMessage() throws MmsException {
        SmsManager smsManager;
        int n;
        String string;
        int n2;
        if (this.mMessageText == null) {
            throw new MmsException("Null message body or have multiple destinations.");
        }
        SmsManager smsManager2 = SmsManager.getDefault();
        if (MmsConfig.getEmailGateway() != null && (Telephony.Mms.isEmailAddress((String)this.mDest) || MessageUtils.isAlias(this.mDest))) {
            string = this.mDest + " " + this.mMessageText;
            this.mDest = MmsConfig.getEmailGateway();
            smsManager2 = smsManager2.divideMessage(string);
        } else {
            smsManager2 = smsManager2.divideMessage(this.mMessageText);
            this.mDest = this.mDest.replaceAll(" ", "");
            this.mDest = Conversation.verifySingleRecipient(this.mContext, this.mThreadId, this.mDest);
        }
        if ((n = smsManager2.size()) == 0) {
            throw new MmsException("SmsMessageSender.sendMessage: divideMessage returned empty messages. Original message is \"" + this.mMessageText + "\"");
        }
        if (!Telephony.Sms.moveMessageToFolder((Context)this.mContext, (Uri)this.mUri, (int)4, (int)0)) {
            throw new MmsException("SmsMessageSender.sendMessage: couldn't move message to outbox: " + (Object)this.mUri);
        }
        string = new ArrayList(n);
        ArrayList arrayList = new ArrayList(n);
        for (n2 = 0; n2 < n; ++n2) {
            int n3;
            if (this.mRequestDeliveryReport && n2 == n - 1) {
                smsManager = new Intent("com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED", this.mUri, this.mContext, (Class)MessageStatusReceiver.class);
                smsManager.putExtra(MSimUtils.SLOT_ID, this.mSlotId);
                string.add((Object)PendingIntent.getBroadcast((Context)this.mContext, (int)0, (Intent)smsManager, (int)0));
            } else {
                string.add((Object)null);
            }
            smsManager = new Intent("com.android.mms.transaction.MESSAGE_SENT", this.mUri, this.mContext, (Class)SmsReceiver.class);
            smsManager.putExtra("show_toast_when_offline", this.mShowToastWhenOffline);
            smsManager.putExtra(MSimUtils.SLOT_ID, this.mSlotId);
            if (n2 == n - 1) {
                smsManager.putExtra("SendNextMsg", true);
                n3 = 1;
            } else {
                n3 = 0;
            }
            arrayList.add((Object)PendingIntent.getBroadcast((Context)this.mContext, (int)n3, (Intent)smsManager, (int)0));
        }
        try {
            smsManager = MSimUtils.getSmsManager(this.mSlotId);
            if (smsManager == null) {
                MyLog.d("SmsSingleRecipientSender", "get SmsManager is failed for slotId " + this.mSlotId);
                return false;
            }
            if (Build.IS_CM_CUSTOMIZATION_TEST) {
                n2 = this.getSmsValidityPeriod();
                smsManager.sendMultipartTextMessage(PhoneNumberUtils.stripSeparators((String)this.mDest), this.mServiceCenter, (ArrayList)smsManager2, arrayList, (ArrayList)string, -1, false, n2);
                return false;
            }
            smsManager.sendMultipartTextMessage(PhoneNumberUtils.stripSeparators((String)this.mDest), this.mServiceCenter, (ArrayList)smsManager2, arrayList, (ArrayList)string);
            return false;
        }
        catch (Exception var4_2) {
            MyLog.e("SmsSingleRecipientSender", "SmsMessageSender.sendMessage: caught", var4_2);
            throw new MmsException("SmsMessageSender.sendMessage: caught " + (Object)((Object)var4_2) + " from SmsManager.sendTextMessage()");
        }
    }
}

