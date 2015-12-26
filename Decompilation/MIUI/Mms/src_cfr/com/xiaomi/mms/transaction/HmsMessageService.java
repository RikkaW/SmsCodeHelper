/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.IntentService
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$AntiSpam
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashSet
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$Hms
 */
package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MiuiSettings;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.VoiceReportUtils;
import java.util.HashSet;
import miui.provider.ExtraTelephony;

public class HmsMessageService
extends IntentService {
    private Context mContext = null;

    public HmsMessageService() {
        super("HmsMessageService");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void deleteAllHmsMessage(Context var1_1) {
        block6 : {
            block7 : {
                Log.d((String)"HmsMessageService", (String)"device login out , and start delete all hms message");
                var5_5 = var1_1.getContentResolver();
                var4_6 = var5_5.query(ExtraTelephony.Hms.CONTENT_URI, new String[]{"thread_id"}, null, null, null);
                var1_1 = var3_7 = null;
                if (var4_6 == null) ** GOTO lbl19
                var1_1 = var3_7;
                try {
                    if (!var4_6.moveToFirst()) break block6;
                    var1_1 = new HashSet();
                    break block7;
                }
                catch (Throwable var1_2) {}
                ** GOTO lbl-1000
            }
            try {
                do {
                    var1_1.add((int)var4_6.getInt(0));
                } while (var2_8 = var4_6.moveToNext());
            }
            catch (Throwable var1_4) {}
        }
        if (var4_6 != null && !var4_6.isClosed()) {
            var4_6.close();
        }
        if (var1_1 == null) return;
        var5_5.delete(Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"hms"), "_id IN  ( " + TextUtils.join((CharSequence)",", (Iterable)var1_1) + " )", null);
        return;
lbl-1000: // 2 sources:
        {
            if (var4_6 == null) throw var1_3;
            if (var4_6.isClosed() != false) throw var1_3;
            var4_6.close();
            throw var1_3;
        }
    }

    private void deleteEmptyHmsThread(long l) {
        if (l > 0) {
            Uri uri = Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"hms");
            this.getContentResolver().delete(uri, "_id =" + l + " AND " + "message_count=0", null);
        }
    }

    private void notifyHmsMessageArrivalAsync(Context context, String string2, String string3) {
        if (!MiuiSettings.AntiSpam.isQuietModeEnable((Context)context)) {
            VoiceReportUtils.voiceReport(context, string2, string3, 0);
        }
        MessagingNotification.blockingUpdateNewMessageIndicator(context, true, false);
    }

    public void onCreate() {
        super.onCreate();
        this.mContext = this.getBaseContext();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onHandleIntent(Intent object) {
        if ("com.xiaomi.mms.action_hms_notification".equals((Object)object.getAction())) {
            String string2 = object.getStringExtra("extra_hms_notification_address");
            object = object.getStringExtra("extra_hms_notification_body");
            this.notifyHmsMessageArrivalAsync(this.mContext, string2, (String)object);
            return;
        } else {
            if ("com.xiaomi.mms.action_del_hms_empty_thread".equals((Object)object.getAction())) {
                this.deleteEmptyHmsThread(object.getLongExtra("extra_hms_thread_id", -1));
                return;
            }
            if (!"com.xiaomi.mms.action_delete_hms_message".equals((Object)object.getAction())) return;
            {
                this.deleteAllHmsMessage(this.mContext);
                return;
            }
        }
    }
}

