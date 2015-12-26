/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location;

import com.amap.api.location.core.AMapLocException;

public class AMapLocalWeatherLive {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private AMapLocException g;
    private String h;
    private String i;
    private String j;

    void a(AMapLocException aMapLocException) {
        this.g = aMapLocException;
    }

    void a(String string2) {
        this.a = string2;
    }

    void b(String string2) {
        this.b = string2;
    }

    void c(String string2) {
        this.c = string2;
    }

    void d(String string2) {
        this.d = string2;
    }

    void e(String string2) {
        this.e = string2;
    }

    void f(String string2) {
        this.f = string2;
    }

    public AMapLocException getAMapException() {
        return this.g;
    }

    public String getCity() {
        return this.h;
    }

    public String getCityCode() {
        return this.j;
    }

    public String getHumidity() {
        return this.e;
    }

    public String getProvince() {
        return this.i;
    }

    public String getReportTime() {
        return this.f;
    }

    public String getTemperature() {
        return this.b;
    }

    public String getWeather() {
        return this.a;
    }

    public String getWindDir() {
        return this.c;
    }

    public String getWindPower() {
        return this.d;
    }

    public void setCity(String string2) {
        this.h = string2;
    }

    public void setCityCode(String string2) {
        this.j = string2;
    }

    public void setProvince(String string2) {
        this.i = string2;
    }
}

