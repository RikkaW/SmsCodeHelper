/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Point
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.graphics.Point;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bb;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.t;
import com.amap.api.mapcore2d.u;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;

class a {
    private b a;
    private int b;

    public a(b b2) {
        this.a = b2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected void a(u var1_1) {
        block16 : {
            block17 : {
                try {
                    if (this.a == null) return;
                    if (this.a.D() == null) {
                        return;
                    }
                    var3_3 = (int)this.a.f();
                    if (var1_1.a == u.a.h) {
                        this.a.a.c((int)var1_1.b, (int)var1_1.c);
                        this.a.postInvalidate();
                        break block16;
                    }
                    if (var1_1.a == u.a.b) {
                        this.a.D().c();
                        break block16;
                    }
                    if (var1_1.a == u.a.e) {
                        this.a.D().d();
                        break block16;
                    }
                    if (var1_1.a == u.a.f) {
                        var4_4 = (int)var1_1.d;
                        this.a.D().c(var4_4);
                        break block16;
                    }
                    if (var1_1.a != u.a.g) break block17;
                    var2_8 = var1_1.e;
                    var4_5 = this.a.a((int)(var2_8 + (float)var3_3));
                    var1_1 = var1_1.h;
                }
                catch (Exception var1_2) {
                    cy.a(var1_2, "AMapCallback", "runCameraUpdate");
                    return;
                }
                var2_8 = var4_5 - var3_3;
                if (var1_1 != null) {
                    this.a.a(var2_8, (Point)var1_1, false);
                }
                this.a.D().c(var4_5);
                ** GOTO lbl53
            }
            if (var1_1.a == u.a.i) {
                var1_1 = var1_1.f;
                var4_6 = (int)(var1_1.target.latitude * 1000000.0);
                var5_9 = (int)(var1_1.target.longitude * 1000000.0);
                this.a.D().a(new ae(var4_6, var5_9), (int)var1_1.zoom);
            } else if (var1_1.a == u.a.c) {
                var1_1 = var1_1.f;
                var4_7 = (int)(var1_1.target.latitude * 1000000.0);
                var5_10 = (int)(var1_1.target.longitude * 1000000.0);
                this.a.D().a(new ae(var4_7, var5_10));
                t.a().b();
            } else if (var1_1.a == u.a.j || var1_1.a == u.a.k) {
                this.a.a((u)var1_1, false, -1);
            } else {
                var1_1.i = true;
            }
        }
        if (var3_3 == this.b) return;
        if (this.a.q().a() == false) return;
        this.a.L();
    }
}

