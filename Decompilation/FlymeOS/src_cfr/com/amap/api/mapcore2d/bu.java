/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.graphics.Point
 *  android.graphics.Rect
 *  android.os.RemoteException
 *  android.view.View
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.View;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.y;

class bu
extends View {
    private String a = "";
    private int b = 0;
    private b c;
    private Paint d;
    private Paint e;
    private Rect f;

    public bu(Context context, b b2) {
        super(context);
        this.c = b2;
        this.d = new Paint();
        this.f = new Rect();
        this.d.setAntiAlias(true);
        this.d.setColor(-16777216);
        this.d.setStrokeWidth(2.0f * y.a);
        this.d.setStyle(Paint.Style.STROKE);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(-16777216);
        this.e.setTextSize(20.0f * y.a);
    }

    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
    }

    public void a(int n2) {
        this.b = n2;
    }

    public void a(String string2) {
        this.a = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onDraw(Canvas canvas) {
        int n2;
        block6 : {
            try {
                boolean bl2 = this.c.q().a();
                if (bl2) break block6;
                return;
            }
            catch (RemoteException var6_3) {
                var6_3.printStackTrace();
            }
        }
        if (this.a.equals((Object)"") || this.b == 0) {
            return;
        }
        int n3 = n2 = this.b;
        try {
            if (n2 > this.c.getWidth() / 5) {
                n3 = this.c.getWidth() / 5;
            }
        }
        catch (Exception var6_5) {
            cy.a(var6_5, "ScaleView", "onDraw");
            n3 = n2;
        }
        Point point = this.c.A();
        this.e.getTextBounds(this.a, 0, this.a.length(), this.f);
        n2 = point.x + n3 > this.c.getWidth() - 10 ? this.c.getWidth() - 10 - (this.f.width() + n3) / 2 : point.x + (n3 - this.f.width()) / 2;
        int n4 = point.y - this.f.height() + 5;
        canvas.drawText(this.a, (float)n2, (float)n4, this.e);
        canvas.drawLine((float)n2, (float)(n4 - 2), (float)(n2 -= (n3 - this.f.width()) / 2), (float)((n4 += this.f.height() - 5) + 2), this.d);
        canvas.drawLine((float)n2, (float)n4, (float)(n2 + n3), (float)n4, this.d);
        canvas.drawLine((float)(n2 + n3), (float)(n4 - 2), (float)(n2 + n3), (float)(n4 + 2), this.d);
    }
}

