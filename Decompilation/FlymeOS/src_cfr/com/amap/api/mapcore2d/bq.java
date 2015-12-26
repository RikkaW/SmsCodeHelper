/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.DashPathEffect
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.PathEffect
 *  android.graphics.Point
 *  android.os.RemoteException
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.ap;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class bq
implements ap {
    private b a;
    private float b = 10.0f;
    private int c = -16777216;
    private float d = 0.0f;
    private boolean e = true;
    private boolean f = false;
    private boolean g = false;
    private String h;
    private List<an> i = new ArrayList();
    private List<LatLng> j = new ArrayList();
    private LatLngBounds k = null;

    public bq(b b2) {
        this.a = b2;
        try {
            this.h = this.c();
            return;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "PolylineDelegateImp", "PolylineDelegateImp");
            return;
        }
    }

    private List<LatLng> m() {
        if (this.i != null) {
            ArrayList arrayList = new ArrayList();
            for (an an2 : this.i) {
                if (an2 == null) continue;
                aa aa2 = new aa();
                this.a.b(an2.a, an2.b, aa2);
                arrayList.add(new LatLng(aa2.b, aa2.a));
            }
            return arrayList;
        }
        return null;
    }

    an a(an an2, an an3, an an4, double d2, int n2) {
        an an5 = new an();
        double d3 = an3.a - an2.a;
        double d4 = an3.b - an2.b;
        double d5 = d4 * d4 / (d3 * d3);
        an5.b = (int)((double)n2 * d2 / Math.sqrt((double)(d5 + 1.0)) + (double)an4.b);
        an5.a = (int)(d4 * (double)(an4.b - an5.b) / d3 + (double)an4.a);
        return an5;
    }

    @Override
    public void a(float f2) {
        this.d = f2;
        this.a.invalidate();
    }

    @Override
    public void a(int n2) {
        this.c = n2;
    }

    @Override
    public void a(Canvas canvas) {
        int n2;
        if (this.i == null || this.i.size() == 0 || this.b <= 0.0f) {
            return;
        }
        Path path = new Path();
        ae ae2 = new ae(this.i.get((int)0).b, this.i.get((int)0).a);
        Point point = new Point();
        ae2 = this.a.s().a(ae2, point);
        path.moveTo((float)ae2.x, (float)ae2.y);
        for (n2 = 1; n2 < this.i.size(); ++n2) {
            ae2 = new ae(this.i.get((int)n2).b, this.i.get((int)n2).a);
            point = new Point();
            ae2 = this.a.s().a(ae2, point);
            path.lineTo((float)ae2.x, (float)ae2.y);
        }
        ae2 = new Paint();
        ae2.setColor(this.h());
        ae2.setAntiAlias(true);
        ae2.setStrokeWidth(this.g());
        ae2.setStyle(Paint.Style.STROKE);
        if (this.f) {
            n2 = (int)this.g();
            ae2.setPathEffect((PathEffect)new DashPathEffect(new float[]{n2 * 3, n2, n2 * 3, n2}, 1.0f));
        }
        canvas.drawPath(path, (Paint)ae2);
    }

    /*
     * Enabled aggressive block sorting
     */
    void a(LatLng object, LatLng object2, List<an> list, LatLngBounds.Builder object3) {
        double d2 = Math.abs((double)(object.longitude - object2.longitude)) * 3.141592653589793 / 180.0;
        LatLng latLng = new LatLng((object2.latitude + object.latitude) / 2.0, (object2.longitude + object.longitude) / 2.0);
        object3.include((LatLng)object).include(latLng).include((LatLng)object2);
        int n2 = latLng.latitude > 0.0 ? 1 : -1;
        object3 = new an();
        this.a.a(object.latitude, object.longitude, (an)object3);
        object = new an();
        this.a.a(object2.latitude, object2.longitude, (an)object);
        object2 = new an();
        this.a.a(latLng.latitude, latLng.longitude, (an)object2);
        double d3 = Math.cos((double)(0.5 * d2));
        object2 = this.a((an)object3, (an)object, (an)object2, Math.hypot((double)(object3.a - object.a), (double)(object3.b - object.b)) * 0.5 * Math.tan((double)(0.5 * d2)), n2);
        latLng = new ArrayList();
        latLng.add(object3);
        latLng.add(object2);
        latLng.add(object);
        this.a((List<an>)((Object)latLng), list, d3);
    }

    @Override
    public void a(List<LatLng> list) {
        if (this.g || this.f) {
            this.j = list;
        }
        this.b(list);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    void a(List<an> list, List<an> list2, double d2) {
        if (list.size() != 3) {
            return;
        }
        int n2 = 0;
        while (n2 <= 10) {
            float f2 = (float)n2 / 10.0f;
            an an2 = new an();
            double d3 = f2;
            double d4 = f2;
            double d5 = list.get((int)0).a;
            double d6 = 2.0f * f2;
            double d7 = f2;
            double d8 = list.get((int)1).a;
            double d9 = (float)list.get((int)2).a * (f2 * f2);
            double d10 = f2;
            double d11 = f2;
            double d12 = list.get((int)0).b;
            double d13 = 2.0f * f2;
            double d14 = f2;
            double d15 = list.get((int)1).b;
            double d16 = (float)list.get((int)2).b * (f2 * f2);
            double d17 = f2;
            double d18 = f2;
            double d19 = 2.0f * f2;
            double d20 = f2;
            double d21 = f2 * f2;
            double d22 = f2;
            double d23 = f2;
            double d24 = 2.0f * f2;
            double d25 = f2;
            double d26 = f2 * f2;
            an2.a = (int)(((1.0 - d3) * (1.0 - d4) * d5 + d6 * (1.0 - d7) * d8 * d2 + d9) / ((1.0 - d17) * (1.0 - d18) + d19 * (1.0 - d20) * d2 + d21));
            an2.b = (int)(((1.0 - d10) * (1.0 - d11) * d12 + d13 * (1.0 - d14) * d15 * d2 + d16) / ((1.0 - d22) * (1.0 - d23) + d24 * (1.0 - d25) * d2 + d26));
            list2.add(an2);
            n2 = (int)((float)n2 + 1.0f);
        }
    }

    @Override
    public void a(boolean bl2) {
        this.e = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean a() {
        if (this.k == null) {
            return false;
        }
        LatLngBounds latLngBounds = this.a.x();
        if (latLngBounds == null) {
            return true;
        }
        if (latLngBounds.contains(this.k)) return true;
        if (!this.k.intersects(latLngBounds)) return false;
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
        this.a.a(this.c());
    }

    @Override
    public void b(float f2) {
        this.b = f2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    void b(List<LatLng> object) {
        if (object == null) return;
        if (object.size() == 0) {
            return;
        }
        LatLngBounds.Builder builder = LatLngBounds.builder();
        this.i.clear();
        if (object != null) {
            Iterator iterator = object.iterator();
            object = null;
            while (iterator.hasNext()) {
                LatLng latLng = (LatLng)iterator.next();
                if (latLng == null || latLng.equals(object)) continue;
                if (!this.g) {
                    object = new an();
                    this.a.a(latLng.latitude, latLng.longitude, (an)object);
                    this.i.add((an)object);
                    builder.include(latLng);
                } else if (object != null) {
                    if (Math.abs((double)(latLng.longitude - object.longitude)) < 0.01) {
                        an an2 = new an();
                        this.a.a(object.latitude, object.longitude, an2);
                        this.i.add(an2);
                        builder.include((LatLng)object);
                        object = new an();
                        this.a.a(latLng.latitude, latLng.longitude, (an)object);
                        this.i.add((an)object);
                        builder.include(latLng);
                    } else {
                        this.a((LatLng)object, latLng, this.i, builder);
                    }
                }
                object = latLng;
            }
        }
        if (this.i.size() <= 0) return;
        this.k = builder.build();
    }

    @Override
    public void b(boolean bl2) {
        this.f = bl2;
    }

    @Override
    public String c() {
        if (this.h == null) {
            this.h = ac.a("Polyline");
        }
        return this.h;
    }

    @Override
    public void c(boolean bl2) {
        if (this.g != bl2) {
            this.g = bl2;
        }
    }

    @Override
    public float d() {
        return this.d;
    }

    @Override
    public boolean e() {
        return this.e;
    }

    @Override
    public int f() {
        return super.hashCode();
    }

    @Override
    public float g() {
        return this.b;
    }

    @Override
    public int h() {
        return this.c;
    }

    @Override
    public List<LatLng> i() {
        if (this.g || this.f) {
            return this.j;
        }
        return this.m();
    }

    @Override
    public boolean j() {
        return this.f;
    }

    @Override
    public boolean k() {
        return this.g;
    }

    @Override
    public void l() {
    }
}

