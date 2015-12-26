/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ed;

public abstract class ec
implements Runnable {
    a a;

    public abstract void a();

    @Override
    public final void run() {
        try {
            if (this.a != null) {
                this.a.a(this);
            }
            if (Thread.interrupted()) {
                return;
            }
            this.a();
            if (!Thread.interrupted() && this.a != null) {
                this.a.b(this);
                return;
            }
        }
        catch (Throwable var1_1) {
            ed.a(var1_1, "ThreadTask", "run");
            var1_1.printStackTrace();
        }
    }

    static interface a {
        public void a(ec var1);

        public void b(ec var1);
    }

}

