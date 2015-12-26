/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.net.Socket
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.regex.Pattern
 */
package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Host;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.stats.StatsHelper;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NetworkCheckup {
    private static final Pattern IP_PATTERN = Pattern.compile((String)"([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
    private static long lastCheckTime = 0;
    private static ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue());

    static /* synthetic */ boolean access$200(String string2) {
        return NetworkCheckup.doConnectTest(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void connectivityTest() {
        ChannelConfig.PushServiceConfig pushServiceConfig;
        long l = System.currentTimeMillis();
        if (sExecutor.getActiveCount() > 0 && l - lastCheckTime < 1800000 || !StatsHandler.getInstance().isAllowStats() || (pushServiceConfig = ServiceConfig.getInstance().getConfig()) == null || pushServiceConfig.getTestHostsCount() <= 0) {
            return;
        }
        lastCheckTime = l;
        NetworkCheckup.connectivityTest(pushServiceConfig.getTestHostsList(), true);
    }

    public static void connectivityTest(final List<String> list, final boolean bl) {
        sExecutor.execute(new Runnable(){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            @Override
            public void run() {
                var1_1 = 1;
                var3_2 = NetworkCheckup.access$200("www.baidu.com:80");
                var4_3 = list.iterator();
                do lbl-1000: // 3 sources:
                {
                    var2_4 = var3_2;
                    if (!var4_3.hasNext()) break;
                    var5_5 = (String)var4_3.next();
                    var2_4 = var3_2 != false || NetworkCheckup.access$200(var5_5) != false;
                    var3_2 = var2_4;
                    if (!var2_4) ** GOTO lbl-1000
                    var3_2 = var2_4;
                } while (bl);
                if (!var2_4) {
                    var1_1 = 2;
                }
                StatsHelper.count(var1_1);
            }
        });
    }

    private static boolean doConnectTest(String string2) {
        long l = System.currentTimeMillis();
        try {
            MyLog.warn("ConnectivityTest: begin to connect to " + string2);
            Socket socket = new Socket();
            socket.connect((SocketAddress)Host.from(string2, 5222), 5000);
            socket.setTcpNoDelay(true);
            long l2 = System.currentTimeMillis();
            MyLog.warn("ConnectivityTest: connect to " + string2 + " in " + (l2 - l));
            socket.close();
            return true;
        }
        catch (Throwable var5_3) {
            MyLog.e("ConnectivityTest: could not connect to:" + string2 + " exception: " + var5_3.getClass().getSimpleName() + " description: " + var5_3.getMessage());
            return false;
        }
    }

}

