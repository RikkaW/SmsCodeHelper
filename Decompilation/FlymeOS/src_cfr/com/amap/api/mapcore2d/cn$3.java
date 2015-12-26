/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.os.RemoteException
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Object
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore2d.cn;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.u;

class cn$3
implements View.OnTouchListener {
    final /* synthetic */ cn a;

    cn$3(cn cn2) {
        this.a = cn2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (cn.c(this.a).f() >= cn.c(this.a).h()) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            cn.e(this.a).setImageBitmap(cn.i(this.a));
            return false;
        }
        if (motionEvent.getAction() != 1) return false;
        cn.e(this.a).setImageBitmap(cn.f(this.a));
        try {
            cn.c(this.a).b(u.b());
            return false;
        }
        catch (RemoteException var1_2) {
            cy.a((Throwable)var1_2, "ZoomControllerView", "ontouch");
            return false;
        }
    }
}

