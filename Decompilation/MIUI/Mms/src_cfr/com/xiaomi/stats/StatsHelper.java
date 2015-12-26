/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Hashtable
 */
package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.push.thrift.StatsEvents;
import com.xiaomi.stats.BindTracker;
import com.xiaomi.stats.StatsAnalyser;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.Hashtable;

public class StatsHelper {
    private static final int PING_RTT = ChannelStatsType.PING_RTT.getValue();

    public static void connectFail(String string2, Exception object) {
        try {
            object = StatsAnalyser.fromConnectionException((Exception)((Object)object));
            StatsEvent statsEvent = StatsHandler.getInstance().createStatsEvent();
            statsEvent.setType(object.type.getValue());
            statsEvent.setAnnotation(object.annotation);
            statsEvent.setHost(string2);
            StatsHandler.getInstance().add(statsEvent);
            return;
        }
        catch (NullPointerException var0_1) {
            return;
        }
    }

    public static void connectionDown(String string2, Exception object) {
        try {
            object = StatsAnalyser.fromDisconnectEx((Exception)((Object)object));
            StatsEvent statsEvent = StatsHandler.getInstance().createStatsEvent();
            statsEvent.setType(object.type.getValue());
            statsEvent.setAnnotation(object.annotation);
            statsEvent.setHost(string2);
            StatsHandler.getInstance().add(statsEvent);
            return;
        }
        catch (NullPointerException var0_1) {
            return;
        }
    }

    public static void count(int n) {
        StatsEvent statsEvent = StatsHandler.getInstance().createStatsEvent();
        statsEvent.setType(ChannelStatsType.CHANNEL_STATS_COUNTER.getValue());
        statsEvent.setSubvalue(n);
        StatsHandler.getInstance().add(statsEvent);
    }

    public static void pingEnded() {
        StatsHelper.trackEnd(0, PING_RTT, null, -1);
    }

    public static void pingStarted() {
        StatsHelper.trackStart(0, PING_RTT);
    }

    public static String retriveStatsAsString() {
        String string2 = null;
        StatsEvents statsEvents = StatsHandler.getInstance().retriveStatsEvents();
        String string3 = string2;
        if (statsEvents != null) {
            statsEvents = (StatsEvents)XmPushThriftSerializeUtils.convertThriftObjectToBytes(statsEvents);
            string3 = string2;
            if (statsEvents != null) {
                string3 = new String(Base64Coder.encode((byte[])statsEvents));
                MyLog.warn("stat encoded size = " + string3.length());
                MyLog.v(string3);
            }
        }
        return string3;
    }

    public static void stats(int n, int n2, int n3, String string2) {
        StatsHandler.getInstance().add(n, n2, n3, string2);
    }

    public static void statsBind(XMPushService xMPushService, PushClientsManager.ClientLoginInfo clientLoginInfo) {
        new BindTracker(xMPushService, clientLoginInfo).track();
    }

    public static void statsGslb(String string2, int n, Exception object) {
        StatsEvent statsEvent = StatsHandler.getInstance().createStatsEvent();
        if (n > 0) {
            statsEvent.setType(ChannelStatsType.GSLB_REQUEST_SUCCESS.getValue());
            statsEvent.setHost(string2);
            statsEvent.setValue(n);
            StatsHandler.getInstance().add(statsEvent);
            return;
        }
        try {
            object = StatsAnalyser.fromGslbException((Exception)((Object)object));
            statsEvent.setType(object.type.getValue());
            statsEvent.setAnnotation(object.annotation);
            statsEvent.setHost(string2);
            StatsHandler.getInstance().add(statsEvent);
            return;
        }
        catch (NullPointerException var0_1) {
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void trackEnd(int n, int n2, String string2, int n3) {
        synchronized (StatsHelper.class) {
            long l = System.currentTimeMillis();
            n = n << 24 | n2;
            if (Holder.sTimeTracker.containsKey((Object)n)) {
                StatsEvent statsEvent = StatsHandler.getInstance().createStatsEvent();
                statsEvent.setType(n2);
                statsEvent.setValue((int)(l - (Long)Holder.sTimeTracker.get((Object)n)));
                statsEvent.setHost(string2);
                if (n3 > -1) {
                    statsEvent.setSubvalue(n3);
                }
                StatsHandler.getInstance().add(statsEvent);
                Holder.sTimeTracker.remove((Object)n2);
                do {
                    return;
                    break;
                } while (true);
            }
            MyLog.e("stats key not found");
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void trackStart(int n, int n2) {
        synchronized (StatsHelper.class) {
            if (n2 < 16777215) {
                Holder.sTimeTracker.put((Object)(n << 24 | n2), (Object)System.currentTimeMillis());
                do {
                    return;
                    break;
                } while (true);
            }
            try {
                MyLog.e("stats key should less than 16777215");
                return;
            }
            catch (Throwable var2_2) {
                throw var2_2;
            }
            finally {
            }
        }
    }

    static class Holder {
        static Hashtable<Integer, Long> sTimeTracker = new Hashtable();

        Holder() {
        }
    }

}

