/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.IBinder
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.ClassLoader
 *  java.lang.Object
 */
package android.support.v4.media.session;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;

final class MediaSessionCompat$Token$1
implements Parcelable.Creator<MediaSessionCompat.Token> {
    MediaSessionCompat$Token$1() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public MediaSessionCompat.Token createFromParcel(Parcel parcel) {
        if (Build.VERSION.SDK_INT >= 21) {
            parcel = parcel.readParcelable(null);
            do {
                return new MediaSessionCompat.Token((Object)parcel);
                break;
            } while (true);
        }
        parcel = parcel.readStrongBinder();
        return new MediaSessionCompat.Token((Object)parcel);
    }

    public MediaSessionCompat.Token[] newArray(int n2) {
        return new MediaSessionCompat.Token[n2];
    }
}

