/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ep;
import java.util.concurrent.Callable;

class eq
implements Callable<Void> {
    final /* synthetic */ ep a;

    eq(ep ep2) {
        this.a = ep2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Void a() {
        ep ep2 = this.a;
        synchronized (ep2) {
            if (ep.a(this.a) == null) {
                return null;
            }
            ep.b(this.a);
            if (ep.c(this.a)) {
                ep.d(this.a);
                ep.a(this.a, 0);
            }
            return null;
        }
    }

    @Override
    public /* synthetic */ Object call() {
        return this.a();
    }
}

