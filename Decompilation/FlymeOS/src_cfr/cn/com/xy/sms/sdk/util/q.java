/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.Calendar
 *  java.util.Date
 *  java.util.Timer
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import cn.com.xy.sms.sdk.util.r;
import cn.com.xy.sms.sdk.util.w;
import cn.com.xy.sms.sdk.util.z;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class q
extends Thread {
    private static boolean a = false;

    public static void a() {
        synchronized (q.class) {
            if (!a) {
                new q().start();
            }
            return;
        }
    }

    private static void a(int n2) {
        List<p> list = cn.com.xy.sms.sdk.db.entity.q.a(n2, 0);
        new StringBuilder("requestQue=").append(list);
        if (!list.isEmpty()) {
            SceneconfigUtil.requestScenceconfig(list, n2, false);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void a(String var0) {
        try {
            var2_3 = new r();
            var3_4 = new Timer();
            var1_5 = new Date();
            var4_6 = Calendar.getInstance();
            var0 = String.valueOf((Object)new StringBuilder(String.valueOf((int)var4_6.get(1))).append("/").append(var4_6.get(2) + 1).append("/").append(var4_6.get(5)).toString()) + " " + var0;
        }
lbl7: // 2 sources:
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return;
        }
        var0 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(var0);
        {
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                var0 = var1_5;
            }
            ** try [egrp 2[TRYBLOCK] [2 : 115->127)] { 
lbl17: // 1 sources:
            var3_4.schedule((TimerTask)var2_3, (Date)var0);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public final void run() {
        try {
            if (a) return;
            a = true;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
        try {
            Thread.sleep((long)20000);
            z.a(false);
            Thread.sleep((long)10000);
        }
        catch (Throwable var1_2) {}
        q.a(0);
        q.a(1);
        w.a(false);
        SceneconfigUtil.updateData();
        a = false;
    }
}

