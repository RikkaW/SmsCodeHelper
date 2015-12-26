/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Point
 *  android.graphics.PointF
 *  android.os.Handler
 *  android.os.Message
 *  android.os.RemoteException
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnKeyListener
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.Throwable
 *  java.util.LinkedList
 */
package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.bh;
import com.amap.api.mapcore2d.bl;
import com.amap.api.mapcore2d.br;
import com.amap.api.mapcore2d.cg;
import com.amap.api.mapcore2d.ch;
import com.amap.api.mapcore2d.co;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.t;
import com.amap.api.mapcore2d.y;
import java.util.LinkedList;

final class bb
implements View.OnKeyListener {
    private int a = 0;
    private int b = 0;
    private bl c;
    private boolean d;
    private b e;
    private a f;

    bb(bl bl2) {
        this.c = bl2;
        this.d = false;
        this.e = new b();
        this.f = new a();
    }

    private int a(float f2) {
        int n2 = 1;
        int n3 = 0;
        int n4 = 1;
        while ((float)n4 <= f2) {
            n4 *= 2;
            n3 = n2++;
        }
        return n3;
    }

    static /* synthetic */ bl a(bb bb2) {
        return bb2.c;
    }

    private boolean a(int n2, int n3, boolean bl2, boolean bl3) {
        return this.a(n2, n3, bl2, bl3, 1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean a(int n2, int n3, boolean bl2, boolean bl3, int n4) {
        this.c.b.g().K();
        boolean bl4 = false;
        n4 = bl2 ? this.c.b.e() + n4 : this.c.b.e() - n4;
        n4 = this.c.b.g().a(n4);
        if (n4 != this.c.b.e()) {
            this.a(n2, n3, n4, bl2, bl3);
            bl4 = true;
        }
        try {
            if (this.c.g.q().a()) {
                this.c.g.L();
            }
            return bl4;
        }
        catch (RemoteException var7_7) {
            cy.a((Throwable)var7_7, "MapController", "zoomWithAnimation");
            return bl4;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean b(ae ae2) {
        ae ae3;
        if (ae2 == null || this.c == null || this.c.b == null || (ae3 = this.c.b.f()) == null || ae2.b() == ae3.b() && ae2.a() == ae3.a()) {
            return false;
        }
        return true;
    }

    private void c(ae ae2) {
        this.c.g.K();
        this.c.b.a(ae2);
    }

    private int f(int n2) {
        com.amap.api.mapcore2d.b b2 = this.c.b.g();
        b2.K();
        n2 = b2.a(n2);
        this.c.b.a(n2);
        try {
            if (this.c.g.q().a()) {
                this.c.g.L();
            }
            return n2;
        }
        catch (RemoteException var2_3) {
            cy.a((Throwable)var2_3, "MapController", "setZoom");
            return n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean g(int n2) {
        if (this.c == null || this.c.b == null || n2 == this.c.b.e()) {
            return false;
        }
        return true;
    }

    public int a() {
        return this.a;
    }

    public void a(int n2) {
        this.a = n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void a(int var1_1, int var2_2) {
        if (var1_1 <= 0) return;
        if (var2_2 <= 0) {
            return;
        }
        if (this.c == null) return;
        if (this.c.b == null) return;
        if (this.c.a == null) return;
        try {
            var7_3 = this.c.b.b();
            var6_4 = this.c.b.a();
            var5_5 = this.c.b.e();
            var8_6 = this.c.a.b();
            var9_7 = this.c.a.a();
            if (var8_6 == 0 && var9_7 == 0) {
                this.a = var1_1;
                this.b = var2_2;
                return;
            }
            ** GOTO lbl21
        }
        catch (Exception var10_8) {
            block11 : {
                block10 : {
                    var1_1 = 0;
                    ** GOTO lbl41
lbl21: // 1 sources:
                    var3_11 = (float)var1_1 / (float)var9_7;
                    var4_12 = (float)var2_2 / (float)var8_6;
                    var3_11 = Math.max((float)var3_11, (float)var4_12);
                    if (var3_11 <= 1.0f) break block10;
                    var2_2 = var5_5 - this.a(var3_11);
                    var1_1 = var7_3;
                    if (var2_2 > var7_3) {
                        var1_1 = var2_2;
                    }
                    break block11;
                }
                if ((double)var3_11 >= 0.5) ** GOTO lbl43
                try {
                    var1_1 = this.a(1.0f / var3_11);
                }
                catch (Exception var10_10) {
                    var1_1 = var5_5;
                }
                var1_1 = var2_2 = var1_1 + var5_5 - 1;
                if (var2_2 >= var6_4) {
                    var1_1 = var6_4;
                }
                ** GOTO lbl44
lbl41: // 2 sources:
                cy.a((Throwable)var10_9, "MapController", "zoomToSpan");
                ** GOTO lbl44
lbl43: // 1 sources:
                var1_1 = var5_5;
            }
            this.c(var1_1);
            return;
        }
    }

    public void a(int n2, int n3, int n4, boolean bl2, boolean bl3) {
        this.e.a(n2, n3, n4, bl2, bl3);
    }

    public void a(ae ae2) {
        if (this.b(ae2)) {
            this.c(ae2);
        }
    }

    public void a(ae ae2, int n2) {
        if (!this.b(ae2) && !this.g(n2)) {
            return;
        }
        this.c(ae2);
        this.f(n2);
        t.a().b();
    }

    public void a(boolean bl2) {
        this.e.a();
        this.f.a();
    }

    public int b() {
        return this.b;
    }

    public void b(int n2) {
        this.b = n2;
    }

    public void b(ae ae2, int n2) {
        this.f.a(ae2, null, null, n2);
    }

    public boolean b(int n2, int n3) {
        return this.a(n2, n3, true, true);
    }

    public int c(int n2) {
        if (!this.g(n2)) {
            return n2;
        }
        this.f(n2);
        t.a().b();
        return n2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void c(int n2, int n3) {
        if (this.d) {
            this.d = false;
            return;
        } else {
            if (n2 == 0 && n3 == 0) return;
            {
                if (y.p) {
                    PointF pointF = new PointF(0.0f, 0.0f);
                    PointF pointF2 = new PointF((float)n2, (float)n3);
                    this.c.h.a(pointF, pointF2, this.c.b.e());
                }
                this.c.b.a(false, false);
                return;
            }
        }
    }

    public boolean c() {
        return this.d(1);
    }

    public boolean d() {
        return this.e(1);
    }

    boolean d(int n2) {
        return this.a(this.c.b.c() / 2, this.c.b.d() / 2, true, false, n2);
    }

    public void e() {
        this.d = true;
    }

    boolean e(int n2) {
        return this.a(this.c.b.c() / 2, this.c.b.d() / 2, false, false, n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onKey(View view, int n2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        boolean bl2 = true;
        switch (n2) {
            default: {
                return false;
            }
            case 21: {
                this.c(-10, 0);
                return bl2;
            }
            case 22: {
                this.c(10, 0);
                return bl2;
            }
            case 19: {
                this.c(0, -10);
                return bl2;
            }
            case 20: 
        }
        this.c(0, 10);
        return bl2;
    }

    class a
    implements ch {
        private cg b;
        private Message c;
        private Runnable d;

        private a() {
            this.b = null;
            this.c = null;
            this.d = null;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private cg a(ae ae2, int n2) {
            int n3 = 500;
            if (n2 < 500) {
                do {
                    return new cg(n3, 10, bb.a((bb)bb.this).h.i, ae2, n2, this);
                    break;
                } while (true);
            }
            n3 = n2;
            return new cg(n3, 10, bb.a((bb)bb.this).h.i, ae2, n2, this);
        }

        private void c() {
            this.b = null;
            this.c = null;
            this.d = null;
        }

        public void a() {
            if (this.b != null) {
                this.b.d();
            }
        }

        @Override
        public void a(ae ae2) {
            if (ae2 == null) {
                return;
            }
            if (ae2.d() == Long.MIN_VALUE || ae2.c() == Long.MIN_VALUE) {
                ae2 = bb.a((bb)bb.this).h.b(ae2);
                bb.this.a(ae2);
                return;
            }
            bb.this.a(ae2);
        }

        public void a(ae ae2, Message message, Runnable runnable, int n2) {
            bb.a((bb)bb.this).c.a = true;
            bb.a((bb)bb.this).h.j = ae2.g();
            this.a();
            this.b = this.a(ae2, n2);
            this.c = message;
            this.d = runnable;
            this.b.c();
        }

        @Override
        public void b() {
            if (this.c != null) {
                this.c.getTarget().sendMessage(this.c);
            }
            if (this.d != null) {
                this.d.run();
            }
            this.c();
            if (bb.a((bb)bb.this).c != null) {
                bb.a((bb)bb.this).c.a = false;
            }
        }
    }

    class b
    implements Animation.AnimationListener {
        private LinkedList<Animation> b;
        private co c;

        private b() {
            this.b = new LinkedList();
            this.c = null;
        }

        private void a(int n2, int n3, int n4, boolean bl2) {
            if (this.c == null) {
                this.c = new co(bb.a((bb)bb.this).b.g(), this);
            }
            this.c.e = bl2;
            this.c.d = n2;
            this.c.a(n2, false, n3, n4);
        }

        private void b(int n2, int n3, int n4, boolean bl2) {
            if (this.c == null) {
                this.c = new co(bb.a((bb)bb.this).b.g(), this);
            }
            this.c.d = n2;
            this.c.e = bl2;
            if (this.c.e) {
                Point point = new Point(n3, n4);
                ae ae2 = bb.a((bb)bb.this).b.g().s().a(n3, n4);
                bb.a((bb)bb.this).h.i = bb.a((bb)bb.this).h.a(ae2);
                bb.a((bb)bb.this).h.a(point);
            }
            this.c.a(n2, true, n3, n4);
        }

        public void a() {
            this.b.clear();
        }

        public void a(int n2, int n3, int n4, boolean bl2, boolean bl3) {
            if (!bl2) {
                this.a(n4, n2, n3, bl3);
                return;
            }
            this.b(n4, n2, n3, bl3);
        }

        public void onAnimationEnd(Animation object) {
            object = bb.a((bb)bb.this).b.g();
            if (this.b.size() == 0) {
                bb.a((bb)bb.this).d.b();
                return;
            }
            object.startAnimation((Animation)this.b.remove());
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

}

