/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.util.DisplayMetrics
 *  android.util.Log
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.mapcore2d.ag;
import com.amap.api.mapcore2d.aj;
import com.amap.api.mapcore2d.au;
import com.amap.api.mapcore2d.b;
import com.amap.api.mapcore2d.cw;
import com.amap.api.mapcore2d.u;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.AMapOptions;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;

public class bc
implements aj {
    public static volatile Context a;
    private ag b;
    private AMapOptions c;

    @Override
    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            if (a == null && layoutInflater != null) {
                a = layoutInflater.getContext().getApplicationContext();
            }
            if (a == null) {
                throw new NullPointerException("Context \u4e3a null \u8bf7\u5728\u5730\u56fe\u8c03\u7528\u4e4b\u524d \u4f7f\u7528 MapsInitializer.initialize(Context paramContext) \u6765\u8bbe\u7f6eContext");
            }
            this.g();
            this.b = new b(a);
        }
        if (this.c == null && bundle != null) {
            this.c = (AMapOptions)bundle.getParcelable("MapOptions");
        }
        this.b(this.c);
        cw.a("MapFragmentDelegateImp", "onCreateView", 113);
        return this.b.e();
    }

    @Override
    public ag a() {
        if (this.b == null) {
            if (a == null) {
                throw new NullPointerException("Context \u4e3a null \u8bf7\u5728\u5730\u56fe\u8c03\u7528\u4e4b\u524d \u4f7f\u7528 MapsInitializer.initialize(Context paramContext) \u6765\u8bbe\u7f6eContext");
            }
            this.g();
            this.b = new b(a);
        }
        return this.b;
    }

    @Override
    public void a(Activity activity, AMapOptions aMapOptions, Bundle bundle) {
        a = activity.getApplicationContext();
        this.c = aMapOptions;
    }

    @Override
    public void a(Context context) {
        if (context != null) {
            a = context.getApplicationContext();
        }
    }

    @Override
    public void a(Bundle bundle) {
        cw.a("MapFragmentDelegateImp", "onCreate", 113);
    }

    @Override
    public void a(AMapOptions aMapOptions) {
        this.c = aMapOptions;
    }

    @Override
    public void b() {
        if (this.b != null) {
            this.b.y();
        }
    }

    @Override
    public void b(Bundle bundle) {
        if (this.b != null) {
            if (this.c == null) {
                this.c = new AMapOptions();
            }
            this.c = this.c.camera(this.a().g());
            bundle.putParcelable("MapOptions", (Parcelable)this.c);
        }
    }

    void b(AMapOptions aMapOptions) {
        if (aMapOptions != null && this.b != null) {
            Object object = aMapOptions.getCamera();
            if (object != null) {
                this.b.a(u.a(object.target, object.zoom, object.bearing, object.tilt));
            }
            object = this.b.q();
            object.e(aMapOptions.getScrollGesturesEnabled());
            object.b(aMapOptions.getZoomControlsEnabled());
            object.f(aMapOptions.getZoomGesturesEnabled());
            object.c(aMapOptions.getCompassEnabled());
            object.a(aMapOptions.getScaleControlsEnabled());
            object.a(aMapOptions.getLogoPosition());
            this.b.b(aMapOptions.getMapType());
            this.b.a(aMapOptions.getZOrderOnTop());
        }
    }

    @Override
    public void c() {
        if (this.b != null) {
            this.b.z();
        }
    }

    @Override
    public void d() {
    }

    @Override
    public void e() {
        if (this.a() != null) {
            this.a().k();
            this.a().v();
        }
    }

    @Override
    public void f() {
        Log.d((String)"onLowMemory", (String)"onLowMemory run");
    }

    void g() {
        int n2;
        y.k = n2 = bc.a.getResources().getDisplayMetrics().densityDpi;
        if (n2 <= 120) {
            y.a = 0.5f;
            return;
        }
        if (n2 <= 160) {
            y.a = 0.6f;
            return;
        }
        if (n2 <= 240) {
            y.a = 0.87f;
            return;
        }
        if (n2 <= 320) {
            y.a = 1.0f;
            return;
        }
        if (n2 <= 480) {
            y.i = 512;
            y.a = 1.5f;
            return;
        }
        y.i = 512;
        y.a = 1.8f;
    }
}

