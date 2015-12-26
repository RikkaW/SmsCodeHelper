/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.location.Location
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  java.lang.Object
 */
package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore2d.ay;
import com.amap.api.mapcore2d.cy;
import com.amap.api.mapcore2d.u;
import com.amap.api.maps2d.model.LatLng;

class ay$2
implements View.OnTouchListener {
    final /* synthetic */ ay a;

    ay$2(ay ay2) {
        this.a = ay2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean onTouch(View view, MotionEvent object) {
        if (!ay.a(this.a)) {
            return false;
        }
        if (object.getAction() == 0) {
            ay.c(this.a).setImageBitmap(ay.b(this.a));
            return false;
        }
        if (object.getAction() != 1) return false;
        try {
            ay.c(this.a).setImageBitmap(ay.d(this.a));
            ay.e(this.a).c(true);
            view = ay.e(this.a).p();
            if (view == null) return false;
            object = new LatLng(view.getLatitude(), view.getLongitude());
            ay.e(this.a).a((Location)view);
            ay.e(this.a).a(u.a((LatLng)object, ay.e(this.a).f()));
            return false;
        }
        catch (Exception var1_2) {
            cy.a(var1_2, "LocationView", "onTouch");
            return false;
        }
    }
}

