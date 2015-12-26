/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.stats;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.stats.StatsHelper;

public class StatsContext
implements ConnectionListener {
    Connection connection;
    private Exception exception;
    XMPushService pushService;
    private int reason;

    StatsContext(XMPushService xMPushService) {
        this.pushService = xMPushService;
    }

    @Override
    public void connectionClosed(Connection connection, int n, Exception exception) {
        if (this.reason == 0 && this.exception == null) {
            this.reason = n;
            this.exception = exception;
            StatsHelper.connectionDown(connection.getHost(), exception);
        }
    }

    @Override
    public void connectionStarted(Connection connection) {
        this.reason = 0;
        this.exception = null;
        this.connection = connection;
        StatsHelper.trackStart(0, ChannelStatsType.CONN_SUCCESS.getValue());
    }

    Exception getCaughtException() {
        return this.exception;
    }

    @Override
    public void reconnectionFailed(Connection connection, Exception exception) {
        StatsHelper.stats(0, ChannelStatsType.CHANNEL_CON_FAIL.getValue(), 1, connection.getHost());
    }

    @Override
    public void reconnectionSuccessful(Connection connection) {
        StatsHelper.trackEnd(0, ChannelStatsType.CONN_SUCCESS.getValue(), connection.getHost(), connection.getConnTryTimes());
    }
}

