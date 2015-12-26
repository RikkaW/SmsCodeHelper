/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Math
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.bv;
import com.amap.api.mapcore2d.ch;
import com.amap.api.mapcore2d.h;
import com.amap.api.mapcore2d.t;

class cg
extends h {
    private ae c;
    private ae d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private ch l;

    public cg(int n2, int n3, ae ae2, ae ae3, int n4, ch ch2) {
        super(n2, n3);
        this.c = ae2;
        this.d = ae3;
        this.e = (int)this.c.e();
        this.f = (int)this.c.f();
        this.l = ch2;
        this.i = (int)Math.abs((double)(ae3.e() - this.c.e()));
        this.j = (int)Math.abs((double)(ae3.f() - this.c.f()));
        this.a(n4);
    }

    private int a(int n2, int n3, int n4) {
        if (n3 > n2) {
            n2 = n4 = n2 + n4;
            if (n4 >= n3) {
                this.k = 0;
                return n3;
            }
        } else {
            n2 = n4 = n2 - n4;
            if (n4 <= n3) {
                this.k = 0;
                return n3;
            }
        }
        return n2;
    }

    private void a(int n2) {
        int n3 = 2;
        if ((n2 = n2 / 10 / 10) < 2) {
            n2 = n3;
        }
        this.g = this.i / n2;
        this.h = this.j / n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a() {
        int n2 = (int)this.d.e();
        int n3 = (int)this.d.f();
        if (!this.e()) {
            this.e = n2;
            this.f = n3;
            this.l.a(new ae(this.f, this.e, false));
            return;
        } else {
            ++this.k;
            this.e = this.a(this.e, n2, this.g);
            this.f = this.a(this.f, n3, this.h);
            this.l.a(new ae(this.f, this.e, false));
            if (this.e != n2 || this.f != n3) return;
            {
                this.a(false);
                this.f();
                return;
            }
        }
    }

    @Override
    protected void b() {
        this.l.b();
        t.a().b();
    }

    protected void f() {
        bv.a().b();
    }
}

