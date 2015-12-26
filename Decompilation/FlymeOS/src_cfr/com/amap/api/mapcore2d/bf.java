/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.util.Collections
 *  java.util.Comparator
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.be;
import com.amap.api.mapcore2d.ed;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class bf
implements Runnable {
    final /* synthetic */ be a;

    bf(be be2) {
        this.a = be2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        synchronized (this) {
            try {
                Collections.sort((List)be.a(this.a), (Comparator)this.a.b);
                Collections.sort((List)be.b(this.a), (Comparator)this.a.b);
                this.a.invalidate();
                do {
                    return;
                    break;
                } while (true);
            }
            catch (Throwable var1_1) {
                try {
                    ed.a(var1_1, "MapOverlayImageView", "changeOverlayIndex");
                    return;
                }
                catch (Throwable var1_2) {
                    throw var1_2;
                }
                finally {
                }
            }
        }
    }
}

