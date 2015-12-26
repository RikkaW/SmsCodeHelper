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
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.logger.MyLog;

public class MxResendService
extends IntentService {
    private static final Uri SMS_URI = Uri.parse((String)"content://sms");

    public MxResendService() {
        super("MxResendService");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        int n = MSimUtils.getSlotIdFromIntent(intent);
        intent = new ContentValues();
        intent.put("mx_status", Integer.valueOf((int)1));
        intent.put("type", Integer.valueOf((int)6));
        int n2 = SqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)SMS_URI, (ContentValues)intent, (String)"type=4 and mx_status=1", (String[])null);
        MyLog.i("MxResendService", "dropped msg resent, count:" + n2);
        if (n2 > 0) {
            MSimUtils.sendQueuedMessage((Context)this, n);
        }
        n = MxMessagePduHelper.moveUnsentMessageToPending((Context)this);
        MyLog.i("MxResendService", "resend dropped mms, count: " + n);
        if (n <= 0) return;
        this.startService(new Intent((Context)this, (Class)MxMmsTransactionService.class));
    }
}

