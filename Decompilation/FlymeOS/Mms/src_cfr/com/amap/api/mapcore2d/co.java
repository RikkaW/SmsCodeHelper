/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Matrix
 *  android.graphics.Point
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 */
package com.amap.api.mapcore2d;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.animation.Animation;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bb;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.bm;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.h;

class co
extends h {
    static float c = 1.0f;
    public int d = -1;
    public boolean e = false;
    private Animation.AnimationListener f;
    private b g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private boolean m;
    private boolean n = false;

    public co(b b2, Animation.AnimationListener animationListener) {
        super(160, 40);
        this.g = b2;
        this.f = animationListener;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a() {
        c = this.m ? (c += this.l) : (c -= this.l);
        Matrix matrix = new Matrix();
        matrix.setScale(c, c, this.h, this.i);
        this.g.c(c);
        this.g.b(matrix);
    }

    public void a(float f2, int n2, boolean bl2, float f3, float f4) {
        this.m = bl2;
        this.h = f3;
        this.i = f4;
        c = this.j = f2;
        if (this.m) {
            this.l = this.j * (float)this.b / (float)this.a;
            this.k = this.j * 2.0f;
            return;
        }
        this.l = this.j * 0.5f * (float)this.b / (float)this.a;
        this.k = this.j * 0.5f;
    }

    public void a(int n2, boolean bl2, float f2, float f3) {
        this.g.b[0] = this.g.b[1];
        this.g.b[1] = n2;
        if (this.g.b[0] == this.g.b[1]) {
            return;
        }
        this.g.a().a(this.g.B());
        if (!this.e()) {
            this.a = 160;
            this.a(this.g.H(), n2, bl2, f2, f3);
            this.g.a().d.a(true);
            this.g.a().d.b = true;
            this.f.onAnimationStart(null);
            super.c();
            return;
        }
        this.n = true;
        this.d();
        this.a(this.k, n2, bl2, f2, f3);
        this.g.a().d.a(true);
        this.g.a().d.b = true;
        this.f.onAnimationStart(null);
        super.c();
        this.n = false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    protected void b() {
        if (this.n) {
            return;
        }
        try {
            Point point;
            ae ae2;
            if (this.g == null) return;
            if (this.g.a() == null) return;
            this.g.a().d.b = false;
            if (this.e) {
                point = new Point((int)this.h, (int)this.i);
                ae2 = this.g.s().a((int)this.h, (int)this.i);
                this.g.a().h.i = this.g.a().h.a(ae2);
                this.g.a().h.a(point);
                this.g.a().b.a(false, false);
            }
            this.g.D().c(this.d);
            this.f.onAnimationEnd(null);
            if (this.e) {
                point = new Point(this.g.a().b.c() / 2, this.g.a().b.d() / 2);
                ae2 = this.g.s().a(this.g.a().b.c() / 2, this.g.a().b.d() / 2);
                this.g.a().h.i = this.g.a().h.a(ae2);
                this.g.a().h.a(point);
                this.g.a().b.a(false, false);
            }
            this.g.e(0);
            c = 1.0f;
            bm.j = 1.0f;
            this.g.a().a(true);
            return;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "ZoomCtlAnim", "onStop");
            return;
        }
    }
}

