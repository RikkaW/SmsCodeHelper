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
import android.support.v4.app.FragmentState;

final class FragmentState$1
implements Parcelable.Creator<FragmentState> {
    FragmentState$1() {
    }

    public FragmentState createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    public FragmentState[] newArray(int n2) {
        return new FragmentState[n2];
    }
}

