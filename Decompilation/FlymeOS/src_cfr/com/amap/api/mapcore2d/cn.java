/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.bb;
import com.amap.api.mapcore2d.cn$1;
import com.amap.api.mapcore2d.cn$2;
import com.amap.api.mapcore2d.cn$3;
import com.amap.api.mapcore2d.cn$4;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.y;

class cn
extends LinearLayout {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private ImageView g;
    private ImageView h;
    private bb i;
    private ag j;
    private int k = 0;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public cn(Context context, bb bb2, ag ag2) {
        super(context);
        this.setWillNotDraw(false);
        this.i = bb2;
        this.j = ag2;
        try {
            this.a = cy.a("zoomin_selected2d.png");
            this.a = cy.a(this.a, y.a);
            this.b = cy.a("zoomin_unselected2d.png");
            this.b = cy.a(this.b, y.a);
            this.c = cy.a("zoomout_selected2d.png");
            this.c = cy.a(this.c, y.a);
            this.d = cy.a("zoomout_unselected2d.png");
            this.d = cy.a(this.d, y.a);
            this.e = cy.a("zoomin_pressed2d.png");
            this.f = cy.a("zoomout_pressed2d.png");
            this.e = cy.a(this.e, y.a);
            this.f = cy.a(this.f, y.a);
        }
        catch (Exception var2_3) {
            cy.a(var2_3, "ZoomControllerView", "ZoomControllerView");
        }
        this.g = new ImageView(context);
        this.g.setImageBitmap(this.a);
        this.g.setOnClickListener((View.OnClickListener)new cn$1(this));
        this.h = new ImageView(context);
        this.h.setImageBitmap(this.c);
        this.h.setOnClickListener((View.OnClickListener)new cn$2(this));
        this.g.setOnTouchListener((View.OnTouchListener)new cn$3(this));
        this.h.setOnTouchListener((View.OnTouchListener)new cn$4(this));
        this.g.setPadding(0, 0, 20, -2);
        this.h.setPadding(0, 0, 20, 20);
        this.setOrientation(1);
        this.addView((View)this.g);
        this.addView((View)this.h);
    }

    static /* synthetic */ Bitmap a(cn cn2) {
        return cn2.c;
    }

    static /* synthetic */ ImageView b(cn cn2) {
        return cn2.h;
    }

    static /* synthetic */ ag c(cn cn2) {
        return cn2.j;
    }

    static /* synthetic */ Bitmap d(cn cn2) {
        return cn2.b;
    }

    static /* synthetic */ ImageView e(cn cn2) {
        return cn2.g;
    }

    static /* synthetic */ Bitmap f(cn cn2) {
        return cn2.a;
    }

    static /* synthetic */ bb g(cn cn2) {
        return cn2.i;
    }

    static /* synthetic */ Bitmap h(cn cn2) {
        return cn2.d;
    }

    static /* synthetic */ Bitmap i(cn cn2) {
        return cn2.e;
    }

    static /* synthetic */ Bitmap j(cn cn2) {
        return cn2.f;
    }

    public void a() {
        try {
            this.a.recycle();
            this.b.recycle();
            this.c.recycle();
            this.d.recycle();
            this.e.recycle();
            this.f.recycle();
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "ZoomControllerView", "destory");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(float f2) {
        if (f2 < this.j.h() && f2 > this.j.i()) {
            this.g.setImageBitmap(this.a);
            this.h.setImageBitmap(this.c);
            return;
        } else {
            if (f2 <= this.j.i()) {
                this.h.setImageBitmap(this.d);
                this.g.setImageBitmap(this.a);
                return;
            }
            if (f2 < this.j.h()) return;
            {
                this.g.setImageBitmap(this.b);
                this.h.setImageBitmap(this.c);
                return;
            }
        }
    }

    public void a(int n2) {
        this.k = n2;
        this.removeView((View)this.g);
        this.removeView((View)this.h);
        this.addView((View)this.g);
        this.addView((View)this.h);
    }

    public int b() {
        return this.k;
    }
}

