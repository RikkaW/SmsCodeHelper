/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.ClassLoader
 *  java.lang.Object
 */
package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

final class Fragment$SavedState$1
implements Parcelable.Creator<Fragment.SavedState> {
    Fragment$SavedState$1() {
    }

    public Fragment.SavedState createFromParcel(Parcel parcel) {
        return new Fragment.SavedState(parcel, null);
    }

    public Fragment.SavedState[] newArray(int n2) {
        return new Fragment.SavedState[n2];
    }
}

