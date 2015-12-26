/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Point
 *  android.graphics.Rect
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.y;
import java.io.IOException;
import java.io.InputStream;

class cm
extends View {
    private Bitmap a;
    private Bitmap b;
    private Paint c = new Paint();
    private boolean d = false;
    private int e = 0;
    private b f;
    private int g = 0;
    private Rect h = new Rect();
    private int i = 10;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public cm(Context object, b b2) {
        super((Context)object);
        this.f = b2;
        b2 = object.getResources().getAssets();
        try {
            object = y.e == y.a.b ? b2.open("apl2d.data") : b2.open("ap2d.data");
            this.a = BitmapFactory.decodeStream((InputStream)object);
            this.a = cy.a(this.a, y.a);
            object.close();
            object = y.e == y.a.b ? b2.open("apl12d.data") : b2.open("ap12d.data");
            this.b = BitmapFactory.decodeStream((InputStream)object);
            this.b = cy.a(this.b, y.a);
            object.close();
            this.e = this.b.getHeight();
        }
        catch (IOException var1_2) {
            cy.a(var1_2, "WaterMarkerView", "WaterMarkerView");
        }
        this.c.setAntiAlias(true);
        this.c.setColor(-16777216);
        this.c.setStyle(Paint.Style.STROKE);
    }

    public void a() {
        try {
            if (this.a != null) {
                this.a.recycle();
            }
            if (this.b != null) {
                this.b.recycle();
            }
            this.a = null;
            this.b = null;
            this.c = null;
            return;
        }
        catch (Exception var1_1) {
            cy.a(var1_1, "WaterMarkerView", "destory");
            return;
        }
    }

    public void a(int n2) {
        this.g = n2;
    }

    public void a(boolean bl2) {
        this.d = bl2;
        this.invalidate();
    }

    public Bitmap b() {
        if (this.d) {
            return this.b;
        }
        return this.a;
    }

    public Point c() {
        return new Point(this.i, this.getHeight() - this.e - 10);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onDraw(Canvas canvas) {
        if (this.b == null) {
            return;
        }
        int n2 = this.b.getWidth() + 3;
        this.i = this.g == 1 ? (this.f.getWidth() - n2) / 2 : (this.g == 2 ? this.f.getWidth() - n2 - 10 : 10);
        if (y.e == y.a.b) {
            canvas.drawBitmap(this.b(), (float)(this.i + 15), (float)(this.getHeight() - this.e - 8), this.c);
            return;
        }
        canvas.drawBitmap(this.b(), (float)this.i, (float)(this.getHeight() - this.e - 8), this.c);
    }
}

