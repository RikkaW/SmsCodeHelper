/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.Application
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.preference.PreferenceManager
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.update;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.update.TemplateRequest;
import com.android.mms.update.TimedUpdateReceiver;

public class UpdateManager {
    public static void init() {
        Application application = MmsApp.getApp();
        long l = PreferenceManager.getDefaultSharedPreferences((Context)application).getLong("update_time_preference", 0);
        if (l == 0) {
            Log.v((String)"UpdateManager", (String)" no need updates, ignore");
            return;
        }
        if (l < System.currentTimeMillis()) {
            Log.v((String)"UpdateManager", (String)" update immediately! ");
            TemplateRequest.tryUpdateTemplate();
            UpdateManager.onUpdated();
            return;
        }
        Log.v((String)"UpdateManager", (String)(" schedule update at: " + l));
        UpdateManager.updateAt((Context)application, l);
    }

    public static void onUpdated() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)MmsApp.getApp());
        sharedPreferences.edit().putLong("update_time_preference", 0).commit();
        sharedPreferences.edit().putString("update_content_preference", "").commit();
    }

    public static void requestUpdateAt(long l, String string2) {
        Application application = MmsApp.getApp();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)application);
        sharedPreferences.edit().putLong("update_time_preference", l).commit();
        sharedPreferences.edit().putString("update_content_preference", string2).commit();
        UpdateManager.updateAt((Context)application, l);
    }

    public static void updateAt(Context context, long l) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService("alarm");
        Intent intent = new Intent(context, (Class)TimedUpdateReceiver.class);
        intent.setAction("com.android.mms.update.UPDATE_TEMPLATE");
        alarmManager.set(0, l, PendingIntent.getBroadcast((Context)context, (int)0, (Intent)intent, (int)268435456));
    }
}

