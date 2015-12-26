/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v4.media;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaDescriptionCompatApi21;

final class MediaDescriptionCompat$1
implements Parcelable.Creator<MediaDescriptionCompat> {
    MediaDescriptionCompat$1() {
    }

    public MediaDescriptionCompat createFromParcel(Parcel parcel) {
        if (Build.VERSION.SDK_INT < 21) {
            return new MediaDescriptionCompat(parcel, null);
        }
        return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(parcel));
    }

    public MediaDescriptionCompat[] newArray(int n2) {
        return new MediaDescriptionCompat[n2];
    }
}

