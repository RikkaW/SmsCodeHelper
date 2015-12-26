/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.Log
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManagerImpl;
import android.support.v4.app.FragmentState$1;
import android.util.Log;

final class FragmentState
implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new FragmentState$1();
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final int mIndex;
    Fragment mInstance;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    /*
     * Enabled aggressive block sorting
     */
    public FragmentState(Parcel parcel) {
        boolean bl2 = true;
        this.mClassName = parcel.readString();
        this.mIndex = parcel.readInt();
        boolean bl3 = parcel.readInt() != 0;
        this.mFromLayout = bl3;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        bl3 = parcel.readInt() != 0;
        this.mRetainInstance = bl3;
        bl3 = parcel.readInt() != 0 ? bl2 : false;
        this.mDetached = bl3;
        this.mArguments = parcel.readBundle();
        this.mSavedFragmentState = parcel.readBundle();
    }

    public FragmentState(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mIndex = fragment.mIndex;
        this.mFromLayout = fragment.mFromLayout;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mDetached = fragment.mDetached;
        this.mArguments = fragment.mArguments;
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentActivity fragmentActivity, Fragment fragment) {
        if (this.mInstance != null) {
            return this.mInstance;
        }
        if (this.mArguments != null) {
            this.mArguments.setClassLoader(fragmentActivity.getClassLoader());
        }
        this.mInstance = Fragment.instantiate((Context)fragmentActivity, this.mClassName, this.mArguments);
        if (this.mSavedFragmentState != null) {
            this.mSavedFragmentState.setClassLoader(fragmentActivity.getClassLoader());
            this.mInstance.mSavedFragmentState = this.mSavedFragmentState;
        }
        this.mInstance.setIndex(this.mIndex, fragment);
        this.mInstance.mFromLayout = this.mFromLayout;
        this.mInstance.mRestored = true;
        this.mInstance.mFragmentId = this.mFragmentId;
        this.mInstance.mContainerId = this.mContainerId;
        this.mInstance.mTag = this.mTag;
        this.mInstance.mRetainInstance = this.mRetainInstance;
        this.mInstance.mDetached = this.mDetached;
        this.mInstance.mFragmentManager = fragmentActivity.mFragments;
        if (FragmentManagerImpl.DEBUG) {
            Log.v((String)"FragmentManager", (String)("Instantiated fragment " + this.mInstance));
        }
        return this.mInstance;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n2) {
        int n3 = 1;
        parcel.writeString(this.mClassName);
        parcel.writeInt(this.mIndex);
        n2 = this.mFromLayout ? 1 : 0;
        parcel.writeInt(n2);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        n2 = this.mRetainInstance ? 1 : 0;
        parcel.writeInt(n2);
        n2 = this.mDetached ? n3 : 0;
        parcel.writeInt(n2);
        parcel.writeBundle(this.mArguments);
        parcel.writeBundle(this.mSavedFragmentState);
    }
}

