/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Path
 *  android.graphics.Point
 *  android.os.RemoteException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.am;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.ao;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class bp
implements ao {
    private b a;
    private float b = 0.0f;
    private boolean c = true;
    private String d;
    private float e;
    private int f;
    private int g;
    private List<an> h = new ArrayList();
    private LatLngBounds i = null;

    public bp(b b2) {
        this.a = b2;
        try {
            this.d = this.c();
            return;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "PolygonDelegateImp", "PolygonDelegateImp");
            return;
        }
    }

    @Override
    public void a(float f2) {
        this.b = f2;
        this.a.invalidate();
    }

    @Override
    public void a(int n2) {
        this.f = n2;
    }

    @Override
    public void a(Canvas canvas) {
        if (this.h == null || this.h.size() == 0) {
            return;
        }
        Path path = new Path();
        ae ae2 = new ae(this.h.get((int)0).b, this.h.get((int)0).a);
        Point point = new Point();
        ae2 = this.a.s().a(ae2, point);
        path.moveTo((float)ae2.x, (float)ae2.y);
        for (int i2 = 1; i2 < this.h.size(); ++i2) {
            ae2 = new ae(this.h.get((int)i2).b, this.h.get((int)i2).a);
            point = new Point();
            ae2 = this.a.s().a(ae2, point);
            path.lineTo((float)ae2.x, (float)ae2.y);
        }
        ae2 = new Paint();
        ae2.setColor(this.h());
        ae2.setAntiAlias(true);
        path.close();
        ae2.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, (Paint)ae2);
        ae2.setStyle(Paint.Style.STROKE);
        ae2.setColor(this.j());
        ae2.setStrokeWidth(this.g());
        canvas.drawPath(path, (Paint)ae2);
    }

    @Override
    public void a(List<LatLng> list) {
        this.b(list);
    }

    @Override
    public void a(boolean bl2) {
        this.c = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public boolean a() {
        if (this.i == null) {
            return false;
        }
        LatLngBounds latLngBounds = this.a.x();
        if (latLngBounds == null) {
            return true;
        }
        if (this.i.contains(latLngBounds)) return true;
        if (!this.i.intersects(latLngBounds)) return false;
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
    public boolean a(LatLng latLng) {
        return cy.a(latLng, this.i());
    }

    @Override
    public void b() {
        this.a.a(this.c());
    }

    @Override
    public void b(float f2) {
        this.e = f2;
    }

    @Override
    public void b(int n2) {
        this.g = n2;
    }

    void b(List<LatLng> object) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        this.h.clear();
        if (object != null) {
            Object object2;
            Iterator<LatLng> iterator = object.iterator();
            object = null;
            while (iterator.hasNext()) {
                object2 = iterator.next();
                if (object2.equals(object)) continue;
                object = new an();
                this.a.a(object2.latitude, object2.longitude, (an)object);
                this.h.add((an)object);
                builder.include((LatLng)object2);
                object = object2;
            }
            int n2 = this.h.size();
            if (n2 > 1) {
                object = this.h.get(0);
                object2 = this.h.get(n2 - 1);
                if (object.a == object2.a && object.b == object2.b) {
                    this.h.remove(n2 - 1);
                }
            }
        }
        this.i = builder.build();
    }

    @Override
    public String c() {
        if (this.d == null) {
            this.d = ac.a("Polygon");
        }
        return this.d;
    }

    @Override
    public float d() {
        return this.b;
    }

    @Override
    public boolean e() {
        return this.c;
    }

    @Override
    public int f() {
        return super.hashCode();
    }

    @Override
    public float g() {
        return this.e;
    }

    @Override
    public int h() {
        return this.f;
    }

    @Override
    public List<LatLng> i() {
        return this.k();
    }

    @Override
    public int j() {
        return this.g;
    }

    List<LatLng> k() {
        if (this.h != null) {
            ArrayList arrayList = new ArrayList();
            for (an an2 : this.h) {
                if (an2 == null) continue;
                aa aa2 = new aa();
                this.a.b(an2.a, an2.b, aa2);
                arrayList.add(new LatLng(aa2.b, aa2.a));
            }
            return arrayList;
        }
        return null;
    }

    @Override
    public void l() {
    }
}

