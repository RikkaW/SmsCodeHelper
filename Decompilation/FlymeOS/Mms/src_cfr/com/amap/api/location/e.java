/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  android.location.LocationListener
 *  android.os.Bundle
 *  android.os.SystemClock
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.SystemClock;
import com.amap.api.location.a;
import com.amap.api.location.d;

class e
implements LocationListener {
    final /* synthetic */ d a;

    e(d d2) {
        this.a = d2;
    }

    public void onLocationChanged(Location location) {
        d.a(this.a).b(true);
        d.a((d)this.a).e = SystemClock.elapsedRealtime();
    }

    public void onProviderDisabled(String string2) {
        if ("gps".equals((Object)string2)) {
            d.a(this.a).b(false);
        }
    }

    public void onProviderEnabled(String string2) {
    }

    public void onStatusChanged(String string2, int n2, Bundle bundle) {
        if (n2 == 0 || n2 == 1) {
            d.a(this.a).b(false);
        }
    }
}

