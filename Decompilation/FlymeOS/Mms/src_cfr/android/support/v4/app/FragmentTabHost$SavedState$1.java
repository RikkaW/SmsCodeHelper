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
import android.support.v4.app.FragmentTabHost;

final class FragmentTabHost$SavedState$1
implements Parcelable.Creator<FragmentTabHost.SavedState> {
    FragmentTabHost$SavedState$1() {
    }

    public FragmentTabHost.SavedState createFromParcel(Parcel parcel) {
        return new FragmentTabHost.SavedState(parcel, null);
    }

    public FragmentTabHost.SavedState[] newArray(int n2) {
        return new FragmentTabHost.SavedState[n2];
    }
}

