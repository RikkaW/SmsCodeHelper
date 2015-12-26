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
import android.support.v7.widget.ActionMenuPresenter;

final class ActionMenuPresenter$SavedState$1
implements Parcelable.Creator<ActionMenuPresenter.SavedState> {
    ActionMenuPresenter$SavedState$1() {
    }

    public ActionMenuPresenter.SavedState createFromParcel(Parcel parcel) {
        return new ActionMenuPresenter.SavedState(parcel);
    }

    public ActionMenuPresenter.SavedState[] newArray(int n2) {
        return new ActionMenuPresenter.SavedState[n2];
    }
}

