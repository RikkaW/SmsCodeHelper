/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteException
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Collections
 */
package com.xiaomi.smack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.push.providers.TrafficDatabaseHelper;
import com.xiaomi.push.service.XMPushService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TrafficUtils {
    private static TrafficDatabaseHelper dbHelper;
    private static String imsi;
    private static final Object lock;
    private static SerializedAsyncTaskProcessor mAsyncProcessor;
    private static int networkType;
    private static List<TrafficInfo> trafficList;

    static {
        mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
        networkType = -1;
        lock = new Object();
        trafficList = Collections.synchronizedList((List)new ArrayList());
        imsi = "";
        dbHelper = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void distributionTraffic(final XMPushService xMPushService, String string2, long l, boolean bl, long l2) {
        if (xMPushService == null) return;
        if (TextUtils.isEmpty((CharSequence)string2)) return;
        if (!"com.xiaomi.xmsf".equals((Object)xMPushService.getPackageName())) return;
        if ("com.xiaomi.xmsf".equals((Object)string2)) {
            return;
        }
        int n = TrafficUtils.getNetworkType((Context)xMPushService);
        if (-1 == n) return;
        Object object = lock;
        // MONITORENTER : object
        boolean bl2 = trafficList.isEmpty();
        int n2 = bl ? 1 : 0;
        String string3 = n == 0 ? TrafficUtils.getIMSI((Context)xMPushService) : "";
        TrafficUtils.insertTrafficInfo2List(new TrafficInfo(string2, l2, n, n2, string3, TrafficUtils.getTraffic(n, l)));
        // MONITOREXIT : object
        if (!bl2) return;
        mAsyncProcessor.addNewTaskWithDelayed(new SerializedAsyncTaskProcessor.SerializedAsyncTask(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            @Override
            public void process() {
                Object object = lock;
                // MONITORENTER : object
                ArrayList arrayList = new ArrayList((Collection)trafficList);
                trafficList.clear();
                // MONITOREXIT : object
                TrafficUtils.insertTraffic((Context)xMPushService, (List)arrayList);
            }
        }, 5000);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static int getActiveNetworkType(Context context) {
        block4 : {
            try {
                context = (ConnectivityManager)context.getSystemService("connectivity");
                if (context != null) break block4;
                return -1;
            }
            catch (Exception var0_1) {
                return -1;
            }
        }
        try {
            if ((context = context.getActiveNetworkInfo()) != null) return context.getType();
        }
        catch (Exception var0_2) {
            return -1;
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String getIMSI(Context object) {
        synchronized (TrafficUtils.class) {
            if (!TextUtils.isEmpty((CharSequence)imsi)) {
                return imsi;
            }
            try {
                object = (TelephonyManager)object.getSystemService("phone");
                if (object == null) return imsi;
                imsi = object.getSubscriberId();
                return imsi;
            }
            catch (Exception var0_1) {}
            return imsi;
        }
    }

    private static int getNetworkType(Context context) {
        if (networkType == -1) {
            networkType = TrafficUtils.getActiveNetworkType(context);
        }
        return networkType;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static long getTraffic(int n, long l) {
        if (n == 0) {
            n = 13;
            do {
                return (long)n * l / 10;
                break;
            } while (true);
        }
        n = 11;
        return (long)n * l / 10;
    }

    private static TrafficDatabaseHelper getTrafficDatabaseHelper(Context context) {
        if (dbHelper != null) {
            return dbHelper;
        }
        dbHelper = new TrafficDatabaseHelper(context);
        return dbHelper;
    }

    public static int getTrafficFlow(String string2) {
        try {
            int n = string2.getBytes("UTF-8").length;
            return n;
        }
        catch (UnsupportedEncodingException var2_2) {
            return string2.getBytes().length;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static void insertTraffic(Context var0, List<TrafficInfo> var1_2) {
        var2_4 = TrafficDatabaseHelper.DataBaseLock;
        // MONITORENTER : var2_4
        var0 = TrafficUtils.getTrafficDatabaseHelper(var0).getWritableDatabase();
        var0.beginTransaction();
        try {
            var1_2 = var1_2.iterator();
            while (var1_2.hasNext()) {
                var3_5 = (TrafficInfo)var1_2.next();
                var4_6 = new ContentValues();
                var4_6.put("package_name", var3_5.packageName);
                var4_6.put("message_ts", Long.valueOf((long)var3_5.messageTs));
                var4_6.put("network_type", Integer.valueOf((int)var3_5.networkType));
                var4_6.put("bytes", Long.valueOf((long)var3_5.bytes));
                var4_6.put("rcv", Integer.valueOf((int)var3_5.rcv));
                var4_6.put("imsi", var3_5.imsi);
                var0.insert("traffic", null, var4_6);
            }
            ** GOTO lbl23
            catch (SQLiteException var0_1) {
                MyLog.e((Throwable)var0_1);
                return;
            }
lbl23: // 1 sources:
            var0.setTransactionSuccessful();
            return;
        }
        finally {
            var0.endTransaction();
        }
    }

    private static void insertTrafficInfo2List(TrafficInfo trafficInfo) {
        for (TrafficInfo trafficInfo2 : trafficList) {
            if (!trafficInfo2.canAccumulate(trafficInfo)) continue;
            trafficInfo2.bytes += trafficInfo.bytes;
            return;
        }
        trafficList.add(trafficInfo);
    }

    static class TrafficInfo {
        public long bytes = 0;
        public String imsi = "";
        public long messageTs = 0;
        public int networkType = -1;
        public String packageName = "";
        public int rcv = -1;

        public TrafficInfo(String string2, long l, int n, int n2, String string3, long l2) {
            this.packageName = string2;
            this.messageTs = l;
            this.networkType = n;
            this.rcv = n2;
            this.imsi = string3;
            this.bytes = l2;
        }

        public boolean canAccumulate(TrafficInfo trafficInfo) {
            if (!TextUtils.equals((CharSequence)trafficInfo.packageName, (CharSequence)this.packageName) || !TextUtils.equals((CharSequence)trafficInfo.imsi, (CharSequence)this.imsi) || trafficInfo.networkType != this.networkType || trafficInfo.rcv != this.rcv || Math.abs((long)(trafficInfo.messageTs - this.messageTs)) > 5000) {
                return false;
            }
            return true;
        }
    }

}

