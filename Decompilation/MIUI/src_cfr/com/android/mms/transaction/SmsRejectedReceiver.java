/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  miui.os.Build
 */
package com.android.mms.transaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.android.mms.ui.MmsTabActivity;
import com.android.mms.util.DownloadManager;
import miui.os.Build;

public class SmsRejectedReceiver
extends BroadcastReceiver {
    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onReceive(Context context, Intent intent) {
        int n;
        if (Settings.Secure.getInt((ContentResolver)context.getContentResolver(), (String)"device_provisioned", (int)0) != 1) return;
        if (!"android.provider.Telephony.SMS_REJECTED".equals((Object)intent.getAction())) return;
        if (intent.getIntExtra("result", -1) != 3) return;
        int n2 = 1;
        if (n2 == 0) {
            return;
        }
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            DownloadManager.getInstance().setOutOfMemory(true);
        }
        intent = (NotificationManager)context.getSystemService("notification");
        Intent intent2 = new Intent(context, (Class)MmsTabActivity.class);
        intent2.setAction("android.intent.action.VIEW");
        intent2.setFlags(872415232);
        intent2 = PendingIntent.getActivity((Context)context, (int)0, (Intent)intent2, (int)0);
        Notification notification = new Notification();
        notification.icon = 2130838005;
        if (n2 != 0) {
            n = 2131361990;
            n2 = 2131361991;
        } else {
            n = 2131361992;
            n2 = 2131361993;
        }
        notification.tickerText = context.getString(n);
        notification.defaults = -1;
        notification.setLatestEventInfo(context, (CharSequence)context.getString(n), (CharSequence)context.getString(n2), (PendingIntent)intent2);
        intent.notify(239, notification);
    }
}

