/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.LinkedBlockingQueue
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.queue;

import cn.com.xy.sms.sdk.queue.f;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONObject;

public final class e {
    public static BlockingQueue<JSONObject> a = new LinkedBlockingQueue();
    private static Thread b = null;

    private static void a() {
        synchronized (e.class) {
            if (b == null) {
                f f2;
                b = f2 = new f();
                f2.start();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(int n2, String string2, String string3, String string4, String string5, int n3, long l2, JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject == null) {
            jSONObject2 = new JSONObject();
        }
        try {
            jSONObject2.put("dataStatu", n2);
            jSONObject2.put("msg_id", (Object)string2);
            jSONObject2.put("phoneNum", (Object)string3);
            jSONObject2.put("smsContent", (Object)string4);
            jSONObject2.put("smsReceiveTime", l2);
            if (string5 != null) {
                jSONObject2.put("centerNum", (Object)string5);
            }
            jSONObject2.put("dataType", n3);
            a.put(jSONObject2);
            e.a();
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

