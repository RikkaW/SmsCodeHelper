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
import com.amap.api.mapcore2d.ay$1;
import com.amap.api.mapcore2d.ay$2;
import com.amap.api.mapcore2d.bd;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.y;

class ay
extends LinearLayout {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private ImageView d;
    private ag e;
    private boolean f = false;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ay(Context context, bd bd2, ag ag2) {
        super(context);
        this.e = ag2;
        try {
            this.a = cy.a("location_selected2d.png");
            this.b = cy.a("location_pressed2d.png");
            this.a = cy.a(this.a, y.a);
            this.b = cy.a(this.b, y.a);
            this.c = cy.a("location_unselected2d.png");
            this.c = cy.a(this.c, y.a);
        }
        catch (Exception var2_3) {
            cy.a(var2_3, "LocationView", "LocationView");
        }
        this.d = new ImageView(context);
        this.d.setImageBitmap(this.a);
        this.d.setPadding(0, 20, 20, 0);
        this.d.setOnClickListener((View.OnClickListener)new ay$1(this));
        this.d.setOnTouchListener((View.OnTouchListener)new ay$2(this));
        this.addView((View)this.d);
    }

    static /* synthetic */ boolean a(ay ay2) {
        return ay2.f;
    }

    static /* synthetic */ Bitmap b(ay ay2) {
        return ay2.b;
    }

    static /* synthetic */ ImageView c(ay ay2) {
        return ay2.d;
    }

    static /* synthetic */ Bitmap d(ay ay2) {
        return ay2.a;
    }

    static /* synthetic */ ag e(ay ay2) {
        return ay2.e;
    }

    public void a() {
        try {
            this.a.recycle();
            this.b.recycle();
            this.c.recycle();
            this.a = null;
            this.b = null;
            this.c = null;
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "LocationView", "destory");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl2) {
        this.f = bl2;
        if (bl2) {
            this.d.setImageBitmap(this.a);
        } else {
            this.d.setImageBitmap(this.c);
        }
        this.d.invalidate();
    }
}

