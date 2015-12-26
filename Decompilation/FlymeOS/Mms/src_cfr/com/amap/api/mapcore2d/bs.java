/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Point
 *  android.graphics.PointF
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ab;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.aq;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.VisibleRegion;

class bs
implements aq {
    private String a = "ProjectionDelegateImp";
    private ag b;

    public bs(ag ag2) {
        this.b = ag2;
    }

    @Override
    public Point a(LatLng latLng) {
        an an2 = new an();
        this.b.b(latLng.latitude, latLng.longitude, an2);
        return new Point(an2.a, an2.b);
    }

    @Override
    public LatLng a(Point point) {
        aa aa2 = new aa();
        this.b.a(point.x, point.y, aa2);
        return new LatLng(aa2.b, aa2.a);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public VisibleRegion a() {
        var9_1 = null;
        try {
            var1_2 = this.b.c();
            var2_3 = this.b.d();
            var6_4 = this.a(new Point(0, 0));
        }
        catch (Throwable var4_9) {
            var3_7 = null;
            var5_6 = null;
            var6_4 = null;
            var8_14 = null;
lbl11: // 5 sources:
            do {
                cy.a((Throwable)var4_8, this.a, "getVisibleRegion");
                var7_5 = var6_4;
                var4_8 = var9_1;
                ** continue;
                break;
            } while (true);
        }
        var7_5 = this.a(new Point(var1_2, 0));
        var5_6 = this.a(new Point(0, var2_3));
        var3_7 = this.a(new Point(var1_2, var2_3));
        try {
            var4_8 = LatLngBounds.builder().include(var5_6).include(var3_7).include(var6_4).include(var7_5).build();
            var8_14 = var6_4;
        }
        catch (Throwable var4_13) {
            var8_14 = var6_4;
            var6_4 = var7_5;
            ** continue;
        }
lbl25: // 2 sources:
        do {
            return new VisibleRegion(var5_6, var3_7, var8_14, var7_5, var4_8);
            break;
        } while (true);
        catch (Throwable var4_10) {
            var3_7 = null;
            var5_6 = null;
            var7_5 = null;
            var8_14 = var6_4;
            var6_4 = var7_5;
            ** GOTO lbl11
        }
        catch (Throwable var4_11) {
            var3_7 = null;
            var5_6 = null;
            var8_14 = var6_4;
            var6_4 = var7_5;
            ** GOTO lbl11
        }
        catch (Throwable var4_12) {
            var3_7 = null;
            var8_14 = var6_4;
            var6_4 = var7_5;
            ** GOTO lbl11
        }
    }

    @Override
    public PointF b(LatLng latLng) {
        ab ab2 = new ab();
        this.b.a(latLng.latitude, latLng.longitude, ab2);
        return new PointF(ab2.a, ab2.b);
    }
}

