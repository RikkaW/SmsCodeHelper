/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.URL
 *  java.util.ArrayList
 */
package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HostManagerV2;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionConfiguration;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class PushHostManagerFactory
extends ServiceConfig.Listener
implements HostManager.HostManagerFactory {
    private long lastFetchTime;
    private XMPushService pushService;

    PushHostManagerFactory(XMPushService xMPushService) {
        this.pushService = xMPushService;
    }

    public static void init(XMPushService xMPushService) {
        boolean bl;
        PushHostManagerFactory pushHostManagerFactory = new PushHostManagerFactory(xMPushService);
        ServiceConfig.getInstance().addListener(pushHostManagerFactory);
        ChannelConfig.PushServiceConfig pushServiceConfig = ServiceConfig.getInstance().getConfig();
        boolean bl2 = bl = true;
        if (pushServiceConfig != null) {
            bl2 = bl;
            if (pushServiceConfig.hasUseBucketV2()) {
                bl2 = pushServiceConfig.hasUseBucketV2();
            }
        }
        if (bl2) {
            HostManager.setHostManagerFactory(pushHostManagerFactory);
        }
        HostManager.init((Context)xMPushService, null, new GslbHttpGet(), "0", "push", "2.2");
    }

    @Override
    public HostManager createHostManager(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String string2) {
        return new PushHostManager(context, hostFilter, httpGet, string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onConfigChange(ChannelConfig.PushServiceConfig pushServiceConfig) {
        if (!pushServiceConfig.hasUseBucketV2()) {
            return;
        }
        MyLog.warn("Switch to BucketV2 :" + pushServiceConfig.getUseBucketV2());
        HostManager hostManager = HostManager.getInstance();
        synchronized (HostManager.class) {
            if (pushServiceConfig.getUseBucketV2()) {
                if (!(hostManager instanceof HostManagerV2)) {
                    HostManager.setHostManagerFactory(this);
                    HostManager.init((Context)this.pushService, null, new GslbHttpGet(), "0", "push", "2.2");
                }
            } else if (HostManager.getInstance() instanceof HostManagerV2) {
                HostManager.setHostManagerFactory(null);
                HostManager.init((Context)this.pushService, null, new GslbHttpGet(), "0", "push", "2.2");
            }
            return;
        }
    }

    @Override
    public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg object) {
        if (object.hasFetchBucket() && System.currentTimeMillis() - this.lastFetchTime > 3600000) {
            MyLog.warn("fetch bucket :" + object.getFetchBucket());
            this.lastFetchTime = System.currentTimeMillis();
            ArrayList<String> arrayList = HostManager.getInstance();
            arrayList.clear();
            arrayList.refreshFallbacks();
            object = this.pushService.getCurrentConnection();
            if (object != null && (arrayList = arrayList.getFallbacksByHost(object.getConfiguration().getHost())) != null) {
                boolean bl;
                block4 : {
                    arrayList = arrayList.getHosts();
                    boolean bl2 = true;
                    Iterator iterator = arrayList.iterator();
                    do {
                        bl = bl2;
                        if (!iterator.hasNext()) break block4;
                    } while (!((String)iterator.next()).equals((Object)object.getHost()));
                    bl = false;
                }
                if (bl && !arrayList.isEmpty()) {
                    MyLog.warn("bucket changed, force reconnect");
                    this.pushService.disconnect(0, null);
                    this.pushService.scheduleConnect(false);
                }
            }
        }
    }

    static class GslbHttpGet
    implements HostManager.HttpGet {
        GslbHttpGet() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public String doGet(String string2) throws IOException {
            int n = (string2 = new URL(string2)).getPort() == -1 ? 80 : string2.getPort();
            try {
                long l = System.currentTimeMillis();
                String string3 = Network.downloadXml(SystemUtils.getContext(), (URL)string2);
                long l2 = System.currentTimeMillis();
                StatsHelper.statsGslb(string2.getHost() + ":" + n, (int)(l2 - l), null);
                return string3;
            }
            catch (IOException var7_5) {
                StatsHelper.statsGslb(string2.getHost() + ":" + n, -1, var7_5);
                throw var7_5;
            }
        }
    }

    static class PushHostManager
    extends HostManagerV2 {
        protected PushHostManager(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String string2) {
            super(context, hostFilter, httpGet, string2);
        }

        @Override
        protected String getRemoteFallbackJSON(ArrayList<String> object, String string2, String string3) throws IOException {
            try {
                object = super.getRemoteFallbackJSON((ArrayList<String>)object, string2, string3);
                return object;
            }
            catch (IOException var1_2) {
                StatsHelper.stats(0, ChannelStatsType.GSLB_ERR.getValue(), 1, null);
                throw var1_2;
            }
        }
    }

}

