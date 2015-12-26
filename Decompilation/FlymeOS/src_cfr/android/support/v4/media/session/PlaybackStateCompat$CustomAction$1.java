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
import android.support.v4.media.session.PlaybackStateCompat$1;

final class PlaybackStateCompat$CustomAction$1
implements Parcelable.Creator<PlaybackStateCompat.CustomAction> {
    PlaybackStateCompat$CustomAction$1() {
    }

    public PlaybackStateCompat.CustomAction createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat.CustomAction(parcel, null);
    }

    public PlaybackStateCompat.CustomAction[] newArray(int n2) {
        return new PlaybackStateCompat.CustomAction[n2];
    }
}

