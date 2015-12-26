/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.LinkedBlockingQueue
 */
package cn.com.xy.sms.sdk.queue;

import cn.com.xy.sms.sdk.queue.h;
import cn.com.xy.sms.sdk.queue.i;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class g {
    public static BlockingQueue<i> a = new LinkedBlockingQueue();
    public static int b = 10;
    private static Thread c = null;

    public static void a() {
        synchronized (g.class) {
            if (c == null) {
                h h2;
                c = h2 = new h();
                h2.start();
            }
            return;
        }
    }

    public static void a(i i2) {
        try {
            a.put(i2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

