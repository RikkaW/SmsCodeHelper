/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location;

import com.amap.api.location.AMapLocalDayWeatherForecast;
import com.amap.api.location.core.AMapLocException;
import java.util.List;

public class AMapLocalWeatherForecast {
    private String a;
    private List<AMapLocalDayWeatherForecast> b;
    private AMapLocException c;

    void a(AMapLocException aMapLocException) {
        this.c = aMapLocException;
    }

    void a(String string2) {
        this.a = string2;
    }

    void a(List<AMapLocalDayWeatherForecast> list) {
        this.b = list;
    }

    public AMapLocException getAMapException() {
        return this.c;
    }

    public String getReportTime() {
        return this.a;
    }

    public List<AMapLocalDayWeatherForecast> getWeatherForecast() {
        return this.b;
    }
}

