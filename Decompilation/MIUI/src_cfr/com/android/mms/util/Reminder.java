/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.KeyguardManager
 *  android.app.Notification
 *  android.app.NotificationManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.util;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.VibratorManager;

public class Reminder
extends BroadcastReceiver {
    private static String ACTION_REMINDER;
    private static long INTERVAL_FIVE_MINUTES;
    private static String TAG;
    private static Notification reminderNotification;
    private static int sReminderRepeatCount;
    private static final Object sReminderRepeatCountLock;

    static {
        TAG = "Reminder";
        INTERVAL_FIVE_MINUTES = 300000;
        ACTION_REMINDER = "com.android.mms.action.Reminder";
        reminderNotification = null;
        sReminderRepeatCount = 0;
        sReminderRepeatCountLock = new Object();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void cancelReminder(Context context) {
        Object object = sReminderRepeatCountLock;
        synchronized (object) {
            if (reminderNotification != null) {
                reminderNotification = null;
                sReminderRepeatCount = 0;
                ((AlarmManager)context.getSystemService("alarm")).cancel(Reminder.getSender(context));
            }
            return;
        }
    }

    private static int getPrefRepeatCount(SharedPreferences object) {
        object = object.getString("pref_key_ringtone_repeat", "1");
        try {
            int n = Integer.parseInt((String)object);
            return n;
        }
        catch (NumberFormatException var0_1) {
            return 1;
        }
    }

    private static PendingIntent getSender(Context context) {
        Intent intent = new Intent(context, (Class)Reminder.class);
        intent.setAction(ACTION_REMINDER);
        return PendingIntent.getBroadcast((Context)context, (int)0, (Intent)intent, (int)134217728);
    }

    public static void newNotification(Context context, Notification notification) {
        Reminder.cancelReminder(context);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (!MessageUtils.getPrefNotificationEnabled(context)) {
            return;
        }
        KeyguardManager keyguardManager = (KeyguardManager)context.getSystemService("keyguard");
        int n = Reminder.getPrefRepeatCount(sharedPreferences);
        if (n == 1 || !keyguardManager.inKeyguardRestrictedInputMode()) {
            Reminder.updateNotification(context, notification);
            return;
        }
        Reminder.updateNotification(context, notification);
        Reminder.setupReminder(context, notification, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void setupReminder(Context context, Notification notification, int n) {
        Object object = sReminderRepeatCountLock;
        synchronized (object) {
            reminderNotification = notification;
            sReminderRepeatCount = n - 1;
        }
        ((AlarmManager)context.getSystemService("alarm")).setRepeating(0, System.currentTimeMillis() + INTERVAL_FIVE_MINUTES, INTERVAL_FIVE_MINUTES, Reminder.getSender(context));
    }

    private static void updateNotification(Context context, Notification notification) {
        VibratorManager.vibrate(context);
        ((NotificationManager)context.getSystemService("notification")).notify(123, notification);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onReceive(Context context, Intent object) {
        if ("android.intent.action.USER_PRESENT".equals(object = object.getAction())) {
            Reminder.cancelReminder(context);
            return;
        }
        if (!ACTION_REMINDER.equals(object)) return;
        {
            if (!MessageUtils.getPrefNotificationEnabled(context)) {
                Log.v((String)TAG, (String)"user cancel for disable notification");
                Reminder.cancelReminder(context);
                return;
            }
        }
        object = sReminderRepeatCountLock;
        synchronized (object) {
            if (sReminderRepeatCount > 0) {
                Reminder.updateNotification(context, reminderNotification);
            }
            if (--sReminderRepeatCount > 0) return;
            {
                Reminder.cancelReminder(context);
            }
            return;
        }
    }
}

