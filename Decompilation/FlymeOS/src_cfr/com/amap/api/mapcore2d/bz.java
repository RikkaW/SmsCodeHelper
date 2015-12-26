/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Thread
 */
package com.amap.api.mapcore2d;

class bz {
    private Thread[] a;

    /*
     * Enabled aggressive block sorting
     */
    public bz(int n2, Runnable runnable, Runnable runnable2) {
        this.a = new Thread[n2];
        int n3 = 0;
        while (n3 < n2) {
            this.a[n3] = n3 == 0 && n2 > 1 ? new Thread(runnable) : new Thread(runnable2);
            ++n3;
        }
    }

    public void a() {
        for (Thread thread : this.a) {
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void b() {
        if (this.a == null) {
            return;
        }
        int n2 = this.a.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            this.a[i2].interrupt();
            this.a[i2] = null;
        }
        this.a = null;
    }
}

