/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.System
 */
package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.NetworkCheckup;
import com.xiaomi.push.service.XMPushService;

class ReconnectionManager {
    private static int MAX_RETRY_INTERVAL = 300;
    private int attempts = 0;
    private int curDelay;
    private long lastConnectTime;
    private XMPushService mPushService;

    public ReconnectionManager(XMPushService xMPushService) {
        this.mPushService = xMPushService;
        this.curDelay = 10;
        this.lastConnectTime = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int timeDelay() {
        int n = 40;
        int n2 = 10;
        if (this.attempts > 8) {
            return 300;
        }
        if (this.attempts > 4) {
            return 60;
        }
        if (this.attempts >= 1) return n2;
        if (this.lastConnectTime == 0) {
            return 0;
        }
        long l = System.currentTimeMillis() - this.lastConnectTime;
        if (l < 300000) {
            if (this.curDelay >= MAX_RETRY_INTERVAL) {
                return this.curDelay;
            }
            n2 = this.curDelay;
            this.curDelay = (int)((double)this.curDelay * 1.5);
            return n2;
        }
        if (l < 900000) {
            n2 = n;
            if (this.curDelay < 40) {
                n2 = this.curDelay;
            }
            this.curDelay = n2;
            return this.curDelay;
        }
        if (l >= 1800000) {
            this.curDelay = 10;
            return this.curDelay;
        }
        n2 = this.curDelay < 20 ? this.curDelay : 20;
        this.curDelay = n2;
        return this.curDelay;
    }

    public void onConnectSucceeded() {
        this.lastConnectTime = System.currentTimeMillis();
        this.mPushService.removeJobs(1);
        this.attempts = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void tryReconnect(boolean bl) {
        if (!this.mPushService.shouldReconnect()) {
            MyLog.v("should not reconnect as no client or network.");
            return;
        }
        if (bl) {
            this.mPushService.removeJobs(1);
            XMPushService xMPushService = this.mPushService;
            XMPushService xMPushService2 = this.mPushService;
            xMPushService2.getClass();
            xMPushService.executeJob(new XMPushService.ConnectJob(xMPushService2));
            ++this.attempts;
            return;
        } else {
            if (this.mPushService.hasJob(1)) return;
            {
                int n = this.timeDelay();
                MyLog.warn("schedule reconnect in " + n + "s");
                XMPushService xMPushService = this.mPushService;
                XMPushService xMPushService3 = this.mPushService;
                xMPushService3.getClass();
                xMPushService.executeJobDelayed(new XMPushService.ConnectJob(xMPushService3), n * 1000);
                ++this.attempts;
                if (this.attempts != 3) return;
                {
                    NetworkCheckup.connectivityTest();
                    return;
                }
            }
        }
    }
}

