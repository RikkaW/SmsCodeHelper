/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Thread
 */
package com.amap.api.location;

import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.a;
import com.amap.api.location.b;

class a$1
extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ AMapLocalWeatherListener b;
    final /* synthetic */ a c;

    a$1(a a2, int n2, AMapLocalWeatherListener aMapLocalWeatherListener) {
        this.c = a2;
        this.a = n2;
        this.b = aMapLocalWeatherListener;
    }

    public void run() {
        this.c.h.a(this.a, this.b, a.d(this.c));
    }
}

