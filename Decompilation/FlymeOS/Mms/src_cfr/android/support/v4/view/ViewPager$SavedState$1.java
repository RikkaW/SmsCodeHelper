/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  java.lang.ClassLoader
 *  java.lang.Object
 */
package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.ViewPager;

final class ViewPager$SavedState$1
implements ParcelableCompatCreatorCallbacks<ViewPager.SavedState> {
    ViewPager$SavedState$1() {
    }

    @Override
    public ViewPager.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager.SavedState(parcel, classLoader);
    }

    public ViewPager.SavedState[] newArray(int n2) {
        return new ViewPager.SavedState[n2];
    }
}

