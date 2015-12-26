/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.SystemClock
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.location;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.a;
import com.amap.api.location.core.AMapLocException;

public class c
implements Runnable {
    ahp a = null;
    volatile boolean b = false;
    boolean c = true;
    private volatile boolean d = false;
    private Context e;
    private long f = 2000;
    private a.a g;
    private a h;
    private boolean i = false;

    c(Context context, a.a a2, a a3) {
        this.h = a3;
        this.b(false);
        this.e = context;
        this.a = new agu();
        this.g = a2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private AMapLocation a(ahf ahf2) {
        AMapLocation aMapLocation = new AMapLocation("");
        aMapLocation.setProvider("lbs");
        aMapLocation.setLatitude(ahf2.f());
        aMapLocation.setLongitude(ahf2.e());
        aMapLocation.setAccuracy(ahf2.g());
        aMapLocation.setTime(ahf2.h());
        aMapLocation.setPoiId(ahf2.b());
        aMapLocation.setFloor(ahf2.c());
        aMapLocation.setCountry(ahf2.n());
        aMapLocation.setRoad(ahf2.q());
        aMapLocation.setPoiName(ahf2.s());
        aMapLocation.setAMapException(ahf2.a());
        Object object = new Bundle();
        object.putString("citycode", ahf2.k());
        object.putString("desc", ahf2.l());
        object.putString("adcode", ahf2.m());
        aMapLocation.setExtras((Bundle)object);
        object = ahf2.k();
        String string2 = ahf2.l();
        String string3 = ahf2.m();
        aMapLocation.setCityCode((String)object);
        aMapLocation.setAdCode(string3);
        if (string3 != null && string3.trim().length() > 0) {
            aMapLocation.setAddress(string2.replace((CharSequence)" ", (CharSequence)""));
        } else {
            aMapLocation.setAddress(string2);
        }
        aMapLocation.setCity(ahf2.p());
        aMapLocation.setDistrict(ahf2.d());
        aMapLocation.a(ahf2.r());
        aMapLocation.setProvince(ahf2.o());
        return aMapLocation;
    }

    /*
     * Exception decompiling
     */
    private void d() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    private ahf e() {
        ahf ahf2;
        ahf ahf3 = ahf2 = this.f();
        if (ahf2 == null) {
            ahf3 = new ahf();
            ahf3.a(new AMapLocException("\u672a\u77e5\u7684\u9519\u8bef"));
            this.c = false;
        }
        return ahf3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private ahf f() {
        try {
            var1_1 = this.a != null ? this.a.a() : null;
        }
        catch (AMapLocException var1_2) {
            var2_3 = new ahf();
            var2_3.a(var1_2);
            this.c = false;
            return var2_3;
        }
        catch (Throwable var2_4) {
            var1_1 = null;
            ** GOTO lbl19
            if (var1_1 != null) ** GOTO lbl16
            try {
                this.c = false;
                return var1_1;
lbl16: // 1 sources:
                this.c = true;
                return var1_1;
            }
            catch (Throwable var2_6) {}
lbl19: // 2 sources:
            this.c = false;
            var2_5.printStackTrace();
            return var1_1;
        }
    }

    private boolean g() {
        boolean bl2 = false;
        long l2 = SystemClock.elapsedRealtime();
        boolean bl3 = bl2;
        if (l2 - this.h.i > 10000) {
            bl3 = bl2;
            if (l2 - this.h.e > 10000) {
                this.h.d = false;
                bl3 = true;
            }
        }
        return bl3;
    }

    void a(long l2) {
        if (l2 > this.f) {
            this.f = l2;
        }
    }

    void a(aho aho2, PendingIntent pendingIntent) {
        this.a.a(aho2, pendingIntent);
    }

    void a(PendingIntent pendingIntent) {
        this.a.a(pendingIntent);
    }

    void a(boolean bl2) {
        synchronized (this) {
            this.b = bl2;
            return;
        }
    }

    public boolean a() {
        synchronized (this) {
            boolean bl2 = this.b;
            return bl2;
        }
    }

    void b() {
        synchronized (this) {
            this.a(true);
            if (!this.d) {
                this.c();
            }
            if (this.h != null) {
                this.h.b();
            }
            this.i = false;
            return;
        }
    }

    void b(aho aho2, PendingIntent pendingIntent) {
        this.a.b(aho2, pendingIntent);
    }

    void b(PendingIntent pendingIntent) {
        this.a.b(pendingIntent);
    }

    void b(boolean bl2) {
        synchronized (this) {
            this.d = bl2;
            return;
        }
    }

    void c() {
        synchronized (this) {
            if (this.a != null) {
                this.a.b();
            }
            this.a = null;
            return;
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    public void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 29[WHILELOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }
}

