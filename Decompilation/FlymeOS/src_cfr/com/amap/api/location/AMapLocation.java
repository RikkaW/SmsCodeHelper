/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.location.Location
 *  java.lang.String
 */
package com.amap.api.location;

import android.location.Location;
import com.amap.api.location.core.AMapLocException;

public class AMapLocation
extends Location {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private AMapLocException m = new AMapLocException();

    public AMapLocation(Location location) {
        super(location);
    }

    public AMapLocation(String string2) {
        super(string2);
    }

    void a(String string2) {
        this.h = string2;
    }

    public AMapLocException getAMapException() {
        return this.m;
    }

    public String getAdCode() {
        return this.e;
    }

    public String getAddress() {
        return this.i;
    }

    public String getCity() {
        return this.b;
    }

    public String getCityCode() {
        return this.d;
    }

    public String getCountry() {
        return this.j;
    }

    public String getDistrict() {
        return this.c;
    }

    public String getFloor() {
        return this.g;
    }

    public String getPoiId() {
        return this.f;
    }

    public String getPoiName() {
        return this.l;
    }

    public String getProvince() {
        return this.a;
    }

    public String getRoad() {
        return this.k;
    }

    public String getStreet() {
        return this.h;
    }

    public void setAMapException(AMapLocException aMapLocException) {
        this.m = aMapLocException;
    }

    public void setAdCode(String string2) {
        this.e = string2;
    }

    public void setAddress(String string2) {
        this.i = string2;
    }

    public void setCity(String string2) {
        this.b = string2;
    }

    public void setCityCode(String string2) {
        this.d = string2;
    }

    public void setCountry(String string2) {
        this.j = string2;
    }

    public void setDistrict(String string2) {
        this.c = string2;
    }

    public void setFloor(String string2) {
        this.g = string2;
    }

    public void setPoiId(String string2) {
        this.f = string2;
    }

    public void setPoiName(String string2) {
        this.l = string2;
    }

    public void setProvince(String string2) {
        this.a = string2;
    }

    public void setRoad(String string2) {
        this.k = string2;
    }
}

