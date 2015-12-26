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
import android.support.v4.app.FragmentManagerState;

final class FragmentManagerState$1
implements Parcelable.Creator<FragmentManagerState> {
    FragmentManagerState$1() {
    }

    public FragmentManagerState createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    public FragmentManagerState[] newArray(int n2) {
        return new FragmentManagerState[n2];
    }
}

