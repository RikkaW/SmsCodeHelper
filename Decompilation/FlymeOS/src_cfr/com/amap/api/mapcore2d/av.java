/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.bw;
import com.amap.api.mapcore2d.n;

class av
extends bw<n.a> {
    av() {
    }

    @Override
    void a(n.a a2) {
        synchronized (this) {
            this.remove(a2);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    boolean b(n.a a2) {
        boolean bl2 = true;
        synchronized (this) {
            block6 : {
                boolean bl3 = this.contains(a2);
                if (!bl3) break block6;
                return false;
            }
            this.a(a2);
            return bl2;
        }
    }
}

