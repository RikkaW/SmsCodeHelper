/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Criteria
 *  android.location.LocationManager
 *  android.location.LocationProvider
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.location;

import android.location.Criteria;
import android.location.LocationManager;
import android.location.LocationProvider;

public class LocationProviderProxy {
    public static final String AMapNetwork = "lbs";
    public static final int AVAILABLE = 2;
    public static final int OUT_OF_SERVICE = 0;
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    private LocationManager a;
    private String b;

    protected LocationProviderProxy(LocationManager locationManager, String string2) {
        this.a = locationManager;
        this.b = string2;
    }

    private LocationProvider a() {
        try {
            if (this.a != null) {
                LocationProvider locationProvider = this.a.getProvider(this.b);
                return locationProvider;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }

    static LocationProviderProxy a(LocationManager locationManager, String string2) {
        return new LocationProviderProxy(locationManager, string2);
    }

    public int getAccuracy() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return 2;
            }
        }
        try {
            if (this.a() != null) {
                int n2 = this.a().getAccuracy();
                return n2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return -1;
    }

    public String getName() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return "lbs";
            }
        }
        try {
            if (this.a() != null) {
                String string2 = this.a().getName();
                return string2;
            }
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return "null";
    }

    public int getPowerRequirement() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return 2;
            }
        }
        try {
            if (this.a() != null) {
                int n2 = this.a().getPowerRequirement();
                return n2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return -1;
    }

    public boolean hasMonetaryCost() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return false;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().hasMonetaryCost();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean meetsCriteria(Criteria var1_1) {
        var2_3 = true;
        if ("lbs" == null) ** GOTO lbl12
        try {
            if ("lbs".equals((Object)this.b)) {
                if (var1_1 == null) {
                    return true;
                }
                if (var1_1.isAltitudeRequired() != false) return false;
                if (var1_1.isBearingRequired() != false) return false;
                if (var1_1.isSpeedRequired() != false) return false;
                if (var1_1.getAccuracy() != 1) return var2_3;
                return false;
            }
lbl12: // 3 sources:
            if (this.a() == null) return false;
            return this.a().meetsCriteria(var1_1);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        return false;
    }

    public boolean requiresCell() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return true;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().requiresCell();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return true;
    }

    public boolean requiresNetwork() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return true;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().requiresNetwork();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return true;
    }

    public boolean requiresSatellite() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return false;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().requiresNetwork();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return true;
    }

    public boolean supportsAltitude() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return false;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().supportsAltitude();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return false;
    }

    public boolean supportsBearing() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return false;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().supportsBearing();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return false;
    }

    public boolean supportsSpeed() {
        block5 : {
            if ("lbs" != null) {
                if (!"lbs".equals((Object)this.b)) break block5;
                return false;
            }
        }
        try {
            if (this.a() != null) {
                boolean bl2 = this.a().supportsSpeed();
                return bl2;
            }
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
        }
        return false;
    }
}

