/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.media;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompatApi21;

class VolumeProviderCompat$1
implements VolumeProviderCompatApi21.Delegate {
    final /* synthetic */ VolumeProviderCompat this$0;

    VolumeProviderCompat$1(VolumeProviderCompat volumeProviderCompat) {
        this.this$0 = volumeProviderCompat;
    }

    @Override
    public void onAdjustVolume(int n2) {
        this.this$0.onAdjustVolume(n2);
    }

    @Override
    public void onSetVolumeTo(int n2) {
        this.this$0.onSetVolumeTo(n2);
    }
}

