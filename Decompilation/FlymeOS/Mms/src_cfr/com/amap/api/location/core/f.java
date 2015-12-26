/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.core.GeoPoint;

final class f
implements Parcelable.Creator<GeoPoint> {
    f() {
    }

    public GeoPoint a(Parcel parcel) {
        return new GeoPoint(parcel, null);
    }

    public GeoPoint[] a(int n2) {
        return new GeoPoint[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

