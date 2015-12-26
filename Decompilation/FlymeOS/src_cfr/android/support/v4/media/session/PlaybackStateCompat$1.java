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
import android.support.v4.media.session.PlaybackStateCompat;

final class PlaybackStateCompat$1
implements Parcelable.Creator<PlaybackStateCompat> {
    PlaybackStateCompat$1() {
    }

    public PlaybackStateCompat createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat(parcel, null);
    }

    public PlaybackStateCompat[] newArray(int n2) {
        return new PlaybackStateCompat[n2];
    }
}

