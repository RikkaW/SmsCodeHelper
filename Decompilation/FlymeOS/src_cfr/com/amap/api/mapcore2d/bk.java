/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Point
 *  android.graphics.Rect
 *  android.util.Log
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.amap.api.mapcore2d.aa;
import com.amap.api.mapcore2d.ab;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.ak;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.be;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.fd;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class bk
implements ak {
    private static int a = 0;
    private int b = 0;
    private float c = 0.0f;
    private CopyOnWriteArrayList<BitmapDescriptor> d = null;
    private int e = 20;
    private String f;
    private LatLng g;
    private LatLng h;
    private String i;
    private String j;
    private float k = 0.5f;
    private float l = 1.0f;
    private boolean m = false;
    private boolean n = true;
    private be o;
    private Object p;
    private boolean q = false;
    private a r;
    private boolean s = false;
    private int t;
    private int u;
    private float v;
    private int w;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public bk(MarkerOptions markerOptions, be be2) {
        this.o = be2;
        this.q = markerOptions.isGps();
        this.v = markerOptions.getZIndex();
        if (markerOptions.getPosition() != null) {
            if (this.q) {
                try {
                    double[] arrd = fd.a(markerOptions.getPosition().longitude, markerOptions.getPosition().latitude);
                    this.h = new LatLng(arrd[1], arrd[0]);
                }
                catch (Exception var2_4) {
                    cy.a(var2_4, "MarkerDelegateImp", "MarkerDelegateImp");
                    this.h = markerOptions.getPosition();
                }
            }
            this.g = markerOptions.getPosition();
        }
        this.k = markerOptions.getAnchorU();
        this.l = markerOptions.getAnchorV();
        this.n = markerOptions.isVisible();
        this.j = markerOptions.getSnippet();
        this.i = markerOptions.getTitle();
        this.m = markerOptions.isDraggable();
        this.e = markerOptions.getPeriod();
        this.f = this.d();
        this.b(markerOptions.getIcons());
        if (this.d != null && this.d.size() == 0) {
            this.b(markerOptions.getIcon());
        }
    }

    private an b(float f2, float f3) {
        float f4 = (float)(3.141592653589793 * (double)this.c / 180.0);
        an an2 = new an();
        an2.a = (int)((double)f2 * Math.cos((double)f4) + (double)f3 * Math.sin((double)f4));
        an2.b = (int)((double)f3 * Math.cos((double)f4) - (double)f2 * Math.sin((double)f4));
        return an2;
    }

    private void b(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.w();
            this.d.add((Object)bitmapDescriptor.clone());
        }
    }

    static /* synthetic */ int c(bk bk2) {
        int n2 = bk2.b;
        bk2.b = n2 + 1;
        return n2;
    }

    private static String c(String string2) {
        return string2 + ++a;
    }

    /*
     * Enabled aggressive block sorting
     */
    public BitmapDescriptor A() {
        if (this.d == null || this.d.size() == 0) {
            this.w();
            this.d.add((Object)BitmapDescriptorFactory.defaultMarker());
            return (BitmapDescriptor)this.d.get(0);
        } else {
            if (this.d.get(0) != null) return (BitmapDescriptor)this.d.get(0);
            {
                this.d.clear();
                return this.A();
            }
        }
    }

    public float B() {
        return this.k;
    }

    public float C() {
        return this.l;
    }

    @Override
    public void a(float f2) {
        this.c = ((- f2) % 360.0f + 360.0f) % 360.0f;
        if (this.k()) {
            this.o.e(this);
            this.o.d(this);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(float f2, float f3) {
        if (this.k == f2 && this.l == f3) {
            return;
        }
        this.k = f2;
        this.l = f3;
        if (!this.k()) return;
        this.o.e(this);
        this.o.d(this);
    }

    @Override
    public void a(int n2) {
        if (n2 <= 1) {
            this.e = 1;
            return;
        }
        this.e = n2;
    }

    @Override
    public void a(int n2, int n3) {
        this.t = n2;
        this.u = n3;
        this.s = true;
        if (this.k()) {
            this.i();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(Canvas canvas, ag bitmap) {
        if (!this.n) return;
        if (this.t() == null) return;
        if (this.A() == null) {
            return;
        }
        an an2 = this.q() ? new an(this.t, this.u) : this.z();
        bitmap = this.p();
        if (bitmap == null) return;
        if (bitmap.size() > 1) {
            bitmap = ((BitmapDescriptor)bitmap.get(this.b)).getBitmap();
        } else {
            if (bitmap.size() != 1) return;
            bitmap = ((BitmapDescriptor)bitmap.get(0)).getBitmap();
        }
        if (bitmap == null) return;
        if (bitmap.isRecycled()) return;
        canvas.save();
        canvas.rotate(this.c, (float)an2.a, (float)an2.b);
        canvas.drawBitmap(bitmap, (float)an2.a - this.B() * (float)bitmap.getWidth(), (float)an2.b - this.C() * (float)bitmap.getHeight(), null);
        canvas.restore();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) return;
        if (this.d == null) {
            return;
        }
        this.d.clear();
        this.d.add((Object)bitmapDescriptor);
        if (!this.k()) return;
        this.o.e(this);
        this.o.d(this);
    }

    @Override
    public void a(LatLng latLng) {
        if (this.q) {
            this.h = latLng;
            return;
        }
        this.g = latLng;
    }

    @Override
    public void a(Object object) {
        this.p = object;
    }

    @Override
    public void a(String string2) {
        this.i = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void a(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList == null) {
            return;
        }
        this.b(arrayList);
        if (this.r == null) {
            this.r = new a();
            this.r.start();
        }
        if (!this.k()) return;
        this.o.e(this);
        this.o.d(this);
    }

    @Override
    public void a(boolean bl2) {
        this.m = bl2;
    }

    @Override
    public boolean a() {
        return this.o.b(this);
    }

    @Override
    public boolean a(ak ak2) {
        if (this.equals((Object)ak2) || ak2.d().equals((Object)this.d())) {
            return true;
        }
        return false;
    }

    @Override
    public Rect b() {
        an an2 = this.z();
        if (an2 == null) {
            return new Rect(0, 0, 0, 0);
        }
        int n2 = this.n();
        int n3 = this.y();
        Rect rect = new Rect();
        if (this.c == 0.0f) {
            rect.top = (int)((float)an2.b - (float)n3 * this.l);
            rect.left = (int)((float)an2.a - this.k * (float)n2);
            float f2 = an2.b;
            rect.bottom = (int)((float)n3 * (1.0f - this.l) + f2);
            f2 = an2.a;
            float f3 = this.k;
            rect.right = (int)(f2 + (float)n2 * (1.0f - f3));
            return rect;
        }
        an an3 = this.b((- this.k) * (float)n2, (this.l - 1.0f) * (float)n3);
        an an4 = this.b((- this.k) * (float)n2, this.l * (float)n3);
        an an5 = this.b((1.0f - this.k) * (float)n2, this.l * (float)n3);
        float f4 = this.k;
        float f5 = n2;
        float f6 = this.l;
        an an6 = this.b(f5 * (1.0f - f4), (float)n3 * (f6 - 1.0f));
        rect.top = an2.b - Math.max((int)an3.b, (int)Math.max((int)an4.b, (int)Math.max((int)an5.b, (int)an6.b)));
        rect.left = an2.a + Math.min((int)an3.a, (int)Math.min((int)an4.a, (int)Math.min((int)an5.a, (int)an6.a)));
        rect.bottom = an2.b - Math.min((int)an3.b, (int)Math.min((int)an4.b, (int)Math.min((int)an5.b, (int)an6.b)));
        rect.right = an2.a + Math.max((int)an3.a, (int)Math.max((int)an4.a, (int)Math.max((int)an5.a, (int)an6.a)));
        return rect;
    }

    @Override
    public void b(float f2) {
        this.v = f2;
        this.o.d();
    }

    @Override
    public void b(int n2) {
        this.w = n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void b(LatLng latLng) {
        if (this.q) {
            try {
                double[] arrd = fd.a(latLng.longitude, latLng.latitude);
                this.h = new LatLng(arrd[1], arrd[0]);
            }
            catch (Exception var2_3) {
                cy.a(var2_3, "MarkerDelegateImp", "setPosition");
                this.h = latLng;
            }
        }
        this.s = false;
        this.g = latLng;
    }

    @Override
    public void b(String string2) {
        this.j = string2;
    }

    public void b(ArrayList<BitmapDescriptor> arrayList) {
        this.w();
        if (arrayList != null) {
            for (BitmapDescriptor bitmapDescriptor : arrayList) {
                if (bitmapDescriptor == null) continue;
                this.d.add((Object)bitmapDescriptor.clone());
            }
            if (arrayList.size() > 1 && this.r == null) {
                this.r = new a();
                this.r.start();
            }
        }
    }

    @Override
    public void b(boolean bl2) {
        this.n = bl2;
        if (!bl2 && this.k()) {
            this.o.e(this);
        }
    }

    @Override
    public LatLng c() {
        if (this.s) {
            aa aa2 = new aa();
            this.o.a.a(this.t, this.u, aa2);
            return new LatLng(aa2.b, aa2.a);
        }
        if (this.q) {
            return this.h;
        }
        return this.g;
    }

    @Override
    public String d() {
        if (this.f == null) {
            this.f = bk.c("Marker");
        }
        return this.f;
    }

    @Override
    public ab e() {
        ab ab2 = new ab();
        if (this.d != null && this.d.size() != 0) {
            ab2.a = (float)this.n() * this.k;
            ab2.b = (float)this.y() * this.l;
        }
        return ab2;
    }

    @Override
    public String f() {
        return this.i;
    }

    @Override
    public String g() {
        return this.j;
    }

    @Override
    public boolean h() {
        return this.m;
    }

    @Override
    public void i() {
        if (!this.s()) {
            return;
        }
        this.o.d(this);
    }

    @Override
    public void j() {
        if (this.k()) {
            this.o.e(this);
        }
    }

    @Override
    public boolean k() {
        return this.o.f(this);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void l() {
        try {
            Iterator iterator = this.d.iterator();
            while (iterator.hasNext()) {
                Bitmap bitmap = ((BitmapDescriptor)iterator.next()).getBitmap();
                if (bitmap == null) continue;
                bitmap.recycle();
            }
            this.d = null;
            this.g = null;
            this.p = null;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "MarkerDelegateImp", "destroy");
            Log.d((String)"destroy erro", (String)"MarkerDelegateImp destroy");
        }
        this.r = null;
    }

    @Override
    public int m() {
        return super.hashCode();
    }

    @Override
    public int n() {
        return this.A().getWidth();
    }

    @Override
    public int o() {
        return this.e;
    }

    @Override
    public ArrayList<BitmapDescriptor> p() {
        if (this.d != null && this.d.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator iterator = this.d.iterator();
            while (iterator.hasNext()) {
                arrayList.add((Object)((BitmapDescriptor)iterator.next()));
            }
            return arrayList;
        }
        return null;
    }

    @Override
    public boolean q() {
        return this.s;
    }

    @Override
    public float r() {
        return this.v;
    }

    @Override
    public boolean s() {
        return this.n;
    }

    @Override
    public LatLng t() {
        if (this.s) {
            aa aa2 = new aa();
            this.o.a.a(this.t, this.u, aa2);
            return new LatLng(aa2.b, aa2.a);
        }
        return this.g;
    }

    @Override
    public Object u() {
        return this.p;
    }

    @Override
    public int v() {
        return this.w;
    }

    void w() {
        if (this.d == null) {
            this.d = new CopyOnWriteArrayList();
            return;
        }
        this.d.clear();
    }

    /*
     * Enabled aggressive block sorting
     */
    public an x() {
        if (this.t() == null) {
            return null;
        }
        an an2 = new an();
        ae ae2 = this.q ? new ae((int)(this.c().latitude * 1000000.0), (int)(this.c().longitude * 1000000.0)) : new ae((int)(this.t().latitude * 1000000.0), (int)(this.t().longitude * 1000000.0));
        Point point = new Point();
        this.o.a().s().a(ae2, point);
        an2.a = point.x;
        an2.b = point.y;
        return an2;
    }

    public int y() {
        return this.A().getHeight();
    }

    public an z() {
        an an2;
        an an3 = an2 = this.x();
        if (an2 == null) {
            an3 = null;
        }
        return an3;
    }

    class a
    extends Thread {
        private a() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void run() {
            this.setName("MarkerThread");
            while (!Thread.currentThread().isInterrupted() && bk.this.d != null && bk.this.d.size() > 1) {
                if (bk.this.b == bk.this.d.size() - 1) {
                    bk.this.b = 0;
                } else {
                    bk.c(bk.this);
                }
                bk.this.o.a().postInvalidate();
                try {
                    Thread.sleep((long)(bk.this.e * 250));
                }
                catch (InterruptedException var1_1) {
                    cy.a(var1_1, "MarkerDelegateImp", "run");
                }
                if (bk.this.d != null) continue;
                Thread.currentThread().interrupt();
            }
        }
    }

}

