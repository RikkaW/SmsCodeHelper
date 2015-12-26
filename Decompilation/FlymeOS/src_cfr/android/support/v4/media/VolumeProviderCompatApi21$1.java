/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.VolumeProvider
 */
package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.v4.media.VolumeProviderCompatApi21;

final class VolumeProviderCompatApi21$1
extends VolumeProvider {
    final /* synthetic */ VolumeProviderCompatApi21.Delegate val$delegate;

    VolumeProviderCompatApi21$1(int n2, int n3, int n4, VolumeProviderCompatApi21.Delegate delegate) {
        this.val$delegate = delegate;
        super(n2, n3, n4);
    }

    public void onAdjustVolume(int n2) {
        this.val$delegate.onAdjustVolume(n2);
    }

    public void onSetVolumeTo(int n2) {
        this.val$delegate.onSetVolumeTo(n2);
    }
}

