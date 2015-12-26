/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location;

import android.location.Location;
import android.os.Bundle;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;

public class h
implements AMapLocationListener {
    private LocationManagerProxy a;
    private AMapLocationListener b = null;

    public h(LocationManagerProxy locationManagerProxy) {
        this.a = locationManagerProxy;
    }

    public void a() {
        if (this.a != null) {
            this.a.removeUpdates(this);
        }
        this.b = null;
    }

    public boolean a(AMapLocationListener aMapLocationListener, long l2, float f2, String string2) {
        boolean bl2 = false;
        this.b = aMapLocationListener;
        if ("lbs".equals((Object)string2)) {
            this.a.requestLocationUpdates(string2, l2, f2, this);
            bl2 = true;
        }
        return bl2;
    }

    public void onLocationChanged(Location location) {
        if (this.b != null) {
            this.b.onLocationChanged(location);
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (this.b != null) {
            this.b.onLocationChanged(aMapLocation);
        }
    }

    public void onProviderDisabled(String string2) {
        if (this.b != null) {
            this.b.onProviderDisabled(string2);
        }
    }

    public void onProviderEnabled(String string2) {
        if (this.b != null) {
            this.b.onProviderEnabled(string2);
        }
    }

    public void onStatusChanged(String string2, int n2, Bundle bundle) {
        if (this.b != null) {
            this.b.onStatusChanged(string2, n2, bundle);
        }
    }
}

