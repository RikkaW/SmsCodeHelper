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

final class MediaSessionCompat$ResultReceiverWrapper$1
implements Parcelable.Creator<MediaSessionCompat.ResultReceiverWrapper> {
    MediaSessionCompat$ResultReceiverWrapper$1() {
    }

    public MediaSessionCompat.ResultReceiverWrapper createFromParcel(Parcel parcel) {
        return new MediaSessionCompat.ResultReceiverWrapper(parcel);
    }

    public MediaSessionCompat.ResultReceiverWrapper[] newArray(int n2) {
        return new MediaSessionCompat.ResultReceiverWrapper[n2];
    }
}

