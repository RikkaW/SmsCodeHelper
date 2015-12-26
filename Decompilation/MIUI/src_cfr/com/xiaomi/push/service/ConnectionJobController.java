/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.util.Pair
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package com.xiaomi.push.service;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectionJobController
extends HandlerThread {
    private volatile boolean executing = false;
    private volatile long lastJob = 0;
    private volatile Handler mHandler;
    private List<Pair<XMPushService.Job, Long>> mPendingJob = new ArrayList();

    public ConnectionJobController(String string2) {
        super(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void executeJobDelayed(XMPushService.Job job, long l) {
        List<Pair<XMPushService.Job, Long>> list = this.mPendingJob;
        synchronized (list) {
            if (this.mHandler != null) {
                Message message = Message.obtain();
                message.what = job.type;
                message.obj = job;
                this.mHandler.sendMessageDelayed(message, l);
            } else {
                MyLog.warn("the job is pended, the controller is not ready.");
                this.mPendingJob.add((Pair)new Pair((Object)job, (Object)l));
            }
            return;
        }
    }

    public boolean hasJob(int n) {
        if (this.mHandler != null) {
            return this.mHandler.hasMessages(n);
        }
        return false;
    }

    public boolean isBlocked() {
        if (this.executing && System.currentTimeMillis() - this.lastJob > 600000) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onLooperPrepared() {
        this.mHandler = new Handler(this.getLooper()){

            public void handleMessage(Message message) {
                ConnectionJobController.this.executing = true;
                ConnectionJobController.this.lastJob = System.currentTimeMillis();
                if (message.obj instanceof XMPushService.Job) {
                    ((XMPushService.Job)message.obj).run();
                }
                ConnectionJobController.this.executing = false;
            }
        };
        List<Pair<XMPushService.Job, Long>> list = this.mPendingJob;
        synchronized (list) {
            Iterator<Pair<XMPushService.Job, Long>> iterator = this.mPendingJob.iterator();
            do {
                if (!iterator.hasNext()) {
                    this.mPendingJob.clear();
                    return;
                }
                Pair<XMPushService.Job, Long> pair = iterator.next();
                MyLog.warn("executing the pending job.");
                this.executeJobDelayed((XMPushService.Job)pair.first, (Long)pair.second);
            } while (true);
        }
    }

    public void removeAllJobs() {
        for (int i = 1; i < 15; ++i) {
            this.removeJobs(i);
        }
    }

    public void removeJobs(int n) {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(n);
        }
    }

    public void removeJobs(int n, Object object) {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(n, object);
        }
    }

}

