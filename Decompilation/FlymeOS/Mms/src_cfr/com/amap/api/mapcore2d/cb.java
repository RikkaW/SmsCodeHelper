/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Point
 *  android.graphics.PointF
 *  java.lang.Float
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.at;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.cc;
import com.amap.api.mapcore2d.cq;
import com.amap.api.mapcore2d.cs;
import com.amap.api.mapcore2d.ct;
import com.amap.api.mapcore2d.cv;
import com.amap.api.mapcore2d.cw;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.model.TileOverlayOptions;
import com.amap.api.maps2d.model.TileProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class cb
implements at {
    private static int f = 0;
    private cc a;
    private TileProvider b;
    private Float c;
    private boolean d;
    private ag e;
    private int g = y.i;
    private int h = y.i;
    private ct i;
    private CopyOnWriteArrayList<a> j = new CopyOnWriteArrayList();
    private boolean k = false;
    private b l = null;
    private String m = null;

    cb(TileOverlayOptions object, cc object2) {
        this.a = object2;
        this.b = object.getTileProvider();
        this.g = this.b.getTileWidth();
        this.h = this.b.getTileHeight();
        this.c = Float.valueOf((float)object.getZIndex());
        this.d = object.isVisible();
        this.m = this.c();
        this.e = this.a.a();
        object2 = new cs.a(this.a.getContext(), this.m);
        object2.a(object.getMemoryCacheEnabled());
        object2.b(object.getDiskCacheEnabled());
        object2.a(object.getMemCacheSize());
        object2.b(object.getDiskCacheSize());
        object = object.getDiskCacheDir();
        if (object != null && !object.equals((Object)"")) {
            object2.a((String)object);
        }
        this.i = new ct(this.a.getContext(), this.g, this.h);
        this.i.a(this.b);
        this.i.a((cs.a)object2);
        this.b(true);
    }

    private static String a(String string2) {
        return string2 + ++f;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private ArrayList<a> a(int var1_1, int var2_2, int var3_3) {
        var18_4 = this.e.b();
        var19_5 = var18_4.i;
        var6_6 = var18_4.h[var18_4.g];
        var16_7 = (int)((var19_5.e() - var18_4.c) / ((double)y.i * var6_6));
        var8_8 = y.i * var16_7;
        var10_9 = var18_4.c;
        var4_10 = 0.0;
        if (var18_4.b == 0) {
            var13_11 = (int)((var18_4.d - var19_5.f()) / ((double)y.i * var6_6));
            var4_10 = var18_4.d - (double)(y.i * var13_11) * var6_6;
        } else if (var18_4.b == 1) {
            var13_11 = (int)((var19_5.f() - var18_4.d) / ((double)y.i * var6_6));
            var4_10 = (double)((var13_11 + 1) * y.i) * var6_6;
        } else {
            var13_11 = 0;
        }
        var19_5 = var18_4.a(new ae(var4_10, var10_9 + var8_8 * var6_6, false), var19_5, var18_4.k, var6_6);
        var21_12 = new a(this, var16_7, var13_11, var18_4.g, -1);
        a.a(var21_12, (PointF)var19_5);
        var20_13 = new ArrayList();
        var20_13.add((Object)var21_12);
        var14_14 = 1;
        block0 : do {
            var1_1 = 0;
            var15_16 = var16_7 - var14_14;
            do {
                if (var15_16 > var16_7 + var14_14) ** GOTO lbl44
                var17_17 = var13_11 + var14_14;
                var21_12 = var18_4.a(var15_16, var17_17, var16_7, var13_11, (PointF)var19_5, var2_2, var3_3);
                var12_15 = var1_1;
                if (var21_12 != null) {
                    if (var1_1 == 0) {
                        var1_1 = 1;
                    }
                    var22_18 = new a(this, var15_16, var17_17, var18_4.g, -1);
                    a.a(var22_18, (PointF)var21_12);
                    var20_13.add((Object)var22_18);
                    var12_15 = var1_1;
                }
                if ((var21_12 = var18_4.a(var15_16, var17_17 = var13_11 - var14_14, var16_7, var13_11, (PointF)var19_5, var2_2, var3_3)) == null) ** GOTO lbl69
                var1_1 = var12_15 == 0 ? 1 : var12_15;
                var22_18 = new a(this, var15_16, var17_17, var18_4.g, -1);
                a.a(var22_18, (PointF)var21_12);
                var20_13.add((Object)var22_18);
                ** GOTO lbl70
lbl44: // 2 sources:
                for (var15_16 = var13_11 + var14_14 - 1; var15_16 > var13_11 - var14_14; --var15_16) {
                    var12_15 = var16_7 + var14_14;
                    var21_12 = var18_4.a(var12_15, var15_16, var16_7, var13_11, (PointF)var19_5, var2_2, var3_3);
                    if (var21_12 != null) {
                        if (var1_1 == 0) {
                            var1_1 = 1;
                        }
                        var22_18 = new a(this, var12_15, var15_16, var18_4.g, -1);
                        a.a(var22_18, (PointF)var21_12);
                        var20_13.add((Object)var22_18);
                    }
                    var17_17 = var16_7 - var14_14;
                    var21_12 = var18_4.a(var17_17, var15_16, var16_7, var13_11, (PointF)var19_5, var2_2, var3_3);
                    var12_15 = var1_1;
                    if (var21_12 != null) {
                        var12_15 = var1_1;
                        if (var1_1 == 0) {
                            var12_15 = 1;
                        }
                        var22_18 = new a(this, var17_17, var15_16, var18_4.g, -1);
                        a.a(var22_18, (PointF)var21_12);
                        var20_13.add((Object)var22_18);
                    }
                    var1_1 = var12_15;
                }
                if (var1_1 == 0) {
                    return var20_13;
                }
                ++var14_14;
                continue block0;
lbl69: // 1 sources:
                var1_1 = var12_15;
lbl70: // 2 sources:
                ++var15_16;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(List<a> list, int n2, boolean bl2) {
        int n3;
        int n4 = 0;
        if (list == null) {
            return false;
        }
        if (this.j == null) {
            return false;
        }
        Object object = this.j.iterator();
        do {
            a a2;
            block10 : {
                if (object.hasNext()) {
                    a2 = (a)object.next();
                    Iterator<a> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        if (!a2.equals(iterator.next())) continue;
                        n3 = 1;
                        break block10;
                    }
                } else {
                    this.j.clear();
                    if (n2 > (int)this.e.h() || n2 < (int)this.e.i()) {
                        return false;
                    }
                    n3 = list.size();
                    n2 = n4;
                    if (n3 > 0) break;
                    return false;
                }
                n3 = 0;
            }
            if (n3 != 0) continue;
            a2.b();
        } while (true);
        while (n2 < n3) {
            object = list.get(n2);
            if (object != null) {
                this.j.add(object);
                this.i.a(bl2, (a)object);
            }
            ++n2;
        }
        return true;
    }

    @Override
    public void a() {
        if (this.l != null && this.l.a() == cq.d.b) {
            this.l.a(true);
        }
        Iterator iterator = this.j.iterator();
        while (iterator.hasNext()) {
            ((a)iterator.next()).b();
        }
        this.j.clear();
        this.i.g();
        this.a.b(this);
    }

    @Override
    public void a(float f2) {
        this.c = Float.valueOf((float)f2);
        this.a.c();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void a(Canvas canvas) {
        if (this.j != null && this.j.size() != 0) {
            for (a a2 : this.j) {
                if (a2 == null) continue;
                try {
                    PointF pointF = a2.g;
                    if (a2.h == null || a2.h.isRecycled() || pointF == null || a2 == null || pointF == null) continue;
                    canvas.drawBitmap(a2.h, pointF.x, pointF.y, null);
                }
                catch (Exception var3_4) {
                    cy.a(var3_4, "TileOverDelegateImp", "drawTiles");
                    cw.a("TileOverlayDelegateImp", var3_4.toString(), 112);
                }
            }
        }
    }

    @Override
    public void a(boolean bl2) {
        this.d = bl2;
        if (bl2) {
            this.b(true);
        }
    }

    @Override
    public boolean a(at at2) {
        if (this.equals((Object)at2) || at2.c().equals((Object)this.c())) {
            return true;
        }
        return false;
    }

    @Override
    public void b() {
        this.i.f();
    }

    @Override
    public void b(boolean bl2) {
        if (this.k) {
            return;
        }
        if (this.l != null && this.l.a() == cq.d.b) {
            this.l.a(true);
        }
        this.l = new b(bl2);
        this.l.c((Params[])new ag[]{this.e});
    }

    @Override
    public String c() {
        if (this.m == null) {
            this.m = cb.a("TileOverlay");
        }
        return this.m;
    }

    @Override
    public float d() {
        return this.c.floatValue();
    }

    @Override
    public boolean e() {
        return this.d;
    }

    @Override
    public int f() {
        return super.hashCode();
    }

    public class a
    implements Cloneable {
        public final int a;
        public final int b;
        public final int c;
        public cv.a d;
        final /* synthetic */ cb e;
        private final int f;
        private PointF g;
        private Bitmap h;
        private int i;

        private a(cb cb2, int n2, int n3, int n4, int n5) {
            this.e = cb2;
            this.h = null;
            this.d = null;
            this.i = 0;
            this.a = n2;
            this.b = n3;
            this.c = n4;
            this.f = n5;
        }

        private a(cb cb2, a a2) {
            this.e = cb2;
            this.h = null;
            this.d = null;
            this.i = 0;
            this.a = a2.a;
            this.b = a2.b;
            this.c = a2.c;
            this.f = a2.f;
            this.g = a2.g;
        }

        static /* synthetic */ PointF a(a a2, PointF pointF) {
            a2.g = pointF;
            return pointF;
        }

        public a a() {
            return new a(this.e, this);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public void a(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                try {
                    this.d = null;
                    int n2 = bitmap.getWidth();
                    int n3 = bitmap.getHeight();
                    this.h = cy.a(bitmap, cy.a(n2), cy.a(n3));
                }
                catch (Exception var1_2) {
                    cy.a(var1_2, "TileOverDelegateImp", "setBitmap");
                    if (this.i < 3) {
                        this.e.i.a(true, this);
                        ++this.i;
                        cw.a("TileOverlayDelegateImp", "setBitmap Exception: " + this + "retry: " + this.i, 111);
                    }
                }
            } else if (this.i < 3) {
                this.e.i.a(true, this);
                ++this.i;
                cw.a("TileOverlayDelegateImp", "setBitmap failed: " + this + "retry: " + this.i, 111);
            }
            if (this.e.e == null || this.e.e.a() == null) {
                return;
            }
            cb.b((cb)this.e).a().g.postInvalidate();
        }

        public void b() {
            cv.a(this);
            if (this.h != null && !this.h.isRecycled()) {
                this.h.recycle();
            }
            this.h = null;
            this.d = null;
        }

        public /* synthetic */ Object clone() {
            return this.a();
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof a)) {
                return false;
            }
            object = (a)object;
            if (this.a != object.a) return false;
            if (this.b != object.b) return false;
            if (this.c != object.c) return false;
            if (this.f == object.f) return true;
            return false;
        }

        public int hashCode() {
            return this.a * 7 + this.b * 11 + this.c * 13 + this.f;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("-");
            stringBuilder.append(this.b);
            stringBuilder.append("-");
            stringBuilder.append(this.c);
            stringBuilder.append("-");
            stringBuilder.append(this.f);
            return stringBuilder.toString();
        }
    }

    class b
    extends cq<ag, Void, List<a>> {
        private int c;
        private boolean d;

        public b(boolean bl2) {
            this.d = bl2;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        protected /* varargs */ List<a> a(ag ... arrag) {
            int n2;
            int n3 = 0;
            try {
                int n4 = arrag[0].c();
                n2 = arrag[0].d();
                this.c = (int)arrag[0].f();
                n3 = n4;
            }
            catch (Exception var1_2) {
                cy.a(var1_2, "TileOverDelegateImp", "doInBackground");
                n2 = 0;
            }
            if (n3 <= 0) return null;
            if (n2 <= 0) {
                return null;
            }
            return cb.this.a(this.c, n3, n2);
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        protected void a(List<a> list) {
            if (list == null || list.size() <= 0) {
                return;
            }
            cb.this.a(list, this.c, this.d);
            list.clear();
        }
    }

}

