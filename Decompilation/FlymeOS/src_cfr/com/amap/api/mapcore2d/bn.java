/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Color
 *  android.os.RemoteException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.graphics.Color;
import android.os.RemoteException;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.ah;
import com.amap.api.mapcore2d.ak;
import com.amap.api.mapcore2d.ar;
import com.amap.api.mapcore2d.bk;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

class bn {
    private ag a;
    private ak b;
    private ah c;
    private MyLocationStyle d;
    private LatLng e;
    private double f;

    bn(ag ag2) {
        this.a = ag2;
    }

    private void b() {
        if (this.d == null) {
            this.c();
            return;
        }
        this.d();
    }

    private void c() {
        try {
            this.c = this.a.a(new CircleOptions().strokeWidth(1.0f).fillColor(Color.argb((int)20, (int)0, (int)0, (int)180)).strokeColor(Color.argb((int)255, (int)0, (int)0, (int)220)).center(new LatLng(0.0, 0.0)));
            this.c.a(200.0);
            this.b = this.a.b(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromAsset(ar.a.c.name() + ".png")).position(new LatLng(0.0, 0.0)));
            return;
        }
        catch (RemoteException var1_1) {
            cy.a((Throwable)var1_1, "MyLocationOverlay", "defaultLocStyle");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void d() {
        if (this.d == null) {
            return;
        }
        try {
            this.c = this.a.a(new CircleOptions().strokeWidth(this.d.getStrokeWidth()).fillColor(this.d.getRadiusFillColor()).strokeColor(this.d.getStrokeColor()).center(new LatLng(0.0, 0.0)));
            if (this.e != null) {
                this.c.a(this.e);
            }
            this.c.a(this.f);
            this.b = this.a.b(new MarkerOptions().anchor(this.d.getAnchorU(), this.d.getAnchorV()).icon(this.d.getMyLocationIcon()).position(new LatLng(0.0, 0.0)));
            if (this.e == null) return;
            this.b.b(this.e);
            return;
        }
        catch (RemoteException var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    public void a() {
        if (this.c != null) {
            this.a.a(this.c.c());
            this.c = null;
        }
        if (this.b != null) {
            this.a.b(this.b.d());
            this.b = null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(float f2) {
        if (this.b == null) return;
        try {
            this.b.a(f2);
            return;
        }
        catch (RemoteException var2_2) {
            cy.a((Throwable)var2_2, "MyLocationOverlay", "setRotateAngle");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(LatLng latLng, double d2) {
        block5 : {
            this.e = latLng;
            this.f = d2;
            if (this.b == null && this.c == null) {
                this.b();
            }
            if (this.b != null) {
                this.b.b(latLng);
                this.c.a(latLng);
                if (d2 != -1.0) break block5;
            }
            return;
        }
        try {
            this.c.a(d2);
            return;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "MyLocationOverlay", "setCentAndRadius");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(MyLocationStyle myLocationStyle) {
        this.d = myLocationStyle;
        if (this.b == null && this.c == null) {
            return;
        }
        try {
            this.a();
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "MyLocationOverlay", "setMyLocationStyle");
        }
        this.d();
    }
}

