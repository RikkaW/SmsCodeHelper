/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Looper
 *  java.lang.Object
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.ds;
import com.amap.api.mapcore2d.dv;
import com.amap.api.mapcore2d.dx;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ek;

class ed$1
implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ dh b;
    final /* synthetic */ boolean c;
    final /* synthetic */ ed d;

    ed$1(ed ed2, Context context, dh dh2, boolean bl2) {
        this.d = ed2;
        this.a = context;
        this.b = dh2;
        this.c = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        Looper looper;
        try {
            looper = Looper.getMainLooper();
            // MONITORENTER : looper
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
        new ds(this.a).a(this.b);
        // MONITOREXIT : looper
        if (!this.c) return;
        looper = Looper.getMainLooper();
        // MONITORENTER : looper
        dv dv2 = new dv(this.a);
        dx dx2 = new dx();
        dx2.c(true);
        dx2.a(true);
        dx2.b(true);
        dv2.a(dx2);
        // MONITOREXIT : looper
        ek.a(ed.a(this.d));
    }
}

