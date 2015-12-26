/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.PointF
 *  android.graphics.Rect
 *  android.graphics.RectF
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.amap.api.mapcore2d.ax;
import com.amap.api.mapcore2d.bw;
import com.amap.api.mapcore2d.ck;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.n;
import com.amap.api.mapcore2d.q;
import com.amap.api.mapcore2d.r;
import com.amap.api.mapcore2d.y;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

class aw
extends ax {
    public String a = "";
    public int b = 18;
    public int c = 3;
    public boolean d = true;
    public boolean e = true;
    public boolean f = false;
    public boolean g = false;
    public boolean h = false;
    public long i = 0;
    public ck j = null;
    public int k = -1;
    public String l = "";
    q m = null;
    r n = null;
    bw<n.a> o = null;
    private String p = "LayerPropertys";

    aw() {
    }

    protected void a() {
        this.n.a((q)null);
        this.m.c();
        this.o.clear();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void a(Canvas canvas) {
        try {
            if (this.o == null) {
                return;
            }
            Iterator<n.a> iterator = this.o.iterator();
            while (iterator.hasNext()) {
                Bitmap bitmap;
                n.a a2 = iterator.next();
                if (a2.g < 0) {
                    if (!this.e) continue;
                    bitmap = n.c();
                } else {
                    bitmap = this.m.a(a2.g);
                }
                a2 = a2.f;
                if (bitmap == null || a2 == null) continue;
                float f2 = a2.x;
                float f3 = y.i;
                float f4 = a2.y;
                float f5 = y.i;
                canvas.drawBitmap(bitmap, null, new RectF(a2.x, a2.y, f2 + f3, f4 + f5), null);
            }
            return;
        }
        catch (ConcurrentModificationException var1_2) {
            cy.a(var1_2, this.p, "drawLayer");
            return;
        }
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof aw)) {
            return false;
        }
        object = (aw)object;
        return this.a.equals((Object)object.a);
    }

    public int hashCode() {
        return this.k;
    }

    public String toString() {
        return this.a;
    }
}

