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
import android.support.v4.media.session.MediaSessionCompat;

final class MediaSessionCompat$QueueItem$1
implements Parcelable.Creator<MediaSessionCompat.QueueItem> {
    MediaSessionCompat$QueueItem$1() {
    }

    public MediaSessionCompat.QueueItem createFromParcel(Parcel parcel) {
        return new MediaSessionCompat.QueueItem(parcel, null);
    }

    public MediaSessionCompat.QueueItem[] newArray(int n2) {
        return new MediaSessionCompat.QueueItem[n2];
    }
}

