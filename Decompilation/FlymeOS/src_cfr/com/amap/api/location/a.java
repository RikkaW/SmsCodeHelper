/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.location.LocationManager
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.Vector
 */
package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.a$1;
import com.amap.api.location.b;
import com.amap.api.location.c;
import com.amap.api.location.core.d;
import com.amap.api.location.i;
import java.util.Vector;

public class a {
    static a j = null;
    Vector<i> a = null;
    com.amap.api.location.d b = null;
    c c = null;
    boolean d = false;
    long e;
    boolean f = true;
    boolean g = true;
    b h;
    long i;
    private Context k;
    private a l = null;
    private Vector<i> m = new Vector();
    private AMapLocation n;
    private AMapLocation o;
    private volatile Thread p;
    private long q = 2000;
    private float r = 10.0f;

    /*
     * Enabled aggressive block sorting
     */
    private a(Context context, LocationManager locationManager) {
        this.k = context;
        this.e();
        this.l = Looper.myLooper() == null ? new a(this, context.getMainLooper()) : new a(this);
        this.b = new com.amap.api.location.d(context, locationManager, this.l, this);
        this.c = new c(context, this.l, this);
        this.b(false);
        this.f = true;
        this.g = true;
        this.h = new b(this, context);
    }

    static /* synthetic */ AMapLocation a(a a2) {
        return a2.n;
    }

    static /* synthetic */ AMapLocation a(a a2, AMapLocation aMapLocation) {
        a2.n = aMapLocation;
        return aMapLocation;
    }

    public static a a(Context object, LocationManager locationManager) {
        synchronized (a.class) {
            if (j == null) {
                j = new a((Context)object, locationManager);
            }
            object = j;
            return object;
        }
    }

    static /* synthetic */ AMapLocation b(a a2, AMapLocation aMapLocation) {
        a2.o = aMapLocation;
        return aMapLocation;
    }

    static /* synthetic */ Vector b(a a2) {
        return a2.m;
    }

    static /* synthetic */ Context c(a a2) {
        return a2.k;
    }

    static void c() {
        synchronized (a.class) {
            if (j != null) {
                j.d();
            }
            j = null;
            return;
        }
    }

    private void c(boolean bl2) {
        this.f = bl2;
    }

    static /* synthetic */ AMapLocation d(a a2) {
        return a2.o;
    }

    private void d(boolean bl2) {
        this.g = bl2;
    }

    private void e() {
        this.a = new Vector();
    }

    AMapLocation a() {
        if (this.n != null) {
            return this.n;
        }
        return d.b(this.k);
    }

    void a(double d2, double d3, float f2, long l2, PendingIntent pendingIntent) {
        aho aho2 = new aho();
        aho2.b = d2;
        aho2.a = d3;
        aho2.c = f2;
        aho2.a(l2);
        this.c.a(aho2, pendingIntent);
    }

    void a(int n2, AMapLocalWeatherListener aMapLocalWeatherListener) {
        try {
            new a$1(this, n2, aMapLocalWeatherListener).start();
            return;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    void a(long l2, float f2, AMapLocationListener object, String string2, boolean bl2) {
        this.q = l2;
        this.r = f2;
        if (object == null) return;
        {
            if (!this.a.contains(object = new i(l2, f2, (AMapLocationListener)object, string2, bl2))) {
                this.a.add(object);
            }
            if ("gps".equals((Object)string2)) {
                this.b.a(l2, f2);
                return;
            } else {
                if (!"lbs".equals((Object)string2)) return;
                {
                    if (this.g) {
                        this.b.a(l2, f2);
                    }
                    this.c.a(l2);
                    this.c(true);
                    if (this.p != null) return;
                    {
                        this.c.b(true);
                        this.p = new Thread((Runnable)this.c);
                        this.p.start();
                        return;
                    }
                }
            }
        }
    }

    void a(PendingIntent pendingIntent) {
        this.c.a(pendingIntent);
    }

    /*
     * Enabled aggressive block sorting
     */
    void a(AMapLocationListener aMapLocationListener) {
        int n2 = this.a != null ? this.a.size() : 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            i i3 = (i)this.a.get(i2);
            if (i3 == null) {
                this.a.remove(i2);
                --i2;
                --n2;
                continue;
            }
            if (i3.b != null && !aMapLocationListener.equals((Object)i3.b)) continue;
            this.a.remove((Object)i3);
            --i2;
            --n2;
        }
        if (this.a == null || this.a.size() == 0) {
            this.b(false);
            this.c(false);
            this.b();
            if (this.b != null) {
                this.b.b();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    void a(boolean bl2) {
        this.d(bl2);
        if (this.a == null || this.a.size() <= 0) return;
        if (bl2) {
            this.b.b();
            this.b.a(this.q, this.r);
            return;
        }
        this.b.b();
    }

    void b() {
        if (this.c != null) {
            this.c.b(false);
        }
        if (this.p != null) {
            this.p.interrupt();
            this.p = null;
        }
    }

    void b(double d2, double d3, float f2, long l2, PendingIntent pendingIntent) {
        aho aho2 = new aho();
        aho2.b = d2;
        aho2.a = d3;
        aho2.c = f2;
        aho2.a(l2);
        this.c.b(aho2, pendingIntent);
    }

    void b(PendingIntent pendingIntent) {
        this.c.b(pendingIntent);
    }

    void b(boolean bl2) {
        this.d = bl2;
    }

    void d() {
        if (this.b != null) {
            this.b.b();
            this.b.a();
            this.b = null;
        }
        if (this.c != null) {
            this.c.b();
        }
        if (this.a != null) {
            this.a.clear();
        }
        this.b(false);
    }

    class a
    extends Handler {
        final /* synthetic */ a a;

        public a(a a2) {
            this.a = a2;
        }

        public a(a a2, Looper looper) {
            this.a = a2;
            super(looper);
            Looper.prepare();
        }

        /*
         * Exception decompiling
         */
        public void handleMessage(Message var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[SIMPLE_IF_TAKEN]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}

