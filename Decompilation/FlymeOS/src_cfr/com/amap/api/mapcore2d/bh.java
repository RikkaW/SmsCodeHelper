/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Point
 *  android.graphics.PointF
 *  java.lang.Math
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.n;
import com.amap.api.mapcore2d.y;
import com.amap.api.mapcore2d.z;
import java.util.ArrayList;

class bh {
    double a = 156543.0339;
    int b = 0;
    double c = -2.003750834E7;
    double d = 2.003750834E7;
    public int e = y.d;
    public int f = y.c;
    public int g = 10;
    public double[] h = null;
    public ae i = null;
    public ae j = null;
    public Point k = null;
    public a l = null;
    bl.d m = null;
    private double n = 116.39716;
    private double o = 39.91669;
    private double p = 0.01745329251994329;

    public bh(bl.d d2) {
        this.m = d2;
    }

    private int a(int n2, int n3) {
        int n4 = 1;
        for (int i2 = 0; i2 < n3; ++i2) {
            n4 *= n2;
        }
        return n4;
    }

    public float a(ae ae2, ae ae3) {
        double d2 = z.a(ae2.c());
        double d3 = z.a(ae2.d());
        double d4 = z.a(ae3.c());
        double d5 = z.a(ae3.d());
        double d6 = d2 * this.p;
        double d7 = d3 * this.p;
        d3 = d4 * this.p;
        d2 = d5 * this.p;
        d5 = Math.sin((double)d6);
        d4 = Math.sin((double)d7);
        d6 = Math.cos((double)d6);
        d7 = Math.cos((double)d7);
        double d8 = Math.sin((double)d3);
        double d9 = Math.sin((double)d2);
        d3 = Math.cos((double)d3);
        d2 = Math.cos((double)d2);
        ae2 = (ae)new double[3];
        ae3 = (ae)new double[3];
        ae2[0] = (ae)(d6 * d7);
        ae2[1] = (ae)(d7 * d5);
        ae2[2] = (ae)d4;
        ae3[0] = (ae)(d2 * d3);
        ae3[1] = (ae)(d2 * d8);
        ae3[2] = (ae)d9;
        return (float)(Math.asin((double)(Math.sqrt((double)((ae2[0] - ae3[0]) * (ae2[0] - ae3[0]) + (ae2[1] - ae3[1]) * (ae2[1] - ae3[1]) + (ae2[2] - ae3[2]) * (ae2[2] - ae3[2]))) / 2.0)) * 1.27420015798544E7);
    }

    /*
     * Enabled aggressive block sorting
     */
    PointF a(int n2, int n3, int n4, int n5, PointF pointF, int n6, int n7) {
        PointF pointF2 = new PointF();
        pointF2.x = (float)((n2 - n4) * y.i) + pointF.x;
        if (this.b == 0) {
            pointF2.y = (float)((n3 - n5) * y.i) + pointF.y;
        } else if (this.b == 1) {
            pointF2.y = pointF.y - (float)((n3 - n5) * y.i);
        }
        if (pointF2.x + (float)y.i <= 0.0f) return null;
        if (pointF2.x >= (float)n6) return null;
        if (pointF2.y + (float)y.i <= 0.0f) return null;
        pointF = pointF2;
        if (pointF2.y < (float)n7) return pointF;
        return null;
    }

    PointF a(ae ae2, ae ae3, Point point, double d2) {
        PointF pointF = new PointF();
        pointF.x = (float)((ae2.e() - ae3.e()) / d2 + (double)point.x);
        pointF.y = (float)((double)point.y - (ae2.f() - ae3.f()) / d2);
        return pointF;
    }

    public ae a(PointF pointF, ae ae2, Point point, double d2, a a2) {
        return this.b(this.b(pointF, ae2, point, d2, a2));
    }

    public ae a(ae ae2) {
        if (ae2 == null) {
            return null;
        }
        double d2 = (double)ae2.b() / 1000000.0;
        double d3 = (double)ae2.a() / 1000000.0 * 2.003750834E7 / 180.0;
        return new ae(Math.log((double)Math.tan((double)((d2 + 90.0) * 3.141592653589793 / 360.0))) / 0.017453292519943295 * 2.003750834E7 / 180.0, d3, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public ArrayList<n.a> a(ae ae2, int n2, int n3, int n4) {
        int n5;
        double d2 = this.h[this.g];
        int n6 = (int)((ae2.e() - this.c) / ((double)y.i * d2));
        double d3 = y.i * n6;
        double d4 = this.c;
        double d5 = 0.0;
        if (this.b == 0) {
            n5 = (int)((this.d - ae2.f()) / ((double)y.i * d2));
            d5 = this.d - (double)(y.i * n5) * d2;
        } else if (this.b == 1) {
            n5 = (int)((ae2.f() - this.d) / ((double)y.i * d2));
            d5 = (double)((n5 + 1) * y.i) * d2;
        } else {
            n5 = 0;
        }
        ae2 = this.a(new ae(d5, d4 + d3 * d2, false), ae2, this.k, d2);
        n.a a2 = new n.a(n6, n5, this.g, -1);
        a2.f = ae2;
        ArrayList arrayList = new ArrayList();
        arrayList.add((Object)a2);
        int n7 = 1;
        do {
            int n8;
            int n9;
            n.a a3;
            int n10;
            n2 = 0;
            for (n9 = n6 - n7; n9 <= n6 + n7; ++n9) {
                n10 = n5 + n7;
                a2 = this.a(n9, n10, n6, n5, (PointF)ae2, n3, n4);
                n8 = n2;
                if (a2 != null) {
                    if (n2 == 0) {
                        n2 = 1;
                    }
                    a3 = new n.a(n9, n10, this.g, -1);
                    a3.f = a2;
                    arrayList.add((Object)a3);
                    n8 = n2;
                }
                n10 = n5 - n7;
                a2 = this.a(n9, n10, n6, n5, (PointF)ae2, n3, n4);
                n2 = n8;
                if (a2 == null) continue;
                n2 = n8;
                if (n8 == 0) {
                    n2 = 1;
                }
                a3 = new n.a(n9, n10, this.g, -1);
                a3.f = a2;
                arrayList.add((Object)a3);
            }
            n8 = n2;
            for (n9 = n5 + n7 - 1; n9 > n5 - n7; --n9) {
                n10 = n6 + n7;
                a2 = this.a(n10, n9, n6, n5, (PointF)ae2, n3, n4);
                n2 = n8;
                if (a2 != null) {
                    n2 = n8 == 0 ? 1 : n8;
                    a3 = new n.a(n10, n9, this.g, -1);
                    a3.f = a2;
                    arrayList.add((Object)a3);
                }
                n10 = n6 - n7;
                a2 = this.a(n10, n9, n6, n5, (PointF)ae2, n3, n4);
                n8 = n2;
                if (a2 == null) continue;
                n8 = n2;
                if (n2 == 0) {
                    n8 = 1;
                }
                a3 = new n.a(n10, n9, this.g, -1);
                a3.f = a2;
                arrayList.add((Object)a3);
            }
            if (n8 == 0) {
                return arrayList;
            }
            ++n7;
        } while (true);
    }

    public void a() {
        this.a = this.d * 2.0 / (double)y.i;
        this.h = new double[this.f + 1];
        for (int i2 = 0; i2 <= this.f; ++i2) {
            double d2;
            this.h[i2] = d2 = this.a / (double)this.a(2, i2);
        }
        this.i = this.a(new ae(this.o, this.n, true));
        this.j = this.i.g();
        this.k = new Point(this.m.c() / 2, this.m.d() / 2);
        this.l = new a();
        this.l.a = -2.0037508E7f;
        this.l.b = 2.0037508E7f;
        this.l.c = 2.0037508E7f;
        this.l.d = -2.0037508E7f;
    }

    public void a(Point point) {
        this.k = point;
    }

    public void a(PointF object, PointF object2, int n2) {
        double d2 = this.h[n2];
        object = this.b((PointF)object, this.i, this.k, d2, this.l);
        object2 = this.b((PointF)object2, this.i, this.k, d2, this.l);
        d2 = object2.e();
        double d3 = object.e();
        double d4 = object2.f();
        double d5 = object.f();
        d2 = this.i.e() + (d2 - d3);
        d5 = this.i.f() + (d4 - d5);
        do {
            d4 = d2;
            if (d2 >= (double)this.l.a) break;
            d2 += (double)(this.l.b - this.l.a);
        } while (true);
        do {
            d2 = d5;
            if (d4 <= (double)this.l.b) break;
            d4 -= (double)(this.l.b - this.l.a);
        } while (true);
        do {
            if (d2 >= (double)this.l.d) break;
            d2 += (double)(this.l.c - this.l.d);
        } while (true);
        for (d5 = d2; d5 > (double)this.l.c; d5 -= (double)(this.l.c - this.l.d)) {
        }
        this.i.b(d5);
        this.i.a(d4);
    }

    public PointF b(ae ae2, ae ae3, Point point, double d2) {
        ae2 = this.a(this.a(ae2), ae3, point, d2);
        return this.m.g().b((PointF)ae2);
    }

    /*
     * Enabled aggressive block sorting
     */
    ae b(PointF pointF, ae ae2, Point point, double d2, a a2) {
        pointF = this.m.g().c(pointF);
        float f2 = pointF.x;
        float f3 = point.x;
        float f4 = pointF.y;
        float f5 = point.y;
        double d3 = ae2.e();
        double d4 = ae2.f();
        double d5 = f4 - f5;
        for (d3 = (double)(f2 - f3) * d2 + d3; d3 < (double)a2.a; d3 += (double)(a2.b - a2.a)) {
        }
        while (d3 > (double)a2.b) {
            d3 -= (double)(a2.b - a2.a);
        }
        for (d2 = d4 - d5 * d2; d2 < (double)a2.d; d2 += (double)(a2.c - a2.d)) {
        }
        while (d2 > (double)a2.c) {
            d2 -= (double)(a2.c - a2.d);
        }
        return new ae(d2, d3, false);
    }

    public ae b(ae ae2) {
        float f2 = (float)(ae2.e() * 180.0 / 2.003750834E7);
        return new ae((int)((double)((float)(57.29577951308232 * (2.0 * Math.atan((double)Math.exp((double)((double)((float)(ae2.f() * 180.0 / 2.003750834E7)) * 3.141592653589793 / 180.0))) - 1.5707963267948966))) * 1000000.0), (int)((double)f2 * 1000000.0));
    }

    static class a {
        float a;
        float b;
        float c;
        float d;

        a() {
        }
    }

}

