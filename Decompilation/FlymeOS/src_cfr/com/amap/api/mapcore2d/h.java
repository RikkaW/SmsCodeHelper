/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;
import com.amap.api.mapcore2d.i;
import com.amap.api.mapcore2d.v;

abstract class h {
    protected int a;
    protected int b;
    private Handler c = null;
    private int d = 0;
    private boolean e = false;
    private Runnable f;

    public h(int n2, int n3) {
        this.f = new i(this);
        this.a = n2;
        this.b = n3;
    }

    static /* synthetic */ Handler a(h h2, Handler handler) {
        h2.c = handler;
        return handler;
    }

    static /* synthetic */ void a(h h2) {
        h2.g();
    }

    static /* synthetic */ Handler b(h h2) {
        return h2.c;
    }

    static /* synthetic */ void c(h h2) {
        h2.h();
    }

    private void f() {
        this.e = false;
    }

    private void g() {
        this.d += this.b;
        if (this.a != -1 && this.d > this.a) {
            this.f();
        }
    }

    private void h() {
        if (this.c != null) {
            this.c.post(this.f);
        }
    }

    protected abstract void a();

    public void a(boolean bl2) {
        this.e = bl2;
    }

    protected abstract void b();

    public void c() {
        if (!this.e()) {
            this.c = new Handler(Looper.getMainLooper());
            this.e = true;
            this.d = 0;
        }
        this.h();
    }

    public void d() {
        v.a().b();
        this.f();
        this.f.run();
    }

    public boolean e() {
        return this.e;
    }
}

