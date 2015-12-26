/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.LocationListener
 *  java.lang.Object
 */
package com.amap.api.location;

import android.location.LocationListener;
import com.amap.api.location.AMapLocation;

public interface AMapLocationListener
extends LocationListener {
    public void onLocationChanged(AMapLocation var1);
}

