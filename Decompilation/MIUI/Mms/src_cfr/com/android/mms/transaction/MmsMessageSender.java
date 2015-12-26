/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.net.Uri
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$Mms$Sent
 *  com.google.android.collect.Lists
 *  com.google.android.mms.InvalidHeaderValueException
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.ReadRecInd
 *  com.google.android.mms.pdu.SendReq
 *  com.google.android.mms.util.SqliteWrapper
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  miui.os.Build
 */
package com.android.mms.transaction;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import com.android.mms.data.Contact;
import com.android.mms.transaction.MessageSender;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.transaction.TransactionService;
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
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.os.Build;

public class MmsMessageSender
implements MessageSender {
    private final Context mContext;
    private final long mMessageSize;
    private final Uri mMessageUri;
    private final boolean mSendByMx;
    private final boolean mSendByMxV2;
    private final int mSlotId;
    private final long mTimeToSend;

    public MmsMessageSender(Context context, Uri uri, long l, long l2, boolean bl, int n) {
        this(context, uri, l, l2, bl, n, false);
    }

    public MmsMessageSender(Context context, Uri uri, long l, long l2, boolean bl, int n, boolean bl2) {
        this.mContext = context;
        this.mMessageUri = uri;
        this.mMessageSize = l;
        this.mTimeToSend = l2;
        this.mSendByMx = bl;
        this.mSlotId = n;
        this.mSendByMxV2 = bl2;
        if (this.mMessageUri == null) {
            throw new IllegalArgumentException("Null message URI.");
        }
    }

    public static void sendReadRec(Context context, String string, String string2, int n) {
        string = new EncodedStringValue(string);
        try {
            string = new ReadRecInd(new EncodedStringValue("insert-address-token".getBytes()), string2.getBytes(), 18, n, new EncodedStringValue[]{string});
            string.setDate(System.currentTimeMillis() / 1000);
            MiuiPduPersister.getPduPersister((Context)context).persist((GenericPdu)string, Telephony.Mms.Outbox.CONTENT_URI, null, -1);
            context.startService(new Intent(context, (Class)TransactionService.class));
            return;
        }
        catch (InvalidHeaderValueException var0_1) {
            MyLog.e("MmsMessageSender", "Invalide header value", (Throwable)var0_1);
            return;
        }
        catch (MmsException var0_2) {
            MyLog.e("MmsMessageSender", "Persist message failed", (Throwable)var0_2);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updatePreferencesHeaders(SendReq sendReq, long l) throws MmsException {
        int n = 128;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this.mContext);
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            String string = sharedPreferences.getString(MSimUtils.createKeyWithSimId(l, "pref_key_mms_validity_period"), "0");
            if (!"0".equals((Object)string)) {
                sendReq.setExpiry((long)Integer.parseInt((String)string));
            }
        } else {
            sendReq.setExpiry(sharedPreferences.getLong("pref_key_mms_expiry", 604800));
        }
        sendReq.setPriority(129);
        int n2 = MessageUtils.requireDeliveryStatusBySimId(this.mContext, l) ? 128 : 129;
        sendReq.setDeliveryReport(n2);
        n2 = sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_read_reports"), false) ? n : 129;
        sendReq.setReadReport(n2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean sendMessage() throws MmsException {
        var5_1 = MSimUtils.getSimIdBySlotId(this.mSlotId);
        var11_2 = MiuiPduPersister.getPduPersister((Context)this.mContext);
        var12_3 = var11_2.load(this.mMessageUri);
        if (var12_3.getMessageType() != 128) {
            throw new MmsException("Invalid message: " + var12_3.getMessageType());
        }
        var13_4 = (EncodedStringValue[])var12_3;
        this.updatePreferencesHeaders((SendReq)var13_4, var5_1);
        var13_4.setMessageClass("personal".getBytes());
        var7_5 = System.currentTimeMillis();
        var13_4.setDate(var7_5 / 1000);
        var13_4.setMessageSize(this.mMessageSize);
        var11_2.updateHeaders(this.mMessageUri, (SendReq)var13_4);
        var12_3 = new ContentValues();
        if (var13_4.getDate() != -1) {
            var12_3.put("date_ms_part", Long.valueOf((long)(var7_5 % 1000)));
        }
        var12_3.put("sim_id", Long.valueOf((long)var5_1));
        SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)this.mMessageUri, (ContentValues)var12_3, (String)null, (String[])null);
        if (this.mTimeToSend > 0) {
            var11_2 = var11_2.move(this.mMessageUri, Telephony.Mms.Sent.CONTENT_URI);
            MessageUtils.setMmsSendTime(this.mContext, (Uri)var11_2, this.mTimeToSend, System.currentTimeMillis());
            TimedMessageReceiver.scheduleNextTimedMsg(this.mContext);
            return true;
        }
        var2_6 = false;
        var1_7 = false;
        var10_8 = false;
        var9_9 = false;
        var12_3 = Lists.newArrayList();
        if (!this.mSendByMx && !this.mSendByMxV2) ** GOTO lbl42
        var13_4 = var13_4.getTo();
        var4_10 = var13_4.length;
        var3_11 = 0;
        do {
            var2_6 = var1_7;
            var10_8 = var9_9;
            if (var3_11 >= var4_10) ** GOTO lbl42
            var14_12 = Contact.get(var13_4[var3_11].getString());
            if (var14_12 != null) {
                var12_3.add(var14_12);
            }
            if ((var14_12 = MxIdCache.getOrQuery(this.mContext, var14_12.getMxPhoneNumber())) == null) {
                var2_6 = false;
                var10_8 = var9_9;
lbl42: // 3 sources:
                var5_1 = ContentUris.parseId((Uri)this.mMessageUri);
                var12_3 = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)var5_1);
                if (!var2_6 && !var10_8) break;
                MxMessagePduHelper.markMmsSendAsMx(this.mContext, (Uri)var12_3, true);
                MxMmsTransactionService.startSendMms(this.mContext, (Uri)var12_3, var10_8);
                return true;
            }
            if (var14_12.allowMms()) {
                var1_7 = true;
            }
            if (var14_12.allowMxV2()) {
                var9_9 = true;
            }
            ++var3_11;
        } while (true);
        if (!this.mSendByMx && !this.mSendByMxV2) {
            var11_2.move(this.mMessageUri, Telephony.Mms.Outbox.CONTENT_URI);
            var11_2 = new Intent(this.mContext, (Class)TransactionService.class);
            var11_2.putExtra("uri", var12_3.toString());
            var11_2.putExtra("type", 2);
            this.mContext.startService((Intent)var11_2);
            return true;
        }
        MxMessagePduHelper.handleMxMmsFailed(this.mContext, var5_1);
        return true;
    }
}

