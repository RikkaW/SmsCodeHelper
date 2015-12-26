/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  java.lang.ClassLoader
 *  java.lang.Object
 */
package android.support.v4.os;

import android.os.Parcel;

public interface ParcelableCompatCreatorCallbacks<T> {
    public T createFromParcel(Parcel var1, ClassLoader var2);

    public T[] newArray(int var1);
}

