/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import com.amap.api.mapcore2d.b;
import java.util.TimerTask;

class c
extends TimerTask {
    final /* synthetic */ b a;

    c(b b2) {
        this.a = b2;
    }

    @Override
    public void run() {
        this.a.j.sendEmptyMessage(19);
    }
}

