/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.queue.BubbleTaskQueue;
import cn.com.xy.sms.sdk.queue.g;
import org.json.JSONObject;

final class a
extends Thread {
    a() {
    }

    public final void run() {
        this.setName("xiaoyuan_taskbubblequeue");
        Process.setThreadPriority((int)g.b);
        do {
            JSONObject jSONObject;
            try {
                jSONObject = (JSONObject)BubbleTaskQueue.a().take();
                if (jSONObject == null) continue;
            }
            catch (Throwable var1_2) {
                LogManager.e("BubbleTaskQueue", var1_2.getLocalizedMessage(), var1_2);
                continue;
            }
            BubbleTaskQueue.a(jSONObject);
            continue;
            break;
        } while (true);
    }
}

