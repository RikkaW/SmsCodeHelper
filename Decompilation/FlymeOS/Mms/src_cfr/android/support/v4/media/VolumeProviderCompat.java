/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v4.media;

import android.os.Build;
import android.support.v4.media.VolumeProviderCompat$1;
import android.support.v4.media.VolumeProviderCompatApi21;

public abstract class VolumeProviderCompat {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;
    private Callback mCallback;
    private final int mControlType;
    private int mCurrentVolume;
    private final int mMaxVolume;
    private Object mVolumeProviderObj;

    public VolumeProviderCompat(int n2, int n3, int n4) {
        this.mControlType = n2;
        this.mMaxVolume = n3;
        this.mCurrentVolume = n4;
    }

    public final int getCurrentVolume() {
        return this.mCurrentVolume;
    }

    public final int getMaxVolume() {
        return this.mMaxVolume;
    }

    public final int getVolumeControl() {
        return this.mControlType;
    }

    public Object getVolumeProvider() {
        if (this.mVolumeProviderObj != null || Build.VERSION.SDK_INT < 21) {
            return this.mVolumeProviderObj;
        }
        this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new VolumeProviderCompat$1(this));
        return this.mVolumeProviderObj;
    }

    public void onAdjustVolume(int n2) {
    }

    public void onSetVolumeTo(int n2) {
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public final void setCurrentVolume(int n2) {
        if (this.mCallback != null) {
            this.mCallback.onVolumeChanged(this);
        }
    }

    public static abstract class Callback {
        public abstract void onVolumeChanged(VolumeProviderCompat var1);
    }

}

