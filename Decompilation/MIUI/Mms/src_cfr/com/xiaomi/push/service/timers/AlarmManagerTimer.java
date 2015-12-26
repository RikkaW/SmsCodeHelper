/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlarmManager
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.SystemClock
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Method
 */
package com.xiaomi.push.service.timers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.smack.SmackConfiguration;
import java.lang.reflect.Method;

public class AlarmManagerTimer {
    private static volatile long mNextPingTs = 0;
    private Context mContext = null;
    private PendingIntent mPi = null;

    public AlarmManagerTimer(Context context) {
        this.mContext = context;
    }

    private void setExact(AlarmManager alarmManager, long l, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", new Class[]{Integer.TYPE, Long.TYPE, PendingIntent.class}).invoke((Object)alarmManager, new Object[]{0, l, pendingIntent});
            return;
        }
        catch (Exception var1_2) {
            MyLog.e(var1_2);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isAlive() {
        synchronized (this) {
            PendingIntent pendingIntent = this.mPi;
            if (pendingIntent == null) return false;
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void register(Intent intent, long l) {
        synchronized (this) {
            PendingIntent pendingIntent = this.mPi;
            if (pendingIntent == null) {
                pendingIntent = (AlarmManager)this.mContext.getSystemService("alarm");
                this.mPi = PendingIntent.getBroadcast((Context)this.mContext, (int)0, (Intent)intent, (int)0);
                if (Build.VERSION.SDK_INT >= 19) {
                    this.setExact((AlarmManager)pendingIntent, l, this.mPi);
                } else {
                    pendingIntent.set(0, l, this.mPi);
                }
                MyLog.v("register timer " + mNextPingTs);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void registerPing(boolean bl) {
        synchronized (this) {
            Intent intent = new Intent(PushConstants.ACTION_PING_TIMER);
            intent.setPackage(this.mContext.getPackageName());
            long l = SmackConfiguration.getPingInteval();
            if (bl || mNextPingTs == 0) {
                mNextPingTs = System.currentTimeMillis() + (l - SystemClock.elapsedRealtime() % l);
            } else if ((mNextPingTs += l) < System.currentTimeMillis()) {
                mNextPingTs = System.currentTimeMillis() + l;
            }
            this.register(intent, mNextPingTs);
            return;
        }
    }

    public void stop() {
        synchronized (this) {
            if (this.mPi != null) {
                ((AlarmManager)this.mContext.getSystemService("alarm")).cancel(this.mPi);
                this.mPi = null;
                MyLog.v("unregister timer");
                mNextPingTs = 0;
            }
            return;
        }
    }
}

