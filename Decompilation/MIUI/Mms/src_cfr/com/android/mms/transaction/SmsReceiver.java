/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import com.android.mms.transaction.SmsReceiverService;
import com.xiaomi.mms.utils.logger.MyLog;

public class SmsReceiver
extends BroadcastReceiver {
    static PowerManager.WakeLock mStartingService;
    static final Object mStartingServiceSync;
    private static SmsReceiver sInstance;

    static {
        mStartingServiceSync = new Object();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void beginStartingService(Context context, Intent intent) {
        Object object = mStartingServiceSync;
        synchronized (object) {
            if (mStartingService == null) {
                mStartingService = ((PowerManager)context.getSystemService("power")).newWakeLock(1, "StartingAlertService");
                mStartingService.setReferenceCounted(false);
            }
            mStartingService.acquire();
            context.startService(intent);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void finishStartingService(Service service, int n) {
        Object object = mStartingServiceSync;
        synchronized (object) {
            if (mStartingService != null && service.stopSelfResult(n)) {
                mStartingService.release();
            }
            return;
        }
    }

    public static SmsReceiver getInstance() {
        if (sInstance == null) {
            sInstance = new SmsReceiver();
        }
        return sInstance;
    }

    public void onReceive(Context context, Intent intent) {
        this.onReceiveWithPrivilege(context, intent, false);
    }

    protected void onReceiveWithPrivilege(Context context, Intent intent, boolean bl) {
        if (!bl && "android.provider.Telephony.SMS_DELIVER".equals((Object)intent.getAction())) {
            return;
        }
        Uri uri = intent.getData();
        if (uri != null) {
            MyLog.d("SmsReceiver", "sms ack received, uri: " + (Object)uri + ", result: " + this.getResultCode());
        }
        intent.setClass(context, (Class)SmsReceiverService.class);
        intent.putExtra("result", this.getResultCode());
        SmsReceiver.beginStartingService(context, intent);
    }
}

