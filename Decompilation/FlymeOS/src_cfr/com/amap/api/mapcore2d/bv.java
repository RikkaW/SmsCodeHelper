/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import java.util.ArrayList;

class bv {
    private static bv a = new bv();
    private ArrayList<a> b = new ArrayList();

    bv() {
    }

    public static bv a() {
        return a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(a a2) {
        synchronized (this) {
            if (a2 != null) {
                this.b.add((Object)a2);
            }
            return;
        }
    }

    public void b() {
        synchronized (this) {
            for (a a2 : this.b) {
                if (a2 == null) continue;
                a2.N();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(a a2) {
        synchronized (this) {
            if (a2 != null) {
                this.b.remove((Object)a2);
            }
            return;
        }
    }

    public static interface a {
        public void N();
    }

}

