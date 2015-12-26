/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.util.Arrays
 *  java.util.Comparator
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.ed;
import java.util.Arrays;
import java.util.Comparator;

class ad
implements Runnable {
    final /* synthetic */ ac a;

    ad(ac ac2) {
        this.a = ac2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        synchronized (this) {
            Object[] arrobject;
            int n2;
            int n3;
            try {
                arrobject = ac.a(this.a).toArray();
                Arrays.sort((Object[])arrobject, (Comparator)ac.b(this.a));
                ac.a(this.a).clear();
                n3 = arrobject.length;
                n2 = 0;
            }
            catch (Throwable var3_2) {
                ed.a(var3_2, "MapOverlayImageView", "changeOverlayIndex");
                return;
            }
            while (n2 < n3) {
                Object object = arrobject[n2];
                ac.a(this.a).add((Object)((am)object));
                ++n2;
                continue;
            }
            return;
        }
    }
}

