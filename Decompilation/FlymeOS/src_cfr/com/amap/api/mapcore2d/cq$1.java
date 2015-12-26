/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.os.Process;
import com.amap.api.mapcore2d.cq;
import com.amap.api.mapcore2d.cr;

class cq$1
extends cq.e<Params, Result> {
    final /* synthetic */ cq a;

    cq$1(cq cq2) {
        this.a = cq2;
        super(null);
    }

    @Override
    public Result call() {
        cq.a(this.a).set(true);
        Process.setThreadPriority((int)10);
        return (Result)cq.a(this.a, this.a.a((Params[])this.b));
    }
}

