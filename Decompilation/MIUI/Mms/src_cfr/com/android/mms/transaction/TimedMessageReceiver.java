/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.PendingIntent
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  java.lang.Class
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.transaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import com.android.mms.LogTag;
import com.android.mms.transaction.SendMessageService;
import com.android.mms.ui.MessageUtils;

public class TimedMessageReceiver
extends BroadcastReceiver {
    private static final Uri TIMED_MMS_URI;
    private static final Uri TIMED_SMS_URI;

    static {
        TIMED_SMS_URI = Uri.parse((String)"content://sms/sent");
        TIMED_MMS_URI = Uri.parse((String)"content://mms/sent");
    }

    private static long getFirstTimedMsgTime(Context context) {
        long l;
        Cursor cursor;
        block9 : {
            long l2;
            block8 : {
                l = Long.MAX_VALUE;
                cursor = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)TIMED_SMS_URI, (String[])new String[]{"date"}, (String)"timed > 0", (String[])null, (String)"date ASC");
                l2 = l;
                if (cursor != null) {
                    l2 = l;
                    if (!cursor.moveToFirst()) break block8;
                    l2 = cursor.getLong(0);
                }
            }
            context = SqliteWrapper.query((Context)context, (ContentResolver)context.getContentResolver(), (Uri)TIMED_MMS_URI, (String[])new String[]{"date_full"}, (String)"timed > 0", (String[])null, (String)"date_full ASC");
            l = l2;
            if (context != null) {
                l = l2;
                if (!context.moveToFirst()) break block9;
                l = Math.min((long)context.getLong(0), (long)l2);
            }
        }
        return l;
        finally {
            cursor.close();
        }
        finally {
            context.close();
        }
    }

    public static void scheduleNextTimedMsg(Context context) {
        AlarmManager alarmManager = (AlarmManager)context.getSystemService("alarm");
        long l = TimedMessageReceiver.getFirstTimedMsgTime(context);
        if (l < Long.MAX_VALUE) {
            LogTag.verbose("Scheduled next timed message at %d (%s)", l, MessageUtils.getPreciseTimeStamp(l, false));
            Intent intent = new Intent(context, (Class)TimedMessageReceiver.class);
            intent.setAction("com.android.mms.transaction.TIMED_MESSAGE");
            alarmManager.set(0, l, PendingIntent.getBroadcast((Context)context, (int)0, (Intent)intent, (int)268435456));
        }
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.android.mms.transaction.TIMED_MESSAGE".equals((Object)intent.getAction())) {
            SendMessageService.startSendTimedMsg(context);
        }
    }
}

