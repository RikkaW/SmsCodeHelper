/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.cq;

class cq$c$1
implements Runnable {
    final /* synthetic */ Runnable a;
    final /* synthetic */ cq.c b;

    cq$c$1(cq.c c2, Runnable runnable) {
        this.b = c2;
        this.a = runnable;
    }

    @Override
    public void run() {
        try {
            this.a.run();
            return;
        }
        finally {
            this.b.a();
        }
    }
}

