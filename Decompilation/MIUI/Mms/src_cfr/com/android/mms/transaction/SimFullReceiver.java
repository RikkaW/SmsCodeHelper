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
import com.android.mms.ui.ManageSimMessages;
import com.android.mms.util.MSimUtils;

public class SimFullReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Settings.Secure.getInt((ContentResolver)context.getContentResolver(), (String)"device_provisioned", (int)0) == 1 && "android.provider.Telephony.SIM_FULL".equals((Object)intent.getAction())) {
            NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            int n = MSimUtils.getSlotIdFromIntent(intent);
            intent = new Intent(context, (Class)ManageSimMessages.class);
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.putExtra(MSimUtils.SLOT_ID, n);
            intent = PendingIntent.getActivity((Context)context, (int)0, (Intent)intent, (int)0);
            Notification notification = new Notification();
            notification.icon = 2130838005;
            notification.tickerText = context.getString(2131361800);
            notification.defaults = -1;
            notification.setLatestEventInfo(context, (CharSequence)context.getString(2131361800), (CharSequence)context.getString(2131361989), (PendingIntent)intent);
            notificationManager.notify(234, notification);
        }
    }
}

