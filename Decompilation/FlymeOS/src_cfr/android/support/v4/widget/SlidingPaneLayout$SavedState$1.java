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
import android.support.v4.widget.SlidingPaneLayout;

final class SlidingPaneLayout$SavedState$1
implements Parcelable.Creator<SlidingPaneLayout.SavedState> {
    SlidingPaneLayout$SavedState$1() {
    }

    public SlidingPaneLayout.SavedState createFromParcel(Parcel parcel) {
        return new SlidingPaneLayout.SavedState(parcel, null);
    }

    public SlidingPaneLayout.SavedState[] newArray(int n2) {
        return new SlidingPaneLayout.SavedState[n2];
    }
}

