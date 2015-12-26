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
import android.support.v4.media.MediaMetadataCompat;

final class MediaMetadataCompat$1
implements Parcelable.Creator<MediaMetadataCompat> {
    MediaMetadataCompat$1() {
    }

    public MediaMetadataCompat createFromParcel(Parcel parcel) {
        return new MediaMetadataCompat(parcel, null);
    }

    public MediaMetadataCompat[] newArray(int n2) {
        return new MediaMetadataCompat[n2];
    }
}

