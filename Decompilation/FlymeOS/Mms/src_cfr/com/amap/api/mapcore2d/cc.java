/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.view.View
 *  java.lang.Integer
 *  java.lang.Object
 *  java.util.Arrays
 *  java.util.Comparator
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.at;
import com.amap.api.mapcore2d.cy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

class cc
extends View {
    CopyOnWriteArrayList<Integer> a;
    private ag b;
    private CopyOnWriteArrayList<at> c = new CopyOnWriteArrayList();
    private a d;

    public cc(Context context, ag ag2) {
        super(context);
        this.d = new a();
        this.a = new CopyOnWriteArrayList();
        this.b = ag2;
    }

    ag a() {
        return this.b;
    }

    protected void a(Canvas canvas) {
        for (at at2 : this.c) {
            if (!at2.e()) continue;
            at2.a(canvas);
        }
    }

    public void a(at at2) {
        this.b(at2);
        this.c.add((Object)at2);
        this.c();
    }

    public void a(boolean bl2) {
        for (at at2 : this.c) {
            if (at2 == null || !at2.e()) continue;
            at2.b(bl2);
        }
    }

    protected boolean b() {
        if (this.c.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean b(at at2) {
        return this.c.remove((Object)at2);
    }

    void c() {
        Object[] arrobject = this.c.toArray();
        Arrays.sort((Object[])arrobject, (Comparator)this.d);
        this.c.clear();
        for (Object object : arrobject) {
            this.c.add((Object)((at)object));
        }
    }

    class a
    implements Comparator<Object> {
        private a() {
        }

        public int compare(Object object, Object object2) {
            object = (at)object;
            object2 = (at)object2;
            if (object != null && object2 != null) {
                block5 : {
                    if (object.d() <= object2.d()) break block5;
                    return 1;
                }
                try {
                    float f2 = object.d();
                    float f3 = object2.d();
                    if (f2 < f3) {
                        return -1;
                    }
                }
                catch (Exception var1_2) {
                    cy.a(var1_2, "TileOverlayView", "compare");
                }
            }
            return 0;
        }
    }

}

