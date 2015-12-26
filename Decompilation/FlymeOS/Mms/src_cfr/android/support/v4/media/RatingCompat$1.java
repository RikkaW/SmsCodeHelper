/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;

final class RatingCompat$1
implements Parcelable.Creator<RatingCompat> {
    RatingCompat$1() {
    }

    public RatingCompat createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat(), null);
    }

    public RatingCompat[] newArray(int n2) {
        return new RatingCompat[n2];
    }
}

