/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.Vector
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.bi;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.bx;
import com.amap.api.mapcore2d.bz;
import com.amap.api.mapcore2d.k;
import com.amap.api.mapcore2d.l;
import java.util.ArrayList;
import java.util.Vector;

abstract class j<T, V>
extends bi {
    protected bx<T> a;
    private volatile boolean d = true;
    private Vector<Thread> e = null;
    private Runnable f;
    private Runnable g;
    private bz h;

    public j(bl bl2, Context context) {
        super(bl2, context);
        this.f = new k(this);
        this.g = new l(this);
        if (this.e == null) {
            this.e = new Vector();
        }
        this.h = new bz(this.e(), this.g, this.f);
        this.h.a();
    }

    static /* synthetic */ Vector a(j j2) {
        return j2.e;
    }

    static /* synthetic */ boolean a(j j2, boolean bl2) {
        j2.d = bl2;
        return bl2;
    }

    static /* synthetic */ boolean b(j j2) {
        return j2.d;
    }

    protected abstract ArrayList<T> a(ArrayList<T> var1);

    @Override
    public void a() {
        this.a.a();
        this.d();
        this.a.c();
        this.a = null;
        this.b = null;
        this.c = null;
    }

    protected abstract ArrayList<T> b(ArrayList<T> var1);

    @Override
    public void b() {
        super.b();
        this.d();
    }

    @Override
    public void c() {
        this.d = true;
        if (this.e == null) {
            this.e = new Vector();
        }
        if (this.h == null) {
            this.h = new bz(this.e(), this.g, this.f);
            this.h.a();
        }
    }

    public void d() {
        this.d = false;
        if (this.e != null) {
            int n2 = this.e.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                Thread thread = (Thread)this.e.get(0);
                if (thread == null) continue;
                thread.interrupt();
                this.e.remove(0);
            }
            this.e = null;
        }
        if (this.h != null) {
            this.h.b();
            this.h = null;
        }
    }

    protected abstract int e();

    protected abstract int f();
}

