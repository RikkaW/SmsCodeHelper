/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.os.PowerManager
 *  android.os.PowerManager$WakeLock
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.transaction;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;

public abstract class WakenService
extends Service {
    private static final Object WAKE_LOCK_SYNCER = new Object();
    private static PowerManager.WakeLock mWakeLock;
    private HandlerThread mHandlerThread;
    private Handler mWorkHandler;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void beginStartingService(Context context, Intent intent) {
        Object object = WAKE_LOCK_SYNCER;
        synchronized (object) {
            if (mWakeLock == null) {
                mWakeLock = ((PowerManager)context.getSystemService("power")).newWakeLock(1, "WakenService");
                mWakeLock.setReferenceCounted(false);
            }
            mWakeLock.acquire();
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
        Object object = WAKE_LOCK_SYNCER;
        synchronized (object) {
            if (mWakeLock != null && service.stopSelfResult(n)) {
                mWakeLock.release();
            }
            return;
        }
    }

    protected abstract void handleIntent(Intent var1);

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.mHandlerThread = new HandlerThread("WakeServiceWorkThread");
        this.mHandlerThread.start();
        this.mWorkHandler = new WorkHandler(this.mHandlerThread.getLooper());
    }

    public void onDestroy() {
        this.mHandlerThread.quit();
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int n, int n2) {
        if (intent == null) {
            return 2;
        }
        Message message = this.mWorkHandler.obtainMessage();
        message.arg1 = n2;
        message.obj = intent;
        this.mWorkHandler.sendMessage(message);
        return 2;
    }

    private class WorkHandler
    extends Handler {
        public WorkHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            WakenService.this.handleIntent((Intent)message.obj);
            WakenService.finishStartingService(WakenService.this, message.arg1);
        }
    }

}

