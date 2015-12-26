/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.NotificationManager
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.mms.ui.NewMessagePopupActivity;
import com.android.mms.util.Reminder;
import com.android.mms.util.VibratorManager;

public class NewMessageNotificationDismissReceiver
extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        NewMessagePopupActivity.dismiss(context);
        Reminder.cancelReminder(context);
        ((NotificationManager)context.getSystemService("notification")).cancel(123);
        VibratorManager.cancel();
    }
}

