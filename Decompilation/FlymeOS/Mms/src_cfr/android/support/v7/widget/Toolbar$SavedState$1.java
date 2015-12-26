/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;

final class Toolbar$SavedState$1
implements Parcelable.Creator<Toolbar.SavedState> {
    Toolbar$SavedState$1() {
    }

    public Toolbar.SavedState createFromParcel(Parcel parcel) {
        return new Toolbar.SavedState(parcel);
    }

    public Toolbar.SavedState[] newArray(int n2) {
        return new Toolbar.SavedState[n2];
    }
}

