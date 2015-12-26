/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
package android.location;

import android.location.Country;
import android.os.Parcel;
import android.os.Parcelable;

final class Country$1
implements Parcelable.Creator<Country> {
    Country$1() {
    }

    public Country createFromParcel(Parcel parcel) {
        return new Country(parcel.readString(), parcel.readInt(), parcel.readLong(), null);
    }

    public Country[] newArray(int n2) {
        return new Country[n2];
    }
}

