/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.ThreadLocal
 */
package android.support.v4.net;

import android.support.v4.net.TrafficStatsCompat;

class TrafficStatsCompat$BaseTrafficStatsCompatImpl$1
extends ThreadLocal<TrafficStatsCompat.BaseTrafficStatsCompatImpl.SocketTags> {
    final /* synthetic */ TrafficStatsCompat.BaseTrafficStatsCompatImpl this$0;

    TrafficStatsCompat$BaseTrafficStatsCompatImpl$1(TrafficStatsCompat.BaseTrafficStatsCompatImpl baseTrafficStatsCompatImpl) {
        this.this$0 = baseTrafficStatsCompatImpl;
    }

    protected TrafficStatsCompat.BaseTrafficStatsCompatImpl.SocketTags initialValue() {
        return new TrafficStatsCompat.BaseTrafficStatsCompatImpl.SocketTags(null);
    }
}

