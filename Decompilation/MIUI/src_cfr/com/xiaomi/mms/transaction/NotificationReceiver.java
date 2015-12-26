/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.util.Log
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.MessagingNotification;

public class NotificationReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("com.xiaomi.mms.UPDATE_NOTIFICATION".equalsIgnoreCase(intent.getAction())) {
            boolean bl = intent.getBooleanExtra("extra_is_new", false);
            MessagingNotification.nonBlockingUpdateNewMessageIndicator(context, bl, false);
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.d((String)"NotificationReceiver", (String)("get a action: com.xiaomi.mms.UPDATE_NOTIFICATION, isNew=" + bl));
            }
        }
    }
}

