/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.amap.api.location;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherLive;

public interface AMapLocalWeatherListener {
    public void onWeatherForecaseSearched(AMapLocalWeatherForecast var1);

    public void onWeatherLiveSearched(AMapLocalWeatherLive var1);
}

