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
import android.support.v7.internal.widget.AbsSpinnerCompat;

final class AbsSpinnerCompat$SavedState$1
implements Parcelable.Creator<AbsSpinnerCompat.SavedState> {
    AbsSpinnerCompat$SavedState$1() {
    }

    public AbsSpinnerCompat.SavedState createFromParcel(Parcel parcel) {
        return new AbsSpinnerCompat.SavedState(parcel);
    }

    public AbsSpinnerCompat.SavedState[] newArray(int n2) {
        return new AbsSpinnerCompat.SavedState[n2];
    }
}

