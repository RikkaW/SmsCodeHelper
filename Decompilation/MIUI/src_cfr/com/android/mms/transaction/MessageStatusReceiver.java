/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.telephony.SmsMessage
 *  android.text.TextUtils
 *  android.util.Log
 *  com.android.internal.telephony.SmsMessageBase
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Method
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.SmsMessageBase;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.lang.reflect.Method;
import miui.telephony.TelephonyManager;

public class MessageStatusReceiver
extends BroadcastReceiver {
    private static final String[] ID_PROJECTION = new String[]{"_id"};
    private static final Uri STATUS_URI = Uri.parse((String)"content://sms/status");
    private int mStatus = 0;

    /*
     * Enabled aggressive block sorting
     */
    private boolean checkSpecialFailedStatus(String string, int n, int n2) {
        if (TextUtils.isEmpty((CharSequence)string)) {
            Log.v((String)"MessageStatusReceiver", (String)"checkSpecialFailedStatus dest is empty");
            return false;
        } else {
            String string2 = TelephonyManager.getDefault().getSimOperatorForSlot(n2);
            if (TextUtils.isEmpty((CharSequence)string2)) {
                Log.v((String)"MessageStatusReceiver", (String)("checkSpecialFailedStatus plmn is empty for slotId " + n2));
                return false;
            }
            if (n != 67 || !"46009".equalsIgnoreCase(string2) || !"10010".equals((Object)string) && !"+8610010".equals((Object)string)) return false;
            {
                Log.v((String)"MessageStatusReceiver", (String)"checkSpecialFailedStatus is right");
                return true;
            }
        }
    }

    private void error(String string) {
        MyLog.e("MessageStatusReceiver", "[MessageStatusReceiver] " + string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getRecipientAddress(SmsMessage object) {
        object = object.mWrappedSmsMessage;
        Method method = SmsMessageBase.class.getDeclaredMethod("getRecipientAddress", new Class[0]);
        Log.v((String)"MessageStatusReceiver", (String)"status getRecipientAddress is abstract");
        try {
            return (String)method.invoke(object, new Object[0]);
        }
        catch (Exception var1_2) {
            try {
                Log.v((String)"MessageStatusReceiver", (String)("Couldn't invoke this method" + (Object)((Object)var1_2)));
                do {
                    return "";
                    break;
                } while (true);
            }
            catch (NoSuchMethodException var1_3) {
                var1_3.printStackTrace();
                return "";
            }
        }
    }

    private void handleSendFailed(int n, ContentValues contentValues) {
        if (n >= 64) {
            contentValues.put("type", Integer.valueOf((int)5));
            contentValues.put("read", Integer.valueOf((int)0));
            contentValues.put("error_code", Integer.valueOf((int)0));
        }
    }

    private void log(String string) {
        MyLog.d("MessageStatusReceiver", "[MessageStatusReceiver] " + string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private SmsMessage updateMessageStatus(Context context, Uri uri, byte[] object, String string, int n) {
        if ((string = SmsMessage.createFromPdu((byte[])object, (String)string)) == null) {
            return null;
        }
        this.mStatus = string.getStatus() & 65535;
        if (this.checkSpecialFailedStatus(this.getRecipientAddress((SmsMessage)string), this.mStatus, n)) {
            this.mStatus = 0;
        }
        if ((object = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri, (String[])ID_PROJECTION, (String)null, (String[])null, (String)null)) == null) {
            return null;
        }
        try {
            if (object.moveToFirst()) {
                n = object.getInt(0);
                Uri uri2 = ContentUris.withAppendedId((Uri)STATUS_URI, (long)n);
                n = this.mStatus;
                boolean bl = string.isStatusReportMessage();
                ContentValues contentValues = new ContentValues();
                if (Log.isLoggable((String)"Mms", (int)3)) {
                    this.log("updateMessageStatus: msgUrl=" + (Object)uri + ", status=" + n + ", isStatusReport=" + bl);
                }
                long l = System.currentTimeMillis();
                contentValues.put("status", Integer.valueOf((int)n));
                contentValues.put("date_sent", Long.valueOf((long)l));
                this.handleSendFailed(n, contentValues);
                SqliteWrapper.update((Context)context, (ContentResolver)context.getContentResolver(), (Uri)uri2, (ContentValues)contentValues, (String)null, (String[])null);
                do {
                    return string;
                    break;
                } while (true);
            }
            this.error("Can't find message for status update: " + (Object)uri);
            return string;
        }
        finally {
            object.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(Context context, Intent intent) {
        if (!"com.android.mms.transaction.MessageStatusReceiver.MESSAGE_STATUS_RECEIVED".equals((Object)intent.getAction())) return;
        {
            Uri uri = intent.getData();
            byte[] arrby = (byte[])intent.getExtra("pdu");
            String string = intent.getStringExtra("format");
            int n = MSimUtils.getSlotIdFromIntent(intent);
            MyLog.d("MessageStatusReceiver", "sms delivered, uri: " + (Object)uri);
            this.mStatus = 0;
            intent = this.updateMessageStatus(context, uri, arrby, string, n);
            if (intent == null || !intent.isStatusReportMessage()) return;
            {
                if (this.mStatus == 0) {
                    MessagingNotification.nonBlockingUpdateDeliveryInfo(context, uri);
                    return;
                } else {
                    if (this.mStatus < 64) return;
                    {
                        MessagingNotification.notifySendFailed(context, true, n);
                        return;
                    }
                }
            }
        }
    }
}

