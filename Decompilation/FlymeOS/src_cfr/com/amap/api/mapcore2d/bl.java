/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Canvas
 *  android.graphics.Matrix
 *  android.graphics.Point
 *  android.graphics.PointF
 *  android.os.RemoteException
 *  android.util.DisplayMetrics
 *  android.util.SparseArray
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.WindowManager
 *  java.lang.Class
 *  java.lang.Float
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.amap.api.mapcore2d.ac;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.aw;
import com.amap.api.mapcore2d.be;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bi;
import com.amap.api.mapcore2d.bl$a$1;
import com.amap.api.mapcore2d.bm;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.bw;
import com.amap.api.mapcore2d.cc;
import com.amap.api.mapcore2d.cd;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.cl;
import com.amap.api.mapcore2d.cn;
import com.amap.api.mapcore2d.co;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.n;
import com.amap.api.mapcore2d.q;
import com.amap.api.mapcore2d.r;
import com.amap.api.mapcore2d.y;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class bl {
    public e a;
    public d b;
    public b c;
    public a d;
    public c e;
    public ac f;
    public com.amap.api.mapcore2d.b g;
    public bh h = null;

    public bl(Context context, com.amap.api.mapcore2d.b b2) {
        this.g = b2;
        this.b = new d(b2);
        this.h = new bh(this.b);
        this.h.a();
        this.a(context);
        this.e = new c(this, context);
        this.d = new a(context);
        this.a = new e();
        this.c = new b();
        this.f = new ac();
        this.b.a(false, false);
    }

    public void a() {
        this.d.a();
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Context context) {
        new DisplayMetrics();
        DisplayMetrics displayMetrics = context.getApplicationContext().getResources().getDisplayMetrics();
        try {
            context = displayMetrics.getClass().getField("densityDpi");
        }
        catch (SecurityException var1_2) {
            cy.a(var1_2, "Mediator", "initialize");
            context = null;
        }
        catch (NoSuchFieldException var1_3) {
            cy.a(var1_3, "Mediator", "initialize");
            context = null;
        }
        if (context != null) {
            int n2;
            long l2 = displayMetrics.widthPixels * displayMetrics.heightPixels;
            try {
                n2 = context.getInt((Object)displayMetrics);
            }
            catch (IllegalArgumentException var1_4) {
                cy.a(var1_4, "Mediator", "initialize");
                n2 = 160;
            }
            catch (IllegalAccessException var1_5) {
                cy.a(var1_5, "Mediator", "initialize");
                n2 = 160;
            }
            if (n2 <= 120) {
                y.l = 1;
                return;
            }
            if (n2 <= 160) {
                y.l = 3;
                return;
            }
            if (n2 <= 240) {
                y.l = 2;
                return;
            }
            if (l2 > 153600) {
                y.l = 2;
                return;
            }
            if (l2 < 153600) {
                y.l = 1;
                return;
            }
            y.l = 3;
            return;
        }
        long l3 = displayMetrics.widthPixels * displayMetrics.heightPixels;
        if (l3 > 153600) {
            y.l = 2;
            return;
        }
        if (l3 < 153600) {
            y.l = 1;
            return;
        }
        y.l = 3;
    }

    public void a(boolean bl2) {
        this.d.b(bl2);
    }

    public class a {
        public bw<aw> a;
        public boolean b;
        public boolean c;
        String d;
        String e;
        String f;
        String g;
        String h;
        private boolean j;
        private boolean k;
        private Context l;
        private int m;
        private int n;
        private boolean o;

        /*
         * Enabled aggressive block sorting
         */
        private a(Context context) {
            this.j = false;
            this.k = true;
            this.a = null;
            this.b = false;
            this.c = false;
            this.d = "zh_cn";
            this.m = 0;
            this.n = 0;
            this.f = "SatelliteMap3";
            this.g = "GridTmc3";
            this.h = "SateliteTmc3";
            this.o = false;
            if (context == null) {
                return;
            }
            this.l = context;
            bl.this = new DisplayMetrics();
            ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)bl.this);
            int n2 = bl.this.widthPixels / y.i + this.c();
            int n3 = bl.this.heightPixels / y.i + this.c();
            this.m = n2 + n2 * n3 + n3;
            this.n = this.m / 8 + 1;
            if (this.n == 0) {
                this.n = 1;
            } else if (this.n > 5) {
                this.n = 5;
            }
            this.a(context, "zh_cn");
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(Context context, String object) {
            if (this.a == null) {
                this.a = new bw();
            }
            if (y.g == null || y.g.equals((Object)"")) {
                if (object.equals((Object)"zh_cn")) {
                    this.e = "GridMapV3";
                } else if (object.equals((Object)"en")) {
                    this.e = "GridMapEnV3";
                }
            } else {
                this.e = y.g;
            }
            object = new aw();
            object.j = new bl$a$1(this);
            object.a = this.e;
            object.e = true;
            object.d = true;
            object.f = true;
            object.g = true;
            object.b = y.c;
            object.c = y.d;
            this.a((aw)object, context);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void a(Canvas canvas) {
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null && aw2.f) {
                    aw2.a(canvas);
                }
                ++n3;
            }
        }

        private void b(Canvas canvas) {
            if (this.k) {
                bl.this.f.a(canvas);
            }
        }

        private int c() {
            if (y.i == 512) {
                return 4;
            }
            return 3;
        }

        private void c(Canvas canvas) {
            bl.this.g.i.a(canvas);
        }

        /*
         * Enabled aggressive block sorting
         */
        private void c(String string2) {
            if (string2.equals((Object)"")) {
                return;
            }
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null && !aw2.a.equals((Object)string2) && aw2.e && aw2.f) {
                    aw2.f = false;
                }
                ++n3;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private void d() {
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null) {
                    aw2.k = n3;
                }
                ++n3;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private boolean d(String string2) {
            if (this.a == null) {
                return false;
            }
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null && aw2.a.equals((Object)string2)) {
                    return true;
                }
                ++n3;
            }
            return false;
        }

        public void a() {
            if (bl.this.d.a == null) {
                return;
            }
            for (aw aw2 : bl.this.d.a) {
                if (aw2 == null) continue;
                aw2.a();
            }
            bl.this.d.a.clear();
            bl.this.d.a = null;
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(Canvas canvas, Matrix matrix, float f2, float f3) {
            if (this.j) {
                canvas.save();
                canvas.translate(f2, f3);
                canvas.concat(matrix);
                this.a(canvas);
                if (bl.this.g.h.b()) {
                    this.b(canvas);
                }
                bl.this.g.h.a(canvas);
                canvas.restore();
                if (!bl.this.g.h.b()) {
                    this.b(canvas);
                }
                if (!this.b && !this.c) {
                    this.a(false);
                    bl.this.b.c.b(new Matrix());
                    bl.this.b.c.c(1.0f);
                    bl.this.b.c.I();
                }
            } else {
                this.a(canvas);
                this.b(canvas);
                bl.this.g.h.a(canvas);
            }
            this.c(canvas);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(String string2) {
            if (string2 == null || string2.equals((Object)"") || this.d.equals((Object)string2) || !string2.equals((Object)"zh_cn") && !string2.equals((Object)"en")) {
                return;
            }
            this.a();
            this.a(this.l, string2);
            this.d = string2;
        }

        public void a(boolean bl2) {
            this.j = bl2;
        }

        public boolean a(int n2, KeyEvent keyEvent) {
            return false;
        }

        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        boolean a(aw var1_1, Context var2_2) {
            if (var1_1 == null) {
                return false;
            }
            if (var1_1.a.equals((Object)"") == true) return false;
            if (this.d(var1_1.a) == true) return false;
            var1_1.o = new bw<T>();
            var1_1.m = new q(this.m, this.n, var1_1.h, var1_1.i);
            var1_1.n = new r((Context)var2_2, d.a((d)bl.this.b).c, var1_1);
            var1_1.n.a(var1_1.m);
            var3_3 = this.a.size();
            if (var1_1.e && var3_3 != 0) {
                --var3_3;
            } else {
                var4_4 = this.a.add(var1_1);
lbl14: // 3 sources:
                do {
                    this.d();
                    if (var1_1.f != true) return var4_4;
                    this.a(var1_1.a, true);
                    return var4_4;
                    break;
                } while (true);
            }
            while (var3_3 >= 0) {
                var2_2 = this.a.get(var3_3);
                if (var2_2 == null || !var2_2.e) ** GOTO lbl25
                this.a.add(var3_3, var1_1);
                var4_4 = false;
                ** GOTO lbl14
lbl25: // 1 sources:
                --var3_3;
            }
            var4_4 = false;
            ** while (true)
        }

        /*
         * Enabled aggressive block sorting
         */
        boolean a(String string2, boolean bl2) {
            if (string2.equals((Object)"")) {
                return false;
            }
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null && aw2.a.equals((Object)string2)) {
                    aw2.f = bl2;
                    if (!aw2.e) {
                        aw2.a();
                        return true;
                    }
                    if (bl2) {
                        if (aw2.b > aw2.c) {
                            bl.this.b.b(aw2.b);
                            bl.this.b.c(aw2.c);
                        }
                        this.c(string2);
                        bl.this.b.a(false, false);
                        return true;
                    }
                }
                ++n3;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         */
        aw b(String string2) {
            if (string2.equals((Object)"") || this.a == null || this.a.size() == 0) {
                return null;
            }
            int n2 = this.a.size();
            int n3 = 0;
            while (n3 < n2) {
                aw aw2 = this.a.get(n3);
                if (aw2 != null && aw2.a.equals((Object)string2)) {
                    return aw2;
                }
                ++n3;
            }
            return null;
        }

        public void b() {
            if (bl.this.b == null || bl.this.b.c == null) {
                return;
            }
            bl.this.b.c.postInvalidate();
        }

        public void b(boolean bl2) {
            this.k = bl2;
        }

        public boolean b(int n2, KeyEvent keyEvent) {
            return false;
        }

        protected boolean b(MotionEvent motionEvent) {
            return false;
        }
    }

    public class b {
        public boolean a;
        int b;

        public b() {
            this.a = false;
            this.b = 0;
            this.e();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a() {
            if (bl.this.d.o) {
                bl.this.d.b();
            }
            ++this.b;
            if (this.b < 20 || this.b % 20 != 0) {
                return;
            }
            int n2 = 0;
            while (n2 < bl.this.e.c.size()) {
                bi bi2 = (bi)bl.this.e.c.valueAt(n2);
                if (bi2 != null) {
                    bi2.h();
                }
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void b() {
            bl.this.b.a = false;
            int n2 = 0;
            while (n2 < bl.this.e.c.size()) {
                bi bi2 = (bi)bl.this.e.c.valueAt(n2);
                if (bi2 != null) {
                    bi2.a();
                }
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void c() {
            int n2 = 0;
            while (n2 < bl.this.e.c.size()) {
                bi bi2 = (bi)bl.this.e.c.valueAt(n2);
                if (bi2 != null) {
                    bi2.c();
                }
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void d() {
            int n2 = 0;
            while (n2 < bl.this.e.c.size()) {
                bi bi2 = (bi)bl.this.e.c.valueAt(n2);
                if (bi2 != null) {
                    bi2.b();
                }
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void e() {
            int n2 = 0;
            while (n2 < bl.this.e.c.size()) {
                bi bi2 = (bi)bl.this.e.c.valueAt(n2);
                if (bi2 != null) {
                    bi2.g();
                }
                ++n2;
            }
        }
    }

    public class c {
        private final Context b;
        private SparseArray<bi> c;

        private c(bl bl3, Context context) {
            this.c = new SparseArray();
            this.b = context;
            this.c.put(0, (Object)new cd(bl3, context));
        }
    }

    public class d {
        public boolean a;
        private com.amap.api.mapcore2d.b c;
        private ArrayList<cl> d;

        private d(com.amap.api.mapcore2d.b b2) {
            this.a = true;
            this.c = b2;
            this.d = new ArrayList();
        }

        public int a() {
            try {
                int n2 = bl.this.h.f;
                return n2;
            }
            catch (Throwable var2_2) {
                cy.a(var2_2, "Mediator", "getMaxZoomLevel");
                return 0;
            }
        }

        public void a(int n2) {
            if (n2 != bl.this.h.g) {
                bl.this.h.g = n2;
                bl.this.g.b[1] = n2;
                bl.this.g.e.a((float)n2);
            }
            this.a(false, false);
        }

        public void a(int n2, int n3) {
            if (n2 != y.m || n3 != y.n) {
                y.m = n2;
                y.n = n3;
                this.a(true, false);
            }
        }

        public void a(ae ae2) {
            if (ae2 == null) {
                return;
            }
            if (y.p) {
                bl.this.h.i = ae2 = bl.this.h.a(ae2);
            }
            this.a(false, false);
        }

        public void a(cl cl2) {
            this.d.add((Object)cl2);
        }

        public void a(boolean bl2, boolean bl3) {
            Iterator iterator = this.d.iterator();
            while (iterator.hasNext()) {
                ((cl)iterator.next()).a(bl2, bl3);
            }
            if (bl.this.g != null && bl.this.g.h != null) {
                bl.this.g.h.a(true);
                bl.this.g.postInvalidate();
            }
        }

        public int b() {
            try {
                int n2 = bl.this.h.e;
                return n2;
            }
            catch (Throwable var2_2) {
                cy.a(var2_2, "Mediator", "getMinZoomLevel");
                return 0;
            }
        }

        public void b(int n2) {
            if (n2 <= 0) {
                return;
            }
            try {
                bh bh2 = bl.this.h;
                y.c = n2;
                bh2.f = n2;
                return;
            }
            catch (Throwable var2_3) {
                cy.a(var2_3, "Mediator", "setMaxZoomLevel");
                return;
            }
        }

        public void b(ae ae2) {
            ae ae3 = bl.this.b.f();
            if (ae2 != null && !ae2.equals(ae3)) {
                if (y.p) {
                    bl.this.h.i = ae2 = bl.this.h.a(ae2);
                }
                this.a(false, true);
            }
        }

        public int c() {
            return y.m;
        }

        public void c(int n2) {
            if (n2 <= 0) {
                return;
            }
            try {
                bh bh2 = bl.this.h;
                y.d = n2;
                bh2.e = n2;
                return;
            }
            catch (Throwable var2_3) {
                cy.a(var2_3, "Mediator", "setMinZoomLevel");
                return;
            }
        }

        public int d() {
            return y.n;
        }

        public int e() {
            try {
                int n2 = bl.this.h.g;
                return n2;
            }
            catch (Throwable var2_2) {
                cy.a(var2_2, "Mediator", "getZoomLevel");
                return 0;
            }
        }

        public ae f() {
            ae ae2;
            ae ae3 = ae2 = bl.this.h.b(bl.this.h.i);
            if (bl.this.c != null) {
                ae3 = ae2;
                if (bl.this.c.a) {
                    ae3 = bl.this.h.j;
                }
            }
            return ae3;
        }

        public com.amap.api.mapcore2d.b g() {
            return this.c;
        }
    }

    public class e
    implements br {
        private int b;
        private HashMap<Float, Float> c;

        public e() {
            this.b = 0;
            this.c = new HashMap();
        }

        private int a(boolean bl2) {
            int n2 = bl.this.b.c();
            ae ae2 = this.a(0, bl.this.b.d());
            ae ae3 = this.a(n2, 0);
            if (bl2) {
                return Math.abs((int)(ae2.a() - ae3.a()));
            }
            return Math.abs((int)(ae2.b() - ae3.b()));
        }

        public float a(float f2) {
            int n2 = bl.this.b.e();
            if (this.c.size() > 30 || n2 != this.b) {
                this.b = n2;
                this.c.clear();
            }
            if (!this.c.containsKey((Object)Float.valueOf((float)f2))) {
                ae ae2;
                ae ae3 = this.a(0, 0);
                float f3 = bl.this.h.a(ae3, ae2 = this.a(0, 100));
                if (f3 <= 0.0f) {
                    return 0.0f;
                }
                f3 = f2 / f3;
                this.c.put((Object)Float.valueOf((float)f2), (Object)Float.valueOf((float)(100.0f * f3)));
            }
            return ((Float)this.c.get((Object)Float.valueOf((float)f2))).floatValue();
        }

        public int a() {
            return this.a(false);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Point a(ae ae2, Point point) {
            int n2;
            int n3 = bl.this.b.e();
            ae2 = bl.this.h.b(ae2, bl.this.h.i, bl.this.h.k, bl.this.h.h[n3]);
            bm bm2 = bl.this.b.c.G();
            Point point2 = d.a((d)bl.this.b).a().h.k;
            if (bm2.m) {
                boolean bl2 = true;
                try {
                    boolean bl3;
                    bl2 = bl3 = bl.this.g.g.f();
                }
                catch (RemoteException var12_6) {
                    var12_6.printStackTrace();
                }
                if (bm2.l && bl2) {
                    float f2 = bm.j * ((float)((int)ae2.x) - bm2.f.x) + bm2.f.x + (bm2.g.x - bm2.f.x);
                    float f3 = bm.j * ((float)((int)ae2.y) - bm2.f.y) + bm2.f.y + (bm2.g.y - bm2.f.y);
                    n3 = (int)f2;
                    int n4 = (int)f3;
                    int n5 = n3;
                    if ((double)f2 >= (double)n3 + 0.5) {
                        n5 = n3 + 1;
                    }
                    n3 = n4;
                    n2 = n5;
                    if ((double)f3 >= (double)n4 + 0.5) {
                        n3 = n4 + 1;
                        n2 = n5;
                    }
                } else {
                    n2 = (int)ae2.x;
                    n3 = (int)ae2.y;
                }
            } else {
                float f4 = co.c;
                float f5 = (int)ae2.x - point2.x;
                f4 = (float)point2.x + f4 * f5;
                f5 = co.c * (float)((int)ae2.y - point2.y) + (float)point2.y;
                n3 = (int)f4;
                int n6 = (int)f5;
                int n7 = n3;
                if ((double)f4 >= (double)n3 + 0.5) {
                    n7 = n3 + 1;
                }
                n3 = n6;
                n2 = n7;
                if ((double)f5 >= (double)n6 + 0.5) {
                    n3 = n6 + 1;
                    n2 = n7;
                }
            }
            ae2 = new Point(n2, n3);
            if (point != null) {
                point.x = ae2.x;
                point.y = ae2.y;
            }
            return ae2;
        }

        @Override
        public ae a(int n2, int n3) {
            int n4 = bl.this.b.e();
            PointF pointF = new PointF((float)n2, (float)n3);
            return bl.this.h.a(pointF, bl.this.h.i, bl.this.h.k, bl.this.h.h[n4], bl.this.h.l);
        }

        public int b() {
            return this.a(true);
        }
    }

}

