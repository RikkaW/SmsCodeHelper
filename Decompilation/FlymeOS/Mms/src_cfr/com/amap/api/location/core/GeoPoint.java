/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.core.f;

public class GeoPoint
implements Parcelable {
    public static final Parcelable.Creator<GeoPoint> CREATOR = new f();
    private long a = Long.MIN_VALUE;
    private long b = Long.MIN_VALUE;
    private double c = Double.MIN_VALUE;
    private double d = Double.MIN_VALUE;

    public GeoPoint() {
        this.a = 0;
        this.b = 0;
    }

    public GeoPoint(int n2, int n3) {
        this.a = n2;
        this.b = n3;
    }

    public GeoPoint(long l2, long l3) {
        this.a = l2;
        this.b = l3;
    }

    private GeoPoint(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readLong();
    }

    /* synthetic */ GeoPoint(Parcel parcel, f f2) {
        this(parcel);
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) return false;
        object = (GeoPoint)object;
        if (this.c != object.c) return false;
        if (this.d != object.d) return false;
        if (this.a != object.a) return false;
        if (this.b != object.b) return false;
        return true;
    }

    public int getLatitudeE6() {
        return (int)this.a;
    }

    public int getLongitudeE6() {
        return (int)this.b;
    }

    public int hashCode() {
        return (int)(this.d * 7.0 + this.c * 11.0);
    }

    public String toString() {
        return "" + this.a + "," + this.b;
    }

    public void writeToParcel(Parcel parcel, int n2) {
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
    }
}

