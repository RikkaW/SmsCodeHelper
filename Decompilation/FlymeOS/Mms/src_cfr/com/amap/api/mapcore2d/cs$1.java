/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import com.amap.api.mapcore2d.cs;
import com.amap.api.mapcore2d.cx;
import com.amap.api.mapcore2d.cy;
import java.lang.ref.WeakReference;

class cs$1
extends cx<String, Bitmap> {
    final /* synthetic */ cs a;

    cs$1(cs cs2, int n2) {
        this.a = cs2;
        super(n2);
    }

    @Override
    protected int a(String string2, Bitmap bitmap) {
        int n2;
        int n3 = n2 = cs.a(bitmap) / 1024;
        if (n2 == 0) {
            n3 = 1;
        }
        return n3;
    }

    @Override
    protected void a(boolean bl2, String string2, Bitmap bitmap, Bitmap bitmap2) {
        if (cy.c() && cs.a(this.a) != null && bitmap != null && !bitmap.isRecycled()) {
            cs.a(this.a).put((Object)string2, new WeakReference<Bitmap>(bitmap));
        }
    }
}

