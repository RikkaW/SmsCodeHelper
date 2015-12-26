/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Rect
 *  android.os.Handler
 *  android.os.RemoteException
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.MotionEvent
 *  android.view.View
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Comparator
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.ak;
import com.amap.api.mapcore2d.al;
import com.amap.api.mapcore2d.an;
import com.amap.api.mapcore2d.as;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.bf;
import com.amap.api.mapcore2d.cy;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class be
extends View {
    b a;
    a b = new a();
    private ArrayList<as> c = new ArrayList(8);
    private ArrayList<ak> d = new ArrayList(8);
    private volatile int e = 0;
    private Handler f = new Handler();
    private Runnable g;
    private an h;
    private ak i;
    private ak j;
    private float k;
    private CopyOnWriteArrayList<Integer> l;

    public be(Context context, AttributeSet attributeSet, b b2) {
        super(context, attributeSet);
        this.g = new bf(this);
        this.j = null;
        this.k = 0.0f;
        this.l = new CopyOnWriteArrayList();
        this.a = b2;
    }

    private as a(Iterator<as> iterator, Rect rect, an an2) {
        while (iterator.hasNext()) {
            as as2 = iterator.next();
            LatLng latLng = as2.t();
            if (latLng == null) continue;
            this.a.b(latLng.latitude, latLng.longitude, an2);
            if (!this.a(rect, an2.a, an2.b)) continue;
            return as2;
        }
        return null;
    }

    static /* synthetic */ ArrayList a(be be2) {
        return be2.d;
    }

    private ak b(Iterator<ak> iterator, Rect rect, an an2) {
        while (iterator.hasNext()) {
            ak ak2 = iterator.next();
            LatLng latLng = ak2.c();
            if (latLng == null) continue;
            this.a.b(latLng.latitude, latLng.longitude, an2);
            if (!this.a(rect, an2.a, an2.b)) continue;
            return ak2;
        }
        return null;
    }

    static /* synthetic */ ArrayList b(be be2) {
        return be2.c;
    }

    private int h() {
        int n2 = this.e;
        this.e = n2 + 1;
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void i() {
        for (ak ak2 : this.d) {
            if (this.i == null || !this.i.d().equals((Object)ak2.d())) continue;
            try {
                boolean bl2 = this.i.q();
                if (bl2) {
                    return;
                }
            }
            catch (RemoteException var5_6) {
                cy.a((Throwable)var5_6, "MapOverlayImageView", "redrawInfoWindow");
            }
            Rect rect = ak2.b();
            int n2 = rect.left;
            this.h = new an(ak2.n() / 2 + n2, rect.top);
            this.a.u();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ak a(MotionEvent object) {
        synchronized (this) {
            int n2 = this.d.size() - 1;
            while (n2 >= 0) {
                block5 : {
                    ak ak2 = (ak)this.d.get(n2);
                    boolean bl2 = this.a(ak2.b(), (int)object.getX(), (int)object.getY());
                    if (!bl2) break block5;
                    return ak2;
                }
                --n2;
            }
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ak a(String object) {
        synchronized (this) {
            boolean bl2;
            ak ak2;
            Iterator iterator = this.d.iterator();
            do {
                if (!iterator.hasNext()) return null;
            } while ((ak2 = (ak)iterator.next()) == null || !(bl2 = ak2.d().equals(object)));
            return ak2;
        }
    }

    public b a() {
        return this.a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Canvas canvas) {
        synchronized (this) {
            this.i();
            Rect rect = new Rect(0, 0, this.a.c(), this.a.d());
            an an2 = new an();
            Iterator iterator = this.d.iterator();
            Iterator iterator2 = this.c.iterator();
            ak ak2 = this.b(iterator, rect, an2);
            as as2 = this.a(iterator2, rect, an2);
            while (ak2 != null || as2 != null) {
                if (ak2 == null) {
                    as2.a(canvas);
                    as2 = this.a(iterator2, rect, an2);
                    continue;
                }
                if (as2 == null) {
                    ak2.a(canvas, this.a);
                    ak2 = this.b(iterator, rect, an2);
                    continue;
                }
                if (ak2.r() < as2.r() || ak2.r() == as2.r() && ak2.v() < as2.v()) {
                    ak2.a(canvas, this.a);
                    ak2 = this.b(iterator, rect, an2);
                    continue;
                }
                as2.a(canvas);
                as2 = this.a(iterator2, rect, an2);
            }
            return;
        }
    }

    public void a(ak ak2) {
        synchronized (this) {
            this.e(ak2);
            ak2.b(this.h());
            this.d.remove((Object)ak2);
            this.d.add((Object)ak2);
            this.d();
            return;
        }
    }

    public void a(as as2) {
        synchronized (this) {
            this.c.remove((Object)as2);
            as2.b(this.h());
            this.c.add((Object)as2);
            this.d();
            return;
        }
    }

    public boolean a(Rect rect, int n2, int n3) {
        return rect.contains(n2, n3);
    }

    protected int b() {
        return this.d.size();
    }

    public void b(as as2) {
        synchronized (this) {
            this.c.remove((Object)as2);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean b(MotionEvent motionEvent) {
        synchronized (this) {
            int n2 = this.d.size() - 1;
            while (n2 >= 0) {
                block6 : {
                    ak ak2 = (ak)this.d.get(n2);
                    Rect rect = ak2.b();
                    boolean bl2 = this.a(rect, (int)motionEvent.getX(), (int)motionEvent.getY());
                    if (!bl2) break block6;
                    this.h = new an(rect.left + ak2.n() / 2, rect.top);
                    this.i = ak2;
                    return bl2;
                }
                --n2;
            }
            return false;
        }
    }

    public boolean b(ak ak2) {
        synchronized (this) {
            this.e(ak2);
            boolean bl2 = this.d.remove((Object)ak2);
            return bl2;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void c() {
        // MONITORENTER : this
        try {
            if (this.d != null) {
                var1_1 = this.d.iterator();
                while (var1_1.hasNext()) {
                    ((ak)var1_1.next()).l();
                }
                this.d.clear();
            }
            ** GOTO lbl14
        }
        catch (Throwable var1_2) {
            cy.a(var1_2, "MapOverlayImageView", "clear");
            return;
lbl14: // 1 sources:
            if (this.c != null) {
                this.c.clear();
                return;
            }
            // MONITOREXIT : this
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void c(ak ak2) {
        synchronized (this) {
            block7 : {
                ak ak3 = this.j;
                if (ak3 != ak2) break block7;
                do {
                    return;
                    break;
                } while (true);
            }
            if (this.j != null && this.j.r() == 2.14748365E9f) {
                this.j.b(this.k);
            }
            this.k = ak2.r();
            this.j = ak2;
            ak2.b(2.14748365E9f);
            this.d();
            return;
        }
    }

    void d() {
        this.f.removeCallbacks(this.g);
        this.f.postDelayed(this.g, 5);
    }

    public void d(ak ak2) {
        if (this.h == null) {
            this.h = new an();
        }
        Rect rect = ak2.b();
        this.h = new an(rect.left + ak2.n() / 2, rect.top);
        this.i = ak2;
        try {
            this.a.a(this.e());
            return;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "MapOverlayImageView", "showInfoWindow");
            return;
        }
    }

    public ak e() {
        return this.i;
    }

    public void e(ak ak2) {
        if (this.f(ak2)) {
            this.a.t();
        }
    }

    public void f() {
        try {
            if (this.f != null) {
                this.f.removeCallbacksAndMessages((Object)null);
            }
            this.c();
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "MapOverlayImageView", "destory");
            Log.d((String)"amapApi", (String)("MapOverlayImageView clear erro" + var1_1.getMessage()));
            return;
        }
    }

    public boolean f(ak ak2) {
        return this.a.b(ak2);
    }

    public List<Marker> g() {
        synchronized (this) {
            ak ak2;
            ArrayList arrayList = new ArrayList();
            Rect rect = new Rect(0, 0, this.a.c(), this.a.d());
            an an2 = new an();
            Iterator iterator = this.d.iterator();
            do {
                LatLng latLng;
                if (iterator.hasNext() && (latLng = (ak2 = (ak)iterator.next()).c()) != null) break block5;
                break;
            } while (true);
            {
                block5 : {
                    return arrayList;
                }
                this.a.b(latLng.latitude, latLng.longitude, an2);
                if (!this.a(rect, an2.a, an2.b)) continue;
                arrayList.add(new Marker(ak2));
                continue;
            }
        }
    }

    static class a
    implements Serializable,
    Comparator<al> {
        a() {
        }

        public int a(al al2, al al3) {
            if (al2 != null && al3 != null) {
                block5 : {
                    if (al2.r() <= al3.r()) break block5;
                    return 1;
                }
                try {
                    float f2 = al2.r();
                    float f3 = al3.r();
                    if (f2 < f3) {
                        return -1;
                    }
                }
                catch (Throwable var1_2) {
                    cy.a(var1_2, "MapOverlayImageView", "compare");
                }
            }
            return 0;
        }

        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((al)object, (al)object2);
        }
    }

}

