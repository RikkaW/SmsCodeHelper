/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;

final class BackStackState$1
implements Parcelable.Creator<BackStackState> {
    BackStackState$1() {
    }

    public BackStackState createFromParcel(Parcel parcel) {
        return new BackStackState(parcel);
    }

    public BackStackState[] newArray(int n2) {
        return new BackStackState[n2];
    }
}

