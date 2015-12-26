/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.util.concurrent.ConcurrentHashMap
 *  java.util.concurrent.Executors
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.eb;
import com.amap.api.mapcore2d.ec;
import com.amap.api.mapcore2d.ed;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ea {
    private static ea a = null;
    private ExecutorService b;
    private ConcurrentHashMap<ec, Future<?>> c = new ConcurrentHashMap();
    private ec.a d;

    private ea(int n2) {
        this.d = new eb(this);
        try {
            this.b = Executors.newFixedThreadPool((int)n2);
            return;
        }
        catch (Throwable var2_2) {
            ed.a(var2_2, "TPool", "ThreadPool");
            var2_2.printStackTrace();
            return;
        }
    }

    public static ea a(int n2) {
        synchronized (ea.class) {
            if (a == null) {
                a = new ea(n2);
            }
            ea ea2 = a;
            return ea2;
        }
    }

    static /* synthetic */ void a(ea ea2, ec ec2, boolean bl2) {
        ea2.a(ec2, bl2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(ec object, boolean bl2) {
        synchronized (this) {
            try {
                void var2_3;
                object = (Future)this.c.remove(object);
                if (var2_3 != false && object != null) {
                    object.cancel(true);
                }
            }
            catch (Throwable var1_2) {
                ed.a(var1_2, "TPool", "removeQueue");
                var1_2.printStackTrace();
            }
            return;
        }
    }
}

