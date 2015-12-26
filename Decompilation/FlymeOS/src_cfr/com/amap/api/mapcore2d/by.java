/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetrics
 *  android.graphics.Point
 *  android.graphics.Typeface
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.as;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.be;
import com.amap.api.mapcore2d.br;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.TextOptions;

public class by
implements as {
    private b a;
    private be b;
    private String c;
    private int d;
    private int e;
    private LatLng f;
    private float g;
    private int h;
    private Typeface i;
    private boolean j;
    private float k;
    private int l;
    private int m;
    private Object n;
    private int o;

    public by(ag ag2, TextOptions textOptions, be be2) {
        this.b = be2;
        this.c = textOptions.getText();
        this.d = textOptions.getFontSize();
        this.e = textOptions.getFontColor();
        this.f = textOptions.getPosition();
        this.g = textOptions.getRotate();
        this.h = textOptions.getBackgroundColor();
        this.i = textOptions.getTypeface();
        this.j = textOptions.isVisible();
        this.k = textOptions.getZIndex();
        this.l = textOptions.getAlignX();
        this.m = textOptions.getAlignY();
        this.n = textOptions.getObject();
        this.a = (b)ag2;
    }

    @Override
    public String a() {
        return this.c;
    }

    @Override
    public void a(float f2) {
        this.g = f2;
        this.a.postInvalidate();
    }

    @Override
    public void a(int n2) {
        this.d = n2;
        this.a.postInvalidate();
    }

    @Override
    public void a(int n2, int n3) {
        this.l = n2;
        this.m = n3;
        this.a.postInvalidate();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(Canvas canvas) {
        int n2;
        int n3;
        if (TextUtils.isEmpty((CharSequence)this.c) || this.f == null) {
            return;
        }
        TextPaint textPaint = new TextPaint();
        if (this.i == null) {
            this.i = Typeface.DEFAULT;
        }
        textPaint.setTypeface(this.i);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize((float)this.d);
        float f2 = textPaint.measureText(this.c);
        float f3 = this.d;
        textPaint.setColor(this.h);
        ae ae2 = new ae((int)(this.f.latitude * 1000000.0), (int)(this.f.longitude * 1000000.0));
        Point point = new Point();
        this.a.s().a(ae2, point);
        canvas.save();
        canvas.rotate(- this.g % 360.0f, (float)point.x, (float)point.y);
        ae2 = textPaint.getFontMetrics();
        if (this.l < 1 || this.l > 3) {
            this.l = 3;
        }
        if (this.m < 4 || this.m > 6) {
            this.m = 6;
        }
        switch (this.l) {
            default: {
                n2 = 0;
                break;
            }
            case 1: {
                n2 = point.x;
                break;
            }
            case 2: {
                n2 = (int)((float)point.x - f2);
                break;
            }
            case 3: {
                n2 = (int)((float)point.x - f2 / 2.0f);
            }
        }
        switch (this.m) {
            default: {
                n3 = 0;
                break;
            }
            case 4: {
                n3 = point.y;
                break;
            }
            case 5: {
                n3 = (int)((float)point.y - f3);
                break;
            }
            case 6: {
                n3 = (int)((float)point.y - f3 / 2.0f);
            }
        }
        f3 = 2.0f + f3;
        canvas.drawRect((float)(n2 - 1), (float)(n3 - 1), (float)n2 + (f2 + 2.0f), (float)n3 + f3, (Paint)textPaint);
        textPaint.setColor(this.e);
        canvas.drawText(this.c, (float)n2, (float)n3 + f3 - ae2.bottom, (Paint)textPaint);
        canvas.restore();
    }

    @Override
    public void a(Typeface typeface) {
        this.i = typeface;
        this.a.postInvalidate();
    }

    @Override
    public void a(Object object) {
        this.n = object;
    }

    @Override
    public void a(String string2) {
        this.c = string2;
        this.a.postInvalidate();
    }

    @Override
    public int b() {
        return this.d;
    }

    @Override
    public void b(float f2) {
        this.k = f2;
        this.b.d();
    }

    @Override
    public void b(int n2) {
        this.o = n2;
    }

    @Override
    public void b(LatLng latLng) {
        this.f = latLng;
        this.a.postInvalidate();
    }

    @Override
    public int c() {
        return this.e;
    }

    @Override
    public void c(int n2) {
        this.e = n2;
        this.a.postInvalidate();
    }

    @Override
    public float d() {
        return this.g;
    }

    @Override
    public void d(int n2) {
        this.h = n2;
        this.a.postInvalidate();
    }

    @Override
    public int e() {
        return this.h;
    }

    @Override
    public Typeface f() {
        return this.i;
    }

    @Override
    public int g() {
        return this.l;
    }

    @Override
    public int h() {
        return this.m;
    }

    @Override
    public void i() {
        if (this.b != null) {
            this.b.b(this);
        }
    }

    @Override
    public float r() {
        return this.k;
    }

    @Override
    public boolean s() {
        return this.j;
    }

    @Override
    public LatLng t() {
        return this.f;
    }

    @Override
    public Object u() {
        return this.n;
    }

    @Override
    public int v() {
        return this.o;
    }
}

