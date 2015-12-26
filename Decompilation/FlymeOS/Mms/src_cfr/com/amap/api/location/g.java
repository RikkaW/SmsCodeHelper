/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.GpsStatus
 *  android.location.GpsStatus$Listener
 *  android.location.LocationManager
 *  java.lang.Object
 */
package com.amap.api.location;

import android.location.GpsStatus;
import android.location.LocationManager;
import com.amap.api.location.d;

class g
implements GpsStatus.Listener {
    final /* synthetic */ d a;

    g(d d2) {
        this.a = d2;
    }

    public void onGpsStatusChanged(int n2) {
        GpsStatus gpsStatus = this.a.a.getGpsStatus(null);
        d.a(this.a, n2, gpsStatus);
    }
}

