/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$ClassLoaderCreator
 *  java.lang.ClassLoader
 *  java.lang.Object
 */
package android.support.v4.os;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

class ParcelableCompatCreatorHoneycombMR2<T>
implements Parcelable.ClassLoaderCreator<T> {
    private final ParcelableCompatCreatorCallbacks<T> mCallbacks;

    public ParcelableCompatCreatorHoneycombMR2(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
        this.mCallbacks = parcelableCompatCreatorCallbacks;
    }

    public T createFromParcel(Parcel parcel) {
        return this.mCallbacks.createFromParcel(parcel, null);
    }

    public T createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return this.mCallbacks.createFromParcel(parcel, classLoader);
    }

    public T[] newArray(int n2) {
        return this.mCallbacks.newArray(n2);
    }
}

