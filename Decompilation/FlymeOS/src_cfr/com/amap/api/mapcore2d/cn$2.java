/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.view.View
 *  android.view.View$OnClickListener
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.view.View;
import com.amap.api.mapcore2d.cn;

class cn$2
implements View.OnClickListener {
    final /* synthetic */ cn a;

    cn$2(cn cn2) {
        this.a = cn2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        cn.e(this.a).setImageBitmap(cn.f(this.a));
        this.a.a(cn.c(this.a).f() - 1.0f);
        if (cn.c(this.a).f() < (float)((int)cn.c(this.a).i() + 2)) {
            cn.b(this.a).setImageBitmap(cn.h(this.a));
        } else {
            cn.b(this.a).setImageBitmap(cn.a(this.a));
        }
        cn.g(this.a).d();
    }
}

