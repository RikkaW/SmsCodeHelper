/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Rate
 *  android.util.Log
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

public class RateController {
    private static RateController sInstance;
    private static boolean sMutexLock;
    private int mAnswer;
    private final BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;

    private RateController(Context context) {
        this.mBroadcastReceiver = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onReceive(Context object, Intent intent) {
                if (!"com.android.mms.RATE_LIMIT_CONFIRMED".equals((Object)intent.getAction())) {
                    return;
                }
                synchronized (this) {
                    object = RateController.this;
                    int n = intent.getBooleanExtra("answer", false) ? 1 : 2;
                    ((RateController)object).mAnswer = n;
                    this.notifyAll();
                    return;
                }
            }
        };
        this.mContext = context;
    }

    public static RateController getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return sInstance;
    }

    public static void init(Context context) {
        if (sInstance != null) {
            Log.w((String)"RateController", (String)"Already initialized.");
        }
        sInstance = new RateController(context.getApplicationContext());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private int waitForAnswer() {
        synchronized (this) {
            int n;
            int n2 = 0;
            while ((n = this.mAnswer) == 0) {
                if (n2 >= 20000) return this.mAnswer;
                try {
                    this.wait(1000);
                }
                catch (InterruptedException var3_3) {}
                n2 += 1000;
            }
            return this.mAnswer;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean isAllowedByUser() {
        boolean bl = true;
        synchronized (this) {
            boolean bl2;
            while (bl2 = sMutexLock) {
                try {
                    this.wait();
                }
                catch (InterruptedException var4_3) {}
            }
            sMutexLock = true;
            this.mContext.registerReceiver(this.mBroadcastReceiver, new IntentFilter("com.android.mms.RATE_LIMIT_CONFIRMED"));
            this.mAnswer = 0;
            Intent intent = new Intent("com.android.mms.RATE_LIMIT_SURPASSED");
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
            int n = this.waitForAnswer();
            if (n != 1) {
                bl = false;
            }
            return bl;
            finally {
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
                sMutexLock = false;
                this.notifyAll();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean isLimitSurpassed() {
        Context context;
        block4 : {
            Uri uri;
            String string2;
            long l = System.currentTimeMillis();
            context = this.mContext;
            ContentResolver contentResolver = this.mContext.getContentResolver();
            if ((context = SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)(uri = Telephony.Mms.Rate.CONTENT_URI), (String[])new String[]{"COUNT(*) AS rate"}, (String)(string2 = "sent_time>" + (l - 3600000)), (String[])null, (String)null)) != null) {
                if (!context.moveToFirst()) break block4;
                int n = context.getInt(0);
                boolean bl = n >= 100;
                return bl;
            }
        }
        return false;
        finally {
            context.close();
        }
    }

    public final void update() {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("sent_time", Long.valueOf((long)System.currentTimeMillis()));
        SqliteWrapper.insert((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)Telephony.Mms.Rate.CONTENT_URI, (ContentValues)contentValues);
    }

}

