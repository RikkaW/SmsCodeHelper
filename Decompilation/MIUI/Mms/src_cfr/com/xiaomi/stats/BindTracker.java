/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.stats;

import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.smack.Connection;
import com.xiaomi.stats.StatsAnalyser;
import com.xiaomi.stats.StatsHandler;

class BindTracker
implements PushClientsManager.ClientLoginInfo.ClientStatusListener {
    private PushClientsManager.ClientLoginInfo client;
    private Connection connection;
    private XMPushService pushService;
    private int reason;
    private PushClientsManager.ClientStatus status;
    private boolean tracked = false;

    BindTracker(XMPushService xMPushService, PushClientsManager.ClientLoginInfo clientLoginInfo) {
        this.pushService = xMPushService;
        this.status = PushClientsManager.ClientStatus.binding;
        this.client = clientLoginInfo;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void done() {
        Object object;
        StatsEvent statsEvent;
        this.untrack();
        if (!this.tracked) {
            return;
        }
        if (this.reason == 11) return;
        object = statsEvent = StatsHandler.getInstance().createStatsEvent();
        switch (.$SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus[this.status.ordinal()]) {
            default: {
                object = statsEvent;
                break;
            }
            case 1: {
                if (this.reason == 17) {
                    statsEvent.type = ChannelStatsType.BIND_TCP_READ_TIMEOUT.getValue();
                    object = statsEvent;
                    break;
                }
                if (this.reason == 21) {
                    statsEvent.type = ChannelStatsType.BIND_TIMEOUT.getValue();
                    object = statsEvent;
                    break;
                }
                try {
                    object = StatsAnalyser.fromBind(StatsHandler.getContext().getCaughtException());
                    statsEvent.type = object.type.getValue();
                    statsEvent.setAnnotation(object.annotation);
                    object = statsEvent;
                }
                catch (NullPointerException var1_4) {
                    return;
                }
            }
            case 2: {
                break;
            }
            case 3: {
                statsEvent.type = ChannelStatsType.BIND_SUCCESS.getValue();
                object = statsEvent;
            }
        }
        if (object == null) return;
        object.setHost(this.connection.getHost());
        object.setUser(this.client.userId);
        object.value = 1;
        try {
            object.setChid((byte)Integer.parseInt((String)this.client.chid));
        }
        catch (NumberFormatException var2_2) {}
        StatsHandler.getInstance().add((StatsEvent)object);
    }

    private void untrack() {
        this.client.removeClientStatusListener(this);
    }

    @Override
    public void onChange(PushClientsManager.ClientStatus clientStatus, PushClientsManager.ClientStatus clientStatus2, int n) {
        if (!this.tracked && clientStatus == PushClientsManager.ClientStatus.binding) {
            this.status = clientStatus2;
            this.reason = n;
            this.tracked = true;
        }
        this.pushService.executeJob(new XMPushService.Job(4){

            @Override
            public String getDesc() {
                return "Handling bind stats";
            }

            @Override
            public void process() {
                BindTracker.this.done();
            }
        });
    }

    void track() {
        this.client.addClientStatusListener(this);
        this.connection = this.pushService.getCurrentConnection();
    }

}

