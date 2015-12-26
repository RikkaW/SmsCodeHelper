/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.NestedScrollView;

final class NestedScrollView$SavedState$1
implements Parcelable.Creator<NestedScrollView.SavedState> {
    NestedScrollView$SavedState$1() {
    }

    public NestedScrollView.SavedState createFromParcel(Parcel parcel) {
        return new NestedScrollView.SavedState(parcel);
    }

    public NestedScrollView.SavedState[] newArray(int n2) {
        return new NestedScrollView.SavedState[n2];
    }
}

