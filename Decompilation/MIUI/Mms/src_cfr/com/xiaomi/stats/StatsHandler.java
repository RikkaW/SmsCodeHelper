/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.LinkedList
 *  java.util.Locale
 */
package com.xiaomi.stats;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.stats.Stats;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.DeviceInfo;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.push.thrift.StatsEvents;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.XMPPConnection;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.stats.StatsContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.XmPushTBinaryProtocol;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TTransport;

public class StatsHandler {
    private boolean allowStatsUpload = false;
    private StatsContext context;
    private int duration;
    private long startTime;
    private Stats statsContainer = Stats.instance();
    private String uuid;

    private StatsEvent from(Stats.Item item) {
        StatsEvent statsEvent = null;
        if (item.key == 0) {
            if (item.obj instanceof StatsEvent) {
                statsEvent = (StatsEvent)item.obj;
            }
            return statsEvent;
        }
        statsEvent = this.createStatsEvent();
        statsEvent.setType(ChannelStatsType.CHANNEL_STATS_COUNTER.getValue());
        statsEvent.setSubvalue(item.key);
        statsEvent.setAnnotation(item.annotation);
        return statsEvent;
    }

    static StatsContext getContext() {
        return Holder.sStatsHandler.context;
    }

    public static StatsHandler getInstance() {
        return Holder.sStatsHandler;
    }

    private void internalAdd(int n, int n2, int n3, String string2, String string3, long l) {
        StatsEvent statsEvent = new StatsEvent();
        statsEvent.chid = (byte)n;
        statsEvent.type = n2;
        statsEvent.value = n3;
        statsEvent.connpt = string3;
        statsEvent.host = string2;
        statsEvent.time = (int)System.currentTimeMillis() / 1000;
        if (this.context.connection != null) {
            statsEvent.setClientIp(this.context.connection.getClientIP());
        }
        this.statsContainer.stat(statsEvent);
        MyLog.v(String.format((Locale)Locale.US, (String)"add stats: chid = %s, type =%d, value = %d, connpt = %s", (Object[])new Object[]{n, n2, n3, string3}));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private StatsEvents retriveStatsEvents(int n) {
        TMemoryBuffer tMemoryBuffer;
        StatsEvents statsEvents;
        TProtocol tProtocol;
        ArrayList arrayList;
        arrayList = new ArrayList();
        statsEvents = new StatsEvents(this.uuid, (List<StatsEvent>)arrayList);
        if (!Network.isWIFIConnected((Context)this.context.pushService)) {
            statsEvents.setOperator(DeviceInfo.getSimOperatorName((Context)this.context.pushService));
        }
        tMemoryBuffer = new TMemoryBuffer(n);
        tProtocol = new XmPushTBinaryProtocol.Factory().getProtocol(tMemoryBuffer);
        try {
            statsEvents.write(tProtocol);
        }
        catch (TException var7_8) {}
        LinkedList<Stats.Item> linkedList = this.statsContainer.getStats();
        try {
            while (linkedList.size() > 0) {
                int n2;
                StatsEvent statsEvent = this.from((Stats.Item)linkedList.getLast());
                if (statsEvent != null) {
                    statsEvent.write(tProtocol);
                }
                if ((n2 = tMemoryBuffer.length()) <= n) {
                    if (statsEvent != null) {
                        arrayList.add(statsEvent);
                    }
                    linkedList.removeLast();
                    continue;
                }
                break;
            }
        }
        catch (NoSuchElementException var5_3) {
        }
        catch (TException var5_4) {}
        MyLog.warn("stat approximate size = " + tMemoryBuffer.length());
        return statsEvents;
    }

    private void startStats() {
        if (this.startTime == 0) {
            this.startTime = System.currentTimeMillis();
        }
    }

    private void stopStatsIfNeed() {
        if (this.allowStatsUpload && System.currentTimeMillis() - this.startTime > (long)this.duration) {
            this.allowStatsUpload = false;
            this.startTime = 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void add(int n, int n2, int n3, String string2) {
        synchronized (this) {
            if (this.uuid == null) {
                MyLog.v(String.format((Locale)Locale.US, (String)"StatsHandler.add() Should initialized before add", (Object[])new Object[0]));
            } else {
                String string3 = Network.getActiveConnPoint((Context)this.context.pushService);
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    this.internalAdd(n, n2, n3, string2, string3, System.currentTimeMillis());
                }
            }
            return;
        }
    }

    void add(StatsEvent statsEvent) {
        synchronized (this) {
            this.statsContainer.stat(statsEvent);
            return;
        }
    }

    StatsEvent createStatsEvent() {
        StatsEvent statsEvent = new StatsEvent();
        statsEvent.setConnpt(Network.getActiveConnPoint((Context)this.context.pushService));
        statsEvent.chid = 0;
        statsEvent.value = 1;
        statsEvent.setTime((int)(System.currentTimeMillis() / 1000));
        if (this.context.connection != null) {
            statsEvent.setClientIp(this.context.connection.getClientIP());
        }
        return statsEvent;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void init(XMPushService xMPushService, XMPPConnection xMPPConnection) {
        synchronized (this) {
            void var2_2;
            this.context = new StatsContext(xMPushService);
            this.uuid = "";
            if (var2_2 != null) {
                var2_2.addConnectionListener(this.context);
            }
            ServiceConfig.getInstance().addListener(new ServiceConfig.Listener(){

                @Override
                public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
                    if (pushServiceConfigMsg.hasDots()) {
                        StatsHandler.getInstance().setDuration(pushServiceConfigMsg.getDots());
                    }
                }
            });
            return;
        }
    }

    public boolean isAllowStats() {
        return this.allowStatsUpload;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    StatsEvents retriveStatsEvents() {
        synchronized (this) {
            StatsEvents statsEvents = null;
            if (!this.shouldSendStatsNow()) return statsEvents;
            int n = 750;
            if (Network.isWIFIConnected(SystemUtils.getContext())) return this.retriveStatsEvents(n);
            n = 750 / 2;
            return this.retriveStatsEvents(n);
        }
    }

    public void setDuration(int n) {
        if (n > 0) {
            int n2;
            this.allowStatsUpload = true;
            n = n2 = n * 1000;
            if (n2 > 604800000) {
                n = 604800000;
            }
            if (this.duration != n) {
                this.duration = n;
                this.startStats();
            }
        }
    }

    boolean shouldSendStatsNow() {
        this.stopStatsIfNeed();
        if (this.allowStatsUpload && this.statsContainer.getCount() > 0) {
            return true;
        }
        return false;
    }

    static class Holder {
        static final StatsHandler sStatsHandler = new StatsHandler();

        Holder() {
        }
    }

}

