/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.concurrent.Executors
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.ed$1;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.ev;
import com.amap.api.mapcore2d.ew;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class ed
implements Thread.UncaughtExceptionHandler {
    private static ed a;
    private static ExecutorService e;
    private Thread.UncaughtExceptionHandler b;
    private Context c;
    private boolean d = true;

    private ed(Context context, dh dh2) {
        this.c = context;
        ev.a(new a(context));
        this.d();
    }

    static /* synthetic */ Context a(ed ed2) {
        return ed2.c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ed a(Context object, dh dh2) {
        synchronized (ed.class) {
            if (dh2 == null) {
                throw new cz("sdk info is null");
            }
            if (dh2.a() == null) throw new cz("sdk name is invalid");
            if ("".equals((Object)dh2.a())) {
                throw new cz("sdk name is invalid");
            }
            try {
                if (a == null) {
                    a = new ed((Context)object, dh2);
                } else {
                    ed.a.d = false;
                }
                a.a((Context)object, dh2, ed.a.d);
                return a;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
            return a;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static ExecutorService a() {
        synchronized (ed.class) {
            try {
                if (e != null) {
                    if (!e.isShutdown()) return e;
                }
                e = Executors.newSingleThreadExecutor();
                return e;
            }
            catch (Throwable var0) {
                var0.printStackTrace();
            }
            return e;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void a(Context context, dh dh2, boolean bl2) {
        ExecutorService executorService = ed.a();
        if (executorService == null) return;
        try {
            if (executorService.isShutdown()) {
                return;
            }
            executorService.submit(new ed$1(this, context, dh2, bl2));
            return;
        }
        catch (RejectedExecutionException var1_2) {
            return;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
        }
    }

    private void a(Throwable throwable, int n2, String string2, String string3) {
        ek.a(this.c, throwable, n2, string2, string3);
    }

    public static void a(Throwable throwable, String string2, String string3) {
        if (a != null) {
            a.a(throwable, 1, string2, string3);
        }
    }

    public static ed b() {
        synchronized (ed.class) {
            ed ed2 = a;
            return ed2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void c() {
        synchronized (ed.class) {
            try {
                if (e != null) {
                    e.shutdown();
                }
            }
            catch (Throwable var0) {
                var0.printStackTrace();
            }
            try {
                if (a != null && Thread.getDefaultUncaughtExceptionHandler() == a && ed.a.b != null) {
                    Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)ed.a.b);
                }
                a = null;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void d() {
        try {
            this.b = Thread.getDefaultUncaughtExceptionHandler();
            if (this.b == null) {
                Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)this);
                this.d = true;
                return;
            }
            if (this.b.toString().indexOf("com.amap.api") != -1) {
                this.d = false;
                return;
            }
            Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)this);
            this.d = true;
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    public void b(Throwable throwable, String string2, String string3) {
        if (throwable == null) {
            return;
        }
        try {
            this.a(throwable, 1, string2, string3);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (throwable == null) {
            return;
        }
        this.a(throwable, 0, null, null);
        if (this.b == null) return;
        this.b.uncaughtException(thread, throwable);
    }

    static class a
    implements ew {
        private Context a;

        a(Context context) {
            this.a = context;
        }

        @Override
        public void a() {
            try {
                ek.b(this.a);
                return;
            }
            catch (Throwable var1_1) {
                ed.a(var1_1, "LogNetListener", "onNetCompleted");
                var1_1.printStackTrace();
                return;
            }
        }
    }

}

