/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Point
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  android.os.RemoteException
 *  android.util.Log
 *  java.lang.Double
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ai;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cp;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

class af
implements ai {
    private final double a = 0.01745329251994329;
    private final double b = 6371000.79;
    private b c;
    private BitmapDescriptor d;
    private LatLng e;
    private float f;
    private float g;
    private LatLngBounds h;
    private float i;
    private float j;
    private boolean k = true;
    private float l = 0.0f;
    private float m = 0.5f;
    private float n = 0.5f;
    private String o;
    private Bitmap p;

    af(b b2) {
        this.c = b2;
        try {
            this.o = this.c();
            return;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "GroundOverlayDelegateImp", "GroundOverlayDelegateImp");
            return;
        }
    }

    private ae b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new ae((int)(latLng.latitude * 1000000.0), (int)(latLng.longitude * 1000000.0));
    }

    private void o() {
        double d2 = (double)this.f / (6371000.79 * Math.cos((double)(this.e.latitude * 0.01745329251994329)) * 0.01745329251994329);
        double d3 = (double)this.g / 111194.94043265979;
        LatLng latLng = new LatLng(this.e.latitude - (double)(1.0f - this.n) * d3, this.e.longitude - (double)this.m * d2);
        double d4 = this.e.latitude;
        double d5 = this.n;
        double d6 = this.e.longitude;
        this.h = new LatLngBounds(latLng, new LatLng(d3 * d5 + d4, d2 * (double)(1.0f - this.m) + d6));
    }

    private void p() {
        LatLng latLng = this.h.southwest;
        LatLng latLng2 = this.h.northeast;
        this.e = new LatLng(latLng.latitude + (double)(1.0f - this.n) * (latLng2.latitude - latLng.latitude), latLng.longitude + (double)this.m * (latLng2.longitude - latLng.longitude));
        this.f = (float)(6371000.79 * Math.cos((double)(this.e.latitude * 0.01745329251994329)) * (latLng2.longitude - latLng.longitude) * 0.01745329251994329);
        this.g = (float)((latLng2.latitude - latLng.latitude) * 6371000.79 * 0.01745329251994329);
    }

    @Override
    public void a(float f2) {
        this.j = f2;
        this.c.invalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(float f2, float f3) {
        boolean bl2 = true;
        boolean bl3 = f2 >= 0.0f;
        cp.b(bl3, "Width must be non-negative");
        bl3 = f3 >= 0.0f ? bl2 : false;
        cp.b(bl3, "Height must be non-negative");
        if (this.f != f2 && this.g != f3) {
            this.f = f2;
            this.g = f3;
            return;
        }
        this.f = f2;
        this.g = f3;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(Canvas canvas) {
        if (!this.k) return;
        if (this.e == null) {
            if (this.h == null) return;
        }
        if (this.d == null) {
            return;
        }
        this.g();
        if (this.f == 0.0f) {
            if (this.g == 0.0f) return;
        }
        this.p = this.d.getBitmap();
        if (this.p == null) return;
        if (this.p.isRecycled()) return;
        Object object = this.h.southwest;
        LatLng latLng = this.h.northeast;
        object = this.b((LatLng)object);
        ae ae2 = this.b(latLng);
        latLng = new Point();
        Point point = new Point();
        this.c.s().a((ae)object, (Point)latLng);
        this.c.s().a(ae2, point);
        object = new Paint();
        latLng = new RectF((float)latLng.x, (float)point.y, (float)point.x, (float)latLng.y);
        object.setAlpha((int)(255.0f - this.l * 255.0f));
        object.setFilterBitmap(true);
        canvas.drawBitmap(this.p, null, (RectF)latLng, (Paint)object);
    }

    @Override
    public void a(BitmapDescriptor bitmapDescriptor) {
        this.d = bitmapDescriptor;
    }

    @Override
    public void a(LatLng latLng) {
        if (this.e != null && !this.e.equals(latLng)) {
            this.e = latLng;
            this.o();
            return;
        }
        this.e = latLng;
    }

    @Override
    public void a(LatLngBounds latLngBounds) {
        if (this.h != null && !this.h.equals(latLngBounds)) {
            this.h = latLngBounds;
            this.p();
            return;
        }
        this.h = latLngBounds;
    }

    @Override
    public void a(boolean bl2) {
        this.k = bl2;
        this.c.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean a() {
        if (this.h == null) {
            return false;
        }
        LatLngBounds latLngBounds = this.c.x();
        if (latLngBounds == null) {
            return true;
        }
        if (latLngBounds.contains(this.h)) return true;
        if (!this.h.intersects(latLngBounds)) return false;
        return true;
    }

    @Override
    public boolean a(am am2) {
        if (this.equals((Object)am2) || am2.c().equals((Object)this.c())) {
            return true;
        }
        return false;
    }

    @Override
    public void b() {
        this.c.a(this.c());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(float f2) {
        boolean bl2 = f2 >= 0.0f;
        cp.b(bl2, "Width must be non-negative");
        if (this.f != f2) {
            this.f = f2;
            this.g = f2;
            return;
        }
        this.f = f2;
        this.g = f2;
    }

    public void b(float f2, float f3) {
        this.m = f2;
        this.n = f3;
    }

    @Override
    public String c() {
        if (this.o == null) {
            this.o = ac.a("GroundOverlay");
        }
        return this.o;
    }

    @Override
    public void c(float f2) {
        f2 = ((- f2) % 360.0f + 360.0f) % 360.0f;
        if (Double.doubleToLongBits((double)this.i) != Double.doubleToLongBits((double)f2)) {
            this.i = f2;
            return;
        }
        this.i = f2;
    }

    @Override
    public float d() {
        return this.j;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void d(float f2) {
        boolean bl2 = f2 >= 0.0f && f2 <= 1.0f;
        cp.b(bl2, "Transparency must be in the range [0..1]");
        this.l = f2;
    }

    @Override
    public boolean e() {
        return this.k;
    }

    @Override
    public int f() {
        return super.hashCode();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void g() {
        if (this.e == null) {
            this.p();
            return;
        } else {
            if (this.h != null) return;
            {
                this.o();
                return;
            }
        }
    }

    @Override
    public LatLng h() {
        return this.e;
    }

    @Override
    public float i() {
        return this.f;
    }

    @Override
    public float j() {
        return this.g;
    }

    @Override
    public LatLngBounds k() {
        return this.h;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void l() {
        try {
            Bitmap bitmap;
            this.b();
            if (this.d != null && (bitmap = this.d.getBitmap()) != null) {
                bitmap.recycle();
                this.d = null;
            }
            this.e = null;
            this.h = null;
            return;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "GroundOverlayDelegateImp", "destroy");
            Log.d((String)"destroy erro", (String)"GroundOverlayDelegateImp destroy");
            return;
        }
    }

    @Override
    public float m() {
        return this.i;
    }

    @Override
    public float n() {
        return this.l;
    }
}

