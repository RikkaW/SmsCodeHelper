/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.c;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

public final class b
extends Thread {
    private static boolean a = false;

    public static void a() {
        synchronized (b.class) {
            if (!a) {
                new b().start();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            a = false;
            return;
        }
        try {
            cn.com.xy.sms.sdk.net.util.g.a(jSONObject.optString("emContent"));
            if (jSONObject == null) return;
            try {
                if (StringUtils.isNull((String)JsonUtil.getValueFromJsonObject(jSONObject, "emVersion"))) return;
                {
                    DBManager.delete("tb_emergency_queue", "emVersion = ?", new String[]{jSONObject.optString("emVersion")});
                    return;
                }
            }
            catch (Throwable var1_2) {}
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            return;
        }
        finally {
            try {
                Thread.sleep((long)2000);
            }
            catch (Throwable var1_6) {
                var1_6.printStackTrace();
            }
            this.a(c.b());
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
            this.setName("xiaoyuan_EmergencyQueue");
            Process.setThreadPriority((int)g.b);
            if (a) return;
            a = true;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
        try {
            Thread.sleep((long)1000);
            this.a(c.b());
        }
        catch (Throwable var1_2) {}
        a = false;
    }
}

