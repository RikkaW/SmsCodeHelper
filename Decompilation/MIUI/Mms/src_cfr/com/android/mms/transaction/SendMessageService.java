/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package com.android.mms.transaction;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.MmsApp;
import com.android.mms.data.Conversation;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.transaction.TimedMessageReceiver;
import com.android.mms.transaction.TransactionService;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.Iterator;

public class SendMessageService
extends IntentService {
    private static Uri TIMED_MMS_URI;
    private static Uri TIMED_SMS_URI;

    static {
        TIMED_SMS_URI = Uri.parse((String)"content://sms/sent");
        TIMED_MMS_URI = Uri.parse((String)"content://mms/sent");
    }

    public SendMessageService() {
        super("SendMessageService");
    }

    private void handleSendTimedMessages() {
        int n;
        int n2;
        ContentResolver contentResolver;
        ArrayList arrayList;
        Object object;
        long l;
        String string;
        Object object2;
        l = System.currentTimeMillis();
        object = new ArrayList();
        arrayList = new ArrayList();
        contentResolver = this.getContentResolver();
        object2 = TIMED_SMS_URI;
        string = "timed > 0 AND date <= " + l + " AND date >= " + MmsApp.getStartupTime();
        if ((contentResolver = SqliteWrapper.query((Context)this, (ContentResolver)contentResolver, (Uri)object2, (String[])new String[]{"_id", "sim_id"}, (String)string, (String[])null, (String)null)) != null) {
            MyLog.i("SendMessageService", "get timed sms: " + contentResolver.getCount());
            try {
                while (contentResolver.moveToNext()) {
                    object.add(contentResolver.getLong(0));
                    arrayList.add(contentResolver.getLong(1));
                }
            }
            finally {
                contentResolver.close();
            }
        }
        contentResolver = new ContentValues(3);
        contentResolver.put("timed", Integer.valueOf((int)0));
        contentResolver.put("type", Integer.valueOf((int)6));
        object2 = object.iterator();
        n = 0;
        n2 = 0;
        while (object2.hasNext()) {
            string = (Long)object2.next();
            contentResolver.put("date", Long.valueOf((long)((long)n2 + l)));
            n = SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_SMS_URI, (ContentValues)contentResolver, (String)("_id=" + string), (String[])null) + n;
            ++n2;
        }
        if (n > 0) {
            MSimUtils.sendQueuedMessage((Context)this, MessageUtils.getSlotId(arrayList));
        }
        arrayList.clear();
        object.clear();
        arrayList = this.getContentResolver();
        object2 = TIMED_MMS_URI;
        string = "timed > 0 AND date*1000+date_ms_part <= " + l + " AND date*1000+date_ms_part >= " + MmsApp.getStartupTime();
        arrayList = SqliteWrapper.query((Context)this, (ContentResolver)arrayList, (Uri)object2, (String[])new String[]{"_id"}, (String)string, (String[])null, (String)null);
        if (arrayList != null) {
            MyLog.i("SendMessageService", "get time mms: " + arrayList.getCount());
            try {
                while (arrayList.moveToNext()) {
                    object.add(arrayList.getLong(0));
                }
            }
            finally {
                arrayList.close();
            }
        }
        contentResolver.clear();
        contentResolver.put("timed", Integer.valueOf((int)0));
        contentResolver.put("msg_box", Integer.valueOf((int)4));
        object = object.iterator();
        int n3 = 0;
        int n4 = n2;
        n2 = n3;
        while (object.hasNext()) {
            arrayList = (Long)object.next();
            contentResolver.put("date_full", Long.valueOf((long)((long)n4 + l)));
            n3 = SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_MMS_URI, (ContentValues)contentResolver, (String)("_id=" + (Object)arrayList), (String[])null);
            ++n4;
            n2 = n3 + n2;
        }
        if (n2 > 0) {
            this.startService(new Intent((Context)this, (Class)TransactionService.class));
        }
        if (n > 0 || n2 > 0) {
            TimedMessageReceiver.scheduleNextTimedMsg((Context)this);
        }
    }

    private void markThreadAsRead(String object) {
        if ((object = Conversation.get(Telephony.Threads.getOrCreateThreadId((Context)this, (String)object))) != null) {
            object.markAsReadSync();
        }
    }

    public static void startDelete(Context context, Uri uri) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.ACTION_DELETE_BACKGROUND");
        intent.putExtra("extra_uri", uri.toString());
        context.startService(intent);
    }

    public static void startMarkFail(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.MARK_FAIL_BACKGROUND");
        intent.putStringArrayListExtra("extra_mms_msg_ids", arrayList);
        intent.putStringArrayListExtra("extra_sms_msg_ids", arrayList2);
        context.startService(intent);
    }

    public static void startMarkRead(Context context, String string) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.ACTION_MARK_READ_BACKGROUND");
        intent.putExtra("extra_address", string);
        context.startService(intent);
    }

    public static void startReSendMms(Context context, ArrayList<String> arrayList) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.RESEND_MMS_BACKGROUND");
        intent.putStringArrayListExtra("extra_mms_msg_ids", arrayList);
        context.startService(intent);
    }

    public static void startReSendSms(Context context, ArrayList<String> arrayList, int n) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.sms.transaction.RESEND_SMS_BACKGROUND");
        intent.putStringArrayListExtra("extra_sms_msg_ids", arrayList);
        intent.putExtra(MSimUtils.SLOT_ID, n);
        context.startService(intent);
    }

    public static void startSend(Context context, String string, String string2, boolean bl, int n) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.SEND_SMS_BACKGROUND");
        intent.putExtra("extra_address", string);
        intent.putExtra("extra_text", string2);
        intent.putExtra("extra_send_by_mx", bl);
        intent.putExtra(MSimUtils.SLOT_ID, n);
        context.startService(intent);
    }

    public static void startSendTimedMsg(Context context) {
        Intent intent = new Intent(context, (Class)SendMessageService.class);
        intent.setAction("com.android.mms.transaction.ACTION_SEND_TIMED_MESSAGE");
        context.startService(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onHandleIntent(Intent object) {
        String string = object.getAction();
        if ("com.android.mms.transaction.SEND_SMS_BACKGROUND".equals((Object)string)) {
            string = object.getStringExtra("extra_address");
            String string2 = object.getStringExtra("extra_text");
            boolean bl = object.getBooleanExtra("extra_send_by_mx", false);
            int n = MSimUtils.getSlotIdFromIntent((Intent)object);
            this.markThreadAsRead(string);
            long l = Telephony.Threads.getOrCreateThreadId((Context)this, (String)string);
            object = new SmsMessageSender((Context)this, new String[]{string}, string2, l, 0, bl, n);
            try {
                object.sendMessage();
                return;
            }
            catch (Exception var1_2) {
                MyLog.e("SendMessageService", "Failed to send SMS message, threadId=" + l, var1_2);
                return;
            }
        } else if ("com.android.sms.transaction.RESEND_SMS_BACKGROUND".equals((Object)string)) {
            string = new ContentValues();
            int n = MSimUtils.getSlotIdFromIntent((Intent)object);
            long l = System.currentTimeMillis();
            object = object.getStringArrayListExtra("extra_sms_msg_ids");
            String string3 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object) + ")";
            if (object.size() <= 0) {
                MyLog.v("SendMessageService", "smsWhere == NULL");
                return;
            }
            string.put("date", Long.valueOf((long)l));
            string.put("timed", Integer.valueOf((int)0));
            string.put("type", Integer.valueOf((int)6));
            MyLog.v("SendMessageService", "smsWhere == " + string3);
            if (SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_SMS_URI, (ContentValues)string, (String)string3, (String[])null) <= 0) return;
            {
                MSimUtils.sendQueuedMessage((Context)this, n);
                return;
            }
        } else if ("com.android.mms.transaction.RESEND_MMS_BACKGROUND".equals((Object)string)) {
            string = new ContentValues();
            long l = System.currentTimeMillis();
            object = object.getStringArrayListExtra("extra_mms_msg_ids");
            String string4 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object) + ")";
            if (object.size() <= 0) {
                MyLog.v("SendMessageService", "mmsWhere == NULL");
                return;
            }
            string.put("date_full", Long.valueOf((long)l));
            string.put("timed", Integer.valueOf((int)0));
            string.put("msg_box", Integer.valueOf((int)4));
            if (SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_MMS_URI, (ContentValues)string, (String)string4, (String[])null) <= 0) return;
            {
                this.startService(new Intent((Context)this, (Class)TransactionService.class));
                return;
            }
        } else if ("com.android.mms.transaction.MARK_FAIL_BACKGROUND".equals((Object)string)) {
            string = new ContentValues();
            long l = System.currentTimeMillis();
            Object object2 = object.getStringArrayListExtra("extra_mms_msg_ids");
            String string5 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object2) + ")";
            String string6 = "msg_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object2) + ")";
            if (object2.size() > 0) {
                string.put("date_full", Long.valueOf((long)l));
                string.put("timed", Integer.valueOf((int)0));
                string.put("msg_box", Integer.valueOf((int)4));
                SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_MMS_URI, (ContentValues)string, (String)string5, (String[])null);
                string.clear();
                string.put("err_type", Integer.valueOf((int)10));
                SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)string, (String)string6, (String[])null);
            } else {
                MyLog.v("SendMessageService", "mark fail == NULL");
            }
            object = object.getStringArrayListExtra("extra_sms_msg_ids");
            object2 = "_id IN (" + TextUtils.join((CharSequence)",", (Iterable)object) + ")";
            if (object.size() <= 0) return;
            {
                MyLog.v("SendMessageService", "mark sms fail");
                string.clear();
                string.put("date", Long.valueOf((long)l));
                string.put("timed", Integer.valueOf((int)0));
                string.put("type", Integer.valueOf((int)5));
                SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)TIMED_SMS_URI, (ContentValues)string, (String)object2, (String[])null);
                return;
            }
        } else {
            if ("com.android.mms.transaction.ACTION_MARK_READ_BACKGROUND".equals((Object)string)) {
                this.markThreadAsRead(object.getStringExtra("extra_address"));
                return;
            }
            if ("com.android.mms.transaction.ACTION_DELETE_BACKGROUND".equals((Object)string)) {
                object = Uri.parse((String)object.getStringExtra("extra_uri"));
                this.getContentResolver().delete((Uri)object, null, null);
                return;
            }
            if (!"com.android.mms.transaction.ACTION_SEND_TIMED_MESSAGE".equals((Object)string)) return;
            {
                this.handleSendTimedMessages();
                return;
            }
        }
    }
}

