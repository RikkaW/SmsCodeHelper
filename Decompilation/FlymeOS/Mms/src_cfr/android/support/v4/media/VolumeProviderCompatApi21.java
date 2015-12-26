/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.VolumeProvider
 *  java.lang.Object
 */
package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.v4.media.VolumeProviderCompatApi21$1;

class VolumeProviderCompatApi21 {
    VolumeProviderCompatApi21() {
    }

    public static Object createVolumeProvider(int n2, int n3, int n4, Delegate delegate) {
        return new VolumeProviderCompatApi21$1(n2, n3, n4, delegate);
    }

    public static void setCurrentVolume(Object object, int n2) {
        ((VolumeProvider)object).setCurrentVolume(n2);
    }

    public static interface Delegate {
        public void onAdjustVolume(int var1);

        public void onSetVolumeTo(int var1);
    }

}

