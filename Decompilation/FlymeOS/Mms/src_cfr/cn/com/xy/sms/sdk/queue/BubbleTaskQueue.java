/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.LinkedBlockingQueue
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.queue;

import android.content.Intent;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.queue.a;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONObject;

public class BubbleTaskQueue {
    private static BlockingQueue<JSONObject> a = new LinkedBlockingQueue();
    private static Thread b = null;

    static /* synthetic */ BlockingQueue a() {
        return a;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static /* synthetic */ void a(JSONObject jSONObject) {
        Integer n2 = (Integer)JsonUtil.getValueFromJsonObject(jSONObject, "dataType");
        ParseSmsToBubbleUtil.parseSmsToBubbleResultMap((String)JsonUtil.getValueFromJsonObject(jSONObject, "msg_id"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "smsContent"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "centerNum"), (Long)JsonUtil.getValueFromJsonObject(jSONObject, "smsReceiveTime"), n2, true, true, null);
        jSONObject = (Integer)JsonUtil.getValueFromJsonObject(jSONObject, "isNeedNotify");
        if (jSONObject == null) return;
        int n3 = jSONObject.intValue();
        if (1 != n3) return;
        {
            catch (Throwable throwable) {
                return;
            }
        }
        try {
            jSONObject = new Intent("cn.com.xy.douqu.reflashlist");
            Constant.getContext().sendBroadcast((Intent)jSONObject);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void addDataToQueue(int n2, String string2, String string3, String string4, String string5, long l2, int n3, JSONObject jSONObject) {
        BubbleTaskQueue.addDataToQueue(n2, string2, string3, string4, string5, l2, n3, jSONObject, 0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addDataToQueue(int n2, String string2, String string3, String string4, String string5, long l2, int n3, JSONObject jSONObject, int n4) {
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
            jSONObject2.put("isNeedNotify", n4);
            a.put(jSONObject2);
            BubbleTaskQueue.startTaskQueue();
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(JSONObject jSONObject) {
        Integer n2 = (Integer)JsonUtil.getValueFromJsonObject(jSONObject, "dataType");
        ParseSmsToBubbleUtil.parseSmsToBubbleResultMap((String)JsonUtil.getValueFromJsonObject(jSONObject, "msg_id"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "phoneNum"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "smsContent"), (String)JsonUtil.getValueFromJsonObject(jSONObject, "centerNum"), (Long)JsonUtil.getValueFromJsonObject(jSONObject, "smsReceiveTime"), n2, true, true, null);
        jSONObject = (Integer)JsonUtil.getValueFromJsonObject(jSONObject, "isNeedNotify");
        if (jSONObject == null) return;
        int n3 = jSONObject.intValue();
        if (1 != n3) return;
        {
            catch (Throwable throwable) {
                return;
            }
        }
        try {
            jSONObject = new Intent("cn.com.xy.douqu.reflashlist");
            Constant.getContext().sendBroadcast((Intent)jSONObject);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void startTaskQueue() {
        synchronized (BubbleTaskQueue.class) {
            if (b == null) {
                a a2;
                b = a2 = new a();
                a2.start();
            }
            return;
        }
    }
}

