/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.ParcelableVolumeInfo;

final class ParcelableVolumeInfo$1
implements Parcelable.Creator<ParcelableVolumeInfo> {
    ParcelableVolumeInfo$1() {
    }

    public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }

    public ParcelableVolumeInfo[] newArray(int n2) {
        return new ParcelableVolumeInfo[n2];
    }
}

