/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Sent
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  com.google.android.mms.MmsException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashSet
 */
package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.transaction.MessageSender;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.HashSet;
import java.util.Set;

public class SmsMessageSender
implements MessageSender {
    private static final String[] SERVICE_CENTER_PROJECTION;
    private static final Uri URI_QUEUED;
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

    static {
        URI_QUEUED = Uri.parse((String)"content://sms/queued");
        SERVICE_CENTER_PROJECTION = new String[]{"reply_path_present", "service_center"};
    }

    public SmsMessageSender(Context context, String[] arrstring, String string, long l, long l2, int n) {
        this(context, arrstring, string, l, l2, false, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public SmsMessageSender(Context context, String[] hashSet, String string, long l, long l2, boolean bl, int n) {
        this.mSlotId = n;
        this.mContext = context;
        this.mMessageText = string;
        if (hashSet != null) {
            this.mNumberOfDests = hashSet.length;
            this.mDests = new String[this.mNumberOfDests];
            System.arraycopy((Object)hashSet, (int)0, (Object)this.mDests, (int)0, (int)this.mNumberOfDests);
        } else {
            this.mNumberOfDests = 0;
            this.mDests = null;
        }
        this.mTimeToSend = l2;
        this.mTimestamp = System.currentTimeMillis();
        if (l == 0) {
            hashSet = new HashSet();
            for (n = 0; n < this.mNumberOfDests; ++n) {
                hashSet.add((Object)this.mDests[n]);
            }
            this.mThreadId = Telephony.Threads.getOrCreateThreadId((Context)context, (Set)hashSet);
        } else {
            this.mThreadId = l;
        }
        this.mServiceCenter = this.getOutgoingServiceCenter(this.mThreadId);
        this.mSendByMx = bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String getOutgoingServiceCenter(long l) {
        Cursor cursor;
        Object object;
        Object object2;
        block7 : {
            boolean bl;
            block6 : {
                bl = true;
                object = null;
                try {
                    cursor = SqliteWrapper.query((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)Telephony.Sms.CONTENT_URI, (String[])SERVICE_CENTER_PROJECTION, (String)("thread_id = " + l), (String[])null, (String)"date DESC");
                    if (cursor != null) {
                        object = cursor;
                        boolean bl2 = cursor.moveToFirst();
                        if (bl2) break block6;
                    }
                    if (cursor == null) return null;
                }
                catch (Throwable var6_7) {
                    if (object == null) throw var6_7;
                    object.close();
                    throw var6_7;
                }
                cursor.close();
                return null;
            }
            object = cursor;
            if (1 != cursor.getInt(0)) {
                bl = false;
            }
            if (bl) {
                object = cursor;
                object = object2 = cursor.getString(1);
                break block7;
            }
            object = null;
        }
        object2 = object;
        if (cursor == null) return object2;
        cursor.close();
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean queueMessage() throws MmsException {
        if (this.mMessageText == null) throw new MmsException("Null message body or dest.");
        if (this.mNumberOfDests == 0) {
            throw new MmsException("Null message body or dest.");
        }
        if (MSimUtils.isMSimSlotIdValid(this.mSlotId)) {
            var3_1 = MxActivateService.isMxEnabled(this.mContext, this.mSlotId);
            var6_2 = MSimUtils.getSimIdBySlotId(this.mSlotId);
        } else {
            var3_1 = false;
            var6_2 = 0;
            LogTag.verbose("slot id is not valid", new Object[0]);
        }
        var5_3 = MessageUtils.requireDeliveryStatusBySimId(this.mContext, var6_2);
        LogTag.debug("\u662f\u5426\u83b7\u53d6\u53d1\u9001\u62a5\u544a:%b", new Object[]{var5_3});
        if (this.mTimeToSend > 0) {
            var13_4 = Telephony.Sms.Sent.CONTENT_URI;
            var8_5 = this.mTimeToSend;
        } else {
            var13_4 = SmsMessageSender.URI_QUEUED;
            var8_5 = this.mTimestamp;
        }
        var10_6 = System.currentTimeMillis();
        var1_7 = 0;
        do {
            if (var1_7 >= this.mNumberOfDests) ** GOTO lbl49
            var2_8 = var4_9 = this.mSendByMx;
            if (var3_1) {
                var2_8 = var4_9;
                if (!var4_9) {
                    var2_8 = var4_9;
                    if (PushSession.getInstance(this.mContext).isConnected(this.mSlotId)) {
                        var2_8 = var4_9;
                        if (this.mNumberOfDests > 1) {
                            var12_10 = Contact.get(this.mDests[var1_7]);
                            var2_8 = (var12_10 = MxIdCache.getOrQuery(this.mContext, var12_10.getMxPhoneNumber())) != null && var12_10.allowSms() != false;
                        }
                    }
                }
            }
            if (this.mTimeToSend > 0 && !(var2_8 = false)) ** GOTO lbl43
            try {
                var12_10 = MSimUtils.addMiMessageToUri(this.mContext.getContentResolver(), SmsMessageSender.URI_QUEUED, this.mDests[var1_7], this.translateWithNickname(this.mDests[var1_7]), null, var8_5, true, var5_3, this.mThreadId, var6_2, B2cMessageUtils.getPossibleLastB2cNumber(this.mContext, this.mDests[var1_7]));
            }
            catch (SQLiteException var12_11) {
                MyLog.e("SmsMessageSender", "failed to queue message, tid: " + this.mThreadId);
                SqliteWrapper.checkSQLiteException((Context)this.mContext, (SQLiteException)var12_11);
                var12_10 = null;
            }
            ** GOTO lbl58
lbl43: // 2 sources:
            var12_10 = MSimUtils.addMessageToUri(this.mContext.getContentResolver(), var13_4, this.mDests[var1_7], this.translateWithNickname(this.mDests[var1_7]), null, var8_5, true, var5_3, this.mThreadId, var6_2);
            try {
                MyLog.d("SmsMessageSender", "message queued, uri:" + var12_10);
            }
            catch (SQLiteException var14_13) {}
            ** GOTO lbl-1000
lbl49: // 1 sources:
            if (this.mTimeToSend > 0) {
                TimedMessageReceiver.scheduleNextTimedMsg(this.mContext);
                return false;
            }
            LogTag.debug("\u5e7f\u64adSmsReceiverService.ACTION_SEND_MESSAGE", new Object[0]);
            MSimUtils.sendQueuedMessage(this.mContext, this.mSlotId);
            return false;
            catch (SQLiteException var14_14) {
                var12_10 = null;
            }
lbl-1000: // 2 sources:
            {
                SqliteWrapper.checkSQLiteException((Context)this.mContext, (SQLiteException)var14_12);
            }
lbl58: // 4 sources:
            if (this.mTimeToSend > 0 && var12_10 != null) {
                MessageUtils.setSmsSendTime(this.mContext, (Uri)var12_10, this.mTimeToSend, var10_6);
            }
            ++var1_7;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private String translateWithNickname(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while (n < this.mMessageText.length()) {
            char c2 = this.mMessageText.charAt(n);
            if (c2 == '\uffff') {
                Object object = Contact.get(string).load(true, false);
                String string2 = object.getNickname();
                object = object.getRealName();
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    stringBuilder.append(string2);
                } else if (!TextUtils.isEmpty((CharSequence)object)) {
                    stringBuilder.append((String)object);
                }
            } else {
                stringBuilder.append(c2);
            }
            ++n;
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean sendMessage() throws MmsException {
        return this.queueMessage();
    }
}

