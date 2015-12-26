/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.internal.widget.SpinnerCompat;
import android.support.v7.internal.widget.SpinnerCompat$1;

final class SpinnerCompat$SavedState$1
implements Parcelable.Creator<SpinnerCompat.SavedState> {
    SpinnerCompat$SavedState$1() {
    }

    public SpinnerCompat.SavedState createFromParcel(Parcel parcel) {
        return new SpinnerCompat.SavedState(parcel, null);
    }

    public SpinnerCompat.SavedState[] newArray(int n2) {
        return new SpinnerCompat.SavedState[n2];
    }
}

