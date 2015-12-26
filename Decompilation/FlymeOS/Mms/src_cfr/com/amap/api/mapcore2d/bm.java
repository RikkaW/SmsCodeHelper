/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Matrix
 *  android.graphics.PointF
 *  android.util.FloatMath
 *  android.view.MotionEvent
 *  java.lang.Class
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import com.amap.api.mapcore2d.cy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class bm {
    static float j = 1.0f;
    private static Method p;
    private static Method q;
    private static boolean r;
    private static boolean s;
    b a;
    int b = 0;
    Matrix c = new Matrix();
    Matrix d = new Matrix();
    PointF e = new PointF();
    PointF f = new PointF();
    PointF g = new PointF();
    float h = 1.0f;
    float i = 1.0f;
    boolean k = false;
    boolean l = false;
    boolean m = false;
    public int n = 0;
    public long o = 0;

    static {
        r = false;
        s = false;
    }

    bm() {
    }

    public static a a(Context object, b b2) {
        object = new a();
        object.a = b2;
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(MotionEvent motionEvent) {
        if (s) {
            return;
        }
        s = true;
        try {
            p = motionEvent.getClass().getMethod("getX", new Class[]{Integer.TYPE});
            q = motionEvent.getClass().getMethod("getY", new Class[]{Integer.TYPE});
            if (p == null) return;
            if (q == null) return;
            r = true;
            return;
        }
        catch (Exception var0_1) {
            cy.a(var0_1, "MutiTouchGestureDetector", "checkSDKForMuti");
            return;
        }
    }

    public static class a
    extends bm {
        float p;
        float q;
        float r;
        float s;
        long t = 0;
        int u = 0;
        int v = 0;

        protected a() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void a(PointF pointF, MotionEvent motionEvent) {
            float f2;
            float f3;
            float f4;
            float f5 = 0.0f;
            try {
                f4 = ((Float)p.invoke((Object)motionEvent, new Object[]{0})).floatValue();
                f2 = ((Float)p.invoke((Object)motionEvent, new Object[]{1})).floatValue();
                f4 = f2 + f4;
            }
            catch (IllegalArgumentException var7_10) {
                cy.a(var7_10, "MutiTouchGestureDetector", "midPoint");
                f4 = 0.0f;
            }
            catch (IllegalAccessException var7_11) {
                cy.a(var7_11, "MutiTouchGestureDetector", "midPoint");
                f4 = 0.0f;
            }
            catch (InvocationTargetException var7_12) {
                cy.a(var7_12, "MutiTouchGestureDetector", "midPoint");
                f4 = 0.0f;
            }
            try {
                f2 = ((Float)q.invoke((Object)motionEvent, new Object[]{0})).floatValue();
                f3 = ((Float)q.invoke((Object)motionEvent, new Object[]{1})).floatValue();
                f5 = f2 + f3;
            }
            catch (IllegalArgumentException var2_3) {
                cy.a(var2_3, "MutiTouchGestureDetector", "midPoint");
            }
            catch (IllegalAccessException var2_4) {
                cy.a(var2_4, "MutiTouchGestureDetector", "midPoint");
            }
            catch (InvocationTargetException var2_5) {
                cy.a(var2_5, "MutiTouchGestureDetector", "midPoint");
            }
            f3 = f4;
            f2 = f5;
            if (this.u != 0) {
                f3 = f4;
                f2 = f5;
                if (this.v != 0) {
                    f3 = this.u;
                    f2 = this.v;
                }
            }
            pointF.set(f3 / 2.0f, f2 / 2.0f);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private float b(MotionEvent motionEvent) {
            float f2;
            float f3;
            float f4 = 0.0f;
            try {
                f3 = ((Float)p.invoke((Object)motionEvent, new Object[]{0})).floatValue();
                f2 = ((Float)p.invoke((Object)motionEvent, new Object[]{1})).floatValue();
                f3 -= f2;
            }
            catch (IllegalArgumentException var6_9) {
                cy.a(var6_9, "MutiTouchGestureDetector", "distance");
                f3 = 0.0f;
            }
            catch (IllegalAccessException var6_10) {
                cy.a(var6_10, "MutiTouchGestureDetector", "distance");
                f3 = 0.0f;
            }
            catch (InvocationTargetException var6_11) {
                cy.a(var6_11, "MutiTouchGestureDetector", "distance");
                f3 = 0.0f;
            }
            try {
                f2 = ((Float)q.invoke((Object)motionEvent, new Object[]{0})).floatValue();
                float f5 = ((Float)q.invoke((Object)motionEvent, new Object[]{1})).floatValue();
                f4 = f2 - f5;
            }
            catch (IllegalArgumentException var1_2) {
                cy.a(var1_2, "MutiTouchGestureDetector", "distance");
                return FloatMath.sqrt((float)(f3 * f3 + f4 * f4));
            }
            catch (IllegalAccessException var1_3) {
                cy.a(var1_3, "MutiTouchGestureDetector", "distance");
                return FloatMath.sqrt((float)(f3 * f3 + f4 * f4));
            }
            catch (InvocationTargetException var1_4) {
                cy.a(var1_4, "MutiTouchGestureDetector", "distance");
                return FloatMath.sqrt((float)(f3 * f3 + f4 * f4));
            }
            return FloatMath.sqrt((float)(f3 * f3 + f4 * f4));
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean a(MotionEvent motionEvent, int n2, int n3) {
            boolean bl2 = true;
            this.u = n2;
            this.v = n3;
            bm.b(motionEvent);
            if (!r) {
                return false;
            }
            switch (motionEvent.getAction() & 255) {
                case 0: {
                    this.t = motionEvent.getEventTime();
                    this.p = motionEvent.getX();
                    this.q = motionEvent.getY();
                    this.d.set(this.c);
                    this.e.set(this.p, this.q);
                    this.b = 1;
                    return false;
                }
                case 5: {
                    ++this.n;
                    if (this.n != 1) return false;
                    this.m = true;
                    j = 1.0f;
                    this.h = this.b(motionEvent);
                    if (this.h <= 10.0f) return false;
                    this.c.reset();
                    this.d.reset();
                    this.d.set(this.c);
                    this.a(this.f, motionEvent);
                    this.b = 2;
                    this.k = true;
                    bl2 = this.a.a(this.e) | false;
                    this.r = this.f.x;
                    this.s = this.f.y;
                    return bl2;
                }
                case 1: {
                    this.o = motionEvent.getEventTime();
                    this.k = false;
                    this.b = 0;
                    return false;
                }
                case 6: {
                    --this.n;
                    if (this.n == 1) {
                        this.m = true;
                        this.b = 2;
                    }
                    if (this.n != 0) return false;
                    this.a(this.f, motionEvent);
                    this.l = false;
                    this.m = false;
                    if (!this.k) return false;
                    bl2 = this.a.a(this.i, this.f) | false;
                    this.b = 0;
                    return bl2;
                }
                default: {
                    return false;
                }
                case 2: 
            }
            if (this.b == 1) {
                float f2 = motionEvent.getX();
                float f3 = motionEvent.getY();
                this.c.set(this.d);
                this.c.postTranslate(motionEvent.getX() - this.e.x, motionEvent.getY() - this.e.y);
                boolean bl3 = this.a.a(f2 - this.p, f3 - this.q);
                this.p = f2;
                this.q = f3;
                bl3 = this.a.a(this.c) | (false | bl3);
                if (motionEvent.getEventTime() - this.t < 30) return bl2;
                return bl3;
            }
            if (this.b != 2) return false;
            float f4 = this.b(motionEvent);
            this.i = 0.0f;
            if (f4 <= 10.0f) return false;
            if (Math.abs((float)(f4 - this.h)) <= 5.0f) return false;
            this.c.set(this.d);
            float f5 = f4 > this.h ? f4 / this.h : this.h / f4;
            this.i = f5;
            j = f4 / this.h;
            if (f4 < this.h) {
                this.i = - this.i;
            }
            this.a(this.g, motionEvent);
            bl2 = this.a.a(this.g.x - this.r, this.g.y - this.s);
            this.r = this.g.x;
            this.s = this.g.y;
            this.c.postScale(f4 / this.h, f4 / this.h, this.f.x, this.f.y);
            bl2 = bl2 | false | this.a.c(this.i) | this.a.b(this.c);
            this.l = true;
            return bl2;
        }
    }

    public static interface b {
        public boolean a(float var1, float var2);

        public boolean a(float var1, PointF var2);

        public boolean a(Matrix var1);

        public boolean a(PointF var1);

        public boolean b(Matrix var1);

        public boolean c(float var1);
    }

}

